<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/25
  Time: 16:02
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
	<title>请假申请</title>
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path + "layui/layui.js"%>></script>
</head>
<body>
<div class="demoTable" style="text-align: center">
	<br/>
	<br/>
</div>

<table class="layui-hide" id="leaveApply" lay-filter="test"></table>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
	layui.use('table', function () {
		var table = layui.table;
		table.render({
			elem: '#leaveApply'
			, url: '<%=path%>forShowAskForLeaveTable.action'
			, limits: [5, 10, 15, 20]
			, cols: [[
				{field: 'leaveId', title: 'ID', sort: true}
				, {field: 'applyName', title: '申请人'}
				, {field: 'startDate', title: '开始日期'}
				, {field: 'leaveDay', title: '请假天数'}
				, {field: 'content', title: '事由'}
				, {field: 'feedback', title: '反馈'}
				, {fixed: 'right', title: '操作', toolbar: '#barDemo'}
			]]
			, page: true
			, parseData: function (res) {
				if (res.code === 1) {
					layer.msg(res.msg)
				}
			}
		});

		//监听行工具事件
		table.on('tool(test)', function (obj) {
			var $ = layui.jquery;
			var data = obj.data;
			//console.log(obj)
			if (obj.event === 'del') {
				layer.confirm('真的要删除么', function (index) {
					var suggestId = data.suggestId;
					var delOb = {suggestId:suggestId};
					$.ajax({
						url: "<%=path%>deleteSuggestRecord.action", //请求的url地址
						dataType: "json", //返回格式为json
						async: true,//请求是否异步，默认为异步，这也是ajax重要特性
						data: delOb, //参数值
						type: "post", //请求方式
						success: function (msg) {
							layer.closeAll();
							layer.msg(msg.msg);
							obj.del();
						},
						error: function () {
							layer.msg("系统忙请重试");
							table.reload('leaveApply');
						}
					})
				});
			}
		});
	});
</script>
</body>
</html>
