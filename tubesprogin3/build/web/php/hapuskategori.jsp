<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%
    //berhasil
    //String kategori="OOP9";
    
    String kategori=request.getParameter("kategori");
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
    String QueryStr = "DELETE FROM category where cat_name='"+kategori+"'";
    String QueryStr2 = "DELETE FROM task where cat_task_name='"+kategori+"'";
    statement.executeUpdate(QueryStr);
    statement.executeUpdate(QueryStr2);   
   
    } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
    }
    
     
%>

