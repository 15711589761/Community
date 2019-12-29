package com.smart.community.ljmcontroller;

import com.smart.community.activiti.Activiti;
import com.smart.community.ljmbean.*;
import com.smart.community.ljmservice.LjmBackstageLoginService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LjmTrimApplyController
{
	@Resource
	private Activiti trimActiviti;
	@Resource
	private LjmBackstageLoginService backstageLoginService;

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
	 * @return
	 */
	@RequestMapping("/createProcessesForTrim.action")
	@ResponseBody
	public LayuiTableBean createProcessesForTrim ( String applyName , String applyPhone , String workDate, String remark , HttpServletRequest request)
	{
		List<OwnerBean> list = (List<OwnerBean>) request.getSession().getAttribute("owners");
		String room = list.get(0).getOwnerRoom();
		Map<String,Object> map = new HashMap<>();
		map.put("roomNum",room);map.put("applyName",applyName);map.put("applyPhone",applyPhone);
		map.put("workDate",workDate);map.put("remark",remark);
		ProcessInstance pi = trimActiviti.createActiviti("trimApply", "processes/TrimApply.bpmn","processes/TrimApply.png",map);
		if (pi!=null)
		{
			LayuiTableBean layuiTableBean = new LayuiTableBean();
			layuiTableBean.setMsg("success");
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
	public LayuiTableBean findOwnerProcessesForTrim(HttpServletRequest request)
	{
		List<ActivityTaskBean> activityTaskBeans;
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		List<OwnerBean> list = (List<OwnerBean>) request.getSession().getAttribute("owners");
		StaffBean staffBean = (StaffBean) request.getSession().getAttribute("staffBean");
		if (staffBean!=null){
			RoleBean roleBean = backstageLoginService.getPersonRole(staffBean.getStaffId());
			System.out.println(roleBean.getRoleName());
			activityTaskBeans = trimActiviti.findPersonTask(roleBean.getRoleName());
			layuiTableBean.setData(activityTaskBeans);
		} else if (list!=null&&list.size()>0){
			String room = list.get(0).getOwnerRoom();
			activityTaskBeans = trimActiviti.findPersonTask(room);
			layuiTableBean.setData(activityTaskBeans);
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
		trimActiviti.deleteProcessDefinition(taskId);
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		layuiTableBean.setMsg("success");
		return layuiTableBean;
	}

	/**
	 * 完成任务
	 * @param taskId 任务id
	 * @return layui接口
	 */
	@RequestMapping("/submitProcessesTask.action")
	@ResponseBody
	public LayuiTableBean submitOwnerProcessesTask(String taskId)
	{
		trimActiviti.completePersonTask(taskId);
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		layuiTableBean.setMsg("success");
		return layuiTableBean;
	}

	/**
	 * 装修审核页面跳转
	 * @return ModelAndView
	 */
	@RequestMapping("/toCustomerTrim.action")
	public ModelAndView toCustomerTrim ()
	{
		return new ModelAndView("ljm_review_trim");
	}

	@RequestMapping("/completeTaskWhitParameter.action")
	@ResponseBody
	public LayuiTableBean returnTask(String taskId , String isAgree ,HttpServletRequest request)
	{
		System.out.println("带参数完成任务");
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("message",isAgree);
		trimActiviti.completePersonTask(taskId,paramMap);
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		layuiTableBean.setMsg("success");
		layuiTableBean.setCode(0);
		return layuiTableBean;
	}

	/**
	 * 历史页面跳转
	 * @return 界面
	 */
	@RequestMapping("/toFindProcessesForTrimHistory.view")
	public ModelAndView toFindProcessesForTrimHistoryView()
	{
		return new ModelAndView("ljm_trim_apply_history");
	}

	/**
	 * 查看某人的历史记录
	 * @param roomNum 办理人
	 * @param page page
	 * @param limit limit
	 * @return layui接口
	 */
	@RequestMapping("/findProcessesForTrimHistory.action")
	@ResponseBody
	public LayuiTableBean findProcessesForTrimHistory(String roomNum , String page, String limit)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		if (null!=roomNum&&!"".equals(roomNum))
		{
			int n = (Integer.parseInt(page)-1)*2;
			int m = Integer.parseInt(limit);
			List<ActivityTaskBean> list = trimActiviti.getHistoryRecord(roomNum,n,m);
			int count = trimActiviti.getHistoryRecord(roomNum);
			if (list!=null && list.size()>0)
			{
				layuiTableBean.setCode(0);
				layuiTableBean.setData(list);
				layuiTableBean.setCount(count);
			} else {
				layuiTableBean.setCode(1);
				layuiTableBean.setMsg("没有任务");
			}
		} else {
			layuiTableBean.setCode(1);
			layuiTableBean.setMsg("通过搜索办理人查询");
		}
		return layuiTableBean;
	}
}
