-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 23, 2013 at 05:09 AM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

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
  PRIMARY KEY (`id_user`,`id_task`),
  KEY `id_task` (`id_task`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id_komentar` int(10) NOT NULL AUTO_INCREMENT,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `komentar` text NOT NULL,
  `id_user` int(10) NOT NULL,
  `id_task` int(10) NOT NULL,
  PRIMARY KEY (`id_komentar`),
  KEY `id_user` (`id_user`),
  KEY `id_task` (`id_task`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=70 ;

-- --------------------------------------------------------

--
-- Table structure for table `edit_kategori`
--

CREATE TABLE IF NOT EXISTS `edit_kategori` (
  `id_user` int(11) NOT NULL,
  `id_kategori` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_kategori`),
  KEY `id_katego` (`id_kategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `edit_kategori`
--

INSERT INTO `edit_kategori` (`id_user`, `id_kategori`) VALUES
(3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `have_tags`
--

CREATE TABLE IF NOT EXISTS `have_tags` (
  `id_task` int(10) NOT NULL,
  `id_tag` int(10) NOT NULL,
  PRIMARY KEY (`id_task`,`id_tag`),
  KEY `id_tag` (`id_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `have_tags`
--

INSERT INTO `have_tags` (`id_task`, `id_tag`) VALUES
(1, 1),
(1, 11);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `id_kategori` int(10) NOT NULL AUTO_INCREMENT,
  `nama_kategori` varchar(100) NOT NULL,
  `id_user` int(10) NOT NULL,
  PRIMARY KEY (`id_kategori`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`, `id_user`) VALUES
(1, 'IF 2034', 2),
(5, 'tesgaya', 3),
(9, 'tes', 4);

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `id_tag` int(10) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tag`),
  UNIQUE KEY `tag_name` (`tag_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`id_tag`, `tag_name`) VALUES
(3, 'adf'),
(1, 'basdat'),
(4, 'fd'),
(5, 'intelegensia buatan'),
(12, 'lol'),
(11, 'progiiin'),
(2, 'rpl'),
(13, 'tes'),
(14, 'tesdoang');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `id_task` int(10) NOT NULL AUTO_INCREMENT,
  `nama_task` varchar(50) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `deadline` date NOT NULL,
  `id_kategori` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  PRIMARY KEY (`id_task`),
  KEY `id_kategori` (`id_kategori`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id_task`, `nama_task`, `status`, `deadline`, `id_kategori`, `id_user`) VALUES
(1, 'Tugas 2', 1, '2013-03-14', 5, 2),
(4, 'Tugas 3', 0, '2013-03-14', 5, 2),
(5, 'Tugas 4', 0, '2013-03-14', 5, 2),
(6, 'Tugas 3', 0, '2013-03-15', 5, 4);

-- --------------------------------------------------------

--
-- Table structure for table `task_attachment`
--

CREATE TABLE IF NOT EXISTS `task_attachment` (
  `id_task` int(10) NOT NULL,
  `attachment` varchar(100) NOT NULL,
  PRIMARY KEY (`id_task`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `avatar` varchar(100) NOT NULL,
  `birthdate` date NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `email`, `fullname`, `avatar`, `birthdate`, `password`) VALUES
(2, 'asdfghjkl', 'adsf@yahoo.com', 'asdf ghjkl', '51BF9CCEC6439E231D13043486B6C50A.jpg', '2013-03-15', '25d55ad283aa400af464c76d713c07ad'),
(3, 'abrahamks', 'abrahamkrisnanda@outlook.com', 'abraham krisnanda', '4897178F18808820BA169AC2DFC3568A.JPG', '2013-03-15', '1441f19754335ca4638bfdf1aea00c6d'),
(4, 'admin', 'fernandojordan.92@gmail.com', 'Jordan Fernando', 'B18D024454540A7B459FEA497EFB9D05.jpg', '2013-03-20', 'f6fdffe48c908deb0f4c3bd36c032e72');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assign`
--
ALTER TABLE `assign`
  ADD CONSTRAINT `assign_ibfk_3` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `assign_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`);

--
-- Constraints for table `edit_kategori`
--
ALTER TABLE `edit_kategori`
  ADD CONSTRAINT `edit_kategori_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `edit_kategori_ibfk_2` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`);

--
-- Constraints for table `have_tags`
--
ALTER TABLE `have_tags`
  ADD CONSTRAINT `have_tags_ibfk_1` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`),
  ADD CONSTRAINT `have_tags_ibfk_2` FOREIGN KEY (`id_tag`) REFERENCES `tag` (`id_tag`);

--
-- Constraints for table `kategori`
--
ALTER TABLE `kategori`
  ADD CONSTRAINT `kategori_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `task_ibfk_3` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `task_attachment`
--
ALTER TABLE `task_attachment`
  ADD CONSTRAINT `task_attachment_ibfk_1` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
