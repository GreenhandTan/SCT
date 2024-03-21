<%@page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>登陆页面</title>
		<style type="text/css">
			*{
				margin: 0px;
				padding: 0px;
			}
			html,body{
				background-image: url(img/login-bg.png);
				background-size: 100% 100%;
				height: 100%;
			}
			.login{
				<!--background-color:rgba(255,255,255,.8);//透明-->
				background-color:#ffffff;
				background-image:url(img/login-login-bg.jpg) ;//背景图片
				background-size: 100% 100%;
				position: absolute;
				border-radius: 15px;
				top: 30%;
				left: 70%;
				right: 3%;
				bottom: 25%;
			}
			.title{
				position: absolute;
				top: 0%;
				bottom: 80%;
				width: 100%;
				text-align: center;
				font-family: "microsoft yahei";
				font-size: 40px;
				font-weight: bold;
				padding-top: 25px;
				box-sizing: border-box;
			}
			button{
				height: 40px;
				width: 40%;
				background-color: rgba(255,255,255,.6);
				border: 0px;
				border-radius: 5px;
				font-size: 20px;
				font-weight: bold;
			}
			input{
				height: 40px;
				width: 60%;
				border: 0px;
				border-radius: 5px;
				padding-left: 38px;
				box-sizing: border-box;
			}
			select{
				height: 40px;
				width: 60%;
				border: 0px;
				border-radius: 5px;
				box-sizing: border-box;
				background: transparent;
				font-size: 18px;
				color: #0064CD;
				text-align: center;
			}
			.uname{
				background: url(img/login-user.png) no-repeat left;
				background-color: rgba(255,255,255,.6);
			}
			.pwd{
				background: url(img/login-pwd.png) no-repeat left;
				background-color: rgba(255,255,255,.6);
			}
			.u{
				position: absolute;
				top: 20%;
				bottom: 60%;
				width: 100%;
				left: 20%;
				padding-top: 40px;
			}
			.p{
				position: absolute;
				top: 40%;
				bottom: 40%;
				width: 100%;
				left: 20%;
				padding-top: 40px;
			}
			.s{
				position: absolute;
				top: 55%;
				bottom: 40%;
				width: 100%;
				left: 20%;
				padding-top: 40px;
			}
			.l{
				position: absolute;
				top: 65%;
				bottom: 20%;
				width: 100%;
				left: 30%;
				padding-top: 40px;
			}
			.l button:hover{
				background-color:#467FE6; 
				cursor: pointer;
			}
			.tips{
				position: absolute;
				top: 85%;
				width: 100%;
				color: #999990;
				text-align: center;
				padding-top: 30px;
			}
		</style>
	</head>
	<body>
		<div class="login">
			<div class="title">
				学生选课系统
			</div>
			<form action="login" method="post">
				<div class="u">
					<input type="text" name="userName" class="uname" />
				</div>
				<div class="p">
					<input type="password" name="password" class="pwd"/>
				</div>
				<div class="s">
					<select name="type">
						<option value="0">学生</option>
						<option value="1">老师</option>
						<option value="2">管理员</option>
					</select>
				</div>
				<div class="l">
					<button type="submit">登录</button>
					<font color="red"><br/>&nbsp;&nbsp;&nbsp;&nbsp;${error }</font>
				</div>
				<div class="tips">
				推荐使用Chrome内核、Firefox浏览器访问本系统
				</div>
			</form>
		</div>
	</body>
</html>