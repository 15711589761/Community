package com.smart.community.wsycontroller;

import com.smart.community.wsyaspects.Log;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_safeEvent;
import com.smart.community.wsyjavabean.Tbl_systemlog;
import com.smart.community.wsyservice.WsyLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class WsyLogController
{
	@Resource
	private WsyLogService wsyLogService;

	@RequestMapping("/log.action")
	@Log(operationType="AAA",operationName="查看日志表")
	public ModelAndView owner(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_loginlist");
	}

	@RequestMapping(value = "/logTable.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="查看日志")
	public TableBean logtable(int page, String operation_person,String startDate,String endDate,String startTime,String endTime)
	{


		TableBean tableBean = wsyLogService.findLogService(page, operation_person, startDate, endDate,startTime,endTime);

		return tableBean;
	}

	//批量删除
	@RequestMapping(value = "/deleMore.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="删除日志")
	public TableBean deleteLog(int operation_id){
		int delLog = wsyLogService.delLog(operation_id);
		Tbl_systemlog tbl_systemlog = new Tbl_systemlog();
		TableBean tableBean = new TableBean();
		if (delLog > 0)
		{
			tableBean.setMsg("1");
			System.out.println("删除成功");
		} else
		{
			System.out.println("删除失败");
		}
		return tableBean;

	}

	//修改日志
	@RequestMapping(value = "/updateLog.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="修改日志")
	public TableBean updateLog(Tbl_systemlog tbl_systemlog){

		int upLog = wsyLogService.updateLog(tbl_systemlog);
		TableBean tableBean = new TableBean();
		if (upLog > 0){
			tableBean.setMsg("1");
			System.out.println("日志修改成功！");
		}else {
			System.out.println("日志修改失败！");
		}
		return tableBean;

	}
	}



