-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 23, 2013 at 04:42 PM
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
-- Table structure for table `asignee`
--

CREATE TABLE IF NOT EXISTS `asignee` (
  `user_ID` int(11) NOT NULL AUTO_INCREMENT,
  `asignee_name` varchar(50) NOT NULL,
  `cat_name` varchar(20) NOT NULL,
  KEY `user_ID` (`user_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `asignee`
--

INSERT INTO `asignee` (`user_ID`, `asignee_name`, `cat_name`) VALUES
(1, 'gaby', 'PROGIN'),
(2, 'monca', '0'),
(3, 'ruth', '0');

-- --------------------------------------------------------

--
-- Table structure for table `authorized`
--

CREATE TABLE IF NOT EXISTS `authorized` (
  `ID_AUT` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_AUT`),
  KEY `cat_ID` (`cat_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `authorized`
--

INSERT INTO `authorized` (`ID_AUT`, `cat_name`, `user_name`) VALUES
(1, 'PROGIN', 'monca'),
(2, 'IMK', 'ruth'),
(3, 'OOP', 'gaby'),
(4, 'OOP', 'monca'),
(5, 'OOP', 'ruth'),
(6, 'OOP', 'monca'),
(7, 'OOP', 'gaby'),
(8, 'OOP', 'monca'),
(9, 'TBO', 'gaby'),
(10, 'JARKOM', 'monca'),
(11, 'JARKOM', 'gaby'),
(12, 'JARKOM', 'gaby'),
(13, 'JARKOM', 'gaby'),
(14, 'JARKOM', 'monca'),
(15, 'OOP', 'gaby'),
(16, 'OOP', 'monca'),
(17, 'OOP1', 'gaby'),
(18, 'OOP2', 'gaby'),
(19, 'OOP3', 'monca'),
(20, 'OOP4', 'gaby'),
(21, 'OOP1', 'gaby'),
(22, 'OOP1', 'monca'),
(23, 'OOP3', 'gaby'),
(24, 'OOP5', 'gaby'),
(25, '', 'gaby'),
(26, 'OOP8', 'gaby'),
(27, 'OOP9', 'gaby');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `cat_ID` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(50) NOT NULL,
  PRIMARY KEY (`cat_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=38 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`cat_ID`, `cat_name`) VALUES
(33, 'OOP7'),
(34, 'PROGIN'),
(35, 'OOP'),
(36, 'OOP8'),
(37, 'OOP9');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comm_ID` int(11) NOT NULL DEFAULT '0',
  `timestamp` text NOT NULL,
  `comm_content` text NOT NULL,
  PRIMARY KEY (`comm_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`user_ID`, `username`, `password`, `full_name`, `avatar`, `birthdate`, `email`) VALUES
(1, 'ruth', 'ruth', 'RUTH NATASHA', '1.jpg', '2013-03-04', 'ruth@mail.com'),
(2, 'monca', 'monca', 'FLORA MONICA ', '2.jpg', '2013-03-17', 'monca@mail.com');

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
  `file` varchar(200) NOT NULL,
  PRIMARY KEY (`task_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=39 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`task_ID`, `cat_task_name`, `task_name`, `task_status`, `task_deadline`, `task_tag_multivalue`, `checkbox`, `assignee_name`, `file`) VALUES
(1, 'PROGIN', 'TUBES 7 PROGIN', 0, '2013-02-02', '', 0, 'gaby', ''),
(3, 'PROGIN', 'TUBES 3 PROGIN', 0, '2012-03-03', '', 0, 'gaby', ''),
(35, '', '', 0, '0000-00-00', '', 0, '', '0'),
(36, '', '', 0, '0000-00-00', '', 0, '', '0'),
(37, '', 'hallo', 0, '2013-02-02', 'asfas', 0, 'fas', '13510060'),
(38, '', 'TUBES 3 progin', 0, '2013-03-04', 'html', 0, 'gaby monca', '13510060');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`comm_ID`) REFERENCES `task` (`task_ID`);

--
-- Constraints for table `file`
--
ALTER TABLE `file`
  ADD CONSTRAINT `file_ibfk_1` FOREIGN KEY (`file_ID`) REFERENCES `task` (`task_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
