-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 13, 2013 at 09:37 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510074`
--

-- --------------------------------------------------------

--
-- Table structure for table `asigner`
--

CREATE TABLE IF NOT EXISTS `asigner` (
  `username` varchar(15) NOT NULL,
  `namatugas` varchar(30) NOT NULL,
  `asignee` varchar(50) NOT NULL,
  KEY `username` (`username`,`namatugas`),
  KEY `namatugas` (`namatugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `asigner`
--

INSERT INTO `asigner` (`username`, `namatugas`, `asignee`) VALUES
('dummy', 'komen', 'saya'),
('dummy', 'aaaaa', 'saya'),
('dummy', 'satu', 'aaaa'),
('dummy1', 'aaaa', 'asda'),
('dummy1', 'sadasd', 'sad'),
('dummy1', 'safa', 'dfs'),
('dummy1', 'asdasdasd', 'adas'),
('dummy1', 'testestes', 'asdaasd'),
('dummy', 'testestes', 'asdaasd'),
('dummy1', 'aaaaaaa', 'asdaad'),
('dummy1', 'tiga', 'asdaad'),
('dummy1', 'empat', 'aasda');

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `username` varchar(15) NOT NULL,
  `namatugas` varchar(30) NOT NULL,
  `namafile` varchar(30) NOT NULL,
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
  `komentar` varchar(160) NOT NULL,
  `penulis` varchar(15) NOT NULL,
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
  `namaavatar` varchar(50) NOT NULL,
  UNIQUE KEY `email` (`email`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profil`
--

INSERT INTO `profil` (`username`, `namalengkap`, `tanggallahir`, `email`, `namaavatar`) VALUES
('akuakuaku', 'asd sadsad', '1971-09-18', 'ada@gsdf.dsf', ''),
('zzzzzzzz', 'as asfasf', '1971-10-18', 'asd@as.fs', ''),
('eeeee', 'askf asdsa', '1972-10-19', 'asf@dfds.dfds', ''),
('kakakaka', 'he he', '1956-02-02', 'sdf@dsfjkh.asdf', '');

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
('dummy', 'komen', 'dada'),
('dummy', 'aaaaa', 'tes'),
('dummy', 'satu', 'asdsa'),
('dummy1', 'aaaa', 'asdsad'),
('dummy1', 'sadasd', 'asdsad'),
('dummy1', 'safa', 'sf'),
('dummy1', 'asdasdasd', 'adasd'),
('dummy1', 'testestes', 'asdsad'),
('dummy', 'testestes', 'asdsad'),
('dummy1', 'aaaaaaa', 'asdaaaaaaa'),
('dummy1', 'tiga', 'asdaaaaaaa'),
('dummy1', 'empat', 'adsad');

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
('dummy', 'komen', '2015-05-05', 'dummy', 0),
('dummy', 'aaaaa', '2013-03-03', 'dummy', 0),
('dummy', 'satu', '2013-02-02', 'dummy', 0),
('dummy1', 'aaaa', '2014-04-04', 'dummy', 0),
('dummy1', 'sadasd', '2013-02-04', 'dummy', 0),
('dummy1', 'safa', '2013-04-04', 'dummy', 0),
('dummy1', 'asdasdasd', '2013-03-04', 'dummy', 0),
('dummy1', 'testestes', '2014-03-03', 'dummy', 0),
('dummy1', 'aaaaaaa', '2013-02-02', 'dummy', 0),
('dummy1', 'tiga', '2014-04-04', 'dummy', 0),
('dummy1', 'empat', '2013-03-03', 'dummy', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL,
  `namalengkap` varchar(50) NOT NULL,
  `tanggallahir` date NOT NULL,
  `email` varchar(20) NOT NULL,
  `avatar` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `namalengkap`, `tanggallahir`, `email`, `avatar`) VALUES
('akuakuaku', 'aaaaaaaa', '', '0000-00-00', '', ''),
('dummy', 'aaaaaaaa', '', '0000-00-00', '', ''),
('dummy1', 'aaaaaaaa', 'asd asd', '1993-01-19', 'ads@dfa.asda', 'testdir'),
('eeeee', 'aaaaaaaa', '', '0000-00-00', '', ''),
('kakakaka', 'hehehehe', '', '0000-00-00', '', ''),
('samboraonit', 'hahahaha', 'kayden kross', '2014-03-01', 'kkk@kkk.com', 'testdir'),
('wachid', 'aaaaaaaa', 'sadasfasd as safagdsdsf', '1974-02-14', 'ads@dfa.asda', 'testdir'),
('zzzzzzzz', 'aaaaaaaa', '', '0000-00-00', '', '');

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
