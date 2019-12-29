package com.smart.community.ljmservice;

<<<<<<< HEAD:src/main/java/com/smart/community/ljmservice/LjmBackstageLoginService.java
import com.smart.community.ljmbean.RoleBean;
=======
>>>>>>> 6f34d25... 李杰明2019-12-18上传，完成登入，访客表查询:src/main/java/com/smart/community/ljmservice/LjmBackstageLoginService.java
import com.smart.community.ljmdao.LjmBackstageLoginMapper;
import com.smart.community.ljmbean.BackstageMenuBean;
import com.smart.community.ljmbean.LoginBean;
import com.smart.community.ljmbean.StaffBean;
import com.smart.community.tool.RandomValidateCodeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ljmservice
 * @author LJM
 */
@Service
public class LjmBackstageLoginService
{
	@Resource
	private LjmBackstageLoginMapper backstageLoginMapper;

	/**
	 * 生成验证码
	 */
	public void getVerify( HttpServletRequest request , HttpServletResponse response )
	{
		try {
			//设置相应类型,告诉浏览器输出的内容为图片
			response.setContentType("image/jpeg");
			//设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			//输出验证码图片方法
			randomValidateCode.getRandcode(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 登入信息匹配
	 */
	public StaffBean backstageLoginService ( LoginBean loginBean )
	{
		return backstageLoginMapper.selectForBackstageLogin(loginBean);
	}

	/**
	 * 后台菜单获取
	 * @param staffId 物业人员ID
	 * @return 后台菜单显示类
	 */
	public Map<String, List<BackstageMenuBean>> backstageMenuTake( int staffId )
	{
		//这个人拥有的所有菜单
		List<BackstageMenuBean> menuBeanList = backstageLoginMapper.selectForGetBackstageMenu(staffId);
		Map<String, List<BackstageMenuBean>> menuMap = new HashMap<>();
		//一级菜单集合
		List<BackstageMenuBean> parentMenus = new ArrayList<>();
		for (BackstageMenuBean backstageMenuBean : menuBeanList)
		{
			if (backstageMenuBean.getMenuPid() == 0)
			{
				parentMenus.add(backstageMenuBean);
				//map的值
				List<BackstageMenuBean> valueList = new ArrayList<>();
				//一级菜单的菜单名称放入map做为key
				menuMap.put(backstageMenuBean.getMenuName(),valueList);
			}
		}
		for (BackstageMenuBean menu : parentMenus)
		{
			for (BackstageMenuBean menuBean : menuBeanList)
			{
				//判断二级菜单所属
				if (menuBean.getMenuPid() == menu.getMenuId())
				{
					//将二级菜单添加到一级菜单下
					menuMap.get(menu.getMenuName()).add(menuBean);
				}
			}
		}
		return menuMap;
	}

	/**
	 * 后去人员角
	 * @param staffId 职员id
	 * @return 角色集合
	 */
	public RoleBean getPersonRole(int staffId)
	{
		return backstageLoginMapper.selectForGetPersonRoles(staffId);
	}
}
