package com.smart.community.zkbean;

public class Zk_OwnerCarBean
{
	private int ownerCarId;
	private String ownerCarNumber;
	private String ownerName;
	private String roomNum;
	private String applyDate;

	public Zk_OwnerCarBean()
	{
	}

	public Zk_OwnerCarBean(int ownerCarId, String ownerCarNumber, String ownerName, String roomNum, String applyDate)
	{
		this.ownerCarId = ownerCarId;
		this.ownerCarNumber = ownerCarNumber;
		this.ownerName = ownerName;
		this.roomNum = roomNum;
		this.applyDate = applyDate;
	}

	public int getOwnerCarId()
	{
		return ownerCarId;
	}

	public void setOwnerCarId(int ownerCarId)
	{
		this.ownerCarId = ownerCarId;
	}

	public String getOwnerCarNumber()
	{
		return ownerCarNumber;
	}

	public void setOwnerCarNumber(String ownerCarNumber)
	{
		this.ownerCarNumber = ownerCarNumber;
	}

	public String getOwnerName()
	{
		return ownerName;
	}

	public void setOwnerName(String ownerName)
	{
		this.ownerName = ownerName;
	}


	public String getApplyDate()
	{
		return applyDate;
	}

	public void setApplyDate(String applyDate)
	{
		this.applyDate = applyDate;
	}

	public String getRoomNum()
	{
		return roomNum;
	}

	public void setRoomNum(String roomNum)
	{
		this.roomNum = roomNum;
	}
}
