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
				
                if ($field == "namakategori" || $field == "semua") {
                    $con = mysql_connect("localhost:3306", "root", "");
                    if (!$con) {
                        die('Could not connect: ' . mysql_error());
                    }

                    mysql_select_db("progin_405_13510057", $con);

                    if ($searching == "yes") {
                        echo "<div id=\"hasilcari\"><h2>Hasil Pencarian Kategori</h2>";
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
                        $data = mysql_query("SELECT * FROM kategori WHERE upper(namakategori) LIKE'%$find%'");


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
                        $data_p = mysql_query("SELECT * FROM kategori WHERE upper(namakategori) LIKE'%$find%' $max") or die(mysql_error());

                        //Menampilkan hasil query 
                        while ($info = mysql_fetch_array($data_p)) {
							echo "<div id=\"isi1\">";
                            echo "<p style='margin-left: 1em;'>" .$info['namakategori']. "</p>";
							echo "</div>";
                        }
						}
                        // Menunjukkan halaman pencarian
                        echo "<div id=\"hasilcari2\"><p style='margin-left: 5em;'>";
                        echo " --Page $pagenum of $last-- </p>";
						echo "<p style='margin-left: 5em;'>";
                        // Jika pagenum bukan 1 maka ditampilkan link untuk ke First yaitu pagenum 1 dan previous
                        if ($pagenum == 1) {
                            
                        } else {
                            echo "<a href=\"#\" onclick = \"searchWords2(1); return false;\" > <<-First</a>";
                            echo " ";
                            $previous = $pagenum - 1;
                            echo "<a href=\"#\" onclick = \"searchWords2(".$previous."); return false;\" > <-Previous</a>";
                        }

                        echo " ---- ";


                        //Jika pagenum bukan last maka ditampilkan next dan last

                        if ($pagenum == $last) {
                            
                        } else {
                            $next = $pagenum + 1;
                            echo "<a href=\"#\" onclick = \"searchWords2(".$next."); return false;\" > Next-></a>";
                            echo " ";
                            echo "<a href=\"#\" onclick = \"searchWords2(".$last."); return false;\" > Last->></a>";
                        }
						echo "</p>";
                        $anymatches = mysql_num_rows($data);

                        //This counts the number or results - and if there wasn't any it gives them a little message explaining that 
						echo "<p style='margin-left: 5em;'>";
                        echo "Hasil pencarian : " . $anymatches;
						echo "</p></div>";
                    }
                    mysql_close($con);
                }
?>
