package com.smart.community.wsyjavabean;

public class Tbl_receivables
{
	private int receivables_Id;    //收款id
	private String receivables_name; //收款人
	private String receivables_type; //收款类型
	private String receivables_money;//收款金额
	private String receivables_time;//收款时间
	private String receivables_remarks;//收款备注

	public Tbl_receivables()
	{
	}

	public Tbl_receivables(int receivables_Id, String receivables_name, String receivables_type, String receivables_money, String receivables_time, String receivables_remarks)
	{
		this.receivables_Id = receivables_Id;
		this.receivables_name = receivables_name;
		this.receivables_type = receivables_type;
		this.receivables_money = receivables_money;
		this.receivables_time = receivables_time;
		this.receivables_remarks = receivables_remarks;
	}

	@Override
	public String toString()
	{
		return "Tbl_receivables{" + "receivables_Id=" + receivables_Id + ", receivables_name='" + receivables_name + '\'' + ", receivables_type='" + receivables_type + '\'' + ", receivables_money='" + receivables_money + '\'' + ", receivables_time='" + receivables_time + '\'' + ", receivables_remarks='" + receivables_remarks + '\'' + '}';
	}

	public int getReceivables_Id()
	{
		return receivables_Id;
	}

	public void setReceivables_Id(int receivables_Id)
	{
		this.receivables_Id = receivables_Id;
	}

	public String getReceivables_name()
	{
		return receivables_name;
	}

	public void setReceivables_name(String receivables_name)
	{
		this.receivables_name = receivables_name;
	}

	public String getReceivables_type()
	{
		return receivables_type;
	}

	public void setReceivables_type(String receivables_type)
	{
		this.receivables_type = receivables_type;
	}

	public String getReceivables_money()
	{
		return receivables_money;
	}

	public void setReceivables_money(String receivables_money)
	{
		this.receivables_money = receivables_money;
	}

	public String getReceivables_time()
	{
		return receivables_time;
	}

	public void setReceivables_time(String receivables_time)
	{
		this.receivables_time = receivables_time;
	}

	public String getReceivables_remarks()
	{
		return receivables_remarks;
	}

	public void setReceivables_remarks(String receivables_remarks)
	{
		this.receivables_remarks = receivables_remarks;
	}
}
