<?php
	
	class DBConnection
	{
		private static $host = "localhost";
		//private $username = "progin";
		//private $password = "progin";
		//private $dbname = "progin_405_13510033";
		private static $username = "root";
		private static $password = "";
		private static $dbname = "kmb";
		private static $link;
		
		public static function openDBconnection() 
		{
            // Create connection
            self::$link = new mysqli(self::$host, self::$username, self::$password, self::$dbname);
            // Check connection
            if (mysqli_connect_errno()) 
            {
                echo 'Failed to connect to MySQL:' . mysqli_connect_error();
            }
        }
        
		public static function DBquery($query)
		{
			if (mysqli_ping(self::$link))
			{
				return self::$link->query($query);
			}
			else
			{
				return "A";
			}
		}
		
        public static function closeDBconnection()
		{
			if (mysqli_ping(self::$link)) 
			{
				mysqli_close(self::$link);
			} else 
			{
				printf ("Error: %s\n", mysqli_error($link));
			}
        } 
	}
?>