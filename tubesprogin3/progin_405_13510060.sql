-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 13, 2013 at 10:02 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510060`
--

-- --------------------------------------------------------

--
-- Table structure for table `authorized`
--

CREATE TABLE IF NOT EXISTS `authorized` (
  `ID_AUT` int(11) NOT NULL AUTO_INCREMENT,
  `aut_cat_name` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `aut_privileged` varchar(20) NOT NULL,
  `aut_task_name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_AUT`),
  KEY `cat_ID` (`aut_cat_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=97 ;

--
-- Dumping data for table `authorized`
--

INSERT INTO `authorized` (`ID_AUT`, `aut_cat_name`, `user_name`, `aut_privileged`, `aut_task_name`) VALUES
(92, 'IMK', 'gabrielle', 'creator', ''),
(93, 'IMK', 'ruthnatasha', 'asign', ''),
(94, 'IMK', 'gabrielle', 'creator', ''),
(95, 'IMK', 'ruthnatasha', 'asign', ''),
(96, 'IMK', 'ruthnatasha', 'asign', 'IMK2');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `cat_ID` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(50) NOT NULL,
  PRIMARY KEY (`cat_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=70 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`cat_ID`, `cat_name`) VALUES
(69, 'IMK');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comm_ID` int(11) NOT NULL AUTO_INCREMENT,
  `comm_content` varchar(1000) NOT NULL DEFAULT '',
  `comment_cat_name` varchar(50) NOT NULL DEFAULT '',
  `comment_task_name` varchar(50) NOT NULL DEFAULT '',
  `user_comment` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`comm_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comm_ID`, `comm_content`, `comment_cat_name`, `comment_task_name`, `user_comment`) VALUES
(33, '', 'IMK', 'IMK2', ''),
(34, 'halo', 'IMK', 'IMK2', 'gaby'),
(35, 'gimana kabarmu?', 'IMK', 'IMK2', 'gaby');

-- --------------------------------------------------------

--
-- Table structure for table `file`
--

CREATE TABLE IF NOT EXISTS `file` (
  `file_ID` int(11) NOT NULL DEFAULT '0',
  `file_content` text NOT NULL,
  PRIMARY KEY (`file_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE IF NOT EXISTS `pengguna` (
  `user_ID` int(11) NOT NULL AUTO_INCREMENT,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `full_name` text NOT NULL,
  `avatar` varchar(50) NOT NULL,
  `birthdate` date NOT NULL,
  `email` varchar(20) NOT NULL,
  PRIMARY KEY (`user_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`user_ID`, `username`, `password`, `full_name`, `avatar`, `birthdate`, `email`) VALUES
(5, 'gabrielle', '12345678', 'Gabrielle WP', '4.jpg', '2013-04-01', 'gaby@ymail.com'),
(6, 'ruthnatasha', '12345678', 'ruth ntsha', '4.jpg', '2013-04-01', 'gasd@jkl.mail.com'),
(7, 'wkw', 'wkwk', 'wkwk', '4.jpg', '2013-04-04', 'wkwk'),
(8, 'hartono', '12345678', 'hartonosw', '4.jpg', '2012-09-09', 'kya@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `task_ID` int(11) NOT NULL AUTO_INCREMENT,
  `cat_task_name` varchar(20) NOT NULL,
  `task_name` varchar(50) NOT NULL,
  `task_status` int(11) NOT NULL,
  `task_deadline` date NOT NULL,
  `task_tag_multivalue` varchar(100) NOT NULL,
  `checkbox` int(11) NOT NULL,
  `assignee_name` varchar(20) NOT NULL,
  `file` varchar(1000) NOT NULL,
  PRIMARY KEY (`task_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=99 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`task_ID`, `cat_task_name`, `task_name`, `task_status`, `task_deadline`, `task_tag_multivalue`, `checkbox`, `assignee_name`, `file`) VALUES
(97, 'IMK', 'IMK2', 0, '2012-10-10', 'html,css', 0, 'ruthnatasha', 'taskdetail_file.pdf'),
(98, 'IMK', 'IMK4', 0, '2012-10-10', 'GA', 0, 'ruthnatasha', 'taskdetail_file.pdf');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
