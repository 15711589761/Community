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

	public List<JobNumberBean> getJobNumberData(ConditionBean condition);

	public int countJobNumberData(ConditionBean condition);

	public List<StaffBean>getNoJobNumberStaffList();

	public int delJobNumberById(@Param("jobNumberId") String jobNumberId);

	public int setStaffJobNumberByStaffId(AjaxInfoBean ajaxInfoBean);

	public int updateJobNumberByJobNumber(AjaxInfoBean ajaxInfoBean);

	public int countJobNumber();

	public String getTheLastJobNumber();

	public int addJobNumber(List<String> list);
}
