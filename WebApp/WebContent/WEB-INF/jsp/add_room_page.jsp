<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="by.bsu.extask.to.RoomData"%>
<%@ page import="by.bsu.extask.to.UserData"%>
<%@ page import="by.bsu.extask.to.RoomTypeData"%>
<%@ page import="by.bsu.extask.to.RoomTypeList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Dream Hotel. Администратор | Добавление апартаментов </title>
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
			<input type="hidden" name="command" value="logout" />
			<input class="header-button" type="submit" value="Выйти"/>
		</form>
	</div>
</div>
</div>

<div class="title">
<h1>Добавление номера</h1>
</div>

<div class="wrapper flex">
<form class="input-form-fullscreen" name="reserve-form" method="post" action="mmm">
	<input type="hidden" name="command" value="add_room" />
	<input type="hidden" name="user_id" value="<%=userData.id%>" />
	<p>Номер:</p><br/>
	<input type="text" name="room_id" value="" pattern="[0-9]{3}" requared/><br/>
	<p>Максимальное количество человек:</p><br/>
	<input type="text" name="max_person" value="" pattern="[0-9]{1,2}" requared/><br/>
	<p>Цена за сутки проживания, $:</p><br/>
	<input type="text" name="price_per_night" pattern="[0-9]{1,4}" value="" requared/><br/>
	<p>Класс:</p><br/>
	<select name="nametype">
	<%
		RoomTypeList types = (RoomTypeList)request.getAttribute("roomTypes");
		for(RoomTypeData type : types.roomtypeList){
	%>
		<option value="<%=type.id_roomtype%>"><%=type.roomtype%></option>
	<% } %>
		
	</select>
	<input class="form-button" type="submit" value="Enter"/>
</form>
</div>

<footer>
<div class="wrapper">
<p>2016. Курсовой проект для БГУ ФСК. Бойко</p>
</div>
</footer>

</body>
</html>