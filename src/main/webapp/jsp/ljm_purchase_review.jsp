<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2020/1/7
  Time: 16:03
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
	<title>采购审核</title>
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path + "layui/layui.js"%>></script>
</head>
<body>
<div class="demoTable" style="text-align: center">
	<br/>
	<br/>
</div>

<table class="layui-hide" id="purchaseTable" lay-filter="test"></table>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="sub">反馈</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
	layui.use('table', function () {
		var table = layui.table;
		var $ = layui.jquery;
		table.render({
			elem: '#purchaseTable'
			, url: '<%=path%>findProcessesForPurchase.action'
			, limits: [5, 10, 15, 20]
			, cols: [[
				{field: 'processTaskId', title: '编号', sort: true}
				, {field: 'taskName', title: '任务名称'}
				, {field: 'taskCreateTime', title: '申请日期'}
				, {field: 'applyName', title: '申请人'}
				, {field: 'purchaseName', title: '商品名称'}
				, {field: 'purchaseModel', title: '商品型号'}
				, {field: 'purchaseQuantity', title: '商品数量'}
				, {field: 'purchasePrice', title: '商品价格'}
				, {fixed: 'right', title: '操作', toolbar: '#barDemo'}
			]]
		});

		//监听行工具事件
		table.on('tool(test)', function (obj) {
			var data = obj.data;
			if (obj.event === 'del') {
				layer.confirm('真的要删除么', function (index) {
					var processTaskId = data.processTaskId;
					var delOb = {processTaskId: processTaskId};
					$.ajax({
						url: "<%=path%>deleteProcessesPurchaseTask.action", //请求的url地址
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
							table.reload('purchaseTable');
						}
					})
				});
			} else if (obj.event === 'sub') {
				layer.prompt({
					formType: 2
				}, function(value, index){
					var taskId = data.processTaskId;
					var upOb = {feedback:value,taskId:taskId};
					$.ajax({
						url: "<%=path%>submitProcessesPurchaseTask.action", //请求的url地址
						dataType: "json", //返回格式为json
						async: true,//请求是否异步，默认为异步，这也是ajax重要特性
						data: upOb, //参数值
						type: "post", //请求方式
						success: function (msg) {
							layer.msg(msg.msg);
							table.reload('purchaseTable');
						},
						error: function () {
							alert("系统忙请重试");
							table.reload('purchaseTable');
						}
					})
					layer.close(index);
				});
			}
		});
	});
</script>
</body>
</html>
