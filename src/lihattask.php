<html>
    <head>
        <title> Next | Task Detail </title>
        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="css/buattask.css">
        <script>
            function showEdit(){
                document.getElementById("detailedit").style.visibility="visible";
                document.getElementById("detail").style.visibility="hidden";
            }
            function hideEdit(){
                document.getElementById("detailedit").style.visibility="hidden";
                document.getElementById("detail").style.visibility="visible";
            }
			
				   
        </script>
		<?php
			/* 
			 * 
			 * link ke profile
			 * attachment
			 * 
			 * 
			 * 
			 * 
			 */	
		?>
    </head>
    <body onload="">
		
		<!--header (dashboard)-->
		<?php require_once("header.php"); ?>
		
        
        <div id="kolomwrapper">
			<div id="kolom1" class="kolom">
				<div class="tombol kotakwarna" id="edit" onclick="showEdit()">
				</div>
				<a href="dashboard.html">
					<div class="tombol kotakwarna" id="back">
					</div>
				</a>
			</div>
			<div id="kolom2" class="kolom">
				<div id="rinciantugas" class="kotakwarna">
					<div id="judultugas"><?php echo $_COOKIE["lt_tugas"]["nama"];?></div>
					<div id="detail" style="visibility: visible">
						
							<label>DEADLINE</label>
							<a id="deadline"><?php echo $_COOKIE["lt_tugas"]["deadline"];?></a>
							
							<label>ASSIGNEE</label>
							<?php
								foreach ($_COOKIE["lt_assignee"] as $x) {
							?>
							<a href ="#">
								<?php echo $x["username"];?>
							</a><br>
							<?php
								}
							?>
							
							<label>TAG</label>
							<a id="tag">KAP</a>
							
							
						
					</div>
					<!--<div id="detailedit" style="display: none">
						<form >
							<label>DEADLINE</label>
							<a id="deadline">11/08/2013</a>
							
							<label>ASSIGNEE</label>
							<a id="assignee">Faiz</a></br>
							<input name="assignee" placeholder="assignee">
							
							<label>TAG</label>
							<input name="catname" placeholder="tag">
							
							<label>ATTACHMENT</label>
							<a href ="#" id="attach">Download Here</a>
							</br>
							<input class= "submitreg" name="submit" type="submit" value="Submit" onclick="hideEdit()">
						</form>
					</div>-->
				</div>
				<div id="komen">
                <div class="daftarkomen kotakwarna">
                   Faiz :<br>
                   Gimana nih udah kelar belum tubesnya ?
                </div>
                <div class="daftarkomen kotakwarna">
                   Fandi :<br>
                   Bentar nih gw masih main tenis
                </div>
                <div class="daftarkomen kotakwarna">
                   Faiz :<br>
                   ah yang bener lah
                </div>
                <form>
                    <div id="submitkomen">
                        <label>submit comment</label>
                        <textarea name="comment" type="comment" placeholder="comment" class="isikomen"></textarea>
						<br>
                        <input class= "submitreg" name="submit" type="submit" onclick="http://google.com" value="Submit">
                    </div>
                </form>
            </div>
			
			
			</div>
			<div id="kolom3" class="kolom kotakwarna">
				<div id="divattachment">
				<label>ATTACHMENT</label>
					<?php
						$extgambar = array("jpg", "jpeg", "gif", "png");
						$extvideo = array("avi", "mp4", "flv", "3gp", "wmv");
						foreach ($_COOKIE["lt_attachment"] as $x) {
							$extension = end(explode(".", $x["nama"]));
							if (in_array($extension, $extgambar)) {
								echo '
									<a href ="#" id="">
										<img class="attachment" src="'.$x["path"].$x["nama"].'"/>
									</a>
								';
							} else
							if (in_array($extension, $extvideo)) {
								echo '
									<a href ="#" id="">
										<video class="attachment" controls>
											<source src="'.$x["path"].$x["nama"].'">
										</video> 
									</a>
								';
							}
						}
					?>
				</div>
			</div>
		</div>
		
		<!--header (dashboard)-->
		<?php require_once("footer.php"); ?>
		
    </body>
</html>