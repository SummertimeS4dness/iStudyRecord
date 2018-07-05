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
            var subjectsShown=0;
            function test() {
                if (shown == 0) {
                    shown = 1;
//                    var stOb=null;
//                    $.ajax({
//                        type: "GET",
//                        url: 'studentObjects',
//                        dataType: "json",
//                        complete: [
//                            function (response) {
//                            stOb=$.parseJSON(response.responseText);
//                            }]});

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
                                    $(document).off().on('click', 'button.myclassL', function (event) {
                                        listLecturers();
                                        onSubjectEdit(this.id, a);
                                    });
                                    $(document).on('click', 'button.myclassE', function (event) {
                                       // listLecturers();
                                        showStudentsForSubject(this.id, a);
                                    });
                                }
                                $("#subjects tbody").append(trHTML);
                            }
                        ]
                    });
                } else {
                    subjectsShown = 0;
//                    document.getElementById("personDataTable").style.visibility = "hidden";
                  //  document.getElementById("lecturerPerson").style.visibility = "hidden";
//                    document.getElementById("studentId").textContent = '';
//                    document.getElementById("studentName").value = '';
//                    document.getElementById("studentLogin").value = '';
//                    document.getElementById("studentPassword").value = '';
                }
            }
            function showStudentsForSubject(pos, mass) {
                var massPos=Number(pos.substring(0,pos.length-1));
                var subject={
                id:mass[massPos].id,
                shortName:mass[massPos].shortName,
                fullName:mass[massPos].fullName,
                lecturerId:mass[massPos].lecturerId
                };
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
                            $(document).on('click', 'button.myclassRR', function (event) {
                               selectedStudents(subject);
                            });
                            $(document).on('click', 'button.myclassAD', function (event) {
                                addStudentForSubject(subject);
                            });
                            $(document).on('click', 'button.myclassCN', function (event) {
                                document.getElementById("studentsOnSubject").style.visibility="hidden";
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
                            $(document).on('click', 'button.myclassADR', function (event) {
                                //addSelected(subject)
                            });
                            $(document).on('click', 'button.myclassCLRSLC', function (event) {
                                clearSelectionForAdd();
                            });
                            $(document).on('click', 'button.myclassCLS', function (event) {
                                document.getElementById("studentsToAdd").style.visibility="hidden";
                            });
                            $("#studentsToAdd tbody").append(trHTML);
                        }
                    ]
                });
            }
            function clearSelectionForAdd() {
                alert("ok")
                $('[class=checkUserToAdd]:checked').each(function() {
                    $(this).prop('checked', false);
                    })

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
                        document.getElementById("studentsOnSubject").style.visibility="hidden";
                    },error: function (xhr, status, errorThrown) {
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
            function onCancelStudent() {
                document.getElementById("studentPersone").style.visibility = "hidden";
            }
            function onCancelSubject() {
                document.getElementById("subjectBody").style.visibility = "hidden";
            }
            function onCancelLecturer() {
                document.getElementById("lecturerPerson").style.visibility = "hidden";
            }
            function saveStudentChange() {
                var student = {
                    id: document.getElementById("studentId").textContent,
                    login: document.getElementById("studentLogin").value,
                    password: document.getElementById("studentPassword").value,
                    name: document.getElementById("studentName").value,
                    group:document.getElementById("selectGroupOnEdit").options[document.getElementById("selectGroupOnEdit").selectedIndex].text,
                groupId:document.getElementById("selectGroupOnEdit").options[document.getElementById("selectGroupOnEdit").selectedIndex].value
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
            function saveSubjectChange() {
                var subject = {
                id:document.getElementById("subjectId").textContent ,
                shortName:document.getElementById("subjectShortName").value,
                fullName:document.getElementById("subjectFullName").value,
                info:document.getElementById("subjectInfo").value,
                lecturerId:document.getElementById("selectSubjectOnEdit").options[document.getElementById("selectSubjectOnEdit").selectedIndex].value
                };
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
                        ddl.find('option').remove();
                      //  ddl.append("<option style=\"display:none\" selected=\"selected\"/>");
                        ddl2.find('option').remove();
                      //  ddl2.append("<option style=\"display:none\" selected=\"selected\"/>");
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
                        ddl.find('option').remove();
                        var ddl2 = $("#selectCathedraOnEdit");
                        ddl2.find('option').remove()
                        for (k = 0; k < cathedras.length; k++) {
                            ddl.append("<option value='" + cathedras[k].id + "'>" + cathedras[k].description + "</option>");
                            ddl2.append("<option value='" + cathedras[k].id + "'>" + cathedras[k].description + "</option>");
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
            function createSubject() {
                var subject = {
                    id:0,
                    shortName:document.getElementById("subjectSortNameCreate").value,
                    fullName:document.getElementById("subjectNameCreate").value,
                    info:document.getElementById("subjectInfoCreate").value,
                    lecturerId:document.getElementById("lecturersForSubjectCreate").options[document.getElementById("lecturersForSubjectCreate").selectedIndex].value
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
                    <th>Cathedra</th>
                    <th></th>
                </tr>
            </table>
            <table id="lecturerPerson" style="visibility: hidden" width="100%">
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
                    <td><input id="lecturerName"/></td>
                    <td><input id="lecturerLogin"/></td>
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
            <table id="subjects" border="1" width="100%">
                <tr>
                    <th>Id</th>
                    <th>Short name</th>
                    <th>Full name</th>
                    <th>Info</th>
                    <th>Lecturer id</th>
                    <th>Lecturer name</th>
                    <th>Students amount</th>
                    <th></th>
                </tr>
            </table>
            <table id="subjectBody" style="visibility: hidden">
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
            </table>
            <table width="100%">
                <tr>
                    <td>
                        <table id="studentsOnSubject" style="visibility: hidden" border="1" width="50%">
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th></th>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table id="studentsToAdd" style="visibility: hidden" border="1" width="100%">
                            <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Group</th>
                            <th></th>
                            </tr>
                        </table>
                    </td>
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
                <br><input type="submit" value="Create">
            </form>
        </div>
        <button class="accordion" onclick="listLecturers()">Add subject</button>
        <div class="panel">
         <label>Full name</label>   <input type="text" id="subjectNameCreate"><br>
         <label>Short name</label>   <input type="text" id="subjectSortNameCreate"><br>
         <label>Info</label>   <input type="text" id="subjectInfoCreate"><br>
         <label>Lecturer</label>   <select id="lecturersForSubjectCreate"></select><br>
            <button onclick="createSubject()">Create</button>
        </div>


    </body>
</html>
