package com.smart.community.ljmservice;

import com.smart.community.ljmbean.*;
import com.smart.community.ljmdao.LjmDeskMapper;
import com.smart.community.tool.LjmTool;
import com.sun.tools.javadoc.Start;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.tools.Tool;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LjmDeskService
{

	@Resource
	private LjmDeskMapper deskMapper;

	/**
	 * 业主一级菜单获取
	 * @return 业主一级菜单集合
	 */
	public List<DeskMenuBean> forGetParentDeskMenu()
	{
		return deskMapper.selectForGetDeskMenu();
	}

	/**
	 * 业主登录信息验证
	 * @param loginBean 登录信息类
	 * @return 业主信息实例
	 */
	public List<OwnerBean> deskLoginService( LoginBean loginBean )
	{
		return deskMapper.selectForDeskLogin(loginBean);
	}

	/**
	 * 获取子菜单集合
	 * @param menuParentId 父级菜单id
	 * @return 子菜单集合
	 */
	public List<DeskMenuBean> forGetChildrenDeskMenu( int menuParentId )
	{
		return deskMapper.selectForGetDeskChildrenMenu(menuParentId);
	}

	/**
	 * 获取选中的父级菜单
	 * @param menuParentId 父级菜单id
	 * @return 选择的父级菜单信息
	 */
	public DeskMenuBean forGetSelectedParentMenu( int menuParentId )
	{
		return deskMapper.selectForGetSelectedMenu(menuParentId);
	}

	/**
	 * 投诉和建议的表格
	 * @param tableSearchBean 搜索信息
	 * @return 投诉和建议记录集合
	 */
	public List<SuggestBean> forGetSuggestTable( TableSearchBean tableSearchBean )
	{
		int page = (tableSearchBean.getPage()-1)*tableSearchBean.getLimit();
		tableSearchBean.setPage(page);
		return deskMapper.selectForGetSuggestTable(tableSearchBean);
	}

	/**
	 *  添加投诉或建议
	 * @param suggestName 发起人
	 * @param suggestPhone 联系方式
	 * @param suggestContext 内容
	 * @param roomNum 楼栋号
	 * @param remark 标识
	 * @return int
	 */
	public int insertSuggestRecordService(String suggestName , String suggestPhone , String suggestContext , String remark ,String roomNum )
	{
		SuggestBean suggestBean = new SuggestBean();
		suggestBean.setSuggestDate(LjmTool.getTodayDate());
		suggestBean.setSuggestContent(suggestContext);
		suggestBean.setSuggestStatus("展示");
		suggestBean.setSuggestRemark(remark);
		suggestBean.setSuggestPerson(suggestName);
		suggestBean.setSuggestPhone(suggestPhone);
		suggestBean.setSuggestRoom(roomNum);
		return deskMapper.insertToSuggestRecord(suggestBean);
	}

	/**
	 * 不展示投诉或建议
	 * @param suggestId 投诉或建议的id
	 * @return int
	 */
	public int deleteSuggestRecordService(String suggestId)
	{
		return deskMapper.deleteToSuggestRecord(suggestId);
	}
}
