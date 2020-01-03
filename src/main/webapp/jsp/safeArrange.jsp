<%--
  Created by IntelliJ IDEA.
  User: 93419
  Date: 2019/11/17
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String Path = application.getContextPath()+"/layui/";
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>安保排班</title>
	<link rel="stylesheet" href=<%=Path+"css/layui.css"%>>
	<script type="text/javascript" src=<%=Path+"layui.js"%>></script>
</head>
<body>
<div class="layui-fluid">
	<div class="layui-card">
		<div class="demoTable" style="text-align: center">
		</div>
		<script type="text/html" id="saferAction">
			<a class="layui-btn layui-btn-xs" id="update-user-status" lay-event="arrange">排班</a>
		</script>
		<table class="layui-table" lay-data="{url:'/Community/safeArrange.action', page:true, id:'saferTable'}" lay-filter="demo">
			<thead>
			<tr>
				<th lay-data="{field:'staffId', sort: true, hide: true}">用户id</th>
				<th lay-data="{field:'staffNumber'}">工号</th>
				<th lay-data="{field:'staffName'}">姓名</th>
				<th lay-data="{field:'staffJoinDate'}">入职时间</th>
				<th lay-data="{field:'staffAddress'}">地址</th>
				<th lay-data="{fixed: 'right',width:200,align:'center', toolbar: '#saferAction'}">操作</th>
			</tr>
			</thead>
		</table>
		<script>
			layui.use('table', function () {
				var table = layui.table;
				//监听工具条
				table.on('tool(demo)', function (obj) {
					var data = obj.data;
					 if (obj.event === 'arrange') {
						 var getId = obj.data.staffId; //获得当前行数据
						layer.open({
							type: 2,
							title: '进行排班',
							area: ['1000px', '800px'],
							btn: ['修改', '取消'],
							btn1: function (index, getId) {
								setTimeout(function () {
									table.reload('saferTable');
								},1000);
								layer.close(index);
							},
								content: '/Community/safeFullcalendar.view?getId='+getId
						});
					}
				});

				var $ = layui.$, active = {};
				$(function () {

				});
			});
		</script>
	</div>
</div>

</body>
</html>
