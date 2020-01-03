<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/7
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	String Path = application.getContextPath() + "/layui/";
	String jspath = request.getContextPath() + "/js/";
	String cssPath = request.getContextPath() + "/css/";
%>
<html>
<head>
	<title>安保进行排班</title>
	<link rel="stylesheet" href=<%=Path+"css/layui.css"%>>
	<script type="text/javascript" src=<%=Path + "layui.js"%>></script>
	<script type="text/javascript" src=<%=jspath + "sx_getdate_gettime_canlendar.js"%>></script>
</head>
<body>
<div class="layui-fluid">
	<div class="layui-card">
		<div id="bigDiv">
			<form action="safeFullcalendar.view" id="appoint" method="post" class="layui-form layui-form-pane" >
				<table class="layui-table">
					<thead><h1 class="h1" align="center">保安排班</h1></thead>
					<tr>
						<th>
							班次/日期
						</th>
						<th>
							<span id="mon">${requestScope.Date[0]}</span>
						</th>
						<th>
							<span id="tues">${requestScope.Date[1]}</span>
						</th>
						<th>
							<span id="wed">${requestScope.Date[2]}</span>
						</th>
						<th>
							<span id="thur">${requestScope.Date[3]}</span>
						</th>
						<th>
							<span id="fri">${requestScope.Date[4]}</span>
						</th>
						<th>
							<span id="sat">${requestScope.Date[5]}</span>
						</th>
						<th>
							<span id="sun">${requestScope.Date[6]}</span>
						</th>

					</tr>
					<c:if test="${requestScope.fullcalendar != null}">
						<c:forEach var="i" step="1" begin="0" items="${requestScope.fullcalendar}">
							<tr>
								<td>${i.key}</td>
								<td>
									<c:forEach var="x" step="1" begin="0" items="${i.value}">
										<c:if test="${x.workDate eq requestScope.Date[0]}">
											<button type="button" onclick="sendData(layui.$('#mon').text(),${requestScope.actionId},${x.workId})" class="layui-btn layui-btn-sm layui-btn-normal"><i
													class="layui-icon"></i>${x.staffName}</button>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach var="x" step="1" begin="0" items="${i.value}">
										<c:if test="${x.workDate eq requestScope.Date[1]}">
											<button type="button" onclick="sendData(layui.$('#tues').text(),${requestScope.actionId},${x.workId})" class="layui-btn layui-btn-sm layui-btn-normal"><i
													class="layui-icon"></i>${x.staffName}</button>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach var="x" step="1" begin="0" items="${i.value}">
										<c:if test="${x.workDate eq requestScope.Date[2]}">
											<button type="button" onclick="sendData(layui.$('#wed').text(),${requestScope.actionId},${x.workId})" class="layui-btn layui-btn-sm layui-btn-normal"><i
													class="layui-icon"></i>${x.staffName}</button>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach var="x" step="1" begin="0" items="${i.value}">
										<c:if test="${x.workDate eq requestScope.Date[3]}">
											<button type="button" onclick="sendData(layui.$('#thur').text(),${requestScope.actionId},${x.workId})" class="layui-btn layui-btn-sm layui-btn-normal"><i
													class="layui-icon"></i>${x.staffName}</button>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach var="x" step="1" begin="0" items="${i.value}">
										<c:if test="${x.workDate eq requestScope.Date[4]}">
											<button type="button" onclick="sendData(layui.$('#fri').text(),${requestScope.actionId},${x.workId})" class="layui-btn layui-btn-sm layui-btn-normal"><i
													class="layui-icon"></i>${x.staffName}</button>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach var="x" step="1" begin="0" items="${i.value}">
										<c:if test="${x.workDate eq requestScope.Date[5]}">
											<button type="button" onclick="sendData(layui.$('#sat').text(),${requestScope.actionId},${x.workId})" class="layui-btn layui-btn-sm layui-btn-normal"><i
													class="layui-icon"></i>${x.staffName}</button>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach var="x" step="1" begin="0" items="${i.value}">
										<c:if test="${x.workDate eq requestScope.Date[6]}">
											<button type="button" onclick="sendData(layui.$('#sun').text(),${requestScope.actionId},${x.workId})" class="layui-btn layui-btn-sm layui-btn-normal"><i
													class="layui-icon"></i>${x.staffName}</button>
										</c:if>
									</c:forEach>
								</td>

							</tr>

						</c:forEach>

					</c:if>

					<input type="button" value="上一周" class="layui-btn"
					        onclick="upWeek(${requestScope.actionId})">
					<input type="button" value="下一周" class="layui-btn"
					        onclick="nextWeek(${requestScope.actionId})">
				</table>

				<%--班次单选--%>
				<div class="layui-form-item">
					<label class="layui-form-label">班次选择</label>
					<div class="layui-input-inline">
						<select name="class" id="classTime">
							<option value=""></option>
							<option value="1">白班</option>
							<option value="2">晚班</option>
						</select>
					</div>
				</div>
				<%--排班选择--%>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">日期选择</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input" id="getDate">
						</div>
					</div>
				</div>
				<div>
					<button type="button" class="layui-btn layui-btn-normal layui-btn-radius" lay-event="arrange" id="fullyes">确定排班</button>
				</div>
			</form>
			<script>

				function checkDate(dateStr){
					var a = /^(\d{4})-(\d{2})-(\d{2})$/
					if (!a.test(dateStr)) {
						return false
					}else{
						return true;
					}
				}
				//日期限制-最小当前最大两周后
				layui.use('laydate', function () {
					var laydate = layui.laydate;
					var myDate = new Date();  //获取当前时间
					//常规用法
					laydate.render({
						elem: '#getDate',
						show: true, //直接显示
						min: myDate.toLocaleString(),  //转化当前时间格式
						max: 14
					});
				});

				// layui的下拉框需要重新渲染
				layui.use('form', function () {
					var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
					//但是，如果HTML是动态生成的，自动渲染就会失效
					//因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
					form.render();
					var $ = layui.$;
					$('#fullyes').on('click', function(){
						var x =$('#getDate').val();
						var y =$('#classTime').val();
						var id =${requestScope.actionId};
						if (y.length<1)
						{
							layer.msg("请选择班次");
						}else if (checkDate(x)===false)
						{
							layer.msg("请选择日期");
						}else
							{
								var ob = {
									workTime:x,
									workDate:y,
									getId:id
								};
								$.ajax({
									type: 'post',
									url: 'insertSaferFull.action',
									data: ob,
									dataType: 'json',
									async: true,
									success : function(msg) {
										if (msg){
											alert("排班成功");
										} else {
											alert("排班失败");
										}
										location.reload();
										layer.close();

									},
									error : function() {
										alert('失败 ');
									}

								});
							}
					});
				});
			</script>
		</div>
	</div>
</div>
</body>
</html>