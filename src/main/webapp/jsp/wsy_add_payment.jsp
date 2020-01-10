<%--
  Created by IntelliJ IDEA.
  User: 魏书源
  Date: 2019/12/19
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>修改参数</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href='<%=path+"layui/css/layui.css"%>'>
	<script src='<%=path+"layui/layui.js"%>'></script>

</head>
<body>


<div class="layui-form" lay-filter="layui-btn-normal" id="layui-btn-normal" style="padding: 20px 0 0 0;">
	<div class="layui-form-item" style="text-align: center">
		<label class="layui-form-label">付款人:</label>
		<div class="layui-input-inline">
			<input type="text" name="payment_name" id="payment_name" lay-verify="required" placeholder="请输入内容"
			       autocomplete="off" class="layui-input">
		</div>
	</div>


	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">付款金额:</label>
		<div class="layui-input-inline">
			<input type="text" name="payment_money" id="payment_money" lay-verify="required" placeholder="请输入付款金额"
			       autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">付款类型:</label>
		<div class="layui-input-inline">
			<input type="text" name="payment_type" id="payment_type" lay-verify="required" placeholder="请输入付款金额"
			       autocomplete="off" class="layui-input">
		</div>
	</div>

<%--	<div class="layui-form-item layui-form-text">--%>
<%--		<label class="layui-form-label">付款时间:</label>--%>
<%--		<div class="layui-input-inline">--%>
<%--			<input type="text" name="payment_time" id="payment_time" lay-verify="required" placeholder="请输入付款金额"--%>
<%--			       autocomplete="off" class="layui-input">--%>
<%--		</div>--%>
<%--	</div>--%>

	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">备注:</label>
		<div class="layui-input-inline">
			<input type="text" name="payment_remarks" id="payment_remarks" lay-verify="required" placeholder="请输入付款金额"
			       autocomplete="off" class="layui-input">
		</div>
	</div>



	<div class="layui-form-item layui-hide" style="text-align: center">
		<input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
	</div>
</div>

<script src="<%=path+"layui/layui.js"%>"></script>
<script>
	layui.use('form', function () {
		var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功


		form.render();
	});
</script>

</body>
</html>
