<%--
  Created by IntelliJ IDEA.
  User: ZKa1
  Date: 2019/12/17
  Time: 14:02
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
	<title>设备管理</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%>>
	<script src=<%=path+"/layui/layui.js"%> charset="utf-8"></script>
</head>
<body>
<div class="demoTable">
	<div class="layui-inline">
		购买时间:
		<div class="layui-input-inline">
			<input type="date" name="beginDate" id="beginDate" autocomplete="off" class="layui-input">
		</div>
		至
		<div class="layui-input-inline">
			<input type="date" name="endDate" id="endDate" autocomplete="off" class="layui-input">
		</div>
		设备名称:
		<div class="layui-input-inline">
			<input type="text" name="FacilityName" id="FacilityName" autocomplete="off" class="layui-input">
		</div>
		<button class="layui-btn" data-type="reload" id="search">搜索</button>
		<button data-method="notice" class="layui-btn" id="add">新增</button>
	</div>
</div>

<table class="layui-hide" id="LAY_table_user" lay-filter="user"></table>

<script>
	layui.use('table', function () {
		var table = layui.table;
		table.render({
			elem: '#LAY_table_user'
			, url: '/Community/findFacility' //数据接口
			, cols: [[ //表头
				{checkbox: true, fixed: true}
				, {field: 'facilityID', title: '设备ID'}
				, {field: 'facilityName', title: '设备名称'}
				, {field: 'facilityNum', title: '数量', event: 'setNum'}
				, {field: 'facilityBuyDate', title: '购入日期'}
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
			var FacilityName = $("#FacilityName").val();
			table.reload('mytable', {
				method: 'post'
				, where: {
					'beginDate': beginDate,
					'endDate': endDate,
					'name': FacilityName
				}
				, page: {
					curr: 1
				}
			});
		});


		//监听单元格事件
		table.on('tool(user)', function (obj) {
			var data = obj.data;
			if (obj.event === 'setNum') {//修改
				layer.prompt({
					formType: 2
					, title: '修改设备为 [' + data.facilityName + '] 的数量'
					, value: data.facilityNum
				}, function (value, index) {
					layer.close(index);
					if (parseInt(value) > 0) {
						//向服务端发送修改指令
						var ob = {facilityID: data.facilityID, facilityNum: parseInt(value)};
						$.ajax({
							type: "POST",//提交的方式
							url: "/Community/setFacilityNum",//提交的地址
							data: ob,//提交的数据
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (msg) {//成功的方法  msg为返回数据
								var num = parseInt(msg);
								if (num > 0) {
									layer.msg("修改成功");
									layer.closeAll();
									//同步更新表格和缓存对应的值
									obj.update({
										facilityNum: value
									});
								}
							},
							error: function () {//错误的方法
								alert("服务器正忙")
							}
						});

					} else {
						alert("请输入正确的数字")
					}
				});
			} else if (obj.event === 'del') { //删除
				layer.confirm('确认删除么', function (index) {
					// obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
					layer.close(index);
					//向服务端发送删除指令
					var ob = {facilityID: data.facilityID};
					$.ajax({
						type: "POST",//提交的方式
						url: "/Community/delFacility",//提交的地址
						data: ob,//提交的数据
						dataType: "json",//希望返回的数据类型
						async: true,//异步操作
						success: function (msg) {//成功的方法  msg为返回数据
							var num = parseInt(msg);
							if (num > 0) {
								layer.msg("删除成功");
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
	});

	layui.use('layer', function () { //独立版的layer无需执行这一句
		var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
		//触发事件
		var active = {
			notice: function () {
				//示范一个公告层
				layer.open({
					type: 2
					,
					title: false //不显示标题栏
					,
					closeBtn: false
					,
					area: ['700px', '400px']
					,
					shade: 0.8
					,
					// id: 'iframe' //设定一个id，防止重复弹出
					// ,
					btn: ['确定', '取消']
					,
					btn1: function (index, layero) {
						var facilityName = $(layero).find('iframe')[0].contentWindow.facilityName.value;
						var facilityNum = $(layero).find('iframe')[0].contentWindow.facilityNum.value;
						var facilityBuyDate = $(layero).find('iframe')[0].contentWindow.facilityDate.value;
						var ob = {
							facilityName: facilityName,
							facilityNum: facilityNum,
							facilityBuyDate: facilityBuyDate
						};
						$.ajax({
							type: "POST",//提交的方式
							url: "/Community/addFacility",//提交的地址
							data: ob,//提交的数据
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (msg) {//成功的方法  msg为返回数据
								var num = parseInt(msg);
								if (num > 0) {
									layer.msg("新增成功");
									layer.closeAll();
									//重新加载当前页面
									location.reload();
								}
							},
							error: function () {//错误的方法
								alert("服务器正忙")
							}
						});

					},
					btnAlign: 'c'
					,
					moveType: 1 //拖拽模式，0或者1
					,
					content: '../Community/jsp/zk_back_addfacility.jsp'
					, success: function (layero) {

					}
				});
			}
		};

		$('#add').on('click', function () {
			var othis = $(this), method = othis.data('method');
			active[method] ? active[method].call(this, othis) : '';
		});

	});


</script>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
</html>
