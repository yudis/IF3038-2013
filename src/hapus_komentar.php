<?php
    $con = mysqli_connect("localhost","root","","progin_405_13510003");
    
    if (mysqli_connect_errno($con)) {
        echo "Gagal connect";
    } else {
        $user = $_REQUEST["user"];
        $id_tugas = $_REQUEST["id_tugas"];
        $waktu = $_REQUEST["tanggal"];
        
        $stmt = mysqli_prepare($con, "DELETE FROM komentar WHERE (username=? && id_tugas=? && waktu=?)");
        mysqli_stmt_bind_param($stmt,"sds",$user,$id_tugas,$waktu);
        mysqli_stmt_execute($stmt);
        if (mysqli_stmt_affected_rows($stmt)) {
            mysqli_stmt_close($stmt);
            $query = "SELECT avatar FROM pengguna WHERE (username = '$user')";
            $result = mysqli_query($con,$query);
            $row = mysqli_fetch_row($result);
            mysqli_free_result($result);
            echo "Komentar berhasil dihapus";
        } else {
            mysqli_stmt_close($stmt);
            echo "Ga ada komen yang dihapus";
        }

        
        mysqli_close($con);
    }
?>