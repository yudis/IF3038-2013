<%@include file="header.jsp"%>
<%
  String categoryID = request.getParameter("CategoryID");
  String userID = request.getParameter("userID");
 %>
<html>
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="autocomplete.css" media="screen" />
    </head>
    <body>
        <div id="category">
            <div class = "kategori"><a title="Go to Dashboard" href="dashboard.jsp">Back to Dashboard</a></div>
        </div>
        <div id ="listtugas" class="list">

            <div class="tugass" id="buattugas">
                <form id="addTaskForm" method="post" action="controller?type=addTaskDB" enctype="multipart/form-data">
                    <br/>
                    Name: <div class="nama"><input type="text" id="namaTask" name="namaTask" required></div><br/>
                    Attachment: <div class="attachment"><input type="file" id="newAttachmentTask" name="attachfile[]"  multiple required></div><br/>
                    Deadline: <div class="deadline"><input id="newDeadlineTask" name="newDeadlineTask" type="date" required></div><br/>
                    Assignee: <div class="asignee"><input id="newAssigneeTask" name="newAssigneeTask" type="text" onkeyup="multiAutocomp(this, 'assignee.php', 'buattugas');" onfocus="multiAutocompClearAll()" required></div><br/>
                    Tag: <div class="tag"> <input id="newTagTask" name="newTagTask" type="text" required></div> <br/>
                    <%
                        out.print("<input id=\"newCategoryID\" name=\"newCategoryID\" type=\"text\" hidden=\"true\" value=\""+categoryID+"\">");
                        out.print("<input id=\"newUserID\" name=\"newUserID\" type=\"text\" hidden=\"true\" value=\""+userID+"\">");
                    %>
                    <input type="submit" id="regbuttosn" name="submit" value="create">
                    <br/>
                    <!-- <a type="submit" onclick="createTask();" class="button">create</a><br/> !-->
                </form>
            </div>
        </div>  
    </body>
    <script type="text/javascript" src="script.js"></script>
</html>