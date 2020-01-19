package com.smart.community.wsyservice;

import com.smart.community.wsydao.WsyPaymentMapper;
import com.smart.community.wsyjavabean.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WsyPaymentService
{
	@Resource
	private WsyPaymentMapper wsyPaymentMapper;

	//获取收入记录表，and 查询功能
	public TableBean findByPayment(int page, String payment_type, String payment_time, String payment_remarks)
	{

		int i = (page - 1) * 5;
		TableBean tableBean = new TableBean();
		tableBean.setPage(i);//获取当前页数
		tableBean.setPayment_type(payment_type);
		tableBean.setPayment_time(payment_time);
		tableBean.setPayment_remarks(payment_remarks);

		List<Tbl_payment> list = wsyPaymentMapper.findByPayment(tableBean);
		tableBean.setCount(0);
		tableBean.setCount(wsyPaymentMapper.findPaymentPage(tableBean));//计算页数
        tableBean.setData(list);//将list（表格数据）传到界面

		return tableBean;

	}

	//删除付款记录
	public int delPayment(int payment_id){
		int delPayment = wsyPaymentMapper.delPayment(payment_id);
		return delPayment;
	}

	//增加付款记录
	public int addPayment(Tbl_payment tbl_payment){

		int add_Payment = wsyPaymentMapper.addPayment(tbl_payment);
		return add_Payment;
	}
	//修改付款记录
	public int updatePayment(Tbl_payment tbl_payment){
		int update_Payment = wsyPaymentMapper.updatePayment(tbl_payment);
		return update_Payment;
	}

	//增加对账记录
	public int addAcount(Tbl_account tbl_account){
		int add_account = wsyPaymentMapper.addAcount(tbl_account);
		return add_account;
	}

	//支出统计
	public List<Wsy_EcharsBean> payMeneyCount(){
		return wsyPaymentMapper.payMeney();
	}

}
