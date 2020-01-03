package com.smart.community.wsycontroller;

import com.smart.community.wsyaspects.Log;
import com.smart.community.wsydao.WsyPurchaseMapper;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_purchase;
import com.smart.community.wsyservice.WsyPurchaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WsyPurchaseController
{
	@Resource
	private WsyPurchaseService wsyPurchaseService;

	//获取采购页面
	@RequestMapping("/purchase.action")
	@Log(operationType="SQR",operationName="查看采购表")
	public ModelAndView owner(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_purchase");
	}

	//获取采购信息
	@RequestMapping(value = "/selectPurchase.action")
	@ResponseBody
	@Log(operationType="SQR",operationName="查询采购信息")
	public TableBean purchaseTable(int page, String startDate,String endDate,String purchase_name,String purchase_model)
	{

        TableBean tableBean = wsyPurchaseService.findPurchase(page, startDate, endDate, purchase_name, purchase_model);

		return tableBean;
	}

	//删除采购信息
	@RequestMapping(value = "/delPurchase.action")
	@ResponseBody
	@Log(operationType="SQR",operationName="删除采购记录")
	public TableBean delPurchase(int purchase_id){

		int delPurchaseId = wsyPurchaseService.delpurchase(purchase_id);
		TableBean tableBean = new TableBean();
		if (delPurchaseId > 0){
			tableBean.setMsg("1");
			System.out.println("采购信息删除成功！");
		}else {
			System.out.println("采购信息删除失败！");
		}
		return tableBean;

	}
	//修改采购信息
	@RequestMapping(value = "/updatePurchase.action")
	@ResponseBody
	@Log(operationType="SQR",operationName="修改采购记录")
	public TableBean updatePurchase(Tbl_purchase tbl_purchase){
		int upPurchase = wsyPurchaseService.updatePurchase(tbl_purchase);
		TableBean tableBean = new TableBean();
		if (upPurchase > 0){
			tableBean.setMsg("1");
			System.out.println("采购信息修改成功");
		}else {
			System.out.println("采购信息修改失败！");
		}
	return tableBean;
	}



}
