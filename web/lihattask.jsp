<%-- 
    Document   : lihattask
    Created on : Apr 11, 2013, 7:42:06 PM
    Author     : PANDU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="Servlets.viewtask" %>
<%
    viewtask vt = new viewtask(request.getParameter("id"));
%>
<!DOCTYPE html>
<html>
	<head>
		<title>View Task</title>
		<link href="styles/viewtask.css" rel="stylesheet" type="text/css" />
		<link href="styles/header.css" rel="stylesheet" type="text/css" />
		<script>
			var attach = "videos/attachmentsample.ogg";
			var tanggal = 1;
			var bulan = "Januari";
			var tahun = 2013;
			function makeTgl(){
				for(var i=1; i<=31; i++){
					var isi=document.createTextNode(i);
					var opsi = document.createElement("option");
					opsi.setAttribute("value",i);
					opsi.appendChild(isi);
					document.getElementById("tgl").appendChild(opsi);
				}
			}
				
			function makeThn(){
				for(var i=1955; i<=2013; i++){
					var isi=document.createTextNode(i);
					var opsi = document.createElement("option");
					opsi.setAttribute("value",i);
					opsi.appendChild(isi);
					document.getElementById("thn").appendChild(opsi);
				}
			}
			
			function getStyle(el, name)
			{
			  if ( document.defaultView && document.defaultView.getComputedStyle )
			  {
				var style = document.defaultView.getComputedStyle(el, null);
				if ( style )
				  return style[name];
			  }
			  else if ( el.currentStyle )
				return el.currentStyle[name];

			  return null;
			}

			function showHide(a){
			  var e=document.getElementById(a);
			  if(!e)return true;
			  if(getStyle(e, "display") == "none"){
				e.style.display="inline"
			  } else {
				e.style.display="none"
			  }
			  return true;
			}
			
			function renameButton(id){
				var elem = document.getElementById(id);
				if (elem.value=="Edit") elem.value = "Cancel";
				else elem.value = "Edit";
			}
			
			function clearContents(id) {
				document.getElementById(id).value = '';
			}
			
			function editValue(id1,id2){
				var value = document.getElementById(id1).value;
				document.getElementById(id2).innerHTML = value;
			}
			
			function setDateValue() {
				document.getElementById("dlvalue").innerHTML = tanggal+"-"+bulan+"-"+tahun;
			}
			
			function getDateValue() {
				tanggal = document.edit.tgl.value;
				bulan = document.edit.bln.value;
				tahun = document.edit.thn.value;
			}
			
			function checkEdit(e,id1,id2,id3) {
				if (e && e.keyCode == 13) {
					editValue(id1,id2);
					showHide(id1);
					showHide(id2);
					renameButton(id3);
				}
			}
			
			function checkSubmit(e) {
				if (e && e.keyCode == 13) {
					displayComment();
				}
			}
			
			function displayComment(){
				var newpara = document.createElement("P")
				text = document.createTextNode(commentField.value);
				newpara.appendChild(text)
				document.getElementById("comment").appendChild(newpara);
				commentField.value = '';
			}
			
			</script>
	</head>

	<body onload="makeTgl();makeThn()">
		<div id="container">
			<div id="header">
				<div class=logo id="logo">
					<a href="dashboard.html"><img src="images/logo.png" title="Home" alt="Home"/></a>
				</div>
				<div id="space">
				</div>
				<div id="search">
					<input type="text" name="search" id="searchbox">
					<button type="submit" id="searchbutton"></button>
				</div>
				<div class="menu" id="logout">
					<a href="index.html">Logout</a>
				</div>
				<div class="menu" id="profile">
					<a href="profile.html">Profile</a>
				</div>
				<div class="menu" id="home">
					<a href="dashboard.html">Home</a>
				</div>
			</div>
			<div id="leftspace">
                <img src="" height="250px"/>
			</div>
			<div id="viewtask">
				<form name=edit>
					<div class="form_field">
						<div class="viewtask_label">
							Task Name
						</div>
						<div class="viewtask_field">
							<a><%= vt.getTask()[1] %></a>
						</div>
					</div>
					<div class="form_field">
						<div class="viewtask_label">
							Deadline
						</div>
						<div class="viewtask_field">
							<div>
<!--								<p id="dlvalue"><script>document.write(tanggal+"-"+bulan+"-"+tahun)</script></p>-->
                                <a><%= vt.getTask()[2] %></a>
							</div>
							<div id="dl">
								<select name="tanggal" id="tgl">
								</select>
								<select name="bulan" id="bln">
									<option value="Januari">Januari</option>
									<option value="Februari">Februari</option>
									<option value="Maret">Maret</option>
									<option value="April">April</option>
									<option value="Mei">Mei</option>
									<option value="Juni">Juni</option>
									<option value="Juli">Juli</option>
									<option value="Agustus">Agustus</option>
									<option value="September">September</option>
									<option value="Oktober">Oktober</option>
									<option value="November">November</option>
									<option value="Desember">Desember</option>
								</select>
								<select name="tahun" id="thn">
								</select>
							</div>
						</div>
						<div class="viewtask_edit">
							<input type=button value="Edit" id="dlbutton" onClick="showHide('dl');showHide('dlvalue');showHide('dlbutton');showHide('savebutton')">
							<input type=button value="Save" id="savebutton" onClick="showHide('dl');showHide('dlvalue');showHide('dlbutton');showHide('savebutton');getDateValue();setDateValue()">
						</div>
					</div>
					<div class="form_field">
						<div class="viewtask_label">
							Assignee
						</div>
						<div class="viewtask_field">
							<p id="asvalue">
                            <% 
                                for (String[] temp : vt.getAssignee()) { 
                                    out.print("<a>"+temp[1]+"</a><br>");
                                } /**/
                            %>
                            </p>
							<input type=text name=as id="as" onKeyPress="checkEdit(event,'as','asvalue','asbutton')" list="suggest"/>
							<datalist id="suggest">
								<option value="Mario Orlando">
								<option value="Luthfi Chandra">
								<option value="Rubiano Adityas">
							</datalist>
							
						</div>
						<div class="viewtask_edit">
							<input type=button value="Edit" id="asbutton" onClick="showHide('as');showHide('asvalue');renameButton('asbutton');clearContents('as')">
						</div>
						
					</div>
					<div class="form_field">
						<div class="viewtask_label">
							Tag
						</div>
						<div class="viewtask_field">
							<p id="tagvalue">
                            <% 
                                for (String[] temp : vt.getTag()) { 
                                    out.print("<a>"+temp[1]+"</a><br>");
                                } /**/
                            %>
                            </p>
							<input type=text name=tag id="tag" onKeyPress="checkEdit(event,'tag','tagvalue','tagbutton')">
						</div>
						<div class="viewtask_edit">
							<input type=button value="Edit" id="tagbutton" onClick="showHide('tag');showHide('tagvalue');renameButton('tagbutton');clearContents('tag')">
						</div>
					</div>
                            
					<div class="form_field">
						<div class="viewtask_label">
							Comment
						</div>
                        <br>
                        <br>
						<div id="viewtask_komen" class="viewtask_field">
                            <% 
                                String[][] komentator = new String[100][];
                                        komentator = vt.getKomentator();
                                int iii = 0;
                                for (String[] temp : vt.getKomen()) { 
                            %>
                                    <div class="kotakwarna daftarkomen ">
                                        <div>
                                            <div class="komenkolom">
                                                <a href=""><img class="komenava ava" src="<%= komentator[iii][6] %>"/></a>
                                            </div>
                                            <div class="komenkolom komenkomen">
                                                <a href="#" class="komennama"><%= komentator[iii][1] %></a>
                                                <p><%= temp[1] %></p>
                                            </div>
                                        </div>
                                        <div class="komenwaktu">
                                            <a><%= temp[4] %></a>
                                        </div>
                                    </div>
                            <%
                                    iii++;
                                } /**/
                            %>
						</div>
                        <div>
<!--                            <div>
                                <fieldset id="fieldset">
                                    <legend id="commentLabel">Comment</legend>
                                    <p id="comment">Deadlinenya sebentar lagi nih!</p>
                                </fieldset>
                            </div>-->
                        </div>
                        <div>
                            <div>
                                <textarea id="commentField" rows="3" cols="30" onKeyPress="checkSubmit(event)"></textarea>
                                <input type=button value="Submit Comment" onClick="displayComment()">
                            </div>
                        </div>
					</div>
                    
				</form>
			</div>
			<div id="rightspace">
                <div class="form_field">
                    <div class="viewtask_label">
                        Attachment
                    </div><br>
                    <div class="viewtask_field">
                        <div class="form_attachment">
                                <% 
                                    for (String[] temp : vt.getAttachment()) { 
                                        if ((temp[2].contains(".mp4"))
                                                ||(temp[2].contains(".avi"))
                                                ||(temp[2].contains(".flv"))
                                                ||(temp[2].contains(".3gp"))
                                                ||(temp[2].contains(".wmv"))) {
                                            out.print("<video class='vidattach' src='"+temp[1]+temp[2]+"' controls onError='this.style.display = /'none/'';></video>");
                                        } else 
                                        if ((temp[2].contains(".doc"))
                                                ||(temp[2].contains(".pdf"))) {
                                            out.print("<a href='"+temp[1]+temp[2]+"'>"+temp[2]+"</a>");                                    
                                        } else 
                                        {
                                            out.print("<img class='imgattach' src='"+temp[1]+temp[2]+"'/>");                                    
                                        }
                                    } /**/
                                %>
                        </div>
                    </div>
                </div>
			</div>
		</div>
	</body>
</html>