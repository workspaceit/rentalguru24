ALTER TABLE  `app_login_credential` ADD  `email_confirmed` TINYINT( 1 ) NOT NULL DEFAULT  '1' AFTER  `varified` ;


ALTER TABLE  `product_rating` ADD  `rent_inf_id` INT NOT NULL AFTER  `product_id` ,
ADD INDEX (  `rent_inf_id` );
ALTER TABLE  `product_rating` ADD CONSTRAINT  `product_rating_rent_inf_id` FOREIGN KEY (  `rent_inf_id` ) REFERENCES  `rentguru24`.`rent_inf` (
`id`
) ON DELETE NO ACTION ON UPDATE NO ACTION ;


ALTER TABLE  `product_rating` ADD  `rent_request_id` INT NOT NULL AFTER  `rent_inf_id` ,
ADD INDEX (  `rent_request_id` );
ALTER TABLE  `product_rating` ADD CONSTRAINT  `product_rent_request_id` FOREIGN KEY (  `rent_inf_id` ) REFERENCES  `rentguru24`.`rent_request` (
`id`
) ON DELETE NO ACTION ON UPDATE NO ACTION ;


ALTER TABLE  `product_rating` CHANGE  `created_date`  `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ;