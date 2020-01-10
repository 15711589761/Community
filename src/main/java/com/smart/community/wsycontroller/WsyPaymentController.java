package com.smart.community.wsycontroller;

import com.smart.community.tool.LjmTool;
import com.smart.community.wsyaspects.Log;
import com.smart.community.wsyjavabean.TableBean;
import com.smart.community.wsyjavabean.Tbl_payment;
import com.smart.community.wsyjavabean.Wsy_EcharsBean;
import com.smart.community.wsyservice.WsyPaymentService;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class WsyPaymentController
{
	@Resource
	private WsyPaymentService wsyPaymentService;

	@RequestMapping("/payment.action")
	@Log(operationType="出纳员",operationName="查看支出表")
	public ModelAndView owner(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_payment_list");
	}

	@RequestMapping("/paymentJSP.action")
	@Log(operationType="出纳员",operationName="查看统计")
	public ModelAndView paymentJSP(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("wsy_psyment_count");
	}

	@RequestMapping(value = "/paymentTable.action")
	@ResponseBody
	@Log(operationType="出纳员",operationName="查看支出记录")
	public TableBean paymenttable(int page,String payment_type, String payment_time, String payment_remarks)
	{


		TableBean tableBean = wsyPaymentService.findByPayment(page,payment_type,payment_time,payment_remarks);

		return tableBean;
	}

	@RequestMapping(value = "/deletePayment.action")
	@ResponseBody
	@Log(operationType="出纳员",operationName="删除付款记录")
	public TableBean deletePayment(int payment_id){
		int delreceivable = wsyPaymentService.delPayment(payment_id);
		TableBean tableBean = new TableBean();
		if (delreceivable > 0)
		{
			tableBean.setMsg("1");
			System.out.println("删除收款记录成功");
		} else
		{
			System.out.println("删除收款记录失败");
		}
		return tableBean;

	}

   //增加付款记录
   @RequestMapping(value = "/addPayment.action")
	@ResponseBody
	@Log(operationType="出纳员",operationName="增加付款记录")
	public TableBean addPayment(Tbl_payment tbl_payment){
		System.out.println("进入增加付款");
		tbl_payment.setPayment_time(LjmTool.getTodayDate());
	  // System.out.println(LjmTool.getTodayDate());
	   TableBean tableBean = new TableBean();


		int add_payment = wsyPaymentService.addPayment(tbl_payment);
		if (add_payment > 0){
			tableBean.setMsg("1");
			System.out.println("增加付款成功");
		}else {
			System.out.println("增加付款失败");
		}

      return tableBean;

	}
	//修改付款记录
	@RequestMapping(value = "/updatePayment.action")
	@ResponseBody
	@Log(operationType="出纳员",operationName="修改付款记录")
	public TableBean updatePayment(Tbl_payment tbl_payment){

		int update_Payment = wsyPaymentService.updatePayment(tbl_payment);
		TableBean tableBean = new TableBean();
		if (update_Payment > 0){
			tableBean.setMsg("1");
			System.out.println("修改付款记录成功！");
		}else {
			System.out.println("修改付款记录失败！");
		}
		return tableBean;
	}

   //支出统计
   @RequestMapping(value = "paymentCount.action")
   @ResponseBody
   @Log(operationType="出纳员",operationName="查看收款统计")
   public List<Wsy_EcharsBean> collectionCount(){
	   return wsyPaymentService.payMeneyCount();
   }



}
