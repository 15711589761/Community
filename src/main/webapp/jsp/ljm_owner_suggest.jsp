<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/25
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>建议和投诉</title>
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
					<table class="table table-bordered">
						<tbody>
						<tr class="error">
							<td>建议人：</td>
							<td>
								<input class="form-control" id="suggest_person"/>
							</td>
						</tr>
						<tr class="success">
							<td>联系电话：</td>
							<td>
								<input class="form-control" id="suggest_phone"
								       name="visitorOrigin"/>
							</td>
						</tr>
						<tr class="info">
							<td>建议内容：</td>
							<td style="height: 200%;" colspan="3"><textarea id="suggest_content" style="height: 100%;width: 100%"></textarea>
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
