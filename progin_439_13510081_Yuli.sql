-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 13, 2013 at 03:13 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

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
(1, 'apapun', 'doraemon'),
(2, 'kalkulus', 'devin'),
(3, 'kamu', 'raymond'),
(4, 'putih', 'yuli'),

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=131 ;

--
-- Dumping data for table `komentar`
--

INSERT INTO `komentar` (`IDTask`, `IDKomentar`, `username`, `isi`, `waktu`) VALUES
(1, 1, 'devin', 'aaa', '2013-04-12 02:18:16'),
(1, 2, 'devin', 'komentar', '2013-04-10 01:27:38'),
(1, 5, 'raymond', 'coba komen', '2013-04-11 01:27:38'),
(2, 3, 'raymond', 'mmm', '0000-00-00 00:00:00'),
(2, 4, 'raymond', 'zxcvbnm,.', '0000-00-00 00:00:00'),
(1, 25, 'yuli', 'd', '2013-04-12 01:27:38'),
(1, 26, 'yuli', 'ddee', '2013-04-12 01:27:48'),
(1, 27, 'yuli', 'iii', '2013-04-12 01:27:57'),
(1, 31, 'yuli', 'iiii', '2013-04-10 07:31:44'),
(1, 32, 'yuli', 'fbsdfg', '2013-04-10 07:33:22'),
(1, 33, 'yuli', 'fasdfasdfsa', '2013-04-10 07:34:42'),
(1, 34, 'yuli', 'dadfasdfadsfasd', '2013-04-10 07:38:04'),
(2, 73, 'yuli', 'aaa', '2013-04-11 05:26:12'),
(1, 76, 'yuli', 'hhhh', '2013-04-12 03:12:10'),
(1, 77, 'yuli', 'mmm', '2013-04-12 03:15:33'),
(1, 100, 'yuli', 'wiii', '2013-04-12 03:56:56'),
(1, 101, 'yuli', 'waa', '2013-04-12 03:57:06'),
(1, 102, 'yuli', 'saya', '2013-04-12 03:57:12'),
(1, 103, 'yuli', 'gw', '2013-04-12 03:57:21'),
(1, 104, 'yuli', 'lu', '2013-04-12 03:58:12'),
(1, 105, 'yuli', 'mmm', '2013-04-12 03:58:24'),
(1, 106, 'yuli', 'sayyy', '2013-04-12 03:58:35'),
(1, 107, 'yuli', 'aaaaaioooo', '2013-04-12 03:59:42'),
(1, 108, 'yuli', 'yullll', '2013-04-12 03:59:52'),
(1, 129, 'yuli', 'aa3', '2013-04-13 00:04:24'),
(1, 130, 'yuli', 'ii', '2013-04-13 00:04:57');

-- --------------------------------------------------------

--
-- Table structure for table `pelampiran`
--

CREATE TABLE IF NOT EXISTS `pelampiran` (
  `IDTugas` int(3) NOT NULL AUTO_INCREMENT,
  `lampiran` varchar(256) NOT NULL,
  KEY `IDTugas` (`IDTugas`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `pelampiran`
--

INSERT INTO `pelampiran` (`IDTugas`, `lampiran`) VALUES
(1, 'upload/datetimepicker_css.js'),
(1, 'upload/Koala.jpg'),
(1, 'upload/movie.ogg');
(3, 'upload/movie.ogg'),
(4, 'upload/Koala.jpg'),
(5, 'upload/datetimepicker_css.js'),
(2, 'upload/Koala.jpg'),
(8, 'upload/datetimepicker_css.js'),


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
('amelia', 'amelia', 'amelia anasthasia', '1992-12-12', 'amel@hotmail.com', 'avatar/foto.jpg'),
('anasthasia', 'anasthasia', 'anasthasia amelia', '1992-12-12', 'anasthasia@hotmail.com', 'avatar/Koala.jpg'),
('devin', 'devin', 'devin hoesen', '2013-03-11', 'devin@hotmail.com', 'avatar/2.jpg'),
('doraemon', 'doraemon', 'doraemon', '2013-03-19', 'doraemon@dorem.com', 'avatar/0.jpg'),
('lukanta', 'lukanta', 'lukanta raymond', '1992-12-12', 'lukanta@hotmail.com', 'avatar/0.jpg'),
('oenang', 'oenang', 'oenang yulianti', '1992-12-12', 'oenang@hotmail.com', 'avatar/0.jpg'),
('raymond', 'raymond', 'raymond lukanta', '2013-03-05', 'raymond@hotmail.com', 'avatar/0.jpg'),
('yuli', 'yuli', 'Yulianti Oenang', '2013-03-07', 'yuli@hotmail.com', 'avatar/3.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `penugasan`
--

CREATE TABLE IF NOT EXISTS `penugasan` (
  `username` varchar(30) NOT NULL,
  `IDTask` int(3) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`username`,`IDTask`),
  KEY `IDTask` (`IDTask`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `penugasan`
--

INSERT INTO `penugasan` (`username`, `IDTask`) VALUES
('devin', 1),
('yuli', 1),
('doraemon', 2),
('raymond', 2),
('devin', 3),
('raymond', 3),
('yuli', 3),
('devin', 4),
('raymond', 4),
('yuli', 4),
('devin', 5),
('raymond', 5),
('yuli', 5),
('devin', 6),
('raymond', 6),
('yuli', 6),
('amelia', 7),
('lukanta', 7),
('oenang', 7),
('doraemon', 5),
('raymond', 8);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `tugas`
--

INSERT INTO `tugas` (`IDTask`, `IDKategori`, `name`, `deadline`, `stat`, `tag`, `username`) VALUES
(1, 1, 'tugas 1', '2013-04-30', 1, 'oo', 'devin'),
(2, 2, 'tugas 2', '2013-04-30', 0, 'oo2', 'devin'),
(3, 3, 'Tubes 1 Progin', '2012-12-12', 0, 'susah, ribet', 'devin'),
(4, 3, 'Tubes 2 Progin', '2012-12-12', 1, 'susah, ribet', 'raymond'),
(5, 3, 'Tubes 3 Progin', '2012-12-12', 0, 'susah, ribet', 'yuli'),
(6, 4, 'Tubes 1 AI', '2012-12-12', 0, 'susah, ribet', 'devin'),
(7, 4, 'Tubes 2 AI', '2012-12-12', 0, 'susah, ribet', 'raymond'),
(8, 1, 'Tubes 3 AI', '2012-12-12', 0, 'susah, ribet', 'yuli');

-- --------------------------------------------------------

--
-- Table structure for table `usercateg`
--

CREATE TABLE IF NOT EXISTS `usercateg` (
  `IDKategori` int(3) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`IDKategori`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `usercateg`
--

INSERT INTO `usercateg` (`IDKategori`, `username`) VALUES
(6, 'amelia'),
(6, 'anasthasia'),
(2, 'devin'),
(5, 'devin'),
(6, 'devin'),
(1, 'doraemon'),
(6, 'oenang'),
(3, 'raymond'),
(5, 'raymond'),
(6, 'raymond'),
(7, 'raymond'),
(4, 'yuli'),
(5, 'yuli'),
(6, 'yuli'),
(7, 'yuli');
(1, 'yuli');

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

ALTER TABLE `usercateg`
  ADD CONSTRAINT `usercateg_ibfk_1` FOREIGN KEY (`IDKategori`) REFERENCES `kategori` (`IDKategori`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usercateg_ibfk_2` FOREIGN KEY (`username`) REFERENCES `pengguna` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
