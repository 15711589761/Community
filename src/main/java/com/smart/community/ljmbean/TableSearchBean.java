package com.smart.community.ljmbean;

/**
 * 表查询所需参数类
 * @author LJM
 */
public class TableSearchBean
{
	private int page;
	private int limit;
	private String startDate;
	private String endDate;
	private String roomNum;


	public String getRoomNum()
	{
		return roomNum;
	}

	public void setRoomNum( String roomNum )
	{
		this.roomNum = roomNum;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage( int page )
	{
		this.page = page;
	}

	public int getLimit()
	{
		return limit;
	}

	public void setLimit( int limit )
	{
		this.limit = limit;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate( String startDate )
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate( String endDate )
	{
		this.endDate = endDate;
	}
}
