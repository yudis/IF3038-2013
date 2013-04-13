<%@include file="header.jsp"%>
<%
int id_cat = Integer.parseInt(request.getParameter("id"));
%>
<div id="main">
	<div id="konten">
		<div class="atas">
		</div>
		<div class="tengah">
			<div class="judul" id="headerpostkonten">
				Post Tugas
			</div>
			<div class="isi">
				<script type="text/javascript" src="mainpage.js"></script>
				<form action="task.jsp" method="post" enctype="multipart/form-data">
                	<input type="hidden" name="id" value="<% out.print(id_cat); %>">
					<div class="register-label">Judul</div><div class="register-td">:</div><div class="register-input"><input class="register-input-input" type="text" name="judul" id="judul" onkeyup = "validatePostTask()" maxlength="25"/>
                    <div><label id="judulvalidate"> </label></div></div>
					<div class="clear error" id="error-username"></div>
					
                    
                    
                    <div class="register-label">Jenis konten</div><div class="register-td">:</div><div class="register-input">
                        <input type="radio" name="file" id="tipe" value="file"  /> File 
                        <input type="radio" name="file" id="tipe" value="image" /> Gambar 
                        <input type="radio" name="file" id="tipe" value="video" /> Video</div>
					<div class="clear" id="error-sex"></div>
					<div id="opt"></div>
					
					<div>
						<div class="register-label">File upload</div><div class="register-td">:</div><div class="register-input"><input class="register-input-input" type="file" name="xxx" id="kontenavatar" />
                        </div>
						<div class="clear" id="error-gambar"></div>
					</div>
                    
                    
                    
                    
					<div class="register-label">Deadline</div>
					<div class="register-td">:</div>
					<div class="register-input">
						<input class="register-input-input" type="text" name="deadline" id="form-tgl" onkeyup="validate()" />
					</div>
					<div id="caldad">
						<div id="calendar"></div>
						<a href="javascript:showcal(2,2012);void(0);"><img src="images/cal.gif" alt="Calendar" /></a>
					</div>
                    
                   
					<div class="clear" id="error-tgl"></div>
                    
                    <div class="register-label"></div><div class="register-td">: </div><div class="register-input"> <div><input type="time" name="time" /></div></div><div id="caldad"><div id="calendar"></div></div>
                    
                   
					<div class="clear" id="error-tgl"></div>
                    
					<div class="register-label">Asignee</div><div class="register-td">:</div><div class="register-input"><input class="register-input-input" type="text" name="asignee" id="asignee"/></div>
                    
					<div class="clear error" id="error-username"></div>
                    
					<div class="register-label">Tag</div><div class="register-td">:</div>
                    <div class="register-input"><input class="registerinput-input"- type="text" name="tag" id="judul"/><ul><div><a href="profil.jsp"></a></div>
					</ul></div>
												
					<div class="post-register-submit"><input type="submit" id = "post1" name="post" value="OK" onclick="postContent();"/></div>
				</form>
			</div>
			
			<div id="afterpost">
				<div class="judul" id="apjudul"></div>
				<div id="ap-artikel">
					<div id="ap-kontenartikel"></div>
					<div id="ap-kontendeskripsi"></div>
				</div>
				<div id="ap-avatar">
					<div id="kontengambar"><img src="images/oke.png" alt="Konten" /></div>
				</div>
				<div id="ap-youtube">
				</div>
				<div class="ap-submit"><input type="button" name="continue" value="Continue" onclick="refreshPage()"/></div>
			</div>
			
		</div>
		<div class="bawah">
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>