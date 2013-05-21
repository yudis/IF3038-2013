-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 13, 2013 at 06:54 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_471_13510087`
--

-- --------------------------------------------------------

--
-- Table structure for table `attach`
--

CREATE TABLE IF NOT EXISTS `attach` (
  `namaTask` varchar(50) NOT NULL,
  `attachment` varchar(100) NOT NULL,
  KEY `namaTask` (`namaTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attach`
--

INSERT INTO `attach` (`namaTask`, `attachment`) VALUES
('task 1', 'attachment/avatar.jpg'),
('task 1', 'movie.mp4'),
('task 1', 'attachment/sql.pdf'),
('coba task 1', 'avatar.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `namaKategori` varchar(50) NOT NULL,
  `creatorKategoriName` varchar(30) NOT NULL,
  PRIMARY KEY (`namaKategori`),
  KEY `creatorKategoriName` (`creatorKategoriName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`namaKategori`, `creatorKategoriName`) VALUES
('categ1', 'arief'),
('categcoba', 'arief'),
('newcategory', 'arief'),
('categ2', 'arief2'),
('categ3', 'arief3');

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `idKomentar` int(11) NOT NULL AUTO_INCREMENT,
  `komentator` varchar(30) NOT NULL,
  `isikomentar` text NOT NULL,
  `namaTask` varchar(50) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`idKomentar`),
  KEY `komentator` (`komentator`),
  KEY `namaTask` (`namaTask`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `komentar`
--

INSERT INTO `komentar` (`idKomentar`, `komentator`, `isikomentar`, `namaTask`, `timestamp`) VALUES
(1, 'arief', 'alohaa~', 'task 1', '2013-04-09 00:00:00'),
(2, 'arief2', 'halo juga', 'task 1', '2013-03-03 00:00:00'),
(3, 'coba', 'nyobain nih', 'task 1', '2013-03-12 00:00:00'),
(4, 'arief', 'coba komentar', 'task 1', '2013-03-05 10:32:38'),
(5, 'arief', 'coba juga', 'task 1', '2013-03-22 08:28:39'),
(6, 'ariefs2', 'afufufuuff', 'task 1', '2013-03-12 12:34:43'),
(7, 'arief', 'asdfasdf', 'task 1', '2013-03-13 19:39:11'),
(8, 'arief', 'komentar baruu~', 'task 1', '2013-04-12 19:40:15'),
(9, 'arief', 'ehehehe', 'task 1', '2013-04-12 19:40:54'),
(10, 'arief', 'tambah', 'task 1', '2013-04-12 19:40:59'),
(11, 'arief', 'tambah lagi', 'task 1', '2013-04-12 19:41:05'),
(12, 'arief', 'lagi dan lagi', 'task 1', '2013-04-12 19:41:10'),
(13, 'arief', 'testing 1', 'task 1', '2013-04-12 19:55:57'),
(14, 'arief', 'testing 2', 'task 1', '2013-04-12 19:56:01'),
(15, 'arief', 'testing 3', 'task 1', '2013-04-12 19:56:06'),
(16, 'arief', 'testing 4', 'task 1', '2013-04-12 19:56:10'),
(17, 'arief', 'testing 5', 'task 1', '2013-04-12 19:56:14'),
(18, 'arief', 'testing 6', 'task 1', '2013-04-12 19:56:18'),
(19, 'arief', 'testing 7', 'task 1', '2013-04-12 19:56:22'),
(20, 'arief', 'testing 8', 'task 1', '2013-04-12 19:56:27');

-- --------------------------------------------------------

--
-- Table structure for table `tagging`
--

CREATE TABLE IF NOT EXISTS `tagging` (
  `namaTask` varchar(50) NOT NULL,
  `tag` varchar(50) NOT NULL,
  KEY `namaTask` (`namaTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tagging`
--

INSERT INTO `tagging` (`namaTask`, `tag`) VALUES
('task 2', 'progin'),
('task 2', 'tubes'),
('task 2', 'gampang'),
('coba task 1', 'progin'),
('coba task 1', 'susah'),
('coba task 1', 'banget'),
('coba task 1', 'asem'),
('task 1', 'progin'),
('task 1', 'tubes'),
('task 1', 'susah'),
('task 1', 'banget');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `namaTask` varchar(50) NOT NULL,
  `deadline` date NOT NULL,
  `status` varchar(20) NOT NULL,
  `creatorTaskName` varchar(30) NOT NULL,
  `namaKategori` varchar(50) NOT NULL,
  PRIMARY KEY (`namaTask`),
  KEY `namaKategori` (`namaKategori`),
  KEY `creatorTaskName` (`creatorTaskName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`namaTask`, `deadline`, `status`, `creatorTaskName`, `namaKategori`) VALUES
('coba task 1', '2013-04-13', 'undone', 'arief', 'categ1'),
('task 1', '2013-03-05', 'undone', 'arief', 'categ1'),
('task 2', '2013-03-08', 'undone', 'arief2', 'categ2'),
('task 3', '2013-03-06', 'undone', 'arief3', 'categ2');

-- --------------------------------------------------------

--
-- Table structure for table `tasktoasignee`
--

CREATE TABLE IF NOT EXISTS `tasktoasignee` (
  `namaTask` varchar(50) NOT NULL,
  `asigneeName` varchar(30) NOT NULL,
  KEY `namaTask` (`namaTask`),
  KEY `asigneeName` (`asigneeName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tasktoasignee`
--

INSERT INTO `tasktoasignee` (`namaTask`, `asigneeName`) VALUES
('task 2', 'arief2'),
('task 2', 'arief'),
('coba task 1', 'arief'),
('coba task 1', 'arief2'),
('coba task 1', 'arief3'),
('coba task 1', 'coba'),
('task 1', 'arief'),
('task 1', 'arief2');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(30) NOT NULL,
  `email` varchar(40) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `avatar` varchar(30) NOT NULL,
  `tanggalLahir` date NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `email`, `fullname`, `avatar`, `tanggalLahir`, `password`) VALUES
('arief', 'arief@arief.arief59', 'arief suharsono', 'avatar2.jpg', '2013-04-30', 'arief12345'),
('arief2', 'arief@arief.arief2', 'arief', 'images/avatar.jpg', '2013-03-07', 'arief'),
('arief3', 'arief@arief.arief3', 'arief', 'images/avatar.jpg', '2013-03-07', 'arief'),
('ariefs', 'arief@arief.arief', 'arief suharsono', 'kartunngampus.jpg', '2013-04-24', 'ariefsuharsono'),
('ariefs2', 'arief@arief.arief12345', 'arief suharsono', 'kartunngampus.jpg', '2013-04-24', 'arief12345'),
('coba', 'coba@coba.coba', 'coba coba', 'back.png', '2013-04-04', 'coba saja');

-- --------------------------------------------------------

--
-- Table structure for table `usertocategory`
--

CREATE TABLE IF NOT EXISTS `usertocategory` (
  `username` varchar(20) NOT NULL,
  `namaKategori` varchar(20) NOT NULL,
  KEY `username` (`username`),
  KEY `namaKategori` (`namaKategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usertocategory`
--

INSERT INTO `usertocategory` (`username`, `namaKategori`) VALUES
('arief', 'categ1'),
('arief2', 'categ2'),
('arief3', 'categ3'),
('arief', 'newcategory'),
('arief', 'categcoba'),
('arief', 'categ2');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attach`
--
ALTER TABLE `attach`
  ADD CONSTRAINT `attach_ibfk_1` FOREIGN KEY (`namaTask`) REFERENCES `task` (`namaTask`);

--
-- Constraints for table `kategori`
--
ALTER TABLE `kategori`
  ADD CONSTRAINT `kategori_ibfk_1` FOREIGN KEY (`creatorKategoriName`) REFERENCES `user` (`username`);

--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `komentar_ibfk_1` FOREIGN KEY (`komentator`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `komentar_ibfk_2` FOREIGN KEY (`namaTask`) REFERENCES `task` (`namaTask`);

--
-- Constraints for table `tagging`
--
ALTER TABLE `tagging`
  ADD CONSTRAINT `tagging_ibfk_1` FOREIGN KEY (`namaTask`) REFERENCES `task` (`namaTask`);

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`namaKategori`) REFERENCES `kategori` (`namaKategori`),
  ADD CONSTRAINT `task_ibfk_2` FOREIGN KEY (`creatorTaskName`) REFERENCES `user` (`username`);

--
-- Constraints for table `tasktoasignee`
--
ALTER TABLE `tasktoasignee`
  ADD CONSTRAINT `tasktoasignee_ibfk_1` FOREIGN KEY (`namaTask`) REFERENCES `task` (`namaTask`),
  ADD CONSTRAINT `tasktoasignee_ibfk_2` FOREIGN KEY (`asigneeName`) REFERENCES `user` (`username`);

--
-- Constraints for table `usertocategory`
--
ALTER TABLE `usertocategory`
  ADD CONSTRAINT `usertocategory_ibfk_2` FOREIGN KEY (`namaKategori`) REFERENCES `kategori` (`namaKategori`),
  ADD CONSTRAINT `usertocategory_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
