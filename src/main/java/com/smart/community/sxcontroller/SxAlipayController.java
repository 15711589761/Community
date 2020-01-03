package com.smart.community.sxcontroller;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class SxAlipayController
{
	final static Logger log = LoggerFactory.getLogger(SxAlipayController.class);
	private final String APP_ID = "2016101600698047";
	private final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCMdUEDVFUk1np5hAuxg1eaRRt7wYr5+t+jl+qwmbC+GD1J698aEj4DgqQyFbn0bI4hy5izxe7HrkFgLKfPGTN5RIg8vE7R4boz4fmLMZE2i/bWYtD5Dk+rTKQvkWZhHfJ/xO+vYzzNQurjKy/Qkq4ZxQHOn+yieKB3q0hf2qCM1PEv4t3N9EbsyLgm4p2SkChZK8S3jFFbaqCydu7R6DsN9la5qayKnfEErkFtuo0nq/HJdiM57fN8RVU4vHAbpNuZgP/G5RWJhvMaccTSEqALazmQH0J7KIKuxqMEcQWJCxu4yn5lIV7F7LbWhGWvK+rSAK/hZXcQWdWgJRy6QRH5AgMBAAECggEAGUUzSDURCIqqCFjQfcSCq1fJpQ9iQK15NQatC87q9JiJ1lXgkuE7VQhZIEW2ahppy/zEYXu4BSb9zh7apjEDiMritAKxL6OsC4+ZhQiK9sRhyZqHvHdtsAUe+Qvw3oIz0fcNpIF3O/rRJcPsW1s4hZxjQdiWC3kmbcFcSRqdjhiw99Kn8SiNx15Wh7wFN41ct+ngr7eFudMcxrz3IwXI0VUp3kMxVo7SYBESE7o/51a99/084L5A2U0wIU9lSQyDKr2aneexbRbd92B/HFpIVhUlXaDXUwS1zWcoWXiUImrIJ0bw09M+Q8Opctvse/PJ+9oQF2E5x7ck7H5Ws7AlgQKBgQDWeNnz8UJqWBpDhlYe5Wz4UaO7J0b8Be1eqXavyF6MNRskEP75ZjnUdu0ooO4/57AX2Xf+yIpY5xfrC0qGGr/6mCAFqfxcLiO6FGYOFP8L5O1kx/7wyosia9e7x84E3oFvtcw0RwQbmMovbO+PbSnSlzM8AKHLYkwMR6G8pucckQKBgQCnp5sqyI62Th3b/nBhKHIwloKeQKZ7ieHxVjv6KzE74d3t3h5+6OXtB5xx3WZ2E5He9WqHZ3xdIJB0AIM0h4J0xKgjs3ivhsLAhQawGR/lV+V41qsfIH5EclSKQTKcYYYe3TabWGh4ELdVtBvaaE0hgh+s3GpH8EwVpdJxuffy6QKBgQCUU96A3LFPNQFtA+JZ2dgkYOn4zLBGKp9A4bjI1620ECJmLi75M175dwqMF8jOf6EEzY00s2MjGygqkZOH7cYqPL2gzTBQXESvM95lDNZbVodsNPk3dQ0G0EjSgNwV+ADSzouc+CR6JZBnflKsAhZzo8h2TgWX0LLBp2TPQSsDUQKBgCMdCXSWeL1Z8wsGaUcQD4p5m3XFmJi7MiU3qGwm67LdBXwyTzTJVo6kT3HRqwoD40NNJzigxf1DOr3w9Ynk77/BCG4hwp8QnG1x7wqm2HVSSW9blo94YLm5MFecfBsgmdSRBETY6VhsN9mtVUATJERmWnf72g6jF2xZRJApIPMxAoGAH7I2jpe6pDjziE+5yAGEvMRJ54H8sosllA7oVa0bDJNdvg8cvk0knFM8qqDrl/odwNqQQ1q/v8wgV/W6LnTa7dXxGYxRfa+m2GWpcIalQOvgZROdGTYC+pWeL6cWicPzYZH/ibD92XPBmY8B474AaYK0b7d2+B+QqVm0t6XZ+5Q=";
	private final String CHARSET = "UTF-8";
	private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkql2mOCgkCCOZVG1KFv6rsxDx1D0dhaopK59vd4FIgDczbj2COf1jEWdhSdbkStoLgkTml+gCoOsuM6WjwBbYP10NM2VT9JGZZhhEBWcoQXxiO77b22yF7AH77ywv9wni8qYYjkQZI/z/azza85Hx4t4nLuUbYs5z6r5SPfnLkNffMqXJYcWMBiqsi23OnogUSae7mcwosuce1qN9OGJqJO2cQ8d3F+zBPMot85nbwa0wCVxwG0uwvd9h80+gQdgRskW+q75aBtmshj78Ayddpu52bLWgzz5hN8BINxCkurWJipft1DPjRSZaeyWFAp95JgB1jKbA3vNyCV+kjlYSQIDAQAB";
	//这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
	private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
	private final String FORMAT = "JSON";
	//签名方式
	private final String SIGN_TYPE = "RSA2";
	//支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
	private final String NOTIFY_URL = "http://公网地址/notifyUrl"; //上线采用
	//支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
	private final String RETURN_URL = "http://localhost:8080/demo5/returnUrl"; //成功后返回路径


	@RequestMapping("alipay.action")
	public void alipay(Integer orderId,HttpServletResponse httpResponse) throws IOException {
		//实例化客户端,填入所需参数
		AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		//在公共参数中设置回跳和通知地址
		request.setReturnUrl(RETURN_URL);
		request.setNotifyUrl(NOTIFY_URL);
		//根据订单编号,查询订单相关信息
//		Order order = payService.selectById(orderId);
		//商户订单号，商户网站订单系统中唯一订单号，必填
//		String out_trade_no = order.getOrderId().toString();
//		//付款金额，必填
//		String total_amount = order.getOrderPrice().toString();
//		//订单名称，必填
//		String subject = order.getOrderName();
		//商品描述，可空
		String body = "";
		request.setBizContent("{\"out_trade_no\":\""+ 2 +"\","
				+ "\"total_amount\":\""+ 500000 +"\","
				+ "\"subject\":\""+ "顽皮的勺子" +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		String form = "";
		try {
			form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		httpResponse.setContentType("text/html;charset=" + CHARSET);
		httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}



}

