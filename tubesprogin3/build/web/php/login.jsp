<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%
    //berhasil, ada session jangan lupa dimasukkin
    String uname="gaby";
    String passw="gaby";
    ResultSet rs = null;
    
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    String QueryStr = "SELECT * FROM pengguna WHERE username='"+uname+"' AND password ='"+passw+"'";
    
 
    rs = statement.executeQuery(QueryStr);
    
   
    } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
    }
     while(rs.next()){
        out.println(rs.getString("full_name")+rs.getString("avatar")+"<br>");
    }
     
%>


