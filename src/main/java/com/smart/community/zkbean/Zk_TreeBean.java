package com.smart.community.zkbean;

import java.util.List;

public class Zk_TreeBean
{
	private int id;
	private String title;
	//节点是否初始展开
	private boolean spread = false;
	//节点是否初始为选中状态
	private boolean checked = false;
	//节点是否为禁用状态
	private boolean disabled = false;

	private List<Zk_TreeBean> children;
	private String parent;

	public Zk_TreeBean()
	{
	}

	public Zk_TreeBean(String title, int id, boolean spread, boolean checked, List<Zk_TreeBean> children, String parent)
	{
		this.title = title;
		this.id = id;
		this.spread = spread;
		this.checked = checked;
		this.children = children;
		this.parent = parent;
	}

	public Zk_TreeBean(int id, String title, boolean spread, boolean checked, boolean disabled, List<Zk_TreeBean> children, String parent)
	{
		this.id = id;
		this.title = title;
		this.spread = spread;
		this.checked = checked;
		this.disabled = disabled;
		this.children = children;
		this.parent = parent;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public boolean isSpread()
	{
		return spread;
	}

	public void setSpread(boolean spread)
	{
		this.spread = spread;
	}

	public boolean isChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}

	public List<Zk_TreeBean> getChildren()
	{
		return children;
	}

	public void setChildren(List<Zk_TreeBean> children)
	{
		this.children = children;
	}

	public String getParent()
	{
		return parent;
	}

	public void setParent(String parent)
	{
		this.parent = parent;
	}

	public boolean isDisabled()
	{
		return disabled;
	}

	public void setDisabled(boolean disabled)
	{
		this.disabled = disabled;
	}

	@Override
	public String toString()
	{
		return "Zk_TreeBean{" + "id=" + id + ", title='" + title + '\'' + ", children=" + children + ", parent='" + parent + '\'' + '}';
	}
}
