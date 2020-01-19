package com.smart.community.wsycontroller;

import com.smart.community.wsyaspects.Log;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_fire_tools;
import com.smart.community.wsyservice.WsyDeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WsyDeviceController
{
	@Resource
	private WsyDeviceService wsyDeviceService;

	@RequestMapping("/device.action")
	@Log(operationType="AAA",operationName="查看设备管理")
	public ModelAndView owner(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_device");
	}

	@RequestMapping(value = "/deviceTable.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="查看设备管理")
	public TableBean devicetable(int page, String fire_tools_name, String fire_tools_classification, String startDate,String endDate)
	{
        TableBean tableBean = wsyDeviceService.findByDevice(page, fire_tools_name,fire_tools_classification, startDate, endDate);

		return tableBean;
	}

	@RequestMapping(value = "delDevice.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="删除设备")
	public TableBean delDevice(Tbl_fire_tools tbl_fire_tools){
		int delDeviceId = wsyDeviceService.delDevice(tbl_fire_tools);
		TableBean tableBean = new TableBean();
		if (delDeviceId > 0){
			tableBean.setMsg("1");
			System.out.println("设备删除成功！");
		}else {
			System.out.println("设备删除失败！");
		}
		return tableBean;
	}

@RequestMapping(value = "addDevice.action")
@ResponseBody
@Log(operationType="AAA",operationName="增加设备")
public TableBean addDevice(Tbl_fire_tools tbl_fire_tools){
	int addDeviceId = wsyDeviceService.addDevice(tbl_fire_tools);
	TableBean tableBean = new TableBean();
	if (addDeviceId > 0){
		tableBean.setMsg("1");

	}else {

	}
	return tableBean;
}
	@RequestMapping(value = "updateDevice.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="修改设备")
	public TableBean updateDevice(Tbl_fire_tools tbl_fire_tools){
		int updateDeviceId = wsyDeviceService.updateDevice(tbl_fire_tools);
		TableBean tableBean = new TableBean();
		if (updateDeviceId > 0){
			tableBean.setMsg("1");

		}else {

		}
		return tableBean;
	}
}
