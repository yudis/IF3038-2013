<?php
 
class User extends Model
{
	private $_username;
    private $_email;
    private $_password;
    private $_full_name;
    private $_tgl_lahir;
	private $_avatar;
	
	public function get_username() { return $this->_username; } 
	public function get_email() { return $this->_email; } 
	public function get_password() { return $this->_password; } 
	public function get_full_name() { return $this->_full_name; } 
	public function get_tgl_lahir() { return $this->_tgl_lahir; } 
	public function get_avatar() { return $this->_avatar; } 
	public function set_username($x) { $this->_username = $x; } 
	public function set_email($x) { $this->_email = $x; } 
	public function set_password($x) { $this->_password = md5($x); } 
	public function set_full_name($x) { $this->_full_name = $x; } 
	public function set_tgl_lahir($x) { $this->_tgl_lahir = $x; } 
	public function set_avatar($x) { $this->_avatar = $x; }
	
	public function fromArray($user)
	{
		$this->_username = $user["username"];
		$this->_email = $user["email"];
		$this->_password = $user["password"];
		$this->_full_name = $user["full_name"];
		$this->_tgl_lahir = $user["tgl_lahir"];
		$this->_avatar = $user["avatar"];	
	}
	
	public function toArray()
	{
		$user = array();
		$user["username"] = $this->_username;
		$user["email"] = $this->_email;
		$user["password"] = $this->_password;
		$user["full_name"] = $this->_full_name;
		$user["tgl_lahir"] = $this->_tgl_lahir;
		$user["avatar"] = $this->_avatar;
		
		return $user;
	}
	
    public function getUserLogin($username, $passwd)
    {
        $sql = "SELECT * FROM users WHERE username=? AND password=MD5(?)";
        $this->_setSql($sql);
		
        $user = $this->getRow(array($username, $passwd));
         
        if (empty($user))
        {
            return false;
        }
		
		$this->_username = $user["username"];
		$this->_email = $user["email"];
		$this->_password = $user["password"];
		$this->_full_name = $user["full_name"];
		$this->_tgl_lahir = $user["tgl_lahir"];
		$this->_avatar = $user["avatar"];
		
        return $this->toArray();
    }
	
    public function checkAvailabilityUsername($username)
    {
        $sql = "SELECT * FROM users WHERE username=?";
        $this->_setSql($sql);
		
        $user = $this->getRow(array($username));
         
        if (empty($user))
        {
            return true;
        }
		
        return false;
    }
	
	
	public function getUser()
    {
		$sql = "SELECT username FROM users";
		$this->_setSql($sql);
		
		$result = $this->getAll();
		 
		return $result;
    }
	
    public function checkAvailabilityEmail($email)
    {
        $sql = "SELECT * FROM users WHERE email=?";
        $this->_setSql($sql);
		
        $user = $this->getRow(array($email));
         
        if (empty($user))
        {
            return true;
        }
		
        return false;
    }
	
    public function store()
    {
        $sql = "INSERT INTO users 
                    (username, email, password, full_name, tgl_lahir, avatar)
                VALUES 
                    (?, ?, ?, ?, ?, ?);";
         
        $data = array(
			$this->_username,
			$this->_email,
			$this->_password,
			$this->_full_name,
			$this->_tgl_lahir,
			$this->_avatar
        );
         
        $sth = $this->_db->prepare($sql);
        return $sth->execute($data);
    }
	
	    public function Update()
    {
        $sql = "UPDATE users 
                    SET `password`=MD5(?), `full_name`=?, `tgl_lahir`=?, `avatar`=?
                WHERE 
                    `username`=?;";		 
		 
        $data = array(
			$this->_password,
			$this->_full_name,
			$this->_tgl_lahir,
			$this->_avatar,
			$this->_username,			
        );
         
        $sth = $this->_db->prepare($sql);
        return $sth->execute($data);
    }	
}

?>