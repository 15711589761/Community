<%--
  Created by IntelliJ IDEA.
  User: ZKa1
  Date: 2020/1/3
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = application.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
	<style type="text/css">
		body, html, #allmap {
			width: 100%;
			height: 100%;
			overflow: hidden;
			margin: 0;
			font-family: "微软雅黑";
		}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
	<title>浏览器定位</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%>>
	<script src=<%=path+"/layui/layui.js"%> charset="utf-8"></script>
</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.331398, 39.897445);
	//放大倍数
	map.centerAndZoom(point, 15);
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用


	//设置定位按钮位置
	var opts = {anchor: BMAP_ANCHOR_BOTTOM_RIGHT};
	//设置缩放按钮位置及类型
	var ove = {anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_ZOOM};
	//将定位控件添加到地图上
	map.addControl(new BMap.GeolocationControl(opts));
	//添加缩放按钮
	map.addControl(new BMap.NavigationControl(ove));


	//单击获取点击的经纬度
	// map.addEventListener("click", function (e) {
	// 	alert(e.point.lng + "," + e.point.lat);
	// });

	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function (r) {
		if (this.getStatus() == BMAP_STATUS_SUCCESS) {
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			map.panTo(r.point);
			// alert('您的位置：'+r.point.lng+','+r.point.lat);

			var p1 = new BMap.Point(r.point.lng, r.point.lat);
			var p2 = new BMap.Point(118.185284, 24.48284);

			//线路规划
			var driving = new BMap.DrivingRoute(map, {renderOptions: {map: map, autoViewport: true}});
			driving.search(p1, p2);

		} else {
			alert('failed' + this.getStatus());
		}
	}, {enableHighAccuracy: true});


	//关于状态码
	//BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
	//BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
	//BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
	//BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
	//BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
	//BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
	//BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
	//BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)
	//BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)
</script>

