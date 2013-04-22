<%-- 
    Document   : tugas
    Created on : Apr 11, 2013, 5:24:19 PM
    Author     : Adriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Todolist</title>
        <link rel="stylesheet" type="text/css" href="default.css" />
        <link rel="stylesheet" type="text/css" href="mediaqueries.css" />
        <link rel="stylesheet" type="text/css" href="styles/tugas.css" />
        <script src="scripts/tugas.js" type="application/javascript"></script>
        <script src="scripts/helper.js" type="application/javascript"></script>
    </head>
    <body onload="initializetugas()">
        <div class="page">
            <header class="content">
                <%@include file="header.jsp" %>
            </header>
            <div class="content">
                <h1>Rincian Tugas</h1>
                <div class="padding12px">
                    <div action="#">
                        <div class="rincianLabel">Nama:</div><div class="rincianDetail"><div id="namaTugas"></div></div>
						<div class="rincianLabel">Status:</div><div class="rincianDetail"><input id="checkstatus" type="checkbox" onchange="changeCheckBox()" disabled /> <span id="statustext">Belum Selesai</span></div>
                        <div class="rincianLabel">Attachment:</div><div class="rincianDetail"><div id="links" class="inlineblock"><a href="files/test.zip" target="_blank">test.zip</a></div></div>
                        <div class="rincianLabel">Deadline:</div><div class="rincianDetail">
                            <div id="deadlineDisplayDiv" class="inlineblock"></div>
                            <div id="deadlineEditDiv">
                                <input type="date" id="deadline" name="deadline" value="2013-02-22" onchange="changeMadeTrue()"/>
                            </div>
                        </div>
                        <div class="rincianLabel">Assignee:</div><div class="rincianDetail">
                            <ul id="assigneesList" class="tag"></ul>
                            <div id="assigneeEditDiv">
                                <input type="text" id="assignee" name="assignee" list="user" />
                                <datalist id="user">
                                    <option value="Abraham Krisnanda Santoso">
                                    <option value="Edward Samuel Pasaribu">
                                    <option value="Stefanus Thobi Sinaga">
                                </datalist>
                                <button onclick="return addAssignees();">Add</button>
                            </div>
                        </div>
                        <div class="rincianLabel">Tags:</div><div class="rincianDetail">
                            <div id="tagsDisplayDiv">
                                <ul id="tagsList" class="tag">
                                </ul>
                            </div>
                            <div id="tagsEditDiv">
                                <input type="text" id="tags" name="tags" value="IF3030" list="tagtag" onchange="changeMadeTrue()"/>
								<datalist id="tagtag">
									<option value="Informatika">
                                    <option value="Tubes">
                                    <option value="Tucil">
								</datalist>
                            </div>
                        </div>
                        <br /><br />
                        <button id="editButton" onclick="return editTugas();">Edit</button><button id="doneButton" onclick="return saveTugas();">Done</button>
                        <br />
						<div id="imagenvideo"></div>
						<br /><br />
                        <div id="commentlabel" class="rincianLabel">Komentar:</div>
						<br /><br />
						<div class="rincianDetail">
                            <div id="commentspace">
								<div id="komentar">
									<!--<img id="pp" src="images/a.png" width="50" height="50" alt="Image not found"/><b>Abraham Krisnanda Santoso</b> - 20 Februari 2013 23:06 <button id="">Delete Comment</button><hr />Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer tortor nunc, posuere sit amet facilisis quis, ullamcorper nec sapien.<br /><br />
									<img id="pp" src="images/a.png" width="50" height="50" alt="Image not found"/><b>Stefanus Thobi Sinaga</b> - 20 Februari 2013 23:06 <button id="">Delete Comment</button><hr />Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer tortor nunc, posuere sit amet facilisis quis, ullamcorper nec sapien. <br /><br />
							-->	</div>
								<textarea id="txtKomentar" rows="4" cols="80" placeholder="Your comment here..."></textarea><br />
							</div>
							<button onclick="return addKomentar();">Submit</button>
						</div>
                    </div>
                </div>
                <br />
            </div>
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.<br />
                IF3094 - Pemrograman Internet.
            </footer>
        </div>
	
    </body> 
</html>
