<?php

require_once('config.php');
?>
<?php

session_start();

if (connectDB()) {
    $category = "SELECT category.IDCategory,category.CategoryName,category.Creator FROM assignment,task,category WHERE assignment.Username=\"" . $_COOKIE['UserLogin'] . "\" AND assignment.IDTask=task.IDTask AND task.IDCategory=category.IDCategory
        UNION DISTINCT
        SELECT category.IDCategory,category.CategoryName,category.Creator FROM authority,category WHERE authority.Username=\"" . $_COOKIE['UserLogin'] . "\" AND authority.IDCategory=category.IDCategory
        UNION DISTINCT
        SELECT IDCategory,CategoryName,category.Creator FROM category WHERE Creator=\"" . $_COOKIE['UserLogin'] . "\" 
        ORDER BY CategoryName";
    $result = mysql_query($category);
    $output = "";
    if ($result > 0) {
        while ($data = mysql_fetch_array($result)) {

            $output = $output . "<div class = \"kategori\" onclick = \"showTask(" . $data['IDCategory'] . ");\"><a>" . $data['CategoryName'] . "</a></div>";
            if($data['Creator']==$_COOKIE['UserLogin'])
            {
                $output = $output . "<img class=\"delcategory\" src=\"img/delete.png\" onclick=\"delCate(".$data['IDCategory'].");\">";
            }
        }
        echo($output);
    }
} else {
    die('Database connection error');
}
?>
