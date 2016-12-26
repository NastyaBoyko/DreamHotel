<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Dream Hotel | Регистрация </title>
<style> 
<%@include file='/WEB-INF/css/styles.css' %> 
.index { 
background: url('https://lh6.googleusercontent.com/3MsgBVyGmbJOySS3goKLn-h38V8k2zyTm6YAdmp1m3VuVHKQqoW5xEADLnF54WrJ5ThKh0morFfPd8Q=w1366-h638-rw') no-repeat center center fixed;
background-size: cover;
}
</style>
</head>
<body class="index">
<div class="wrapper">
<div class="hello-text">
	<h1>Всегда рады видеть новых клиентов!</h1>
	<p class="text">Пожалуйста, заполните все поля для регистрации.</p>
</div>
<div id="singin" class="cover">
<form class="login-form" name="login-form" method="post" action="mmm">
	<input type="hidden" name="command" value="singin" />
	<p>Имя:</p>
	<input type="text" name="name" value="" required/><br/>
	<p>Фамилия:</p>
	<input type="text" name="surname" value="" required/><br/>
	<p>E-mail:</p>
	<input type="email" name="email" value=""  required/><br/>
	<p>Логин:</p>
	<input type="text" name="login" value="" pattern="[\x1F-\xBF]*" required/><br/>
	<p>Пароль:</p>
	<input type="password" name="password" value="" required/><br/>
	<input class="form-button" type="submit" value="Enter"/>
</form>
</div>
</div>
</body>
</html>