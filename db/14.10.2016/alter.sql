ALTER TABLE `product_rating` ADD `review_text` TEXT NOT NULL AFTER `rate_value`;
ALTER TABLE `product_rating` CHANGE `review_text` `review_text` TEXT CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL;