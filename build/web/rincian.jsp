<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Rincian Task</title>

<?php
	include "connect.php";
	
	$kodetugas = $_GET['kodetugas'];
	$kodeuser = $_GET['kodeuserasal'];
	$kodeuserlogin = $_COOKIE['kodeuser'];
?>

	<script type="text/javascript" src="rinciantugas.js"></script>

</head>

<?php
	
	$data  = mysql_query("SELECT * FROM tugas WHERE kodeuser='$kodeuser' AND kodetugas='$kodetugas'");
	$data2 = mysql_query("SELECT * FROM assigne WHERE kodeuser='$kodeuser' AND kodetugas='$kodetugas'");
	$data3 = mysql_query("SELECT * FROM tag WHERE kodeuser='$kodeuser' AND kodetugas='$kodetugas'");
	$data4 = mysql_query("SELECT * FROM komentar WHERE kodeuser='$kodeuser' AND kodetugas='$kodetugas' ORDER BY tanggal,waktu");
?>

<body>
<div class="corner_inner">
  <div id="main_body">
    <p align="center">
      <?php
			$baris = mysql_fetch_array($data);
			$deadline = $baris['deadline'];
		?>
    </p>
    <div align="center" id="rincian">
      <table width="70%" border="1" class="tabelkeren">
        <tr bgcolor="#0099CC">
          <th height="34" colspan="2" scope="col">RINCIAN TUGAS</th>
        </tr>
        <tr>
          <td width="27%" height="40"><div align="left"><strong>Nama Tugas </strong></div></td>
          <td width="73%"><div align="left"><?php echo $baris['namatugas'];?></div></td>
        </tr>
        <tr>
          <td height="40"><div align="left"><strong>Status Tugas</strong></div></td>
          <td><div align="left">
		    
		
		    <?php 
		  
		  	  if($baris['statustugas']==1)
			  {
				  ?>
			  <input type="checkbox" name="status" id="status" value="<?php echo $kodeuser."-".$kodetugas."-".$baris['statustugas'];?>" onchange=proses(this.value)>
			  	  <?php
				  echo "Tugas Belum Selesai";
              }
			  else
			  {
				  ?>
			  <input type="checkbox" name="status" id="status" value="<?php echo $kodeuser."-".$kodetugas."-".$baris['statustugas'];?>" checked="checked" onchange=proses(this.value)>
	  	  <?php
				  echo "Tugas Sudah Selesai";
			  }
		  
		  ?></div></td>
        </tr>
        <tr>
          <td height="40"><div align="left"><strong>Attachment </strong></div></td>
          <td><div align="left">
            <p>
			<?php 
				$data5 = mysql_query("SELECT * FROM attachment WHERE kodeuser='$kodeuser' AND kodetugas='$kodetugas'");
				
				while($baris2 = mysql_fetch_array($data5))
				{
					$alamat = $baris2['alamat'];
					$panjang = strlen($alamat);
					
					if($alamat[$panjang-4]!=".")
					{
						$ekstensi = $alamat[$panjang-4].$alamat[$panjang-3].$alamat[$panjang-2].$alamat[$panjang-1];
					}
					else
					{
						$ekstensi = $alamat[$panjang-3].$alamat[$panjang-2].$alamat[$panjang-1];
					}
					
					if(($ekstensi == "jpeg")||($ekstensi == "jpg"))
					{
						?>
                        <img src="<?php echo $baris2['alamat'];?>" width="300" height="300" /> <br /> <br />
                        <?php
					}
					else
					if($ekstensi == "mp4")
					{
						?>
                        <video width="300" height="300" controls>
                          <source src="<?php echo $baris['alamat'];?>" type="video/mp4"> <br /> <br />
                        </video> 
                        <?php
					}
					else
					{
						?>
                        <a href="<?php echo $baris2['alamat'];?>" target="_blank"> <?php echo $baris2['alamat'];?> </a> <br /> <br />
                        <?php
					}
				}
			?>
			            </p>
</div></td>
        </tr>
        <tr>
          <td height="40"><div align="left"><strong>Deadline </strong></div></td>
          <td><div align="left"><?php echo $deadline;?></div></td>
        </tr>
        <tr>
          <td height="40"><div align="left"><strong>Assigne </strong></div></td>
          <td><div align="left">
            <?php 
								  	while($baris2=mysql_fetch_array($data2))
									{
										$dumkod = $baris2['assigne'];
										$dummy = mysql_query("SELECT * FROM user WHERE kodeuser='$dumkod'");
										$dumdata = mysql_fetch_array($dummy);
										?>
<a href="home.php?link=halamanprofil&username=<?php echo $dumdata['username']; ?>">
                                        <?php 
										echo $dumdata['username']." ";
										?>
              </a>
                                        <?php
									}
								  ?>
          </div></td>
        </tr>
        <tr>
          <td height="40"><div align="left"><strong>Komentar </strong></div></td>
          <td><div align="left">
            <?php
                                    $jumlahkomentar = mysql_num_rows($data4);
									echo "Jumlah Komentar : ".$jumlahkomentar;
									while($baris4=mysql_fetch_array($data4))
									{
										$dumkod = $baris4['kodeuserkomentar'];
										$dummy = mysql_query("SELECT * FROM user WHERE kodeuser='$dumkod'");
										$dumdata = mysql_fetch_array($dummy);
										$avatar = $dumdata['foto'];
										echo "<p><img src='$avatar' width='20' height='20' />".$dumdata['username']." : ".$baris4['komentar']." (".$baris4['tanggal']."-".$baris4['waktu'].")" ?> <?php if($baris4['kodeuserkomentar']==$kodeuserlogin) {?> <a href="home.php?link=deletekomentar&kodetugas=<?php echo $kodetugas; ?>&kodeuser=<?php echo $kodeuser; ?>&kodekomentar=<?php echo $baris4['kodekomentar'];?>" onClick="return confirm('Apakah komentar ini akan dihapus?')"> Hapus</a> <?php }"</p>";
									}
									?>
          </div></td>
        </tr>
        <tr>
          <td height="40"><div align="left"><strong>Tambah Komentar </strong></div></td>
          <td><div align="center">
            <label for="komentar"></label>
            <form name="komentar" method="GET" action="" >
              <p align="left">
                <textarea name="komentar" id="komentar" cols="50" rows="5"></textarea>
                <label for="kodeuser"></label>
                <input name="kodeuser" type="hidden" id="kodeuser" value="<?php echo $kodeuser; ?>" />
              </p>
              <p align="left">
                <label for="kodeuser"></label>
                <input name="kodetugas" type="hidden" id="kodeuser" value="<?php echo $kodetugas; ?>" />
              </p>
              <p align="left">
                <input type="button" name="kirim" id="kirim" value="Kirim" onclick="tambahkomentar(komentar.value)">
              </p>
            </form>
            </p>
          </div></td>
        </tr>
        <tr>
          <td height="40"><div align="left"><strong>Tag </strong></div></td>
          <td><div align="left">
            <?php                                  	  
                                        while($baris3=mysql_fetch_array($data3))
                                        {
                                            echo $baris3['tag']." ";
                                        }
									  ?>
          </div></td>
        </tr>
      </table>
      </p>
    </div>
    <p align="center">&nbsp;
    </p>
    <p align="center"><a href="home.php?link=edittugas&kodetugas=<?php echo $kodetugas; ?>&kodeuserasal=<?php echo $kodeuser; ?>"> <input type="submit" name="edit" id="edit" value="Edit" /> 
    </a></p>
    <table cellpadding="0" cellspacing="0" align="center">
      <tr>
        <td valign="top"><div>
          <table class="contentpaneopen" align="center">
            <tr>
              <td valign="top" colspan="2"></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>