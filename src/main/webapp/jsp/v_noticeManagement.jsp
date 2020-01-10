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
	<style>
		.layui-table-cell{
			height:auto !important;
		}
	</style>
</head>
<body>

<div class="demoTable" style="text-align: center">
	<br/>
	<br/>
	<div class="layui-inline">
		发布日期：
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="startDate" name="startDate" placeholder="yyyy-MM-dd">
		</div>
		至
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="endDate" name="endDate" placeholder="yyyy-MM-dd">
		</div>
		公告标题：
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="noticeTitle" name="noticeTitle" placeholder="标题内容">
		</div>
		<div class="layui-input-inline">
			<input type="hidden" class="layui-input" id="staffId" name="staffId" value="${requestScope.staffId}">
		</div>
		<button title="查询" class="layui-btn layui-icon layui-icon-search" data-type="reload" id="search-form"></button>
		<button title="增加公告" class="layui-btn layui-btn-danger layui-icon layui-icon-add-1" data-type="reload" id="addNew"></button>
	</div>
</div>
<br/>
<table class="layui-hide" id="noticeData" lay-filter="noticeData"></table>

<script>
	layui.use(['table','form'],function(){

		var table=layui.table
			,$=layui.$
			,form=layui.form;

		table.render({
			elem: '#noticeData'
			,url:<%=path%>+'/getNoticeData.action'
			,page:true
			,limit:10
			,limits:[5,10,15,20]
			,id:'noticeData'
			,title: '公告信息表'
			,cols: [[
				{field:'noticeId',title: 'id', sort: true,align:'center'}
				,{field:'noticeTitle', title: '公告标题',align:'center'}
				,{field:'noticeDetails', title: '公告内容',align:'center'}
				,{field:'issuerId',title: '发布者id',align:'center',hide:'true'}
				,{field:'issuer',title: '发布者',align:'center'}
				,{field:'issuedDate',title: '发布日期',align:'center'}
				,{fixed: 'right',width:'20%',title: '操作', align:'center', toolbar: '#barDemo'}
			]]
		});

		$('#search-form').on('click', function () {
			var startDate = $('#startDate').val();
			var endDate = $('#endDate').val();
			var noticeTitle = $('#noticeTitle').val();
			table.reload('noticeData', {
				method: 'post',
				where: {
					'startDate': startDate,
					'endDate': endDate,
					'noticeTitle': noticeTitle
				}, page: {
					curr: 1
				}
			})
		});

		$(function () {
			var today = new Date();//声明变量today 获取当前的时间日期
			var nowMonthStartDate =getMonthStartDate(today);
			var nowDate=getNowDate(today);
			$('#startDate').val(nowMonthStartDate);
			$('#endDate').val(nowDate);
		});


		$('#addNew').on('click', function () {
			layer.open({
				type: 2
				, title: '新增公告'
				, content: <%=path%>+'jsp/v_noticeInfoAdd.jsp'
				, area: ['400px', '300px']
			});
		});

		//监听事件
		table.on('tool(noticeData)', function(obj){
			var data = obj.data;
			if(obj.event === 'del'){
				layer.confirm('真的执行此操作吗?', function(index){
					var theId = data.noticeId;
					var ob = {
						methodName:"delNotice.action",
						targetId:theId,
						operatorId:$('#staffId').val()
				};
					doAjax(ob);
				});
			}
			else if (obj.event==='edit') {
				layer.open({
					type: 2
					,title: '公告信息修改'
					,content: <%=path%>+'jsp/v_noticeInfoUpdate.jsp'
					,area: ['400px', '300px']
					, success : function(layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find('#noticeTitle').val(data.noticeTitle);
						body.find("#noticeDetails").val(data.noticeDetails);
						body.find("#targetId").val(data.noticeId);
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
					if (msg.identify === "delNotice"){
						if (msg.resultMsg === "done")
						{
							layer.msg("删除成功！");
							table.reload("noticeData")

						} else {
							layer.msg("删除失败!请重新操作");
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


<script type="text/html" id="barDemo">

	<button title="公告修改" class="layui-btn layui-btn-sm layui-icon layui-icon-edit layui-btn-primary" lay-event="edit"></button>
	<button title="删除公告" class="layui-btn layui-btn-sm layui-icon layui-icon-delete layui-btn-danger" lay-event="del"></button>
</script>

</body>
</html>
