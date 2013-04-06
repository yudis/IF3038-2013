<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%
    //berhasil
    String kategori="PTIA";
    String namaasign = "Budi";
    ResultSet rs = null;
    
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    String QueryStr = "INSERT INTO authorized (cat_name,user_name) VALUES ('"+kategori+"','"+namaasign+"')";
    statement.executeUpdate(QueryStr);
   
    
    } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
    }
   

     
%>