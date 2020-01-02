package com.smart.community.wsydao;

import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_parameter;
import com.smart.community.wsyjavabean.Tbl_purchase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsyPurchaseMapper
{
	//获取采购信息表
	public List<Tbl_purchase> findPurchase(TableBean tableBean);
	//查询采购信息
	public int findPurchasePage(TableBean tableBean);
    //删除采购信息
	public int deletePurchase(int purchase_id);
	//修改采购信息
	public int updatePurchase(Tbl_purchase tbl_purchase);

}
