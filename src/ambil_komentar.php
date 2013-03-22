<?php
    $con = mysqli_connect("localhost","root","","progin_405_13510003");
    
    if (mysqli_connect_errno($con)) {
        echo "Gagal Koneksi";
    } else {
        $id_tugas = $_REQUEST["id_tugas"];
    
        if ($stmt = mysqli_prepare($con, "SELECT * FROM komentar WHERE (id_tugas = ?) ORDER BY waktu")) {
            mysqli_stmt_bind_param($stmt,"d",$id_tugas);
            mysqli_stmt_execute($stmt);
            $h1="";
            $h2=0;
            $h3="";
            $h4="";
            mysqli_stmt_bind_result($stmt,$h1,$h2,$h3,$h4);
            
            $array;
            
            while (mysqli_stmt_fetch($stmt)) {
                $array[] = array($h1,$h3,$h4);
            }
            
            mysqli_stmt_close($stmt);
            
            foreach ($array as $row) {
                $result = mysqli_query($con,"SELECT avatar FROM pengguna WHERE (username = '$h1')");
                $avatar = mysqli_fetch_row($result);
                echo $row[0],"\n",$row[1],"\n",$row[2],"\n",$avatar[0],"\n";
                mysqli_free_result($result);
            }
        } else {
            echo "Gagal mengambil komentar";
        }
        mysqli_close($con);
    }
?>
