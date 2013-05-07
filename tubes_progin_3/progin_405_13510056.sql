-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 13, 2013 at 04:04 PM
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510056`
--

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `id_attachment` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(100) NOT NULL,
  PRIMARY KEY (`id_attachment`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `attachment`
--

INSERT INTO `attachment` (`id_attachment`, `path`) VALUES
(1, 'img/diagram.jpg'),
(2, 'img/rules.pdf'),
(5, 'img/video.MP4');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id_category` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `categ_creator` varchar(25) NOT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id_category`, `name`, `categ_creator`) VALUES
(1, 'Fraud', 'timotius'),
(2, 'Robbery', 'nicholas'),
(3, 'Gambling', 'jeremy');

-- --------------------------------------------------------

--
-- Table structure for table `caurelation`
--

CREATE TABLE IF NOT EXISTS `caurelation` (
  `id_cau` int(11) NOT NULL AUTO_INCREMENT,
  `id_category` int(11) NOT NULL,
  `authorized_user` varchar(25) NOT NULL,
  PRIMARY KEY (`id_cau`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `caurelation`
--

INSERT INTO `caurelation` (`id_cau`, `id_category`, `authorized_user`) VALUES
(1, 1, 'timotius'),
(2, 1, 'jeremy'),
(3, 2, 'nicholas'),
(4, 3, 'jeremy'),
(5, 3, 'timotius');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id_comment` int(11) NOT NULL AUTO_INCREMENT,
  `id_task` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `timestamp` varchar(16) NOT NULL,
  `content` varchar(1000) NOT NULL,
  PRIMARY KEY (`id_comment`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=58 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id_comment`, `id_task`, `username`, `timestamp`, `content`) VALUES
(1, 11, 'nicholas', '2013:03:20:11:00', 'Inspired by: Calamity Jane, famous frontierswoman and professional scout known for fighting against Native Americans.'),
(2, 11, 'jeremy', '2013:03:20:11:05', 'Ability: She can play BANG! cards as Missed! cards and vice versa. If she plays a Missed! as a BANG!, she cannot play another BANG! card that turn (unless she has a Volcanic in play).'),
(3, 11, 'timotius', '2013:03:20:11:30', 'Playing against Calamity Janet: Often with Cat Balous and Panics, we forget that it can be very powerful to steal from their hand. This can be useful in knocking out Calamity Janet''s supply of BANG! and Missed!, or even a handy beer. Having multiple attack cards (Duel, Indians!, Gatling) to punish Calamity Janet is also useful. While Calamity Janet will generally win duels, it is not necessarily a bad idea to try to drain her cards with a Duel and then slam her with the other attack cards. Calamity Janet will take some serious damage, not to mention that other players will be able to easily target her until her next turn. If you are desperate, a chaotic Dynamite can always be worth a shot, since she cannot dodge it if it explodes on her.'),
(4, 11, 'timotius', '2013:03:20:11:35', 'Okay, let''s go hunt this person today.'),
(5, 11, 'nicholas', '2013:03:20:11:36', 'When will we do our work?'),
(6, 11, 'jeremy', '2013:03:20:11:41', 'Ready when you are.'),
(7, 11, 'timotius', '2013:03:20:11:42', 'So when and where will we meet?'),
(8, 11, 'nicholas', '2013:03:20:11:53', 'The usual saloon, I think.'),
(9, 11, 'timotius', '2013:03:20:11:57', 'Come on. We''ve been there countless times already.'),
(10, 11, 'jeremy', '2013:03:20:12:00', 'So what about that bed and breakfast by the city border?'),
(11, 11, 'nicholas', '2013:03:20:12:03', 'Sounds good to me. Who''s with me?'),
(12, 11, 'timotius', '2013:03:20:12:07', 'Alright. Count me in. But you''re paying, Rio.'),
(13, 11, 'nicholas', '2013:03:20:12:10', 'Not cool. How come am I always paying?'),
(14, 11, 'jeremy', '2013:03:20:12:14', 'Well, helping out friends is a good thing, right?'),
(15, 11, 'nicholas', '2013:03:20:12:15', 'Even if that''s true, but come on, dude...'),
(16, 14, 'jeremy', '2013:03:20:12:22', 'Nothing special about this guy.'),
(17, 1, 'nicholas', '2013:03:20:12:32', 'Suzy oh Suzyyy... The girl whom I set my eyes on. Yesterday, at least.'),
(18, 2, 'timotius', '2013:03:20:12:02', 'This guy was my friend back in my kindergarten days.'),
(19, 2, 'jeremy', '2013:03:20:12:04', 'Really? Why did he turn to be a bad guy?'),
(20, 2, 'timotius', '2013:03:20:12:16', 'Well. How should I say it. It might be because of what I''ve done to the guy back in those days.'),
(21, 2, 'jeremy', '2013:03:20:12:18', 'You''re way worse than this guy.'),
(22, 4, 'nicholas', '2013:03:20:12:33', 'Do re mi fa sol la ti do~ This guy practiced piano with me.'),
(23, 4, 'timotius', '2013:03:20:12:36', 'Well, this is a surprise. What happened?'),
(31, 11, 'nicholas', '2013:03:20:12:03', 'Sounds good to me. Who''s with me?'),
(32, 11, 'timotius', '2013:03:20:12:07', 'Alright. Count me in. But you''re paying, Rio.'),
(33, 11, 'nicholas', '2013:03:20:12:10', 'Not cool. How come am I always paying?'),
(34, 11, 'jeremy', '2013:03:20:12:14', 'Well, helping out friends is a good thing, right?'),
(35, 11, 'nicholas', '2013:03:20:12:15', 'Even if that''s true, but come on, dude...'),
(36, 14, 'jeremy', '2013:03:20:12:22', 'Nothing special about this guy.');

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `id_tag` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id_tag`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`id_tag`, `name`) VALUES
(1, 'Male'),
(2, 'Woman'),
(3, 'Three Bullets'),
(4, 'Four Bullets');

-- --------------------------------------------------------

--
-- Table structure for table `tarelation`
--

CREATE TABLE IF NOT EXISTS `tarelation` (
  `id_ta` int(11) NOT NULL AUTO_INCREMENT,
  `id_task` int(11) NOT NULL,
  `id_attachment` int(11) NOT NULL,
  PRIMARY KEY (`id_ta`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Dumping data for table `tarelation`
--

INSERT INTO `tarelation` (`id_ta`, `id_task`, `id_attachment`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 2),
(7, 7, 2),
(8, 8, 2),
(9, 9, 2),
(10, 10, 2),
(11, 11, 1),
(12, 11, 2),
(13, 12, 1),
(14, 12, 2),
(15, 13, 1),
(16, 13, 2),
(17, 14, 1),
(18, 14, 2),
(19, 15, 1),
(20, 15, 2),
(21, 16, 1),
(22, 16, 2),
(26, 4, 5),
(25, 11, 5),
(27, 11, 5),
(28, 4, 5);

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `id_task` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `deadline` varchar(20) NOT NULL,
  `status` char(1) NOT NULL,
  `id_category` int(11) NOT NULL,
  `creator` varchar(25) NOT NULL,
  PRIMARY KEY (`id_task`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id_task`, `name`, `deadline`, `status`, `id_category`, `creator`) VALUES
(1, 'Suzy Lafayette', '2013-03-24', 'T', 1, 'timotius'),
(2, 'Paul Regret', '2013-03-23', 'F', 1, 'timotius'),
(3, 'Pedro Ramirez', '2013-03-23', 'F', 1, 'jeremy'),
(4, 'Calamity Janet', '2013-03-23', 'F', 1, 'timotius'),
(5, 'El Gringo', '2013-03-23', 'F', 1, 'jeremy'),
(6, 'Jourdonnais', '2013-03-23', 'F', 2, 'nicholas'),
(7, 'Lucky Duke', '2013-03-23', 'F', 2, 'nicholas'),
(8, 'Slab The Killer', '2013-03-23', 'T', 2, 'nicholas'),
(9, 'Willy The Kid', '2013-03-23', 'F', 2, 'nicholas'),
(10, 'Jesse Jones', '2013-03-23', 'F', 2, 'nicholas'),
(11, 'Bart Cassidy', '2013-03-23', 'T', 3, 'jeremy'),
(12, 'Black Jack', '2013-03-23', 'F', 3, 'jeremy'),
(13, 'Kit Carlson', '2013-03-23', 'F', 3, 'timotius'),
(14, 'Rose Doolan', '2013-03-23', 'F', 3, 'jeremy'),
(15, 'Sid Ketchum', '2013-03-23', 'F', 3, 'timotius'),
(16, 'Vulture Sam', '2013-03-23', 'F', 3, 'timotius'),
(20, 'NewCriminal', '2013/04/30', 'F', 1, 'timotius');

-- --------------------------------------------------------

--
-- Table structure for table `ttrelation`
--

CREATE TABLE IF NOT EXISTS `ttrelation` (
  `id_tt` int(11) NOT NULL AUTO_INCREMENT,
  `id_task` int(11) NOT NULL,
  `id_tag` int(11) NOT NULL,
  PRIMARY KEY (`id_tt`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=39 ;

--
-- Dumping data for table `ttrelation`
--

INSERT INTO `ttrelation` (`id_tt`, `id_task`, `id_tag`) VALUES
(2, 2, 1),
(3, 3, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1),
(8, 8, 1),
(9, 9, 1),
(10, 10, 1),
(11, 11, 1),
(12, 12, 1),
(13, 13, 1),
(15, 15, 1),
(16, 16, 1),
(18, 2, 3),
(21, 5, 3),
(38, 1, 4),
(37, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `ucrelation`
--

CREATE TABLE IF NOT EXISTS `ucrelation` (
  `id_uc` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `id_category` int(11) NOT NULL,
  PRIMARY KEY (`id_uc`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Dumping data for table `ucrelation`
--

INSERT INTO `ucrelation` (`id_uc`, `username`, `id_category`) VALUES
(1, 'timotius', 1),
(2, 'timotius', 2),
(3, 'timotius', 3),
(4, 'nicholas', 1),
(5, 'nicholas', 2),
(6, 'nicholas', 3),
(7, 'jeremy', 1),
(8, 'jeremy', 3),
(27, 'timotius', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(25) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `avatar` varchar(200) NOT NULL,
  `dob` varchar(10) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `fullname`, `avatar`, `dob`, `email`, `password`) VALUES
('timotius', 'Timotius Kevin Levandi', 'uploaded/timo.jpg', '1992/03/07', 'timotius@bang.com', 'd4f7342267d6f398b373096360d20d9b'),
('nicholas', 'Nicholas Rio', 'uploaded/rio.jpg', '1992/11/28', 'nicholas@bang.com', '86a79009bb31eb433425f8be8fa53e7d'),
('jeremy', 'Jeremy Joseph Hanniel', '', '1993/06/16', 'jeremy@bang.com', '0eeec2cacfb303ad78a8567fe0d8fa1c');

-- --------------------------------------------------------

--
-- Table structure for table `utrelation`
--

CREATE TABLE IF NOT EXISTS `utrelation` (
  `id_ut` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `id_task` int(11) NOT NULL,
  PRIMARY KEY (`id_ut`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=39 ;

--
-- Dumping data for table `utrelation`
--

INSERT INTO `utrelation` (`id_ut`, `username`, `id_task`) VALUES
(18, 'timotius', 8),
(12, 'jeremy', 14),
(13, 'jeremy', 3),
(14, 'jeremy', 12),
(15, 'jeremy', 11),
(16, 'jeremy', 5),
(17, 'timotius', 6),
(6, 'timotius', 2),
(7, 'nicholas', 9),
(8, 'nicholas', 6),
(9, 'nicholas', 7),
(10, 'nicholas', 8),
(11, 'nicholas', 10),
(2, 'timotius', 15),
(3, 'timotius', 13),
(4, 'timotius', 16),
(5, 'timotius', 4),
(36, 'timotius', 1),
(19, 'timotius', 10),
(20, 'jeremy', 15),
(21, 'nicholas', 13),
(22, 'nicholas', 16),
(23, 'jeremy', 16),
(24, 'jeremy', 2),
(25, 'nicholas', 14),
(26, 'timotius', 12),
(27, 'nicholas', 11),
(28, 'timotius', 11),
(29, 'nicholas', 5);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
