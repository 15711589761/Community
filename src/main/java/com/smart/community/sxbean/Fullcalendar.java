package com.smart.community.sxbean;

public class Fullcalendar
{
	private int staffId;
	private String roleNAME;
	private String staffName;
	private String workDate;
	private String workTime;
	private int workId;

	public Fullcalendar(int staffId, String roleNAME, String staffName, String workDate, String workTime, int workId)
	{
		this.staffId = staffId;
		this.roleNAME = roleNAME;
		this.staffName = staffName;
		this.workDate = workDate;
		this.workTime = workTime;
		this.workId = workId;
	}

	public Fullcalendar()
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

	public String getRoleNAME()
	{
		return roleNAME;
	}

	public void setRoleNAME(String roleNAME)
	{
		this.roleNAME = roleNAME;
	}

	public String getStaffName()
	{
		return staffName;
	}

	public void setStaffName(String staffName)
	{
		this.staffName = staffName;
	}

	public String getWorkDate()
	{
		return workDate;
	}

	public void setWorkDate(String workDate)
	{
		this.workDate = workDate;
	}

	public String getWorkTime()
	{
		return workTime;
	}

	public void setWorkTime(String workTime)
	{
		this.workTime = workTime;
	}

	public int getWorkId()
	{
		return workId;
	}

	public void setWorkId(int workId)
	{
		this.workId = workId;
	}
}
