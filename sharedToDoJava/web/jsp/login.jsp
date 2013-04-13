<%@page import="java.util.Vector"%>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@page import="com.mysql.jdbc.Driver"%>
<%@page import="com.mysql.jdbc.exceptions.jdbc4.CommunicationsException" %>
<%@page import="javax.swing.JOptionPane" %>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="org.json.simple.JSONObject"%>

<%
	//Class.forName("com.mysql.jdbc.Driver");	
	new Driver();
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/progin_405_13510027","progin","progin");		
	
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("SELECT username, password FROM user");
	
	Vector<String> username = new Vector<String>();
	Vector<String> password = new Vector<String>();
	
	while(rs.next()) {
		username.add(rs.getString("username"));
		password.add(rs.getString("password"));
	}
	
	String usr = request.getParameter("u");
	String pass = request.getParameter("p");
	
	
	
	String respon = "Username dan password tidak cocok";
	for(int i=0; i<username.size(); i++) {
		if(usr.compareTo(username.get(i))==0  && pass.compareTo(password.get(i))==0) {
			respon = "";
			session.setAttribute("username", username.get(i));
			session.setAttribute("loggedin", "yes");
			break;
		}
	}
	
	JSONObject obj = new JSONObject();
	obj.put("response", respon);
	out.print(obj);
	out.flush();
%>