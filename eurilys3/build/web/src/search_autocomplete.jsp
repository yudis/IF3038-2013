<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" import ="java.sql.*" %>  
<%  
    String hint     = request.getParameter("hint");  
    String filter   = request.getParameter("filter");
    String buffer   = "";
    
    List<String> nameList   = new ArrayList<String>();
    List<String> idList     = new ArrayList<String>();
    List<String> typeList   = new ArrayList<String>();
    
    ResultSet rs_search = null;
    
    try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Berhasil connect ke Mysql JDBC Driver ... ");
    } catch (ClassNotFoundException ex) {
        System.out.println("Where is your MySQL JDBC Driver?");
    }
    Connection conn_search = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
    PreparedStatement stmt_searchAutocomplete = conn_search.prepareStatement("");
    
    if (filter.equals("1")) { //Search All
        //search user
        String query = "SELECT distinct full_name, username FROM user WHERE username LIKE ? OR email LIKE ? OR full_name LIKE ? OR birthdate LIKE ?";
        stmt_searchAutocomplete = conn_search.prepareStatement(query);
        stmt_searchAutocomplete.setString(1, "%" + hint + "%"); //username
        stmt_searchAutocomplete.setString(2, "%" + hint + "%"); //email
        stmt_searchAutocomplete.setString(3, "%" + hint + "%"); //full_name
        stmt_searchAutocomplete.setString(4, "%" + hint + "%"); //birthdate        
        rs_search = stmt_searchAutocomplete.executeQuery();
        rs_search.beforeFirst();
        while (rs_search.next()) {
            System.out.print(".... yeah ");
            nameList.add(rs_search.getString("full_name"));
            idList.add(rs_search.getString("username"));
            typeList.add("user");
            System.out.println("Search user : " + rs_search.getString("full_name"));
        }
        
        //search category
        stmt_searchAutocomplete = conn_search.prepareStatement("SELECT distinct cat_id, cat_name FROM category WHERE cat_name LIKE ?;");
        stmt_searchAutocomplete.setString(1, "%" + hint + "%"); //category_name
        rs_search = stmt_searchAutocomplete.executeQuery();
        rs_search.beforeFirst();
        while (rs_search.next()) {
            nameList.add(rs_search.getString("cat_name"));
            idList.add(rs_search.getString("cat_id"));
            typeList.add("category");
        }
        
        //search task
        stmt_searchAutocomplete = conn_search.prepareStatement("SELECT DISTINCT task_name, task.task_id FROM ((task LEFT JOIN tag ON task.task_id = tag.task_id) LEFT JOIN comment ON task.task_id = comment.task_id) WHERE task_name LIKE ? OR tag_name LIKE ? OR comment_content LIKE ?;");
        stmt_searchAutocomplete.setString(1, "%" + hint + "%"); //task_name
        stmt_searchAutocomplete.setString(2, "%" + hint + "%"); //tag_name
        stmt_searchAutocomplete.setString(3, "%" + hint + "%"); //comment_content
        rs_search = stmt_searchAutocomplete.executeQuery();
        rs_search.beforeFirst();
        while (rs_search.next()) {
            nameList.add(rs_search.getString("task_name"));
            idList.add(rs_search.getString("task_id"));
            typeList.add("task");
        }
    }
    else if (filter.equals("2")) { //Search User (username, email, nama lengkap, birthdate)
        stmt_searchAutocomplete = conn_search.prepareStatement("SELECT distinct full_name, username FROM user WHERE username LIKE ? OR email LIKE ? OR full_name LIKE ? OR birthdate LIKE ?;");
        stmt_searchAutocomplete.setString(1, "%" + hint + "%"); //username
        stmt_searchAutocomplete.setString(2, "%" + hint + "%"); //email
        stmt_searchAutocomplete.setString(3, "%" + hint + "%"); //full_name
        stmt_searchAutocomplete.setString(4, "%" + hint + "%"); //birthdate        
        rs_search = stmt_searchAutocomplete.executeQuery();
        rs_search.beforeFirst();
        while (rs_search.next()) {
            nameList.add(rs_search.getString("full_name"));
            idList.add(rs_search.getString("username"));
            typeList.add("user");
        }
    }
    else if (filter.equals("3")) { //Search Category
        stmt_searchAutocomplete = conn_search.prepareStatement("SELECT distinct cat_id, cat_name FROM category WHERE cat_name LIKE ?;");
        stmt_searchAutocomplete.setString(1, "%" + hint + "%"); //category_name
        rs_search = stmt_searchAutocomplete.executeQuery();
        rs_search.beforeFirst();
        while (rs_search.next()) {
            nameList.add(rs_search.getString("cat_name"));
            idList.add(rs_search.getString("cat_id"));
            typeList.add("category");
        }
    }
    else if (filter.equals("4")) { //Search Task (task name, tag, comment)
        stmt_searchAutocomplete = conn_search.prepareStatement("SELECT DISTINCT task_name, task.task_id FROM ((task LEFT JOIN tag ON task.task_id = tag.task_id) LEFT JOIN comment ON task.task_id = comment.task_id) WHERE task_name LIKE ? OR tag_name LIKE ? OR comment_content LIKE ?");
        stmt_searchAutocomplete.setString(1, "%" + hint + "%"); //task_name
        stmt_searchAutocomplete.setString(2, "%" + hint + "%"); //tag_name
        stmt_searchAutocomplete.setString(3, "%" + hint + "%"); //comment_content
        rs_search = stmt_searchAutocomplete.executeQuery();
        rs_search.beforeFirst();
        while (rs_search.next()) {
            nameList.add(rs_search.getString("task_name"));
            idList.add(rs_search.getString("task_id"));
            typeList.add("task");
        }
    }
    
    if (hint.length() > 0) {
        System.out.println("name list size : " + nameList.size());
        for (int i=0; i<nameList.size(); i++) {
            if (buffer.equals("")) {
                buffer = "<div class='search_recommend' onclick=\"javascript:searchResult('"+idList.get(i)+"' ,'"+typeList.get(i)+"');\">"+nameList.get(i)+"</div>";
            }
            else {
                buffer = buffer + "<br> <div class='search_recommend' onclick=\"javascript:searchResult('"+idList.get(i)+"' ,'"+typeList.get(i)+"');\">"+nameList.get(i)+"</div>";
            }
        }
    }
    
    if (buffer.equals("")) {
        buffer = "no suggestion";
    }
    response.getWriter().println(buffer);  
%>