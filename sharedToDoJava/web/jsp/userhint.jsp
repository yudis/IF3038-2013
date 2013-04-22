<%@page import="com.mysql.jdbc.Driver"%>
<%@page import="com.mysql.jdbc.exceptions.jdbc4.CommunicationsException" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="org.json.simple.JSONObject"%>
    
<%              
    new Driver();
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/progin_405_13510027","progin","progin");
	
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("SELECT username FROM user");
	
	Vector<String> username = new Vector<String>();	
	while(rs.next()) {
		username.add(rs.getString("username"));
	}
    
	String usr = request.getParameter("key");
    String hint = "";
    if (usr.length() > 0){    	
        for (int i = 0; i < username.size(); i++) {        	
            if ( usr.toLowerCase().compareTo(username.get(i).substring(0, usr.length()).toLowerCase()) == 0) {
                if (hint == "") {
                    hint = username.get(i);
                } else {
                    hint = hint + ", " + username.get(i) ;
                }
            }
        }
    }
    
    String respon;
    if (hint == "") {
        respon = "tidak ada saran";
    } else {
        respon = hint;
    }
    
    JSONObject obj = new JSONObject();
	obj.put("response", respon);
	out.print(obj);
	out.flush();    
%>