package com.smart.community.vencontroller;


import com.smart.community.ljmbean.StaffBean;
import com.smart.community.venjavabean.AjaxInfoBean;
import com.smart.community.venjavabean.ConditionBean;
import com.smart.community.venjavabean.TableBean;
import com.smart.community.venservices.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Controller

public class NoticeController
{

	@Resource
	private NoticeService noticeService;

	/**
	 * 跳转到公告管理界面
	 */
	@RequestMapping(value = "/toNoticeManagement")
	public ModelAndView toManagingPeople(HttpSession session)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("v_noticeManagement");
		StaffBean staffBean =(StaffBean) session.getAttribute("staffBean");
		mv.addObject("staffId",staffBean.getStaffId());
		return mv;
	}
	/**
	 * 增加新的公告
	 * @param ajaxInfoBean ajax数据对象
	 * @return ajax对象
	 */

	@RequestMapping("/addNewNotice.action")
	@ResponseBody
	public AjaxInfoBean addNewNotice(AjaxInfoBean ajaxInfoBean)
	{

		AjaxInfoBean msg = new AjaxInfoBean();
		int num = noticeService.addNewNotice(ajaxInfoBean);

		if (num > 0)
		{
			msg.setResultMsg("done");
			msg.setIdentify("addNewNotice");
		}
		return msg;
	}
	/**
	 * 删除工号的
	 * @param ajaxInfoBean ajax数据对象
	 * @return ajax对象
	 */

	@RequestMapping("/delNotice.action")
	@ResponseBody
	public AjaxInfoBean delNotice(AjaxInfoBean ajaxInfoBean)
	{

		AjaxInfoBean msg = new AjaxInfoBean();
		int num = noticeService.delNoticeById(ajaxInfoBean.getTargetId());

		if (num > 0)
		{
			msg.setResultMsg("done");
			msg.setIdentify("delNotice");
		}
		return msg;
	}
	/**
	 * 修改维修人员信息
	 * @param ajaxInfoBean ajax数据对象
	 * @return ajax
	 */
	@RequestMapping("/updateNotice.action")
	@ResponseBody
	public AjaxInfoBean updateNotice(AjaxInfoBean ajaxInfoBean)
	{

		AjaxInfoBean msg = new AjaxInfoBean();
		int num = noticeService.updateNotice(ajaxInfoBean);

		if (num > 0)
		{
			msg.setResultMsg("done");
			msg.setIdentify("updateNotice");
		}
		return msg;
	}
	/**
	 * 查询公告数据
	 * @param startDate 开始日期
	 * @param endDate  结束日期
	 * @param noticeTitle 标题
	 * @param page 页面
	 * @param limit 每页条数限制
	 * @return 查询结果集
	 */
	@RequestMapping("/getNoticeData.action")
	@ResponseBody
	public TableBean queryStaff(String startDate, String endDate, String noticeTitle, String page, String limit,HttpSession session)
	{
		ConditionBean bean = new ConditionBean();
		StaffBean staffBean =(StaffBean) session.getAttribute("staffBean");
		if (staffBean!=null){
			bean.setStaffId(String.valueOf(staffBean.getStaffId()));
		}
		bean.setStartDate(startDate);
		bean.setEndDate(endDate);
		bean.setNoticeTitle(noticeTitle);
		bean.setPage((Integer.valueOf(page) - 1) * Integer.valueOf(limit));
		bean.setLimit(Integer.valueOf(limit));
		return noticeService.queryNotice(bean);
	}
}