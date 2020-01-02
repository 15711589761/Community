package com.smart.community.wsyjavabean;

public class Tbl_purchase
{

	private int purchase_id;//采购id
	private String purchase_name;//采购商品名
	private String purchase_model;//商品型号
	private String purchase_quantity;//商品数量
	private String purchase_price;//商品价格
	private String applicant;//申请人
	private String applicant_time;//申请时间
	private String reviewer;//审核人
	private String reviewer_status;//审核状态

	public Tbl_purchase()
	{
	}

	public Tbl_purchase(int purchase_id, String purchase_name, String purchase_model, String purchase_quantity, String purchase_price, String applicant, String applicant_time, String reviewer, String reviewer_status)
	{
		this.purchase_id = purchase_id;
		this.purchase_name = purchase_name;
		this.purchase_model = purchase_model;
		this.purchase_quantity = purchase_quantity;
		this.purchase_price = purchase_price;
		this.applicant = applicant;
		this.applicant_time = applicant_time;
		this.reviewer = reviewer;
		this.reviewer_status = reviewer_status;
	}

	@Override
	public String toString()
	{
		return "Tbl_purchase{" + "purchase_id=" + purchase_id + ", purchase_name='" + purchase_name + '\'' + ", purchase_model='" + purchase_model + '\'' + ", purchase_quantity='" + purchase_quantity + '\'' + ", purchase_price='" + purchase_price + '\'' + ", applicant='" + applicant + '\'' + ", applicant_time='" + applicant_time + '\'' + ", reviewer='" + reviewer + '\'' + ", reviewer_status='" + reviewer_status + '\'' + '}';
	}

	public int getPurchase_id()
	{
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id)
	{
		this.purchase_id = purchase_id;
	}

	public String getPurchase_name()
	{
		return purchase_name;
	}

	public void setPurchase_name(String purchase_name)
	{
		this.purchase_name = purchase_name;
	}

	public String getPurchase_model()
	{
		return purchase_model;
	}

	public void setPurchase_model(String purchase_model)
	{
		this.purchase_model = purchase_model;
	}

	public String getPurchase_quantity()
	{
		return purchase_quantity;
	}

	public void setPurchase_quantity(String purchase_quantity)
	{
		this.purchase_quantity = purchase_quantity;
	}

	public String getPurchase_price()
	{
		return purchase_price;
	}

	public void setPurchase_price(String purchase_price)
	{
		this.purchase_price = purchase_price;
	}

	public String getApplicant()
	{
		return applicant;
	}

	public void setApplicant(String applicant)
	{
		this.applicant = applicant;
	}

	public String getApplicant_time()
	{
		return applicant_time;
	}

	public void setApplicant_time(String applicant_time)
	{
		this.applicant_time = applicant_time;
	}

	public String getReviewer()
	{
		return reviewer;
	}

	public void setReviewer(String reviewer)
	{
		this.reviewer = reviewer;
	}

	public String getReviewer_status()
	{
		return reviewer_status;
	}

	public void setReviewer_status(String reviewer_status)
	{
		this.reviewer_status = reviewer_status;
	}
}
