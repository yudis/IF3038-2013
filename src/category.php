<?php 
include 'session.php';
include 'database.php';

// Connect to server and select databse.
$con=mysqli_connect($host,$username,$password,$db_name);
if (mysqli_connect_errno()) {
	echo "Failed to connect to MySQL: ".mysqli_connect_error();
}

foreach ($_POST as $k => $v) {
	${$k} = $v;
}

mysqli_query($con, "INSERT INTO categories (
				name
				)
				VALUES (
					'$category_name'
				)");
				
$CatId = "SELECT id FROM categories WHERE name='{$category_name}'";
$GetCatId = mysqli_query($con, $CatId);
$result = mysqli_fetch_array($GetCatId);
$idCategory = $result['id'];

$member = array();
$member = explode(",", $relateduser);
$i=1;
$j=count($member);
while($i<=$j)
  {
	$k=$i-1;
  	$member[$k] = trim($member[$k]," ");
  		
	$mem = "SELECT * FROM members WHERE username='{$member[$k]}'";
	$getMem = mysqli_query($con, $mem);
	$resulta = mysqli_fetch_array($getMem);
	$idMember = $resulta['id'];
	
	mysqli_query($con, "INSERT INTO editors (
				member,
				category
				)
				VALUES (
					'$idMember',
					'$idCategory'
				)");
 	$i++;
  }
mysqli_close($con);
header("location:dashboard.php");
?>
