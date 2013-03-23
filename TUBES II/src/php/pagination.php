<?php
session_start(); 
include 'ChromePhp.php';
ChromePhp::log('rincian.php');

$iduser = $_SESSION['login'];
ChromePhp::log($iduser);
$idtask = $_GET['t'];
ChromePhp::log($idtask);

	$con = mysqli_connect(localhost,"progin","progin","progin") or die ('Cannot connect to database : ' . mysql_error());

	// How many adjacent pages should be shown on each side?
	$adjacents = 3;
	
	/* 
	   First get total number of rows in data table. 
	   If you have a WHERE clause in your query, make sure you mirror it here.
	*/
	$query = "SELECT COUNT(*) as num FROM komentar INNER JOIN task ON task.ID=komentar.IDTask WHERE task.ID='".$idtask."'";
        $total_pages_res = mysqli_query($con, $query);
	$total_pages_row = mysqli_fetch_array($total_pages_res);
	$total_pages = $total_pages_row['num'];
	ChromePhp::log($total_pages);
	/* Setup vars for query. */
	$targetpage = "php/pagination.php"; 	//your file name  (the name of this file)
	$limit = 10; 								//how many items to show per page
	$page = $_GET['page'];
        ChromePhp::log($page);
	if($page) 
		$start = ($page - 1) * $limit; 			//first item to display on this page
	else
		$start = 0;								//if no page var is given, set start to 0
	
	/* Get data. */
	$sql = "SELECT * FROM komentar INNER JOIN task ON task.ID=komentar.IDTask LIMIT $start, $limit";
	$result = mysqli_query($con, $sql);
	
	/* Setup page vars for display. */
	if ($page == 0) $page = 1;					//if no page var is given, default to 1.
	$prev = $page - 1;							//previous page is page - 1
	$next = $page + 1;							//next page is page + 1
	$lastpage = ceil($total_pages/$limit);		//lastpage is = total pages / items per page, rounded up.
	$lpm1 = $lastpage - 1;						//last page minus 1
	
	/* 
		Now we apply our rules and draw the pagination object. 
		We're actually saving the code to a variable in case we want to draw it more than once.
	*/
	$pagination = "";
	if($lastpage > 1)
	{	
		$pagination .= "<div class=\"pagination\">";
                ChromePhp::log($pagination);
		//previous button
		if ($page > 1) 
			$pagination.= "<a href='#' onclick=\"loadComment($prev,'".$idtask."')\">? previous</a>";
		else
			$pagination.= "<span class=\"disabled\">? previous</span>";	
		
		//pages	
		if ($lastpage < 7 + ($adjacents * 2))	//not enough pages to bother breaking it up
		{	
			for ($counter = 1; $counter <= $lastpage; $counter++)
			{
				if ($counter == $page)
					$pagination.= "<span class=\"current\">$counter</span>";
				else
					$pagination.= "<a href='#' onclick=\"loadComment($counter,'".$idtask."')\">$counter</a>";					
			}
		}
		elseif($lastpage > 5 + ($adjacents * 2))	//enough pages to hide some
		{
			//close to beginning; only hide later pages
			if($page < 1 + ($adjacents * 2))		
			{
				for ($counter = 1; $counter < 4 + ($adjacents * 2); $counter++)
				{
					if ($counter == $page)
						$pagination.= "<span class=\"current\">$counter</span>";
					else
						$pagination.= "<a href='#' onclick=\"loadComment($counter,'".$idtask."')\">$counter</a>";					
				}
				$pagination.= "...";
				$pagination.= "<a href='#' onclick=\"loadComment($lpm1,'".$idtask."')\">$lpm1</a>";
				$pagination.= "<a href='#' onclick=\"loadComment($lastpage,'".$idtask."')\">$lastpage</a>";		
			}
			//in middle; hide some front and some back
			elseif($lastpage - ($adjacents * 2) > $page && $page > ($adjacents * 2))
			{
				$pagination.= "<a href='#' onclick=\"loadComment(1,'".$idtask."')\">1</a>";
				$pagination.= "<a href='#' onclick=\"loadComment(2,'".$idtask."')\">2</a>";
				$pagination.= "...";
				for ($counter = $page - $adjacents; $counter <= $page + $adjacents; $counter++)
				{
					if ($counter == $page)
						$pagination.= "<span class=\"current\">$counter</span>";
					else
						$pagination.= "<a href='#' onclick=\"loadComment($counter,'".$idtask."')\">$counter</a>";					
				}
				$pagination.= "...";
				$pagination.= "<a href='#' onclick=\"loadComment($lpm1,'".$idtask."')\">$lpm1</a>";
				$pagination.= "<a href='#' onclick=\"loadComment($lastpage,'".$idtask."')\">$lastpage</a>";		
			}
			//close to end; only hide early pages
			else
			{
				$pagination.= "<a href='#' onclick=\"loadComment(1,'".$idtask."')\">1</a>";
				$pagination.= "<a href='#' onclick=\"loadComment(2,'".$idtask."')\">2</a>";
				$pagination.= "...";
				for ($counter = $lastpage - (2 + ($adjacents * 2)); $counter <= $lastpage; $counter++)
				{
					if ($counter == $page)
						$pagination.= "<span class=\"current\">$counter</span>";
					else
						$pagination.= "<a href='#' onclick=\"loadComment($counter,'".$idtask."')\">$counter</a>";					
				}
			}
		}
		
		//next button
		if ($page < $counter - 1) 
			$pagination.= "<a href='#' onclick=\"loadComment($next,'".$idtask."')\">next ?</a>";
		else
			$pagination.= "<span class=\"disabled\">next ?</span>";
		$pagination.= "</div>\n";		
	}
        
        
        while($row = mysqli_fetch_assoc($result))
        {

        $pagination.="-- ".$row['IDUser']." ".$row['Waktu']." ".$row['Isi']." <br>";

        }
        ChromePhp::log($pagination);
        echo $pagination;
	?>

	