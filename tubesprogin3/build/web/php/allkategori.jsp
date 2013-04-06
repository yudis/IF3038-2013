<%--<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 $con=mysqli_connect("localhost","progin","progin","progin_405_13510060");
 if (mysqli_connect_errno($con))
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
 // ec $sql = "SELEho "udah ampe sini";  
  
  $sql = "SELECT * FROM category ";
  //echo $sql;
  if (!mysqli_query($con,$sql))
  {
  die('Error: ' . mysqli_error());
  }
  $result  = mysqli_query($con,$sql);
 
    $res = array();
    $i=0;
   // echo "<html><body>";
    
    
   while($row = mysqli_fetch_array($result))  
  {
    //echo "row ".$i;
   // print_r($row);
    array_push($res,$row);
     //echo "<br/>";
    $i++;
  }
  //print_r($res[0]['ID_TASK']);
  //echo"</body></html>";
  echo json_encode($res);
?>--%>


<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="net.sf.json.JSONException"%>
<%@include file="../php/rstojson.jsp"%>
<%
   // String student = request.getParameter("searchStudent"); 
   // int value=1; //udah bener ini
   // String taskname="Pokemon"; //correct
  
   ResultSet rs = null;
   try{
    
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
    
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    String QueryStr = "SELECT * FROM category";
    rs = statement.executeQuery(QueryStr);
    
   
   } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
   }
   //out.write(rs);
   //return rs;
   try{
    JSONArray jsonarr = rstojson(rs);
    out.println(jsonarr.toString());
       }catch(Exception ex){
           
       }
    //return jsonarr;
  
        
   
   
%>

