-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 20, 2013 at 12:00 PM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510020`
--
DROP DATABASE `progin_405_13510020`;
CREATE DATABASE `progin_405_13510020` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `progin_405_13510020`;

-- --------------------------------------------------------

--
-- Table structure for table `assignment`
--

CREATE TABLE IF NOT EXISTS `assignment` (
  `IDAssignment` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(30) NOT NULL,
  `IDTask` int(11) NOT NULL,
  PRIMARY KEY (`IDAssignment`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `assignment`
--

INSERT INTO `assignment` (`IDAssignment`, `Username`, `IDTask`) VALUES
(1, 'admin', 2),
(3, 'admin', 3);

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `IDAttachment` int(11) NOT NULL AUTO_INCREMENT,
  `IDTask` int(11) NOT NULL,
  `PathFile` text NOT NULL,
  PRIMARY KEY (`IDAttachment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `authority`
--

CREATE TABLE IF NOT EXISTS `authority` (
  `IDAuthority` int(11) NOT NULL AUTO_INCREMENT,
  `IDCategory` int(11) NOT NULL,
  `Username` varchar(30) NOT NULL,
  PRIMARY KEY (`IDAuthority`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `authority`
--

INSERT INTO `authority` (`IDAuthority`, `IDCategory`, `Username`) VALUES
(1, 6, 'admin'),
(8, 5, 'ranger');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `IDCategory` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(30) NOT NULL,
  `Creator` varchar(50) NOT NULL,
  PRIMARY KEY (`IDCategory`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`IDCategory`, `CategoryName`, `Creator`) VALUES
(1, 'Fraud', 'admin'),
(2, 'Robbery', 'progin'),
(3, 'Gambling', 'progin'),
(4, 'Public Drunkenness', 'ranger'),
(5, 'Drug Law Violation', 'admin'),
(6, 'Motor Vehicle Theft', 'coba');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `IDComment` int(11) NOT NULL AUTO_INCREMENT,
  `IDTask` int(11) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Content` text NOT NULL,
  `Timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IDComment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `IDTag` int(11) NOT NULL AUTO_INCREMENT,
  `TagName` varchar(30) NOT NULL,
  PRIMARY KEY (`IDTag`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`IDTag`, `TagName`) VALUES
(1, 'amel'),
(2, 'kevin'),
(3, 'devin'),
(4, 'geser');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `IDTask` int(11) NOT NULL AUTO_INCREMENT,
  `IDCategory` int(11) NOT NULL,
  `TaskName` varchar(50) NOT NULL,
  `Status` varchar(30) NOT NULL,
  `Deadline` date NOT NULL,
  `Creator` varchar(50) NOT NULL,
  PRIMARY KEY (`IDTask`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`IDTask`, `IDCategory`, `TaskName`, `Status`, `Deadline`, `Creator`) VALUES
(1, 1, 'Kenapa', 'done', '2013-03-14', 'coba'),
(2, 1, 'Kiri', 'done', '2013-03-02', 'coba'),
(3, 2, 'tese', 'done', '2013-03-03', 'coba'),
(4, 6, 'cobaqasdfd', 'done', '2013-03-16', 'admin'),
(5, 5, 'rapatanggota', 'done', '2013-03-30', 'ranger');

-- --------------------------------------------------------

--
-- Table structure for table `tasktag`
--

CREATE TABLE IF NOT EXISTS `tasktag` (
  `IDTaskTag` int(11) NOT NULL AUTO_INCREMENT,
  `IDTask` int(11) NOT NULL,
  `IDTag` int(11) NOT NULL,
  PRIMARY KEY (`IDTaskTag`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `tasktag`
--

INSERT INTO `tasktag` (`IDTaskTag`, `IDTask`, `IDTag`) VALUES
(1, 1, 3),
(2, 1, 2),
(3, 4, 1),
(4, 3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `Username` varchar(30) NOT NULL,
  `Fullname` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Avatar` varchar(50) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Username`, `Fullname`, `Password`, `DateOfBirth`, `Email`, `Avatar`) VALUES
('admin', 'Afif Al Hawari', 'ead87b76ecec61b5eeb1828da7fcaca2', '2013-03-02', 'afif.alhawari@gmail.com', 'img/fotoanonim.png'),
('coba', 'ini cuma coba', '1621a5dc746d5d19665ed742b2ef9736', '2013-03-02', 'sdfsdfas@tes.com', 'img/fotoanonim.png'),
('progin', 'user1', '5a6c733c30a2bb57dfc7b74ab3c18512', '2013-03-07', 'progin@if.itb', 'img/fotoanonim.png'),
('ranger', 'proginranger', 'ad92694923612da0600d7be498cc2e08', '2013-03-08', 'progin@ranger.com', 'img/fotoanonim.png');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`IDComment`) REFERENCES `task` (`IDTask`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tasktag`
--
ALTER TABLE `tasktag`
  ADD CONSTRAINT `tasktag_ibfk_1` FOREIGN KEY (`IDTaskTag`) REFERENCES `task` (`IDTask`) ON DELETE CASCADE ON UPDATE CASCADE;
  
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
