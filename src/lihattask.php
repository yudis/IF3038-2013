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
    </head>
    <body onload="taskawal(6);">
		
		<!--header (dashboard)-->
		<?php require_once("header.php"); ?>
		
        <div><?php
					
					//foreach ($_COOKIE["lt_tugas"] as $key => $value) {
					//	echo "<div>Key: $key; Value: ".json_encode($value)."<br>\n";
					//}
					
					
					
					?>
		</div>
        
        <div class="main">
            <div id="edit" onclick="showEdit()">
            </div>
            <a href="dashboard.html">
                <div id="back">
                </div>
            </a>
            <div id="rinciantugas">
                <div id="judultugas"><?php echo $_COOKIE["lt_tugas"]["nama"];?></div>
                <div id="detail" style="visibility: visible">
                    
                        <label>DEADLINE</label>
                        <a id="deadline"><?php echo $_COOKIE["lt_tugas"]["deadline"];?></a>
                        
                        <label>ASSIGNEE</label>
                        <a id="assignee">Faiz</a>
                        
                        <label>TAG</label>
                        <a id="tag">KAP</a>
                        
                        <label>ATTACHMENT</label>
						<?php
							foreach ($_COOKIE["lt_attachment"] as $x) {
						?>
                        <a href ="#" id="attach">
							<img src="<?php echo $x["path"].$x["nama"];?>" width="200"/>
						</a>
						<?php
							}
						?>
                    
                </div>
                <div id="detailedit" style="visibility: hidden">
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
                </div>
            </div>
            <div id="komen">
                <div id="daftarkomen">
                   Faiz :<br>
                   Gimana nih udah kelar belum tubesnya ?
                </div>
                <div id="daftarkomen">
                   Fandi :<br>
                   Bentar nih gw masih main tenis
                </div>
                <div id="daftarkomen">
                   Faiz :<br>
                   ah yang bener lah
                </div>
                <form>
                    <div id="submitkomen">
                        <label>submit comment</label>
                        <textarea name="comment" type="comment" placeholder="comment" class="isikomen"></textarea>
                        <input class= "submitreg" name="submit" type="submit" onclick="http://google.com" value="Submit">
                    </div>
                </form>
            </div>
        </div>
		
		<!--header (dashboard)-->
		<?php require_once("footer.php"); ?>
		
    </body>
</html>