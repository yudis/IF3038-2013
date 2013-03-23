<?php
    $con = mysqli_connect("localhost", "root", "", "sharetodo");
    $query = mysqli_query($con, "SELECT username, email FROM user");
    
    while($row = mysqli_fetch_assoc($query)) {
        $username[] = $row["username"];
        $email[] = $row["email"];
    }
//    //username
//    $username[] = "msmaromi";
//    $username[] = "sonnymanurung";
//    $username[] = "krisnadibyo";
//    
//    
//    //email
//    $email[] = "romi@gmail.com";
//    $email[] = "sonny@gmail.com";
//    $email[] = "krisna@gmail.com";
    
    $q = $_GET["q"];
    $p = $_GET["p"];
    
    if(strlen($q)>0) {
        for($i=0; $i<count($username); $i++) {
            $response = "";
            if($q == $username[$i]) {
                $response = "Username sudah ada";
                break;
            }
        }
    }
    
    if(strlen($p)>0) {
        for($i=0; $i<count($email); $i++) {
            $response = "";
            if($p == $email[$i]) {
                $response = "Email sudah ada";
                break;
            }
        }
    }    
    
    echo $response;
?>