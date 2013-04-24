<?php 
	$con = mysql_connect("localhost","root","") or die("Can't connect to database. Contact Your Administrator.");	
	if (!mysql_select_db("progin_405_13510086")) {
		mysql_query("CREATE DATABASE progin_405_13510086");
		mysql_select_db("progin_405_13510086",$con);
		
		$sql = 
		"CREATE TABLE IF NOT EXISTS `attachment` (
		`att_id` int(11) NOT NULL AUTO_INCREMENT,
		`att_content` text NOT NULL,
		`att_task_id` int(11) NOT NULL,
		PRIMARY KEY (`att_id`)
		) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;";
		mysql_query($sql,$con);
		
		//-------------- 
		$sql_ = "INSERT INTO `attachment` (`att_id`, `att_content`, `att_task_id`) VALUES
		(1, 'dsadasdsdadsadasds', 1),
		(2, 'sadasdasdsaddsdas', 1)";
		mysql_query($sql_,$con);
		//--------------
		
		$sql1 = 
		"CREATE TABLE IF NOT EXISTS `category` (
		`cat_id` int(11) NOT NULL AUTO_INCREMENT,
		`cat_name` varchar(50) NOT NULL,
		`cat_creator` varchar(50) NOT NULL,
		PRIMARY KEY (`cat_id`),
		UNIQUE KEY `cat_name` (`cat_name`),
		UNIQUE KEY `cat_creator` (`cat_creator`)
		) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;";
		mysql_query($sql1,$con);
		
		//-------------------
		$sql1_ = "INSERT INTO `category` (`cat_id`, `cat_name`, `cat_creator`) VALUES
		(1, 'Schoolicious', 'kennyazrina'),
		(2, 'Household', 'sharonloh')";
		mysql_query($sql1_,$con);
		//-------------------
		
		$sql2 = 
		"CREATE TABLE IF NOT EXISTS `cat_asignee` (
		`cat_id` int(11) NOT NULL,
		`username` varchar(50) NOT NULL
		) ENGINE=InnoDB DEFAULT CHARSET=latin1;";
		mysql_query($sql2,$con);
		//----------------------
		$sql2_ = "
		INSERT INTO `cat_asignee` (`cat_id`, `username`) VALUES
		(1, 'nflubis'),
		(1, 'sharonloh')";
		mysql_query($sql2_, $con);
		//----------------------
		
		$sql3 = 
		"CREATE TABLE IF NOT EXISTS `comment` (
		`comment_id` int(11) NOT NULL AUTO_INCREMENT,
		`comment_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
		`comment_content` text NOT NULL,
		`task_id` int(11) NOT NULL,
		`comment_creator` varchar(50) NOT NULL,
		PRIMARY KEY (`comment_id`),
		KEY `task_id` (`task_id`)
		) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;";
		mysql_query($sql3,$con);
		//----------------------
		$sql3_ = "
		INSERT INTO `comment` (`comment_id`, `comment_timestamp`, `comment_content`, `task_id`, `comment_creator`) VALUES
		(1, '2013-03-12 04:44:26', 'asdasdasdas', 1, 'sharonloh'),
		(2, '2013-03-12 04:44:26', 'sndajasndnsajkdans', 1, 'nflubis')";
		mysql_query($sql3_, $con);
		//----------------------
		
		
		$sql4 = 
		"CREATE TABLE IF NOT EXISTS `tag` (
		`tag_name` varchar(50) NOT NULL,
		`task_id` int(11) NOT NULL
		) ENGINE=InnoDB DEFAULT CHARSET=latin1;";
		mysql_query($sql4,$con);
		//---------------------
		$sql4_ ="INSERT INTO `tag` (`tag_name`, `task_id`) VALUES
		('capek', 1),
		('males', 2);";
		mysql_query($sql4_, $con);
		//---------------------
		
		$sql5 = 
		"CREATE TABLE IF NOT EXISTS `task` (
		`task_id` int(11) NOT NULL AUTO_INCREMENT,
		`task_name` varchar(50) NOT NULL,
		`task_status` tinyint(1) NOT NULL DEFAULT '0',
		`task_deadline` date NOT NULL,
		`cat_name` varchar(50) NOT NULL,
		`task_creator` varchar(50) NOT NULL,
		PRIMARY KEY (`task_id`),
		KEY `task_id` (`task_id`)
		) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;";
		mysql_query($sql5,$con);
		//--------------------
		$sql5_ = "INSERT INTO `task` (`task_id`, `task_name`, `task_status`, `task_deadline`, `cat_name`, `task_creator`) VALUES
		(1, 'Tubes Progin 2', 0, '2013-03-23', 'Schoolicious', 'kennyazrina'),
		(2, 'Tubes Progin 3', 0, '2013-04-25', 'Schoolicious', 'nflubis')";
		mysql_query($sql5_, $con);
		//--------------------
		
		$sql6 = 
		"CREATE TABLE IF NOT EXISTS `task_asignee` (
		`task_id` int(11) NOT NULL,
		`username` varchar(50) NOT NULL
		) ENGINE=MyISAM DEFAULT CHARSET=latin";
		mysql_query($sql6,$con);
		//---------------------
		$sql6_ ="INSERT INTO `task_asignee` (`task_id`, `username`) VALUES
		(1, 'nflubis'),
		(2, 'sharonloh'),
		(1, 'sharonloh')";
		mysql_query($sql6_, $con);
		//---------------------
		
		$sql7 = 
		"CREATE TABLE IF NOT EXISTS `user` (
		`username` varchar(20) NOT NULL,
		`password` varchar(20) NOT NULL,
		`full_name` varchar(100) NOT NULL,
		`birthdate` date NOT NULL,
		`avatar` varchar(100),
		`email` varchar(50) NOT NULL,
		PRIMARY KEY (`username`)
		) ENGINE=InnoDB DEFAULT CHARSET=latin1";
		mysql_query($sql7,$con);
		//---------------------------
		$sql7_ = "INSERT INTO `user` (`username`, `password`, `full_name`, `birthdate`, `avatar`, `email`) VALUES
		('kennyazrina', 'kennyazrina', 'Kania Azrina', '1992-03-13', NULL, 'kaniaazrina@gmail.com'),
		('nflubis', 'nflubis', 'Nurul Fithria Lubis', '1992-04-17', NULL, 'nflubis@gmail.com'),
		('sharonloh', 'sharonloh', 'Sharon Loh', '1992-02-10', NULL, 'sharonloh@gmail.com');";
		mysql_query($sql7_, $con);
		//---------------------------
		
		$sql8 = 
		"ALTER TABLE `category`
		ADD CONSTRAINT `category_ibfk_1` FOREIGN KEY (`cat_creator`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE";
		mysql_query($sql8, $con);

		$sql9 = 
		"ALTER TABLE `comment`
		ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE";
		mysql_query($sql9, $con);
	}

?>