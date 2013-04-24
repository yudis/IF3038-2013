<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" import ="java.sql.*" %>  
<%  
    String q     = request.getParameter("q");  
    String type     = request.getParameter("type");
    String buffer   = "";
    
    ResultSet search_result = null;
    
    try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Berhasil connect ke Mysql JDBC Driver ... ");
    } catch (ClassNotFoundException ex) {
        System.out.println("Where is your MySQL JDBC Driver?");
    }
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
    PreparedStatement stmt_searchresult = con.prepareStatement("");
    
    if (type.equals("user")) {
        String username = "";
        String fullname = "";
        String birthdate = "";
        Blob avatar = null;
        String email = "";
        
        stmt_searchresult = con.prepareStatement("SELECT * FROM user WHERE username=?");
        stmt_searchresult.setString(1, q);
        search_result = stmt_searchresult.executeQuery();
        search_result.beforeFirst();
        while (search_result.next()) {
            username = search_result.getString("username");
            fullname = search_result.getString("full_name");
            birthdate = search_result.getString("birthdate");
            avatar = search_result.getBlob("avatar");
            email = search_result.getString("email");           
        }
        if (q.length() > 0 ) {
            buffer = "<div class='half_div'><div id='upperprof'><img id='mainpp' width='225' src='"+avatar+"' alt=''><div id='namauser'>"+fullname+"</div></div> <br/><br/></div>";
            buffer = buffer + "<div class='half_div'> <div class='user_search_result_label'> Username  </div> <div class='left'> "+username+"</div> <div class='clear'></div> <br>";
            buffer = buffer + "<div class='user_search_result_label'> Email </div><div class='left'> "+email+"</div><div class='clear'></div><br>";	
            buffer = buffer + "<div class='user_search_result_label'>  Birthdate  </div> <div class='left'> "+birthdate+"</div><div class='clear'></div></div>";
        }
    }
    else
    if (type.equals("category")) {
        List<String> taskList = new ArrayList<String>();
        String categoryID = "";
        String categoryName = "";
        String categoryCreator = "";
        
        //Search Category
        stmt_searchresult = con.prepareStatement("SELECT * FROM category WHERE cat_id=?");
        stmt_searchresult.setString(1, q);
        search_result = stmt_searchresult.executeQuery();
        search_result.beforeFirst();
        while (search_result.next()) {
            categoryID = search_result.getString("cat_id");
            categoryName = search_result.getString("cat_name");
            categoryCreator = search_result.getString("cat_creator");
        }
        
        //Search corresponding task
        stmt_searchresult = con.prepareStatement("SELECT task_name, task_id FROM task WHERE cat_name=?");
        stmt_searchresult.setString(1, categoryName);
        search_result = stmt_searchresult.executeQuery();
        search_result.beforeFirst();
        while (search_result.next()) {
            taskList.add(search_result.getString("task_name"));
        }
        
        if (q.length() > 0 ) {
            buffer = "<br><br><div class='task_view'>" +
                    "<div class='cat_search_result_label'> Category Name  </div> <div class='left'>"+categoryName+"</div>" +
                    "<div class='clear'></div><br>" +
                    "<div class='cat_search_result_label'> Creator  </div> <div class='left'>" +categoryCreator +"</div>" +
                    "<div class='clear'></div><br>" +
                    "<div class='cat_search_result_label'> List of Task </div>";
            for (int i=0; i<taskList.size(); i++) {
                if (i!=0) {
                    buffer += "<div class='cat_search_result_label'> &nbsp; </div>";
                }
                buffer = buffer + "<div class='left'>" + taskList.get(i) +"</div><div class='clear'></div>";
            }
            buffer += "</div>";
        }                
    }
    else
    if (type.equals("task")) {
        
    }
    
    response.getWriter().println(buffer); 
%>