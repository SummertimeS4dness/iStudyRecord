
<%@ page contentType="text/html;charset=UTF-8;text/plain" language="java" %>
<%@ page isErrorPage="true" import="java.io.*"%>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1>Houston, we have a problem</h1>
        <p>Message: <%=exception.getMessage()%></p>
        <p>
            StackTrace:
            <%
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                exception.printStackTrace(printWriter);
                out.println(stringWriter);
                printWriter.close();
                stringWriter.close();
            %>
        </p>
        <button onclick="history.back()">Back to Previous Page</button>
    </body>
</html>
