package com.smart.community.sxbean;

public class StoreInfo
{
	private int storeId;
	private String storeAddress;
	private String storeAttr;
	private String sellPrice;
	private String finalPrice;
	private String sellDate;
	private String storeTel;
	private int storeType;
	private String storeSize;
	private String storeImg;

	public StoreInfo(int storeId, String storeAddress, String storeAttr, String sellPrice, String finalPrice, String sellDate, String storeTel, int storeType ,String storeSize,String storeImg)
	{
		this.storeId = storeId;
		this.storeAddress = storeAddress;
		this.storeAttr = storeAttr;
		this.sellPrice = sellPrice;
		this.finalPrice = finalPrice;
		this.sellDate = sellDate;
		this.storeTel = storeTel;
		this.storeType = storeType;
		this.storeSize = storeSize;
		this.storeImg = storeImg;
	}

	public StoreInfo()
	{
	}

	public int getStoreId()
	{
		return storeId;
	}

	public void setStoreId(int storeId)
	{
		this.storeId = storeId;
	}

	public String getStoreAddress()
	{
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress)
	{
		this.storeAddress = storeAddress;
	}

	public String getStoreAttr()
	{
		return storeAttr;
	}

	public void setStoreAttr(String storeAttr)
	{
		this.storeAttr = storeAttr;
	}

	public String getSellPrice()
	{
		return sellPrice;
	}

	public void setSellPrice(String sellPrice)
	{
		this.sellPrice = sellPrice;
	}

	public String getFinalPrice()
	{
		return finalPrice;
	}

	public void setFinalPrice(String finalPrice)
	{
		this.finalPrice = finalPrice;
	}

	public String getSellDate()
	{
		return sellDate;
	}

	public void setSellDate(String sellDate)
	{
		this.sellDate = sellDate;
	}

	public String getStoreTel()
	{
		return storeTel;
	}

	public void setStoreTel(String storeTel)
	{
		this.storeTel = storeTel;
	}

	public int getStoreType()
	{
		return storeType;
	}

	public void setStoreType(int storeType)
	{
		this.storeType = storeType;
	}

	public String getStoreSize()
	{
		return storeSize;
	}

	public void setStoreSize(String storeSize)
	{
		this.storeSize = storeSize;
	}

	public String getStoreImg()
	{
		return storeImg;
	}

	public void setStoreImg(String storeImg)
	{
		this.storeImg = storeImg;
	}
}
