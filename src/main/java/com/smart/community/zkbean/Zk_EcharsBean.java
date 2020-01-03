package com.smart.community.zkbean;

public class Zk_EcharsBean
{
	private String billname;
	private int record;

	public Zk_EcharsBean()
	{
	}

	public Zk_EcharsBean(String billname, int record)
	{
		this.billname = billname;
		this.record = record;
	}


	public String getBillname()
	{
		return billname;
	}

	public void setBillname(String billname)
	{
		this.billname = billname;
	}

	public int getRecord()
	{
		return record;
	}

	public void setRecord(int record)
	{
		this.record = record;
	}
}

