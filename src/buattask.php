<html>
    <head>
        <title> Next | ADD TASK </title>
        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="css/buattask.css">
        <link rel="stylesheet" href="css/calendar.css">
		<script src="js/calendar.js" > </script>
		<script src="js/buattask.js"></script>
    </head>
    <body onload="">
	  
		<!--header (dashboard)-->
		<?php require_once("header.php"); ?>
		  
        <div class="main">
            <div id="addtask">
            </div>
            <a href="dashboard.html">
			  <div id="back">
			  </div>
            </a>
            <div id="formtask">
                <div id="judulform">
                </div>
                <div id="formtask2">
                    <form name="buatugas" method="post" action="task.php?tujuan=buat" enctype="multipart/form-data">
                        <label>nama tugas</label>
						  <input name="username" type="text" placeholder="username" onkeyup="name_valid()" />
						  <img src="pict/blank.png" alt="icon1" id="namaicon"  />
                        <label>attachment</label>
						  <input type="file" name="attach" onchange="atta_validating()" />
						  <img src="pict/blank.png" alt="icon2" id="attaicon"  />
						  <input type="file" name="attach2" onchange="atta_validating()" />
						  <img src="pict/blank.png" alt="icon2" id="attaicon"  />
						  <input type="file" name="attach3" onchange="atta_validating()" />
						  <img src="pict/blank.png" alt="icon2" id="attaicon"  />
						  <input type="file" name="attach4" onchange="atta_validating()" />
						  <img src="pict/blank.png" alt="icon2" id="attaicon"  />
                        <label>deadline</label>
						  <input type="text" name="deadline" id="datedead" onmousedown="dead_validating()" />
						  <img src="pict/blank.png" alt="icon3" id="deadicon" />
						  <script type="text/javascript">
							  calendar.set("datedead");
						  </script>
					    <label>assignee</label>
						  <input id="idasignee" autocomplete="off" name="namasign" placeholder="nama lengkap" onkeyup="showResult(this.value); asi_validating();" >
						  <img src="pict/blank.png" alt="icon4" id="asicon" />
						  <div id="hasil_autocomplete"></div>						  
                        <label>tag</label>
						  <input name="tag" placeholder="tag" onkeyup="tag_validating()" />
						  <img src="pict/blank.png" alt="icon5" id="tagicon" />
                        <br><br>
                        <input class= "submitreg" name="submit" type="submit" value="Submit">
                    </form>
                </div>
            </div>
        </div>
		<!--header (dashboard)-->
		<?php require_once("footer.php"); ?>
        
        
    </body>
</html>