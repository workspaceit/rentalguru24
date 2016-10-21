ALTER TABLE  `product_location` CHANGE  `lat`  `lat` DOUBLE( 13, 9 ) NULL DEFAULT NULL ;
ALTER TABLE  `product_location` CHANGE  `lng`  `lng` DOUBLE( 13, 9 ) NULL DEFAULT NULL ;
ALTER TABLE `product_location` CHANGE `created_date` `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE `user_address` ADD `lat` DOUBLE(13,9)  NULL AFTER `state`;
ALTER TABLE `user_address` ADD `lng` DOUBLE(13,9)  NULL AFTER `lat`;

ALTER TABLE `user_address` CHANGE `created_date` `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;