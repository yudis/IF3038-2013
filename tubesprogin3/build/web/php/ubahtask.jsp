<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%
    //berhasil 

    String tasknamelama="apake";
    String tasknamebaru="apakelagi";
    String attachment="aku.pdf";
    String deadline="2012-05-06";
    String assign="gaby";
    String tag="lla";
    ResultSet rs = null;
    
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
   
    String QueryStr = "UPDATE task SET task_name='"+tasknamebaru+"',task_deadline='"+deadline+"',task_tag_multivalue='"+tag+"',assignee_name='"+assign+"',file='"+attachment+"' WHERE task_name='"+tasknamelama+"'";
    statement.executeUpdate(QueryStr);
    
    
    } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
    }
    
     
%>