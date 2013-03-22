-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 22, 2013 at 09:51 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignee`
--

CREATE TABLE IF NOT EXISTS `assignee` (
  `IDTask` varchar(255) NOT NULL,
  `IDUser` varchar(255) NOT NULL,
  PRIMARY KEY (`IDTask`,`IDUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignee`
--


-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `IDTask` varchar(255) NOT NULL,
  `Attachment` varchar(255) NOT NULL,
  PRIMARY KEY (`IDTask`,`Attachment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attachment`
--


-- --------------------------------------------------------

--
-- Table structure for table `hubkomentar`
--

CREATE TABLE IF NOT EXISTS `hubkomentar` (
  `IDTask` varchar(255) NOT NULL,
  `IDKomentar` varchar(255) NOT NULL,
  PRIMARY KEY (`IDTask`,`IDKomentar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hubkomentar`
--


-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `ID` varchar(255) NOT NULL,
  `Waktu` datetime NOT NULL,
  `Isi` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `komentar`
--


-- --------------------------------------------------------

--
-- Table structure for table `profil`
--

CREATE TABLE IF NOT EXISTS `profil` (
  `Username` varchar(255) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `FullName` varchar(80) NOT NULL,
  `Avatar` varchar(256) NOT NULL,
  `TanggalLahir` date NOT NULL,
  `Email` varchar(90) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profil`
--

INSERT INTO `profil` (`Username`, `Password`, `FullName`, `Avatar`, `TanggalLahir`, `Email`) VALUES
('dvdekow', 'yihayuhu', 'xxx', 'xxx', '0000-00-00', 'dvdekow@gmail.com'),
('fmuhtadin', 'test', '', '', '0000-00-00', 'muhtadin@gmail.com'),
('yulius', 'yus', '', '', '0000-00-00', 'yulius@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE IF NOT EXISTS `tags` (
  `IDTask` varchar(255) NOT NULL,
  `Tag` varchar(255) NOT NULL,
  PRIMARY KEY (`IDTask`,`Tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tags`
--


-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `ID` varchar(255) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Status` int(1) NOT NULL,
  `Deadline` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

