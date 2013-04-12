-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Waktu pembuatan: 12. April 2013 jam 08:58
-- Versi Server: 5.5.8
-- Versi PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_439_13510081`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `IDKategori` int(3) NOT NULL AUTO_INCREMENT,
  `judul` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`IDKategori`),
  KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data untuk tabel `kategori`
--

INSERT INTO `kategori` (`IDKategori`, `judul`, `username`) VALUES
(1, 'apapun', 'doraemon'),
(2, 'kalkulus', 'devin'),
(3, 'kamu', 'raymond'),
(4, 'putih', 'yuli'),
(5, 'progin', 'devin'),
(6, 'ai', 'devin'),
(7, 'sister', 'raymond');

-- --------------------------------------------------------

--
-- Struktur dari tabel `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `IDTask` int(3) NOT NULL,
  `IDKomentar` int(3) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `isi` text NOT NULL,
  `waktu` datetime NOT NULL,
  PRIMARY KEY (`IDKomentar`),
  KEY `IDTask` (`IDTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data untuk tabel `komentar`
--


-- --------------------------------------------------------

--
-- Struktur dari tabel `pelampiran`
--

CREATE TABLE IF NOT EXISTS `pelampiran` (
  `IDTugas` int(3) NOT NULL AUTO_INCREMENT,
  `lampiran` varchar(256) NOT NULL,
  KEY `IDTugas` (`IDTugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data untuk tabel `pelampiran`
--


-- --------------------------------------------------------

--
-- Struktur dari tabel `pengguna`
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
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`username`, `password`, `fullname`, `birthday`, `email`, `avatar`) VALUES
('amelia', 'amelia', 'amelia anasthasia', '1992-12-12', 'amel@hotmail.com', 'avatar/amelia.jpg'),
('anasthasia', 'anasthasia', 'anasthasia amelia', '1992-12-12', 'anasthasia@hotmail.com', 'avatar/anasthasia.jpg'),
('devin', 'devin', 'devin hoesen', '2013-03-11', 'devin@hotmail.com', 'avatar/0.jpg'),
('doraemon', 'doraemon', 'doraemon', '2013-03-19', 'doraemon@dorem.com', 'avatar/0.jpg'),
('lukanta', 'lukanta', 'lukanta raymond', '1992-12-12', 'lukanta@hotmail.com', 'avatar/0.jpg'),
('oenang', 'oenang', 'oenang yulianti', '1992-12-12', 'oenang@hotmail.com', 'avatar/0.jpg'),
('raymond', 'raymond', 'raymond lukanta', '2013-03-10', 'raymond@hotmail.com', 'avatar/0.jpg'),
('yuli', 'yuli', 'yulianti oenang', '2013-03-19', 'yuli@hotmail.com', 'avatar/0.jpg');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penugasan`
--

CREATE TABLE IF NOT EXISTS `penugasan` (
  `username` varchar(30) NOT NULL,
  `IDTask` int(3) NOT NULL,
  PRIMARY KEY (`username`,`IDTask`),
  KEY `IDTask` (`IDTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `penugasan`
--

INSERT INTO `penugasan` (`username`, `IDTask`) VALUES
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
('raymond', 7),
('yuli', 7),
('yuli', 8);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tugas`
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
-- Dumping data untuk tabel `tugas`
--

INSERT INTO `tugas` (`IDTask`, `IDKategori`, `name`, `deadline`, `stat`, `tag`, `username`) VALUES
(3, 5, 'Tubes 1 Progin', '2012-12-12', 0, 'susah, ribet', 'devin'),
(4, 5, 'Tubes 2 Progin', '2012-12-12', 1, 'susah, ribet', 'raymond'),
(5, 5, 'Tubes 3 Progin', '2012-12-12', 0, 'susah, ribet', 'yuli'),
(6, 6, 'Tubes 1 AI', '2012-12-12', 0, 'susah, ribet', 'devin'),
(7, 6, 'Tubes 2 AI', '2012-12-12', 0, 'susah, ribet', 'raymond'),
(8, 6, 'Tubes 3 AI', '2012-12-12', 0, 'susah, ribet', 'yuli');

-- --------------------------------------------------------

--
-- Struktur dari tabel `usercateg`
--

CREATE TABLE IF NOT EXISTS `usercateg` (
  `IDKategori` int(3) NOT NULL,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`IDKategori`,`username`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `usercateg`
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

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `kategori`
--
ALTER TABLE `kategori`
  ADD CONSTRAINT `kategori_ibfk_1` FOREIGN KEY (`username`) REFERENCES `pengguna` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `komentar_ibfk_1` FOREIGN KEY (`IDTask`) REFERENCES `tugas` (`IDTask`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `pelampiran`
--
ALTER TABLE `pelampiran`
  ADD CONSTRAINT `pelampiran_ibfk_1` FOREIGN KEY (`IDTugas`) REFERENCES `tugas` (`IDTask`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `penugasan`
--
ALTER TABLE `penugasan`
  ADD CONSTRAINT `penugasan_ibfk_1` FOREIGN KEY (`username`) REFERENCES `pengguna` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `penugasan_ibfk_2` FOREIGN KEY (`IDTask`) REFERENCES `tugas` (`IDTask`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tugas`
--
ALTER TABLE `tugas`
  ADD CONSTRAINT `tugas_ibfk_1` FOREIGN KEY (`IDKategori`) REFERENCES `kategori` (`IDKategori`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tugas_ibfk_2` FOREIGN KEY (`username`) REFERENCES `pengguna` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `usercateg`
--
ALTER TABLE `usercateg`
  ADD CONSTRAINT `usercateg_ibfk_1` FOREIGN KEY (`IDKategori`) REFERENCES `kategori` (`IDKategori`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usercateg_ibfk_2` FOREIGN KEY (`username`) REFERENCES `pengguna` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
