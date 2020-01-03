package com.smart.community.sxbean;

public class WorkTime
{
	private String workTime;

	private String workDate;

	private String getId;

	public WorkTime(String workTime, String workDate, String getId)
	{
		this.workTime = workTime;
		this.workDate = workDate;
		this.getId = getId;
	}

	public WorkTime()
	{

	}

	public String getWorkTime()
	{
		return workTime;
	}

	public void setWorkTime(String workTime)
	{
		this.workTime = workTime;
	}

	public String getWorkDate()
	{
		return workDate;
	}

	public void setWorkDate(String workDate)
	{
		this.workDate = workDate;
	}

	public String getGetId()
	{
		return getId;
	}

	public void setGetId(String getId)
	{
		this.getId = getId;
	}
}
