<%-- 
    Document   : createtugas
    Created on : Apr 13, 2013, 5:08:19 AM
    Author     : sthobis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width; initial-scale=1.0" />
        <title>Todolist: Create Tugas</title>
        <link rel="stylesheet" type="text/css" href="default.css" />
        <link rel="stylesheet" type="text/css" href="mediaqueries.css" />
        <script src="scripts/formtugas.js" type="application/javascript"></script>
    </head>
    
    <body onload="initializeCreateTugas()">
        <div class="page">
            <header class="content">
                <%@include file="header.jsp" %>
            </header>
            <div class ="content">
                <h1>Buat Tugas Baru</h1>
                <div class="formtugas">
                    <form name="form" method="POST" onsubmit="parse()" action="addtugas" enctype="multipart/form-data">
                        <ul class="item">
                            <li id="folil1">
                                <label id="title1">Nama task:</label>
                                <div>
                                    <input id="namatask" class="regbox" name="namatask" type="text" maxlength="25" tabindex="1" pattern="[A-Za-z0-9 ]{1,25}"
                                           title="Nama task tidak diperbolehkan menggunakan karakter spesial" placeholder="Type task name here.."/>
                                </div>
                            </li>

                            <li id="folil2">
                                <label id="title2">Attachment:</label>
                                <div id="listattachment">
                                    	<input type="file" name="files1" accept="application/pdf,application/msword,image/*"/>
                                </div>
                                <div class="tag" onclick="addattachment()">Add Attachment!</div>
                            </li>
                            
                            <li id="folil3">
                                <label id="title3">Deadline:</label>
                                <div>
                                    <input id="deadline" class="regbox" name="deadline" type="date" tabindex="3"/>
                                </div>
                            </li>

                            <li id="folil4">
                                <label for="assignee">Assignee</label>:<br />
								<input id="assignee" name="assignee" class="inputbox regbox" type="text" placeholder="Type username here.." pattern="[A-Za-z0-9-, ]{1,}"  list="listuser" />
								<div id="datalistuser">
								</div>
							</li>

                            <li id="folil5">
                                <label id="title5">Tags:</label>
                                <div>
                                    <input id="tag" class="regbox" name="tag" type="text" tabindex="5" pattern="[A-Za-z0-9-, ]{1,}" placeholder="Separate multiple tags with comma.."/>
                                </div>
                            </li>

                            <li id="btn">
                                <div><br/>
                                    <input type="submit" value="Create Task!" class="button"/>
                                </div>
                            </li>
                        </ul>
                        <input type="hidden" id="n" value="1" name="n">
                        <input type="hidden" id="path" value="<%=getServletContext().getRealPath("/") %>" name="path">
                        <input type="hidden" id="uname" value="<%=request.getParameter("uname") %>" name="uname">
                        <input type="hidden" id="cat" value="<%=request.getParameter("cat") %>" name="cat">
                    </form>
                </div>
            </div>
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.</br>
                IF3094 - Pemrograman Internet.
            </footer>
        </div>

    </body>
 	
    
</html>
