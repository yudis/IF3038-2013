-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 10, 2013 at 08:20 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510057`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignee`
--

CREATE TABLE IF NOT EXISTS `assignee` (
  `username` varchar(12) NOT NULL,
  `idtugas` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignee`
--


-- --------------------------------------------------------

--
-- Table structure for table `hak`
--

CREATE TABLE IF NOT EXISTS `hak` (
  `username` varchar(12) NOT NULL,
  `idkategori` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hak`
--


-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `idkategori` varchar(10) NOT NULL,
  `namakategori` varchar(20) NOT NULL,
  PRIMARY KEY (`idkategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--


-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `idtugas` varchar(10) NOT NULL,
  `username` varchar(12) NOT NULL,
  `isikomentar` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `komentar`
--


-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `idtugas` varchar(10) NOT NULL,
  `isitag` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tag`
--


-- --------------------------------------------------------

--
-- Table structure for table `tugas`
--

CREATE TABLE IF NOT EXISTS `tugas` (
  `idtugas` varchar(10) NOT NULL,
  `namatugas` varchar(20) NOT NULL,
  `attachment` varchar(30) NOT NULL,
  `deadline` date NOT NULL,
  `idkategori` varchar(10) NOT NULL,
  `username` varchar(12) NOT NULL,
  PRIMARY KEY (`idtugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tugas`
--


-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(12) NOT NULL,
  `password` varchar(20) NOT NULL,
  `fullname` varchar(25) NOT NULL,
  `birthdate` date NOT NULL,
  `phonenumber` varchar(12) NOT NULL,
  `email` varchar(50) NOT NULL,
  `avatar` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `fullname`, `birthdate`, `phonenumber`, `email`, `avatar`) VALUES
('admin', 'admin', 'Admin Z', '2013-03-08', '081977972383', 'admin@gmail.com', '');
