
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
                }]
            });

            $('#markSubject').change(function () {
                var subject = {
                    id: document.getElementById("markSubject").options[document.getElementById("markSubject").selectedIndex].value
                }
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
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',
                    url: 'getGroupsForSubject',
                    data: JSON.stringify(subject),
                    complete: [
                        function (response) {
                            var groups = $.parseJSON(response.responseText);
                            $('<option disabled selected value></option>').appendTo('#markGroup');
                            $.each(groups, function (i, gr) {
                                $('<option value="' + gr.id + '">' + gr.description + '</option>').appendTo('#markGroup');
                            });
                        }
                    ]
                });
                $('#markGroup').empty();
                $('#markStudent').empty();
                $('#markLesson').empty();
            });
            //jdfkghdfkhgkjdfg
            $('#markGroup').change(function () {
                var group = {
                    id: document.getElementById("markGroup").options[document.getElementById("markGroup").selectedIndex].value
                }
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',
                    url: 'getStudentsForGroup',
                    data: JSON.stringify(group),
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
                $('#markStudent').empty();
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
                success: function (data, textStatus, xhr) {
                    alert("Mark put");
                },
                error: function (data, textStatus, xhr) {
                    alert("Something went wrong, mark not put\nText of error: " + data.responseText);
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
<h1>Hello, ${name}</h1>
<input type="search" class="light-table-filter" data-table="order-table" placeholder="Filter">
<button class="accordion" onclick="getSchedule()">Schedule</button>
<div class="panel">
    <table id="scheduleTable" align="left" class="order-table" style="width: 50%">
        <thead>
        <tr>
            <th align="left">Subject</th>
            <th align="left">Date</th>
        </tr>
        </thead>
        <tbody></tbody>
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
            <td><label>Group</label></td>
            <td><select id="markGroup"></select></td>
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
    <table id="marksForGroupTable" align="left" class="order-table" style="width: 50%">
        <thead>
        <tr>
            <th align="left">Student</th>
            <th align="left">Mark</th>
            <th align="left">Date</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
</body>
</html>
