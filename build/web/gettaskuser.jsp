<?php
	require ('connect.php');
	session_start();

	$a=$_SESSION['iduser'];

	$query="SELECT * FROM task WHERE user='$a'";
	$query1=mysql_query($query);
	
	$q="SELECT COUNT(*) FROM task WHERE user='$a'";
	$q1=mysql_query($q,$conn) or die(mysql_error());
	$h=mysql_result($q1,0);
	
	while($row=mysql_fetch_assoc($query1)){
		$arrayid[]=$row['id_task'];
		$arraynama[]=$row['nama_task'];
		$arraytanggal[]=$row['tanggal'];
		$arraystatus[]=$row['status'];
		$arraytag[]=$row['tag'];
	}
		
	for($i=0; $i<$h; $i++){
		
		$que="SELECT name FROM user WHERE iduser='$arrayuser[$i]'";
		$que1=mysql_query($que);
		$pengirim=mysql_result($que1,0);
		
		echo "<div class='contentlistprofile'>
                    <div class='judulProfile'>
                        <h4>
                            <a href='$li'>
                                $arrayjudul[$i]
                            </a>
                        </h4>
                        <div class='postdateProfile'>
                            $arraytanggal[$i]
                        </div>
                        <div class='contentlikeisiProfile'>
                                
                        </div>
                    </div>
					</br>
					<div class='theContentProfile'>
					
							$cont
					</div>
					</br>
                </div>";
	}
	
?>