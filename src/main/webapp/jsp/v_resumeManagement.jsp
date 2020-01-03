<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<title>简历管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"layui/css/layui.css"%>>
	<script src=<%=path+"layui/layui.js"%>></script>
</head>
<body>

<div class="demoTable" style="text-align: center">
	<br/>
	<br/>
	<div class="layui-inline">
		简历上传时间：
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="startDate" name="startDate" placeholder="yyyy-MM-dd">
		</div>
		至
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="endDate" name="endDate" placeholder="yyyy-MM-dd">
		</div>
		上传者：
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="userName" name="userName" placeholder="请输入名称">
		</div>
		<button class="layui-btn" data-type="reload" id="search-form">查询</button>
	</div>
</div>
<br/>
<table class="layui-hide" id="resumeData" lay-filter="resumeData"></table>

<script>
	layui.use(['table','form'],function(){
		var table=layui.table
			,$=layui.$
			,form=layui.form;

		table.render({
			elem: '#resumeData'
			,url:<%=path%>+'/getResumeData.action'
			,page:true
			,limit:5
			,limits:[5,10,15,20]
			,id:'resumeData'
			,title: '简历数据表'
			,cols: [[
				{field:'resumeId', title: 'id', sort: true,align:'center'}
				,{field:'resumeTitle', title: '简历标题',align:'center'}
				,{field:'applicantName', title: '求职者姓名',align:'center'}
				,{field:'applicantTel',  title: '联系电话',align:'center'}
				,{field:'uploadDate',  title: '上传日期',align:'center'}
				,{field:'resumeUrl', title: '简历地址',align:'center',hide:true}
				,{fixed: 'right',title: '操作', align:'center', toolbar: '#barDemo'}
			]]
		});

		$('#search-form').on('click', function () {
			var startDate = $('#startDate').val();
			var endDate = $('#endDate').val();
			var userName = $('#userName').val();
			table.reload('resumeData', {
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
		table.on('tool(resumeData)', function(obj){
			var data = obj.data;
			if(obj.event === 'del'){
				layer.confirm('真的执行此操作吗?', function(index){

					layui.$.ajax({
						type: "post",
						url: <%=path%>+"delResume.action",
						data: {
							targetId:data.resumeId,
							operator:null,
							operatorId:null
						},
						dataType: "json",
						async: true,
						success: function (msg) {
								if (msg.resultMsg === "done")
								{
									layer.msg("文件删除成功！");
									table.reload("resumeData")

								} else {
									layer.msg("文件删除失败!请重新操作，若多次操作失败，请联系管理员");
								}

						},
						error: function () {
							layer.msg("系统故障，请联系维护人员");
						}
					})
				});
			}
			else if (obj.event==='download') {
				layer.confirm('确认下载', function (index) {
					var ob={targetId:data.resumeId};
					layui.$.ajax({
						type: "post",
						url: <%=path%>+"checkFileExists.action",
						data: ob,
						dataType: "json",
						async: true,
						success: function (msg) {
							if (msg.resultMsg=== "存在"){
								var documentId = data.resumeId;
								//下载的文件名
								var fileName=data.resumeTitle;
								//请求下载的路径
								var url=<%=path%>+"/resumeDownload.action?resumeId="+documentId;
								var xmlResquest = new XMLHttpRequest();
								xmlResquest.open("POST",url, true);
								xmlResquest.setRequestHeader("Content-type", "application/json");
								xmlResquest.setRequestHeader("Authorization", "Bearer 6cda86e3-ba1c-4737-972c-f815304932ee");
								xmlResquest.responseType = "blob";
								xmlResquest.onload = function (oEvent) {
									var content = xmlResquest.response;
									var elink = document.createElement('a');
									elink.download = fileName;
									elink.style.display = 'none';
									var blob = new Blob([content]);
									//解决下载不存在文件的问题，根据blob大小判断
									if(blob.size===0){
										layer.msg('服务器没找到此文件，请联系管理员!');
									}else{
										elink.href = URL.createObjectURL(blob);
										document.body.appendChild(elink);
										elink.click();
										document.body.removeChild(elink);
										layer.close(index);
									}
								};
								xmlResquest.send();

							}
							else {
								layer.msg("文件不存在,路径错误或者已经被删除！")
							}

						},
						error: function () {
							layer.msg("数据交互异常，请联系维护人员");
						}
					});

				});
			}
		});

		//doAjax
		function doAjax(ob) {

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
	<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="download">下载</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

</body>
</html>
