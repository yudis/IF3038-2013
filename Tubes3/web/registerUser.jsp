<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>        
<%  //-------------------------register---------------------------------
    String usrReg = request.getParameter("usernameReg");
    String pwdReg = request.getParameter("passwordReg");
    String namaReg = request.getParameter("namaleng");
    String tglReg = request.getParameter("tanggalReg");
    String emlReg = request.getParameter("emailReg");
    
    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection conLogin2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510003","root","");
    
    Statement stLogin2= conLogin2.createStatement(); 
    int rsLogin2=stLogin2.executeUpdate("INSERT INTO pengguna VALUES('"+usrReg+"','"+pwdReg+"','"+namaReg+"','"+tglReg+"','"+emlReg+"','belum')"); 
    
    
%>   
