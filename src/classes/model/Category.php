<?php
	include_once "SimpleRecord.php";
	
	/**
	 * This is the model class for table "kategori".
	 *
	 * The followings are the available columns in table 'kategori':
	 * @property integer $id_kategori
	 * @property string $nama_kategori
	 * @property integer $id_user
	 */
	class Category extends SimpleRecord
	{		
		/**
		 * Returns the static model of the specified SimpleRecord class.
		 * @param string $className active record class name.
		 * @return Category the static model class
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
			return "kategori";
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
			// check same category name
			if ($this->id==null)
			{
				// new category
				$insert = "INSERT INTO %s (id_kategori, nama_kategori, id_user) VALUES (0, '%s', '%d')";
				$insert = sprintf($insert, $this->tableName(), addslashes($this->nama_kategori), $this->id_user);

				$success = DBConnection::DBquery($insert);

				if ($success)
					$this->id_kategori = DBConnection::insertID();
				else
					return false;

				return $result;
			}
			else
			{
				// existing category
				$insert = "UPDATE %s SET (id_kategori, nama_kategori, id_user) VALUES (0, '%s', '%d')";
				$insert = sprintf($insert, $this->tableName(), addslashes($this->nama_kategori), $this->id_user);

				$success = DBConnection::DBquery($insert);

				if ($success)
					$this->id_kategori = DBConnection::insertID();
				else
					echo 'Fail';

				return $result;
			}
		}
		
		/**
		 * Get the task associated with the category
		 * @return array of Task that is associated
		 */
		public function getTasks() 
		{
			return Task::model()->findAll("id_kategori='" . $this->id_kategori . "'");
		}
	}
?>