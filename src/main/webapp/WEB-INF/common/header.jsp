<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header/header.css">
</head>
<body>
<div class="header">
    <div class="nav-container">
        <a href="" class="nav-logo">
            <img src="${pageContext.request.contextPath}/img/Cake.png" alt="1">
        </a>
        <a href="" class="nav-text">
            首页
        </a>
        <div class="nav-menu">
            <a href="${pageContext.request.contextPath}" class="nav-item">抽奖</a>
            <a href="${pageContext.request.contextPath}/record" class="nav-item">记录</a>
            <a href="${pageContext.request.contextPath}/user" class="nav-item">用户</a>
            <a href="${pageContext.request.contextPath}/info" class="nav-item">个人</a>
            <a href="${pageContext.request.contextPath}/logout" class="nav-item">退出</a>
        </div>
    </div>
</div>
</body>
</html>
