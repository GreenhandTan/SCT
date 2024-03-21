<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>权限不足</title>
<style type="text/css">
	.Nobody{
		position: absolute;
		left: 0px;
		right: 0px;
		top: 0px;
		bottom: 0px;
		background: url(img/NoPower.jpg) no-repeat;
		background-size: 100%;
		text-align: center;
	}
	.Nobody .font{
		position: absolute;
		top: 100px;
		left: 500px;
	}
</style>
</head>
<body>
	<!-- 无权限页面，防止越权跳转 -->
	<div class="Nobody">
		<font size=7 class="font"><a href="login.jsp">没有权限，请登录后在使用或者联系管理员！</font>
	</div>
    
</body>
</html>