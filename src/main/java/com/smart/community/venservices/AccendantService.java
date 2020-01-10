package com.smart.community.venservices;

import com.smart.community.venjavabean.*;
import com.smart.community.venmapper.AccendantMapper;
import com.smart.community.venmapper.JobNumberMapper;
import com.smart.community.ventool.CodeTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccendantService

{
	@Resource
	private AccendantMapper accendantMapper;

	/**
	 * 维修人员查询
	 * @param condition 查询条件对象
	 * @return layui表格需要的数据对象
	 */
	public TableBean queryAccendant(ConditionBean condition){

		TableBean tableBean = new TableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");
		tableBean.setData(accendantMapper.getAccendantData(condition));
		tableBean.setCount(accendantMapper.countAccendantData(condition));
		return tableBean;
	}

	/**
	 *
	 * 新增维修人员
	 * @param ajaxInfoBean ajax数据对像
	 * @return 执行结果
	 */

	public int addAccendant(AjaxInfoBean ajaxInfoBean){
		return accendantMapper.addAccendant(ajaxInfoBean);
	}

	/**
	 * 删除维修人员
	 * @param accendantId 维修人员目标id
	 * @return 执行结果
	 */
	public int delAccendant(String accendantId){
		return accendantMapper.delAccendant(accendantId);
	}

	/**
	 * 更新维修人员对象数据
	 * @param ajaxInfoBean ajax数据对象
	 * @return 执行结果
	 */

	public int updateAccendantById(AjaxInfoBean ajaxInfoBean){
		return accendantMapper.updateAccendantById(ajaxInfoBean);
	}

}


