<?php

class Komentar extends Model
{

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
	    	$sql = "SELECT c.`id` AS `id`, c.`user` AS `user`, u.`full_name` AS `full_name`,  u.`avatar` AS `avatar`,  IF(c.`user` = ?, 1, 0) AS `priviledge`, c.`time` AS `time`, c.`content` AS `content`
	    	        FROM `comments` c, `users` u
	    	        WHERE `id_tugas`=? AND c.`user`=u.`username` ORDER BY c.`time` DESC LIMIT $startindex, $count";
	    	$this->_setSql($sql);
        	$comments = $this->getAll(array($username, $idtugas));
	    }
		
        return $comments;
    }
}