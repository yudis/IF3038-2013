<%@page import="java.util.HashMap"%>
<%@page import="org.apache.commons.fileupload.FileUploadException"%>
<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@ page import="java.io.*" %>
<%@page import = "org.progin.todo.Query" %>
<%
    HashMap<String,String> reqParam = new HashMap<String,String>();
    File savedFile = null;
    FileItem fi = null;
    try {
        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                reqParam.put(item.getFieldName(),item.getString());
                out.println(item.getFieldName());
                out.println(item.getString());
            } else {
                fi = item;
            }
        }
    } catch (FileUploadException e) {
        throw new ServletException("Cannot parse multipart request.", e);
    }
    String[] param = {reqParam.get("username"), reqParam.get("password"), reqParam.get("nama_lengkap"), reqParam.get("tanggal_lahir"), reqParam.get("email")};
    int user_id;
    user_id = Query.nid("INSERT into user (username,password,name,birthdate,email) values (?,?,?,?,?)", param);
    savedFile = new File(config.getServletContext().getRealPath("/")+"avatar/" + user_id + ".jpg");
    fi.write(savedFile);
//	move_uploaded_file($_FILES["avatar"]["tmp_name"],"avatar/" . $user_id . '.jpg');
    session.setAttribute("user_id", user_id);
    response.sendRedirect("dashboard.jsp");
%>