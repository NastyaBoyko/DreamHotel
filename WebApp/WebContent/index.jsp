<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Dream Hotel</title>
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
	<h1>Вас приветствует DreamHotel!</h1>
	<p class="text">Для бронирования номера, пожалуйста, войдите под своей учетной записью.</p>
</div>
<div class="cover">
<form class="login-form" name="login-form" method="post" action="mmm">
	<input type="hidden" name="command" value="login" />
	<p>Логин:</p>
	<input type="text" name="login" value="" required/><br/>
	<p>Пароль:</p>
	<input type="password" name="password" value="" required/><br/>
	<input class="form-button" type="submit" value="Войти"/>
</form>
<form name="show-singin-page" method="post" action="mmm">
	<input type="hidden" name="command" value="show_singin" />
	<input class="submit-like-link" type="submit" value="Зарегистрироваться"/>
</form>
</div>
</div>
</body>
</html>