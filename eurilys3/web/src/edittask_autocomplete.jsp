<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" import ="java.sql.*" %>  
<%  
    String hint     = request.getParameter("hint");  
    String buffer   = "";
    
    List<String> nameList   = new ArrayList<String>();
    List<String> idList     = new ArrayList<String>();
    
    ResultSet rs_autoAssignee = null;
    
    try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Berhasil connect ke Mysql JDBC Driver - edittask_autocomplete.jsp ");
    } catch (ClassNotFoundException ex) {
        System.out.println("Where is your MySQL JDBC Driver? - edittask_autocomplete.jsp");
    }
    Connection conn_search = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
    PreparedStatement stmt_autoAssignee = conn_search.prepareStatement("");
    
    stmt_autoAssignee = conn_search.prepareStatement("SELECT distinct full_name, username FROM user WHERE username LIKE ?;");
    stmt_autoAssignee.setString(1, "%" + hint + "%"); //username
        
    rs_autoAssignee = stmt_autoAssignee.executeQuery();
    rs_autoAssignee.beforeFirst();
    while (rs_autoAssignee.next()) {
        //nameList.add(rs_autoAssignee.getString("full_name"));
        idList.add(rs_autoAssignee.getString("username"));
    }
    
    if (hint.length() > 0) {
        for (int i=0; i<idList.size(); i++) {
            if (buffer.equals("")) {
                buffer = "<div class='search_recommend' id='edittask_ass_"+idList.get(i)+"' onclick=\"javascript:addEditTaskAssigne('"+idList.get(i)+"');\">"+idList.get(i)+"</div>";
            }
            else {
                buffer = buffer + "<br> <div class='search_recommend' id='edittask_ass_"+idList.get(i)+"' onclick=\"javascript:addEditTaskAssigne('"+idList.get(i)+"');\">"+idList.get(i)+"</div>";
            }
        }
    }
    
    if (buffer.equals("")) {
        buffer = "no suggestion";
    }
    response.getWriter().println(buffer);  
%>