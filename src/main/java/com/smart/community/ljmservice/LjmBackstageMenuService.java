package com.smart.community.ljmservice;

import com.smart.community.ljmbean.BackstageMenuBean;
import com.smart.community.ljmbean.DeskMenuBean;
import com.smart.community.ljmdao.LjmBackstageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LJM
 */
@Service
public class LjmBackstageMenuService
{
	@Resource
	private LjmBackstageMapper backstageMapper;

	/**
	 * 后台菜单管理表格显示搜索
	 * @param menuPid 父级id
	 * @param menuName 菜单名称
	 * @param n page
	 * @param m limit
	 * @return 菜单信息集合
	 */
	public List<BackstageMenuBean> forGetShowTableBackstageMenus(String menuPid,String menuName,String n,String m)
	{
		int limit = Integer.parseInt(m);
		int page = (Integer.parseInt(n)-1)*limit;
		List<BackstageMenuBean> backstageMenuBeans = backstageMapper.selectForShowBackstageMenuTable(menuPid,menuName,page,limit);
		for (BackstageMenuBean backstageMenuBean : backstageMenuBeans)
		{
			if ("0".equals(backstageMenuBean.getMenuStatus()))
			{
				backstageMenuBean.setMenuStatus("启用");
			} else {
				backstageMenuBean.setMenuStatus("禁用");
			}
		}
		return backstageMenuBeans;
	}

	/**
	 * 后台菜单管理表格显示搜索总记录数
	 * @param menuPid 父级id
	 * @param menuName 菜单名称
	 * @return 数量
	 */
	public int forGetShowTableBackstageMenuCount(String menuPid,String menuName)
	{
		return backstageMapper.selectForShowBackstageMenuCount(menuPid,menuName);
	}

	/**
	 * 添加二级菜单时用到的一级菜单集合
	 * @return 一级菜单集合
	 */
	public List<BackstageMenuBean> forDialogParentMenu()
	{
		return backstageMapper.selectForBackstageDialogParentMenu();
	}

	/**
	 * 添加菜单
	 * @param menuName 菜单名称
	 * @param menuPid 菜单父类id
	 * @param menuUrl 菜单路径
	 * @param menuRemark 菜单所属标识 0 后台管理 1 物业管理
	 * @return int
	 */
	public int forAddMenuRecordService(String menuName ,String menuPid,String menuUrl,String menuRemark)
	{
		Map<String,String> params = new HashMap<>();
		params.put("menuName",menuName);params.put("menuPid",menuPid);
		params.put("menuUrl",menuUrl);params.put("menuRemark",menuRemark);
		return backstageMapper.insertForBackstageAddMenuRecord(params);
	}

	/**
	 * 修改菜单
	 * @param menuName 菜单名称
	 * @param menuPid 菜单父级id
	 * @param menuUrl 菜单路径
	 * @param menuRemark 菜单表示 0 后台管理 1 物业端
	 * @param menuId 菜单id
	 * @return int
	 */
	public int forUpdateMenuRecordService(String menuName ,String menuPid,String menuUrl,String menuRemark,String menuId)
	{
		Map<String,String> params = new HashMap<>();
		params.put("menuName",menuName);params.put("menuPid",menuPid);
		params.put("menuUrl",menuUrl);params.put("menuRemark",menuRemark);
		params.put("menuId",menuId);
		return backstageMapper.updateForBackstageUpMenuRecord(params);
	}

	/**
	 * 删除菜单
	 * @param menuId 菜单id
	 * @return int
	 */
	public int forDeleteMenuRecordService(String menuId)
	{
		return backstageMapper.deleteForBackstageDelMenuRecord(menuId);
	}

	/**
	 * 启用禁用
	 * @param menuStatus 菜单状态
	 * @param menuId 菜单id
	 * @return int
	 */
	public int forUpdateMenuDisableService(String menuStatus ,String menuId)
	{
		return backstageMapper.updateForBackstageSetAble(menuStatus,menuId);
	}

	/**
	 * 前端菜单管理表格显示搜索
	 * @param menuPid 父级id
	 * @param menuName 菜单名称
	 * @param n page
	 * @param m limit
	 * @return 菜单信息集合
	 */
	public List<DeskMenuBean> forGetShowTableDeskMenus( String menuPid, String menuName, String n, String m)
	{
		int limit = Integer.parseInt(m);
		int page = (Integer.parseInt(n)-1)*limit;
		return backstageMapper.selectForShowDeskMenuTable(menuPid,menuName,page,limit);
	}

	/**
	 * 前端菜单管理表格显示搜索总记录数
	 * @param menuPid 父级id
	 * @param menuName 菜单名称
	 * @return 数量
	 */
	public int forGetShowTableDeskMenuCount(String menuPid,String menuName)
	{
		return backstageMapper.selectForShowDeskMenuCount(menuPid,menuName);
	}

	/**
	 * 添加前端菜单
	 * @param menuName 菜单名称
	 * @param menuPid 菜单父类id
	 * @param menuUrl 菜单路径
	 * @return int
	 */
	public int forAddDeskMenuRecordService(String menuName ,String menuPid,String menuUrl)
	{
		Map<String,String> params = new HashMap<>();
		if (Integer.parseInt(menuPid)==0)
		{
			menuUrl = "";
		}
		params.put("menuName",menuName);params.put("menuPid",menuPid);
		params.put("menuUrl",menuUrl);
		return backstageMapper.insertForDeskAddMenuRecord(params);
	}

	/**
	 * 修改前端菜单
	 * @param menuName 菜单名称
	 * @param menuUrl 菜单路径
	 * @param menuId 菜单id
	 * @return int
	 */
	public int forUpdateDeskMenuRecordService(String menuName ,String menuUrl,String menuId)
	{
		Map<String,String> params = new HashMap<>();
		params.put("menuName",menuName);
		params.put("menuUrl",menuUrl);
		params.put("menuId",menuId);
		return backstageMapper.updateForDeskUpMenuRecord(params);
	}

	/**
	 * 业主菜单启用禁用
	 * @param menuStatus 菜单状态
	 * @param menuId 菜单id
	 * @return int
	 */
	public int forUpdateDeskMenuDisableService(String menuStatus ,String menuId)
	{
		if ("启用".equals(menuStatus))
		{
			menuStatus = "禁用";
		} else {
			menuStatus = "启用";
		}
		return backstageMapper.updateForDeskSetAble(menuStatus,menuId);
	}

	/**
	 * 删除菜单
	 * @param menuId 菜单id
	 * @return int
	 */
	public int forDeleteDeskMenuRecordService(String menuId)
	{
		return backstageMapper.deleteForDeskDelMenuRecord(menuId);
	}
}
