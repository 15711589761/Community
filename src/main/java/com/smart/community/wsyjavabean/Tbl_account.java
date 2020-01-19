package com.smart.community.wsyjavabean;

public class Tbl_account
{
	private int accountId; //对账id
	private String accountMoney;//收支金额
	private String accountTime; //收支时间
	private String accountType; //收支类型
	private String accountRemark; //收支备注

	public Tbl_account()
	{
	}

	public Tbl_account(int accountId, String accountMoney, String accountTime, String accountType, String accountRemark)
	{
		this.accountId = accountId;
		this.accountMoney = accountMoney;
		this.accountTime = accountTime;
		this.accountType = accountType;
		this.accountRemark = accountRemark;
	}

	@Override
	public String toString()
	{
		return "Tbl_account{" + "accountId=" + accountId + ", accountMoney='" + accountMoney + '\'' + ", accountTime='" + accountTime + '\'' + ", accountType='" + accountType + '\'' + ", accountRemark='" + accountRemark + '\'' + '}';
	}

	public int getAccountId()
	{
		return accountId;
	}

	public void setAccountId(int accountId)
	{
		this.accountId = accountId;
	}

	public String getAccountMoney()
	{
		return accountMoney;
	}

	public void setAccountMoney(String accountMoney)
	{
		this.accountMoney = accountMoney;
	}

	public String getAccountTime()
	{
		return accountTime;
	}

	public void setAccountTime(String accountTime)
	{
		this.accountTime = accountTime;
	}

	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	public String getAccountRemark()
	{
		return accountRemark;
	}

	public void setAccountRemark(String accountRemark)
	{
		this.accountRemark = accountRemark;
	}
}
