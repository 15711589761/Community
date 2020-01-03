package com.smart.community.zkdao;

import com.smart.community.zkbean.ParameterBean;
import com.smart.community.zkbean.Zk_FacilityBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface zk_FacilityMapper
{
	List<Zk_FacilityBean> findFacility(ParameterBean parameterBean);

	int countFacility(ParameterBean parameterBean);

	int setFacilityNum(Zk_FacilityBean zk_facilityBean);

	int delFacility(int facilityID);

	int addFacility(Zk_FacilityBean zk_facilityBean);
}
