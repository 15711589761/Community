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
		<button class="layui-btn layui-btn-normal" id="send_apply">填写请假申请</button>
	</div>
</div>

<table class="layui-hide" id="askForLeaveTable" lay-filter="test"></table>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="sub">提交</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
	layui.use('table', function () {
		var table = layui.table;
		var $ = layui.jquery;
		table.render({
			elem: '#askForLeaveTable'
			, url: '<%=path%>findProcessesForLeave.action'
			, limits: [5, 10, 15, 20]
			, cols: [[
				{field: 'processTaskId', title: '编号', sort: true}
				, {field: 'taskName', title: '任务名称'}
				, {field: 'startDate', title: '开始日期'}
				, {field: 'leaveDay', title: '请假天数'}
				, {field: 'applyName', title: '申请人'}
				, {field: 'content', title: '事由'}
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
						url: "<%=path%>deleteProcessesLeaveTask.action", //请求的url地址
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
							table.reload('askForLeaveTable');
						}
					})
				});
			} else if (obj.event === 'sub') {
				var taskId = data.processTaskId;
				var subOb = {taskId: taskId};
				$.ajax({
					url: "<%=path%>submitProcessesLeaveTask.action", //请求的url地址
					dataType: "json", //返回格式为json
					async: true,//请求是否异步，默认为异步，这也是ajax重要特性
					data: subOb, //参数值
					type: "post", //请求方式
					success: function (msg) {
						layer.msg(msg.msg);
						table.reload('askForLeaveTable');
					},
					error: function () {
						layer.msg("系统忙请重试");
						table.reload('askForLeaveTable');
					}
				})
			}
		});

		$('#send_apply').on('click', function () {
			layer.open({
				type: 2,
				content: 'jsp/ljm_edit_for_leave.jsp',
				offset: ['10%', '40%'],
				title: '提交',
				area: ['300px', '300px'],
				btn: ['提交', '关闭'],
				btn1: function (index, layero) {
					var leaveDate = $(layero).find('iframe')[0].contentWindow.leave_date.value;
					var leaveDay = $(layero).find('iframe')[0].contentWindow.leave_day.value;
					var leaveContent = $(layero).find('iframe')[0].contentWindow.leave_content.value;
					if (leaveDate.length > 0) {
						if (leaveDay.length > 0) {
							if (leaveContent.length > 0) {
								var ob = {
									leaveDate: leaveDate,
									leaveDay: leaveDay,
									leaveContent: leaveContent
								};
								$.ajax({
									url: "<%=path%>createProcessesForLeave.action", //请求的url地址
									dataType: "json", //返回格式为json
									async: true,//请求是否异步，默认为异步，这也是ajax重要特性
									data: ob, //参数值
									type: "post", //请求方式
									success: function (msg) {
										layer.closeAll();
										layer.msg(msg.msg);
										table.reload('askForLeaveTable');
									},
									error: function () {
										layer.msg("系统忙请重试");
										table.reload('askForLeaveTable');
									}
								})
							} else {
								alert("请输入事由！");
							}
						} else {
							alert("请输入请假持续天数！");
						}
					} else {
						alert("请输入请假开始日期！");
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
