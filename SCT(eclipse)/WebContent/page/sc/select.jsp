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
		<title>选课</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>css/styles.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>css/font-awesome-4.7.0/css/font-awesome.min.css"/>
		<script src="<%=basePath %>js/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script type="text/javascript">
		
		</script>
	</head>
	<body>
		<table class="tablelist">
			<tr>
				<th width="745px">课程名</th>
				<th>任课教师</th>
				<th width="120px">选课操作</th>
			</tr>
		</table>
		<form action="<%=basePath %>sc?method=submit" method="post">
			<table class="tablelist">
				<c:forEach items="${courses }" var="course">
				<tr>
					<td>${course.cName}</td>
					<td>${course.tName}</td>
					<td width="100px" align="center">
						<!-- 选中的课程号 -->
						<input type="checkbox" <c:forEach items="${scs}" var="sc"><c:if test="${sc.cId eq course.cId }">checked="checked"</c:if></c:forEach> name="cId" value="${course.cId}">
					</td>
				</tr>
				</c:forEach>
			</table>
			<br />
			<div class="mybuttondiv">
				&nbsp;&nbsp;<span style="color:green">${param.msg}</span>
				<button type="submit">
					<i class="fa fa-save"></i>
					提交选课
				</button>
			</div>
		</form>
	</body>
</html>