package com.smart.community.wsycontroller;

import com.smart.community.tool.LjmTool;
import com.smart.community.wsyaspects.Log;
import com.smart.community.wsyjavabean.*;
import com.smart.community.wsyservice.WsyCollectionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class WsyCollectionController
{
	@Resource
	private WsyCollectionService wsyCollectionService;

	//收款页面
	@RequestMapping("/collection.action")
	@Log(operationType="出纳员",operationName="查看收入表")
	public ModelAndView owner(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_collection_list");
	}

	//收款统计饼图
	@RequestMapping("/collectionCountJSP.action")
	@Log(operationType="出纳员",operationName="查看收入统计图")
	public ModelAndView collectionCount(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_collection_Count");
	}



	@RequestMapping(value = "/collectionTable.action")
	@ResponseBody
	@Log(operationType="出纳员",operationName="查看收入记录")
	public TableBean logtable(int page, String receivables_type, String receivables_time, String receivables_remarks)
	{


		TableBean tableBean = wsyCollectionService.findByCollection(page,receivables_type,receivables_time,receivables_remarks);

		return tableBean;
	}

	//删除
	@RequestMapping(value = "/deleteCollection.action")
	@ResponseBody
	@Log(operationType="出纳员",operationName="删除收款记录")
	public TableBean deleteCollection(int receivables_Id){
		int delreceivable = wsyCollectionService.delReceivables(receivables_Id);
		//int delAccount = wsyCollectionService.delAccount(accountId);
		TableBean tableBean = new TableBean();
		if (delreceivable > 0)
		{
			tableBean.setMsg("1");

		} else
		{

		}
		return tableBean;

	}
	//增加收款记录
	@RequestMapping(value = "/addCollection.action")
	@ResponseBody
	@Log(operationType="出纳员",operationName="增加收款记录")
	public TableBean addCollection(Tbl_receivables tbl_receivables,Tbl_account tbl_account){

		tbl_receivables.setReceivables_time(LjmTool.getTodayDate());
		tbl_account.setAccountTime(LjmTool.getTodayDate());
	    TableBean tableBean = new TableBean();
        int add_Collection = wsyCollectionService.addCollection(tbl_receivables);//收入表
		int add_Account = wsyCollectionService.addAcount(tbl_account);//对账
		if (add_Collection > 0 && add_Account > 0){
			tableBean.setMsg("1");

		}else {

		}

		return tableBean;

	}
	//修改收款记录
	@RequestMapping(value = "/updateCollection.action")
	@ResponseBody
	@Log(operationType="出纳员",operationName="修改收款记录")
	public TableBean updateCollection(Tbl_receivables tbl_receivables){

		int update_Collection = wsyCollectionService.updateCollection(tbl_receivables);
		//int updateAccount = wsyCollectionService.updateAccount(tbl_account);
		TableBean tableBean = new TableBean();
		if (update_Collection > 0 ){
			tableBean.setMsg("1");

		}else {

		}
		return tableBean;
	}

	//收款统计
	@RequestMapping(value = "collectionCount.action")
	@ResponseBody
	@Log(operationType="出纳员",operationName="查看收款统计")
	public List<Wsy_EcharsBean> collectionCount(){
		return wsyCollectionService.collectionCountMap();
	}




}
