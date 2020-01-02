package com.smart.community.vencontroller;


import com.smart.community.venjavabean.*;
import com.smart.community.venservices.JobNumberStaffService;
import com.smart.community.ventool.CodeTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller

public class JobNumberController
{

	@Resource
	private JobNumberStaffService service;

	/**
	 * 跳转到工号管理界面
	 */

	@RequestMapping(value = "/toJobNumberManagement")
	public ModelAndView toManagingPeople()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("v_jobNumberManagement");
		return mv;
	}
	/** 跳转到工号分配界面*/

	@RequestMapping(value = "/toSetJobNumber")
	public ModelAndView toSetJobNumber(HttpSession session)
	{
		List<StaffBean> list=service.getNoJobNumberStaffList();

		if (list.size()>0){
			session.setAttribute("jobNumberMark",1);
			session.setAttribute("noJobNumberStaffList",list);
		}
		else {
			session.setAttribute("jobNumberMark",0);
		}
		ModelAndView mv=new ModelAndView();
		mv.setViewName("v_jobNumberSetting");
		return mv;
	}

	/**检查是否有员工有未分配工号*/

	@RequestMapping("/checkStaffJobNumber.action")
	@ResponseBody
	public AjaxInfoBean checkStaffJobNumber()
	{
		AjaxInfoBean bean=new AjaxInfoBean();

		List<StaffBean> list=service.getNoJobNumberStaffList();

		if (list.size()==0){
			bean.setResultMsg("0");
			bean.setIdentify("checkStaffJobNumber");
		}
		else {
			bean.setResultMsg("1");
			bean.setIdentify("checkStaffJobNumber");
		}
		return bean;

	}
	/** 获取最新的工号*/
	@RequestMapping("/getLastJobNumber.action")
	@ResponseBody
	public AjaxInfoBean getLastJobNumber()
	{
		AjaxInfoBean ajaxInfoBean=new AjaxInfoBean();

		String str=service.getLastJobNumber();

		ajaxInfoBean.setIdentify("getLastJobNumber");

		ajaxInfoBean.setResultMsg(str);

		return ajaxInfoBean;

	}

	/**获取和工号数据*/

	@RequestMapping("/getJobNumberData.action")
	@ResponseBody
	public TableBean querySeparatingEmploy(String status, String page, String limit)
	{
		ConditionBean bean = new ConditionBean();

		bean.setStatus(status);

		bean.setPage((Integer.valueOf(page) - 1) * Integer.valueOf(limit));

		bean.setLimit(Integer.valueOf(limit));

		return service.queryJobNumber(bean);
	}

	/**删除工号*/

	@RequestMapping("/delJobNumber.action")
	@ResponseBody
	public AjaxInfoBean querySeparatingEmploy(AjaxInfoBean ajaxInfoBean)
	{

		AjaxInfoBean msg = new AjaxInfoBean();
		int num = service.delJobNumberById(ajaxInfoBean.getTargetId());

		if (num > 0)
		{
			msg.setResultMsg("success");
			msg.setIdentify("delJobNumber");
		}
		return msg;
	}

	/** 分配工号*/


	@RequestMapping("/jobNumberSetting.action")
	@ResponseBody
	public AjaxInfoBean jobNumberSetting(AjaxInfoBean ajaxInfoBean)
	{
		AjaxInfoBean msg = new AjaxInfoBean();
		String str = service.jobNumberSetting(ajaxInfoBean);
		msg.setResultMsg(str);
		return msg;
	}
	/** 增加工号*/

	@RequestMapping("/addJobNumber.action")
	@ResponseBody
	public AjaxInfoBean addJobNumber(AjaxInfoBean ajaxInfoBean)
	{
		AjaxInfoBean msg = new AjaxInfoBean();

		int startNumber=ajaxInfoBean.getStartNumber();

		int quantity=ajaxInfoBean.getQuantity();

		int endNumber=startNumber+quantity-1;

		List<String> list= CodeTool.getJobNumberList(startNumber,endNumber);

		int s=service.addJobNumber(list);

		if (s>0){
			msg.setResultMsg("done");
			msg.setQuantity(s);
		}

		return msg;
	}



}