-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2013 at 10:00 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tubes2`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignee`
--

CREATE TABLE IF NOT EXISTS `assignee` (
  `idtask` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `assignee_by` int(11) NOT NULL,
  KEY `iduser` (`iduser`),
  KEY `idtask` (`idtask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignee`
--

INSERT INTO `assignee` (`idtask`, `iduser`, `assignee_by`) VALUES
(3, 1, 1),
(2, 1, 1),
(6, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `namakat` varchar(100) NOT NULL,
  `pembuat` int(11) NOT NULL,
  `share_to` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id`, `namakat`, `pembuat`, `share_to`) VALUES
(1, 'Kuliah', 1, 1),
(2, 'Organisasi', 1, 1),
(3, 'Percintaan', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idtask` int(11) DEFAULT NULL,
  `komentar` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idtask` (`idtask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idtask` int(11) NOT NULL,
  `namatag` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idtask_2` (`idtask`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`id`, `idtask`, `namatag`) VALUES
(1, 1, 'lelah'),
(2, 1, 'huft');

-- --------------------------------------------------------

--
-- Table structure for table `tugas`
--

CREATE TABLE IF NOT EXISTS `tugas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idkat` int(11) NOT NULL,
  `namatask` varchar(100) NOT NULL,
  `deadline` date NOT NULL,
  `status` int(1) NOT NULL,
  `attachment` varbinary(10000) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idkat_2` (`idkat`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `tugas`
--

INSERT INTO `tugas` (`id`, `idkat`, `namatask`, `deadline`, `status`, `attachment`) VALUES
(1, 1, 'Tubes Progin', '2013-03-23', 0, ''),
(2, 1, 'Tubes Sister', '2013-03-24', 1, ''),
(3, 1, 'Tugas IMK', '2013-03-24', 0, ''),
(4, 2, 'Diklat Legislatif', '2013-03-23', 0, ''),
(5, 2, 'LPJ DE', '2013-03-23', 1, ''),
(6, 3, 'Morning Call <3', '2013-03-25', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `sex` text NOT NULL,
  `tanggalbirthday` int(11) NOT NULL,
  `bulanbirthday` int(11) NOT NULL,
  `tahunbirthday` int(11) NOT NULL,
  `avatar` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `fullname`, `email`, `sex`, `tanggalbirthday`, `bulanbirthday`, `tahunbirthday`, `avatar`) VALUES
(1, 'firauliya', '123456789', 'Syafira Fitri Auliya', 'firauliya@yahoo.com', 'female', 0, 0, 0, '../images/User1'),
(2, 'herephy', '123456789', 'Here Phy', 'herephy@gmail.com', 'female', 0, 0, 0, '../images/User1'),
(3, 'inception', '123456789', 'Inception 2010', 'Inception@yahoo.com', 'male', 0, 0, 0, '../images/User1'),
(7, 'pahlevi', 'pahlevi@gmail.com', 'lalala', 'Pahlevi Fikri Auliya', 'male', 1, 0, 1989, '9021_1155810264113_1392119_n.jpg');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assignee`
--
ALTER TABLE `assignee`
  ADD CONSTRAINT `assignee_ibfk_1` FOREIGN KEY (`idtask`) REFERENCES `tugas` (`id`),
  ADD CONSTRAINT `assignee_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`);

--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `komentar_ibfk_1` FOREIGN KEY (`idtask`) REFERENCES `tugas` (`id`);

--
-- Constraints for table `tag`
--
ALTER TABLE `tag`
  ADD CONSTRAINT `tag_ibfk_1` FOREIGN KEY (`idtask`) REFERENCES `tugas` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
