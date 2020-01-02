<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/11/1
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>业主维修申请</title>
	<link rel="stylesheet" href=<%=path+"bootstrap/css/bootstrap.css"%>>
	<script type="text/javascript" src=<%=path + "js/jquery-3.4.1.min.js"%>></script>
	<script type="text/javascript" src=<%=path + "bootstrap/js/bootstrap.js"%>></script>
	<style>
		#tb {
			table-layout: fixed;
		}
	</style>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row clearfix">
				<div class="col-md-1 column">
				</div>
				<div class="col-md-10 column">
					<h3>
						业主在线报修申请
					</h3>
					<table class="table table-bordered">
						<tbody>
						<tr class="error">
							<td>申请人：</td>
							<td>
								<input class="form-control" id="apply_owner"/>
							</td>
						</tr>
						<tr class="success">
							<td>联系电话：</td>
							<td>
								<input class="form-control" id="apply_owner_phone"/>
							</td>
						</tr>
						<tr class="info">
							<td>备注：</td>
							<td style="height: 200%;" colspan="3"><textarea id="apply_owner_remark" style="height: 100%;width: 100%"></textarea>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				<div class="col-md-1 column">
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
