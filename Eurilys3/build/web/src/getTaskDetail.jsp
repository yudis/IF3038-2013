<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%
   Connection conn = null;
   String taskname, status, attachment, deadline, creator, category;
   taskname = status = attachment = deadline = creator = category = "";
   ArrayList<String> tag = new ArrayList<String>();
   ArrayList<String> assignee = new ArrayList<String>();
   
   try {
      try {
         Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException ex) {
         System.out.println("JDCB driver not found");
      }
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510010", "root", "");

      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM task WHERE idtask=?");
      stmt.setString(1, request.getParameter("idtask"));

      ResultSet result = stmt.executeQuery();
      
      if (result.next()) {
         taskname = result.getString("taskname");
         status = result.getString("status");
         attachment = result.getString("attachment");
         deadline = result.getString("deadline");
         creator = result.getString("creator");
         category = result.getString("idcat");
      }
      
      stmt = conn.prepareStatement("SELECT name FROM tag WHERE idtask=?");
      stmt.setString(1, request.getParameter("idtask"));
      result = stmt.executeQuery();
      
      while (result.next()){
        tag.add(result.getString(1));
      }
      
      stmt = conn.prepareStatement("SELECT username FROM do WHERE idtask=?");
      stmt.setString(1, request.getParameter("idtask"));
      result = stmt.executeQuery();
      
      while (result.next()){
         assignee.add(result.getString(1));
      }
      
      stmt = conn.prepareStatement("SELECT catname FROM category WHERE idcat=?");
      stmt.setString(1, category);
      result = stmt.executeQuery();
      
      if (result.next()){
         category = result.getString(1);
      }
      
   } catch (SQLException e) {
      System.out.println("Connection to database failed");
      System.out.println(e.getMessage());
   } finally {
      try {
         conn.close();
      } catch (SQLException ex) {
         System.out.println("Connection can not be closed");
      }
   }
%>