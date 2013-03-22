<?php
    $con = mysqli_connect("localhost","root","","progin_405_13510003");
    
    if (mysqli_connect_errno($con)) {
        echo "Gagal Koneksi";
    } else {
        $id_tugas = $_REQUEST["id_tugas"];
        $orang = $_REQUEST["assignee"];
        $beres = $_REQUEST["status"];
        $tag = $_REQUEST["tag"];
    
        if ($stmt = mysqli_prepare($con, "UPDATE tugas SET status=?,tag = ? WHERE (id_tugas = ?)")) {
            mysqli_stmt_bind_param($stmt,"dsd",$beres,$tag,$id_tugas);
            mysqli_stmt_execute($stmt);
            
            if (mysqli_stmt_affected_rows($stmt)) {
                mysqli_stmt_close($stmt);
                
                //Memperbaharui daftar assignee
                $query = "DELETE FROM mengerjakan WHERE (id_tugas = $id_tugas)";
                $result = mysqli_query($con,$query);
                
                $daftar = explode("/",$orang);
                if ($stmt2 = mysqli_prepare($con,"INSERT INTO mengerjakan (id_tugas,username,status_tugas) VALUES (?,?,?)")) {
                    foreach($daftar as $nama) {
                        mysqli_stmt_bind_param($stmt2,"dsd",$id_tugas,$nama,$beres);
                        mysqli_stmt_execute($stmt2);
                    }
                    mysqli_stmt_close($stmt2);                    
                } else {
                    echo "Gagal memasukkan assignee baru";
                }
                echo true;
            } else {
                echo "Tidak ada tugas dengan id_tugas tersebut";
                mysqli_stmt_close($stmt);                
            }

        } else {
            echo "parsing failed";
        }
        mysqli_close($con);
    }
?>