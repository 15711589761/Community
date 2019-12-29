package com.smart.community.ljmcontroller;

import com.smart.community.ljmbean.BackstageMenuBean;
import com.smart.community.ljmbean.DeskMenuBean;
import com.smart.community.ljmbean.LayuiTableBean;
import com.smart.community.ljmservice.LjmBackstageMenuService;
import com.smart.community.ljmservice.LjmDeskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LJM。
 */
@Controller
public class LjmBackstageMenuController
{
	@Resource
	private LjmBackstageMenuService backstageService;
	@Resource
	private LjmDeskService deskService;

	/**
	 * 后台菜单管理页面跳转
	 * @return 界面
	 */
	@RequestMapping("/toMenuBackstageManageTable.view")
	public ModelAndView toMenuBackstageManageTable()
	{
		return new ModelAndView("ljm_backstage_menu_manage");
	}

	/**
	 * 后台菜单管理表格展示及搜索
	 * @param menuPid 父级id
	 * @param menuName 菜单名称
	 * @param page page
	 * @param limit limit
	 * @return layui接口
	 */
	@RequestMapping("/forShowBackstageMenuManageTable.action")
	@ResponseBody
	public LayuiTableBean forShowBackstageMenuManageTable(String menuPid,String menuName,String page,String limit)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		List<BackstageMenuBean> dataList = backstageService.forGetShowTableBackstageMenus(menuPid,menuName,page,limit);
		int count = backstageService.forGetShowTableBackstageMenuCount(menuPid,menuName);
		if (null!=dataList&&dataList.size()>0)
		{
			layuiTableBean.setCode(0);
			layuiTableBean.setCount(count);
			layuiTableBean.setData(dataList);
		} else {
			layuiTableBean.setCode(1);
			layuiTableBean.setMsg("没有找到相关记录");
		}
		return layuiTableBean;
	}

	/**
	 * 跳转到添加二级菜单的dialog
	 * @return 界面
	 */
	@RequestMapping("/toDialogAddBackstageChildrenMenu.view")
	public ModelAndView toDialogAddBackstageChildrenMenu()
	{
		ModelAndView modelAndView = new ModelAndView("ljm_add_child_menu");
		List<BackstageMenuBean> parentMenus = backstageService.forDialogParentMenu();
		modelAndView.addObject("parentMenu",parentMenus);
		return modelAndView;
	}

	/**
	 * 添加菜单
	 * @param menuName 菜单名称
	 * @param menuPid 菜单父级id
	 * @param menuUrl 菜单路径
	 * @param menuRemark 菜单标识 0后台 1物业
	 * @return 信息
	 */
	@RequestMapping("/forAddBackstageMenuRecord.action")
	@ResponseBody
	public LayuiTableBean forAddBackstageMenuRecord(String menuName ,String menuPid,String menuUrl,String menuRemark)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		int result = backstageService.forAddMenuRecordService(menuName,menuPid,menuUrl,menuRemark);
		if (result>0)
		{
			layuiTableBean.setMsg("添加成功");
		} else {
			layuiTableBean.setMsg("系统繁忙，请稍后再试");
		}
		return layuiTableBean;
	}

	/**
	 * 跳转到修改菜单dialog
	 * @return 界面
	 */
	@RequestMapping("/toDialogUpdateBackstageMenu.view")
	public ModelAndView toDialogUpdateBackstageMenu()
	{
		ModelAndView modelAndView = new ModelAndView("ljm_update_menu");
		List<BackstageMenuBean> parentMenus = backstageService.forDialogParentMenu();
		modelAndView.addObject("parentMenu",parentMenus);
		return modelAndView;
	}

	/**
	 * 修改菜单
	 * @param menuName 菜单名称
	 * @param menuPid 菜单父级id
	 * @param menuUrl 菜单路径
	 * @param menuRemark 菜单标识 0后台 1物业
	 * @param menuId 菜单id
	 * @return 信息
	 */
	@RequestMapping("/forUpdateBackstageMenuRecord.action")
	@ResponseBody
	public LayuiTableBean forUpdateBackstageMenuRecord(String menuName ,String menuPid,String menuUrl,String menuRemark , String menuId)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		System.out.println(menuPid);
		if ("0".equals(menuPid))
		{
			menuUrl = "0";
		}

		System.out.println(menuUrl);
		int result = backstageService.forUpdateMenuRecordService(menuName,menuPid,menuUrl,menuRemark,menuId);
		if (result>0)
		{
			layuiTableBean.setMsg("修改成功");
		} else {
			layuiTableBean.setMsg("系统繁忙，请刷新后重试");
		}
		return layuiTableBean;
	}

	/**
	 * 删除菜单
	 * @param menuId 菜单id
	 * @return 信息
	 */
	@RequestMapping("/forDeleteBackstageMenuRecord.action")
	@ResponseBody
	public LayuiTableBean forDeleteBackstageMenuRecord(String menuId)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		int result = backstageService.forDeleteMenuRecordService(menuId);
		if (result>0)
		{
			layuiTableBean.setMsg("删除成功");
		} else {
			layuiTableBean.setMsg("系统繁忙，请刷新后重试");
		}
		return layuiTableBean;
	}

	/**
	 * 启用禁用菜单
	 * @param menuStatus 菜单状态
	 * @param menuId 菜单id
	 * @return 信息
	 */
	@RequestMapping("/enableAndDisableSet.action")
	@ResponseBody
	public LayuiTableBean enableAndDisableSet(String menuStatus,String menuId)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		int result = backstageService.forUpdateMenuDisableService(menuStatus,menuId);
		String msg;
		if (Integer.parseInt(menuStatus)==0)
		{
			msg = "启用";
		}else {
			msg = "禁用";
		}
		if (result>0)
		{
			layuiTableBean.setMsg(msg+"成功");
		} else {
			layuiTableBean.setMsg("系统繁忙，请刷新后重试");
		}
		return layuiTableBean;
	}

	/**
	 * 前端菜单管理页面跳转
	 * @return 界面
	 */
	@RequestMapping("/toMenuDeskManageTable.view")
	public ModelAndView toMenuDeskManageTable()
	{
		return new ModelAndView("ljm_desk_menu_manage");
	}

	/**
	 * 前端菜单管理表格展示及搜索
	 * @param menuPid 父级id
	 * @param menuName 菜单名称
	 * @param page page
	 * @param limit limit
	 * @return layui接口
	 */
	@RequestMapping("/forShowDeskMenuManageTable.action")
	@ResponseBody
	public LayuiTableBean forShowDeskMenuManageTable(String menuPid,String menuName,String page,String limit)
	{
		LayuiTableBean tableBean = new LayuiTableBean();
		List<DeskMenuBean> deskMenuBeans = backstageService.forGetShowTableDeskMenus(menuPid,menuName,page,limit);
		int count = backstageService.forGetShowTableDeskMenuCount(menuPid,menuName);
		if (null!=deskMenuBeans && deskMenuBeans.size()>0)
		{
			tableBean.setCount(count);
			tableBean.setCode(0);
			tableBean.setData(deskMenuBeans);
		} else {
			tableBean.setCode(1);
			tableBean.setMsg("没有找到相关记录");
		}
		return tableBean;
	}

	/**
	 * 跳转到前端添加菜单的dialog
	 * @return 界面
	 */
	@RequestMapping("/toDialogAddDeskChildrenMenu.view")
	public ModelAndView toDialogAddDeskChildrenMenu()
	{
		ModelAndView modelAndView = new ModelAndView("ljm_desk_add_menu");
		List<DeskMenuBean> parentMenus = deskService.forGetParentDeskMenu();
		modelAndView.addObject("parentMenu",parentMenus);
		return modelAndView;
	}

	/**
	 * 添加前端菜单
	 * @param menuName 菜单名称
	 * @param menuPid 菜单父级id
	 * @param menuUrl 菜单路径
	 * @return 信息
	 */
	@RequestMapping("/forAddDeskMenuRecord.action")
	@ResponseBody
	public LayuiTableBean forAddDeskMenuRecord(String menuName ,String menuPid,String menuUrl)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		int result = backstageService.forAddDeskMenuRecordService(menuName,menuPid,menuUrl);
		if (result>0)
		{
			layuiTableBean.setMsg("添加成功");
		} else {
			layuiTableBean.setMsg("系统繁忙，请稍后再试");
		}
		return layuiTableBean;
	}

	/**
	 * 修改前端菜单路径
	 * @param menuName 菜单名称
	 * @param menuUrl 菜单路径
	 * @param menuId 菜单id
	 * @return 信息
	 */
	@RequestMapping("/forUpdateDeskMenuRecord.action")
	@ResponseBody
	public LayuiTableBean forUpdateDeskMenuRecord(String menuName,String menuUrl, String menuId)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		int result = backstageService.forUpdateDeskMenuRecordService(menuName,menuUrl,menuId);
		if (result>0)
		{
			layuiTableBean.setMsg("修改成功");
		} else {
			layuiTableBean.setMsg("系统繁忙，请刷新后重试");
		}
		return layuiTableBean;
	}

	/**
	 * 启用禁用业主菜单
	 * @param menuStatus 菜单状态
	 * @param menuId 菜单id
	 * @return 信息
	 */
	@RequestMapping("/deskEnableAndDisableSet.action")
	@ResponseBody
	public LayuiTableBean deskEnableAndDisableSet(String menuStatus,String menuId)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		int result = backstageService.forUpdateDeskMenuDisableService(menuStatus,menuId);
		if (result>0)
		{
			layuiTableBean.setMsg(menuStatus+"成功");
		} else {
			layuiTableBean.setMsg("系统繁忙，请刷新后重试");
		}
		return layuiTableBean;
	}

	/**
	 * 删除业主菜单
	 * @param menuId 菜单id
	 * @return 信息
	 */
	@RequestMapping("/forDeleteDeskMenuRecord.action")
	@ResponseBody
	public LayuiTableBean forDeleteDeskMenuRecord(String menuId)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		int result = backstageService.forDeleteDeskMenuRecordService(menuId);
		if (result>0)
		{
			layuiTableBean.setMsg("删除成功");
		} else {
			layuiTableBean.setMsg("系统繁忙，请刷新后重试");
		}
		return layuiTableBean;
	}
}
