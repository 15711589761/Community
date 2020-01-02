<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<script type="text/javascript" src=<%=path + "js/md5.js"%>></script>
	<script type="text/javascript" src=<%=path + "bootstrap/js/bootstrap.js"%>></script>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<form role="form" action="<%=path%>updateDeskPassWord.action" onsubmit="return updateSub()">
				<br>
				<br>
				<div class="form-inline">
					<label for="old_password">旧&ensp;密&ensp;码：</label><input type="password" class="form-inline"
					                                                         name="oldPass" id="old_password">
				</div>
				<br>
				<br>
				<div class="form-inline">
					<label for="new_password">新&ensp;密&ensp;码：</label><input type="password" class="form-inline"
					                                                         name="newPass" id="new_password">
				</div>
				<br>
				<br>
				<div class="form-inline">
					<label for="repeat_password">确认密码：</label><input type="password" class="form-inline"
					                                                  name="repeatPass"
					                                                  id="repeat_password"/>
				</div>
				<br>
				<br>
				<div>
					<button class="btn btn-danger">确认修改</button>
				</div>
			</form>
			<c:if test="${requestScope.message!=null}">
				<div class="alert alert-warning alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<strong>提示！</strong> ${requestScope.message}
				</div>
			</c:if>
		</div>
	</div>
</div>
<script>
	function updateSub() {
		var op = $('#old_password').val();
		var np = $('#new_password').val();
		var rp = $('#repeat_password').val();
		if (op.length>0&&np.length>0&&rp.length>0)
		{
			if (rp === np)
			{
				$('#old_password').val(md5(op));
				$('#new_password').val(md5(np));
				return true;
			} else {
				alert('两次输入密码不同');
			}
		} else {
			alert('请补全信息');
		}
		return false;
	}
</script>
</body>
</html>
