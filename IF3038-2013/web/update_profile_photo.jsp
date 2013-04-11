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
    String user_id = session.getAttribute("user_id").toString();
    try {
        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                
            } else {
                File savedFile = new File(config.getServletContext().getRealPath("/")+"avatar/" + user_id + ".jpg");
                savedFile.delete();
                item.write(savedFile);
            }
        }
    } catch (FileUploadException e) {
        throw new ServletException("Cannot parse multipart request.", e);
    }
    response.sendRedirect("profile.jsp?user_id="+user_id);
%>