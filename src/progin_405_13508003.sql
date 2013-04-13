-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 23, 2013 at 04:38 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `category` varchar(255) NOT NULL,
  `users` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category`, `users`) VALUES
('progin', 'ikmal,qwerty,rezamp'),
('kriptografi', 'asdfg,yuiop');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `username` varchar(255) NOT NULL,
  `comment` varchar(1000) NOT NULL,
  `time` datetime NOT NULL,
  `taskname` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`username`, `comment`, `time`, `taskname`, `category`) VALUES
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:08', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:09', 'Tubes 2', 'progin'),
('rezamp', 'sudah commit index.php', '2013-03-20 07:06:10', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:11', 'Tubes 2', 'progin'),
('rezamp', 'sudah commit index.php', '2013-03-20 07:06:12', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:13', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:14', 'Tubes 2', 'progin'),
('rezamp', 'sudah commit index.php', '2013-03-20 07:06:15', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:16', 'Tubes 2', 'progin'),
('rezamp', 'a', '2013-03-21 08:39:17', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:18', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:19', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:20', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:21', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:22', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:23', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:24', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:25', 'Tubes 2', 'progin'),
('ikmal', 'a', '2013-03-21 08:39:26', 'Tubes 2', 'progin'),
('ikmal', 'sudah commit index.php', '2013-03-20 07:06:18', 'Tubes 1', 'progin'),
('qwerty', 'sudah commit index.php', '2013-03-20 07:06:19', 'Tubes 1', 'progin'),
('rezamp', 'sudah commit index.php', '2013-03-20 07:06:20', 'Tubes 1', 'progin');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `dob` date NOT NULL,
  `photo` varchar(255) NOT NULL,
  KEY `username` (`username`),
  KEY `username_2` (`username`),
  KEY `username_3` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`, `email`, `fullname`, `dob`, `photo`) VALUES
('ikmal', 'qwertyuiop', 'iam@ikmalsyifai.com', 'Ikmal Syifai', '2013-03-20', ''),
('rezamp', 'qwertyuiop', 'reza@reza.com', 'M Reza MP', '2013-02-05', ''),
('asdfg', 'qwertyuiop', 'asdf@asdf.com', 'Asdf Jklm', '2013-03-06', ''),
('yuiop', 'qwertyuiop', 'asdf@jkl.com', 'Jkl Asdf', '2013-03-04', ''),
('qwerty', 'qwertyuiop', 'asdf@qwerty.com', 'Qwerty Uiop', '2013-03-03', '');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `taskname` varchar(255) NOT NULL,
  `deadline` date NOT NULL,
  `tags` varchar(255) NOT NULL,
  `status` int(1) NOT NULL,
  `category` varchar(255) NOT NULL,
  `attachment` varchar(1000) NOT NULL,
  `type` varchar(5) NOT NULL,
  `assignee` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`taskname`, `deadline`, `tags`, `status`, `category`, `attachment`, `type`, `assignee`) VALUES
('Tubes 1', '2013-04-06', 'java,vigenere', 1, 'kriptografi', '', '', 'asdfg,yuiop'),
('Tubes 1', '2013-02-28', 'tubes,html,css', 1, 'progin', '', '', 'ikmal,qwerty'),
('Tubes 3', '2013-02-28', 'tubes,html,css', 1, 'progin', '', '', 'ikmal,qwerty'),
('Tubes 4', '2013-02-28', 'tubes,html,css', 1, 'progin', '', '', 'ikmal,qwerty'),
('Tubes 5', '2013-02-28', 'tubes,html,css', 1, 'progin', '', '', 'ikmal,qwerty'),
('Tubes 6', '2013-02-28', 'tubes,html,css', 1, 'progin', '', '', 'ikmal,qwerty'),
('Tubes 7', '2013-02-28', 'tubes,html,css', 1, 'progin', '', '', 'ikmal,qwerty'),
('Tubes 8', '2013-02-28', 'tubes,html,css', 1, 'progin', '', '', 'ikmal,qwerty'),
('Tubes 9', '2013-02-28', 'tubes,html,css', 1, 'progin', '', '', 'ikmal,qwerty'),
('Tubes 10', '2013-02-28', 'tubes,html,css', 1, 'progin', '', '', 'ikmal,qwerty'),
('Tubes 11', '2013-02-28', 'tubes,html,css', 1, 'progin', '', '', 'ikmal,qwerty'),
('Tubes 12', '2013-02-28', 'tubes,html,css', 1, 'progin', '', '', 'ikmal,qwerty'),
('Tubes 13', '2013-02-28', 'tubes,html,css', 1, 'progin', '', '', 'ikmal,qwerty'),
('Tubes 2', '2013-02-28', 'tubes,html,css', 1, 'progin', '', '', 'ikmal,qwerty');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
