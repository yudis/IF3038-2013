<%-- 
    Document   : add_task
    Created on : Apr 6, 2013, 8:16:49 AM
    Author     : VAIO
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Class.*"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Task</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="javascript/calendar.js"></script>
        <link href="css/calendar.css" rel="stylesheet">
        <script type="text/javascript" src="javascript/add_task.js"></script>
    </head>
    <body>
        <%
        /*session management*/
        if(request.getSession().getAttribute("userlistapp")==null){
            response.sendRedirect("index.jsp");
        }
        %>
        <div id="main-body-general">
                <!--Header-->
                <div id="header">
                <jsp:include page="header.jsp" />
                <%
                    Function func = new Function();
                    String usernameToShow = request.getParameter("username");
                    HashMap<String, String> userShow = func.GetUser(usernameToShow);
                %>
                </div>
                <div><hr id="border"></div>
                <!--Body-->
                <div id="task-page-body">
                        <h1>Category: 
                        <%
                            String categoryid = request.getParameter("categoryid");
                            out.print(func.getCategoryName(categoryid));
                        %>
                        
        </h1>
                        <div id="add-task">
                                Task name:<br><br>
                                Attach file:<br><br><br><br><br><br>
                                Deadline,<br>
                                Date:<br>
                                Time:<br><br>
                                Asignee:<br><br>
                                Tag:
                        </div>
                        <div id="add-task-form">
                            <form enctype="multipart/form-data" method="post" action="inserttask?categoryid=<%=categoryid %>">
                                <!--Name-->
                                <div id="spacing">
                                <input type="text" id="taskname" onKeyUp="check_task_name()" name="textTaskName"/>
                                </div>
                                <!--Attachment
            "
            -->
                                <div id="spacing-attach">
                                <input id="attached1" onChange="check_attachment1()" type="file" name="attachment1"/><br />
                                <input id="attached2" onChange="check_attachment2()" type="file" name="attachment2"/><br />
                                <input id="attached3" onChange="check_attachment3()" type="file" name="attachment3"/><br />
                                <input id="attached4" onChange="check_attachment4()" type="file" name="attachment4"/><br />
                                <input id="attached5" onChange="check_attachment5()" type="file" name="attachment5"/>
                                </div>					
                                <!--Deadline-->
                                <div id="spacing-deadline">
                                <input type="text" class="calendarSelectDate" name="textDeadline"/><div id="calendarDiv"></div><br />
                                <input type="text" onKeyUp="check_time()" id="time" name="textTimeDeadline" placeholder="HH:MM"/>
                                </div>
                                <!--Assignee-->
                                <div id="spacing">
                                <input type="text" id="task-assignee" onKeyUp="addAssignee()" name="textAssignee" list="assignee-task" value="" placeholder="tag1,tag2,tag3">
                                <div id="shared-with"></div>
                                </div>
                                <!--Tag (multivalue)-->
                                <div id="spacing">
                                <input type="text" id="tag" onKeyUp="check_tag()" name="textTag" placeholder="tag1,tag2,tag3"/>
                                </div>
                                <div id="warning-message"></div>
                                <button id="create">Add Task</button>
                        </form>
                        </div>
                </div>
        </div>
    </body>
</html>
