<%@ page language="java" import="database.MyDatabase,java.sql.ResultSet;" errorPage="" %>

<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    String tQuery = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "';";
    try {
        ResultSet tResult = MyDatabase.getSingleton().selectDB(tQuery);
        if (tResult.next()) {
            session.setMaxInactiveInterval(2592000);
            session.setAttribute("isActive",true);
            session.setAttribute("sUsername", tResult.getString("username"));
            session.setAttribute("sAvatar", tResult.getString("avatar"));
            session.setAttribute("sNama", tResult.getString("fullname"));
            response.sendRedirect("dashboard.jsp");
        } else {
            String message = "No user or password matched";
            response.sendRedirect("index.jsp?error=" + message);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

%>
