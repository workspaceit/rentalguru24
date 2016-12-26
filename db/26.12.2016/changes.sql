ALTER TABLE  `product_location` DROP  `city` ;
ALTER TABLE  `product_location` ADD  `city_id` INT NULL AFTER  `state_id` ;
ALTER TABLE  `product_location` ADD INDEX (  `city_id` ) ;

ALTER TABLE  `product_location` ADD CONSTRAINT  `product_location_city_id`
     FOREIGN KEY (  `city_id` )
     REFERENCES  `cities` (`id`)
     ON DELETE SET NULL ON UPDATE SET NULL ;