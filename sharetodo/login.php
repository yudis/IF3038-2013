<?php
    session_start();    
    //email
    $username[] = "msmaromi";
    $username[] = "smanurung";
    $username[] = "krisnadibyo";
    
    //password
    $password[] = "debbeh";
    $password[] = "hottest";
    $password[] = "barca";
    
    $usrnm = $_GET["u"];
    $psswrd = $_GET["p"];        
    
    $response = "Username dan password tidak cocok";
    for($i=0; $i<count($username); $i++) {
        if($usrnm==$username[$i] && $psswrd==$password[$i]) {
            $response = "";
            $_SESSION["username"] = $username[$i];
            $_SESSION["password"] = $password[$i];
            $_SESSION["loggedin"] = "yes";
            break;
        }
    }
    echo $response;
?>