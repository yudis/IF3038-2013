<%-- 
    Document   : task_page
    Created on : Apr 6, 2013, 8:26:09 AM
    Author     : VAIO
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="Class.GetConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.HashMap"%>
<%@page import="Class.Function"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Task</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="javascript/task_page.js"></script>
        <script src="javascript/calendar.js"></script>
        <link href="css/calendar.css" rel="stylesheet">
    </head>
    <body onLoad="check_html5()">
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
                            HashMap<String,String> task = new HashMap<String, String>();
                            String idtaskToShow = request.getParameter("taskid");
                            HashMap<String, String> taskShow = func.GetTask(idtaskToShow);
                        %>
                </div>

                <div id="nomargin"><hr id="border"></div>

                <!--Body-->
                <div id="task-page-body">
                        <div id="task-page-title">
                                <div>
                                    <h1><% out.print(taskShow.get("taskname"));%></h1>
                                        <i><div id="delete-task">
                                        <% 
                                            String creator = taskShow.get("username");
                                            if(creator.equals(userActive)){
                                                    out.print("<a href=\"deletetask?taskid=\""+taskShow.get("taskid") +"\" onClick=\"confirmTask()\"><i>Delete This Task</i></a>");
                                            }
                                        %>
                <br><br></div></i>
                                        Submit by <b><i><% out.print(taskShow.get("username"));%></i></b> at. <% out.print(taskShow.get("createddate"));%>
                                </div>
                                <br>
                                <div id="deadline_done">
                                        <div id="left-main-body">Deadline : <% out.print(taskShow.get("deadline"));%></div>
                                        <% 
                                                if(creator.equals(userActive)){
                                                        out.print("<div id=\"right-main-body\"><a href=\"#\" onCLick=\"edit_deadline()\"><u>edit</u></a></div>");
                                                }
                                        %>
                                </div>
                                <div id="deadline_edit">
                                        <div id="left-main-body"><div id="label">Deadline : </div>
                                                <div id="date_html">
                                                        <input id="datedeadlineinput" type="text" class="calendarSelectDate" name="textDeadlineDate" placeholder="YYYY-MM-DD"/><div id="calendarDiv"></div><br />
                                                        <input id="timedeadlineinput" type="text" name="textDeadlineTime" placeholder="HH:MM"/>
                                                </div>              
                                        </div>
                                        <div id="right-main-body"><a href="#" onCLick="finish_deadline(<% out.print(taskShow.get("taskid"));%>)"><u>done</u></a></div>
                                </div>
                                <br><br><br>
            <div>
                                        <div id="left-main-body4">Status : <% out.print(taskShow.get("status"));%></div> 
                                        <div id="right-main-body">
                                        <% 
                                                if(creator.equals(userActive)){
                                                        out.print("<input name=\"changeStatus\" type=\"button\" value=\"Change Status\" onClick=\"setCompleteStatus("+taskShow.get("taskid")+")\">");
                                                }
                                        %>                   
                </div>
                                </div>
                        </div>
                        <br><br><br><br>
                        <div id="attachment">
                                <div id="image-attachment">
                                        <div>
                                        <br>
                                        <br>
                                                <% 
                                                    GetConnection getCon = new GetConnection();
                                                    Connection con = getCon.getConnection();
                                                    String query = "SELECT filename FROM attachment WHERE taskid = "+idtaskToShow;
                                                    Statement stt = con.createStatement();
                                                    ResultSet result = stt.executeQuery(query);
                                                    while(result.next()){
                                                            String filename = result.getString("filename");
                                                            if(func.GetTypeFile(filename) == 0){
                                                                    out.print("<img id=\"screenshots\" src=\"attachment/"+filename+"\" alt=\""+filename+"\" title=\"$filename\"\">");
                                                            }
                                                    }
                                                %>
                                        </div>
                                </div>
            <div id="video-attachment">
                                        <% 
                                            query = "SELECT filename FROM attachment WHERE taskid = "+idtaskToShow;
                                            result = stt.executeQuery(query);
                                            while(result.next()){
                                                String filename = result.getString("filename");
                                                if(func.GetTypeFile(filename) == 1){
                                                    out.print("<video width=\"320\" height=\"240\" controls>");
                                                    out.print("<source src=\"attachment/"+filename+"\" type=\"video/mp4\">");
                                                    out.print("</video>");
                                                }
                                            }
                                        %>
                                </div>
                                <div id="other-attachment">
                                        <p>External File:</p>
                                        <ul>
                                                <% 
                                                    query = "SELECT filename FROM attachment WHERE taskid = "+idtaskToShow;
                                                    result = stt.executeQuery(query);
                                                    while(result.next()){
                                                        String filename = result.getString("filename");
                                                        if(func.GetTypeFile(filename) == 2){
                                                            out.print("<li><a href=\"attachment/"+filename+"\">"+filename+"</a></li>");
                                                        }
                                                    }
                                                %>
                                        <ul>
                                </div>
                        </div>
                        <div>
                                <div id="task-nomargin" name="1"><div id="assignee_done">
                                        <div id="left-main-body2">Shared with : <i>
                                        <%
                                            query = "SELECT username FROM assignee WHERE taskid = "+idtaskToShow;
                                            result = stt.executeQuery(query);
                                            while(result.next()){
                                                out.print("<a href=\"profile.jsp?username="+result.getString("username") +"\"> <u>"+result.getString("username")+"</u> </a>");
                                            }
                                        %>
                </i></div>
                                        <div id="right-main-body">
                    <% 
                        if(creator.equals(userActive)){
                                        out.print("<a href=\"#2\" onClick=\"edit_assignee()\"><u>edit</u></a>");
                        }
                    %>
                </div>
                                </div></div>
                                <div id="assignee_edit" name="2">
                                        <div id="left-main-body">Shared with : <input id="task-assignee" type="text" name="textAssignee" list="assignee-task" onKeyUp="autoCompleteAsignee()" placeholder="tag1,tag2,tag3"/>
                                        <div id="shared-with"></div>
                                        </div>
                                        <div id="right-main-body"><a href="#1" onClick="finish_assignee(<% out.print(idtaskToShow); %>)"><u>done</u></a></div>
                                </div>
                                <br>
                                <br>
                                <div id="task-nomargin" name="3"><div id="tag_done">
                                        <div id="left-main-body3">Tag : <i>
                        <% 
                            query = "SELECT tagid FROM task_tag WHERE taskid = "+idtaskToShow;
                            result = stt.executeQuery(query);
                            while(result.next())
                            {
                                HashMap<String,String> tag = func.GetTag(result.getString("tagid"));
                                out.print(" <u>"+tag.get("tagname")+"</u> ");
                            }
                        %>
                </i></div>
                                        <div id="right-main-body">
                <% 
                    if(creator.equals(userActive)){
                        out.print("<a href=\"#3\" onClick=\"edit_tag()\"><u>edit</u></a>");
                    }
                %>
                </div>
                                </div></div>
                                <div id="tag_edit" name="4">
                                        <div id="left-main-body">Tag : <input id="tag-edit" type="text" placeholder="tag1,tag2,tag3"/></div>
                                        <div id="right-main-body"><a href="#4" onClick="finish_tag(<% out.print(idtaskToShow);%>)"><u>done</u></a></div>
                                </div>
                        </div>
                </div>

                <div id="nomargin"><hr id="border"></div>

                <!--Comment-->
                <div id="task-comment">
                        <div id="user-comment">
                            <p id="nComment"><b><% out.print(func.GetNComment(idtaskToShow));%> Comment</b></p>
                                <div id="comment-list">
                <%
                        query = "SELECT * FROM comment WHERE taskid ="+idtaskToShow;
                        ResultSet rs = stt.executeQuery(query);
                        while(rs.next()){
                                out.print(" <div id=\"comment\">");
                                out.print(" 	<div id=\"user-info\">");
                                out.print(" 		<div id=\"left-comment-body\">");
                                out.print(" 			<img src=\"avatar/"+func.GetUser(rs.getString("username")).get("avatar") +"\" width=\"50px\" height=\"50px\"/>");
                                out.print(" 		</div>");
                                out.print(" 		<div id=\"right-comment-body\">");
                                out.print(" 			<b id=\"komentator\">"+rs.getString("username") +"</b>");
                                out.print(" 			<br>");
                                out.print(" 			<b id=\"post-date\">Post at "+rs.getString("createdate") +"</b>");
                                out.print(" 		</div>");
                                out.print(" 		<div id=\"delete-comment\">");
                                        if(rs.getString("username").equals(userActive)){
                                            out.print("<a href=\"#\" onClick=\"deleteComment("+rs.getString("commentid") +","+idtaskToShow+")\"><i>Delete Comment</i></a>");
                                        }
                                out.print(" 		</div>");
                                out.print(" 	</div>");
                                out.print(" 	<div id=\"comment-box\">");
                                out.print(" 		<p>");
                                out.print(rs.getString("message"));
                                out.print(" 		</p>");
                                out.print(" 	</div>");
                                out.print(" </div>");
                        }
                %>

                                        <div id="new-comment"></div>
                                </div>
                        </div>
                        <div id="add-comment">
                                <p><b>Leave a comment</b></p>
                                <form>
                                        <textarea id="textarea-comment" rows="8" cols="92" placeholder="Comment about this task..."></textarea>
                                </form>
                                <div><button id="submit-comment" onClick="addComment(<% out.print(idtaskToShow); %>)">Submit</button>&nbsp;</div>
                        </div>
                </div>

        </div>
    </body>
</html>
