
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%@ page import="java.text.*" %> 
<%!
//BELOM SELESE
public ResultSet getalltaskuserfinish(String namauser){
     ResultSet rs = null;
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    String QueryStr = "SELECT * FROM task";
    rs = statement.executeQuery(QueryStr);
   
   
   } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
   }

    return rs;
     }

%>