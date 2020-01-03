package com.smart.community.sxservice;

import com.smart.community.sxbean.CheckAdd;
import com.smart.community.sxbean.CheckFacility;
import com.smart.community.sxdao.SxCheckMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CheckService
{
	@Resource
	SxCheckMapper sxCheckMapper;

	public List<CheckFacility> CheckInfo(){
		List<CheckFacility> list= sxCheckMapper.CheckInfo();
		return list;
	}
	public int insertCheckInfo(CheckAdd checkAdd){
		int add = sxCheckMapper.insertCheckInfo(checkAdd);
		return add;
	}


}
