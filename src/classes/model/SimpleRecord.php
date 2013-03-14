<?php

	include_once "User.php";
	include_once "Connection.php";
	
	abstract class SimpleRecord
	{
		private static $class_name;
		private static $models = array();
		protected $data = array();
		
		abstract protected function tableName();
		
		public function __construct()
		{
		}
		
		public static function model($className)
		{
			self::$class_name = $className;
			if (!array_key_exists($className, self::$models))
			{
				$models[$className] = new $className();
			}
			return $models[$className];
		}
		
		public function __get($property) 
		{
			if (array_key_exists($property, $this->data))
			{
				return $this->data[$property];
			}
		}

		public function __set($property, $value) 
		{
			$this->data[$property] = $value;
			return $this;
		}
		
		public function find($query="")
		{
			$ret = new self::$class_name();

			DBConnection::openDBconnection();
			
			if ($query != "")
				$query = " WHERE ".$query;
			$result = DBConnection::DBquery("SELECT * FROM ".$this->tableName().$query);
			$fields = $result->fetch_fields();

			$i = 0;
			$row = $result->fetch_object();

			foreach ($fields as $val) 
			{
				$name = $val->name;
				$ret->$name = $row->$name;
			}
			
			$result->close();
			
			DBConnection::closeDBconnection();
						
			return $ret;
		}
		
		public function findByAttributes($query)
		{
			DBConnection::openDBconnection();
			
			
			
			DBConnection::closeDBconnection();
		}
		
		public function findAll($query="")
		{
			$ret = array();
		
			DBConnection::openDBconnection();
			
			if ($query != "")
				$query = " WHERE ".$query;
			$result = DBConnection::DBquery("SELECT * FROM ".$this->tableName().$query);
			$fields = $result->fetch_fields();

			$i = 0;
			while ($row = $result->fetch_object())
			{
				$ret[$i] = new self::$class_name();
				foreach ($fields as $val) 
				{
					$name = $val->name;
					$ret[$i]->$name = $row->$name;
				}
				$i++;
			}
			
			$result->close();
			
			DBConnection::closeDBconnection();
			
			return $ret;
		}
		
		public function findAllByAttributes($query)
		{
			DBConnection::openDBconnection();
			
			
			
			DBConnection::closeDBconnection();
		}
	}

?>