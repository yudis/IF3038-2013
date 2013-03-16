<?php
	include_once "SimpleRecord.php";
	
	/**
	 * This is the model class for table "user".
	 *
	 * The followings are the available columns in table 'user':
	 * @property integer $id_user
	 * @property string $username
	 * @property string $e-mail
	 * @property string $fullname
	 * @property string $avatar
	 * @property string $birthdate
	 * @property string $password
	 */
	class User extends SimpleRecord
	{
		/**
		 * Returns the static model of the specified SimpleRecord class.
		 * @param string $className active record class name.
		 * @return MateriKuliah the static model class
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
			return "user";
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
			if ((!preg_match("/^.{8,}$/", $this->data['password'])) || 
				($this->data['password']!=$this->data['confirm_password']) || 
				($this->data['password']==$this->data['email']) || 
				($this->data['password']==$this->data['username']))
			{
				$error["password"] = "Sandi harus minimal 8 karakter, tidak sama dengan email dan username.";
			}
			if (!preg_match("/^.+ .+$/", $this->data['name']))
			{
				$error["name"] = "Nama lengkap harus terdiri dari 2 kata dipisah oleh spasi.";
			}
			if ((!preg_match("#^[0-3][0-9]/[0-1][0-9]/[1-9][0-9][0-9][0-9]$#", $this->data['birth_date'])) && 
				(explode("/", $this->data['birth_date'])[sizeof(explode("/", $this->data['birth_date']))-1]>=1955))
			{
				$error["birth_date"] = "Format tanggal lahir yang dimasukkan salah.";
			}
			if (!preg_match("/^.+@.+\...+$/", $this->data['email']))
			{
				$error["email"] = "Format email yang dimasukkan salah.";
			}
			return $error;
		}
		
		/**
		 * Save the current model, insert if new model, update if exists
		 * @return boolean whether record is saved or not
		 */
		public function save()
		{
			// check same username or email
			if ($this->id==null)
			{
				// new user
				if (($this->username=="admin")||($this->email=="fernandojordan.92@gmail.com"))
				{
					return false;
				}
				DBConnection::openDBconnection();
				
				$result = DBConnection::DBquery("INSERT into ".tableName()."");
				
				DBConnection::closeDBconnection();
								
				return $result;
			}
			else
			{
				// existing user
			}
		}
		
		/**
		 * Get the task associated with the user
		 * @return array of Task that is associated
		 */
		public function getTasks() 
		{
			return Task::model()->findAll("id_task IN (SELECT id_task FROM have_task WHERE id_user='" . $this->id_user . "')");
		}
		
		/**
		 * Get the category supervised by the user
		 * @return array of Category that is supervised by the user
		 */
		public function getSupervisedCategory() 
		{
			return Category::model()->findAll("id_kategori IN (SELECT id_katego FROM edit_kategori WHERE id_user='" . $this->id_user . "')");
		}
		
		/**
		 * Get the category created by the user
		 * @return array of Category that is created by the user
		 */
		public function getCreatedCategory() 
		{
			return Category::model()->findAll("id_user='" . $this->id_user);
		}
	}
?>