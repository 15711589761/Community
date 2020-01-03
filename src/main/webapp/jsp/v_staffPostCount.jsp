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
	<title>职位人员统计</title>
	<script src=<%=JsPath + "jquery-3.4.1.min.js" %>></script>
	<script src=<%=JsPath + "echarts.js" %>></script>

</head>
<body>
<div id="main" style="width: 100%;height:100%; align-content: center"></div>

<script type="text/javascript">

	var nameArr = [];
	var obArr = [];

	$(function () {
		$.ajax({
			method: "POST",
			url: <%=path%>+"postCount.action",
			dataType: "json",
			success: function (postCountBeanList) {

				for (var i = 0; i < postCountBeanList.length; i++) {
					//alert(postCountList[i].name);
					nameArr.push(postCountBeanList[i].roleName);
					obArr.push({
						value: postCountBeanList[i].roleNumber,
						name: postCountBeanList[i].roleName
					});

				}

				rose();// 创建饼图

			},
			error: function () {
				alert("服务器正忙");
			}
		});
	});


	var myChart = echarts.init(document.getElementById('main'));

	function rose() {

		// 基于准备好的dom，初始化echarts实例


		var option = {
			title: {
				text: '职位人员统计',
				x: 'center',
				textStyle: {

					fontSize: 18
				}
			},
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient: 'vertical',
				left: 'left',
				data: nameArr,
				label: {
					normal: {
						textStyle: {

							fontSize: 18
						}
					}

				}
			},
			series: [
				{
					name: '人员',
					type: 'pie',
					radius: '55%',
					center: ['50%', '60%'],
					label: {
						normal: {
							textStyle: {

								fontSize: 18
							}
						}

					},
					data:
					obArr
					,
					itemStyle: {
						emphasis: {
							shadowBlur: 10,
							shadowOffsetX: 0,
							shadowColor: 'rgba(0, 0, 0, 0.5)'
						}
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