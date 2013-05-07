<%@page import="Servlets.register"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Utility.DBUtil"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%
    File file;
    String name = "";
    String[] asignee;
    String[] tag;
    String deadline = "";
    String query = "";
    String idTask = "";
    String category = session.getAttribute("curCategId").toString();
    String username = session.getAttribute("username").toString();

    Connection con = DBUtil.getConnection();
    PreparedStatement ps;
    ResultSet rs1, rs2;

    DiskFileItemFactory factory = new DiskFileItemFactory();
    String contextRoot = getServletContext().getRealPath("/");
    String filePath = contextRoot + "..\\..\\web\\uploaded\\";
    System.out.println(filePath);
    factory.setRepository(new File(contextRoot + "..\\..\\web\\temp"));
    ServletFileUpload upload = new ServletFileUpload(factory);
    try {
        List fileItems = upload.parseRequest(request);
        Iterator i = fileItems.iterator();
        while (i.hasNext()) {
            FileItem fi = (FileItem) i.next();
            System.out.println(fi.getFieldName());
            if (!fi.isFormField()) {
                String fileName = fi.getName();
                if (fileName.lastIndexOf("\\") >= 0) {
                    fileName = filePath + fileName.substring(fileName.lastIndexOf("\\"));
                } else {
                    fileName = filePath + fileName.substring(fileName.lastIndexOf("\\") + 1);
                }
                file = new File(fileName);
                fi.write(file);
                System.out.println(fileName);
                fileName="uploaded/"+fi.getName();
                query = "INSERT INTO attachment (path) VALUES ('" + fileName + "');";
                ps = con.prepareStatement(query);
                ps.executeUpdate();

                query = "SELECT id_attachment FROM attachment WHERE path='" + fileName + "';";
                ps = con.prepareStatement(query);
                rs2 = ps.executeQuery();
                rs2.next();

                query = "INSERT INTO tarelation (id_task, id_attachment) VALUES ('" + idTask + "', '" + rs2.getString(1) + "');";
                ps = con.prepareStatement(query);
                ps.executeUpdate();
            } else {
                if (fi.getFieldName().equalsIgnoreCase("newTaskName")) {
                    name = fi.getString();
                } else if (fi.getFieldName().equalsIgnoreCase("newTaskDeadline")) {
                    String[] temp = fi.getString().split("-");
                    deadline = temp[0] + "/" + temp[1] + "/" + temp[2];
                    query = "INSERT INTO task (name, deadline, status, id_category, creator) VALUES ('"
                            + name + "', '" + deadline + "', 'F', '" + category + "', '" + username + "');";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();

                    query = "SELECT id_task FROM task WHERE name='" + name + "' AND creator='" + username + "';";
                    ps = con.prepareStatement(query);
                    rs1 = ps.executeQuery();
                    rs1.next();
                    idTask = rs1.getString(1);

                    query = "INSERT INTO utrelation (username, id_task) VALUES ('" + username + "', '" + rs1.getString(1) + "');";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } else if (fi.getFieldName().equalsIgnoreCase("newTaskAssignee")) {
                    asignee = fi.getString().split(",");
                    for (int j = 0; j < asignee.length; j++) {
                        if (asignee[j] != "") {
                            query = "INSERT INTO utrelation (username, id_task) VALUES ('" + asignee[j] + "', '" + idTask + "');";
                            ps = con.prepareStatement(query);
                            ps.executeUpdate();
                        }
                    }
                } else if (fi.getFieldName().equalsIgnoreCase("newTaskTag")) {
                    System.out.println("haiyaa");
                    tag = fi.getString().split(",");
                    for (int j = 0; j < tag.length; j++) {
                        System.out.println("haiyaa 1");
                        query = "SELECT name FROM tag WHERE name='" + tag[j] + "'";
                        ps = con.prepareStatement(query);
                        rs2 = ps.executeQuery();
                        if (!rs2.next()) {
                            System.out.println("haiyaa insert new tag");
                            query = "INSERT INTO tag (name) VALUES ('" + tag[j] + "');";
                            ps = con.prepareStatement(query);
                            ps.executeUpdate();
                        }
                        System.out.println("haiyaa 2");
                        query = "SELECT id_tag FROM tag WHERE name='" + tag[j] + "';";
                        ps = con.prepareStatement(query);
                        rs2 = ps.executeQuery();
                        rs2.next();
                        System.out.println("haiyaa 3");
                        query = "INSERT INTO ttrelation (id_task, id_tag) VALUES ('" + idTask + "', '" + rs2.getString(1) + "');";
                        ps = con.prepareStatement(query);
                        ps.executeUpdate();
                    }
                }
            }
        }
        response.sendRedirect("dashboard.jsp");
    } catch (Exception ex) {
        Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
    }

    /*if (request.getParameter("newTaskAsignee") != null) {
     asignee = request.getParameter("newTaskAsignee").toString().split(",");
     for (int i = 0; i < asignee.length; i++) {
     if (asignee[i] != "") {
     query = "INSERT INTO utrelation (username, id_task) VALUES ('" + asignee[i] + "', '" + rs1.getString(1) + "');";
     ps = con.prepareStatement(query);
     ps.executeUpdate();
     }
     }
     }

     if (request.getParameter("newTaskTag") != null) {
     tag = request.getParameter("newTaskTag").toString().split(",");
     for(int i=0; i<tag.length; i++){
     query = "SELECT name FROM tag WHERE name='"+tag[i]+"'";
     ps = con.prepareStatement(query);
     rs2 = ps.executeQuery();
     if(!rs2.next()){
     query = "INSERT INTO tag (name) VALUES ('" + tag[i] + "');";
     ps = con.prepareStatement(query);
     ps.executeUpdate();
     }
     query = "SELECT id_tag FROM tag WHERE name='" + tag[i] + "';";
     ps = con.prepareStatement(query);
     rs2 = ps.executeQuery();
            
     query = "INSERT INTO ttrelation (id_task, id_tag) VALUES ('" + rs1.getString(1) + "', '" + rs2.getString(1) + "');";
     ps = con.prepareStatement(query);
     ps.executeUpdate();
     }
     }*/
%>
