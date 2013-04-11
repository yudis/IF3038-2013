<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.commons.fileupload.FileUploadException"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.progin.todo.Function"%>
<%@page import = "org.progin.todo.Query" %>
<%
    String name = null;
    String deadline = null;
    String done = "0";
    String category_id = null;
    String user_id = session.getAttribute("user_id").toString();
    String tag = null;
    List<String> assignee = new ArrayList<String>();
    List<FileItem> fis = new ArrayList<FileItem>();
    try {
        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        out.println("trying" + items.size());
        for (FileItem item : items) {
            if (item.isFormField()) {
                String fieldname = item.getFieldName();
                if (fieldname.compareTo("name") == 0) {
                    name = item.getString();
                } else if (fieldname.compareTo("deadline") == 0) {
                    deadline = item.getString();
                } else if (fieldname.compareTo("tag") == 0) {
                    tag = item.getString();
                } else if (fieldname.compareTo("category_id") == 0) {
                    category_id = item.getString();
                } else if (fieldname.compareTo("assignee[]") == 0) {
                    assignee.add(item.getString());
                }
                out.println(item.getString());

            } else {
                out.println("files");
                //out.println(item.getString());
                out.println(item.getFieldName());
                fis.add(item);
            }
        }
    } catch (FileUploadException e) {
        out.println("Ga bisa upload file");
        throw new ServletException("Cannot parse multipart request.", e);
    }
    Integer task_id;
    String[] param = {user_id, category_id, name, deadline, done};
    task_id = Query.nid("insert into task (user_id,category_id,name,deadline,done) values (?,?,?,?,?)", param);
    if ("".compareTo(tag) != 0) {
        String[] tags = tag.split("\\,");
        for (int i = 0; i < tags.length; i++) {
            Function.addTag(task_id.toString(), tags[i]);
        }
    }
    for (String assign : assignee) {
        Function.addAssignee(assign, task_id.toString(), null);
    }
    for (FileItem item : fis) {
        String type = item.getContentType().split("/")[0];
        String itemName = FilenameUtils.getName(item.getName());
        out.println(itemName);
        String extension = itemName.substring(itemName.lastIndexOf('.'));
        if ("image".compareTo(type) != 0 && "video".compareTo(type) != 0) {
            type = "file";
        }
        String[] param2 = {task_id.toString(), type};
        Integer attachment_id = Query.nid("insert into attachment (task_id,type) values (?,?)", param2);
        out.println(attachment_id);
        String filename = attachment_id + extension;
        out.println(filename);
        String[] param3 = {filename, attachment_id.toString()};
        Query.n("update attachment set filename = ? where attachment_id = ?", param3);
        File savedFile = new File(config.getServletContext().getRealPath("/") + "attachment/" + filename);
        item.write(savedFile);
    }
    out.println("wohoo");
    response.sendRedirect("view_tugas.jsp?task_id="+task_id);
%>
