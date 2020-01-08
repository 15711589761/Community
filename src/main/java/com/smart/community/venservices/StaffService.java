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

	/**
	 * 查询在职人员数据
	 * @param condition 查询条件对象
	 * @return layui表格数据对象
	 */
	public TableBean queryStaff(ConditionBean condition){

		TableBean tableBean = new TableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");
		tableBean.setData(mapper.getStaffData(condition));
		tableBean.setCount(mapper.countStaffData(condition));
		return tableBean;
	}

	/**
	 * 查询离职人员数据
	 * @param condition 查询条件
	 * @return layui表格数据对象
	 */

	public TableBean querySeparatingEmploy(ConditionBean condition){

		TableBean tableBean = new TableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");
		tableBean.setData(mapper.getSeparatingEmploy(condition));
		tableBean.setCount(mapper.countSeparatingEmployData(condition));
		return tableBean;
	}

	/**
	 * 职员离职处理
	 * @param ajaxInfoBean ajax数据对象
	 * @return 执行结果
	 */
	public int staffDimission(AjaxInfoBean ajaxInfoBean){

		mapper.delRoleStaff(ajaxInfoBean.getTargetId());
		mapper.updateJobNumber(ajaxInfoBean.getJobNumber());
		return mapper.staffDimission(ajaxInfoBean);

	}

	/**
	 * 删除离职人员数据
	 * @param staffId 目标职员id
	 * @return 执行结果
	 */

	public int delSeparatingEmploy(String staffId){
		return mapper.delSeparatingEmploy(staffId);
	}

	/**
	 * 获取岗位对象集合
	 * @return 岗位对象集合
	 */
	public List<RoleBean> getRoleList(){

		return mapper.getRoleList();
	}

	/**
	 * 新增工号
	 * @param ajaxInfoBean ajax数据对象
	 * @return ajax对象
	 */

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

	/**
	 * 更新职员信息
	 * @param ajaxInfoBean ajax对象
	 * @return 字符串
	 */

	public String updateStaffInfo(AjaxInfoBean ajaxInfoBean){

		String str=null;
		int n=mapper.updateStaffInFoById(ajaxInfoBean);
		if (n>0){
			str="修改成功";
			return str;
		}
		return str;
	}

	/**
	 * 统计岗位人数对象集合
	 * @return 岗位人数对象集合
	 */
	public List<PostCountBean> postCountBeanList(){
		return mapper.postCountBeanList();
	}

	/**
	 * 统计岗位男女人数对象集合
	 * @return 岗位男女人数对象集合
	 */

	public List<PostSexCountBean> postSexCountBeanList(){
		return mapper.postSexCountBeanList();
	}
}


