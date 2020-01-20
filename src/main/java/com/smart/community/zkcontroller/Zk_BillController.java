package com.smart.community.zkcontroller;

import com.smart.community.zkbean.ParameterBean;
import com.smart.community.zkbean.TableBean;
import com.smart.community.zkbean.Zk_BillBean;
import com.smart.community.zkbean.Zk_EcharsBean;
import com.smart.community.zkservice.Zk_BillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class Zk_BillController
{
	@Resource
	private Zk_BillService zk_billService;

	@RequestMapping("billJsp")
	public String billJsp()
	{
		return "zk_back_bill";
	}

	@RequestMapping("billCountJsp")
	public String billCountJsp()
	{
		return "zk_back_billcount";
	}

	@RequestMapping("addBill")
	@ResponseBody
	public int addBill(Zk_BillBean zk_billBean)
	{
		return zk_billService.addBill(zk_billBean);
	}

	@RequestMapping("findBill")
	@ResponseBody
	public TableBean findBill(String name, String beginDate, String endDate, String page, String limit)
	{
		ParameterBean parameterBean = new ParameterBean();
		parameterBean.setName(name);
		parameterBean.setBeginDate(beginDate);
		parameterBean.setEndDate(endDate);
		parameterBean.setPage(((Integer.valueOf(page) - 1) * Integer.valueOf(limit)));
		parameterBean.setLimit(Integer.valueOf(limit));
		TableBean tableBean = new TableBean();
		List<Zk_BillBean> zkBillBeans = zk_billService.findBill(parameterBean);
		tableBean.setCode(0);
		tableBean.setData(zkBillBeans);
		tableBean.setMsg("");
		tableBean.setCount(zk_billService.countBill(parameterBean));
		return tableBean;
	}
	@RequestMapping("billCount")
	@ResponseBody
	public List<Zk_EcharsBean> billCount()
	{
		return zk_billService.billCount();
	}


	@RequestMapping("setBillState")
	@ResponseBody
	public int setBillState(String billid){
		return zk_billService.setBillState(billid);
	}
}
