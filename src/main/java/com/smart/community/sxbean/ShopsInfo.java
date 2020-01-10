package com.smart.community.sxbean;

public class ShopsInfo
{
	private int  goodsId;
	private String goodsName;
	private int goodsPrice;
	private String goodsImage;
	private int goodsSortId;
	private int goodsShopTypeId;
	private int goodsShopsNumber;
	private int goodsOff;
	private String goodsContext;

	public ShopsInfo(int goodsId, String goodsName, int goodsPrice, String goodsImage, int goodsSortId, int goodsShopTypeId, int goodsShopsNumber, int goodsOff, String goodsContext)
	{
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsImage = goodsImage;
		this.goodsSortId = goodsSortId;
		this.goodsShopTypeId = goodsShopTypeId;
		this.goodsShopsNumber = goodsShopsNumber;
		this.goodsOff = goodsOff;
		this.goodsContext = goodsContext;
	}

	public ShopsInfo()
	{
	}

	public int getGoodsId()
	{
		return goodsId;
	}

	public void setGoodsId(int goodsId)
	{
		this.goodsId = goodsId;
	}

	public String getGoodsName()
	{
		return goodsName;
	}

	public void setGoodsName(String goodsName)
	{
		this.goodsName = goodsName;
	}

	public int getGoodsPrice()
	{
		return goodsPrice;
	}

	public void setGoodsPrice(int goodsPrice)
	{
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsImage()
	{
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage)
	{
		this.goodsImage = goodsImage;
	}

	public int getGoodsSortId()
	{
		return goodsSortId;
	}

	public void setGoodsSortId(int goodsSortId)
	{
		this.goodsSortId = goodsSortId;
	}

	public int getGoodsShopTypeId()
	{
		return goodsShopTypeId;
	}

	public void setGoodsShopTypeId(int goodsShopTypeId)
	{
		this.goodsShopTypeId = goodsShopTypeId;
	}

	public int getGoodsShopsNumber()
	{
		return goodsShopsNumber;
	}

	public void setGoodsShopsNumber(int goodsShopsNumber)
	{
		this.goodsShopsNumber = goodsShopsNumber;
	}

	public int getGoodsOff()
	{
		return goodsOff;
	}

	public void setGoodsOff(int goodsOff)
	{
		this.goodsOff = goodsOff;
	}

	public String getGoodsContext()
	{
		return goodsContext;
	}

	public void setGoodsContext(String goodsContext)
	{
		this.goodsContext = goodsContext;
	}
}
