package com.smart.community.wsydao;

import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_safeEvent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsySafeEventMapper
{
	//治安事件分页及查询
	public List<Tbl_safeEvent> findByEvent(TableBean tableBean);
	public int findEventPage(TableBean tableBean);
	//添加治安事件
	public int insetAddEvent(Tbl_safeEvent tbl_safeEvent);
	//修改治安事件
	public int updateEvent(Tbl_safeEvent tbl_safeEvent);
	//删除治安事件
	public int deleteEvent(int safe_event_id);


}
