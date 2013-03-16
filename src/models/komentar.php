<?php

class Komentar extends Model
{

    public function getUserLogin($username, $passwd)
    {
        $sql = "SELECT * FROM users WHERE username=? AND password=MD5(?)";
        $this->_setSql($sql);
		
        $user = $this->getAll(array($username, $passwd));
    }
}