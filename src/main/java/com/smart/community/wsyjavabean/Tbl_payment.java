package com.smart.community.wsyjavabean;

public class Tbl_payment
{
	private int payment_id;  //付款id
	private String payment_name;//付款人
	private String payment_money;//付款金额
    private String payment_type;//付款类型
	private String payment_time;//付款时间
	private String payment_remarks;//备注

	public Tbl_payment()
	{
	}

	public Tbl_payment(int payment_id, String payment_name, String payment_money, String payment_type, String payment_time, String payment_remarks)
	{
		this.payment_id = payment_id;
		this.payment_name = payment_name;
		this.payment_money = payment_money;
		this.payment_type = payment_type;
		this.payment_time = payment_time;
		this.payment_remarks = payment_remarks;
	}

	@Override
	public String toString()
	{
		return "Tbl_payment{" + "payment_id=" + payment_id + ", payment_name='" + payment_name + '\'' + ", payment_money='" + payment_money + '\'' + ", payment_type='" + payment_type + '\'' + ", payment_time='" + payment_time + '\'' + ", payment_remarks='" + payment_remarks + '\'' + '}';
	}

	public int getPayment_id()
	{
		return payment_id;
	}

	public void setPayment_id(int payment_id)
	{
		this.payment_id = payment_id;
	}

	public String getPayment_name()
	{
		return payment_name;
	}

	public void setPayment_name(String payment_name)
	{
		this.payment_name = payment_name;
	}

	public String getPayment_money()
	{
		return payment_money;
	}

	public void setPayment_money(String payment_money)
	{
		this.payment_money = payment_money;
	}

	public String getPayment_type()
	{
		return payment_type;
	}

	public void setPayment_type(String payment_type)
	{
		this.payment_type = payment_type;
	}

	public String getPayment_time()
	{
		return payment_time;
	}

	public void setPayment_time(String payment_time)
	{
		this.payment_time = payment_time;
	}

	public String getPayment_remarks()
	{
		return payment_remarks;
	}

	public void setPayment_remarks(String payment_remarks)
	{
		this.payment_remarks = payment_remarks;
	}
}
