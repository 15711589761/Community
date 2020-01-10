package com.smart.community.venservices;

import com.smart.community.venjavabean.*;
import com.smart.community.venmapper.ResumeMapper;
import com.smart.community.ventool.CodeTool;
import com.smart.community.ventool.ToolParameter;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.net.URL;
import java.util.List;

@Service
public class ResumeService

{
	@Resource
	private ResumeMapper resumeMapper;

	/**
	 * 查询简历
	 * @param condition 查询条件对象
	 * @return layui表格数据对象
	 */
	public TableBean queryResumeDate(ConditionBean condition)
	{
		TableBean tableBean = new TableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");
		tableBean.setData(resumeMapper.getResumeData(condition));
		tableBean.setCount(resumeMapper.countResumeData(condition));
		return tableBean;
	}

	/**
	 * 获取简历目标对象
	 * @param resumeId 简历id
	 * @return 执行结果
	 */
	public ResumeBean getResumeById(int resumeId){

		return resumeMapper.getResumeById(resumeId);
	}

	/**
	 * 删除目标简历记录
	 * @param resumeId 简历id
	 * @return 执行结果
	 */

	public int delResume(int resumeId){

		return resumeMapper.delResume(resumeId);
	}

}


