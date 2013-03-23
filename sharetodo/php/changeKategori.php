<?php
    session_start();
    
    $kategori = $_GET['k'];
    //$kategori = "Pemrograman Internet"; //harusnya dipassing dari parameter function nanti
    
    //create connection
    $con = mysqli_connect("127.0.0.1","root","root","distributedAgenda");

    //check the connection
    if (mysqli_connect_errno($con)) {
        echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
    }else {
        $result = mysqli_query($con,"SELECT * FROM task WHERE namaKategori='$kategori'");
        while ($row = mysqli_fetch_array($result)){
            $nama = $row['namaTask'];
            echo "<div id={$nama}space>";
                echo "<div id='taskTitle1' class='taskElmtLeft' onclick=toHalamanRincianTugas('taskTitle1');>";
                    echo "<p></strong>".$nama."</strong></p>";
                echo "</div>";
                echo "<div class='taskElmtRight'>";
                echo "</div>";
                echo "<div class='taskElmtLeft'>";
                    echo "<p>Deadline :</p>";
                echo "</div>";
                echo "/<div class='taskElmtRight'>";
                    echo "<p>".$row['deadline']."</p>";
                echo "</div>";
                echo "<div class='taskElmtLeft'>";
                    echo "<p>Tag :</p>";
                    echo "</div>";
                    echo "<div class='taskElmtRight'>";
                    
                    //mengambil semua tag untuk task tertentu
                    $tagQuery = "SELECT tag FROM tagging WHERE namaTask='".$row['namaTask']."'";
                    $resultTag = mysqli_query($con,$tagQuery);
                    $tagString = "";
                    while ($tag = mysqli_fetch_array($resultTag)){
                        $tagString .= $tag['tag']." | ";
                    }
                    $refined = substr($tagString,0,strlen($tagString)-3);
                    echo "<p>".$refined."</p>";
                echo "</div>";
                echo "<div class='taskElmtLeft'>";
                    echo "<p>Status :</p>";
                echo "</div>";
                $idStatus = $nama."status";
                echo "<div class='taskElmtRight' id='".$idStatus."'>";
                    echo "<p>".$row['status']."</p>";
                echo "</div>";
                echo "<div class='taskElmtLeft'>";
                echo "</div>";
                echo "<div class='taskElmtRight'>";
                    $nama = $row['namaTask'];
                    //$nama = "apake sih";
                    echo "<button class='ubahStatusTask' onclick=\"changeTaskStatus('$nama','$idStatus')\";>Ubah Status</button>";
                echo "</div>";
                echo "<div class='taskElmtLeft'>";
                echo "</div>";
                echo "<div class='taskElmtRight'>";
                    echo "<button class='ubahStatusTask' onclick=\"deleteTask('$nama')\";>Hapus Tugas</button>";
                echo "</div>";
            echo "</div>";
        }
        echo "<button class='addTask' onclick=\"toHalamanPembuatanTugas();\">tambah tugas</button>";
	echo "<button class='addTask' onclick=\"deleteKategori('$kategori');\">hapus kategori</button>";
    }
?>