package com.smart.community.wsyservice;

import com.smart.community.wsydao.WsyDeviceMapper;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_fire_tools;
import com.smart.community.wsyjavabean.Tbl_owner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WsyDeviceService
{
	@Resource
	private WsyDeviceMapper wsyDeviceMapper;

    //获取合同管理数据
	public TableBean findByDevice(int page, String fire_tools_name, String fire_tools_classification, String startDate,String endDate)
	{
		System.out.println("当前页条数.." + page);
		int i = (page - 1) * 5;
		TableBean tableBean = new TableBean();
		tableBean.setPage(i);//获取当前页数
		tableBean.setFire_tools_name(fire_tools_name);
		tableBean.setFire_tools_classification(fire_tools_classification);
		tableBean.setStartDate(startDate);
		tableBean.setEndDate(endDate);
		List<Tbl_fire_tools> list = wsyDeviceMapper.findByDevice(tableBean);
         for (Tbl_fire_tools tbl_fire_tools : list){
	         System.out.println("设备管理..."+tbl_fire_tools.toString());
         }
		tableBean.setCount(0);
		tableBean.setCount(wsyDeviceMapper.findByDevicePage(tableBean));//计算页数

		tableBean.setData(list);//将list（表格数据）传到界面

		return tableBean;

	}
	//删除设备管理
	public int delDevice(Tbl_fire_tools tbl_fire_tools){
		int tools = wsyDeviceMapper.delDevice(tbl_fire_tools);
		return tools;
	}
	//添加设备管理
	public int addDevice(Tbl_fire_tools tbl_fire_tools){
		int vice = wsyDeviceMapper.addDevice(tbl_fire_tools);
		return vice;
	}
	//修改设备信息
	public int updateDevice(Tbl_fire_tools tbl_fire_tools){
		int device = wsyDeviceMapper.updateDevice(tbl_fire_tools);
		return device;

	}
}
