-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 21, 2016 at 06:40 PM
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

--
-- Dumping data for table `admin_cms_page`
--

INSERT INTO `admin_cms_page` (`id`, `page_key`, `page_name`, `page_content`, `sorted_order`, `created_date`) VALUES
(1, 'google', 'Google', '<div class="row"><div class="col-sm-12"><table id="example1" class="table table-bordered table-striped dataTable" role="grid" aria-describedby="example1_info">\n                <thead>\n                <tr role="row"><th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Page Name: activate to sort column descending" style="width: 474px;">Page Name</th><th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="View Page: activate to sort column ascending" style="width: 446px;">View Page</th><th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Action: activate to sort column ascending" style="width: 584px;">Action</th></tr>\n                </thead>\n                <tbody>\n                \n                \n                \n                \n                \n                \n                \n                <tr role="row" class="odd">\n\n                  <td class="sorting_1">Google</td>\n                  <td>\n                    <a href="http://localhost:9090/static/google" target="_blank">click here</a>\n                  </td>\n                  <td>\n                    <button type="button" class="btn btn-primary">Edit</button>\n                    <button type="button" class="btn btn-danger">Delete</button>\n                  </td>\n                </tr><tr role="row" class="even">\n\n                  <td class="sorting_1">Tumbler</td>\n                  <td>\n                    <a href="http://localhost:9090/static/tumbler" target="_blank">click here</a>\n                  </td>\n                  <td>\n                    <button type="button" class="btn btn-primary">Edit</button>\n                    <button type="button" class="btn btn-danger">Delete</button>\n                  </td>\n                </tr><tr role="row" class="odd">\n\n                  <td class="sorting_1">Yahoo</td>\n                  <td>\n                    <a href="http://localhost:9090/static/yahoo" target="_blank">click here</a>\n                  </td>\n                  <td>\n                    <button type="button" class="btn btn-primary">Edit</button>\n                    <button type="button" class="btn btn-danger">Delete</button>\n                  </td>\n                </tr></tbody>\n                <tfoot>\n                <tr><th rowspan="1" colspan="1">Page Name</th><th rowspan="1" colspan="1">View Page</th><th rowspan="1" colspan="1">Action</th></tr>\n                </tfoot>\n              </table></div></div>', 1, '2016-09-08 09:22:00'),
(2, 'yahoo', 'Yahoo', '<a href="http://www.google.com"> google</a>', 1, '2016-09-08 06:33:07'),
(3, 'tumbler', 'Tumbler', '<a href="http://www.google.com"> google</a>', 1, '2016-09-08 06:33:07');

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

--
-- Dumping data for table `admin_paypal_credential`
--

INSERT INTO `admin_paypal_credential` (`id`, `api_key`, `api_secret`, `created_date`) VALUES
(1, 'AWQr0Ls0qt0zRtXFvSBZ2k3zNgt-0ME5eI6qC8A9dTh2RHodYtDre5cJT7BNElg9mm3dZw6v9F-G-vyn', 'EAiElxCy_o6-h3VKR_iAGIwUVisEUInSXQwbdgRo-Fd8cKUujB2RH86LTXHwOzgUAAGY6Lbm0Nu3kV9q', '2016-09-20 09:49:22');

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

--
-- Dumping data for table `admin_site_fees`
--

INSERT INTO `admin_site_fees` (`id`, `fixed`, `percentage`, `fixed_value`, `percentage_value`, `created_date`) VALUES
(1, 1, 0, 10.25, 5.25, '2016-08-29 09:03:26');

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
  `blocked` tinyint(1) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_unser_inf_id` (`user_inf_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=71 ;

--
-- Dumping data for table `app_login_credential`
--

INSERT INTO `app_login_credential` (`id`, `user_inf_id`, `role`, `email`, `password`, `accesstoken`, `varified`, `blocked`, `created_date`) VALUES
(32, 33, -1, 'mousum@workspace.com', 'e10adc3949ba59abbe56e057f20f883e', '61ec56ac9b9442af7cda8b85977e7849', 1, 0, '2016-09-07 06:52:20'),
(33, 34, -1, 'tomal@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '5f08323684a25d460fbd4a574f64af96', 1, 0, '2016-08-25 05:36:49'),
(34, 35, 1, 'admin@admin.com', 'e10adc3949ba59abbe56e057f20f883e', 'c92610120e28f6858e358b2c5e7bbf53', 1, 0, '2016-09-07 09:35:06'),
(35, 36, -1, 'rafi@workspace.com', 'e10adc3949ba59abbe56e057f20f883e', '273f1a8b06d9e0063f2bb9ed21bbf624', 1, 0, '2016-08-25 05:36:49'),
(36, 37, -1, 'mausum@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'f54f2b3b33936bd2d91a6ec219d62485', 1, 0, '2016-08-25 05:36:49'),
(37, 38, -1, 'fayme@work.com', 'e10adc3949ba59abbe56e057f20f883e', '85e7bc630a1f1259ce0b0c1e88366cd2', 1, 0, '2016-08-25 05:36:49'),
(38, 39, -1, 'f@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '34ac9072ee5000706b88e49c67d58af8', 1, 0, '2016-08-25 05:36:49'),
(39, 40, -1, 'tmina@yahoo.com', '6182a61e266111245440159ef6901169', '5e514d2bb99e4beb79815e2a2bcccfbe', 1, 0, '2016-08-25 05:36:49'),
(40, 41, -1, 'lima@yahoo.com', 'e10adc3949ba59abbe56e057f20f883e', '1642e525a70dee1eb3352ed35220011d', 1, 0, '2016-09-07 07:18:04'),
(41, 42, -1, 'e@sdf.com', 'd58e3582afa99040e27b92b13c8f2280', '54ba615ccb87199abaaa485199e38dc8', 1, 0, '2016-08-25 05:36:49'),
(42, 43, -1, 'dsa@sdf.com', 'a8f5f167f44f4964e6c998dee827110c', '47dbc11357bb1d0645dfddf39de6a840', 0, 0, '2016-08-25 05:36:49'),
(43, 44, -1, 'asd@sdf.comn', '89aaaeeda8a4f50e0ad8fb8ad6e6e437', '85effe24aea679cf4ce8ffa3f9284305', 0, 0, '2016-08-25 05:36:49'),
(44, 45, -1, 'dummy20@tummy.com', '5a2dd3b5557333af7d0d89a8790379e9', '9c98b4bc3ada6a81c946bc278c531c91', 1, 0, '2016-08-25 05:36:49'),
(45, 46, -1, 'dummy21@tummy.com', '5a2dd3b5557333af7d0d89a8790379e9', 'e2e3a9a544ccedef1e317a57d351bc47', 1, 0, '2016-08-25 05:36:49'),
(46, 47, -1, 'modon@chand.com', 'e10adc3949ba59abbe56e057f20f883e', 'b93d8b1f80f8138952d5efc58ec1cd15', 1, 0, '2016-08-25 05:36:49'),
(47, 48, -1, 'sdfsdf@sdfsdf.com', '89400617397134a1fc9c9fbcb7e1d10a', '27dd99c4e7a880f1803ce2268c4b0806', 1, 0, '2016-08-25 10:04:00'),
(48, 49, -1, 'sdfd@sdf.com', '73a90acaae2b1ccc0e969709665bc62f', '05dfffc43184c7fe95f7b9c20bddf8af', 1, 0, '2016-08-25 10:57:16'),
(49, 50, -1, 'dfg@sdf.com', '73a90acaae2b1ccc0e969709665bc62f', '6ddeeff683d14043a081a86c127a96ef', 1, 0, '2016-08-25 11:00:35'),
(69, 70, -1, 'app.developer.wsit@gmail.com', '222ba8674e84e6e0ef4b00d7d8db5c98', '0d6d4a288c5f2439dbfa42892687673f', 1, 0, '2016-09-03 10:30:00'),
(70, 71, -1, 'rafi@workspaceit.com', 'e10adc3949ba59abbe56e057f20f883e', '09559c281f6aaeef1f4762ecd0237f2d', 1, 0, '2016-09-03 10:44:50');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

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
(14, 'Swedish washing machine', 9, 1, 7, 0, '2016-08-17 11:35:09'),
(15, 'HOME SUBCAT ', 14, 1, 8, 0, '2016-09-07 11:45:18');

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
-- Table structure for table `identity_type`
--

CREATE TABLE IF NOT EXISTS `identity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `identity_type`
--

INSERT INTO `identity_type` (`id`, `name`, `created_date`) VALUES
(1, 'Passport', '2016-08-05 05:19:24'),
(2, 'National ID', '2016-08-03 08:02:10'),
(3, '', '2016-09-02 09:26:13');

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE IF NOT EXISTS `password_resets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_credential_id` int(11) NOT NULL,
  `token` varchar(500) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app` (`app_credential_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `password_resets`
--

INSERT INTO `password_resets` (`id`, `app_credential_id`, `token`, `created_at`) VALUES
(4, 70, '04dfd0b1b093a9a8dd9c14e3b1ec4e49', '2016-09-07 08:56:19'),
(5, 32, '63807392f5031e01f35e9d290e7c0e0c', '2016-09-08 08:28:33');

-- --------------------------------------------------------

--
-- Table structure for table `payment_refund`
--

CREATE TABLE IF NOT EXISTS `payment_refund` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_credential_id` int(11) NOT NULL,
  `payment_id` bigint(11) NOT NULL,
  `total_amount` double(9,5) NOT NULL,
  `paypal_payer_id` varchar(200) NOT NULL,
  `paypal_pay_id` varchar(200) NOT NULL,
  `paypal_transection` varchar(200) NOT NULL,
  `paypal_payment_date` datetime NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_id` (`app_credential_id`),
  KEY `payment_id` (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `payout`
--

CREATE TABLE IF NOT EXISTS `payout` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_credential_id` int(11) NOT NULL,
  `total_amount` double(9,5) NOT NULL,
  `paypal_payer_id` varchar(200) NOT NULL,
  `paypal_pay_id` varchar(200) NOT NULL,
  `paypal_transection` varchar(200) NOT NULL,
  `paypal_payment_date` datetime NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_id` (`app_credential_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `owner_id`, `name`, `description`, `average_rating`, `profile_image`, `other_images`, `current_value`, `rent_fee`, `rent_type_id`, `active`, `currently_available`, `available_from`, `available_till`, `review_status`, `created_date`) VALUES
(12, 40, 'CK Tempting Glimmer Sheer Creme EyeShadow - 303 Baby Blue', 'Offers high shine high impact colour Lightweight & extra-smooth finish.', 5.00, '{"original":{"path":"product/40/26594005394083.jpg","type":"","size":{"width":458,"height":458}},"thumb":[]}', '[]', 1.00, 0.05, 1, 1, 1, '2016-08-30 00:00:00', '2016-11-09 00:00:00', 1, '2016-09-21 10:58:10'),
(14, 32, 'PRODUCT 1', 'PRODUCT 1 DEs', 3.00, '{"original":{"path":"product/32/815788920270.jpg","type":"","size":{"width":660,"height":371}},"thumb":[]}', '[]', 2.00, 0.06, 2, 1, 1, '2016-08-24 00:00:00', '2016-12-25 00:00:00', 1, '2016-09-21 05:47:33'),
(17, 32, 'PRODUCT 2', 'PRODUCT 2 des', 4.00, '{"original":{"path":"product/32/1087095250392.jpg","type":"","size":{"width":660,"height":371}},"thumb":[]}', '[]', 3.00, 0.50, 1, 1, 1, '2016-08-24 00:00:00', '2016-12-25 00:00:00', 1, '2016-09-21 05:47:53'),
(19, 32, 'PRODUCT 3', 'PRODUCT 3 des', 0.00, '{"original":{"path":"product/32/1278511427233.jpg","type":"","size":{"width":660,"height":371}},"thumb":[]}', '[]', 4.00, 1.00, 2, 1, 1, '2016-08-24 00:00:00', '2016-12-25 00:00:00', 1, '2016-09-21 05:47:56');

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
(12, 14, 14, '2016-09-08 08:05:56'),
(15, 17, 8, '2016-08-12 04:46:32'),
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `product_liked`
--

INSERT INTO `product_liked` (`id`, `product_id`, `app_credential_id`, `created_date`) VALUES
(1, 12, 41, '2016-08-22 09:09:58');

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
(5, 12, 'dhaka', NULL, 'Nikunja-2, Khilkhet 2324', '234s', 10.000000, 30.000000, '2016-08-29 12:07:44'),
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `rental_product_returned`
--

INSERT INTO `rental_product_returned` (`id`, `rent_inf_id`, `confirm`, `dispute`, `expired`, `rentee_remarks`, `renter_remarks`, `created_date`) VALUES
(3, 12, 0, 0, 0, NULL, NULL, '2016-09-19 12:42:47');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `rental_product_return_request`
--

INSERT INTO `rental_product_return_request` (`id`, `product_id`, `rent_inf_id`, `expired`, `remarks`, `created_date`) VALUES
(4, 19, 13, 0, NULL, '2016-09-19 01:38:28');

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
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `rent_request_id` (`rent_request_id`),
  KEY `product_id` (`product_id`),
  KEY `rentee_id` (`rentee_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `rent_inf`
--

INSERT INTO `rent_inf` (`id`, `rent_request_id`, `rentee_id`, `product_id`, `start_date`, `ends_date`, `product_returned`, `product_received`, `expired`, `created_date`) VALUES
(12, 79, 40, 19, '2016-09-01', '2016-09-02', 1, 0, 0, '2016-09-19 07:36:28'),
(13, 80, 40, 19, '2016-09-08', '2016-09-10', 0, 0, 0, '2016-09-19 07:38:21');

-- --------------------------------------------------------

--
-- Table structure for table `rent_payment`
--

CREATE TABLE IF NOT EXISTS `rent_payment` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_credential_id` int(11) DEFAULT NULL,
  `rent_request_id` int(11) DEFAULT NULL,
  `rent_inf_id` int(11) DEFAULT NULL,
  `rent_fee` double(9,5) NOT NULL,
  `refund_amount` double NOT NULL,
  `total_amount` double(9,5) NOT NULL,
  `transaction_fee` double(6,2) NOT NULL,
  `currency` varchar(5) DEFAULT NULL,
  `paypal_payer_id` varchar(200) DEFAULT NULL,
  `paypal_pay_id` varchar(200) DEFAULT NULL,
  `paypal_sale_id` varchar(200) DEFAULT NULL,
  `paypal_payment_date` datetime DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_id` (`app_credential_id`),
  KEY `rent_inf_id` (`rent_inf_id`),
  KEY `rent_request_id` (`rent_request_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `rent_payment`
--

INSERT INTO `rent_payment` (`id`, `app_credential_id`, `rent_request_id`, `rent_inf_id`, `rent_fee`, `refund_amount`, `total_amount`, `transaction_fee`, `currency`, `paypal_payer_id`, `paypal_pay_id`, `paypal_sale_id`, `paypal_payment_date`, `created_date`) VALUES
(4, NULL, NULL, 12, 0.00000, 0, 3.00000, 0.40, 'USD', 'TKCD9W66CR9R4', 'PAY-18T77298U47141249K7RCTEQ', '5PS32330RM0068418', NULL, '2016-09-21 07:15:29'),
(5, NULL, NULL, 12, 0.00000, 0, 3.00000, 0.40, 'USD', 'TKCD9W66CR9R4', 'PAY-4KU74976K79173456K7RDLFQ', '0TH333749H698892Y', NULL, '2016-09-21 07:25:25'),
(6, NULL, NULL, 12, 0.00000, 0, 3.00000, 0.40, 'USD', 'TKCD9W66CR9R4', 'PAY-4H8532625P956981MK7RDMJI', '8AK14111R81163129', NULL, '2016-09-21 07:27:17'),
(7, NULL, NULL, 12, 0.00000, 0, 3.00000, 0.40, 'USD', 'TKCD9W66CR9R4', 'PAY-1BP00538DT761201EK7RDNQQ', '303682890N2684901', NULL, '2016-09-21 07:30:05'),
(8, NULL, 81, 12, 0.00000, 0, 3.00000, 0.40, 'USD', 'TKCD9W66CR9R4', 'PAY-1JS453987L026705JK7RDOVI', '9TL46536MD439071L', NULL, '2016-09-21 07:35:50'),
(9, NULL, 81, NULL, 0.00000, 0, 3.00000, 0.40, 'USD', 'TKCD9W66CR9R4', 'PAY-23C64600ST4099403K7RCKPI', '4D960335MV7629508', NULL, '2016-09-21 10:10:41');

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
  `payment_complete` tinyint(1) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `rent_request_app_login_credential` (`requested_by`),
  KEY `rent_request_product` (`product_id`),
  KEY `rent_request_rent_request` (`request_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=83 ;

--
-- Dumping data for table `rent_request`
--

INSERT INTO `rent_request` (`id`, `product_id`, `requested_by`, `request_id`, `expired`, `request_cancel`, `start_date`, `end_date`, `approve`, `disapprove`, `extension`, `remark`, `payment_complete`, `created_date`) VALUES
(79, 19, 40, NULL, 0, 0, '2016-09-01', '2016-09-02', 1, 0, 0, 'Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....Remarks ..........!!!!!!!!!!!>.....', 0, '2016-09-01 09:34:17'),
(80, 19, 40, NULL, 0, 0, '2016-09-08', '2016-09-10', 1, 0, 0, NULL, 0, '2016-09-06 10:59:59'),
(81, 17, 40, NULL, 0, 0, '2016-09-19', '2016-09-20', 0, 0, 0, NULL, 1, '2016-09-19 05:18:09'),
(82, 12, 32, NULL, 0, 0, '2016-09-21', '2016-09-22', 0, 0, 0, NULL, 0, '2016-09-21 04:58:29');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=178 ;

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
(140, 1000504935, 'temp/886440.jpg', '2016-08-12 04:49:30'),
(141, 1000000054, 'temp/80071.txt', '2016-08-24 09:15:56'),
(142, 1000699850, 'temp/250284.txt', '2016-08-24 09:17:35'),
(143, 1000732860, 'temp/187931.txt', '2016-08-25 09:59:15'),
(145, 1000809813, 'temp/471386.jpg', '2016-08-25 10:28:41'),
(146, 1000055582, 'temp/809728.jpg', '2016-08-25 10:31:07'),
(147, 1000018199, 'temp/510776.jpg', '2016-08-25 10:33:53'),
(148, 1000353427, 'temp/789629.jpg', '2016-08-25 10:33:57'),
(149, 1000628083, 'temp/810872.jpg', '2016-08-25 10:35:55'),
(150, 1000474321, 'temp/446489.jpg', '2016-08-25 10:36:32'),
(151, 1000334650, 'temp/746848.pdf', '2016-09-06 06:50:26'),
(152, 1000365877, 'temp/517084.pdf', '2016-09-06 06:51:00'),
(153, 1000313381, 'temp/56682.pdf', '2016-09-06 06:51:27'),
(154, 1000387891, 'temp/625839.png', '2016-09-06 06:51:39'),
(155, 1000238071, 'temp/226387.png', '2016-09-06 07:56:08'),
(156, 1000862900, 'temp/696144.pdf', '2016-09-06 07:56:51'),
(157, 1000817144, 'temp/816512.pdf', '2016-09-06 07:58:03'),
(158, 1000009697, 'temp/518266.png', '2016-09-06 08:24:20'),
(159, 1000264076, 'temp/266383.png', '2016-09-06 08:24:51'),
(160, 1000703636, 'temp/230566.pdf', '2016-09-06 08:26:09'),
(161, 1000369952, 'temp/547150.pdf', '2016-09-06 08:29:08'),
(162, 1000123383, 'temp/225203.pdf', '2016-09-06 08:36:40'),
(163, 1000720357, 'temp/373481.pdf', '2016-09-06 08:38:44'),
(164, 1000684584, 'temp/689813.pdf', '2016-09-06 08:42:36'),
(165, 1000433853, 'temp/236052.pdf', '2016-09-06 08:42:57'),
(166, 1000400603, 'temp/581798.pdf', '2016-09-06 08:48:17'),
(167, 1000689635, 'temp/461592.pdf', '2016-09-06 08:48:25'),
(168, 1000185526, 'temp/418899.pdf', '2016-09-06 08:49:01'),
(169, 1000002369, 'temp/61478.pdf', '2016-09-06 08:49:07'),
(170, 1000261043, 'temp/58999.pdf', '2016-09-06 08:49:14'),
(171, 1000802735, 'temp/649130.pdf', '2016-09-06 08:49:32'),
(172, 1000430830, 'temp/463805.pdf', '2016-09-06 08:49:35'),
(173, 1000899201, 'temp/177505.pdf', '2016-09-06 08:51:13'),
(174, 1000347666, 'temp/369914.pdf', '2016-09-06 08:51:48'),
(175, 1000777363, 'temp/787448.pdf', '2016-09-06 08:53:21'),
(176, 1000533096, 'temp/223531.pdf', '2016-09-06 09:33:24'),
(177, 1000175651, 'temp/898515.png', '2016-09-06 10:49:57');

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
  `address` text NOT NULL,
  `zip` varchar(20) NOT NULL,
  `city` varchar(200) NOT NULL,
  `state` varchar(200) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=61 ;

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
(33, '', '', '', '', '2016-08-10 12:10:22'),
(34, '', '', '', '', '2016-08-24 09:19:43'),
(35, '', '', '', '', '2016-08-25 10:04:00'),
(36, '', '', '', '', '2016-08-25 10:57:16'),
(37, '', '', '', '', '2016-08-25 11:00:35'),
(43, '', '', '', '', '2016-09-02 09:26:13'),
(44, '', '', '', '', '2016-09-02 09:31:20'),
(45, '', '', '', '', '2016-09-02 09:37:55'),
(46, '', '', '', '', '2016-09-02 09:42:16'),
(47, '', '', '', '', '2016-09-02 10:25:37'),
(48, '', '', '', '', '2016-09-02 10:29:21'),
(49, '', '', '', '', '2016-09-02 10:31:37'),
(50, '', '', '', '', '2016-09-02 10:34:59'),
(51, '', '', '', '', '2016-09-02 10:36:17'),
(52, '', '', '', '', '2016-09-02 12:41:41'),
(53, '', '', '', '', '2016-09-03 08:56:58'),
(54, '', '', '', '', '2016-09-03 09:01:53'),
(55, '', '', '', '', '2016-09-03 09:06:53'),
(56, '', '', '', '', '2016-09-03 09:11:49'),
(57, '', '', '', '', '2016-09-03 09:13:58'),
(58, '', '', '', '', '2016-09-03 10:28:03'),
(59, '', '', '', '', '2016-09-03 10:30:00'),
(60, '', '', '', '', '2016-09-03 10:44:50');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=72 ;

--
-- Dumping data for table `user_inf`
--

INSERT INTO `user_inf` (`id`, `user_address_id`, `identity_type_id`, `first_name`, `last_name`, `profile_pic`, `identity_doc_path`, `created_date`) VALUES
(33, 19, 1, 'Mausum', 'Nandi', '{"original":{"path":"product/32/22855382016713.jpg","type":"","size":{"width":660,"height":371}},"thumb":[]}', 'identityDoc/32/10300038890383.documentIdentity', '2016-08-25 10:12:41'),
(34, 20, 1, 'Taiful', 'Islam', NULL, 'identityDoc/33/13172418657778.documentIdentity', '2016-08-08 08:02:14'),
(35, 21, 1, 'developer', 'wsit', 'null', 'identityDoc/34/14499115145635.jpg', '2016-08-08 08:24:21'),
(36, 22, 1, 'Maidul', 'Rafi', NULL, 'identityDoc/35/16406680045445.documentIdentity', '2016-08-08 08:56:09'),
(37, 23, 1, 'Mausum', 'Nandy', NULL, 'identityDoc/36/17206486365557.documentIdentity', '2016-08-08 09:09:28'),
(38, 24, 1, 'fayme', 'Pauli', NULL, 'identityDoc/37/19053048474532.documentIdentity', '2016-08-08 09:40:15'),
(39, 25, 2, 'fa', 'y me', NULL, 'identityDoc/38/25894182279794.docx', '2016-08-08 11:34:16'),
(40, 26, 1, 'Tahmina', 'A', NULL, 'identityDoc/39/13320013602830.docx', '2016-08-09 07:43:33'),
(41, 27, 2, 'Lima', 'N', NULL, 'identityDoc/40/24704427305911.docx', '2016-08-09 10:53:17'),
(42, 28, 1, 'xc', 'zxc', NULL, 'identityDoc/41/30488810011776.png', '2016-08-09 12:29:42'),
(43, 29, 2, 'asd', 'sad', NULL, 'identityDoc/42/30542282440316.png', '2016-08-09 12:30:35'),
(44, 30, 1, 'asd', 'asd', NULL, 'identityDoc/43/30587637477572.png', '2016-08-09 12:31:21'),
(45, 32, 1, 'dummy', 'tummy', NULL, 'identityDoc/44/26962367826164.docx', '2016-08-10 12:07:05'),
(46, 33, 1, 'dummy', 'tummy', NULL, 'identityDoc/45/27159631397218.docx', '2016-08-10 12:10:22'),
(47, 34, 1, 'Modon', 'Chand', NULL, 'identityDoc/46/18881855098272.txt', '2016-08-24 09:19:43'),
(48, 35, 1, 'sdfsdf', 'sdfsdf', NULL, 'identityDoc/47/20898679824950.txt', '2016-08-25 10:04:00'),
(49, 36, 2, 'sdfs', 'sdfsdf', 'null', 'identityDoc/48/24094491958505.txt', '2016-08-25 10:57:16'),
(50, 37, 2, 'Mausum', 'Nandi', '{"original":{"path":"","type":"","size":{"width":0,"height":0}},"thumb":[]}', 'identityDoc/49/24293334369307.txt', '2016-08-25 11:00:35'),
(70, 59, 1, 'Wsit', 'Dev', '{"original":{"path":"69/670884.jpg","type":"","size":{"width":375,"height":380}},"thumb":[]}', '', '2016-09-03 10:30:00'),
(71, 60, 1, 'Maieedul', 'Islam', '{"original":{"path":"70/186526.jpg","type":"","size":{"width":720,"height":720}},"thumb":[]}', '', '2016-09-03 10:44:50');

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
  ADD CONSTRAINT `payout_app_credential_id` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
  ADD CONSTRAINT `FKoyyqmab7rl4l8vbjnqiorvi32` FOREIGN KEY (`app_credential_id`) REFERENCES `app_login_credential` (`id`),
  ADD CONSTRAINT `FKt3mecsgki7hdg8srke4baeidk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;