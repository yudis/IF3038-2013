SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510020`
--
DROP DATABASE IF EXISTS `progin_405_13510020`;
CREATE DATABASE `progin_405_13510020` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `progin_405_13510020`;

-- --------------------------------------------------------

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
CREATE TABLE IF NOT EXISTS `assignment` (
  `IDAssignment` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(30) NOT NULL,
  `IDTask` int(11) NOT NULL,
  PRIMARY KEY (`IDAssignment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `assignment`
--


-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
CREATE TABLE IF NOT EXISTS `attachment` (
  `IDAttachment` int(11) NOT NULL AUTO_INCREMENT,
  `IDTask` int(11) NOT NULL,
  `PathFile` text NOT NULL,
  PRIMARY KEY (`IDAttachment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `attachment`
--


-- --------------------------------------------------------

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
CREATE TABLE IF NOT EXISTS `authority` (
  `IDAuthority` int(11) NOT NULL AUTO_INCREMENT,
  `IDCategory` int(11) NOT NULL,
  `Username` varchar(30) NOT NULL,
  PRIMARY KEY (`IDAuthority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `authority`
--


-- --------------------------------------------------------

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `IDCategory` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(30) NOT NULL,
  PRIMARY KEY (`IDCategory`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `category`
--


-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `IDComment` int(11) NOT NULL AUTO_INCREMENT,
  `IDTask` int(11) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Content` text NOT NULL,
  PRIMARY KEY (`IDComment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `comment`
--


-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
CREATE TABLE IF NOT EXISTS `tag` (
  `IDTag` int(11) NOT NULL AUTO_INCREMENT,
  `TagName` varchar(30) NOT NULL,
  PRIMARY KEY (`IDTag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

--
-- Dumping data for table `tag`
--


-- --------------------------------------------------------

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task` (
  `IDTask` int(11) NOT NULL AUTO_INCREMENT,
  `IDCategory` int(11) NOT NULL,
  `TaskName` varchar(50) NOT NULL,
  `Status` varchar(30) NOT NULL,
  `Deadline` date NOT NULL,
  PRIMARY KEY (`IDTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `task`
--


-- --------------------------------------------------------

--
-- Table structure for table `tasktag`
--

DROP TABLE IF EXISTS `tasktag`;
CREATE TABLE IF NOT EXISTS `tasktag` (
  `IDTaskTag` int(11) NOT NULL AUTO_INCREMENT,
  `IDTask` int(11) NOT NULL,
  `IDTag` int(11) NOT NULL,
  PRIMARY KEY (`IDTaskTag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `tasktag`
--


-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `Username` varchar(30) NOT NULL,
  `Fullname` varchar(50) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Avatar` varchar(50) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

