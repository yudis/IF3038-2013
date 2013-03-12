-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 12, 2013 at 04:47 AM
-- Server version: 5.1.44
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510086`
--

-- --------------------------------------------------------

--
-- Table structure for table `ATTACHMENT`
--

CREATE TABLE IF NOT EXISTS `ATTACHMENT` (
  `att_id` int(11) NOT NULL AUTO_INCREMENT,
  `att_content` text NOT NULL,
  `task_id` int(11) NOT NULL,
  PRIMARY KEY (`att_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `ATTACHMENT`
--

INSERT INTO `ATTACHMENT` (`att_id`, `att_content`, `task_id`) VALUES
(1, 'dsadasdsdadsadasds', 1),
(2, 'sadasdasdsaddsdas', 1);

-- --------------------------------------------------------

--
-- Table structure for table `CATEGORY`
--

CREATE TABLE IF NOT EXISTS `CATEGORY` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(50) NOT NULL,
  `cat_creator` varchar(50) NOT NULL,
  PRIMARY KEY (`cat_id`),
  UNIQUE KEY `cat_name` (`cat_name`),
  UNIQUE KEY `cat_creator` (`cat_creator`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `CATEGORY`
--

INSERT INTO `CATEGORY` (`cat_id`, `cat_name`, `cat_creator`) VALUES
(1, 'Schoolicious', 'kennyazrina'),
(2, 'Household', 'sharonloh');

-- --------------------------------------------------------

--
-- Table structure for table `CAT_ASIGNEE`
--

CREATE TABLE IF NOT EXISTS `CAT_ASIGNEE` (
  `cat_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CAT_ASIGNEE`
--

INSERT INTO `CAT_ASIGNEE` (`cat_id`, `username`) VALUES
(1, 'nflubis'),
(1, 'sharonloh');

-- --------------------------------------------------------

--
-- Table structure for table `COMMENT`
--

CREATE TABLE IF NOT EXISTS `COMMENT` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` text NOT NULL,
  `task_id` int(11) NOT NULL,
  `comment_creator` varchar(50) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `COMMENT`
--

INSERT INTO `COMMENT` (`comment_id`, `comment_timestamp`, `content`, `task_id`, `comment_creator`) VALUES
(1, '2013-03-12 11:44:26', 'asdasdasdas', 1, 'sharonloh'),
(2, '2013-03-12 11:44:26', 'sndajasndnsajkdans', 1, 'nflubis');

-- --------------------------------------------------------

--
-- Table structure for table `TAG`
--

CREATE TABLE IF NOT EXISTS `TAG` (
  `tag_name` varchar(50) NOT NULL,
  `task_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TAG`
--

INSERT INTO `TAG` (`tag_name`, `task_id`) VALUES
('capek', 1),
('males', 2);

-- --------------------------------------------------------

--
-- Table structure for table `TASK`
--

CREATE TABLE IF NOT EXISTS `TASK` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(50) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `deadline` date NOT NULL,
  `cat_name` varchar(50) NOT NULL,
  `task_creator` varchar(50) NOT NULL,
  PRIMARY KEY (`task_id`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `TASK`
--

INSERT INTO `TASK` (`task_id`, `task_name`, `status`, `deadline`, `cat_name`, `task_creator`) VALUES
(1, 'Tubes Progin 2', 0, '2013-03-23', 'Schoolicious', 'kennyazrina'),
(2, 'Tubes Progin 3', 0, '2013-04-25', 'Schoolicious', 'nflubis');

-- --------------------------------------------------------

--
-- Table structure for table `TASK_ASIGNEE`
--

CREATE TABLE IF NOT EXISTS `TASK_ASIGNEE` (
  `task_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TASK_ASIGNEE`
--

INSERT INTO `TASK_ASIGNEE` (`task_id`, `username`) VALUES
(1, 'nflubis'),
(2, 'sharonloh'),
(1, 'sharonloh');

-- --------------------------------------------------------

--
-- Table structure for table `USER`
--

CREATE TABLE IF NOT EXISTS `USER` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `birthdate` date NOT NULL,
  `avatar` text NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USER`
--

INSERT INTO `USER` (`username`, `password`, `full_name`, `birthdate`, `avatar`, `email`) VALUES
('kennyazrina', 'kennyazrina', 'Kania Azrina', '1992-03-13', 'abcdefg', 'kaniaazrina@gmail.com'),
('nflubis', 'nflubis', 'Nurul Fithria Lubis', '1992-04-17', 'dasjndjksndkas', 'nflubis@gmail.com'),
('sharonloh', 'sharonloh', 'Sharon Loh', '1992-02-10', 'dsddsdsa', 'sharonloh@gmail.com');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `CATEGORY`
--
ALTER TABLE `CATEGORY`
  ADD CONSTRAINT `category_ibfk_1` FOREIGN KEY (`cat_creator`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `COMMENT`
--
ALTER TABLE `COMMENT`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE;
