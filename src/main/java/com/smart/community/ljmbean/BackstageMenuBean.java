package com.smart.community.ljmbean;

public class BackstageMenuBean
{
	private int menuId;
	private String menuName;
	private int menuPid;
	private String toUrl;
	private String menuStatus;

	public String getMenuStatus()
	{
		return menuStatus;
	}

	public void setMenuStatus( String menuStatus )
	{
		this.menuStatus = menuStatus;
	}

	public int getMenuId()
	{
		return menuId;
	}

	public void setMenuId( int menuId )
	{
		this.menuId = menuId;
	}

	public String getMenuName()
	{
		return menuName;
	}

	public void setMenuName( String menuName )
	{
		this.menuName = menuName;
	}

	public int getMenuPid()
	{
		return menuPid;
	}

	public void setMenuPid( int menuPid )
	{
		this.menuPid = menuPid;
	}

	public String getToUrl()
	{
		return toUrl;
	}

	public void setToUrl( String toUrl )
	{
		this.toUrl = toUrl;
	}
}
