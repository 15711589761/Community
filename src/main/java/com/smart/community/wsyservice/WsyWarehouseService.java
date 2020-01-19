package com.smart.community.wsyservice;


import com.smart.community.wsydao.WsyWarehouseMapper;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_warehouse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WsyWarehouseService
{
	@Resource
	private WsyWarehouseMapper wsyWarehouseMapper;

	//业主表格数据回显和查询
	public TableBean WarehouseTable(int page, String manifest_number, String manifest_name)
	{

		int i = (page - 1) * 5;
		TableBean tableBean = new TableBean();
		tableBean.setPage(i);//获取当前页数
		tableBean.setManifest_number(manifest_number);
		tableBean.setManifest_name(manifest_name);
        List<Tbl_warehouse> list = wsyWarehouseMapper.findByWarehouse(tableBean);

		tableBean.setCount(0);
		tableBean.setCount(wsyWarehouseMapper.findWarehousePage(tableBean));//计算页数

		tableBean.setData(list);//将list（表格数据）传到界面

		return tableBean;

	}

	//增加货物
	public int addWarehouse(Tbl_warehouse tbl_warehouse){
		int addWh = wsyWarehouseMapper.addWarehouse(tbl_warehouse);
		return addWh;

	}


	public  int updateWarehouse(Tbl_warehouse tbl_warehouse){
		int updateNum = wsyWarehouseMapper.updateWarehouse(tbl_warehouse);
		return updateNum;
	}


	//增加原有货物的数量
	public int  addNumWarehouse(int manifest_id){


		int addnum = wsyWarehouseMapper.findByquantity(manifest_id);


		return addnum;



	}

}

