
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%
    //berhasil
    String newcomment=request.getParameter("komen");
    String kategori=request.getParameter("namakategori");
    String task=request.getParameter("namatask");
    String activeuser = "gaby";
    /*String newcomment = "hmmm ga dikerjain lagi";
    String kategori ="WKWK";
    String task ="PROGIN4";*/
    ResultSet rs = null;
    
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    out.println("haloo");
    //String QueryStr = "UPDATE comment SET comm_content='"+newcomment+"',user_comment='"++"'"
    String QueryStr = "INSERT INTO comment (comm_content,user_comment,comment_cat_name,comment_task_name) VALUES ('"+newcomment+"','"+activeuser+"','"+kategori+"','"+task+"')";
    out.println("haloo3");
    statement.executeUpdate(QueryStr);
    out.println("haloo2");
   
    } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
    }
    

     
%>
