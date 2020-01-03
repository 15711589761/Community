package com.smart.community.zkcontroller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smart.community.zkbean.*;
import com.smart.community.zkservice.zk_RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class Zk_Role_Controller
{
	@Resource
	private zk_RoleService zk_roleService;

	@RequestMapping("/roleJsp")
	public String roleJsp()
	{
		return "zk_back_role";
	}

	@RequestMapping("/findRole")
	@ResponseBody
	public TableBean findRole(String roleName, String page, String limit)
	{
		ParameterBean parameterBean = new ParameterBean();
		parameterBean.setName(roleName);
		parameterBean.setPage(((Integer.valueOf(page) - 1) * Integer.valueOf(limit)));
		parameterBean.setLimit(Integer.valueOf(limit));
		TableBean tableBean = new TableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");
		tableBean.setData(zk_roleService.findRole(parameterBean));
		tableBean.setCount(zk_roleService.countRole(parameterBean));
		return tableBean;
	}

	//获取角色tree
	public List<Zk_TreeBean> getMenu(String roleid)

	{
		List<Zk_TreeBean> menuBeans = null;
		if (roleid != null)
		{
			//角色
			menuBeans = zk_roleService.findRoleMenu(roleid);
		} else
		{
			//超级管理员
			menuBeans = zk_roleService.findAllMenu();
		}
		List<Zk_TreeBean> treeBeans = new ArrayList<>();
		//一级菜单
		for (Zk_TreeBean menuBean : menuBeans)
		{
			if (Integer.valueOf(menuBean.getParent()) == 0)
			{
				treeBeans.add(menuBean);
			}
		}
		//二级菜单
		for (Zk_TreeBean treeBean : treeBeans)
		{
			List<Zk_TreeBean> childrens = new ArrayList<>();
			treeBean.setChildren(childrens);
			for (Zk_TreeBean menuBean : menuBeans)
			{
				if (Integer.valueOf(menuBean.getParent()) == treeBean.getId())
				{
					treeBean.getChildren().add(menuBean);
				}
			}
		}
		return treeBeans;
	}

	//角色tree回显
	@RequestMapping("/findAllMenu")
	@ResponseBody
	public List<Zk_TreeBean> getTree(String roleId)
	{
		List<Zk_TreeBean> allTree = getMenu(null);
		List<Zk_TreeBean> roleTree = getMenu(roleId);
		for (int i = 0; i < allTree.size(); i++)
		{
			if (allTree.get(i).getTitle().equals("权限配置"))
			{
				allTree.get(i).setDisabled(true);
			}
			Zk_TreeBean treeBean = allTree.get(i);
			for (int j = 0; j < roleTree.size(); j++)
			{
				Zk_TreeBean treeBean1 = roleTree.get(j);

				for (int k = 0; k < treeBean.getChildren().size(); k++)
				{
					if (treeBean.getChildren().get(k).getTitle().equals("角色权限管理"))
					{
						treeBean.getChildren().get(k).setDisabled(true);
					}
					for (int l = 0; l < treeBean1.getChildren().size(); l++)
					{
						if (treeBean.getChildren().get(k).getId() == treeBean1.getChildren().get(l).getId())
						{
							allTree.get(i).getChildren().get(k).setChecked(true);
						}
					}
				}
			}
		}
		return allTree;
	}

	//修改权限
	@RequestMapping("/addRoleMenu")
	@ResponseBody
	public int addMenu(String roleid, String field)
	{
		System.out.println("roleid=" + roleid);
		int res = 0;
		Gson g = new Gson();
		List<Integer> list = new ArrayList<>();
		HashMap<String, String> map = g.fromJson(field, HashMap.class);
		for (String key : map.keySet())
		{
			list.add(Integer.valueOf(map.get(key)));
		}
		res = zk_roleService.delRoleMenu(roleid);
		if (field != null && field.length() > 0)
		{
			res = zk_roleService.addRoleMenu(roleid, list);
		}
		return res;
	}

	//修改角色名称
	@RequestMapping("/setRoleName")
	@ResponseBody
	public int setRoleName(ParameterBean parameterBean){
		if ((zk_roleService.byRoleNameFind(parameterBean.getName())).size()==0){
			return zk_roleService.setRoleName(parameterBean);
		}
		return 2;
	}

	//添加角色
	@RequestMapping("/addRole")
	@ResponseBody
	public int addRole(String roleName){
		if ((zk_roleService.byRoleNameFind(roleName)).size()==0){
			return zk_roleService.addRole(roleName);
		}
		return 2;
	}

}
