<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<meta charset="UTF-8">
		<title>新增页面</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>css/styles.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>css/font-awesome-4.7.0/css/font-awesome.min.css"/>
		<script src="<%=basePath %>js/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/jquery-validation-1.14.0/jquery.validate.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/jquery-validation-1.14.0/localization/messages_zh.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$("#addForm").validate({
					rules:{
						pwd:{
							required:true,
							minlength:6,
							maxlength:18,
						},
						tName:"required",
						}
					}
				});
			});
		</script>
	</head>
	<body>
		<div class="add">
			<form id="editForm" action="<%=basePath %>teacher?method=editsubmit" method="post">
				<table class="tableadd">
				<tr>
					<td>账号：</td>
					<td><input type="text" name="userName" value="${teacher.userName }"/><br/><h4>账号不可更改！<h4></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="pwd"/></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td><input type="text" name="tName" value="${teacher.tName}"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" class="edit" onclick="window.history.back(-1);">
							<i class="fa fa-arrow-left"></i>
							返回
						</button>
						<button type="button" class="edit" onclick="location=location">
							<i class="fa fa-refresh"></i>
							重置
						</button>
						<button type="submit" class="edit">
							<i class="fa fa-save"></i>
							修改
						</button>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</body>
</html>