package com.smart.community.ljmbean;

/**
 * 物业人员类
 * @author LJM
 */
public class StaffBean
{
	private int staffId;
	private String staffJobNum;
	private String staffName;
	private String staffSex;
	private String staffAge;
	private String staffTel;
	private String staffAddress;
	private String staffJoinDate;
	private String staffStatus;

	@Override
	public String toString()
	{
		return "StaffBean{" + "staffId=" + staffId + ", staffJobNum='" + staffJobNum + '\'' + ", staffName='" + staffName + '\'' + ", staffSex='" + staffSex + '\'' + ", staffAge='" + staffAge + '\'' + ", staffTel='" + staffTel + '\'' + ", staffAddress='" + staffAddress + '\'' + ", staffJoinDate='" + staffJoinDate + '\'' + ", staffStatus='" + staffStatus + '\'' + '}';
	}

	public int getStaffId()
	{
		return staffId;
	}

	public void setStaffId( int staffId )
	{
		this.staffId = staffId;
	}

	public String getStaffJobNum()
	{
		return staffJobNum;
	}

	public void setStaffJobNum( String staffJobNum )
	{
		this.staffJobNum = staffJobNum;
	}

	public String getStaffName()
	{
		return staffName;
	}

	public void setStaffName( String staffName )
	{
		this.staffName = staffName;
	}

	public String getStaffSex()
	{
		return staffSex;
	}

	public void setStaffSex( String staffSex )
	{
		this.staffSex = staffSex;
	}

	public String getStaffAge()
	{
		return staffAge;
	}

	public void setStaffAge( String staffAge )
	{
		this.staffAge = staffAge;
	}

	public String getStaffTel()
	{
		return staffTel;
	}

	public void setStaffTel( String staffTel )
	{
		this.staffTel = staffTel;
	}

	public String getStaffAddress()
	{
		return staffAddress;
	}

	public void setStaffAddress( String staffAddress )
	{
		this.staffAddress = staffAddress;
	}

	public String getStaffJoinDate()
	{
		return staffJoinDate;
	}

	public void setStaffJoinDate( String staffJoinDate )
	{
		this.staffJoinDate = staffJoinDate;
	}

	public String getStaffStatus()
	{
		return staffStatus;
	}

	public void setStaffStatus( String staffStatus )
	{
		this.staffStatus = staffStatus;
	}
}
