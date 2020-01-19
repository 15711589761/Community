package com.smart.community.wsycontroller;

import com.smart.community.tool.LjmTool;
import com.smart.community.wsyaspects.Log;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_account;
import com.smart.community.wsyjavabean.Tbl_fire_tools;
import com.smart.community.wsyjavabean.Wsy_EcharsBean;
import com.smart.community.wsyservice.WsyAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class WsyAccountController
{
	@Resource
	private WsyAccountService wsyAccountService;

	@RequestMapping(value = "accountJSP.action")
	@Log(operationType="出纳员",operationName="查看对账表")
	public ModelAndView accountView(){
		return new ModelAndView("wsy_account");
	}

	@RequestMapping(value = "/accountTable.action")
	@ResponseBody
	@Log(operationType="出纳员",operationName="查看对账记录")
	public TableBean logtable(int page,String startMonry, String endMoney, String accountMoney,String startDate, String endDate, String accountType, String accountRemarks)
	{
		TableBean tableBean = wsyAccountService.findByAccount(page,startMonry,endMoney,accountMoney,startDate,endDate,accountType,accountRemarks);

		return tableBean;
	}

//	@RequestMapping(value = "addAcountion.action")
//	@ResponseBody
//	@Log(operationType="AAA",operationName="增加账单")
//	public TableBean addAcount(Tbl_account tbl_account){
//		tbl_account.setAccountTime(LjmTool.getTodayDate());
//		int addAcount = wsyAccountService.addAcount(tbl_account);
//		TableBean tableBean = new TableBean();
//		if (addAcount > 0){
//			tableBean.setMsg("1");
//			System.out.println("账单增加成功！");
//		}else {
//			System.out.println("账单增加失败！");
//		}
//		return tableBean;
//	}


	//删除
	@RequestMapping(value = "/deleteAccount.action")
	@ResponseBody
	@Log(operationType="出纳员",operationName="删除对账记录")
	public TableBean deleteCollection(int accountId){
		int delreceivable = wsyAccountService.delAccount(accountId);
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

	@RequestMapping(value = "accountCount.action")
	@ResponseBody
	@Log(operationType="出纳员",operationName="查看对账统计")
	public List<Wsy_EcharsBean> accountCount(){
		return wsyAccountService.accountCountMap();
	}




}
