package com.smart.community.wsyservice;

import com.smart.community.wsydao.WsyAccountMapper;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_account;
import com.smart.community.wsyjavabean.Tbl_receivables;
import com.smart.community.wsyjavabean.Wsy_EcharsBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WsyAccountService
{
	@Resource
	private WsyAccountMapper wsyAccountMapper;

	//获取收入记录表，and 查询功能
	public TableBean findByAccount(int page,String startMonry, String endMoney,String accountMoney, String startDate, String endDate,String accountType, String accountRemark)
	{
		System.out.println("当前页条数.." + page);
		int i = (page - 1) * 5;
		TableBean tableBean = new TableBean();
		tableBean.setPage(i);//获取当前页数
		tableBean.setAccountMoney(accountMoney);
		tableBean.setStartDate(startDate);
		tableBean.setEndDate(endDate);
		tableBean.setStartMonry(startMonry);
		tableBean.setEndMoney(endMoney);
		tableBean.setAccountType(accountType);
		tableBean.setAccountRemark(accountRemark);

		List<Tbl_account> list = wsyAccountMapper.findByAccount(tableBean);

		tableBean.setCount(0);
		tableBean.setCount(wsyAccountMapper.findAccountPage(tableBean));//计算页数

		tableBean.setData(list);//将list（表格数据）传到界面

		return tableBean;

	}

//	//增加对账
//	public int addAcount(Tbl_account tbl_account){
//		int addAcount = wsyAccountMapper.addAcount(tbl_account);
//		return addAcount;
//	}

	//删除对账
	public int delAccount(int accountId){
		int del_account = wsyAccountMapper.delAccount(accountId);
		return del_account;
	}

	//统计对账
	public List<Wsy_EcharsBean> accountCountMap()
	{
		return wsyAccountMapper.accountCountMap();

	}
}
