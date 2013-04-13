-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 23, 2013 at 04:47 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510087`
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
('Tugas_Besar_3', 'avatar.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `editkategori`
--

CREATE TABLE IF NOT EXISTS `editkategori` (
  `username` varchar(30) NOT NULL,
  `namaKategori` varchar(50) NOT NULL,
  KEY `username` (`username`),
  KEY `namaKategori` (`namaKategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
('Pemrograman_Internet', 'ariefsuharsono'),
('Sistem_Terdistribusi', 'fabrianoktavino');

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `idKomentar` int(11) NOT NULL,
  `komentator` varchar(30) NOT NULL,
  `isikomentar` text NOT NULL,
  `namaTask` varchar(50) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`idKomentar`),
  KEY `komentator` (`komentator`),
  KEY `namaTask` (`namaTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
('Tugas_Besar_3', 'php'),
('Tugas_Besar_3', 'ajax');

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
  `jumlahKomentar` int(11) NOT NULL,
  PRIMARY KEY (`namaTask`),
  KEY `namaKategori` (`namaKategori`),
  KEY `creatorTaskName` (`creatorTaskName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`namaTask`, `deadline`, `status`, `creatorTaskName`, `namaKategori`, `jumlahKomentar`) VALUES
('Tugas_Besar_3', '2013-03-23', 'Belum_Selesai', 'ariefsuharsono', 'Pemrograman_Internet', 0);

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
('Tugas_Besar_3', 'atikayusuf'),
('Tugas_Besar_3', 'fabrianoktavino'),
('Tugas_Besar_3', 'ariefsuharsono');

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
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `email`, `fullname`, `avatar`, `tanggalLahir`, `password`) VALUES
('ariefsuharsono', 'arief.suharsono@hotmail.com', 'Arief Suharsono', 'avatar.jpg', '1991-04-08', 'ariefsuharsono'),
('atikayusuf', 'atika.yusuf@gmail.com', 'Atika Yusuf', 'avatar.jpg', '1992-10-29', 'atikayusuf'),
('fabrianoktavino', 'fabrian.oktavino@yahoo.com', 'Fabrian Oktavino', 'avatar.jpg', '1992-10-07', 'fabrianoktavino');

-- --------------------------------------------------------

--
-- Table structure for table `usertotask`
--

CREATE TABLE IF NOT EXISTS `usertotask` (
  `username` varchar(30) NOT NULL,
  `namaTask` varchar(50) NOT NULL,
  KEY `username` (`username`),
  KEY `namaTask` (`namaTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usertotask`
--

INSERT INTO `usertotask` (`username`, `namaTask`) VALUES
('ariefsuharsono', 'Tugas_Besar_3'),
('atikayusuf', 'Tugas_Besar_3'),
('fabrianoktavino', 'Tugas_Besar_3');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attach`
--
ALTER TABLE `attach`
  ADD CONSTRAINT `attach_ibfk_1` FOREIGN KEY (`namaTask`) REFERENCES `task` (`namaTask`);

--
-- Constraints for table `editkategori`
--
ALTER TABLE `editkategori`
  ADD CONSTRAINT `editkategori_ibfk_2` FOREIGN KEY (`namaKategori`) REFERENCES `kategori` (`namaKategori`),
  ADD CONSTRAINT `editkategori_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Constraints for table `kategori`
--
ALTER TABLE `kategori`
  ADD CONSTRAINT `kategori_ibfk_1` FOREIGN KEY (`creatorKategoriName`) REFERENCES `user` (`username`);

--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `komentar_ibfk_2` FOREIGN KEY (`namaTask`) REFERENCES `task` (`namaTask`),
  ADD CONSTRAINT `komentar_ibfk_1` FOREIGN KEY (`komentator`) REFERENCES `user` (`username`);

--
-- Constraints for table `tagging`
--
ALTER TABLE `tagging`
  ADD CONSTRAINT `tagging_ibfk_1` FOREIGN KEY (`namaTask`) REFERENCES `task` (`namaTask`);

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_2` FOREIGN KEY (`creatorTaskName`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`namaKategori`) REFERENCES `kategori` (`namaKategori`);

--
-- Constraints for table `tasktoasignee`
--
ALTER TABLE `tasktoasignee`
  ADD CONSTRAINT `tasktoasignee_ibfk_2` FOREIGN KEY (`asigneeName`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `tasktoasignee_ibfk_1` FOREIGN KEY (`namaTask`) REFERENCES `task` (`namaTask`);

--
-- Constraints for table `usertotask`
--
ALTER TABLE `usertotask`
  ADD CONSTRAINT `usertotask_ibfk_2` FOREIGN KEY (`namaTask`) REFERENCES `task` (`namaTask`),
  ADD CONSTRAINT `usertotask_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
