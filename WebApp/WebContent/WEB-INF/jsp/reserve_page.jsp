<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="by.bsu.extask.to.RoomData"%>
<%@ page import="by.bsu.extask.to.UserData"%>
<%@ page import="java.sql.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> Dream Hotel | Бронирование номера </title>
<style> 
<%@include file='/WEB-INF/css/styles.css' %> 
.title {
background: url('https://lh6.googleusercontent.com/3MsgBVyGmbJOySS3goKLn-h38V8k2zyTm6YAdmp1m3VuVHKQqoW5xEADLnF54WrJ5ThKh0morFfPd8Q=w1366-h638-rw') no-repeat center center fixed;
background-size: 100%;
padding: 3% 0;
text-align: center;
color: white;
margin: auto;
}
</style>
</head>
<body>
		<%
			
			UserData userData = (UserData)request.getAttribute("userData1");
			RoomData room = (RoomData)request.getAttribute("roomData1");
			Date datein = (Date)request.getAttribute("dateIn");
			Date dateout = (Date)request.getAttribute("dateOut");
		%>
		
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
<h1>Проверка данных</h1>
</div>

<div class="wrapper flex">
<form class="input-form-fullscreen" name="reserve-form" method="post" action="mmm">
	<input type="hidden" name="command" value="reserve" />
	<input type="hidden" name="user_id" value="<%=userData.id%>" />
	<p>Номер:</p><br/>
	<input type="text" name="room_id" value="<%=room.number_room%>" readonly/><br/>
	<p>для <%=room.max_person%> человек. Класса <%=room.type%>.</p>
	<p>Стоимость за сутки проживания: <%=room.pricepernight%> $</p>
	<p>Прибытие:</p><br/>
	<input type="date" name="dateIn" value="<%=datein%>" readonly/><br/>
	<p>Отбытие:</p><br/>
	<input type="date" name="dateOut" value="<%=dateout%>" readonly/><br/>	
	<input class="form-button"  type="submit" value="Подтвердить"/>
</form>
</div>
<footer>
<div class="wrapper">
<p>2016. Курсовой проект для БГУ ФСК. Бойко</p>
</div>
</footer>

</body>
</html>