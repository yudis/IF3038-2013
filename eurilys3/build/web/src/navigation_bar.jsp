<%@page import="java.sql.Blob"%>
<script type="text/javascript" src="../js/animation.js"> </script>
<div id="navbar">
    <div id="short_profile">
        <%
        Blob avatar_ = null;
        Connection con_avatar = null;    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil connect ke Mysql JDBC Driver - navigation_bar.jsp");
        } catch (ClassNotFoundException ex) {
            System.out.println("Where is your MySQL JDBC Driver? - navigation_bar.jsp");
        }
        con_avatar = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");

        PreparedStatement stmt_avatar = con_avatar.prepareStatement("SELECT avatar FROM user WHERE username=?");
        stmt_avatar.setString(1, (String) session.getAttribute("username"));
        ResultSet rs_avatar_ = stmt_avatar.executeQuery();
        rs_avatar_.beforeFirst();
        while (rs_avatar_.next()) {
            avatar_ = rs_avatar_.getBlob("avatar");
        }         
        con_avatar.close();
        %>
        
        <a href="profile.jsp"> <img id="profile_picture" src="<%= avatar_ %>" alt=""> </a>
        <div id="profile_info">
            Welcome, <br>
            <a href="profile.jsp" class="darkBlue"> <%= session.getAttribute("fullname") %> </a>
            <br><br>
            <div class="link_tosca" id="edit_profile_button"> <a href="edit_profile.jsp"> Edit Profile </a></div>
        </div>
    </div>
    <div id="category_list">
        <div class="link_blue_rect" id="category_title"><a href="dashboard.jsp" onclick=""> All Categories </a> </div>
        <ul id="category_item">   
            <%@include file="loadCategory.jsp"%>	
        </ul>
        <div id="add_task_link"> <a id="add_task" name="" onclick="addCatName();" href="addtask.jsp"> + new task </a> </div>
        <div id="add_new_category" onclick="toggle_visibility('category_form');"> + new category </div>
        <div id="category_form">
            <div id="category_form_inner">
                <form method="POST" action="../ServletHandler?type=add_category">
                    <button type="submit" id="add_category_button" name="add_category_button" class="link_red"> Add </button>
                    <br>
                    Category name : <br>
                    <input type="text" name="add_category_name" id="add_category_name" value="">
                    <br>
                    Assignee(s) : <br>
                    <input type="text" autocomplete="off" name="add_category_asignee_name" id="add_category_asignee_name" value="">
                    <input type="text" autocomplete="off" onkeyup="AddCategoryAssigneHint(this.value)" id="add_category_asignee_name_auto" placeholder="Type here..." value="">
                    <div id="category_asignee_autocomplete"> </div>                    
                </form>
            </div>
        </div>
    </div>
</div>