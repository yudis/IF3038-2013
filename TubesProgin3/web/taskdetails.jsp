<%--
    Document   : taskdetails
    Created on : Apr 6, 2013, 10:30:40 PM
    Author     : Yulianti Oenang
--%>

<%@page import="tubes3.Task"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tubes3.profile"%>
<jsp:include page="/header.jsp" />
<script src="taskdetails.js" type="text/javascript" language="javascript"> </script>
<!DOCTYPE html>
<div id="isi">
    <div id="leftsidebar">
            <b>TASK DETAILS</b>
            <img src="image/leftmenu.png"/>
    </div>
    <div id="rightsidebar">
            <div id="wrapper-left">
                    <ul class="task">
                            <h1>Details</h1>
                            <li>
                                    <label for="tugas">Nama Tugas</label>
                                    <div class="text"><%Task task=new Task();out.print(task.name);%></div>
                            </li>
                            <li>
                                    <label for="tugas">Status</label>
                                    <div class="text"><%out.print(task.status);;%></div>
                                    <form method="post" action="taskdetailscontroller.php">
                                    <button>ubah status</button>
                                    </form>
                            </li>
                            <li>
                                    <label for="attach">Attachment</label>
                                    <br>
                                    <br>
                                    <%
                                        for(int i=0;i<task.attachment.size();i++)
                                        {
                                            out.print(task.attachment.get(i));
                                            if(i<task.attachment.size()-1)
                                            {
                                                out.print(", ");
                                            }
                                        }
                                     %>
                            </li>
                            <li>
                                    <label for="deadline">Deadline</label>
                                    <input id="deadline" type="text" size="25" value="<%out.print(task.deadline);%>"readonly>
                                    <a id="tanggal" href="javascript:NewCal('deadline','ddmmyyyy')" onclick="return false"><img src="image/cal.gif" alt="Pick a date"/></a>
                            </li>
                            <li>
                                    <label for="assignee">Assignee</label>
                                    <div id="anggota">
                                    <%
                                    for(int i=0;i<task.assignee.size();i++)
                                    {
                                        out.print(task.assignee.get(i));
                                        if(i<task.assignee.size()-1)
                                        {
                                            out.print(", ");
                                        }
                                    }

                                    %>
                                    </div>
                                    <div id="assignee">

                                    </div>
                                    <li id="layer1">
                                    </li>

                            </li>							
                            <li>
                                    <label>Tag</label>
                                    <div id="data">
                                    <%
                                       out.print(task.tag);
                                    %>
                                    </div>
                                    <input id="inputtag" type="text" style="visibility:hidden;" placeholder="example1,example2"></input>
                            </li>
                            <button id="edit" name="edit" type="button"><b>Edit</b></button><br>
                    <div class="task">
                    <%
                        out.print("<li>");
			out.print("<label id=\"a\"  for=\"komentar\">Komentar("+task.comment.size()+")</label>");
			out.print("</li>");
			out.print ("<br>");	
                        for(int i=0;i<task.comment.size();i++)
                        {
                        out.print ("<div id=\""+task.comment.get(i).id+"\"");
                        out.print ("<div class=\"headerComment\">");
                        out.print ("<div class=avatar style=\"float:left;\">");
                        out.print ("<img src="+task.comment.get(i).avatar+" height=\"42\" width=\"42\">");
                        out.print ("</div>");
                        out.print ("<div class=username style=\"float:left;\"><b>"+task.comment.get(i).username);
                        out.print ("</b></div>");
                        out.print ("<div class=waktu><b>"+task.comment.get(i).waktu);
                        out.print ("</b></div>");
                        out.print ("<div>");
                        if(!(task.comment.get(i).username.equals("yuli")))
                        {}
                        else
                        {
                        out.print ("<a class=\"remove\" href=\"\" onClick=\"removeComment("+task.comment.get(i).id+");return false;\" >remove");

                        out.print ("</a>");
                        }
                        out.print ("</div>");
                        out.print ("</div>");
                        out.print ( "<li>"+task.comment.get(i).isi+"</li>");
                        out.print ("</div>");
                        //echo "aaa";
                        }
                    
                           
                                    %>



                            <form id="commentform">
                                    <input class="task" id="commentfield" name="commentfield" type="text" size="1000"/> 

                                    <input id="commentbutton" name="commentbutton" type="submit" value="Comment"/>
                            </form>
                            </div>
                    </ul>

            </div>
				</div>
			</div>
			
			<div id="footer" class="home">
				<p>&copy Copyright 2013. All rights reserved<br>
				Chalkz Team<br>
				Yulianti - Raymond - Devin</p>			
			</div>
        </div>
         <script	type="text/javascript">
			var temp = document.getElementById("commentform");
			temp.onsubmit = function()
			{
				postComment();
				return false;
			}
			
			
			
			function postComment()
			{
				var form = document.getElementById("commentform");
			
				var a = document.getElementById("commentbox");
				var b = document.getElementById("commentfield")
				a.value += "User: " + b.value + "\n\n";
				
				b.value = "";
			}
			
			
			
			
		</script>
        
    </body>
</html>

