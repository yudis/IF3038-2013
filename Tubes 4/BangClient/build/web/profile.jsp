<%-- 
    Document   : profile
    Created on : Apr 10, 2013, 8:41:19 AM
    Author     : Compaq
--%>

<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DBOperation.UserOp"%>
<%@page import="DBOperation.JoinOp"%>
<%@page import="Model.User"%>
<%@page import="Model.Task"%>
<%
    //JoinOp JO = new JoinOp();
    String username = "";
    String userfullname = "";
    String useravatar = "";
    String userdob = "";
    String useremail = "";
    
    if(session.getAttribute("username") != null){
        username = session.getAttribute("username").toString();
        //System.out.println("cur user : " + username);
    }else{
        response.sendRedirect("index.jsp");
    }
    
    if(request.getParameter("userprofile")!=null){
        username = request.getParameter("userprofile").toString();
    }
    
    //System.out.println(username);
    
    URL url = new URL("http://localhost:8080/BangServer/user/profil/" + username);
    //URL url = new URL("http://progin4.ap01.aws.af.cm/user/login/" + username);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");

    if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
    }
    
    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
    StringBuilder output = new StringBuilder();
    String test;
    while ( (test = br.readLine()) != null)
    {
       output.append(test);
    }
    
    JSONObject result = new JSONObject(output.toString());
    if (!output.toString().equals(""))
    {
        useravatar = result.getString("avatar");
        userfullname = result.getString("fullname");
        userdob = result.getString("dob");
        useremail = result.getString("email");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>BANG! - Profile</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS Files/profile.css" media="screen" />
        <script type="text/javascript" src="Javascript Files/Profile.js"></script>
        <jsp:include page="header.jsp" />
    </head>
    <body onload="resetProfile();">
        <div class="panel">
            <img id="avatarbig" src="<%=useravatar%>"/>
            <img id="badge" src="img/badge.png">
            <div id="biousername"><%=username%></div>
            <div id="biodata">
                Name<div class="biodesc"><%=userfullname%></div></br>
                Birth<div class="biodesc"><%=userdob%></div></br>
                Email<div class="biodesc"><%=useremail%></div></br></br>
            </div>
        </div>
        <%
            if(username.equalsIgnoreCase(session.getAttribute("username").toString())){
                out.write("<input type='button' id='biopop' value='Edit Profile' onclick='showForm();'/>");
            }
        %>
        <div id="wall">
            <img src="img/todolist.png" id="todopict"/>
            <div id="todolist">
                <%
                    URL url2 = new URL("http://localhost:8080/BangServer/user/list/" + username);
                    //URL url = new URL("http://progin4.ap01.aws.af.cm/user/login/" + username);
                    HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
                    conn2.setRequestMethod("GET");
                    conn2.setRequestProperty("Accept", "application/json");

                    if (conn2.getResponseCode() != 200) {
                            throw new RuntimeException("Failed : HTTP error code : "
                                            + conn2.getResponseCode());
                    }

                    BufferedReader br2 = new BufferedReader(new InputStreamReader((conn2.getInputStream())));
                    StringBuilder output2 = new StringBuilder();
                    String test2;
                    while ( (test2 = br2.readLine()) != null)
                    {
                       output2.append(test2);
                    }
                    
                    JSONObject result2 = new JSONObject(output2.toString());
                    for (int i = 0; i < result2.length(); i++)
                    {
                        out.println(result2.getString("Task " + Integer.toString(i)) + "<br>");
                    }
            
                    //ArrayList<Task> todoList;
                    //Task task;
                    //todoList = JO.GetTodoTasksByUsername(username);
                    //System.out.println("Listing this profile's todo list...");
                    /*
                    for(int i=0; i<todoList.size(); i++){
                        task = todoList.get(i);
                        out.println(task.getName()+"<br>");
                        System.out.println(task.getName());
                    }*/
                %>
            </div>
            <img src="img/donelist.png" id="donepict"/>
            <div id="donelist">
                <%/*
                    ArrayList<Task> doneList;
                    doneList = JO.GetDoneTasksByUsername(username);
                    System.out.println("Listing this profile's done list...");
                    for(int i=0; i<doneList.size(); i++){
                        task = doneList.get(i);
                        out.println(task.getName()+"<br>");
                        System.out.println(task.getName());
                    }
                   */
                    URL url3 = new URL("http://localhost:8080/BangServer/user/list2/" + username);
                    //URL url = new URL("http://progin4.ap01.aws.af.cm/user/login/" + username);
                    HttpURLConnection conn3 = (HttpURLConnection) url3.openConnection();
                    conn3.setRequestMethod("GET");
                    conn3.setRequestProperty("Accept", "application/json");

                    if (conn3.getResponseCode() != 200) {
                            throw new RuntimeException("Failed : HTTP error code : "
                                            + conn3.getResponseCode());
                    }

                    BufferedReader br3 = new BufferedReader(new InputStreamReader((conn3.getInputStream())));
                    StringBuilder output3 = new StringBuilder();
                    String test3;
                    while ( (test3 = br3.readLine()) != null)
                    {
                       output3.append(test3);
                    }
                    
                    JSONObject result3 = new JSONObject(output3.toString());
                    for (int i = 0; i < result3.length(); i++)
                    {
                        out.println(result3.getString("Task " + Integer.toString(i)) + "<br>");
                    }
                %>
            </div>
            
        </div>
        <div id="popoutovl"></div>
        <div id="popoutbg">
            <big>Edit Profile</big>
            <form id="bioform" name="bioform" method="POST" action="EditProfile" enctype="multipart/form-data">
                Name
                <input type="text" 
                       id="bioname" 
                       name="bioname" 
                       size="10" 
                       value="<%=userfullname%>" 
                       onchange="bioFill();"/>
                </br>
                <small class="bioerror" id="errname">Name should be constructed by two or more words separated by space</br></small>
                Avatar
                <input type="file" 
                       id="biofile" 
                       name="biofile" 
                       accept="image/jpg, image/jpeg" 
                       onchange="bioFill();"/>
                </br>
                Birth
                <input type="date" 
                       id="biodate" 
                       name="biodate" 
                       size="10" 
                       value="<%=userdob%>"/>
                </br>
                Password
                <input type="password" 
                       id="biopassword1" 
                       name="biopassword1" 
                       size="10" 
                       onchange="bioFill();"/>
                </br>
                <small class="bioerror" id="errpassword1">Password should be at least 8 characters long</br></small>
                Confirm Password
                <input type="password" 
                       id="biopassword2" 
                       name="biopassword2" 
                       size="10" 
                       onchange="bioFill();"/></br>
                <small class="bioerror" id="errpassword2">Confirmed password and password are not the same</br></small>
                <input type="submit" id="biobutton" value="Done Editing"/>
            </form>
        </div>
        
        <%
            if (session.getAttribute("message") != null){     
                out.print("<script>alert('"+(String)session.getAttribute("message")+"');</script>");
                request.getSession().removeAttribute("message");
            }
        %>
    </body>
</html>
