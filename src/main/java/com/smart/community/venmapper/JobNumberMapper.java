package com.smart.community.venmapper;

import com.smart.community.venjavabean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ven
 * 工号管理接口
 */

@Mapper
public interface JobNumberMapper
{
	/**
	 * 根据条件获取工号数据
	 * @param condition 查询条件对象
	 * @return 返回工号对象结果集
	 */
	public List<JobNumberBean> getJobNumberData(ConditionBean condition);

	/**
	 * 根据条件查询记录数
	 * @param condition 查询条件对象
	 * @return 返回符合条件的数据记录数
	 */
	public int countJobNumberData(ConditionBean condition);

	/**
	 * 查询角色对象集合
	 * @return 角色对象集合
	 */
	public List<StaffBean>getNoJobNumberStaffList();

	/**
	 * 删除工号
	 * @param jobNumberId 目标工号的id
	 * @return 返回执行结果
	 */
	public int delJobNumberById(@Param("jobNumberId") String jobNumberId);

	/**
	 * 分配工号
	 * @param ajaxInfoBean ajax参数数据
	 * @return 执行结果
	 */
	public int setStaffJobNumberByStaffId(AjaxInfoBean ajaxInfoBean);

	/**
	 * 更新工号信息
	 * @param ajaxInfoBean ajax参数数据
	 * @return 返回执行结果
	 */
	public int updateJobNumberByJobNumber(AjaxInfoBean ajaxInfoBean);

	/**
	 * 查询当前是否有工号数据
	 * @return 当前工号的总数
	 */
	public int countJobNumber();

	/**
	 * 获取最新的一条工号
	 * @return 最新的工号
	 */
	public String getTheLastJobNumber();

	/**
	 * 批量增加工号
	 * @param list 工号对象集合
	 * @return 执行结果
	 */
	public int addJobNumber(List<String> list);
}
