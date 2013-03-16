<?php
// Fill up array with names
$a[]="Algoritma";
$a[]="Branding";
$a[]="Pemrograman Internet";
$a[]="Sistem Terdistribusi";
$a[]="Intelegensia Buatan";

$q = $_GET["key"];

if (strlen($q) > 0){
    $hint = "";
    for ($i = 0; $i < count($a); $i++) {
        if (strtolower($q) == strtolower(substr($a[$i], 0, strlen($q)))) {
            if ($hint == "") {
                $hint = $a[$i];
            } else {
                $hint = $hint . ", " . $a[$i];
            }
        }
    }
}

if ($hint == "") {
    $response = "tidak ada saran";
} else {
    $response = $hint;
}

echo $hint;
?>