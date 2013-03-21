-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 21, 2013 at 09:03 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

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
  `id_usertask` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `id_task` int(11) NOT NULL,
  PRIMARY KEY (`id_usertask`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `assignee`
--

INSERT INTO `assignee` (`id_usertask`, `username`, `id_task`) VALUES
(1, 'EndyDoank', 1),
(2, 'StefanDoank', 2),
(3, 'ArieDoank', 3);

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `id_attachment` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(100) NOT NULL,
  PRIMARY KEY (`id_attachment`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `attachment`
--

INSERT INTO `attachment` (`id_attachment`, `path`) VALUES
(1, '/img/foto_anonim.png'),
(2, '/img/Arie.jpg'),
(3, '/img/Attachment1'),
(4, '/img/Attachment2'),
(5, '/img/Video');

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
  `id_catcreator` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `id_cat` int(11) NOT NULL,
  PRIMARY KEY (`id_catcreator`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `categorycreator`
--

INSERT INTO `categorycreator` (`id_catcreator`, `username`, `id_cat`) VALUES
(1, 'EndyDoank', 1),
(2, 'StefanDoank', 2),
(3, 'ArieDoank', 3),
(4, 'EndyDoank', 4),
(5, 'StefanDoank', 5),
(6, 'ArieDoank', 6),
(7, 'EndyDoank', 7),
(8, 'StefanDoank', 8),
(9, 'ArieDoank', 9),
(10, 'EndyDoank', 10);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id_comment` int(11) NOT NULL AUTO_INCREMENT,
  `id_task` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `time` varchar(10) NOT NULL,
  `content` varchar(140) NOT NULL,
  PRIMARY KEY (`id_comment`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id_comment`, `id_task`, `username`, `time`, `content`) VALUES
(1, 1, 'EndyDoank', '10:20', 'Wuih keren ya'),
(2, 2, 'StefanDoank', '20:30', 'Ah gampang banget sih ini'),
(3, 3, 'ArieDoank', '05:50', 'Nyemm... Lucu juga'),
(4, 1, 'EndyDoank', '01:10', 'Mau donk plisss'),
(5, 2, 'StefanDoank', '20:02', 'Yay uda beres');

-- --------------------------------------------------------

--
-- Table structure for table `joincategory`
--

CREATE TABLE IF NOT EXISTS `joincategory` (
  `id_join` int(11) NOT NULL AUTO_INCREMENT,
  `id_cat` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`id_join`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `joincategory`
--

INSERT INTO `joincategory` (`id_join`, `id_cat`, `username`) VALUES
(1, 1, 'EndyDoank'),
(2, 2, 'StefanDoank'),
(3, 3, 'ArieDoank');

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `id_tag` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id_tag`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`id_tag`, `name`) VALUES
(1, 'student'),
(2, 'male'),
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id_task`, `name`, `status`, `deadline`, `id_cat`, `pemilik`) VALUES
(1, 'Capture Timo', 0, '2013-3-26', 1, 'Nugroho Satrijandi'),
(2, 'Capture Afif', 1, '2013-4-5', 2, 'Stefan Lauren'),
(3, 'Capture Rio', 0, '2013-4-14', 3, 'Arie Tando');

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
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `taskcreator`
--

CREATE TABLE IF NOT EXISTS `taskcreator` (
  `id_taskcreator` int(11) NOT NULL AUTO_INCREMENT,
  `id_task` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`id_taskcreator`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `taskcreator`
--

INSERT INTO `taskcreator` (`id_taskcreator`, `id_task`, `username`) VALUES
(1, 1, 'Nugroho Satrijandi'),
(2, 2, 'Stefan Lauren'),
(3, 3, 'Arie Tando');

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
(1, 1),
(1, 2),
(1, 3),
(2, 2),
(2, 3),
(3, 1);

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
('ArieDoank', 'Arie Tando', '/img/Arie.jpg', '1991-11-26', '13510018@std.stei.itb.ac.id', 'arie'),
('EndyDoank', 'Nugroho Satrijandi', 'img/Endy.jpg', '1992-10-24', 'nugroho.satrijandi@gmail.com', 'endy'),
('StefanDoank', 'Stefan Lauren', '/img/Stefan.jpg', '1992-3-30', 'stefan.lauren@yahoo.com', 'stefan');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
