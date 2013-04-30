-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2013 at 02:49 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_471_13510008`
--

-- --------------------------------------------------------

--
-- Table structure for table `able`
--

CREATE TABLE IF NOT EXISTS `able` (
  `username` varchar(30) NOT NULL,
  `idcat` varchar(8) NOT NULL,
  PRIMARY KEY (`username`,`idcat`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `able`
--

INSERT INTO `able` (`username`, `idcat`) VALUES
('aditya', 'giboeee2'),
('aditya', 'pXlL46Q4'),
('adityaagung', 'D0KYXATc'),
('adityaagung', 'J1ImOC8s'),
('adityaagung', 'RSnUT3B4'),
('afiff', 'D0KYXATc'),
('afiff', 'RSnUT3B4'),
('agahh', 'giboeee2'),
('agahh', 'pXlL46Q4'),
('dedyy', 'giboeee2'),
('dedyy', 'pXlL46Q4'),
('gilanglaba', 'D0KYXATc'),
('gilanglaba', 'J1ImOC8s'),
('gilanglaba', 'RSnUT3B4');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `idcat` varchar(8) NOT NULL,
  `catname` varchar(25) NOT NULL,
  `creator` varchar(30) NOT NULL,
  PRIMARY KEY (`idcat`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`idcat`, `catname`, `creator`) VALUES
('D0KYXATc', 'Himpunan', 'gilanglaba'),
('RSnUT3B4', 'test', 'gilanglaba'),
('J1ImOC8s', 'Hello', 'adityaagung'),
('giboeee2', 'Progin', 'agahh'),
('pXlL46Q4', 'IMK', 'agahh');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `idcomment` varchar(8) NOT NULL,
  `username` varchar(30) NOT NULL,
  `idtask` varchar(8) NOT NULL,
  `content` text NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idcomment`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`idcomment`, `username`, `idtask`, `content`, `time`) VALUES
('qn3T4ohT', 'adityaagung', 'IdB1f1FX', 'lagi nih cok', '2013-04-23 04:00:03'),
('JYpiPgtY', 'adityaagung', 'IdB1f1FX', 'eh aing tes komen yak', '2013-04-23 03:57:02'),
('CqAGHKBb', 'gilanglaba', 'IdB1f1FX', 'oke siap masbro', '2013-04-23 01:32:29'),
('UVQqXhl1', 'afiff', 'IdB1f1FX', 'woi kerjain ini tubesnya yak', '2013-04-23 01:32:10'),
('TersqX3b', 'gilanglaba', 'IdB1f1FX', 'eh eh ada yg lupa nih komen dong', '2013-04-23 01:35:47'),
('XtQgZJy2', 'afiff', 'IdB1f1FX', 'cape nih aing fufufu', '2013-04-23 01:54:01'),
('djH0aBLa', 'gilanglaba', 'pbO62zCJ', 'tes', '2013-04-23 04:10:49'),
('1tE6oFKd', 'adityaagung', 'pbO62zCJ', 'hallooo', '2013-04-23 04:12:49'),
('pt4QYn21', 'agahh', 'pdz6z7Hu', 'aduh video nya knp nih', '2013-04-23 06:28:51');

-- --------------------------------------------------------

--
-- Table structure for table `do`
--

CREATE TABLE IF NOT EXISTS `do` (
  `username` varchar(30) NOT NULL,
  `idtask` varchar(8) NOT NULL,
  PRIMARY KEY (`username`,`idtask`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `do`
--

INSERT INTO `do` (`username`, `idtask`) VALUES
('aditya', 'w6RHdHDR'),
('aditya', 'WeqMP2Oa'),
('aditya', 'xZQutOQ0'),
('adityaagung', 'AkjslWUT'),
('adityaagung', 'EmZ6FTQh'),
('adityaagung', 'uYMveWMK'),
('adityaagung', 'YeMZGN3w'),
('afiff', 'IdB1f1FX'),
('agahh', 'pdz6z7Hu'),
('agahh', 'Q4CPJlmO'),
('agahh', 'w6RHdHDR'),
('agahh', 'WeqMP2Oa'),
('agahh', 'xZQutOQ0'),
('dedyy', '6JN6BTMw'),
('dedyy', 'Q4CPJlmO'),
('dedyy', 'WeqMP2Oa'),
('gilanglaba', '67ga1HTm'),
('gilanglaba', '6JN6BTMw'),
('gilanglaba', 'AkjslWUT'),
('gilanglaba', 'EmZ6FTQh'),
('gilanglaba', 'GbN3GxF0'),
('gilanglaba', 'IdB1f1FX'),
('gilanglaba', 'K3NiqNiH'),
('gilanglaba', 'ntx3kIzN'),
('gilanglaba', 'pbO62zCJ'),
('gilanglaba', 'uYMveWMK'),
('gilanglaba', 'X2bcNQqo'),
('gilanglaba', 'YeMZGN3w');

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `idtask` varchar(8) NOT NULL,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`idtask`,`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`idtask`, `name`) VALUES
('6JN6BTMw', 'hello'),
('6JN6BTMw', 'java'),
('6JN6BTMw', 'world'),
('AkjslWUT', 'beruang'),
('AkjslWUT', 'ga jelas'),
('AkjslWUT', 'video'),
('EmZ6FTQh', 'afiff'),
('IdB1f1FX', 'jsp'),
('IdB1f1FX', 'progin'),
('IdB1f1FX', 'tubes'),
('pbO62zCJ', 'DE'),
('pbO62zCJ', 'himpunan'),
('pbO62zCJ', 'muker'),
('pbO62zCJ', 'tes'),
('pbO62zCJ', 'teslagi'),
('pdz6z7Hu', 'desain'),
('pdz6z7Hu', 'IMK'),
('pdz6z7Hu', 'perbaikan'),
('Q4CPJlmO', 'cuma tes'),
('Q4CPJlmO', 'iseng'),
('Q4CPJlmO', 'tes'),
('Q4CPJlmO', 'video'),
('uYMveWMK', 'hello'),
('uYMveWMK', 'world'),
('w6RHdHDR', 'buat'),
('w6RHdHDR', 'coba'),
('w6RHdHDR', 'iseng'),
('WeqMP2Oa', 'desain'),
('WeqMP2Oa', 'imk'),
('WeqMP2Oa', 'marbel'),
('xZQutOQ0', 'cuma tes'),
('xZQutOQ0', 'iseng'),
('xZQutOQ0', 'tes'),
('YeMZGN3w', 'asdf'),
('YeMZGN3w', 'asdfa'),
('YeMZGN3w', 'asdfasdf');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `idtask` varchar(8) NOT NULL,
  `idcat` varchar(8) NOT NULL,
  `taskname` varchar(25) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `attachment` varchar(100) NOT NULL,
  `deadline` date NOT NULL,
  `creator` varchar(30) NOT NULL,
  PRIMARY KEY (`idtask`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`idtask`, `idcat`, `taskname`, `status`, `attachment`, `deadline`, `creator`) VALUES
('uYMveWMK', 'J1ImOC8s', 'world', 1, 'attachment/gilanglaba_uYMveWMK_asdf.PNG', '2013-05-02', 'gilanglaba'),
('pbO62zCJ', 'D0KYXATc', 'Muker', 0, 'attachment/gilanglaba_pbO62zCJ_bg_main.png', '2013-04-29', 'gilanglaba'),
('xZQutOQ0', 'giboeee2', 'tes jpg', 0, 'attachment/agahh_xZQutOQ0_bg2.jpg', '2013-05-02', 'agahh'),
('X2bcNQqo', 'D0KYXATc', 'rapat3', 0, 'attachment/gilanglaba_X2bcNQqo_Desert.jpg', '2013-05-08', 'gilanglaba'),
('AkjslWUT', 'J1ImOC8s', 'tes video task', 0, 'attachment/gilanglaba_AkjslWUT_video.mp4', '2013-04-25', 'gilanglaba'),
('IdB1f1FX', 'D0KYXATc', 'rapat2', 0, 'attachment/afiff_IdB1f1FX_Tubes III.pdf', '2013-04-26', 'afiff'),
('YeMZGN3w', 'RSnUT3B4', 'test lagi', 0, 'attachment/gilanglaba_YeMZGN3w_Stack.cpp', '2013-04-29', 'gilanglaba'),
('GbN3GxF0', 'D0KYXATc', 'rapat4', 0, 'attachment/gilanglaba_GbN3GxF0_Desert.jpg', '2013-05-08', 'gilanglaba'),
('67ga1HTm', 'D0KYXATc', 'rapat4', 0, 'attachment/gilanglaba_67ga1HTm_Desert.jpg', '2013-05-08', 'gilanglaba'),
('WeqMP2Oa', 'pXlL46Q4', 'Marbel Answering Machine', 0, 'attachment/agahh_WeqMP2Oa_mesin jawab.PNG', '2013-04-30', 'agahh'),
('w6RHdHDR', 'giboeee2', 'coba buat tugas', 0, 'attachment/agahh_w6RHdHDR_Chrysanthemum.jpg', '2013-04-30', 'agahh'),
('pdz6z7Hu', 'pXlL46Q4', 'Perbaikan desain', 1, 'attachment/agahh_pdz6z7Hu_video.mp4', '2013-05-03', 'agahh'),
('Q4CPJlmO', 'pXlL46Q4', 'tes video', 0, 'attachment/agahh_Q4CPJlmO_video.mp4', '2013-05-07', 'agahh');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `birthdate` date NOT NULL,
  `avatar` varchar(100) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `name`, `email`, `birthdate`, `avatar`) VALUES
('adityaagung', '13510010', 'Aditya Agung', '13510010@std.stei.itb.ac.id', '1993-02-25', 'img/avatar/adityaagung.jpg'),
('afiff', 'alhawari', 'afif al hawari', 'afif@std.stei.itb.ac.id', '1992-01-01', 'img/avatar/afiff.jpg'),
('agahh', '12345678', 'Anugrah Ss', 'anugrahsulaeman17@gmail.com', '1992-07-18', 'img/avatar/agahh.jpg'),
('aditya', 'password', 'Aditya Agung', 'aditya@stei.itb.ac.id', '2013-03-31', 'img/avatar/aditya.jpg'),
('dedyy', 'password', 'Dedy P', 'dedy@yahoo.com', '2013-04-21', 'img/avatar/dedyy.jpg'),
('gilang', 'password', 'gilang laba', 'gilanglaba@gmail.com', '2013-03-31', 'img/avatar/gilang.jpg'),
('gilanglaba', 'gilanglaksana', 'Gilang L Laba', 'gilang.laba@gmail.com', '1993-05-07', 'img/avatar/gilanglaba.jpg');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
