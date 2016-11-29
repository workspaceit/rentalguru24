-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 29, 2016 at 01:25 PM
-- Server version: 5.6.33-0ubuntu0.14.04.1
-- PHP Version: 5.6.23-1+deprecated+dontuse+deb.sury.org~trusty+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `rentguru24`
--

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

CREATE TABLE IF NOT EXISTS `state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=53 ;

--
-- Dumping data for table `state`
--

INSERT INTO `state` (`id`, `code`, `name`, `created_date`) VALUES
(1, 'AL', 'Alabama', '2016-11-29 07:23:28'),
(2, 'AK', 'Alaska', '2016-11-29 07:23:28'),
(3, 'AZ', 'Arizona', '2016-11-29 07:23:28'),
(4, 'AR', 'Arkansas', '2016-11-29 07:23:28'),
(5, 'CA', 'California', '2016-11-29 07:23:28'),
(6, 'CO', 'Colorado', '2016-11-29 07:23:28'),
(7, 'CT', 'Connecticut', '2016-11-29 07:23:28'),
(8, 'DE', 'Delaware', '2016-11-29 07:23:28'),
(9, 'DC', 'District of Columbia', '2016-11-29 07:23:28'),
(10, 'FL', 'Florida', '2016-11-29 07:23:28'),
(11, 'GA', 'Georgia', '2016-11-29 07:23:28'),
(12, 'HI', 'Hawaii', '2016-11-29 07:23:28'),
(13, 'ID', 'Idaho', '2016-11-29 07:23:28'),
(14, 'IL', 'Illinois', '2016-11-29 07:23:28'),
(15, 'IN', 'Indiana', '2016-11-29 07:23:28'),
(16, 'IA', 'Iowa', '2016-11-29 07:23:28'),
(17, 'KS', 'Kansas', '2016-11-29 07:23:28'),
(18, 'KY', 'Kentucky', '2016-11-29 07:23:28'),
(19, 'LA', 'Louisiana', '2016-11-29 07:23:28'),
(20, 'ME', 'Maine', '2016-11-29 07:23:28'),
(21, 'MD', 'Maryland', '2016-11-29 07:23:28'),
(22, 'MA', 'Massachusetts', '2016-11-29 07:23:28'),
(23, 'MI', 'Michigan', '2016-11-29 07:23:28'),
(24, 'MN', 'Minnesota', '2016-11-29 07:23:28'),
(25, 'MS', 'Mississippi', '2016-11-29 07:23:28'),
(26, 'MO', 'Missouri', '2016-11-29 07:23:28'),
(27, 'MT', 'Montana', '2016-11-29 07:23:28'),
(28, 'NE', 'Nebraska', '2016-11-29 07:23:28'),
(29, 'NV', 'Nevada', '2016-11-29 07:23:28'),
(30, 'NH', 'New Hampshire', '2016-11-29 07:23:28'),
(31, 'NJ', 'New Jersey', '2016-11-29 07:23:28'),
(32, 'NM', 'New Mexico', '2016-11-29 07:23:28'),
(33, 'NY', 'New York', '2016-11-29 07:23:28'),
(34, 'NC', 'North Carolina', '2016-11-29 07:23:28'),
(35, 'ND', 'North Dakota', '2016-11-29 07:23:28'),
(36, 'OH', 'Ohio', '2016-11-29 07:23:28'),
(37, 'OK', 'Oklahoma', '2016-11-29 07:23:28'),
(38, 'OR', 'Oregon', '2016-11-29 07:23:28'),
(39, 'PA', 'Pennsylvania', '2016-11-29 07:23:28'),
(40, 'PR', 'Puerto Rico', '2016-11-29 07:23:28'),
(41, 'RI', 'Rhode Island', '2016-11-29 07:23:28'),
(42, 'SC', 'South Carolina', '2016-11-29 07:23:28'),
(43, 'SD', 'South Dakota', '2016-11-29 07:23:28'),
(44, 'TN', 'Tennessee', '2016-11-29 07:23:28'),
(45, 'TX', 'Texas', '2016-11-29 07:23:28'),
(46, 'UT', 'Utah', '2016-11-29 07:23:28'),
(47, 'VT', 'Vermont', '2016-11-29 07:23:28'),
(48, 'VA', 'Virginia', '2016-11-29 07:23:28'),
(49, 'WA', 'Washington', '2016-11-29 07:23:28'),
(50, 'WV', 'West Virginia', '2016-11-29 07:23:28'),
(51, 'WI', 'Wisconsin', '2016-11-29 07:23:28'),
(52, 'WY', 'Wyoming', '2016-11-29 07:23:28');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
