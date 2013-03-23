-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 23, 2013 at 04:28 PM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510032`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignee`
--

CREATE TABLE IF NOT EXISTS `assignee` (
  `username` varchar(20) NOT NULL,
  `id_task` int(11) NOT NULL,
  UNIQUE KEY `username` (`username`,`id_task`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignee`
--

INSERT INTO `assignee` (`username`, `id_task`) VALUES
('', 17),
('akunih', 18),
('akunih', 22),
('apaajadeh', 22),
('ArieDoank', 16),
('ArieDoank', 18),
('ArieDoank', 19),
('ArieDoank', 21),
('ArieDoank', 22),
('StefanDoank', 17),
('StefanDoank', 22);

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `id_attachment` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(100) NOT NULL,
  PRIMARY KEY (`id_attachment`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Dumping data for table `attachment`
--

INSERT INTO `attachment` (`id_attachment`, `path`) VALUES
(1, 'img/foto_anonim.png'),
(2, 'img/Arie.jpg'),
(3, 'img/Attachment1.txt'),
(4, 'img/Attachment2.txt'),
(5, 'img/Video.mp4'),
(22, 'att/att11.jpg'),
(28, 'att/att01.jpg'),
(29, 'att/att12.jpg'),
(30, 'att/att23.jpg'),
(31, 'att/att02.jpg'),
(32, 'att/att03.jpg'),
(33, 'att/att01.jpg'),
(34, 'att/att01.jpg'),
(35, 'att/att01.jpg'),
(36, 'att/att02.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id_cat` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id_cat`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id_cat`, `name`) VALUES
(1, 'Cheating'),
(2, 'Offensive Weapon'),
(3, 'Harmful Drugs'),
(4, 'Conspiracy'),
(5, 'Robbery'),
(6, 'Drunken'),
(7, 'Forcible Rape'),
(8, 'Murder'),
(9, 'Poisoning'),
(10, 'Hacking');

-- --------------------------------------------------------

--
-- Table structure for table `categorycreator`
--

CREATE TABLE IF NOT EXISTS `categorycreator` (
  `username` varchar(20) NOT NULL,
  `id_cat` int(11) NOT NULL,
  UNIQUE KEY `username` (`username`,`id_cat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categorycreator`
--

INSERT INTO `categorycreator` (`username`, `id_cat`) VALUES
('ArieDoank', 3),
('ArieDoank', 6),
('ArieDoank', 9),
('EndyDoank', 1),
('EndyDoank', 4),
('EndyDoank', 7),
('EndyDoank', 10),
('StefanDoank', 2),
('StefanDoank', 5),
('StefanDoank', 8);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id_comment` int(11) NOT NULL AUTO_INCREMENT,
  `id_task` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `time` varchar(20) NOT NULL,
  `content` varchar(140) NOT NULL,
  PRIMARY KEY (`id_comment`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id_comment`, `id_task`, `username`, `time`, `content`) VALUES
(7, 1, '', '9 : 26 - 23/3', 'Nyohohohohoh'),
(8, 1, '', '9 : 27 - 23/3', 'aaa');

-- --------------------------------------------------------

--
-- Table structure for table `joincategory`
--

CREATE TABLE IF NOT EXISTS `joincategory` (
  `id_cat` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  UNIQUE KEY `id_cat` (`id_cat`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `joincategory`
--

INSERT INTO `joincategory` (`id_cat`, `username`) VALUES
(1, 'EndyDoank'),
(2, 'StefanDoank'),
(3, 'akunih'),
(3, 'apaajadeh'),
(3, 'ArieDoank'),
(3, 'StefanDoank'),
(10, 'apaajadeh'),
(10, 'EndyDoank'),
(10, 'StefanDoank');

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `id_tag` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id_tag`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`id_tag`, `name`) VALUES
(29, ''),
(30, 'abc'),
(25, 'arie'),
(31, 'def'),
(24, 'endi'),
(32, 'ghi'),
(2, 'male'),
(1, 'student'),
(26, 'tes1'),
(27, 'tes2'),
(28, 'tes3'),
(3, 'unyu');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `id_task` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deadline` varchar(20) NOT NULL,
  `id_cat` int(11) NOT NULL,
  `pemilik` varchar(20) NOT NULL,
  KEY `id_task` (`id_task`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id_task`, `name`, `status`, `deadline`, `id_cat`, `pemilik`) VALUES
(18, 'tugas2', 1, '2013-03-13', 3, 'ArieDoank'),
(19, 'tugas3', 0, '2013-03-14', 3, 'ArieDoank'),
(20, 'tugas3', 0, '2013-03-14', 3, 'ArieDoank'),
(21, 'tugas4', 0, '2013-03-14', 3, 'ArieDoank'),
(22, 'tugas6', 0, '2013-03-21', 3, 'ArieDoank');

-- --------------------------------------------------------

--
-- Table structure for table `taskattachment`
--

CREATE TABLE IF NOT EXISTS `taskattachment` (
  `id_task` int(11) NOT NULL,
  `id_attachment` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `taskattachment`
--

INSERT INTO `taskattachment` (`id_task`, `id_attachment`) VALUES
(17, 31),
(18, 32),
(19, 28),
(19, 28),
(21, 28),
(22, 31);

-- --------------------------------------------------------

--
-- Table structure for table `taskcreator`
--

CREATE TABLE IF NOT EXISTS `taskcreator` (
  `id_taskcreator` int(11) NOT NULL AUTO_INCREMENT,
  `id_task` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`id_taskcreator`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

--
-- Dumping data for table `taskcreator`
--

INSERT INTO `taskcreator` (`id_taskcreator`, `id_task`, `username`) VALUES
(1, 1, 'EndyDoank'),
(2, 2, 'StefanDoank'),
(3, 3, 'ArieDoank'),
(22, 14, 'ArieDoank'),
(23, 15, 'ArieDoank'),
(24, 16, 'ArieDoank'),
(25, 17, ''),
(26, 18, 'ArieDoank'),
(27, 19, 'ArieDoank'),
(28, 19, 'ArieDoank'),
(29, 21, 'ArieDoank'),
(30, 22, 'ArieDoank');

-- --------------------------------------------------------

--
-- Table structure for table `tasktag`
--

CREATE TABLE IF NOT EXISTS `tasktag` (
  `id_task` int(11) NOT NULL,
  `id_tag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tasktag`
--

INSERT INTO `tasktag` (`id_task`, `id_tag`) VALUES
(16, 23),
(17, 24),
(17, 25),
(18, 26),
(18, 27),
(18, 28),
(19, 29),
(19, 29),
(21, 29),
(22, 30),
(22, 31),
(22, 32);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(20) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `avatar` varchar(100) NOT NULL,
  `birthday` varchar(20) NOT NULL,
  `email` varchar(35) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `fullname`, `avatar`, `birthday`, `email`, `password`) VALUES
('akunih', 'aku ini', 'img/akunih1.jpg', '2013-03-05', 'aku@ni.co', '12345123'),
('apaajadeh', 'apa aja', 'img/apaajadeh2.jpg', '2013-03-06', 'apa@aja.com', '12345123'),
('ArieDoank', 'Arie Tando', 'img/Arie.jpg', '1991-11-26', '13510018@std.stei.itb.ac.id', 'tando123'),
('EndyDoank', 'Nugroho Satrijandi', 'img/Endy.jpg', '2013-03-15', 'nugroho.satrijandi@gmail.com', 'nugroho123'),
('sesuatu', 'sesuatu banget', 'img/sesuatu3.jpg', '2013-03-12', 'sesuatu@co.id', '12345123'),
('StefanDoank', 'Stefan Lauren', 'img/Stefan.jpg', '1992-03-30', 'stefan.lauren@yahoo.com', 'stefan123');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
