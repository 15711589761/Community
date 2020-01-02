package com.smart.community.ljmcontroller;

import com.smart.community.activiti.Activiti;
import com.smart.community.ljmbean.*;
import com.smart.community.ljmservice.LjmBackstageLoginService;
import com.smart.community.ljmservice.LjmCustomerService;
import com.smart.community.ljmservice.LjmDeskService;
import com.smart.community.tool.LjmTool;
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

/**
 * ljm
 *
 * @author ljm
 */
@Controller
public class LjmDispatchController
{

	@Resource
	private Activiti dispatchActiviti;
	@Resource
	private LjmBackstageLoginService backstageLoginService;
	@Resource
	private LjmCustomerService customerService;
	@Resource
	private LjmDeskService deskService;

	/**
	 * 跳转到在线报修
	 *
	 * @return 界面
	 */
	@RequestMapping("/toDispatch.view")
	public ModelAndView toDispatchView()
	{
		return new ModelAndView("ljm_owner_dispatch_apply");
	}

	/**
	 * 创建启动流程实例
	 *
	 * @return 信息
	 */
	@RequestMapping("/createProcessesForDispatch.action")
	@ResponseBody
	public LayuiTableBean createProcessesForDispatch( String applyName, String applyPhone, String remark, HttpServletRequest request )
	{
		List<OwnerBean> list = (List<OwnerBean>) request.getSession().getAttribute("owners");
		String room = list.get(0).getOwnerRoom();
		//参数设置，在启动时传入流程
		Map<String, Object> map = new HashMap<>();
		map.put("applyPhone", applyPhone);
		map.put("remark", remark);
		map.put("roomNum", room);
		map.put("applyName", applyName);
		map.put("serviceName","派工服务");
		ApplyRecordBean recordBean = new ApplyRecordBean();
		recordBean.setApplyName("派工服务");recordBean.setApplyRoom(room);recordBean.setApplyStatus("待提交");
		recordBean.setApplyTime(LjmTool.getTodayDate());
		int result = deskService.insertForApplyRecord(recordBean);
		if (result>0)
		{
			String applyId = deskService.selectForGetLastApply(room);
			map.put("applyId",applyId);
			ProcessInstance pi = dispatchActiviti.createActiviti("dispatchApply", "processes/DispatchApply.bpmn", "processes/DispatchApply.png", map);
			if (pi != null)
			{
				LayuiTableBean layuiTableBean = new LayuiTableBean();
				layuiTableBean.setMsg("success");
				return layuiTableBean;
			}
		}else {
			LayuiTableBean layuiTableBean = new LayuiTableBean();
			layuiTableBean.setMsg("添加失败");
			return layuiTableBean;
		}

		return null;
	}

	/**
	 * 客服维修申请审核页面跳转
	 *
	 * @return ModelAndView
	 */
	@RequestMapping("/toCustomerDispatch.view")
	public ModelAndView toCustomerDispatch()
	{
		return new ModelAndView("ljm_customer_dispatch");
	}

	/**
	 * 后勤维修申请审核页面跳转
	 *
	 * @return ModelAndView
	 */
	@RequestMapping("/toLogisticsDispatch.view")
	public ModelAndView toLogisticsDispatch()
	{
		return new ModelAndView("ljm_logistics_dispatch");
	}

	/**
	 * 派工人员下拉框界面
	 * @return 界面
	 */
	@RequestMapping("/toDispatchPersonGet.view")
	public ModelAndView toDispatchPersonGet()
	{
		ModelAndView modelAndView = new ModelAndView("ljm_select_dispatch_work");
		List<DispatchPersonBean> workPersons = customerService.forGetDispatchList();
		modelAndView.addObject("personList",workPersons);
		return modelAndView;
	}

	/**
	 * 角色完成任务
	 *
	 * @param taskId     任务id
	 * @param isAgree    执行人
	 * @return 信息
	 */
	@RequestMapping("/completeTaskWhitParameterForDispatch.action")
	@ResponseBody
	public LayuiTableBean completeTaskWhitParameterForDispatch( String taskId, String isAgree, String workId )
	{
		Map<String,Object> map = dispatchActiviti.getVariables(taskId);
		String applyId = (String) map.get("applyId");
		String status = null;
		if ("派工".equals(isAgree))
		{
			status = "已派遣工人";
		} else {
			status = "短期内没有工人了";
		}
		int result = deskService.updateForApplyStatus(status,applyId);
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		if (result>0)
		{
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("message", isAgree);
			DispatchPersonBean dispatchPersonBean = customerService.forGetSelectedDispatchPerson(workId);
			paramMap.put("workPerson", dispatchPersonBean.getWorkPerson());
			paramMap.put("workTel", dispatchPersonBean.getWorkTel());
			dispatchActiviti.completePersonTask(taskId, paramMap);
			layuiTableBean.setMsg("success");
			layuiTableBean.setCode(0);
		}else {
			layuiTableBean.setMsg("系统忙，请重试");
		}
		return layuiTableBean;
	}
}
