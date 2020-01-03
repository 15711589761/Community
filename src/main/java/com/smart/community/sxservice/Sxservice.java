package com.smart.community.sxservice;

import com.smart.community.sxbean.Fullcalendar;
import com.smart.community.sxbean.WorkTime;
import com.smart.community.sxdao.SxMapper;
import com.smart.community.sxbean.SaferScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Sxservice
{
	@Resource
	private SxMapper SxMapper;
	public List<SaferScheduling> saferCanlendar()
	{
		List<SaferScheduling> saferSchedulingList = SxMapper.saferFullcalendar();
		return saferSchedulingList;
	}
	public Map<String,List<Fullcalendar>> SaferFullenlendarDate(String getId)
	{
		HashMap<String,List<Fullcalendar>> map = new HashMap();
		List<Fullcalendar> fullcalendars = SxMapper.SaferFullenlendarDate(getId);
		for (int i = 0; i < fullcalendars.size(); i++)
		{
			Fullcalendar fullcalendar = fullcalendars.get(i);
			if (map.containsKey(fullcalendar.getWorkTime()))
			{
				List<Fullcalendar> list = map.get(fullcalendar.getWorkTime());
				list.add(fullcalendar);
			} else
			{
				List<Fullcalendar> list = new ArrayList<>();
				list.add(fullcalendar);
				map.put(fullcalendar.getWorkTime(), list);
			}
		}
		return map;
	}

	public List<WorkTime> checkInsert(WorkTime checkWorkTime){
		List<WorkTime> result = SxMapper.checkInsert(checkWorkTime);
		return result;
	}
	public int insertSaferFullenlendar(WorkTime addWorkTime)
	{
		int result = SxMapper.insertSaferFullenlendar(addWorkTime);
		return result;
	}

	public int deleteSaferFullenlendar(WorkTime delWorkTime)
	{
		int result = SxMapper.deleteSaferFullenlendar(delWorkTime);
		return result;
	}
}
