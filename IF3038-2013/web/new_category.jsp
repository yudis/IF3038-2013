<%@page import="org.progin.todo.Function"%>
<%@page import = "org.progin.todo.Query" %>
<%
    String name = request.getParameter("name");
    String user_id = session.getAttribute("user_id").toString();
    String[] assignee;
    if (request.getParameterMap().containsKey("assignee[]")) {
        assignee = request.getParameterValues("assignee[]");
    } else {
        assignee = new String[0];
    }
    String[] param = {name, user_id};
    Integer category_id;
    category_id = Query.nid("INSERT into category (name,user_id) values (?,?)", param);
    for (int i = 0; i < assignee.length; i++) {
        Function.addAssignee(assignee[i],null,category_id.toString());
    }
    response.sendRedirect("dashboard.jsp");
%>