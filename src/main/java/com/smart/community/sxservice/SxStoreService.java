package com.smart.community.sxservice;

import com.smart.community.sxbean.Fullcalendar;
import com.smart.community.sxbean.SaferScheduling;
import com.smart.community.sxbean.StoreInfo;
import com.smart.community.sxbean.WorkTime;
import com.smart.community.sxdao.SxMapper;
import com.smart.community.sxdao.SxStoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SxStoreService
{
	@Resource
	private SxStoreMapper storeMapper;
	public List<StoreInfo> findStoreInfo()
	{
		List<StoreInfo> storeInfos = storeMapper.findStoreInfo();
		return storeInfos;
	}

	public int delStoreInfoById(StoreInfo storeInfo)
	{
		int result = storeMapper.delStoreInfoById(storeInfo);
		return result;
	}
	public int updateStoreInfoById(StoreInfo storeInfo)
	{
		int result = storeMapper.updateStoreInfoById(storeInfo);
		return result;
	}
	public int addStoreInfoById(StoreInfo storeInfo)
	{
		int result = storeMapper.addStoreInfoById(storeInfo);
		return result;
	}

	public List<StoreInfo> findNoAttrInfo(){
		List<StoreInfo> storeInfos = storeMapper.findNoAttrInfo();
		return storeInfos;
	}
}
