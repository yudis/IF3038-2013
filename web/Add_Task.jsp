<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Add Task</title>
<script type="text/javascript" src="datetimepicker_css.js"></script>
</head>

<body>
<?php
include 'Header.php';
?>
<form id="form2" name="form2" method="post" action="Task.php">
<table width="400" border="0">
  <tr>
    <td><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" width="79" height="21">
      <param name="movie" value="text4.swf" />
      <param name="quality" value="high" />
      <param name="bgcolor" value="#000066" />
      <embed src="text4.swf" quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="79" height="21" bgcolor="#000066"></embed>
    </object></td>
    <td>: 	<input type="add_nama" name="add_nama" id="add_nama" placeholder="Add Nama" /></td>
  </tr>
  <tr>
    <td><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" width="133" height="21">
      <param name="movie" value="text5.swf" />
      <param name="quality" value="high" />
      <param name="bgcolor" value="#000066" />
      <embed src="text5.swf" quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="133" height="21" bgcolor="#000066"></embed>
    </object></td>
    <td>:  	<input type="add_tanggal" class="labelboxreg" id="add_tanggal" name="add_tanggal"></input>
			<img src="images2/cal.gif" onClick="javascript:NewCssCal('add_tanggal')" style="cursor:pointer" alt="calendar"/></td>
  </tr>
  <tr>
    <td><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" width="120" height="21">
      <param name="movie" value="text6.swf" />
      <param name="quality" value="high" />
      <param name="bgcolor" value="#000066" />
      <embed src="text6.swf" quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="120" height="21" bgcolor="#000066"></embed>
    </object></td>
    <td>: <input type="add_status" name="add_status" id="add_status" placeholder="Add Status" /></td>
  </tr>
  <tr>
    <td><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" width="60" height="21">
      <param name="movie" value="text7.swf" />
      <param name="quality" value="high" />
      <param name="bgcolor" value="#000066" />
      <embed src="text7.swf" quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="60" height="21" bgcolor="#000066"></embed>
    </object></td>
    <td>: <input type="add_tag" name="add_tag" id="add_tag" placeholder="Add Tag" /></td>
  </tr>
</table>
<p>&nbsp;</p>
<input type="SUBMIT" name="SUBMIT" id="SUBMIT" value="Add" />
</form>
</body>
</html>
