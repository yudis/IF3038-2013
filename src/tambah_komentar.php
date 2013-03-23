<?php
    $con = mysqli_connect("localhost","root","","progin_405_13510003");
    
    if (mysqli_connect_errno($con)) {
        echo "Gagal connect";
    } else {
        $user = $_REQUEST["user"];
        $id_tugas = $_REQUEST["id_tugas"];
        $waktu = date('Y-m-d H:i:s');
        $komentar = $_REQUEST["komentar"];
        
        $stmt = mysqli_prepare($con, "INSERT INTO komentar (username, id_tugas, waktu, isi) VALUES (?,?,?,?)");
        mysqli_stmt_bind_param($stmt,"sdss",$user,$id_tugas,$waktu,$komentar);
        mysqli_stmt_execute($stmt);
        if (mysqli_stmt_affected_rows($stmt)) {
            echo $user,"\n",$waktu,"\n",$komentar,"\n";
        }
        mysqli_stmt_close($stmt);
        
        mysqli_close($con);
    }
?>
