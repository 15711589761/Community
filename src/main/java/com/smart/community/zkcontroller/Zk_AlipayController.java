package com.smart.community.zkcontroller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.smart.community.ljmbean.OwnerBean;
import com.smart.community.wsyaspects.Log;
import com.smart.community.zkbean.Zk_BillBean;
import com.smart.community.zkservice.Zk_BillService;
import com.smart.community.tool.LjmTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class Zk_AlipayController
{
	//应用ID
	private final String APP_ID = "2016101600697986";
	//应用私钥
	private final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJ+3thfejPU2n8jBkfDH1w7wu0Zq35k78tigookoSsiAeb0BoH/vLGWdZvvTHG28UneTf+V6ZUtyOPAC6gYF50vlXizAXfvGb+ygWtzqfUnxINO+wrnb5P+JajRK79wEHtZ6hphHQH8d3V5bFaKvSHkwYc5m6W88Xlh7pdXv3yoob5tKd8MefaeGb21kqG7odCpX98K+jCBXF4hZDw+bREDqhL+USrRzpJKngzdJIGS1TjnUTtMHh3oPXuo1rBKIfoaJKGpv+9Qg+PDkzoqi0rRImytzBYcJwYDVwfbZqmOHKEndosBo6HeDP+ba0hjp2C6GbJ5JtOh056xRQs2SfFAgMBAAECggEANf7RY29QIYG04f04/2t10zHYVLoxro7gq0Nvgj0D6jg5MgIXjbV476HMRmG1DuqFllLbVI6cKl6YbadWl6/ehrTNCzlnuwwRCfeojUFnb87n04H1g4PxK35OBIomKGqJ4gtEXjZU6Z7+fmuqIWzZ0Av6wmhyMmQmtxGMHmnD2qSnsi170x5RE4rqaMA37jGQu9CiuJIe0GeK5Mvq6P8ifdN0B+x3bqWZNRSdHCM37bUJMFbEEkQuepQvUMpYkxL9zo8saczZHmwS70Gk/BvSkuUthCUwIDMQ36FsZ6MG3BHYdgfwA2VlOXZnKB0dq0T6x4LnPEHiu6lKYCs/lsHx6QKBgQDL++/cudbfSqRVJ0VCpW/YURJNnXQ9k8DVJYBCH61QfaqZ1Py624zU8EWifVrrJ6pk27urwV2o3xAH6aseyw4Tjl+K+t1/94M0qHp4IN/99QivufHz0Jp/OkAGVQjYXAmPGbZnZN9pc4/d7569PWZqc0EBT6VHf74JqvIc8BsCgwKBgQCtKvSqfbzLu4eq1tPDmXMI99R9+y2LA2bGuYdG+4Wm55yYqaaE8vRaa4rLcTh+6YT6mRnLEjoZDm1+lwK4w0yJI4sbJB7qm5bflDg7vOa573akRmdnABu8pv9ihGKT8S0Q+hpRBncOFypZ8hji6SQ+/qrnErPEq2O7GssK3Kj6FwKBgQCLouBVCgaUfmU2QZbBA70nqH/OD+4lxQJpJq3BFiwvgIcLnWiQhYDHt98DV6qodVPIjT7QWaNC2cgAkq3oe3ocWwb+fbKpSgdQQdDscRzEnTugB7sge3l3hKmVbBqM+Zw1kJVz+/KQHmKqu8C/rMtyLYmfWyU6Z7Dwr0vkEV17/wKBgCkHjID42crL3BE2ZWZq7T6qucY/xUKyw59CRjMjVT9+kTP6vmGxzwO9h108rk4jtkZKEDp1Tx6wtHDSBusQGwpDSapcnJap1s2SqOA+mcxkWJADvmxBMlE4oDoPc+vpsOvc1m9sQLkt8/Lr0cmxw8zJOmUVoVJv6Wljp8gjEEZxAoGAUtl/XNKFOgEJkqpfgBlc6zEuiy3hVGzfWAmuXLg6Yot0D1K3GBQqR/E+QgdU5SXWrhSVKYjZ2toeiMXOyD6nIqTWKF9uDmNmckozggcm1gIe2RgqRe03M/goxubIaiIgsQzF1fIi7PlBN8iO7dUpCySWBglK3Anhx7SgTfgIfXw=";
	private final String CHARSET = "UTF-8";
	//支付宝公钥
	private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkZvAGNZRBh8o0Vwi5VD71hm5MZ1rpalqL6FyTnksOLDEwf/jIvCg6DCsXC0vnmoajL0wKEzJ//8hrstVrhX8d9PH42kHXRr0dxLMmzicKBAX9nChwioHM8k05ZGOX47Kh2QRHtWKBhpUr81YrZ87Qs90WrGAy2yB+bJJDAkWawVVEVvp/po3yd5ieDeppaxJT8fGKfEKgeWfLqbgUyUuud/qlDDmuq4ic5b7AQBROe/KKYgB2mwzkCAJsEzVAzHnGpjAALWkwOL6jULj/b+/ib7XdcKEKIyJliQkYl7qhLDrA8Vv0trjT8QSYwmumgV1ihFnWMC2eQKKx9HovsDk4wIDAQAB";
	//这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
	private final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
	private final String FORMAT = "JSON";
	//签名方式
	private final String SIGN_TYPE = "RSA2";
	//支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
	private final String NOTIFY_URL = "http://公网地址/notifyUrl";
	//支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
	private final String RETURN_URL = "http://localhost:8080/Community/successJsp";
	@Resource
	private Zk_BillService zk_billService;

	@RequestMapping("/payforJsp")
	public String payforJsp()
	{
		return "zk_parkingcard";
	}

	@RequestMapping("/propertycosts.view")
	@Log(operationType="AAA",operationName="业主缴纳物业费")
	public ModelAndView owner()
	{
		return  new ModelAndView("wsy_costs");
	}


	//生成订单界面
	@RequestMapping("/alipay")
	@ResponseBody
	public void alipay(HttpServletResponse httpResponse, String money) throws IOException
	{
		//实例化客户端,填入所需参数
		AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();



		//根据订单编号,查询订单相关信息
		//Order order = payService.selectById(orderId);
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = LjmTool.getPreciseTime();
		//付款金额，必填
		String total_amount = money;
		//订单名称，必填
		String subject = "停车卡缴费";
		String subject1=java.net.URLEncoder.encode(subject,"utf-8");
		String url="http://localhost:8080/Community/successJsp?subject1="+subject1;
		//在公共参数中设置回跳和通知地址
		request.setReturnUrl(url);
		request.setNotifyUrl(NOTIFY_URL);
		//商品描述，可空
		String body = "";
		request.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\"," + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		String form = "";
		try
		{
			form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
		} catch (AlipayApiException e)
		{
			e.printStackTrace();
		}
		httpResponse.setContentType("text/html;charset=" + CHARSET);
		httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}



	@RequestMapping("/successJsp")
	@ResponseBody
	public ModelAndView returnUrl(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,String subject1)throws IOException, AlipayApiException
	{
		List<OwnerBean> ownerBeans= (List) request.getSession().getAttribute("owners");
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); // 调用SDK验证签名
		//验证签名通过
		System.out.println(subject1);
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

			//添加票据
			Zk_BillBean zk_billBean=new Zk_BillBean();
			zk_billBean.setOrderNo(out_trade_no);
			zk_billBean.setPayee("物业管理中心");
			String owner=null;
			for (int i = 0; i < ownerBeans.size(); i++)
			{
				if (ownerBeans.get(i).getOwnerAffilation().equals("是")){
					owner=ownerBeans.get(i).getOwnerName();
					zk_billBean.setPayer(owner);
				}
			}
			zk_billBean.setDetails(subject1);
			zk_billBean.setBillDate(LjmTool.getTodayDate());
			zk_billBean.setMoney(total_amount);
			zk_billService.addBill(zk_billBean);

			//支付成功，修复支付状态
			//payService.updateById(Integer.valueOf(out_trade_no));
			ModelAndView mv = new ModelAndView();
			mv.addObject("out_trade_no", out_trade_no);
			mv.addObject("trade_no", trade_no);
			mv.addObject("total_amount", total_amount);
			mv.addObject("subject1", subject1);
			mv.addObject("flag", "success");
			mv.setViewName("zk_alipaySuccess");
			return mv;
	}


	//生成订单界面
	@RequestMapping("/wyalipay")
	@ResponseBody
	public void wyalipay1(HttpServletResponse httpResponse, String money) throws IOException
	{
		System.out.println("物业支付宝进入");
		//实例化客户端,填入所需参数
		AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		//在公共参数中设置回跳和通知地址
		//订单名称，必填
		String subject = "物业缴费";
		String subject1=java.net.URLEncoder.encode(subject,"utf-8");
		String url="http://localhost:8080/Community/successJsp?subject1="+subject1;

		request.setReturnUrl(url);
		request.setNotifyUrl(NOTIFY_URL);

		//根据订单编号,查询订单相关信息
		//Order order = payService.selectById(orderId);
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = LjmTool.getPreciseTime();
		//付款金额，必填
		String total_amount = money;

		//商品描述，可空
		String body = "";
		request.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\"," + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		String form = "";
		try
		{
			form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
		} catch (AlipayApiException e)
		{
			e.printStackTrace();
		}
		httpResponse.setContentType("text/html;charset=" + CHARSET);
		httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}
}
