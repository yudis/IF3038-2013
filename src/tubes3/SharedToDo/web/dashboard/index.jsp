<html>
    <head>
        <title>Shared To Do List - Dashboard</title>
        <link rel="stylesheet" type="text/css" href="../style.css">
        <link rel="shortcut icon" href="../favicon.ico">		
        <script type="text/javascript" src="../validation.js"></script>		
    </head>
    <body onload="checkLogged();">
        <div id="navsearch">
            <script>checkLogged()</script>
        </div>
        <div class="clearall container">
            <h2>Category&nbsp;&nbsp;<img onclick="popupcat()" src="../images/plus.png" id="pluscat"></h2>			
                <%@include file = "loadcategory.jsp" %>
        </div>
        <div class="clearall container">
            <h2>Task&nbsp;&nbsp;<img onclick="redirAdd()" src="../images/plus.png" id="plustask"></h2>			
                <%@include file = "loadtask.jsp" %>
        </div>		
    </body>
</html>