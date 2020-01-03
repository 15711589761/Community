package com.smart.community.zkservice;

import com.smart.community.zkbean.ParameterBean;
import com.smart.community.zkbean.Zk_BillBean;
import com.smart.community.zkbean.Zk_EcharsBean;
import com.smart.community.zkdao.zk_BillMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Zk_BillService
{
	@Resource
	private zk_BillMapper zk_billMapper;

	public int addBill(Zk_BillBean zk_billBean){
		return zk_billMapper.addBill(zk_billBean);
	}

	public List<Zk_BillBean> findBill(ParameterBean parameterBean){
		return zk_billMapper.findBill(parameterBean);
	}

	public int countBill(ParameterBean parameterBean){
		return zk_billMapper.countBill(parameterBean);
	}

	public List<Zk_EcharsBean> billCount(){
		return zk_billMapper.billCount();
	}

	public int setBillState(String billid){
		return zk_billMapper.setBillState(billid);
	}
}
