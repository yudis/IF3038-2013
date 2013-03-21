<?php

class Komentar extends Model
{
    private $_id;
    private $_id_tugas;
    private $_username;
    private $_time;
    private $_content;

    public function get_id() { return $this->_id; } 
    public function get_id_tugas() { return $this->_id_tugas; } 
    public function get_username() { return $this->_username; } 
    public function get_time() { return $this->_time; } 
    public function get_content() { return $this->_content; } 
    public function set_id($x) { $this->_id = $x; } 
    public function set_id_tugas($x) { $this->_id_tugas = $x; } 
    public function set_username($x) { $this->_username = $x; } 
    public function set_time($x) { $this->_time = $x; } 
    public function set_content($x) { $this->_content = $x; } 

    public function getCommentsCount($idtugas)
    {
	    $sql = "SELECT COUNT(*) as `n` FROM `comments` WHERE `id_tugas`=?";
        $this->_setSql($sql);
		
        $comments = $this->getRow(array($idtugas));
        return $comments["n"];
    }

    public function getComments($idtugas, $startindex, $count, $username = null)
    {
    	if ($username == null)
    	{
	    	$sql = "SELECT `id`, `user`, `time`, `content` FROM `comments` WHERE `id_tugas`=? ORDER BY `time` DESC LIMIT $startindex, $count";
	    	$this->_setSql($sql);
        	$comments = $this->getAll(array($idtugas));
	    }
	    else
	    {
	    	$sql = "SELECT c.`id` AS `id`, c.`user` AS `user`, u.`full_name` AS `full_name`,  u.`avatar` AS `avatar`,  IF(c.`user` = ?, 1, 0) AS `priviledge`, DATE_FORMAT(c.`time`,'%H:%i %e/%c') AS `time`, c.`content` AS `content`
	    	        FROM `comments` c, `users` u
	    	        WHERE `id_tugas`=? AND c.`user`=u.`username` ORDER BY c.`time` DESC LIMIT $startindex, $count";
	    	$this->_setSql($sql);
        	$comments = $this->getAll(array($username, $idtugas));
	    }
		
        return $comments;
    }

    public function store()
    {
        $sql = "INSERT INTO comments 
                    (id_tugas, user, content)
                VALUES 
                    (?, ?, ?);";
         
        $data = array(
            $this->_id_tugas,
            $this->_username,
            $this->_content
        );
         
        $sth = $this->_db->prepare($sql);
        return $sth->execute($data);
    }
}