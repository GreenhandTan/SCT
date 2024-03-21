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
		<title>老师评分</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>css/styles.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>css/font-awesome-4.7.0/css/font-awesome.min.css"/>
		<script src="<%=basePath %>js/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/jquery-validation-1.14.0/jquery.validate.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/jquery-validation-1.14.0/localization/messages_zh.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$("#addForm").validate({
					rules:{
						score:{
							required:true,
							digits:true,
							max:100,
							min:0
						},
					}
				});
			});
		</script>
	</head>
	<body>
		<form id="addForm" action="<%=basePath %>sc?method=score_submit" method="post">
		<input type="hidden" name="cId" value="${cId }"/>
		<table class="tablelist">
			<thead>
				<tr>
					<th>ID</th>
					<th>学生姓名</th>
					<th>学号</th>
					<th width="400px">评分</th>
				</tr>
			</thead>
			<c:forEach items="${list}" var="student">
			<tr>
				<td>${student.stuId}</td>
				<td>${student.stuName}</td>
				<td>${student.stuNo}</td>
				<td width="400px" style="color:red">
					<font style="color:black">最终成绩：</font><input type="text" name="score" value="${student.score }"/>
					<input type="hidden" name="stuNo" value="${student.stuNo }"/>
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