package com.smart.community.wsycontroller;

import com.smart.community.wsyaspects.Log;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_owner;
import com.smart.community.wsyservice.WsyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WsyController
{
	@Resource
	private WsyService wsyService;

	private String base1=null;

	@RequestMapping("/owner.action")
	@Log(operationType="AAA",operationName="查看业主表")
	public ModelAndView owner(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("ownerlist");
	}

	@RequestMapping("/add_owner.action")
	@Log(operationType="AAA",operationName="查看业主表")
	public ModelAndView add_owner(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_addowner");
	}




	//获取业主表
	@RequestMapping(value = "/ownerlist.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="查询业主信息")
	public TableBean ownerTable(int page, String owner_name, String owner_tel, String owner_status, String owner_identity)
	{


		TableBean tableBean = wsyService.findByOwner(page, owner_name, owner_tel, owner_status, owner_identity);

		return tableBean;
	}

	//用id删除业主
	@RequestMapping(value = "/delOwner.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="删除业主")
	public TableBean delOwner(int owner_id)
	{
        int ownerId = wsyService.delOwner(owner_id);
		TableBean tableBean = new TableBean();
		//判断如果 ownerId>0 时向界面发送“1”删除成功，“2”为失败
		if (ownerId > 0)
		{
			tableBean.setMsg("1");
			System.out.println("删除成功");
		} else
		{
			tableBean.setMsg("2");
			System.out.println("删除失败");
		}


		return tableBean;
	}


	//录入业主人脸
	@RequestMapping("/addOwnerList.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="录入业主人脸")
	public int babyface(String base) {
		base1 = base;

		System.out.println("base"+base);
		if(base1 != null){
			int flag=1;
			return flag;
		}

		return 0;
	}
	//增加业主
	@RequestMapping(value = "addOwner.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="增加业主")
	public TableBean addOwner(Tbl_owner tbl_owner)
	{
		System.out.println("base。。。"+base1.getBytes());
        tbl_owner.setSavePhotos(base1.getBytes());
        int ownerAdd = wsyService.addOwner(tbl_owner);
        TableBean tableBean = new TableBean();
		if (ownerAdd > 0)
		{
			tableBean.setMsg("1");
			System.out.println("添加业主成功");
		} else
		{
			System.out.println("添加业主失败");
		}
		return tableBean;
	}

	//修改业主信息
	@RequestMapping(value = "updateOwner.action")
	@ResponseBody
	@Log(operationType="AAA",operationName="修改业主信息")
	public TableBean updateOwner(Tbl_owner tbl_owner)
	{
		int upOwner = wsyService.updateOwner(tbl_owner);
		TableBean tableBean = new TableBean();
		if (upOwner > 0)
		{
			tableBean.setMsg("1");
			System.out.println("修改成功");
		} else
		{
			System.out.println("修改失败");
		}


		return tableBean;
	}


}
