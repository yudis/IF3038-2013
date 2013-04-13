<%-- 
    Document   : profile
    Created on : Apr 12, 2013, 2:52:14 PM
    Author     : Sonny Theo Thumbur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SharedToDoList | profil</title>
        <link rel="stylesheet" type="text/css" href="style/style.css"></link>
        <script LANGUAGE="Javascript" src="script/script.js"></script>
    </head>
    <body>
        <!--<h1>Hello World!</h1>-->
        <form action="Suggestion" method="get">
    	<div id="header">
            <a href="dashboard.jsp"><img id="logo" src="res/logo1.png" alt="to-do list"></img></a>
            <a id="dashboardLink" href="dashboard.jsp">dashboard</a>
            <input id="searchForm" type="text" name="keyword" onkeyup="showHint(this.value)" placeholder="search"></input>
            <select id="filter" name="filter">
                <!--<option selected>Select Filter ...</option>-->
                <option>all</option>
                <option>user</option>
                <option>category</option>
                <option>task</option>
            </select>
            <input id="submitForm" type="submit" name="search" value="search" onclick="toSearchResult(searchForm.value,filter.value)">
	    <a href="profile.jsp"><img id="profile" src="res/profileLogo.png" onclick="keProfil()";/></a>
	    <a id="logout" href="logout.php">Log Out</a>
	    <div class="suggest">Suggestion : <span id="textHint"></span></div>
        </div>
	</form>
        
        <div id="spasi">
        </div>
        
        <div id="photoSpace">
        	<div id="userPhoto">
                <%
                    String curUser = (String) session.getAttribute("username");
//                        out.println(curUser);
//                    response.setIntHeader("Refresh", 5);
                %>
                <img id="user" src="server/<%= curUser %>.png" alt="userPhoto"/>
		<div id="photoUploader">
		    <label><em>Upload new Avatar : </em></label>
		    <form method="POST" action="UploadImage" enctype="multipart/form-data" name="uploadImage">
			<input id="fileUpload" type="file" name="image"></input>
			<input type="hidden" name="MAX_FILE_SIZE" value="<?php echo MAX_FILE_SIZE; ?>"/>
			<input type="submit" value="Upload new Avatar"/>
		    </form>
		</div>
            </div>
        </div>
        
        <%
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            
            Connection con = null;
            ResultSet rs = null;
            Statement stmt = null;
            
            String url = "jdbc:mysql://localhost:3306/progin_405_13510027?user=progin&password=progin";
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String sql = "SELECT * from user";
            rs = stmt.executeQuery(sql);
        %>
        
        <%
            while (rs.next()) {
                if (rs.getString("username").equals(curUser)) {
        %>
            <div id="userData">
                <h2 id="biodataTitle">BIODATA</h2><hr/>
                <div id="biodataContent">
                    <div class="bioLeft">
                        <p>Nama Lengkap :</p>
                    </div>
                    <div id="userFullName" class="bioRight">
                        <p><%= rs.getString("fullname") %></p>
                    </div>
                    <div class="bioLeft">
                        <p>Username :</p>
                    </div>
                    <div class="bioRight">
                        <p><em><%= rs.getString("username") %></em></p>
                    </div>
                    <div class="bioLeft">
                        <p>Tanggal Lahir :</p>
                    </div>
                    <div id="userBirthdate" class="bioRight">
                        <p><%= rs.getString("tanggalLahir") %></p>
                    </div>
                    <div class="bioLeft">
                        <p>Email :</p>
                    </div>
                    <div class="bioRight">
                        <p><%= rs.getString("email") %></p>
                    </div>
                    <div class="bioLeft">
                        <p></p>
                    </div>
                    <div class="bioRight">
                        <button id="editProfileBtn" onclick="showEditForm();">Edit</button>
                    </div>
                </div>
            </div>
        <%
           } else {
            //do nothing
           }
                       }
        %>
        
        <!--FORM EDIT SECTION-->
        <div id=editForm>
            <form method="POST" enctype="multipart/form-data" name="uploadImage">
                <div class="bioLeft">
                    <p>new Full Name :</p>
                </div>
                <div class="bioRight">
                    <input id="newFullName" type=text name="newFullName"></input>
                </div>
                <div class="bioLeft">
                    <p>new Birthdate :</p>
                </div>
                <div class="bioRight">
                    <input id="newBirthdate" type=date name="newBirthdate"></input>
                </div>
                <div class="bioLeft">
                    <p>new Password :</p>
                </div>
                <div class="bioRight">
                    <input id="newPassword" type="password" name="newPassword"></input>
                </div>
                <div class="bioLeft">
                    <p>Confirm new Password :</p>
                </div>
                <div class="bioRight">
                    <input id="newPasswordAgain" type="password" name="newPasswordAgain"></input>
                </div>
                <div class="bioLeft">
                    <p></p>
                </div>
                <div class="bioRight">
                    <input class="submitBtn" type="button" value="Submit Form" name="upload" onclick="hideEditForm(); updateProfile(newFullName.value, newBirthdate.value, newPassword.value, newPasswordAgain.value);"></input>
                </div>
            </form>
        </div>
    </body>
</html>
