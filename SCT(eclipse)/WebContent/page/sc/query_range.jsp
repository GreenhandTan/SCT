<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    
    String basePath = request.getScheme() 
    + "://" + request.getServerName() 
    + ":" 
    + request.getServerPort() 
    + path 
    + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>区间查询统计</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>css/styles.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>css/font-awesome-4.7.0/css/font-awesome.min.css"/>
		<script src="<%=basePath %>js/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<table class="tablelist">
			<thead>
				<tr>
					<th>ID</th>
					<th>课程名</th>
					<th>不及格(0,60)</th>
					<th>中等[60,70]</th>
					<th>良好(70,85]</th>
					<th>优秀(85,100]</th>
				</tr>
			</thead>
			<c:forEach items="${list}" var="qr">
			<tr>
				<td>${qr.cId}</td>
				<td>${qr.cName}</td>
				<td>${qr.fail}</td>
				<td>${qr.medium}</td>
				<td>${qr.good}</td>
				<td>${qr.best}</td>
			</tr>
			</c:forEach>
		</table>
	</body>
</html>