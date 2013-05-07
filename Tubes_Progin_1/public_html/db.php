<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    class ProginDB extends mysqli {


        // single instance of self shared among all instances
        private static $instance = null;


        // db connection config vars
        private $user = "progin";
        private $pass = "progin";
        private $dbName = "progin_405_13510056";
        private $dbHost = "localhost";
        
        //This method must be static, and must return an instance of the object if the object
        //does not already exist.
        public static function getInstance() {
        if (!self::$instance instanceof self) {
            self::$instance = new self;
        }
        return self::$instance;
        }
        
        // private constructor
        private function __construct() {
            parent::__construct($this->dbHost, $this->user, $this->pass, $this->dbName);
            if (mysqli_connect_error()) {
                exit('Connect Error (' . mysqli_connect_errno() . ') '
                        . mysqli_connect_error());
            }
            parent::set_charset('utf-8');
        }
        
        // The clone and wakeup methods prevents external instantiation of copies of the Singleton class,
        // thus eliminating the possibility of duplicate objects.
        public function __clone() {
        trigger_error('Clone is not allowed.', E_USER_ERROR);
        }
        public function __wakeup() {
        trigger_error('Deserializing is not allowed.', E_USER_ERROR);
        }
        
        public function verify_user_credentials($username, $password) {
            $username = $this->real_escape_string($username);
            $password = $this->real_escape_string($password);
            $result = $this->query("SELECT 1 FROM user WHERE username = '"
                            . $username . "' AND password = '" . md5($password) . "'");
            return $result->data_seek(0);
        }
        public function create_account($username, $fullname, $dob, $password, $email, $avatar){
            $username = $this->real_escape_string($username);
            $fullname = $this->real_escape_string($fullname);
            $dob = $this->real_escape_string($dob);
            $password = $this->real_escape_string($password);
            $email = $this->real_escape_string($email);
            $avatar = $this->real_escape_string($avatar);
            $this->query("INSERT INTO user (username, fullname, dob, password, email, avatar) VALUES 
                ('".$username."','".$fullname."','".$dob."','".md5($password)."','".$email."','avatar/".$avatar."')");
        }
        public function display_avatar($username){
            $username = $this->real_escape_string($username);
            $result = $this->query("SELECT * FROM user WHERE username = '".$username."'");
            $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
            return $row['avatar'];
        }
        public function verify_user_exists_by_username($username){
            $username = $this->real_escape_string($username);
            $result = $this->query("SELECT * FROM user WHERE username = '".$username."'");
            return $result->num_rows;
        }
        public function verify_user_exists_by_email($email){
            $email = $this->real_escape_string($email);
            $result = $this->query("SELECT * FROM user WHERE email = '".$email."'");
            return $result->num_rows;
        }
        public function edit_profile($username, $fullname, $avatar, $dob, $password){
            $fullname = $this->real_escape_string($fullname);
            $dob = $this->real_escape_string($dob);
            $avatar = $this->real_escape_string($avatar);
            $password = $this->real_escape_string($password);
            $this->query("UPDATE user SET fullname='".$fullname."', avatar='avatar/".$avatar."', dob='".$dob."', password='".md5($password)."' WHERE username = '".$username."'");
        }
        public function donelist_display($user){
            $result = $this->query("SELECT category.`name`, task.`name` FROM user JOIN utrelation JOIN task JOIN category WHERE user.username='".$user."' AND task.status='T' AND utrelation.username= user.username AND utrelation.id_task=task.id_task AND category.id_category=task.id_category ORDER BY task.id_category");
            while ($row = mysqli_fetch_array($result, MYSQLI_NUM)){
                echo ("<b>".$row[0]." : </b>".$row[1]."</br>");
            }
        }
        public function todolist_display($user){
            $result = $this->query("SELECT category.`name`, task.`name` FROM user JOIN utrelation JOIN task JOIN category WHERE user.username='".$user."' AND task.status='F' AND utrelation.username= user.username AND utrelation.id_task=task.id_task AND category.id_category=task.id_category ORDER BY task.id_category");
            while ($row = mysqli_fetch_array($result, MYSQLI_NUM)){
                echo ("<b>".$row[0]." : </b>".$row[1]."</br>");
            }
        }
        /*function insert_tuple($nameID, $unitID, $priceID){
            $this->query("INSERT INTO has (Name, Unit, Price) 
                            VALUES ('" . $nameID . "', '" . $unitID . "', '" 
                            . $priceID . "')");
        }
        public function update_tuple($nameID, $unitID, $priceID){
            $this->query("UPDATE has SET Price = '" . $priceID .
                    "' WHERE Name = '" . $nameID . "' AND Unit = '" . $unitID . "'");
        }
        function delete_tuple ($nameID, $unitID){
            $this->query("DELETE FROM has WHERE Name='$nameID' AND Unit='$unitID'");
        }
        public function get_tuple_by_id ($nameID, $unitID) {
            return $this->query("SELECT Name, Unit, Price FROM has WHERE Name = '" . $nameID . "' AND Unit = '" . $unitID . "'");
        }*/
        
    }
?>
