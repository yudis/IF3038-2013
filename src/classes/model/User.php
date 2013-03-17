<?php

	include_once "SimpleRecord.php";
	
	class User extends SimpleRecord
	{
		public static function model($className=__CLASS__)
		{
			return parent::model($className);
		}
	
		public static function tableName()
		{
			return "user";
		}
		
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
		
		public function getTasks() 
		{
			return Task::model()->findAll("id_task IN (SELECT id_task FROM have_tasks WHERE username='" . $this->username . "')");
		}

		public function getCategories() {
			$id = addslashes($this->id_user);
			return Category::model()->findAll("id_user='$id' OR id_kategori IN (SELECT id_katego FROM edit_kategori WHERE id_user='$id')");
		}

		public function findByUsername($username) {
			return $this->find("username='" . addslashes($username) . "'");
		}
	}
?>