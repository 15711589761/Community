package com.smart.community.wsyjavabean;

public class Tbl_systemlog
{
	private int operation_id; //日志id
	private String operation_matter;//日志内容
	private String operation_person;//操作人员
	private String operation_date;//日志操作日期
	private String operation_time;//日志操作时间

	public Tbl_systemlog()
	{
	}

	public Tbl_systemlog(int operation_id, String operation_matter, String operation_person, String operation_date, String operation_time)
	{
		this.operation_id = operation_id;
		this.operation_matter = operation_matter;
		this.operation_person = operation_person;
		this.operation_date = operation_date;
		this.operation_time = operation_time;
	}

	@Override
	public String toString()
	{
		return "Tbl_systemlog{" + "operation_id=" + operation_id + ", operation_matter='" + operation_matter + '\'' + ", operation_person='" + operation_person + '\'' + ", operation_date='" + operation_date + '\'' + ", operation_time='" + operation_time + '\'' + '}';
	}

	public int getOperation_id()
	{
		return operation_id;
	}

	public void setOperation_id(int operation_id)
	{
		this.operation_id = operation_id;
	}

	public String getOperation_matter()
	{
		return operation_matter;
	}

	public void setOperation_matter(String operation_matter)
	{
		this.operation_matter = operation_matter;
	}

	public String getOperation_person()
	{
		return operation_person;
	}

	public void setOperation_person(String operation_person)
	{
		this.operation_person = operation_person;
	}

	public String getOperation_date()
	{
		return operation_date;
	}

	public void setOperation_date(String operation_date)
	{
		this.operation_date = operation_date;
	}

	public String getOperation_time()
	{
		return operation_time;
	}

	public void setOperation_time(String operation_time)
	{
		this.operation_time = operation_time;
	}
}
