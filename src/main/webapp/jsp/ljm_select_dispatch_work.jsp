<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/30
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>派工人员下拉框</title>
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path + "layui/layui.js"%>></script>
</head>
<body>
	选择派工人员：
	<select class="layui-select" id="work_person">
		<c:if test="${requestScope.personList!=null}">
			<c:forEach items="${requestScope.personList}" begin="0" step="1" var="p">
				<option value="${p.workId}">${p.workPerson}</option>
			</c:forEach>
		</c:if>
	</select>
</body>
</html>
