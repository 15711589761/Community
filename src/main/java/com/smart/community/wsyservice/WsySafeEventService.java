package com.smart.community.wsyservice;


import com.smart.community.wsydao.WsySafeEventMapper;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_owner;
import com.smart.community.wsyjavabean.Tbl_safeEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class WsySafeEventService
{
  @Resource
	private WsySafeEventMapper wsySafeEventMapper;

  public TableBean findByEvent(int page,String safe_event_title,String startDate,String endDate){


	  int i = (page - 1) * 5;
	  TableBean tableBean = new TableBean();
	  tableBean.setPage(i);
	  tableBean.setSafe_event_title(safe_event_title);
	  tableBean.setStartDate(startDate);
	  tableBean.setEndDate(endDate);

	  List<Tbl_safeEvent> list = wsySafeEventMapper.findByEvent(tableBean);

	  tableBean.setCount(0);//
	  tableBean.setCount(wsySafeEventMapper.findEventPage(tableBean));//计算页数

	  tableBean.setData(list);//将list（表格数据）传到界面

	  return tableBean;
  }

  //添加治安事件
  public int insetAddEvent(Tbl_safeEvent tbl_safeEvent){

  	int addEvent = wsySafeEventMapper.insetAddEvent(tbl_safeEvent);

  	return addEvent;
  }

  //修改治安信息
	public int updateSafeEvent(Tbl_safeEvent tbl_safeEvent){
  	  return wsySafeEventMapper.updateEvent(tbl_safeEvent);

	}

	//删除治安事件
	public int deleteSafeEvent(int safe_event_id){

  	int delEvent = wsySafeEventMapper.deleteEvent(safe_event_id);

  	return delEvent;

	}


}
