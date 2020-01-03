package com.smart.community.zkdao;

import com.smart.community.zkbean.ParameterBean;
import com.smart.community.zkbean.Zk_OwnerCarBean;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface zk_Owner_CarMapper
{
	List<Zk_OwnerCarBean> findOwnerCar(ParameterBean parameterBean);

	int countOwnerCar(ParameterBean parameterBean);

	Zk_OwnerCarBean getOwnerName(String roomNum);

	int addOwnerCar(ParameterBean parameterBean);

	int delOwnerCar(String ownerCarId);

	List<zk_Owner_CarMapper> getRoomNum();
}
