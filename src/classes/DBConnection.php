<?php

final class DBConnection {
	public static $dbh;

	const host = 'localhost'
	const user = 'progin';
	const pass = 'progin';
	const dbname = 'progin_405_13510033';

	public function openDBconnection() {
		if (!self::$dbh) {
			// No connection made yet

			// Create connection
			self::$dbh = new mysqli(self::host, self::user, self::pass, self::dbname);

			if (mysqli_connect_errno(self::$dbh)) {
				// TODO Make this more elegant
				// Error
				unset(self::$dbh);
				echo 'Failed to connect to MySQL:' . mysqli_connect_error();
				exit; // Connection failure does us no good, just exit
			}
		}
		else {
			// Connection already made. Do nothing.
		}
	}
	
	public function closeDBconnection() {
		if (self::$dbh) {
			self::$dbh->close();
		}
	}

	public function DBquery($query, $resultmode = MYSQLI_STORE_RESULT) {
		self::openDBconnection();
		return self::$dbh->query($query, $resultMode);
	}
}