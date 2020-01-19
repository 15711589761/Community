package com.smart.community.wsydao;

import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_warehouse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsyWarehouseMapper
{
	//获取仓库表和表分页查询
	public List<Tbl_warehouse> findByWarehouse( TableBean tableBean );
	public int findWarehousePage( TableBean tableBean );
	//增加货物
	public int addWarehouse( Tbl_warehouse tbl_warehouse );
	public int updateWarehouse( Tbl_warehouse tbl_warehouse );
	public int findByquantity( int manifest_id );
}
