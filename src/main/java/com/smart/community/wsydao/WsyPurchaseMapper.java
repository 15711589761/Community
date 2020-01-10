package com.smart.community.wsydao;

import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_parameter;
import com.smart.community.wsyjavabean.Tbl_purchase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

	/**
	 * 获取本人采购申请列表
	 * @param applicant 申请人
	 * @return 采购申请集合
	 */
	public List<Tbl_purchase> selectForGetPurchaseId( @Param ("applicant")String applicant);

	/**
	 * 添加采购申请
	 * @param purchase 添加采购申请信息
	 * @return int
	 */
	public int insertForPurchaseApply(Tbl_purchase purchase);

	/**
	 * 修改采购状态
	 * @param status 状态
	 * @param purchaseId 采购id
	 * @return int
	 */
	public int updateForPurchaseApply(@Param("status")String status,@Param("purchaseId")String purchaseId,@Param("reviewer")String reviewer);
}
