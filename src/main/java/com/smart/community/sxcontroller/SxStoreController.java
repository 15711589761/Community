package com.smart.community.sxcontroller;


import com.smart.community.sxbean.LayuiTableBean;
import com.smart.community.sxbean.StoreInfo;
import com.smart.community.sxservice.SxStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SxStoreController
{
	@Resource
	private SxStoreService storeService;

	@RequestMapping("/findStore.view")
	public ModelAndView toStoreInfo()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("sx_store_manage");
		return modelAndView;
	}
	@RequestMapping("/findStore.action")
	@ResponseBody
	public LayuiTableBean findStoreInfo()
	{

		List<StoreInfo> storeInfos = storeService.findStoreInfo();
		LayuiTableBean tableBean = new LayuiTableBean();
		tableBean.setCode(0);
		tableBean.setMsg("");
		tableBean.setCount(storeInfos.size());
		tableBean.setData(storeInfos);
		return tableBean;
	}

	@RequestMapping("/delStore.action")
	@ResponseBody
	public Boolean delStoreInfo(StoreInfo storeInfo)
	{
		int x =storeService.delStoreInfoById(storeInfo);
		if (x>0)
		{
			return true;
		}
		return false;
	}

	@RequestMapping("/updateStore.action")
	@ResponseBody
	public Boolean updateStoreInfo(StoreInfo storeInfo)
	{
		int x =storeService.updateStoreInfoById(storeInfo);
		if (x>0)
		{
			return true;
		}
		return false;
	}
	@RequestMapping("/addStore.action")
	@ResponseBody
	public Boolean addeStoreInfo(StoreInfo storeInfo)
	{
		int x =storeService.addStoreInfoById(storeInfo);
		if (x>0)
		{
			return true;
		}
		return false;
	}



	@RequestMapping("/findNoAttrStore.view")
	@ResponseBody
	public ModelAndView findNoAttrStore()
	{
		ModelAndView modelAndView=new ModelAndView();
		List list = storeService.findNoAttrInfo();
		modelAndView.addObject("list",list);
		modelAndView.setViewName("sx_store_sell");
		return modelAndView;
	}

	@RequestMapping("/toStoreOrder.view")
	public ModelAndView toStoreOrder()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("sx_store_order");
		return modelAndView;
	}


}
