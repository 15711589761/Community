package com.smart.community.sxbean;

public class CheckAdd
{
	private String examineId;
	private String checkPrincipal;
	private String checkStatus;
	private String checkDate;

	public CheckAdd(String examineId, String checkPrincipal, String checkStatus, String checkDate)
	{
		this.examineId = examineId;
		this.checkPrincipal = checkPrincipal;
		this.checkStatus = checkStatus;
		this.checkDate = checkDate;
	}
	public CheckAdd()
	{

	}

	public String getExamineId()
	{
		return examineId;
	}

	public void setExamineId(String examineId)
	{
		this.examineId = examineId;
	}

	public String getCheckPrincipal()
	{
		return checkPrincipal;
	}

	public void setCheckPrincipal(String checkPrincipal)
	{
		this.checkPrincipal = checkPrincipal;
	}

	public String getCheckStatus()
	{
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus)
	{
		this.checkStatus = checkStatus;
	}

	public String getCheckDate()
	{
		return checkDate;
	}

	public void setCheckDate(String checkDate)
	{
		this.checkDate = checkDate;
	}
}
