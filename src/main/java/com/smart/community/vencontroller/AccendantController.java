package com.smart.community.vencontroller;


import com.smart.community.venjavabean.*;
import com.smart.community.venservices.AccendantService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;

/**
 * 维修人员控制层
 * @author Ven
 */

@Controller

public class AccendantController
{

	@Resource
	private AccendantService accendantService;

	/**
	 * 跳转到维修人员管理界面
	 * @return 维修人员管理界面
	 */
	@RequestMapping(value = "/toAccendantManagement")
	public ModelAndView toManagingPeople()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("v_accendantManagement");
		return mv;
	}

	/**
	 * 维修人员数据查询
	 * @param userSex 性别
	 * @param userName 姓名
	 * @param page 当前页
	 * @param limit 限制
	 * @return layui表格的数据对象
	 */

	@RequestMapping("/getAccendantData.action")
	@ResponseBody
	public TableBean querySeparatingEmploy(String userSex,String userName, String page, String limit)
	{
		ConditionBean bean = new ConditionBean();

		bean.setUserSex(userSex);

		bean.setUserName(userName);

		bean.setPage((Integer.valueOf(page) - 1) * Integer.valueOf(limit));

		bean.setLimit(Integer.valueOf(limit));

		return accendantService.queryAccendant(bean);
	}

	/**
	 * 新增维修人员
	 * @param ajaxInfoBean ajax交互数据
	 * @return ajax对象
	 */
	@RequestMapping("/addAccendant.action")
	@ResponseBody
	public AjaxInfoBean addAccendant(AjaxInfoBean ajaxInfoBean)
	{

		AjaxInfoBean msg=new AjaxInfoBean();

		int m=accendantService.addAccendant(ajaxInfoBean);

		if (m>0){
			msg.setResultMsg("done");
			return msg;
		}
		return msg;

	}

	/**
	 * 删除维修人员数据
	 * @param ajaxInfoBean ajax交互数据
	 * @return ajax对象
	 */

	@RequestMapping("/delAccendant.action")
	@ResponseBody
	public AjaxInfoBean delAccendant(AjaxInfoBean ajaxInfoBean)
	{

		AjaxInfoBean msg=new AjaxInfoBean();

		int m=accendantService.delAccendant(ajaxInfoBean.getAccendantId());

		if (m>0){
			msg.setResultMsg("done");
			return msg;
		}

		return msg;

	}
	/**
	 * 更新维修人员数据
	 * @param ajaxInfoBean 前端返回的交互数据
	 * @return ajax对象
	 */
	@RequestMapping("/updateAccendant.action")
	@ResponseBody
	public AjaxInfoBean updateAccendant(AjaxInfoBean ajaxInfoBean)
	{

		AjaxInfoBean msg=new AjaxInfoBean();

		int m=accendantService.updateAccendantById(ajaxInfoBean);

		if (m>0){
			msg.setResultMsg("done");
			return msg;
		}

		return msg;

	}

}