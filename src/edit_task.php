<?php
include "koneksi.php";


$id=$_GET["id"];
$assignee=$_GET["assignee"];

$simpan = mysql_query("insert into assignee (idtask,nama_user) values ('$id','$assignee')");
$assignee="select * from assignee where idtask= '$id'";
$hasilass=mysql_query($assignee);
while($rowass=mysql_fetch_array($hasilass)){

echo "<a href=\"profile.php?user=".$rowass['nama_user']." \">".$rowass['nama_user']."</a>";
echo "<br>";
}

echo "<div id=\"result\" style=\"display:none;\"></div>
<div id=\"edit_ass\" style=\"display:block\"> 
<form>
   <table>
        <tr>
			<input type=\"text\" id=\"assignee\" autocomplete=\"off\" list=\"listassignee\" onkeydown=\"javascript:getSuggest();\"></input>
<datalist id=\"listassignee\">
                                            </datalist>
        </td>
        </tr>
        
        <tr>
            <td>
                <input id=\"tambah_button\" type=\"button\" onclick=\"addRows(".$id.")\" value=\"Tambah\" />
            </td>
        </tr>
    </table>
</form>
</div>";

echo "<div id=\"delete_ass\" style=\"display:block\"><form action=\"\"> <select id=\"assignees\" onchange=\"delAss(this.value, ".$id.")\"> <option value=\"\">Delete an assignee:</option>";
$assignee = array();
$x="select * from assignee where idtask= '$id'";
$hasilx=mysql_query($x);
while(($row =  mysql_fetch_assoc($hasilx))) {
    $assignee[] = $row['nama_user'];
}
foreach ($assignee as $opt) {
    $sel = '';
    if (in_array($opt, $mytitle)) {
        $sel = ' selected="selected" ';
    }
    echo '<option ' . $sel . ' value="' . $opt . '">' . $opt . '</option>';
}
echo "</select></form></div>";

	
?>