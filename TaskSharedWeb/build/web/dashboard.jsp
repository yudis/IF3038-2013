<%-- 
    Document   : dashboard
    Created on : Apr 6, 2013, 8:14:09 AM
    Author     : VAIO
--%>

<%@page import="Class.Function"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link href="css/modal.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="javascript/dashboard.js"></script>
    </head>
    <body>
        <%
        /*session management  */
        String userActive = "";
        if(request.getSession().getAttribute("userlistapp")==null){
            response.sendRedirect("index.jsp");
        }else{
            userActive = request.getSession().getAttribute("userlistapp").toString();
        }
        %>
        <div id="main-body-general">
            <!--Header-->
            <div id="header">
            <jsp:include page="header.jsp" />
            </div>
            <div><hr id="border"></div>
            <!--Body-->
            <div id="dashboard-body">
                <div id="dashboard-body">
                    <div id="profile-pic">
                        <%
                        HashMap<String,String> userActiveData = (new Function()).GetUser(userActive);
                        %>
                        <a href="profile.jsp?username=<%=userActive %>"><img alt="" id="photo" src="avatar/<%=userActiveData.get("avatar") %>" width="120" height="150"/>
                            <br />
                            <b><%=userActive %></b></a>
                    </div>
                    <div id="main-dashboard">
                            <div id="dashboard-title"><b>MY TASK<br /></b><br /></div>
                            <div id="add-category"><a href="#add_task"><button>+ New Category</button></a>&nbsp;</div>

                            <!-- popup form #1 -->
                            <a href="#x" class="overlay" id="add_task"></a>
                            <div class="popup">
                                    <h2>Add New Category</h2>
                                    <br>
                            <form action="insertcategory" method="post">
                                    <div>
                                            <label for="login">Name:</label>
                                            <input type="text" id="login" value="" name="newCategory"/>
                                    </div>
                                    <div>
                                            <label for="asignee">Assignee:</label>
                                            <input type="text" id="asignee" value="" name="listAssignee" onKeyUp="autoCompleteAsignee()" list="assignee" placeholder="Assignee1,Assignee2,Assignee3"/>
                                            <div id="assignee-suggest"></div>
                                    </div>
                                    <div align="right"><input type="submit" value="Finish"/></div>
                                    </form>

                                    <a class="close" href="#close"></a>
                            </div>

                            <div id="sort">	
                                    Sort by :
                                    <select name="Sort by">
                                            <option value="Auto">Auto</option>
                                            <option value="Name">Name</option>
                                            <option value="Date">Date</option>
                                    </select>&nbsp;			
                            </div>
                            <!--Category list (static)-->
                            <div id="category">
                                <jsp:include page="listCategory.jsp" />
                            </div>

                            <div id="new-category"></div>
                            <!--New category button ==> popup-->
                                    <!--Name-->
                                    <!--List of priveleged users-->
                            <!--New task button ==> new_task.html (this button only appears if a category is selected)-->
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
