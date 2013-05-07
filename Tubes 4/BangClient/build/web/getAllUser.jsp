<%-- 
    Document   : getAllUser
    Created on : Apr 12, 2013, 4:27:52 PM
    Author     : Nicholas Rio
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="Model.User"%>

<%
    String q = request.getParameter("q");
    
    URL url = new URL("http://localhost:8080/BangServer/Bang/getAllUser/" + q);
    
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
    
    ArrayList<User> users = new ArrayList<User>();
    
    JSONObject result = new JSONObject(output.toString());
    for (int i = 0; i < result.length(); i++) {
        users.add(new User(result.getJSONObject("User " + Integer.toString(i))));
    }
    
    out.write("<xml>");

    String username;
    String fullname;
    
    for (int i = 0; i < users.size(); i++) {
        username = users.get(i).getUsername();
        fullname = users.get(i).getFullname();
        out.write("<Data>");
        out.write("<ID>");
        out.write(username);
        out.write("</ID>");
        out.write("<String>");
        out.write(fullname);
        out.write("</String>");
        out.write("</Data>");
    }

    out.write("</xml>");
%>
