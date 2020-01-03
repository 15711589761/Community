package com.smart.community.zkservice;

import com.smart.community.zkbean.ParameterBean;
import com.smart.community.zkbean.Zk_MenuBean;
import com.smart.community.zkbean.Zk_RoleBean;
import com.smart.community.zkbean.Zk_TreeBean;
import com.smart.community.zkdao.zk_RoleMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class zk_RoleService
{
	@Resource
	private zk_RoleMapper zk_roleMapper;

	public List<Zk_RoleBean> findRole(ParameterBean parameterBean)
	{
		return zk_roleMapper.findRole(parameterBean);
	}

	public int countRole(ParameterBean parameterBean)
	{
		return zk_roleMapper.countRole(parameterBean);
	}

	public List<Zk_TreeBean> findAllMenu()
	{
		return zk_roleMapper.findAllMenu();
	}

	public List<Zk_TreeBean> findRoleMenu(String roleid)
	{
		return zk_roleMapper.findRoleMenu(roleid);
	}

	public int delRoleMenu(String roleid){
		return zk_roleMapper.delRoleMenu(roleid);
	}

	public int setRoleName(ParameterBean parameterBean){
		return zk_roleMapper.setRoleName(parameterBean);
	}

	public int addRoleMenu(String roleid,List<Integer> list){
		return zk_roleMapper.addRoleMenu(roleid,list);
	}

	public List<Zk_RoleBean> byRoleNameFind(String roleName){
		return zk_roleMapper.byRoleNameFind(roleName);
	}

	public int addRole(String roleName){
		return zk_roleMapper.addRole(roleName);
	}
}
