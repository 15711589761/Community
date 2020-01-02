package com.smart.community.wsyjavabean;

public class Tbl_ontract
{
	private int ontract_id; //合同编号
	private String ontract_name;//合同名称
	private String ontract_uploadPath;//合同上传路径
	private String ontract_downPath;//合同下载时间
	private String ontract_time;//合同上传时间

	public Tbl_ontract()
	{
	}

	public Tbl_ontract(int ontract_id, String ontract_name, String ontract_uploadPath, String ontract_downPath, String ontract_time)
	{
		this.ontract_id = ontract_id;
		this.ontract_name = ontract_name;
		this.ontract_uploadPath = ontract_uploadPath;
		this.ontract_downPath = ontract_downPath;
		this.ontract_time = ontract_time;
	}

	@Override
	public String toString()
	{
		return "Tbl_ontract{" + "ontract_id=" + ontract_id + ", ontract_name='" + ontract_name + '\'' + ", ontract_uploadPath='" + ontract_uploadPath + '\'' + ", ontract_downPath='" + ontract_downPath + '\'' + ", ontract_time='" + ontract_time + '\'' + '}';
	}

	public int getOntract_id()
	{
		return ontract_id;
	}

	public void setOntract_id(int ontract_id)
	{
		this.ontract_id = ontract_id;
	}

	public String getOntract_name()
	{
		return ontract_name;
	}

	public void setOntract_name(String ontract_name)
	{
		this.ontract_name = ontract_name;
	}

	public String getOntract_uploadPath()
	{
		return ontract_uploadPath;
	}

	public void setOntract_uploadPath(String ontract_uploadPath)
	{
		this.ontract_uploadPath = ontract_uploadPath;
	}

	public String getOntract_downPath()
	{
		return ontract_downPath;
	}

	public void setOntract_downPath(String ontract_downPath)
	{
		this.ontract_downPath = ontract_downPath;
	}

	public String getOntract_time()
	{
		return ontract_time;
	}

	public void setOntract_time(String ontract_time)
	{
		this.ontract_time = ontract_time;
	}
}
