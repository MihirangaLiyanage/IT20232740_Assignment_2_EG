-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 09:02 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `power_grid`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `Bill_ID` int(10) NOT NULL COMMENT 'Bill ID',
  `Customer_Name` varchar(30) NOT NULL COMMENT 'Customer Name',
  `Customer_Account` varchar(10) NOT NULL COMMENT 'Customer Account',
  `Date` varchar(19) NOT NULL COMMENT 'Date',
  `Units_Used` varchar(10) NOT NULL COMMENT 'Units Used',
  `Amount` varchar(20) NOT NULL COMMENT 'Amount'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`Bill_ID`, `Customer_Name`, `Customer_Account`, `Date`, `Units_Used`, `Amount`) VALUES
(1, 'Mihiranga', '19028368', '2021-05-20', '150', '5500'),
(2, 'Suran', '45643456', '2021-01-23', '180', '6800'),
(3, 'Dilani', '17892367', '2022-02-06', '200', '9800'),
(4, 'Thevindu1', '56787636', '2022-03-12', '120', '3500'),
(10, 'Naruto', '66787664', '2021-01-23', '160', '4500');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`Bill_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `Bill_ID` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Bill ID', AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
