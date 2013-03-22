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
            mysqli_stmt_close($stmt);
            $query = "SELECT avatar FROM pengguna WHERE (username = '$user')";
            $result = mysqli_query($con,$query);
            $row = mysqli_fetch_row($result);
            mysqli_free_result($result);
            echo $user,"\n",$waktu,"\n",$komentar,"\n",$row[0],"\n";
        } else {
            mysqli_stmt_close($stmt);
        }

        
        mysqli_close($con);
    }
?>
