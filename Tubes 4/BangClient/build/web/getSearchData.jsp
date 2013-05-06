<%-- 
    Document   : getSearchData
    Created on : Apr 12, 2013, 9:40:46 PM
    Author     : Nicholas Rio
--%>

<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="Utility.DBUtil" %>

<%
    String q = request.getParameter("q").toString();
    String opt = request.getParameter("opt").toString();
    String usr = request.getParameter("usr").toString();
    System.out.println(usr);
    
    URL url = new URL("http://localhost:8080/BangServer/header/"+q+"/"+opt+"/"+usr);
    //URL url = new URL("http://progin4.ap01.aws.af.cm/header/"+q+"/"+opt+"/"+usr);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");

    if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
    }
    
    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
    StringBuilder output = new StringBuilder();
    String test;
    while ( (test = br.readLine()) != null)
    {
       output.append(test);
    }
    
    out.write(output.toString());
%>
