package com.smart.community.sxbean;

public class ShopUserLogin
{
	private String roomNumber;
	private String roomPassword;
	private int uid;

	public ShopUserLogin(String roomNumber, String roomPassword, int uid)
	{
		this.roomNumber = roomNumber;
		this.roomPassword = roomPassword;
		this.uid = uid;

	}

	public ShopUserLogin()
	{
	}

	public String getRoomNumber()
	{
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber)
	{
		this.roomNumber = roomNumber;
	}

	public String getRoomPassword()
	{
		return roomPassword;
	}

	public void setRoomPassword(String roomPassword)
	{
		this.roomPassword = roomPassword;
	}

	public int getUid()
	{
		return uid;
	}

	public void setUid(int uid)
	{
		this.uid = uid;
	}
}
