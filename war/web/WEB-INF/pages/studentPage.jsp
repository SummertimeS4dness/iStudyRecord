<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="errorPage.jsp" %>
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
                            var trHTML = '';
                            var obj = $.parseJSON(response.responseText);
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
                            var trHTML = '';
                            var obj = $.parseJSON(response.responseText);
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
                            var lecturer = $.parseJSON(response.responseText);
                            alert("Info about lecturer:" +
                                "\nname: " + lecturer.name +
                                "\ninfo: " + lecturer.info +
                                "\ndegree: " + lecturer.degree +
                                "\nworks: " + lecturer.works +
                                "\ninterests: " + lecturer.interests +
                                "\ncathedra:" + lecturer.cathedra);

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
        <input type="search" class="light-table-filter" data-table="order-table" placeholder="Filter">
        <button class="accordion" onclick="getMarks()">Marks</button>
        <div class="panel">
            <table id="marksTable"  align="left" class="order-table" style="width: 50%">
                <thead>
                <tr>
                    <th align="left">Subject</th>
                    <th align="left">Score</th>
                    <th align="left">Date</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <button class="accordion" onclick="getSchedule()">Schedule</button>
        <div class="panel">
            <table id="scheduleTable"  align="left" style="width: 50%" class="order-table">
                <thead>
                <tr>
                    <th align="left">Subject</th>
                    <th align="left">Lecturer</th>
                    <th align="left">Date</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </body>
</html>