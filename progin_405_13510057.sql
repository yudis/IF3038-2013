-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 23, 2013 at 07:34 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


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
('iLol', '1'),
('dragoon20', '11'),
('yagami', '4'),
('soba5', '4'),
('moonray', '10'),
('excalibur', '5'),
('coba2', '6'),
('borju', '7'),
('12345', '8'),
('borju', '9'),
('sun', '10'),
('xelif', '12'),
('coba3', '13'),
('moonray', '1');

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `idtugas` varchar(10) NOT NULL,
  `isiattachment` varchar(100) NOT NULL,
  KEY `attacment_ibfk_1` (`idtugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attachment`
--

INSERT INTO `attachment` (`idtugas`, `isiattachment`) VALUES
('3', 'upload/tugas1.pdf'),
('4', 'upload/tugas5.pdf'),
('3', 'upload/tugas3.pdf'),
('4', 'upload/tugas4.pdf'),
('5', 'upload/tugas5.pdf'),
('6', 'upload/tugas6.pdf'),
('7', 'upload/tugas7.pdf'),
('8', 'upload/tugas8.pdf'),
('9', 'upload/tugas9.pdf'),
('10', 'upload/tugas10.pdf'),
('11', 'upload/tugas11.pdf'),
('12', 'upload/tugas12.pdf');

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
('moonray', '2'),
('soba4', '3'),
('coba2', '6'),
('xelif', '4'),
('excalibur', '5'),
('sun', '7'),
('raven', '8'),
('yagami', '9'),
('soba5', '10'),
('edo', '11'),
('maria', '12'),
('12345', '13'),
('coba3', '14');

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
('10', 'Kategori 10'),
('11', 'Kategori 11'),
('12', 'Kategori 12'),
('13', 'Kategori 13'),
('14', 'Kategori 14'),
('2', 'Tugas Pribadi'),
('3', 'Kategori 3'),
('4', 'Kategori 4'),
('5', 'Kategori 5'),
('6', 'Kategori 6'),
('7', 'Kategori 7'),
('8', 'Kategori 8'),
('9', 'Kategori 9');

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `idtugas` varchar(10) NOT NULL,
  `username` varchar(12) NOT NULL,
  `isikomentar` varchar(200) NOT NULL,
  `waktu` datetime NOT NULL,
  KEY `komentar_ibfk_1` (`username`),
  KEY `komentar_ibfk_2` (`idtugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `komentar`
--

INSERT INTO `komentar` (`idtugas`, `username`, `isikomentar`, `waktu`) VALUES
('1', 'moonray', 'saya sudah kirim ', '2013-03-15 15:00:01'),
('5', 'raven', 'selesai bung', '0000-00-00 00:00:00'),
('12', 'sun', 'yes', '0000-00-00 00:00:00'),
('11', 'dragoon20', 'oh', '0000-00-00 00:00:00'),
('10', 'yagami', 'a', '0000-00-00 00:00:00'),
('9', 'borju', 'iPad', '0000-00-00 00:00:00'),
('8', 'coba', 'test', '0000-00-00 00:00:00'),
('7', 'raven', 'hmm', '0000-00-00 00:00:00'),
('6', 'soba5', 'hahhh', '0000-00-00 00:00:00'),
('5', '12345', 'oke', '0000-00-00 00:00:00'),
('4', 'moonray', 'nice', '0000-00-00 00:00:00'),
('4', 'coba2', 'hore', '0000-00-00 00:00:00');

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
('3', 'Tugas3'),
('4', 'Tugas4'),
('5', 'Tgs5'),
('6', 'Tgs6'),
('7', 'Tgs7'),
('8', 'Tugas8'),
('12', 'Tucil5'),
('13', 'Tugas13'),
('2', 'sdfdsf'),
('1', 'kuliah');

-- --------------------------------------------------------

--
-- Table structure for table `tugas`
--

CREATE TABLE IF NOT EXISTS `tugas` (
  `idtugas` varchar(10) NOT NULL,
  `namatugas` varchar(20) NOT NULL,
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

INSERT INTO `tugas` (`idtugas`, `namatugas`, `deadline`, `idkategori`, `username`, `status`) VALUES
('1', 'Tubes 2 Progin', '2013-03-23', '1', 'moonray', 'undone'),
('10', 'Tugas10', '2013-03-13', '9', 'dragoon20', 'Done'),
('11', 'Tugas11', '2013-03-20', '10', 'coba2', 'Undone'),
('12', 'Tugas12', '2013-03-18', '11', 'edo', 'Undone'),
('13', 'Tugas13', '2013-03-16', '12', 'yagami', 'Undone'),
('2', 'UTS Psiter', '2013-03-14', '2', 'moonray', 'done'),
('3', 'Tugas3', '2013-03-30', '3', 'xelif', 'Undone'),
('4', 'Tugas4', '2013-03-22', '4', 'xelif', 'Undone'),
('5', 'Tugas5', '2013-03-29', '5', 'yagami', 'Done'),
('6', 'Tugas6', '2013-03-20', '6', 'maria', 'Done'),
('7', 'Tugas7', '2013-03-09', '7', 'sun', 'Undone'),
('8', 'Tugas8', '2013-03-26', '7', 'excalibur', 'Done'),
('9', 'Tugas9', '2013-03-17', '8', 'raven', 'Done');

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
  `avatar` varchar(100) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `fullname`, `birthdate`, `phonenumber`, `email`, `avatar`) VALUES
('12345', '12345', 'abc def', '1900-01-01', '0', 'abc@gmail.com', ''),
('borju', '12345', 'Wilson Fonda', '1990-01-01', '0', '222@gmail.com', ''),
('coba', 'coba', 'Maria Orlan', '2013-03-14', '', 'MO@yahoo.com', ''),
('coba2', 'coba2', 'Marie Orlan', '2013-03-23', '0', 'mor@yahoo.com', ''),
('coba3', 'coba3', 'Mariane Orlan', '2013-03-22', '0', 'maor@yahoo.com', ''),
('dragoon20', '12345', 'Jordan Fernando', '1990-01-01', '0', 'dragoon@gmail.com', ''),
('edo', '12345', 'Edward Samuel', '1990-01-01', '0', '555@gmail.com', ''),
('excalibur', '12345', 'Mario Orlando Tank', '1990-01-01', '0', 'excalibur@yahoo.com', ''),
('iLol', '87654321', 'Kevin Winata', '1955-01-01', '081987654321', 'kevin@gmail.com', 'upload/282804_700b_v1.jpg'),
('maria', '12345', 'Maria Orlanda', '1990-01-01', '0', 'maria@gmail.com', ''),
('moonray', '12345678', 'Raymond Lukanta', '1955-01-01', '081912345678', 'raymond@gmail.com', 'upload/287712_700b.jpg'),
('raven', 'rage', 'Mario Orlan', '2013-03-16', '0', 'mario@yahoo.com', ''),
('soba4', 'soba4', 'Marine Orlan', '2013-03-22', '0', 'marri@yahoo.com', ''),
('soba5', 'soba5', 'Marina Orlan', '2013-03-29', '0', 'marina@yahoo.com', ''),
('sun', '12345', 'Nikodemus Adriel', '1990-01-01', '0', '444@gmail.com', ''),
('xelif', '12345', 'Felix Terahadi', '1990-01-01', '0', '333@gmail.com', ''),
('yagami', '12345', 'Alfianto Jangtjik', '1990-01-01', '0', '111@gmail.com', '');

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
-- Constraints for table `attachment`
--
ALTER TABLE `attachment`
  ADD CONSTRAINT `attacment_ibfk_1` FOREIGN KEY (`idtugas`) REFERENCES `tugas` (`idtugas`);

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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
