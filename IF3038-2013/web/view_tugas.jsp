<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%
	String title = "View Tugas";
	boolean login_permission = true;
        String[] param = {request.getParameter("task_id")};
        HashMap<String,Object> tugas = Query.one("select * from task where task_id = ?",param);
        if (tugas == null)
            response.sendRedirect("dashboard.jsp");
        else {
%>
<%@include file="inc/header.jsp" %>

		<script>
			window.onload=function(){_task_id = <%=tugas.get("task_id")%>; localStorage.user_id = <%=user_id%>; refreshComment(_task_id,1);};
		</script>
		<div class="content">
			<div class="task-details not-editing">
				<header>
					<form method="POST">
						<h1>
							<label>
								<span class="task-checkbox"><input id="taskcheck" type="checkbox" class="task-checkbox" onclick="negateTask(<%=tugas.get("task_id")%>)" <% if (tugas.get("done").toString().compareTo("true") == 0) out.print("checked "); %>></span>
								<span class="task-title"><a href="view_tugas.jsp?task_id=<%=tugas.get("task_id")%>"><%=tugas.get("name")%></a></span>
							</label>
						</h1>
					</form>
					<% 
                                        String [] param2 = {tugas.get("task_id").toString(),user_id.toString(),tugas.get("task_id").toString(),user_id.toString()};
					HashMap<String,Object> isPermit = Query.one("select user_id from task where task_id = ? and user_id = ? union select user_id from assign where task_id = ? and user_id = ?",param2);
					if (isPermit != null) {%>
					<ul>
						<li><a href="#" id="editTaskLink">Edit Task</a></li>
					</ul>
					<% } %>
				</header>
				<div id="current-task">
					<section class="details">
						<header>
							<h3>Details</h3>
						</header>
						<p class="status">
							<span class="detail-label">Status:</span>
							<span class="detail-content"><% out.print((tugas.get("done").toString().compareTo("true") == 0)?"Selesai":"Belum"); %></span>
						</p>
						<p class="assignee">
							<span class="detail-label">Assignee:</span>
							<% String[] param3 = {tugas.get("task_id").toString()};
                                                        List assignees = Query.all("select * from assign where task_id = ?",param3);
							if (!assignees.isEmpty()) { %>
                                                                <% for (Iterator it = assignees.iterator(); it.hasNext();) {
                                                                    HashMap<String, Object> r = (HashMap<String, Object>) it.next();
                                                                    String w = r.get("user_id").toString(); %>
									<span class="detail-content names"><a href="profile.jsp?user_id=<%=w%>"><%=Function.getUserName(w)%></a></span>
                                                                <% } %>
							<% } %>
						</p>
						<p class="deadline">
							<span class="detail-label">Deadline:</span>
							<span class="detail-content"><%=tugas.get("deadline")%></span>
						</p>
						<p class="tags">
							<span class="detail-label">Tag:</span>
							<% String[] param4 = {tugas.get("task_id").toString()};
                                                        List tags = Query.all("select * from tags where task_id = ?",param4);
							if (!tags.isEmpty()) { %>
                                                                <% for (Iterator it = tags.iterator(); it.hasNext();) {
                                                                    HashMap<String, Object> r = (HashMap<String, Object>) it.next();
                                                                    String w = r.get("tag_id").toString(); %>
									<span class="tag"><%= Function.getTagName(w) %></span>
                                                                <% } %>
							<% } else { %>
                                                                No tag specified!
                                                        <% } %>
						</p>
					</section>
					<section class="attachment">
						<header>
							<h3>Attachment</h3>
						</header>
							<% String[] param5 = {tugas.get("task_id").toString()};
                                                        List attachments = Query.all("select * from attachment where task_id = ?",param5);
							if (!attachments.isEmpty()) { %>
                                                                <% for (Iterator it = attachments.iterator(); it.hasNext();) {
                                                                    HashMap<String, Object> r = (HashMap<String, Object>) it.next();
                                                                    String type = r.get("type").toString(); %>
                                                                    <% if (type.compareTo("file") == 0) { %>
                                                                        <% String filename = r.get("filename").toString(); %>
									Download <a href="attachment/<%=filename%>"><%=filename%></a>
                                                                    <% } else { %>
									<figure>
									<% if (type.compareTo("image") == 0) { %>
                                                                                <% String filename = r.get("filename").toString(); %>
										<img src="attachment/<%=filename%>" alt="<%=filename%>">
									<% } else { %>
                                                                                <% String filename = r.get("filename").toString(); %>
										<video width="320" height="240" controls>
											<source src="attachment/<%=filename%>">
											Your browser does not support the video tag.
										</video>
									<% } %>
									</figure>
                                                                    <% } %>
                                                                <% } %>
							<% } else { %>
								No attachment available !
                                                        <% } %>
					</section>
				</div>
				<div id="edit-task">
                                    <% if (tugas.get("user_id").toString().compareTo(user_id.toString()) == 0 || Function.isAssignee(tugas.get("task_id").toString(),user_id.toString())) { %>
					<form id="new_tugas" action="#" method="post">
						<datalist id="suggestion">
						</datalist>
						<div class="field">
							<label>Deadline</label>
							<input size="30" name="deadline" id="deadline" type="date">
							<div class="buttons">
								<button onclick="updateTask('deadline',<%=tugas.get("task_id")%>);">Save Deadline</button>
							</div><br>&nbsp;
						</div>
						<div class="field">
							<label>Assignee</label>
							<input size="30" name="assignee" id="addassignee" type="text" onkeyup="assignee_autocomplete(this)" list="suggestion">
							<div class="buttons">
								<button onclick="updateTask('addassignee',<%=tugas.get("task_id")%>);">Add Assignee</button>
							</div>
							<% 
                                                        if (!assignees.isEmpty()) { %><br><label>click to delete</label>
                                                                <% for (Iterator it = assignees.iterator(); it.hasNext();) {
                                                                    HashMap<String, Object> r = (HashMap<String, Object>) it.next();
                                                                    String id = r.get("id").toString();
                                                                    String w = r.get("user_id").toString(); %>
									<span class="detail-content names" onclick="removeElement('delassignee',this,<%=tugas.get("task_id").toString()%>,<%=id%>)"><%=Function.getUserName(w)%></span>
                                                                <% } %>
								<br>&nbsp;
							<% } %>
						</div>
						<div class="field">
							<label>Tag</label>
							<input size="30" name="tag" id="addtag" type="text" onkeyup="tag_autocomplete(this)" list="suggestion">
							<div class="buttons">
								<button onclick="updateTask('addtag',<%=tugas.get("task_id").toString()%>);">Add Tag</button>
							</div>
							<% if (!tags.isEmpty()) { %><br><label>click to delete</label>
                                                                <% for (Iterator it = tags.iterator(); it.hasNext();) {
                                                                    HashMap<String, Object> r = (HashMap<String, Object>) it.next();
                                                                    String tag_id = r.get("tag_id").toString();
                                                                    String id = r.get("id").toString();
                                                                    String task_id = r.get("task_id").toString(); %>
									<span class="tag" onclick="removeElement('deltag',this,<%=task_id%>,<%=id%>)"><%=Function.getTagName(tag_id)%></span>
                                                                <% } %>
								<br>&nbsp;
							<% }%>
						</div>
						<div class="buttons">
					<% } %>
					<% if (tugas.get("user_id").toString().compareTo(user_id.toString()) == 0) { %>
							<button onclick="deleteTask(<%=tugas.get("task_id")%>);">Delete Task</button>
					<% } %>
                                        <% if (tugas.get("user_id").toString().compareTo(user_id.toString()) == 0 || Function.isAssignee(tugas.get("task_id").toString(),user_id.toString())) { %>
							<br>
						</div>
					</form>
                                        <% } %>
				</div>
				<section class="comments">
					<header>
						<h3><span id="commentCount"></span> Comments</h3>
					</header>

					<div id="commentsList">
					</div>
					
					<div id="commentPage">
					</div>
					
					<div class="comment-form">
						<h3>Add Comment</h3>
						<form id="commentForm" action="#" method="post">
							<textarea name="komentar" id="commentBody"></textarea>
							<button type="submit">Send</button>
						</form>
					</div>
				</section>
			</div>
		</div>
<%@include file="inc/footer.jsp" %>
<% } %>