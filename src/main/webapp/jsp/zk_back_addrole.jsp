<%--
  Created by IntelliJ IDEA.
  User: ZKa1
  Date: 2019/12/18
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = application.getContextPath();
%>
<html>
<head>
	<title>添加设备</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%>>
	<script src=<%=path+"/layui/layui.js"%> charset="utf-8"></script>
</head>
<body>
<form class="layui-form layui-form-pane" action="">
	<div class="layui-form-item">
		<label class="layui-form-label">角色名称</label>
		<div class="layui-input-block">
			<input type="text" name="name" id="roleName" lay-verify="required" autocomplete="off" placeholder="请输入角色名称" class="layui-input">
		</div>
	</div>
</form>
</body>
</html>
