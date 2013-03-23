<?php

require_once('config.php');
?>
<?php

if (connectDB()) {
    $IDComment = $_GET['IDComment'];
    
    $DeleteCommentQuery = "DELETE FROM comment WHERE IDComment='".$IDComment."';";
    $DeleteComment = mysql_query($DeleteCommentQuery);
}
?>