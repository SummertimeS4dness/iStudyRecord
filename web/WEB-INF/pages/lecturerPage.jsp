<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.07.2018
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript">
        function getSchedule() {
            $.ajax({
                type: "GET",
                url: 'lecturerSchedule',
                dataType: "json",
                complete: [
                    function (response) {
                        $("#scheduleTable").find("tr:not(:first)").remove();
                        alert(response.responseText);
                        var trHTML = '';
                        var obj = $.parseJSON(response.responseText);
                        alert(obj.length);
                        for (var i = 0; i < obj.length; i++) {
                            trHTML += '<tr><td><label>' + obj[i].subject + '</label></td><td>' + obj[i].date + '</td></tr>';
                            console.log(trHTML);
                        }
                        $("#scheduleTable tbody").append(trHTML);
                    }
                ]
            });
        }

        function listLessonSubjects() {
            $.ajax({
                type: "GET",
                url: 'getSubjectsForLecturer',
                dataType: "json",
                complete: [function (response) {
                    var subjects = $.parseJSON(response.responseText);
                    alert(response.responseText)
                    var ddl = $("#lessonSubject");
                    ddl.find('option').remove();
                    for (k = 0; k < subjects.length; k++) {
                        ddl.append("<option value='" + subjects[k].id + "'>" + subjects[k].fullName + "</option>");
                    }
                }]
            });
        }

        function createLesson() {
            var lesson = {
                date: document.getElementById("lessonDate").value,
                lecturerId:${lecturerID},
                subjectId: document.getElementById("lessonSubject").options[document.getElementById("lessonSubject").selectedIndex].value
            }
            alert(JSON.stringify(lesson))
            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                url: "createLesson",
                data: JSON.stringify(lesson),
                success: function (response) {
                }
            });
        }

        function listMarkSubjectAndStudent() {
            $.ajax({
                type: "GET",
                url: 'getSubjectsForLecturer',
                dataType: "json",
                complete: [function (response) {
                    var subjects = $.parseJSON(response.responseText);
                    //alert(response.responseText)
                    var ddl = $("#markSubject");
                    ddl.find('option').remove();
                    for (k = 0; k < subjects.length; k++) {
                        ddl.append("<option value='" + subjects[k].id + "'>" + subjects[k].fullName + "</option>");
                    }
                    ddl.append("<option value='" + 56 + "'>" + "hello" + "</option>");
                }]
            });

            $('#markSubject').change(function () {
                var subject = {
                    id: document.getElementById("markSubject").options[document.getElementById("markSubject").selectedIndex].value
                }
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',
                    url: 'getStudentsForSubject',
                    data: JSON.stringify(subject),
                    complete: [
                        function (response) {
                            alert(response.responseText)
                            var students = $.parseJSON(response.responseText);
                            $.each(students, function (i, st) {
                                $('<option value="' + st.id + '">' + st.name + '</option>').appendTo('#markStudent');
                            });
                        }
                    ]
                });
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',
                    url: 'getLessonsForSubject',
                    data: JSON.stringify(subject),
                    complete: [
                        function (response) {
                            alert(response.responseText)
                            var lessons = $.parseJSON(response.responseText);
                            $.each(lessons, function (i, les) {
                                $('<option value="' + les.lessonId + '">' + les.date + '</option>').appendTo('#markLesson');
                            });
                        }
                    ]
                });
                $('#markStudent').empty();
                $('#markLesson').empty();
            });
        }

        function createMark() {
            var mark = {
                lessonId: document.getElementById("markLesson").options[document.getElementById("markLesson").selectedIndex].value,
                score: document.getElementById("markScore").value,
                subjectId: document.getElementById("markSubject").options[document.getElementById("markSubject").selectedIndex].value,
                studentId: document.getElementById("markStudent").options[document.getElementById("markStudent").selectedIndex].value,
                lecturerId:${lecturerID}
            }
            alert(JSON.stringify(mark))
            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                url: "createMark",
                data: JSON.stringify(mark),
                success: function (response) {
                }
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
            //alert(${lecturerID})
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
<button class="accordion" onclick="getSchedule()">Schedule</button>
<div class="panel">
    <table id="scheduleTable" border="2" align="center">
        <tr>
            <th>Subject</th>
            <th>Date</th>
        </tr>
    </table>
</div>
<button class="accordion" onclick="listLessonSubjects()">Add lesson</button>
<div class="panel">
    <table>
        <tr>
            <td><label>Date</label></td>
            <td><input type="text" id="lessonDate"></td>
        </tr>
        <tr>
            <td><label>Subject</label></td>
            <td><select id="lessonSubject"></select></td>
        </tr>
        <tr>
            <td>
                <button onclick="createLesson()">Create</button>
            </td>
        </tr>
    </table>
</div>
<button class="accordion" onclick="listMarkSubjectAndStudent()">Put mark</button>
<div class="panel">
    <table>
        <tr>
            <td><label>Subject</label></td>
            <td><select id="markSubject"></select></td>
        </tr>
        <tr>
            <td><label>Lesson</label></td>
            <td><select id="markLesson"></select></td>
        </tr>
        <tr>
            <td><label>Student</label></td>
            <td><select id="markStudent"></select></td>
        </tr>
        <tr>
            <td><label>Mark</label></td>
            <td><input type="text" id="markScore"></td>
        </tr>
        <tr>
            <td>
                <button onclick="createMark()">Create</button>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
