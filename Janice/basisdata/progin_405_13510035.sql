-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 11, 2013 at 12:40 PM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510035`
--

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `attachment_id` varchar(255) NOT NULL,
  `task_id` varchar(255) NOT NULL,
  `link` varchar(255) NOT NULL,
  PRIMARY KEY (`attachment_id`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attachment`
--


-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `category_id` varchar(255) NOT NULL,
  `category_name` int(255) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--


-- --------------------------------------------------------

--
-- Table structure for table `category_incharge`
--

CREATE TABLE IF NOT EXISTS `category_incharge` (
  `category_id` varchar(255) NOT NULL,
  `people_incharge` varchar(255) NOT NULL,
  KEY `category_id` (`category_id`),
  KEY `people_incharge` (`people_incharge`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category_incharge`
--


-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` varchar(255) NOT NULL,
  `commented_task` varchar(255) NOT NULL,
  `writer` varchar(255) NOT NULL,
  `writing_time` datetime NOT NULL,
  `comment` text NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `commented_task` (`commented_task`),
  KEY `writer` (`writer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--


-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `tag_id` varchar(255) NOT NULL,
  `tag_name` varchar(255) NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tag`
--


-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `task_id` varchar(255) NOT NULL,
  `task_name` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deadline` date NOT NULL,
  `assignee` varchar(255) NOT NULL,
  `task_category` varchar(255) NOT NULL,
  PRIMARY KEY (`task_id`),
  KEY `assignee` (`assignee`),
  KEY `task_category` (`task_category`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--


-- --------------------------------------------------------

--
-- Table structure for table `tasktag`
--

CREATE TABLE IF NOT EXISTS `tasktag` (
  `task_id` varchar(255) NOT NULL,
  `tag_id` varchar(255) NOT NULL,
  KEY `task_id` (`task_id`),
  KEY `tag_id` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tasktag`
--


-- --------------------------------------------------------

--
-- Table structure for table `task_incharge`
--

CREATE TABLE IF NOT EXISTS `task_incharge` (
  `task_id` varchar(255) NOT NULL,
  `people_incharge_task` varchar(255) NOT NULL,
  KEY `task_id` (`task_id`),
  KEY `people_incharge_task` (`people_incharge_task`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task_incharge`
--


-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--


--
-- Constraints for dumped tables
--

--
-- Constraints for table `attachment`
--
ALTER TABLE `attachment`
  ADD CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE;

--
-- Constraints for table `category_incharge`
--
ALTER TABLE `category_incharge`
  ADD CONSTRAINT `category_incharge_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `category_incharge_ibfk_2` FOREIGN KEY (`people_incharge`) REFERENCES `user` (`username`) ON DELETE CASCADE;

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`commented_task`) REFERENCES `task` (`task_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`writer`) REFERENCES `user` (`username`) ON DELETE CASCADE;

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_3` FOREIGN KEY (`assignee`) REFERENCES `user` (`username`) ON DELETE CASCADE,
  ADD CONSTRAINT `task_ibfk_4` FOREIGN KEY (`task_category`) REFERENCES `category` (`category_id`) ON DELETE CASCADE;

--
-- Constraints for table `tasktag`
--
ALTER TABLE `tasktag`
  ADD CONSTRAINT `tasktag_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `tasktag_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE;

--
-- Constraints for table `task_incharge`
--
ALTER TABLE `task_incharge`
  ADD CONSTRAINT `task_incharge_ibfk_2` FOREIGN KEY (`people_incharge_task`) REFERENCES `user` (`username`) ON DELETE CASCADE,
  ADD CONSTRAINT `task_incharge_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE;
