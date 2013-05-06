<%-- 
    Document   : template
    Created on : Apr 10, 2013, 9:57:19 PM
    Author     : Compaq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DBOperation.*"%>
<%@page import="Model.*"%>
<link rel="stylesheet" type="text/css" href="CSS Files/template.css" media="screen" />
<link rel="stylesheet" type="text/css" href="CSS Files/header_auto.css" media="screen" />
<script type="text/javascript" src="Javascript Files/header_auto.js"></script>
<%
    session.setMaxInactiveInterval(900);
    String username = "";
    String useravatar = "";
    if(session.getAttribute("username")!=null){
        username = (String) session.getAttribute("username");
    }else{
        response.sendRedirect("index.jsp");
    }
    //System.out.println("current session username : "+username);
    
    //UserOp UO = new UserOp();
    //User U = UO.SelectUserInfoByUsername(username);
    //if(U!=null){
    //    useravatar = U.getAvatar();
    //}
    useravatar = (String) session.getAttribute("avatar");
%>
<div id="dashboard-header">
    <a href="dashboard.jsp">
    <img id="text-logo" src="img/logo_small.png" alt="logo" href="dashboard.jsp" title="Home"/>
    </a>
    <div id="header-menu">
        <a title="Go to Profile" href="profile.jsp" id="profile"><%=username%></a>
        <a title="Go to Dashboard" href="dashboard.jsp" id="dashboard">Dashboard</a>
        <a title="Log out from here" href="logout.jsp" id="logout">Logout</a>
        <img id="avatarsmall" src="<%=useravatar%>"></img>
    </div>
    <form id="search" action="resultPage.jsp" method="post">
        <input type="text" name="Search" id="box" onkeyup="h_autocomp(this,'getSearchData.jsp');" onfocusin="h_autocompClearAll()">
        <input type="submit" value="Search">
        <br/>
        <input type='radio' name='options' id='option_all' value = 'a' checked="checked"/>All
        <input type='radio' name='options' id='option_user' value = 'u'/>Username
        <input type='radio' name='options' id='option_category' value = 'c'/>Category
        <input type='radio' name='options' id='option_task' value = 't'/>Task
        <input type='hidden' name='usr' id='usr' value ='<%=username%>'/>
    </form>        
</div>