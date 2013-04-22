<?php
    session_start();
    
    $namaKategori = $_GET['kat'];
    $userList = $_GET['userList'];
    $user = explode(",",$userList); //membentuk array yang berisikan list anggota yang diberi access untuk edit kategori
    
    //create connection
    $con = mysqli_connect("127.0.0.1","progin","progin","progin_405_13510027");
    
    //check the connection
    if (mysqli_connect_errno($con)) {
        echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
    }
    
    $isValidKategoriName = true;
    //mengecek validitas dari nama kategori
    $kategoriResult = mysqli_query($con,"SELECT * FROM kategori WHERE namaKategori='$namaKategori'");
    $kategoriCount = mysqli_num_rows($kategoriResult);
    if ($kategoriCount != 0) {
        echo "Warning. Nama kategori sudah ada.\n";
        $isValidKategoriName = false;
    }
    
    //mengecek validitas dari nama user editable
    $isValidUser = true;
    for ($i=0;$i<count($user);$i++){
        $result = mysqli_query($con,"SELECT username FROM user WHERE username='$user[$i]'");
        $rowCount = mysqli_num_rows($result);
        if ($rowCount== 0) {
            echo "Warning. user tidak ada. ulangi pengisian.\n";
            $isValidUser = false;
            break;
        } else {
            //echo "user ada";
        }
    }
    
    if ($isValidKategoriName && $isValidUser){
        //melakukan insert ke table kategori
        $curUser = $_SESSION['username']; //mengambil username yang sedang aktif
        $sql = "INSERT INTO kategori VALUES ('".$namaKategori."','".$curUser."')";
        $retVal = mysqli_query($con,$sql);
        
        //melakukan insert ke table editKategori
        $retValEdit = true;
        for ($i=0;$i<count($user);$i++){
            $sqlEdit = "INSERT INTO editKategori VALUES ('".$user[$i]."','".$namaKategori."')";
            $retValEdit = (mysqli_query($con,$sqlEdit) && $retValEdit);
        }
        
        if ($retValEdit && $retVal) {
            echo "insert table kategori dan editKategori berhasil\n";
        }
    }
    //echo "selesai\n";
?>