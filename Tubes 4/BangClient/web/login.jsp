<%-- 
    Document   : login
    Created on : Apr 9, 2013, 11:33:16 PM
    Author     : Nicholas Rio
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.security.MessageDigest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String password = request.getParameter("p").toString();
    String username = request.getParameter("u").toString();
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(password.getBytes());
    byte[] digest = md.digest();
    StringBuffer hexString = new StringBuffer();
    for(int i=0;i<digest.length;i++){
        password = Integer.toHexString(0xFF & digest[i]);
        if(password.length()<2){
            password = "0" + password;
        }
        hexString.append(password);
    }
    password = hexString.toString();
    
    URL url = new URL("http://localhost:8080/BangServer/user/login/" + username);
    //URL url = new URL("http://progin4.ap01.aws.af.cm/user/login/" + username);
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
    
    JSONObject result = new JSONObject(output.toString());
    if(password.equals(result.getString("password"))){
        session.setAttribute("username", result.getString("username"));
        session.setAttribute("fullname", result.getString("fullname"));
        session.setAttribute("avatar", result.getString("avatar"));
        session.setAttribute("dob", result.getString("dob"));
        session.setAttribute("email", result.getString("email"));
        out.write("1");
    }else{
        out.write("0");
    }
%>
