-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 13, 2013 at 04:50 PM
-- Server version: 5.1.37
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE IF NOT EXISTS `accounts` (
  `idaccounts` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `nama_lengkap` varchar(30) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idaccounts`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`idaccounts`, `username`, `password`, `email`, `nama_lengkap`, `tgl_lahir`, `avatar`) VALUES
(1, 'panda', 'ce61649168c4550c2f7acab92354dc6e', 'anpandumail@gmail.com', 'pandu', '1992-10-11', 'pict/pandaa.jpg'),
(2, 'pandu', 'pandu', 'anpandumail@gmail.com', 'pandu', '1992-10-11', 'pict/cute-panda.jpg'),
(3, 'pandi', 'pandi', 'anpandumail@gmail.com', 'pandi', '2013-03-21', 'pict/kunfu-panda.jpg'),
(4, 'anpandu', '22d7fe8c185003c98f97e5d6ced420c7', 'anpandu@ymail.com', 'asd zxc', '2013-03-03', 'upload/293341_263226573699052_261443193877390_902753_1681182897_n.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `accounts_has_tugas`
--

CREATE TABLE IF NOT EXISTS `accounts_has_tugas` (
  `accounts_idaccounts` int(11) NOT NULL,
  `tugas_idtugas` int(11) NOT NULL,
  `pembuat` tinyint(1) DEFAULT NULL,
  KEY `fk_accounts_has_tugas_tugas1` (`tugas_idtugas`),
  KEY `fk_accounts_has_tugas_accounts1` (`accounts_idaccounts`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts_has_tugas`
--

INSERT INTO `accounts_has_tugas` (`accounts_idaccounts`, `tugas_idtugas`, `pembuat`) VALUES
(1, 2, 0),
(4, 3, 1),
(3, 3, 0),
(1, 5, 0),
(1, 6, 0),
(3, 6, 0),
(4, 6, 0),
(4, 1, 0),
(1, 1, 0),
(2, 67, 0),
(2, 68, 0),
(2, 69, 0),
(2, 70, 0),
(2, 71, 0),
(2, 72, 0),
(2, 74, 0),
(2, 74, 0),
(2, 75, 0),
(2, 76, 0),
(2, 77, 0),
(2, 78, 0),
(2, 79, 0),
(2, 80, 0),
(2, 81, 0),
(2, 82, 0),
(2, 83, 0),
(2, 84, 0),
(2, 85, 0),
(2, 86, 0),
(2, 87, 0),
(2, 88, 0),
(2, 89, 0);

-- --------------------------------------------------------

--
-- Table structure for table `assignee_has_kategori`
--

CREATE TABLE IF NOT EXISTS `assignee_has_kategori` (
  `accounts_idaccounts` int(11) NOT NULL,
  `kategori_idkategori` int(11) NOT NULL,
  KEY `fk_accounts_has_kategori_kategori1` (`kategori_idkategori`),
  KEY `fk_accounts_has_kategori_accounts1` (`accounts_idaccounts`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignee_has_kategori`
--

INSERT INTO `assignee_has_kategori` (`accounts_idaccounts`, `kategori_idkategori`) VALUES
(4, 3),
(4, 3),
(1, 3),
(4, 4),
(4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `idtable1` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(45) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `tugas_idtugas` int(11) NOT NULL,
  PRIMARY KEY (`idtable1`),
  KEY `fk_attachment_tugas1` (`tugas_idtugas`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=38 ;

--
-- Dumping data for table `attachment`
--

INSERT INTO `attachment` (`idtable1`, `path`, `nama`, `tugas_idtugas`) VALUES
(1, 'attachment/Pemrograman Master-f8b58ec0/', 'weird-funny-gif-picard-shuttle.gif', 1),
(2, 'attachment/Pemrograman Master-f8b58ec0/', '293341_263226573699052_261443193877390_902753_1681182897_n.jpg', 1),
(3, 'attachment/Pemrograman Master-f8b58ec0/', 'baby laughing.mp4', 1),
(4, 'attachment/Pemrograman Master-f8b58ec0/', 'database.png', 1),
(5, 'attachment/a-01ef5c55/', 'database.png', 2),
(6, 'attachment/internet hacking-3a2bd3ba/', 'avatar1516022_15.gif', 3),
(7, 'attachment/progmaster-fece3373/', 'SAX ROLL - Epic Sax Guy Looped for 10 mins (+ mp3_wav).mp4', 5),
(8, 'attachment/progmaster-fece3373/', 'ZJadwal Kuliah IF-STI 2011 Semester 3.jpg', 5),
(9, 'attachment/internet hacking-c2ac32e1/', 'SAX ROLL - Epic Sax Guy Looped for 10 mins (+ mp3_wav).mp4', 6),
(10, 'attachment/internet hacking-c2ac32e1/', 'hearing1.jpg', 6),
(11, 'attachment/', 'hearing1.jpg', 77),
(12, 'attachment/tes-83125', 'url.jpg', 78),
(13, 'attachment/tes-270621', '', 79),
(14, 'attachment/tes-246070', '', 80),
(15, 'attachment/null-837982', '', 81),
(16, 'attachment/null-660755', '', 82),
(17, 'attachment/tes-221805', 'happy-derp.jpg', 83),
(18, 'attachment/tes-50791', 'happy-derp.jpg', 83),
(19, 'attachment/tes-80207', 'happy-derp.jpg', 83),
(20, 'attachment/tes-823590', 'happy-derp.jpg', 83),
(21, 'attachment/tes-827591', 'happy-derp.jpg', 83),
(22, 'attachment/tes2-280561', 'null', 84),
(23, 'attachment/tes2-280561', 'happy-derp.jpg', 84),
(24, 'attachment/tes2-280561', 'null', 84),
(25, 'attachment/tes2-280561', 'null', 84),
(26, 'attachment/tes2-280561', 'null', 84),
(27, 'attachment/tes-485049', 'happy-derp.jpg', 85),
(28, 'attachment/JavaServerPages-285964', '818102_15563142_lz.jpg', 86),
(29, 'attachment/JavaServerPages-57667', '', 87),
(30, 'attachment/JavaServerPages-57667', '', 87),
(31, 'attachment/JavaServerPages-57667', '', 87),
(32, 'attachment/JavaServerPages-88266', '0307016.jpg', 88),
(33, 'attachment/JavaServerPages-88266', '316628_285460648134968_100000131056562_1319514_585737016_n.jpg', 88),
(34, 'attachment/JavaServerPages-88266', '41GGHKYE4DL.jpg', 88),
(35, 'attachment/JavaServerPages-61149', '[8-bit] Blink 182 - anthem part 2.mp4', 89),
(36, 'attachment/JavaServerPages-61149', '316628_285460648134968_100000131056562_1319514_585737016_n.jpg', 89),
(37, 'attachment/JavaServerPages-61149', 'Tugas_SBP.pdf', 89);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `idkategori` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(30) DEFAULT NULL,
  `pembuat` int(11) NOT NULL,
  PRIMARY KEY (`idkategori`),
  KEY `fk_kategori_accounts1` (`pembuat`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`idkategori`, `nama`, `pembuat`) VALUES
(1, 'tubes', 1),
(2, 'tugas sangat besar', 4),
(3, 'tugas sangat kecil', 4),
(4, 'tugas lain', 4);

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `idkomentar` int(11) NOT NULL AUTO_INCREMENT,
  `isi` varchar(511) DEFAULT NULL,
  `tugas_idtugas` int(11) NOT NULL,
  `accounts_idaccounts` int(11) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  PRIMARY KEY (`idkomentar`),
  KEY `fk_komentar_tugas1` (`tugas_idtugas`),
  KEY `fk_komentar_accounts1` (`accounts_idaccounts`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Dumping data for table `komentar`
--

INSERT INTO `komentar` (`idkomentar`, `isi`, `tugas_idtugas`, `accounts_idaccounts`, `CREATED`) VALUES
(1, 'Halo semua', 1, 1, '2013-03-23 16:46:56'),
(2, 'berisik', 1, 2, '2013-03-23 16:47:27'),
(7, 'kau yang berisik', 1, 3, '2013-03-23 16:52:56'),
(32, 'komentar tes, bisa loh', 1, 4, '2013-03-23 22:41:20'),
(33, 'hai', 6, 1, '2013-04-04 14:11:58'),
(34, 'lagi', 6, 1, '2013-04-04 14:12:12'),
(35, 'a', 6, 1, '2013-04-04 14:12:25'),
(36, 'aaaa', 6, 1, '2013-04-04 14:12:27');

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `idtag` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idtag`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`idtag`, `nama`) VALUES
(1, 'tubes'),
(2, 'tucil'),
(3, 'tubesar'),
(4, 'inet'),
(5, 'asd'),
(6, 'lagu'),
(7, 'tucil,'),
(8, 'tucil,,'),
(9, 'tubes,'),
(10, ' tubes'),
(11, ' tucil'),
(12, '  tubes'),
(13, ' asd'),
(14, 'semuanya'),
(15, ' ');

-- --------------------------------------------------------

--
-- Table structure for table `tugas`
--

CREATE TABLE IF NOT EXISTS `tugas` (
  `idtugas` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(45) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `status_selesai` tinyint(1) DEFAULT NULL,
  `kategori_idkategori` int(11) NOT NULL,
  PRIMARY KEY (`idtugas`),
  KEY `fk_tugas_kategori1` (`kategori_idkategori`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=90 ;

--
-- Dumping data for table `tugas`
--

INSERT INTO `tugas` (`idtugas`, `nama`, `deadline`, `status_selesai`, `kategori_idkategori`) VALUES
(1, 'Pemrograman Master', '2013-03-05', 0, 1),
(2, 'a', '2013-03-12', 0, 1),
(3, 'internet hacking', '2013-03-01', 0, 3),
(5, 'progmaster', '2013-03-03', 0, 3),
(6, 'internet hacking', '2013-03-03', 0, 3),
(67, 'tes', '2013-03-12', 0, 1),
(68, 'tes', '2013-03-12', 0, 1),
(69, 'JavaServerPages', '2013-03-12', 0, 1),
(70, 'JavaServerPages2', '2013-03-03', 0, 1),
(71, 'JavaServerPages2', '2013-03-03', 0, 1),
(72, 'JavaServerPages2', '2013-03-03', 0, 1),
(73, 'asd', '2013-03-03', 0, 1),
(74, 'asd', '2013-03-03', 0, 1),
(75, 'asd', '2013-03-03', 0, 1),
(76, 'JavaServerPages2', '2013-03-03', 0, 1),
(77, 'JavaServerPages', '2013-03-03', 0, 1),
(78, 'tes', '2013-04-01', 0, 1),
(79, 'tes', '2013-04-01', 0, 1),
(80, 'tes', '2013-04-01', 0, 1),
(81, 'null', '2013-04-01', 0, 1),
(82, 'null', '2013-04-01', 0, 1),
(83, 'tes', '2013-04-01', 0, 1),
(84, 'tes2', '2013-04-01', 0, 1),
(85, 'tes', '2013-04-01', 0, 1),
(86, 'JavaServerPages', '2013-04-01', 0, 1),
(87, 'JavaServerPages', '2013-03-03', 0, 2),
(88, 'JavaServerPages', '2013-04-01', 0, 2),
(89, 'JavaServerPages', '2013-04-01', 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tugas_has_tag`
--

CREATE TABLE IF NOT EXISTS `tugas_has_tag` (
  `tugas_idtugas` int(11) NOT NULL,
  `tag_idtag` int(11) NOT NULL,
  KEY `fk_tugas_has_tag_tag1` (`tag_idtag`),
  KEY `fk_tugas_has_tag_tugas1` (`tugas_idtugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tugas_has_tag`
--

INSERT INTO `tugas_has_tag` (`tugas_idtugas`, `tag_idtag`) VALUES
(2, 1),
(3, 1),
(5, 1),
(6, 1),
(1, 1),
(1, 14),
(67, 1),
(68, 1),
(69, 1),
(70, 1),
(71, 1),
(72, 1),
(74, 1),
(74, 1),
(75, 1),
(76, 1),
(77, 1),
(78, 2),
(79, 2),
(80, 2),
(81, 2),
(82, 2),
(83, 2),
(84, 2),
(85, 2),
(86, 1),
(87, 1),
(88, 2),
(89, 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accounts_has_tugas`
--
ALTER TABLE `accounts_has_tugas`
  ADD CONSTRAINT `fk_accounts_has_tugas_accounts1` FOREIGN KEY (`accounts_idaccounts`) REFERENCES `accounts` (`idaccounts`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_accounts_has_tugas_tugas1` FOREIGN KEY (`tugas_idtugas`) REFERENCES `tugas` (`idtugas`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `assignee_has_kategori`
--
ALTER TABLE `assignee_has_kategori`
  ADD CONSTRAINT `fk_accounts_has_kategori_accounts1` FOREIGN KEY (`accounts_idaccounts`) REFERENCES `accounts` (`idaccounts`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_accounts_has_kategori_kategori1` FOREIGN KEY (`kategori_idkategori`) REFERENCES `kategori` (`idkategori`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `attachment`
--
ALTER TABLE `attachment`
  ADD CONSTRAINT `fk_attachment_tugas1` FOREIGN KEY (`tugas_idtugas`) REFERENCES `tugas` (`idtugas`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `kategori`
--
ALTER TABLE `kategori`
  ADD CONSTRAINT `fk_kategori_accounts1` FOREIGN KEY (`pembuat`) REFERENCES `accounts` (`idaccounts`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `fk_komentar_accounts1` FOREIGN KEY (`accounts_idaccounts`) REFERENCES `accounts` (`idaccounts`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_komentar_tugas1` FOREIGN KEY (`tugas_idtugas`) REFERENCES `tugas` (`idtugas`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tugas`
--
ALTER TABLE `tugas`
  ADD CONSTRAINT `fk_tugas_kategori1` FOREIGN KEY (`kategori_idkategori`) REFERENCES `kategori` (`idkategori`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tugas_has_tag`
--
ALTER TABLE `tugas_has_tag`
  ADD CONSTRAINT `fk_tugas_has_tag_tag1` FOREIGN KEY (`tag_idtag`) REFERENCES `tag` (`idtag`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tugas_has_tag_tugas1` FOREIGN KEY (`tugas_idtugas`) REFERENCES `tugas` (`idtugas`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
