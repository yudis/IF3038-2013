-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Waktu pembuatan: 23. Maret 2013 jam 14:21
-- Versi Server: 5.5.16
-- Versi PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510086`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `att_id` int(11) NOT NULL AUTO_INCREMENT,
  `att_content` text NOT NULL,
  `att_task_id` int(11) NOT NULL,
  PRIMARY KEY (`att_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data untuk tabel `attachment`
--

INSERT INTO `attachment` (`att_id`, `att_content`, `att_task_id`) VALUES
(1, 'dsadasdsdadsadasds', 1),
(2, 'sadasdasdsaddsdas', 1),
(3, '', 6),
(4, '', 7),
(5, '', 7),
(6, '', 7),
(7, '', 8),
(8, '', 8),
(9, '', 8),
(10, '', 9),
(11, '', 9),
(12, '', 9),
(13, 'IF3056_TugasBesar1.pdf', 10),
(14, '', 10),
(15, '', 10);

-- --------------------------------------------------------

--
-- Struktur dari tabel `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(50) NOT NULL,
  `cat_creator` varchar(50) NOT NULL,
  PRIMARY KEY (`cat_id`),
  UNIQUE KEY `cat_name` (`cat_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data untuk tabel `category`
--

INSERT INTO `category` (`cat_id`, `cat_name`, `cat_creator`) VALUES
(2, 'Household', 'sharonloh'),
(6, 'paper', 'nflubis'),
(10, 'Personal', 'sharonloh'),
(11, 'Love', 'sharonloh');

-- --------------------------------------------------------

--
-- Struktur dari tabel `cat_asignee`
--

CREATE TABLE IF NOT EXISTS `cat_asignee` (
  `cat_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_content` text NOT NULL,
  `task_id` int(11) NOT NULL,
  `comment_creator` varchar(50) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data untuk tabel `comment`
--

INSERT INTO `comment` (`comment_id`, `comment_timestamp`, `comment_content`, `task_id`, `comment_creator`) VALUES
(5, '2013-03-22 14:02:55', '					asfasdf	', 4, 'sharonloh'),
(7, '2013-03-22 14:03:37', '			asdfasdfasdfasdfasd			', 4, 'sharonloh'),
(18, '2013-03-23 09:16:18', '		bb				', 4, 'sharonloh');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `tag_name` varchar(50) NOT NULL,
  `task_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tag`
--

INSERT INTO `tag` (`tag_name`, `task_id`) VALUES
('HTML', 1),
('males', 2),
('CSS', 1),
('jQuery', 1),
('jQuery', 2),
('Tugas Besar', 2),
('piring', 6),
(' cuci', 6),
('cinta', 7),
(' gila', 7),
('cinta', 8),
(' gila', 8),
(' lalala', 8),
('cinta', 9),
(' gila', 9),
(' lalala', 9),
('tugas', 10),
(' rumah', 10),
(' buah', 10),
(' mangga', 10);

-- --------------------------------------------------------

--
-- Struktur dari tabel `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(50) NOT NULL,
  `task_status` tinyint(1) NOT NULL DEFAULT '0',
  `task_deadline` date NOT NULL,
  `cat_name` varchar(50) NOT NULL,
  `task_creator` varchar(50) NOT NULL,
  PRIMARY KEY (`task_id`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data untuk tabel `task`
--

INSERT INTO `task` (`task_id`, `task_name`, `task_status`, `task_deadline`, `cat_name`, `task_creator`) VALUES
(4, 'CCC', 0, '2013-03-23', 'Household', 'sharonloh'),
(6, 'Cuci Piring', 0, '2013-03-14', 'Household', 'sharonloh'),
(7, 'Cinta', 0, '2013-03-20', 'Personal', 'sharonloh'),
(8, 'Ini baru cinta', 0, '2013-03-28', 'Love', 'sharonloh');

-- --------------------------------------------------------

--
-- Struktur dari tabel `task_asignee`
--

CREATE TABLE IF NOT EXISTS `task_asignee` (
  `task_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `task_asignee`
--

INSERT INTO `task_asignee` (`task_id`, `username`) VALUES
(4, 'kennyazrina'),
(4, 'nflubis'),
(7, 'kennyazrina'),
(7, ' nflubis'),
(8, ''),
(9, ''),
(10, 'nflubis');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `birthdate` date NOT NULL,
  `avatar` blob,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`username`, `password`, `full_name`, `birthdate`, `avatar`, `email`) VALUES
('kennyazrina', 'kennyazrina', 'Kania Azrina', '1992-03-13', NULL, 'kaniaazrina@gmail.com'),
('nflubis', 'nflubis', 'Nurul Fithria Lubis', '1992-04-17', NULL, 'nflubis@gmail.com'),
('sharonloh', 'insaneinsane', 'Sharon Loh', '1992-02-10', 0x2e2e2f696d672f736861726f6e6c6f6844534330313137362e4a5047, 'sharonloh@gmail.com');

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
