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
                      
            while (mysqli_stmt_fetch($stmt)) {
                echo $h1,"\n",$h3,"\n",$h4,"\n";
            }
            
            mysqli_stmt_close($stmt);
        }        
        mysqli_close($con);
    }
?>
