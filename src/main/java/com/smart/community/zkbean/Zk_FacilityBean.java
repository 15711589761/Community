package com.smart.community.zkbean;

public class Zk_FacilityBean
{
	private int facilityID;
	private String facilityName;
	private int facilityNum;
	private String facilityBuyDate;


	public Zk_FacilityBean()
	{
	}

	public Zk_FacilityBean(int facilityID, String facilityName, int facilityNum, String facilityBuyDate)
	{
		this.facilityID = facilityID;
		this.facilityName = facilityName;
		this.facilityNum = facilityNum;
		this.facilityBuyDate = facilityBuyDate;
	}

	public int getFacilityID()
	{
		return facilityID;
	}

	public void setFacilityID(int facilityID)
	{
		this.facilityID = facilityID;
	}

	public String getFacilityName()
	{
		return facilityName;
	}

	public void setFacilityName(String facilityName)
	{
		this.facilityName = facilityName;
	}

	public String getFacilityBuyDate()
	{
		return facilityBuyDate;
	}

	public void setFacilityBuyDate(String facilityBuyDate)
	{
		this.facilityBuyDate = facilityBuyDate;
	}

	public int getFacilityNum()
	{
		return facilityNum;
	}

	public void setFacilityNum(int facilityNum)
	{
		this.facilityNum = facilityNum;
	}
}
