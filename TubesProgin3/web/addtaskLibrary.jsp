<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.File" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="tubes3.Tubes3Connection"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if ((request.getParameter("tugas") != null) && (request.getParameter("tugas") != "")) {


        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
        } else {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {
                } else {
                    try {
                        String itemName = item.getName();
                        File savedFile = new File(config.getServletContext().getRealPath("/") + "uploadedFiles/" + itemName);
                        item.write(savedFile);
                        //   out.println("<tr><td><b>Your file has been saved at the loaction:<//b></td></tr><tr><td><b>"+ config.getServletContext().getRealPath("/") + "uploadedFiles" + "\\" + itemName + "</td></tr>");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
%>