package com.smart.community.zkcontroller;

import com.smart.community.zkbean.ParameterBean;
import com.smart.community.zkbean.TableBean;
import com.smart.community.zkbean.Zk_FacilityBean;
import com.smart.community.zkbean.Zk_OwnerCarBean;
import com.smart.community.zkdao.zk_Owner_CarMapper;
import com.smart.community.zkservice.Zk_OwnerCarService;
import com.smart.community.tool.LjmTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Zk_Owner_Car_Controller
{
	@Resource
	private Zk_OwnerCarService zk_ownerCarService;

	//跳转车辆管理界面
	@RequestMapping("/ownerCarJsp")
	@ResponseBody
	public ModelAndView ownerCarJsp()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("zk_back_owner_car");
		return modelAndView;
	}

	//跳转导航界面
	@RequestMapping("/navigationJsp")
	public String navigationJsp()
	{
		return "navigation";
	}

	//跳转录入车辆信息界面
	@RequestMapping("/addownerCarJsp")
	@ResponseBody
	public ModelAndView addownerCarJsp(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("roomNum",zk_ownerCarService.getRoomNum());
		modelAndView.setViewName("zk_back_addownerCar");
		return modelAndView;
	}

	//带条件查询车辆信息
	@RequestMapping("/findOwnerCar")
	@ResponseBody
	public TableBean findOwnerCar(String ownerName,String carNum, String page, String limit){
		ParameterBean parameterBean=new ParameterBean();
		parameterBean.setName(ownerName);
		parameterBean.setCarNum(carNum);
		parameterBean.setPage(((Integer.valueOf(page) - 1) * Integer.valueOf(limit)));
		parameterBean.setLimit(Integer.valueOf(limit));
		TableBean tableBean=new TableBean();
		List<Zk_OwnerCarBean> zk_ownerCarBeans=zk_ownerCarService.findOwnerCar(parameterBean);
		tableBean.setCode(0);
		tableBean.setData(zk_ownerCarBeans);
		tableBean.setMsg("");
		tableBean.setCount(zk_ownerCarService.countOwnerCar(parameterBean));
		return tableBean;
	}

	//获取业主名
	@RequestMapping("/getOwnerName")
	@ResponseBody
	public Zk_OwnerCarBean getOwnerName(String msg){
		System.out.println(zk_ownerCarService.getOwnerName(msg));
		return zk_ownerCarService.getOwnerName(msg);
	}

	//添加车辆信息
	@RequestMapping("/addOwnerCar")
	@ResponseBody
	public int addOwnerCar(ParameterBean parameterBean){
		parameterBean.setBeginDate(LjmTool.getTodayDate());
		return zk_ownerCarService.addOwnerCar(parameterBean);
	}

	//删除车辆信息
	@RequestMapping("/delOwnerCar")
	@ResponseBody
	public int delOwnerCar(String msg){
		return zk_ownerCarService.delOwnerCar(msg);
	}

	//得到楼栋号
	public List<zk_Owner_CarMapper> getRoomNum(){
		return zk_ownerCarService.getRoomNum();
	}

}
