<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/27
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>二级菜单添加</title>
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
							<td>菜单名称：</td>
							<td>
								<input class="form-control" id="parent_menu"/>
							</td>
						</tr>
						<tr class="success">
							<td>菜单地址：</td>
							<td>
								<input class="form-control" id="parent_menu_url"
								       name="visitorOrigin"/>
							</td>
						</tr>
						<tr class="info">
							<td>所属一级菜单</td>
							<td style="height: 200%;" colspan="3">
								<div class="layui-input-inline">
									<select id="menu_by" name="menuPid" class="layui-input">
										<option value="0">无归属</option>
										<c:if test="${requestScope.parentMenu!=null}">
											<c:forEach items="${requestScope.parentMenu}" var="i" step="1" begin="0">
												<option value="${i.menuId}">${i.menuName}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
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
</body>
</html>
