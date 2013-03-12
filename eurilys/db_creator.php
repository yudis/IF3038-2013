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
		
		$sql2 = 
		"CREATE TABLE IF NOT EXISTS `cat_asignee` (
		`cat_id` int(11) NOT NULL,
		`username` varchar(50) NOT NULL
		) ENGINE=InnoDB DEFAULT CHARSET=latin1;";
		mysql_query($sql2,$con);
		
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
		
		$sql4 = 
		"CREATE TABLE IF NOT EXISTS `tag` (
		`tag_name` varchar(50) NOT NULL,
		`task_id` int(11) NOT NULL
		) ENGINE=InnoDB DEFAULT CHARSET=latin1;";
		mysql_query($sql4,$con);
		
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
		
		$sql6 = 
		"CREATE TABLE IF NOT EXISTS `task_asignee` (
		`task_id` int(11) NOT NULL,
		`username` varchar(50) NOT NULL
		) ENGINE=MyISAM DEFAULT CHARSET=latin";
		mysql_query($sql6,$con);
		
		$sql7 = 
		"CREATE TABLE IF NOT EXISTS `user` (
		`username` varchar(20) NOT NULL,
		`password` varchar(20) NOT NULL,
		`full_name` varchar(100) NOT NULL,
		`birthdate` date NOT NULL,
		`avatar` blob,
		`email` varchar(50) NOT NULL,
		PRIMARY KEY (`username`)
		) ENGINE=InnoDB DEFAULT CHARSET=latin1";
		mysql_query($sql7,$con);
		
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