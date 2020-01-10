<%--
  Created by IntelliJ IDEA.
  User: ZKa1
  Date: 2019/12/19
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>业主车辆信息管理</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%> media="all">
	<script type="text/javascript" src=<%=path + "/layui/layui.js"%> charset="utf-8"></script>
</head>
<body>
<br>
<br>
	<div class="demoTable" style="text-align: center">
		<div class="layui-inline">
			业主名:
			<div class="layui-input-inline">
				<input type="text" name="ownerName" id="ownerName" autocomplete="off" class="layui-input">
			</div>
			车牌号:
			<div class="layui-input-inline">
				<input type="text" name="carNum" id="carNum" autocomplete="off" class="layui-input">
			</div>
			<button class="layui-btn" data-type="reload" id="search">搜索</button>
			<%--		<button data-method="notice" class="layui-btn" id="add">新增</button>--%>
		</div>
	</div>
	<table class="layui-hide" id="LAY_table_user" lay-filter="user"></table>
</body>
<script>
	layui.use('table', function () {
		var table = layui.table;
		table.render({
			elem: '#LAY_table_user'
			, url: '/Community/findOwnerCar' //数据接口
			, cols: [[ //表头
				{checkbox: true, fixed: true}
				, {field: 'ownerCarId', title: 'ID',align: 'center'}
				, {field: 'ownerCarNumber', title: '车牌号',align: 'center'}
				, {field: 'ownerName', title: '业主名',align: 'center'}
				, {field: 'roomNum', title: '楼栋房间号',align: 'center'}
				, {field: 'applyDate', title: '申请日期',align: 'center'}
				, {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
			]]
			, page: true //开启分页
			, id: 'mytable'

		});
		var $ = layui.$, active = {
			reload: function () {
				var demoReload = $('#mytable');
				//执行重载
				table.reload('mytable', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
						key: {
							id: demoReload.val()
						}
					}
				}, 'data');
			}
		};

		//查询
		$('#search').on('click', function () {
			var ownerName = $("#ownerName").val();
			var carNum = $("#carNum").val();
			table.reload('mytable', {
				method: 'post'
				, where: {
					'ownerName': ownerName,
					'carNum': carNum
				}
				, page: {
					curr: 1
				}
			});
		});

		table.on('tool(user)', function (obj) {
			var data = obj.data;
			if (obj.event === 'del') { //删除
				layer.confirm('确认删除么', function (index) {
					// obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
					layer.close(index);
					//向服务端发送删除指令
					var msg = data.ownerCarId;
					$.ajax({
						type: "POST",//提交的方式
						url: "/Community/delOwnerCar",//提交的地址
						data: "msg=" + msg,//提交的数据
						dataType: "json",//希望返回的数据类型
						async: true,//异步操作
						success: function (msg) {//成功的方法  msg为返回数据
							var num = parseInt(msg);
							if (num > 0) {
								alert("删除成功");
								layer.closeAll();
								table.reload();

							}
						},
						error: function () {//错误的方法
							alert("服务器正忙")
						}
					});
				});
			}
		});
	});
</script>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</html>
