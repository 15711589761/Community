package com.smart.community.ljmservice;

import com.smart.community.activiti.Activiti;
import com.smart.community.ljmbean.*;
import com.smart.community.tool.LjmTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ljm
 */
@Service
public class ActivitiService
{
	@Resource
	private LjmBackstageLoginService backstageLoginService;
	@Resource
	private Activiti activiti;

	/**
	 * 返回审核状态,在装修申请里使用
	 * @param role 角色
	 * @param isAgree 执行人
	 * @return status
	 */
	public String getStatus(String role,String isAgree)
	{
		String status=null;
		if (LjmTool.ROLE_CUSTOMER.equals(role))
		{
			if ("同意".equals(isAgree))
			{
				status = "已受理，待审核";
			} else {
				status = "已受理，驳回，时间不合适";
			}
		} else if (LjmTool.ROLE_LOGISTICS.equals(role)){
			if ("同意".equals(isAgree))
			{
				status = "同意申请";
			} else {
				status = "否决申请";
			}
		}
		return status;
	}

	/**
	 * 申请服务筛选
	 * @param activityTaskBeans 任务参数信息
	 * @param taskName 任务名称
	 * @return 筛选后集合
	 */
	public List<ActivityTaskBean> separate(List<ActivityTaskBean> activityTaskBeans,String taskName)
	{
		List<ActivityTaskBean> results = new ArrayList<>();
		for (ActivityTaskBean activityTaskBean : activityTaskBeans)
		{
			if (taskName.equals(activityTaskBean.getServiceName()))
			{
				results.add(activityTaskBean);
			}
		}
		return results;
	}


	/**
	 * 任务筛选
	 * @param request request
	 * @param taskName 任务名称
	 * @return layui接口
	 */
	public LayuiTableBean filterTask( HttpServletRequest request, String taskName)
	{

		List<OwnerBean> list = (List<OwnerBean>) request.getSession().getAttribute("owners");
		StaffBean staffBean = (StaffBean) request.getSession().getAttribute("staffBean");
		List<ActivityTaskBean> activityTaskBeans = null;
		if (staffBean!=null){
			RoleBean roleBean = backstageLoginService.getPersonRole(staffBean.getStaffId());
			activityTaskBeans = activiti.findPersonTask(roleBean.getRoleName());
		} else if (list!=null&&list.size()>0){
			String room = list.get(0).getOwnerRoom();
			activityTaskBeans = activiti.findPersonTask(room);
		}
		return dataFilter(activityTaskBeans,taskName);
	}

	/**
	 * 任务筛选
	 * @param request request
	 * @param taskName 任务名称
	 * @return layui接口
	 */
	public LayuiTableBean filterBackstageTask( HttpServletRequest request, String taskName )
	{
		StaffBean staffBean = (StaffBean) request.getSession().getAttribute("staffBean");
		RoleBean roleBean = backstageLoginService.getPersonRole(staffBean.getStaffId());
		List<ActivityTaskBean> activityTaskBeans = null;
		if (LjmTool.ROLE_LOGISTICS.equals(roleBean.getRoleName()))
		{
			activityTaskBeans =  activiti.findPersonTask(staffBean.getStaffName());
		} else if (LjmTool.ROLE_FINANCE.equals(roleBean.getRoleName()))
		{
			activityTaskBeans = activiti.findPersonTask(roleBean.getRoleName());
		}
		return dataFilter(activityTaskBeans,taskName);
	}

	/**
	 * 任务筛选
	 * @param request request
	 * @param taskName 任务名称
	 * @return layui接口
	 */
	public LayuiTableBean filterLeaveTask( HttpServletRequest request, String taskName )
	{
		StaffBean staffBean = (StaffBean) request.getSession().getAttribute("staffBean");
		RoleBean roleBean = backstageLoginService.getPersonRole(staffBean.getStaffId());
		List<ActivityTaskBean> activityTaskBeans = null;
		if (LjmTool.ROLE_AFFAIRS.equals(roleBean.getRoleName()))
		{
			activityTaskBeans = activiti.findPersonTask(roleBean.getRoleName());
		} else {
			activityTaskBeans =  activiti.findPersonTask(staffBean.getStaffName());
		}
		return dataFilter(activityTaskBeans,taskName);
	}

	/**
	 * 过滤list
	 * @param list 数据
	 * @param taskName 任务名称
	 * @return layui接口
	 */
	public LayuiTableBean dataFilter(List<ActivityTaskBean>list , String taskName)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		if (list!=null)
		{
			List<ActivityTaskBean> results = new ArrayList<>();
			for (ActivityTaskBean activityTaskBean : list)
			{
				if (activityTaskBean.getServiceName()!=null)
				{
					if (activityTaskBean.getServiceName().equals(taskName))
					{
						results.add(activityTaskBean);
					}
				}
			}
			layuiTableBean.setData(results);
		}
		return layuiTableBean;
	}
}
