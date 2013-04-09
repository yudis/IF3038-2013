<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>

<%
    String username_ = (String) session.getAttribute("username");
    String taskID = null;
    String tagList = "";
    String taskCreator = "";
    Connection conn_ = null;
    
    try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Berhasil connect ke Mysql JDBC Driver ... ");
    } catch (ClassNotFoundException ex) {
        System.out.println("Where is your MySQL JDBC Driver?");
    }
    conn_ = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
    
    // Get Task where user is either the task creator or the task asignee
    PreparedStatement stmt_ = conn_.prepareStatement("SELECT DISTINCT task.* FROM task INNER JOIN task_asignee WHERE task.task_creator=? OR task_asignee.username=?;");
    stmt_.setString(1, username_);
    stmt_.setString(2, username_);
    ResultSet rs_task = stmt_.executeQuery();
    rs_task.beforeFirst();
    
    //Result set is not empty    
    while (rs_task.next()) {
        taskID = rs_task.getString("task_id");
        taskCreator = rs_task.getString("task_creator");
        
        //Get Tag List
        stmt_ = conn_.prepareStatement("SELECT * from tag WHERE task_id=?");
        stmt_.setString(1, taskID);
        ResultSet rs_tag = stmt_.executeQuery();
        rs_tag.beforeFirst();
        tagList = "";
        while (rs_tag.next()) {
            tagList = tagList + rs_tag.getString("tag_name") + " , " ;
        }
        
        //Generate Output 
        %>
        <br>
        <div class='task_view' id='<%=taskID%>'>            
        <%
        if (taskCreator.equals(username_)) { %>
            <div onclick="javascript:deleteTask('<%=taskID%>')" class='task_done_button'> Delete </div>
            <div class='task_done_button'> &nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp; </div>
        <%
        }
        
        //Task has not been finished 
        if (rs_task.getString("task_status").equals("0")) { %>
            <div onclick="javascript:finishTask('<%=taskID%>')" class='task_done_button'> Mark as Finished </div>
        <%
        }
        //Task is finished
        else { %>
            <img src='../img/yes.png' class='task_done_button' alt=''/>
        <%
        }
        %>
        
            <div id='task_name_ltd' class='left dynamic_content_left'> Task Name </div>
            <div id='task_name_rtd' class='left dynamic_content_right darkBlueLink' onclick="javascript:viewTask('<%= taskID %>')"> <%= rs_task.getString("task_name") %> </div>
            <br><br>
            <div id='deadline_ltd' class='left dynamic_content_left'>Deadline</div>
            <div id='deadline_rtd' class='left dynamic_content_right'> <%= rs_task.getString("task_deadline") %> </div>
            <br><br>
            <div id='tag_ltd' class='left dynamic_content_left'>Tag</div>
            <div id='tag_rtd' class='left dynamic_content_right'> <%= tagList %> </div>
            <br>
            <div class='task_view_category'> <%= rs_task.getString("cat_name") %> </div>
            <br>
        </div>
        
        <%
    }    
    conn_.close();
%>