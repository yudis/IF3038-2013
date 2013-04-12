<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>        
<%  //-------------------------register---------------------------------
    String usrReg = request.getParameter("username");
    String pwdReg = request.getParameter("password");
    String namaReg = request.getParameter("namaleng");
    String tglReg = request.getParameter("tanggal");
    String emlReg = request.getParameter("email");
    
    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection conLogin2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510003","root","");
    
    Statement stLogin2= conLogin2.createStatement(); 
    int rsLogin2=stLogin2.executeUpdate("INSERT INTO pengguna VALUES('"+usrReg+"','"+pwdReg+"','"+namaReg+"','"+tglReg+"','"+emlReg+"','belum')"); 
    
    session.setAttribute("userLoginSession", usrReg);     
    response.sendRedirect("dashboard.jsp");
%>   
