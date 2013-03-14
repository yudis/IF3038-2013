<?php

	include_once "SimpleRecord.php";
	
	class User extends SimpleRecord
	{
		public static function model($className=__CLASS__)
		{
			return parent::model($className);
		}
	
		protected function tableName()
		{
			return "tbl_users";
		}
	}
?>