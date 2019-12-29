package com.smart.community.ljmdao;

import com.smart.community.ljmbean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LjmDeskMapper
{
	/**
	 * 前端登入验证
	 * @param loginBean 登入信息类
	 * @return 业主信息类
	 */
	public List<OwnerBean> selectForDeskLogin(LoginBean loginBean);

	/**
	 * 业主一级菜单获取
	 * @return 前端一级菜单集合
	 */
	public List<DeskMenuBean> selectForGetDeskMenu();

	/**
	 * 获取选中的父级菜单
	 * @param selectedMenuId 选中的父级菜单id
	 * @return 父级菜单信息类
	 */
	public DeskMenuBean selectForGetSelectedMenu(int selectedMenuId );

	/**
	 * 获取对应的子菜单
	 * @param menuParentId 父级菜单id
	 * @return 子菜单集合
	 */
	public List<DeskMenuBean> selectForGetDeskChildrenMenu( @Param ("menuParentId") int menuParentId );

	/**
	 * 投诉和建议
	 * @param tableSearchBean 搜索参数
	 * @return 投诉建议记录集合
	 */
	public List<SuggestBean> selectForGetSuggestTable( TableSearchBean tableSearchBean );

	/**
	 * 添加投诉或者建议
	 * @param suggestBean 添加信息
	 * @return int
	 */
	public int insertToSuggestRecord(SuggestBean suggestBean);

	/**
	 * 投诉或建议不展示
	 * @param suggestId id
	 * @return int
	 */
	public int deleteToSuggestRecord(String suggestId);
}
