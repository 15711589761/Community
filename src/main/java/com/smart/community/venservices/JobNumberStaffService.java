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

	/**
	 * 查询工号数据
	 * @param condition 查询条件对象
	 * @return 符合查询条件的工号对象结果集
	 */
	public TableBean queryJobNumber(ConditionBean condition){

		TableBean tableBean = new TableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");

		tableBean.setData(jobNumberMapper.getJobNumberData(condition));
		tableBean.setCount(jobNumberMapper.countJobNumberData(condition));
		return tableBean;
	}

	/**
	 * 删除工号记录
	 * @param jobNumberId 目标工号的id
	 * @return 删除工号的执行结果
	 */
	public int delJobNumberById(String jobNumberId){

		return jobNumberMapper.delJobNumberById(jobNumberId);

	}
	/**
	 * 获取没有分配工号的职员对象集合
	 * @return 没有分配的职员对象集合
	 */
	public List<StaffBean> getNoJobNumberStaffList()
	{
		return jobNumberMapper.getNoJobNumberStaffList();
	}

	/**、
	 * 分配工号
	 * @param ajaxInfoBean ajax交互数据对象
	 * @return 执行结果
	 */
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

	/**
	 * 获取最新的起始工号
	 * @return 最新记录的工号
	 */
	public String getLastJobNumber(){

		String lastJobNumber=null;

		int m=jobNumberMapper.countJobNumber();

		if (m==0){
			lastJobNumber="0001";
		}
		else {
			lastJobNumber=jobNumberMapper.getTheLastJobNumber();
			lastJobNumber=CodeTool.toAdd0(Integer.parseInt(lastJobNumber)+1);
		}

		return lastJobNumber;
	}

	/**
	 * 批量增加工号
	 * @param list 需要增加的工号集合
	 * @return 执行结果
	 */
	public int addJobNumber(List<String> list){

		return jobNumberMapper.addJobNumber(list);
	}


}


