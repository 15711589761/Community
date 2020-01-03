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
	public List<ResumeBean> getResumeData(ConditionBean condition);

	public int countResumeData(ConditionBean condition);

	public ResumeBean getResumeById(@Param("resumeId") int resumeId);

	public int delResume(@Param("resumeId") int resumeId);


}
