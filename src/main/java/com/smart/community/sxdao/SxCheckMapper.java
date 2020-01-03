package com.smart.community.sxdao;
import com.smart.community.sxbean.CheckAdd;
import com.smart.community.sxbean.CheckFacility;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SxCheckMapper
{
	public List<CheckFacility> CheckInfo();
	public int insertCheckInfo(CheckAdd checkAdd);
}
