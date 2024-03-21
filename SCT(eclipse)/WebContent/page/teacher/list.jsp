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
		<title>列表</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>css/styles.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>css/font-awesome-4.7.0/css/font-awesome.min.css"/>
		<script src="<%=basePath %>js/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$('.remove').click(function(){
					if(confirm("请确认删除！")){
						window.location.href="<%=basePath %>teacher?method=delete&pageNo=0&userName="+$(this).attr("keyword");
					}
				})
			})
		</script>
	</head>
	<body>
		<!-- 组合条件查询 -->
		<form action="<%=basePath %>teacher?method=list&pageNo=${pageInfo.pageNo}" method="post">
		<div class="condition">
			教师姓名:<input type="text" name="tName"/>
			教师账号:<input type="text" name="userName"/>
			<button type="submit">
				<i class="fa fa-search"></i>
				查询
			</button>
			<button type="button" onclick="window.location.href='page/teacher/add.jsp'">
				<i class="fa fa-plus"></i>
				新增
			</button>
		</div>
		</form>
		<table class="tablelist">
			<thead>
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>账号</th>
					<th width="250px">操作</th>
				</tr>
			</thead>
			<c:forEach items="${pageInfo.list}" var="teacher">
			<tr>
				<td>${teacher.tId}</td>
				<td>${teacher.tName}</td>
				<td>${teacher.userName}</td>
				<td>
					<button type="button" class="edit" onclick="window.location.href='<%=basePath %>teacher?method=edit&userName=${teacher.userName}'">
						<i class="fa fa-edit"></i>
						修改
					</button>
					<button type="button" class="remove" keyword="${teacher.userName}">
						<i class="fa fa-remove"></i>
						删除
					</button>
				</td>
			</tr>
			</c:forEach>
		</table>
		<table class="page">
			<td>
				总记录条数${pageInfo.totalCount}条，当前${pageInfo.pageNo}/${pageInfo.totalPage}页
				<button type="button" onclick="javascrpt:go(1);">首页</button>
				<button type="button" onclick="javascrpt:go(${pageInfo.prePage});">上一页</button>
				<button type="button" onclick="javascrpt:go(${pageInfo.nextPage});">下一页</button>
				<button type="button" onclick="javascrpt:go(${pageInfo.totalPage});">尾页</button>
				<input type="text" class="page-no" name="pageNo" id="pageNo" value="${pageInfo.pageNo}"/>
				<button type="button" class="zhuan" onclick="javascrpt:go(-1);">跳转到指定页</button>
			</td>
		</table>
		<script type="text/javascript">
			function go(page){
				if(page == -1){
					page = $('#pageNo').val();//jquery获取input的value，需要跳转的页数
					if(page<1 || page>${pageInfo.totalPage}){
						alert("页码不正确！");
						page = 1;
						window.location.href="<%=basePath %>teacher?method=list&pageNo="+page;
					}else{
						window.location.href="<%=basePath %>teacher?method=list&pageNo="+page;
					}
				}else{
					window.location.href="<%=basePath %>teacher?method=list&pageNo="+page;
				}
			}
		</script>
	</body>
</html>