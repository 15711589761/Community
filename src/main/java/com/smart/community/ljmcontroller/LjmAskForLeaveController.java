package com.smart.community.ljmcontroller;

import com.smart.community.activiti.Activiti;
import com.smart.community.ljmbean.AskForLeaveBean;
import com.smart.community.ljmbean.LayuiTableBean;
import com.smart.community.ljmbean.RoleBean;
import com.smart.community.ljmbean.StaffBean;
import com.smart.community.ljmservice.ActivitiService;
import com.smart.community.ljmservice.LeaveService;
import com.smart.community.ljmservice.LjmBackstageLoginService;
import com.smart.community.tool.LjmTool;
import com.smart.community.wsyaspects.Log;
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
public class LjmAskForLeaveController
{
	@Resource
	private Activiti activiti;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private LeaveService leaveService;
	@Resource
	private LjmBackstageLoginService backstageLoginService;

	/**
	 * 跳转请假申请界面
	 * @return 界面
	 */
	@RequestMapping("/toAskForLeaveApply.view")
	public ModelAndView toAskForLeaveApply()
	{
		return new ModelAndView("ljm_ask_for_leave_apply");
	}

	/**
	 * 跳转请假审核界面
	 * @return 界面
	 */
	@RequestMapping("/toAskForLeaveReview.view")
	public ModelAndView toAskForLeaveReview()
	{
		return new ModelAndView("ljm_ask_for_leave_review");
	}

	/**
	 * 跳转本人请假申请查看
	 * @return 界面
	 */
	@RequestMapping("/toAskForLeaveTable.view")
	public ModelAndView toAskForLeaveTable()
	{
		return new ModelAndView("ljm_leave_table");
	}

	/**
	 * 请假申请查看
	 * @param request request
	 * @return layui接口
	 */
	@RequestMapping("/forShowAskForLeaveTable.action")
	@ResponseBody
	public LayuiTableBean forShowAskForLeaveTable( HttpServletRequest request )
	{
		StaffBean staffBean = (StaffBean) request.getSession().getAttribute("staffBean");
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		List<AskForLeaveBean> data = leaveService.selectForShowLeaveTable(staffBean.getStaffName());
		layuiTableBean.setCount(data.size());
		layuiTableBean.setData(data);
		layuiTableBean.setCode(0);
		return layuiTableBean;
	}

	/**
	 * 创建启动流程实例
	 * @return 信息
	 */
	@RequestMapping("/createProcessesForLeave.action")
	@ResponseBody
	public LayuiTableBean createProcessesLeave (  String leaveDate , String leaveDay, String leaveContent , HttpServletRequest request)
	{
		StaffBean staffBean = (StaffBean) request.getSession().getAttribute("staffBean");
		//参数设置，在启动时传入流程
		Map<String,Object> map = new HashMap<>();
		String staff = staffBean.getStaffName();
		map.put("applyName",staff);
		map.put("startDate",leaveDate);
		map.put("leaveDay",leaveDay);
		map.put("content",leaveContent);
		map.put("serviceName","请假申请");
		int result = leaveService.insertForAddLeaveRecord(staff,leaveDate,leaveDay,leaveContent);
		if (result > 0 )
		{
			int applyId = leaveService.selectForGetLastRecord(staff);
			map.put("applyId",applyId);
			ProcessInstance pi = activiti.createActiviti("askForLeave", "processes/AskForLeave.bpmn","processes/AskForLeave.png",map);
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
	@RequestMapping("/findProcessesForLeave.action")
	@ResponseBody
	public LayuiTableBean findOwnerProcessesForLeave(HttpServletRequest request)
	{
		String taskName = "请假申请";
		LayuiTableBean layuiTableBean = activitiService.filterLeaveTask(request,taskName);
		layuiTableBean.setCode(0);
		return layuiTableBean;
	}

	/**
	 * 删除撤回流程
	 * @param taskId 任务id
	 * @return layui接口
	 */
	@RequestMapping("/deleteProcessesLeaveTask.action")
	@ResponseBody
	public LayuiTableBean deletePersonProcessesTask(String taskId)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		Map<String,Object> map = activiti.getVariables(taskId);
		int applyId = (int) map.get("applyId");
		int result = leaveService.deleteForDelLeaveRecord(applyId);
		if (result>0)
		{
			activiti.deleteProcessDefinition(taskId);
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
	@RequestMapping("/submitProcessesLeaveTask.action")
	@ResponseBody
	public LayuiTableBean submitStaffProcessesTask(String taskId,String feedback)
	{
		Map<String,Object> map = activiti.getVariables(taskId);
		int applyId = (int) map.get("applyId");
		int result = leaveService.updateForUpLeaveFeedback(feedback,applyId);
		if (result>0)
		{
			activiti.completePersonTask(taskId);
			LayuiTableBean layuiTableBean = new LayuiTableBean();
			layuiTableBean.setMsg("提交成功");
			return layuiTableBean;
		}
		return null;
	}
}
