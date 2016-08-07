-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 07, 2016 at 07:50 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rentguru24`
--

-- --------------------------------------------------------

--
-- Table structure for table `activation`
--

CREATE TABLE `activation` (
  `id` int(11) NOT NULL,
  `app_login_credential_id` int(11) NOT NULL,
  `code` varchar(500) NOT NULL,
  `expire_date` date NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `app_login_credential`
--

CREATE TABLE `app_login_credential` (
  `id` int(11) NOT NULL,
  `user_inf_id` int(11) NOT NULL,
  `role` int(11) NOT NULL,
  `email` varchar(500) NOT NULL,
  `password` varchar(500) NOT NULL,
  `accesstoken` varchar(500) NOT NULL,
  `varified` tinyint(1) NOT NULL,
  `blocked` tinyint(1) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `app_login_credential`
--

INSERT INTO `app_login_credential` (`id`, `user_inf_id`, `role`, `email`, `password`, `accesstoken`, `varified`, `blocked`, `created_date`) VALUES
(1, 1, -1, 'dummy2@tummy.com', '$2a$10$Xf.T6cLdQj/5/lGgG2CoCu.momkOn5C6X3WDKdgDH1pSLjYRxqReS', '6d8198a927ae15186ee58e35f565c3e3', 0, 0, '2016-08-02 12:23:07'),
(2, 3, -1, 'dummy3@tummy.com', '$2a$10$RmKNXJraFDFRVi3S0nMYbOj5uPiSP4NDyzMk5ypg.jLRTitzfbcSS', '08e360df61e971ec7c5ce390be5b404e', 0, 0, '2016-08-02 12:36:18'),
(3, 4, -1, 'dummy4@tummy.com', '$2a$10$MQSPdLB5Gs5ADG17.UE7SuMu1fKnqIjajggnnrALkMLbhx6ZJB7yu', '8b49c98c87b8064ff98475c9610a3a7d', 0, 0, '2016-08-02 12:38:17'),
(4, 5, -1, 'dummy5@tummy.com', '$2a$10$lsbHg1USMjACjitLIsJPhu4mqkneXW8HbX/2heQYixbVD8RLnEvdq', 'd13ef753bba918a1db2a10faee2b72d2', 0, 0, '2016-08-02 12:41:09'),
(5, 6, -1, 'dummy6@tummy.com', '$2a$10$Doo9QDp47yjpCF7JyS4u6.TWthR5kega4WU59VWbazbZ5sRSjzrYO', '797d6153c1999d808fe8b00f57dde76b', 0, 0, '2016-08-02 12:41:56'),
(6, 7, -1, 'dummy7@tummy.com', '$2a$10$4CK6bYoqf5ougH2RYfDWPexIiUendXaZEk2D2YYdGgjrrCfEN3/Fu', 'a1412578fa3ddd72ca299d0908211a6b', 0, 0, '2016-08-02 12:45:12'),
(7, 8, -1, 'dummy8@tummy.com', '$2a$10$GNikbt58FwC3GosQThk.E.8AYy07P67KwfaNr5h.E.O.TBW.BsPi2', 'd0222d8ed1680c7092bc035fd04add73', 0, 0, '2016-08-03 07:06:07'),
(8, 9, -1, 'dummy9@tummy.com', '$2a$10$cVfGyaaTXQWad5Tw0WuvgukNShvvkJJU8e0/J6nfbadweAd2njo7K', 'd9475b0bed4e3fb5a5a6baac1e9f1999', 0, 0, '2016-08-03 07:09:13'),
(9, 10, -1, 'dummy10@tummy.com', '$2a$10$p/bsoKk5nmhU96uYuvC4I.iP1x99iXCedyGU9hR72HWPTEX7UWp/G', '043a8522fa503d795509bafea91c3dcc', 0, 0, '2016-08-03 07:14:51'),
(10, 11, -1, 'dummy11@tummy.com', '$2a$10$qTPWRzldyEMDFkCN7NDzEudEqAfx3DQyaINe7rd7LPivgJDmLPK52', 'c50e59c0ed8a6f64223164b3ea80c3f4', 0, 0, '2016-08-03 07:32:21'),
(11, 12, -1, 'dummy13@tummy.com', '$2a$10$YDp.6NVgVsyBOoDtZf28g.BQ5EMG0zExVRahtlq6C8UJDb288VL6i', '306711ada72c3d61ae3995b108936459', 0, 0, '2016-08-03 07:41:02'),
(12, 13, -1, 'dummy12@tummy.com', '$2a$10$4kllKh2fIrPGloKp2EQCBuO0A0187oHHmyxAjG2ss9pO4Aj0mVsnC', 'c024e41ea7c70ee577380f25419a7666', 0, 0, '2016-08-03 07:41:49'),
(13, 14, -1, 'dummy13@tummy.com', '$2a$10$UofISgZOOZO.UfiAqPRHHulfuSorNDK9Lu8qMGgHdm5oFz3S7wwNS', '306711ada72c3d61ae3995b108936459', 0, 0, '2016-08-03 08:33:30'),
(14, 15, -1, 'dummy14@tummy.com', '$2a$10$sHlJ.y.hJrhO0s0ptpLl9eiG/0unX9o1w.HmuIVx69wujaQHUE9Zu', '9723fac4d02586269b1d3ffb6c9dfb0b', 0, 0, '2016-08-03 09:01:39'),
(15, 16, -1, 'dummy15@tummy.com', '$2a$10$lmn5rr2vHAR9TDq53lZkTeAT3X6iNfLmFqe2TDq2BrQPqxO6mmiMe', 'a5af1f9176d9aa0cbc87178129bc1fc4', 0, 0, '2016-08-04 12:47:12'),
(16, 17, -1, 'dummy16@tummy.com', '$2a$10$eJlvxnIHeR/1aZ5Pjc4FteTk63enwC9LWmu.7BXUPFi/HYFxVlbeu', '9947623d6aab7e9e217cb4da94d58753', 0, 0, '2016-08-04 12:53:50'),
(17, 18, -1, 'sadasd@gmail.com', '$2a$10$BocModh6AJHmD7/BltL2N.kvGBFNQ5oJftg7MtKp2oswgJid5uIbC', '57970f52d64b2080bb9bd5ece1905aee', 0, 0, '2016-08-04 13:17:31'),
(18, 19, -1, 'zakariya@gmail.com', '25f9e794323b453885f5181f1b624d0b', '9180ef539e81dd41a9cb8f284bfed7db', 0, 0, '2016-08-05 05:32:10'),
(19, 20, -1, 'sadasasfasgtdsrutuskd@gmail.com', '25f9e794323b453885f5181f1b624d0b', '7d62afb8e764c621379a259ccb5a6189', 0, 0, '2016-08-05 06:54:45'),
(20, 21, -1, 'sadaasdaww2eassd@gmail.com', '25f9e794323b453885f5181f1b624d0b', '7fafe40a62654bb3458cd521e843e3f5', 0, 0, '2016-08-05 06:56:14'),
(21, 22, -1, 'zakarigfggftfhtfhfthtya@gmail.coms', '25f9e794323b453885f5181f1b624d0b', 'b0bd3ba89a9afd02b2adb75e1ec8a490', 0, 0, '2016-08-05 06:57:24'),
(22, 23, -1, 'omsssssssssar@hotmail.com', '25f9e794323b453885f5181f1b624d0b', '3f49ee5fbca9874a7f63731c68c73c85', 0, 0, '2016-08-05 06:59:27'),
(23, 24, -1, 'sadasasfasssssssssgtdsrutuskd@gmail.com', '25f9e794323b453885f5181f1b624d0b', '65ae42e5071c783a65b2fe59eb427138', 0, 0, '2016-08-05 07:04:06'),
(24, 25, -1, 'sadasssssssssssssssssssssssssssssssd@gmail.com', '25f9e794323b453885f5181f1b624d0b', '745c6b3be0b8f17beb077d8c2d83520f', 0, 0, '2016-08-05 07:11:14'),
(25, 26, -1, 'sadaknajsdhaowinlauiwesd@gmail.com', '25f9e794323b453885f5181f1b624d0b', 'b34a98090c19a2a1570b2214960892eb', 0, 0, '2016-08-05 09:22:41'),
(26, 27, -1, 'sadewrewr3wrdfhtrikiasd@gmail.com', '25f9e794323b453885f5181f1b624d0b', '6b90192d79252f0ebbb64b4d5949447e', 0, 0, '2016-08-05 09:25:29'),
(27, 28, -1, 'jansfkjbanfujasfn@gmail.com', '25f9e794323b453885f5181f1b624d0b', '30589c9ecc23bcf85cd771b06056d1e7', 0, 0, '2016-08-05 09:32:02'),
(28, 29, -1, 'askjdnunasdjunywldamkl@gmail.com', '25f9e794323b453885f5181f1b624d0b', '4c4e6b7cc117212d573947ef6ac4c288', 0, 0, '2016-08-05 09:37:43'),
(29, 30, -1, 'sadasasfasssssssgtdsrutuskd@gmail.com', '25f9e794323b453885f5181f1b624d0b', '859bb0c2e857bd11bda332e3af49f4bc', 0, 0, '2016-08-05 09:40:14'),
(30, 31, -1, 'ssssssssssssadasd@gmail.com', '25f9e794323b453885f5181f1b624d0b', 'cada720e067ce9f03e31b91b92e93c76', 0, 0, '2016-08-05 09:42:55'),
(31, 32, -1, 'zakssssssssariya@gmail.com', '25f9e794323b453885f5181f1b624d0b', '0a2ce216bf97d576bb65e0a4596cbd2c', 0, 0, '2016-08-05 12:11:04');

-- --------------------------------------------------------

--
-- Table structure for table `attributes`
--

CREATE TABLE `attributes` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `attribute_values`
--

CREATE TABLE `attribute_values` (
  `id` int(11) NOT NULL,
  `attributes_id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `created_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `sorted_order` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `parent_id`, `sorted_order`, `created_by`, `created_date`) VALUES
(1, 'parent_category', NULL, 2, 1, '2016-08-06 15:59:11'),
(2, 'parent_category 1', NULL, 2, 1, '2016-08-06 16:23:14'),
(3, 'child1', 2, 3, 1, '2016-08-06 16:23:54'),
(4, 'child2', 3, 4, 1, '2016-08-06 16:24:04'),
(5, 'parent_category 2', NULL, 2, 1, '2016-08-06 16:24:29'),
(6, 'child 3', 5, 3, 1, '2016-08-06 16:24:38'),
(7, 'child 4', 5, 4, 1, '2016-08-06 16:24:46');

-- --------------------------------------------------------

--
-- Table structure for table `identity_type`
--

CREATE TABLE `identity_type` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `description` text NOT NULL,
  `images` text NOT NULL,
  `current_value` double(200,2) NOT NULL,
  `rent_fee` double(8,2) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `currently_available` tinyint(1) NOT NULL,
  `review_status` tinyint(1) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_attribute`
--

CREATE TABLE `product_attribute` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `attribute_values_id` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_availability`
--

CREATE TABLE `product_availability` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `from_date` datetime NOT NULL,
  `to_date` datetime NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--

CREATE TABLE `product_category` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_location`
--

CREATE TABLE `product_location` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `city` varchar(200) DEFAULT NULL,
  `state` varchar(200) DEFAULT NULL,
  `formated_address` text NOT NULL,
  `zip` varchar(200) DEFAULT NULL,
  `lat` float(10,6) NOT NULL,
  `lng` float(10,6) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_received`
--

CREATE TABLE `product_received` (
  `id` int(11) NOT NULL,
  `rent_product_id` int(11) NOT NULL,
  `is_received` tinyint(1) NOT NULL,
  `received_date` datetime NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_returned`
--

CREATE TABLE `product_returned` (
  `id` int(11) NOT NULL,
  `rent_product_id` int(11) NOT NULL,
  `is_returned` tinyint(1) NOT NULL,
  `returned_date` datetime NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rent_product`
--

CREATE TABLE `rent_product` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `ends_date` date NOT NULL,
  `product_returned` tinyint(1) NOT NULL,
  `product_received` tinyint(1) NOT NULL,
  `expired` tinyint(1) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rent_request`
--

CREATE TABLE `rent_request` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `requested_by` int(11) NOT NULL,
  `rent_product_id` int(11) DEFAULT NULL,
  `request_id` int(11) DEFAULT NULL COMMENT 'Request extension',
  `request_cancel` int(11) NOT NULL DEFAULT '0',
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `approve` tinyint(1) NOT NULL DEFAULT '0',
  `extension` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `temp_file`
--

CREATE TABLE `temp_file` (
  `id` int(11) NOT NULL,
  `token` int(11) NOT NULL,
  `path` text NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(61, 1000244241, 'temp/195807.jpg', '2016-08-05 11:14:01');

-- --------------------------------------------------------

--
-- Table structure for table `user_address`
--

CREATE TABLE `user_address` (
  `id` int(11) NOT NULL,
  `address` text NOT NULL,
  `zip` varchar(20) NOT NULL,
  `city` varchar(200) NOT NULL,
  `state` varchar(200) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(18, '', '', '', '', '2016-08-05 12:11:04');

-- --------------------------------------------------------

--
-- Table structure for table `user_inf`
--

CREATE TABLE `user_inf` (
  `id` int(11) NOT NULL,
  `user_address_id` int(11) DEFAULT NULL,
  `identity_type_id` int(11) NOT NULL,
  `first_name` varchar(500) NOT NULL,
  `last_name` varchar(500) NOT NULL,
  `identity_doc_path` text NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_inf`
--

INSERT INTO `user_inf` (`id`, `user_address_id`, `identity_type_id`, `first_name`, `last_name`, `identity_doc_path`, `created_date`) VALUES
(1, NULL, 1, 'Dummy', 'Tummy', '', '2016-08-03 08:10:49'),
(3, NULL, 1, 'Dummy', 'Tummy', '', '2016-08-03 08:10:49'),
(4, NULL, 1, 'Dummy', 'Tummy', '', '2016-08-03 08:10:49'),
(5, NULL, 1, 'Dummy', 'Tummy', '', '2016-08-03 08:10:49'),
(6, 1, 1, 'Dummy', 'Tummy', '', '2016-08-03 08:10:49'),
(7, NULL, 1, 'Dummy', 'Tummy', '', '2016-08-03 08:10:49'),
(8, NULL, 1, 'Dummy', 'Tummy', '/home/mi/Projects/j2ee/rentguru24files/identityDoc/7/10340174867435.pdf', '2016-08-03 08:10:49'),
(9, NULL, 1, 'Dummy', 'Tummy', '/home/mi/Projects/j2ee/rentguru24files/identityDoc/8/10526220065227.pdf', '2016-08-03 08:10:49'),
(10, NULL, 1, 'Dummy', 'Tummy', '/home/mi/Projects/j2ee/rentguru24files/identityDoc/9/10864440785468.pdf', '2016-08-03 08:10:49'),
(11, NULL, 1, 'Dummy', 'Tummy', '10/11914235854858.pdf', '2016-08-03 08:10:49'),
(12, NULL, 1, 'Dummy', 'Tummy', 'identityDoc/11/12435434870569.pdf', '2016-08-03 08:10:49'),
(13, NULL, 1, 'Dummy', 'Tummy', 'identityDoc/12/12481864261034.pdf', '2016-08-03 08:10:49'),
(14, NULL, 1, 'Dummy', 'Tummy', 'identityDoc/13/15582991791391.pdf', '2016-08-03 08:33:30'),
(15, NULL, 1, 'Dummy', 'Tummy', 'identityDoc/14/17272600703079.pdf', '2016-08-03 09:01:39'),
(16, 2, 1, 'dummy', 'tummy', 'identityDoc/15/1381116257134.pdf', '2016-08-04 12:47:12'),
(17, 3, 1, 'dummy', 'tummy', 'identityDoc/16/1778700127238.pdf', '2016-08-04 12:53:50'),
(18, 4, 1, 'zakariya', 'naseef', 'identityDoc/17/8279041029990.jpg', '2016-08-04 13:17:31'),
(19, 5, 1, 'zakariya', 'naseef', 'identityDoc/18/4694518004098.jpg', '2016-08-05 05:32:10'),
(20, 6, 1, 'zakariya', 'naseef', 'identityDoc/19/9649766072717.jpg', '2016-08-05 06:54:46'),
(21, 7, 1, 'zakariya', 'naseef', 'identityDoc/20/9738156948310.jpg', '2016-08-05 06:56:14'),
(22, 8, 1, 'zakariya', 'asawdawrryhfj', 'identityDoc/21/9807924206240.jpg', '2016-08-05 06:57:24'),
(23, 9, 1, 'zakariya', 'naseef', 'identityDoc/22/9931722817626.jpg', '2016-08-05 06:59:27'),
(24, 10, 1, 'zakariya', 'naseef', 'identityDoc/23/10210092722168.jpg', '2016-08-05 07:04:06'),
(25, 11, 1, 'zakariya', 'naseef', 'identityDoc/24/10637843147018.jpg', '2016-08-05 07:11:14'),
(26, 12, 1, 'zakariya', 'naseef', 'identityDoc/25/1430084885068.jpg', '2016-08-05 09:22:41'),
(27, 13, 1, 'zakariya', 'naseef', 'identityDoc/26/1597714985659.jpg', '2016-08-05 09:25:29'),
(28, 14, 1, 'zakariya', 'naseef', 'identityDoc/27/1991443376119.jpg', '2016-08-05 09:32:02'),
(29, 15, 1, 'zakariya', 'naseef', 'identityDoc/28/2332143704301.jpeg', '2016-08-05 09:37:43'),
(30, 16, 1, 'zakariya', 'naseef', 'identityDoc/29/2482667224530.jpeg', '2016-08-05 09:40:14'),
(31, 17, 1, 'zakariya', 'naseef', 'identityDoc/30/2644072510930.jpeg', '2016-08-05 09:42:55'),
(32, 18, 1, 'zakariya', 'naseef', 'identityDoc/31/11532035329432.jpg', '2016-08-05 12:11:04');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activation`
--
ALTER TABLE `activation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `activation_app_login_credential` (`app_login_credential_id`);

--
-- Indexes for table `app_login_credential`
--
ALTER TABLE `app_login_credential`
  ADD PRIMARY KEY (`id`),
  ADD KEY `app_credential_unser_inf_id` (`user_inf_id`);

--
-- Indexes for table `attributes`
--
ALTER TABLE `attributes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `attributes_app_login_credential` (`created_by`);

--
-- Indexes for table `attribute_values`
--
ALTER TABLE `attribute_values`
  ADD PRIMARY KEY (`id`),
  ADD KEY `attribute_values_app_login_credential` (`created_by`),
  ADD KEY `attribute_values_attributes` (`attributes_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_app_login_credential` (`created_by`),
  ADD KEY `category_category` (`parent_id`);

--
-- Indexes for table `identity_type`
--
ALTER TABLE `identity_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_app_login_credential` (`owner_id`);

--
-- Indexes for table `product_attribute`
--
ALTER TABLE `product_attribute`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_attribute_attribute_values` (`attribute_values_id`),
  ADD KEY `product_attribute_product` (`product_id`);

--
-- Indexes for table `product_availability`
--
ALTER TABLE `product_availability`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product_category`
--
ALTER TABLE `product_category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_category_app_login_credential` (`created_by`),
  ADD KEY `product_category_category` (`category_id`),
  ADD KEY `product_category_product` (`product_id`);

--
-- Indexes for table `product_location`
--
ALTER TABLE `product_location`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product_received`
--
ALTER TABLE `product_received`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rent_product_id` (`rent_product_id`);

--
-- Indexes for table `product_returned`
--
ALTER TABLE `product_returned`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rent_product_id` (`rent_product_id`);

--
-- Indexes for table `rent_product`
--
ALTER TABLE `rent_product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `booking_product` (`product_id`);

--
-- Indexes for table `rent_request`
--
ALTER TABLE `rent_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rent_request_app_login_credential` (`requested_by`),
  ADD KEY `rent_request_booking` (`rent_product_id`),
  ADD KEY `rent_request_product` (`product_id`),
  ADD KEY `rent_request_rent_request` (`request_id`);

--
-- Indexes for table `temp_file`
--
ALTER TABLE `temp_file`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_address`
--
ALTER TABLE `user_address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_inf`
--
ALTER TABLE `user_inf`
  ADD PRIMARY KEY (`id`),
  ADD KEY `identity_type` (`identity_type_id`),
  ADD KEY `user_inf_user_address` (`user_address_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activation`
--
ALTER TABLE `activation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `app_login_credential`
--
ALTER TABLE `app_login_credential`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `attributes`
--
ALTER TABLE `attributes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `attribute_values`
--
ALTER TABLE `attribute_values`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `identity_type`
--
ALTER TABLE `identity_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product_attribute`
--
ALTER TABLE `product_attribute`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product_availability`
--
ALTER TABLE `product_availability`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product_category`
--
ALTER TABLE `product_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product_location`
--
ALTER TABLE `product_location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product_received`
--
ALTER TABLE `product_received`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product_returned`
--
ALTER TABLE `product_returned`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `rent_product`
--
ALTER TABLE `rent_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `rent_request`
--
ALTER TABLE `rent_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `temp_file`
--
ALTER TABLE `temp_file`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;
--
-- AUTO_INCREMENT for table `user_address`
--
ALTER TABLE `user_address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `user_inf`
--
ALTER TABLE `user_inf`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
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
  ADD CONSTRAINT `product_category_app_login_credential` FOREIGN KEY (`created_by`) REFERENCES `app_login_credential` (`id`),
  ADD CONSTRAINT `product_category_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `product_category_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product_location`
--
ALTER TABLE `product_location`
  ADD CONSTRAINT `product_location_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rent_product`
--
ALTER TABLE `rent_product`
  ADD CONSTRAINT `booking_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `rent_request`
--
ALTER TABLE `rent_request`
  ADD CONSTRAINT `rent_request_app_login_credential` FOREIGN KEY (`requested_by`) REFERENCES `app_login_credential` (`id`),
  ADD CONSTRAINT `rent_request_booking` FOREIGN KEY (`rent_product_id`) REFERENCES `rent_product` (`id`),
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
