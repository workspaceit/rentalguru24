-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 27, 2016 at 05:04 PM
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
-- Table structure for table `payout`
--

CREATE TABLE IF NOT EXISTS `payout` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `rent_inf_id` int(11) NOT NULL,
  `app_credential_id` int(11) NOT NULL,
  `state` enum('pending','completed') NOT NULL COMMENT 'pending = Payment have not been transferred to user paypal account ',
  `total_amount` double(9,5) NOT NULL,
  `transaction_id` varchar(200) DEFAULT NULL,
  `transaction_status` varchar(200) DEFAULT NULL,
  `payout_batch_id` varchar(200) DEFAULT NULL,
  `paypal_payment_date` datetime DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_id` (`app_credential_id`),
  KEY `rent_inf_id` (`rent_inf_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `payout`
--
ALTER TABLE `payout`
  ADD CONSTRAINT `payout_app_credential_id` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rent_inf_id` FOREIGN KEY (`rent_inf_id`) REFERENCES `rent_inf` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
