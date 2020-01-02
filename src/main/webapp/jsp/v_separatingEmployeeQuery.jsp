<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<title>离职人员管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path+"layui/layui.js"%>></script>
	<script src=<%=path+"js/venTool.js"%>></script>
</head>
<body>

<div class="demoTable" style="text-align: center">
	<br/>
	<br/>
	<div class="layui-inline">
		离职时间：
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="startDate" name="startDate" placeholder="yyyy-MM-dd">
		</div>
		至
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="endDate" name="endDate" placeholder="yyyy-MM-dd">
		</div>
		姓名：
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="userName" name="userName" placeholder="请输入名称">
		</div>
		<button class="layui-btn" data-type="reload" id="search-form">查询</button>
	</div>
</div>
<br/>
<table class="layui-hide" id="staffData" lay-filter="staffData"></table>

<script>
	layui.use(['table','form'],function(){
		var table=layui.table
			,$=layui.$
			,form=layui.form;

		table.render({
			elem: '#staffData'
			,url:<%=path%>+'/getSeparatingEmployData.action'
			,page:true
			,limit:5
			,limits:[5,10,15,20]
			,id:'staffData'
			,title: '离职人员信息表'
			,cols: [[
				{field:'staffId', title: 'id', sort: true,align:'center'}
				,{field:'staffName', title: '姓名',align:'center',templet:'#staffNameTpl'}
				,{field:'jobNumber', title: '工号',align:'center'}
				,{field:'staffSex',  title: '性别',align:'center',templet: '#sexTpl'}
				,{field:'staffAge', title: '年龄',align:'center'}
				,{field:'staffBirthday', title: '出生日期',align:'center'}
				,{field:'staffTel', title: '联系电话',align:'center'}
				,{field:'staffAddress', title: '住址',align:'center'}
				,{field:'entryDate', title: '入职日期',align:'center'}
				,{field:'staffTermDate', title: '离职日期',align:'center'}
				,{fixed: 'right',title: '操作', align:'center', toolbar: '#barDemo'}
			]]
		});

		$('#search-form').on('click', function () {
			var startDate = $('#startDate').val();
			var endDate = $('#endDate').val();
			var userName = $('#userName').val();
			table.reload('staffData', {
				method: 'post',
				where: {
					'startDate': startDate,
					'endDate': endDate,
					'userName': userName
				}, page: {
					curr: 1
				}
			})
		});

		//监听事件
		table.on('tool(staffData)', function(obj){
			var data = obj.data;
			if(obj.event === 'del'){
				layer.confirm('真的执行此操作吗?', function(index){
					var theId = data.staffId;
					var ob = {
						methodName:"delEmployeeInfo.action",
						targetId:theId
					};
					doAjax(ob);
				});
			}
		});

		//doAjax
		function doAjax(ob) {
			layui.$.ajax({
				type: "post",
				url: <%=path%>+ob.methodName,
				data: ob,
				dataType: "json",
				async: true,
				success: function (msg) {
					if (msg.identify === "del"){
						if (msg.resultMsg === "success")
						{
							layer.msg("执行成功！");
							table.reload("staffData")

						} else {
							layer.msg("执行失败!请重新操作");
						}
					}
				},
				error: function () {
					layer.msg("系统故障，请联系维护人员");
				}
			})
		}
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

	});


</script>

<script type="text/html" id="sexTpl">
	{{#  if(d.staffSex === '女'){ }}
	<span style="color: #F581B1;">{{ d.staffSex }}</span>
	{{#  } else { }}
	{{ d.staffSex }}
	{{#  } }}
</script>
<script type="text/html" id="staffNameTpl">
	<span style="color: #8765ff;">{{ d.staffName }}</span>
</script>

<script type="text/html" id="barDemo">
	<a class="layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

</body>
</html>
