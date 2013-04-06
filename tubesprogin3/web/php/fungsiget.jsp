
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%!

public ResultSet getallkategori(){
     ResultSet rs = null;
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    String QueryStr = "SELECT cat_name FROM category";
    rs = statement.executeQuery(QueryStr);
   
   
   } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
   }

    return rs;
     }  
%>


<%
ResultSet result =getallkategori();
 while(result.next()){
        out.println(result.getString("cat_name")+"<br>");
    }
%>