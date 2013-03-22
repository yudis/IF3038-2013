<?php

require_once('config.php');
?>
<?php

session_start();

if (connectDB()) {
    $category = "SELECT category.IDCategory,category.CategoryName FROM assignment,task,category WHERE assignment.Username=\"" . $_SESSION['username'] . "\" AND assignment.IDTask=task.IDTask AND task.IDCategory=category.IDCategory
UNION DISTINCT
SELECT category.IDCategory,category.CategoryName FROM authority,category WHERE authority.Username=\"" . $_SESSION['username'] . "\" AND authority.IDCategory=category.IDCategory
ORDER BY CategoryName";
    $result = mysql_query($category);
    $output = "";    
    if ($result > 0) {
        while ($data = mysql_fetch_array($result)) {
            
            $output = $output . "<div class = \"kategori\" onclick = \"showTask(" . $data['IDCategory'] . ");\"><a>" . $data['CategoryName'] . "</a></div>";
        }
         echo($output);
    }
   
} else {
    die('Database connection error');
}
?>
