<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
	<form:form id="regForm" modelAttribute="lecturer" action="registerLecturerProcess" method="post">
		<table align="center">
			<tr>
				<td><form:label path="lecturerID">LecturerID</form:label></td>
				<td><form:input path="lecturerID" name="lecturerID" id="lecturerID" /></td>
			</tr>
			<tr>
				<td><form:label path="lecturerLogin">LecturerLogin</form:label></td>
				<td><form:input path="lecturerLogin" name="lecturerLogin" id="lecturerLogin" /></td>
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
				<td></td>
				<td><form:button id="registerLecturer" name="registerLecturer">Register</form:button></td>
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