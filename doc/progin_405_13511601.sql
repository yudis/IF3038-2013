-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 20, 2013 at 09:44 
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
('wchaq2', 1),
('wchaq', 1),
('masperi', 2),
('wchaq', 2),
('ismail', 2),
('wchaq', 3),
('masperi', 3),
('wchaq2', 3);

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
('3-Capture.JPG', 3);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryid`, `categoryname`, `username`, `createddate`) VALUES
(1, 'pekerjaan Rumah', 'wchaq2', '2013-03-16 05:09:35'),
(3, 'Iseng', 'robert', '2013-04-04 06:19:41'),
(7, 'mainan anak anak', 'akukukamu', '2013-04-12 14:06:11'),
(10, 'ini category gue', 'masperi', '2013-04-12 14:53:03'),
(11, 'njajal', 'wchaq', '2013-04-12 18:17:03'),
(13, 'yuk mari yuk', 'ucupbinsanusi', '2013-04-12 18:46:00');

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
(1, '2013-03-16 05:28:38', 'gaul', 'ismail', 1),
(2, '2013-03-16 05:28:53', 'gaul Banget Kakakaaaaa', 'ismail', 1),
(3, '2013-03-16 05:29:11', 'gaul banget kakakakakakakak ampun dewaaaa,..,.,.,', 'ismail', 1),
(4, '2013-03-16 05:30:01', 'ah situ bisa aja....', 'wchaq', 1),
(9, '2013-03-23 11:09:12', 'lemah semua', 'wchaq', 1),
(11, '2013-03-23 13:36:28', 'wasuuuuu', 'wchaq2', 1),
(12, '2013-04-10 00:00:00', 'awd', 'wchaq', 1),
(13, '2013-04-10 00:00:00', 'awdawdawd', 'wchaq', 1),
(14, '2013-04-10 00:00:00', 'awdawdawdawd', 'wchaq', 1),
(15, '2013-04-12 15:39:53', 'test comment', 'masperi', 2),
(16, '2013-04-12 15:39:59', 'nambah lagi', 'masperi', 2),
(17, '2013-04-12 15:40:04', 'ni juga lagi', 'masperi', 2),
(18, '2013-04-12 15:40:12', 'eh kok bisa terus sih', 'masperi', 2),
(19, '2013-04-12 15:40:25', 'jangan terus donk', 'masperi', 2),
(20, '2013-04-12 16:29:22', 'crewet', 'wchaq', 2);

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
('wchaq2', 1),
('wchaq', 1),
('robert', 3),
('wchaq', 3),
('ismail', 3),
('akukukamu', 7),
('masperi', 10),
('ismail', 10),
('wchaq', 10),
('wchaq', 11),
('ismail', 11),
('ucupbinsanusi', 13),
('wchaq', 13),
('ismail', 13);

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `tagid` int(11) NOT NULL AUTO_INCREMENT,
  `tagname` varchar(50) NOT NULL,
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=46 ;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`tagid`, `tagname`) VALUES
(13, 'gampang'),
(14, 'Males'),
(15, 'Banyak'),
(16, 'Serius'),
(17, 'Keringat'),
(18, 'tenaga'),
(19, 'Kerjakan'),
(20, 'selesaikan'),
(21, 'gawat'),
(22, 'gaul'),
(23, 'ini'),
(24, 'barusan'),
(25, 'bikin'),
(26, 'banget'),
(27, 'baru'),
(28, 'lama'),
(29, 'aw'),
(30, 'iw'),
(31, 'ow'),
(32, 'yakin'),
(33, 'sumpah'),
(34, 'asoy'),
(35, 'geboy'),
(36, 'ngebut'),
(37, 'dijalanan'),
(38, 'ibu'),
(39, 'kota'),
(40, 'sepele'),
(41, 'abis'),
(42, 'nih'),
(43, 'tugas'),
(44, 'mainan'),
(45, 'ecky');

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
  `dtmlastupdate` datetime NOT NULL,
  PRIMARY KEY (`taskid`),
  KEY `task_ibfk_1` (`categoryid`),
  KEY `FK_task_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`taskid`, `taskname`, `username`, `createddate`, `deadline`, `status`, `categoryid`, `dtmlastupdate`) VALUES
(1, 'Menyapu halaman', 'wchaq2', '2013-03-16 04:10:20', '2013-03-30 03:30:00', 'UNCOMPLETE', 1, '2013-05-20 20:14:34'),
(2, 'as you wish', 'masperi', '2013-04-12 15:39:32', '2013-04-02 23:22:00', 'COMPLETE', 10, '2013-05-21 02:32:34'),
(3, 'ini tugas baru', 'wchaq', '2013-04-22 12:39:32', '2013-04-26 11:11:00', 'COMPLETE', 10, '2013-05-21 02:32:37');

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
(1, 13),
(1, 14),
(1, 15),
(2, 40),
(2, 41),
(3, 22),
(3, 26),
(3, 42),
(3, 43);

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
  `join` date NOT NULL,
  `aboutme` varchar(200) NOT NULL,
  `avatar` varchar(100) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `fullname`, `birthday`, `email`, `join`, `aboutme`, `avatar`) VALUES
('akukukamu', 'awdawdawd', 'aku ku kamu', '2013-04-03', 'awd@ew.asddw', '2013-04-12', 'telling yourself in here', 'akukukamu.jpg'),
('awdawd', 'awdawdawd', 'awd awd', '2013-03-31', 'awd@awd.awd', '2013-04-06', 'telling yourself in here', 'avatar'),
('awdawdawd', 'awdawdawdawd', 'awd awd', '2013-03-31', 'awd@awd.awda', '2013-04-06', 'telling yourself in here', 'avatar'),
('awdzxc', 'awdawdawd', 'awda awd', '2013-04-09', 'awd@awd.as', '2013-04-12', 'telling yourself in here', 'avatar'),
('ismail', 'ismailalay', 'ismail abdul keren', '2013-03-24', 'ismail@keren.com', '2013-03-16', 'telling yourself in here', 'ismail.jpg'),
('masperi', 'awdawdawdawd', 'fery susilo', '2013-04-30', 'fery.susilo@rocketmail.com', '2013-04-12', 'telling yourself in here', 'masperi.jpg'),
('robert', 'barupass', 'robert gaul baik hati', '2013-04-22', 'rb.th@gmail.com', '2013-04-04', 'kljasdbfliasrbolairbhogliarbgolairbhgoairhbgolairbggrbar arfhaigrhoarhgo', 'robert.jpg'),
('ucupbinsanusi', 'awdawdawdawd', 'ucup gaul', '2013-04-13', 'ucup@rocketmail.com', '2013-04-12', 'telling yourself in here', 'ucupbinsanusi.jpg'),
('ucupgaul', 'awdawdawdawd', 'ucup gaul', '2013-04-25', 'awd@ew.asddw', '2013-04-13', 'telling yourself in here', 'ucupgaul.jpg'),
('wchaq', 'awdawdawdawd', 'Whilda Chaq Baru', '2013-03-13', 'whilda@rocketmial.com', '2013-03-16', '2013-04-24', 'wchaq.jpg'),
('wchaq2', 'whildaganteng', 'Whilda Chaq', '2013-03-19', 'whilda2@rocketmial.com', '2013-03-16', 'Saya orang pandai yang tidak sombong seperti martin', 'wchaq2.jpg');

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
