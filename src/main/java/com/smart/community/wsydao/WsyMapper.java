package com.smart.community.wsydao;

import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_owner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsyMapper
{

	public List<Tbl_owner> findByOwner(TableBean tableBean);//业主表格分页

	public int findByOwnerPage(TableBean tableBean);//计算总页数及分页查询

	public int delOwner(int owner_id);//删除业主

	public int addOwner(Tbl_owner tbl_owner);//添加业主

	public int updateOwner(Tbl_owner tbl_owner);//修改业主信息

}
