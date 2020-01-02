package com.smart.community.venservices;

import com.smart.community.venjavabean.*;
import com.smart.community.venmapper.StaffMapper;
import com.smart.community.ventool.CodeTool;
import com.smart.community.ventool.ToolParameter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StaffService

{
	@Resource
	private StaffMapper mapper;

	public TableBean queryStaff(ConditionBean condition){

		TableBean tableBean = new TableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");
		tableBean.setData(mapper.getStaffData(condition));
		tableBean.setCount(mapper.countStaffData(condition));
		return tableBean;
	}


	public TableBean querySeparatingEmploy(ConditionBean condition){

		TableBean tableBean = new TableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");
		tableBean.setData(mapper.getSeparatingEmploy(condition));
		tableBean.setCount(mapper.countSeparatingEmployData(condition));
		return tableBean;
	}

	public int staffdimission(AjaxInfoBean ajaxInfoBean){

		mapper.delRoleStaff(ajaxInfoBean.getTargetId());
		mapper.updateJobNumber(ajaxInfoBean.getJobNumber());
		return mapper.staffdimission(ajaxInfoBean);

	}


	public int delSeparatingEmploy(String staffId){
		return mapper.delSeparatingEmploy(staffId);
	}



	public List<RoleBean> getRoleList(){

		return mapper.getRoleList();
	}

	public String addNewStaff(AjaxInfoBean ajaxInfoBean){


		String msg=null;

		String defaultPass= CodeTool.toMD5(ToolParameter.defaultPass);

		ajaxInfoBean.setStaffPass(defaultPass);

		mapper.addStaff(ajaxInfoBean);

		int staffId =ajaxInfoBean.getStaffId();

		System.out.println(staffId);

		if (staffId>0){
			mapper.roleAllocation(ajaxInfoBean);
			msg="新增成功";
		}

		return msg;
	}


	public String updateStaffInfo(AjaxInfoBean ajaxInfoBean){

		String str=null;
		int n=mapper.updateStaffInFoById(ajaxInfoBean);
		if (n>0){
			str="修改成功";
			return str;
		}
		return str;
	}

	public List<PostCountBean> postCountBeanList(){
		return mapper.postCountBeanList();
	}
}


