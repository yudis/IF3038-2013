

<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%
    //berhasil 
    String cattask="PTIB";
    String taskname="tucil60";
    String taskdeadline="2012-03-04";
    String tasktag="c++";
    String assignee="gaby";
    String file="aku.pdf";
    ResultSet rs = null;
    
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
   
    String QueryStr = "INSERT INTO task (cat_task_name,task_name,task_deadline,task_tag_multivalue,task_status,checkbox,assignee_name,file) VALUES ('"+cattask+"','"+taskname+"','"+taskdeadline+"','"+tasktag+"',0,0,'"+assignee+"','"+file+"')";
    statement.executeUpdate(QueryStr);
    
    
    } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
    }
    
     
%>