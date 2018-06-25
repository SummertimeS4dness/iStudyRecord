<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
	<form:form id="regForm" modelAttribute="student" action="registerStudentProcess" method="post">
		<table align="center">
			<tr>
				<td><form:label path="studentLogin">StudentLogin</form:label></td>
				<td><form:input path="studentLogin" studentLogin="studentLogin" id="studentLogin" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password" name="password" id="password" /></td>
			</tr>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" name="name" id="name" /></td>
			</tr>
			<tr>
				<td><form:label path="group">Group</form:label></td>
				<td><form:input path="group" name="group" id="group" /></td>
			</tr>
			<tr>
				<td><form:label path="starosta">isStarosta(0 - нет, 1 - да)</form:label></td>
				<td><form:input path="starosta" name="starosta" id="starosta" pattern="[0-1]{1}" title="Введите \"0\" для \"Нет\" или \"1\" для \"Да\""/></td>
			</tr>

			<tr>
				<td></td>
				<td><form:button id="registerStudent" name="registerStudent">Register</form:button></td>
			</tr>
			<tr></tr>
			<tr>
				<td></td>
				<td><a href="">Home</a></td>
			</tr>
		</table>
	</form:form>
</body>
</html>