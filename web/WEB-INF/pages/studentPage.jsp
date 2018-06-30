<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script type="text/javascript">
            function test() {
                $.ajax({
                    type: "get",
                    url: 'marks',
                    dataType: "json",
                    complete: [
                        function (response) {
                            $("#personDataTable").find("tr:not(:first)").remove();
                            alert(response.responseText);
                            var trHTML = '';
                            var obj = $.parseJSON(response.responseText);
                            alert(obj.length);
                            for (var i = 0; i < obj.length; i++) {
                                trHTML += '<tr><td>' + obj[i].subject + '</td><td>' + obj[i].score + '</td></tr>';
                                console.log(trHTML);
                            }
                            $("#personDataTable tbody").append(trHTML);
                        }
                    ]
                });
            }
        </script>
        <title>Welcome</title>
    </head>
    <body>
        <button name="objectType" id="marks" onclick="test()" class="lft2">Marks</button>
        <button name="objectType" id="lessons" onclick="test()" class="lft2">Lessons</button>
        <table id="personDataTable" border = "2" align="center">
            <tr>
                <th>Subject</th>
                <th>Score</th>
            </tr>
        </table>
        <div id="parameters"> </div>
    </body>
</html>