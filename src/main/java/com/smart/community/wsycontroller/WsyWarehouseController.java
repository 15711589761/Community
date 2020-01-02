package com.smart.community.wsycontroller;

import com.smart.community.wsyaspects.Log;
import com.smart.community.wsydao.WsyWarehouseMapper;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_safeEvent;
import com.smart.community.wsyjavabean.Tbl_warehouse;
import com.smart.community.wsyservice.WsyWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WsyWarehouseController
{
	@Resource
	private WsyWarehouseService wsyWarehouseService;


	@RequestMapping("/library.action")
	@Log(operationType="仓库管理员",operationName="查看仓库信息表")
	public ModelAndView owner(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_warehouse");
	}

	@RequestMapping(value = "/warehouse.action")
	@ResponseBody
	@Log(operationType="仓库管理员",operationName="查询仓库库存信息")
	public TableBean safeEvent(int page, String manifest_number, String manifest_name)
	{
        TableBean tableBean = wsyWarehouseService.WarehouseTable(page,manifest_number,manifest_name);

		return tableBean;
	}

	//增加仓库物品
	@RequestMapping(value = "/addwarehouse.action")
	@ResponseBody
	@Log(operationType="仓库管理员",operationName="商品入库")
	public TableBean addwarehouse(Tbl_warehouse tbl_warehouse)
	{


		int addwh = wsyWarehouseService.addWarehouse(tbl_warehouse);

		TableBean tableBean = new TableBean();
		if (addwh > 0)
		{
			tableBean.setMsg("1");
			System.out.println("货物添加成功！");
		} else
		{
			System.out.println("货物添加失败！");
		}

		return tableBean;

	}

	//入库
	@RequestMapping(value = "/updatewarehouse.action")
	@ResponseBody
	@Log(operationType="仓库管理员",operationName="增加仓库商品数量")
	public TableBean updateWare(HttpServletRequest request,int manifest_id,Tbl_warehouse tbl_warehouse)
	{

		String quantity = request.getParameter("manifest_quantity");//获取入库的数量
		System.out.println("入库的数量.."+quantity);
		int addwarnum = wsyWarehouseService.addNumWarehouse(manifest_id);//获取原来的数量
		System.out.println("原来的数量.." + addwarnum);

		int num = Integer.valueOf(quantity);
		int numSum = addwarnum + num;//计算总和
		System.out.println("总数量.." + numSum);
		TableBean tableBean = new TableBean();
		tbl_warehouse.setManifest_id(manifest_id);
		tbl_warehouse.setManifest_quantity(numSum);
		int updateNum = wsyWarehouseService.updateWarehouse(tbl_warehouse);

		//总数大于0时，货物增加成功，否则失败
		if (numSum > 0)
		{

			tableBean.setManifest_quantity(1);
			System.out.println("货物数量增加成功");
		} else
		{
			System.out.println("货物数量增加失败！");
		}
		return tableBean;
	}

        //出库
		@RequestMapping(value = "/lesswarehouse.action")
		@ResponseBody
		@Log(operationType="仓库管理员",operationName="商品出库")
		public TableBean lessWare(HttpServletRequest request,HttpServletResponse response ,int manifest_id,Tbl_warehouse tbl_warehouse){

			String quantity = request.getParameter("manifest_quantity");//获取入库的数量
			System.out.println("出库的数量.."+quantity);
			int addwarnum = wsyWarehouseService.addNumWarehouse(manifest_id);//获取原来的数量
			System.out.println("原来的数量.."+addwarnum);

            int num = Integer.valueOf(quantity);
			int numless = addwarnum - num;//计算总和
			System.out.println("出库后剩余的数量.."+numless);
			tbl_warehouse.setManifest_id(manifest_id);
			tbl_warehouse.setManifest_quantity(numless);
            int updateNum = wsyWarehouseService.updateWarehouse(tbl_warehouse);


			TableBean tableBean = new TableBean();


			//总数大于0时，货物增加成功，否则失败
			if (numless > 0){

				tableBean.setManifest_quantity(1);
				System.out.println("货物数量出库成功");
			}else {
				System.out.println("货物数量出库失败！");
			}
			return tableBean;

	}



}
