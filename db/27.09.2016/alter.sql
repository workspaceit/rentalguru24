ALTER TABLE  `rent_inf` ADD  `rent_complete` TINYINT( 1 ) NOT NULL DEFAULT  '0' AFTER  `expired` ;
ALTER TABLE  `rent_request` ADD  `rent_complete` TINYINT( 1 ) NOT NULL AFTER  `remark` ;
ALTER TABLE  `rent_request` CHANGE  `payment_complete`  `payment_complete` TINYINT( 1 ) NOT NULL DEFAULT  '0';
