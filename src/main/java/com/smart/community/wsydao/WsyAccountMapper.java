package com.smart.community.wsydao;

import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_account;
import com.smart.community.wsyjavabean.Tbl_receivables;
import com.smart.community.wsyjavabean.Wsy_EcharsBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsyAccountMapper
{
	//获取对账记录和查询功能
	public List<Tbl_account> findByAccount( TableBean tableBean );
	public int findAccountPage( TableBean tableBean );
	//增加对账记录
//	public int addAcount(Tbl_account tbl_account);
	//删除对账
	public int delAccount( int accountId );
	//对账统计
	public List<Wsy_EcharsBean> accountCountMap();


}
