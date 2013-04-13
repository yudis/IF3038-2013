<%@ page import="java.util.*" %>
<%@ page import="java.sql.*;" %>
<%@ page import="javax.sql.*;" %>

<%
	String sql = "SELECT username FROM user";
	PreparedStatement stmt = con.prepareStatement(sql);
	ResultSet rs = stmt.executeQuery();
	String message="Username available";
	String message2="Username invalid";
	
	boolean AlreadyExists = false;
	while(!rs.last() || (!AlreadyExists)){
		rs.next();
		if(rs.getString("username").compareTo("registerusername")){
			AlreadyExists = true;
			response.sendRedirect("login.jsp?error="+message2);
		}
		if(!AlreadyExists){
			String sqlstatement = "INSERT INTO user VALUES ('"+username"','"+password+"','"+name+"','"+dob+"','"+email+"','"+avatar+"')";
			PreparedStatement updsql = con.prepareStatement(sqlstatement);
			updsql.executeUpdate();
			response.sendRedirect(response.encodeRedirectURL("home.jsp"));
			break;
		}
	}
%>
