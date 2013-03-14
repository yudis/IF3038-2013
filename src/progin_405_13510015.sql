-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 14, 2013 at 01:58 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `attachments`
--

CREATE TABLE IF NOT EXISTS `attachments` (
  `id_tugas` int(100) NOT NULL,
  `attachment` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tugas`,`attachment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `id_tugas` int(100) NOT NULL,
  `id_kategori` int(100) NOT NULL,
  PRIMARY KEY (`id_tugas`,`id_kategori`),
  KEY `tugas@002dkategori_ibfk_2` (`id_kategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id_tugas`, `id_kategori`) VALUES
(1, 1),
(2, 2),
(3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `last_mod` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id`, `nama`, `last_mod`) VALUES
(1, 'Intelegensi buatan', '2013-03-07 13:46:36'),
(2, 'Progin', '2013-03-12 17:00:00'),
(3, 'IMK', '2013-03-14 07:26:14'),
(4, 'SIster', '2013-03-14 07:41:42'),
(5, 'Benny', '2013-03-14 08:17:38');

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE IF NOT EXISTS `tags` (
  `id_tugas` int(100) NOT NULL AUTO_INCREMENT,
  `tag` varchar(30) NOT NULL,
  PRIMARY KEY (`id_tugas`,`tag`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`id_tugas`, `tag`) VALUES
(1, 'ada'),
(1, 'tes');

-- --------------------------------------------------------

--
-- Table structure for table `tugas`
--

CREATE TABLE IF NOT EXISTS `tugas` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `tgl_deadline` date NOT NULL,
  `status` tinyint(1) NOT NULL,
  `last_mod` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pemilik` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pemilik` (`pemilik`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `tugas`
--

INSERT INTO `tugas` (`id`, `nama`, `tgl_deadline`, `status`, `last_mod`, `pemilik`) VALUES
(1, 'gunBound', '2013-03-22', 0, '2013-03-12 17:00:00', 'edwardsp'),
(2, 'to do list', '2013-03-22', 0, '2013-03-12 17:00:00', 'edogawa'),
(3, 'gun', '2013-03-26', 1, '2013-03-13 17:00:00', 'edogawa');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(32) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` char(32) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `avatar` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `email_2` (`email`),
  UNIQUE KEY `email_3` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `email`, `password`, `full_name`, `tgl_lahir`, `avatar`) VALUES
('edogawa', 'edward_samuel_esp@yahoo.com', '19b13f6bbc0d19a5dba84b209a22a487', 'Edward Samuel', '0000-00-00', 'image-1.jpg'),
('edwardsp', 'edward_samuel_esp@hotmail.com', '9aa6e5f2256c17d2d430b100032b997c', 'Edward Samuel Pasaribu', '1992-07-16', 'edwardsp.jpg');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `asignee`
--
ALTER TABLE `asignee`
  ADD CONSTRAINT `asignee_ibfk_1` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `asignee_ibfk_2` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `attachments`
--
ALTER TABLE `attachments`
  ADD CONSTRAINT `attachments_ibfk_1` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `categories`
--
ALTER TABLE `categories`
  ADD CONSTRAINT `categories_ibfk_1` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `categories_ibfk_2` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tags`
--
ALTER TABLE `tags`
  ADD CONSTRAINT `tags_ibfk_1` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tugas`
--
ALTER TABLE `tugas`
  ADD CONSTRAINT `tugas_ibfk_1` FOREIGN KEY (`pemilik`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
