<%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/26
  Time: 13:44
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
	<title>后台菜单管理</title>
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path + "layui/layui.js"%>></script>
</head>
<body>
<br>
<br>
<div class="demoTable" style="text-align: center">
	<br/>
	<br/>
	<div class="layui-inline">
		菜单等级：
		<div class="layui-input-inline">
			<select id="menu_grade" name="menuPid" class="layui-input">
				<option value="">请选择</option>
				<option value="0">一级菜单</option>
				<option value="1">二级菜单</option>
			</select>
		</div>
		菜单名称：
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="menu_name" name="menuName">
		</div>
		<button class="layui-btn" data-type="reload" id="search-form">查询</button>
		<button class="layui-btn" data-type="reload" id="add_menu_one">添加一级菜单</button>
		<button class="layui-btn" data-type="reload" id="add_menu_two">添加二级菜单</button>
	</div>
</div>

<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="barDemo">
	{{#  if(d.menuStatus === '启用'){ }}
	<a class="layui-btn layui-btn-xs" lay-event="able">禁用</a>
	{{#  } else { }}
	<a class="layui-btn layui-btn-xs" lay-event="able">启用</a>
	{{#  } }}
	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
	layui.use('table', function () {
		var table = layui.table;
		var $ = layui.jquery;
		table.render({
			elem: '#test'
			, url: '<%=path%>forShowBackstageMenuManageTable.action'
			, title: '用户数据表'
			, cols: [[
				{field: 'menuId', title: '菜单ID', sort: true}
				, {field: 'menuName', title: '菜单名称'}
				, {field: 'toUrl', title: '菜单路径'}
				, {field: 'menuPid', title: '所属菜单ID'}
				, {field: 'menuStatus', title: '菜单状态'}
				, {fixed: 'right', title: '操作', toolbar: '#barDemo'}
			]]
			, page: true
			, parseData: function (res) {
				if (res.code === 1) {
					layer.msg(res.msg);
				}
			}
		});

		//监听行工具事件
		table.on('tool(test)', function (obj) {
			var data = obj.data;
			//console.log(obj)
			if (obj.event === 'del') {
				layer.confirm('真的删除行么', function (index) {
					var deleteId = data.menuId;
					var delOb = {menuId:deleteId};
					$.ajax({
						url:'<%=path%>forDeleteBackstageMenuRecord.action',
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
							layer.closeAll();
							table.reload('test');
						}
					})
				});
			} else if (obj.event === 'edit') {
				layer.open({
					type: 2,
					content: 'toDialogUpdateBackstageMenu.view',
					offset: ['10%', '40%'],
					title: '提交',
					area: ['350px', '320px'],
					btn: ['提交', '关闭'],
					btn1: function (index, layero) {
						var menuName = $(layero).find('iframe')[0].contentWindow.update_menu.value;
						var menuUrl = $(layero).find('iframe')[0].contentWindow.update_menu_url.value;
						var menuRemark = $(layero).find('iframe')[0].contentWindow.menu_ascription.value;
						var menuByOne = $(layero).find('iframe')[0].contentWindow.menu_by.value;
						var menuId = data.menuId;
						if (menuByOne.length>0||menuName.length>0||menuUrl.length>0||menuRemark.length>0)
						{
							var upOb = {
								menuName:menuName,
								menuUrl:menuUrl,
								menuRemark:menuRemark,
								menuPid:menuByOne,
								menuId:menuId
							};
							$.ajax({
								url: "<%=path%>forUpdateBackstageMenuRecord.action", //请求的url地址
								dataType: "json", //返回格式为json
								async: true,//请求是否异步，默认为异步，这也是ajax重要特性
								data: upOb, //参数值
								type: "post", //请求方式
								success: function (msg) {
									layer.closeAll();
									layer.msg(msg.msg);
									table.reload('test');
								},
								error: function () {
									layer.msg("系统忙请重试");
									layer.closeAll();
									table.reload('test');
								}
							})
						}
					},
					btn2: function () {
						layer.closeAll();
					}
				})
			} else if (obj.event === 'able'){
				var menuIdent = data.menuId;
				var menuType = data.menuStatus;
				var menuName = data.menuName;
				if(menuName!=='后台菜单管控'&&menuName!=='业主菜单管控'&&menuName!=='菜单管理')
				{
					if (menuType==='启用')
					{
						menuType = '1';
					} else {
						menuType = '0';
					}
					var ableOb = {menuId:menuIdent,menuStatus:menuType};
					$.ajax({
						url: "<%=path%>enableAndDisableSet.action", //请求的url地址
						dataType: "json", //返回格式为json
						async: true,//请求是否异步，默认为异步，这也是ajax重要特性
						data: ableOb, //参数值
						type: "post", //请求方式
						success: function (msg) {
							layer.closeAll();
							layer.msg(msg.msg);
							table.reload('test');
						},
						error: function () {
							layer.msg("系统忙请重试");
							layer.closeAll();
							table.reload('test');
						}
					})
				} else {
					layer.msg('您不能禁用这个菜单')
				}

			}
		});

		$('#search-form').on('click', function () {
			var menuPid = $("#menu_grade").val();
			var menuName = $("#menu_name").val();
			table.reload('test', {
				method: 'post'
				, where: {
					'menuPid': menuPid,
					'menuName': menuName
				}
				, page: {
					curr: 1
				}
			});
		});

		$('#add_menu_one').on('click', function () {
			layer.open({
				type: 2,
				content: 'jsp/ljm_add_parent_menu.jsp',
				offset: ['10%', '40%'],
				title: '提交',
				area: ['350px', '320px'],
				btn: ['提交', '关闭'],
				btn1: function (index, layero) {
					var menuName = $(layero).find('iframe')[0].contentWindow.parent_menu.value;
					var menuUrl = $(layero).find('iframe')[0].contentWindow.parent_menu_url.value;
					var menuRemark = $(layero).find('iframe')[0].contentWindow.menu_ascription.value;
					if (menuName.length>0)
					{
						if (menuUrl.length>0)
						{
							if (menuRemark.length>0)
							{
								var addOne = {
									menuName: menuName,
									menuUrl: menuUrl,
									menuRemark: menuRemark,
									menuPid: 0
								};
								$.ajax({
									url: "<%=path%>forAddBackstageMenuRecord.action", //请求的url地址
									dataType: "json", //返回格式为json
									async: true,//请求是否异步，默认为异步，这也是ajax重要特性
									data: addOne, //参数值
									type: "post", //请求方式
									success: function (msg) {
										layer.closeAll();
										layer.msg(msg.msg);
										table.reload('test');
									},
									error: function () {
										layer.msg("系统忙请重试");
										layer.closeAll();
										table.reload('test');
									}
								})
							} else {
								layer.msg("请填写菜单归属");
							}
						} else {
							layer.msg("请填写菜单路径");
						}
					} else {
						layer.msg("请填写菜单名称");
					}
				},
				btn2: function () {
					layer.closeAll();
				}
			})
		});

		$('#add_menu_two').on('click', function () {
			layer.open({
				type: 2,
				content: 'toDialogAddBackstageChildrenMenu.view',
				offset: ['10%', '40%'],
				title: '提交',
				area: ['350px', '350px'],
				btn: ['提交', '关闭'],
				btn1: function (index, layero) {
					var menuName = $(layero).find('iframe')[0].contentWindow.parent_menu.value;
					var menuUrl = $(layero).find('iframe')[0].contentWindow.parent_menu_url.value;
					var menuRemark = $(layero).find('iframe')[0].contentWindow.menu_ascription.value;
					var menuByOne = $(layero).find('iframe')[0].contentWindow.menu_by.value;
					if (menuName.length>0)
					{
						if (menuUrl.length>0)
						{
							if (menuRemark.length>0)
							{
								if (menuByOne.length>0)
								{
									var addTwo = {
										menuName: menuName,
										menuUrl: menuUrl,
										menuRemark: menuRemark,
										menuPid: menuByOne
									};
									$.ajax({
										url: "<%=path%>forAddBackstageMenuRecord.action", //请求的url地址
										dataType: "json", //返回格式为json
										async: true,//请求是否异步，默认为异步，这也是ajax重要特性
										data: addTwo, //参数值
										type: "post", //请求方式
										success: function (msg) {
											layer.closeAll();
											layer.msg(msg.msg);
											table.reload('test');
										},
										error: function () {
											layer.msg("系统忙请重试");
											layer.closeAll();
											table.reload('test');
										}
									})
								} else {
									layer.msg("请填写菜单所属一级菜单");
								}
							} else {
								layer.msg("请填写菜单归属");
							}
						} else {
							layer.msg("请填写菜单路径");
						}
					} else {
						layer.msg("请填写菜单名称");
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
