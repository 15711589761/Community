package com.smart.community.ljmcontroller;

import com.smart.community.activiti.Activiti;
import com.smart.community.ljmbean.*;
import com.smart.community.ljmservice.ActivitiService;
import com.smart.community.ljmservice.LjmCustomerService;
import com.smart.community.ljmservice.LjmDeskService;
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

@Controller
public class LjmComplaintController
{

	@Resource
	private LjmDeskService deskService;
	@Resource
	private LjmCustomerService customerService;
	@Resource
	private Activiti complaintActiviti;
	@Resource
	private ActivitiService activitiService;


	/**
	 * 跳转到用户查看投诉反馈界面
	 * @return 业主投诉和建议界面
	 */
	@RequestMapping("/toComplaintOwner.view")
	public ModelAndView toComplaintTable()
	{
		return new ModelAndView("ljm_owner_complaint_table");
	}

	/**
	 * 业主投诉反馈表显示查询
	 * @param tableSearchBean 搜索信息
	 * @param request request
	 * @return layui表格接口
	 */
	@RequestMapping("/fotGetComplaintTable.action")
	@ResponseBody
	public LayuiTableBean fotGetComplaintTable( TableSearchBean tableSearchBean, HttpServletRequest request)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		List<OwnerBean> list = (List<OwnerBean>) request.getSession().getAttribute("owners");
		String roomNum = list.get(0).getOwnerRoom();
		tableSearchBean.setRoomNum(roomNum);
		List<SuggestBean> suggestBeans = deskService.forGetSuggestTable(tableSearchBean);
		List<SuggestBean> showTable = new ArrayList<>();
		if (suggestBeans!=null&&suggestBeans.size()>0)
		{
			layuiTableBean.setCode(0);
			for (SuggestBean suggestBean : suggestBeans)
			{
				if ("投诉".equals(suggestBean.getSuggestRemark()))
				{
					showTable.add(suggestBean);
				}
			}
			layuiTableBean.setData(showTable);
		} else {
			layuiTableBean.setCode(1);
			layuiTableBean.setMsg("没有过投诉或建议");
		}
		return layuiTableBean;
	}

	/**
	 * 跳转到用户查看投诉反馈界面
	 * @return 业主投诉和建议界面
	 */
	@RequestMapping("/toComplaintOwnerApply.view")
	public ModelAndView toComplaintApply()
	{
		return new ModelAndView("ljm_owner_complaint_apply");
	}

	/**
	 * 创建启动流程实例
	 * @return 信息
	 */
	@RequestMapping("/createProcessesForComplaint.action")
	@ResponseBody
	public LayuiTableBean createProcessesForTrim ( String complaintName , String complaintPhone , String complaintContext, HttpServletRequest request)
	{
		List<OwnerBean> list = (List<OwnerBean>) request.getSession().getAttribute("owners");
		String room = list.get(0).getOwnerRoom();
		//参数设置，在启动时传入流程
		Map<String,Object> map = new HashMap<>();
		map.put("complaintName",complaintName);map.put("complaintPhone",complaintPhone);
		map.put("complaintContext",complaintContext);map.put("serviceName","业主投诉");
		map.put("roomNum",room);
		int result = deskService.insertSuggestRecordService(complaintName,complaintPhone,complaintContext,"投诉",room);
		if (result > 0 )
		{
			int complaintId = deskService.selectForGetLastComplaint(room);
			map.put("applyId",String.valueOf(complaintId));
			ProcessInstance pi = complaintActiviti.createActiviti("complaintApply", "processes/Complaint.bpmn","processes/Complaint.png",map);
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
	@RequestMapping("/findProcessesForComplaint.action")
	@ResponseBody
	public LayuiTableBean findOwnerProcessesForTrim(HttpServletRequest request)
	{
		String taskName = "业主投诉";
		LayuiTableBean layuiTableBean = activitiService.filterTask(request,taskName);
		layuiTableBean.setCode(0);
		return layuiTableBean;
	}

	/**
	 * 删除撤回流程
	 * @param taskId 任务id
	 * @return layui接口
	 */
	@RequestMapping("/deleteProcessesComplaintTask.action")
	@ResponseBody
	public LayuiTableBean deletePersonProcessesTask(String taskId)
	{
		Map<String,Object> map = complaintActiviti.getVariables(taskId);
		String applyId = (String) map.get("applyId");
		int result = deskService.deleteSuggestRecordService(applyId);
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		if (result>0)
		{
			complaintActiviti.deleteProcessDefinition(taskId);
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
	@RequestMapping("/submitProcessesComplaintTask.action")
	@ResponseBody
	public LayuiTableBean submitOwnerProcessesTask(String taskId,String feedback)
	{
		Map<String,Object> map = complaintActiviti.getVariables(taskId);
		String applyId = (String) map.get("applyId");
		int result = customerService.feedbackForSuggestService(feedback,applyId);
		if (result>0)
		{
			LayuiTableBean layuiTableBean = new LayuiTableBean();
			complaintActiviti.completePersonTask(taskId);
			layuiTableBean.setMsg("提交成功");
			return layuiTableBean;
		}
		return null;
	}

	/**
	 * 跳转到客服查看投诉反馈界面
	 * @return 业主投诉和建议界面
	 */
	@RequestMapping("/toComplaintForCustomer.view")
	public ModelAndView toComplaintForCustomer()
	{
		return new ModelAndView("ljm_Complaint_feedback");
	}

}
