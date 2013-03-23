<?php
    require_once("db.php");

    if ($_SERVER['REQUEST_METHOD'] == "POST"){
        session_start();
        $result = ProginDB::getInstance()->query("SELECT * FROM user WHERE username = '".$_SESSION['username']."'");
        $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
        if (($_POST['bioname'] === $row['fullname']) && ($_POST['biopassword1'] === $_SESSION['password']) && ($_POST['biodate'] === $row['dob']) && ($_FILES["biofile"]["name"]==="")){
            header('Location: profile.php?');
        } else {
            move_uploaded_file($_FILES["biofile"]["tmp_name"],"avatar/".$_FILES["biofile"]["name"]);
            ProginDB::getInstance()->edit_profile($_SESSION['username'], $_POST['bioname'], $_FILES["biofile"]["name"], $_POST['biodate'], $_POST['biopassword1']);
            header('Location: profile.php?edited');
        }
    }
?>