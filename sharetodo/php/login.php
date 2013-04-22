<?php
    session_start();
    $con = mysqli_connect("localhost", "progin", "progin", "progin_405_13510027");
    $query = mysqli_query($con, "SELECT username, password FROM user");
    
    while($row = mysqli_fetch_assoc($query)) {
        $username[] = $row["username"];
        $password[] = $row["password"];
    }
//    //email
//    $username[] = "msmaromi";
//    $username[] = "sonnymanurung";
//    $username[] = "krisnadibyo";
//    
//    //password
//    $password[] = "debbeh";
//    $password[] = "hottest";
//    $password[] = "barca";
    
    $usrnm = $_GET["u"];
    $psswrd = $_GET["p"];        
    
    $response = "Username dan password tidak cocok";
    for($i=0; $i<count($username); $i++) {
        if($usrnm==$username[$i] && $psswrd==$password[$i]) {
            $response = "";
            $_SESSION["username"] = $username[$i];
            $_SESSION["loggedin"] = "yes";
            break;
        }
    }
    echo $response;
?>