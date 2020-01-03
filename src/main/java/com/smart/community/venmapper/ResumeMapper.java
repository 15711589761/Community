package com.smart.community.venmapper;


import com.smart.community.venjavabean.ConditionBean;
import com.smart.community.venjavabean.ResumeBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * @author Ven
 * 简历管理接口
 */
@Mapper
public interface ResumeMapper
{
	/**
	 * 根据查询条件获取简历对象集合数据
	 * @param condition 查询条件对象
	 * @return 简历对象结果集
	 */
	public List<ResumeBean> getResumeData(ConditionBean condition);

	/**
	 * 根据查询条件获取简历对象集的记录数
	 * @param condition 查询条件对象
	 * @return 简历对象结果集的记录数
	 */
	public int countResumeData(ConditionBean condition);

	/**
	 * 获取简历对象的到路径
	 * @param resumeId 简历对象的id
	 * @return 简历对象
	 */
	public ResumeBean getResumeById(@Param("resumeId") int resumeId);

	/**
	 * 删除简历
	 * @param resumeId 简历对象的id
	 * @return 执行结果
	 */
	public int delResume(@Param("resumeId") int resumeId);


}
