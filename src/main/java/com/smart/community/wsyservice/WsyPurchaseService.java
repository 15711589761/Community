package com.smart.community.wsyservice;

import com.smart.community.tool.LjmTool;
import com.smart.community.wsydao.WsyPurchaseMapper;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_owner;
import com.smart.community.wsyjavabean.Tbl_purchase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WsyPurchaseService
{
	@Resource
	private WsyPurchaseMapper wsyPurchaseMapper;

	//采购信息回显和查询
	public TableBean findPurchase(int page, String startDate,String endDate,String purchase_name,String purchase_model)
	{
		System.out.println("当前页条数.." + page);
		int i = (page - 1) * 5;
		TableBean tableBean = new TableBean();
		tableBean.setPage(i);//获取当前页数
		tableBean.setStartDate(startDate);
		tableBean.setEndDate(endDate);
		tableBean.setPurchase_name(purchase_name);
		tableBean.setPurchase_model(purchase_model);

		List<Tbl_purchase> list = wsyPurchaseMapper.findPurchase(tableBean);
         for (Tbl_purchase tbl_purchase : list){
	         System.out.println(tbl_purchase.toString());
         }

		tableBean.setCount(0);
		tableBean.setCount(wsyPurchaseMapper.findPurchasePage(tableBean));//计算页数

		tableBean.setData(list);//将list（表格数据）传到界面

		return tableBean;

	}

	//删除采购信息
	public int delpurchase(int purchase_id){
		int delpur = wsyPurchaseMapper.deletePurchase(purchase_id);
		return delpur;
	}

	/**
	 * 添加采购记录
	 * @param applyName 申请人
	 * @param purchaseModel 商品型号
	 * @param purchaseQuantity 商品数量
	 * @param purchasePrice 商品价格
	 * @return
	 */
	public int insertForPurchaseApply(String staff ,String purchaseName, String purchaseModel , String purchaseQuantity, String purchasePrice)
	{
		Tbl_purchase purchase = new Tbl_purchase();
		purchase.setApplicant(staff);
		purchase.setPurchase_name(purchaseName);
		purchase.setPurchase_model(purchaseModel);
		purchase.setPurchase_quantity(purchaseQuantity);
		purchase.setPurchase_price(purchasePrice);
		purchase.setApplicant_time(LjmTool.getTodayDate());
		return wsyPurchaseMapper.insertForPurchaseApply(purchase);
	}

	/**
	 * 获取新添加的采购记录id
	 * @param applicant 申请人
	 * @return int
	 */
	public int selectForGetPurchaseId(String applicant)
	{
		List<Tbl_purchase> list = wsyPurchaseMapper.selectForGetPurchaseId(applicant);
		int purchaseId = list.get(list.size()-1).getPurchase_id();
		return purchaseId;
	}

	/**
	 * 修改采购申请记录
	 * @param status 状态
	 * @param purchaseId 采购id
	 * @return int
	 */
	public int updateForPurchaseApply(String status,String purchaseId,String reviewer)
	{
		return wsyPurchaseMapper.updateForPurchaseApply(status,purchaseId,reviewer);
	}
}
