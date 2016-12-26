<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="by.bsu.extask.to.RoomList"%>
<%@ page import="by.bsu.extask.to.RoomData"%>
<%@ page import="by.bsu.extask.to.UserData"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> Dream Hotel | Апартаменты </title>
<style> 
<%@include file='/WEB-INF/css/styles.css' %> 
.title {
background: url('https://lh6.googleusercontent.com/3MsgBVyGmbJOySS3goKLn-h38V8k2zyTm6YAdmp1m3VuVHKQqoW5xEADLnF54WrJ5ThKh0morFfPd8Q=w1366-h638-rw') no-repeat;
background-size: 100%;
padding: 3% 0;
text-align: center;
color: white;
margin: auto;
}
</style>
</head>
<body>
<% UserData userData = (UserData)request.getAttribute("userData"); %>
<div class="header">
<div class="wrapper">
		<form class="header-form" name="header-form" method="post" action="mmm">
			<input type="hidden" name="command" value="to_home_page" />
			<input type="hidden" name="id_user" value="<%=userData.id%>" />
			<input class="logo-link" type="submit" value="DREAM HOTEL"/>
		</form>
	
	<div class="right-align">
		<p class="name"><%=userData.name%> <%=userData.surname%></p>
		<form class="header-form" name="header-form" method="post" action="mmm">
			<input type="hidden" name="command" value="user_history_page" />
			<input type="hidden" name="user_id" value="<%=userData.id%>"> 
			<input class="header-button" type="submit" value="История моих заказов" />
		</form>
		<form class="header-form" name="header-form" method="post" action="mmm">
			<input type="hidden" name="command" value="logout" />
			<input class="header-button" type="submit" value="Выйти"/>
		</form>
	</div>
</div>
</div>

<div class="title">
<h1>Мы подберем для Вас лучший номер</h1>
</div>

<div class="wrapper flex">
	<form class="input-form-fullscreen" name="reserve-form" method="post" action="mmm">
		<input type="hidden" name="command" value="find_room_page" />
		<input type="hidden" name="user_id" value="<%=userData.id%>" />
		<p>Приезжаю:</p>
		<input type="date" name="dateIn" value="" requared /><br/>
		<p>Уезжаю:</p>	
		<input type="date" name="dateOut" value="" requared/><br/>	
		<p>Количество человек:</p>
		<input type="text" name="max_person" value="" pattern="\d" requared/><br/>	
		<input class="form-button" type="submit" value="Поиск"/>
	</form>
</div>

<footer>
<div class="wrapper">
<p>2016. Курсовой проект для БГУ ФСК. Бойко</p>
</div>
</footer>

	
</body>
</html>