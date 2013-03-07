-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 07, 2013 at 02:55 PM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510015`
--

-- --------------------------------------------------------

--
-- Table structure for table `asignee`
--

CREATE TABLE IF NOT EXISTS `asignee` (
  `id_tugas` int(100) NOT NULL,
  `username` varchar(25) NOT NULL,
  PRIMARY KEY (`id_tugas`,`username`),
  KEY `asignee_ibfk_2` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `asignee`
--

INSERT INTO `asignee` (`id_tugas`, `username`) VALUES
(1, 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `last_mod` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id`, `nama`, `last_mod`) VALUES
(1, 'Intelegensi buatan', '2013-03-07 20:46:36');

-- --------------------------------------------------------

--
-- Table structure for table `pemilik`
--

CREATE TABLE IF NOT EXISTS `pemilik` (
  `username` varchar(25) NOT NULL,
  `id_tugas` int(100) NOT NULL,
  PRIMARY KEY (`username`,`id_tugas`),
  KEY `pemilik_ibfk_2` (`id_tugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pemilik`
--

INSERT INTO `pemilik` (`username`, `id_tugas`) VALUES
('admin', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tugas`
--

CREATE TABLE IF NOT EXISTS `tugas` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `tgl_deadline` date NOT NULL,
  `status` tinyint(1) NOT NULL,
  `last_mod` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `tugas`
--

INSERT INTO `tugas` (`id`, `nama`, `tgl_deadline`, `status`, `last_mod`) VALUES
(1, 'Gunbound', '2013-03-30', 0, '2013-03-07 20:47:30');

-- --------------------------------------------------------

--
-- Table structure for table `tugas-kategori`
--

CREATE TABLE IF NOT EXISTS `tugas-kategori` (
  `id_tugas` int(100) NOT NULL,
  `id_kategori` int(100) NOT NULL,
  PRIMARY KEY (`id_tugas`,`id_kategori`),
  KEY `tugas@002dkategori_ibfk_2` (`id_kategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tugas-kategori`
--

INSERT INTO `tugas-kategori` (`id_tugas`, `id_kategori`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tugas-tag`
--

CREATE TABLE IF NOT EXISTS `tugas-tag` (
  `id_tugas` int(100) NOT NULL AUTO_INCREMENT,
  `tag` varchar(30) NOT NULL,
  PRIMARY KEY (`id_tugas`,`tag`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `tugas-tag`
--

INSERT INTO `tugas-tag` (`id_tugas`, `tag`) VALUES
(1, 'AI'),
(1, 'IB');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(25) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(25) NOT NULL,
  `full_name` text NOT NULL,
  `tgl_lahir` date NOT NULL,
  `avatar` varchar(200) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `email`, `password`, `full_name`, `tgl_lahir`, `avatar`) VALUES
('admin', 'admin@admin.com', 'adminadmin', 'admin admin', '2013-03-07', 'D:/image.jpg');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `asignee`
--
ALTER TABLE `asignee`
  ADD CONSTRAINT `asignee_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `asignee_ibfk_1` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pemilik`
--
ALTER TABLE `pemilik`
  ADD CONSTRAINT `pemilik_ibfk_2` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pemilik_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tugas-kategori`
--
ALTER TABLE `tugas-kategori`
  ADD CONSTRAINT `tugas@002dkategori_ibfk_2` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tugas@002dkategori_ibfk_1` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tugas-tag`
--
ALTER TABLE `tugas-tag`
  ADD CONSTRAINT `tugas@002dtag_ibfk_1` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
