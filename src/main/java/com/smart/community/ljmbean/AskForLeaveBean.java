package com.smart.community.ljmbean;

public class AskForLeaveBean
{
	private int leaveId;
	private String applyName;
	private String startDate;
	private String leaveDay;
	private String content;
	private String feedback;

	public int getLeaveId()
	{
		return leaveId;
	}

	public void setLeaveId( int leaveId )
	{
		this.leaveId = leaveId;
	}

	public String getApplyName()
	{
		return applyName;
	}

	public void setApplyName( String applyName )
	{
		this.applyName = applyName;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate( String startDate )
	{
		this.startDate = startDate;
	}

	public String getLeaveDay()
	{
		return leaveDay;
	}

	public void setLeaveDay( String leaveDay )
	{
		this.leaveDay = leaveDay;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent( String content )
	{
		this.content = content;
	}

	public String getFeedback()
	{
		return feedback;
	}

	public void setFeedback( String feedback )
	{
		this.feedback = feedback;
	}
}
