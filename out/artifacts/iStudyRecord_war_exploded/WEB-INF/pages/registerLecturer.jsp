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
                <td><form:label path="login">Login</form:label></td>
                <td><form:input path="login" name="login" id="login" /></td>
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
				<td><form:label path="info">Info</form:label></td>
				<td><form:input path="info" name="info" id="info" /></td>
			</tr>
			<tr>
				<td><form:label path="degree">Degree</form:label></td>
				<td><form:input path="degree" name="degree" id="degree" /></td>
			</tr>
			<tr>
				<td><form:label path="works">Works</form:label></td>
				<td><form:input path="works" name="works" id="works" /></td>
			</tr>

			<tr>
				<td><form:label path="interests">Interests</form:label></td>
				<td><form:input path="interests" name="interests" id="interests" /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:button id="registerLecturer" name="registerLecturer">Register</form:button></td>
			</tr>
			<tr></tr>
		</table>
	</form:form>
</body>
</html>