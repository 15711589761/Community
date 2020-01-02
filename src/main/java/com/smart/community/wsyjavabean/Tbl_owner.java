package com.smart.community.wsyjavabean;

public class Tbl_owner
{
	private int owner_id;
	private String owner_name;  //姓名
	private String owner_room;  //楼栋号
	private String owner_tel;   //手机号码
	private String owner_affiliation; //户主
	private String owner_status; //状态
	private String owner_identity; //身份证
	private Tbl_room tbl_room;

	public Tbl_owner()
	{
	}

	public Tbl_owner(int owner_id, String owner_name, String owner_room, String owner_tel, String owner_affiliation, String owner_status, String owner_identity, Tbl_room tbl_room)
	{
		this.owner_id = owner_id;
		this.owner_name = owner_name;
		this.owner_room = owner_room;
		this.owner_tel = owner_tel;
		this.owner_affiliation = owner_affiliation;
		this.owner_status = owner_status;
		this.owner_identity = owner_identity;
		this.tbl_room = tbl_room;
	}

	@Override
	public String toString()
	{
		return "Tbl_owner{" + "owner_id=" + owner_id + ", owner_name='" + owner_name + '\'' + ", owner_room='" + owner_room + '\'' + ", owner_tel='" + owner_tel + '\'' + ", owner_affiliation='" + owner_affiliation + '\'' + ", owner_status='" + owner_status + '\'' + ", owner_identity='" + owner_identity + '\'' + ", tbl_room=" + tbl_room + '}';
	}

	public int getOwner_id()
	{
		return owner_id;
	}

	public void setOwner_id(int owner_id)
	{
		this.owner_id = owner_id;
	}

	public String getOwner_name()
	{
		return owner_name;
	}

	public void setOwner_name(String owner_name)
	{
		this.owner_name = owner_name;
	}

	public String getOwner_room()
	{
		return owner_room;
	}

	public void setOwner_room(String owner_room)
	{
		this.owner_room = owner_room;
	}

	public String getOwner_tel()
	{
		return owner_tel;
	}

	public void setOwner_tel(String owner_tel)
	{
		this.owner_tel = owner_tel;
	}

	public String getOwner_affiliation()
	{
		return owner_affiliation;
	}

	public void setOwner_affiliation(String owner_affiliation)
	{
		this.owner_affiliation = owner_affiliation;
	}

	public String getOwner_status()
	{
		return owner_status;
	}

	public void setOwner_status(String owner_status)
	{
		this.owner_status = owner_status;
	}

	public String getOwner_identity()
	{
		return owner_identity;
	}

	public void setOwner_identity(String owner_identity)
	{
		this.owner_identity = owner_identity;
	}

	public Tbl_room getTbl_room()
	{
		return tbl_room;
	}

	public void setTbl_room(Tbl_room tbl_room)
	{
		this.tbl_room = tbl_room;
	}
}
