package com.smart.community.wsyjavabean;

public class Wsy_EcharsBean
{
	private String collectionName;
	private String collectionMoney;

	public Wsy_EcharsBean()
	{
	}

	public Wsy_EcharsBean(String collectionName, String collectionMoney)
	{
		this.collectionName = collectionName;
		this.collectionMoney = collectionMoney;
	}

	@Override
	public String toString()
	{
		return "Wsy_EcharsBean{" + "collectionName='" + collectionName + '\'' + ", collectionMoney='" + collectionMoney + '\'' + '}';
	}

	public String getCollectionName()
	{
		return collectionName;
	}

	public void setCollectionName(String collectionName)
	{
		this.collectionName = collectionName;
	}

	public String getCollectionMoney()
	{
		return collectionMoney;
	}

	public void setCollectionMoney(String collectionMoney)
	{
		this.collectionMoney = collectionMoney;
	}
}
