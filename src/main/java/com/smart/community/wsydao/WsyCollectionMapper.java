package com.smart.community.wsydao;

import com.smart.community.wsyjavabean.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsyCollectionMapper
{
	//获取收入记录表和查询功能
	public List<Tbl_receivables> findByCollection( TableBean tableBean );
	public int findCollectionPage( TableBean tableBean );
	//删除收款记录
	public int delCollection( int receivables_Id );
	//添加收款记录
	public int addCollection( Tbl_receivables tbl_receivables );
	//修改收款记录
	public int updateCollection( Tbl_receivables tbl_receivables );
	//统计收款数据
	public List<Wsy_EcharsBean> collectionCountMap();
	//对账增加
	public int addAcount( Tbl_account tbl_account );
	//删除对账
	public int delAccount( int accountId );
	//修改对账
	public int updateAccount( Tbl_account tbl_account );

}
