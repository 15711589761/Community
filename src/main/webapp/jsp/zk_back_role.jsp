<%--
  Created by IntelliJ IDEA.
  User: ZKa1
  Date: 2019/12/21
  Time: 13:56
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
	<title>角色管理</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%>>
	<script src=<%=path+"/layui/layui.js"%> charset="utf-8"></script>
	<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>
</head>
<body>
<br>
<br>
<div style="text-align: center">
	<div class="demoTable">
		<div class="layui-inline">
			角色名:
			<div class="layui-input-inline">
				<input type="text" name="roleName" id="roleName" autocomplete="off" class="layui-input">
			</div>
			<button class="layui-btn" data-type="reload" id="search">搜索</button>
			<button data-method="notice" class="layui-btn" id="add">新增</button>
		</div>
	</div>

	<table class="layui-hide" id="LAY_table_user" lay-filter="role"></table>
</div>
</body>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="setPower">修改权限</a>
</script>
<script>

	//这里设置一个空的json串，用于接受表格的行信息
	var json;
	layui.use('table', function () {
		var table = layui.table, layer = layui.layer;
		table.render({
			elem: '#LAY_table_user'
			, url: '/Community/findRole' //数据接口
			, cols: [[ //表头
				{checkbox: true, fixed: true}
				, {field: 'roleId', title: '角色id',align: 'center'}
				, {field: 'roleName', title: '角色名称', event: 'setName',align: 'center'}
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
			var roleName = $("#roleName").val();
			table.reload('mytable', {
				method: 'post'
				, where: {
					'roleName': roleName
				}
				, page: {
					curr: 1
				}
			});
		});


		table.on('tool(role)', function (obj) {
			var data = obj.data;
			//这行是监听到的表格行数据信息，复制给json全局变量。
			json = JSON.stringify(data);
			if (obj.event === 'setPower') {//修改权限
				if (1 != data.roleId) {
					layer.open({
						type: 2
						,
						title: '权限设置' //不显示标题栏
						,
						closeBtn: false
						,
						area: ['400px', '600px']
						,
						shade: 0.8
						,
						btn: ['确定', '取消']
						,
						btn1: function (index, layero) {
							var iframeWindow = window['layui-layer-iframe' + index]
								, submit = layero.find('iframe').contents().find("#LAY-user-role-submit");
							iframeWindow.layui.form.on('submit(LAY-user-role-submit)', function (data) {
								var field = data.field;
								console.log(field);
								$.ajax({
									type: "POST",//提交的方式
									url: "/Community/addRoleMenu",//提交的地址
									data: "roleid=" + obj.data.roleId + "&field=" + JSON.stringify(field),//提交的数据
									dataType: "json",//希望返回的数据类型
									async: true,//异步操作
									success: function (msg) {//成功的方法  msg为返回数据
										var num = parseInt(msg);
										if (num > 0) {
											layer.msg("权限设置成功");
											layer.close(index);
											//刷新表格
											table.reload();
										}
									},
									error: function () {//错误的方法
										alert("服务器正忙")
									}
								});
							});
							submit.trigger('click');

						},
						btnAlign: 'c'
						,
						moveType: 1 //拖拽模式，0或者1
						,
						content: '../Community/jsp/roleTree.jsp'
						, success: function (layero) {

						}
					});
				} else {
					layer.msg("超级管理员权限不可修改")
				}
			} else if (obj.event === 'setName') {
				layer.prompt({
					formType: 2
					, title: '修改当前角色名为 [' + data.roleName + '] 的名称'
					, value: data.roleName
				}, function (value, index) {
					layer.close(index);
					if (value.length > 0) {
						if (value !== data.roleName) {
							//向服务端发送修改指令
							var ob = {roomNum: data.roleId, name: value};
							$.ajax({
								type: "POST",//提交的方式
								url: "/Community/setRoleName",//提交的地址
								data: ob,//提交的数据
								dataType: "json",//希望返回的数据类型
								async: true,//异步操作
								success: function (msg) {//成功的方法  msg为返回数据
									var num = parseInt(msg);
									if (num === 1) {
										layer.msg("修改成功");
										//同步更新表格和缓存对应的值
										obj.update({
											roleName: value
										});
									} else if (num === 2) {
										layer.msg("该角色名已存在，请重新输入");
									}

								},
								error: function () {//错误的方法
									alert("服务器正忙")
								}
							});

						} else {
							layer.msg("请输入与当前角色名不同的名称")
						}
					} else {
						layer.msg("请输入名称")
					}
				});
			}
		});

	});


	layui.use('layer', function () { //独立版的layer无需执行这一句
		var $ = layui.jquery, layer = layui.layer, table = layui.table; //独立版的layer无需执行这一句
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
					area: ['400px', '400px']
					,
					shade: 0.8
					,
					btn: ['确定', '取消']
					,
					btn1: function (index, layero) {
						var roleName = $(layero).find('iframe')[0].contentWindow.roleName.value;
						if (roleName.length > 0) {
							$.ajax({
								type: "POST",//提交的方式
								url: "/Community/addRole",//提交的地址
								data: "roleName=" + roleName,//提交的数据
								dataType: "json",//希望返回的数据类型
								async: true,//异步操作
								success: function (msg) {//成功的方法  msg为返回数据
									var num = parseInt(msg);
									if (num === 1) {
										layer.msg("添加成功");
										layer.close(index);
										location.reload();
									} else if (num === 2) {
										layer.msg("该角色名已存在，请重新输入");
									}
								},
								error: function () {//错误的方法
									alert("服务器正忙")
								}
							});
						} else {
							layer.msg("请输入角色名称")
						}

					},
					btnAlign: 'c'
					,
					moveType: 1 //拖拽模式，0或者1
					,
					content: '../Community/jsp/zk_back_addrole.jsp'
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

</html>
