package com.smart.community.wsydao;

import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_fire_tools;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface WsyDeviceMapper
{
	public List<Tbl_fire_tools> findByDevice(TableBean tableBean);
	public int findByDevicePage(TableBean tableBean);
	//删除设备管理
	public int delDevice(Tbl_fire_tools tbl_fire_tools);
	//添加设备
	public int addDevice(Tbl_fire_tools tbl_fire_tools);
	//修改设备信息
	public int updateDevice(Tbl_fire_tools tbl_fire_tools);
}
