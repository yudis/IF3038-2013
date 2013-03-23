<?php
    $con = mysqli_connect("localhost","root","","progin_405_13510003");
    
    if (mysqli_connect_errno($con)) {
        echo "Gagal connect";
    } else {
        $user = $_REQUEST["assignee"];
        $panjang = strlen($user);
        
        if ($stmt = mysqli_prepare($con, "SELECT username FROM pengguna WHERE (LEFT(username,?) = ?)")) {
            mysqli_stmt_bind_param($stmt,"ds",$panjang,$user);
            mysqli_stmt_execute($stmt);
            $nama = "";
            mysqli_stmt_bind_result($stmt,$nama);
            while (mysqli_stmt_fetch($stmt)) {
                echo $nama,"\n";
            }
            mysqli_stmt_close($stmt);
        } else {
            echo "autocomplete gagal";
        }
        mysqli_close($con);
    }
?>
