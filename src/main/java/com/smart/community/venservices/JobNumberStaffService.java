package com.smart.community.venservices;

import com.smart.community.venjavabean.*;
import com.smart.community.venmapper.JobNumberMapper;
import com.smart.community.venmapper.StaffMapper;
import com.smart.community.ventool.CodeTool;
import com.smart.community.ventool.ToolParameter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JobNumberStaffService

{
	@Resource
	private JobNumberMapper jobNumberMapper;

	public TableBean queryJobNumber(ConditionBean condition){

		TableBean tableBean = new TableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");

		tableBean.setData(jobNumberMapper.getJobNumberData(condition));
		tableBean.setCount(jobNumberMapper.countJobNumberData(condition));
		return tableBean;
	}

	public int delJobNumberById(String jobNumberId){

		return jobNumberMapper.delJobNumberById(jobNumberId);

	}

	public List<StaffBean> getNoJobNumberStaffList()
	{
		return jobNumberMapper.getNoJobNumberStaffList();
	}


	public String jobNumberSetting(AjaxInfoBean ajaxInfoBean){

		int n=jobNumberMapper.setStaffJobNumberByStaffId(ajaxInfoBean);
		if (n>0){
			int m=jobNumberMapper.updateJobNumberByJobNumber(ajaxInfoBean);
			if (m>0){
				return "succeed";
			}
		}
		return "fail";

	}


	public String getLastJobNumber(){

		String lastJobNumber=null;

		int m=jobNumberMapper.countJobNumber();

		if (m==0){
			lastJobNumber="0001";
		}
		else {
			lastJobNumber=jobNumberMapper.getTheLastJobNumber();
			lastJobNumber=CodeTool.toAdd0(Integer.parseInt(lastJobNumber)+1);
			System.out.println("获取到最新的一条工号为："+lastJobNumber);
		}

		return lastJobNumber;
	}

	public int addJobNumber(List<String> list){

		return jobNumberMapper.addJobNumber(list);
	}


}


