<%@page import="org.json.*"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.sql.Blob"%>
<script type="text/javascript" src="../js/animation.js"> </script>

<%
    String user_name = (String) session.getAttribute("username");
    URL url = new URL("http://localhost:8084/eurilys4-service/category/get_list?username=" + session.getAttribute("username"));
    //URL url = new URL("http://eurilys.ap01.aws.af.cm/category/get_list?username=" + session.getAttribute("username"));
    HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
    httpConn.setRequestMethod("GET");
    httpConn.setRequestProperty("Accept", "application/json");
    if (httpConn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + httpConn.getResponseCode());
    }
    BufferedReader br = new BufferedReader(new InputStreamReader((httpConn.getInputStream())));
    String output;
    String categoryJSONObject = "";
    while ((output = br.readLine()) != null) {
        categoryJSONObject += output;
    } 
    httpConn.disconnect();
    
    //Parse categoryJSONObject 
    JSONTokener tokener = new JSONTokener(categoryJSONObject);
    JSONArray root = new JSONArray(tokener);    
%>

<div id="navbar">
    <div id="short_profile">
        <a href="profile.jsp"> <img id="profile_picture" src="" alt=""> </a>
        <div id="profile_info">
            Welcome, <br>
            <a href="profile.jsp" class="darkBlue"> <%= session.getAttribute("fullname") %> </a>
            <br><br>
            <div class="link_red" id="edit_profile_button"> <a href="edit_profile.jsp"> Edit Profile </a></div>
        </div>
    </div>
    <div id="category_list">
        <% if ("failed".equals(request.getParameter("delete_category"))) { %>
            <div class="red"> Fail to delete category </div>
        <% } %>    
        <div class="link_blue_rect" id="category_title"><a href="dashboard.jsp" onclick=""> All Categories </a> </div>
        <ul id="category_item">   
            <%
                for (int i=0; i<root.length(); i++) {
                    JSONObject category = root.getJSONObject(i);
                    String categoryId = category.getString("category_id");
                    String categoryName = category.getString("category_name");
                    String categoryCreator = category.getString("category_creator");
            %>
                <li> 
                    <span class='categoryList' onclick="javascript:refreshTask('<%=categoryId%>','<%=categoryCreator%>','<%= categoryName %>','<%=user_name%>');"> <%= categoryName %> </span> 
                </li>
            <%    
                }
            %>
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
        
<script>
    function refreshTask(categoryId, categoryCreator, categoryName, username) {
        document.getElementById("dynamic_content").innerHTML = "";
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp=new XMLHttpRequest();
        }
        else {// code for IE6, IE5
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        
        categoryName = escape(categoryName);
        
        xmlhttp.onreadystatechange=function() {
            var buffer = "";
                
            if (categoryCreator == username) {
                buffer += "<form method='POST' action='../ServletHandler?type=delete_category&category_id="+categoryId+"'>";
                buffer += "<input type='hidden' name='delete_category_id' value='"+categoryId+"'/>";
                buffer += "<input type='hidden' name='delete_category_name' value='"+categoryName+"'/>";
                buffer += "<input type='submit' id='delete_category_button' name='delete_category_button' class='link_red top20' value='Delete Category'/>";
                buffer += "</form>";
            }

            if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                var taskArray = JSON.parse(xmlhttp.responseText);                
                
                for(var i=0; i<taskArray.length; i++) {
                    var task_name = taskArray[i].task_name; 
                    var task_id = taskArray[i].task_id;
                    var task_status = taskArray[i].task_status;
                    var task_deadline = taskArray[i].task_deadline;
                    var task_creator = taskArray[i].task_creator;
                    var tagList = taskArray[i].tag_list;
                    
                    //Generate Output
                    buffer += "<br>";
                    buffer = buffer + "<div class='task_view' id='" +task_id+ "'>";  
                    if (task_creator == username) {
                        buffer = buffer + "<div onclick=\"javascript:deleteTask('"+task_id+"')\" class='task_done_button'> Delete </div>";
                        buffer = buffer + "<div class='task_done_button'> &nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp; </div>";
                    }
                    
                    buffer = buffer + "<div id='taskHandler_"+task_id+"'>";
                    //Task has not been finished 
                    if (task_status == 0) {
                        buffer = buffer + "<div onclick=\"javascript:finishTask('"+task_id+"')\" class='task_done_button'> Mark as Finished </div>";
                    } else {
                        buffer += "<img src='../img/yes.png' class='task_done_button' alt=''/>";
                    }
                    buffer += "</div>";
                    buffer += "<div id='task_name_ltd' class='left dynamic_content_left'> Task Name </div>";
                    buffer += "<div id='task_name_rtd' class='left dynamic_content_right darkBlueLink' onclick=\"javascript:viewTask('"+task_id+"')\">" +task_name+ "</div>";
                    buffer += "<br><br>";

                    buffer += "<div id='deadline_ltd' class='left dynamic_content_left'>Deadline</div>";
                    buffer = buffer + "<div id='deadline_rtd' class='left dynamic_content_right'>"+ task_deadline +"</div>";
                    buffer += "<br><br>";

                    buffer += "<div id='tag_ltd' class='left dynamic_content_left'>Tag</div>";
                    buffer += "<div id='tag_rtd' class='left dynamic_content_right'>";
                    buffer += tagList;
                    buffer += "</div>";
                    buffer += "<br>";

                    buffer = buffer + "<div class='task_view_category'>"+ categoryName +"</div>";
                    buffer += "<br></div>";
                }
                document.getElementById("dynamic_content").innerHTML += buffer;
            }
        }

        document.getElementById("add_task_link").style.display = "block";
        document.getElementById("add_task").setAttribute('href',"addtask.jsp?cat_name="+categoryName);

        xmlhttp.open("GET", "../ServletHandler?type=category_task&category_name="+categoryName, true);
        xmlhttp.send();
    }
</script>