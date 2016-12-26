<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="by.bsu.extask.to.RoomList"%>
<%@ page import="by.bsu.extask.to.RoomData"%>
<%@ page import="by.bsu.extask.to.UserData"%>
<%@ page import="java.sql.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Dream Hotel. | Доступные апартаменты </title>
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
System.out.println("RoomList");
RoomList rooms = (RoomList)request.getAttribute("findRooms");
System.out.println("Date");
Date datein = (Date)request.getAttribute("inDate");
Date dateout = (Date)request.getAttribute("outDate");
System.out.println("UserData");
UserData userData = (UserData)request.getAttribute("userData");
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
<h1>Доступные номера по Вашему запросу</h1>
</div>

<div class="wrapper flex">
	<table id="keywords">
	<thead id="td-title">
		<tr>
			<td>Номер</td>
			<td>Максимальное количество человек</td>
			<td>Цена за сутки</td>
			<td>Класс апартаментов</td>
			<td> &nbsp 	</td>
		</tr>
	</thead>
	<tbody>
		<%
			for(RoomData room : rooms.roomList){
		%>
	
		<tr>
			<td><%=room.number_room%></td>
			<td><%=room.max_person%></td>
			<td><%=room.pricepernight%> $</td>
			<td><%=room.type%></td>
			<td>
				<form name="reserve-room" method="post" action="mmm">
					<input type="hidden" name="command" value="reserve_page" />
					<input type="hidden" name="number_room" value="<%=room.number_room%>">
					<input type="hidden" name="user_id" value="<%=userData.id%>">
					<input type="hidden" name="date_in" value="<%=datein%>">
					<input type="hidden" name="date_out" value="<%=dateout%>">
					<input class="form-button" type="submit" value="Заказать"/>
				</form>
			</td>
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