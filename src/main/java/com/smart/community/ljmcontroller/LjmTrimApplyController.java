package com.smart.community.ljmcontroller;

import com.smart.community.activiti.Activiti;
import com.smart.community.ljmbean.*;
import com.smart.community.ljmservice.LjmBackstageLoginService;
import com.smart.community.ljmservice.LjmDeskService;
import com.smart.community.tool.LjmTool;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ljm
 */
@Controller
public class LjmTrimApplyController
{
	@Resource
	private Activiti trimActiviti;
	@Resource
	private LjmBackstageLoginService backstageLoginService;
	@Resource
	private LjmDeskService deskService;

	/**
	 * 业主装修申请页面
	 * @return 装修申请页面
	 */
	@RequestMapping("/toOwnerTrim.view")
	public ModelAndView toOwnerTrimApply()
	{
		return new ModelAndView("ljm_owner_trim_apply");
	}

	/**
	 * 创建启动流程实例
	 * @return 信息
	 */
	@RequestMapping("/createProcessesForTrim.action")
	@ResponseBody
	public LayuiTableBean createProcessesForTrim ( String applyName , String applyPhone , String workDate, String remark , HttpServletRequest request)
	{
		List<OwnerBean> list = (List<OwnerBean>) request.getSession().getAttribute("owners");
		String room = list.get(0).getOwnerRoom();
		//参数设置，在启动时传入流程
		Map<String,Object> map = new HashMap<>();
		map.put("roomNum",room);map.put("applyName",applyName);map.put("applyPhone",applyPhone);
		map.put("workDate",workDate);map.put("remark",remark);map.put("serviceName","装修申请");
		ApplyRecordBean recordBean = new ApplyRecordBean();
		recordBean.setApplyName("装修申请");recordBean.setApplyRoom(room);recordBean.setApplyStatus("待提交");
		recordBean.setApplyTime(LjmTool.getTodayDate());
		int result = deskService.insertForApplyRecord(recordBean);
		if (result > 0 )
		{
			String applyId = deskService.selectForGetLastApply(room);
			map.put("applyId",applyId);
			ProcessInstance pi = trimActiviti.createActiviti("trimApply", "processes/TrimApply.bpmn","processes/TrimApply.png",map);
			if (pi!=null)
			{
				LayuiTableBean layuiTableBean = new LayuiTableBean();
				layuiTableBean.setMsg("添加成功");
				return layuiTableBean;
			}
		} else {
			LayuiTableBean layuiTableBean = new LayuiTableBean();
			layuiTableBean.setMsg("添加失败");
			return layuiTableBean;
		}
		return null;
	}

	/**
	 * 查找流程
	 * @param request request
	 * @return layui接口
	 */
	@RequestMapping("/findProcessesForTrim.action")
	@ResponseBody
	public LayuiTableBean findOwnerProcessesForTrim(HttpServletRequest request , String taskName)
	{
		System.out.println("tasKnAME:"+taskName);
		List<ActivityTaskBean> activityTaskBeans;
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		List<OwnerBean> list = (List<OwnerBean>) request.getSession().getAttribute("owners");
		StaffBean staffBean = (StaffBean) request.getSession().getAttribute("staffBean");
		if (staffBean!=null){
			RoleBean roleBean = backstageLoginService.getPersonRole(staffBean.getStaffId());
			System.out.println(roleBean.getRoleName());
			activityTaskBeans = trimActiviti.findPersonTask(roleBean.getRoleName());
			List<ActivityTaskBean> results = new ArrayList<>();
			for (ActivityTaskBean activityTaskBean : activityTaskBeans)
			{
				if (taskName.equals(activityTaskBean.getServiceName()))
				{
					results.add(activityTaskBean);
				}
			}
			layuiTableBean.setData(results);
		} else if (list!=null&&list.size()>0){
			String room = list.get(0).getOwnerRoom();
			activityTaskBeans = trimActiviti.findPersonTask(room);
			List<ActivityTaskBean> results = new ArrayList<>();
			for (ActivityTaskBean activityTaskBean : activityTaskBeans)
			{
				if (taskName.equals(activityTaskBean.getServiceName()))
				{
					results.add(activityTaskBean);
				}
			}
			layuiTableBean.setData(results);
		}
		layuiTableBean.setCode(0);
		return layuiTableBean;
	}

	/**
	 * 删除撤回流程
	 * @param taskId 任务id
	 * @return layui接口
	 */
	@RequestMapping("/deleteProcessesTask.action")
	@ResponseBody
	public LayuiTableBean deletePersonProcessesTask(String taskId)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		Map<String,Object> map = trimActiviti.getVariables(taskId);
		String applyId = (String) map.get("applyId");
		int result = deskService.deleteForApplyRecord(applyId);
		if (result>0)
		{
			trimActiviti.deleteProcessDefinition(taskId);
			layuiTableBean.setMsg("删除成功");
		} else {
			layuiTableBean.setMsg("系统忙，请重试");
		}
		return layuiTableBean;
	}

	/**
	 * 完成任务
	 * @param taskId 任务id
	 * @return layui接口
	 */
	@RequestMapping("/submitProcessesTask.action")
	@ResponseBody
	public LayuiTableBean submitOwnerProcessesTask(String taskId,HttpServletRequest request)
	{
		List<OwnerBean> owners = (List) request.getSession().getAttribute("owners");
		String status = "待受理";
		Map<String,Object> map = trimActiviti.getVariables(taskId);
		String applyId = (String) map.get("applyId");
		int result = deskService.updateForApplyStatus(status,applyId);
		if (result>0)
		{
			trimActiviti.completePersonTask(taskId);
			LayuiTableBean layuiTableBean = new LayuiTableBean();
			layuiTableBean.setMsg("提交成功");
			return layuiTableBean;
		}
		return null;
	}

	/**
	 * 装修审核页面跳转
	 * @return ModelAndView
	 */
	@RequestMapping("/toCustomerTrim.view")
	public ModelAndView toCustomerTrim ()
	{
		return new ModelAndView("ljm_review_trim");
	}

	/**
	 * 角色完成任务
	 * @param taskId 任务id
	 * @param isAgree 是否同意
	 * @return 信息
	 */
	@RequestMapping("/completeTaskWhitParameter.action")
	@ResponseBody
	public LayuiTableBean returnTask(String taskId , String isAgree,HttpServletRequest request)
	{
		String status = "待受理";
		StaffBean staff = (StaffBean) request.getSession().getAttribute("staffBean");
		if (staff!=null)
		{
			RoleBean roleBean = backstageLoginService.getPersonRole(staff.getStaffId());
			if ("客服".equals(roleBean.getRoleName()))
			{
				if ("同意".equals(isAgree))
				{
					status = "已受理，待审核";
				} else {
					status = "已受理，驳回，时间不合适";
				}
			} else if ("后勤".equals(roleBean.getRoleName())){
				if ("同意".equals(isAgree))
				{
					status = "同意申请";
				} else {
					status = "否决申请";
				}
			}
		}
		Map<String,Object> map = trimActiviti.getVariables(taskId);
		String applyId = (String) map.get("applyId");
		int result = deskService.updateForApplyStatus(status,applyId);
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		if (result>0)
		{
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("message",isAgree);
			trimActiviti.completePersonTask(taskId,paramMap);
			layuiTableBean.setMsg("成功");
			layuiTableBean.setCode(0);
		} else {
			layuiTableBean.setMsg("系统忙，请重试");
		}
		return layuiTableBean;
	}

}
