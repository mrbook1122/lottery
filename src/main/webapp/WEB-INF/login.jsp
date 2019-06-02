<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="con"></div>
<form class="form-signin" action="login" method="post">
    <div class="form-title">
        <div class="item">
            Please Login
        </div>
    </div>
    <div class="login-content">
        <input class="form-item" type="text" name="name" placeholder="用户名">
        <input class="form-item" type="password" name="pass" placeholder="密码">
        <div class="check">
            <label>
                <input type="checkbox" name="remember">
                是否记住登录状态？
            </label>
        </div>
        <input class="btn" type="submit" value="登录">
    </div>
</form>
</body>
</html>
