
<%-- 
    Document   : profile
    Created on : Apr 3, 2013, 2:28:46 PM
    Author     : Yulianti Oenang
--%>

<%@page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="tubes3.profile"%>
<!DOCTYPE html>
<jsp:include page="/header.jsp" />


<!-- Foto profile -->
        <div id="isi">
                <div id="leftsidebar">
					<img id="leftsidebar" class="foto" src=<% profile p=new profile(); out.print(p.avatar);%> alt="Smiley face"/>
					<b><%out.print(p.username); %></b>
				</div>

                <div id="rightsidebar">
                        <ul class="prof">
                                <h1 align="left">Profile</h1>
                                <li>
                                        <label>Nama Lengkap:</label>
                                        <p id="namalengkap" class="prof1"><% out.print(p.fullname); %></p>
                                </li>
                              
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
