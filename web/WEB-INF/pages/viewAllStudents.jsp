<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.05.2018
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType = "text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Title</title>
</head>
<body>

        <table border = "2" >
            <tr><th>ID</th><th>Name</th><th>Group</th></tr>
            <c:forEach var = "student" items = "${list}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.group}</td>
            </tr>
            </c:forEach>
        </table>

</body>
</html>
