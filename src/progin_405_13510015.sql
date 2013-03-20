-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 20, 2013 at 06:05 PM
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
-- Table structure for table `assignees`
--

CREATE TABLE IF NOT EXISTS `assignees` (
  `id_tugas` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  PRIMARY KEY (`id_tugas`,`username`),
  KEY `asignee_ibfk_2` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignees`
--

INSERT INTO `assignees` (`id_tugas`, `username`) VALUES
(1, 'edogawa'),
(2, 'edogawa'),
(7, 'edogawa'),
(1, 'edwardsp'),
(3, 'edwardsp');

-- --------------------------------------------------------

--
-- Table structure for table `attachments`
--

CREATE TABLE IF NOT EXISTS `attachments` (
  `id_attachment` int(30) NOT NULL AUTO_INCREMENT,
  `id_tugas` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `filename` varchar(100) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id_attachment`),
  KEY `attachments_ibfk_1` (`id_tugas`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `attachments`
--

INSERT INTO `attachments` (`id_attachment`, `id_tugas`, `name`, `filename`, `type`) VALUES
(1, 1, 'Hai.zip', '089767979878.zip', 'file'),
(2, 1, 'lalla.jpg', '80756d75476597.jpg', 'image'),
(3, 7, 'felix.jpg', 'C:\\xampp\\tmp\\php8ADB.tmp', 'image/jpeg'),
(4, 7, 'image-1.jpg', 'C:\\xampp\\tmp\\php8AEB.tmp', 'image/jpeg'),
(5, 8, 'image-3.jpg', 'C:\\xampp\\tmp\\php842B.tmp', 'image/jpeg'),
(6, 8, 'loginbutton.png', 'C:\\xampp\\tmp\\php842C.tmp', 'image/png'),
(7, 8, 'signupbutton.png', 'C:\\xampp\\tmp\\php843C.tmp', 'image/png');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `last_mod` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `nama`, `last_mod`) VALUES
(1, 'Intelegensi buatan', '2013-03-07 06:46:36'),
(2, 'Progin', '2013-03-12 10:00:00'),
(4, 'SIster', '2013-03-14 00:41:42');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idtugas` int(11) NOT NULL,
  `user` varchar(32) NOT NULL,
  `time` int(11) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comments_ibfk_1` (`idtugas`),
  KEY `comments_ibfk_2` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `coordinator`
--

CREATE TABLE IF NOT EXISTS `coordinator` (
  `id_kategori` int(50) NOT NULL,
  `user` varchar(32) NOT NULL,
  PRIMARY KEY (`id_kategori`,`user`),
  KEY `user` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `coordinator`
--

INSERT INTO `coordinator` (`id_kategori`, `user`) VALUES
(1, 'edogawa'),
(2, 'edogawa'),
(4, 'edogawa'),
(1, 'edwardsp'),
(2, 'edwardsp');

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE IF NOT EXISTS `tags` (
  `id_tugas` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(30) NOT NULL,
  PRIMARY KEY (`id_tugas`,`tag`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`id_tugas`, `tag`) VALUES
(1, 'ada'),
(1, 'tes'),
(7, 'ampas'),
(7, 'susah'),
(8, 'buat'),
(8, 'wilson'),
(8, 'yang');

-- --------------------------------------------------------

--
-- Table structure for table `tugas`
--

CREATE TABLE IF NOT EXISTS `tugas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `tgl_deadline` date NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `last_mod` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pemilik` varchar(32) NOT NULL,
  `id_kategori` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tugas_ibfk_1` (`pemilik`),
  KEY `tugas_ibfk_2` (`id_kategori`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `tugas`
--

INSERT INTO `tugas` (`id`, `nama`, `tgl_deadline`, `status`, `last_mod`, `pemilik`, `id_kategori`) VALUES
(1, 'gunBound', '2013-03-22', 0, '2013-03-12 10:00:00', 'edwardsp', 1),
(2, 'to do list', '2013-03-22', 0, '2013-03-12 10:00:00', 'edogawa', 2),
(3, 'gun', '2013-03-26', 1, '2013-03-13 10:00:00', 'edogawa', 2),
(7, 'Thrall', '2013-03-21', 0, '2013-03-20 16:39:57', 'edwardsp', 1),
(8, 'DoneEarly', '2013-03-13', 0, '2013-03-20 16:46:28', 'edwardsp', 2);

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
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `email`, `password`, `full_name`, `tgl_lahir`, `avatar`) VALUES
('edogawa', 'edward_samuel_esp@yahoo.com', '19b13f6bbc0d19a5dba84b209a22a487', 'Edward Samuel', '1991-12-16', 'edogawa.jpg'),
('edwardsp', 'edward_samuel_esp@hotmail.com', '9aa6e5f2256c17d2d430b100032b997c', 'Edward Samuel Pasaribu', '1992-07-16', 'edwardsp.jpg');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assignees`
--
ALTER TABLE `assignees`
  ADD CONSTRAINT `assignees_ibfk_4` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `assignees_ibfk_3` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `attachments`
--
ALTER TABLE `attachments`
  ADD CONSTRAINT `attachments_ibfk_1` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`);

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`idtugas`) REFERENCES `tugas` (`id`),
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user`) REFERENCES `users` (`username`);

--
-- Constraints for table `coordinator`
--
ALTER TABLE `coordinator`
  ADD CONSTRAINT `coordinator_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `coordinator_ibfk_2` FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tags`
--
ALTER TABLE `tags`
  ADD CONSTRAINT `tags_ibfk_1` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`);

--
-- Constraints for table `tugas`
--
ALTER TABLE `tugas`
  ADD CONSTRAINT `tugas_ibfk_1` FOREIGN KEY (`pemilik`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `tugas_ibfk_2` FOREIGN KEY (`id_kategori`) REFERENCES `categories` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
