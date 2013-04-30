<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@include file="logged_in_header.jsp"%>

<section>
    <!-- Navigation Bar -->        
    <%@include file="navigation_bar.jsp"%>
    <%
        URL userDetailURL = new URL("http://localhost:8084/eurilys4-service/user/user_detail?username=" + session.getAttribute("username"));
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
        String password = userDetailroot.getString("password");
        String fullname = userDetailroot.getString("fullname");
        String birthdate = userDetailroot.getString("birthdate");
        String email = userDetailroot.getString("email");
        String avatar = userDetailroot.getString("avatar");
    %>
    <div id="dynamic_content">
        <div id="edit_profil_container">
            <h1>  Edit Profile </h1>
            <form id="edit_profile_form" method="POST" action="../ServletHandler?type=edit_profile">
                <label> Username </label> <%= session.getAttribute("username") %>
                <input type="hidden" id="edit_username" name="edit_username" value="<%= session.getAttribute("username") %>">
                <input type="hidden" id="edit_email" name="edit_email" value="">

                <h2>Change Password</h2>
                <label> New Password </label> 	
                <input type="password" name="password" onkeyup="editProfileCheck()" id="edit_password" title="Password should be at least 8 characters long" >
                <img src="../img/no.png" id="password_validation" class="signup_form_validation" alt="validation image">

                <label> Confirm New Password </label> 	
                <input type="password" name="password_confirm" onkeyup="editProfileCheck()" id="edit_password_confirm" title="Confirmation password should be the same with Password" >
                <img src="../img/no.png" id="confirm_validation" class="signup_form_validation" alt="validation image">
                <div class="clear"></div>

                <h2>Change Details</h2>
                <label> Full Name </label> 
                <input type="text" name="fullname" id="fullname" onkeyup="editProfileCheck()" value="<%=fullname%>" title="Your name should be at least consists first name and last name">
                <img src="../img/yes.png" id="name_validation" class="signup_form_validation" alt="validation image">
                <br><label> Birth Date </label> <input type="date" id="birthdate" name="birthdate" value="<%=birthdate%>">  
                <br><label> Avatar </label> <input type="file" id="avatar" name="avatar" onchange="javascript:editProfileCheck();">

                <input type="submit" name="edit_profile_submit" class="link_red top10 bold" id="edit_profile_submit" value="SAVE" disabled="disabled">
            </form>
        </div>
    </div>
</section>
<%@include file="footer.jsp"%>