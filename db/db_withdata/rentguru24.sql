-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 17, 2017 at 11:42 AM
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

--
-- Dumping data for table `admin_cms_page`
--

INSERT INTO `admin_cms_page` (`id`, `page_key`, `page_name`, `page_content`, `sorted_order`, `created_date`) VALUES
(1, 'google', 'Google', '<div class="row"><div class="col-sm-12"><table id="example1" class="table table-bordered table-striped dataTable" role="grid" aria-describedby="example1_info">\n                <thead>\n                <tr role="row"><th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Page Name: activate to sort column descending" style="width: 474px;">Page Name</th><th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="View Page: activate to sort column ascending" style="width: 446px;">View Page</th><th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Action: activate to sort column ascending" style="width: 584px;">Action</th></tr>\n                </thead>\n                <tbody>\n                \n                \n                \n                \n                \n                \n                \n                <tr role="row" class="odd">\n\n                  <td class="sorting_1">Google</td>\n                  <td>\n                    <a href="http://localhost:9090/static/google" target="_blank">click here</a>\n                  </td>\n                  <td>\n                    <button type="button" class="btn btn-primary">Edit</button>\n                    <button type="button" class="btn btn-danger">Delete</button>\n                  </td>\n                </tr><tr role="row" class="even">\n\n                  <td class="sorting_1">Tumbler</td>\n                  <td>\n                    <a href="http://localhost:9090/static/tumbler" target="_blank">click here</a>\n                  </td>\n                  <td>\n                    <button type="button" class="btn btn-primary">Edit</button>\n                    <button type="button" class="btn btn-danger">Delete</button>\n                  </td>\n                </tr><tr role="row" class="odd">\n\n                  <td class="sorting_1">Yahoo</td>\n                  <td>\n                    <a href="http://localhost:9090/static/yahoo" target="_blank">click here</a>\n                  </td>\n                  <td>\n                    <button type="button" class="btn btn-primary">Edit</button>\n                    <button type="button" class="btn btn-danger">Delete</button>\n                  </td>\n                </tr></tbody>\n                <tfoot>\n                <tr><th rowspan="1" colspan="1">Page Name</th><th rowspan="1" colspan="1">View Page</th><th rowspan="1" colspan="1">Action</th></tr>\n                </tfoot>\n              </table></div></div>', 1, '2016-09-08 09:22:00'),
(2, 'yahoo', 'Yahoo', '<a href="http://www.google.com"> google</a>', 1, '2016-09-08 06:33:07'),
(3, 'tumbler', 'Tumbler', '<a href="http://www.google.com"> google</a>', 1, '2016-09-08 06:33:07');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=88 ;

--
-- Dumping data for table `app_login_credential`
--

INSERT INTO `app_login_credential` (`id`, `user_inf_id`, `role`, `email`, `password`, `accesstoken`, `varified`, `email_confirmed`, `blocked`, `created_date`) VALUES
(32, 33, -1, 'mousum@workspace.com', '$2a$10$OLOuRvQYBjtlchdWwvvDpO5nKCkioKTzY5khWwI8saOv.efSOIxlm', 'cb8176894ad9985c46a0d8ff98de39e4818e1cd6c5506da0b1a2ebcb3c510abe32', 1, 1, 0, '2016-12-27 06:22:02'),
(33, 34, -1, 'tomal@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '5f08323684a25d460fbd4a574f64af96', 1, 1, 0, '2016-12-27 06:22:02'),
(34, 35, 1, 'admin@admin.com', '$2a$10$IKohcmA6Nz3qv3rQ2oP5Zu89ltU.aUCvPuGLTnwXZAFyygPy4UrVa', 'c92610120e28f6858e358b2c5e7bbf53', 1, 1, 0, '2016-12-27 06:22:02'),
(35, 36, -1, 'rafi@workspace.com', 'e10adc3949ba59abbe56e057f20f883e', '273f1a8b06d9e0063f2bb9ed21bbf624', 1, 1, 0, '2016-12-27 06:22:02'),
(36, 37, -1, 'mausum@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'f54f2b3b33936bd2d91a6ec219d62485', 1, 1, 0, '2016-12-27 06:22:02'),
(37, 38, -1, 'fayme@work.com', 'e10adc3949ba59abbe56e057f20f883e', '85e7bc630a1f1259ce0b0c1e88366cd2', 1, 1, 0, '2016-12-27 06:22:02'),
(38, 39, -1, 'f@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '34ac9072ee5000706b88e49c67d58af8', 1, 1, 0, '2016-12-27 06:22:02'),
(39, 40, -1, 'tmina@yahoo.com', '6182a61e266111245440159ef6901169', '5e514d2bb99e4beb79815e2a2bcccfbe', 1, 1, 0, '2016-12-27 06:22:02'),
(40, 41, -1, 'lima@yahoo.com', 'e10adc3949ba59abbe56e057f20f883e', '1642e525a70dee1eb3352ed35220011d', 1, 1, 0, '2016-12-27 06:22:02'),
(41, 42, -1, 'e@sdf.com', 'd58e3582afa99040e27b92b13c8f2280', '54ba615ccb87199abaaa485199e38dc8', 1, 1, 0, '2016-12-27 06:22:02'),
(42, 43, -1, 'dsa@sdf.com', 'a8f5f167f44f4964e6c998dee827110c', '47dbc11357bb1d0645dfddf39de6a840', 0, 1, 0, '2016-12-27 06:22:02'),
(43, 44, -1, 'asd@sdf.comn', '89aaaeeda8a4f50e0ad8fb8ad6e6e437', '85effe24aea679cf4ce8ffa3f9284305', 0, 1, 0, '2016-12-27 06:22:02'),
(44, 45, -1, 'dummy20@tummy.com', '5a2dd3b5557333af7d0d89a8790379e9', '9c98b4bc3ada6a81c946bc278c531c91', 1, 1, 0, '2016-12-27 06:22:02'),
(45, 46, -1, 'dummy21@tummy.com', '5a2dd3b5557333af7d0d89a8790379e9', 'e2e3a9a544ccedef1e317a57d351bc47', 1, 1, 0, '2016-12-27 06:22:02'),
(46, 47, -1, 'modon@chand.com', 'e10adc3949ba59abbe56e057f20f883e', 'b93d8b1f80f8138952d5efc58ec1cd15', 1, 1, 0, '2016-12-27 06:22:02'),
(47, 48, -1, 'sdfsdf@sdfsdf.com', '89400617397134a1fc9c9fbcb7e1d10a', '27dd99c4e7a880f1803ce2268c4b0806', 1, 1, 0, '2016-12-27 06:22:02'),
(48, 49, -1, 'sdfd@sdf.com', '73a90acaae2b1ccc0e969709665bc62f', '05dfffc43184c7fe95f7b9c20bddf8af', 1, 1, 0, '2016-12-27 06:22:02'),
(49, 50, -1, 'dfg@sdf.com', '73a90acaae2b1ccc0e969709665bc62f', '6ddeeff683d14043a081a86c127a96ef', 1, 1, 0, '2016-12-27 06:22:02'),
(69, 70, -1, 'app.developer.wsit@gmail.com', '222ba8674e84e6e0ef4b00d7d8db5c98', '0d6d4a288c5f2439dbfa42892687673f', 1, 1, 0, '2016-12-27 06:22:02'),
(70, 71, -1, 'rafi@workspaceit.com', 'e10adc3949ba59abbe56e057f20f883e', '09559c281f6aaeef1f4762ecd0237f2d', 1, 1, 0, '2016-12-27 06:22:02'),
(79, 80, -1, 'rafi@workspaceit.com', 'e10adc3949ba59abbe56e057f20f883e', '09559c281f6aaeef1f4762ecd0237f2d', 1, 1, 0, '2016-10-19 10:22:13'),
(80, 81, -1, 'mousum@workspac1e.com', 'e10adc3949ba59abbe56e057f20f883e', '55400b02c16c8b741469eac772611102', 1, 1, 0, '2016-11-03 09:00:58'),
(81, 82, -1, 'mousum@worwkspace.com', 'e10adc3949ba59abbe56e057f20f883e', '1be05a828cba60e811755d367ebd3327', 1, 1, 0, '2016-11-03 09:01:36'),
(82, 83, -1, 'rafi1@workspaceit.com', 'e10adc3949ba59abbe56e057f20f883e', '169b603bf49362c102e5705c6a004b62', 1, 1, 0, '2016-11-03 09:04:50'),
(83, 84, -1, 'sdf@sdf.com', 'e10adc3949ba59abbe56e057f20f883e', 'a443a41128dbce0bc2bf6ad3f7031f3f', 1, 1, 0, '2016-11-03 09:29:26'),
(84, 85, -1, 'sdf@saddff.com', '73a90acaae2b1ccc0e969709665bc62f', '661743520f7e9c2b93e6950c4e37ee3484', 1, 1, 0, '2016-11-08 06:46:52'),
(85, 86, -1, 'admiasdn@admiasdn.com', '$2a$10$z4aOz5eIWcEGHXivvl9SiOhibdqneT2n/NI41uQS2f1usTuSuAQ62', '3f61bf201a5df481845ee551dbea7ac376f9e245af670408de539df9b7a41eb685', 1, 1, 0, '2016-11-08 07:17:19'),
(86, 87, -1, 'asd@s.com', '$2a$10$sCNozitM2w43VAc6mQdI8O/SxuRpiVDNiVht3wHcP6S8Ik.4mgsA2', '6d838171341a662795d2f06acaaa10f91043016e8551a364d65ef8164b6820fa86', 1, 1, 0, '2016-11-08 11:10:53'),
(87, 88, -1, 'rafi101010@gmail.com', '$2a$10$IKohcmA6Nz3qv3rQ2oP5Zu89ltU.aUCvPuGLTnwXZAFyygPy4UrVa', 'cdfeb6758edcdeb05e1b5dc6468c56c55bdcae8328e74f368576da3fc862ecee87', 1, 1, 0, '2016-12-27 05:34:42');

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

--
-- Dumping data for table `banner_image`
--

INSERT INTO `banner_image` (`id`, `image_path`, `url`, `created_date`) VALUES
(1, '', 'www.google.com', '2016-10-25 12:51:51'),
(2, '4347335971968.png', 'http://adsds.com', '2016-10-27 06:24:11');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `is_subcategory` tinyint(1) NOT NULL DEFAULT '0',
  `sorted_order` int(11) NOT NULL,
  `picture` text,
  `product_count` int(11) NOT NULL DEFAULT '0',
  `created_by` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `category_app_login_credential` (`created_by`),
  KEY `category_category` (`parent_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `parent_id`, `is_subcategory`, `sorted_order`, `picture`, `product_count`, `created_by`, `created_date`) VALUES
(8, 'HOME', NULL, 0, 1, '{"original":{"path":"6508271277834.jpg","type":"","size":{"width":1164,"height":775}},"thumb":[]}', 0, 0, '2016-12-22 07:10:05'),
(9, 'HOME APPLIANCE', NULL, 0, 2, NULL, 0, 0, '2016-08-08 09:27:59'),
(10, 'FURNITURE', NULL, 0, 3, NULL, 0, 0, '2016-08-08 09:27:08'),
(11, 'GAMING & PARTY', NULL, 0, 4, NULL, 0, 0, '2016-08-08 09:27:08'),
(12, 'COOL GADGETS', NULL, 0, 5, '{"original":{"path":"21516728661550.jpg","type":"","size":{"width":680,"height":420}},"thumb":[]}', 0, 0, '2016-08-08 09:27:52'),
(13, 'BLOGS', NULL, 0, 6, '{"original":{"path":"21402397931365.jpg","type":"","size":{"width":1600,"height":1200}},"thumb":[]}', 0, 0, '2016-08-08 09:27:52'),
(14, 'Swedish washing machine', 9, 1, 7, NULL, 0, 0, '2016-08-17 11:35:09'),
(15, 'HOME SUBCAT ', 9, 1, 8, 'null', -1, 0, '2016-11-10 06:15:05'),
(16, 'CTG', 8, 1, 9, 'null', 0, 34, '2016-10-14 09:38:24'),
(17, 'DHK', 8, 1, 10, 'null', 0, 34, '2016-10-14 09:38:42');

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE IF NOT EXISTS `cities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state_id` int(11) NOT NULL,
  `city_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`id`, `state_id`, `city_name`) VALUES
(1, 1, 'Huntsville'),
(2, 1, 'Mobile'),
(3, 1, 'Birmingham'),
(4, 1, 'Montgomery'),
(5, 2, 'Anchorage'),
(6, 3, 'Peoria'),
(7, 3, 'Phoenix'),
(8, 3, 'Chandler'),
(9, 3, 'Gilbert'),
(10, 2, 'Glendale'),
(11, 3, 'Mesa'),
(12, 3, 'Scottsdale'),
(13, 3, 'Surprise'),
(14, 3, 'Tempe'),
(15, 3, 'Tucson'),
(16, 4, 'Little Rock');

-- --------------------------------------------------------

--
-- Table structure for table `cron_last_executed`
--

CREATE TABLE IF NOT EXISTS `cron_last_executed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rent_request_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `cron_last_executed`
--

INSERT INTO `cron_last_executed` (`id`, `rent_request_id`) VALUES
(3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cron_log`
--

CREATE TABLE IF NOT EXISTS `cron_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=209 ;

--
-- Dumping data for table `cron_log`
--

INSERT INTO `cron_log` (`id`, `description`, `created_date`) VALUES
(1, 'RENT REQUEST ID : ', '2016-10-17 06:30:00'),
(2, 'RENT REQUEST ID : ', '2016-10-17 07:00:00'),
(3, 'RENT REQUEST ID : ', '2016-10-17 07:30:00'),
(4, 'RENT REQUEST ID : ', '2016-10-19 01:00:00'),
(5, 'RENT REQUEST ID : ', '2016-10-19 01:30:00'),
(6, 'RENT REQUEST ID : ', '2016-10-19 02:00:00'),
(7, 'RENT REQUEST ID : ', '2016-10-19 02:30:00'),
(8, 'RENT REQUEST ID : ', '2016-10-19 03:00:00'),
(9, 'RENT REQUEST ID : ', '2016-10-19 03:30:00'),
(10, 'RENT REQUEST ID : ', '2016-10-19 04:00:00'),
(11, 'RENT REQUEST ID : ', '2016-10-19 04:30:00'),
(12, 'RENT REQUEST ID : ', '2016-10-19 05:00:00'),
(13, 'RENT REQUEST ID : ', '2016-10-19 05:30:00'),
(14, 'RENT REQUEST ID : ', '2016-10-19 06:00:00'),
(15, 'RENT REQUEST ID : ', '2016-10-20 02:30:00'),
(16, 'RENT REQUEST ID : ', '2016-10-20 03:00:00'),
(17, 'RENT REQUEST ID : ', '2016-10-20 03:30:00'),
(18, 'RENT REQUEST ID : ', '2016-10-20 04:00:00'),
(19, 'RENT REQUEST ID : ', '2016-10-20 04:30:00'),
(20, 'RENT REQUEST ID : ', '2016-10-20 05:00:01'),
(21, 'RENT REQUEST ID : ', '2016-10-20 06:30:03'),
(22, 'RENT REQUEST ID : ', '2016-10-21 03:00:00'),
(23, 'RENT REQUEST ID : ', '2016-10-21 03:30:00'),
(24, 'RENT REQUEST ID : ', '2016-10-21 04:00:00'),
(25, 'RENT REQUEST ID : ', '2016-10-21 04:30:00'),
(26, 'RENT REQUEST ID : ', '2016-10-21 05:30:00'),
(27, 'RENT REQUEST ID : ', '2016-10-23 23:00:00'),
(28, 'RENT REQUEST ID : ', '2016-10-23 23:30:00'),
(29, 'RENT REQUEST ID : ', '2016-10-24 00:00:00'),
(30, 'RENT REQUEST ID : ', '2016-10-24 02:00:01'),
(31, 'RENT REQUEST ID : ', '2016-10-24 02:30:00'),
(32, 'RENT REQUEST ID : ', '2016-10-24 03:00:00'),
(33, 'RENT REQUEST ID : ', '2016-10-24 03:30:00'),
(34, 'RENT REQUEST ID : ', '2016-10-24 04:00:00'),
(35, 'RENT REQUEST ID : ', '2016-10-24 04:30:02'),
(36, 'RENT REQUEST ID : ', '2016-10-24 05:00:00'),
(37, 'RENT REQUEST ID : ', '2016-10-24 05:30:01'),
(38, 'RENT REQUEST ID : ', '2016-10-24 06:00:00'),
(39, 'RENT REQUEST ID : ', '2016-10-24 06:30:00'),
(40, 'RENT REQUEST ID : ', '2016-10-24 07:00:00'),
(41, 'RENT REQUEST ID : ', '2016-10-25 00:00:00'),
(42, 'RENT REQUEST ID : ', '2016-10-25 00:30:00'),
(43, 'RENT REQUEST ID : ', '2016-10-25 01:00:00'),
(44, 'RENT REQUEST ID : ', '2016-10-25 01:30:00'),
(45, 'RENT REQUEST ID : ', '2016-10-25 02:30:00'),
(46, 'RENT REQUEST ID : ', '2016-10-25 03:00:00'),
(47, 'RENT REQUEST ID : ', '2016-10-25 03:30:00'),
(48, 'RENT REQUEST ID : ', '2016-10-25 04:00:00'),
(49, 'RENT REQUEST ID : ', '2016-10-25 04:30:00'),
(50, 'RENT REQUEST ID : ', '2016-10-25 05:00:00'),
(51, 'RENT REQUEST ID : ', '2016-10-25 06:00:00'),
(52, 'RENT REQUEST ID : ', '2016-10-25 06:30:00'),
(53, 'RENT REQUEST ID : ', '2016-10-25 07:00:00'),
(54, 'RENT REQUEST ID : ', '2016-10-25 22:30:00'),
(55, 'RENT REQUEST ID : ', '2016-10-26 00:30:00'),
(56, 'RENT REQUEST ID : ', '2016-10-26 01:00:00'),
(57, 'RENT REQUEST ID : ', '2016-10-27 00:00:00'),
(58, 'RENT REQUEST ID : ', '2016-10-27 00:30:00'),
(59, 'RENT REQUEST ID : ', '2016-10-27 01:00:00'),
(60, 'RENT REQUEST ID : ', '2016-10-27 01:30:00'),
(61, 'RENT REQUEST ID : ', '2016-10-27 02:30:00'),
(62, 'RENT REQUEST ID : ', '2016-10-27 06:30:00'),
(63, 'RENT REQUEST ID : ', '2016-11-01 23:30:00'),
(64, 'RENT REQUEST ID : ', '2016-11-02 23:30:00'),
(65, 'RENT REQUEST ID : ', '2016-11-03 00:00:00'),
(66, 'RENT REQUEST ID : ', '2016-11-03 00:30:00'),
(67, 'RENT REQUEST ID : ', '2016-11-03 01:00:00'),
(68, 'RENT REQUEST ID : ', '2016-11-03 01:30:00'),
(69, 'RENT REQUEST ID : ', '2016-11-03 02:00:00'),
(70, 'RENT REQUEST ID : ', '2016-11-03 02:30:00'),
(71, 'RENT REQUEST ID : ', '2016-11-03 03:00:00'),
(72, 'RENT REQUEST ID : ', '2016-11-03 03:30:00'),
(73, 'RENT REQUEST ID : ', '2016-11-03 04:00:00'),
(74, 'RENT REQUEST ID : ', '2016-11-03 04:30:00'),
(75, 'RENT REQUEST ID : ', '2016-11-03 05:00:00'),
(76, 'RENT REQUEST ID : ', '2016-11-03 05:30:00'),
(77, 'RENT REQUEST ID : ', '2016-11-03 23:30:00'),
(78, 'RENT REQUEST ID : ', '2016-11-04 00:00:00'),
(79, 'RENT REQUEST ID : ', '2016-11-04 00:30:00'),
(80, 'RENT REQUEST ID : ', '2016-11-04 01:00:00'),
(81, 'RENT REQUEST ID : ', '2016-11-04 01:30:00'),
(82, 'RENT REQUEST ID : ', '2016-11-04 02:00:00'),
(83, 'RENT REQUEST ID : ', '2016-11-04 02:30:00'),
(84, 'RENT REQUEST ID :  142', '2016-11-04 03:00:00'),
(85, 'RENT REQUEST ID : ', '2016-11-07 06:30:00'),
(86, 'RENT REQUEST ID : ', '2016-11-08 00:00:00'),
(87, 'RENT REQUEST ID : ', '2016-11-08 00:30:00'),
(88, 'RENT REQUEST ID : ', '2016-11-08 01:00:00'),
(89, 'RENT REQUEST ID : ', '2016-11-08 05:00:00'),
(90, 'RENT REQUEST ID : ', '2016-11-08 06:00:00'),
(91, 'RENT REQUEST ID : ', '2016-11-08 06:30:00'),
(92, 'RENT REQUEST ID : ', '2016-11-09 02:30:00'),
(93, 'RENT REQUEST ID :  143', '2016-11-09 03:00:00'),
(94, 'RENT REQUEST ID : ', '2016-11-09 23:30:00'),
(95, 'RENT REQUEST ID : ', '2016-11-10 00:00:00'),
(96, 'RENT REQUEST ID : ', '2016-11-10 05:00:00'),
(97, 'RENT REQUEST ID : ', '2016-11-10 23:00:00'),
(98, 'RENT REQUEST ID : ', '2016-11-10 23:30:00'),
(99, 'RENT REQUEST ID : ', '2016-11-11 00:00:00'),
(100, 'RENT REQUEST ID : ', '2016-11-11 00:30:00'),
(101, 'RENT REQUEST ID : ', '2016-11-11 01:00:00'),
(102, 'RENT REQUEST ID : ', '2016-11-11 01:30:00'),
(103, 'RENT REQUEST ID : ', '2016-11-11 02:00:00'),
(104, 'RENT REQUEST ID : ', '2016-11-11 02:30:00'),
(105, 'RENT REQUEST ID : ', '2016-11-11 03:00:02'),
(106, 'RENT REQUEST ID : ', '2016-11-11 03:30:00'),
(107, 'RENT REQUEST ID : ', '2016-11-11 04:00:00'),
(108, 'RENT REQUEST ID : ', '2016-11-11 04:30:00'),
(109, 'RENT REQUEST ID : ', '2016-11-11 05:00:00'),
(110, 'RENT REQUEST ID : ', '2016-11-11 05:30:00'),
(111, 'RENT REQUEST ID : ', '2016-11-14 01:30:00'),
(112, 'RENT REQUEST ID : ', '2016-11-15 00:46:05'),
(113, 'RENT REQUEST ID : ', '2016-11-15 00:47:05'),
(114, 'RENT REQUEST ID : ', '2016-11-15 00:48:05'),
(115, 'RENT REQUEST ID : ', '2016-11-15 00:49:05'),
(116, 'RENT REQUEST ID : ', '2016-11-15 00:50:05'),
(117, 'RENT REQUEST ID : ', '2016-11-15 00:51:05'),
(118, 'RENT REQUEST ID : ', '2016-11-15 00:52:05'),
(119, 'RENT REQUEST ID : ', '2016-11-15 00:53:05'),
(120, 'RENT REQUEST ID : ', '2016-11-15 00:54:05'),
(121, 'RENT REQUEST ID : ', '2016-11-15 00:55:05'),
(122, 'RENT REQUEST ID : ', '2016-11-15 00:56:05'),
(123, 'RENT REQUEST ID : ', '2016-11-15 00:57:05'),
(124, 'RENT REQUEST ID : ', '2016-11-15 00:58:05'),
(125, 'RENT REQUEST ID : ', '2016-11-15 00:59:05'),
(126, 'RENT REQUEST ID : ', '2016-11-15 01:00:05'),
(127, 'RENT REQUEST ID : ', '2016-11-15 01:01:05'),
(128, 'RENT REQUEST ID : ', '2016-11-15 01:02:05'),
(129, 'RENT REQUEST ID : ', '2016-11-15 01:03:05'),
(130, 'RENT REQUEST ID : ', '2016-11-15 01:05:05'),
(131, 'RENT REQUEST ID : ', '2016-11-15 01:06:05'),
(132, 'RENT REQUEST ID : ', '2016-11-15 01:07:05'),
(133, 'RENT REQUEST ID : ', '2016-11-15 01:08:05'),
(134, 'RENT REQUEST ID : ', '2016-11-15 01:09:05'),
(135, 'RENT REQUEST ID : ', '2016-11-15 01:10:05'),
(136, 'RENT REQUEST ID :  144', '2016-11-15 01:11:05'),
(137, 'RENT REQUEST ID : ', '2016-11-15 01:12:05'),
(138, 'RENT REQUEST ID : ', '2016-11-15 01:13:05'),
(139, 'RENT REQUEST ID : ', '2016-11-15 01:14:05'),
(140, 'RENT REQUEST ID : ', '2016-11-15 01:15:05'),
(141, 'RENT REQUEST ID : ', '2016-11-15 01:16:05'),
(142, 'RENT REQUEST ID : ', '2016-11-15 01:17:05'),
(143, 'RENT REQUEST ID : ', '2016-11-15 01:18:05'),
(144, 'RENT REQUEST ID : ', '2016-11-15 01:19:05'),
(145, 'RENT REQUEST ID : ', '2016-11-15 01:20:05'),
(146, 'RENT REQUEST ID : ', '2016-11-15 01:21:05'),
(147, 'RENT REQUEST ID : ', '2016-11-15 01:22:05'),
(148, 'RENT REQUEST ID : ', '2016-11-15 01:23:05'),
(149, 'RENT REQUEST ID : ', '2016-11-15 01:24:05'),
(150, 'RENT REQUEST ID : ', '2016-11-15 01:25:05'),
(151, 'RENT REQUEST ID : ', '2016-11-15 01:26:05'),
(152, 'RENT REQUEST ID : ', '2016-11-15 01:27:05'),
(153, 'RENT REQUEST ID : ', '2016-11-15 01:28:05'),
(154, 'RENT REQUEST ID : ', '2016-11-15 01:29:05'),
(155, 'RENT REQUEST ID : ', '2016-11-15 08:30:05'),
(156, 'RENT REQUEST ID :  144', '2016-11-15 08:31:05'),
(157, 'RENT REQUEST ID : ', '2016-11-15 08:32:05'),
(158, 'RENT REQUEST ID : ', '2016-11-15 08:33:05'),
(159, 'RENT REQUEST ID :  145', '2016-11-15 09:06:05'),
(160, 'RENT REQUEST ID : ', '2016-11-15 09:07:05'),
(161, 'RENT REQUEST ID : ', '2016-11-15 09:08:05'),
(162, 'RENT REQUEST ID : ', '2016-11-15 09:09:05'),
(163, 'RENT REQUEST ID : ', '2016-11-15 09:10:05'),
(164, 'RENT REQUEST ID : ', '2016-11-15 09:11:05'),
(165, 'RENT REQUEST ID : ', '2016-11-15 09:12:05'),
(166, 'RENT REQUEST ID : ', '2016-11-15 09:13:05'),
(167, 'RENT REQUEST ID : ', '2016-11-15 09:14:05'),
(168, 'RENT REQUEST ID : ', '2016-11-15 09:15:05'),
(169, 'RENT REQUEST ID : ', '2016-11-15 09:16:05'),
(170, 'RENT REQUEST ID : ', '2016-11-15 09:17:05'),
(171, 'RENT REQUEST ID : ', '2016-11-15 09:18:05'),
(172, 'RENT REQUEST ID : ', '2016-11-15 09:19:05'),
(173, 'RENT REQUEST ID : ', '2016-11-15 09:20:05'),
(174, 'RENT REQUEST ID : ', '2016-11-15 09:21:05'),
(175, 'RENT REQUEST ID : ', '2016-11-15 09:22:05'),
(176, 'RENT REQUEST ID : ', '2016-11-15 09:23:05'),
(177, 'RENT REQUEST ID : ', '2016-11-15 09:24:05'),
(178, 'RENT REQUEST ID :  145', '2016-11-15 09:25:05'),
(179, 'RENT REQUEST ID : ', '2016-11-15 03:26:05'),
(180, 'RENT REQUEST ID : ', '2016-11-15 03:27:05'),
(181, 'RENT REQUEST ID : ', '2016-11-15 03:28:05'),
(182, 'RENT REQUEST ID : ', '2016-11-15 03:29:05'),
(183, 'RENT REQUEST ID : ', '2016-11-15 03:30:05'),
(184, 'RENT REQUEST ID : ', '2016-11-15 03:31:05'),
(185, 'RENT REQUEST ID : ', '2016-11-15 05:55:55'),
(186, 'RENT REQUEST ID : ', '2016-11-15 05:56:00'),
(187, 'RENT REQUEST ID : ', '2016-11-15 05:56:05'),
(188, 'RENT REQUEST ID : ', '2016-11-15 05:56:10'),
(189, 'RENT REQUEST ID : ', '2016-11-15 05:56:15'),
(190, 'RENT REQUEST ID : ', '2016-11-15 05:56:20'),
(191, 'RENT REQUEST ID : ', '2016-11-15 05:56:25'),
(192, 'RENT REQUEST ID : ', '2016-11-15 05:56:30'),
(193, 'RENT REQUEST ID : ', '2016-11-15 05:56:35'),
(194, 'RENT REQUEST ID : ', '2016-11-15 05:56:40'),
(195, 'RENT REQUEST ID : ', '2016-11-15 05:56:45'),
(196, 'RENT REQUEST ID : ', '2016-11-15 05:56:50'),
(197, 'RENT REQUEST ID : ', '2016-11-15 05:56:55'),
(198, 'RENT REQUEST ID : ', '2016-11-15 05:57:00'),
(199, 'RENT REQUEST ID : ', '2016-11-15 05:57:05'),
(200, 'RENT REQUEST ID : ', '2016-11-15 05:57:10'),
(201, 'RENT REQUEST ID : ', '2016-11-15 05:57:15'),
(202, 'RENT REQUEST ID : ', '2016-11-15 05:58:05'),
(203, 'RENT REQUEST ID :  146', '2016-12-28 00:00:00'),
(204, 'RENT REQUEST ID : ', '2016-12-28 00:30:00'),
(205, 'RENT REQUEST ID : ', '2016-12-28 01:00:00'),
(206, 'RENT REQUEST ID :  139 140', '2016-12-28 01:30:00'),
(207, 'RENT REQUEST ID : ', '2016-12-28 02:00:00'),
(208, 'RENT REQUEST ID : ', '2016-12-28 02:30:00');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `email_confirmation`
--

INSERT INTO `email_confirmation` (`id`, `app_credential_id`, `token`, `already_used`, `created_date`) VALUES
(8, 79, 'bb4079e747138d7b54859565391dfba0', 0, '2016-10-19 10:22:14'),
(9, 80, '79ba2bd446cce26b78fd39baa6a12c6f', 0, '2016-11-03 09:00:58'),
(10, 81, '7bcc66385a7b023650170f7e0502d67b', 0, '2016-11-03 09:01:36'),
(11, 82, '8c630b664373751caa5b89ffbfe77b26', 0, '2016-11-03 09:04:50'),
(12, 83, '289c83362acf09084afab38c2f0acb11', 0, '2016-11-03 09:29:26'),
(13, 84, 'd6c2df852c3c1cb6c20fbda6e3f38cb5', 0, '2016-11-08 06:46:52'),
(14, 85, '9bf43ffe744b953cdea58519d7bc5617', 0, '2016-11-08 07:17:19'),
(15, 86, '823918eea596f9724fd06d6fca0103b8', 0, '2016-11-08 11:10:53'),
(16, 87, 'a18857e23cb5b6a12d6db382eefa0941', 1, '2016-12-27 05:28:12');

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
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app` (`app_credential_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `password_resets`
--

INSERT INTO `password_resets` (`id`, `app_credential_id`, `token`, `created_date`) VALUES
(4, 70, '04dfd0b1b093a9a8dd9c14e3b1ec4e49', '2016-09-07 08:56:19'),
(5, 32, '63807392f5031e01f35e9d290e7c0e0c', '2016-09-08 08:28:33'),
(10, 79, '9eedfdaf903b6a22bad48dccc0c563a5', '2016-10-27 06:15:28');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `payment_refund`
--

INSERT INTO `payment_refund` (`id`, `app_credential_id`, `payment_id`, `total_amount`, `parent_pay_id`, `paypal_sale_id`, `paypal_refund_id`, `paypal_payment_date`, `created_date`) VALUES
(6, 32, 40, 6.00000, 'PAY-4V073205W1747884GK7VBQYA', '8D6302898S3676134', NULL, NULL, '2016-09-27 07:01:21'),
(7, 32, 41, 6.00000, 'PAY-5AP36138TY9856233K7VDIHY', '6DJ98506AS662504B', NULL, NULL, '2016-09-27 09:06:02'),
(8, 32, 42, 10.00000, 'PAY-5EK49287PU8357325K77VQNI', '03Y22315BY815125M', NULL, NULL, '2016-10-13 09:49:04'),
(9, 32, 43, 10.00000, 'PAY-0JN4455680279873XLAEHXUY', '3H361222BF406642K', NULL, NULL, '2016-12-28 07:30:20');

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
-- Dumping data for table `payout`
--

INSERT INTO `payout` (`id`, `rent_inf_id`, `app_credential_id`, `state`, `total_amount`, `transaction_id`, `transaction_status`, `payout_batch_id`, `paypal_payment_date`, `created_date`) VALUES
(7, 5, 40, 'completed', 4.00000, '4LW99845SJ2446124', 'SUCCESS', 'FRWBVVA5MP6WC', NULL, '2016-09-27 07:01:49'),
(8, 6, 40, 'completed', 4.00000, '8AX53633Y7319035K', 'SUCCESS', 'E68VUZLNUN2ZA', NULL, '2016-09-27 09:06:08');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `owner_id`, `name`, `description`, `average_rating`, `profile_image`, `other_images`, `current_value`, `rent_fee`, `rent_type_id`, `active`, `currently_available`, `available_from`, `available_till`, `review_status`, `created_date`) VALUES
(12, 40, 'CK Tempting Glimmer Sheer Creme EyeShadow - 303 Baby Blue', 'Offers high shine high impact colour Lightweight & extra-smooth finish.', 5.00, '{"original":{"path":"product/40/26594005394083.jpg","type":"","size":{"width":458,"height":458}},"thumb":[]}', '[]', 10.00, 2.00, 1, 1, 1, '2016-08-30 00:00:00', '2017-04-29 00:00:00', 1, '2016-11-09 08:24:06'),
(14, 32, 'PRODUCT 1', 'PRODUCT 1 DEs', 3.00, '{"original":{"path":"product/32/815788920270.jpg","type":"","size":{"width":660,"height":371}},"thumb":[]}', '[]', 2.00, 0.06, 2, 1, 1, '2016-08-24 00:00:00', '2016-12-25 00:00:00', 1, '2016-09-21 05:47:33'),
(17, 32, 'PRODUCT 2', 'PRODUCT 2 des', 4.00, '{"original":{"path":"product/32/1087095250392.jpg","type":"","size":{"width":660,"height":371}},"thumb":[]}', '[]', 3.00, 0.50, 1, 1, 1, '2016-09-23 00:00:00', '2016-12-14 00:00:00', 1, '2016-09-21 05:47:53'),
(19, 32, '123456', 'asd', 0.00, '{"original":{"path":"product/32/26790123192750.png","type":"","size":{"width":3286,"height":1080}},"thumb":[]}', '[{"original":{"path":"product/32/26884432623757.jpg","type":"","size":{"width":1500,"height":800}},"thumb":[]},{"original":{"path":"product/32/26884595585744.jpg","type":"","size":{"width":1600,"height":592}},"thumb":[]},{"original":{"path":"product/32/26884641859443.jpg","type":"","size":{"width":960,"height":640}},"thumb":[]}]', 10.00, 10.00, 1, 1, 1, '2016-11-30 18:00:00', '2017-11-30 18:00:00', 1, '2016-09-21 05:47:56'),
(20, 79, 'AD &lt;script&gt;console.log(&quot;FirstName&quot;);&lt;/script&gt;', 'ASD', 0.00, '{"original":{"path":"product/32/21260186848854.jpg","type":"","size":{"width":680,"height":420}},"thumb":[]}', '[]', 6.00, 13.00, 2, 1, 1, '2016-10-20 18:00:00', '2016-10-21 18:00:00', 1, '2016-11-08 11:11:35'),
(21, 32, 'sdf', 'sdf', 0.00, '{"original":{"path":"product/32/22613548380877.jpg","type":"","size":{"width":1500,"height":800}},"thumb":[]}', '[]', 6.00, 4.00, 1, 1, 1, '2016-11-30 00:00:00', '2017-05-24 00:00:00', 1, '2016-11-30 10:37:41'),
(23, 32, 'asd', 'asd', 0.00, '{"original":{"path":"product/32/28261518442183.png","type":"","size":{"width":3286,"height":1080}},"thumb":[]}', '[]', 9.00, 10.00, 2, 1, 1, '2016-12-26 00:00:00', '2016-12-30 00:00:00', 1, '2016-12-26 13:07:30'),
(24, 32, 'sdf', 'sdf', 0.00, '{"original":{"path":"product/32/4362814520197.png","type":"","size":{"width":979,"height":699}},"thumb":[]}', '[]', 7.00, 6.00, 2, 1, 1, '2016-12-27 00:00:00', '2016-12-30 00:00:00', 0, '2016-12-27 06:24:40');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=48 ;

--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` (`id`, `product_id`, `category_id`, `created_date`) VALUES
(11, 12, 9, '2016-08-09 11:24:47'),
(12, 14, 9, '2016-10-03 09:47:05'),
(19, 20, 14, '2016-10-21 10:27:48'),
(30, 19, 14, '2016-11-11 11:01:20'),
(34, 17, 15, '2016-11-29 09:58:34'),
(44, 23, 15, '2016-12-26 13:06:29'),
(45, 24, 16, '2016-12-27 06:24:40'),
(47, 21, 15, '2016-12-27 06:31:16');

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
  `state_id` int(11) NOT NULL,
  `city_id` int(11) DEFAULT NULL,
  `formatted_address` text NOT NULL,
  `zip` varchar(200) DEFAULT NULL,
  `lat` double(13,9) DEFAULT NULL,
  `lng` double(13,9) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `city_id` (`city_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `product_location`
--

INSERT INTO `product_location` (`id`, `product_id`, `state_id`, `city_id`, `formatted_address`, `zip`, `lat`, `lng`, `created_date`) VALUES
(5, 12, 1, 1, 'Nikunja-2, Khilkhet 2324', '234s', 23.828155400, 90.417240200, '2016-08-29 12:07:44'),
(6, 14, 1, 1, 'Banani', 'wdf', 23.828155400, 90.000000000, '2016-08-12 04:42:01'),
(7, 17, 1, 1, 'Banani', 'wdf', 23.828155400, 90.412518000, '2016-08-12 04:46:32'),
(9, 20, 2, 1, 'asd', 'asd', 23.828155400, 90.417240200, '2016-10-21 10:27:48'),
(10, 19, 2, 1, 'asd', 'asd', 23.828155400, 90.417240200, '2016-11-11 10:53:17'),
(11, 21, 1, 2, 'Rd no 6, Dhaka, Bangladesh', 'dsf', 23.822043600, 90.417221800, '2016-11-30 10:37:09'),
(13, 23, 2, NULL, 'asd', 'asd', 0.000000000, 0.000000000, '2016-12-26 13:06:29'),
(14, 24, 9, NULL, 'sdf', 'sdf', 0.000000000, 0.000000000, '2016-12-27 06:24:40');

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

--
-- Dumping data for table `rental_product_returned`
--

INSERT INTO `rental_product_returned` (`id`, `rent_inf_id`, `confirm`, `dispute`, `expired`, `rentee_remarks`, `renter_remarks`, `created_date`) VALUES
(5, 5, 0, 0, 0, NULL, NULL, '2016-09-27 07:00:19'),
(6, 6, 1, 0, 0, NULL, NULL, '2016-09-27 08:58:36');

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

--
-- Dumping data for table `rental_product_returned_history`
--

INSERT INTO `rental_product_returned_history` (`id`, `product_returned_id`, `confirm`, `dispute`, `created_date`) VALUES
(7, 5, 1, 0, '2016-09-27 01:01:49'),
(8, 6, 1, 0, '2016-09-27 03:06:08');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `rent_inf`
--

INSERT INTO `rent_inf` (`id`, `rent_request_id`, `rentee_id`, `product_id`, `start_date`, `ends_date`, `product_returned`, `product_received`, `expired`, `rent_complete`, `created_date`) VALUES
(5, 131, 32, 12, '2016-09-27', '2016-09-29', 1, 1, 0, 1, '2016-09-27 06:59:49'),
(6, 132, 32, 12, '2016-09-27', '2016-09-29', 1, 1, 0, 1, '2016-09-27 08:57:55'),
(7, 141, 79, 19, '2016-11-03', '2016-11-05', 0, 0, 0, 0, '2016-11-03 11:26:00');

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
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `app_credential_id` (`app_credential_id`),
  KEY `rent_inf_id` (`rent_inf_id`),
  KEY `rent_request_id` (`rent_request_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=47 ;

--
-- Dumping data for table `rent_payment`
--

INSERT INTO `rent_payment` (`id`, `app_credential_id`, `rent_request_id`, `rent_inf_id`, `site_fee`, `rent_fee`, `refund_amount`, `total_amount`, `transaction_fee`, `currency`, `authorization_id`, `paypal_payer_id`, `paypal_pay_id`, `paypal_sale_id`, `paypal_payment_date`, `created_date`) VALUES
(40, 32, 131, 5, 0, 4.00000, 0, 10.00000, 0.64, 'USD', NULL, 'TKCD9W66CR9R4', 'PAY-4V073205W1747884GK7VBQYA', '8D6302898S3676134', NULL, '2016-09-27 06:58:33'),
(41, 32, 132, 6, 0, 4.00000, 0, 10.00000, 0.64, 'USD', NULL, 'TKCD9W66CR9R4', 'PAY-5AP36138TY9856233K7VDIHY', '6DJ98506AS662504B', NULL, '2016-09-27 08:57:07'),
(42, 32, 138, NULL, 0, 4.00000, 0, 10.00000, 0.64, 'USD', NULL, 'TKCD9W66CR9R4', 'PAY-5EK49287PU8357325K77VQNI', '03Y22315BY815125M', NULL, '2016-10-13 09:48:18'),
(43, 32, 140, NULL, 0, 6.00000, 0, 10.00000, 0.64, 'USD', NULL, 'TKCD9W66CR9R4', 'PAY-0JN4455680279873XLAEHXUY', '3H361222BF406642K', NULL, '2016-10-20 08:10:23'),
(44, 79, 141, 7, 0, 0.29000, 0, 4.00000, 0.44, 'USD', NULL, 'TKCD9W66CR9R4', 'PAY-8SK487712Y6689022LANR4CI', '0VN35004VU947973E', NULL, '2016-11-03 11:24:07'),
(45, 32, 147, NULL, 0, 4.00000, 0, 20.25000, 0.99, 'USD', NULL, 'TKCD9W66CR9R4', 'PAY-4KY13739JU162442CLBRYYII', '7M816497WH551435N', NULL, '2016-12-28 03:58:15'),
(46, 32, 148, NULL, 10.25, 4.00000, 0, 20.25000, 0.99, 'USD', NULL, 'TKCD9W66CR9R4', 'PAY-37X544600W435033CLBR25VY', '4BH50913E3481532P', NULL, '2016-12-28 06:25:10');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=149 ;

--
-- Dumping data for table `rent_request`
--

INSERT INTO `rent_request` (`id`, `product_id`, `requested_by`, `rent_fee`, `advance_amount`, `request_id`, `expired`, `request_cancel`, `start_date`, `end_date`, `approve`, `disapprove`, `extension`, `remark`, `rent_complete`, `payment_complete`, `created_date`) VALUES
(131, 12, 32, 4.00, 10.00, NULL, 0, 0, '2016-09-27', '2016-09-29', 1, 0, 0, NULL, 1, 1, '2016-09-27 00:57:08'),
(132, 12, 32, 4.00, 10.00, NULL, 0, 0, '2016-09-27', '2016-09-29', 1, 0, 0, NULL, 1, 1, '2016-09-27 07:31:08'),
(134, 12, 32, 4.00, 10.00, NULL, 1, 0, '2016-09-27', '2016-09-29', 0, 0, 0, NULL, 0, 0, '2016-09-28 17:53:33'),
(135, 12, 32, 4.00, 10.00, NULL, 1, 1, '2016-09-28', '2016-09-30', 0, 0, 0, NULL, 0, 0, '2016-09-29 16:00:18'),
(136, 12, 32, 6.00, 10.00, NULL, 1, 0, '2016-10-07', '2016-10-10', 0, 0, 0, NULL, 0, 0, '2016-10-12 11:05:27'),
(137, 12, 32, 6.00, 10.00, NULL, 1, 0, '2016-10-11', '2016-10-14', 0, 0, 0, NULL, 0, 0, '2016-10-12 11:03:54'),
(138, 12, 32, 4.00, 10.00, NULL, 1, 0, '2016-10-13', '2016-10-15', 0, 0, 0, NULL, 0, 0, '2016-10-11 05:22:53'),
(139, 12, 32, 2.00, 10.00, NULL, 1, 0, '2016-10-19', '2016-10-20', 0, 0, 0, NULL, 0, 0, '2016-10-20 05:14:35'),
(140, 12, 32, 6.00, 10.00, NULL, 1, 0, '2016-10-24', '2016-10-27', 0, 0, 0, NULL, 0, 1, '2016-10-21 02:09:08'),
(141, 19, 79, 0.29, 4.00, NULL, 0, 0, '2016-11-03', '2016-11-05', 1, 0, 0, NULL, 0, 1, '2016-11-03 05:20:22'),
(142, 12, 32, 4.00, 10.00, NULL, 1, 0, '2016-11-04', '2016-11-06', 0, 0, 0, NULL, 0, 0, '2016-11-04 02:57:17'),
(143, 12, 32, 4.00, 10.00, NULL, 1, 0, '2016-11-09', '2016-11-11', 0, 0, 0, NULL, 0, 0, '2016-11-09 02:48:11'),
(144, 12, 32, 4.00, 10.00, NULL, 1, 0, '2016-11-15', '2016-11-17', 0, 0, 0, NULL, 0, 0, '2016-11-15 01:34:11'),
(145, 12, 32, 4.00, 10.00, NULL, 1, 0, '2016-11-16', '2016-11-18', 0, 0, 0, NULL, 0, 0, '2016-11-15 01:57:28'),
(146, 12, 32, 6.00, 10.00, NULL, 1, 0, '2016-11-23', '2016-11-26', 0, 0, 0, NULL, 0, 0, '2016-11-16 03:26:04'),
(147, 12, 32, 4.00, 10.00, NULL, 0, 0, '2016-12-28', '2016-12-30', 0, 0, 0, NULL, 0, 1, '2016-12-28 03:47:09'),
(148, 12, 32, 4.00, 10.00, NULL, 0, 0, '2017-01-04', '2017-01-06', 0, 0, 0, NULL, 0, 1, '2016-12-28 06:23:23');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=266 ;

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
(177, 1000175651, 'temp/898515.png', '2016-09-06 10:49:57'),
(178, 1000055542, 'temp/714409.jpg', '2016-09-28 05:39:32'),
(179, 1000351340, 'temp/221684.jpg', '2016-09-28 05:39:45'),
(180, 1000507597, 'temp/284783.jpg', '2016-09-28 05:39:58'),
(181, 1000583981, 'temp/346642.jpg', '2016-09-28 05:40:01'),
(182, 1000168410, 'temp/14682.jpg', '2016-09-28 06:06:36'),
(183, 1000325925, 'temp/231373.png', '2016-10-12 05:40:43'),
(184, 1000106145, 'temp/560806.png', '2016-10-12 05:50:57'),
(185, 1000586132, 'temp/69407.png', '2016-10-12 05:58:59'),
(186, 1000805157, 'temp/873657.png', '2016-10-12 06:00:25'),
(187, 1000171097, 'temp/33158.png', '2016-10-12 06:01:58'),
(188, 1000092862, 'temp/442313.png', '2016-10-12 06:03:30'),
(189, 1000699478, 'temp/343648.png', '2016-10-12 06:03:34'),
(190, 1000500567, 'temp/268481.png', '2016-10-12 06:03:38'),
(191, 1000318154, 'temp/517155.png', '2016-10-12 06:03:47'),
(192, 1000656676, 'temp/489458.png', '2016-10-12 06:05:50'),
(193, 1000245453, 'temp/290233.png', '2016-10-12 06:06:38'),
(194, 1000606615, 'temp/668334.png', '2016-10-12 06:06:44'),
(195, 1000133937, 'temp/146978.png', '2016-10-12 06:10:15'),
(196, 1000686484, 'temp/281305.png', '2016-10-12 06:10:37'),
(197, 1000142901, 'temp/813685.png', '2016-10-12 06:10:50'),
(198, 1000753291, 'temp/36388.png', '2016-10-12 06:16:28'),
(199, 1000733985, 'temp/311374.png', '2016-10-12 06:16:39'),
(200, 1000078106, 'temp/189168.png', '2016-10-12 06:21:17'),
(201, 1000232349, 'temp/705731.png', '2016-10-12 06:21:28'),
(202, 1000725428, 'temp/54538.png', '2016-10-12 06:22:22'),
(203, 1000092989, 'temp/760831.png', '2016-10-12 06:26:18'),
(204, 1000568502, 'temp/267483.png', '2016-10-12 06:26:24'),
(205, 1000271308, 'temp/568326.png', '2016-10-12 06:26:33'),
(206, 1000326916, 'temp/49160.png', '2016-10-12 06:30:48'),
(207, 1000378698, 'temp/191281.png', '2016-10-12 06:30:53'),
(208, 1000441782, 'temp/499299.png', '2016-10-12 06:30:56'),
(209, 1000758874, 'temp/568174.png', '2016-10-12 06:31:03'),
(210, 1000535258, 'temp/694565.png', '2016-10-12 06:31:09'),
(211, 1000299975, 'temp/176632.png', '2016-10-12 06:45:10'),
(212, 1000601726, 'temp/18361.png', '2016-10-12 06:46:41'),
(213, 1000414884, 'temp/322854.png', '2016-10-12 06:47:04'),
(214, 1000722887, 'temp/609355.png', '2016-10-12 06:47:30'),
(215, 1000801283, 'temp/393476.png', '2016-10-12 06:48:58'),
(216, 1000815628, 'temp/115782.png', '2016-10-12 06:52:17'),
(217, 1000232433, 'temp/288806.png', '2016-10-12 06:58:47'),
(218, 1000237787, 'temp/338006.png', '2016-10-12 09:58:42'),
(219, 1000448330, 'temp/309433.jpg', '2016-10-12 10:00:05'),
(220, 1000629265, 'temp/344482.jpg', '2016-10-12 10:01:59'),
(221, 1000810367, 'temp/698362.jpg', '2016-10-13 06:25:39'),
(222, 1000765227, 'temp/709928.jpg', '2016-10-13 06:25:58'),
(223, 1000413874, 'temp/574507.jpg', '2016-10-13 06:26:18'),
(224, 1000276510, 'temp/815601.jpg', '2016-10-13 06:27:11'),
(225, 1000115188, 'temp/3876.jpg', '2016-10-13 06:29:09'),
(226, 1000653802, 'temp/477220.png', '2016-10-19 06:52:08'),
(230, 1000166373, 'temp/648969.png', '2016-10-19 09:48:29'),
(231, 1000713102, 'temp/281310.jpg', '2016-10-21 09:51:15'),
(232, 1000315494, 'temp/550423.jpg', '2016-10-21 10:04:26'),
(233, 1000033859, 'temp/857184.png', '2016-10-21 10:26:49'),
(234, 1000440871, 'temp/599656.jpg', '2016-10-21 10:26:55'),
(235, 1000183557, 'temp/825305.jpg', '2016-10-21 10:27:45'),
(236, 1000471125, 'temp/249690.jpg', '2016-10-21 11:29:20'),
(237, 1000522742, 'temp/571242.png', '2016-10-25 07:31:07'),
(238, 1000803984, 'temp/886252.jpg', '2016-10-25 07:31:16'),
(239, 1000302321, 'temp/557011.jpg', '2016-10-25 07:31:16'),
(240, 1000881326, 'temp/137738.jpg', '2016-10-25 12:51:42'),
(241, 1000309515, 'temp/284276.png', '2016-10-27 06:24:03'),
(242, 1000515000, 'temp/722250.jpg', '2016-10-27 07:14:17'),
(243, 1000674320, 'temp/13340.png', '2016-11-01 11:20:53'),
(244, 1000571385, 'temp/431646.png', '2016-11-01 11:25:43'),
(245, 1000242687, 'temp/718003.png', '2016-11-01 11:26:51'),
(246, 1000192668, 'temp/334213.png', '2016-11-11 11:33:19'),
(247, 1000879113, 'temp/300569.jpg', '2016-11-11 11:34:40'),
(248, 1000802355, 'temp/18811.jpg', '2016-11-11 11:34:40'),
(249, 1000736884, 'temp/646418.jpg', '2016-11-11 11:34:40'),
(250, 1000268091, 'temp/54927.jpg', '2016-11-30 10:27:50'),
(251, 1000071121, 'temp/638540.jpg', '2016-11-30 10:28:56'),
(252, 1000810386, 'temp/691232.jpg', '2016-11-30 10:29:01'),
(253, 1000119991, 'temp/703244.png', '2016-12-26 07:19:15'),
(254, 1000664011, 'temp/345429.png', '2016-12-26 07:23:42'),
(255, 1000177177, 'temp/837063.png', '2016-12-26 07:23:51'),
(256, 1000535970, 'temp/687363.png', '2016-12-26 07:24:16'),
(257, 1000407611, 'temp/139901.png', '2016-12-26 07:26:57'),
(258, 1000173152, 'temp/101236.png', '2016-12-26 07:32:54'),
(259, 1000577389, 'temp/457003.png', '2016-12-26 07:34:54'),
(260, 1000035348, 'temp/20739.png', '2016-12-26 07:38:04'),
(263, 1000887238, 'temp/378158.png', '2016-12-26 09:26:27'),
(264, 1000029120, 'temp/116665.png', '2016-12-26 09:29:12'),
(265, 1000320288, 'temp/487186.png', '2016-12-26 13:01:18');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=79 ;

--
-- Dumping data for table `user_address`
--

INSERT INTO `user_address` (`id`, `address`, `zip`, `city`, `state`, `lat`, `lng`, `created_date`) VALUES
(1, 'sdf', 'sdf', 'sdf', 'sdf', NULL, NULL, '2016-08-02 12:41:56'),
(2, '', '', '', '', NULL, NULL, '2016-08-04 12:47:12'),
(3, '', '', '', '', NULL, NULL, '2016-08-04 12:53:50'),
(4, '', '', '', '', NULL, NULL, '2016-08-04 13:17:31'),
(5, '', '', '', '', NULL, NULL, '2016-08-05 05:32:10'),
(6, '', '', '', '', NULL, NULL, '2016-08-05 06:54:46'),
(7, '', '', '', '', NULL, NULL, '2016-08-05 06:56:14'),
(8, '', '', '', '', NULL, NULL, '2016-08-05 06:57:24'),
(9, '', '', '', '', NULL, NULL, '2016-08-05 06:59:28'),
(10, '', '', '', '', NULL, NULL, '2016-08-05 07:04:06'),
(11, '', '', '', '', NULL, NULL, '2016-08-05 07:11:14'),
(12, '', '', '', '', NULL, NULL, '2016-08-05 09:22:41'),
(13, '', '', '', '', NULL, NULL, '2016-08-05 09:25:29'),
(14, '', '', '', '', NULL, NULL, '2016-08-05 09:32:03'),
(15, '', '', '', '', NULL, NULL, '2016-08-05 09:37:43'),
(16, '', '', '', '', NULL, NULL, '2016-08-05 09:40:14'),
(17, '', '', '', '', NULL, NULL, '2016-08-05 09:42:55'),
(18, '', '', '', '', NULL, NULL, '2016-08-05 12:11:04'),
(19, '', '', '', '', NULL, NULL, '2016-08-08 07:14:22'),
(20, '', '', '', '', NULL, NULL, '2016-08-08 08:02:14'),
(21, '', '', '', '', NULL, NULL, '2016-08-08 08:24:21'),
(22, '', '', '', '', NULL, NULL, '2016-08-08 08:56:09'),
(23, '', '', '', '', NULL, NULL, '2016-08-08 09:09:28'),
(24, '', '', '', '', NULL, NULL, '2016-08-08 09:40:15'),
(25, '', '', '', '', NULL, NULL, '2016-08-08 11:34:16'),
(26, '', '', '', '', NULL, NULL, '2016-08-09 07:43:33'),
(27, '', '', '', '', NULL, NULL, '2016-08-09 10:53:17'),
(28, '', '', '', '', NULL, NULL, '2016-08-09 12:29:42'),
(29, '', '', '', '', NULL, NULL, '2016-08-09 12:30:35'),
(30, '', '', '', '', NULL, NULL, '2016-08-09 12:31:21'),
(32, '', '', '', '', NULL, NULL, '2016-08-10 12:07:05'),
(33, '', '', '', '', NULL, NULL, '2016-08-10 12:10:22'),
(34, '', '', '', '', NULL, NULL, '2016-08-24 09:19:43'),
(35, '', '', '', '', NULL, NULL, '2016-08-25 10:04:00'),
(36, '', '', '', '', NULL, NULL, '2016-08-25 10:57:16'),
(37, '', '', '', '', NULL, NULL, '2016-08-25 11:00:35'),
(43, '', '', '', '', NULL, NULL, '2016-09-02 09:26:13'),
(44, '', '', '', '', NULL, NULL, '2016-09-02 09:31:20'),
(45, '', '', '', '', NULL, NULL, '2016-09-02 09:37:55'),
(46, '', '', '', '', NULL, NULL, '2016-09-02 09:42:16'),
(47, '', '', '', '', NULL, NULL, '2016-09-02 10:25:37'),
(48, '', '', '', '', NULL, NULL, '2016-09-02 10:29:21'),
(49, '', '', '', '', NULL, NULL, '2016-09-02 10:31:37'),
(50, '', '', '', '', NULL, NULL, '2016-09-02 10:34:59'),
(51, '', '', '', '', NULL, NULL, '2016-09-02 10:36:17'),
(52, '', '', '', '', NULL, NULL, '2016-09-02 12:41:41'),
(53, '', '', '', '', NULL, NULL, '2016-09-03 08:56:58'),
(54, '', '', '', '', NULL, NULL, '2016-09-03 09:01:53'),
(55, '', '', '', '', NULL, NULL, '2016-09-03 09:06:53'),
(56, '', '', '', '', NULL, NULL, '2016-09-03 09:11:49'),
(57, '', '', '', '', NULL, NULL, '2016-09-03 09:13:58'),
(58, '', '', '', '', NULL, NULL, '2016-09-03 10:28:03'),
(59, '', '', '', '', NULL, NULL, '2016-09-03 10:30:00'),
(60, '', '', '', '', NULL, NULL, '2016-09-03 10:44:50'),
(62, '', '', '', '', NULL, NULL, '2016-10-19 08:44:19'),
(69, '', '', '', '', NULL, NULL, '2016-10-19 10:22:13'),
(71, NULL, NULL, NULL, NULL, NULL, NULL, '2016-11-03 09:00:58'),
(72, NULL, NULL, NULL, NULL, NULL, NULL, '2016-11-03 09:01:36'),
(73, NULL, NULL, NULL, NULL, NULL, NULL, '2016-11-03 09:04:50'),
(74, NULL, NULL, NULL, NULL, NULL, NULL, '2016-11-03 09:29:26'),
(75, NULL, NULL, NULL, NULL, NULL, NULL, '2016-11-08 06:46:52'),
(76, NULL, NULL, NULL, NULL, NULL, NULL, '2016-11-08 07:17:19'),
(77, NULL, NULL, NULL, NULL, NULL, NULL, '2016-11-08 11:10:53'),
(78, '12', '132', '321', '132', NULL, NULL, '2016-12-27 05:28:12');

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
  `phone_number` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `profile_pic` text,
  `identity_doc_path` text,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `identity_type` (`identity_type_id`),
  KEY `user_inf_user_address` (`user_address_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=89 ;

--
-- Dumping data for table `user_inf`
--

INSERT INTO `user_inf` (`id`, `user_address_id`, `identity_type_id`, `first_name`, `last_name`, `phone_number`, `gender`, `profile_pic`, `identity_doc_path`, `created_date`) VALUES
(33, 19, 1, 'Mausum', 'Nandi', '', '', '{"original":{"path":"32/7355841379748.jpg","type":"","size":{"width":680,"height":420}},"thumb":[]}', 'identityDoc/32/10300038890383.documentIdentity', '2016-08-25 10:12:41'),
(34, 20, 1, 'Taiful', 'Islam', '', '', NULL, 'identityDoc/33/13172418657778.documentIdentity', '2016-08-08 08:02:14'),
(35, 21, 1, 'developer', 'wsit', '', '', 'null', 'identityDoc/34/14499115145635.jpg', '2016-08-08 08:24:21'),
(36, 22, 1, 'Maidul', 'Rafi', '', '', NULL, 'identityDoc/35/16406680045445.documentIdentity', '2016-08-08 08:56:09'),
(37, 23, 1, 'Mausum', 'Nandy', '', '', NULL, 'identityDoc/36/17206486365557.documentIdentity', '2016-08-08 09:09:28'),
(38, 24, 1, 'fayme', 'Pauli', '', '', NULL, 'identityDoc/37/19053048474532.documentIdentity', '2016-08-08 09:40:15'),
(39, 25, 2, 'fa', 'y me', '', '', NULL, 'identityDoc/38/25894182279794.docx', '2016-08-08 11:34:16'),
(40, 26, 1, 'Tahmina', 'A', '', '', NULL, 'identityDoc/39/13320013602830.docx', '2016-08-09 07:43:33'),
(41, 27, 2, 'Lima', 'N', '', '', '{"original":{"path":"32/7355841379748.jpg","type":"","size":{"width":680,"height":420}},"thumb":[]}', 'identityDoc/40/24704427305911.docx', '2016-11-04 07:57:29'),
(42, 28, 1, 'xc', 'zxc', '', '', NULL, 'identityDoc/41/30488810011776.png', '2016-08-09 12:29:42'),
(43, 29, 2, 'asd', 'sad', '', '', NULL, 'identityDoc/42/30542282440316.png', '2016-08-09 12:30:35'),
(44, 30, 1, 'asd', 'asd', '', '', NULL, 'identityDoc/43/30587637477572.png', '2016-08-09 12:31:21'),
(45, 32, 1, 'dummy', 'tummy', '', '', NULL, 'identityDoc/44/26962367826164.docx', '2016-08-10 12:07:05'),
(46, 33, 1, 'dummy', 'tummy', '', '', NULL, 'identityDoc/45/27159631397218.docx', '2016-08-10 12:10:22'),
(47, 34, 1, 'Modon', 'Chand', '', '', NULL, 'identityDoc/46/18881855098272.txt', '2016-08-24 09:19:43'),
(48, 35, 1, 'sdfsdf', 'sdfsdf', '', '', NULL, 'identityDoc/47/20898679824950.txt', '2016-08-25 10:04:00'),
(49, 36, 2, 'sdfs', 'sdfsdf', '', '', 'null', 'identityDoc/48/24094491958505.txt', '2016-08-25 10:57:16'),
(50, 37, 2, 'Mausum', 'Nandi', '', '', '{"original":{"path":"","type":"","size":{"width":0,"height":0}},"thumb":[]}', 'identityDoc/49/24293334369307.txt', '2016-08-25 11:00:35'),
(70, 59, 1, 'Wsit', 'Dev', '', '', '{"original":{"path":"69/670884.jpg","type":"","size":{"width":375,"height":380}},"thumb":[]}', '', '2016-09-03 10:30:00'),
(71, 60, 1, 'Maieedul', 'Islam', '', '', '{"original":{"path":"70/186526.jpg","type":"","size":{"width":720,"height":720}},"thumb":[]}', '', '2016-09-03 10:44:50'),
(80, 69, 2, 'Wsit', 'Developer', '', '', '{"original":{"path":"","type":"","size":{"width":0,"height":0}},"thumb":[]}', 'identityDoc/79/20296430819856.jpg', '2016-10-19 10:22:13'),
(81, 71, 1, 'u', 'u', '', '', '{"original":{"path":"","type":"","size":{"width":0,"height":0}},"thumb":[]}', '', '2016-11-03 09:00:58'),
(82, 72, 1, 'asd', 'asd', '', '', '{"original":{"path":"","type":"","size":{"width":0,"height":0}},"thumb":[]}', '', '2016-11-03 09:01:36'),
(83, 73, 1, 'Wsit', 'Developer', '', '', '{"original":{"path":"","type":"","size":{"width":0,"height":0}},"thumb":[]}', '', '2016-11-03 09:04:50'),
(84, 74, 1, 'sdf', 'sdf', '', '', '{"original":{"path":"","type":"","size":{"width":0,"height":0}},"thumb":[]}', '', '2016-11-03 09:29:26'),
(85, 75, 1, 'sdf', 'sdf', '', '', '{"original":{"path":"","type":"","size":{"width":0,"height":0}},"thumb":[]}', '', '2016-11-08 06:46:52'),
(86, 76, 1, 'asdasd', 'asdasd', '', '', '{"original":{"path":"","type":"","size":{"width":0,"height":0}},"thumb":[]}', '', '2016-11-08 07:17:19'),
(87, 77, 1, '&lt;script&gt;console.log(&quot;FirstName&quot;);&lt;/script&gt;', '&lt;script&gt;console.log(&quot;LastName&quot;);&lt;/script&gt;', '', '', '{"original":{"path":"","type":"","size":{"width":0,"height":0}},"thumb":[]}', '', '2016-11-08 11:10:53'),
(88, 78, 1, 'rafi', 'islam', '123456789', 'male', '{"original":{"path":"","type":"","size":{"width":0,"height":0}},"thumb":[]}', '', '2016-12-27 05:28:12');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `user_paypal_credential`
--

INSERT INTO `user_paypal_credential` (`id`, `app_credential_id`, `email`, `created_date`) VALUES
(2, 32, 'tahsin_1356501324_per@gmail.com', '2016-09-27 07:33:22'),
(3, 40, 'tahsin_1356501324_per@gmail.com', '2016-09-27 09:05:17'),
(4, 79, 'tahsin_1356501324_per@gmail.com', '2016-11-03 11:22:24');

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
  ADD CONSTRAINT `product_location_city_id` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
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
