<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="by.bsu.extask.to.RequestList"%>
<%@ page import="by.bsu.extask.to.RequestData"%>
<%@ page import="by.bsu.extask.to.RoomData"%>
<%@ page import="by.bsu.extask.to.UserData"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Dream Hotel. Администатор | История заказов </title>
<style> 
<%@include file='/WEB-INF/css/styles.css' %> 
</style>
</head>
<body>
<%
RequestList requests = (RequestList)request.getAttribute("requestList");
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
			<input type="hidden" name="command" value="logout" />
			<input class="header-button" type="submit" value="Выйти"/>
		</form>
	</div>
</div>
</div>
<div class="wrapper flex">
	<table id="keywords">
	<thead id="td-title">
		<tr>
			<td>Номер</td>
			<td>Гость</td>
			<td>Приехал</td>
			<td>Уехал</td>
			<td>Цена за проживание</td>
		</tr>
		</thead>
		<tbody>
	
	
	<%
		for(RequestData req : requests.requestList){
			UserData bufuser = req.user;
			RoomData bufroom = req.room;
	%>
		
		<tr>
			<td><%=bufroom.number_room%></td>
			<td><%=bufuser.name%> <%=bufuser.surname%></td>
			<td><%=req.date_in%></td>
			<td><%=req.date_out%></td>
			<td><%=req.total_price%></td>
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