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
        <input type="text" name="name" placeholder="用户名">
        <input type="password" name="pass" placeholder="密码">
        <input type="submit" value="登录">
    </div>
</form>
</body>
</html>
