<?php
    session_start();
    session_destroy(); //menghapus semua variabel session yang ada
    header("Location:index.php");
?>