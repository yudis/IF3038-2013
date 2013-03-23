<?php       
    $con = mysqli_connect("localhost", "progin", "progin", "progin_405_13510027");
    $query = mysqli_query($con, "SELECT username FROM user");
    while($row = mysqli_fetch_assoc($query)) {
        $a[] = $row["username"];
    }
    
    $q = $_GET["key"];
    
    if (strlen($q) > 0){
        $hint = "";
        for ($i = 0; $i < count($a); $i++) {
            if (strtolower($q) == strtolower(substr($a[$i], 0, strlen($q)))) {
                if ($hint == "") {
                    $hint = $a[$i];
                } else {
                    $hint = $hint . ", " . $a[$i];
                }
            }
        }
    }
    
    if ($hint == "") {
        $response = "tidak ada saran";
    } else {
        $response = $hint;
    }
    
    echo $hint;    
?>
