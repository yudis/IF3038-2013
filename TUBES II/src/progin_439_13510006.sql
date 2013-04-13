-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 23, 2013 at 04:56 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


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

INSERT INTO `assignee` (`IDTask`, `IDUser`) VALUES
('T001', 'david');

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `IDTask` varchar(255) NOT NULL,
  `Attachment` varchar(255) NOT NULL,
  PRIMARY KEY (`IDTask`,`Attachment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `Category` varchar(255) NOT NULL,
  `IDTask` varchar(255) NOT NULL,
  `IDCreator` varchar(255) NOT NULL,
  PRIMARY KEY (`Category`,`IDTask`,`IDCreator`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`Category`, `IDTask`, `IDCreator`) VALUES
('Perkuliahan', 'T001', 'fadhil');

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `ID` varchar(255) NOT NULL,
  `IDTask` varchar(255) NOT NULL,
  `IDUser` varchar(255) NOT NULL,
  `Waktu` datetime NOT NULL,
  `Isi` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `komentar`
--

INSERT INTO `komentar` (`ID`, `IDTask`, `IDUser`, `Waktu`, `Isi`) VALUES
('K001', 'T001', 'fadhil', '2013-03-01 00:00:00', 'Susah gan'),
('K002', 'T001', 'fadhil', '2013-03-07 00:00:00', 'stress nih'),
('K003', 'T001', 'david', '2013-03-08 00:00:00', 'gampang kok'),
('K004', 'T001', 'yulius', '2013-03-09 00:00:00', 'gampang dari mana vid'),
('K005', 'T001', 'david', '2013-03-11 00:00:00', '5 menit juga kelar'),
('K006', 'T001', 'fadhil', '2013-03-12 00:00:00', 'gak mungkin lah'),
('K007', 'T001', 'yulius', '2013-03-22 00:00:00', 'imba lo vid'),
('K008', 'T001', 'david', '2013-03-30 00:00:00', 'kalian yang cupu'),
('K009', 'T001', 'fadhil', '2013-03-31 00:00:00', 'sombong'),
('K010', 'T001', 'yulius', '2013-04-01 00:00:00', 'Songong lu'),
('K011', 'T001', 'david', '2013-04-03 00:00:00', 'emang gw imba'),
('K012', 'T001', 'fadhil', '2013-03-23 21:31:32', 'qqqqqqq'),
('K013', 'T001', 'fadhil', '2013-03-23 21:34:34', 'aaaaaaa');

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
('david', 'david', 'David E W', 'sasasas', '2013-03-23', 'david@gmail.com'),
('fadhil', 'fadhil', 'Fadhil M', 'asaassa', '2013-03-11', 'fmuhtadin@gmail.com'),
('yulius', 'yulius', 'Yulius N', 'asasasasas', '2013-03-23', 'yulius@gmail.com');

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

INSERT INTO `tags` (`IDTask`, `Tag`) VALUES
('T001', 'kuliah'),
('T001', 'susah'),
('T001', 'tubes');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `ID` varchar(255) NOT NULL,
  `IDCreator` varchar(255) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Status` int(1) NOT NULL,
  `Deadline` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`ID`, `IDCreator`, `Nama`, `Status`, `Deadline`) VALUES
('T001', 'fadhil', 'Tubes 2 progin', 0, '2013-04-25 00:00:00');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
