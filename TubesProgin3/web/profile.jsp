
<%-- 
    Document   : profile
    Created on : Apr 3, 2013, 2:28:46 PM
    Author     : Yulianti Oenang
--%>
<%@page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="tubes3.profile"%>
<!DOCTYPE html>
<jsp:include page="/header.jsp" />
<script src="editprofile.js" type="text/javascript" language="javascript"> </script>

<%
String user;boolean editable;
if(request.getParameter("username")==null)
       {
           user=(String)((HttpServletRequest) request).getSession().getAttribute("bananauser");            
           editable=true;
       }
else
       {
    user=request.getParameter("username");
    editable=false;
}
  

profile p=new profile(user); 
//HttpSession session = request.getSession(false);
if (((HttpServletRequest) request).getSession().getAttribute("flag") != null)
       {
         Integer flag= (Integer) session.getAttribute("flag");
            if(flag== 1)
            {
                out.print("<script type=\"text/javascript\">notifikasi();</script>");
            }
         else
            {
                out.print ("<script type=\"text/javascript\">notifikasiquery();</script>");
            }
         ((HttpServletRequest) request).getSession().setAttribute("flag",null);
            
       }
else
       {
}

%>
<!-- Foto profile -->
        <div id="isi">
                <div id="leftsidebar">
					<img id="leftsidebar" class="foto" src=<%out.print(p.avatar);%> alt="Smiley face"/>
					<b><%out.print(p.username); %></b>
				</div>

                <div id="rightsidebar">
                        <ul class="prof">
                                <h1 align="left">Profile</h1>
                                <li>
                                        <label>Nama Lengkap:</label>
                                        <p id="namalengkap" class="prof1"><% out.print(p.fullname); %></p>
                                </li>
                                <li>
                                        <label>Email:</label>
                                        <p class="prof1"><% out.print(p.email); %></p>
                                </li>
                                <li>
                                        <label>Tanggal Lahir:</label>
                                        <p class="prof1"><% String date[]=p.birthday.split("-"); out.print(date[2]+"-"+date[1]+"-"+date[0]); %></p>
                                        
                                </li>
                                <li>
                                        <label>On going tasks:</label>
                                        <% 
                                        for (int i=0;i<p.tugasBelumSelesai.size();i++)
                                        {   out.print("<p class=\"prof1\">");
                                            out.print(p.tugasBelumSelesai.get(i));
                                            out.print("</p>");
                                        }  
                                        %>
                                </li>
                                <li>
                                        <label>Tasks done:</label>
                                        <% 
                                        for (int i=0;i<p.tugasSelesai.size();i++)
                                        {   out.print("<p class=\"prof1\">");
                                            out.print(p.tugasSelesai.get(i));
                                            out.print("</p>");
                                        }  
                                        %>
                                </li>
                                <%
                               if(editable==true)
				{
                                    out.print("<li>");
                                    out.print("<button class=\"reg\" type=\"button\" onclick=\"location.href='editprofile.jsp'\"><b>Edit</b></button>");
                                    out.print("</li>");
				}
                              %>
                        </ul>
                </div>
        </div>

        <div id="footer" class="home">
                <p>&copy Copyright 2013. All rights reserved<br>
                Chalkz Team<br>
                Yulianti - Raymond - Devin</p>			
        </div>
</div>
</body>
        
</html>
