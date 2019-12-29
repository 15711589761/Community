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
	<title>建议</title>
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path + "layui/layui.js"%>></script>
</head>
<body>
<div class="demoTable" style="text-align: center">
	<br/>
	<br/>
	<div class="layui-inline">
		日期：
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="startDate" name="startDate" placeholder="yyyy-MM-dd">
		</div>
		至
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="endDate" name="endDate" placeholder="yyyy-MM-dd">
		</div>
		<button class="layui-btn" data-type="reload" id="search-form">查询</button>
		<button class="layui-btn layui-btn-normal" id="send_suggest">编辑发送</button>
	</div>
</div>

<table class="layui-hide" id="suggestTable" lay-filter="test"></table>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
	layui.use('table', function () {
		var table = layui.table;
		table.render({
			elem: '#suggestTable'
			, url: '<%=path%>forGetSuggestTable.action'
			, limits: [5, 10, 15, 20]
			, cols: [[
				{field: 'suggestId', title: '编号', sort: true}
				, {field: 'suggestDate', title: '建议日期'}
				, {field: 'suggestContent', title: '建议内容'}
				, {field: 'suggestResult', title: '反馈'}
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
							table.reload('suggestTable');
						}
					})
				});
			}
		});

		var $ = layui.$, active = {};
		$('#search-form').on('click', function () {
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			table.reload('suggestTable', {
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
									suggestName: name,
									suggestPhone: phone,
									suggestContext: context,
									remark: '建议'
								};
								$.ajax({
									url: "<%=path%>insertSuggestRecord.action", //请求的url地址
									dataType: "json", //返回格式为json
									async: true,//请求是否异步，默认为异步，这也是ajax重要特性
									data: ob, //参数值
									type: "post", //请求方式
									success: function (msg) {
										layer.closeAll();
										layer.msg(msg.msg);
										table.reload('suggestTable');
									},
									error: function () {
										layer.msg("系统忙请重试");
										table.reload('suggestTable');
									}
								})
							} else {
								alert("请输入想让物业知道的内容");
							}
						} else {
							alert("请输入联系方式");
						}
					} else {
						alert("请输入申请人姓名");
					}
				},
				btn2: function () {
					layer.closeAll();
				}
			})
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
