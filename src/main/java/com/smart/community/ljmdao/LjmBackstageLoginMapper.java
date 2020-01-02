package com.smart.community.ljmdao;

import com.smart.community.ljmbean.BackstageMenuBean;
import com.smart.community.ljmbean.LoginBean;
import com.smart.community.ljmbean.RoleBean;
import com.smart.community.ljmbean.StaffBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mapper接口
 * @author LJM
 */
@Mapper
public interface LjmBackstageLoginMapper
{
	/**
	 * 后台登入验证
	 * @param loginBean 登入信息类
	 * @return 物业人员信息类
	 */
	public StaffBean selectForBackstageLogin( LoginBean loginBean );

	/**
	 * 管理端、物业端菜单获取
	 * @param staffId 物业人员ID
	 * @return 后台菜单集合
	 */
	public List<BackstageMenuBean> selectForGetBackstageMenu( @Param ("staffId") int staffId );

	/**
	 * 获取人员角色表
	 * @param staffId 职员id
	 * @return 角色集合
	 */
	public RoleBean selectForGetPersonRoles (int staffId);

	/**
	 * 查询原密码是否正确
	 * @param jobNum 工号
	 * @param password 密码
	 * @return int
	 */
	public int selectForExistStaff(@Param("jobNum")String jobNum , @Param("password")String password);

	/**
	 * 修改密码
	 * @param password 密码
	 * @param staffId 员工id
	 * @return int
	 */
	public int updateForUpPassword(@Param("password")String password,@Param("staffId")int staffId);
}
