<%-- 
    Document   : listCategory
    Created on : Apr 9, 2013, 7:09:16 PM
    Author     : User
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Class.*"%>
<%@page import="Servlet.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List" %>
<%@page import="com.google.appengine.api.datastore.DatastoreService" %> 
<%@page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@page import="javax.jdo.JDOHelper" %>
<%@page import="javax.jdo.PersistenceManager" %>
<%@page import="javax.jdo.PersistenceManagerFactory" %>
<%@page import="javax.jdo.Query" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        /*session management*/
        String userActive = "";
        if(request.getSession().getAttribute("userlistapp")==null){
            response.sendRedirect("index.jsp");
        }else{
            userActive = request.getSession().getAttribute("userlistapp").toString();
        }
       
        try{
            PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery("select from category");
			List<Category> result = (List<Category>)q.execute();
			
	        out.print("<div id=\"category-list\">");
	        out.print("<div><hr id=\"border\"></div>");
	        
	        int i = 0;
	        
	        Function FuncClass = new Function();
			
            if (!result.isEmpty()) {
				for (Category rs : result) {
					out.print("<div class=\"task-category-body\">");
		            out.print("<div><div class=\"category-title\"><b>"+rs.categoryname +"</b></div><div class=\"delete-category\" align=\"right\">");
		            
		            if(userActive.equals(rs.username)){
		                out.print("<a href=\"deletecategory?id="+rs.categoryid+"\"><input name=\"delete\" type=\"button\" value=\"Delete Category\"/></a>");
		                out.print("</div>");
		                out.print("</div>");
		                out.print("<div class=\"category-title-secondary\">Created by : <i>"+rs.username+"</i>, at "+rs.createdate+"</div><br>");
		            }
		            else {
		                out.print("</div>");
		                out.print("</div>");
		                out.print("<div class=\"kosong\">Created by : <i>"+rs.username+"</i>, at "+rs.createdate+"</div><br>");
		            }
		            
		            out.print("<ul>");
		          
		            q = pm.newQuery("select from" +Task.class.getName()+" where categoryid='"+rs.categoryid+"'" );
		            List<Task> result2 = (List<Task>)q.execute();
		            
		            if (!result2.isEmpty()) {
		            	for (Task rs2 : result2) {
		            		String taskid = FuncClass.getTaskId(rs2.taskname, String.valueOf(rs2.categoryid));
			                out.print("<br><li><a href = \"task_page.jsp?taskid="+taskid+"\">"+rs2.taskname+"</a><div class=\"task-tag\">submit by : <b><i>"+rs2.username+"</i></b>, deadline : "+rs2.deadline+", status : <b id=\"red-text"+(i++)+"\">"+rs2.status+"</b></div>");
					out.print("<br><div><div id=\"task-tag-delete\">");
			                
			                if(userActive.equals(rs2.username)){
			                    out.print("<a href=\"deletetask?taskid="+rs2.taskid+"\" onClick=\"confirmTask()\"><i>Delete Task</i></a>");
			                }
			                
			                if(FuncClass.isAssignee(userActive, taskid)){
			                    out.print("<div class=\"task-tag\"><a href=\"javascript:setCompleteStatus("+(i-1)+","+taskid+")\"><i>Change Status</i></a></div>");
			                }
			                
			                out.print("</div><br><br>");
							out.print("<div id=\"task-tag\">Tag :<br>");
			                
				            q = pm.newQuery("select tagid from" +Task_Tag.class.getName()+" where taskid='"+taskid+"'" );
				            List<Task_Tag> result3 = (List<Task_Tag>)q.execute();
				            
				            if (!result3.isEmpty()) {
				            	for (Task_Tag rs3 : result3) {
				            		String tagname = FuncClass.getTagname(String.valueOf(rs3.tagid));
				                    out.print("<u>"+tagname+"</u>");
				            	}
				            }
			                
			                out.print("</div>");
			                out.print("</li><br>");
		            	}
		            }

		            out.print("<br><br><br>");
		            if(FuncClass.isResponsibility(String.valueOf(rs.categoryid), userActive)){
		        	out.print("<div class = \"add-task\"><a href = \"add_task.jsp?categoryid="+rs.categoryid+"\"><button>+New Task</button></a></div>");
		            }
		            out.print("</ul>");
		            out.print("</div>");
		            out.print("<div><hr id=\"border\"></div>");
				}
			}
			q.closeAll();
			pm.close();
        }catch(Exception e){
        
        }

        out.print("</div>");
        
        %>
    </body>
</html>
