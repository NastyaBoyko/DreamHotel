<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="by.bsu.extask.to.UserData" %>
<%@ page import="by.bsu.extask.to.RoomList"%>
<%@ page import="by.bsu.extask.to.RoomData"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> Dream Hotel. Администратор | Апартаменты </title>
<style> 
<%@include file='/WEB-INF/css/styles.css' %> 
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
		<p class="name">Админ: <%=userData.name%> <%=userData.surname%></p>
		<form class="header-form" name="header-form" method="post" action="mmm">
			<input type="hidden" name="command" value="logout" />
			<input class="header-button" type="submit" value="Выйти"/>
		</form>
	</div>
</div>
</div>

<div class="wrapper">
<div class="admin-nav">
	<form name="show-users" method="post" action="mmm">
		<input type="hidden" name="command" value="show_users" />
		<input type="hidden" name="user_id" value="<%=userData.id%>">
		<input class="admin-button" type="submit" value="Все зарегестрированные пользователи"/>
	</form>

	<form name="reserve-room" method="post" action="mmm">
		<input type="hidden" name="command" value="add_room_page" />
		<input type="hidden" name="user_id" value="<%=userData.id%>">
		<input class="admin-button" type="submit" value="Добавить комнату"/>
	</form>

	<form name="reserve-room" method="post" action="mmm">
		<input type="hidden" name="command" value="history_room_page" />
		<input type="hidden" name="user_id" value="<%=userData.id%>">
		<input class="admin-button" type="submit" value="История заказов"/>
	</form>
</div>
</div>




<div class="wrapper flex content">



	<table id="keywords">
	<thead id="td-title">
		<tr>
			<td>Номер</td>
			<td>Максимальное количество человек</td>
			<td>Цена за сутки</td>
			<td>Класс</td>
	
		</tr>
	</thead>
	<tbody>
		<%
			RoomList rooms = (RoomList)request.getAttribute("roomList");
			
			for(RoomData room : rooms.roomList){
		%>
	
		<tr>
			<td class="lalign"><%=room.number_room%></td>
			<td><%=room.max_person%></td>
			<td><%=room.pricepernight%> $</td>
			<td><%=room.type%></td>			
		</tr>
		<%
			}
		%>
	</tbody>
	</table>
</div>

<footer>
<div class="wrapper">
<p>2016. Курсовой проект для БГУ ФСК. Бойко</p>
</div>
</footer>


</body>
</html>