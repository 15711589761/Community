package com.smart.community.wsyjavabean;

public class Tbl_parameter
{
	private int parameter_id;
	private String parameter_name;
	private String parameter_remark;

	public Tbl_parameter(int parameter_id, String parameter_name, String parameter_remark)
	{
		this.parameter_id = parameter_id;
		this.parameter_name = parameter_name;
		this.parameter_remark = parameter_remark;
	}

	public Tbl_parameter()
	{
	}

	@Override
	public String toString()
	{
		return "Tbl_parameter{" + "parameter_id=" + parameter_id + ", parameter_name='" + parameter_name + '\'' + ", parameter_remark='" + parameter_remark + '\'' + '}';
	}

	public int getParameter_id()
	{
		return parameter_id;
	}

	public void setParameter_id(int parameter_id)
	{
		this.parameter_id = parameter_id;
	}

	public String getParameter_name()
	{
		return parameter_name;
	}

	public void setParameter_name(String parameter_name)
	{
		this.parameter_name = parameter_name;
	}

	public String getParameter_remark()
	{
		return parameter_remark;
	}

	public void setParameter_remark(String parameter_remark)
	{
		this.parameter_remark = parameter_remark;
	}
}
