<%-- 
    Document   : addtask
    Created on : Apr 12, 2013, 10:48:35 PM
    Author     : Arief
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" type="text/css" href="dashboard.css" media="screen">
    <link rel="stylesheet" href="loginpage.css" type="text/css" media="screen">
    <script src="addtask.js"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>S.Y.N. Add Task</title>
    </head>
    <jsp:include page="header.jsp"/>
    <body>
        &nbsp
        <br>
        &nbsp
        <br>
        &nbsp
        <br>
        &nbsp
        <br>
        <h1>Add Task</h1>
        <div id="Front_Form">
            <h2>Add Task Form :</h2>
            <form action="addtaskprocess.jsp" method="POST" id="AddTaskForm"  onsubmit="return checkValidation();">
                <table width="400px">
                    <tr>
                        <td width="140px">
                            Nama Task
                        </td>
                        <td width="20px">
                            :
                        </td>
                        <td width="190px">
                            <input type="hidden" name="addtask_category" id="addtask_category" value="<%= request.getParameter("namaKategori") %>">
                            <input type="text" name="addtask_namaTask" id="addtask_namaTask" onkeyup="checkValidation();" required>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="namaTaskWarning"></div>
                        </td>
                    </tr>
                    <tr>
                        <td width="140px">
                            Assignee
                        </td>
                        <td width="20px">
                            :
                        </td>
                        <td width="190px">
                            <input type="text" name="addtask_assignee" id="addtask_assignee" onkeyup="checkValidation();" required>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="assigneeWarning"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="asigneeSugestion"></div>
                        </td>
                    </tr>
                    <tr>
                        <td width="140px">
                            Tag
                        </td>
                        <td width="20px">
                            :
                        </td>
                        <td width="190px">
                            <input type="text" name="addtask_tag" id="addtask_tag" onkeyup="checkValidation();">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="tagWarning"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="tagSugestion"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Tanggal Deadline
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <input type="date" name="addtask_deadline" id="addtask_deadline" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Attachment
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <input type="file" name="addtask_attachment1" id="addtask_attachment1" onchange="checkvalidation();"  required>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="avatarWarning"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Attachment
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <input type="file" name="addtask_attachment2" id="addtask_attachment2" onchange="checkvalidation();">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Attachment
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <input type="file" name="addtask_attachment3" id="addtask_attachment3" onchange="checkvalidation();">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Attachment
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <input type="file" name="addtask_attachment4" id="addtask_attachment4" onchange="checkvalidation();">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Attachment
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <input type="file" name="addtask_attachment5" id="addtask_attachment5" onchange="checkvalidation();">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Buat">
                        </td>
                        <td>
                        </td>
                        <td>
                            <input type="reset" value="Batal">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
