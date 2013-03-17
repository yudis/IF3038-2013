-- phpMyAdmin SQL Dump
-- version 3.3.7deb7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 12, 2013 at 02:11 PM
-- Server version: 5.1.66
-- PHP Version: 5.3.3-7+squeeze15

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510011`
--
DROP DATABASE IF EXISTS `progin_405_13510011`;
CREATE DATABASE `progin_405_13510011`;
USE `progin_405_13510011`;

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE IF NOT EXISTS `members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `birthdate` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `avatar` varchar(50) NOT NULL,
  `gender` char(1) NOT NULL,
  `about` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`id`, `username`, `password`, `fullname`, `birthdate`, `email`, `avatar`, `gender`, `about`) VALUES
(1, 'enjellan', 'de4ab6e26db462b930510ba83e9f80b7db2bef88', 'Enjella Melissa Nababan', '1991-03-03', 'enjellamelissan@yahoo.com', 'images/enjella.JPG', 'F', ''),
(2, 'danny', '0c258ac8c40b49eea99ecbd24e31b1d72aa42772', 'Danny Andrianto', '0000-00-00', 'email', 'images/danny.jpg', 'M', 'about');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Pemrograman Internet'),
(2, 'Date');

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE IF NOT EXISTS `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `creator` int(11) NOT NULL,
  `timestamp` datetime NOT NULL,
  `deadline` datetime NOT NULL,
  `category` int(11) NOT NULL,
  `done` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT FOREIGN KEY (`creator`) REFERENCES `members` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (`category`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`id`, `name`, `creator`, `timestamp`, `deadline`, `category`, `done`) VALUES
(1, 'Tubes 1', 1, '2013-02-20 10:50:49', '2013-02-25 22:00:00', 1, 1),
(2, 'Tubes 2', 2, '2013-03-11 21:20:11', '2013-03-22 22:03:00', 1, 0),
(3, 'Kencan dengan si "dia"', 1, '2013-02-20 21:21:53', '2013-02-24 20:00:00', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `assignees`
--

CREATE TABLE IF NOT EXISTS `assignees` (
  `member` int(11) NOT NULL,
  `task` int(11) NOT NULL,
  PRIMARY KEY (`member`,`task`),
  CONSTRAINT FOREIGN KEY (`member`) REFERENCES `members` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (`task`) REFERENCES `tasks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignees`
--

INSERT INTO `assignees` (`member`, `task`) VALUES
(2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `attachments`
--

CREATE TABLE IF NOT EXISTS `attachments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(256) NOT NULL,
  `filetype` varchar(5) NOT NULL,
  `task` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT FOREIGN KEY (`task`) REFERENCES `tasks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `attachments`
--

INSERT INTO `attachments` (`id`, `path`, `filetype`, `task`) VALUES
(1, 'images/Up.png', 'file', 1),
(2, 'images/gajah.png', 'image', 1),
(3, 'images/movie.ogg', 'video', 1);

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member` int(11) NOT NULL,
  `task` int(11) NOT NULL,
  `timestamp` datetime NOT NULL,
  `comment` text NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT FOREIGN KEY (`member`) REFERENCES `members` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (`task`) REFERENCES `tasks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `member`, `task`, `timestamp`, `comment`) VALUES
(1, 2, 1, '2013-03-12 13:56:30', 'Does it work?'),
(2, 1, 1, '2013-03-12 13:56:47', 'Yes, it does');

-- --------------------------------------------------------

--
-- Table structure for table `editors`
--

CREATE TABLE IF NOT EXISTS `editors` (
  `member` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  PRIMARY KEY (`member`,`category`),
  CONSTRAINT FOREIGN KEY (`member`) REFERENCES `members` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (`category`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `editors`
--

INSERT INTO `editors` (`member`, `category`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE IF NOT EXISTS `tags` (
  `name` varchar(50) NOT NULL,
  `tagged` int(11) NOT NULL,
  PRIMARY KEY (`name`,`tagged`),
  CONSTRAINT FOREIGN KEY (`tagged`) REFERENCES `tasks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`name`, `tagged`) VALUES
('css', 1),
('html', 1),
('javascript', 1);

