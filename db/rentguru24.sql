-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 18, 2016 at 07:20 PM
-- Server version: 5.6.31-0ubuntu0.14.04.2
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
-- Table structure for table `activation`
--

CREATE TABLE IF NOT EXISTS `activation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_login_credential_id` int(11) NOT NULL,
  `code` varchar(500) NOT NULL,
  `expire_date` date NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `activation_app_login_credential` (`app_login_credential_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `app_login_credential`
--

CREATE TABLE IF NOT EXISTS `app_login_credential` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_inf_id` int(11) NOT NULL,
  `role` int(11) NOT NULL,
  `email` varchar(500) NOT NULL,
  `oldPassword` varchar(500) NOT NULL,
  `accesstoken` varchar(500) NOT NULL,
  `varified` tinyint(1) NOT NULL,
  `blocked` tinyint(1) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_unser_inf_id` (`user_inf_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=46 ;

--
-- Dumping data for table `app_login_credential`
--

INSERT INTO `app_login_credential` (`id`, `user_inf_id`, `role`, `email`, `oldPassword`, `accesstoken`, `varified`, `blocked`, `created_date`) VALUES
(32, 33, -1, 'mousum@workspace.com', 'e10adc3949ba59abbe56e057f20f883e', '61ec56ac9b9442af7cda8b85977e7849', 0, 0, '2016-08-08 07:14:22'),
(33, 34, -1, 'tomal@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '5f08323684a25d460fbd4a574f64af96', 0, 0, '2016-08-08 08:02:14'),
(34, 35, -1, 'developer@wsit.com', 'e10adc3949ba59abbe56e057f20f883e', '84e2761bca190cb87d9e0306f27b6c82', 0, 0, '2016-08-08 08:24:21'),
(35, 36, -1, 'rafi@workspace.com', 'e10adc3949ba59abbe56e057f20f883e', '273f1a8b06d9e0063f2bb9ed21bbf624', 0, 0, '2016-08-08 08:56:09'),
(36, 37, -1, 'mausum@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'f54f2b3b33936bd2d91a6ec219d62485', 0, 0, '2016-08-08 09:09:28'),
(37, 38, -1, 'fayme@work.com', 'e10adc3949ba59abbe56e057f20f883e', '85e7bc630a1f1259ce0b0c1e88366cd2', 0, 0, '2016-08-08 09:40:15'),
(38, 39, -1, 'f@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '34ac9072ee5000706b88e49c67d58af8', 0, 0, '2016-08-08 11:34:16'),
(39, 40, -1, 'tmina@yahoo.com', '6182a61e266111245440159ef6901169', '5e514d2bb99e4beb79815e2a2bcccfbe', 0, 0, '2016-08-09 07:43:33'),
(40, 41, -1, 'lima@yahoo.com', '6182a61e266111245440159ef6901169', '1642e525a70dee1eb3352ed35220011d', 0, 0, '2016-08-09 10:53:17'),
(41, 42, -1, 'e@sdf.com', 'd58e3582afa99040e27b92b13c8f2280', '54ba615ccb87199abaaa485199e38dc8', 0, 0, '2016-08-09 12:29:42'),
(42, 43, -1, 'dsa@sdf.com', 'a8f5f167f44f4964e6c998dee827110c', '47dbc11357bb1d0645dfddf39de6a840', 0, 0, '2016-08-09 12:30:35'),
(43, 44, -1, 'asd@sdf.comn', '89aaaeeda8a4f50e0ad8fb8ad6e6e437', '85effe24aea679cf4ce8ffa3f9284305', 0, 0, '2016-08-09 12:31:21'),
(44, 45, -1, 'dummy20@tummy.com', '5a2dd3b5557333af7d0d89a8790379e9', '9c98b4bc3ada6a81c946bc278c531c91', 0, 0, '2016-08-10 12:07:05'),
(45, 46, -1, 'dummy21@tummy.com', '5a2dd3b5557333af7d0d89a8790379e9', 'e2e3a9a544ccedef1e317a57d351bc47', 0, 0, '2016-08-10 12:10:22');

-- --------------------------------------------------------

--
-- Table structure for table `attributes`
--

CREATE TABLE IF NOT EXISTS `attributes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `attributes_app_login_credential` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `attribute_values`
--

CREATE TABLE IF NOT EXISTS `attribute_values` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attributes_id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `created_by` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `attribute_values_app_login_credential` (`created_by`),
  KEY `attribute_values_attributes` (`attributes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `is_subcategory` tinyint(1) NOT NULL DEFAULT '0',
  `sorted_order` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `category_app_login_credential` (`created_by`),
  KEY `category_category` (`parent_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `parent_id`, `is_subcategory`, `sorted_order`, `created_by`, `created_date`) VALUES
(8, 'Home', NULL, 0, 1, 0, '2016-08-08 09:04:04'),
(9, 'HOME APPLIANCE', NULL, 0, 2, 0, '2016-08-08 09:27:59'),
(10, 'FURNITURE', NULL, 0, 3, 0, '2016-08-08 09:27:08'),
(11, 'GAMING & PARTY', NULL, 0, 4, 0, '2016-08-08 09:27:08'),
(12, 'COOL GADGETS', NULL, 0, 5, 0, '2016-08-08 09:27:52'),
(13, 'BLOGS', NULL, 0, 6, 0, '2016-08-08 09:27:52'),
(14, 'Swedish washing machine', 9, 1, 7, 0, '2016-08-17 11:35:09');

-- --------------------------------------------------------

--
-- Table structure for table `identity_type`
--

CREATE TABLE IF NOT EXISTS `identity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `identity_type`
--

INSERT INTO `identity_type` (`id`, `name`, `created_date`) VALUES
(1, 'Passport', '2016-08-05 05:19:24'),
(2, 'National ID', '2016-08-03 08:02:10');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `description` text NOT NULL,
  `average_rating` float(5,2) NOT NULL DEFAULT '0.00',
  `profile_image` text NOT NULL,
  `other_images` text,
  `current_value` double(200,2) NOT NULL,
  `rent_fee` double(8,2) NOT NULL,
  `rent_type_id` int(11) NOT NULL DEFAULT '1',
  `active` tinyint(1) NOT NULL,
  `currently_available` tinyint(1) NOT NULL,
  `available_from` datetime NOT NULL,
  `available_till` datetime NOT NULL,
  `review_status` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_app_login_credential` (`owner_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `owner_id`, `name`, `description`, `average_rating`, `profile_image`, `other_images`, `current_value`, `rent_fee`, `rent_type_id`, `active`, `currently_available`, `available_from`, `available_till`, `review_status`, `created_date`) VALUES
(12, 40, 'CK Tempting Glimmer Sheer Creme EyeShadow - 303 Baby Blue', 'Offers high shine high impact colour Lightweight & extra-smooth finish.', 2.00, '{"original":{"path":"product/40/26594005394083.jpg","type":"","size":{"width":458,"height":458}},"thumb":[]}', '[]', 1000.00, 900.00, 1, 1, 0, '2016-08-09 00:00:00', '2016-10-09 00:00:00', 0, '2016-08-16 09:44:46'),
(14, 32, 'dsf', 'sdf', 0.00, '{"original":{"path":"product/32/815788920270.jpg","type":"","size":{"width":660,"height":371}},"thumb":[]}', '[]', 34.00, 3.00, 2, 1, 1, '2016-08-24 00:00:00', '2016-08-25 00:00:00', 0, '2016-08-12 04:42:01'),
(17, 32, 'dsf', 'sdf', 0.00, '{"original":{"path":"product/32/1087095250392.jpg","type":"","size":{"width":660,"height":371}},"thumb":[]}', '[]', 34.00, 3.00, 2, 1, 1, '2016-08-24 00:00:00', '2016-08-25 00:00:00', 0, '2016-08-12 04:46:32'),
(19, 32, 'dsf', 'sdf', 0.00, '{"original":{"path":"product/32/1278511427233.jpg","type":"","size":{"width":660,"height":371}},"thumb":[]}', '[]', 34.00, 3.00, 2, 1, 1, '2016-08-24 00:00:00', '2016-08-25 00:00:00', 0, '2016-08-12 04:49:44');

-- --------------------------------------------------------

--
-- Table structure for table `product_attribute`
--

CREATE TABLE IF NOT EXISTS `product_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `attribute_values_id` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_attribute_attribute_values` (`attribute_values_id`),
  KEY `product_attribute_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `product_availability`
--

CREATE TABLE IF NOT EXISTS `product_availability` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `from_date` datetime NOT NULL,
  `to_date` datetime NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `product_availability`
--

INSERT INTO `product_availability` (`id`, `product_id`, `from_date`, `to_date`, `created_date`) VALUES
(1, 12, '2016-08-18 00:00:00', '2016-08-26 00:00:00', '2016-08-11 10:46:48'),
(2, 12, '2016-08-25 00:00:00', '2016-08-26 00:00:00', '2016-08-11 10:46:48');

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--

CREATE TABLE IF NOT EXISTS `product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_category_category` (`category_id`),
  KEY `product_category_product` (`product_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` (`id`, `product_id`, `category_id`, `created_date`) VALUES
(11, 12, 9, '2016-08-09 11:24:47'),
(12, 14, 9, '2016-08-12 04:42:01'),
(15, 17, 8, '2016-08-12 04:46:32'),
(17, 19, 8, '2016-08-12 04:49:44'),
(18, 19, 10, '2016-08-12 04:49:44');

-- --------------------------------------------------------

--
-- Table structure for table `product_liked`
--

CREATE TABLE IF NOT EXISTS `product_liked` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `app_credential_id` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_id` (`app_credential_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `product_location`
--

CREATE TABLE IF NOT EXISTS `product_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `city` varchar(200) DEFAULT NULL,
  `state` varchar(200) DEFAULT NULL,
  `formatted_address` text NOT NULL,
  `zip` varchar(200) DEFAULT NULL,
  `lat` float(10,6) DEFAULT NULL,
  `lng` float(10,6) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `product_location`
--

INSERT INTO `product_location` (`id`, `product_id`, `city`, `state`, `formatted_address`, `zip`, `lat`, `lng`, `created_date`) VALUES
(5, 12, 'dhaka', 'Dhaka', 'Nikunja-2, Khilkhet 2324', '234s', 10.000000, 30.000000, '2016-08-09 11:24:47'),
(6, 14, NULL, NULL, 'Banani', 'wdf', 3.000000, 34.000000, '2016-08-12 04:42:01'),
(7, 17, NULL, NULL, 'Banani', 'wdf', 3.000000, 34.000000, '2016-08-12 04:46:32'),
(8, 19, NULL, NULL, 'Banani', 'wdf', 3.000000, 34.000000, '2016-08-12 04:49:44');

-- --------------------------------------------------------

--
-- Table structure for table `product_rating`
--

CREATE TABLE IF NOT EXISTS `product_rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `app_credential_id` int(11) NOT NULL,
  `rate_value` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_id` (`app_credential_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `product_rating`
--

INSERT INTO `product_rating` (`id`, `product_id`, `app_credential_id`, `rate_value`, `created_date`) VALUES
(6, 12, 32, 2, '2016-08-16 09:45:56');

-- --------------------------------------------------------

--
-- Table structure for table `product_received`
--

CREATE TABLE IF NOT EXISTS `product_received` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rent_product_id` int(11) NOT NULL,
  `is_received` tinyint(1) NOT NULL,
  `received_date` datetime NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `rent_product_id` (`rent_product_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `product_received`
--

INSERT INTO `product_received` (`id`, `rent_product_id`, `is_received`, `received_date`, `created`) VALUES
(1, 1, 1, '2016-08-11 00:00:00', '2016-08-11 12:57:50');

-- --------------------------------------------------------

--
-- Table structure for table `product_returned`
--

CREATE TABLE IF NOT EXISTS `product_returned` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rent_product_id` int(11) NOT NULL,
  `is_returned` tinyint(1) NOT NULL,
  `returned_date` datetime NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `rent_product_id` (`rent_product_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `product_returned`
--

INSERT INTO `product_returned` (`id`, `rent_product_id`, `is_returned`, `returned_date`, `created`) VALUES
(1, 1, 0, '2016-08-11 00:00:00', '2016-08-11 12:58:15');

-- --------------------------------------------------------

--
-- Table structure for table `rent_product`
--

CREATE TABLE IF NOT EXISTS `rent_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rent_request_id` int(11) NOT NULL,
  `rentee_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `ends_date` date NOT NULL,
  `product_returned` tinyint(1) NOT NULL,
  `product_received` tinyint(1) NOT NULL,
  `expired` tinyint(1) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `rent_request_id` (`rent_request_id`),
  KEY `product_id` (`product_id`),
  KEY `rentee_id` (`rentee_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `rent_product`
--

INSERT INTO `rent_product` (`id`, `rent_request_id`, `rentee_id`, `product_id`, `start_date`, `ends_date`, `product_returned`, `product_received`, `expired`, `created_date`) VALUES
(1, 50, 32, 12, '2016-09-22', '2016-09-24', 0, 0, 0, '2016-08-18 12:54:15'),
(2, 48, 32, 12, '2016-10-22', '2016-12-24', 0, 0, 0, '2016-08-18 13:11:02');

-- --------------------------------------------------------

--
-- Table structure for table `rent_request`
--

CREATE TABLE IF NOT EXISTS `rent_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `requested_by` int(11) NOT NULL,
  `request_id` int(11) DEFAULT NULL COMMENT 'Request extension',
  `expired` tinyint(1) NOT NULL DEFAULT '0',
  `request_cancel` int(11) NOT NULL DEFAULT '0',
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `approve` tinyint(1) NOT NULL DEFAULT '0',
  `disapprove` tinyint(1) NOT NULL DEFAULT '0',
  `extension` tinyint(1) NOT NULL DEFAULT '0',
  `remark` text,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `rent_request_app_login_credential` (`requested_by`),
  KEY `rent_request_product` (`product_id`),
  KEY `rent_request_rent_request` (`request_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=51 ;

--
-- Dumping data for table `rent_request`
--

INSERT INTO `rent_request` (`id`, `product_id`, `requested_by`, `request_id`, `expired`, `request_cancel`, `start_date`, `end_date`, `approve`, `disapprove`, `extension`, `remark`, `created_date`) VALUES
(28, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 1, 0, 0, NULL, '2016-08-18 05:17:31'),
(29, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 1, 0, NULL, '2016-08-18 05:31:36'),
(30, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-17 07:04:02'),
(31, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-17 07:09:22'),
(32, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-17 07:09:32'),
(33, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-17 07:10:26'),
(34, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-17 07:11:57'),
(35, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-17 07:22:23'),
(36, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-17 07:22:54'),
(37, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-17 07:40:22'),
(38, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-17 08:13:20'),
(39, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-17 08:23:58'),
(40, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-17 08:29:00'),
(41, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, '', '2016-08-17 08:29:06'),
(42, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, 'sdfsdfsdf', '2016-08-17 08:29:10'),
(43, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, '', '2016-08-17 08:29:22'),
(44, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-17 08:29:33'),
(45, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-17 08:30:23'),
(46, 12, 32, NULL, 0, 1, '2016-10-22', '2016-12-24', 0, 0, 0, '                ', '2016-08-18 05:45:38'),
(47, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 1, 0, NULL, '2016-08-18 05:38:30'),
(48, 12, 32, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, NULL, '2016-08-18 13:11:18'),
(49, 17, 40, NULL, 0, 0, '2016-10-22', '2016-12-24', 0, 0, 0, 'fgdfgdfg', '2016-08-17 13:06:59'),
(50, 17, 40, NULL, 0, 0, '2016-09-22', '2016-09-24', 0, 0, 0, 'fgdfgdfg', '2016-08-17 13:07:22');

-- --------------------------------------------------------

--
-- Table structure for table `rent_type`
--

CREATE TABLE IF NOT EXISTS `rent_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `rent_type`
--

INSERT INTO `rent_type` (`id`, `name`, `created_date`) VALUES
(1, 'Day', '2016-08-11 13:03:54'),
(2, 'Week', '2016-08-11 13:03:58'),
(3, 'Month', '2016-08-11 13:04:00'),
(4, 'Year', '2016-08-11 13:04:10');

-- --------------------------------------------------------

--
-- Table structure for table `temp_file`
--

CREATE TABLE IF NOT EXISTS `temp_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` int(11) NOT NULL,
  `path` text NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=141 ;

--
-- Dumping data for table `temp_file`
--

INSERT INTO `temp_file` (`id`, `token`, `path`, `created_date`) VALUES
(11, 1000679317, 'temp/50776.pdf', '2016-08-03 07:41:17'),
(12, 1000137523, 'temp/412577.pdf', '2016-08-03 07:41:18'),
(15, 1000194970, 'temp/866872.pdf', '2016-08-03 09:01:00'),
(17, 1000200247, 'temp/341075.txt', '2016-08-04 05:05:31'),
(18, 1000581495, 'temp/558777.pdf', '2016-08-04 10:41:05'),
(19, 1000886923, 'temp/394287.pdf', '2016-08-04 12:19:20'),
(20, 1000275102, 'temp/79343.pdf', '2016-08-04 12:20:48'),
(21, 1000382921, 'temp/297617.jpg', '2016-08-05 04:32:25'),
(22, 1000201163, 'temp/628582.jpg', '2016-08-05 04:35:49'),
(23, 1000251568, 'temp/561451.jpg', '2016-08-05 04:37:51'),
(24, 1000757564, 'temp/802967.jpg', '2016-08-05 04:42:06'),
(25, 1000523402, 'temp/136099.jpg', '2016-08-05 04:47:10'),
(26, 1000035646, 'temp/739176.jpg', '2016-08-05 04:51:31'),
(27, 1000892336, 'temp/580548.jpg', '2016-08-05 04:54:07'),
(28, 1000637817, 'temp/880250.jpg', '2016-08-05 05:02:07'),
(29, 1000353128, 'temp/703313.jpg', '2016-08-05 05:02:30'),
(30, 1000140795, 'temp/645652.jpg', '2016-08-05 05:03:08'),
(31, 1000162905, 'temp/90471.jpg', '2016-08-05 05:04:07'),
(32, 1000300265, 'temp/3786.jpg', '2016-08-05 05:04:10'),
(33, 1000711794, 'temp/426036.jpg', '2016-08-05 05:05:22'),
(34, 1000377675, 'temp/577154.jpg', '2016-08-05 05:06:09'),
(35, 1000272032, 'temp/672056.jpg', '2016-08-05 05:10:12'),
(37, 1000021573, 'temp/567777.jpg', '2016-08-05 06:50:31'),
(38, 1000198621, 'temp/765954.jpg', '2016-08-05 06:53:10'),
(45, 1000654374, 'temp/555199.jpg', '2016-08-05 08:57:29'),
(46, 1000332405, 'temp/100268.jpg', '2016-08-05 09:03:49'),
(47, 1000293841, 'temp/124478.jpg', '2016-08-05 09:06:01'),
(48, 1000336627, 'temp/39403.jpg', '2016-08-05 09:06:59'),
(49, 1000427071, 'temp/348469.jpg', '2016-08-05 09:08:03'),
(50, 1000004267, 'temp/486098.jpg', '2016-08-05 09:09:17'),
(55, 1000514770, 'temp/786679.pdf', '2016-08-05 09:40:09'),
(58, 1000541350, 'temp/529431.jpeg', '2016-08-05 10:47:42'),
(59, 1000509038, 'temp/32653.jpg', '2016-08-05 11:03:03'),
(60, 1000622465, 'temp/24583.jpg', '2016-08-05 11:12:50'),
(61, 1000244241, 'temp/195807.jpg', '2016-08-05 11:14:01'),
(62, 1000076031, 'temp/825716.documentIdentity', '2016-08-08 06:26:22'),
(63, 1000160416, 'temp/563853.documentIdentity', '2016-08-08 06:54:07'),
(64, 1000341724, 'temp/389658.documentIdentity', '2016-08-08 06:56:05'),
(65, 1000260198, 'temp/350402.documentIdentity', '2016-08-08 07:00:15'),
(66, 1000357063, 'temp/262241.documentIdentity', '2016-08-08 07:02:05'),
(67, 1000064960, 'temp/270270.documentIdentity', '2016-08-08 07:03:01'),
(68, 1000195274, 'temp/359652.documentIdentity', '2016-08-08 07:03:58'),
(69, 1000266772, 'temp/143603.documentIdentity', '2016-08-08 07:05:06'),
(70, 1000667714, 'temp/246999.documentIdentity', '2016-08-08 07:11:51'),
(74, 1000875880, 'temp/323240.documentIdentity', '2016-08-08 08:46:03'),
(75, 1000574946, 'temp/894765.documentIdentity', '2016-08-08 08:47:48'),
(76, 1000633065, 'temp/11400.documentIdentity', '2016-08-08 08:49:17'),
(77, 1000350978, 'temp/403572.documentIdentity', '2016-08-08 08:50:39'),
(78, 1000664351, 'temp/446335.documentIdentity', '2016-08-08 08:52:26'),
(79, 1000777366, 'temp/617910.documentIdentity', '2016-08-08 08:55:46'),
(81, 1000594149, 'temp/652106.jpg', '2016-08-08 08:58:59'),
(84, 1000813693, 'temp/140925.jpg', '2016-08-08 09:52:58'),
(85, 1000198824, 'temp/660275.jpg', '2016-08-08 11:29:03'),
(87, 1000097015, 'temp/234421.jpg', '2016-08-08 11:40:32'),
(88, 1000290017, 'temp/572056.jpg', '2016-08-08 11:41:50'),
(89, 1000002751, 'temp/399091.jpg', '2016-08-08 11:49:24'),
(90, 1000502502, 'temp/236041.jpg', '2016-08-08 11:54:51'),
(91, 1000082742, 'temp/749173.jpg', '2016-08-08 12:04:00'),
(92, 1000644783, 'temp/10584.jpg', '2016-08-08 12:09:00'),
(93, 1000059578, 'temp/234166.jpg', '2016-08-08 12:12:41'),
(94, 1000588189, 'temp/450549.jpg', '2016-08-08 12:13:27'),
(95, 1000303493, 'temp/804723.jpg', '2016-08-08 12:17:06'),
(96, 1000283700, 'temp/655476.jpg', '2016-08-08 12:50:32'),
(97, 1000615665, 'temp/621005.jpg', '2016-08-08 12:50:39'),
(98, 1000187093, 'temp/609603.jpg', '2016-08-08 12:54:05'),
(99, 1000743794, 'temp/16013.jpg', '2016-08-09 05:01:03'),
(100, 1000568186, 'temp/156594.jpg', '2016-08-09 05:01:10'),
(101, 1000394798, 'temp/215930.jpg', '2016-08-09 05:01:22'),
(102, 1000197513, 'temp/739258.jpg', '2016-08-09 05:04:29'),
(103, 1000271035, 'temp/585410.jpg', '2016-08-09 05:04:36'),
(104, 1000096477, 'temp/646499.jpg', '2016-08-09 05:04:46'),
(105, 1000842329, 'temp/900648.jpg', '2016-08-09 05:06:46'),
(106, 1000597942, 'temp/899053.jpg', '2016-08-09 05:06:53'),
(107, 1000046079, 'temp/574627.jpg', '2016-08-09 05:07:03'),
(108, 1000310827, 'temp/26153.jpg', '2016-08-09 05:12:44'),
(109, 1000875695, 'temp/598325.jpg', '2016-08-09 05:12:53'),
(110, 1000577960, 'temp/115189.jpg', '2016-08-09 05:13:07'),
(111, 1000577030, 'temp/242255.jpg', '2016-08-09 05:14:45'),
(112, 1000118780, 'temp/310928.jpg', '2016-08-09 07:04:17'),
(113, 1000162534, 'temp/357803.jpg', '2016-08-09 07:04:27'),
(114, 1000641342, 'temp/32137.jpg', '2016-08-09 07:04:37'),
(115, 1000759998, 'temp/809561.jpg', '2016-08-09 07:05:03'),
(117, 1000760495, 'temp/460853.jpg', '2016-08-09 09:41:44'),
(118, 1000013352, 'temp/540129.jpg', '2016-08-09 09:55:45'),
(119, 1000273473, 'temp/860506.jpg', '2016-08-09 09:57:20'),
(120, 1000460961, 'temp/774823.docx', '2016-08-09 10:47:33'),
(121, 1000739416, 'temp/757254.docx', '2016-08-09 10:50:47'),
(123, 1000359935, 'temp/637452.png', '2016-08-09 10:56:33'),
(124, 1000475194, 'temp/114463.jpg', '2016-08-09 11:14:19'),
(125, 1000223598, 'temp/232240.jpg', '2016-08-09 11:15:54'),
(126, 1000185479, 'temp/531200.jpg', '2016-08-09 11:24:36'),
(127, 1000880238, 'temp/617836.jpg', '2016-08-11 13:40:16'),
(128, 1000741350, 'temp/104787.jpg', '2016-08-11 13:46:25'),
(129, 1000409236, 'temp/9455.jpg', '2016-08-11 13:47:03'),
(130, 1000443843, 'temp/721869.jpg', '2016-08-11 13:50:02'),
(131, 1000493493, 'temp/308365.jpg', '2016-08-11 13:51:09'),
(132, 1000053671, 'temp/190790.jpg', '2016-08-11 13:51:37'),
(133, 1000546137, 'temp/499471.jpg', '2016-08-11 13:53:24'),
(134, 1000631157, 'temp/858968.jpg', '2016-08-12 04:39:38'),
(135, 1000458381, 'temp/859789.jpg', '2016-08-12 04:41:54'),
(136, 1000530735, 'temp/749024.jpg', '2016-08-12 04:43:28'),
(137, 1000568425, 'temp/538099.jpg', '2016-08-12 04:44:55'),
(138, 1000675201, 'temp/374906.jpg', '2016-08-12 04:46:17'),
(139, 1000362158, 'temp/704563.jpg', '2016-08-12 04:47:25'),
(140, 1000504935, 'temp/886440.jpg', '2016-08-12 04:49:30');

-- --------------------------------------------------------

--
-- Table structure for table `user_address`
--

CREATE TABLE IF NOT EXISTS `user_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` text NOT NULL,
  `zip` varchar(20) NOT NULL,
  `city` varchar(200) NOT NULL,
  `state` varchar(200) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

--
-- Dumping data for table `user_address`
--

INSERT INTO `user_address` (`id`, `address`, `zip`, `city`, `state`, `created_date`) VALUES
(1, 'sdf', 'sdf', 'sdf', 'sdf', '2016-08-02 12:41:56'),
(2, '', '', '', '', '2016-08-04 12:47:12'),
(3, '', '', '', '', '2016-08-04 12:53:50'),
(4, '', '', '', '', '2016-08-04 13:17:31'),
(5, '', '', '', '', '2016-08-05 05:32:10'),
(6, '', '', '', '', '2016-08-05 06:54:46'),
(7, '', '', '', '', '2016-08-05 06:56:14'),
(8, '', '', '', '', '2016-08-05 06:57:24'),
(9, '', '', '', '', '2016-08-05 06:59:28'),
(10, '', '', '', '', '2016-08-05 07:04:06'),
(11, '', '', '', '', '2016-08-05 07:11:14'),
(12, '', '', '', '', '2016-08-05 09:22:41'),
(13, '', '', '', '', '2016-08-05 09:25:29'),
(14, '', '', '', '', '2016-08-05 09:32:03'),
(15, '', '', '', '', '2016-08-05 09:37:43'),
(16, '', '', '', '', '2016-08-05 09:40:14'),
(17, '', '', '', '', '2016-08-05 09:42:55'),
(18, '', '', '', '', '2016-08-05 12:11:04'),
(19, '', '', '', '', '2016-08-08 07:14:22'),
(20, '', '', '', '', '2016-08-08 08:02:14'),
(21, '', '', '', '', '2016-08-08 08:24:21'),
(22, '', '', '', '', '2016-08-08 08:56:09'),
(23, '', '', '', '', '2016-08-08 09:09:28'),
(24, '', '', '', '', '2016-08-08 09:40:15'),
(25, '', '', '', '', '2016-08-08 11:34:16'),
(26, '', '', '', '', '2016-08-09 07:43:33'),
(27, '', '', '', '', '2016-08-09 10:53:17'),
(28, '', '', '', '', '2016-08-09 12:29:42'),
(29, '', '', '', '', '2016-08-09 12:30:35'),
(30, '', '', '', '', '2016-08-09 12:31:21'),
(32, '', '', '', '', '2016-08-10 12:07:05'),
(33, '', '', '', '', '2016-08-10 12:10:22');

-- --------------------------------------------------------

--
-- Table structure for table `user_inf`
--

CREATE TABLE IF NOT EXISTS `user_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_address_id` int(11) DEFAULT NULL,
  `identity_type_id` int(11) NOT NULL,
  `first_name` varchar(500) NOT NULL,
  `last_name` varchar(500) NOT NULL,
  `identity_doc_path` text NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `identity_type` (`identity_type_id`),
  KEY `user_inf_user_address` (`user_address_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=47 ;

--
-- Dumping data for table `user_inf`
--

INSERT INTO `user_inf` (`id`, `user_address_id`, `identity_type_id`, `first_name`, `last_name`, `identity_doc_path`, `created_date`) VALUES
(33, 19, 1, 'Mausum', 'Nandi', 'identityDoc/32/10300038890383.documentIdentity', '2016-08-08 07:14:22'),
(34, 20, 1, 'Taiful', 'Islam', 'identityDoc/33/13172418657778.documentIdentity', '2016-08-08 08:02:14'),
(35, 21, 1, 'developer', 'wsit', 'identityDoc/34/14499115145635.jpg', '2016-08-08 08:24:21'),
(36, 22, 1, 'Maidul', 'Rafi', 'identityDoc/35/16406680045445.documentIdentity', '2016-08-08 08:56:09'),
(37, 23, 1, 'Mausum', 'Nandy', 'identityDoc/36/17206486365557.documentIdentity', '2016-08-08 09:09:28'),
(38, 24, 1, 'fayme', 'Pauli', 'identityDoc/37/19053048474532.documentIdentity', '2016-08-08 09:40:15'),
(39, 25, 2, 'fa', 'y me', 'identityDoc/38/25894182279794.docx', '2016-08-08 11:34:16'),
(40, 26, 1, 'Tahmina', 'A', 'identityDoc/39/13320013602830.docx', '2016-08-09 07:43:33'),
(41, 27, 2, 'Lima', 'N', 'identityDoc/40/24704427305911.docx', '2016-08-09 10:53:17'),
(42, 28, 1, 'xc', 'zxc', 'identityDoc/41/30488810011776.png', '2016-08-09 12:29:42'),
(43, 29, 2, 'asd', 'sad', 'identityDoc/42/30542282440316.png', '2016-08-09 12:30:35'),
(44, 30, 1, 'asd', 'asd', 'identityDoc/43/30587637477572.png', '2016-08-09 12:31:21'),
(45, 32, 1, 'dummy', 'tummy', 'identityDoc/44/26962367826164.docx', '2016-08-10 12:07:05'),
(46, 33, 1, 'dummy', 'tummy', 'identityDoc/45/27159631397218.docx', '2016-08-10 12:10:22');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activation`
--
ALTER TABLE `activation`
  ADD CONSTRAINT `activation_app_login_credential` FOREIGN KEY (`app_login_credential_id`) REFERENCES `app_login_credential` (`id`);

--
-- Constraints for table `app_login_credential`
--
ALTER TABLE `app_login_credential`
  ADD CONSTRAINT `app_credential_unser_inf_id` FOREIGN KEY (`user_inf_id`) REFERENCES `user_inf` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `attributes`
--
ALTER TABLE `attributes`
  ADD CONSTRAINT `attributes_app_login_credential` FOREIGN KEY (`created_by`) REFERENCES `app_login_credential` (`id`);

--
-- Constraints for table `attribute_values`
--
ALTER TABLE `attribute_values`
  ADD CONSTRAINT `attribute_values_app_login_credential` FOREIGN KEY (`created_by`) REFERENCES `app_login_credential` (`id`),
  ADD CONSTRAINT `attribute_values_attributes` FOREIGN KEY (`attributes_id`) REFERENCES `attributes` (`id`);

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `category_category` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_app_login_credential` FOREIGN KEY (`owner_id`) REFERENCES `app_login_credential` (`id`);

--
-- Constraints for table `product_attribute`
--
ALTER TABLE `product_attribute`
  ADD CONSTRAINT `product_attribute_attribute_values` FOREIGN KEY (`attribute_values_id`) REFERENCES `attribute_values` (`id`),
  ADD CONSTRAINT `product_attribute_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product_availability`
--
ALTER TABLE `product_availability`
  ADD CONSTRAINT `product_availability_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `product_category`
--
ALTER TABLE `product_category`
  ADD CONSTRAINT `product_category_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `product_category_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product_liked`
--
ALTER TABLE `product_liked`
  ADD CONSTRAINT `app_login_credential_app_credential_id` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `product_liked_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `product_location`
--
ALTER TABLE `product_location`
  ADD CONSTRAINT `product_location_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `product_rating`
--
ALTER TABLE `product_rating`
  ADD CONSTRAINT ` product_rating_app_credential_id` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT ` product_rating_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rent_product`
--
ALTER TABLE `rent_product`
  ADD CONSTRAINT `rent_product_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rent_product_rent_request` FOREIGN KEY (`rent_request_id`) REFERENCES `rent_request` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rent_product_rentee_id` FOREIGN KEY (`rentee_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rent_request`
--
ALTER TABLE `rent_request`
  ADD CONSTRAINT `rent_request_app_login_credential` FOREIGN KEY (`requested_by`) REFERENCES `app_login_credential` (`id`),
  ADD CONSTRAINT `rent_request_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `rent_request_rent_request` FOREIGN KEY (`request_id`) REFERENCES `rent_request` (`id`);

--
-- Constraints for table `user_inf`
--
ALTER TABLE `user_inf`
  ADD CONSTRAINT `user_inf_identity_type` FOREIGN KEY (`identity_type_id`) REFERENCES `identity_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `user_inf_user_address` FOREIGN KEY (`user_address_id`) REFERENCES `user_address` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
