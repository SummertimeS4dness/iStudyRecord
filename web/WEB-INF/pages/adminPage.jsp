<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 29.06.2018
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
    <script type="text/javascript">
        var shown=0;
        function test() {
            if(shown==0){
                shown=1;
            $.ajax({
                type: "GET",
                url: 'students',
                dataType: "json",
                complete: [
                    function (response) {
                    document.getElementById("personDataTable").style.visibility="visible";
                        $("#personDataTable").find("tr:not(:first)").remove();
                        var trHTML = '';
                        var obj = $.parseJSON(response.responseText);
                        for (var i = 0; i < obj.length; i++) {
                            trHTML += '<tr><td>' + obj[i].id + '</td><td>' + obj[i].name + '</td><td>' + obj[i].login + '</td><td>' + obj[i].password + '</td><td>' +
                                '<button id="'+i+'"class="myclass">edit</button> </td></tr>';
                            var a = obj;
                            $(document).off().on('click','button.myclass',function (event) {
                              //  test2(this.id,a);
                               onEdit(this.id,a);
                            });
                        }
                        $("#personDataTable tbody").append(trHTML);
                    }
                ]
            });}else {
                shown=0;
                document.getElementById("personDataTable").style.visibility="hidden";
            }
        }
        function test2(a,ob) {
            var student={
                id:ob[a].id,
                login:ob[a].login,
                password:ob[a].password,
                name:ob[a].name
            }
            $.ajax({
                type: "POST",
                contentType : 'application/json; charset=utf-8',

                url: "testAdmin",
                data: JSON.stringify(student),
                success: function (response)
                {
                    alert(response);
                    $('#page_grid').html(responce);
                },
                error : function(xhr, status, errorThrown) {
                    alert(status+" "+errorThrown.toString());
                }
            });

    }
        function onEdit(pos,mass) {
            var student={
                id:mass[pos].id,
                login:mass[pos].login,
                password:mass[pos].password,
                name:mass[pos].name
            }
            document.getElementById("studentPersone").style.visibility="visible";
            document.getElementById("studentId").textContent=student.id;
            document.getElementById("studentName").value=student.name;
            document.getElementById("studentLogin").value=student.login;
            document.getElementById("studentPassword").value=student.password;
        }
        function saveStudentChange() {
            var student= {
                id: document.getElementById("studentId").textContent,
                login: document.getElementById("studentLogin").value,
                password: document.getElementById("studentPassword").value,
                name: document.getElementById("studentName").value
            }
            $.ajax({
                type: "POST",
                contentType : 'application/json; charset=utf-8',

                url: "updateStudent",
                data: JSON.stringify(student),
                success: function (response)
                {
                    test();
                },
                error : function(xhr, status, errorThrown) {
                    alert(status+" "+errorThrown.toString());
                }
            });
        }
    </script>
    <script>

    </script>
    <title>adminPage</title>
</head>
<body>
<div id="result"></div>
<h1>Hello admin</h1>
<button name="objectType" id="ot" class="aclass" onclick="test()" >All students</button>
<table>
    <tr>
     <td>
<table id="personDataTable" style="visibility: hidden" border="">
    <tr>
        <th width="20%">ID</th>
        <th width="20%">Name</th>
        <th width="20%">Login</th>
        <th width="20%">Password</th>
        <th width="20%"></th>
    </tr>
</table>
     </td>
        <td></td>
        <td>
<table id="studentPersone"  style="visibility: hidden">
        <tr>
            <td >id</td>
            <td >Name</td>
            <td >Login</td>
            <td >Password</td>
        </tr>
        <tr>
            <td ><label id="studentId">id</label></td>
            <td ><input id="studentName"/></td>
            <td ><input id="studentLogin"/></td>
            <td ><input id="studentPassword"/></td>
        </tr>
        <td><button id="saveStudentChage" onclick="saveStudentChange()">Save</button> </td>

</table>
        </td>
    </tr>
</table>
<div id="parameters"> </div>
</body>
</html>
