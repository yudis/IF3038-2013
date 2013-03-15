-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 15, 2013 at 03:39 PM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

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
-- Table structure for table `asigner`
--

CREATE TABLE IF NOT EXISTS `asigner` (
  `username` varchar(15) NOT NULL,
  `namatugas` varchar(30) NOT NULL,
  `asignee` int(11) NOT NULL,
  KEY `username` (`username`,`namatugas`),
  KEY `namatugas` (`namatugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `asigner`
--

INSERT INTO `asigner` (`username`, `namatugas`, `asignee`) VALUES
('dummy', 'abcdefg', 0),
('dummy', 'baru', 0),
('dummy', 'tes', 0),
('dummy', '', 0),
('dummy', '', 0),
('dummy', 'testestes', 0);

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `username` varchar(15) NOT NULL,
  `namatugas` varchar(30) NOT NULL,
  `namafile` varchar(30) NOT NULL,
  `tipefile` varchar(30) NOT NULL,
  `size` int(11) NOT NULL,
  `file` longblob NOT NULL,
  KEY `username` (`username`,`namatugas`),
  KEY `namatugas` (`namatugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `username` varchar(15) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `username` varchar(15) NOT NULL,
  `namatugas` varchar(30) NOT NULL,
  `komentar` int(11) NOT NULL,
  `penulis` varchar(160) NOT NULL,
  KEY `username` (`username`),
  KEY `namatugas` (`namatugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `profil`
--

CREATE TABLE IF NOT EXISTS `profil` (
  `username` varchar(15) NOT NULL,
  `namalengkap` varchar(50) NOT NULL,
  `tanggallahir` date NOT NULL,
  `email` varchar(20) NOT NULL,
  `avatar` longblob NOT NULL,
  UNIQUE KEY `email` (`email`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `username` varchar(15) NOT NULL,
  `namatugas` varchar(30) NOT NULL,
  `tag` varchar(20) NOT NULL,
  KEY `username` (`username`,`namatugas`),
  KEY `namatugas` (`namatugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`username`, `namatugas`, `tag`) VALUES
('dummy', 'abcdefg', 'bbbb'),
('dummy', 'baru', 'baru'),
('dummy', 'tes', 'tes'),
('dummy', '', ''),
('dummy', '', ''),
('dummy', 'testestes', 'kelompok');

-- --------------------------------------------------------

--
-- Table structure for table `tugas`
--

CREATE TABLE IF NOT EXISTS `tugas` (
  `username` varchar(15) NOT NULL,
  `namatugas` varchar(30) NOT NULL,
  `deadline` date NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  KEY `username` (`username`),
  KEY `namatugas` (`namatugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tugas`
--

INSERT INTO `tugas` (`username`, `namatugas`, `deadline`, `kategori`, `status`) VALUES
('dummy', 'abcdefg', '2013-01-01', 'dummy', 0),
('dummy', 'baru', '2013-02-02', 'dummy', 0),
('dummy', 'tes', '2013-01-01', 'dummy', 0),
('dummy', '', '2013-01-01', 'dummy', 0),
('dummy', '', '2013-01-01', 'dummy', 0),
('dummy', 'testestes', '2013-09-06', 'dummy', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`) VALUES
('dummy', 'dummy');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `asigner`
--
ALTER TABLE `asigner`
  ADD CONSTRAINT `asigner_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `asigner_ibfk_2` FOREIGN KEY (`namatugas`) REFERENCES `tugas` (`namatugas`);

--
-- Constraints for table `attachment`
--
ALTER TABLE `attachment`
  ADD CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `attachment_ibfk_2` FOREIGN KEY (`namatugas`) REFERENCES `tugas` (`namatugas`);

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `category_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`namatugas`) REFERENCES `tugas` (`namatugas`);

--
-- Constraints for table `profil`
--
ALTER TABLE `profil`
  ADD CONSTRAINT `profil_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Constraints for table `tag`
--
ALTER TABLE `tag`
  ADD CONSTRAINT `tag_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `tag_ibfk_2` FOREIGN KEY (`namatugas`) REFERENCES `tugas` (`namatugas`);

--
-- Constraints for table `tugas`
--
ALTER TABLE `tugas`
  ADD CONSTRAINT `tugas_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
