<?php
	
	if(isset($_POST['btnsubmit'])) 
	{
		$data=$_POST['data'];
		require_once("database.php");
		$con= connectDatabase();
		
		
			
		for($i=0;$i<sizeof($data);$i++)
		{
		$query= "UPDATE task SET status = 'done' WHERE namaTask = 'Go to work'";
		mysqli_query($con, $query);
		
		}
		
		$num = mysqli_affected_rows($con);

		if ($num > 0) {
			echo "Task has been completed, congratulations";
			?> <br/> <br/>
			<a href="dashboard.php" > Go Back To Dashboard</a>
			<?php
			}
		else {
			echo "Failed";
			}
	}
	else
	{
		echo "Unable to mark specified task done";
	}    
	
?>