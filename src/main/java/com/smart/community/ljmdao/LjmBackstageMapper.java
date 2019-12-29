package com.smart.community.ljmdao;

import com.smart.community.ljmbean.BackstageMenuBean;
import com.smart.community.ljmbean.DeskMenuBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author LJM
 */
@Mapper
public interface LjmBackstageMapper
{
	/**
	 * 菜单管理表格展示
	 * @param menuPid  菜单父类id
	 * @param menuName 菜单名称
	 * @param page     页数
	 * @param limit    每页数据条数
	 * @return 菜单信息
	 */
	public List<BackstageMenuBean> selectForShowBackstageMenuTable( @Param("menuPid") String menuPid, @Param("menuName") String menuName, @Param("page") int page, @Param("limit") int limit );

	/**
	 * 菜单管理表格展示记录数
	 * @param menuPid  菜单父类id
	 * @param menuName 菜单名称
	 * @return 菜单信息
	 */
	public int selectForShowBackstageMenuCount( @Param("menuPid") String menuPid, @Param("menuName") String menuName );

	/**
	 * 添加二级菜单时用到的一级菜单集合
	 *
	 * @return 一级菜单集合
	 */
	public List<BackstageMenuBean> selectForBackstageDialogParentMenu();

	/**
	 * 菜单添加
	 *
	 * @param params 所需参数
	 * @return int
	 */
	public int insertForBackstageAddMenuRecord( Map<String, String> params );

	/**
	 * 修改菜单
	 * @param params 参数
	 * @return int
	 */
	public int updateForBackstageUpMenuRecord( Map<String, String> params );

	/**
	 * 删除菜单
	 * @param menuId 菜单id
	 * @return int
	 */
	public int deleteForBackstageDelMenuRecord(String menuId);

	/**
	 * 启用禁用菜单
	 * @param menuStatus 菜单状态
	 * @param menuId 菜单id
	 */
	public int updateForBackstageSetAble(@Param("menuStatus")String menuStatus , @Param("menuId")String menuId);

	/**
	 * 前端菜单表格展示
	 * @param menuPid  菜单父类id
	 * @param menuName 菜单名称
	 * @param page     页数
	 * @param limit    每页数据条数
	 * @return 菜单信息
	 */
	public List<DeskMenuBean> selectForShowDeskMenuTable( @Param("menuPid") String menuPid, @Param("menuName") String menuName, @Param("page") int page, @Param("limit") int limit );

	/**
	 * 业主菜单管理表格展示记录数
	 * @param menuPid  菜单父类id
	 * @param menuName 菜单名称
	 * @return 菜单信息
	 */
	public int selectForShowDeskMenuCount( @Param("menuPid") String menuPid, @Param("menuName") String menuName );

	/**
	 * 业主菜单添加
	 *
	 * @param params 所需参数
	 * @return int
	 */
	public int insertForDeskAddMenuRecord( Map<String, String> params );

	/**
	 * 修改业主菜单
	 * @param params 参数
	 * @return int
	 */
	public int updateForDeskUpMenuRecord( Map<String, String> params );

	/**
	 * 删除业主菜单
	 * @param menuId 菜单id
	 * @return int
	 */
	public int deleteForDeskDelMenuRecord(String menuId);

	/**
	 * 启用禁用业主菜单
	 * @param menuStatus 菜单状态
	 * @param menuId 菜单id
	 */
	public int updateForDeskSetAble(@Param("menuStatus")String menuStatus , @Param("menuId")String menuId);
}
