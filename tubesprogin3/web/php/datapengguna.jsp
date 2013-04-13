<%--<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function datapengguna($namauser){
  
 $con=mysqli_connect("localhost","progin","progin","progin_405_13510060");
    if (mysqli_connect_errno($con))
        {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
 // echo "udah ampe sini";
     $sql = "SELECT * FROM pengguna WHERE username='$namauser'";
    if (!mysqli_query($con,$sql))
        {
             die('Error: ' . mysqli_error());
        }
    $result = mysqli_query($con, $sql);
    $res = array();
    $i = 0;
        // echo "<html><body>";


    while ($row = mysqli_fetch_array($result)) {
            //echo "row ".$i;
            // print_r($row);
            array_push($res, $row);
            //echo "<br/>";
            $i++;
    }
       /* foreach ($res as $row) {
            echo($row['KATEGORI_TASK']);
        }*/
    
   //print_r($res);
   return $res;  
   
}
   
?>--%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%!

public ResultSet datapengguna(String namauser){
    
     ResultSet rs = null;
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    String QueryStr = "SELECT * FROM pengguna WHERE username='"+namauser+"'";
    rs = statement.executeQuery(QueryStr);
   
   
   } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
   }

    return rs;
     }  
%>


<%
ResultSet result =datapengguna("gaby");
 while(result.next()){
        out.println(result.getString("full_name")+"<br>");
    }
%>