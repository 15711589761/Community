package com.smart.community.ljmbean;

public class ActivityTaskBean
{
	//任务id
	private String processTaskId;
	//任务名称
	private String taskName;
	//任务创建时间
	private String taskCreateTime;
	//任务结束时间
	private String taskEndTime;
	//任务办理人
	private String processAssignee;
	//施工时间
	private String workDate;
	//备注
	private String remark;
	//流程实例id
	private String processInstanceId;
	//流程定义id
	private String processDefinitionId;
	//部署id
	private String deploymentId;
	//部署名称
	private String deploymentName;
	//申请楼栋号
	private String applyRoom;
	//反馈信息
	private String message;
	//工人姓名
	private String workPerson;
	//工人联系方式
	private String workPersonPhone;
	//服务名称
	private String serviceName;
	//内容/事由
	private String content;
	//投诉人
	private String complaintName;
	//投诉电话
	private String complaintPhone;
	//商品型号
	private String purchaseModel;
	//商品数量
	private String purchaseQuantity;
	//商品价格
	private String purchasePrice;
	//商品名称
	private String purchaseName;
	//申请人
	private String applyName;
	//请假天数
	private String leaveDay;
	//请假开始日期
	private String startDate;

	public String getLeaveDay()
	{
		return leaveDay;
	}

	public void setLeaveDay( String leaveDay )
	{
		this.leaveDay = leaveDay;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate( String startDate )
	{
		this.startDate = startDate;
	}

	public String getApplyName()
	{
		return applyName;
	}

	public void setApplyName( String applyName )
	{
		this.applyName = applyName;
	}

	public String getPurchaseQuantity()
	{
		return purchaseQuantity;
	}

	public void setPurchaseQuantity( String purchaseQuantity )
	{
		this.purchaseQuantity = purchaseQuantity;
	}

	public String getPurchasePrice()
	{
		return purchasePrice;
	}

	public void setPurchasePrice( String purchasePrice )
	{
		this.purchasePrice = purchasePrice;
	}

	public String getPurchaseName()
	{
		return purchaseName;
	}

	public void setPurchaseName( String purchaseName )
	{
		this.purchaseName = purchaseName;
	}

	public String getPurchaseModel()
	{
		return purchaseModel;
	}

	public void setPurchaseModel( String purchaseModel )
	{
		this.purchaseModel = purchaseModel;
	}

	public String getComplaintName()
	{
		return complaintName;
	}

	public void setComplaintName( String complaintName )
	{
		this.complaintName = complaintName;
	}

	public String getComplaintPhone()
	{
		return complaintPhone;
	}

	public void setComplaintPhone( String complaintPhone )
	{
		this.complaintPhone = complaintPhone;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent( String content )
	{
		this.content = content;
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName( String serviceName )
	{
		this.serviceName = serviceName;
	}

	public String getTaskEndTime()
	{
		return taskEndTime;
	}

	public void setTaskEndTime( String taskEndTime )
	{
		this.taskEndTime = taskEndTime;
	}

	public String getWorkPerson()
	{
		return workPerson;
	}

	public void setWorkPerson( String workPerson )
	{
		this.workPerson = workPerson;
	}

	public String getWorkPersonPhone()
	{
		return workPersonPhone;
	}

	public void setWorkPersonPhone( String workPersonPhone )
	{
		this.workPersonPhone = workPersonPhone;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage( String message )
	{
		this.message = message;
	}

	public String getApplyRoom()
	{
		return applyRoom;
	}

	public void setApplyRoom( String applyRoom )
	{
		this.applyRoom = applyRoom;
	}

	public String getProcessTaskId()
	{
		return processTaskId;
	}

	public void setProcessTaskId( String processTaskId )
	{
		this.processTaskId = processTaskId;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskName( String taskName )
	{
		this.taskName = taskName;
	}

	public String getTaskCreateTime()
	{
		return taskCreateTime;
	}

	public void setTaskCreateTime( String taskCreateTime )
	{
		this.taskCreateTime = taskCreateTime;
	}

	public String getProcessAssignee()
	{
		return processAssignee;
	}

	public void setProcessAssignee( String processAssignee )
	{
		this.processAssignee = processAssignee;
	}

	public String getProcessInstanceId()
	{
		return processInstanceId;
	}

	public void setProcessInstanceId( String processInstanceId )
	{
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionId()
	{
		return processDefinitionId;
	}

	public void setProcessDefinitionId( String processDefinitionId )
	{
		this.processDefinitionId = processDefinitionId;
	}

	public String getDeploymentId()
	{
		return deploymentId;
	}

	public void setDeploymentId( String deploymentId )
	{
		this.deploymentId = deploymentId;
	}

	public String getDeploymentName()
	{
		return deploymentName;
	}

	public void setDeploymentName( String deploymentName )
	{
		this.deploymentName = deploymentName;
	}

	public String getWorkDate()
	{
		return workDate;
	}

	public void setWorkDate( String workDate )
	{
		this.workDate = workDate;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark( String remark )
	{
		this.remark = remark;
	}
}
