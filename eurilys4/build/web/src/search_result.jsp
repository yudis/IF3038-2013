<%@page import="org.json.JSONArray"%>
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
        //URL userDetailURL = new URL("http://localhost:8084/eurilys4/user/user_detail?username=" + q);
        URL userDetailURL = new URL("http://eurilys.ap01.aws.af.cm/user/user_detail?username=" + q);
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
        //call search/category?id=
        //URL searchURL = new URL("http://localhost:8084/eurilys4/search/category?id=" + q);
        URL searchURL = new URL("http://eurilys.ap01.aws.af.cm/search/category?id=" + q);
        HttpURLConnection searchConn = (HttpURLConnection) searchURL.openConnection();
        searchConn.setRequestMethod("GET");
        searchConn.setRequestProperty("Accept", "application/json");
        if (searchConn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + searchConn.getResponseCode());
        }
        BufferedReader searchBr = new BufferedReader(new InputStreamReader((searchConn.getInputStream())));
        String searchOutput;
        String searchJSONObject = "";
        while ((searchOutput = searchBr.readLine()) != null) {
            searchJSONObject += searchOutput;
        } 
        searchConn.disconnect();
        
        //Parse userDetailJSONObject 
        JSONTokener searchTokener = new JSONTokener(searchJSONObject);
        JSONObject searchRoot = new JSONObject(searchTokener);
        
        buffer += "<br><br><div class='task_view'>";
        buffer += "<div class='cat_search_result_label'> Category Name  </div> <div class='left'>"+ searchRoot.getString("cat_name")+"</div>";
        buffer += "<div class='clear'></div><br>";
        buffer += "<div class='cat_search_result_label'> Creator  </div> <div class='left'>" + searchRoot.getString("cat_creator") +"</div>";
        buffer += "<div class='clear'></div><br>";
        buffer += "<div class='cat_search_result_label'> List of Task </div> <div class='left'>";
        JSONArray task_list = searchRoot.getJSONArray("task");
        for (int i=0; i<task_list.length(); i++) {
            buffer += task_list.get(i);
            if (i!=task_list.length()-1) {
                buffer += " , ";
            }
        }
        buffer += "</div></div>";
    }

    out.println(buffer);
%>