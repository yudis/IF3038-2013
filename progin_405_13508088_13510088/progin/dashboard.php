<?php session_start(); ?>
<html>
    <head>
        <title>CanDo</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <script type="text/javascript" src="DashboardPage.js"></script>
        <script src="ajax.js"> </script>
    </head>
    <body>
		<?php include "header.php"?>
        <div class="body">
            <div class="catagories">
                <h3>Catagories:</h3>
                <?php	
					$result = mysql_query('SELECT * FROM kategori INNER JOIN tugas ON ( tugas.idkat = kategori.id ) INNER JOIN assignee ON ( assignee.idtask = tugas.id )  WHERE iduser = '. $IDlogin_session);
					while($row = mysql_fetch_array($result))
						:?>
							<li><?php 
							if ($row ['namakat']=="Kuliah") {
								$kat = $row ['namakat'];
								echo "<a href=\"#\" onclick=\"showDapur()\">$kat</a>";
							}
							else if ($row ['namakat']=="Organisasi") {
								$kat = $row ['namakat'];
								echo "<a href=\"#\" onclick=\"showSekolah()\">$kat</a>";
							}
							else if ($row ['namakat']=="Percintaan") {
								$kat = $row ['namakat'];
								echo "<a href=\"#\" onclick=\"showPersonal()\">$kat</a>";
							}
							?></li>
					<?php	endwhile;
							
					?>
                <input type="submit" value="Add Catagory" onclick="addKategori()">
            </div>
            <div class="contents">
                <h3>Your Tasks:</h3>
                <div id="alltask">
					<?php
						$result3 = mysql_query('SELECT * FROM tugas INNER JOIN kategori ON ( tugas.idkat = kategori.id ) INNER JOIN assignee ON ( assignee.idtask = tugas.id ) WHERE iduser = '.+ $IDlogin_session);
						while($row3 = mysql_fetch_array($result3)){
							$idtask = $row3 ['idtask'];
							$namatask = $row3 ['namatask'];
							$deadline = $row3 ['deadline'];
							$status = $row3 ['status'];
							echo "<li><a href=\"rinciantugas5.php\">$namatask</a> Deadline : $deadline, Status : $status <a href=\"edit_status.php?idtask= $idtask\">[Edit Status]</a> <a href=\"hapus_data.php?no_task=$idtask\">[delete]</a></li>";

						}
					?>
                </div>
				
                <div id="dapurtask">
					<?php
						$result3 = mysql_query('SELECT * FROM tugas INNER JOIN kategori ON ( tugas.idkat = kategori.id ) INNER JOIN assignee ON ( assignee.idtask = tugas.id ) WHERE (iduser =1) AND (namakat = "Kuliah")');
						while($row3 = mysql_fetch_array($result3)){
							$idtask = $row3 ['idtask'];
							$namatask = $row3 ['namatask'];
							$deadline = $row3 ['deadline'];
							$status = $row3 ['status'];
							echo "<li><a href=\"rinciantugas5.php\">$namatask</a> Deadline : $deadline, Status : $status <a href=\"edit_status.php?idtask= $idtask\">[Edit Status]</a> <a href=\"hapus_data.php?no_task=$idtask\">[delete]</a></li>";
}
					?>
						
                <input type="submit" value="Add New Task" id="addnewtask" onclick="addTask()">
                </div>
				
                <div id="sekolahtask">
					<?php
						$result3 = mysql_query('SELECT * FROM tugas INNER JOIN kategori ON ( tugas.idkat = kategori.id ) INNER JOIN assignee ON ( assignee.idtask = tugas.id ) WHERE (iduser =1) AND (namakat = "Organisasi")');
						while($row3 = mysql_fetch_array($result3)){
							$idtask = $row3 ['idtask'];
							$namatask = $row3 ['namatask'];
							$deadline = $row3 ['deadline'];
							$status = $row3 ['status'];
							echo "<li><a href=\"rinciantugas5.php\">$namatask</a> Deadline : $deadline, Status : $status <a href=\"edit_status.php?idtask= $idtask\">[Edit Status]</a> <a href=\"hapus_data.php?no_task=$idtask\">[delete]</a></li>";
}
					?>
                <input type="submit" value="Add New Task" id="addnewtask" onclick="addTask()">
                </div>
                
                <div id="personaltask">
					<?php
						$result3 = mysql_query('SELECT * FROM tugas INNER JOIN kategori ON ( tugas.idkat = kategori.id ) INNER JOIN assignee ON ( assignee.idtask = tugas.id ) WHERE (iduser =1) AND (namakat = "Percintaan")');
						while($row3 = mysql_fetch_array($result3)){
							$idtask = $row3 ['idtask'];
							$namatask = $row3 ['namatask'];
							$deadline = $row3 ['deadline'];
							$status = $row3 ['status'];
							echo "<li><a href=\"rinciantugas5.php\">$namatask</a> Deadline : $deadline, Status : $status <a href=\"edit_status.php?idtask= $idtask\">[Edit Status]</a> <a href=\"hapus_data.php?no_task=$idtask\">[delete]</a></li>";
}
					?>
                <input type="submit" value="Add New Task" id="addnewtask" onclick="addTask()">
                </div>

 
				<br>
			</div>	
			<br>
        
        </div>
    </body>
	    <footer>
			<p></p>
            CanDo. Yes you can!
							
            <br>
            About us
        </footer>
</html>