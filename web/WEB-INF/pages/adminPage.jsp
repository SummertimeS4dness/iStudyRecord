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
            (function(document) {
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
            var shown = 0;
            var lecturerShown=0;
            var subjectsShown=0;
            var objectShown=0;
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
                    url: 'getGroup',
                    dataType: "json",
                    complete: [function (response) {
                        groups = $.parseJSON(response.responseText);
                        var ddl = $("#selectGroup");
                        var ddl2 = $("#selectGroupOnEdit");
                        var ddl3=$("#selectParent");
                        ddl.find('option').remove();
                      //  ddl.append("<option style=\"display:none\" selected=\"selected\"/>");
                        ddl2.find('option').remove();
                        ddl3.find('option').remove();

                      //  ddl2.append("<option style=\"display:none\" selected=\"selected\"/>");
                        for (k = 0; k < groups.length; k++) {
                            ddl.append("<option value='" + groups[k].id + "'>" + groups[k].description + "</option>");
                            ddl2.append("<option value='" + groups[k].id + "'>" + groups[k].description + "</option>");
                            ddl3.append("<option value='" + groups[k].id + "'>" + groups[k].description + "</option>");
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
                    url: 'getCathedra',
                    dataType: "json",
                    complete: [function (response) {
                        cathedras = $.parseJSON(response.responseText);
                        var ddl = $("#selectCathedra");
                        ddl.find('option').remove();
                        var ddl2 = $("#selectCathedraOnEdit");
                        ddl2.find('option').remove();
                        var ddl3 = $("#selectParent");
                        ddl3.find('option').remove();

                        for (k = 0; k < cathedras.length; k++) {
                            ddl.append("<option value='" + cathedras[k].id + "'>" + cathedras[k].description + "</option>");
                            ddl2.append("<option value='" + cathedras[k].id + "'>" + cathedras[k].description + "</option>");
                            ddl3.append("<option value='" + cathedras[k].id + "'>" + cathedras[k].description + "</option>");
                        }
//                $("#selectGroup").html("");
//                $(array_list).each(function (i) { //populate child options
//                    $("#selectGroup").append("<option value=\""+array_list[i].id+"\">"+array_list[i].description+"</option>");
                    }
                    ]
                });

            }
            function listLecturers() {
                var lecturers;
                $.ajax({
                    type: "GET",
                    url: 'getLecturers',
                    dataType: "json",
                    complete: [function (response) {
                        lecturers = $.parseJSON(response.responseText);
                        var ddl = $("#lecturersForSubjectCreate");
                        ddl.find('option').remove();
                         var ddl2 = $("#selectSubjectOnEdit");
                         ddl2.find('option').remove()
                        for (k = 0; k < lecturers.length; k++) {
                            ddl.append("<option value='" + lecturers[k].id + "'>" + lecturers[k].name + "</option>");
                             ddl2.append("<option value='" + lecturers[k].id + "'>" + lecturers[k].name + "</option>");
                        }
//                $("#selectGroup").html("");
//                $(array_list).each(function (i) { //populate child options
//                    $("#selectGroup").append("<option value=\""+array_list[i].id+"\">"+array_list[i].description+"</option>");
                    }
                    ]
                });
            }
            //lecturer
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
                                        '<td>' + obj[i].interests + '</td>' +
                                        '<td>' + obj[i].cathedra + '</td><td>' +
                                        '<button id="' + i + '"class="myclassk">edit</button> </td></tr>';
                                    var a = obj;
                                    $(document).off().on('click', 'button.myclassk', function (event) {
                                        listCathedras();
                                        onLecturerEdit(this.id, a);
                                    });
                                }
                                $("#lecturersTable tbody").append(trHTML);
                            }
                        ]
                    });
                } else {
                    lecturerShown = 0;
//                    document.getElementById("personDataTable").style.visibility = "hidden";
                    document.getElementById("lecturerPerson").style.visibility = "hidden";
//                    document.getElementById("studentId").textContent = '';
//                    document.getElementById("studentName").value = '';
//                    document.getElementById("studentLogin").value = '';
//                    document.getElementById("studentPassword").value = '';
                }
            }
            function onLecturerEdit(pos, mass) {
                var lecturer = {
                    id: mass[pos].id,
                    login: mass[pos].login,
                    password: mass[pos].password,
                    name: mass[pos].name,
                    cathedra:mass[pos].cathedra,
                    info:mass[pos].info,
                    degree:mass[pos].degree,
                    works:mass[pos].works,
                    interests:mass[pos].interests,
                    cathedraId:mass[pos].cathedraId
                }
                document.getElementById("lecturerPerson").style.visibility = "visible";
                document.getElementById("lecturerId").textContent = lecturer.id;
                document.getElementById("lecturerName").value = lecturer.name;
                document.getElementById("lecturerLogin").value = lecturer.login;
                document.getElementById("lecturerPassword").value = lecturer.password;
                document.getElementById("lecturerInfo").value = lecturer.password;
                document.getElementById("lecturerDegree").value = lecturer.degree;
                document.getElementById("lecturerWorks").value = lecturer.works;
                document.getElementById("lecturerInterests").value = lecturer.interests;
            }
            function onCancelLecturer() {
                document.getElementById("lecturerPerson").style.visibility = "hidden";
            }
            function saveLecturerChange() {
                var lecturer = {
                    id: document.getElementById("lecturerId").textContent,
                    name:  document.getElementById("lecturerName").value,
                    login:  document.getElementById("lecturerLogin").value,
                    password: document.getElementById("lecturerPassword").value,
                    info:  document.getElementById("lecturerInfo").value,
                    degree:  document.getElementById("lecturerDegree").value,
                    works: document.getElementById("lecturerWorks").value,
                    interests:  document.getElementById("lecturerInterests").value,
                    cathedra:document.getElementById("selectCathedraOnEdit").options[document.getElementById("selectCathedraOnEdit").selectedIndex].text,
                    cathedraId:document.getElementById("selectCathedraOnEdit").options[document.getElementById("selectCathedraOnEdit").selectedIndex].value
                }
                if(lecturer.name==""||lecturer.login==""||lecturer.password==""||lecturer.cathedraId==""){
                    alert("fill everything");
                    return;
                }
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',

                    url: "updateLecturer",
                    data: JSON.stringify(lecturer),
                    success: function (response) {
                        lecturerShown = 0;
                        lecturers()
                        document.getElementById("lecturerPerson").style.visibility = "hidden";
                    },
                    error: function (xhr, status, errorThrown) {
                        alert(status + " " + errorThrown.toString());
                    }
                });

            }
            function removeLecturer() {
                var lecturer = {
                    id: document.getElementById("lecturerId").textContent,
                    name:  document.getElementById("lecturerName").value,
                    login:  document.getElementById("lecturerLogin").value,
                    password: document.getElementById("lecturerPassword").value,
                    info:  document.getElementById("lecturerInfo").value,
                    degree:  document.getElementById("lecturerDegree").value,
                    works: document.getElementById("lecturerWorks").value,
                    interests:  document.getElementById("lecturerInterests").value,
                }
                $.ajax({
                    type: "DELETE",
                    contentType: 'application/json; charset=utf-8',
                    url: "deleteLecturer",
                    data: JSON.stringify(lecturer),
                    success: function (response) {
                        lecturerShown = 0;
                        lecturers();
                        document.getElementById("lecturerPerson").style.visibility = "hidden";
                    },
                    error: function (xhr, status, errorThrown) {
                        alert(status + " " + errorThrown.toString());
                    }
                });
            }
            function createLecturer() {
                var lecturer = {
                    id:0,
                    name:document.getElementById("lecturerNameToCreate").value,
                    login:document.getElementById("lecturerLoginToCreate").value,
                    password:document.getElementById("lecturerPasswordToCreate").value,
                    cathedraId:document.getElementById("selectCathedra").options[document.getElementById("selectCathedra").selectedIndex].value
                };
                if(lecturer.name==""||lecturer.login==""||lecturer.password==""||lecturer.cathedraId==""){
                    alert("fill everything");
                    return;
                }
                $.ajax({
                    type: "post",
                    url: "createLecturer",
                    dataType: "json",
                    contentType: 'application/json; charset=utf-8',
                    data:JSON.stringify(lecturer),
                    complete: [function (response) {
                        lecturerShown=0;
                        lecturers();
                        document.getElementById("lecturerNameToCreate").value='';
                        document.getElementById("lecturerLoginToCreate").value='';
                        document.getElementById("lecturerPasswordToCreate").value='';
                    }]

                });

            }
            //student
            function onCancelStudent() {
                document.getElementById("studentPersone").style.visibility = "hidden";
            }
            function saveStudentChange() {
                var student = {
                    id: document.getElementById("studentId").textContent,
                    login: document.getElementById("studentLogin").value,
                    password: document.getElementById("studentPassword").value,
                    name: document.getElementById("studentName").value,
                    group:document.getElementById("selectGroupOnEdit").options[document.getElementById("selectGroupOnEdit").selectedIndex].text,
                    groupId:document.getElementById("selectGroupOnEdit").options[document.getElementById("selectGroupOnEdit").selectedIndex].value
                };
                if(student.login==""||student.password==""||student.name==""||student.groupId==""){
                    alert("fill evetyrhing");
                    return;
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
            function onEdit(pos, mass) {
                var student = {
                    id: mass[pos].id,
                    login: mass[pos].login,
                    password: mass[pos].password,
                    name: mass[pos].name,
                    group:mass[pos].group,
                    groupId:mass[pos].groupId
                }
                document.getElementById("studentPersone").style.visibility = "visible";
                document.getElementById("studentId").textContent = student.id;
                document.getElementById("studentName").value = student.name;
                document.getElementById("studentLogin").value = student.login;
                document.getElementById("studentPassword").value = student.password;
                $("#selectGroupOnEdit").value = (student.group);
            }
            function test() {
                if (shown == 0) {
                    shown = 1;

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
                                        '<td>' +obj[i].group+'</td>' +
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
                }
            }
            function createStudent() {
                var student = {
                    id:0,
                    name:document.getElementById("studentNameToCreate").value,
                    login:document.getElementById("studentLoginToCreate").value,
                    password:document.getElementById("studentPasswordToCreate").value,
                    groupId:document.getElementById("selectGroup").options[document.getElementById("selectGroup").selectedIndex].value
                };
                if(student.login==""||student.password==""||student.name==""||student.groupId==""){
                    alert("fill evetyrhing");
                    return;
                }
                $.ajax({
                    type: "post",
                    url: "createStudent",
                    dataType: "json",
                    contentType: 'application/json; charset=utf-8',
                    data:JSON.stringify(student),
                    complete: [function (response) {
                        shown=0;
                        test();
                        document.getElementById("studentNameToCreate").value='';
                        document.getElementById("studentLoginToCreate").value='';
                        document.getElementById("studentPasswordToCreate").value='';
                    }]
                });
            }
            //subject
            function onCancelSubject() {
                document.getElementById("subjectBody").style.visibility = "hidden";
            }
            function onSubjectEdit(pos, mass) {
                var subject = {
                    id: mass[pos].id,
                    shortName: mass[pos].shortName,
                    fullName: mass[pos].fullName,
                    info: mass[pos].info,
                    lecturerId:mass[pos].group,
                }
                document.getElementById("subjectBody").style.visibility = "visible";
                document.getElementById("subjectId").textContent = subject.id;
                document.getElementById("subjectShortName").value = subject.shortName;
                document.getElementById("subjectFullName").value = subject.fullName;
                document.getElementById("subjectInfo").value = subject.info;
                // $("#selectGroupOnEdit").value = (student.group);
            }
            function subjects() {
                if (subjectsShown == 0) {
                    subjectsShown = 1;


                    $.ajax({
                        type: "GET",
                        url: 'subjects',
                        dataType: "json",
                        complete: [
                            function (response) {
                                document.getElementById("subjects").style.visibility = "visible";
                                $("#subjects").find("tr:not(:first)").remove();
                                var trHTML = '';
                                var obj = $.parseJSON(response.responseText);
                                for (var i = 0; i < obj.length; i++) {
                                    trHTML += '<tr><td>' + obj[i].id + '</td>' +
                                        '<td>' + obj[i].shortName + '</td>' +
                                        '<td>' + obj[i].fullName + '</td>' +
                                        '<td>' + obj[i].info + '</td>'+
                                        '<td>' + obj[i].lecturerId + '</td>' +
                                        '<td>' + obj[i].lecturerName + '</td>' +
                                        '<td>' + obj[i].amount + '</td>' +
                                        '<td><button id="' + i+"f" + '"class="myclassE">show students</button> </td>'+
                                        '<td><button id="' + i + '"class="myclassL">edit</button> </td></tr>';
                                    var a = obj;
                                    var b=obj[i];
                                    $(document).off().on('click', 'button.myclassL', function (event) {
                                        listLecturers();
                                        onSubjectEdit(this.id, a);
                                    });
                                    $(document).on('click', 'button.myclassE', function (event) {
                                        // listLecturers();
                                        showStudentsForSubject(b);
                                    });
                                }
                                $("#subjects tbody").append(trHTML);
                            }
                        ]
                    });
                } else {
                    subjectsShown = 0;
                }
            }
            function showStudentsForSubject(subject) {
                $.ajax({
                    type: "GET",
                    url: 'studentsForSubject',
                    dataType: "json",
                    contentType: 'application/json; charset=utf-8',
                    data:{id:subject.id},
                    complete: [
                        function (response) {
                            //alert(response.responseText);
                            document.getElementById("subjects").style.visibility = "visible";
                            $("#studentsOnSubject").find("tr:not(:first)").remove();
                            var trHTML = '';
                            var obj = $.parseJSON(response.responseText);
                            var k;
                            for(k=0;k<obj.length;k++){
                                trHTML+= '<tr><td>' + obj[k].id + '</td>' +
                                    '<td>' + obj[k].name + '</td>'+
                                    '<td><input type="checkbox" value="'+obj[k].id+'" class="checkUser"></td></tr>'
                            }
                            trHTML+='<tr><td><button class="myclassRR">Remove selected</button> </td><td><button class="myclassAD">Add students</button></td><td><button class="myclassCN">Cancel</button></td></tr>'
                            $("#studentsOnSubject tbody").append(trHTML);
                            $(document).on('click', '.myclassRR', function (event) {
                                selectedStudents(subject);
                            });
                            $(document).on('click', '.myclassAD', function (event) {
                                addStudentForSubject(subject);
                            });
                            $(document).on('click', '.myclassCN', function (event) {
                                document.getElementById("studentsOnSubject").style.visibility="hidden";
                                document.getElementById("studentsToAdd").style.visibility="hidden";

                            });
                        }]



                });
                document.getElementById("studentsOnSubject").style.visibility="visible";
            }
            function addStudentForSubject(subject) {
                document.getElementById("studentsToAdd").style.visibility="visible";
                $.ajax({
                    type: "GET",
                    url: 'students',
                    dataType: "json",
                    complete: [
                        function (response) {
                            document.getElementById("personDataTable").style.visibility = "visible";
                            $("#studentsToAdd").find("tr:not(:first)").remove();
                            var trHTML = '';
                            var obj = $.parseJSON(response.responseText);
                            for (var i = 0; i < obj.length; i++) {
                                trHTML += '<tr>' +
                                    '<td>' + obj[i].id + '</td>' +
                                    '<td>' + obj[i].name + '</td>' +
                                    '<td>' +obj[i].group+'</td>' +
                                    '<td><input type="checkbox" value="'+obj[i].id+'" class="checkUserToAdd"></td></tr>'
                                '</tr>';

                            }
                            trHTML+='<tr><td><button class="myclassADS">Add selected</button> </td><td><button class="myclassCLRSLC">Clear selection</button></td><td><button class="myclassCLS">Cancel</button></td></tr>'
                            $(document).on('click', '.myclassADS', function (event) {
                                addSelectedForSubject(subject);
                            });
                            $(document).on('click', '.myclassCLRSLC', function (event) {
                                clearSelectionForAdd();
                            });
                            $(document).on('click', '.myclassCLS', function (event) {
                                document.getElementById("studentsToAdd").style.visibility="hidden";
                            });
                            $("#studentsToAdd tbody").append(trHTML);
                        }
                    ]
                });
            }
            function clearSelectionForAdd() {
                $('[class=checkUserToAdd]:checked').each(function() {
                    $(this).prop('checked', false);
                })

            }
            function addSelectedForSubject(subject) {
                var students = [];
                students.push({id:subject.id, name: "e",
                    login: "e",
                    password: subject.lecturerId,});
                var i = 0;
                $('[class=checkUserToAdd]:checked').each(function() {
                    var a = ($(this).val());
                    var student= {
                        id: a,
                        name: "e",
                        login: "e",
                        password: "e"
                    }
                    students.push(student);
                    i++;
                });
                if(subject.size<2){
                    alert("choose some students")
                    return
                }
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',
                    url: "addStudentForSubject",
                    data:(JSON.stringify(students)),
                    success: function (response) {
                        subjectsShown=0;
                        subjects();
                        showStudentsForSubject(subject);
                        // document.getElementById("studentsOnSubject").style.visibility="hidden";
                    },error: function (xhr, status, errorThrown) {
                        alert(status + " " + errorThrown.toString());
                    }

                });
            }
            function selectedStudents(subject) {
                document.getElementById("studentsOnSubject").style.visibility="visible";
                var students = [];
                students.push({id:subject.id, name: "e",
                    login: "e",
                    password: "e"});
                var i = 0;
                $('[class=checkUser]:checked').each(function() {
                    var a = ($(this).val());
                    var student= {
                        id: a,
                        name: "e",
                        login: "e",
                        password: "e"
                    }
                    students.push(student);
                    i++;
                });
                $.ajax({
                    type: "DELETE",
                    contentType: 'application/json; charset=utf-8',
                    url: "deleteStudentsFromSubject",
                    data:(JSON.stringify(students)),
                    success: function (response) {
                        subjectsShown=0;
                        subjects();
                        showStudentsForSubject(subject);
                        //document.getElementById("studentsOnSubject").style.visibility="hidden";
                    },error: function (xhr, status, errorThrown) {
                        alert(status + " " + errorThrown.toString());
                    }

                });
            }
            function removeSubject() {
                var subject = {
                    id:document.getElementById("subjectId").textContent ,
                    shortName:document.getElementById("subjectShortName").value,
                    fullName:document.getElementById("subjectFullName").value,
                    info:document.getElementById("subjectInfo").value,
                    lecturerId:document.getElementById("selectSubjectOnEdit").options[document.getElementById("selectSubjectOnEdit").selectedIndex].value
                };
                $.ajax({
                    type: "DELETE",
                    contentType: 'application/json; charset=utf-8',
                    url: "deleteSubject",
                    data: JSON.stringify(subject),
                    success: function (response) {
                        subjectsShown = 0;
                        subjects();
                        document.getElementById("subjectBody").style.visibility = "hidden";
                    },
                    error: function (xhr, status, errorThrown) {
                        alert(status + " " + errorThrown.toString());
                    }
                });
            }
            function saveSubjectChange() {
                var subject = {
                    id:document.getElementById("subjectId").textContent ,
                    shortName:document.getElementById("subjectShortName").value,
                    fullName:document.getElementById("subjectFullName").value,
                    info:document.getElementById("subjectInfo").value,
                    lecturerId:document.getElementById("selectSubjectOnEdit").options[document.getElementById("selectSubjectOnEdit").selectedIndex].value
                };
                if(subject.shortName==""||subject.fullName==""||subject.lecturerId==""){
                    alert("fill everitying");
                    return;
                }
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',
                    url: "updateSubject",
                    data: JSON.stringify(subject),
                    success: function (response) {
                        subjectsShown = 0;
                        subjects();
                        document.getElementById("subjectBody").style.visibility = "hidden";
                    },
                    error: function (xhr, status, errorThrown) {
                        alert(status + " " + errorThrown.toString());
                    }
                });

            }
            function createSubject() {
                var subject = {
                    id:0,
                    shortName:document.getElementById("subjectSortNameCreate").value,
                    fullName:document.getElementById("subjectNameCreate").value,
                    info:document.getElementById("subjectInfoCreate").value,
                    lecturerId:document.getElementById("lecturersForSubjectCreate").options[document.getElementById("lecturersForSubjectCreate").selectedIndex].value
                };
                if(subject.shortName==""||subject.fullName==""||subject.lecturerId==""){
                    alert("fill everitying");
                    return;
                }
                $.ajax({
                    type: "post",
                    contentType: 'application/json; charset=utf-8',
                    url: "createSubject",
                    data: JSON.stringify(subject),
                    success: function (response) {
                        document.getElementById("subjectSortNameCreate").value='';
                        document.getElementById("subjectNameCreate").value='';
                        document.getElementById("subjectInfoCreate").value='';
                    },
                    error: function (xhr, status, errorThrown) {
                        alert(status + " " + errorThrown.toString());
                    }
                });

            }
            //object
            function fillSelectOfObject(object) {
                $("#selectParent").find('option').remove();
                var whatToFill;
                if(object.type=="student"){
                    listStudent();
                    whatToFill="no";
                }else if(object.type=="lecturer"){
                    listCathedras();
                    whatToFill="no";
                }else if(object.type=="cathedra"){
                    whatToFill="getFaculty";
                }else if(object.type=="group"){
                    whatToFill='getFaculty';
                }else if(object.type=="faculty"){
                    whatToFill='getUniversity';
                }else {
                    whatToFill="no";
                }
                if(whatToFill!="no"){
                    $.ajax({
                        type: "GET",
                        url: whatToFill,
                        dataType: "json",
                        complete: [function (response) {
                            var obj = $.parseJSON(response.responseText);
                            var ddl3 = $("#selectParent");
                            ddl3.find('option').remove()
                            for (k = 0; k < obj.length; k++) {
                                ddl3.append("<option value='" + obj[k].id + "'>" + obj[k].description + "</option>");
                            }
                        }
                        ]
                    });

                }
            }
            function objects() {
                if (objectShown == 0) {
                    objectShown = 1;

                    $.ajax({
                        type: "GET",
                        url: 'objects',
                        dataType: "json",
                        complete: [
                            function (response) {
                                $("#objects").find("tr:not(:first)").remove();
                                var trHTML = '';
                                var obj = $.parseJSON(response.responseText);
                                for (var i = 0; i < obj.length; i++) {
                                    trHTML += '<tr>' +
                                        '<td>' + obj[i].id + '</td>' +
                                        '<td>' + obj[i].type + '</td>' +
                                        '<td>' + obj[i].description + '</td>' +
                                        '<td>' + obj[i].parentId + '</td>' +
                                        '<td><button id="' + i + '"class="myclassObj">edit</button> </td>' +
                                        '</tr>';
                                    var a = obj;
                                    $(document).off().on('click', 'button.myclassObj', function (event) {
                                        onObjectEdit(a,this.id);
                                    });
                                }
                                $("#objects tbody").append(trHTML);
                            }
                        ]
                    });
                } else {
                    objectShown= 0;
                    //document.getElementById("objects").style.visibility = "hidden";
                    // document.getElementById("studentPersone").style.visibility = "hidden";
                }
            }
            function onObjectEdit(mass,pos) {
                var a = mass[pos];
                document.getElementById("objectBody").style.visibility = "visible";
                document.getElementById("objectId").textContent = a.id;
                document.getElementById("objectType").textContent = a.type;
                document.getElementById("objectDescription").value = a.description;
                fillSelectOfObject(a);
            }
            function selectParent() {
                var toSelect=document.getElementById("objectTypeCreate").options[document.getElementById("objectTypeCreate").selectedIndex].value
                if(toSelect!="no"){
                    $.ajax({
                        type: "GET",
                        url: toSelect,
                        dataType: "json",
                        complete: [function (response) {
                            obj = $.parseJSON(response.responseText);
                            var ddl3 = $("#parentTypeCreate");
                            ddl3.find('option').remove()
                            for (k = 0; k < obj.length; k++) {
                                ddl3.append("<option value='" + obj[k].id + "'>" + obj[k].description + "</option>");
                            }
                        }
                        ]
                    });}else{
                    var ddl3 = $("#parentTypeCreate");
                    ddl3.find('option').remove();
                    ddl3.append("<option value='0'>" +" " + "</option>");

                }
                $('#parentTypeCreate').removeAttr('disabled');
                $('#createObject').removeAttr('disabled');
                $('#objectTypeCreate').attr('disabled', 'disabled');
            }
            function cancelSelectParent() {
                $('#objectTypeCreate').removeAttr('disabled');
                $('#parentTypeCreate').attr('disabled', 'disabled');
                $('#createObject').attr('disabled', 'disabled');
            }
            function createObject() {
                var  object={
                    id:0,
                    description:document.getElementById("objectDescriptionCreate").value,
                    type:document.getElementById("objectTypeCreate").options[document.getElementById("objectTypeCreate").selectedIndex].text,
                    parentId:document.getElementById("parentTypeCreate").options[document.getElementById("parentTypeCreate").selectedIndex].value};
                if(object.type=""||object.description==""||(object.parentId=""&&object.type!="university")){
                    alert("fill everyting");
                    return;
                }
                $.ajax({
                    type: "post",
                    url: "addObject",
                    dataType: "json",
                    contentType: 'application/json; charset=utf-8',
                    data:JSON.stringify(object),
                    complete: [function (response) {
                        objectShown = 0;
                        objects();
                        document.getElementById("objectDescriptionCreate").value='';
                        cancelSelectParent();
                    }]

                });
            }
            function saveObjectChange() {
                var  object={
                    id:document.getElementById("objectId").textContent,
                    description:document.getElementById("objectDescription").value,
                    type:document.getElementById("objectType").textContent,
                    parentId:document.getElementById("selectParent").options[document.getElementById("selectParent").selectedIndex].value};
                if(object.type=""||object.description==""||(object.parentId=""&&object.type!="university")){
                    alert("fill everyting");
                    return;
                }
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',
                    url: "updateObject",
                    data: JSON.stringify(object),
                    success: function (response) {
                        objectShown = 0;
                        objects();
                        document.getElementById("objectBody").style.visibility = "hidden";
                    },
                    error: function (xhr, status, errorThrown) {
                        alert(status + " " + errorThrown.toString());
                    }
                });
            }
            function removeObject(){
                var  object={
                    id:document.getElementById("objectId").textContent,
                    description:document.getElementById("objectDescription").value,
                    type:document.getElementById("objectType").textContent,
                    parentId:document.getElementById("selectParent").options[document.getElementById("selectParent").selectedIndex].value};
                $.ajax({
                    type: "DELETE",
                    contentType: 'application/json; charset=utf-8',
                    url: "deleteObject",
                    data: JSON.stringify(object),
                    success: function (response) {
                        objectShown = 0;
                        objects();
                        document.getElementById("objectBody").style.visibility = "hidden";
                    },
                    error: function (xhr, status, errorThrown) {
                        alert(status + " " + errorThrown.toString());
                    }
                });
            }
            function onCancelObject() {
                document.getElementById("objectBody").style.visibility = "hidden";
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
        <style>
            /*.order-table {*/
                /*margin-bottom: 20px;*/
                /*border-collapse:collapse;*/
                /*font-family: 'Lato', Calibri, Arial, sans-serif;*/
                /*border: none;*/
                /*border-radius: 3px;*/
                /*-webkit-border-radius: 3px;*/
                /*-moz-border-radius: 3px;*/
            /*}*/
            /*.order-table th, .order-table td {*/
                /*box-shadow: inset 0 -1px rgba(0,0,0,0.25),*/
                /*inset 0 1px rgba(0,0,0,0.25);*/
            /*}*/
            /*.order-table th {*/
                /*font-weight: normal;*/
                /*-webkit-font-smoothing: antialiased;*/
                /*padding: 1em;*/
                /*color: rgba(0,0,0,0.45);*/
                /*text-shadow: 0 0 1px rgba(0,0,0,0.1);*/
                /*font-size: 1.5em;*/
            /*}*/
            /*.order-table td {*/
                /*color: #f7f7f7;*/
                /*padding: 0.7em 1em 0.7em 1.15em;*/
                /*text-shadow: 0 0 1px rgba(255,255,255,0.1);*/
                /*font-size: 1.4em;*/
            /*}*/
            /*.order-table tr {*/
                /*-webkit-transition: background 0.3s, box-shadow 0.3s;*/
                /*-moz-transition: background 0.3s, box-shadow 0.3s;*/
                /*transition: background 0.3s, box-shadow 0.3s;*/
            /*}*/
            /*.order-table {*/
                /*background:#eee;*/
            /*}*/
            /*.order-table-1 tr:hover {*/
                /*background: rgba(0,0,0,0.19);*/
            /*}*/
            /*.order-table-2 tr:hover {*/
                /*background: rgba(0,0,0,0.1);*/
            /*}*/
            /*.flat-table-2 {*/
                /*background: #f06060;*/
            /*}*/
            /*.flat-table-3 {*/
                /*background: #52be7f;*/
            /*}*/
            /*.flat-table-3 tr:hover {*/
                /*background: rgba(0,0,0,0.1);*/
            /*}*/
        </style>
        <title>adminPage</title>
    </head>
    <body>
        <div id="result"></div>
        <h3>Search</h3>
        <input type="search" class="light-table-filter" data-table="order-table" placeholder="Filter">
        <button class="accordion" onclick="test()">Show all students</button>
        <div class="panel">
        <%--<button name="objectType" id="ot" class="aclass" onclick="test()">All students</button>--%>
            <table class="order-table table" id="personDataTable" style="visibility: hidden" width="100%" class="flat-table-1">
                <thead>
                <tr>
                    <th align="left" >ID</th>
                    <th align="left" >Name</th>
                    <th align="left" >Login</th>
                    <th align="left" >Password</th>
                    <th align="left" >Group</th>
                    <th align="left" >Edit</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <table id="studentPersone" style="visibility: hidden" >
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
                    <td><select id="selectGroupOnEdit" selected></select></td>
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
            <table class="order-table table"  id="lecturersTable" style="visibility: hidden" width="100%" class="flat-table-1">
                <thead>
                <tr>
                    <th align="left"  >ID</th>
                    <th align="left"  >Name</th>
                    <th align="left"  >Login</th>
                    <th align="left"  >Password</th>
                    <th align="left"  >Info</th>
                    <th align="left"  >Degree</th>
                    <th align="left"  >Works</th>
                    <th align="left"  >Interests</th>
                    <th align="left" >Cathedra</th>
                    <th align="left" ></th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <table  id="lecturerPerson" style="visibility: hidden" width="100%" >
                <tr>
                    <td>id</td>
                    <td>Name</td>
                    <td>Login</td>
                    <td>Password</td>
                    <td>Info</td>
                    <td>Degree</td>
                    <td>Works</td>
                    <td>Interests</td>
                    <td>Cathedra</td>
                </tr>
                <tr>
                    <td><label id="lecturerId">id</label></td>
                    <td><input id="lecturerName" style='width:100%'/></td>
                    <td><input id="lecturerLogin"style='width:100%'/></td>
                    <td><input id="lecturerPassword"/></td>
                    <td><input id="lecturerInfo"/></td>
                    <td><input id="lecturerDegree"/></td>
                    <td><input id="lecturerWorks"/></td>
                    <td><input id="lecturerInterests"/></td>
                    <td><select id="selectCathedraOnEdit"></select></td>
                </tr>
                <tr>
                    <td>
                        <button id="saveLecturerChage" onclick="saveLecturerChange()">Save</button>
                    </td>
                    <td>
                        <button id="removeLecturer" onclick="removeLecturer()">Delete</button>
                    </td>
                    <td>
                        <button id="cancelLecturer" onclick="onCancelLecturer()">Cancel</button>
                    </td>
                </tr>
            </table>
        </div>
        <button class="accordion" onclick="subjects()">Show all subjects</button>
        <div class="panel">
            <table class="order-table table" id="subjects"width="100%" class="flat-table-1">
                <thead>
                <tr>
                    <th align="left" >Id</th>
                    <th align="left" >Short name</th>
                    <th align="left" >Full name</th>
                    <th align="left" >Info</th>
                    <th align="left" >Lecturer id</th>
                    <th align="left" >Lecturer name</th>
                    <th align="left" >Students amount</th>
                    <th align="left" ></th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <table id="subjectBody" style="visibility: hidden" >
                <tr>
                    <td>id</td>
                    <td>Short name</td>
                    <td>Full name</td>
                    <td>Info</td>
                    <td>Lecturer</td>
                    <td></td>
                </tr>
                <tr>
                    <td><label id="subjectId">id</label></td>
                    <td><input id="subjectShortName"/></td>
                    <td><input id="subjectFullName"/></td>
                    <td><input id="subjectInfo"/></td>
                    <td><select id="selectSubjectOnEdit" selected></select></td>
                </tr>
                <tr>
                    <td>
                        <button id="saveSubjectChage" onclick="saveSubjectChange()">Save</button>
                    </td>
                    <td>
                        <button id="removeSubject" onclick="removeSubject()">Delete</button>
                    </td>
                    <td>
                        <button id="cancelSubject" onclick="onCancelSubject()">Cancel</button>
                    </td>
                </tr>
            </table >
            <table width="100%">
                <tr>
                    <td>
                        <table class="order-table table" id="studentsOnSubject" style="visibility: hidden" width="50%" >
                            <thead>
                            <tr>
                                <th align="left" >ID</th>
                                <th align="left" >Name</th>
                                <th align="left" ></th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </td>
                    <td>
                        <table class="order-table table" id="studentsToAdd" style="visibility: hidden" width="100%" >
                            <thead>
                            <tr>
                                <th align="left" >ID</th>
                                <th align="left" >Name</th>
                                <th align="left" >Group</th>
                                <th align="left" ></th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
        <button class="accordion" onclick="objects()">Show all ...</button>
        <div class ="panel">
            <table class="order-table table" id="objects"width="100%" class="flat-table-1">
                <thead>
                <tr>
                    <th align="left" >Id</th>
                    <th align="left" >Type</th>
                    <th align="left" >Description</th>
                    <th align="left" >ParentID</th>
                    <th align="left" ></th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <table id="objectBody" style="visibility: hidden">
                <tr>
                    <td>id</td>
                    <td>Type</td>
                    <td>Description</td>
                    <td>Parent id</td>
                    <td>ParentId</td>
                </tr>
                <tr>
                    <td><label id="objectId">id</label></td>
                    <td><label id="objectType">type</label></td>
                    <td><input id="objectDescription"/></td>
                    <td><select id="selectParent" selected></select></td>
                </tr>
                <tr>
                    <td>
                        <button id="saveObjectChage" onclick="saveObjectChange()">Save</button>
                    </td>
                    <td>
                        <button id="removeObject" onclick="removeObject()">Delete</button>
                    </td>
                    <td>
                        <button id="cancelObject" onclick="onCancelObject()">Cancel</button>
                    </td>
                </tr>
            </table>
        </div>
        <button class="accordion" onclick="listCathedras()">Register lecturer</button>
        <div class="panel">
            <table>
                <tr>
                    <td>Name</td>
                    <td><input id="lecturerNameToCreate"></td>
                </tr>
                <tr>
                    <td>Login</td>
                    <td><input id="lecturerLoginToCreate"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input id="lecturerPasswordToCreate"></td>
                </tr>
                <tr>
                    <td>Cathedra</td>
                    <td> <select id="selectCathedra"></select></td>
                </tr>
                <tr>
                    <td><button onclick="createLecturer()">Create</button></td></tr>
            </table>
        </div>
        <button class="accordion" onclick="listStudent()">Register student</button>
        <div class="panel">
            <table>
                <tr>
                    <td>Name</td>
                    <td><input id="studentNameToCreate"></td>
                </tr>
                <tr>
                    <td>Login</td>
                    <td><input id="studentLoginToCreate"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input id="studentPasswordToCreate"></td>
                </tr>
                <tr>
                    <td>Group</td>
                    <td> <select id="selectGroup"></select></td>
                </tr>
                <tr>
                    <td><button onclick="createStudent()">Create</button></td>
                </tr>
            </table>
        </div>
        <button class="accordion" onclick="listLecturers()">Add subject</button>
        <div class="panel">
            <table>
                <tr><td>Full name</td><td>  <input type="text" id="subjectNameCreate"></td></tr>
                <tr><td>Short name</td><td>    <input type="text" id="subjectSortNameCreate"></td></tr>
                <tr><td>Info</td><td>  <input type="text" id="subjectInfoCreate"></td></tr>
                <tr><td>Lecturer</td><td>  <select id="lecturersForSubjectCreate"></select></td></tr>
                <tr><td> <button onclick="createSubject()">Create</button></td></tr>
            </table>
        </div>
        <button class="accordion" >Add ...</button>
         <div class="panel">
             <table>
                 <tr><td>Description</td><td> <input type="text" id="objectDescriptionCreate"></td></tr>
                 <tr><td>Type</td><td>  <select id="objectTypeCreate">
                    <option value="getFaculty">group</option>
                    <option value="getFaculty">cathedra</option>
                    <option value="getUniversity">faculty</option>
                    <option value="no">university</option>
                 </select></td></tr>
                 <tr><td><button onclick="selectParent()">Choose parent</button></td><td><button onclick="cancelSelectParent()">Back</button></td></tr>
                 <tr><td>Parent</td><td>  <select id="parentTypeCreate" disabled/></td></tr>
                 <tr><td> <button onclick="createObject()" id="createObject" disabled>Create</button></td></tr>
             </table>
         </div>
    </body>
</html>
