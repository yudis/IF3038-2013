<%@page import="org.json.*"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>

<!DOCTYPE html>
<html>
	<head>
            <link href='../css/mobile.css' rel='stylesheet' type='text/css'>
            <link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
            <link href='../css/wide.css' rel='stylesheet' type='text/css'>
            <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
            <script type="text/javascript" src="../js/animation.js"> </script> 
            <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >		
            <title> Eurilys </title>
	</head>
	
	<body>
            <!-- Web Header -->
            <header>
                <div id="header_container"> 
                    <div class="left">
                        <a href="dashboard.php"> <img id="logo" src="../img/logo.png" alt="logo"> </a>					
                    </div>

                    <input id="search_box" autocomplete="off" type="text" onkeyup="showSearchHint(this.value)" placeholder="Search...">
                    <div id="txtHint"> </div>

                    <select id="search_box_filter">
                        <option value="1"> All </option>
                        <option value="2"> User </option> <!-- username, email, nama lengkap, birthdate -->
                        <option value="3"> Category </option>
                        <option value="4"> Task </option> <!-- task name, tag, comment -->
                    </select>				
                    <div class="header_menu"> 
                        <div id="menu_dashboard" class="header_menu_button"> <a href="dashboard.jsp"> DASHBOARD </a>  </div>
                        <div id="menu_profile" class="header_menu_button current_header_menu">  <a href="profile.jsp"> PROFILE </a> </div>
                        <div id="menu_logout" class="header_menu_button"> <a id="logout" href="../index.jsp"> LOGOUT </a> </div>
                    </div>
                </div>
                <div class="thin_line"></div>
            </header>	

<!-- Web Content -->
<section>
        <%@include file="navigation_bar.jsp"%>
	<%
            URL userDetailURL = new URL("http://localhost:8084/eurilys4/user/user_detail?username=" + session.getAttribute("username"));
            //URL userDetailURL = new URL("http://eurilys.ap01.aws.af.cm/user/user_detail?username=" + session.getAttribute("username"));
            HttpURLConnection userDetailConn = (HttpURLConnection) userDetailURL.openConnection();
            userDetailConn.setRequestMethod("GET");
            userDetailConn.setRequestProperty("Accept", "application/json");
            if (userDetailConn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + userDetailConn.getResponseCode());
            }
            BufferedReader userDetailBr = new BufferedReader(new InputStreamReader((userDetailConn.getInputStream())));
            String userDetailOutput;
            String userDetailJSONObject = "";
            while ((userDetailOutput = userDetailBr.readLine()) != null) {
                userDetailJSONObject += userDetailOutput;
            } 
            userDetailConn.disconnect();

            //Parse userDetailJSONObject 
            JSONTokener userDetailTokener = new JSONTokener(userDetailJSONObject);
            JSONObject userDetailroot = new JSONObject(userDetailTokener);
            String user_name = userDetailroot.getString("username");
            String fullname = userDetailroot.getString("fullname");
            String birthdate = userDetailroot.getString("birthdate");
            String email = userDetailroot.getString("email");
            String avatar = userDetailroot.getString("avatar");
        %>
    <div id="dynamic_content">
        <div class="half_div">
            <div id="upperprof">
                    <img id="mainpp" width="225" src="" alt=""/>
                    <div id="namauser"> <%=fullname%> </div>
            </div>
            <br/><br/>
            <%=user_name%>
            <br>
            <%=email%>
            <br>
            <%=birthdate%>
        </div>
        
        <div class="half_div">
            <div class="half_tall">
                <div class="headsdeh">Current Tasks</div>
                <%
                    URL currentTaskURL = new URL("http://localhost:8084/eurilys4/user/current_task?username=" + session.getAttribute("username"));
                    //URL currentTaskURL = new URL("http://eurilys.ap01.aws.af.cm/user/current_task?username=" + session.getAttribute("username"));
                    HttpURLConnection currentTaskConn = (HttpURLConnection) currentTaskURL.openConnection();
                    currentTaskConn.setRequestMethod("GET");
                    currentTaskConn.setRequestProperty("Accept", "application/json");
                    if (currentTaskConn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : " + currentTaskConn.getResponseCode());
                    }
                    BufferedReader currentTaskBr = new BufferedReader(new InputStreamReader((currentTaskConn.getInputStream())));
                    String currentTaskOutput;
                    String currentTaskJSONObject = "";
                    while ((currentTaskOutput = currentTaskBr.readLine()) != null) {
                        currentTaskJSONObject += currentTaskOutput;
                    } 
                    currentTaskConn.disconnect();

                    //Parse userDetailJSONObject 
                    JSONTokener currentTaskTokener = new JSONTokener(currentTaskJSONObject);
                    JSONArray currentTaskRoot = new JSONArray(currentTaskTokener);
                
                    for (int i=0; i<currentTaskRoot.length(); i++) {
                        JSONObject currentTask = currentTaskRoot.getJSONObject(i);
                        String task_name = currentTask.getString("task_name");
                        String task_id = currentTask.getString("task_id");
                %>
                    <div class='cursorPointer darkBlueLink' onclick='javascript:viewTask("<%=task_id%>")'> <%=task_name%> </div>   
                <%    
                    }
                %>
            </div>
            <div class="half_tall">
                <div class="headsdeh">Finished Tasks</div>
                <%
                    currentTaskURL = new URL("http://localhost:8084/eurilys4/user/finished_task?username=" + session.getAttribute("username"));
                    //currentTaskURL = new URL("http://eurilys.ap01.aws.af.cm/user/finished_task?username=" + session.getAttribute("username"));
                    currentTaskConn = (HttpURLConnection) currentTaskURL.openConnection();
                    currentTaskConn.setRequestMethod("GET");
                    currentTaskConn.setRequestProperty("Accept", "application/json");
                    if (currentTaskConn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : " + currentTaskConn.getResponseCode());
                    }
                    currentTaskBr = new BufferedReader(new InputStreamReader((currentTaskConn.getInputStream())));
                    currentTaskOutput = "";
                    currentTaskJSONObject = "";
                    while ((currentTaskOutput = currentTaskBr.readLine()) != null) {
                        currentTaskJSONObject += currentTaskOutput;
                    } 
                    currentTaskConn.disconnect();

                    //Parse userDetailJSONObject 
                    currentTaskTokener = new JSONTokener(currentTaskJSONObject);
                    currentTaskRoot = new JSONArray(currentTaskTokener);
                
                    for (int i=0; i<currentTaskRoot.length(); i++) {
                        JSONObject currentTask = currentTaskRoot.getJSONObject(i);
                        String task_name = currentTask.getString("task_name");
                        String task_id = currentTask.getString("task_id");
                %>
                    <div class='cursorPointer darkBlueLink' onclick='javascript:viewTask("<%=task_id%>")'> <%=task_name%> </div>   
                <%    
                    }
                %>
            </div>
        </div>
    </div>
</section>
		
<%@include file="footer.jsp"%>	