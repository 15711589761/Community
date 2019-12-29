package com.smart.community.ljmcontroller;

import com.smart.community.ljmbean.LayuiTableBean;
import com.smart.community.ljmbean.TableSearchBean;
import com.smart.community.ljmservice.LjmVisitorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

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
}
