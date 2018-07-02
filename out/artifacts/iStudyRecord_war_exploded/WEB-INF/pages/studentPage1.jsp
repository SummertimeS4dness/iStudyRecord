<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<table align="center">
		<tr>
			<td>
				Welcome ${firstname}
			</td>
		</tr>
		<tr>
			<table border = "2" align="center">
				<tr><th>Subject</th><th>Score</th></tr>
				<c:forEach var = "mark" items = "${list}">
					<tr>
						<td>${mark.subject}</td>
						<td>${mark.score}</td>
					</tr>
				</c:forEach>
			</table>
		</tr>
		<tr>
			<td><a href="register">Home</a></td>
		</tr>
	</table>
</body>
</html>