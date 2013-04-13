<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
   <head>
        <title>View Task -- CanDo</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <script type="text/javascript" src="rinciantugas.js"></script>
    </head>
    
	
    <body>
	<?php include "header.php"?>
        <div class="body">
            <div class="contents">
				<?php
					$result = mysql_query('SELECT * FROM kategori INNER JOIN tugas ON ( tugas.idkat = kategori.id ) INNER JOIN assignee ON ( assignee.idtask = tugas.id )  WHERE iduser =1');
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
			
                <h3>Task Details</h3>
                <div class="namatugas">Beli Minyak Goreng</div>
                <br>
                <div class="atributtugas">Deadline</div>
                <div id="atributdeadline">15 Maret 2013</div>
                <br>

                <div> <div class="atributtugas">Assignee</div>
                    <div id="atributassignee">
                    <ul>
                        <li>Assignee 1</li>
                        <li>Assignee 2</li>
                        <li>Assignee n</li>
                    </ul>
                </div>
                <div><div class="atributtugas">Tags</div> <div id="atributtag">dapur, makanan, penting</div></div>
                <br>
                <div class="atributtugas">Comments</div>
                <p class="commenttugas">Wah seru banget nih tugasnya! huhuhu ngiri sama assigneenya</p>
                <p class="commenttugas">Gila! ayo beli minyak goreng merk terbaik!</p>
                <div class="atributtugas">Say something about this task:</div>
                <div>
                    <textarea name="comments" rows="3" maxlength="200" cols="60"></textarea>
                    <br>
                    <input type="submit" value="Comment!" onClick="">
                </div>
                <br>
                <div class="atributtugas">Attachments</div>
                <!-- Di sini taro attchmentnya -->
                <a href="files\PDFTest.pdf">PDFTest.pdf</a>
                <img src ="images\tes.jpg">
                <!-- to do: kalo ada file pop-up/download sesuai MIME -->
                <br><br><br>
                <input type="submit" id="edittaskbutton" value="Edit Task" onclick="editTask()">
                <!-- to do: kalo button diklik bbrp field jadi editable -->
            </div>
        </div>
    </body>
	    <footer>
            CanDo. Yes you can!
            <br>
            About us
        </footer>
</html>
