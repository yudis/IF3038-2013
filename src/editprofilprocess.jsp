<%@page import="database.MyDatabase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="database.Checker"%>
<%
    String uname = request.getParameter("daftar_username");
    String fullname = request.getParameter("daftar_namalengkap");
    String password = request.getParameter("daftar_password");
    String email = request.getParameter("daftar_email");
    String tanggal = request.getParameter("daftar_tanggallahir");
    String avatar = request.getParameter("daftar_avatar");
    if (avatar != null) {
        String pQuery = "UPDATE `sharedtodolist`.`user` SET `avatar` = '" + avatar + "' WHERE `user`.`username` = '" + uname + "'";
        MyDatabase.getSingleton().queryDB(pQuery);
    }
    if (!(tanggal == null || tanggal == "")) {
        String pQuery = "UPDATE `sharedtodolist`.`user` SET `tanggalLahir` = '" + tanggal + "' WHERE `user`.`username` = '" + uname + "'";
        MyDatabase.getSingleton().queryDB(pQuery);
    }
    if (fullname != null && !fullname.equals("")) {
        String pQuery = "UPDATE `sharedtodolist`.`user` SET `fullname` = '" + fullname + "' WHERE `user`.`username` = '" + uname + "'";
        MyDatabase.getSingleton().queryDB(pQuery);
    }
    if (password != null && !password.equals("")) {
        String pQuery = "UPDATE `sharedtodolist`.`user` SET `password` = '" + password + "' WHERE `user`.`username` = '" + uname + "'";
        MyDatabase.getSingleton().queryDB(pQuery);
    }
    response.sendRedirect("profil.jsp");
%>
