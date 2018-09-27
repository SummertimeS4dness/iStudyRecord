<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errorPage.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript">
        function getProfile() {
            $.ajax({
                type: "GET",
                url: 'getLecturerProfile',
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: {id: ${lecturerID}},
                complete: [
                    function (response) {
                        var obj = $.parseJSON(response.responseText);
                        document.getElementById("lecturerName").value = obj.name;
                        document.getElementById("lecturerLogin").value = obj.login;
                        document.getElementById("lecturerPassword").value = obj.password;
                        document.getElementById("lecturerInfo").value = obj.password;
                        document.getElementById("lecturerDegree").value = obj.degree;
                        document.getElementById("lecturerWorks").value = obj.works;
                        document.getElementById("lecturerInterests").value = obj.interests;
                    }
                ]
            });
        }

        function saveProfile() {
            var lecturer = {
                id: ${lecturerID},
                name: document.getElementById("lecturerName").value,
                login: document.getElementById("lecturerLogin").value,
                password: document.getElementById("lecturerPassword").value,
                info: document.getElementById("lecturerInfo").value,
                degree: document.getElementById("lecturerDegree").value,
                works: document.getElementById("lecturerWorks").value,
                interests: document.getElementById("lecturerInterests").value,
            };
            if (lecturer.login == "" || lecturer.password == "" || lecturer.name == "") {
                alert("Fill everything!");
                return;
            }
            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                url: "updateLecturerProfile",
                data: JSON.stringify(lecturer),
                success: function (response) {
                    getProfile();
                    alert("Profile data changed successfully!");
                },
                error: function (xhr, status, errorThrown) {
                    alert(status + " " + errorThrown.toString());
                }
            });
        }

        function getSchedule() {
            $.ajax({
                type: "GET",
                url: 'lecturerSchedule',
                dataType: "json",
                complete: [
                    function (response) {
                        $("#scheduleTable tr").remove();
                        var trHTML = '';
                        var obj = $.parseJSON(response.responseText);
                        var res = obj[0].day;
                        var count = 1;
                        trHTML += '<tr><td colspan="2" align="center"><b>' + obj[0].day + '</b></tr>';
                        for (var i = 0; i < obj.length; i++) {
                            if (res != obj[i].day) {
                                trHTML += '<tr><td colspan="3"><br/></td></tr><tr><td colspan="2" align="center"><b>' + obj[i].day + '</b></td></tr>';
                                res = obj[i].day;
                                count = 1;
                            }
                            trHTML += '<tr><td>' + count + '</td><td>' + obj[i].subject + '</td><td>' + obj[i].time + '</td></tr>';
                            count++;
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
            if (document.getElementById("lessonDate").value == "" ||
                document.getElementById("lessonSubject").options[document.getElementById("lessonSubject").selectedIndex].value == "") {
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
            $('#markYear').empty();
            $('#markMonth').empty();
            $('#markDay').empty();
            $('#markTime').empty();
            var lessons;
            var year, month, day;
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
                            lessons = $.parseJSON(response.responseText);
                            /*$('<option disabled selected value></option>').appendTo('#markLesson');
                            $.each(lessons, function (i, les) {
                                $('<option value="' + les.lessonId + '">' + les.stringDate + '</option>').appendTo('#markLesson');
                            });*/
                            $('#markYear').find('option').remove();
                            $('<option disabled selected value></option>').appendTo('#markYear');
                            $.each(lessons, function (i, les) {
                                var opts = [];
                                $('#markYear option').each(
                                    function () {
                                        opts.push($(this).text());
                                    });
                                if ($.inArray(les.year, opts) == -1) {
                                    $('<option>' + les.year + '</option>').appendTo('#markYear');
                                }
                            });
                        }
                    ]
                });
                $('#markYear').change(function () {
                    year = document.getElementById("markYear").options[document.getElementById("markYear").selectedIndex].value
                    $('#markMonth').find('option').remove();
                    $('<option disabled selected value></option>').appendTo('#markMonth');
                    $.each(lessons, function (i, les) {
                        if (les.year == year) {
                            //alert("from change: " + les.year)
                            var opts = [];
                            $('#markMonth option').each(
                                function () {
                                    opts.push($(this).text());
                                });
                            if ($.inArray(les.month, opts) == -1) {
                                $('<option>' + les.month + '</option>').appendTo('#markMonth');
                            }
                        }
                    });
                });
                $('#markMonth').change(function () {
                    month = document.getElementById("markMonth").options[document.getElementById("markMonth").selectedIndex].value
                    $('#markDay').find('option').remove();
                    $('<option disabled selected value></option>').appendTo('#markDay');
                    $.each(lessons, function (i, les) {
                        if (les.month == month && les.year == year) {
                            //alert("from change: " + les.year)
                            var opts = [];
                            $('#markDay option').each(
                                function () {
                                    opts.push($(this).text());
                                });
                            if ($.inArray(les.day, opts) == -1) {
                                $('<option>' + les.day + '</option>').appendTo('#markDay');
                            }
                        }
                    });
                });
                $('#markDay').change(function () {
                    day = document.getElementById("markDay").options[document.getElementById("markDay").selectedIndex].value
                    $('#markTime').find('option').remove();
                    $('<option disabled selected value></option>').appendTo('#markTime');
                    $.each(lessons, function (i, les) {
                        if (les.month == month && les.year == year && les.day == day) {
                            var opts = [];
                            $('#markTime option').each(
                                function () {
                                    opts.push($(this).text());
                                });
                            if ($.inArray(les.day, opts) == -1) {
                                $('<option value="' + les.lessonId + '">' + les.time + '</option>').appendTo('#markTime');
                            }
                        }
                    });
                });
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',
                    url: 'getGroupsForSubject',
                    data: JSON.stringify(subject),
                    complete: [
                        function (response) {
                            var groups = $.parseJSON(response.responseText);
                            $('#markGroup').find('option').remove();
                            $('<option disabled selected value></option>').appendTo('#markGroup');
                            $.each(groups, function (i, gr) {
                                $('<option value="' + gr.id + '">' + gr.description.substring(0, gr.description.indexOf(" cours")) + '</option>').appendTo('#markGroup');
                            });
                        }
                    ]
                });
                $('#markGroup').empty();
                $('#markStudent').empty();
            });
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
                            $('#markStudent').find('option').remove();
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
            var x = document.getElementById("markScore").value;
            var regex = /^[0-9]+$/;
            if (/*document.getElementById("markLesson").options[document.getElementById("markLesson").selectedIndex].value == "" ||*/
            document.getElementById("markYear").options[document.getElementById("markYear").selectedIndex].value == "" ||
            document.getElementById("markMonth").options[document.getElementById("markMonth").selectedIndex].value == "" ||
            document.getElementById("markDay").options[document.getElementById("markDay").selectedIndex].value == "" ||
            document.getElementById("markTime").options[document.getElementById("markTime").selectedIndex].value == "" ||
            document.getElementById("markGroup").options[document.getElementById("markGroup").selectedIndex].value == "" ||
            document.getElementById("markSubject").options[document.getElementById("markSubject").selectedIndex].value == "" ||
            document.getElementById("markStudent").options[document.getElementById("markStudent").selectedIndex].value == "" ||
            !x.match(regex) || x < 0 || x > 100) {
                alert("Check your input!");
                return
            }
            var mark = {
                lessonId: document.getElementById("markTime").options[document.getElementById("markTime").selectedIndex].value,
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
            $("#marksForGroupTable tr").remove();
            document.getElementById("markEdit").style.visibility = "hidden";
            $("#marksForGroupGroup").find('option').remove();
            $("#marksForGroupSubject").find('option').remove();
            $.ajax({
                type: "GET",
                url: 'getGroup',
                dataType: "json",
                complete: [
                    function (response) {
                        var groups = $.parseJSON(response.responseText);
                        $("#marksForGroupGroup").find('option').remove();
                        $('<option disabled selected value></option>').appendTo('#marksForGroupGroup');
                        $.each(groups, function (i, gr) {
                            $('<option value="' + gr.id + '">' + gr.description.substring(0, gr.description.indexOf(" cours")) + '</option>').appendTo('#marksForGroupGroup');
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
                    data: {objId: object.objId, subjId: object.subjId},
                    complete: [function (response) {
                        $("#marksForGroupTable tr").remove();
                        var trHTML = '';
                        var obj = $.parseJSON(response.responseText);
                        var res = obj[0].stringDate;
                        var count = 1;
                        trHTML += '<tr><td colspan="2" align="center"><b>' + count + ". " + obj[0].stringDate + '</b></tr>';
                        for (var i = 0; i < obj.length; i++) {
                            if (res != obj[i].stringDate) {
                                count++;
                                trHTML += '<tr><td colspan="2"><br/></td></tr><tr><td colspan="2" align="center"><b>'
                                    + count + ". " + obj[i].stringDate + '</b></td></tr>';
                                res = obj[i].stringDate;
                            }
                            trHTML += '<tr><td>' + obj[i].studentName + '</td><td>' + obj[i].score + '</td><td><button ' +
                                'id="' + i + '"class="myclassk">edit</button></td></tr>';
                            var a = obj;
                            $(document).off().on('click', 'button.myclassk', function (event) {
                                onMarkEdit(this.id, a);
                            });
                        }
                        $("#marksForGroupTable tbody").append(trHTML);
                    }]
                });
            });
        }

        function onMarkEdit(pos, mass) {
            document.getElementById("markEdit").style.visibility = "visible";
            document.getElementById("markIdd").innerHTML = mass[pos].id;
            document.getElementById("student").innerHTML = mass[pos].studentName;
            document.getElementById("subject").innerHTML = document.getElementById("marksForGroupSubject").options[document.getElementById("marksForGroupSubject").selectedIndex].text;
            document.getElementById("lesson").innerHTML = mass[pos].stringDate;
            document.getElementById("score").value = mass[pos].score;
        }

        function saveMark() {
            var mark = {
                id: document.getElementById("markIdd").innerHTML,
                score: document.getElementById("score").value
            }
            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                url: 'updateMark',
                data: JSON.stringify(mark),
                success: function (response) {
                    document.getElementById("markEdit").style.visibility = "hidden";
                    alert("Mark edited successfully!");
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
        /*filter*/
        (function (document) {
            'use strict';

            var LightTableFilter = (function (Arr) {

                var _input;

                function _onInputEvent(e) {
                    _input = e.target;
                    var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
                    Arr.forEach.call(tables, function (table) {
                        Arr.forEach.call(table.tBodies, function (tbody) {
                            Arr.forEach.call(tbody.rows, _filter);
                        });
                    });
                }

                function _filter(row) {
                    var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
                    row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
                }

                return {
                    init: function () {
                        var inputs = document.getElementsByClassName('light-table-filter');
                        Arr.forEach.call(inputs, function (input) {
                            input.oninput = _onInputEvent;
                        });
                    }
                };
            })(Array.prototype);

            document.addEventListener('readystatechange', function () {
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
<input type="search" class="light-table-filter" data-table="order-table" placeholder="Input something to search">
<button class="accordion" onclick="getProfile()">Edit profile data</button>
<div class="panel">
    <table id="lecturer">
        <tr>
            <td>Name</td>
            <td>Login</td>
            <td>Password</td>
            <td>Info</td>
            <td>Degree</td>
            <td>Works</td>
            <td>Interests</td>
        </tr>
        <tr>
            <td><input id="lecturerName" style='width:100%'/></td>
            <td><input id="lecturerLogin" style='width:100%'/></td>
            <td><input id="lecturerPassword"/></td>
            <td><input id="lecturerInfo"/></td>
            <td><input id="lecturerDegree"/></td>
            <td><input id="lecturerWorks"/></td>
            <td><input id="lecturerInterests"/></td>
        </tr>
        <tr>
            <td>
                <button id="saveProfile" onclick="saveProfile()">Save</button>
            </td>
        </tr>
    </table>
</div>
<button class="accordion" onclick="getSchedule()">Schedule</button>
<div class="panel">
    <table id="scheduleTable" align="left" class="order-table">
        <thead>
        <%--<tr>
            <th align="left">Subject</th>
            <th align="left">Date</th>
        </tr>--%>
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
            <td><label>Year</label></td>
            <td><select id="markYear"></select></td>
        </tr>
        <tr>
            <td><label>Month</label></td>
            <td><select id="markMonth"></select></td>
        </tr>
        <tr>
            <td><label>Day</label></td>
            <td><select id="markDay"></select></td>
        </tr>
        <tr>
            <td><label>Time</label></td>
            <td><select id="markTime"></select></td>
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
    <table id="marksForGroupTable" align="left" class="order-table">
        <thead>
        <%--<tr>
            <th align="left">Student</th>
            <th align="left">Mark</th>
            <th align="left">Date</th>
        </tr>--%>
        </thead>
        <tbody></tbody>
    </table>
    <table id="markEdit" style="visibility: hidden" width="100%">
        <tr>
            <th>ID</th>
            <th>Subject</th>
            <th>Student</th>
            <th>Lesson</th>
            <th>Score</th>
        </tr>
        <tr>
            <td id="markIdd"></td>
            <td id="subject"></td>
            <td id="student"></td>
            <td id="lesson"></td>
            <td><input id="score"/></td>
        </tr>
        <tr>
            <td>
                <button id="saveMark" onclick="saveMark()">Save</button>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
