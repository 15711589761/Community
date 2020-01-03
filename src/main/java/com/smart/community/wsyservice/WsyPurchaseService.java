package com.smart.community.wsyservice;

import com.smart.community.wsydao.WsyPurchaseMapper;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_owner;
import com.smart.community.wsyjavabean.Tbl_purchase;
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
	//修改参数信息
	public int updatePurchase(Tbl_purchase tbl_purchase){
		int updatePur = wsyPurchaseMapper.updatePurchase(tbl_purchase);
		return updatePur;
	}

}
