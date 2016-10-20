-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 20, 2016 at 06:38 PM
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
-- Table structure for table `product_rating`
--

CREATE TABLE IF NOT EXISTS `product_rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `rent_inf_id` int(11) NOT NULL,
  `rent_request_id` int(11) NOT NULL,
  `app_credential_id` int(11) NOT NULL,
  `rate_value` int(11) NOT NULL,
  `review_text` text,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_id` (`app_credential_id`),
  KEY `product_id` (`product_id`),
  KEY `rent_inf_id` (`rent_inf_id`),
  KEY `rent_request_id` (`rent_request_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product_rating`
--
ALTER TABLE `product_rating`
  ADD CONSTRAINT ` product_rating_app_credential_id` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT ` product_rating_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `product_rating_rent_inf_id` FOREIGN KEY (`rent_inf_id`) REFERENCES `rent_inf` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `product_rent_rent_inf_id` FOREIGN KEY (`rent_inf_id`) REFERENCES `rent_inf` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `product_rent_rent_request_id` FOREIGN KEY (`rent_request_id`) REFERENCES `rent_request` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;