<?php
	$task = Task::model()->find("id_task = ".$_POST['id_task']);
	
	if ($task->getEditable($this->app->currentUserId))
	{
		// replace values
		foreach ($_POST as $key => $value)
		{
			$task->$key = $_POST[$key];
		}
		
		// generate token
		$i = 0;
		$attachments = array();
		if (ISSET($_FILES['attachment']))
		{
			foreach($_FILES['attachment']['name'] as $name)
			{
				$temp = explode(".",$name);
				$extension = end($temp);
				$attachments[] = new Attachment();
				$attachments[$i]->attachment = strtoupper(md5(uniqid(rand(), true))).".".$extension;
				$attachments[$i]->temp_name = $_FILES['attachment']['tmp_name'];
			}
		}
		$task->attachments = $attachments;
		
		$temperror = $task->checkValidity();
		
		if ($temperror)
		{
			print_r($temperror);
		}
		else
		{
			if ($task->save())
			{
				foreach ($tags as $tag)
				{
					$tag->save();
				}
				foreach($attachments as $attachment)
				{
					if ($attachment->save())
					{
						move_uploaded_file($attachment->temp_name, $_SESSION['full_path']."upload/attachments/" . $attachment->attachment);
					}
				}
			}
			else
			{
				echo "fail";
			}
		}
	}
?>
