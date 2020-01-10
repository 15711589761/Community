<%--
  Created by IntelliJ IDEA.
  User: Ven
  Date: 2019/12/23
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = application.getContextPath() + "/";
	String JsPath = path + "js/";
	String cssPath = path + "css/";
%>
<html>
<head>
	<title>在职人员性别统计</title>
	<script src=<%=JsPath + "jquery-3.4.1.min.js" %>></script>
	<script src=<%=JsPath + "echarts.js" %>></script>

</head>
<body>
<div id="main" style="width: 100%;height:100%; align-content: center"></div>

<script type="text/javascript">

	var roleName = [];
	var maleData = [];
	var femaleDate = [];

	$(function () {
		$.ajax({
			method: "POST",
			url: <%=path%>+"postSexCount.action",
			dataType: "json",
			success: function (list) {

				for (var i = 0; i < list.length; i++) {

					roleName.push(list[i].roleName);
					maleData.push(list[i].maleNumber);
					femaleDate.push(list[i].femaleNumber);

				}

				columnar();

			},
			error: function () {
				alert("服务器正忙");
			}
		});
	});


	var myChart = echarts.init(document.getElementById('main'));

	function columnar() {

	var	option = {
			title: {
				text: '岗位男女人数统计表'
			},
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				data: ['男', '女']
			},
			toolbox: {
				show: true,
				feature: {
					dataView: {show: true, readOnly: false},
					magicType: {show: true, type: ['line', 'bar']},
					restore: {show: true},
					saveAsImage: {show: true}
				}
			},
			calculable: true,
			xAxis: [
				{
					type: 'category',
					data: roleName
				}
			],
			yAxis: [
				{
					type: 'value'
				}
			],
			series: [
				{
					name: '男',
					type: 'bar',
					data: maleData,
					markPoint: {
						data: [
							{type: 'max', name: '最大值'},
							{type: 'min', name: '最小值'}
						]
					},
					markLine: {
						data: [
							{type: 'average', name: '平均值'}
						]
					}
				},
				{
					name: '女',
					type: 'bar',
					data: femaleDate,
					markPoint: {
						data: [
							{type: 'max', name: '最大值'},
							{type: 'min', name: '最小值'}
						]
					},
					markLine: {
						data: [
							{type: 'average', name: '平均值'}
						]
					}
				}
			]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);


	}


</script>


</body>
</html>