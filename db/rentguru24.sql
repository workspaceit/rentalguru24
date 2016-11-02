-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 02, 2016 at 12:08 PM
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
-- Table structure for table `admin_cms_page`
--

CREATE TABLE IF NOT EXISTS `admin_cms_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `page_key` varchar(100) NOT NULL,
  `page_name` varchar(100) NOT NULL,
  `page_content` text NOT NULL,
  `sorted_order` int(3) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Table structure for table `admin_global_notification`
--

CREATE TABLE IF NOT EXISTS `admin_global_notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `template_id` int(11) NOT NULL,
  `details` text,
  `type` enum('dispute') NOT NULL,
  `rent_inf_id` int(11) DEFAULT NULL,
  `is_read` tinyint(1) NOT NULL,
  `read_by` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `read_by` (`read_by`),
  KEY `rent_inf_id` (`rent_inf_id`),
  KEY `template_id` (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `admin_global_notification_template`
--

CREATE TABLE IF NOT EXISTS `admin_global_notification_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('dispute') NOT NULL,
  `title` text NOT NULL,
  `template` text NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `admin_paypal_credential`
--

CREATE TABLE IF NOT EXISTS `admin_paypal_credential` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `api_key` varchar(500) NOT NULL,
  `api_secret` varchar(500) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `admin_site_fees`
--

CREATE TABLE IF NOT EXISTS `admin_site_fees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fixed` tinyint(1) NOT NULL,
  `percentage` tinyint(1) NOT NULL,
  `fixed_value` float(7,2) NOT NULL,
  `percentage_value` float(7,2) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `admin_unread_alert_count`
--

CREATE TABLE IF NOT EXISTS `admin_unread_alert_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `global_notification` int(11) NOT NULL,
  PRIMARY KEY (`id`)
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
  `password` varchar(500) NOT NULL,
  `accesstoken` varchar(500) NOT NULL,
  `varified` tinyint(1) NOT NULL,
  `email_confirmed` tinyint(1) NOT NULL DEFAULT '1',
  `blocked` tinyint(1) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_unser_inf_id` (`user_inf_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=80 ;

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
-- Table structure for table `banner_image`
--

CREATE TABLE IF NOT EXISTS `banner_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_path` varchar(200) NOT NULL,
  `url` varchar(200) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

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
  `picture` text,
  `created_by` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `category_app_login_credential` (`created_by`),
  KEY `category_category` (`parent_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

-- --------------------------------------------------------

--
-- Table structure for table `cron_last_executed`
--

CREATE TABLE IF NOT EXISTS `cron_last_executed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rent_request_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Table structure for table `cron_log`
--

CREATE TABLE IF NOT EXISTS `cron_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=64 ;

-- --------------------------------------------------------

--
-- Table structure for table `current_balance`
--

CREATE TABLE IF NOT EXISTS `current_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_credential_id` int(11) NOT NULL,
  `amount` double(11,5) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_id` (`app_credential_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `developer_exception_log`
--

CREATE TABLE IF NOT EXISTS `developer_exception_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `details` text NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `device_info`
--

CREATE TABLE IF NOT EXISTS `device_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_credential_id` int(11) NOT NULL,
  `device_id` varchar(200) NOT NULL,
  `os` int(11) NOT NULL COMMENT 'IOS = 1 , ANDROID = 2',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_id` (`app_credential_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `email_confirmation`
--

CREATE TABLE IF NOT EXISTS `email_confirmation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_credential_id` int(11) NOT NULL,
  `token` varchar(200) NOT NULL,
  `already_used` int(11) NOT NULL DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_credential_id_2` (`app_credential_id`),
  KEY `app_credential_id` (`app_credential_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

-- --------------------------------------------------------

--
-- Table structure for table `identity_type`
--

CREATE TABLE IF NOT EXISTS `identity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE IF NOT EXISTS `password_resets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_credential_id` int(11) NOT NULL,
  `token` varchar(500) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app` (`app_credential_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

-- --------------------------------------------------------

--
-- Table structure for table `payment_refund`
--

CREATE TABLE IF NOT EXISTS `payment_refund` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_credential_id` int(11) NOT NULL,
  `payment_id` bigint(11) NOT NULL,
  `total_amount` double(9,5) NOT NULL,
  `parent_pay_id` varchar(200) DEFAULT NULL,
  `paypal_sale_id` varchar(200) NOT NULL,
  `paypal_refund_id` varchar(200) DEFAULT NULL,
  `paypal_payment_date` datetime DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_id` (`app_credential_id`),
  KEY `payment_id` (`payment_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

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
  KEY `product_app_login_credential` (`owner_id`),
  KEY `FKln4s7fuq1817jdxhcg74s21hj` (`rent_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

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
  `lat` double(13,9) DEFAULT NULL,
  `lng` double(13,9) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `rental_product_returned`
--

CREATE TABLE IF NOT EXISTS `rental_product_returned` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rent_inf_id` int(11) NOT NULL,
  `confirm` tinyint(1) NOT NULL DEFAULT '0',
  `dispute` tinyint(1) NOT NULL DEFAULT '0',
  `expired` tinyint(1) NOT NULL DEFAULT '0',
  `rentee_remarks` text,
  `renter_remarks` text,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `rent_product_id_2` (`rent_inf_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Table structure for table `rental_product_returned_history`
--

CREATE TABLE IF NOT EXISTS `rental_product_returned_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_returned_id` int(11) NOT NULL,
  `confirm` tinyint(1) NOT NULL DEFAULT '0',
  `dispute` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_returned_id` (`product_returned_id`),
  KEY `product_returned_id_2` (`product_returned_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

-- --------------------------------------------------------

--
-- Table structure for table `rental_product_return_request`
--

CREATE TABLE IF NOT EXISTS `rental_product_return_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `rent_inf_id` int(11) NOT NULL,
  `expired` tinyint(1) NOT NULL DEFAULT '0',
  `remarks` text,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`,`rent_inf_id`),
  KEY `rent_product_id` (`rent_inf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `rent_inf`
--

CREATE TABLE IF NOT EXISTS `rent_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rent_request_id` int(11) NOT NULL,
  `rentee_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `ends_date` date NOT NULL,
  `product_returned` tinyint(1) NOT NULL,
  `product_received` tinyint(1) NOT NULL,
  `expired` tinyint(1) NOT NULL,
  `rent_complete` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `rent_request_id` (`rent_request_id`),
  KEY `product_id` (`product_id`),
  KEY `rentee_id` (`rentee_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Table structure for table `rent_payment`
--

CREATE TABLE IF NOT EXISTS `rent_payment` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_credential_id` int(11) DEFAULT NULL,
  `rent_request_id` int(11) DEFAULT NULL,
  `rent_inf_id` int(11) DEFAULT NULL,
  `site_fee` double NOT NULL DEFAULT '0',
  `rent_fee` double(9,5) NOT NULL,
  `refund_amount` double NOT NULL,
  `total_amount` double(9,5) NOT NULL,
  `transaction_fee` double(6,2) NOT NULL,
  `currency` varchar(5) DEFAULT NULL,
  `authorization_id` varchar(200) DEFAULT NULL,
  `paypal_payer_id` varchar(200) DEFAULT NULL,
  `paypal_pay_id` varchar(200) DEFAULT NULL,
  `paypal_sale_id` varchar(200) DEFAULT NULL,
  `paypal_payment_date` datetime DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_id` (`app_credential_id`),
  KEY `rent_inf_id` (`rent_inf_id`),
  KEY `rent_request_id` (`rent_request_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;

-- --------------------------------------------------------

--
-- Table structure for table `rent_request`
--

CREATE TABLE IF NOT EXISTS `rent_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `requested_by` int(11) NOT NULL,
  `rent_fee` double(6,2) NOT NULL,
  `advance_amount` double(6,2) NOT NULL,
  `request_id` int(11) DEFAULT NULL COMMENT 'Request extension',
  `expired` tinyint(1) NOT NULL DEFAULT '0',
  `request_cancel` int(11) NOT NULL DEFAULT '0',
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `approve` tinyint(1) NOT NULL DEFAULT '0',
  `disapprove` tinyint(1) NOT NULL DEFAULT '0',
  `extension` tinyint(1) NOT NULL DEFAULT '0',
  `remark` text,
  `rent_complete` tinyint(1) NOT NULL,
  `payment_complete` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `rent_request_app_login_credential` (`requested_by`),
  KEY `rent_request_product` (`product_id`),
  KEY `rent_request_rent_request` (`request_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=141 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=246 ;

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_credential_id` int(11) NOT NULL,
  `payment_id` bigint(11) DEFAULT NULL,
  `refund_id` bigint(20) DEFAULT NULL,
  `payout_id` bigint(20) DEFAULT NULL,
  `type` enum('deposite','payment','payout','refund','received_rent_fee') NOT NULL,
  `details` text NOT NULL,
  `dr` double(9,5) NOT NULL,
  `cr` double(9,5) NOT NULL,
  `cumulative_dr` double(11,5) NOT NULL,
  `cumulative_cr` double(11,5) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_id` (`app_credential_id`),
  KEY `payment_id` (`payment_id`),
  KEY `refund_id` (`refund_id`),
  KEY `payout_id` (`payout_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user_address`
--

CREATE TABLE IF NOT EXISTS `user_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` text,
  `zip` varchar(20) DEFAULT NULL,
  `city` varchar(200) DEFAULT NULL,
  `state` varchar(200) DEFAULT NULL,
  `lat` double(13,9) DEFAULT NULL,
  `lng` double(13,9) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=70 ;

-- --------------------------------------------------------

--
-- Table structure for table `user_inf`
--

CREATE TABLE IF NOT EXISTS `user_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_address_id` int(11) DEFAULT NULL,
  `identity_type_id` int(11) DEFAULT NULL,
  `first_name` varchar(500) NOT NULL,
  `last_name` varchar(500) NOT NULL,
  `profile_pic` text,
  `identity_doc_path` text,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `identity_type` (`identity_type_id`),
  KEY `user_inf_user_address` (`user_address_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=81 ;

-- --------------------------------------------------------

--
-- Table structure for table `user_paypal_credential`
--

CREATE TABLE IF NOT EXISTS `user_paypal_credential` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_credential_id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_credential_id` (`app_credential_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activation`
--
ALTER TABLE `activation`
  ADD CONSTRAINT `activation_app_login_credential` FOREIGN KEY (`app_login_credential_id`) REFERENCES `app_login_credential` (`id`);

--
-- Constraints for table `admin_global_notification`
--
ALTER TABLE `admin_global_notification`
  ADD CONSTRAINT `: admin_global_notification_template_id` FOREIGN KEY (`template_id`) REFERENCES `admin_global_notification_template` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `admin_global_notification_read_by` FOREIGN KEY (`read_by`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `admin_global_notification_rent_inf_id` FOREIGN KEY (`rent_inf_id`) REFERENCES `rent_inf` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `app_login_credential`
--
ALTER TABLE `app_login_credential`
  ADD CONSTRAINT `FKe4ymatsj2ooqxvsilddn8gm18` FOREIGN KEY (`user_inf_id`) REFERENCES `user_inf` (`id`),
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
  ADD CONSTRAINT `FKsdv2mmlr5ql1jh9y8v7xldnpx` FOREIGN KEY (`attributes_id`) REFERENCES `attributes` (`id`),
  ADD CONSTRAINT `attribute_values_app_login_credential` FOREIGN KEY (`created_by`) REFERENCES `app_login_credential` (`id`),
  ADD CONSTRAINT `attribute_values_attributes` FOREIGN KEY (`attributes_id`) REFERENCES `attributes` (`id`);

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FK2y94svpmqttx80mshyny85wqr` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `category_category` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `device_info`
--
ALTER TABLE `device_info`
  ADD CONSTRAINT `device_info_app_credential_id` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `email_confirmation`
--
ALTER TABLE `email_confirmation`
  ADD CONSTRAINT `app_login_credential_account_confirmation` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD CONSTRAINT `password_reset_app_credential_id` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `payment_refund`
--
ALTER TABLE `payment_refund`
  ADD CONSTRAINT `payment_refund_app_credential_id` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `payment_refund_payment_id` FOREIGN KEY (`payment_id`) REFERENCES `rent_payment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `payout`
--
ALTER TABLE `payout`
  ADD CONSTRAINT `payout_app_credential_id` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rent_inf_id` FOREIGN KEY (`rent_inf_id`) REFERENCES `rent_inf` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1eghvssbn7wvaaw0wyuom9oe0` FOREIGN KEY (`owner_id`) REFERENCES `app_login_credential` (`id`),
  ADD CONSTRAINT `FKln4s7fuq1817jdxhcg74s21hj` FOREIGN KEY (`rent_type_id`) REFERENCES `rent_type` (`id`),
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
  ADD CONSTRAINT `FK2k3smhbruedlcrvu6clued06x` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKkud35ls1d40wpjb5htpp14q4e` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `product_category_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `product_category_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product_liked`
--
ALTER TABLE `product_liked`
  ADD CONSTRAINT `FK8l2tmap6x86bax3ptc8lbuaev` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`),
  ADD CONSTRAINT `FKhbu26ux8f4ile47coe7qxawcf` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
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
  ADD CONSTRAINT ` product_rating_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `product_rating_rent_inf_id` FOREIGN KEY (`rent_inf_id`) REFERENCES `rent_inf` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `product_rent_rent_inf_id` FOREIGN KEY (`rent_inf_id`) REFERENCES `rent_inf` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `product_rent_rent_request_id` FOREIGN KEY (`rent_request_id`) REFERENCES `rent_request` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rental_product_returned`
--
ALTER TABLE `rental_product_returned`
  ADD CONSTRAINT `product_returned_rent_product_id` FOREIGN KEY (`rent_inf_id`) REFERENCES `rent_inf` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rental_product_returned_history`
--
ALTER TABLE `rental_product_returned_history`
  ADD CONSTRAINT `product_returned_history_product_returned_id` FOREIGN KEY (`product_returned_id`) REFERENCES `rental_product_returned` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rental_product_return_request`
--
ALTER TABLE `rental_product_return_request`
  ADD CONSTRAINT `FKq22espjqbg5w5ehhtahrhoyuu` FOREIGN KEY (`rent_inf_id`) REFERENCES `rent_inf` (`id`),
  ADD CONSTRAINT `FKt91udmx9wva0tng2h2tihvjkf` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `request_product_return_productid` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `request_product_return_rpid` FOREIGN KEY (`rent_inf_id`) REFERENCES `rent_inf` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rent_inf`
--
ALTER TABLE `rent_inf`
  ADD CONSTRAINT `FKpji1gl0wk5bp6horjvboc9pdx` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKqf9yqapca3lt13h4pwjudun7c` FOREIGN KEY (`rent_request_id`) REFERENCES `rent_request` (`id`),
  ADD CONSTRAINT `rent_product_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rent_product_rent_request` FOREIGN KEY (`rent_request_id`) REFERENCES `rent_request` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rent_product_rentee_id` FOREIGN KEY (`rentee_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rent_payment`
--
ALTER TABLE `rent_payment`
  ADD CONSTRAINT `rent_payment_app_credential_id` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rent_payment_rent_inf_id` FOREIGN KEY (`rent_inf_id`) REFERENCES `rent_inf` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rent_payment_rent_request_id` FOREIGN KEY (`rent_request_id`) REFERENCES `rent_request` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rent_request`
--
ALTER TABLE `rent_request`
  ADD CONSTRAINT `FK7uf4ynnkep50aokr0mfu57km0` FOREIGN KEY (`request_id`) REFERENCES `rent_request` (`id`),
  ADD CONSTRAINT `FKa4pt960yimr4ioulhhi47umia` FOREIGN KEY (`requested_by`) REFERENCES `app_login_credential` (`id`),
  ADD CONSTRAINT `FKp8eka0j8hacs3kuwv6n6v78i0` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `rent_request_app_login_credential` FOREIGN KEY (`requested_by`) REFERENCES `app_login_credential` (`id`),
  ADD CONSTRAINT `rent_request_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `rent_request_rent_request` FOREIGN KEY (`request_id`) REFERENCES `rent_request` (`id`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transection_app_credential_id` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `transection_payment_id` FOREIGN KEY (`payment_id`) REFERENCES `rent_payment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `transection_payout_id` FOREIGN KEY (`payout_id`) REFERENCES `payout` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `transection_refund_id` FOREIGN KEY (`refund_id`) REFERENCES `payment_refund` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_inf`
--
ALTER TABLE `user_inf`
  ADD CONSTRAINT `FK8xvlcxq6qd8fxwao83trys367` FOREIGN KEY (`identity_type_id`) REFERENCES `identity_type` (`id`),
  ADD CONSTRAINT `user_inf_identity_type` FOREIGN KEY (`identity_type_id`) REFERENCES `identity_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `user_inf_user_address` FOREIGN KEY (`user_address_id`) REFERENCES `user_address` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_paypal_credential`
--
ALTER TABLE `user_paypal_credential`
  ADD CONSTRAINT `user_paypal_credential_app_credential_id` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
