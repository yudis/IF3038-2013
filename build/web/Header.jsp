<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Header</title>
</head>

<body background="images/yellow.jpg">
<div class="header"><center>
<table width="400" border="0" bgcolor="#FFFFFF">
  <tr>
    <td><a href="dashboard.php"><center>
	<img src="images/togo.png" />
      <?php
			if(isset($_SESSION['avatar'])) {echo "<img1 src='{$_SESSION['avatar']}'>";}
			elseif(isset($_SESSION['name'])) {echo "<img1 src='avatar/default.jpg'>";}
		?>
    </center></a></td>
  </tr>
  <tr>
    <td><center><table width="400" border="0">
      <tr>
        <td height="21"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" width="100" height="22">
          <param name="movie" value="button.swf" />
          <param name="quality" value="high" />
          <embed src="button.swf" quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="100" height="22" ></embed>
        </object></td>
        <td><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" width="100" height="22">
          <param name="movie" value="button2.swf" />
          <param name="quality" value="high" />
          <embed src="button2.swf" quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="100" height="22" ></embed>
        </object></td>
        <td><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" width="100" height="22">
          <param name="movie" value="button4.swf" />
          <param name="quality" value="high" />
          <embed src="button4.swf" quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="100" height="22" ></embed>
        </object></td>
      </tr>
    </table>
	</center>
	<form id="form1" name="form1" method="post" action="Test Result.php">
      <label>Search:
        <input type="text_search" name="text_search" id="text_search" placeholder="Search" />
        <input type="SUBMIT" name="SUBMIT" id="SUBMIT" value="Search" />
    </label>
      <label>Filter:
      <select id="filter" name="filter" width="400">
        <option> All </option>
        <option> Users </option>
        <option> Kategori </option>
        <option> Task </option>
      </select>
	  </label>
	  </form>
      <?php
				if(isset($_SESSION['uname'])){
					echo "Hello there, <b><a href='halamanprofil.php'>".$_SESSION['uname']."</a></b> Welcome Home!  <b><a href='logout.php'>Log Out</a></b>";
				}
			?>
      </label></td>
  </tr>
</table>
        </center>
		<center>
		</center>
		  <center>
		    <form id="form1" name="form1" method="post" action="Test Result.php">
		      <label></label>
		    </form>
		  </center>
		    <div id="sign"></div>
	  </div>
		
</body>
</html>
