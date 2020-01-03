package com.smart.community.ljmcontroller;

import com.smart.community.ljmbean.LayuiTableBean;
import com.smart.community.ljmbean.StaffBean;
import com.smart.community.ljmbean.TableSearchBean;
import com.smart.community.ljmbean.VisitorBean;
import com.smart.community.ljmservice.LjmVisitorService;
import com.smart.community.tool.LjmTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LjmVisitorController
{
	@Resource
	private LjmVisitorService visitorService;

	/**
	 * 访客查询页面跳转
	 */
	@RequestMapping("/visitorsTable.view")
	public ModelAndView toBackstageLogin()
	{
		return new ModelAndView("ljm_visitors");
	}


	/**
	 * 访客查询记录查询
	 */
	@RequestMapping("/getVisitorsTable.action")
	@ResponseBody
	public LayuiTableBean visitorsSelectAction( TableSearchBean tableSearchBean )
	{
		LayuiTableBean layuiTableBean = visitorService.getVisitorTable( tableSearchBean );
		return layuiTableBean;
	}

	/**
	 * 访客登记页面跳转
	 */
	@RequestMapping("/toInsertVisitor.view")
	public ModelAndView toInsertVisitorView ()
	{
		return new ModelAndView("ljm_register_visitor");
	}

	/**
	 * 访客登记
	 */
	@RequestMapping("/insertVisitorRecord.action")
	public ModelAndView visitorInsertAction( VisitorBean visitorBean , HttpServletRequest request )
	{
		ModelAndView modelAndView = new ModelAndView("ljm_register_visitor");
		StaffBean staffBean = (StaffBean) request.getSession().getAttribute("staffBean");
		visitorBean.setRecorder(staffBean.getStaffName());
		visitorBean.setRecorderId(staffBean.getStaffId());
		visitorBean.setVisitorDate(LjmTool.getTodayDate());
		visitorBean.setVisitorTime(LjmTool.getTime());
		int result = visitorService.insertForVisitorService(visitorBean);
		if (result>0)
		{
			modelAndView.addObject("insertSuccess","成功!");
		} else {
			modelAndView.addObject("insertFail","系统繁忙，请重试!");
		}
		return modelAndView;
	}
}
