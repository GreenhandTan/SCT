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
						stuNo:{
							required:true,
							digits:true
						},
						stuName:"required",
						stuPwd:{
							required:true,
							minlength:6,
							maxlength:18,
						}
					}
				});
			});
		</script>
	</head>
	<body>
		<div class="add">
			<form id="addForm" action="<%=basePath %>student?method=add" method="post">
				<table class="tableadd">
				<tr>
					<td>学号：</td>
					<td><input type="text" name="stuNo"/></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td><input type="text" name="stuName"/></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="stuPwd" value="123456"/></td>
					<h4 align="center">初始密码为：123456</h4>
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
							提交
						</button>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</body>
</html>
