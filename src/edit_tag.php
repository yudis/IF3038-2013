<?php
include "koneksi.php";


$id=$_GET["id"];
$tag=$_GET["tag"];

$simpan = mysql_query("DELETE FROM tag WHERE idtask=$id");

$tagex = explode(',', $tag) ;
foreach ($tagex as $tagin) {
    $querytag = "INSERT INTO `tag` (`idtask`,`namatag`) VALUES ('$id','$tagin')" ;
    mysql_query($querytag) ;

}

$tag="select * from tag where idtask= '$id'";
$hasiltag=mysql_query($tag);
while($rowtag=mysql_fetch_array($hasiltag)){
echo $rowtag['namatag'];
echo " | ";
}

echo "<div id=\"edit_tag\" style=\"display:block\"> <input type=\"text\" id=\"tag_input\">
     <input id=\"tag_button\" type=\"button\" onclick=\"editTag(".$id.")\" value=\"Save Tag\" />
					</div>";

?>