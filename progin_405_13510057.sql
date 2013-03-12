-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 12, 2013 at 05:35 PM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510057`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignee`
--

CREATE TABLE IF NOT EXISTS `assignee` (
  `username` varchar(12) NOT NULL,
  `idtugas` varchar(10) NOT NULL,
  KEY `assignee_ibfk_1` (`idtugas`),
  KEY `assignee_ibfk_2` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignee`
--

INSERT INTO `assignee` (`username`, `idtugas`) VALUES
('iLol', '1');

-- --------------------------------------------------------

--
-- Table structure for table `hak`
--

CREATE TABLE IF NOT EXISTS `hak` (
  `username` varchar(12) NOT NULL,
  `idkategori` varchar(10) NOT NULL,
  KEY `hak_ibfk_1` (`username`),
  KEY `hak_ibfk_2` (`idkategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hak`
--

INSERT INTO `hak` (`username`, `idkategori`) VALUES
('iLol', '1'),
('moonray', '1'),
('moonray', '2');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `idkategori` varchar(10) NOT NULL,
  `namakategori` varchar(20) NOT NULL,
  PRIMARY KEY (`idkategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`idkategori`, `namakategori`) VALUES
('1', 'Tugas Kelompok'),
('2', 'Tugas Pribadi');

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `idtugas` varchar(10) NOT NULL,
  `username` varchar(12) NOT NULL,
  `isikomentar` varchar(100) NOT NULL,
  KEY `komentar_ibfk_1` (`username`),
  KEY `komentar_ibfk_2` (`idtugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `komentar`
--


-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `idtugas` varchar(10) NOT NULL,
  `isitag` varchar(20) NOT NULL,
  KEY `tag_ibfk_1` (`idtugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`idtugas`, `isitag`) VALUES
('1', 'kuliah'),
('1', 'tubes'),
('2', 'kuliah'),
('2', 'uts');

-- --------------------------------------------------------

--
-- Table structure for table `tugas`
--

CREATE TABLE IF NOT EXISTS `tugas` (
  `idtugas` varchar(10) NOT NULL,
  `namatugas` varchar(20) NOT NULL,
  `attachment` varchar(30) NOT NULL,
  `deadline` date NOT NULL,
  `idkategori` varchar(10) NOT NULL,
  `username` varchar(12) NOT NULL,
  `status` varchar(10) NOT NULL,
  UNIQUE KEY `idtugas` (`idtugas`),
  KEY `tugas_ibfk_1` (`username`),
  KEY `tugas_ibfk_2` (`idkategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tugas`
--

INSERT INTO `tugas` (`idtugas`, `namatugas`, `attachment`, `deadline`, `idkategori`, `username`, `status`) VALUES
('1', 'Tubes 2 Progin', 'upload/tubes2progin.pdf', '2013-03-23', '1', 'moonray', 'undone'),
('2', 'UTS Psiter', 'upload/utspsiter.pdf', '2013-03-14', '2', 'moonray', 'done');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(12) NOT NULL,
  `password` varchar(20) NOT NULL,
  `fullname` varchar(25) NOT NULL,
  `birthdate` date NOT NULL,
  `phonenumber` varchar(12) NOT NULL,
  `email` varchar(20) NOT NULL,
  `avatar` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `fullname`, `birthdate`, `phonenumber`, `email`, `avatar`) VALUES
('iLol', '87654321', 'Kevin Winata', '1992-12-25', '081987654321', 'kevin@gmail.com', 'upload/kevin.jpg'),
('moonray', '12345678', 'Raymond Lukanta', '1992-12-01', '081912345678', 'raymond@gmail.com', 'upload/raymond.jpg');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assignee`
--
ALTER TABLE `assignee`
  ADD CONSTRAINT `assignee_ibfk_1` FOREIGN KEY (`idtugas`) REFERENCES `tugas` (`idtugas`),
  ADD CONSTRAINT `assignee_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Constraints for table `hak`
--
ALTER TABLE `hak`
  ADD CONSTRAINT `hak_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `hak_ibfk_2` FOREIGN KEY (`idkategori`) REFERENCES `kategori` (`idkategori`);

--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `komentar_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `komentar_ibfk_2` FOREIGN KEY (`idtugas`) REFERENCES `tugas` (`idtugas`);

--
-- Constraints for table `tag`
--
ALTER TABLE `tag`
  ADD CONSTRAINT `tag_ibfk_1` FOREIGN KEY (`idtugas`) REFERENCES `tugas` (`idtugas`);

--
-- Constraints for table `tugas`
--
ALTER TABLE `tugas`
  ADD CONSTRAINT `tugas_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `tugas_ibfk_2` FOREIGN KEY (`idkategori`) REFERENCES `kategori` (`idkategori`);
