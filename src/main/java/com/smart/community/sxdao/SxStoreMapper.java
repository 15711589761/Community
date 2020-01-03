package com.smart.community.sxdao;
import com.smart.community.sxbean.StoreInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SxStoreMapper
{
	public List<StoreInfo> findStoreInfo();
	public int delStoreInfoById(StoreInfo storeInfo);
	public int updateStoreInfoById(StoreInfo storeInfo);
	public int addStoreInfoById(StoreInfo storeInfo);
	public List<StoreInfo> findNoAttrInfo();
}
