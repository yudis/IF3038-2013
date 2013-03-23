<?php
    function connectDB(){
        $db_host = "localhost";
        $db_name = "progin_405_13510020";
        $username = "progin";
        $password = "progin";
        $db_con = mysql_connect($db_host, $username, $password);
        $connection_string = mysql_select_db($db_name);
	return $connection_string;
    }

    function isPinValid(){
            if($_SESSION['valid']==1){
                    return true;
            }else{
                    return false;
            }
    }

    function logout(){
            $_SESSION = array(); //destroy all of the session variables
            $_SESSION['valid'] = 0;
            session_destroy();
            return true;
    }
    
?>