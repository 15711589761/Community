package com.smart.community.sxdao;
import com.smart.community.sxbean.Fullcalendar;
import com.smart.community.sxbean.SaferScheduling;
import com.smart.community.sxbean.WorkTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SxMapper
{
	public List<SaferScheduling> saferFullcalendar();
	public List<Fullcalendar> SaferFullenlendarDate(@Param("getID") String getId);
	public List<WorkTime> checkInsert(WorkTime workTime);
	public int insertSaferFullenlendar(WorkTime workTime);
	public int deleteSaferFullenlendar(WorkTime workTime);
}
