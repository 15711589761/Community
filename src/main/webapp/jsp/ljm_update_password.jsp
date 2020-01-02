<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/31
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>修改员工密码</title>
	<link rel="stylesheet" href=<%=path+"bootstrap/css/bootstrap.css"%>>
	<script type="text/javascript" src=<%=path + "js/jquery-3.4.1.min.js"%>></script>
	<script type="text/javascript" src=<%=path + "bootstrap/js/bootstrap.js"%>></script>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
				<br>
				<br>
				<div class="form-inline">
					<label for="old_password">旧&ensp;密&ensp;码：</label><input type="password" class="form-inline" name="oldPass" id="old_password">
				</div>
				<br>
				<br>
				<div class="form-inline">
					<label for="new_password">新&ensp;密&ensp;码：</label><input type="password" class="form-inline" name="newPass" id="new_password">
				</div>
				<br>
				<br>
				<div class="form-inline">
					<label for="repeat_password"> 确认密码：</label><input type="password" class="form-inline" name="repeatPass" id="repeat_password"/>
				</div>
		</div>
	</div>
</div>
</body>
</html>
