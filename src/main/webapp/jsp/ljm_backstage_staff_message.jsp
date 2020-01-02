<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/31
  Time: 14:22
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
			<div class="form-inline">
				<label>编&ensp;&ensp;&ensp;&ensp;码：</label>
				<label>${requestScope.staff.staffId}</label>
			</div>
			<br>
			<div class="form-inline">
				<label>工&ensp;&ensp;&ensp;&ensp;号：</label>
				<label>${requestScope.staff.staffJobNum}</label>
			</div>
			<br>
			<div class="form-inline">
				<label>姓&ensp;&ensp;&ensp;&ensp;名：</label>
				<label>${requestScope.staff.staffName}</label>
			</div>
			<br>
			<div class="form-inline">
				<label>性&ensp;&ensp;&ensp;&ensp;别：</label>
				<label>${requestScope.staff.staffSex}</label>
			</div>
			<br>
			<div class="form-inline">
				<label>年&ensp;&ensp;&ensp;&ensp;龄：</label>
				<label>${requestScope.staff.staffAge}</label>
			</div>
			<br>
			<div class="form-inline">
				<label>电&ensp;&ensp;&ensp;&ensp;话：</label>
				<label>${requestScope.staff.staffTel}</label>
			</div>
			<br>
			<div class="form-inline">
				<label>住&ensp;&ensp;&ensp;&ensp;址：</label>
				<label>${requestScope.staff.staffAddress}</label>
			</div>
			<br>
			<div class="form-inline">
				<label>入职时间：</label>
				<label>${requestScope.staff.staffJoinDate}</label>
			</div>
		</div>
	</div>
</div>
</body>
</html>
