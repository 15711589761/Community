<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/20
  Time: 9:52
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
<div>
	<button type="button" class="layui-btn layui-btn-normal" id="create_processes">添加申请</button>
</div>
<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="barDemo">
	{{#  if(d.message===null){ }}
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="sub">提交</a>
	{{#  } }}
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
	layui.use('table', function(){
		var table = layui.table;
		table.render({
			elem: '#test'
			,url:'<%=path%>findProcessesForTrim.action'
			,title: '用户数据表'
			,cols: [[
				{field:'processTaskId', width:80, title:'任务ID'}
				,{field:'taskName',width:90, title:'任务名称'}
				,{field:'taskCreateTime',width:120, title:'任务创建时间'}
				,{field:'processAssignee',width:90, title:'楼栋号'}
				,{field:'workDate',width:120, title:'施工时间'}
				,{field:'remark',width:160, title:'备注'}
				,{field:'message',width:80, title:'反馈'}
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
							obj.del();
						},
						error:function () {
							alert("系统忙请重试");
							table.reload('test');
						}
					})
				});
			} else if (obj.event === 'sub')
			{
				var taskId = data.processTaskId;
				var subOb = {taskId:taskId};
				$.ajax({
					url:"<%=path%>submitProcessesTask.action", //请求的url地址
					dataType:"json", //返回格式为json
					async:true,//请求是否异步，默认为异步，这也是ajax重要特性
					data:subOb, //参数值
					type:"post", //请求方式
					success:function(msg){
						layer.msg("提交成功");
						table.reload('test');
					},
					error:function () {
						alert("系统忙请重试");
						table.reload('test');
					}
				})
			}
		});
		var $ = layui.jquery;
		$("#create_processes").on("click",function () {
			layer.open({
				type: 2,
				content: 'jsp/ljm_owner_trim.jsp',
				offset: ['10%','40%'],
				title: '添加申请',
				area: ['300','400px'],
				btn: ['添加申请','关闭'],
				btn1:function(index,layero){
					var name = $(layero).find('iframe')[0].contentWindow.apply_owner.value;
					var phone = $(layero).find('iframe')[0].contentWindow.apply_owner_phone.value;
					var date = $(layero).find('iframe')[0].contentWindow.apply_owner_date.value;
					var remark = $(layero).find('iframe')[0].contentWindow.apply_owner_remark.value;
					if (name.length>0)
					{
						if (phone.length>0)
						{
							if (date.length>0)
							{
								var ob = {
									applyName:name,
									applyPhone:phone,
									workDate:date,
									remark:remark
								};
								$.ajax({
									url:"<%=path%>createProcessesForTrim.action", //请求的url地址
									dataType:"json", //返回格式为json
									async:true,//请求是否异步，默认为异步，这也是ajax重要特性
									data:ob, //参数值
									type:"post", //请求方式
									success:function(msg){
										layer.closeAll();
										table.reload('test');
									},
									error:function () {
										alert("系统忙请重试");
										table.reload('test');
									}
								})
							} else {
								alert("请输入施工日期");
							}
						} else {
							alert("请输入联系方式");
						}
					} else {
						alert("请输入申请人姓名");
					}
				},
				btn2:function () {
					layer.closeAll();
				}
			})
		})
	});
</script>
</body>
</html>
