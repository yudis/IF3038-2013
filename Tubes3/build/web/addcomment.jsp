<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%
    String userID = request.getParameter("user");

    String comment = request.getParameter("comment");
    int taskID = Integer.parseInt(request.getParameter("task"));
    Connection conn = null;

    try {
        //make a connection
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
        
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO `comment` (`IDComment`, `IDTask`, `Username`, `Content`, `Timestamp`)"
                + "VALUES (NULL, ?, ?, ?, CURRENT_TIMESTAMP);");
        ps.setInt(1, taskID);
        ps.setString(2, userID);
        ps.setString(3, comment);
        ps.executeUpdate();

        ps = conn.prepareStatement(
                "SELECT * FROM comment WHERE IDTask=? and Content=?;");
        ps.setInt(1, taskID);
        ps.setString(2, comment);
        ResultSet rs = ps.executeQuery();
    } catch (SQLException e) {
        System.out.println("Connection Failed! Check output console");
    } finally {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Can not close connection");
        }
    }
%>