package com.smart.community.sxbean;

public class CheckFacility
{
	private int facilityId;
	private String checkDate;
	private String checkStatus;
	private String checkPrincipal;
	private String examineName;

	public CheckFacility(int facilityId, String checkDate, String checkStatus, String checkPrincipal, String examineName)
	{
		this.facilityId = facilityId;
		this.checkDate = checkDate;
		this.checkStatus = checkStatus;
		this.checkPrincipal = checkPrincipal;
		this.examineName = examineName;
	}

	public CheckFacility()
	{

	}

	public int getFacilityId()
	{
		return facilityId;
	}

	public void setFacilityId(int facilityId)
	{
		this.facilityId = facilityId;
	}

	public String getCheckDate()
	{
		return checkDate;
	}

	public void setCheckDate(String checkDate)
	{
		this.checkDate = checkDate;
	}

	public String getCheckStatus()
	{
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus)
	{
		this.checkStatus = checkStatus;
	}

	public String getCheckPrincipal()
	{
		return checkPrincipal;
	}

	public void setCheckPrincipal(String checkPrincipal)
	{
		this.checkPrincipal = checkPrincipal;
	}

	public String getExamineName()
	{
		return examineName;
	}

	public void setExamineName(String examineName)
	{
		this.examineName = examineName;
	}
}
