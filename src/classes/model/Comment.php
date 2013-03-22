<?php
	include_once "SimpleRecord.php";
	
	/**
	 * This is the model class for table "comment".
	 *
	 * The followings are the available columns in table 'comment':
	 * @property integer $id_komentar
	 * @property string $timestamp
	 * @property string $komentar
	 * @property string $id_user
	 * @property string $id_task
	 */
	class Comment extends SimpleRecord
	{		
		/**
		 * Returns the static model of the specified SimpleRecord class.
		 * @param string $className active record class name.
		 * @return Comment the static model class
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
			return "comment";
		}
		
		/**
		 * Check the validity of the record
		 * @return array of errors
		 */
		public function checkValidity()
		{
			$error = array();
			if (!preg_match("/^.{5,}$/", $this->data['username']))
			{
				$error["username"] = "Username harus minimal 5 karakter.";
			}
			return $error;
		}
		
		/**
		 * Save the current model, insert if new model, update if exists
		 * @return boolean whether record is saved or not
		 */
		public function save()
		{
			// check same comment name
			if ($this->id==null)
			{
				// new comment
				DBConnection::openDBconnection();
				
				$result = DBConnection::DBquery("INSERT into ".tableName()."");
				
				DBConnection::closeDBconnection();
								
				return $result;
			}
			else
			{
				// existing comment
			}
		}
		
		/**
		 * Get the user who comment
		 * @return User who comment
		 */
		public function getUser()
		{
			return User::model()->find("id_user = ".$this->id_user, array("id_user", "username", "fullname", "avatar"));
		}
		
		/**
		 * Get the task where the comment exist
		 * @return Task where the comment exist
		 */
		public function getTask()
		{
			return Task::model()->find("id_task = ".$this->id_task);
		}
	}
?>
