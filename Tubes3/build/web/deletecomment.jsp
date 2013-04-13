<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%
    int IDComment = Integer.parseInt(request.getParameter("IDComment"));
    Connection conn = null;

    try {
        //make a connection
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
        
        PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM comment WHERE IDComment=?;");
        ps.setInt(1, IDComment);
        ps.executeUpdate();

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