<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<title>工作人员管理</title>
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
		入职时间：
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
		<button title="查询" class="layui-btn layui-icon layui-icon-search" data-type="reload" id="search-form"></button>
		<button title="职员信息新增" class="layui-btn layui-btn-danger layui-icon layui-icon-add-1" data-type="reload" id="addNew"></button>

	</div>
</div>
<br/>
<table class="layui-hide" id="staffData" lay-filter="saffData"></table>

<script>
	layui.use(['table','form'],function(){
		var table=layui.table
			,$=layui.$
			,form=layui.form;
		
		table.render({
			elem: '#staffData'
			,url:<%=path%>+'/getStaffData.action'
			,page:true
			,limit:10
			,limits:[5,10,15,20]
			,id:'staffData'
			,title: '在职人员数据表'
			,cols: [[
				{field:'staffId', width:'5%',title: 'id', sort: true,align:'center'}
				,{field:'staffName',width:'10%', title: '姓名',align:'center',templet:'#staffNameTpl'}
				,{field:'jobNumber',width:'10%', title: '工号',align:'center',templet:'#jobNumberTpl'}
				,{field:'staffSex',width:'5%',title: '性别',align:'center',templet: '#sexTpl'}
				,{field:'staffAge',width:'5%',title: '年龄',align:'center'}
				,{field:'staffBirthday',width:'10%', title: '出生日期',align:'center'}
				,{field:'staffTel', width:'10%',title: '联系电话',align:'center'}
				,{field:'staffAddress',width:'15%', title: '住址',align:'center'}
				,{field:'entryDate', width:'10%',title: '入职日期',align:'center'}
				,{field:'status',title: '在职情况',align:'center',hide:'true'}
				,{fixed: 'right',width:'20%',title: '操作', align:'center', toolbar: '#barDemo'}
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


		$('#addNew').on('click', function () {
			layer.open({
				type: 2
				, title: '职员信息登记'
				, content: <%=path%>+'jsp/v_staffInfoAdd.jsp'
				, area: ['450px', '600px']

			});
		});

		$(function () {
			var today = new Date();//声明变量today 获取当前的时间日期
			var nowMonthStartDate =getMonthStartDate(today);
			var nowDate=getNowDate(today);
			$('#startDate').val(nowMonthStartDate);
			$('#endDate').val(nowDate);
		});

		//监听事件
		table.on('tool(saffData)', function(obj){
			var data = obj.data;
			if(obj.event === 'dimission'){
				layer.confirm('真的执行此操作吗?', function(index){
					var theId = data.staffId;
					var jobNumber=data.jobNumber;
					var ob = {
						methodName:"dimission.action",
						targetId:theId,
						operator:null,
						operatorId:null,
						jobNumber:jobNumber
					};
					doAjax(ob);
				});
			}
			else if (obj.event==='edit') {
				layer.open({
					type: 2
					,title: '职员信息修改'
					,content: <%=path%>+'jsp/v_staffInfoUpdate.jsp'
					,area: ['500px', '500px']
					, success : function(layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find('#staffId').val(data.staffId);
						body.find("#staffName").val(data.staffName);
						body.find("#joinDate").val(data.entryDate);
						body.find("#staffAddress").val(data.staffAddress);
						body.find("#staffTel").val(data.staffTel);
						body.find("#staffBirthday").val(data.staffBirthday);
						body.find(":radio[name='sex'][value='" + data.staffSex + "']").prop("checked","checked");
					}
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
					if (msg.identify === "dimission"){
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

<script type="text/html" id="jobNumberTpl">
	{{#  if(d.jobNumber ===""|d.jobNumber===null){ }}
	<span style="color: #35d700;">未分配</span>
	{{#  } else { }}
	{{ d.jobNumber }}
	{{#  } }}
</script>
<script type="text/html" id="staffNameTpl">
	<span style="color: #2504af;">{{ d.staffName }}</span>
</script>

<script type="text/html" id="barDemo">

	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="dimission">离职</a>

</script>

</body>
</html>
