<?php
	require ('connect.php');
	session_start();
	
	$a=$_SESSION['iduser'];

	$query="SELECT * FROM task WHERE user='$a'";
	$query1=mysql_query($query);
	
	$q="SELECT COUNT(*) FROM task WHERE user='$a'";
	$q1=mysql_query($q,$connect) or die(mysql_error());
	$h=mysql_result($q1,0);
	
	while($row=mysql_fetch_assoc($query1)){
		$arrayid[]=$row['idtask'];
		$arraynama[]=$row['namatask'];
		$arraytanggal[]=$row['tanggal'];
		$arraylokasi[]=$row['tag'];
		$arraydesk[]=$row['status'];
	}
		
	for($i=0; $i<$h; $i++){
		
		$que="SELECT name FROM user WHERE iduser='$arrayuser[$i]'";
		$que1=mysql_query($que);
		$pengirim=mysql_result($que1,0);
		
		if ($arraytipe[$i]=="img"){
			$cont = "<img src='$arraylokasi[$i]'> <br/><br/><br/>";
			$li = "rincian.php";
		}
		elseif($arraytipe[$i]=="link"){
			$li = "$arraylokasi[$i]<br/>";
			$cont = "$arraydesk[$i]<br/><br/><br/>";
		}
		elseif ($arraytipe[$i]=="vid"){
			$li = "rincian.php";
			$cont = show_youtube($arraylokasi[$i]);
		}
		
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