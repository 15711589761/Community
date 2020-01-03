package com.smart.community.zkbean;

public class Zk_MenuBean
{
	private int menuId;
	private String menuName;
	private String parent;

	public Zk_MenuBean()
	{
	}

	public Zk_MenuBean(int menuId, String menuName, String parent)
	{
		this.menuId = menuId;
		this.menuName = menuName;
		this.parent = parent;
	}

	public int getMenuId()
	{
		return menuId;
	}

	public void setMenuId(int menuId)
	{
		this.menuId = menuId;
	}

	public String getMenuName()
	{
		return menuName;
	}

	public void setMenuName(String menuName)
	{
		this.menuName = menuName;
	}

	public String getParent()
	{
		return parent;
	}

	public void setParent(String parent)
	{
		this.parent = parent;
	}
}
