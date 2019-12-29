package com.smart.community.ljmbean;

import java.util.List;

/**
 * @author LJM
 */
public class DeskMenuBean
{
	private int menuId;
	private String menuName;
	private String menuUrl;
	private int menuPid;
	private String menuStatus;
	private List<DeskMenuBean> childMenus;

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

	public String getMenuUrl()
	{
		return menuUrl;
	}

	public void setMenuUrl( String menuUrl )
	{
		this.menuUrl = menuUrl;
	}

	public int getMenuPid()
	{
		return menuPid;
	}

	public void setMenuPid( int menuPid )
	{
		this.menuPid = menuPid;
	}

	public List<DeskMenuBean> getChildMenus()
	{
		return childMenus;
	}

	public void setChildMenus( List<DeskMenuBean> childMenus )
	{
		this.childMenus = childMenus;
	}

	public String getMenuStatus()
	{
		return menuStatus;
	}

	public void setMenuStatus( String menuStatus )
	{
		this.menuStatus = menuStatus;
	}
}
