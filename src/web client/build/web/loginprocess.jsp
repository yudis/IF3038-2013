<%@page import="org.json.JSONArray"%>
<%@ page language="java" import="database.MyDatabase,java.sql.ResultSet;" errorPage="" %>

<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String tQuery = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "';";
    try {
        JSONArray tResult = MyDatabase.getSingleton().selectDBREST(tQuery);
        out.println(tResult.toString());
        if (!tResult.isNull(0)) {
            session.setMaxInactiveInterval(2592000);
            session.setAttribute("isActive",true);
            session.setAttribute("sUsername", tResult.getJSONObject(0).getString("username"));
            session.setAttribute("sAvatar", tResult.getJSONObject(0).getString("avatar"));
            session.setAttribute("sNama", tResult.getJSONObject(0).getString("fullname"));
            response.sendRedirect("dashboard.jsp");
        } else {
            
            String message = "No user or password matched";
            response.sendRedirect("index.jsp?error=" + message);
        }
    } catch (Exception e) {
        e.printStackTrace();
        String message = "No user or password matched";
        response.sendRedirect("index.jsp?error=" + message);
    }

%>
