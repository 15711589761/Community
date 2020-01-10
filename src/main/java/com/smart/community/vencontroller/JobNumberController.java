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
		ModelAndView mv = new ModelAndView();
		mv.setViewName("v_jobNumberManagement");
		return mv;
	}

	/**
	 * 跳转到工号分配界面
	 *
	 * @param session 用于设置没有分配工号的职员对象集合
	 * @return 公告分配界面
	 */

	@RequestMapping(value = "/toSetJobNumber")
	public ModelAndView toSetJobNumber(HttpSession session)
	{
		List<StaffBean> list = service.getNoJobNumberStaffList();

		if (list.size() > 0)
		{
			session.setAttribute("jobNumberMark", 1);
			session.setAttribute("noJobNumberStaffList", list);
		} else
		{
			session.setAttribute("jobNumberMark", 0);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("v_jobNumberSetting");
		return mv;
	}

	/**
	 * 检查是否有未分配工号的人员
	 *
	 * @return ajax数据对象
	 */

	@RequestMapping("/checkStaffJobNumber.action")
	@ResponseBody
	public AjaxInfoBean checkStaffJobNumber()
	{
		AjaxInfoBean bean = new AjaxInfoBean();

		List<StaffBean> list = service.getNoJobNumberStaffList();

		if (list.size() == 0)
		{
			bean.setResultMsg("0");
			bean.setIdentify("checkStaffJobNumber");
		} else
		{
			bean.setResultMsg("1");
			bean.setIdentify("checkStaffJobNumber");
		}
		return bean;

	}

	/**
	 * 获取当前最新的工号
	 *
	 * @return ajax对象
	 */
	@RequestMapping("/getLastJobNumber.action")
	@ResponseBody
	public AjaxInfoBean getLastJobNumber()
	{
		AjaxInfoBean ajaxInfoBean = new AjaxInfoBean();

		String str = service.getLastJobNumber();

		ajaxInfoBean.setIdentify("getLastJobNumber");

		ajaxInfoBean.setResultMsg(str);

		return ajaxInfoBean;

	}

	/**
	 * 多条件及分页查询工号
	 *
	 * @param status 工号状态
	 * @param page   当前页面
	 * @param limit  每页显示记录数量
	 * @return layui的表格数据对象
	 */

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

	/**
	 * 删除工号
	 *
	 * @param ajaxInfoBean ajax对象
	 * @return ajax对象
	 */
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

	/**
	 * 分配工号
	 *
	 * @param ajaxInfoBean ajax数据对象
	 * @return ajax对象
	 */
	@RequestMapping("/jobNumberSetting.action")
	@ResponseBody
	public AjaxInfoBean jobNumberSetting(AjaxInfoBean ajaxInfoBean)
	{
		AjaxInfoBean msg = new AjaxInfoBean();
		String str = service.jobNumberSetting(ajaxInfoBean);
		msg.setResultMsg(str);
		return msg;
	}

	/**
	 * 批量增加工号
	 *
	 * @param ajaxInfoBean ajax数据对线
	 * @return ajax对像
	 */
	@RequestMapping("/addJobNumber.action")
	@ResponseBody
	public AjaxInfoBean addJobNumber(AjaxInfoBean ajaxInfoBean)
	{
		AjaxInfoBean msg = new AjaxInfoBean();
		//获取起始工号的数字（去除前面补的0）
		int startNumber = ajaxInfoBean.getStartNumber();
		//获取数量
		int quantity = ajaxInfoBean.getQuantity();
		//获取结束工号的数字（去除前面补的0）
		int endNumber = startNumber + quantity - 1;
		//调用封装的方法返回工号集合
		List<String> list = CodeTool.getJobNumberList(startNumber, endNumber);
		//批量插入工号
		int s = service.addJobNumber(list);
		if (s > 0)
		{
			msg.setResultMsg("done");
			msg.setQuantity(s);
		}

		return msg;
	}


}