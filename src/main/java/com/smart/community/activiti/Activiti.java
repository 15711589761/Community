package com.smart.community.activiti;

import com.smart.community.ljmbean.ActivityTaskBean;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.testng.annotations.Test;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LJM
 */
@ManagedBean
public class Activiti
{
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	@Resource
	private IdentityService identityService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private ProcessEngine processEngine;
	@Resource
	private HistoryService historyService;

	/**
	 * 创建工作流实例,启动流程实例
	 * @param deployName 定义流程名称
	 * @param resourceBpmn bpmn路径
	 * @param resourcePng png路径
	 * @param assignMap 信息
	 * @return 工作流程实例
	 */
	public ProcessInstance createActiviti( String deployName,String resourceBpmn , String resourcePng ,Map<String,Object> assignMap )
	{
		Deployment deployment = processEngine.getRepositoryService().createDeployment().name(deployName)
				.addClasspathResource(resourceBpmn)
				.addClasspathResource(resourcePng)
				.deploy();
		ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(definition.getKey(),assignMap);
		System.out.println("部署id:"+deployment.getId());
		System.out.println("部署名称"+deployment.getName());
		System.out.println("部署时间"+deployment.getDeploymentTime());
		System.out.println("流程实例id"+pi.getId());
		System.out.println("流程实例名称"+pi.getName());

		return pi;
	}

	/**
	 * 查找某个执行人的所有任务
	 * @param handler 楼栋号或职位角色
	 * @return 所有任务信息
	 */
	public List<ActivityTaskBean> findPersonTask( String handler )
	{
		List<Task> myTasks = taskService.createTaskQuery().taskAssignee(handler).list();
		List<ActivityTaskBean> activityTaskBeans = new ArrayList<>();
		if (myTasks!=null&&myTasks.size()>0)
		{
			for (Task myTask : myTasks)
			{
				Map<String,Object> map = getVariables(myTask.getId());
				ActivityTaskBean activityTaskBean = new ActivityTaskBean();
				activityTaskBean.setProcessTaskId(myTask.getId());
				activityTaskBean.setTaskName(myTask.getName());
				activityTaskBean.setTaskCreateTime(new SimpleDateFormat("yyyy-mm-dd").format(myTask.getCreateTime()));
				activityTaskBean.setProcessAssignee(myTask.getAssignee());
				activityTaskBean.setProcessDefinitionId(myTask.getProcessDefinitionId());
				activityTaskBean.setProcessInstanceId(myTask.getProcessInstanceId());
				activityTaskBean.setApplyRoom((String) map.get("roomNum"));
				activityTaskBean.setWorkDate((String) map.get("workDate"));
				activityTaskBean.setRemark((String)map.get("remark"));
				activityTaskBean.setMessage((String) map.get("message"));
				activityTaskBean.setWorkPerson((String)map.get("workPerson"));
				activityTaskBean.setWorkPersonPhone((String)map.get("workPersonPhone"));
				activityTaskBeans.add(activityTaskBean);
			}
		}
		return activityTaskBeans;
	}

	/**
	 * 删除流程定义
	 * @param taskId 任务id
	 */
	public void deleteProcessDefinition(String taskId)
	{
		HistoricTaskInstance currTask = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(currTask.getProcessDefinitionId()).singleResult();
		String depolyId = processDefinition.getDeploymentId();
		repositoryService.deleteDeployment(depolyId,true);
		System.out.println("删除成功");
	}

	/**
	 * 生成流程图图片
	 */
	public void processPic( String deploymentId , HttpServletResponse response )
	{
		List<String> list = repositoryService.getDeploymentResourceNames(deploymentId);
		String resouceName = "";
		if (list!=null && list.size()>0)
		{
			for (String name:list)
			{
				if (name.contains(".png"))
				{
					resouceName = name;
				}
			}
		}

		try
		{
			InputStream in = repositoryService.getResourceAsStream(deploymentId,resouceName);
			BufferedImage image = ImageIO.read(in);
			response.setContentType("image/png");
			OutputStream outputStream = response.getOutputStream();
			ImageIO.write(image,"png",outputStream);
			in.close();
			outputStream.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 完成任务
	 * @param taskId 任务id
	 */
	public void completePersonTask(String taskId)
	{
		taskService.complete(taskId);
		System.out.println("完成任务---"+taskId);
	}

	/**
	 * 带参数完成任务
	 * @param taskId 任务id
	 * @param params 参数map
	 */
	public void completePersonTask(String taskId,Map<String,Object> params)
	{
		taskService.complete(taskId,params);
		System.out.println("完成任务++++++"+taskId);
	}

	/**
	 * 设置参数
	 * @param taskId 任务id
	 * @param message 信息
	 */
	public void setVariables( String taskId, Map<String,String> message )
	{
		taskService.setVariables(taskId,message);
	}

	/**
	 * 获取参数
	 * @param taskId 任务id
	 * @return 参数map
	 */
	public Map<String,Object> getVariables(String taskId)
	{
		Map<String, Object> map = taskService.getVariables(taskId);
		return map;
	}

	/**
	 * 查询某个人的历史记录
	 * @param assignee 某个人
	 * @return 记录信息
	 */
	public List<ActivityTaskBean> getHistoryRecord(String assignee,int page,int limit)
	{
		List<HistoricTaskInstance> list=historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().desc().taskAssignee(assignee).finished().listPage(page,limit);
		List<ActivityTaskBean> taskBeanlist = new ArrayList<>();
		for (HistoricTaskInstance historicTaskInstance : list)
		{
//			ActivityTaskBean activityTaskBean = new ActivityTaskBean();
//			activityTaskBean.setTaskCreateTime(new SimpleDateFormat("yyyy-mm-dd").format(historicTaskInstance.getClaimTime()));
//			activityTaskBean.setTaskEndTime(new SimpleDateFormat("yyyy-mm-dd").format(historicTaskInstance.getEndTime()));
//			activityTaskBean.setProcessAssignee(historicTaskInstance.getAssignee());
//			activityTaskBean.setProcessTaskId(historicTaskInstance.getId());
		}

		return taskBeanlist;
	}

	/**
	 * 获取行
	 * @param assignee 办理人呢
	 * @return count
	 */
	public int getHistoryRecord(String assignee)
	{
		List<HistoricTaskInstance> list=historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().desc().taskAssignee(assignee).finished().list();
		return list.size();
	}
}
