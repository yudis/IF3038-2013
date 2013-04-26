-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 13, 2013 at 07:49 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_471_13510039`
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
(23, 'edwardsp'),
(14, 'felixt'),
(18, 'felixt'),
(23, 'felixt'),
(19, 'wIlson');

-- --------------------------------------------------------

--
-- Table structure for table `attachments`
--

CREATE TABLE IF NOT EXISTS `attachments` (
  `id_attachment` int(11) NOT NULL AUTO_INCREMENT,
  `id_tugas` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `filename` varchar(100) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id_attachment`),
  KEY `attachments_ibfk_1` (`id_tugas`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `attachments`
--

INSERT INTO `attachments` (`id_attachment`, `id_tugas`, `name`, `filename`, `type`) VALUES
(13, 14, 'PROGIN.jpg', 'd05aebfecc37280437b02591573d6e03514d59b9208c98.93112038', 'image'),
(14, 14, 'PROGIN.ogg', '624567140fecc40163fed3c45a959a7c514d59b92b2232.25700064', 'video'),
(15, 14, 'PROGIN.swf', '8f9091ab452b3613a295459b9ce0738b514d59b9369312.77473039', 'file'),
(16, 15, 'math.swf', 'da08a1d5b8f673d4ceaad3b9ef9a17de514d5b43318729.48617551', 'file'),
(17, 15, 'moreless.swf', 'ea9416ea03a73c0167c0e4a0063d9c14514d5b433b4565.80162445', 'file'),
(18, 18, 'jpg', 'jpg-1163974538', 'image'),
(19, 19, 'jpg', 'jpg-1517830044', 'image'),
(21, 21, 'jpg', 'jpg-918132700', 'image');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `last_mod` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `nama`, `last_mod`) VALUES
(1, 'Elektro', '2013-04-10 14:27:04'),
(2, 'Telekomunikasi', '2013-04-10 14:27:04'),
(3, 'Power', '2013-04-10 14:27:23'),
(4, 'Sistem dan Teknologi informasi', '2013-04-10 14:27:23'),
(5, 'Matematika', '2013-04-10 14:27:43'),
(6, 'Fisika', '2013-04-10 14:27:43'),
(10, 'Progin', '2013-03-23 07:28:18'),
(11, 'Intelegensi Buatan', '2013-03-23 07:31:26'),
(12, 'Kuliah Labdas 3', '2013-04-11 10:39:34');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_tugas` int(11) NOT NULL,
  `user` varchar(32) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comments_ibfk_1` (`id_tugas`),
  KEY `comments_ibfk_2` (`user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=69 ;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `id_tugas`, `user`, `time`, `content`) VALUES
(58, 14, 'edwardsp', '2013-03-23 07:29:23', 'ini terlalu gampang'),
(60, 15, 'wIlson', '2013-03-23 07:36:37', 'iya ni, gmn dong<br>'),
(61, 15, 'edwardsp', '2013-03-23 07:46:22', 'kok gw ga assignee?'),
(63, 14, 'edwardsp', '2013-04-11 03:45:33', 'saya, Edward Samuel, merasa terhina dapat tugas macem ginian'),
(68, 18, 'proginner', '2013-04-13 04:02:47', 'Selamat pagi');

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
(10, 'edwardsp'),
(11, 'edwardsp'),
(12, 'felixt'),
(11, 'wIlson');

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE IF NOT EXISTS `tags` (
  `id_tugas` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(30) NOT NULL,
  PRIMARY KEY (`id_tugas`,`tag`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`id_tugas`, `tag`) VALUES
(14, 'asal'),
(14, 'tubes'),
(15, 'Peer'),
(15, 'Tracker'),
(18, 'gampang'),
(18, 'tubes1'),
(19, 'gampang'),
(19, 'tubes4'),
(21, 'deadliner'),
(21, 'gampang'),
(21, 'smt6'),
(21, 'weka'),
(23, 'algo');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `tugas`
--

INSERT INTO `tugas` (`id`, `nama`, `tgl_deadline`, `status`, `last_mod`, `pemilik`, `id_kategori`) VALUES
(14, 'PHP AJAX', '2013-03-24', 0, '2013-04-13 04:44:28', 'edwardsp', 10),
(15, 'GunBond', '2013-03-24', 1, '2013-04-10 16:38:51', 'wIlson', 11),
(18, 'HTML', '2013-04-13', 0, '2013-04-13 04:44:19', 'edwardsp', 10),
(19, 'Google App Engine', '2013-04-01', 0, '2013-04-11 10:36:15', 'edwardsp', 10),
(21, 'Data Mining', '2013-04-01', 1, '2013-04-11 10:27:05', 'edwardsp', 11),
(23, 'Naive Bayes', '2013-04-22', 0, '2013-04-13 04:44:29', 'edwardsp', 11);

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
('edwardsp', 'edward_samuel_esp@yahoo.com', '9f2788a951100afe63326ea54ce835ce', 'Edward Samuel Pasaribu', '1992-03-12', 'edwardsp.jpg'),
('felixt', 'felixterahadi@yahoo.com', '9f2788a951100afe63326ea54ce835ce', 'Felix Terahadi', '1990-03-12', 'felixt.jpg'),
('proginner', 'proginer@progin.com', 'a2d10a3211b415832791a6bc6031f9ab', 'progin beginner', '2013-03-21', 'proginner.jpg'),
('wIlson', 'wilsonfonda@yahoo.com', 'a2d10a3211b415832791a6bc6031f9ab', 'Wilson Fonda', '2013-03-12', 'wIlson.jpg');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assignees`
--
ALTER TABLE `assignees`
  ADD CONSTRAINT `assignees_ibfk_3` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `assignees_ibfk_4` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `attachments`
--
ALTER TABLE `attachments`
  ADD CONSTRAINT `attachments_ibfk_2` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_3` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comments_ibfk_4` FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

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
  ADD CONSTRAINT `tags_ibfk_2` FOREIGN KEY (`id_tugas`) REFERENCES `tugas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tugas`
--
ALTER TABLE `tugas`
  ADD CONSTRAINT `tugas_ibfk_3` FOREIGN KEY (`pemilik`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tugas_ibfk_4` FOREIGN KEY (`id_kategori`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
