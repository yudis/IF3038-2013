<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>

<%
    String username = (String) session.getAttribute("username");
    String categoryName = "";
    String categoryId = "";
    Connection conn = null;
    Connection conn2 = null;
    
    /* Search Category by Creator */
    try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Berhasil connect ke Mysql JDBC Driver - loadCategory.jsp ");
    } catch (ClassNotFoundException ex) {
        System.out.println("Where is your MySQL JDBC Driver? - loadCategory.jsp");
    }
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
    
    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM category WHERE cat_creator=?;");
    stmt.setString(1, username);
    ResultSet rs = stmt.executeQuery();

    //Result set is not empty
    rs.beforeFirst();
    while (rs.next()) {
        categoryName    = rs.getString("cat_name"); 
        //categoryId      = rs.getString("cat_id"); %>
        <li> <span class='categoryList' onclick="javascript:generateTask('<%= categoryName %>');"> <%= categoryName %> </span> </li> <%
    }
    conn.close();
    
    /* Search Category by Assignee */
    try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Berhasil connect ke Mysql JDBC Driver ... ");
    } catch (ClassNotFoundException ex) {
        System.out.println("Where is your MySQL JDBC Driver?");
    }
    conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
    
    PreparedStatement stmt2 = conn2.prepareStatement("SELECT cat_id FROM cat_asignee WHERE username =?;");
    stmt2.setString(1, username);
    ResultSet rs2 = stmt2.executeQuery();
    rs2.beforeFirst();
    while (rs2.next()) {
        categoryId = rs2.getString("cat_id"); 
        System.out.println("cat id : " + categoryId);
        PreparedStatement stmt3 = conn2.prepareStatement("SELECT * FROM category WHERE cat_id =?;");
        stmt3.setString(1, categoryId);
        ResultSet rs3 = stmt3.executeQuery();
        rs3.beforeFirst();
        while (rs3.next()) { 
            categoryName = rs3.getString("cat_name");
            %>
            <li> 
                <span class='categoryList' onclick="javascript:generateTask('<%= categoryName %>');"> <%= categoryName %> </span> 
            </li>                
        <%}
    }
    conn2.close();
%>