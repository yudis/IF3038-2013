<?php
    $datetime = $_REQUEST["tanggal"];
    $unixtime = strtotime( $datetime );

    if ( FALSE !== $unixtime ) {
        echo true;
    } else {
        echo false;
    }
?>
