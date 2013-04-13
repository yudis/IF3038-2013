<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="net.sf.json.JSONException"%>
<%@include file="../php/rstojson.jsp"%>
<%
    
    ResultSet rs = null;
   try{
     String namacat=request.getParameter("namakategori");
     String namatask=request.getParameter("namatask");
       //String namacat="PROGIN";
     //String namatask="PROGIN4";
    String connectionURL ="jdbc:mysql://localhost:3306/progin_405_13510060";
    Connection connection = null;
    Statement statement= null;
    
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    connection = DriverManager.getConnection(connectionURL,"progin","progin");
    statement = connection.createStatement();
    String QueryStr = "SELECT comm_content,user_comment FROM comment WHERE comment_cat_name='"+namacat+"' AND comment_task_name='"+namatask+"'";
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