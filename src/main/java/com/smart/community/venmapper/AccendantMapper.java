package com.smart.community.venmapper;

import com.smart.community.venjavabean.AccendantBean;
import com.smart.community.venjavabean.AjaxInfoBean;
import com.smart.community.venjavabean.ConditionBean;
import com.smart.community.venjavabean.JobNumberBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ven
 * 维修人员管理接口
 */

@Mapper
public interface AccendantMapper
{
	/**
	 * 根据条件查询维修人员数据
	 * @param condition 查询条件
	 * @return 维修人员数据
	 */
	public List<AccendantBean> getAccendantData(ConditionBean condition);

	/**
	 * 根据条件查询维修人员的记录数
	 * @param condition 查询条件
	 * @return 符合条件的维修人员记录数
	 */

	public int countAccendantData(ConditionBean condition);

	/**
	 * 新增维修人员
	 * @param ajaxInfoBean 新增的维修人员信息
	 * @return 返回执行结果
	 */
	public int addAccendant(AjaxInfoBean ajaxInfoBean);

	/**
	 * 删除维修人员数据
	 * @param AccendantId 目标维修人员id
	 * @return 返回执行结果
	 */
	public int delAccendant(@Param("accendantId") String AccendantId);

	/**
	 * 更新维修人员数据
	 * @param ajaxInfoBean 修改后填入的数据
	 * @return 返回执行结果
	 */

	public int updateAccendantById(AjaxInfoBean ajaxInfoBean);
}
