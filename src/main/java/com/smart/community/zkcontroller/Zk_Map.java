package com.smart.community.zkcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Zk_Map
{
	@RequestMapping("/mapJsp")
	public String mapJsp(){
		return "zk_desk_map";
	}
}
