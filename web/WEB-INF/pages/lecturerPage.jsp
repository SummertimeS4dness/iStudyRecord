<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.07.2018
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errorPage.jsp" %>
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
                        var trHTML = '';
                        var obj = $.parseJSON(response.responseText);
                        for (var i = 0; i < obj.length; i++) {
                            trHTML += '<tr><td><label>' + obj[i].subject + '</label></td><td>' + obj[i].stringDate + '</td></tr>';
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
                    $("#lessonSubject").find('option').remove();
                    $('<option disabled selected value></option>').appendTo('#lessonSubject');
                    $.each(subjects, function (i, sub) {
                        $('<option value="' + sub.id + '">' + sub.fullName + '</option>').appendTo('#lessonSubject');
                    });
                }]
            });
        }

        function createLesson() {
            if(document.getElementById("lessonDate").value == "" ||
                document.getElementById("lessonSubject").options[document.getElementById("lessonSubject").selectedIndex].value =="") {
                alert("Check your input!")
                return
            }
            var lesson = {
                stringDate: document.getElementById("lessonDate").value,
                lecturerId:${lecturerID},
                subjectId: document.getElementById("lessonSubject").options[document.getElementById("lessonSubject").selectedIndex].value
            }
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
                    $("#markSubject").find('option').remove();
                    $('<option disabled selected value></option>').appendTo('#markSubject');
                    $.each(subjects, function (i, sub) {
                        $('<option value="' + sub.id + '">' + sub.fullName + '</option>').appendTo('#markSubject');
                    });
                    $('<option value="' + 56 + '">' + "hello" + '</option>').appendTo('#markSubject');
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
                            var students = $.parseJSON(response.responseText);
                            $('<option disabled selected value></option>').appendTo('#markStudent');
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
                            var lessons = $.parseJSON(response.responseText);
                            $('<option disabled selected value></option>').appendTo('#markLesson');
                            $.each(lessons, function (i, les) {
                                $('<option value="' + les.lessonId + '">' + les.stringDate + '</option>').appendTo('#markLesson');
                            });
                        }
                    ]
                });
                $('#markStudent').empty();
                $('#markLesson').empty();
            });
        }

        function createMark() {
            var x=document.getElementById("markScore").value;
            var regex=/^[0-9]+$/;
            if(document.getElementById("markLesson").options[document.getElementById("markLesson").selectedIndex].value == "" ||
                document.getElementById("markSubject").options[document.getElementById("markSubject").selectedIndex].value == "" ||
                document.getElementById("markStudent").options[document.getElementById("markStudent").selectedIndex].value == "" ||
                !x.match(regex) || x < 0 || x > 100) {
                alert("Check your input!");
                return
            }
            var mark = {
                lessonId: document.getElementById("markLesson").options[document.getElementById("markLesson").selectedIndex].value,
                score: document.getElementById("markScore").value,
                subjectId: document.getElementById("markSubject").options[document.getElementById("markSubject").selectedIndex].value,
                studentId: document.getElementById("markStudent").options[document.getElementById("markStudent").selectedIndex].value,
                lecturerId:${lecturerID}
            }
            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                url: "createMark",
                data: JSON.stringify(mark),
                success: function (response) {
                }
            });
        }
        function listGroups() {
            $.ajax({
                type: "GET",
                url: 'getGroup',
                dataType: "json",
                complete: [function (response) {
                    var groups = $.parseJSON(response.responseText);
                    $("#marksForGroupGroup").find('option').remove();
                    $('<option disabled selected value></option>').appendTo('#marksForGroupGroup');
                    $.each(groups, function (i, gr) {
                        $('<option value="' + gr.id + '">' + gr.description + '</option>').appendTo('#marksForGroupGroup');
                    });
                    $('<option value="' + 56 + '">' + "hello" + '</option>').appendTo('#marksForGroupGroup');
                }]
            });
            $('#marksForGroupGroup').change(function () {
                var object = {
                    id: document.getElementById("marksForGroupGroup").options[document.getElementById("marksForGroupGroup").selectedIndex].value,
                }
                $.ajax({
                    type: "GET",
                    url: 'getSubjectsForGroup',
                    dataType: "json",
                    contentType: 'application/json; charset=utf-8',
                    data: {id: object.id},
                    complete: [function (response) {
                        var subjects = $.parseJSON(response.responseText);
                        $("#marksForGroupSubject").find('option').remove();
                        $('<option disabled selected value></option>').appendTo('#marksForGroupSubject');
                        $.each(subjects, function (i, sub) {
                            $('<option value="' + sub.id + '">' + sub.fullName + '</option>').appendTo('#marksForGroupSubject');
                        });
                        $('<option value="' + 56 + '">' + "hello" + '</option>').appendTo('#marksForGroupSubject');
                    }]
                });
            });
            $('#marksForGroupSubject').change(function () {
                var object = {
                    objId: document.getElementById("marksForGroupGroup").options[document.getElementById("marksForGroupGroup").selectedIndex].value,
                    subjId: document.getElementById("marksForGroupSubject").options[document.getElementById("marksForGroupSubject").selectedIndex].value
                }
                $.ajax({
                    type: "GET",
                    url: 'getMarksForGroupAndSubject',
                    dataType: "json",
                    contentType: 'application/json; charset=utf-8',
                    data:{objId:object.objId,subjId:object.subjId},
                    complete: [function (response) {
                        $("#marksForGroupTable").find("tr:not(:first)").remove();
                        var trHTML = '';
                        var obj = $.parseJSON(response.responseText);
                        for (var i = 0; i < obj.length; i++) {
                            trHTML += '<tr><td>' + obj[i].studentName + '</td><td>' + obj[i].score + '</td><td>' + obj[i].stringDate + '</td></tr>';
                            console.log(trHTML);
                        }
                        $("#marksForGroupTable tbody").append(trHTML);
                    }]
                });
            });
        }
        $(document).ready(function () {
            var acc = document.getElementsByClassName("accordion");
            var i;

            for (i = 0; i < acc.length; i++) {
                acc[i].addEventListener("click", function() {
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
            <td><input type="datetime-local" id="lessonDate"></td>
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
<button class="accordion" onclick="listGroups()">Marks for group</button>
<div class="panel">
    <table>
        <tr>
            <td><label>Select group</label></td>
            <td><select id="marksForGroupGroup"></select></td>
        </tr>
        <tr>
            <td><label>Select subject</label></td>
            <td><select id="marksForGroupSubject"></select></td>
        </tr>
    </table>
    <table id="marksForGroupTable" border="2" align="center">
        <tr>
            <th>Student</th>
            <th>Mark</th>
            <th>Date</th>
        </tr>
    </table>
</div>
</body>
</html>
