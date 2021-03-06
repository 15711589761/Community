<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>缴费成功后的购买详情界面</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%> media="all">
	<script type="text/javascript" src=<%=path + "/layui/layui.js"%>></script>
</head>
<body>
<br>
<br>
<div class="demoTable" style="text-align: center">
	<form class="layui-form layui-form-pane" action="deskLogin.action">
		<div class="layui-form-item">
			<div class="layui-input-block">
				<h1 style="color: green;">购买成功</h1>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				订单编号：${out_trade_no }
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				支付宝交易号：${trade_no }
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				实付金额：${total_amount }
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				购买产品：${subject1}
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn">返回首页</button>
			</div>
		</div>
	</form>
</div>
</body>
</html>


