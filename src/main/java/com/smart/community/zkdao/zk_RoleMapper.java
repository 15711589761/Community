package com.smart.community.zkdao;

import com.smart.community.zkbean.ParameterBean;
import com.smart.community.zkbean.Zk_MenuBean;
import com.smart.community.zkbean.Zk_RoleBean;
import com.smart.community.zkbean.Zk_TreeBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface zk_RoleMapper
{
	List<Zk_RoleBean> findRole(ParameterBean parameterBean);

	int countRole(ParameterBean parameterBean);

	List<Zk_TreeBean> findAllMenu();

	List<Zk_TreeBean> findRoleMenu(String roleid);

	int delRoleMenu(String roleid);

	int setRoleName(ParameterBean parameterBean);

	int addRoleMenu(@Param("roleid") String roleid, @Param("list") List<Integer> list);

	List<Zk_RoleBean> byRoleNameFind(String roleName);

	int addRole(String roleName);
}
