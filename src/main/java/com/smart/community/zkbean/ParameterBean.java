package com.smart.community.zkbean;

public class ParameterBean
{
	private String name;
	private String beginDate;
	private String endDate;
	private String carNum;
	private String roomNum;
	private int limit;
	private int page;

	public ParameterBean()
	{
	}

	public ParameterBean(String name, String beginDate, String endDate, String carNum, int limit, int page)
	{
		this.name = name;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.carNum = carNum;
		this.limit = limit;
		this.page = page;
	}

	public ParameterBean(String name, String beginDate, String endDate, String carNum, String roomNum, int limit, int page)
	{
		this.name = name;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.carNum = carNum;
		this.roomNum = roomNum;
		this.limit = limit;
		this.page = page;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getBeginDate()
	{
		return beginDate;
	}

	public void setBeginDate(String beginDate)
	{
		this.beginDate = beginDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public int getLimit()
	{
		return limit;
	}

	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public String getCarNum()
	{
		return carNum;
	}

	public void setCarNum(String carNum)
	{
		this.carNum = carNum;
	}

	public String getRoomNum()
	{
		return roomNum;
	}

	public void setRoomNum(String roomNum)
	{
		this.roomNum = roomNum;
	}

	@Override
	public String toString()
	{
		return "ParameterBean{" + "name='" + name + '\'' + ", beginDate='" + beginDate + '\'' + ", endDate='" + endDate + '\'' + ", carNum='" + carNum + '\'' + ", roomNum='" + roomNum + '\'' + ", limit=" + limit + ", page=" + page + '}';
	}
}
