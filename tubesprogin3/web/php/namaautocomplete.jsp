
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="net.sf.json.JSONException"%>
<%@include file="../php/rstojson.jsp"%>
<%
//BERHASIL
   // String namatask="apake";
    String user=request.getParameter("key");

    //String namatask="PROGIN1";
    ResultSet rs = null;
   try{
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    String QueryStr = "SELECT username FROM pengguna WHERE username LIKE '%"+user+"%'";
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


