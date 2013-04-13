-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 23, 2013 at 11:43 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510100`
--

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `id_task` int(15) NOT NULL AUTO_INCREMENT,
  `nama_task` varchar(255) NOT NULL,
  `tanggal` date NOT NULL,
  `status` varchar(35) NOT NULL,
  `tag` varchar(455) NOT NULL,
  PRIMARY KEY (`id_task`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `task`
--


-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `iduser` int(3) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(16) NOT NULL,
  `name` varchar(30) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(35) NOT NULL,
  `avatar` varchar(50) NOT NULL,
  `jmltask` int(4) NOT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`iduser`, `username`, `password`, `name`, `dob`, `email`, `avatar`, `jmltask`) VALUES
(1, 'agnes', '12121212', 'Agnes Theresia', '1991-08-20', 'dam@yahoo.com', 'avatar/agnes.jpg', 0),
(2, 'ucup12', '123123123', 'Yusuf Ganteng', '1991-08-20', 'kimi_sanwalikenoother@yahoo.com', 'avatar/ucup12.jpg', 0),
(3, 'aknes1', '12312345', 'Theresia Damanik', '1991-08-21', 'lala@lili.com', 'avatar/aknes1.jpg', 0);
