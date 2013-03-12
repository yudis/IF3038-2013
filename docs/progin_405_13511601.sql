-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 12, 2013 at 03:58 
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13511601`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignee`
--

CREATE TABLE IF NOT EXISTS `assignee` (
  `username` varchar(50) NOT NULL,
  `taskid` int(11) NOT NULL,
  KEY `assignee_ibfk_1` (`taskid`),
  KEY `assignee_ibfk_2` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignee`
--

INSERT INTO `assignee` (`username`, `taskid`) VALUES
('wchaq', 1),
('martinz', 1);

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `filename` varchar(50) NOT NULL,
  `taskid` int(11) NOT NULL,
  KEY `FK_attachment_task` (`taskid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attachment`
--

INSERT INTO `attachment` (`filename`, `taskid`) VALUES
('a.jpg', 1),
('b.jpg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `categoryid` int(11) NOT NULL AUTO_INCREMENT,
  `categoryname` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `createddate` datetime NOT NULL,
  PRIMARY KEY (`categoryid`),
  KEY `FK_category_user` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryid`, `categoryname`, `username`, `createddate`) VALUES
(1, 'PKM', 'wchaq', '2013-03-12 09:45:53'),
(2, 'I-Cup', 'martinz', '2013-03-12 09:46:13'),
(3, 'KRI (Kontes Robot Indonesia)', 'meckyr', '2013-03-12 09:47:06');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `commentid` int(11) NOT NULL,
  `createdate` datetime NOT NULL,
  `message` varchar(150) NOT NULL,
  `username` varchar(50) NOT NULL,
  `taskid` int(11) NOT NULL,
  PRIMARY KEY (`commentid`),
  KEY `comment_ibfk_1` (`username`),
  KEY `comment_ibfk_2` (`taskid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`commentid`, `createdate`, `message`, `username`, `taskid`) VALUES
(1, '2013-03-17 09:56:01', 'Gaul men...', 'wchaq', 1),
(2, '2013-03-12 19:57:34', 'ah biasa aja ah... menurut lu aja kali...', 'meckyr', 1);

-- --------------------------------------------------------

--
-- Table structure for table `responsibility`
--

CREATE TABLE IF NOT EXISTS `responsibility` (
  `username` varchar(50) NOT NULL,
  `categoryid` int(11) NOT NULL,
  KEY `responsibility_ibfk_1` (`categoryid`),
  KEY `responsibility_ibfk_2` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `responsibility`
--

INSERT INTO `responsibility` (`username`, `categoryid`) VALUES
('wchaq', 1),
('martinz', 1),
('meckyr', 1),
('martinz', 2),
('wchaq', 2),
('meckyr', 3);

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `tagid` int(11) NOT NULL AUTO_INCREMENT,
  `tagname` varchar(50) NOT NULL,
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`tagid`, `tagname`) VALUES
(1, 'Basis Data'),
(2, 'Inteligensi Buatan'),
(3, 'Natural Language');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `taskid` int(11) NOT NULL,
  `taskname` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `createddate` datetime NOT NULL,
  `deadline` datetime DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  `categoryid` int(11) NOT NULL,
  PRIMARY KEY (`taskid`),
  KEY `task_ibfk_1` (`categoryid`),
  KEY `FK_task_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`taskid`, `taskname`, `username`, `createddate`, `deadline`, `status`, `categoryid`) VALUES
(1, 'Deploy In Adroid Device', 'wchaq', '2013-03-12 16:50:14', '2013-03-12 22:50:17', 'UNCOMPLETE', 1),
(2, 'Oprek apa aja', 'meckyr', '2013-03-12 15:52:29', '2013-03-17 09:52:32', 'UNCOMPLETE', 3);

-- --------------------------------------------------------

--
-- Table structure for table `task_tag`
--

CREATE TABLE IF NOT EXISTS `task_tag` (
  `taskid` int(11) NOT NULL,
  `tagid` int(11) NOT NULL,
  KEY `task_tag_ibfk_1` (`taskid`),
  KEY `task_tag_ibfk_2` (`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task_tag`
--

INSERT INTO `task_tag` (`taskid`, `tagid`) VALUES
(1, 2),
(1, 3),
(2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `fullname`, `birthday`, `email`) VALUES
('martinz', 'ALAYMAN', 'Vinsentius Martin', '1992-03-17', 'vincentiusmartin@hotmail.com'),
('meckyr', 'TWOOFUSMAN', 'Muhammad Ecky Rabbani', '1992-03-13', 'meckyr@rocketmail.com'),
('wchaq', 'GAULMAN', 'Whilda Chaq', '1991-02-20', 'chaqjozz@rocketmail.com');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assignee`
--
ALTER TABLE `assignee`
  ADD CONSTRAINT `assignee_ibfk_1` FOREIGN KEY (`taskid`) REFERENCES `task` (`taskid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `assignee_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `attachment`
--
ALTER TABLE `attachment`
  ADD CONSTRAINT `FK_attachment_task` FOREIGN KEY (`taskid`) REFERENCES `task` (`taskid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FK_category_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`taskid`) REFERENCES `task` (`taskid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `responsibility`
--
ALTER TABLE `responsibility`
  ADD CONSTRAINT `responsibility_ibfk_1` FOREIGN KEY (`categoryid`) REFERENCES `category` (`categoryid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `responsibility_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `FK_task_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`categoryid`) REFERENCES `category` (`categoryid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `task_tag`
--
ALTER TABLE `task_tag`
  ADD CONSTRAINT `task_tag_ibfk_1` FOREIGN KEY (`taskid`) REFERENCES `task` (`taskid`) ON DELETE CASCADE,
  ADD CONSTRAINT `task_tag_ibfk_2` FOREIGN KEY (`tagid`) REFERENCES `tag` (`tagid`) ON DELETE CASCADE;
