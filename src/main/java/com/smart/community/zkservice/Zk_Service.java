package com.smart.community.zkservice;

import com.smart.community.zkbean.ParameterBean;
import com.smart.community.zkbean.Zk_FacilityBean;
import com.smart.community.zkdao.zk_FacilityMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Zk_Service
{
	@Resource
	private zk_FacilityMapper zk_mapper;

	//设备带条件查询
	public List<Zk_FacilityBean> findFacility(ParameterBean parameterBean){
		return zk_mapper.findFacility(parameterBean);
	}
	//设备带条件查询统计
	public int countFacility(ParameterBean parameterBean){
		return zk_mapper.countFacility(parameterBean);
	}
	//修改设备数量
	public int setFacilityNum(Zk_FacilityBean zk_facilityBean){
		return zk_mapper.setFacilityNum(zk_facilityBean);
	}
	//删除设备
	public int delFacility(int facilityID){
		return zk_mapper.delFacility(facilityID);
	}

	//添加设备
	public int addFacility(Zk_FacilityBean zk_facilityBean){
		return zk_mapper.addFacility(zk_facilityBean);
	}
}
