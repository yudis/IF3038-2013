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
<%
int ID=1;
if (((HttpServletRequest) request).getSession().getAttribute("idtugas") != null)
       {
            response.sendRedirect("home.jsp");
       }
else
       {
            ID=(Integer)((HttpServletRequest) request).getSession().getAttribute("idtugas");            
            
       }
Task task=new Task(ID);
%>

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
                                    <div class="text"><%out.print(task.name);%></div>
                            </li>
                            <li>
                                    <label for="tugas">Status</label>
                                    <div class="text"><%out.print(task.status);%></div>
                                    <form method="post" action="Task">
                                    <button>ubah status</button>
                                    </form>
                            </li>
                            <li>
                                    <label for="attach">Attachment</label>
                                    <br>
                                    <br>
                                    <%
                                    String ext[];
                                        for(int i=0;i<task.attachment.size();i++)
                                        {
                                            ext=task.attachment.get(i).split("\\.");
                                            if(ext[1].equals("jpg"))
                                            {
                                                out.print("<img src="+task.attachment.get(i)+" style=\"width: 220px;height: 230px;\"><br>");
                                            }
                                            else if(ext[1].equals("ogg"))
                                            {
                                                out.print("<video width=\"320\" height=\"240\" controls autoplay><source src="+task.attachment.get(i)+" type=\"video/"+ext[1]+"\"></video><br>");
                                            }
                                            else
                                            {
                                                out.print("<a href=\""+task.attachment.get(i)+"\" target=\"_blank\">"+task.attachment.get(i).substring(7) +"</a><br>");
                                            }
                                        }
                                     %>
                            </li>
                            <li>
                                    <label for="deadline">Deadline</label>
                                    <input id="deadline" type="text" size="25" value="<%String parser[]=task.deadline.split("-");out.print(parser[2]+"-"+parser[1]+"-"+parser[0]);%>"readonly>
                                    <a id="tanggal" href="javascript:NewCssCal('deadline','ddmmyyyy')" onclick="return false"><img src="image/cal.gif" alt="Pick a date"/></a>
                            </li>
                            <li>
                                    <label for="assignee">Assignee</label>
                                    <div id="anggota">
                                    <%
                                    if(task.assignee.size()==0)
                                    {
                                            out.print("<br>");
                                    }
                                    else{
                                        for(int i=0;i<task.assignee.size();i++)
                                        {
                                            out.print ("<div id=\""+task.assignee.get(i) +"\">");
                                            out.print ("<a  href=\"profile.jsp?username="+task.assignee.get(i)+"\">"+task.assignee.get(i)+"</a>");
                                            out.print ("<a id=\"r"+i+"\" href=\"#\" style=\"visibility:hidden\" onclick=\"removeA('"+task.assignee.get(i)+"')\">(remove)</a><br>");
                                            out.print ("</div>");
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
                                    <input id="inputtag" type="text"  style="visibility:hidden;" placeholder="example1,example2"></input>
                            </li>
                            <div id="jumlahA" style="visibility:hidden">
                                <%out.print(task.jumlahA);%>
                                </div>
                            <button id="edit" name="edit" onclick="editTask(document.getElementById('jumlahA').innerHTML)" type="button"><b>Edit</b></button><br>
                    <div id="jumkom" style="visibility:hidden">
                                  <%out.print(task.jumlah);%>
                    </div>        
                    <div class="task">
                    <%
                        out.print("<li>");
			out.print("<label id=\"a\"  for=\"komentar\">Komentar("+task.jumlah+")</label>");
			out.print("</li>");
			out.print ("<br>");	
                        out.print("<div id=\"comment\">");
                        for(int i=0;i<task.comment.size();i++)
                        {
                        out.print ("<div id=\""+task.comment.get(i).id +"\">");
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
                        out.print ("<a class=\"remove\" href=\"\" onClick=\"removeComment("+task.comment.get(i).id+","+task.jumlah+");return false;\" >remove");

                        out.print ("</a>");
                        }
                        out.print ("</div>");
                        out.print ("</div>");
                        out.print ( "<li>"+task.comment.get(i).isi+"</li>");
                        out.print ("</div>");
                        
                        //echo "aaa";
                        }
                    out.print ("</div>");
                           
                                    %>
                            


                            <form id="commentform">
                                    <input class="task" id="commentfield" name="commentfield" type="text" size="1000"/> 
                                    <input id="commentbutton" name="commentbutton" type="submit" value="Comment" onClick="addcomment(<%out.print("'yuli'");%>);return false;"/>
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
<script type="text/javascript" language="javascript">		
    document.onscroll = function(){
        //alert("+: "+(window.pageYOffset + window.innerHeight)+"hegi:"+document.body.offsetHeight);
        if ((window.scrollY + window.innerHeight) >= document.body.offsetHeight){
            doSearch(document.getElementById('filter1').value, document.getElementById('keyword1').value, document.getElementById('user').value, 'true');
        }
		
		
    }
</script>
        
    </body>
</html>

