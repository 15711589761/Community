package com.smart.community.wsycontroller;

import com.smart.community.wsyaspects.Log;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_parameter;
import com.smart.community.wsyservice.WsyParameterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;

@Controller
public class wsyParameterController
{
	@Resource
	private WsyParameterService wsyParameterService;

	@RequestMapping("/parmeter.action")
	@Log(operationType="BBB",operationName="查看参数表")
	public ModelAndView parmeter(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_parmeter");
	}

	//获取参数表
	@RequestMapping(value = "/parmeterlist.action")
	@ResponseBody
	@Log(operationType="查询参数信息",operationName="BBB")
	public TableBean parameterTable(int page, String searchName)
	{

		TableBean tableBean = wsyParameterService.findByParameter(page, searchName);

		return tableBean;
	}

	//删除参数
	@RequestMapping(value = "delParmeter.action")
	@ResponseBody
	@Log(operationType="BBB",operationName="删除参数信息")
	public TableBean delParmeter(int parameter_id)
	{
		int delPar = wsyParameterService.parameter(parameter_id);
		TableBean tableBean = new TableBean();

		if (delPar > 0)
		{
			tableBean.setMsg("1");
			System.out.println("参数删除成功");
		} else
		{
			System.out.println("参数删除失败");
		}


		return tableBean;
	}

	//增加参数
	@RequestMapping(value = "addParmeter.action")
	@ResponseBody
	@Log(operationType="BBB",operationName="增加参数信息")
	public TableBean addParmeter(Tbl_parameter tbl_parameter)
	{
		int addPar = wsyParameterService.addParameter(tbl_parameter);
		TableBean tableBean = new TableBean();
		if (addPar > 0)
		{
			tableBean.setMsg("1");
			System.out.println("参数添加成功！");
		} else
		{
			System.out.println("参数添加失败！");
		}

		return tableBean;
	}

	//修改参数
	@RequestMapping(value = "updateParameter.action")
	@ResponseBody
	@Log(operationType="BBB",operationName="修改参数信息")
	public TableBean updateParameter(Tbl_parameter tbl_parameter)
	{
		int upPar = wsyParameterService.updateParmeter(tbl_parameter);
		TableBean tableBean = new TableBean();
		if (upPar > 0)
		{
			tableBean.setMsg("1");
			System.out.println("参数修改成功！");
		} else
		{
			System.out.println("参数修改失败！");
		}
		return tableBean;
	}


}
