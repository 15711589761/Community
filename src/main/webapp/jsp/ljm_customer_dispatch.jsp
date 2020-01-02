<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/30
  Time: 15:35
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
	<title>业主报修申请（客服）</title>
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path + "layui/layui.js"%>></script>
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="agree">同意</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="disagree">不同意</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
	layui.use('table', function(){
		var table = layui.table;
		var tableIns = table.render();
		table.render({
			elem: '#test'
			,url:'<%=path%>findProcessesForTrim.action?taskName=派工服务'
			,title: '用户数据表'
			,cols: [[
				{field:'processTaskId', width:80, title:'任务ID'}
				,{field:'taskName',width:90, title:'任务名称'}
				,{field:'taskCreateTime',width:120, title:'任务创建时间'}
				,{field:'applyRoom',width:100, title:'申请楼栋号'}
				,{field:'remark',width:160, title:'备注', sort: true}
				,{field:'processAssignee',width:90, title:'办理部门'}
				,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
			]]
		});

		//监听行工具事件
		table.on('tool(test)', function(obj){
			var $ = layui.jquery;
			var data = obj.data;
			//console.log(obj)
			if(obj.event === 'del'){
				layer.confirm('真的要删除么', function(index){
					var taskId = data.processTaskId;
					var delOb = {taskId:taskId};
					$.ajax({
						url:"<%=path%>deleteProcessesTask.action", //请求的url地址
						dataType:"json", //返回格式为json
						async:true,//请求是否异步，默认为异步，这也是ajax重要特性
						data:delOb, //参数值
						type:"post", //请求方式
						success:function(msg){
							layer.closeAll();
							layer.msg("删除成功");
							obj.del();
						},
						error:function () {
							layer.msg("系统忙请重试");
							table.reload('test');
						}
					})
				});
			} else if (obj.event === 'agree')
			{
				var agreeId = data.processTaskId
				var agreeOb = {taskId:agreeId,isAgree:"同意"};
				$.ajax({
					url:"<%=path%>completeTaskWhitParameter.action", //请求的url地址
					dataType:"json", //返回格式为json
					async:true,//请求是否异步，默认为异步，这也是ajax重要特性
					data:agreeOb, //参数值
					type:"post", //请求方式
					success:function(msg){
						layer.msg("审核成功");
						table.reload('test');
					},
					error:function () {
						layer.msg("系统忙请重试");
						table.reload('test');
					}
				})
			} else if (obj.event === 'disagree')
			{
				var disagreeId = data.processTaskId
				var disagreeOb = {taskId:disagreeId,isAgree:"不同意"};
				$.ajax({
					url:"<%=path%>completeTaskWhitParameter.action", //请求的url地址
					dataType:"json", //返回格式为json
					async:true,//请求是否异步，默认为异步，这也是ajax重要特性
					data:disagreeOb, //参数值
					type:"post", //请求方式
					success:function(msg){
						layer.msg("驳回成功");
						table.reload('test');
					},
					error:function () {
						layer.msg("系统忙请重试");
						table.reload('test');
					}
				})
			}
		});
	});
</script>
</body>
</html>
