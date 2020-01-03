package com.smart.community.venjavabean;

public class ResumeBean
{
	private int resumeId;
	private String resumeTitle;
	private String resumeUrl;
	private String applicantName;
	private String applicantTel;
	private String uploadDate;

	public String getUploadDate()
	{
		return uploadDate;
	}

	public void setUploadDate(String uploadDate)
	{
		this.uploadDate = uploadDate;
	}

	public int getResumeId()
	{
		return resumeId;
	}

	public void setResumeId(int resumeId)
	{
		this.resumeId = resumeId;
	}

	public String getResumeTitle()
	{
		return resumeTitle;
	}

	public void setResumeTitle(String resumeTitle)
	{
		this.resumeTitle = resumeTitle;
	}

	public String getResumeUrl()
	{
		return resumeUrl;
	}

	public void setResumeUrl(String resumeUrl)
	{
		this.resumeUrl = resumeUrl;
	}

	public String getApplicantName()
	{
		return applicantName;
	}

	public void setApplicantName(String applicantName)
	{
		this.applicantName = applicantName;
	}

	public String getApplicantTel()
	{
		return applicantTel;
	}

	public void setApplicantTel(String applicantTel)
	{
		this.applicantTel = applicantTel;
	}
}
