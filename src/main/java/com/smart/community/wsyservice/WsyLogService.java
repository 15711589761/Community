package com.smart.community.wsyservice;

import com.smart.community.wsydao.WsyLogMapper;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_parameter;
import com.smart.community.wsyjavabean.Tbl_systemlog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WsyLogService
{
	@Resource
	private WsyLogMapper wsyLogMapper;

	public TableBean findLogService(int page, String operation_person,String startDate,String endDate,String startTime,String endTime){

		int i = (page - 1) * 5;
		TableBean tableBean = new TableBean();
		tableBean.setPage(i);
		tableBean.setOperation_person(operation_person);
		tableBean.setStartDate(startDate);
		tableBean.setEndDate(endDate);
		tableBean.setStartTime(startTime);
		tableBean.setEndTime(endTime);

		List<Tbl_systemlog> list = wsyLogMapper.findByLog(tableBean);

		tableBean.setCount(0);
		tableBean.setCount(wsyLogMapper.findLogPage(tableBean));
		tableBean.setData(list);

		return tableBean;
	}

	//增加日志
	public int addLog(Tbl_systemlog tbl_systemlog){
		int addlog = wsyLogMapper.saveLog(tbl_systemlog);
		return addlog;
	}

	//批量删除
	public int delLog(int operation_id){
		Integer delId = wsyLogMapper.delLog(operation_id);
		return delId;

	}

	//修改日志
	public int updateLog(Tbl_systemlog tbl_systemlog){
		int updateLog = wsyLogMapper.updateLog(tbl_systemlog);
		return updateLog;
	}

}
