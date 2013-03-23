<?php  
include "koneksi.php";
$id=$_GET["id"];
$sql = "DELETE FROM kategori WHERE id='$id'";
$result = mysql_query($sql);

$curr_username = $_COOKIE['username'];
$sql_id = mysql_query("SELECT id FROM user WHERE username LIKE '".$curr_username."'");
$loginid = mysql_fetch_array($sql_id);
 
echo "<div class=\"link_blue_rect\" id=\"category_title\"><a href=\"#\" onclick=\"catchange(0)\">All Categories </a> </div>
					<ul id=\"category_item\">";
$kategori="select * from kategori";
$hasilkat=mysql_query($kategori);
while($rowkat=mysql_fetch_array($hasilkat)){
$idkat=$rowkat['id'];
echo "<li>";
    echo "<a href=\"#\" onclick=\"catchange(".$idkat.")\" id=\"kuliah\">\n";
    echo $rowkat['namakat'] . "</a>" ;
    
    if ($rowkat['idcreator'] == $loginid['id']) {
        echo "<input id=\"kuliah\" onclick=\"deletekat(".$idkat.")\" type=\"button\" value=\"Delete\">";
    }
    
    echo "</li>" ;
    }
echo "</ul> <div id=\"add_new_category\" onclick=\"window.open('tambahkat.php', 'PopUpAing',  'width=432,height=270,toolbar=0,scrollbars=0,screenX=200,screenY=200,left=200,top=200')\"> TAMBAH KATEGORI </div>";
					
  
?> 