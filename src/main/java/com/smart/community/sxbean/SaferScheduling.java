package com.smart.community.sxbean;

//安保人员排班类
public class SaferScheduling
{
	private int staffId;
	private String staffName;
	private String staffNumber;
	private String staffAddress;
	private String staffJoinDate;

	public SaferScheduling(int staffId, String staffName, String staffNumber, String staffAddress, String staffJoinDate)
	{
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffNumber = staffNumber;
		this.staffAddress = staffAddress;
		this.staffJoinDate = staffJoinDate;
	}

	public SaferScheduling()
	{

	}

	public int getStaffId()
	{
		return staffId;
	}

	public void setStaffId(int staffId)
	{
		this.staffId = staffId;
	}

	public String getStaffName()
	{
		return staffName;
	}

	public void setStaffName(String staffName)
	{
		this.staffName = staffName;
	}

	public String getStaffNumber()
	{
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber)
	{
		this.staffNumber = staffNumber;
	}

	public String getStaffAddress()
	{
		return staffAddress;
	}

	public void setStaffAddress(String staffAddress)
	{
		this.staffAddress = staffAddress;
	}

	public String getStaffJoinDate()
	{
		return staffJoinDate;
	}

	public void setStaffJoinDate(String staffJoinDate)
	{
		this.staffJoinDate = staffJoinDate;
	}

	@Override
	public String toString()
	{
		return "SaferScheduling{" + "staffId=" + staffId + ", staffName='" + staffName + '\'' + ", staffNumber='" + staffNumber + '\'' + ", staffAddress='" + staffAddress + '\'' + ", staffJoinDate='" + staffJoinDate + '\'' + '}';
	}
}
