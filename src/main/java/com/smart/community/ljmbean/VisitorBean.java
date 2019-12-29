package com.smart.community.ljmbean;

/**
 * 访客信息类
 * @author LJM
 */
public class VisitorBean
{
	private int visitorId;
	private String visitorName;
	private String visitorIdentity;
	private String visitorOrigin;
	private String visitorRoom;
	private String recorder;
	private int recorderId;
	private String visitorDate;
	private String visitorTime;

	@Override
	public String toString()
	{
		return "VisitorBean{" + "visitorId=" + visitorId + ", visitorName='" + visitorName + '\'' + ", visitorIdentity='" + visitorIdentity + '\'' + ", visitorOrigin='" + visitorOrigin + '\'' + ", visitorRoom='" + visitorRoom + '\'' + ", recorder='" + recorder + '\'' + ", recorderId='" + recorderId + '\'' + ", visitorDate='" + visitorDate + '\'' + ", visitorTime='" + visitorTime + '\'' + '}';
	}

	public int getVisitorId()
	{
		return visitorId;
	}

	public void setVisitorId( int visitorId )
	{
		this.visitorId = visitorId;
	}

	public String getVisitorName()
	{
		return visitorName;
	}

	public void setVisitorName( String visitorName )
	{
		this.visitorName = visitorName;
	}

	public String getVisitorIdentity()
	{
		return visitorIdentity;
	}

	public void setVisitorIdentity( String visitorIdentity )
	{
		this.visitorIdentity = visitorIdentity;
	}

	public String getVisitorOrigin()
	{
		return visitorOrigin;
	}

	public void setVisitorOrigin( String visitorOrigin )
	{
		this.visitorOrigin = visitorOrigin;
	}

	public String getVisitorRoom()
	{
		return visitorRoom;
	}

	public void setVisitorRoom( String visitorRoom )
	{
		this.visitorRoom = visitorRoom;
	}

	public String getRecorder()
	{
		return recorder;
	}

	public void setRecorder( String recorder )
	{
		this.recorder = recorder;
	}

	public int getRecorderId()
	{
		return recorderId;
	}

	public void setRecorderId( int recorderId )
	{
		this.recorderId = recorderId;
	}

	public String getVisitorDate()
	{
		return visitorDate;
	}

	public void setVisitorDate( String visitorDate )
	{
		this.visitorDate = visitorDate;
	}

	public String getVisitorTime()
	{
		return visitorTime;
	}

	public void setVisitorTime( String visitorTime )
	{
		this.visitorTime = visitorTime;
	}
}
