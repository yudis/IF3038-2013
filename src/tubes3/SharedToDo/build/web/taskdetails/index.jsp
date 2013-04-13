<html>
    <head>
        <title>Shared To Do List - Task Details</title>
        <link rel="stylesheet" type="text/css" href="../istyle.css">
        <link rel="shortcut icon" href="=../favicon.ico">
        <script type="text/javascript" src="../ivalidation.js"></script>
        <script type="text/javascript" src="comment.js"></script>
        <script type="text/javascript" src="listOfComments.js"></script>
        <script>checkLogged()</script>
    </head>
    <body>
        <div id="navsearch">
            <script>checkLogged()</script>
        </div>
    </div>
    <div class="clearall container" id="details">
        <%
    String taskname = request.getParameter("taskname");
    String category = request.getParameter("category");				
        %>
        <div name="taskname" id="<% out.print(taskname); %>"></div>
        <div name="category" id="<% out.print(category); %>"></div>
        <script>loadTaskDetails()</script>
    </div>
</body>
</html>