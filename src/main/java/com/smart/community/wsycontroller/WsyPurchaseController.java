package com.smart.community.wsycontroller;

import com.smart.community.activiti.Activiti;
import com.smart.community.ljmbean.LayuiTableBean;
import com.smart.community.ljmbean.RoleBean;
import com.smart.community.ljmbean.StaffBean;
import com.smart.community.ljmservice.ActivitiService;
import com.smart.community.ljmservice.LjmBackstageLoginService;
import com.smart.community.tool.LjmTool;
import com.smart.community.wsyaspects.Log;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyservice.WsyPurchaseService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WsyPurchaseController
{
	@Resource
	private WsyPurchaseService wsyPurchaseService;
	@Resource
	private Activiti purchaseActiviti;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private LjmBackstageLoginService backstageLoginService;

	//获取采购页面
	@RequestMapping("/purchase.action")
	@Log(operationType="SQR",operationName="查看采购表")
	public ModelAndView owner(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_purchase");
	}

	//获取采购信息
	@RequestMapping(value = "/selectPurchase.action")
	@ResponseBody
	@Log(operationType="SQR",operationName="查询采购信息")
	public TableBean purchaseTable(int page, String startDate,String endDate,String purchase_name,String purchase_model)
	{

        TableBean tableBean = wsyPurchaseService.findPurchase(page, startDate, endDate, purchase_name, purchase_model);

		return tableBean;
	}

	//删除采购信息
	@RequestMapping(value = "/delPurchase.action")
	@ResponseBody
	@Log(operationType="SQR",operationName="删除采购记录")
	public TableBean delPurchase(int purchase_id){

		int delPurchaseId = wsyPurchaseService.delpurchase(purchase_id);
		TableBean tableBean = new TableBean();
		if (delPurchaseId > 0){
			tableBean.setMsg("1");
			System.out.println("采购信息删除成功！");
		}else {
			System.out.println("采购信息删除失败！");
		}
		return tableBean;

	}

	/**
	 * 创建启动流程实例
	 * @return 信息
	 */
	@RequestMapping("/createProcessesForPurchase.action")
	@ResponseBody
	@Log(operationType="采购申请",operationName="添加采购申请")
	public LayuiTableBean createProcessesPurchase ( String purchaseName , String purchaseModel , String purchaseQuantity, String purchasePrice , HttpServletRequest request)
	{
		StaffBean staffBean = (StaffBean) request.getSession().getAttribute("staffBean");
		//参数设置，在启动时传入流程
		Map<String,Object> map = new HashMap<>();
		String staff = staffBean.getStaffName();
		map.put("applyName",staff);
		map.put("purchaseName",purchaseName);
		map.put("purchaseModel",purchaseModel);
		map.put("purchaseQuantity",purchaseQuantity);
		map.put("purchasePrice",purchasePrice);
		map.put("serviceName","采购申请");
		int result = wsyPurchaseService.insertForPurchaseApply(staff ,purchaseName, purchaseModel , purchaseQuantity, purchasePrice);
		if (result > 0 )
		{
			int applyId = wsyPurchaseService.selectForGetPurchaseId(staff);
			map.put("applyId",applyId);
			ProcessInstance pi = purchaseActiviti.createActiviti("purchaseApply", "processes/PurchaseApply.bpmn","processes/PurchaseApply.png",map);
			if (pi!=null)
			{
				LayuiTableBean layuiTableBean = new LayuiTableBean();
				layuiTableBean.setMsg("添加成功");
				return layuiTableBean;
			}
		} else {
			LayuiTableBean layuiTableBean = new LayuiTableBean();
			layuiTableBean.setMsg("添加失败");
			return layuiTableBean;
		}
		return null;
	}

	/**
	 * 查找流程
	 * @param request request
	 * @return layui接口
	 */
	@RequestMapping("/findProcessesForPurchase.action")
	@ResponseBody
	@Log(operationType="采购申请",operationName="查询采购申请")
	public LayuiTableBean findOwnerProcessesForPurchase(HttpServletRequest request)
	{
		String taskName = "采购申请";
		LayuiTableBean layuiTableBean = activitiService.filterBackstageTask(request,taskName);
		layuiTableBean.setCode(0);
		return layuiTableBean;
	}

	/**
	 * 删除撤回流程
	 * @param taskId 任务id
	 * @return layui接口
	 */
	@RequestMapping("/deleteProcessesPurchaseTask.action")
	@ResponseBody
	@Log(operationType="采购申请",operationName="删除采购申请")
	public LayuiTableBean deletePersonProcessesTask(String taskId)
	{
		Map<String,Object> map = purchaseActiviti.getVariables(taskId);
		int applyId = (int) map.get("applyId");
		int result = wsyPurchaseService.delpurchase(applyId);
		LayuiTableBean layuiTableBean = new LayuiTableBean();
		if (result>0)
		{
			purchaseActiviti.deleteProcessDefinition(taskId);
			layuiTableBean.setMsg("删除成功");
		} else {
			layuiTableBean.setMsg("系统忙，请重试");
		}
		return layuiTableBean;
	}

	/**
	 * 完成任务
	 * @param taskId 任务id
	 * @return layui接口
	 */
	@RequestMapping("/submitProcessesPurchaseTask.action")
	@ResponseBody
	@Log(operationType="采购申请",operationName="修改采购申请")
	public LayuiTableBean submitStaffProcessesTask(String taskId,String feedback,HttpServletRequest request)
	{
		StaffBean staffBean = (StaffBean)request.getSession().getAttribute("staffBean");
		RoleBean roleBean = backstageLoginService.getPersonRole(staffBean.getStaffId());
		Map<String,Object> map = purchaseActiviti.getVariables(taskId);
		int applyId = (int) map.get("applyId");
		String staff = staffBean.getStaffName();
		if (LjmTool.ROLE_LOGISTICS.equals(roleBean.getRoleName()))
		{
			staff = null;
			feedback = null;
		}
		int result = wsyPurchaseService.updateForPurchaseApply(feedback,String.valueOf(applyId),staff);
		if (result>0)
		{
			purchaseActiviti.completePersonTask(taskId);
			LayuiTableBean layuiTableBean = new LayuiTableBean();
			layuiTableBean.setMsg("提交成功");
			return layuiTableBean;
		}
		return null;
	}

	@RequestMapping("/toPurchaseApply.view")
	@Log(operationType="查看",operationName="查看采购申请")
	public ModelAndView toPurchaseApply()
	{
		return new ModelAndView("ljm_purchase_apply");
	}

	@RequestMapping("/toPurchaseReview.view")
	@Log(operationType="审核",operationName="审核采购申请")
	public ModelAndView toPurchaseReview()
	{
		return new ModelAndView("ljm_purchase_review");
	}
}
