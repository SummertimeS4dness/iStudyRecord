<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script type="text/javascript">
            function getMarks() {
                $.ajax({
                    type: "get",
                    url: 'marks',
                    dataType: "json",
                    complete: [
                        function (response) {
                            $("#marksTable").find("tr:not(:first)").remove();
                            alert(response.responseText);
                            var trHTML = '';
                            var obj = $.parseJSON(response.responseText);
                            alert(obj.length);
                            for (var i = 0; i < obj.length; i++) {
                                trHTML += '<tr><td>' + obj[i].subjectName + '</td><td>' + obj[i].score + '</td><td>' + obj[i].stringDate + '</td></tr>';
                                console.log(trHTML);
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
                            $("#scheduleTable").find("tr:not(:first)").remove();
                            alert(response.responseText);
                            var trHTML = '';
                            var obj = $.parseJSON(response.responseText);
                            alert(obj.length);
                            for (var i = 0; i < obj.length; i++) {
                                trHTML += '<tr onclick="getLecturerBySubject(' + obj[i].subjectId + ')"><td><label>' +
                                    obj[i].subject + '</label></td><td>' + obj[i].lecturer + '</td><td>' + obj[i].stringDate + '</td></tr>';
                                console.log(trHTML);
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
                            alert(response.responseText)
                            var lecturer = $.parseJSON(response.responseText);
                            alert("Name: " + lecturer.name + "\nInfo: " + lecturer.info + "\nDegree: " + lecturer.degree
                                + "\nWorks: " + lecturer.works + "\nInterests: " + lecturer.interest)
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
                /*$.ajax({
                    type: "GET",
                    contentType: 'application/json; charset=utf-8',
                    dataType: "json",
                    url: "getName",
                    complete: [
                        function (response) {
                            $("h1").append('Hello, ' + response.responseText)
                        }
                    ]
                });*/
            });
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
        <title>Welcome</title>
    </head>
    <body>
        <h1>Hello, ${name}</h1>
        <button class="accordion" onclick="getMarks()">Marks</button>
        <div class="panel">
            <table id="marksTable" border = "2" align="center">
                <tr>
                    <th>Subject</th>
                    <th>Score</th>
                    <th>Date</th>
                </tr>
            </table>
        </div>
        <button class="accordion" onclick="getSchedule()">Schedule</button>
        <div class="panel">
            <table id="scheduleTable" border = "2" align="center">
                <tr>
                    <th>Subject</th>
                    <th>Lecturer</th>
                    <th>Date</th>
                </tr>
            </table>
        </div>
    </body>
</html>