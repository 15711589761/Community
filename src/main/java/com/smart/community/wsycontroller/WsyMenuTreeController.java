package com.smart.community.wsycontroller;

import com.smart.community.wsyaspects.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WsyMenuTreeController
{


	@RequestMapping("/wsy_menu.action")
	@Log(operationType="SD",operationName="查看菜单")
	public ModelAndView owner(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_menuTree");
	}

}
