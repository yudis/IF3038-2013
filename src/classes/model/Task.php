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
	 * @property integer $id_kategori
	 * @property integer $id_user
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
			
			$user = User::model()->find("username='".$this->data['assignee']."'");
			if ($user->data)
			{
			
			}
			else 
			{
				$error['assignee'] = "User yang di-assign tidak ada di dalam basis data";
			}
			print_r ($error);
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
				$user_id = $_SESSION['user_id'];
				DBConnection::openDBconnection();
				$result = DBConnection::DBquery("INSERT INTO `task` (`nama_task`, `deadline`, `id_kategori`, `id_user`) VALUES ('".$this->data['nama_task']."', '".$this->data['deadline']."', '".$this->id_kategori."', '".$user_id."')");
				DBConnection::closeDBconnection();

				/* $array_of_tags = $this->data['tag'];
				$tags = explode(",", $array_of_tags);
				
				if ($tags)
				{
					foreach($tags as $temp_tag)
					{

						$tag = Tag::model()->find("tag_name='".$temp_tag."'");
						print_r ($tag->data);
						if ($tag->data) 
						{
									
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
				} */
				
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
			return Attachment::model()->find("id_task = ".$this->id_task);
		}
		
		/**
		 * Get the assignee in the task
		 * @return array of User that is asignee of task
		 */
		public function getAssignee()
		{
			return User::model()->findAll("id_user IN (SELECT id_user FROM assign WHERE id_task = '" . $this->id_task . "')", array("id_user", "username"));
		}
	}
?>
