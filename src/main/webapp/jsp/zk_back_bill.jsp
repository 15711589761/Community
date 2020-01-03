<%--
  Created by IntelliJ IDEA.
  User: ZKa1
  Date: 2019/12/26
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = application.getContextPath();
%>
<html>
<head>
	<title>票据管理</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%>>
	<script src=<%=path+"/layui/layui.js"%> charset="utf-8"></script>
</head>
<body>
<div class="demoTable">
	<div class="layui-inline">
		时间:
		<div class="layui-input-inline">
			<input type="date" name="beginDate" id="beginDate" autocomplete="off" class="layui-input">
		</div>
		至
		<div class="layui-input-inline">
			<input type="date" name="endDate" id="endDate" autocomplete="off" class="layui-input">
		</div>
		付款人:
		<div class="layui-input-inline">
			<input type="text" name="billPayer" id="billPayer" autocomplete="off" class="layui-input">
		</div>
		<button class="layui-btn" data-type="reload" id="search">搜索</button>
	</div>
</div>

<table class="layui-hide" id="LAY_table_user" lay-filter="user"></table>

<script>
	layui.use('table', function () {
		var table = layui.table;
		table.render({
			elem: '#LAY_table_user'
			, url: '/Community/findBill' //数据接口
			, cols: [[ //表头
				{checkbox: true, fixed: true}
				, {field: 'billid', title: '票据ID', hide: true}
				, {field: 'payee', title: '收款人'}
				, {field: 'payer', title: '付款人'}
				, {field: 'billDate', title: '日期时间'}
				, {field: 'details', title: '详细信息'}
				, {field: 'money', title: '金额/元'}
				, {field: 'orderNo', title: '订单号'}
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
			var beginDate = $("#beginDate").val();
			var endDate = $("#endDate").val();
			var billPayer = $("#billPayer").val();
			table.reload('mytable', {
				method: 'post'
				, where: {
					'beginDate': beginDate,
					'endDate': endDate,
					'name': billPayer
				}
				, page: {
					curr: 1
				}
			});
		});

		//监听单元格事件
		table.on('tool(user)', function (obj) {
			var data = obj.data;
			if (obj.event === 'del') { //删除
				layer.confirm('确认作废么', function (index) {
					layer.close(index);
					//向服务端发送删除指令
					$.ajax({
						type: "POST",//提交的方式
						url: "/Community/setBillState",//提交的地址
						data: "billid="+data.billid,//提交的数据
						dataType: "json",//希望返回的数据类型
						async: true,//异步操作
						success: function (msg) {//成功的方法  msg为返回数据
							var num = parseInt(msg);
							if (num > 0) {
								layer.msg("作废成功");
								obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
							}
						}
						, error: function () {//错误的方法
							alert("服务器正忙")
						}
					})
				});
			}

		});
	})
</script>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">作废</a>
</script>
</body>
</html>
