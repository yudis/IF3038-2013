<?php
    $user = $_REQUEST["user"];
    $id_tugas = $_REQUEST["id_tugas"];
    $komentar = $_REQUEST["komentar"];
    $waktu = date('Y-m-d H:i:s');
    
    $con = mysqli_connect("localhost","root","","progin_405_13510003");
    
    if (mysqli_connect_errno($con)) {
        echo "Gagal Connect";
    } else {
        $query = "INSERT INTO komentar (username, id_tugas, waktu, isi)
                  VALUES ('$user',$id_tugas,'$waktu',$komentar)";
        $result = mysqli_query($con,$query);
        echo $result;
        mysqli_close($con);
    }
?>