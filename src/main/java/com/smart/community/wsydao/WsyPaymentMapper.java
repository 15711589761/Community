package com.smart.community.wsydao;

import com.smart.community.wsyjavabean.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsyPaymentMapper
{
	//获取支出激励和查询功能
	public List<Tbl_payment> findByPayment( TableBean tableBean );
	public int findPaymentPage( TableBean tableBean );
	//删除支出记录
	public int delPayment( int payment_id );
	//添加付款记录
	public int addPayment( Tbl_payment tbl_payment );
	//修改付款记录
	public int updatePayment( Tbl_payment tbl_payment );
	//对账
	public int addAcount( Tbl_account tbl_account );

	//付款统计
	public List<Wsy_EcharsBean> payMeney();


}
