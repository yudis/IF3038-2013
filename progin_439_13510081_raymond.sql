-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2013 at 11:50 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_439_13510081`
--

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `IDKategori` int(3) NOT NULL AUTO_INCREMENT,
  `judul` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`IDKategori`),
  KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`IDKategori`, `judul`, `username`) VALUES
(1, 'ai', 'doraemon'),
(2, 'progin', 'devin'),
(3, 'kripto', 'raymond'),
(4, 'IMK', 'yuli');

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `IDTask` int(3) NOT NULL,
  `IDKomentar` int(3) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `isi` text NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`IDKomentar`),
  KEY `IDTask` (`IDTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `pelampiran`
--

CREATE TABLE IF NOT EXISTS `pelampiran` (
  `IDTugas` int(3) NOT NULL AUTO_INCREMENT,
  `lampiran` varchar(256) NOT NULL,
  KEY `IDTugas` (`IDTugas`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=49 ;

--
-- Dumping data for table `pelampiran`
--

INSERT INTO `pelampiran` (`IDTugas`, `lampiran`) VALUES
(2, 'datetimepicker_css.js'),
(32, 'registry.xml'),
(33, 'registry.xml'),
(33, 'Code Gym 2.pptx'),
(33, '.project'),
(34, 'registry.xml'),
(34, 'Code Gym 2.pptx'),
(34, '.project'),
(35, 'commons-io-2.4-javadoc.jar'),
(35, 'commons-io-2.4.jar'),
(36, '02-secondhand_serenade-fall_for_you.mp3'),
(36, '09-secondhand_serenade-stay_close_dont_go.mp3'),
(37, '02-secondhand_serenade-fall_for_you.mp3'),
(37, '09-secondhand_serenade-stay_close_dont_go.mp3'),
(43, 'helper.exe'),
(44, 'helper.exe'),
(45, 'helper.exe'),
(46, 'helper.exe'),
(47, 'helper.exe'),
(48, 'movie.ogg');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE IF NOT EXISTS `pengguna` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `avatar` varchar(256) NOT NULL DEFAULT 'avatar/0.jpg' COMMENT 'Ukuran Maks. 256 kB',
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`username`, `password`, `fullname`, `birthday`, `email`, `avatar`) VALUES
('avatar', 'pppppppp', 'po dfpsao', '2013-04-24', 'rfds@kjfds.codsf', 'avatar/0.jpg'),
('devin', 'devin', 'devin hoesen', '2013-03-11', 'devin@hotmail.com', 'avatar/0.jpg'),
('doraemon', 'doraemon', 'doraemon', '2013-03-19', 'doraemon@dorem.com', 'avatar/0.jpg'),
('jhjkjkjkjmmj', '1234567890', 'fc ggg', '2013-04-10', 'de@hj.ss', 'avatar/0.jpg'),
('popopo', 'aaaaaaaa', 'dfsafds fdsa', '2013-04-17', 'raymond_pluto@Hotmail.com', 'avatar/0.jpg'),
('raymond', 'raymond', 'raymond lukanta', '2013-03-10', 'raymond@hotmail.com', 'avatar/images.jpg'),
('yuli', 'yuli', 'yulianti oenang', '2013-03-19', 'yuli@hotmail.com', 'avatar/0.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `penugasan`
--

CREATE TABLE IF NOT EXISTS `penugasan` (
  `username` varchar(30) NOT NULL,
  `IDTask` int(3) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`username`,`IDTask`),
  KEY `IDTask` (`IDTask`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=49 ;

--
-- Dumping data for table `penugasan`
--

INSERT INTO `penugasan` (`username`, `IDTask`) VALUES
('raymond', 34),
('raymond', 35),
('raymond', 36),
('devin', 37),
('raymond', 37),
('yuli', 37),
('devin', 44),
('RAYMOND', 44),
('devin', 45),
('RAYMOND', 45),
('devin', 46),
('RAYMOND', 46),
('devin', 47),
('RAYMOND', 47),
('raymond', 48);

-- --------------------------------------------------------

--
-- Table structure for table `tugas`
--

CREATE TABLE IF NOT EXISTS `tugas` (
  `IDTask` int(3) NOT NULL AUTO_INCREMENT,
  `IDKategori` int(3) NOT NULL,
  `name` varchar(30) NOT NULL,
  `deadline` date NOT NULL,
  `stat` tinyint(1) NOT NULL DEFAULT '0',
  `tag` varchar(256) DEFAULT NULL,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`IDTask`),
  KEY `IDKategori` (`IDKategori`),
  KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=49 ;

--
-- Dumping data for table `tugas`
--

INSERT INTO `tugas` (`IDTask`, `IDKategori`, `name`, `deadline`, `stat`, `tag`, `username`) VALUES
(1, 2, 'ajax', '2013-03-12', 1, 'selesai', 'yuli'),
(2, 2, 'ajax', '2013-03-12', 1, 'selesai', 'yuli'),
(3, 1, 'wer', '2013-04-24', 0, 'a', 'yuli'),
(4, 1, 'wer', '2013-04-24', 0, 'a', 'yuli'),
(5, 2, 'php', '2013-03-14', 1, 'terbaru', 'raymond'),
(6, 2, 'UI', '2013-03-19', 0, 'sulit', 'devin'),
(7, 2, 'UI', '2013-03-19', 0, 'sulit', 'devin'),
(8, 2, 'UI', '2013-03-19', 0, 'sulit', 'devin'),
(9, 2, 'ajax', '2013-03-12', 1, 'selesai', 'yuli'),
(10, 1, 'GA', '2013-03-05', 1, 'menarik', 'devin'),
(11, 1, 'GA', '2013-03-05', 1, 'menarik', 'devin'),
(12, 1, 'GA', '2013-03-05', 1, 'menarik', 'devin'),
(13, 1, 'GA', '2013-03-05', 1, 'menarik', 'devin'),
(14, 1, 'GA', '2013-03-05', 1, 'menarik', 'devin'),
(15, 1, 'wer', '2013-04-24', 0, 'a', 'yuli'),
(16, 1, 'a', '2013-04-24', 1, 'a', 'RAYMOND'),
(17, 1, 'wer', '2013-04-24', 0, 'a', 'yuli'),
(18, 1, 'dd', '2013-04-24', 0, 'a', 'devin'),
(19, 1, 'ddd', '2013-04-24', 0, 'a', 'devin'),
(20, 1, 'ddd', '2013-04-24', 0, 'a', 'devin'),
(21, 1, 'dddd', '2013-04-24', 0, 'a', 'devin'),
(22, 1, 'dddd', '2013-04-24', 0, 'a', 'yuli'),
(23, 1, 'dss', '2013-04-24', 0, 'a', 'yuli'),
(24, 1, 'fsa', '2013-04-24', 0, 'a', 'yuli'),
(25, 1, 'wer', '2013-04-24', 0, 'a', 'yuli'),
(26, 1, 'a', '2013-04-09', 0, 'aku, dan, kau', 'RAYMOND'),
(27, 1, 'a', '2013-04-09', 0, 'aku, dan, kau', 'RAYMOND'),
(28, 1, 'a', '2013-04-09', 0, 'aku, dan, kau', 'RAYMOND'),
(29, 1, 'a', '2013-04-09', 0, 'aku, dan, kau', 'RAYMOND'),
(30, 1, 'a', '2013-04-09', 0, 'aku, dan, kau', 'RAYMOND'),
(31, 1, 'a', '2013-04-09', 1, 'aku, dan, kau', 'RAYMOND'),
(32, 1, 'a', '2013-04-09', 1, 'aku, dan, kau', 'RAYMOND'),
(33, 1, 'aku', '2013-04-09', 1, 'aku, dan, kau', 'RAYMOND'),
(34, 1, 'kiki', '2013-04-09', 0, 'aku, dan, kau', 'RAYMOND'),
(35, 1, 'amah', '2013-04-09', 1, 'lala, lala', 'RAYMOND'),
(36, 1, 'momo', '2013-04-17', 0, 'momo, mau, makan', 'RAYMOND'),
(37, 1, 'mom', '2013-04-17', 0, 'momo, mau, makan', 'RAYMOND'),
(43, 1, 'komo', '2013-04-17', 0, 'aku, mau, makan', 'RAYMOND'),
(44, 1, 'komo', '2013-04-17', 0, 'aku, mau, makan', 'RAYMOND'),
(45, 1, 'komoh', '2013-04-17', 0, 'aku, mau, makan', 'RAYMOND'),
(46, 1, 'kimi', '2013-04-17', 0, 'aku, mau, makan', 'RAYMOND'),
(47, 1, 'kim', '2013-04-17', 0, 'aku, mau, makan', 'RAYMOND'),
(48, 1, 'pipi', '2013-04-30', 0, 'rfs', 'RAYMOND');

-- --------------------------------------------------------

--
-- Table structure for table `usercateg`
--

CREATE TABLE IF NOT EXISTS `usercateg` (
  `IDKategori` int(3) NOT NULL,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`IDKategori`,`username`),
  KEY `usercateg_ibfk_2` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kategori`
--
ALTER TABLE `kategori`
  ADD CONSTRAINT `kategori_ibfk_1` FOREIGN KEY (`username`) REFERENCES `pengguna` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `komentar_ibfk_1` FOREIGN KEY (`IDTask`) REFERENCES `tugas` (`IDTask`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pelampiran`
--
ALTER TABLE `pelampiran`
  ADD CONSTRAINT `pelampiran_ibfk_1` FOREIGN KEY (`IDTugas`) REFERENCES `tugas` (`IDTask`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `penugasan`
--
ALTER TABLE `penugasan`
  ADD CONSTRAINT `penugasan_ibfk_1` FOREIGN KEY (`username`) REFERENCES `pengguna` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `penugasan_ibfk_2` FOREIGN KEY (`IDTask`) REFERENCES `tugas` (`IDTask`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tugas`
--
ALTER TABLE `tugas`
  ADD CONSTRAINT `tugas_ibfk_1` FOREIGN KEY (`IDKategori`) REFERENCES `kategori` (`IDKategori`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tugas_ibfk_2` FOREIGN KEY (`username`) REFERENCES `pengguna` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `usercateg`
--
ALTER TABLE `usercateg`
  ADD CONSTRAINT `usercateg_ibfk_1` FOREIGN KEY (`IDKategori`) REFERENCES `kategori` (`IDKategori`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usercateg_ibfk_2` FOREIGN KEY (`username`) REFERENCES `pengguna` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
