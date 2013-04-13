<html>
    <head></head>
    <body>
    <?php
        session_start();
        $task=$_POST['task'];
        $user=$_SESSION['login'];
        $komen=$_POST['isikomentar'];
        $id=$_POST['lastid']+1;
        echo $id;
        $timestamp= getdate();
        $timestampsend = $timestamp['year']."-".$timestamp['mon']."-".$timestamp['mday']." ".$timestamp['hours'].":".$timestamp['minutes'].":".$timestamp['seconds'];
        require_once("database.php");
        $con = connectDatabase();
        $query = "INSERT INTO `sharedtodolist`.`komentar` (`idKomentar`, `komentator`, `isikomentar`, `namaTask`, `timestamp`) VALUES ('$id', '$user', '$komen', '$task', '$timestampsend');";
        mysqli_query($con,$query);
        header("Location:viewtask.php?nama=".$task);
    ?>
    </body>
</html>