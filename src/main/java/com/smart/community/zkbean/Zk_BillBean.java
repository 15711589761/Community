package com.smart.community.zkbean;

public class Zk_BillBean
{
	private int billid;
	//收款人
	private String payee;
	//付款人
	private String payer;
	//详细信息
	private String details;
	//时间
	private String billDate;
	//金额
	private String money;
	//订单号
	private String orderNo;

	public Zk_BillBean()
	{
	}

	public Zk_BillBean(int billid, String payee, String payer, String details, String billDate, String money, String orderNo)
	{
		this.billid = billid;
		this.payee = payee;
		this.payer = payer;
		this.details = details;
		this.billDate = billDate;
		this.money = money;
		this.orderNo = orderNo;
	}

	public int getBillid()
	{
		return billid;
	}

	public void setBillid(int billid)
	{
		this.billid = billid;
	}

	public String getPayee()
	{
		return payee;
	}

	public void setPayee(String payee)
	{
		this.payee = payee;
	}

	public String getPayer()
	{
		return payer;
	}

	public void setPayer(String payer)
	{
		this.payer = payer;
	}

	public String getDetails()
	{
		return details;
	}

	public void setDetails(String details)
	{
		this.details = details;
	}

	public String getBillDate()
	{
		return billDate;
	}

	public void setBillDate(String billDate)
	{
		this.billDate = billDate;
	}

	public String getMoney()
	{
		return money;
	}

	public void setMoney(String money)
	{
		this.money = money;
	}


	public String getOrderNo()
	{
		return orderNo;
	}

	public void setOrderNo(String orderNo)
	{
		this.orderNo = orderNo;
	}
}
