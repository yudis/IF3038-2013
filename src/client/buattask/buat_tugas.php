<?php
    $con = mysqli_connect("localhost","root","","progin_405_13510003");
    
    if (mysqli_connect_errno($con)) {
        echo "Gagal Koneksi";
    } else {
        $nama_tugas = $_REQUEST["nama"];
        $orang = $_REQUEST["assignee"];
        $tag = $_REQUEST["tag"];
        $tanggal = date_create_from_format("Y-m-d H:i:s",$_REQUEST["deadline"])->format('Y-m-d H:i:s');
        $attach = $_REQUEST["attachment"];
        $id_kategori = $_REQUEST["id_kategori"];
    
        $result = mysqli_query($con,"SELECT MAX(id_tugas) FROM tugas");
        $row = mysqli_fetch_row($result);
        $id_tugas = 1+$row[0];
        mysqli_free_result($result);
    
        if ($stmt = mysqli_prepare($con, "INSERT INTO tugas(id_tugas,nama_tugas,status,deadline,id_kategori,tag,attachment) VALUES (?,?,0,?,?,?,?)")) {
            mysqli_stmt_bind_param($stmt,"dssdss",$id_tugas,$nama_tugas,$tanggal,$id_kategori,$tag,$attach);
            mysqli_stmt_execute($stmt);
            //echo mysqli_stmt_affected_rows($stmt),'\n';
            //echo $nama_tugas,$id_tugas;
            
            if (mysqli_stmt_affected_rows($stmt) == 1) {
                mysqli_stmt_close($stmt);
                
                //Memperbaharui daftar assignee                
                $daftar = explode("/",$orang);
                if ($stmt2 = mysqli_prepare($con,"INSERT INTO mengerjakan (id_tugas,username,status_tugas) VALUES (?,?,0)")) {
                    foreach($daftar as $nama) {
                        mysqli_stmt_bind_param($stmt2,"ds",$id_tugas,$nama);
                        mysqli_stmt_execute($stmt2);
                    }
                    mysqli_stmt_close($stmt2);                    
                } else {
                    echo "Gagal memasukkan assignee baru";
                }
                echo true;
            } else {
                echo "Gagal memasukkan tugas";
                mysqli_stmt_close($stmt);     
            }
        } else {
            echo "parsing failed";
        }
        mysqli_close($con);
    }
?>
