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
	<title>投诉流</title>
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path + "layui/layui.js"%>></script>
</head>
<body>
<div class="demoTable" style="text-align: center">
	<br/>
	<br/>
	<div class="layui-inline">
		<button class="layui-btn layui-btn-normal" id="send_suggest">编辑投诉内容</button>
	</div>
</div>

<table class="layui-hide" id="complaintTable" lay-filter="test"></table>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="sub">提交</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
	layui.use('table', function () {
		var table = layui.table;
		var $ = layui.jquery;
		table.render({
			elem: '#complaintTable'
			, url: '<%=path%>findProcessesForComplaint.action'
			, limits: [5, 10, 15, 20]
			, cols: [[
				{field: 'processTaskId', title: '编号', sort: true}
				, {field: 'taskName', title: '任务名称'}
				, {field: 'taskCreateTime', title: '投诉日期'}
				, {field: 'processAssignee', title: '楼栋号'}
				, {field: 'content', title: '投诉内容'}
				, {fixed: 'right', title: '操作', toolbar: '#barDemo'}
			]]
		});

		//监听行工具事件
		table.on('tool(test)', function (obj) {
			var data = obj.data;
			//console.log(obj)
			if (obj.event === 'del') {
				layer.confirm('真的要删除么', function (index) {
					var processTaskId = data.processTaskId;
					var delOb = {processTaskId: processTaskId};
					$.ajax({
						url: "<%=path%>deleteProcessesComplaintTask.action", //请求的url地址
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
							table.reload('complaintTable');
						}
					})
				});
			} else if (obj.event === 'sub') {
				var taskId = data.processTaskId;
				var subOb = {taskId: taskId, feedback: '待受理'};
				$.ajax({
					url: "<%=path%>submitProcessesComplaintTask.action", //请求的url地址
					dataType: "json", //返回格式为json
					async: true,//请求是否异步，默认为异步，这也是ajax重要特性
					data: subOb, //参数值
					type: "post", //请求方式
					success: function (msg) {
						layer.msg(msg.msg);
						table.reload('complaintTable');
					},
					error: function () {
						layer.msg("系统忙请重试");
						table.reload('complaintTable');
					}
				})
			}
		});

		$('#send_suggest').on('click', function () {
			layer.open({
				type: 2,
				content: 'jsp/ljm_owner_suggest.jsp',
				offset: ['10%', '40%'],
				title: '提交',
				area: ['300px', '300px'],
				btn: ['提交', '关闭'],
				btn1: function (index, layero) {
					var name = $(layero).find('iframe')[0].contentWindow.suggest_person.value;
					var phone = $(layero).find('iframe')[0].contentWindow.suggest_phone.value;
					var context = $(layero).find('iframe')[0].contentWindow.suggest_content.value;
					if (name.length > 0) {
						if (phone.length > 0) {
							if (context.length > 0) {
								var ob = {
									complaintName: name,
									complaintPhone: phone,
									complaintContext: context
								};
								$.ajax({
									url: "<%=path%>createProcessesForComplaint.action", //请求的url地址
									dataType: "json", //返回格式为json
									async: true,//请求是否异步，默认为异步，这也是ajax重要特性
									data: ob, //参数值
									type: "post", //请求方式
									success: function (msg) {
										layer.closeAll();
										layer.msg(msg.msg);
										table.reload('complaintTable');
									},
									error: function () {
										layer.msg("系统忙请重试");
										table.reload('complaintTable');
									}
								})
							} else {
								alert("请输入想让物业知道的内容");
							}
						} else {
							alert("请输入联系方式");
						}
					} else {
						alert("请输入姓名");
					}
				},
				btn2: function () {
					layer.closeAll();
				}
			})
		});

	});
</script>
</body>
</html>
