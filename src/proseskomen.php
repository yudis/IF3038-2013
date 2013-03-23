<?php
include "koneksi.php";


$id=$_GET["id"];
$komentar=$_GET["komentar"];
$uid=$_GET["uid"];

$date = date('h:i d/m', time());
$simpan = mysql_query("insert into komentar (idtask,iduser,komentar,waktu) values ('$id','$uid','$komentar', '$date')");


$jumlah ="SELECT COUNT(id) as jumlah FROM komentar where idtask='$id'";
					$resultjml = mysql_query($jumlah);
$rowjml = mysql_fetch_assoc($resultjml);
echo "Jumlah Komentar : ".$rowjml['jumlah']." <br> ";
$sql = "SELECT COUNT(*) FROM komentar";
$result = mysql_query($sql, $conn) or trigger_error("SQL", E_USER_ERROR);
$r = mysql_fetch_row($result);
$numrows = $r[0];

// number of rows to show per page
$rowsperpage = 10;
// find out total pages
$totalpages = ceil($numrows / $rowsperpage);

// get the current page or set a default
if (isset($_GET['currentpage']) && is_numeric($_GET['currentpage'])) {
   // cast var as int
   $currentpage = (int) $_GET['currentpage'];
} else {
   // default page num
   $currentpage = 1;
} // end if

// if current page is greater than total pages...
if ($currentpage > $totalpages) {
   // set current page to last page
   $currentpage = $totalpages;
} // end if
// if current page is less than first page...
if ($currentpage < 1) {
   // set current page to first page
   $currentpage = 1;
} // end if

// the offset of the list, based on current page 
$offset = ($currentpage - 1) * $rowsperpage;

// get the info from the db 
$sql = "select *, user.id as uid, komentar.id as kid from komentar,user where idtask= '$id' and user.id=komentar.iduser order by kid DESC LIMIT $offset, $rowsperpage";
$result = mysql_query($sql, $conn) or trigger_error("SQL", E_USER_ERROR);

// while there are rows to be fetched...
while ($list = mysql_fetch_assoc($result)) {
   // echo data
   echo "<img width=\"30\" height=\"30\" src=\"../img/avatar/".$list['avatar']."\"></img> (".$list['waktu'].") : ";
  echo $list['komentar'];
  if ($list['iduser'] == $uid)
					{
						echo "<input class=\"right top30 link_blue_rect\" type=\"button\" value=\"Delete\" onclick=\"del_komen(".$list['kid'].",".$id.");\"></input>"; 
						
					}
  echo "<br><hr>";
} // end while

/******  build the pagination links ******/
// range of num links to show
$range = 3;

// if not on page 1, don't show back links
if ($currentpage > 1) {
   // show << link to go back to page 1
   echo " <a href='detail.php?id=".$id."&currentpage=1'><<</a> ";
   // get previous page num
   $prevpage = $currentpage - 1;
   // show < link to go back to 1 page
   echo " <a href='detail.php?id=".$id."&currentpage=$prevpage'><</a> ";
} // end if 

// loop to show links to range of pages around current page
for ($x = ($currentpage - $range); $x < (($currentpage + $range) + 1); $x++) {
   // if it's a valid page number...
   if (($x > 0) && ($x <= $totalpages)) {
      // if we're on current page...
      if ($x == $currentpage) {
         // 'highlight' it but don't make a link
         echo " [<b>$x</b>] ";
      // if not current page...
      } else {
         // make it a link
         echo " <a href='detail.php?id=".$id."&currentpage=$x'>$x</a> ";
      } // end else
   } // end if 
} // end for
                 
// if not on last page, show forward and last page links        
if ($currentpage != $totalpages) {
   // get next page
   $nextpage = $currentpage + 1;
    // echo forward link for next page 
   echo " <a href='detail.php?id=".$id."&currentpage=$nextpage'>></a> ";
   // echo forward link for lastpage
   echo " <a href='detail.php?id=".$id."&currentpage=$totalpages'>>></a> ";
} // end if
/****** end build pagination links ******/
?>