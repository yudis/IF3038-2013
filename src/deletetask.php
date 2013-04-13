<?php
	//$q=$_GET["q"];
	

	if(isset($_POST['btnsubmit'])) 
	{
		$data = $_POST['data'];
		require_once("database.php");
		$con= connectDatabase();
		
		for($i=0;$i<sizeof($data);$i++)
		{
			$query= "DELETE FROM task WHERE namaTask = 'Go to work'";			  
			mysqli_query($con,$query);	
		}
		
		$num = mysqli_affected_rows($con);

		if ($num > 0) {
			echo "Task has been deleted";
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
		echo "Unable to delete";
	}    
	
?>