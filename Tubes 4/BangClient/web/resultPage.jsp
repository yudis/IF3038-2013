<%-- 
    Document   : resultPage
    Created on : Apr 12, 2013, 11:10:47 PM
    Author     : Nicholas Rio
--%>

<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Utility.DBUtil"%>
<%
    String username = "";
    if(session.getAttribute("username")!=null){
        username = session.getAttribute("username").toString();
    }else{
        response.sendRedirect("index.jsp");
    }
%>

<!DOCTYPE html>
<!DOCTYPE html>
<html>

    <head>
        <title>BANG! - Dashboard</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS Files/resultpage.css" media="screen" />
        <jsp:include page="header.jsp" />
    </head>
    <body>
        <%
            String opt = request.getParameter("options");
            String box = request.getParameter("Search");
        %>
        <div id = "wall">
            <div id="category_wall">
                <%
                    out.write("<div class='resulttitle'>Category Result</div>");        
                    if (opt.equalsIgnoreCase("c") || opt.equalsIgnoreCase("a")) {
                        //URL url = new URL("http://localhost:8080/BangServer/header/cat/"+box);
                        URL url = new URL("http://progin4.ap01.aws.af.cm/header/cat/"+box);
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
                        JSONArray result = new JSONArray(output.toString());
                        
                        for (int i = 0; i < result.length(); i++)
                        {
                            out.write("<div class='resultelm'>");
                            out.write(result.getJSONObject(i).getString("id")+"<br/>");
                            out.write(result.getJSONObject(i).getString("name")+"<br/>");
                            out.write("</div>");
                        }
                    }
                %>
            </div>
            <div id="user_wall">
                <%
                    out.write("<div class='resulttitle'>User Result</div>");
                    if (opt.equalsIgnoreCase("u") || opt.equalsIgnoreCase("a")) {
                        //URL url = new URL("http://localhost:8080/BangServer/header/usr/"+box);
                        URL url = new URL("http://progin4.ap01.aws.af.cm/header/usr/"+box);
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
                        JSONArray result = new JSONArray(output.toString());
                        
                        for (int i = 0; i < result.length(); i++)
                        {   
                            out.write("<div class='resultelm'>");
                            out.write("<a href='profile.jsp?userprofile="+result.getJSONObject(i).getString("username")+"'>");
                            out.write(result.getJSONObject(i).getString("username")+"<br/>");
                            out.write("</a>");
                            out.write(result.getJSONObject(i).getString("fullname")+"<br/>");
                            out.write("<img src='" + result.getJSONObject(i).getString("avatar") + "'></img>"+"<br/>");
                            out.write("</div>");
                        }
                    }
                %>
            </div>
            <div id="task_wall">
                <%
                    out.write("<div class='resulttitle'>Task Result</div>");
                    
                    //URL url = new URL("http://localhost:8080/BangServer/header/tsk/"+box+"/"+username);
                    URL url = new URL("http://progin4.ap01.aws.af.cm/header/tsk/"+box+"/"+username);
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
                    JSONArray result = new JSONArray(output.toString());

                    for (int i = 0; i < result.length(); i++)
                    {
                        out.write("<div class='resultelm'>");
                        out.write(result.getJSONObject(i).getString("id")+"<br/>");
                        out.write(result.getJSONObject(i).getString("name")+"<br/>");
                        out.write("</div>");
                        
                        String id = result.getJSONObject(i).getString("id_task");
                        out.write("<div class='resultelm'>");
                        out.write("<a href='dashboard.jsp?seektask="+result.getJSONObject(i).getString("id_task")+"'>");
                        out.write(result.getJSONObject(i).getString("id_task")+"<br/>");
                        out.write("</a>");
                        out.write(result.getJSONObject(i).getString("name")+"<br/>");
                        out.write(result.getJSONObject(i).getString("deadline")+"<br/>");
                        if(result.getJSONObject(i).getString("status").equalsIgnoreCase("T")){
                            out.write("<input type='checkbox' name='task"+id+"' id='task"+id+"'onclick='changeTaskStatus("+id+",this.checked)' checked='checked'/>"+"<br/>");
                        }else{
                            out.write("<input type='checkbox' name='task"+id+"' id='task"+id+"'onclick='changeTaskStatus("+id+",this.checked)'/>"+"<br/>");
                        }
                        out.write("</div>");
                    }
                %>
            </div>
        </div>
    </body>
</html>
