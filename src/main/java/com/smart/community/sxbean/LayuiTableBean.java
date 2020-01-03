package com.smart.community.sxbean;

import java.util.List;

public class LayuiTableBean
{
	private int code;
	private String msg;
	private List<?> data;
	private int count;

	@Override
	public String toString()
	{
		return "LayuiTableBean{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + ", count=" + count + '}';
	}

	public int getCode()
	{
		return code;
	}

	public void setCode( int code )
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg( String msg )
	{
		this.msg = msg;
	}

	public List<?> getData()
	{
		return data;
	}

	public void setData( List<?> data )
	{
		this.data = data;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount( int count )
	{
		this.count = count;
	}
}
