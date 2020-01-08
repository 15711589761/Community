package com.smart.community.venjavabean;

/**
 * 公告实体类
 * @author Ven
 */
public class NoticeBean
{
	/**公告id*/
	private int noticeId;
	/**公告标题*/
	private String noticeTitle;
	/**公告内容*/
	private String noticeDetails;
	/**发布者的id*/
	private int issuerId;
	/**发布者*/
	private String issuer;
	/**发布日期*/
	private String issuedDate;

	public int getNoticeId()
	{
		return noticeId;
	}

	public void setNoticeId(int noticeId)
	{
		this.noticeId = noticeId;
	}

	public String getNoticeTitle()
	{
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle)
	{
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeDetails()
	{
		return noticeDetails;
	}

	public void setNoticeDetails(String noticeDetails)
	{
		this.noticeDetails = noticeDetails;
	}

	public int getIssuerId()
	{
		return issuerId;
	}

	public void setIssuerId(int issuerId)
	{
		this.issuerId = issuerId;
	}

	public String getIssuer()
	{
		return issuer;
	}

	public void setIssuer(String issuer)
	{
		this.issuer = issuer;
	}

	public String getIssuedDate()
	{
		return issuedDate;
	}

	public void setIssuedDate(String issuedDate)
	{
		this.issuedDate = issuedDate;
	}
}
