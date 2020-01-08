package com.smart.community.vencontroller;



import com.smart.community.venjavabean.*;
import com.smart.community.venservices.StaffService;
import com.smart.community.ventool.CodeTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller(value = "staffController")

public class StaffController
{

	@Resource
	private StaffService staffService;


	/**
	 * 跳转到职员管理界面
	 */

	@RequestMapping(value = "/toStaffQuery")
	public ModelAndView toStaffQuery(HttpSession session)
	{
		ModelAndView mv=new ModelAndView();
		List<RoleBean> roleBeans=staffService.getRoleList();
		System.out.println("角色的集合长度："+roleBeans.size());
		mv.setViewName("v_staffQuery");
		session.setAttribute("roleBeans",roleBeans);
		return mv;
	}
	/**
	 * 跳转到人资管理-离职人员管理界面
	 */

	@RequestMapping(value = "/toSeparatingEmployeeQuery")
	public ModelAndView toSeparatingEmployeeQuery(HttpSession session)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("v_separatingEmployeeQuery");
		return mv;
	}

	/**
	 * 跳转到在职人员-岗位统计界面
	 */
	@RequestMapping(value = "/toStaffPostCount")
	public ModelAndView toPostCount(HttpSession session)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("v_staffPostCount");
		return mv;
	}

	/**
	 * 跳转到在职人员-岗位男女人数统计界面
	 */
	@RequestMapping(value = "/toStaffPostSexCount")
	public ModelAndView toPostCount()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("v_staffPostSexCount");
		return mv;
	}

	/**获取离职人员数据*/

	@RequestMapping("/getSeparatingEmployData.action")
	@ResponseBody
	public TableBean querySeparatingEmploy(String startDate, String endDate, String userName, String page, String limit)
	{

		ConditionBean bean = new ConditionBean();

		bean.setStartDate(startDate);

		bean.setEndDate(endDate);

		bean.setUserName(userName);

		bean.setPage((Integer.valueOf(page) - 1) * Integer.valueOf(limit));

		bean.setLimit(Integer.valueOf(limit));

		return staffService.querySeparatingEmploy(bean);

	}

	/**获取在职人员数据*/

	@RequestMapping("/getStaffData.action")
	@ResponseBody
	public TableBean queryStaff(String startDate, String endDate, String userName, String page, String limit)
	{

		System.out.println("------------获取用户数据");

		ConditionBean conditionBean = new ConditionBean();

		conditionBean.setStartDate(startDate);

		conditionBean.setEndDate(endDate);

		conditionBean.setUserName(userName);

		conditionBean.setPage((Integer.valueOf(page) - 1) * Integer.valueOf(limit));

		conditionBean.setLimit(Integer.valueOf(limit));

		return staffService.queryStaff(conditionBean);

	}

	/**在职人员信息录入*/

	@RequestMapping("/addStaff.action")
	@ResponseBody
	public AjaxInfoBean addUser(AjaxInfoBean ajaxInfoBean)
	{
		AjaxInfoBean infoBean=new AjaxInfoBean();

		String msg=staffService.addNewStaff(ajaxInfoBean);

		infoBean.setResultMsg(msg);

		return infoBean;

	}

	/**在职人员离职处理*/

	@RequestMapping("/dimission.action")
	@ResponseBody
	public AjaxInfoBean dimission(AjaxInfoBean ajaxInfoBean)
	{

		AjaxInfoBean msg = new AjaxInfoBean();

		ajaxInfoBean.setStaffTermDate(CodeTool.getDate());

		int num = staffService.staffDimission(ajaxInfoBean);

		if (num > 0)
		{
			msg.setResultMsg("success");
			msg.setIdentify("dimission");
		}
		return msg;

	}
	/** 删除离职人员信息*/
	@RequestMapping("/delEmployeeInfo.action")
	@ResponseBody
	public AjaxInfoBean delMethod(AjaxInfoBean ajaxInfoBean)
	{

		AjaxInfoBean msg = new AjaxInfoBean();

		int num = staffService.delSeparatingEmploy(ajaxInfoBean.getTargetId());

		if (num > 0)
		{
			msg.setResultMsg("success");
			msg.setIdentify("del");
		}
		return msg;

	}

	/**在职人员数据更新*/
	@RequestMapping("/updateStaffInfo.action")
	@ResponseBody
	public AjaxInfoBean updateStaffInfo(AjaxInfoBean ajaxInfoBean)
	{
		AjaxInfoBean infoBean=new AjaxInfoBean();

		String msg=staffService.updateStaffInfo(ajaxInfoBean);

		infoBean.setResultMsg(msg);

		return infoBean;

	}

	/**岗位人员数据统计*/

	@RequestMapping("/postCount.action")
	@ResponseBody
	public List<PostCountBean> postCountBeanList( )
	{
		return staffService.postCountBeanList();

	}

	/**岗位人员性别数据统计*/

	@RequestMapping("/postSexCount.action")
	@ResponseBody
	public List<PostSexCountBean> postSexCountBeanList( )
	{
		return staffService.postSexCountBeanList();

	}

}