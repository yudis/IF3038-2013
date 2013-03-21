<?php
    $kategori = "Pemrograman Internet"; //harusnya dipassing dari parameter function nanti
    
    //create connection
    $con = mysqli_connect("127.0.0.1","root","root","distributedAgenda");

    //check the connection
    if (mysqli_connect_errno($con)) {
        echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
    }else {
        $result = mysqli_query($con,"SELECT * FROM task WHERE namaKategori='$kategori'");
        while ($row = mysqli_fetch_array($result)){
            echo "<div id='taskTitle1' class='taskElmtLeft' onclick=toHalamanRincianTugas('taskTitle1');>";
		echo "<p></strong>".$row['namaTask']."</strong></p>";
	    echo "</div>";
            echo "<div class='taskElmtRight'>";
	    echo "</div>";
	    echo "<div class='taskElmtLeft'>";
		echo "<p>Deadline :</p>";
	    echo "</div>";
	    echo "/<div class='taskElmtRight'>";
		echo "<p>".$row['deadline']."</p>";
	    echo "</div>";
        }
    }
?>