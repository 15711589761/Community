<%--
  Created by IntelliJ IDEA.
  User: 93419
  Date: 2019/11/17
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String jspath = request.getContextPath() + "/js/";
	String cssPath = request.getContextPath() + "/css/";
	String Path = application.getContextPath()+"/layui/";
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>商铺管理</title>
	<link rel="stylesheet" href=<%=Path+"css/layui.css"%>>
	<script type="text/javascript" src=<%=Path+"layui.js"%>></script>
	<script type="text/javascript" src=<%=jspath + "sx_store_action.js"%>></script>
</head>
<body>
<div class="layui-fluid">
	<div class="layui-card">
		<div class="demoTable" style="text-align: center">
			<div class="layui-inline">
				售出时间：
				<div class="layui-input-inline">
					<input type="date" class="layui-input" id="startDate" name="startDate" placeholder="yyyy-MM-dd" >
				</div>

				归属者：
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="userName" name="userName" placeholder="请输入用户名" >
				</div>

				<button class="layui-btn" data-type="reload" id="search" >搜索</button>
				<button class="layui-btn" data-type="add" id="newAdd">新增</button>
			</div>
		</div>


		<div class="demoTable" style="text-align: center">
		</div>
		<script type="text/html" id="storeAction">
			<a class="layui-btn layui-btn-xs" id="del-info" lay-event="del">删除</a>
			<a class="layui-btn layui-btn-xs" id="update-info" lay-event="update">修改</a>
		</script>
		<table class="layui-table" lay-data="{url:'/Community/findStore.action', page:true, id:'storeTable'}" lay-filter="demo">
			<thead>
			<tr>

				<th lay-data="{field:'storeId', sort: true, hide: true}">商铺ID</th>
				<th lay-data="{field:'storeAddress'}">商铺地址</th>
				<th lay-data="{field:'storeAttr'}">商铺归属者</th>
				<th lay-data="{field:'sellPrice'}">售价</th>
				<th lay-data="{field:'finalPrice'}">最终成交价</th>
				<th lay-data="{field:'sellDate'}">售出日期</th>
				<th lay-data="{field:'storeTel'}">商铺电话</th>
				<th lay-data="{fixed: 'right',width:200,align:'center', toolbar: '#storeAction'}">操作</th>
			</tr>
			</thead>
		</table>
		<script>
			layui.use('table', function () {
				var table = layui.table;
				//监听工具条
				table.on('tool(demo)', function (obj) {
					var data = obj.data;
					if (obj.event === 'del') {
						layer.confirm('敏感操作是否确认删除', function (index) {
							var delid = data.storeId;
							var ob = {
								storeId:delid,
							};
							delAjax(ob);
							obj.del();
							layer.close(index);
						});

					}else if (obj.event === 'update')
					{
						layer.open({
							type: 1,
							btn: ['修改', '取消'],
							btn1: function (index, layero) {
								var address = $('#update-address').val();
								var tel = $('#update-tel').val();
								var attr = $('#update-attr').val();
								var id = data.storeId;
								var ob = {
									storeId: id,
									storeAddress: address,
									storeTel: tel,
									storeAttr: attr
								};
								updateAjax(ob);
								setTimeout(function () {
									table.reload('storeTable');
								},1000);
								layer.close(index);
							},
							content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
								'<div class="layui-inline">' +
								'商铺地址：<div>' +
								'<input type="text" id="update-address" autocomplete="off" class="layui-input" >' +
								'</div><br>' +
								'电话：<div>' +
								'<input type="text" id="update-tel" autocomplete="off" class="layui-input">' +
								'</div><br>' +
								'归属者：<div>' +
								'<input type="text" id="update-attr" autocomplete="off" class="layui-input">' +
								'</div><br>' +
								'</div>' +
								'</div>'
						});
					}
				});

				var $ = layui.$, active = {};
				$(function () {

					var $ = layui.$;
					$("#search").on('click', function () {
						var type = $(this).data('type');
						active[type] ? active[type].call(this) : '';
					});

					$('#newAdd').on('click', function(){
						var othis = $(this), method = othis.data('method');
						active[method] ? active[method].call(this, othis) : '';


						layer.open({
							type: 1,
							offset: 'auto',
							moveType: 1 ,//拖拽模式，0或者1
							content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
								'<div class="layui-inline">' +
								'商铺地址：<div>' +
								'<input type="text" id="new-address" autocomplete="off" class="layui-input">' +
								'</div><br>' +
								'归属者：<div>' +
								'<input type="text" id="new-attr" autocomplete="off" class="layui-input">' +
								'</div><br>' +
								'售价：<div>' +
								'<input type="text" id="new-sellPrice" autocomplete="off" class="layui-input">' +
								'</div><br>' +
								'最终成交价：<div>' +
								'<input type="text" id="new-finalPrice" autocomplete="off" class="layui-input">' +
								'</div><br>' +
								'商铺电话：<div>' +
								'<input type="text" id="#new-tel" autocomplete="off" class="layui-input">' +
								'</div><br>' +
								'</div>' +
								'</div>',
							skin: 'layui-layer-rim', //加上边框
							area: ['450px', '580px'] ,//宽高
							anim: 5,
							btn: ['新增', '取消'], //可以无限个按钮
							btn1:function () {
								var myDate = new Date();
								var time = myDate.toLocaleDateString().split('/').join('-');
								var address = $('#new-address').val();
								var attr = $('#new-attr').val();
								var sellPrice = $('#new-sellPrice').val();
								var finalPrice = $('#new-finalPrice').val();
								var storeTel = $('#new-tel').val();

								if (address.length>0){
									if (attr.length>0){
										var ob = {
											storeAddress:address,
											storeAttr:attr,
											sellPrice:sellPrice,
											finalPrice:finalPrice,
											sellDate:time,
											storeTel:storeTel
										};
										$.ajax({
											type: 'post',
											url: 'addStore.action',
											data: ob,
											dataType: 'json',
											async: true,
											success : function(msg) {
												if (msg){
													layer.msg("添加成功");
												} else {
													layer.msg("添加失败");
												}
												layer.closeAll();

											},
											error : function() {
												alert('服务器出错');
											}

										});
										layer.close();

									} else {
										layer.msg("请填写密码");
									}
								} else {
									layer.msg("请填写用户名");
								}
							}
						});


					});



				});
			});
		</script>
	</div>
</div>

</body>
</html>
