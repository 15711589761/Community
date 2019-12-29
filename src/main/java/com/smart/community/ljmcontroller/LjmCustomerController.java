package com.smart.community.ljmcontroller;

import com.smart.community.ljmbean.LayuiTableBean;
import com.smart.community.ljmbean.SuggestBean;
import com.smart.community.ljmbean.TableSearchBean;
import com.smart.community.ljmservice.LjmCustomerService;
import com.smart.community.ljmservice.LjmDeskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class LjmCustomerController
{
	@Resource
	private LjmDeskService deskService;
	@Resource
	private LjmCustomerService customerService;

	/**
	 * 跳转至投诉建议管理
	 * @return 界面
	 */
	@RequestMapping("/toSuggestManage.view")
	public ModelAndView toSuggestManageView()
	{
		return new ModelAndView("ljm_suggest_manage");
	}

	@RequestMapping("/forGetSuggestManageTable.action")
	@ResponseBody
	public LayuiTableBean forGetSuggestManageTable( TableSearchBean tableSearchBean )
	{
		LayuiTableBean tableBean = new LayuiTableBean();
		List<SuggestBean> suggestBeans = deskService.forGetSuggestTable(tableSearchBean);
		if (suggestBeans!=null&&suggestBeans.size()>0)
		{
			tableBean.setCode(0);
			tableBean.setData(suggestBeans);
		} else {
			tableBean.setCode(1);
			tableBean.setMsg("没有过投诉或建议");
		}

		return tableBean;
	}


	@RequestMapping("/feedbackForSuggest.action")
	@ResponseBody
	public LayuiTableBean feedbackForSuggest (String result,String suggestId)
	{
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		int sult = customerService.feedbackForSuggestService(result,suggestId);
		if (sult>0)
		{
			layuiTableBean.setCode(0);
			layuiTableBean.setMsg("反馈成功");
		} else {
			layuiTableBean.setCode(0);
			layuiTableBean.setMsg("系统繁忙，请稍后再试");
		}
		return layuiTableBean;
	}
}
