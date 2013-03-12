-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Waktu pembuatan: 12. Maret 2013 jam 14:36
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
CREATE DATABASE IF NOT EXISTS progin_405_13510086;
USE progin_405_13510086;
-- --------------------------------------------------------

--
-- Struktur dari tabel `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `att_id` int(11) NOT NULL AUTO_INCREMENT,
  `att_content` text NOT NULL,
  `att_task_id` int(11) NOT NULL,
  PRIMARY KEY (`att_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data untuk tabel `attachment`
--

INSERT INTO `attachment` (`att_id`, `att_content`, `att_task_id`) VALUES
(1, 'dsadasdsdadsadasds', 1),
(2, 'sadasdasdsaddsdas', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(50) NOT NULL,
  `cat_creator` varchar(50) NOT NULL,
  PRIMARY KEY (`cat_id`),
  UNIQUE KEY `cat_name` (`cat_name`),
  UNIQUE KEY `cat_creator` (`cat_creator`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data untuk tabel `category`
--

INSERT INTO `category` (`cat_id`, `cat_name`, `cat_creator`) VALUES
(1, 'Schoolicious', 'kennyazrina'),
(2, 'Household', 'sharonloh');

-- --------------------------------------------------------

--
-- Struktur dari tabel `cat_asignee`
--

CREATE TABLE IF NOT EXISTS `cat_asignee` (
  `cat_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `cat_asignee`
--

INSERT INTO `cat_asignee` (`cat_id`, `username`) VALUES
(1, 'nflubis'),
(1, 'sharonloh');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data untuk tabel `comment`
--

INSERT INTO `comment` (`comment_id`, `comment_timestamp`, `comment_content`, `task_id`, `comment_creator`) VALUES
(1, '2013-03-12 04:44:26', 'asdasdasdas', 1, 'sharonloh'),
(2, '2013-03-12 04:44:26', 'sndajasndnsajkdans', 1, 'nflubis');

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
('capek', 1),
('males', 2);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data untuk tabel `task`
--

INSERT INTO `task` (`task_id`, `task_name`, `task_status`, `task_deadline`, `cat_name`, `task_creator`) VALUES
(1, 'Tubes Progin 2', 0, '2013-03-23', 'Schoolicious', 'kennyazrina'),
(2, 'Tubes Progin 3', 0, '2013-04-25', 'Schoolicious', 'nflubis');

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
(1, 'nflubis'),
(2, 'sharonloh'),
(1, 'sharonloh');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `birthdate` date NOT NULL,
  `avatar` text NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`username`, `password`, `full_name`, `birthdate`, `avatar`, `email`) VALUES
('kennyazrina', 'kennyazrina', 'Kania Azrina', '1992-03-13', 'abcdefg', 'kaniaazrina@gmail.com'),
('nflubis', 'nflubis', 'Nurul Fithria Lubis', '1992-04-17', 'dasjndjksndkas', 'nflubis@gmail.com'),
('sharonloh', 'sharonloh', 'Sharon Loh', '1992-02-10', 'dsddsdsa', 'sharonloh@gmail.com');

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `category_ibfk_1` FOREIGN KEY (`cat_creator`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
