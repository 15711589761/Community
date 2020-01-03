package com.smart.community.zkservice;

import com.smart.community.zkbean.ParameterBean;
import com.smart.community.zkbean.Zk_OwnerCarBean;
import com.smart.community.zkdao.zk_Owner_CarMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class Zk_OwnerCarService
{
	@Resource
	private zk_Owner_CarMapper zk_owner_carMapper;

	public List<Zk_OwnerCarBean> findOwnerCar(ParameterBean parameterBean){
		return zk_owner_carMapper.findOwnerCar(parameterBean);
	}

	public int countOwnerCar(ParameterBean parameterBean){
		return zk_owner_carMapper.countOwnerCar(parameterBean);
	}


	public Zk_OwnerCarBean getOwnerName(String roomNum){
		return zk_owner_carMapper.getOwnerName(roomNum);
	}

	public int addOwnerCar(ParameterBean parameterBean){
		return zk_owner_carMapper.addOwnerCar(parameterBean);
	}

	public int delOwnerCar(String id){
		return zk_owner_carMapper.delOwnerCar(id);
	}

	public List<zk_Owner_CarMapper> getRoomNum(){return zk_owner_carMapper.getRoomNum();}
}
