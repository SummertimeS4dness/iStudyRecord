<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 29.06.2018
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mvc.beans.Student" %>
<%@ page import="mvc.beans.Lecturer" %>
<%@ page import="mvc.beans.Object" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
        <script type="text/javascript">
            var shown = 0;
            var lecturerShown=0;
            function test() {
                if (shown == 0) {
                    shown = 1;
                    var stOb=null;
                    $.ajax({
                        type: "GET",
                        url: 'studentObjects',
                        dataType: "json",
                        complete: [
                            function (response) {
                            stOb=$.parseJSON(response.responseText);
                            }]});

                    $.ajax({
                        type: "GET",
                        url: 'students',
                        dataType: "json",
                        complete: [
                            function (response) {
                                document.getElementById("personDataTable").style.visibility = "visible";
                                $("#personDataTable").find("tr:not(:first)").remove();
                                var trHTML = '';
                                var obj = $.parseJSON(response.responseText);
                                for (var i = 0; i < obj.length; i++) {
                                    trHTML += '<tr>' +
                                        '<td>' + obj[i].id + '</td>' +
                                        '<td>' + obj[i].name + '</td>' +
                                        '<td>' + obj[i].login + '</td>' +
                                        '<td>' + obj[i].password + '</td>' +
                                        '<td>' +stOb[i].parentId+'</td>' +
                                        '<td><button id="' + i + '"class="myclass">edit</button> </td>' +
                                        '</tr>';
                                    var a = obj;
                                    $(document).off().on('click', 'button.myclass', function (event) {
                                        //  test2(this.id,a);
                                        listStudent();
                                        onEdit(this.id, a);
                                    });
                                }
                                $("#personDataTable tbody").append(trHTML);
                            }
                        ]
                    });
                } else {
                    shown = 0;
                    document.getElementById("personDataTable").style.visibility = "hidden";
                    document.getElementById("studentPersone").style.visibility = "hidden";
                    document.getElementById("studentId").textContent = '';
                    document.getElementById("studentName").value = '';
                    document.getElementById("studentLogin").value = '';
                    document.getElementById("studentPassword").value = '';
                }
            }
            function lecturers() {
                if (lecturerShown == 0) {
                    lecturerShown = 1;


                    $.ajax({
                        type: "GET",
                        url: 'lecturers',
                        dataType: "json",
                        complete: [
                            function (response) {
                                document.getElementById("lecturersTable").style.visibility = "visible";
                                $("#lecturersTable").find("tr:not(:first)").remove();
                                var trHTML = '';
                                var obj = $.parseJSON(response.responseText);
                                for (var i = 0; i < obj.length; i++) {
                                    trHTML += '<tr><td>' + obj[i].id + '</td>' +
                                        '<td>' + obj[i].name + '</td>' +
                                        '<td>' + obj[i].login + '</td>' +
                                        '<td>' + obj[i].password + '</td>' +
                                        '<td>' + obj[i].info + '</td>' +
                                        '<td>' + obj[i].degree + '</td>' +
                                        '<td>' + obj[i].works + '</td>' +
                                        '<td>' + obj[i].interests + '</td><td>' +
                                        '<button id="' + i + '"class="myclassL">edit</button> </td></tr>';
                                    var a = obj;
                                    $(document).off().on('click', 'button.myclassL', function (event) {

                                       // onEdit(this.id, a);
                                    });
                                }
                                $("#lecturersTable tbody").append(trHTML);
                            }
                        ]
                    });
                } else {
                    lecturerShown = 0;
//                    document.getElementById("personDataTable").style.visibility = "hidden";
//                    document.getElementById("studentPersone").style.visibility = "hidden";
//                    document.getElementById("studentId").textContent = '';
//                    document.getElementById("studentName").value = '';
//                    document.getElementById("studentLogin").value = '';
//                    document.getElementById("studentPassword").value = '';
                }
            }
            function test2(a, ob) {
                var student = {
                    id: ob[a].id,
                    login: ob[a].login,
                    password: ob[a].password,
                    name: ob[a].name
                }
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',

                    url: "testAdmin",
                    data: JSON.stringify(student),
                    success: function (response) {
                        alert(response);
                        $('#page_grid').html(responce);
                    },
                    error: function (xhr, status, errorThrown) {
                        alert(status + " " + errorThrown.toString());
                    }
                });

            }

            function onEdit(pos, mass) {
                var student = {
                    id: mass[pos].id,
                    login: mass[pos].login,
                    password: mass[pos].password,
                    name: mass[pos].name
                }
                document.getElementById("studentPersone").style.visibility = "visible";
                document.getElementById("studentId").textContent = student.id;
                document.getElementById("studentName").value = student.name;
                document.getElementById("studentLogin").value = student.login;
                document.getElementById("studentPassword").value = student.password;
            }

            function onCancelStudent() {
                document.getElementById("studentPersone").style.visibility = "hidden";
                document.getElementById("studentId").textContent = '';
                document.getElementById("studentName").value = '';
                document.getElementById("studentLogin").value = '';
                document.getElementById("studentPassword").value = '';
            }

            function saveStudentChange() {
                var student = {
                    id: document.getElementById("studentId").textContent,
                    login: document.getElementById("studentLogin").value,
                    password: document.getElementById("studentPassword").value,
                    name: document.getElementById("studentName").value
                }

                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',

                    url: "updateStudent",
                    data: JSON.stringify(student),
                    success: function (response) {
                        shown = 0;
                        test();
                        document.getElementById("studentPersone").style.visibility = "hidden";
                    },
                    error: function (xhr, status, errorThrown) {
                        alert(status + " " + errorThrown.toString());
                    }
                });

            }

            function removeStudent() {
                var student = {
                    id: document.getElementById("studentId").textContent,
                    login: document.getElementById("studentLogin").value,
                    password: document.getElementById("studentPassword").value,
                    name: document.getElementById("studentName").value
                }
                $.ajax({
                    type: "DELETE",
                    contentType: 'application/json; charset=utf-8',
                    url: "deleteStudent",
                    data: JSON.stringify(student),
                    success: function (response) {
                        shown = 0;
                        test();
                        document.getElementById("studentPersone").style.visibility = "hidden";
                    },
                    error: function (xhr, status, errorThrown) {
                        alert(status + " " + errorThrown.toString());
                    }
                });
            }

            $(document).ready(function () {

                var acc = document.getElementsByClassName("accordion");
                var i;

                for (i = 0; i < acc.length; i++) {
                    acc[i].addEventListener("click", function () {
                       // list();
                        this.classList.toggle("active");
                        var panel = this.nextElementSibling;
                        if (panel.style.display === "block") {
                            panel.style.display = "none";
                        } else {
                            panel.style.display = "block";
                        }
                    });
                }
            });
            function listStudent() {
                var groups;
                $.ajax({
                    type: "GET",
                    url: 'getGroups',
                    dataType: "json",
                    complete: [function (response) {
                        groups = $.parseJSON(response.responseText);
                        var ddl = $("#selectGroup");
                        var ddl2 = $("#selectGroupOnEdit");
                        ddl.find('option').remove()
                        for (k = 0; k < groups.length; k++) {
                            ddl.append("<option value='" + groups[k].id + "'>" + groups[k].description + "</option>");
                            ddl2.append("<option value='" + groups[k].id + "'>" + groups[k].description + "</option>");
                        }

//                $("#selectGroup").html("");
//                $(array_list).each(function (i) { //populate child options
//                    $("#selectGroup").append("<option value=\""+array_list[i].id+"\">"+array_list[i].description+"</option>");
                    }
                    ]
                });
            }
            function listCathedras() {
                var cathedras;
                $.ajax({
                    type: "GET",
                    url: 'getCathedras',
                    dataType: "json",
                    complete: [function (response) {
                        cathedras = $.parseJSON(response.responseText);
                        var ddl = $("#selectCathedra");
                        ddl.find('option').remove()
                        for (k = 0; k < cathedras.length; k++)
                            ddl.append("<option value='" + cathedras[k].id + "'>" + cathedras[k].description + "</option>");

//                $("#selectGroup").html("");
//                $(array_list).each(function (i) { //populate child options
//                    $("#selectGroup").append("<option value=\""+array_list[i].id+"\">"+array_list[i].description+"</option>");
                    }
                    ]
                });

            }
        </script>
        <script>

        </script>
        <style>
            .accordion {
                background-color: #eee;
                color: #444;
                cursor: pointer;
                padding: 18px;
                width: 100%;
                border: none;
                text-align: left;
                outline: none;
                font-size: 15px;
                transition: 0.4s;
            }

            .active, .accordion:hover {
                background-color: #ccc;
            }

            .panel {
                padding: 0 18px;
                display: none;
                background-color: white;
                overflow: hidden;
            }
        </style>
        <title>adminPage</title>
    </head>
    <body>
        <div id="result"></div>
        <h1>Hello admin</h1>
        <button class="accordion" onclick="test()">Show all students</button>
        <div class="panel">
        <%--<button name="objectType" id="ot" class="aclass" onclick="test()">All students</button>--%>
            <table id="personDataTable" style="visibility: hidden" border="" width="100%">
                <tr>
                    <th >ID</th>
                    <th >Name</th>
                    <th >Login</th>
                    <th >Password</th>
                    <th >Group</th>
                    <th>Edit</th>
                </tr>
            </table>
            <table id="studentPersone" style="visibility: hidden">
                <tr>
                    <td>id</td>
                    <td>Name</td>
                    <td>Login</td>
                    <td>Password</td>
                    <td>Group</td>
                </tr>
                <tr>
                    <td><label id="studentId">id</label></td>
                    <td><input id="studentName"/></td>
                    <td><input id="studentLogin"/></td>
                    <td><input id="studentPassword"/></td>
                    <td><select id="selectGroupOnEdit"></select></td>
                </tr>
                <tr>
                    <td>
                        <button id="saveStudentChage" onclick="saveStudentChange()">Save</button>
                    </td>
                    <td>
                        <button id="removeStudent" onclick="removeStudent()">Delete</button>
                    </td>
                    <td>
                        <button id="cancelStudent" onclick="onCancelStudent()">Cancel</button>
                    </td>
                </tr>
            </table>

        </div>
        <button class="accordion" onclick="lecturers()">Show all lecturers</button>
        <div class="panel">
            <table id="lecturersTable" style="visibility: hidden" border="" width="100%">
                <tr>
                    <th >ID</th>
                    <th >Name</th>
                    <th >Login</th>
                    <th >Password</th>
                    <th >Info</th>
                    <th >Degree</th>
                    <th >Works</th>
                    <th >Interests</th>
                </tr>
            </table>
        </div>
        <button class="accordion" onclick="listCathedras()">Register lecturer</button>
        <div class="panel">
            <jsp:useBean id="lecturer" class="mvc.beans.Lecturer"/>
            <c:set var="lecturer" value="${lecturer}" scope="request"/>
            <jsp:useBean id="object1" class="mvc.beans.Object"/>
            <c:set var="object1" value="${object1}" scope="request"/>
            <form  id ="registerLecturer" method="post" action="addLecturer" >
                <spring:bind path="lecturer.login">
                    <label>Login</label>
                    <input type="text" name="${status.expression}"><br />
                </spring:bind>
                <spring:bind path="lecturer.password">
                    <label>Password</label>
                    <input type="password" name="${status.expression}"><br />
                </spring:bind>
                <spring:bind path="lecturer.name">
                    <label>Name</label>
                    <input type="text" name="${status.expression}"><br />
                </spring:bind>
                <spring:bind path="object1.parentId">
                    <select id="selectCathedra" path="object1.parentId" name="${status.expression}"></select>
                    <%--<label>oParentId</label>--%>
                    <%--<input type="text" name="${status.expression}" ><br />--%>
                </spring:bind>
                <input type="submit" value="Create">
            </form>
        </div>
        <button class="accordion" onclick="listStudent()">Register student</button>
        <div class="panel">
            <jsp:useBean id="student" class="mvc.beans.Student"/>
            <c:set var="student" value="${student}" scope="request"/>
            <jsp:useBean id="object" class="mvc.beans.Object"/>
            <c:set var="object" value="${object}" scope="request"/>
            <form  id ="registerStudent" method="post" action="addStudent">
                <spring:bind path="student.login">
                    <label>Login</label>
                    <input type="text" name="${status.expression}"><br />
                </spring:bind>
                <spring:bind path="student.password">
                    <label>Password</label>
                    <input type="password" name="${status.expression}"><br />
                </spring:bind>
                <spring:bind path="student.name">
                    <label>Name</label>
                    <input type="text" name="${status.expression}"><br />
                </spring:bind>
                <spring:bind path="object.parentId">
                    <select id="selectGroup" path="object.parentId" name="${status.expression}"></select>
                    <%--<label>oParentId</label>--%>
                    <%--<input type="text" name="${status.expression}" ><br />--%>
                </spring:bind>
                <input type="submit" value="Create">
            </form>
        </div>
    </body>
</html>
