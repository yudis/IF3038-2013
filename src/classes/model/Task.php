<?php

	include_once "SimpleRecord.php";
	
	class Task extends SimpleRecord
	{		
		public static function model($className=__CLASS__)
		{
			return parent::model($className);
		}
	
		public static function tableName()
		{
			return "task";
		}
		
		public function checkValidity()
		{
			$error = array();
			if (!preg_match("/^.{5,}$/", $this->data['username']))
			{
				$error["username"] = "Username harus minimal 5 karakter.";
			}
			return $error;
		}
		
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

		public function getTags() {
			return Tag::model()->findAll("id_tag IN (SELECT id_tag FROM have_tags WHERE id_task='" . $this->id_task . "')");
		}
	}
?>