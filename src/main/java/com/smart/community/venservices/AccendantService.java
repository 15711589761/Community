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

	public TableBean queryAccendant(ConditionBean condition){

		TableBean tableBean = new TableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");
		tableBean.setData(accendantMapper.getAccendantData(condition));
		tableBean.setCount(accendantMapper.countAccendantData(condition));
		return tableBean;
	}


	public int addAccendant(AjaxInfoBean ajaxInfoBean){
		return accendantMapper.addAccendant(ajaxInfoBean);
	}

	public int delAccendant(String accendantId){
		return accendantMapper.delAccendant(accendantId);
	}


	public int updateAccendantById(AjaxInfoBean ajaxInfoBean){
		return accendantMapper.updateAccendantById(ajaxInfoBean);
	}

}


