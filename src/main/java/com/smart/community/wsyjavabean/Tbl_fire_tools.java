package com.smart.community.wsyjavabean;

public class Tbl_fire_tools
{
	private int fire_tools_id;//设备id
	private String fire_tools_name;//设备名称
	private String fire_tools_classification;//设备分类
	private String buy_date;//设备购买时间

	public Tbl_fire_tools()
	{
	}

	public Tbl_fire_tools(int fire_tools_id, String fire_tools_name, String fire_tools_classification, String buy_date)
	{
		this.fire_tools_id = fire_tools_id;
		this.fire_tools_name = fire_tools_name;
		this.fire_tools_classification = fire_tools_classification;
		this.buy_date = buy_date;
	}

	@Override
	public String toString()
	{
		return "Tbl_fire_tools{" + "fire_tools_id=" + fire_tools_id + ", fire_tools_name='" + fire_tools_name + '\'' + ", fire_tools_classification='" + fire_tools_classification + '\'' + ", buy_date='" + buy_date + '\'' + '}';
	}

	public int getFire_tools_id()
	{
		return fire_tools_id;
	}

	public void setFire_tools_id(int fire_tools_id)
	{
		this.fire_tools_id = fire_tools_id;
	}

	public String getFire_tools_name()
	{
		return fire_tools_name;
	}

	public void setFire_tools_name(String fire_tools_name)
	{
		this.fire_tools_name = fire_tools_name;
	}

	public String getFire_tools_classification()
	{
		return fire_tools_classification;
	}

	public void setFire_tools_classification(String fire_tools_classification)
	{
		this.fire_tools_classification = fire_tools_classification;
	}

	public String getBuy_date()
	{
		return buy_date;
	}

	public void setBuy_date(String buy_date)
	{
		this.buy_date = buy_date;
	}
}
