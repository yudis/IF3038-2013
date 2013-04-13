<%-- 
    Document   : getSearchData
    Created on : Apr 12, 2013, 9:40:46 PM
    Author     : Nicholas Rio
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="Utility.DBUtil" %>

<%
    String q = request.getParameter("q").toString();
    String opt = request.getParameter("opt").toString();
    String usr = request.getParameter("usr").toString();
    System.out.println(usr);
    String query = "";
    PreparedStatement ps;
    ResultSet rs;
    out.write("<xml>");
    Connection con = DBUtil.getConnection();
    if (opt.equalsIgnoreCase("a") || opt.equalsIgnoreCase("u")) {
        query = "SELECT username,fullname FROM user WHERE username LIKE '%" + q + "%' OR fullname LIKE '%" + q + "%' OR email LIKE '%"
                + q + "%' OR dob LIKE '%" + q + "%'";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            out.write("<User>");
            out.write("<ID>");
            out.write(rs.getString(1));
            out.write("</ID>");
            out.write("<String>");
            out.write(rs.getString(2));
            out.write("</String>");
            out.write("</User>");
        }
    } 
    if (opt.equalsIgnoreCase("a") || opt.equalsIgnoreCase("c")) {
        query = "SELECT id_category,name FROM category WHERE name LIKE '%" + q + "%'";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            out.write("<Category>");
            out.write("<ID>");
            out.write(rs.getString(1));
            out.write("</ID>");
            out.write("<String>");
            out.write(rs.getString(2));
            out.write("</String>");
            out.write("</Category>");
        }
    } 
    if (opt.equalsIgnoreCase("a") || opt.equalsIgnoreCase("t")) {
        query = "SELECT DISTINCT t.id_task, t.name FROM task AS t "
                + "INNER JOIN ttrelation AS tt "
                + "ON t.id_task = tt.id_task "
                + "INNER JOIN tag AS ta "
                + "ON tt.id_tag = ta.id_tag "
                + "INNER JOIN utrelation AS u "
                + "ON t.id_task = u.id_task "
                + "WHERE (t.name LIKE '%" + q + "%' OR ta.name LIKE '%" + q + "%') AND u.username = '" + usr + "'";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            out.write("<Task>");
            out.write("<ID>");
            out.write(rs.getString(1));
            out.write("</ID>");
            out.write("<String>");
            out.write(rs.getString(2));
            out.write("</String>");
            out.write("</Task>");
        }
    }
    out.write("</xml>");

%>
