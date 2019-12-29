package com.smart.community.ljmbean;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 登录信息过滤收集
 * @author LJM
 */
public class LoginBean
{
	@NotEmpty(message = "登录的工号不能为空！")
	private String loginName;
	@NotEmpty(message = "登录的密码不能为空！")
	private String loginPassWord;
	@NotEmpty(message = "请输入验证码！")
	private String imageCode;

	@Override
	public String toString()
	{
		return "LoginBean{" + "loginName='" + loginName + '\'' + ", loginPassWord='" + loginPassWord + '\'' + ", imageCode='" + imageCode + '\'' + '}';
	}

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName( String loginName )
	{
		this.loginName = loginName;
	}

	public String getLoginPassWord()
	{
		return loginPassWord;
	}

	public void setLoginPassWord( String loginPassWord )
	{
		this.loginPassWord = loginPassWord;
	}

	public String getImageCode()
	{
		return imageCode;
	}

	public void setImageCode( String imageCode )
	{
		this.imageCode = imageCode;
	}
}
