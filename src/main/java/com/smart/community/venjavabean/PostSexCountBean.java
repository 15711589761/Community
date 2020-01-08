package com.smart.community.venjavabean;

/**
 * 各个岗位的男女人数统计
 * @author Ven
 */
public class PostSexCountBean
{
	private String roleName;
	private int maleNumber;
	private int femaleNumber;

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public int getMaleNumber()
	{
		return maleNumber;
	}

	public void setMaleNumber(int maleNumber)
	{
		this.maleNumber = maleNumber;
	}

	public int getFemaleNumber()
	{
		return femaleNumber;
	}

	public void setFemaleNumber(int femaleNumber)
	{
		this.femaleNumber = femaleNumber;
	}
}
