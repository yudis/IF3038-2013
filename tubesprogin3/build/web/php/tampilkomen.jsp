<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%!

public ResultSet getkomen(String namakategori,String namatask){
     ResultSet rs = null;
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    String QueryStr = "SELECT comm_content FROM comment WHERE comment_cat_name='"+namakategori+"' AND comment_task_name='"+namatask+"'";
    rs = statement.executeQuery(QueryStr);
   
   
   } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
   }

    return rs;
     }  
%>
