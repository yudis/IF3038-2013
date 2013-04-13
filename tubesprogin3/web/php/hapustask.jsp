<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%
   //berhasil
    //String kategori="OOP9";
    
    String kategori=request.getParameter("namakategori");
    String namatask=request.getParameter("task");
    //out.println(kategori);
    ResultSet rs = null;
    ResultSet rs2= null;
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    String QueryStr1 = "DELETE FROM task where task_name='"+namatask+"' AND cat_task_name='"+kategori+"'";
    String QueryStr2 = "DELETE FROM comment where comment_task_name='"+namatask+"' AND comment_cat_name='"+kategori+"'";
    String QueryStr3 = "DELETE FROM authorized where aut_task_name='"+namatask+"' AND aut_cat_name='"+kategori+"'";
    
    statement.executeUpdate(QueryStr1);
    statement.executeUpdate(QueryStr2);
    statement.executeUpdate(QueryStr3);
    
    //statement.executeUpdate(QueryStr2);   
   
    } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
    }
    
     
    
     
%>

