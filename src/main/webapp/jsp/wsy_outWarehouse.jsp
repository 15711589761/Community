<%--
  Created by IntelliJ IDEA.
  User: wsy
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
	<title>添加参数</title>
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
		<label class="layui-form-label">货物单号:</label>
		<div class="layui-input-inline">
			<input type="text" name="manifest_number" id="manifest_number" lay-verify="required" placeholder="请输入货物单号"
			       autocomplete="off" class="layui-input">
		</div>
	</div>


	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">货物名称:</label>
		<div class="layui-input-inline">
			<input type="text" name="manifest_name" id="manifest_name" lay-verify="required" placeholder="请输入参货物名称"
			       autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">货物数量:</label>
		<div class="layui-input-inline">
			<input type="text" name="manifest_quantity" id="manifest_quantity" lay-verify="required" placeholder="请输入货物数量"
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
