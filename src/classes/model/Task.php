<?php
	include_once "SimpleRecord.php";
	
	/**
	 * This is the model class for table "task".
	 *
	 * The followings are the available columns in table 'task':
	 * @property integer $id_task
	 * @property string $nama_task
	 * @property string $status
	 * @property string $deadline
	 * @property string $id_kategori
	 */
	class Task extends SimpleRecord
	{		
		/**
		 * Returns the static model of the specified SimpleRecord class.
		 * @param string $className active record class name.
		 * @return Task the static model class
		 */
		public static function model($className=__CLASS__)
		{
			return parent::model($className);
		}
	
		/**
		 * @return string the associated database table name
		 */
		public static function tableName()
		{
			return "task";
		}
		
		/**
		 * Check the validity of the record
		 * @return array of errors
		 */
		public function checkValidity()
		{
			$error = array();
			/* if (!preg_match("/^.{5,}$/", $this->data['username']))
			{
				$error["username"] = "Username harus minimal 5 karakter.";
			} */ 
			
			//$kategori = Category::model()->find("nama_kategori='".$
			$array_of_tags = $this->data['tag'];
			$tags = explode(",", $array_of_tags);
			if ($tags)
			{
				foreach($tags as $temp_tag)
				{
					$tag = Tag::model()->find("tag_name='".$temp_tag."'");
					// if tag exists, insert to table have_tags
					if ($tag->data) 
					{
						print_r ($tag);
						/*mysql_query("INSERT INTO `task` (`id_task`, `deadline`, `id_kategori`) VALUES ('".$this->data['nama_task']."', '".$this->data['deadline']."', '".5'");*/

						mysql_query("INSERT INTO `have_tags` (`id_task` ,`id_tag`) VALUES ('".$tag->data['id_tag']."'", '11');
					}
					// if tag doesn't exist, insert into table tags & have_tags
					else 
					{
						// insert into table tags
						$tag->data['tag_name'] = $temp_tag;
						$tag->save();
						// insert into table have tags
					}
				}
			}
			print_r ($tag_name);
			$user = User::model()->find("username='".$this->data['assignee']."'");
			if ($user->data)
			{
				$error['assignee'] = "asik";
			}
			else 
			{
				$error['assignee'] = "User yang di-assign tidak ada di dalam basis data";
			}
			print_r ($error);
			
			/*$i = 0;
			$tags = array();
			$temptags = explode(",", $task->assignee);
			foreach ($temptags as $tag)
			{
				if (Tag::model()->find("tag_name=".$tag))
				{
					$tags[] = new Tag();
					$tags[$i]->tag_name = $tag;
				}
			}
			$task->tags = $tags;
			
			$i = 0;
			$tags = array();
			$temptags = explode(",", $task->tag);
			foreach ($temptags as $tag)
			{
				if (Tag::model()->find("tag_name=".$tag))
				{
					$tags[] = new Tag();
					$tags[$i]->tag_name = $tag;
				}
			}
			$task->tags = $tags;
			*/
		}
		
		/**
		 * Save the current model, insert if new model, update if exists
		 * @return boolean whether record is saved or not
		 */
		public function save()
		{
			// check same task name
			if ($this->id==null)
			{
				// new task
				DBConnection::openDBconnection();
				
				$result = DBConnection::DBquery("INSERT into ".tableName()."");
				
				DBConnection::closeDBconnection();
								
				return $result;
			}
			else
			{
				// existing task
			}
		}

		/**
		 * Get the tags associated with the task
		 * @return array of Tag that is associated
		 */
		public function getTags() 
		{
			return Tag::model()->findAll("id_tag IN (SELECT id_tag FROM have_tags WHERE id_task='" . $this->id_task . "')");
		}
		
		/**
		 * Get the kategori where the task belongs
		 * @return Category where the task belongs
		 */
		public function getCategory()
		{
			return Category::model()->find("id_kategori = ".$this->id_kategori);
		}
		
		/**
		 * Get the attachments in the task
		 * @return array of Attachment in the task
		 */
		public function getAttachment()
		{
			//TODO implement Attachment model
			return Attachment::model()->findAll("id_task = ".$this->id_task);
		}
		
		/**
		 * Get the assignee in the task
		 * @return array of User that is asignee of task
		 */
		public function getAssignee()
		{
			return User::model()->findAll("id_user IN (SELECT id_user FROM assign WHERE id_task = '" . $this->id_task . "')", array("id_user", "username"));
		}
		
		/**
		 * Get the comment in the task
		 * @return array of Comment of the task
		 */
		public function getComment()
		{
			return Comment::model()->findAll("id_task = '".$this->id_task."' ORDER BY timestamp");
		}
		
		/**
		 * Check if task editable or not
		 * @return boolean
		 */
		public function getEditable($id_user)
		{
			$id_user = addslashes($id_user);
			return (User::model()->find("id_user IN (SELECT id_user FROM assign WHERE id_task='" . $this->id_task . "' AND id_user ='"+id_user+"')")) ? true : false ;
		}
		
		/**
		 * Check if task deletable or not
		 * @return boolean
		 */
		public function getDeletable($id_user)
		{
			$id_user = addslashes($id_user);
			return ($this->id_user == $id_user) ? true : false ;
		}
	}
?>
