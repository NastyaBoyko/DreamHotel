<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="by.bsu.extask.to.RoomList"%>
<%@ page import="by.bsu.extask.to.RoomData"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Dream Hotel. Administrator | Апартаменты </title>
</head>
<body>
	<table border='1'>
		<%
			RoomList rooms = (RoomList)request.getAttribute("roomList");
			
			for(RoomData room : rooms.roomList){
		%>
		<tr>
			<td><%=room.number_room%></td>
			<td><%=room.max_person%></td>
			<td><%=room.pricepernight%></td>
			<td><%=room.type%></td>
		</tr>
		<%
			}
		%>
	</table>
<form name="log-out" method="post" action="mmm">
	<input type="hidden" name="command" value="to_home_page" />
	<input class="submi-link" type="submit" value="Вернуться на главную страницу"/>
</form>
</body>
</html>