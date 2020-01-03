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

	public List<StaffBean> getStaffData(ConditionBean condition);

	public int countStaffData(ConditionBean condition);

	public List<StaffBean> getSeparatingEmploy(ConditionBean condition);

	public int countSeparatingEmployData(ConditionBean condition);

	public int delSeparatingEmploy(@Param("staffId") String staffId);

	public int delRoleStaff(@Param("staffId") String staffId);

	public int staffdimission(AjaxInfoBean ajaxInfoBean);

	public int updateJobNumber(@Param("jobNumber")String jobNumber);

	public List<RoleBean> getRoleList();

	public int addStaff(AjaxInfoBean ajaxInfoBean);

	public int roleAllocation(AjaxInfoBean ajaxInfoBean);

	public int updateStaffInFoById(AjaxInfoBean ajaxInfoBean);

	public List<PostCountBean> postCountBeanList();

}
