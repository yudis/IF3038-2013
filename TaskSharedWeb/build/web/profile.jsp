<%-- 
    Document   : profile
    Created on : Apr 6, 2013, 8:18:32 AM
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
        <title>Profile</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="javascript/profile.js"></script>
        <script src="javascript/calendar.js"></script>
        <link href="css/calendar.css" rel="stylesheet">
    </head>
    <body onLoad="hidden_update_box()">
        <%
        /*session management*/
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
                <%
                    Function func = new Function();
                    String usernameToShow = request.getParameter("username");
                    HashMap<String, String> userShow = func.GetUser(usernameToShow);
                %>
                </div>
                <div><hr id="border"></div>
                <!--Body-->
        <div id="profile-page-body">
                <div id="profile-header">
                        <div id="left-profile-header">
                                <img alt="Photo profile" id="photo" src="avatar/<% out.print(userShow.get("avatar"));%>" width="250" height="325"/>
                        </div>
                        <div id="right-profile-header">
                            <h2><% out.print(userShow.get("username")); %></h2>
                <%
                    if(userActive.equals(usernameToShow)){
                        out.print("<div id=\"upload_button\"><a href=\"#\" onClick=\"edit_avatar('"+usernameToShow+"')\">Upload New Avatar</a></div>");
                        out.print("<div id=\"uploader\">");
                        out.print("<form enctype=\"multipart/form-data\" method=\"post\" action=\"getavatar\">");
                        out.print("	<input type=\"file\" name=\"changeAvatar\" id=\"inputfileid\">");
                        out.print("	<input type=\"submit\">");
                        out.print("</form>");
                        out.print("</div>");
                        
                        out.print("<div id=\"change_password\"><a href=\"#\" onClick=\"edit_password('"+usernameToShow+"')\">Change Password</a></div>");
                        out.print("<div id=\"password_form\">");
                        out.print("<div id=\"newpassword\">");
                        out.print("<div id=\"left-profile-body\">New Password</div><div id=\"right-profile-body\"> : <input type=\"text\" name=\"newPass\" id=\"newpasstext\" onKeyUp=\"check_password('"+usernameToShow+"')\"></div>");
                        out.print("</div>");
                        out.print("<div id=\"warning-message\"></div>");
                        out.print("<br>");
                        out.print("</div>");
                    }
                %>
                <p>Joined on : <% out.print(userShow.get("join"));%></p>
                                <div>
                                        <div id="left-main-body"><p>About Me :</p></div>
                                        <div id="right-main-body">
                        <% 
                            if(userActive.equals(usernameToShow)){
                                out.print("<a href=\"#\" onClick=\"edit_aboutme('"+userActive+"')\"><u><p>edit</p></u></a>");
                            }else{
                                out.print("<br />");	
                            }
                        %>
                </div>
                                </div>
                                <div id="about">
                                        <div id="aboutme_show"><% out.print(userShow.get("aboutme"));%></div>
                                <div id="aboutme_edit"><input type="text" id="aboutme_to_edit" size=80></div>
                                </div>
                        </div>
                </div>
                <br><br>
                <div><hr id="border"></div>
                <div id="biodata">
                        <div>
                                <div id="left-profile-name"><p>Full Name : <% out.print(userShow.get("fullname"));%></p></div>
                                <div id="left-profile-newname"><p>Full Name : <input type="text" id="newname"></p></div>
                                <div id="right-profile-editname">
                                <% 
                                    if(userActive.equals(usernameToShow)){
                                        out.print("<a href=\"#\" onclick=\"edit_fullname('"+userActive+"')\"><u><p>edit</p></u></a>");
                                    }
                                %>
            </div>
                        </div>
                        <br><br><br>
                        <div>
                                <div id="left-profile-birthday"><p>Birth Date : <% out.print(userShow.get("birthday"));%></p></div>
                                <div id="left-profile-newbirthday"><p>Birth Date : <input type="text" id="newbirthday" class="calendarSelectDate" name="textDeadline"/><div id="calendarDiv"></div></p></div>
                                <div id="right-profile-editbirthday">
                                <% 
                                    if(userActive.equals(usernameToShow)){
                                        out.print("<a href=\"#\" onClick=\"edit_birthday('"+usernameToShow+"')\"><u><p>edit</p></u></a>");
                                    }
                                %>
            </div>
                        </div>
                        <br><br><br>
                        <div>
                                <div id="left-profile-email"><p>Email : <i><% out.print(userShow.get("email"));%></i></p></div>
                                <div id="left-profile-newemail"><p>Email : <input type="text" id="newemail"></i></p></div>
                                <div id="right-profile-editemail">
                <% 
                    if(userActive.equals(usernameToShow)){ 
                        out.print("<a href=\"#\" onClick=\"edit_email('"+usernameToShow+"')\"><u><p>edit</p></u></a>");
                    }
                %>
            </div>
                        </div>
                </div>
                <br><br><br>
                <div id="unfinished-task">
                        <div>
                                <div id="left-profile-unfinished"><h3>Unfinished Task</h3></div>
                                <div id="right-profile-body">
                                </div>	
                        </div>

                        <div>
                                <ul>
                                <% 
                                    GetConnection getCon = new GetConnection();
                                    Connection conn = getCon.getConnection();
                                    Statement stt = conn.createStatement();
                                    String query = "SELECT distinct taskid FROM assignee WHERE username='"+usernameToShow+"'";
                                    ResultSet rs = stt.executeQuery(query);
                                    while(rs.next()){
                                        HashMap<String,String> task = func.GetTask(rs.getString("taskid"));
                                        if (task != null){
                                            if(task.get("status").equals("UNCOMPLETE")){
                                                    out.print("<li><a href=\"task_page.jsp?taskid="+task.get("taskid") +"\">"+task.get("taskname")+"</a></li>");
                                            }
                                        }
                                    }
                                %>
                                </ul>
                        </div>
                </div>

                <div id="finished-task">
                        <div>
                                <div id="left-profile-finished"><h3>Finished Task</h3></div>
                                <div id="right-profile-body"><p></p>
                                </div>	
                        </div>

                        <div>
                                <ul>
                                <% 
                                    query = "SELECT distinct taskid FROM assignee WHERE username='"+usernameToShow+"'";
                                    rs = stt.executeQuery(query);
                                    while(rs.next()){
                                        HashMap<String,String> task = func.GetTask(rs.getString("taskid"));
                                        if (task != null){
                                            if(task.get("status").equals("COMPLETE")){
                                                    out.print("<li><a href=\"task_page.jsp?taskid="+task.get("taskid") +"\">"+task.get("taskname")+"</a></li>");
                                            }
                                        }
                                    }
                                    conn.close();
                                %>
                                </ul>
                        </div>
                </div>
                <br>
        </div>
    </body>
</html>
