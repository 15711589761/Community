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

	public TableBean queryResumeDate(ConditionBean condition)
	{
		TableBean tableBean = new TableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");
		tableBean.setData(resumeMapper.getResumeData(condition));
		tableBean.setCount(resumeMapper.countResumeData(condition));
		return tableBean;
	}


	public ResumeBean getResumeById(int resumeId){

		return resumeMapper.getResumeById(resumeId);
	}

	public int delResume(int resumeId){

		return resumeMapper.delResume(resumeId);
	}

}


