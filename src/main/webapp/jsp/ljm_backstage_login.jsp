<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: 啤酒沫。
  Date: 2019/12/17
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href=<%=path + "css/backstageloginbasic.css"%>>
	<link rel="stylesheet" href=<%=path + "css/login.css"%>>
	<script type="text/javascript" src=<%=path + "js/jquery-3.4.1.min.js"%>></script>
	<script type="text/javascript" src=<%=path + "js/basic.js"%>></script>
	<script type="text/javascript" src=<%=path + "js/three.js"%>></script>
	<script type="text/javascript" src=<%=path + "js/md5.js"%>></script>
	<script type="text/javascript" src=<%=path + "js/ljm_backstage_login.js"%>></script>
	<title>智慧社区管理平台</title>
</head>
<body>
<div class="pagewrap">
	<div class="main" id="screen">
		<div class="login-page dsj-login">
			<i class="ico ico-star1"></i>
			<i class="ico ico-star2"></i>
			<i class="ico ico-star3"></i>
			<i class="ico ico-star4"></i>
			<i class="ico ico-star5"></i>
			<h3 class="platform-name">
				智慧社区管理平台
				<input type="hidden" id="backstage_login_fail_massage_isEmpty" value="${requestScope.isEmpty}"/>
				<input type="hidden" id="backstage_login_fail_massage_authority" value="${requestScope.authority}"/>
				<input type="hidden" id="backstage_login_fail_massage_error" value="${requestScope.error}">
				<input type="hidden" id="backstage_login_fail_massage_verification" value="${requestScope.verification}"/>
			</h3>
			<form role="form" method="post" id="backstage_login_form" onsubmit="return isEmptyInput()" action=<%=path + "backstageLogin.action"%>>
				<div class="login-block">
					<div class="login-pop">
						<div class="login-body clearfix">
							<div class="login-info">
								<div class="form-list">
									<div class="li">
										<div class="ele-wrap">
											<input type="text" class="form-control" id="backstage_login_name" name="loginName" placeholder="请输入用户名" oninput="this.value=value.replace(/[^u4e00-u9fa5w]/g,'')"/>
										</div>
									</div>
									<div class="li">
										<div class="ele-wrap">
											<input type="password" class="form-control" id="backstage_login_password" name="loginPassWord" placeholder="请输入密码" oninput="this.value=value.replace(/[^u4e00-u9fa5w]/g,'')"/>
										</div>
									</div>
									<div class="li">
										<div class="ele-wrap">
											<div class="code-img">
												<img alt="验证码" id="image_code" height="36" width="100%"
												     onclick="getVerify(this)" src=<%=path + "getVerify.image"%>/>
											</div>
											<div class="proving-code">
												<input type="text" class="form-control" name="imageCode" id="backstage_login_image_code" placeholder="验证码">
											</div>
										</div>
									</div>
									<div class="li">
										<div class="ele-wrap">
											<button class="btn btn-blue">登 录</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>
<script>
	$(function () {
		var winH = $(window).height();
		var starH = winH + 50;
		var starW = 998 / 998 * starH;
		$('.ico-star1').css({
			'height': starH + 'px',
			'width': starW + 'px'
		});
	});
</script>
<script type="text/javascript">
	document.writeln("<div class=\"snow\" style=\"height:1200px;position:fixed; left:0px; top:0px; right:0px; bottom:0px; pointer-events: none;z-index: 9999;\"><canvas width=\"1904\" height=\"913\" style=\"position: absolute;left: 0;top: 0;\"></canvas></div>");

	$(function () {

		if (/MSIE 6|MSIE 7|MSIE 8/.test(navigator.userAgent)) {
			return
		}
		var container = document.querySelector(".snow");
		// IE9-10 pointer-events兼容
		if (/MSIE 9|MSIE 10/.test(navigator.userAgent)) {
			$(container).bind('click mousemove', function (evt) {
				this.style.display = 'none';
				var x = evt.pageX, y = evt.pageY;
				if ($(document).scrollTop() > 0 || $(document).scrollTop() > 0) {
					x = x - $(document).scrollLeft() + 1;
					y = y - $(document).scrollTop() + 1;
				}
				evt.preventDefault();
				evt.stopPropagation();
				var under = document.elementFromPoint(x, y);
				var evtType = evt.type === 'click' ? 'click' : 'mouseenter';
				if (evt.type === 'click') {
					$(under)[0].click();
				} else {
					$(under).trigger('mouseenter');
				}
				$('body').css('cursor', 'default');
				this.style.display = '';
				return false;
			});
		}
		var containerWidth = $(container).width();
		var containerHeight = $(container).height();
		var particle;
		var camera;
		var scene;
		var renderer;
		var mouseX = 0;
		var mouseY = 0;
		var windowHalfX = window.innerWidth / 2;
		var windowHalfY = window.innerHeight / 2;
		var particles = [];
		var particleImages = [new Image(), new Image(), new Image(), new Image(), new Image()];
		particleImages[0].src = <%=path + "images/dsj_login/01.png"%>;
		particleImages[1].src = <%=path + "images/dsj_login/02.png"%>;
		particleImages[2].src = <%=path + "images/dsj_login/03.png"%>;
		particleImages[3].src = <%=path + "images/dsj_login/04.png"%>;
		var snowNum = 300;

		function init() {
			camera = new THREE.PerspectiveCamera(75, containerWidth / containerHeight, 1, 10000);
			camera.position.z = 1000;
			scene = new THREE.Scene();
			scene.add(camera);
			renderer = new THREE.CanvasRenderer();
			renderer.setSize(containerWidth, containerHeight);
			for (var i = 0; i < snowNum; i++) {
				var material = new THREE.ParticleBasicMaterial({map: new THREE.Texture(particleImages[i % 5])});
				particle = new Particle3D(material);
				particle.position.x = Math.random() * 2000 - 1000;
				particle.position.y = Math.random() * 2000 - 1000;
				particle.position.z = Math.random() * 2000 - 1000;
				particle.scale.x = particle.scale.y = 1;
				scene.add(particle);
				particles.push(particle)
			}
			container.appendChild(renderer.domElement);
			document.addEventListener("mousemove", onDocumentMouseMove, false);
			document.addEventListener("touchstart", onDocumentTouchStart, false);
			document.addEventListener("touchmove", onDocumentTouchMove, false);
			setInterval(loop, 1000 / 50)
		}

		function onDocumentMouseMove(event) {
			mouseX = event.clientX - windowHalfX;
			mouseY = event.clientY - windowHalfY;
		}

		function onDocumentTouchStart(event) {
			if (event.touches.length === 1) {
				event.preventDefault();
				mouseX = event.touches[0].pageX - windowHalfX;
				mouseY = event.touches[0].pageY - windowHalfY;
			}
		}

		function onDocumentTouchMove(event) {
			if (event.touches.length === 1) {
				event.preventDefault();
				mouseX = event.touches[0].pageX - windowHalfX;
				mouseY = event.touches[0].pageY - windowHalfY;
			}
		}

		function loop() {
			for (var i = 0; i < particles.length; i++) {
				var particle = particles[i];
				// 滚动到楼层模块，减少雪花 （自定义）
				if ($(window).scrollTop() < 1000) {
					particle.scale.x = particle.scale.y = 1;
				} else {
					if (i > particles.length / 5 * 3) {
						particle.scale.x = particle.scale.y = 0;
					} else {
						particle.scale.x = particle.scale.y = 0.8;
					}
				}
				particle.updatePhysics();
				with (particle.position) {
					if (y < -1000) {
						y += 2000
					}
					if (x > 1000) {
						x -= 2000
					} else {
						if (x < -1000) {
							x += 2000
						}
					}
					if (z > 1000) {
						z -= 2000
					} else {
						if (z < -1000) {
							z += 2000
						}
					}
				}
			}
			camera.position.x += (mouseX - camera.position.x) * 0.005;
			camera.position.y += (-mouseY - camera.position.y) * 0.005;
			camera.lookAt(scene.position);
			renderer.render(scene, camera)
		}

		init();
	});
</script>
