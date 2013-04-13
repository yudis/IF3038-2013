<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%
    //berhasil 

    String usernamelama="gaby3";
    String usernamebaru="gaby4";
    String avatar="2.jpg";
    String password="gaby4";
    String fullname="Gabrielle4";
    String email="gaby@yot.com";
    ResultSet rs = null;
    
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
   
    String QueryStr = "UPDATE pengguna SET username='"+usernamebaru+"',avatar='"+avatar+"',password='"+password+"',full_name='"+fullname+"',email='"+email+"' WHERE username='"+usernamelama+"'";
    statement.executeUpdate(QueryStr);
    
    
    } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
    }
    
     
%>