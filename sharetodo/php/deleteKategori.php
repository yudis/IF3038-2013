<?php
    session_start();
    
    $namaKategori = $_GET['kat'];
    //echo $namaKategori;
    
    //create connection
    $con = mysqli_connect("127.0.0.1","progin","progin","progin_405_13510027");
    
    //check the connection
    if (mysqli_connect_errno($con)) {
        echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
    }
    
    //mengecek validitas user
    $isValid = false;
    $curUser = $_SESSION['username'];
    $sql = "SELECT namaKategori FROM kategori WHERE creatorKategoriName='$curUser'";
    $result = mysqli_query($con,$sql);
    while ($row = mysqli_fetch_array($result)){
	if ($row['namaKategori'] == $namaKategori) {
	    $isValid = true;
	}
    }
    
    //echo $row['namaKategori'];
    if ($isValid) {
	//echo "bener";
	//hapus semua table terkait : tabel kategori, editKategori, task
	$retValEditKategori = mysqli_query($con,"DELETE FROM editKategori WHERE namaKategori='$namaKategori'");
	$retValTask = mysqli_query($con,"DELETE FROM task WHERE namaKategori='$namaKategori'");
	$retValKategori = mysqli_query($con,"DELETE FROM kategori WHERE namaKategori='$namaKategori'");
	
	if ($retValKategori && $retValEditKategori && $retValTask) {
	    echo "penghapusan kategori $namaKategori berhasil dilakukan";
	}
    } else {
	echo "Anda tidak berhak untuk menghapus kategori $namaKategori";
    }    
?>