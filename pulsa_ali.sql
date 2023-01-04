-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 04, 2023 at 07:39 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pulsa_ali`
--

-- --------------------------------------------------------

--
-- Table structure for table `akun`
--

CREATE TABLE `akun` (
  `id_akun` int(11) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `saldo` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `akun`
--
DELIMITER $$
CREATE TRIGGER `log_saldo` BEFORE UPDATE ON `akun` FOR EACH ROW BEGIN
IF new.saldo<old.saldo THEN
INSERT INTO log_saldo SET tgl=now(), log_saldo.transaksi = new.saldo-old.saldo,
ket = 'saldo berkurang',
saldo = new.saldo,
id_akun = old.id_akun;
ELSE 
INSERT INTO log_saldo SET tgl=now(), log_saldo.transaksi = new.saldo-old.saldo,
ket = 'saldo bertambah',
saldo = new.saldo,
id_akun = old.id_akun;
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `log_saldo`
--

CREATE TABLE `log_saldo` (
  `tgl` datetime NOT NULL,
  `transaksi` int(11) NOT NULL,
  `ket` varchar(15) NOT NULL,
  `saldo` int(11) NOT NULL,
  `id_akun` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `no_pln` char(16) NOT NULL,
  `nama` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `saldo`
--

CREATE TABLE `saldo` (
  `nominal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `token_listrik`
--

CREATE TABLE `token_listrik` (
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `trans_beli`
--

CREATE TABLE `trans_beli` (
  `no_trans_beli` int(11) NOT NULL,
  `nominal` int(11) NOT NULL,
  `id_akun` int(11) NOT NULL,
  `tgl` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `trans_beli`
--
DELIMITER $$
CREATE TRIGGER `penambahan_saldo` BEFORE INSERT ON `trans_beli` FOR EACH ROW UPDATE akun SET akun.saldo=saldo+(new.nominal)
WHERE akun.id_akun = new.id_akun
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `trans_jual`
--

CREATE TABLE `trans_jual` (
  `no_trans_jual` int(11) NOT NULL,
  `no_pln` char(16) NOT NULL,
  `harga` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `id_akun` int(11) NOT NULL,
  `tgl` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `trans_jual`
--
DELIMITER $$
CREATE TRIGGER `pengurangan_saldo` AFTER INSERT ON `trans_jual` FOR EACH ROW UPDATE akun SET akun.saldo=saldo-(new.harga+100)
WHERE akun.id_akun = new.id_akun
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `validasi_saldo_kurang` BEFORE INSERT ON `trans_jual` FOR EACH ROW BEGIN
IF (new.harga+100)>(SELECT saldo FROM akun WHERE akun.id_akun=new.id_akun )
THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = "saldo anda tidak mencukupi untuk melakukan transaksi";
END IF;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `akun`
--
ALTER TABLE `akun`
  ADD PRIMARY KEY (`id_akun`),
  ADD UNIQUE KEY `no_hp` (`no_hp`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`no_pln`);

--
-- Indexes for table `saldo`
--
ALTER TABLE `saldo`
  ADD PRIMARY KEY (`nominal`);

--
-- Indexes for table `token_listrik`
--
ALTER TABLE `token_listrik`
  ADD PRIMARY KEY (`harga`);

--
-- Indexes for table `trans_beli`
--
ALTER TABLE `trans_beli`
  ADD PRIMARY KEY (`no_trans_beli`);

--
-- Indexes for table `trans_jual`
--
ALTER TABLE `trans_jual`
  ADD PRIMARY KEY (`no_trans_jual`),
  ADD KEY `no_pln` (`no_pln`),
  ADD KEY `id_admin` (`id_akun`),
  ADD KEY `harga` (`harga`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `akun`
--
ALTER TABLE `akun`
  MODIFY `id_akun` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `saldo`
--
ALTER TABLE `saldo`
  MODIFY `nominal` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `trans_beli`
--
ALTER TABLE `trans_beli`
  MODIFY `no_trans_beli` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `trans_jual`
--
ALTER TABLE `trans_jual`
  MODIFY `no_trans_jual` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `trans_beli`
--
ALTER TABLE `trans_beli`
  ADD CONSTRAINT `trans_beli_ibfk_1` FOREIGN KEY (`id_akun`) REFERENCES `akun` (`id_akun`),
  ADD CONSTRAINT `trans_beli_ibfk_2` FOREIGN KEY (`nominal`) REFERENCES `saldo` (`nominal`);

--
-- Constraints for table `trans_jual`
--
ALTER TABLE `trans_jual`
  ADD CONSTRAINT `trans_jual_ibfk_1` FOREIGN KEY (`no_pln`) REFERENCES `pelanggan` (`no_pln`),
  ADD CONSTRAINT `trans_jual_ibfk_2` FOREIGN KEY (`id_akun`) REFERENCES `akun` (`id_akun`),
  ADD CONSTRAINT `trans_jual_ibfk_3` FOREIGN KEY (`harga`) REFERENCES `token_listrik` (`harga`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
