<%-- 
    Document   : dashboard
    Created on : Apr 9, 2013, 11:33:04 PM
    Author     : Nicholas Rio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String username = session.getAttribute("username").toString();
    //System.out.println("cur user : " + username);
%>
<!DOCTYPE html>
<html>
    <head>
        <title>BANG! - Dashboard</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--link rel="stylesheet" type="text/css" href="css.css" media="screen" /-->
        <link rel="stylesheet" type="text/css" href="CSS Files/autocomplete.css"/>
        <link rel="stylesheet" type="text/css" href="CSS Files/dashboard.css"/>
        <script src="Javascript Files/multiautocomplete.js"></script>
        <script type="text/javascript" src="Javascript Files/Comment.js"></script>
        <jsp:include page="header.jsp" />
        
    </head>
    <body>

        <div class="panel">    
            <div id="category"></div>
            <div id="addCat">
                <a onclick="addCategory();">+ category</a>
            </div>
        </div>

        <div id="listwall" class="list"></div>

        <div id="popoutovl"></div>
        <div class="tugas" id="rincitugas">
        </div>
        
        <div id="popoutovl"></div>
        <div class="tugas" id ="edittugas">
        </div>

        <div id="popoutovl"></div>
        <div id="add2">
            <div class="tugas" id="buattugas">
                <form method='POST' onsubmit="addTask();" enctype="multipart/form-data">
                    <div class="nama">Nama task: <input type="text" id="newTaskName" required
                        pattern='[A-Za-z0-9]{1,25}'></div><br/>
                    <div class="deadline">Deadline: <input type="date" id='newTaskDeadline' required></div><br/>
                    <div class="attachment">Attachment: <input type="file" id='newTaskAttach' multiple></div><br/>
                    <div class="asignee">Assignee: <input type="text" name='newTaskAssignee'
                        id='newTaskAssignee'
                        onkeyup="multiAutocomp(this, 'getAllUser.jsp');"
                        onfocusin="multiAutocompClearAll();"
                        autocomplete="off"></div><br/>
                    <div class="tag">Tag:  <input type="text" name='newTaskTag' id='newTaskTag'
                        onkeyup="multiAutocomp(this, 'getAllTag.jsp');"
                        onfocusin="multiAutocompClearAll();"
                        autocomplete="off"></div> <br/>
                    <input type='submit' value='Create Task'><br/>
                </form>
                <input type="submit" onclick="restore3();" value="Cancel">
            </div>
        </div>
            
        <div id="popoutovl"></div>
        <div id="popoutbg">
            <big>Add Category</big>
            <div id="add">
                <form onsubmit='return addCat();'>
                    Category Name: <input type='text' id='newCategoryName' required autocomplete="off"><br/>
                    Authorized Users: <input type='text' id ="authUsers"
                                             onkeyup="multiAutocomp(this, 'getAllUser.jsp');"
                                             onfocusin="multiAutocompClearAll()"
                                             autocomplete="off"><br/>
                    <input type="submit" value="Create Category">
                </form>
                <input type="submit" onclick="restore();" value="Cancel">
            </div>
        </div>
    </body>
    <script type="text/javascript" src="Javascript Files/Dashboard.js"></script>
    <%
        if (request.getParameter("seektask") != null) {
            String taskID = request.getParameter("seektask").toString();
            out.write("<script type = text/javascript>");
            out.write("showRinci(" + taskID + ");");
            out.write("</script>");
        }
    %>
</html>
