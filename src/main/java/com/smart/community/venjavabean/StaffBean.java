package com.smart.community.venjavabean;

public class StaffBean
{

	/**id*/
	private int staffId;
	/**姓名*/
	private String staffName;
	/**工号*/
	private String jobNumber;
	/**密码*/
	private String staffPass;
	/**性别*/
	private String staffSex;
	/**年龄*/
	private String staffAge;
	/**联系电话*/
	private String staffTel;
	/**地址*/
	private String staffAddress;
	/**入职日期*/
	private String entryDate ;
	/**在职状态id*/
	private int statusId;
	/**在职状态*/
	private String status;
	/**出生日期*/
	private String staffBirthday;
	/**离职日期*/
	private String staffTermDate;

	private byte[] faceCode;

	public byte[] getFaceCode()
	{
		return faceCode;
	}

	public void setFaceCode(byte[] faceCode)
	{
		this.faceCode = faceCode;
	}

	public String getStaffBirthday()
	{
		return staffBirthday;
	}

	public void setStaffBirthday(String staffBirthday)
	{
		this.staffBirthday = staffBirthday;
	}

	public String getStaffTermDate()
	{
		return staffTermDate;
	}

	public void setStaffTermDate(String staffTermDate)
	{
		this.staffTermDate = staffTermDate;
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

	public String getStaffPass()
	{
		return staffPass;
	}

	public void setStaffPass(String staffPass)
	{
		this.staffPass = staffPass;
	}

	public String getStaffSex()
	{
		return staffSex;
	}

	public void setStaffSex(String staffSex)
	{
		this.staffSex = staffSex;
	}

	public String getStaffAge()
	{
		return staffAge;
	}

	public void setStaffAge(String staffAge)
	{
		this.staffAge = staffAge;
	}

	public String getStaffTel()
	{
		return staffTel;
	}

	public void setStaffTel(String staffTel)
	{
		this.staffTel = staffTel;
	}

	public String getStaffAddress()
	{
		return staffAddress;
	}

	public void setStaffAddress(String staffAddress)
	{
		this.staffAddress = staffAddress;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public int getStatusId()
	{
		return statusId;
	}

	public void setStatusId(int statusId)
	{
		this.statusId = statusId;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getJobNumber()
	{
		return jobNumber;
	}

	public void setJobNumber(String jobNumber)
	{
		this.jobNumber = jobNumber;
	}
}
