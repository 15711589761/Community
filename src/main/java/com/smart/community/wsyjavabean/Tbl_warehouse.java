package com.smart.community.wsyjavabean;

public class Tbl_warehouse
{
	private int manifest_id;//编号
	private String manifest_number;//货物单号
	private String manifest_name;//货物名称
	private int manifest_quantity;//货物数量

	public Tbl_warehouse()
	{
	}

	public Tbl_warehouse(int manifest_id, String manifest_number, String manifest_name, int manifest_quantity)
	{
		this.manifest_id = manifest_id;
		this.manifest_number = manifest_number;
		this.manifest_name = manifest_name;
		this.manifest_quantity = manifest_quantity;
	}

	@Override
	public String toString()
	{
		return "Tbl_warehouse{" + "manifest_id=" + manifest_id + ", manifest_number='" + manifest_number + '\'' + ", manifest_name='" + manifest_name + '\'' + ", manifest_quantity=" + manifest_quantity + '}';
	}

	public int getManifest_id()
	{
		return manifest_id;
	}

	public void setManifest_id(int manifest_id)
	{
		this.manifest_id = manifest_id;
	}

	public String getManifest_number()
	{
		return manifest_number;
	}

	public void setManifest_number(String manifest_number)
	{
		this.manifest_number = manifest_number;
	}

	public String getManifest_name()
	{
		return manifest_name;
	}

	public void setManifest_name(String manifest_name)
	{
		this.manifest_name = manifest_name;
	}

	public int getManifest_quantity()
	{
		return manifest_quantity;
	}

	public void setManifest_quantity(int manifest_quantity)
	{
		this.manifest_quantity = manifest_quantity;
	}
}
