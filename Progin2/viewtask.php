<html>

<?php
session_start();
if(!isset($_SESSION['id']))
	header("location:index.php");
?>

	<head>
		<title>View Task</title>
		<link href="styles/viewtask.css" rel="stylesheet" type="text/css" />
		<link href="styles/header.css" rel="stylesheet" type="text/css" />
		
		<script>
			var attach = "videos/attachmentsample.ogg";
			var tanggal = 1;
			var bulan = "Januari";
			var tahun = 2013;
			
			function editTag(e,uidtugas){
			var xmlhttp;
			var value;
			if (e && e.keyCode == 13) {
				value = document.getElementById("tag").value;
				if (window.XMLHttpRequest) {
				  // code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp=new XMLHttpRequest();
				}
				else {
				  // code for IE6, IE5
				  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange=function() {
				  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
					document.getElementById("tagvalue").innerHTML = value;
					showHide("tag");
					showHide("tagvalue");
					renameButton("tagbutton");
					}
				}
				xmlhttp.open("GET","edittag.php?q="+uidtugas+"&p="+value,true);
				xmlhttp.send();
				}
			}
			
			function editDeadline(uidtugas){
			var xmlhttp;
			var tgl=document.getElementById("tgl");
			var bln=document.getElementById("bln");
			var thn=document.getElementById("thn");
			if (window.XMLHttpRequest) {
			  // code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			}
			else {
			  // code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange=function() {
			  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				document.getElementById("dlvalue").innerHTML = tgl.options[tgl.selectedIndex].text+"-"+bln.options[bln.selectedIndex].text+"-"+thn.options[thn.selectedIndex].text;
				}
			}
			xmlhttp.open("GET","editdeadline.php?q="+uidtugas+"&tgl="+tgl.options[tgl.selectedIndex].text+"&bln="+bln.options[bln.selectedIndex].text+"&thn="+thn.options[thn.selectedIndex].text,true);
			xmlhttp.send();
			}
			
			function addAssignee(uidtugas,username) {
				var xmlhttp;
				var assignee = document.getElementById("as").value;
				if (window.XMLHttpRequest) {
				  // code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp=new XMLHttpRequest();
				}
				else {
				  // code for IE6, IE5
				  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange=function() {
				  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
					document.getElementById("asvalue").innerHTML = xmlhttp.responseText;
					document.getElementById("daftarassignee").innerHTML = "<option value=shit>Shit</option>";
					}
				}
				xmlhttp.open("GET","addassignee.php?q="+uidtugas+"&p="+assignee,true);
				xmlhttp.send();
			}
			
			function hapusAssignee(uidtugas) {
				var xmlhttp;
				var selection = document.getElementById("fieldhapus");
				if (window.XMLHttpRequest) {
				  // code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp=new XMLHttpRequest();
				}
				else {
				  // code for IE6, IE5
				  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange=function() {
				  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
					document.getElementById("asvalue").innerHTML=xmlhttp.responseText;
					}
				}
				xmlhttp.open("GET","hapusassignee.php?q="+uidtugas+"&p="+selection.options[selection.selectedIndex].text,true);
				xmlhttp.send();
			}
			
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
			
			function renameButton2(id){
				var elem = document.getElementById(id);
				if (elem.value=="Add") elem.value = "Cancel";
				else elem.value = "Add";
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
			
			function showComment(comment, idtask, username){
				var xmlhttp;
				if (window.XMLHttpRequest) {
				  // code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp=new XMLHttpRequest();
				}
				else {
				  // code for IE6, IE5
				  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange=function() {
				  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
					document.getElementById("comment").innerHTML=xmlhttp.responseText;
				  }
				}
				xmlhttp.open("GET","listcomment.php?q="+comment+"&r="+idtask+"&s="+username,true);
				xmlhttp.send();
			}
			
			function myFunction(comment, idtask, username, pagenum)
			{
			setInterval(function() {
				var xmlhttp;
				if (window.XMLHttpRequest) {
				  // code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp=new XMLHttpRequest();
				}
				else {
				  // code for IE6, IE5
				  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange=function() {
				  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
					document.getElementById("comment").innerHTML=xmlhttp.responseText;
				  }
				}
				xmlhttp.open("GET","listcomment.php?q="+comment+"&r="+idtask+"&s="+username+"&pagenum="+pagenum,true);
				xmlhttp.send();
				},500);
			}
			function suggestion(){
				
				var suggest = document.getElementById("as").value;
				var xmlhttp;
				document.getElementById("opsi").innerHTML="";
				if (suggest.length==0) { 
					  document.getElementById("opsi").innerHTML="";
					  return;
				}
				if (window.XMLHttpRequest) {
				  // code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp=new XMLHttpRequest();
				}
				else {
				  // code for IE6, IE5
				  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange=function() {
				  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
					document.getElementById("opsi").innerHTML=xmlhttp.responseText;
				  }
				  
				}
				
				xmlhttp.open("GET","functionsuggest.php?suggest="+suggest,true);
				xmlhttp.send();
			}
			</script>
	</head>

	<body onload="makeTgl();makeThn();myFunction(document.addcomment.isicomment.value,
	<?php
	echo "'".$_GET['q']."'";
	?>
	,
	<?php
	echo "'".$_SESSION['id']."'";
	?>
	,
	<?php
	echo "'".$_SESSION['pagenum']."'";
	?>
	);">
		<div id="container">
			<div id="header">
				<div class=logo id="logo">
					<a href="dashboard.html"><img src="images/logo.png" title="Home" alt="Home"/></a>
				</div>
				<div id="space">
				</div>
				<div class = "menu" id = "search">
				 <form name="search" method="post" action="search.php">
					 Search for: <input type="text" name="find" /> in 
					 <Select NAME="field">
					 <Option VALUE="semua">Semua</option>
					 <Option VALUE="username">Username</option>
					 <Option VALUE="namakategori">Judul Kategori</option>
					 <Option VALUE="tasktag">Task atau Tag</option>
					 </Select>
					 <input type="hidden" name="searching" value="yes" />
					 <button type="submit" id="searchbutton"></button>
				 </form>
			</div>
				<div class="menu" id="logout" action="logout.php">
					<a href="index.php">Logout</a>
				</div>
				<div class="menu" id="home">
				<a href="dashboard.php">Home</a>
			</div>
			<div class="menu" id="profile">
				<a href="profile.php">
				<img alt="" height=25 width=25 src="<?php
					$con = mysql_connect("localhost:3306","root","");
					if (!$con)
					  {
					  die('Could not connect: ' . mysql_error());
					  }

					mysql_select_db("progin_405_13510057", $con);
				
					$result = mysql_query("SELECT * FROM user WHERE username='$_SESSION[id]'");
					while($row = mysql_fetch_array($result)) 
						echo $row['avatar'];
				?>">
				Profile</a>
			</div>
			</div>
			<div id="leftspace">
				
			</div>
			<div id="viewtask">
				<form name=edit>
					<div class="form_field">
						<div class="viewtask_label">
							Task Name
						</div>
						<div class="viewtask_field">
							<?php
							$con = mysql_connect("localhost:3306","root","");
							if (!$con)
							  {
							  die('Could not connect: ' . mysql_error());
							  }

							mysql_select_db("progin_405_13510057", $con);
							$idtugas = $_GET["q"];
							
							$tugas = mysql_query("SELECT * FROM tugas WHERE idtugas='$idtugas'");
							$row = mysql_fetch_array($tugas);
							echo $row['namatugas'];
							?>
						</div>
					</div>
					<div class="form_attachment">
					<?php
					$attachment = mysql_query("SELECT isiattachment FROM attachment WHERE idtugas='$idtugas'");
					$row = mysql_fetch_array($attachment);
					$ext = pathinfo($row['isiattachment'], PATHINFO_EXTENSION);
					if ($ext == "jpg" || $ext == "jpeg" || $ext == "bmp")
						echo "<img id=image src=\"".$row['isiattachment']."\" width=320 alt=\"\"/>";
					else if ($ext == "ogg" || $ext == "webm" || $ext == "3gp")
						echo "<video id=video width=320 src=\"".$row['isiattachment']."\"  controls onError=\"this.style.display = 'none';\">";
					?>
							</video>
					</div>
					<div class="form_field">
						<div class="viewtask_label">
							Attachment
						</div>
						<?php
							echo "<form name=viewtask_form method=post action=addattachment.php?q=".$idtugas." enctype=\"multipart/form-data\">";
						?>
						<div class="viewtask_field">
							<?php
							$attachment = mysql_query("SELECT isiattachment FROM attachment WHERE idtugas='$idtugas'");
							while($row = mysql_fetch_array($attachment))
								echo "<a id=link href=\"".$row['isiattachment']."\" target=\"_blank\">".$row['isiattachment']."</a>";
							?>
						</div>
						</form>
					</div>
					<div class="form_field">
						<div class="viewtask_label">
							Deadline
						</div>
						<div class="viewtask_field">
							<div>
								<p id="dlvalue">
								<?php
								$tugas = mysql_query("SELECT * FROM tugas WHERE idtugas='$idtugas'");
								$row = mysql_fetch_array($tugas);
								echo $row['deadline'];
								?>
								</p>
							</div>
							<div id="dl">
								<select name="tgl" id="tgl">
								</select>
								<select name="bln" id="bln">
									<option value="January">January</option>
									<option value="February">February</option>
									<option value="March">March</option>
									<option value="April">April</option>
									<option value="May">May</option>
									<option value="June">June</option>
									<option value="July">July</option>
									<option value="August">August</option>
									<option value="September">September</option>
									<option value="October">October</option>
									<option value="November">November</option>
									<option value="December">December</option>
								</select>
								<select name="thn" id="thn">
								</select>
							</div>
						</div>
						<div class="viewtask_edit">
							<input type=button value="Edit" id="dlbutton" onClick="showHide('dl');showHide('dlvalue');showHide('dlbutton');showHide('savebutton')">
							<?php
							echo "<input type=button value=\"Save\" id=\"savebutton\" onClick=\"editDeadline('".$idtugas."');showHide('dl');showHide('dlvalue');showHide('dlbutton');showHide('savebutton');getDateValue();setDateValue()\">";
							?>
						</div>
					</div>
					<div class="form_field">
						<div class="viewtask_label">
							Assignee
						</div>
						<div class="viewtask_field">
							<p id="asvalue">
							<?php
							$assignee = mysql_query("SELECT username FROM assignee WHERE idtugas='$idtugas'");
							$count = mysql_num_rows($assignee);
							while($row = mysql_fetch_array($assignee)){
								if ($count > 1)
									echo "<a href=\"profilesearch.php?idsearch=".$row['username']."\">".$row['username']."</a>".", ";
								else 
									echo "<a href=\"profilesearch.php?idsearch=".$row['username']."\">".$row['username']."</a>";
								$count--;
							}
							?>
							</p>			
						</div>
						<div class="viewtask_edit">
							<input type=text name=as id="as" type="text" tabindex="4" list="user" onKeyUp="suggestion()" onKeyPress="checkEdit(event,'as','asvalue','asbutton')" list="suggest"/>
							<label id="opsi"></label>
							<?php
							echo "<input type=button value=\"Add\" id=\"asbutton\" onclick=\"addAssignee(".$idtugas.")\">";
							?>
							<select id="fieldhapus" name="fieldhapus">
							<div id="daftarassignee">
							<?php
							$assignee = mysql_query("SELECT username FROM assignee WHERE idtugas='$idtugas'");
							while($row = mysql_fetch_array($assignee)){
								echo "<option value=\"".$row['username']."\">".$row['username']."</option>";
							}
							?>
							</div>
							 </select>
							<?php
							echo "<input type=button value=\"Hapus\" id=\"hapusbutton\" onclick=\"hapusAssignee(".$idtugas.")\">";
							?>
						</div>
						
					</div>
					<div class="form_field">
						<div class="viewtask_label">
							Tag
						</div>
						<div class="viewtask_field">
							<p id="tagvalue">
							<?php
							$isitag = '';
							$tag = mysql_query("SELECT isitag FROM tag WHERE idtugas='$idtugas'");
							$count = mysql_num_rows($tag);
							while($row = mysql_fetch_array($tag)){
								if ($count > 1)
									$isitag .= $row['isitag'].", ";
								else 
									$isitag .= $row['isitag'];
								$count--;
							echo $isitag."</p>";
							}
							echo "<input type=text name=tag id=\"tag\" onKeyPress=\"editTag(event,".$idtugas.")\">";
							?>
						</div>
						<div class="viewtask_edit">
							<input type=button value="Edit" id="tagbutton" onClick="showHide('tag');showHide('tagvalue');renameButton('tagbutton');clearContents('tag')">
						</div>
					</div>
					<div class="form_field">
						<div class="viewtask_label">
							Status
						</div>
						<div class="viewtask_field">
						<?php
						$result = mysql_query("SELECT status FROM tugas WHERE idtugas = '$idtugas'");
						$row = mysql_fetch_array($result);
						if ($row['status'] == "done") {
							$status = "checked";
							echo "Done ";
						}
						else {
							$status = '';
							echo "Undone ";
						}
						echo "<input type=checkbox name=\"status\" value=\"done\" ".$status."/ onchange=\"location.href='changestatusview.php?q=".$idtugas."'\">";
						?>
						</div>
					</div>
				</form>
				<?php
				echo "<button onclick=\"location.href='deletetask.php?q=".$idtugas."'\">Hapus Task...</button>";
				?>
			</div>
			<div id="rightspace">
				<script type="text/javascript">
					
				</script>
				<div>
					<div>
						<fieldset id="fieldset">
							<legend id="commentLabel">Comment</legend>
							<div class="comment" id="comment">
							
							</div>
						</fieldset>
					</div>
				</div>
			</div>
			<div id="rightspace2">
				<form name="addcomment" method="post">
				<div>
					<textarea id="commentField" name="isicomment" rows="3" cols="30" onfocus="this.value='';"></textarea>
				</div>
				<div>
					<input type=button value="Submit Comment" onClick="showComment(document.addcomment.isicomment.value,
					<?php
					echo "'".$_GET['q']."'";
					?>
					,
					<?php
					echo "'".$_SESSION['id']."'";
					?>
					,
					<?php
					echo "'".$_SESSION['pagenum']."'";
					?>
					)">
				</div>
				</form>
			</div>
		</div>
	</body>
</html>