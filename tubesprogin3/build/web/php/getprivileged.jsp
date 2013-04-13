<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="net.sf.json.JSONException"%>
<%@include file="../php/rstojson.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //berhasil
    //int lastidx=1;
    
    ResultSet rs = null;
    String kategori=request.getParameter("namakategori");
    String task=request.getParameter("namatask");
    String user=request.getParameter("namauser");
    //String lastidx="5";
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    if(task.equals("")){
        //lagi di mode tanpa task
        String QueryStr = "SELECT aut_privileged FROM authorized WHERE user_name='"+namauser+"' AND aut_cat_name='"+namakategori+"'";
    }else{
        String QueryStr = "SELECT aut_privileged FROM authorized WHERE user_name='"+namauser+"' AND aut_task_name='"+namatask+"'";
    }
    
    rs = statement.executeQuery(QueryStr);
   
   
    } catch(Exception e){
       System.out.println("Exception pas connect ");
       e.printStackTrace();
       //out.println("Unable to connect to database "+e.getMessage());
    }
    try{
    JSONArray jsonarr = rstojson(rs);
    out.println(jsonarr.toString());
       }catch(Exception ex){
           
       }
    

     
%>
