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
	ResultSet rs = st.executeQuery("SELECT username, email FROM user");
	
	Vector<String> username = new Vector<String>();
	Vector<String> email = new Vector<String>();
	
	while(rs.next()) {
		username.add(rs.getString("username"));
		email.add(rs.getString("email"));
	}
	
	String eml = request.getParameter("p");
	String usr = request.getParameter("q");	
	
	String respon = "";	
	if(usr!=null) {
		for(int i=0; i<username.size(); i++) {
			if(usr.compareTo(username.get(i))==0) {
				respon = "Username sudah ada";
				break;
			}
		}
	}
	
	if(eml!=null) {
		for(int i=0; i<email.size(); i++) {
			if(eml.compareTo(email.get(i))==0) {
				respon = "Email sudah ada";
				break;
			}
		}
	}
	
	JSONObject obj = new JSONObject();
	obj.put("response", respon);
	out.print(obj);
	out.flush();
%>