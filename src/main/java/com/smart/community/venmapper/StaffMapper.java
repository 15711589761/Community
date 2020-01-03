package com.smart.community.venmapper;

import com.smart.community.venjavabean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author Ven
 * 职员管理接口
 */

@Mapper
public interface StaffMapper
{
	/**
	 * 获取符合查询条件的工作人员结果集
	 * @param condition 查询条件对象
	 * @return 工作人员对象结果集
	 */
	public List<StaffBean> getStaffData(ConditionBean condition);

	/**
	 * 获取符合查询条件的结果集的记录数
	 * @param condition 查询条件
	 * @return 结果集的记录数
	 */
	public int countStaffData(ConditionBean condition);

	/**
	 * 获取符合查询条件的离职人员结果集
	 * @param condition 查询条件
	 * @return 离职人员结果集
	 */
	public List<StaffBean> getSeparatingEmploy(ConditionBean condition);

	/**
	 * 查询离职人员的结果集记录数
	 * @param condition 查询条件
	 * @return 离职人员结果集记录数
	 */
	public int countSeparatingEmployData(ConditionBean condition);

	/**
	 * 删除离职人员的记录
	 * @param staffId 职员id
	 * @return 执行结果
	 */
	public int delSeparatingEmploy(@Param("staffId") String staffId);

	/**
	 * 删除角色和工作人员关系表记录
	 * @param staffId 职员id
	 * @return 执行结果
	 */
	public int delRoleStaff(@Param("staffId") String staffId);

	/**
	 * 离职操作，更新职员表对应的就数据
	 * @param ajaxInfoBean ajax参数对象
	 * @return 结果集
	 */
	public int staffdimission(AjaxInfoBean ajaxInfoBean);

	/**
	 * 更新离职人员的工号信息
	 * @param jobNumber 离职人员在职时用的工号
	 * @return 执行结果
	 */
	public int updateJobNumber(@Param("jobNumber")String jobNumber);

	/**
	 * 获取角色对象集合
	 * @return 角色对象集合
	 */
	public List<RoleBean> getRoleList();

	/**
	 * 新增职员
	 * @param ajaxInfoBean ajax参数对象
	 * @return 执行结果
	 */
	public int addStaff(AjaxInfoBean ajaxInfoBean);

	/**
	 * 给职员分配角色，角色-职员表增加记录
	 * @param ajaxInfoBean ajax参数对象
	 * @return 执行结果
	 */
	public int roleAllocation(AjaxInfoBean ajaxInfoBean);

	/**
	 * 更新职员信息
	 * @param ajaxInfoBean ajax参数对象
	 * @return 执行结果
	 */
	public int updateStaffInFoById(AjaxInfoBean ajaxInfoBean);

	/**
	 * 职位人数统计
	 * @return 职位人数对象集合
	 */
	public List<PostCountBean> postCountBeanList();

}
