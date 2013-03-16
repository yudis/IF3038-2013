-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 16, 2013 at 05:55 PM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510033`
--

-- --------------------------------------------------------

--
-- Table structure for table `assign`
--

CREATE TABLE IF NOT EXISTS `assign` (
  `id_user` int(10) NOT NULL,
  `id_task` int(10) NOT NULL,
  KEY `id_task` (`id_task`),
  KEY `id_user` (`id_user`,`id_task`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id_komentar` int(10) NOT NULL,
  `timestamp` datetime NOT NULL,
  `komentar` text NOT NULL,
  `id_user` int(10) NOT NULL,
  `id_task` int(10) NOT NULL,
  PRIMARY KEY (`id_komentar`),
  UNIQUE KEY `id_user` (`id_user`),
  UNIQUE KEY `id_task` (`id_task`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id_komentar`, `timestamp`, `komentar`, `id_user`, `id_task`) VALUES
(1, '2013-03-15 10:43:22', 'wah tugasnya lumayan nih', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `edit_kategori`
--

CREATE TABLE IF NOT EXISTS `edit_kategori` (
  `id_user` int(11) NOT NULL,
  `id_katego` int(11) NOT NULL,
  UNIQUE KEY `id_user` (`id_user`),
  KEY `id_katego` (`id_katego`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `have_tags`
--

CREATE TABLE IF NOT EXISTS `have_tags` (
  `id_task` int(10) NOT NULL,
  `id_tag` int(10) NOT NULL,
  KEY `id_tag` (`id_tag`),
  KEY `id_task` (`id_task`,`id_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `have_task`
--

CREATE TABLE IF NOT EXISTS `have_task` (
  `id_user` int(10) NOT NULL,
  `id_task` int(10) NOT NULL,
  KEY `id_task` (`id_task`),
  KEY `id_user` (`id_user`,`id_task`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `have_task`
--

INSERT INTO `have_task` (`id_user`, `id_task`) VALUES
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `id_kategori` int(10) NOT NULL,
  `nama_kategori` varchar(100) NOT NULL,
  `id_user` int(10) NOT NULL,
  PRIMARY KEY (`id_kategori`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`, `id_user`) VALUES
(1, 'IF3038 - Pemrograman Internet', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `id_tag` int(10) NOT NULL,
  `tag_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tag`),
  UNIQUE KEY `tag_name` (`tag_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `id_task` int(10) NOT NULL,
  `nama_task` varchar(50) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deadline` date NOT NULL,
  `id_kategori` int(10) NOT NULL,
  PRIMARY KEY (`id_task`),
  KEY `id_kategori` (`id_kategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id_task`, `nama_task`, `status`, `deadline`, `id_kategori`) VALUES
(2, 'Tugas2_PHP_Ajax', 0, '2013-03-23', 1);

-- --------------------------------------------------------

--
-- Table structure for table `task_attachment`
--

CREATE TABLE IF NOT EXISTS `task_attachment` (
  `id_task` int(10) NOT NULL,
  `attachment` varchar(100) NOT NULL,
  KEY `id_task` (`id_task`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `e-mail` varchar(50) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `avatar` varchar(100) NOT NULL,
  `birthdate` date NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `e-mail`, `fullname`, `avatar`, `birthdate`) VALUES
(1, 'abrahamks', 'abraham.k@students.itb.ac.id', 'Abraham Krisnanda', 'C:/asdf', '2013-02-08'),
(2, 'admin', 'admin@yahoo.com', 'administrator ku', 'C:/asdf', '2013-03-01');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assign`
--
ALTER TABLE `assign`
  ADD CONSTRAINT `assign_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `assign_ibfk_2` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`),
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `edit_kategori`
--
ALTER TABLE `edit_kategori`
  ADD CONSTRAINT `edit_kategori_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `edit_kategori_ibfk_2` FOREIGN KEY (`id_katego`) REFERENCES `kategori` (`id_kategori`);

--
-- Constraints for table `have_tags`
--
ALTER TABLE `have_tags`
  ADD CONSTRAINT `have_tags_ibfk_1` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`),
  ADD CONSTRAINT `have_tags_ibfk_2` FOREIGN KEY (`id_tag`) REFERENCES `tag` (`id_tag`);

--
-- Constraints for table `have_task`
--
ALTER TABLE `have_task`
  ADD CONSTRAINT `have_task_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `have_task_ibfk_2` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`);

--
-- Constraints for table `kategori`
--
ALTER TABLE `kategori`
  ADD CONSTRAINT `kategori_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`);

--
-- Constraints for table `task_attachment`
--
ALTER TABLE `task_attachment`
  ADD CONSTRAINT `task_attachment_ibfk_1` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
