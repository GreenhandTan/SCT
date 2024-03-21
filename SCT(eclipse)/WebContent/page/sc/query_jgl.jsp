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
		<title>及格率查询统计</title>
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
					<th>及格人数</th>
					<th>总人数</th>
					<th>及格率</th>
				</tr>
			</thead>
			<c:forEach items="${list}" var="qj">
			<tr>
				<td>${qj.cId}</td>
				<td>${qj.cName}</td>
				<td>${qj.jgnum}</td>
				<td>${qj.allnum}</td>
				<td>${qj.jgl}%</td>
			</tr>
			</c:forEach>
		</table>
	</body>
</html>