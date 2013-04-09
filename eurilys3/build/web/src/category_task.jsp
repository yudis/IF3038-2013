<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" import ="java.sql.*" %>  
<%  
    String categoryName     = request.getParameter("categoryName");  
    String categoryCreator  = "";
    String categoryId       = "";
    String buffer           = "";
    String taskID           = "";
    String taskCreator      = "";
    String tagList          = "";
    ResultSet rs_task       = null;
    ResultSet rs_tag        = null;
    
    try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Berhasil connect ke Mysql JDBC Driver - category_task.jsp");
    } catch (ClassNotFoundException ex) {
        System.out.println("Where is your MySQL JDBC Driver? - category_task.jsp");
    }
    Connection conn_categorytask = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
    PreparedStatement stmt_categorytask = conn_categorytask.prepareStatement("");
    
    //Cek category creator
    stmt_categorytask = conn_categorytask.prepareStatement("SELECT cat_id, cat_creator FROM category WHERE cat_name=?");
    stmt_categorytask.setString(1, categoryName);
    rs_task = stmt_categorytask.executeQuery();
    rs_task.beforeFirst();
    while (rs_task.next()) {
        categoryCreator = rs_task.getString("cat_creator");
        categoryId = rs_task.getString("cat_id");
    }
    if (categoryCreator.equals((String) session.getAttribute("username"))) {        
        buffer += "<form method='POST' action='../ServletHandler?type=delete_category'>";
            buffer += "<input type='hidden' name='delete_category_id' value='"+categoryId+"'/>";
            buffer += "<input type='hidden' name='delete_category_name' value='"+categoryName+"'/>";
            buffer += "<input type='submit' id='delete_category_button' name='delete_category_button' class='link_red top20' value='Delete Category'/>";
        buffer += "</form>";
    }
    
    // Get Task where user is either the task creator or the task asignee AND task.cat_name = categoryName
    stmt_categorytask = conn_categorytask.prepareStatement("SELECT DISTINCT task.* FROM task INNER JOIN task_asignee WHERE (task.task_creator=? OR task_asignee.username=?) AND task.cat_name=?;");
    stmt_categorytask.setString(1, (String) session.getAttribute("username"));
    stmt_categorytask.setString(2, (String) session.getAttribute("username"));
    stmt_categorytask.setString(3, categoryName);
    
    rs_task = stmt_categorytask.executeQuery();
    rs_task.beforeFirst();
    while (rs_task.next()) {
        System.out.println("task id : " + rs_task.getString("task_id"));
        System.out.println("task creator : " + rs_task.getString("task_creator"));
        taskID      = rs_task.getString("task_id");
        taskCreator = rs_task.getString("task_creator");
        
        //Get Tag List
        stmt_categorytask = conn_categorytask.prepareStatement("SELECT * from tag WHERE task_id=?");
        stmt_categorytask.setString(1, taskID);
        rs_tag = stmt_categorytask.executeQuery();
        rs_tag.beforeFirst();
        tagList = "";
        while (rs_tag.next()) {
            tagList = tagList + rs_tag.getString("tag_name") + " , " ;
        }
        System.out.println("tag list : " + tagList);
        
        //Generate Output
        buffer += "<br>";
        buffer = buffer + "<div class='task_view' id='" +taskID+ "'>";        
        if (taskCreator.equals((String) session.getAttribute("username"))) {
            buffer = buffer + "<div onclick='javascript:deleteTask('"+taskID +"');' class='task_done_button'> Delete </div>";
            buffer = buffer + "<div class='task_done_button'> &nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp; </div>";
        }
        //Task has not been finished 
        if (rs_task.getString("task_status").equals("0")) {
            buffer = buffer + "<div onclick='javascript:finishTask('"+taskID+"');' class='task_done_button'> Mark as Finished </div>";
        } else {
            buffer += "<img src='../img/yes.png' class='task_done_button' alt=''/>";
        }
        
        buffer += "<div id='task_name_ltd' class='left dynamic_content_left'> Task Name </div>";
        buffer += "<div id='task_name_rtd' class='left dynamic_content_right darkBlueLink' onclick='javascript:viewTask('"+taskID+"');'>" +rs_task.getString("task_name")+ "</div>";
        buffer += "<br><br>";
        
        buffer += "<div id='deadline_ltd' class='left dynamic_content_left'>Deadline</div>";
        buffer = buffer + "<div id='deadline_rtd' class='left dynamic_content_right'>"+ rs_task.getString("task_deadline") +"</div>";
        buffer += "<br><br>";
        
        buffer += "<div id='tag_ltd' class='left dynamic_content_left'>Tag</div>";
        buffer = buffer + "<div id='tag_rtd' class='left dynamic_content_right'>" +tagList+ "</div>";
        buffer += "<br>";
        
        buffer = buffer + "<div class='task_view_category'>" +rs_task.getString("cat_name") +"</div>";
        buffer += "<br></div>";
    }
    
    conn_categorytask.close();
    response.getWriter().println(buffer);
%>