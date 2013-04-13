
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%
    //berhasil
    String namakategori="PTIB";
    ResultSet rs = null;
    
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
   
    String QueryStr = "INSERT INTO category (cat_name) VALUES ('"+namakategori+"')";
    String QueryStr2 = "SELECT * FROM category where (cat_name='"+namakategori+"')";
    statement.executeUpdate(QueryStr);
    rs=statement.executeQuery(QueryStr2);
    
    } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
    }
     while(rs.next()){
        out.println(rs.getString("cat_ID")+rs.getString("cat_name")+"<br>");
    }

     
%>
