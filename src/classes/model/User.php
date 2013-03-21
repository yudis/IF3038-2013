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
		 * @return User the static model class
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
			if (!preg_match("/^.{5,}$/", $this->username))
			{
				$error["username"] = "Username harus minimal 5 karakter.";
			}
			if ((!preg_match("/^.{8,}$/", $this->data['password'])) || 
				($this->data['password']!=$this->data['confirm_password']) || 
				($this->data['password']==$this->data['e-mail']) || 
				($this->data['password']==$this->data['username']))
			{
				$error["password"] = "Sandi harus minimal 8 karakter, tidak sama dengan email dan username.";
			}
			if (!preg_match("/^.+ .+$/", $this->fullname))
			{
				$error["name"] = "Nama lengkap harus terdiri dari 2 kata dipisah oleh spasi.";
			}
			if ((!preg_match("#^[1-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]$#", $this->data['birthdate'])) && 
				(explode("/", $this->birthdate)[sizeof(explode("/", $this->birthdate))-1]>=1955))
			{
				$error["birth_date"] = "Format tanggal lahir yang dimasukkan salah.";
			}
			if (!preg_match("/^.+@.+\...+$/", $this->email))
			{
				$error["email"] = "Format email yang dimasukkan salah.";
			}
			if (!preg_match("/^.+\.(jpe?g|JPE?G)$/",$this->avatar))
			{
				$error["avatar"] = "Avatar harus berekstensi jpeg atau jpg.";
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
			if ($this->id_user==null)
			{
				// new user
				if (User::model()->find("username='".addslashes($this->username)."' OR email='".addslashes($this->email)."'")->data)
				{
					// username and email already used
					return false;
				}
				else
				{
					$result = DBConnection::DBquery("INSERT INTO `".self::tableName()."`".
													" (username, email, fullname, avatar, birthdate, password)".
													" VALUES ('".addslashes($this->username)."','".addslashes($this->email)."',
														'".addslashes($this->fullname)."','".$this->avatar."',
														'".addslashes($this->birthdate)."','".md5($this->password)."')");

					return $result;
				}
			}
			else
			{
				// existing user
				if (count(User::model()->find("username='".$this->username."' OR email='".$this->email."'"))>1)
				{
					// username and email already used
					return false;
				}
				else
				{
					$result = DBConnection::DBquery("UPDATE `".self::tableName()."` SET ".
													" username = '".addslashes($this->username)."', email = '".addslashes($this->email)."'".
													", fullname = '".addslashes($this->fullname)."', avatar = '".$this->avatar."'".
													", birthdate = '".addslashes($this->birthdate)."', password = '".md5($this->password)."'");

					return $result;
				}
			}
		}
		
		/**
		 * Get the task created by the user
		 * @return array of Task that is created by the user
		 */
		public function getCreatedTasks() 
		{
			return Task::model()->findAll("id_task IN (SELECT id_task FROM have_task WHERE id_user='" . $this->id_user . "')");
		}
		
		/**
		 * Get the task assigned to the user
		 * @return array of Task that is assigned to the user
		 */
		public function getAssignedTasks()
		{
			return Task::model()->findAll("id_task IN (SELECT id_task FROM assign WHERE id_user='" . $this->id_user . "')");
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
			return Category::model()->findAll("id_user='" . $this->id_user . "'");
		}
		
		/**
		 * Get the category either created or supervised by the user
		 * @return array of Category that is either created or supervised by the user
		 */
		public function getCategories() 
		{
			$id = addslashes($this->id_user);
			return Category::model()->findAll("id_user='$id' OR id_kategori IN (SELECT id_katego FROM edit_kategori WHERE id_user='$id')");
		}

		public function findByUsername($username) 
		{
			return $this->find("username='" . addslashes($username) . "'");
		}
	}
?>