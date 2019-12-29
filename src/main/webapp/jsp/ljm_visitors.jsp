<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/18
  Time: 10:13
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
	<title>Title</title>
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path + "layui/layui.js"%>></script>
</head>
<body>
<br>
<br>
<div class="demoTable" style="text-align: center">
	<br/>
	<br/>
	<div class="layui-inline">
		来访日期：
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="startDate" name="startDate" placeholder="yyyy-MM-dd">
		</div>
		至
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="endDate" name="endDate" placeholder="yyyy-MM-dd">
		</div>
		<button class="layui-btn" data-type="reload" id="search-form">查询</button>
	</div>
</div>

<table class="layui-hide" id="visitorTable"></table>
<script>
	layui.use('table', function(){
		var table = layui.table;

		table.render({
			elem: '#visitorTable'
			,url:'<%=path%>getVisitorsTable.action'
			,limits:[5,10,15,20,25,30,35,40,45,50]
			,cols: [[
				{field:'visitorId',  title: 'ID', sort: true}
				,{field:'visitorName',  title: '来访者姓名'}
				,{field:'visitorIdentity',  title: '身份证号码'}
				,{field:'visitorOrigin',  title: '来访原因'}
				,{field:'visitorRoom',  title: '访问住户'}
				,{field:'visitorDate',  title: '来访日期', sort: true}
				,{field:'visitorTime', title: '来访时间', sort: true}
				,{field:'recorder', title: '记录人', sort: true}
			]]
			,page: true
			,parseData:function (res) {
				if (res.code === 1 )
				{
					layer.msg(res.msg)
				}
			}
		});

		var $ = layui.$, active = {};

		$('#search-form').on('click', function () {
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			table.reload('visitorTable', {
				method: 'post'
				, where: {
					'startDate': startDate,
					'endDate': endDate
				}
				, page: {
					curr: 1
				}
			});
		});

	});

	//时间选择器
	layui.use('laydate', function () {
		var laydate = layui.laydate;
		//常规用法
		laydate.render({
			elem: '#startDate'
		});
		laydate.render({
			elem: '#endDate'
		});
	});
</script>
</body>
</html>
