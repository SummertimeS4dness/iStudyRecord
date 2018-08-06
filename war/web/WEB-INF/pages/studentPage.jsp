<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="errorPage.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script type="text/javascript">
            function getProfile() {
                $.ajax({
                    type: "GET",
                    url: 'getStudentProfile',
                    dataType: "json",
                    contentType: 'application/json; charset=utf-8',
                    data: {id: ${studentID}},
                    complete: [
                        function (response) {
                            var obj = $.parseJSON(response.responseText);
                            document.getElementById("studentName").value = obj.name;
                            document.getElementById("studentLogin").value = obj.login;
                            document.getElementById("studentPassword").value = obj.password;
                        }
                    ]
                });
            }
            function save() {
                var student = {
                    id: ${studentID},
                    login: document.getElementById("studentLogin").value,
                    password: document.getElementById("studentPassword").value,
                    name: document.getElementById("studentName").value,
                };
                if(student.login==""||student.password==""||student.name==""){
                    alert("Fill everything!");
                    return;
                }
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',
                    url: "updateStudentProfile",
                    data: JSON.stringify(student),
                    success: function (response) {
                        getProfile();
                        alert("Profile data changed successfully!");
                    },
                    error: function (xhr, status, errorThrown) {
                        alert(status + " " + errorThrown.toString());
                    }
                });
            }
            function getMarks() {
                $.ajax({
                    type: "get",
                    url: 'marks',
                    dataType: "json",
                    complete: [
                        function (response) {
                            $("#marksTable tr").remove();
                            var trHTML = '';
                            var obj = $.parseJSON(response.responseText);
                            var res = obj[0].stringDate;
                            var count = 1;
                            trHTML += '<tr><td colspan="2" align="center"><b>' + count + ". " + obj[0].stringDate + '</b></tr>';
                            for (var i = 0; i < obj.length; i++) {
                                if(res != obj[i].stringDate) {
                                    count++;
                                    trHTML += '<tr><td colspan="2"><br/></td></tr><tr><td colspan="2" align="center"><b>'
                                        + count + ". " + obj[i].stringDate + '</b></td></tr>';
                                    res = obj[i].stringDate;
                                }
                                trHTML += '<tr><td>' + obj[i].subjectName + '</td><td>' + obj[i].score + '</td></tr>';
                            }
                            $("#marksTable tbody").append(trHTML);
                        }
                    ]
                });
            }
            function getSchedule() {
                $.ajax({
                    type: "get",
                    url: 'studentSchedule',
                    dataType: "json",
                    complete: [
                        function (response) {
                            $("#scheduleTable tr").remove();
                            var trHTML = '';
                            var obj = $.parseJSON(response.responseText);
                            var res = obj[0].day;
                            var count = 1;
                            trHTML += '<tr><td colspan="3" align="center"><b>' + count + ". " + obj[0].day + '</b></tr>';
                            for (var i = 0; i < obj.length; i++) {
                                if(res != obj[i].day) {
                                    count++;
                                    trHTML += '<tr><td colspan="3"><br/></td></tr><tr><td colspan="3" align="center"><b>' + count + ". " + obj[i].day + '</b></td></tr>';
                                    res = obj[i].day;
                                }
                                trHTML += '<tr><td><label>' + obj[i].subject + '</label></td><td><button onclick=' +
                                    '"getLecturerBySubject(' + obj[i].subjectId + ')">' + obj[i].lecturer + '</button>' +
                                    '</td><td>' + obj[i].time + '</td></tr>';
                            }
                            $("#scheduleTable tbody").append(trHTML);
                        }
                    ]
                });
            }
            function getLecturerBySubject(id) {
                var subject = {
                    id: id,
                }
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',
                    dataType: "json",
                    url: "getLecturerBySubject",
                    data: JSON.stringify(subject),
                    complete: [
                        function (response) {
                            var lecturer = $.parseJSON(response.responseText);
                            alert("Info about lecturer:" +
                                "\nname: " + lecturer.name +
                                "\ninfo: " + lecturer.info +
                                "\ndegree: " + lecturer.degree +
                                "\nworks: " + lecturer.works +
                                "\ninterests: " + lecturer.interests +
                                "\ncathedra: " + lecturer.cathedra);

                        }
                    ]
                });
            }
            $(document).ready(function () {
                var acc = document.getElementsByClassName("accordion");
                var i;
                for (i = 0; i < acc.length; i++) {
                    acc[i].addEventListener("click", function () {
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
            /*filter*/(function(document) {
                'use strict';

                var LightTableFilter = (function(Arr) {

                    var _input;

                    function _onInputEvent(e) {
                        _input = e.target;
                        var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
                        Arr.forEach.call(tables, function(table) {
                            Arr.forEach.call(table.tBodies, function(tbody) {
                                Arr.forEach.call(tbody.rows, _filter);
                            });
                        });
                    }

                    function _filter(row) {
                        var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
                        row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
                    }

                    return {
                        init: function() {
                            var inputs = document.getElementsByClassName('light-table-filter');
                            Arr.forEach.call(inputs, function(input) {
                                input.oninput = _onInputEvent;
                            });
                        }
                    };
                })(Array.prototype);

                document.addEventListener('readystatechange', function() {
                    if (document.readyState === 'complete') {
                        LightTableFilter.init();
                    }
                });

            })(document);
        </script>
        <style>
            .accordion {
                background-color: #eee;
                color: #444;
                cursor: pointer;
                padding: 8px;
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
        <title>Welcome</title>
    </head>
    <body>
        <h1>Hello, ${name}. Your group - ${group}, starosta - ${starosta}</h1>
        <input type="search" class="light-table-filter" data-table="order-table" placeholder="Input something to search">
        <button class="accordion" onclick="getProfile()">Edit profile data</button>
        <div class="panel">
            <table id="student">
                <tr>
                    <td>Name</td>
                    <td>Login</td>
                    <td>Password</td>
                </tr>
                <tr>
                    <td><input id="studentName"/></td>
                    <td><input id="studentLogin"/></td>
                    <td><input id="studentPassword"/></td>
                </tr>
                <tr>
                    <td>
                        <button id="save" onclick="save()">Save</button>
                    </td>
                </tr>
            </table>
        </div>
        <button class="accordion" onclick="getMarks()">Marks</button>
        <div class="panel">
            <table id="marksTable"  align="left" class="order-table">
                <thead>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <button class="accordion" onclick="getSchedule()">Schedule</button>
        <div class="panel">
            <p>To see info about lecturer click on it in the schedule</p>
            <table id="scheduleTable"  align="left" class="order-table">
                <thead>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </body>
</html>