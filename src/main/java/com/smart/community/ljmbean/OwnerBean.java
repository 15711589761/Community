package com.smart.community.ljmbean;

public class OwnerBean
{
	private int ownerId;
	private String ownerName;
	private String ownerRoom;
	private String ownerTel;
	private String ownerAffilation;
	private String ownerStatus;
	private String ownerIdentity;
	private String ownerCarNum;
	private String carRequestDate;

	@Override
	public String toString()
	{
		return "OwnerBean{" + "ownerId=" + ownerId + ", ownerName='" + ownerName + '\'' + ", ownerRoom='" + ownerRoom + '\'' + ", ownerTel='" + ownerTel + '\'' + ", ownerAffilation='" + ownerAffilation + '\'' + ", ownerStatus='" + ownerStatus + '\'' + ", ownerIdentity='" + ownerIdentity + '\'' + ", ownerCarNum='" + ownerCarNum + '\'' + ", carRequestDate='" + carRequestDate + '\'' + '}';
	}

	public int getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId( int ownerId )
	{
		this.ownerId = ownerId;
	}

	public String getOwnerName()
	{
		return ownerName;
	}

	public void setOwnerName( String ownerName )
	{
		this.ownerName = ownerName;
	}

	public String getOwnerRoom()
	{
		return ownerRoom;
	}

	public void setOwnerRoom( String ownerRoom )
	{
		this.ownerRoom = ownerRoom;
	}

	public String getOwnerTel()
	{
		return ownerTel;
	}

	public void setOwnerTel( String ownerTel )
	{
		this.ownerTel = ownerTel;
	}

	public String getOwnerAffilation()
	{
		return ownerAffilation;
	}

	public void setOwnerAffilation( String ownerAffilation )
	{
		this.ownerAffilation = ownerAffilation;
	}

	public String getOwnerStatus()
	{
		return ownerStatus;
	}

	public void setOwnerStatus( String ownerStatus )
	{
		this.ownerStatus = ownerStatus;
	}

	public String getOwnerIdentity()
	{
		return ownerIdentity;
	}

	public void setOwnerIdentity( String ownerIdentity )
	{
		this.ownerIdentity = ownerIdentity;
	}

	public String getOwnerCarNum()
	{
		return ownerCarNum;
	}

	public void setOwnerCarNum( String ownerCarNum )
	{
		this.ownerCarNum = ownerCarNum;
	}

	public String getCarRequestDate()
	{
		return carRequestDate;
	}

	public void setCarRequestDate( String carRequestDate )
	{
		this.carRequestDate = carRequestDate;
	}
}
