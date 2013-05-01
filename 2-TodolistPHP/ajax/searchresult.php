<?php
	require '../utilities/db.php';
	require '../utilities/model.php';
	require '../utilities/view.php';
	require '../models/tugas.php';
	require '../models/kategori.php';
	require '../models/user.php';
	
	
if (!empty($_GET["filter"])) {

$q = $_GET["q"];
$x = $_GET["x"];		// current position (posisi ini belum ditampilkan)
$curTable = $_GET["n"];	// current tabel (string)
$n = 10;


	$searchRes = array();
	$user = new User();
	$tugas = new Tugas();
	
	if ($_GET["filter"] == 'All'){
		$var = (strlen($curTable) <= 2);
		if ($var){
			$searchRes['category'] = NULL;		
			goto a;
		} else if ($curTable == 'user'){
			$searchRes['category'] = NULL;
			$searchRes['tugas'] = NULL;
			goto b;
		}

		$searchRes['category'] = $tugas->searchTitle($q,$x,$n);
		if (sizeof($searchRes['category']) == $n){
			$searchRes['tugas'] = NULL;		
			$searchRes['user'] = NULL;
			$searchRes['x'] = $x + $n;		
			$searchRes['n'] = 'category';
		} else {
			$x = 0;
			$n -= sizeof($searchRes['category']);
			$curTable = 0;
			a:
			$sumTuple = $tugas->countTuple($q, $x, $n);

			$rTuple = sizeof($sumTuple);
			for ($a = 0; $a < sizeof($sumTuple); $a++){
				$tempvar = $sumTuple[$a];
				if ($tempvar['ntag'] > 1){
					$rTuple += $tempvar['ntag'] - 1;
				}					
			}
			
			$searchRes['tugas'] = $tugas->searchTask($q,$curTable,$rTuple);
			if (sizeof($sumTuple) == $n){				
				$searchRes['user'] = NULL;
				$searchRes['x'] = $x + $n;
				$searchRes['n'] = $rTuple + $curTable;
			} else {
				$x = 0;
				$n -= sizeof($sumTuple);
				b:
				$searchRes['user'] = $user->searchUsername($q, $x, $n);
				// die(json_encode(sizeof($searchRes['user'])));
				// die(json_encode($n));
				if (sizeof($searchRes['user']) == $n){
					$searchRes['x'] = $x + $n;		
					$searchRes['n'] = 'user';
				} else {
					$searchRes['x'] = NULL;		
					$searchRes['n'] = NULL;					
				}
					
			}
		}
		
	} else if ($_GET["filter"] == 'Username'){
		$searchRes['category'] = NULL;
		$searchRes['tugas'] = NULL;		
		$searchRes['user'] = $user->searchUsername($q,$x,$n);
		if (sizeof($searchRes['user']) >= $n){
			$searchRes['x'] = $x + $n;
			$searchRes['n'] = 'Username';				
		} else {
			$searchRes['x'] = NULL;
			$searchRes['n'] = NULL;				
		}
	} else if ($_GET["filter"] == 'Title'){
		$searchRes['category'] = $tugas->searchTitle($q,$x,$n);		
		$searchRes['tugas'] = NULL;		
		$searchRes['user'] = NULL;
		if (sizeof($searchRes['category']) >= $n){
			$searchRes['x'] = $x + $n;
			$searchRes['n'] = 'category';				
		} else {
			$searchRes['x'] = NULL;
			$searchRes['n'] = NULL;				
		}		
		
	} else if ($_GET["filter"] == 'Task'){
		$temp = $tugas->countTuple($q, $x ,$n); 
		$rTuple = sizeof($temp);
		if (sizeof($temp) == $n){
			for ($a = 0; $a < $n; $a++){
				$tempvar = $temp[$a];
				if ($tempvar['ntag'] > 1){
					$rTuple += $tempvar['ntag'] - 1;
				}
			}
		
		$searchRes['category'] = NULL;
		$searchRes['tugas'] = $tugas->searchTask($q,$x,$rTuple);		
		$searchRes['user'] = NULL;
		
		$searchRes['x'] = $x + $n;
		$searchRes['n'] = $rTuple + $curTable;		
		} else {
			for ($a = 0; $a < sizeof($temp); $a++){
				$tempvar = $temp[$a];
				if ($tempvar['ntag'] > 1){
					$rTuple += $tempvar['ntag'] - 1;
				}
			}

			$searchRes['category'] = NULL;
			$searchRes['tugas'] = $tugas->searchTask($q,$x,$rTuple);
			$searchRes['user'] = NULL;		
			$searchRes['x'] = NULL;
			$searchRes['n'] = NULL;				
		}		
	}
} else {
	$searchRes['category'] = NULL;
	$searchRes['tugas'] = NULL;		
	$searchRes['user'] = NULL;
}
echo json_encode($searchRes);


?>





