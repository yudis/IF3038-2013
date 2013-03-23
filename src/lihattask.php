
		<?php
			
			/* 
			 * 
			 * link ke profile
			 * komen, siapa yang komen
			 * 
			 */	
		?>
		
		<html>
    <head>
        <title> Next | Task Detail </title>
        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="css/buattask.css">
        <link rel="stylesheet" href="css/calendar.css">
		<script src="js/calendar.js" > </script>
		<script src="js/lihattask.js" > </script>
        <script>
            function showEdit(){
                document.getElementById("detailedit").style.display="block";
                document.getElementById("detail").style.display="none";
            }
            function hideEdit(){
                document.getElementById("detailedit").style.display="none";
                document.getElementById("detail").style.display="block";
            }
			
				   
        </script>
		<script src="js/buattask.js"></script>
    </head>
    <body onload="">
		
		<!--header (dashboard)-->
		<?php require_once("header.php"); ?>
		
        
        <div id="kolomwrapper">
			<div id="kolom1" class="kolom">
				<div class="tombol kotakwarna" id="edit" onclick="showEdit()">
				</div>
				<a href="dashboard.php">
					<div class="tombol kotakwarna" id="back">
					</div>
				</a>
			</div>
			<div id="kolom2" class="kolom">
				<div id="rinciantugas" class="kotakwarna">
					<div id="judultugas"><?php echo $_COOKIE["lt_tugas"]["nama"];?></div>
					<div id="detail">
						<label>DEADLINE</label>
						<a id="deadline">
						<?php
							$datetime = strtotime($_COOKIE["lt_tugas"]["deadline"]);
							$mysqldate = date("d F Y", $datetime);
							echo $mysqldate;
						;?>
						</a>
						<br>
						<br>
						<label>ASSIGNEE</label>
						<div id="asgdivwrapper">
						<?php
							foreach ($_COOKIE["lt_assignee"] as $x) {
						?>
							<a href ="#">
								<div class="asgdiv">
									<img class="asgava asgdivelemt" src="<?php echo ($x['avatar']) ?>"/>
									<a href ="#" class="asgdivelemt"><?php echo $x["username"];?></a>
								</div>
							</a>
						<?php
							}
						?>
							<br>
						</div>
						<label>TAG</label>
						<?php
							foreach ($_COOKIE["lt_tag"] as $x) {
						?>
						<button class="btag" value=""><?php echo $x["nama"];?></button>
						<?php
							}
						?>
						<br>
					</div>
					<div id="detailedit">
						<form >
							<label>DEADLINE</label>
							<input type="text" name="deadline" id="iddeadline" value="<?php echo $_COOKIE["lt_tugas"]["deadline"];?>"/>
							<script type="text/javascript">
								calendar.set("iddeadline");
							</script>
							
							<label>ASSIGNEE</label>
							<input id="idasignee" autocomplete="off" name="namasign" placeholder="nama lengkap" onkeyup="showResult(this.value, 'hasil_autocomplete')"  value="<?php
								foreach ($_COOKIE["lt_assignee"] as $x) {
									echo $x["username"];
									echo ", ";
								}
							?>">
							<div id="hasil_autocomplete"></div>						  
							
							<label>TAG</label>
							<input id="idtag" autocomplete="off" value="<?php
								foreach ($_COOKIE["lt_tag"] as $x) {
									echo $x["nama"];
									echo ", ";
								}
							?>">
							</br>
							<input class='submitreg'
								   name='edit'
								   type='button'
								   value='EDIT'
								   onclick='updatetugas("<?php echo $_COOKIE["lt_tugas"]["idtugas"];?>"); hideEdit();'>
						</form>
					</div>
				</div>
				<div id="komen">
					<?php
						$count = 0;
						foreach ($_COOKIE["lt_komen"] as $x) {
							$datetime = strtotime($x['CREATED']);
							$mysqldate = date("H:i - d F", $datetime);
					?>
					<div class="kotakwarna daftarkomen ">
						<div>
							<div class="komenkolom">
								<a href=""><img class="komenava ava" src="<?php echo ($_COOKIE["lt_komentator"][$count]['avatar']) ?>"/></a>
							</div>
							<div class="komenkolom komenkomen">
								<a href="#" class="komennama"><?php echo ($_COOKIE["lt_komentator"][$count]['username'])."<BR>"; ?></a>
								<p><?php echo $x['isi']; ?></p>
							</div>
						</div>
						<div class="komenwaktu">
							<a><?php echo $mysqldate; ?></a>
						</div>
					</div>
					<?php
							$count++;
						}
					?>
				</div>
				<form>
					<div id="submitkomen">
						<label>submit comment</label>
						<textarea id="takomen" type="comment" placeholder="comment" class="isikomen"></textarea>
						<br>
						<input class= "submitreg" name="submit" type="button" onclick="tambahkomen('<?php echo $_COOKIE["lt_tugas"]["idtugas"];?>','<?php echo $_SESSION['userID'];?>'); clearta();" value="Submit">
					</div>
				</form>
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