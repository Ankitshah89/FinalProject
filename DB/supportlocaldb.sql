-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema supportlocaldb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `supportlocaldb` ;

-- -----------------------------------------------------
-- Schema supportlocaldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `supportlocaldb` DEFAULT CHARACTER SET utf8 ;
USE `supportlocaldb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `role` ENUM('User', 'Admin', 'Business') NOT NULL DEFAULT 'User',
  `phone` VARCHAR(45) NULL,
  `created_at` DATE NULL,
  `active` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `preference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `preference` ;

CREATE TABLE IF NOT EXISTS `preference` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `preference_type` VARCHAR(100) NOT NULL,
  `preference_category` ENUM('Food', 'Sports', 'Entertainment', 'Shopping') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_preference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_preference` ;

CREATE TABLE IF NOT EXISTS `user_preference` (
  `user_id` INT NOT NULL,
  `preference_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `preference_id`),
  INDEX `fk_user_has_preference_preference1_idx` (`preference_id` ASC),
  INDEX `fk_user_has_preference_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_userpreference_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userpreference_preference1`
    FOREIGN KEY (`preference_id`)
    REFERENCES `preference` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `business`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `business` ;

CREATE TABLE IF NOT EXISTS `business` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(500) NOT NULL,
  `description` VARCHAR(1000) NULL,
  `phone` VARCHAR(45) NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `image_url` VARCHAR(500) NULL,
  `manager_id` INT NULL,
  `create_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_business_user1_idx` (`manager_id` ASC),
  CONSTRAINT `fk_business_user`
    FOREIGN KEY (`manager_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `business_preference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `business_preference` ;

CREATE TABLE IF NOT EXISTS `business_preference` (
  `business_id` INT NOT NULL,
  `preference_id` INT NOT NULL,
  PRIMARY KEY (`business_id`, `preference_id`),
  INDEX `fk_business_has_preference_preference1_idx` (`preference_id` ASC),
  INDEX `fk_business_has_preference_business1_idx` (`business_id` ASC),
  CONSTRAINT `fk_businesspreference_business1`
    FOREIGN KEY (`business_id`)
    REFERENCES `business` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_businesspreference_preference1`
    FOREIGN KEY (`preference_id`)
    REFERENCES `preference` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(500) NOT NULL,
  `street_2` VARCHAR(500) NULL,
  `city` VARCHAR(500) NOT NULL,
  `state` VARCHAR(500) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  `country` VARCHAR(500) NOT NULL,
  `business_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_business1_idx` (`business_id` ASC),
  CONSTRAINT `fk_address_business`
    FOREIGN KEY (`business_id`)
    REFERENCES `business` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_favourite_business`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_favourite_business` ;

CREATE TABLE IF NOT EXISTS `user_favourite_business` (
  `business_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`business_id`, `user_id`),
  INDEX `fk_user_favourite_business_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_userfavouritebusiness_business1`
    FOREIGN KEY (`business_id`)
    REFERENCES `business` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userfavouritebusiness_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review` ;

CREATE TABLE IF NOT EXISTS `review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `create_date` DATE NOT NULL,
  `business_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `description` VARCHAR(1000) NULL,
  `rating` INT(10) NULL,
  `notification` TINYINT(1) NULL DEFAULT 0,
  `active` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comments_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `uq_business_user` (`business_id` ASC, `user_id` ASC),
  CONSTRAINT `fk_review_business`
    FOREIGN KEY (`business_id`)
    REFERENCES `business` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review_comment` ;

CREATE TABLE IF NOT EXISTS `review_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `review_id` INT NULL,
  `content` TEXT NULL,
  `create_date` DATETIME NULL,
  `inreply_to_id` INT NULL,
  `user_id` INT NULL,
  `active` TINYINT(1) NULL,
  `date_updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reviewcomment_review_idx` (`review_id` ASC),
  INDEX `fk_comment_parentcomment_idx` (`inreply_to_id` ASC),
  INDEX `fk_reviewcomment_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_reviewcomment_review`
    FOREIGN KEY (`review_id`)
    REFERENCES `review` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_parentcomment`
    FOREIGN KEY (`inreply_to_id`)
    REFERENCES `review_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reviewcomment_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `article` ;

CREATE TABLE IF NOT EXISTS `article` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `business_id` INT NULL,
  `title` VARCHAR(500) NULL,
  `content` TEXT NULL,
  `create_at` DATETIME NULL,
  `image_url` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_article_user_idx` (`user_id` ASC),
  INDEX `fk_article_business_idx` (`business_id` ASC),
  CONSTRAINT `fk_article_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_article_business`
    FOREIGN KEY (`business_id`)
    REFERENCES `business` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `article_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `article_comment` ;

CREATE TABLE IF NOT EXISTS `article_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `article_id` INT NULL,
  `content` TEXT NULL,
  `create_date` DATETIME NULL,
  `inreply_to_id` INT NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_article_user_idx` (`user_id` ASC),
  INDEX `fk_article_parentarticle_idx` (`inreply_to_id` ASC),
  INDEX `fk_articlecomment_article_idx` (`article_id` ASC),
  CONSTRAINT `fk_articlecomment_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_article_parentarticle`
    FOREIGN KEY (`inreply_to_id`)
    REFERENCES `article_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articlecomment_article`
    FOREIGN KEY (`article_id`)
    REFERENCES `article` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS admin@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'admin'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `phone`, `created_at`, `active`) VALUES (1, 'Jason', 'Nash', 'abc@xyz.com', '$2a$10$iFcq7QU0MTFhsMESWUkkzeb2HR66wDjyt0gkMzbJFYNbmSylBX/Ce', 'Admin', '1234567890', NULL, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `phone`, `created_at`, `active`) VALUES (2, 'Devin', 'Homie', 'xyz@abc.com', '$2a$10$iFcq7QU0MTFhsMESWUkkzeb2HR66wDjyt0gkMzbJFYNbmSylBX/Ce', 'User', '5675432341', NULL, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `phone`, `created_at`, `active`) VALUES (3, 'Ankit', 'Shah', 'ankit@ankit.com', '$2a$10$iFcq7QU0MTFhsMESWUkkzeb2HR66wDjyt0gkMzbJFYNbmSylBX/Ce', 'Business', '1234567799', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `preference`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (1, 'climbing', 'Sports');
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (2, 'clothes', 'Shopping');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_preference`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `user_preference` (`user_id`, `preference_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `business`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`) VALUES (1, 'Mount Rushmore', 'Rock climbing', '123456789', 1, NULL, 1, NULL);
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`) VALUES (2, 'Mrs. Cooper', 'Boutique', '234512312', 1, NULL, 2, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `business_preference`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (1, '123 Street', NULL, 'Centennail', 'CO', '80112', 'USA', 1);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (2, '234 Street', NULL, 'Omaha', 'NE', '12345', 'USA', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_favourite_business`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `user_favourite_business` (`business_id`, `user_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `review` (`id`, `create_date`, `business_id`, `user_id`, `description`, `rating`, `notification`, `active`) VALUES (1, '2020-04-27', 1, 1, 'Great Rock Climbing place', 5, 1, 1);
INSERT INTO `review` (`id`, `create_date`, `business_id`, `user_id`, `description`, `rating`, `notification`, `active`) VALUES (2, '2020-04-29', 2, 1, 'Love Mrs.Cooper', 4, 1, 1);
INSERT INTO `review` (`id`, `create_date`, `business_id`, `user_id`, `description`, `rating`, `notification`, `active`) VALUES (3, '2020-04-17', 1, 2, 'Test Test', 3, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `review_comment` (`id`, `review_id`, `content`, `create_date`, `inreply_to_id`, `user_id`, `active`, `date_updated`) VALUES (1, 1, 'More content', '2020-04-27', NULL, 1, 1, NULL);
INSERT INTO `review_comment` (`id`, `review_id`, `content`, `create_date`, `inreply_to_id`, `user_id`, `active`, `date_updated`) VALUES (2, 1, 'Replying to more content', '2020-04-29', 1, 2, 1, NULL);
INSERT INTO `review_comment` (`id`, `review_id`, `content`, `create_date`, `inreply_to_id`, `user_id`, `active`, `date_updated`) VALUES (3, 1, 'Replying to 2', '2020-04-29', 2, 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `article`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `article` (`id`, `user_id`, `business_id`, `title`, `content`, `create_at`, `image_url`) VALUES (1, 1, 1, 'Hello World', 'Trial and Error', '2020-04-27', NULL);
INSERT INTO `article` (`id`, `user_id`, `business_id`, `title`, `content`, `create_at`, `image_url`) VALUES (2, 3, 2, 'Some Small Businesses That Got Aid Fear the Rules Too Much to Spend It', 'When a $192,000 loan from the federal government’s small-business aid program arrived in his bank account last month, George Evageliou, the founder of a custom woodworking company, felt like one of the lucky ones.\n\nUnder the program’s rules, Mr. Evageliou has eight weeks from the day he received the cash to spend it. But nearly three weeks after the clock started on April 14, he hasn’t used a penny.', '2020-05-01', NULL);
INSERT INTO `article` (`id`, `user_id`, `business_id`, `title`, `content`, `create_at`, `image_url`) VALUES (3, 3, 1, 'There’s a more accurate way to compare coronavirus deaths to the flu', 'Months into the coronavirus pandemic, some politicians and pundits continue to promote ham-handed comparisons between covid-19 and the seasonal flu to score political points.\n\nThough there are many ways to debunk this fundamentally flawed comparison, one of the clearest was put forth this week by Jeremy Samuel Faust, an emergency room physician at Brigham and Women’s Hospital at Harvard Medical School', '2020-05-01', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `article_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `article_comment` (`id`, `article_id`, `content`, `create_date`, `inreply_to_id`, `user_id`) VALUES (1, 1, 'Check Check', '2020-04-27', NULL, 1);
INSERT INTO `article_comment` (`id`, `article_id`, `content`, `create_date`, `inreply_to_id`, `user_id`) VALUES (2, 1, 'Reply to Check Check', '2020-04-29', 1, 2);

COMMIT;

