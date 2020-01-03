package com.smart.community.zkdao;

import com.smart.community.zkbean.ParameterBean;
import com.smart.community.zkbean.Zk_BillBean;
import com.smart.community.zkbean.Zk_EcharsBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface zk_BillMapper
{
	int addBill(Zk_BillBean zk_billBean);

	List<Zk_BillBean> findBill(ParameterBean parameterBean);

	int countBill(ParameterBean parameterBean);

	List<Zk_EcharsBean> billCount();

	int setBillState(String billid);
}
