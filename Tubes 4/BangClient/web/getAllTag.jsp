<%-- 
    Document   : getAllTag
    Created on : Apr 13, 2013, 12:56:37 AM
    Author     : dell
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>

<%
    String q = request.getParameter("q");
    
    URL url = new URL("http://localhost:8080/BangServer/Bang/getAllTag/" + q);
    
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");
    
    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
    StringBuilder output = new StringBuilder();
    String test;
    while ( (test = br.readLine()) != null)
    {
       output.append(test);
    }
    
    ArrayList<String> tags = new ArrayList<String>();
    
    JSONObject result = new JSONObject(output.toString());
    for (int i = 0; i < result.length(); i++) {
        tags.add(result.getString("Tag " + Integer.toString(i)));
    }

    out.write("<xml>");
    
    for (int i = 0; i < tags.size(); i++) {
        String temp = tags.get(i);
        out.write("<Data>");
        out.write("<ID>");
        out.write(temp);
        out.write("</ID>");
        out.write("<String>");
        out.write(temp);
        out.write("</String>");
        out.write("</Data>");
    }

    out.write("</xml>");
%>
