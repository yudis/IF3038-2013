<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONTokener"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" import ="java.sql.*" %>  
<%  
    String q     = request.getParameter("q");  
    String type     = request.getParameter("type");
    String buffer   = "";
    
    ResultSet search_result = null;
    
    if (type.equals("user")) {
        URL userDetailURL = new URL("http://localhost:8084/eurilys4/user/user_detail?username=" + q);
        //URL userDetailURL = new URL("http://eurilys.ap01.aws.af.cm/user/user_detail?username=" + q);
        HttpURLConnection userDetailConn = (HttpURLConnection) userDetailURL.openConnection();
        userDetailConn.setRequestMethod("GET");
        userDetailConn.setRequestProperty("Accept", "application/json");
        if (userDetailConn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + userDetailConn.getResponseCode());
        }
        BufferedReader userDetailBr = new BufferedReader(new InputStreamReader((userDetailConn.getInputStream())));
        String userDetailOutput;
        String userDetailJSONObject = "";
        while ((userDetailOutput = userDetailBr.readLine()) != null) {
            userDetailJSONObject += userDetailOutput;
        } 
        userDetailConn.disconnect();

        //Parse userDetailJSONObject 
        JSONTokener userDetailTokener = new JSONTokener(userDetailJSONObject);
        JSONObject userDetailroot = new JSONObject(userDetailTokener);
        String user_name = userDetailroot.getString("username");
        String fullname = userDetailroot.getString("fullname");
        String birthdate = userDetailroot.getString("birthdate");
        String email = userDetailroot.getString("email");
        String avatar = userDetailroot.getString("avatar");
        
        buffer = "<div class='half_div'><div id='upperprof'><img id='mainpp' width='225' src='"+avatar+"' alt=''><div id='namauser'>"+fullname+"</div></div> <br/><br/></div>";
        buffer = buffer + "<div class='half_div'> <div class='user_search_result_label'> Username  </div> <div class='left'> "+user_name+"</div> <div class='clear'></div> <br>";
        buffer = buffer + "<div class='user_search_result_label'> Email </div><div class='left'> "+email+"</div><div class='clear'></div><br>";	
        buffer = buffer + "<div class='user_search_result_label'>  Birthdate  </div> <div class='left'> "+birthdate+"</div><div class='clear'></div></div>";

    }
    else
    if (type.equals("category")) {
        //call category/category_detail?id=q
        
        //Search Category
        //stmt_searchresult = con.prepareStatement("SELECT * FROM category WHERE cat_id=?");
        //cat_id, cat_name, cat_creator
        
        //Search corresponding task
        //stmt_searchresult = con.prepareStatement("SELECT task_name, task_id FROM task WHERE cat_name=?");
        //task_name
                 
    }

    out.println(buffer);
%>