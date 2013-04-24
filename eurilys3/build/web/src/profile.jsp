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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil connect ke Mysql JDBC Driver - edit_profile.jsp");
        } catch (ClassNotFoundException ex) {
            System.out.println("Where is your MySQL JDBC Driver? - edit_profile.jsp");
        }
        Connection con_editprofile = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
        
        //Get user detail
        String user_name = (String) session.getAttribute("username");
        String fullname = "";
        String birthdate = "";
        String email = "";
        
        PreparedStatement stmt_editprofile = con_editprofile.prepareStatement("SELECT * FROM user WHERE username=?");
        stmt_editprofile.setString(1, user_name);
        ResultSet rs_userdetail = stmt_editprofile.executeQuery();
        rs_userdetail.beforeFirst();
        while (rs_userdetail.next()) {
            fullname = rs_userdetail.getString("full_name");
            birthdate = rs_userdetail.getString("birthdate");
            email = rs_userdetail.getString("email");
        }
    %>
        
    <div id="dynamic_content">
        <div class="half_div">
            <div id="upperprof">
                    <img id="mainpp" width="225" src="../img/faceyourmanga.jpg" alt=""/>
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
                stmt_editprofile = con_editprofile.prepareStatement("SELECT DISTINCT task.task_name, task.task_id, task.task_status FROM task_asignee LEFT JOIN task ON task.task_id=task_asignee.task_id WHERE username=? OR task_creator=?");
                stmt_editprofile.setString(1, user_name);
                stmt_editprofile.setString(2, user_name);
                rs_userdetail = stmt_editprofile.executeQuery();
                rs_userdetail.beforeFirst();
                while (rs_userdetail.next()) {
                    if (rs_userdetail.getString("task_status").equals("0")) { %>
                        <div class='cursorPointer darkBlueLink' onclick='javascript:viewTask("<%=rs_userdetail.getString("task_id")%>")'> <%= rs_userdetail.getString("task_name") %> </div>   
                    <%}
                }
                %>
            </div>
            <div class="half_tall">
                <div class="headsdeh">Finished Tasks</div>
                <%
                stmt_editprofile = con_editprofile.prepareStatement("SELECT DISTINCT task.task_name, task.task_id, task.task_status FROM task_asignee LEFT JOIN task ON task.task_id=task_asignee.task_id WHERE username=? OR task_creator=?");
                stmt_editprofile.setString(1, user_name);
                stmt_editprofile.setString(2, user_name);
                rs_userdetail = stmt_editprofile.executeQuery();
                rs_userdetail.beforeFirst();
                rs_userdetail.beforeFirst();
                while (rs_userdetail.next()) {
                    if (rs_userdetail.getString("task_status").equals("1")) { %>
                        <div class='cursorPointer darkBlueLink' onclick='javascript:viewTask("<%=rs_userdetail.getString("task_id")%>")'> <%= rs_userdetail.getString("task_name") %> </div>   
                    <% }
                }
                %>
            </div>
        </div>
    </div>
</section>
		
<%@include file="footer.jsp"%>	