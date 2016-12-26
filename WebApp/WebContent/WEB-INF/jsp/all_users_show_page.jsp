<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="by.bsu.extask.to.UserList"%>
<%@ page import="by.bsu.extask.to.UserData"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> Dream Hotel. Администратор | Пользователи </title>
<style> 
<%@include file='/WEB-INF/css/styles.css' %> 
</style>
</head>
<body>
<%
UserList users = (UserList)request.getAttribute("userList");
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
		<p class="name">Админ: <%=userData.name%> <%=userData.surname%></p>
		<form class="header-form" name="header-form" method="post" action="mmm">
			<input type="hidden" name="command" value="logout" />
			<input class="header-button" type="submit" value="Выйти"/>
		</form>
	</div>
</div>
</div>

<div class="wrapper flex content">

	<table id="keywords">
	<thead id="td-title">
		<tr>
			<td>Имя</td>
			<td>Фамилия</td>
			<td>E-mail</td>
		</tr>
	</thead>
	<tbody>
		<%
			for(UserData user : users.userList){
		%>
		
		<tr>
			<td><%=user.name%></td>
			<td><%=user.surname%></td>
			<td><%=user.email%></td>
			<td>&nbsp</td>
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