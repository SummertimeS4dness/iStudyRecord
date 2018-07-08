<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<form:form id="loginForm" modelAttribute="login" action="loginProcess" method="post">
		<table align="center">
			<tr>
				<td><form:label path="nickname">Login: </form:label></td>
				<td><form:input path="nickname" name="nickname" id="nickname" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password:</form:label></td>
				<td><form:password path="password" name="password" id="password" /></td>
			</tr>
            <tr>
                <td>
                    <label  class="radio-inline">
                        <input type="radio" name="radioName" id="student"  value="student" checked="checked"/>Student
                    </label>
                    <label  class="radio-inline">
                        <input  type="radio" name="radioName"  id="lecturer" value="lecturer"/>Lecturer
                    </label>
					<label  class="radio-inline">
						<input  type="radio" name="radioName"  id="admin" value="admin"/>Admin
					</label>
                </td>
            <tr>
			<tr>
				<td></td>
				<td align="left"><form:button id="login" name="login">Login</form:button></td>
			</tr>
		</table>
	</form:form>
	<table align="center">
		<tr>
			<td style="font-style: italic; color: red;">${message}</td>
		</tr>
	</table>

</body>
</html>