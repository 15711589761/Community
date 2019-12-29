<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/26
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>业主装修申请</title>
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path + "layui/layui.js"%>></script>
</head>
<body>
<div class="demoTable" style="text-align: center">
	<br/>
	<br/>
	<div class="layui-inline">
		办理人
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="roomNum" name="roomNum">
		</div>
		<button class="layui-btn" data-type="reload" id="search-form">查询</button>
	</div>
</div>
<table class="layui-hide" id="test" lay-filter="test"></table>
<script>
	layui.use('table', function(){
		var table = layui.table;
		var tableIns = table.render();
		table.render({
			elem: '#test'
			,url:'<%=path%>findProcessesForTrimHistory.action'
			,title: '用户数据表'
			,page: true
			,cols: [[
				{field:'processTaskId', width:80, title:'ID'}
				,{field:'taskCreateTime',width:120, title:'任务创建时间'}
				,{field:'taskEndTime',width:120, title:'任务结束时间'}
				,{field:'processAssignee',width:90, title:'办理人'}
			]]
			, parseData: function (res) {
				if (res.code === 1) {
					layer.msg(res.msg)
				}
			}
		});

		var $ = layui.$, active = {};

		$('#search-form').on('click', function () {
			var roomNum = $("#roomNum").val();
			table.reload('test', {
				method: 'post'
				, where: {
					'roomNum': roomNum
				}
				, page: {
					curr: 1
				}
			});
		});
	});
</script>
</body>
</html>
