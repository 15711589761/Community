package com.smart.community.wsyjavabean;

public class Tbl_cashier
{
	private int cashierId; //出纳id
	private String cashierName; //出纳人
	private String cashierMoney; // 报销金额
	private String cashierAccount; //出款账户
	private String cashierType; //报销类型
	private String payee; //出款人
	private String applicant; //申请人

	public Tbl_cashier()
	{
	}

	public Tbl_cashier(int cashierId, String cashierName, String cashierMoney, String cashierAccount, String cashierType, String payee, String applicant)
	{
		this.cashierId = cashierId;
		this.cashierName = cashierName;
		this.cashierMoney = cashierMoney;
		this.cashierAccount = cashierAccount;
		this.cashierType = cashierType;
		this.payee = payee;
		this.applicant = applicant;
	}

	@Override
	public String toString()
	{
		return "Tbl_cashier{" + "cashierId=" + cashierId + ", cashierName='" + cashierName + '\'' + ", cashierMoney='" + cashierMoney + '\'' + ", cashierAccount='" + cashierAccount + '\'' + ", cashierType='" + cashierType + '\'' + ", payee='" + payee + '\'' + ", applicant='" + applicant + '\'' + '}';
	}

	public int getCashierId()
	{
		return cashierId;
	}

	public void setCashierId(int cashierId)
	{
		this.cashierId = cashierId;
	}

	public String getCashierName()
	{
		return cashierName;
	}

	public void setCashierName(String cashierName)
	{
		this.cashierName = cashierName;
	}

	public String getCashierMoney()
	{
		return cashierMoney;
	}

	public void setCashierMoney(String cashierMoney)
	{
		this.cashierMoney = cashierMoney;
	}

	public String getCashierAccount()
	{
		return cashierAccount;
	}

	public void setCashierAccount(String cashierAccount)
	{
		this.cashierAccount = cashierAccount;
	}

	public String getCashierType()
	{
		return cashierType;
	}

	public void setCashierType(String cashierType)
	{
		this.cashierType = cashierType;
	}

	public String getPayee()
	{
		return payee;
	}

	public void setPayee(String payee)
	{
		this.payee = payee;
	}

	public String getApplicant()
	{
		return applicant;
	}

	public void setApplicant(String applicant)
	{
		this.applicant = applicant;
	}
}
