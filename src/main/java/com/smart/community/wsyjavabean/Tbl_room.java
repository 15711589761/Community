package com.smart.community.wsyjavabean;

public class Tbl_room
{
	private int room_id;   //id
	private String room_number; //楼栋编号
	private String room_status; //房屋状态

	public Tbl_room()
	{
	}

	public Tbl_room(int room_id, String room_number, String room_status)
	{
		this.room_id = room_id;
		this.room_number = room_number;
		this.room_status = room_status;
	}

	@Override
	public String toString()
	{
		return "Tbl_room{" + "room_id=" + room_id + ", room_number='" + room_number + '\'' + ", room_status='" + room_status + '\'' + '}';
	}

	public int getRoom_id()
	{
		return room_id;
	}

	public void setRoom_id(int room_id)
	{
		this.room_id = room_id;
	}

	public String getRoom_number()
	{
		return room_number;
	}

	public void setRoom_number(String room_number)
	{
		this.room_number = room_number;
	}

	public String getRoom_status()
	{
		return room_status;
	}

	public void setRoom_status(String room_status)
	{
		this.room_status = room_status;
	}
}
