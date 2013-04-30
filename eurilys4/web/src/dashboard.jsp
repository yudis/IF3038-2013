<%@include file="logged_in_header.jsp"%>	

<section>
	<!-- Navigation Bar -->
        <%@include file="navigation_bar.jsp"%>
        <div id="dynamic_content">
        
        <%
            String username = (String) session.getAttribute("username");
            URL taskURL = new URL("http://localhost:8084/eurilys4-service/task/all_task?username=" + session.getAttribute("username"));
            //URL taskURL = new URL("http://eurilys.ap01.aws.af.cm/task/all_task?username=" + session.getAttribute("username"));
            HttpURLConnection taskConn = (HttpURLConnection) taskURL.openConnection();
            taskConn.setRequestMethod("GET");
            taskConn.setRequestProperty("Accept", "application/json");
            if (taskConn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + taskConn.getResponseCode());
            }
            BufferedReader taskBr = new BufferedReader(new InputStreamReader((taskConn.getInputStream())));
            String taskOutput;
            String taskJSONObject = "";
            while ((taskOutput = taskBr.readLine()) != null) {
                taskJSONObject += taskOutput;
            } 
            taskConn.disconnect();

            //Parse taskJSONObject 
            JSONTokener taskTokener = new JSONTokener(taskJSONObject);
            JSONArray taskroot = new JSONArray(taskTokener);
        
            for (int i=0; i<taskroot.length(); i++) {
                JSONObject task = taskroot.getJSONObject(i);
                String task_id = task.getString("task_id");
                String task_name = task.getString("task_name");
                String task_status = task.getString("task_status");
                String task_deadline = task.getString("task_deadline");
                String task_category = task.getString("task_category");
                String task_creator = task.getString("task_creator");
                
                out.println("<br><div class=\"task_view\" id='"+ task_id +"'>");
                if (task_creator.equals(username)) { 
                    out.println("<div onclick=\"javascript:deleteTask('"+task_id+"')\" class=\"task_done_button\"> Delete </div>");
                    out.println("<div class=\"task_done_button\"> &nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp; </div>"); 
                }
                out.println("<div id='taskHandler_"+task_id+"'>");
                if ("0".equals(task_status)) {
                    out.println("<div onclick=\"javascript:finishTask('"+task_id+"')\" class=\"task_done_button\"> Mark as Finished </div>");
                } else {
                    out.println("<img src=\"../img/yes.png\" class=\"task_done_button\" alt=''/>");
                }
                out.println("</div>");
                    out.println("<div id='task_name_ltd' class=\"left dynamic_content_left\"> Task Name </div>");
                    out.println("<div id='task_name_rtd' class=\"left dynamic_content_right darkBlueLink\" onclick=\"javascript:viewTask('"+task_id+"')\">" +task_name+ "</div><br><br>");
                    
                    out.println("<div id='deadline_ltd' class='left dynamic_content_left'>Deadline</div>");
                    out.println("<div id='deadline_rtd' class='left dynamic_content_right'>" +task_deadline+ "</div> <br><br>");
                    
                    out.println("<div id='tag_ltd' class='left dynamic_content_left'>Tag</div>");
                    out.println("<div id='tag_rtd' class='left dynamic_content_right'>");
                    JSONArray tag_list = task.getJSONArray("tag_list");                    
                    for (int j=0; j<tag_list.length(); j++) {
                        out.println(tag_list.get(j) + " , ");
                    }
                    out.println("</div><br>");
                    
                    out.println("<div class='task_view_category'> "+task_category+"</div> <br>");
                out.println("</div>"); 
            }
        %>
        <br><br>
        </div>
</section>
		
<%@include file="footer.jsp"%>