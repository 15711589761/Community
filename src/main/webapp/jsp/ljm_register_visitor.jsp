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
	<title>访客登记</title>
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
						访客登记
					</h3>
					<form role="form" method="post" action="<%=path%>insertVisitorRecord.action" onsubmit="return check()">
						<table class="table table-bordered">
							<tbody>
							<tr>
								<td>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
								<td>
									<input style="border: 0;width: 100%;height: 100%" id="insert_visitor_name"
									       name="visitorName"/>
								</td>
								<td>访问住户：</td>
								<td>
									<input style="border: 0;width: 100%;height: 100%" id="insert_visitor_room"
									       name="visitorRoom"/>
								</td>
							</tr>
							<tr class="error">
								<td>来访原因：</td>
								<td>
									<input style="border: 0;width: 100%;height: 100%" id="insert_visitor_origin"
									       name="visitorOrigin"/>
								</td>
								<td>证&nbsp;&nbsp;件&nbsp;&nbsp;号：</td>
								<td colspan="2">
									<input style="border: 0;width: 100%;height: 100%" id="insert_visitor_identity"
									       name="visitorIdentity"/>
								</td>
							</tr>
							<tr class="success">
								<td colspan="6" style="text-align: center;vertical-align: middle">
									<button class="btn btn-default" id="insert_visitor_button">登 记</button>
								</td>
							</tr>
							</tbody>
						</table>
					</form>
					<c:if test="${requestScope.insertFail!=null}">
						<div class="alert alert-success alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
							<h4>注意!</h4> <strong>${requestScope.insertFail}</strong>
						</div>
					</c:if>
					<c:if test="${requestScope.insertSuccess!=null}">
						<div class="alert alert-success alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
							<h4>${requestScope.insertSuccess}</h4>
						</div>
					</c:if>
				</div>
				<div class="col-md-1 column">
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<script>
	function check(){
		var visitorName = $("#insert_visitor_name").val();
		var visitorRoom = $("#insert_visitor_room").val();
		var visitorOrigin = $("#insert_visitor_origin").val();
		var visitorIdentity = $("#insert_visitor_identity").val().toUpperCase();
		var res = /^[1-9][0-9]{5}([1][9][0-9]{2}|[2][0][0|1][0-9])([0][1-9]|[1][0|1|2])([0][1-9]|[1|2][0-9]|[3][0|1])[0-9]{3}([0-9]|[X])$/;
		// var resRoom =/ /[^u4e00-u9fa5w]/g,''/;
		if (visitorName.length > 0 && visitorName.length < 10)
		{
			if (visitorRoom.length > 0 && visitorRoom.length < 6)
			{
				if (visitorOrigin.length > 0)
				{
					if (res.test(visitorIdentity))
					{
						return true;
					} else {
						alert("请正确填写身份证号码！")
					}
				} else {
					alert("请正确填写来访事由！");
				}
			}else {
				alert("请正确填写被访问的住户！")
			}
		} else {
			alert("请正确填写来访姓名！");
		}
		return false;
	}
</script>
