<?php
// Contoh String Jawaban
$task[]='<div id="task">
			<div id="taskName">Nama Taks 1</div>
			<div id="taskDeadline">Tanggal Deadline</div>
			<div id="taskTag">Tag</div>
			<div id="taskStatus">Status</div>
			<div id="taskButton">Button</div>
		</div>'; 
$task[]='<div id="task">
			<div id="taskName">Nama Taks 2</div>
			<div id="taskDeadline">Tanggal Deadline</div>
			<div id="taskTag">Tag</div>
			<div id="taskStatus">Status</div>
			<div id="taskButton">Button</div>
		</div>';
$task[]='<div id="task">
			<div id="taskName">Nama Taks 3</div>
			<div id="taskDeadline">Tanggal Deadline</div>
			<div id="taskTag">Tag</div>
			<div id="taskStatus">Status</div>
			<div id="taskButton">Button</div>
		</div>';
$task[]='<div id="task">
			<div id="taskName">Nama Taks 4</div>
			<div id="taskDeadline">Tanggal Deadline</div>
			<div id="taskTag">Tag</div>
			<div id="taskStatus">Status</div>
			<div id="taskButton">Button</div>
		</div>';
$task[]='<div id="task">
			<div id="taskName">Nama Taks 5</div>
			<div id="taskDeadline">Tanggal Deadline</div>
			<div id="taskTag">Tag</div>
			<div id="taskStatus">Status</div>
			<div id="taskButton">Button</div>
		</div>';
$task[]='<div id="task">
			<div id="taskName">Nama Taks 6</div>
			<div id="taskDeadline">Tanggal Deadline</div>
			<div id="taskTag">Tag</div>
			<div id="taskStatus">Status</div>
			<div id="taskButton">Button</div>
		</div>';
$task[]='<div id="task">
			<div id="taskName">Nama Taks 7</div>
			<div id="taskDeadline">Tanggal Deadline</div>
			<div id="taskTag">Tag</div>
			<div id="taskStatus">Status</div>
			<div id="taskButton">Button</div>
		</div>';
$task[]='<div id="task">
			<div id="taskName">Nama Taks 8</div>
			<div id="taskDeadline">Tanggal Deadline</div>
			<div id="taskTag">Tag</div>
			<div id="taskStatus">Status</div>
			<div id="taskButton">Button</div>
		</div>';
$task[]='<div id="task">
			<div id="taskName">Nama Taks 9</div>
			<div id="taskDeadline">Tanggal Deadline</div>
			<div id="taskTag">Tag</div>
			<div id="taskStatus">Status</div>
			<div id="taskButton">Button</div>
		</div>';
$task[]='<div id="task">
			<div id="taskName">Nama Taks 10</div>
			<div id="taskDeadline">Tanggal Deadline</div>
			<div id="taskTag">Tag</div>
			<div id="taskStatus">Status</div>
			<div id="taskButton">Button</div>
		</div>';				
$user[]='<div id="user">
			<div id="userName">User Name 1</div>
			<div id="userFullName">User Full Name</div>
			<div id="userAvatar">User Avatar</div>
		</div>';
$user[]='<div id="user">
			<div id="userName">User Name 2</div>
			<div id="userFullName">User Full Name</div>
			<div id="userAvatar">User Avatar</div>
		</div>';
$user[]='<div id="user">
			<div id="userName">User Name 3</div>
			<div id="userFullName">User Full Name</div>
			<div id="userAvatar">User Avatar</div>
		</div>';
$user[]='<div id="user">
			<div id="userName">User Name 4</div>
			<div id="userFullName">User Full Name</div>
			<div id="userAvatar">User Avatar</div>
		</div>';
$user[]='<div id="user">
			<div id="userName">User Name 5</div>
			<div id="userFullName">User Full Name</div>
			<div id="userAvatar">User Avatar</div>
		</div>';
$user[]='<div id="user">
			<div id="userName">User Name 6</div>
			<div id="userFullName">User Full Name</div>
			<div id="userAvatar">User Avatar</div>
		</div>';
$user[]='<div id="user">
			<div id="userName">User Name 7</div>
			<div id="userFullName">User Full Name</div>
			<div id="userAvatar">User Avatar</div>
		</div>';
$user[]='<div id="user">
			<div id="userName">User Name 8</div>
			<div id="userFullName">User Full Name</div>
			<div id="userAvatar">User Avatar</div>
		</div>';
$user[]='<div id="user">
			<div id="userName">User Name 9</div>
			<div id="userFullName">User Full Name</div>
			<div id="userAvatar">User Avatar</div>
		</div>';
$user[]='<div id="user">
			<div id="userName">User Name 10</div>
			<div id="userFullName">User Full Name</div>
			<div id="userAvatar">User Avatar</div>
		</div>';		

$result = '';		
for($i=0; $i<count($task); $i++)
    {
		if($i==0){
			$result = $task[$i];
		}
		else{
			$result = $result.'<pemisah>'.$task[$i];
		}		
    }
$result = $result.'<end_task>';	
for($i=0; $i<count($user); $i++)
    {
		$result = $result.'<pemisah>'.$user[$i]; 
    }
echo $result;	
?>