package com.smart.community.wsycontroller;

import com.smart.community.wsyaspects.Log;
import com.smart.community.wsydao.WsySafeEventMapper;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_safeEvent;
import com.smart.community.wsyservice.WsySafeEventService;
import com.smart.community.wsyservice.WsyService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class WsySafeEventController
{
	@Resource
	private WsySafeEventService wsySafeEventService;

	@RequestMapping("/safe.action")
	@Log(operationType="保安",operationName="查看治安表")
	public ModelAndView owner(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_stafeEvent");
	}

	@RequestMapping(value = "/safeEvent.action")
	@ResponseBody
	@Log(operationType="保安",operationName="查询治安信息")
	public TableBean safeEvent(int page, String safe_event_title, String startDate, String endDate)
	{


		TableBean tableBean = wsySafeEventService.findByEvent(page, safe_event_title, startDate, endDate);

		return tableBean;
	}

	@RequestMapping(value = "/insertSafeEvent.action")
	@ResponseBody
	@Log(operationType="保安",operationName="增加治安信息")
	//添加治安事件
	public TableBean insertEvent(Tbl_safeEvent tbl_safeEvent)
	{


		int addEvent = wsySafeEventService.insetAddEvent(tbl_safeEvent);
		System.out.println("增加治安事件..." + addEvent);
		TableBean tableBean = new TableBean();
		if (addEvent > 0)
		{
			tableBean.setMsg("1");
			System.out.println("治安事件添加成功！");
		} else
		{
			System.out.println("治安事件添加失败！");
		}

		return tableBean;

	}

	//修改治安事件
	@RequestMapping(value = "updateSafeEvent.action")
	@ResponseBody
	@Log(operationType="保安",operationName="修改治安信息")
	public TableBean updateSafeEvent(Tbl_safeEvent tbl_safeEvent)
	{

		int upSafeEvent = wsySafeEventService.updateSafeEvent(tbl_safeEvent);
		TableBean tableBean = new TableBean();

		if (upSafeEvent > 0)
		{


			tableBean.setMsg("1");
			System.out.println("治安事件修改成功！");
		} else
		{
			System.out.println("治安事件修改失败！");
		}

		return tableBean;

	}

	//删除治安事件
	@RequestMapping(value = "delSafeEvent.action")
	@ResponseBody
	@Log(operationType="保安",operationName="删除治安信息")
	public TableBean delSafeEvent(int safe_event_id)
	{


		int delSafe = wsySafeEventService.deleteSafeEvent(safe_event_id);
		Tbl_safeEvent tbl_safeEvent = new Tbl_safeEvent();
		TableBean tableBean = new TableBean();
		if (delSafe > 0)
		{
			tableBean.setMsg("1");
			System.out.println("删除成功");
		} else
		{
			System.out.println("删除失败");
		}
		return tableBean;

	}


}
