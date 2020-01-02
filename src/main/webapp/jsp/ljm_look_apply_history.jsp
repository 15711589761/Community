<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2020/1/2
  Time: 10:39
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
	<title>查看申请记录</title>
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path + "layui/layui.js"%>></script>
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
	layui.use('table', function(){
		var table = layui.table;
		var $ = layui.jquery;
		table.render({
			elem: '#test'
			,url:'<%=path%>forShowApplyHistory.action'
			,title: '用户数据表'
			,cols: [[
				{field:'applyId', title:'ID', sort: true}
				,{field:'applyName', title:'申请类型'}
				,{field:'applyTime', title:'申请发起时间'}
				,{field:'applyStatus', title:'申请状态'}
				,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
			]]
			,page: true
			, parseData: function (res) {
				if (res.code === 1) {
					layer.msg(res.msg)
				}
			}
		});

		//监听行工具事件
		table.on('tool(test)', function(obj){
			var data = obj.data;
			//console.log(obj)
			if(obj.event === 'del'){
				layer.confirm('真的删除行么', function(index){
					console.log("1111");
					var deleteId = data.applyId;
					var delOb = {applyId:deleteId};
					$.ajax({
						url:"<%=path%>deleteApplyHistory.action", //请求的url地址
						dataType:"json", //返回格式为json
						async:true,//请求是否异步，默认为异步，这也是ajax重要特性
						data:delOb, //参数值
						type:"post", //请求方式
						success:function(msg){
							layer.msg(msg.msg);
							table.reload('test');
						},
						error:function () {
							layer.msg("系统忙请重试");
							table.reload('test');
						}
					});
					layer.close(index);
				});
			}
		});
	});
</script>
</body>
</html>
