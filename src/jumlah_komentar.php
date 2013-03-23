<?php
    $con = mysqli_connect("localhost","root","","progin_405_13510003");
    
    if (mysqli_connect_errno($con)) {
        echo "Gagal Koneksi";
    } else {
        $id_tugas = $_REQUEST["id_tugas"];
    
        if ($stmt = mysqli_prepare($con, "SELECT COUNT(*) FROM komentar WHERE (id_tugas = ?) ORDER BY waktu")) {
            mysqli_stmt_bind_param($stmt,"d",$id_tugas);
            mysqli_stmt_execute($stmt);
            $h2=0;
            mysqli_stmt_bind_result($stmt,$h2);
            mysqli_stmt_fetch($stmt);
            
            echo (9+$h2 - (9+$h2) % 10) / 10;
            mysqli_stmt_close($stmt);
        } else {
            echo "Gagal mengambil komentar";
        }
        mysqli_close($con);
    }
?>
