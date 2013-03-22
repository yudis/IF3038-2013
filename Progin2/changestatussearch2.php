<?php
//This is only displayed if they have submitted the form 
                if (!empty($_POST['searching'])) {
                    $searching = $_GET['searching'];
                    $find = $_GET['find'];
                    $field = $_GET['field'];
                } else {
                    $searching = $_GET['searching'];
                    $find = $_GET['find'];
                    $field = $_GET['field'];
                }
                if ($field == "tasktag" || $field == "semua") {
                    $con = mysql_connect("localhost:3306", "root", "");
                    if (!$con) {
                        die('Could not connect: ' . mysql_error());
                    }

                    mysql_select_db("progin_405_13510057", $con);
					session_start();
					
					$idtugas = $_GET["q"];
					$result = mysql_query("SELECT status FROM tugas WHERE idtugas = '$idtugas'");
					$row = mysql_fetch_array($result);
					if ($row['status'] == "done")
						mysql_query("UPDATE tugas SET status='undone' WHERE idtugas = '$idtugas'");
					else 
						mysql_query("UPDATE tugas SET status='done' WHERE idtugas = '$idtugas'");
					
                    if ($searching == "yes") {
                        echo "<div id=\"hasilcari\"><h3>Hasil Pencarian Task dan Tag</h3>";
						echo "<p style='margin-left: 1em;'><b>Anda mencari : </b> " . $find . "</p></div>";
                        //If they did not enter a search term we give them an error 
                        if ($find == "") {
                            echo "<p>Tolong masukkan data yang ingin anda cari";
                            exit;
                        }
						
						

							
                        // We preform a bit of filtering 
                        $find = strtoupper($find);
                        $find = strip_tags($find);
                        $find = trim($find);

                        $anymatches = 0;

                        //Bila pagenum belum diset di parameter maka akan di set menjadi 1
                        if (!isset($pagenum)) {
                            $pagenum = 10;
                        }
                        // jika parameter sudah diset maka dilakukan pengisian pageum dengna parameter
                        if (empty($_GET['pagenum'])) {
                            
                        } else {
                            $pagenum = $_GET['pagenum'];
                        }

                        //Now we search for our search term, in the field the user specified 
                        $data = mysql_query("SELECT * FROM tag,tugas WHERE upper(isitag) LIKE'%$find%' AND tag.idtugas = tugas.idtugas");

                        $rows = mysql_num_rows($data);

                        //Jumlah hasil tiap page
                        $page_rows = 10;

                        //Page terakhir
                        $last = ceil($rows / $page_rows);

                        //Memastikan pagenum ada di range 1 sampai last
                        if ($pagenum < 1) {
                            $pagenum = 1;
                        } elseif ($pagenum > $last) {
                            $pagenum = $last;
                        }

                        //Melakukan set fungsi LIMIT untuk melakukan query selanjutnya
                        $max = 'LIMIT ' . ($pagenum - 1) * $page_rows . ',' . $page_rows;

                        //Melakukan Query dengna menambahkan fungsi limit
						if ($rows == 0)
						{
							
						}
						else
						{
						
                        $data_p = mysql_query("SELECT * FROM tag,tugas WHERE upper(isitag) LIKE'%$find%' AND tag.idtugas = tugas.idtugas $max") or die(mysql_error());
						
                        //Menampilkan hasil query 
                        while ($info = mysql_fetch_array($data_p)) {
							echo "<div id=\"isi1\">";
                            echo "<p style='margin-left: 1em;'>Tugas : ";
                            echo "<p style='margin-left: 1em;'> Nama Task : <a href=\"viewtask.php?q=". $info['idtugas']."\">". $info['namatugas'] ."</a></p>";
                            echo "<br>";
                            echo "Deadline : ";
                            echo $info['deadline'];
                            echo "<br>";
                            echo "Tag : ";
                            echo $info['isitag'];
                            echo "<br>";
							$temp0 = mysql_query("SELECT username FROM assignee WHERE idtugas = '$info[idtugas]' AND username = '$_SESSION[id]'");
							$row0 = mysql_fetch_array($temp0);
							$temp1 = mysql_query("SELECT username FROM hak WHERE idkategori = '$info[idkategori]' AND username = '$_SESSION[id]'");
							$row1 = mysql_fetch_array($temp1);
							if ($info['username'] == $_SESSION['id'] || $row0['username'] == $_SESSION['id'] || $row1['username'] == $_SESSION['id']){
								if ($info['status'] == "done")
									$status = "checked";
								else 
									$status = '';
								echo "<div>Status : <input type=checkbox name=\"status\" value=\"done\" ".$status."/ onchange=\"changeStatus(".$info['idtugas'].")\"></div>";
                            }
							echo "</p>";
							echo "</div>";
                        }
						}
                        // Menunjukkan halaman pencarian
                        echo "<div id=\"hasilcari2\"><p style='margin-left: 5em;'>";
                        echo " --Page $pagenum of $last-- <p>";
						echo "<p style='margin-left: 5em;'>";
                        // Jika pagenum bukan 1 maka ditampilkan link untuk ke First yaitu pagenum 1 dan previous
                        if ($pagenum == 1 || $pagenum == 0) {
                            
                        } else {
                            echo "<a href=\"#\" onclick = \"searchWords3(1); return false;\" > <<-First</a>";
                            echo " ";
                            $previous = $pagenum - 1;
                            echo "<a href=\"#\" onclick = \"searchWords3(".$previous."); return false;\" > <-Previous</a>";
                        }

                        echo " ---- ";


                        //Jika pagenum bukan last maka ditampilkan next dan last

                        if ($pagenum == $last) {
                            
                        } else {
                            $next = $pagenum + 1;
                            echo "<a href=\"#\" onclick = \"searchWords3(".$next."); return false;\" > Next-></a>";
                            echo " ";
                            echo "<a href=\"#\" onclick = \"searchWords3(".$last."); return false;\" > Last->></a>";
                        }
						echo "</p>";
                        $anymatches = mysql_num_rows($data);
                    }
					echo "<p style='margin-left: 5em;'>";
                    echo "Hasil pencarian : " . $anymatches;
					echo "</p></div>";
                    mysql_close($con);
                }
                ?>