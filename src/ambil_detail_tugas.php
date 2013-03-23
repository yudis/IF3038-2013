<?php
    $con = mysqli_connect("localhost","root","","progin_405_13510003");
    
    if (mysqli_connect_errno($con)) {
        echo "Gagal Koneksi";
    } else {
        $id_tugas = $_REQUEST["id_tugas"];
    
        if ($stmt = mysqli_prepare($con, "SELECT * FROM tugas WHERE (id_tugas = ?)")) {
            mysqli_stmt_bind_param($stmt,"d",$id_tugas);
            mysqli_stmt_execute($stmt);
            
            if (mysqli_stmt_affected_rows($stmt)) {
                $h1=0;
                $h2=0;
                $h3="";
                $h4="";
                $h5=0;
                $h6="";
                $h7="";
                mysqli_stmt_bind_result($stmt,$h1,$h2,$h3,$h4,$h5,$h6,$h7);
                mysqli_stmt_fetch($stmt);
                echo $h3,"\n",$h4,"\n",$h5,"\n",$h6,"\n",$h7,"\n";
                mysqli_stmt_close($stmt);
                
                //Mengambil daftar assignee
                $query = "SELECT username FROM mengerjakan WHERE (id_tugas = $id_tugas)";
                $result = mysqli_query($con,$query);
                
                if ($result != false) {
                    while ($row = mysqli_fetch_row($result)) {
                        echo $row[0],"\n";
                    }
                } else {
                    echo "Gagal mengambil daftar assignee\n";
                }
                
                mysqli_free_result($result);
            } else {
                echo "Tidak ada tugas dengan id_tugas tersebut";
                mysqli_stmt_close($stmt);                
            }

        }        
        mysqli_close($con);
    }    
?>
