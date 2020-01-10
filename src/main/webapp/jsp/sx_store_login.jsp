<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String jsPath = application.getContextPath() + "/shops_model/js/";
    String imgPath = application.getContextPath() + "/shops_model/img/";
    String cssPath = application.getContextPath() + "/shops_model/css/";
    String path = application.getContextPath() + "/";

%>
<html>
<head>
<meta charset="utf-8">
<title>登录</title>

    <link type="text/css" href=<%=cssPath+"sx_store_login_style.css"%> rel="stylesheet" />
    <script type="text/javascript" src=<%=path + "js/jquery-3.4.1.min.js"%>></script>
    <script type="text/javascript" src=<%=path + "js/md5.js"%>></script>

</head>
<body>

<script src=<%=jsPath+"anime.min.js"%>></script>
<form action="toShop.view" method="post" onsubmit="return deskLoginSub()">
<div class="page">
    <div class="container">
        <div class="left">
            <div class="login">登录</div>
            <div class="eula">欢迎使用！</div>
        </div>
        <div class="right">
            <svg viewBox="0 0 320 300">
        <defs>
          <linearGradient
                          inkscape:collect="always"
                          id="linearGradient"
                          x1="13"
                          y1="193.49992"
                          x2="307"
                          y2="193.49992"
                          gradientUnits="userSpaceOnUse">
            <stop
                  style="stop-color:#ff00ff;"
                  offset="0"
                  id="stop876" />
            <stop
                  style="stop-color:#ff0000;"
                  offset="1"
                  id="stop878" />
          </linearGradient>
        </defs>
        <path d="m 40,120.00016 239.99984,-3.2e-4 c 0,0 24.99263,0.79932 25.00016,35.00016 0.008,34.20084 -25.00016,35 -25.00016,35 h -239.99984 c 0,-0.0205 -25,4.01348 -25,38.5 0,34.48652 25,38.5 25,38.5 h 215 c 0,0 20,-0.99604 20,-25 0,-24.00396 -20,-25 -20,-25 h -190 c 0,0 -20,1.71033 -20,25 0,24.00396 20,25 20,25 h 168.57143" />
      </svg>
            <div class="form">
                <label for="user">户主</label>
                <input type="text" id="user" name="user">
                <label for="password">密码</label>
                <input type="password" id="password" name="password">
                <input type="submit" id="submit" value="提交">
            </div>
        </div>
    </div>
</div>
</form>

<script>
var current = null;
document.querySelector('#user').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: 0,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '240 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
document.querySelector('#password').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: -336,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '240 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
document.querySelector('#submit').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: -730,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '530 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});</script>
<script>


    function deskLoginSub ()
    {
        var uName = $("#user").val();
        var uPass = $("#password").val();
        var res = /[0-9a-zA-Z]{3,4}/;
        var resp = /[0-9a-zA-Z]{6,11}/;
        if (uName.length > 0 && res.test(uName))
        {
            if (uPass.length > 0 && resp.test(uPass))
            {
                $("#password").val(md5(uPass));
                return true
            } else {
                alert("请输入正确的密码格式进行登录");
            }
        } else {
            alert("输入您的楼栋号进行登录");
        }
        return false;
    }
</script>


</body>
</html>
