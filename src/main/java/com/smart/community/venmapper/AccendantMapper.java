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


	public int countAccendantData(ConditionBean condition);

	public int addAccendant(AjaxInfoBean ajaxInfoBean);

	public int delAccendant(@Param("accendantId") String AccendantId);


	public int updateAccendantById(AjaxInfoBean ajaxInfoBean);
}
