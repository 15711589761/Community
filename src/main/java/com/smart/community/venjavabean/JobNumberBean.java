package com.smart.community.venjavabean;

public class JobNumberBean
{
	private int numberId;
	private String jobNumber;
	private int status;
	private String attributable;

	public String getAttributable()
	{
		return attributable;
	}

	public void setAttributable(String attributable)
	{
		this.attributable = attributable;
	}

	public int getNumberId()
	{
		return numberId;
	}

	public void setNumberId(int numberId)
	{
		this.numberId = numberId;
	}

	public String getJobNumber()
	{
		return jobNumber;
	}

	public void setJobNumber(String jobNumber)
	{
		this.jobNumber = jobNumber;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}
}
