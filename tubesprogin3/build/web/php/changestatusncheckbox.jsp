<%--<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$value = $_GET["t"];
$taskname = $_GET["nama"];

   $con=mysqli_connect("localhost","progin","progin","progin_405_13510060");
    if (mysqli_connect_errno($con))
        {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
 // echo "udah ampe sini";
     $sql = "UPDATE task SET task_status='$value',checkbox='$value' WHERE task_name='$taskname'";
  
?>--%>

<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%
   // String student = request.getParameter("searchStudent"); 
   // int value=1; //udah bener ini
   // String taskname="Pokemon"; //correct
    int value;
    String taskname;
  
   try{
    ResultSet rs = null;
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
    
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    String QueryStr = "UPDATE task SET task_status='"+1+"',checkbox='"+1+"' WHERE task_name='Pokemon'";
    statement.executeUpdate(QueryStr);
    
   
   } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
   }

   
%>


<%--
ResultSet result =getallkategori();
 while(result.next()){
        out.println(result.getString("cat_name")+"<br>");
    }
--%>

