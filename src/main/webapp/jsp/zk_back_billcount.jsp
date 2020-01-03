<%--
  Created by IntelliJ IDEA.
  User: ZKa1
  Date: 2019/12/30
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>票据统计</title>
	<script src=<%=path + "/js/jquery-3.4.1.js" %>></script>
	<script src=<%=path + "/js/echarts.js" %>></script>
	<script src=<%=path + "/js/json2.js" %>></script>
</head>
<body>
<div id="echarts" style="width: 1000px;height:400px;"></div>
<div id="echarts-rose" style="width: 1000px;height:400px;"></div>
</body>
<script>
	var nameArr = [];
	var valueArr = [];
	var obArr = [];

	$(function () {
		$.ajax({
			method: "POST",
			url: "/Community/billCount",
			dataType: "json",
			success: function (arr) {
				for (var i = 0; i < arr.length; i++) {
					// 普通柱状图使用的数据
					nameArr.push(arr[i].billname+"/元");
					valueArr.push(arr[i].record);
					// 南丁格尔玫瑰圆饼图使用的数据
					obArr.push({
						name: arr[i].billname+"/元",
						value: arr[i].record
					});
				}
				createEchars();// 创建普通柱状图
				rose();// 创建南丁格尔玫瑰圆饼图
			},
			error: function () {
				alert("服务器正忙");
			}
		});
	});


	//普通柱状图
	function createEchars() {

		//基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('echarts'));//dark为暗黑主题 不要可以去掉

		// 指定图表的配置项和数据
		var option = {
			color: ['#22b101'],
			title: {
				text: '票据柱状图统计'
			},
			//是否显示提示框组件
			tooltip: {},
			legend: {
				data: ['柱状数据表']
			},
			xAxis: {
				data: nameArr
			},
			yAxis: {},
			series: [{
				name: '数据',
				type: 'bar',
				data: valueArr
			}]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);

	}

	//南丁格尔玫瑰图
	function rose() {
		//基于准备好的dom，初始化echarts实例
		var myChart2 = echarts.init(document.getElementById('echarts-rose'));//dark为暗黑主题 不要可以去掉

		var option = {
			title: {
				text: '票据扇形统计'
			},
			//是否显示提示框组件
			tooltip: {},
			series: [{
				name: '访问来源',
				type: 'pie',
				radius: '55%',
				data: obArr
			}]
		};
		myChart2.setOption(option);
	}
</script>
</html>
