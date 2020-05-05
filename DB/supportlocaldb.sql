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
  `user_image` VARCHAR(1000) NULL,
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
  `phone` VARCHAR(45) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `image_url` VARCHAR(500) NULL,
  `manager_id` INT NULL,
  `create_date` DATETIME NULL,
  `web_url` VARCHAR(500) NULL,
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
  `active` TINYINT(1) NULL DEFAULT 1,
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
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `phone`, `created_at`, `active`, `user_image`) VALUES (1, 'Jason', 'Nash', 'abc@xyz.com', '$2a$10$iFcq7QU0MTFhsMESWUkkzeb2HR66wDjyt0gkMzbJFYNbmSylBX/Ce', 'Admin', '1234567890', '2020-04-29', 1, 'https://pmchollywoodlife.files.wordpress.com/2020/03/howie-mandel-agt-hazmat-suit-coronavirus-rex-ftr.jpg');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `phone`, `created_at`, `active`, `user_image`) VALUES (2, 'Devin', 'Homie', 'xyz@abc.com', '$2a$10$iFcq7QU0MTFhsMESWUkkzeb2HR66wDjyt0gkMzbJFYNbmSylBX/Ce', 'User', '5675432341', '2020-05-01', 1, 'https://lh3.googleusercontent.com/-6WuiiOSNyPo/WcJwbEbLCcI/AAAAAAAAwtE/3CWQX3r_hOMUh1pBEVOMcP4h_j0gLAHBwCHMYCw/s1600/Hugh-Jackman-awesome-dp-profile-pics-MyWhatsappImages.com-244.jpg');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `phone`, `created_at`, `active`, `user_image`) VALUES (3, 'Ankit', 'Dude', 'ankit@ankit.com', '$2a$10$iFcq7QU0MTFhsMESWUkkzeb2HR66wDjyt0gkMzbJFYNbmSylBX/Ce', 'Business', '1234567799', '2020-05-01', 1, 'https://pmchollywoodlife.files.wordpress.com/2020/03/howie-mandel-agt-hazmat-suit-coronavirus-rex-ftr.jpg');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `phone`, `created_at`, `active`, `user_image`) VALUES (4, 'Steve', 'King', 'steve@steve.com', '$2a$10$iFcq7QU0MTFhsMESWUkkzeb2HR66wDjyt0gkMzbJFYNbmSylBX/Ce', 'Business', '4352671611', '2020-05-01', 1, 'http://www.goodmorningimagesforlover.com/wp-content/uploads/2019/01/sfd54s.jpg');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `phone`, `created_at`, `active`, `user_image`) VALUES (5, 'Lewis', 'Hamilton', 'lewis@lewis.com', '$2a$10$iFcq7QU0MTFhsMESWUkkzeb2HR66wDjyt0gkMzbJFYNbmSylBX/Ce', 'Business', '1345823452', '2020-05-01', 1, 'https://avatarfiles.alphacoders.com/107/107911.jpg');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `phone`, `created_at`, `active`, `user_image`) VALUES (6, 'Anthony', 'Hopkins', 'anthony@anthony.com', '$2a$10$iFcq7QU0MTFhsMESWUkkzeb2HR66wDjyt0gkMzbJFYNbmSylBX/Ce', 'Business', '7087675442', '2020-05-02', 1, 'https://i3.kym-cdn.com/photos/images/facebook/000/409/177/4ab.png');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `phone`, `created_at`, `active`, `user_image`) VALUES (7, 'Brandon', 'King', 'brandon@brandon.com', '$2a$10$iFcq7QU0MTFhsMESWUkkzeb2HR66wDjyt0gkMzbJFYNbmSylBX/Ce', 'Business', '4567283821', '2020-05-02', 1, 'https://m.media-amazon.com/images/M/MV5BMTMwNTAyMTYxM15BMl5BanBnXkFtZTYwNjQ1NDkz._V1_UY1200_CR85,0,630,1200_AL_.jpg');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `phone`, `created_at`, `active`, `user_image`) VALUES (8, 'Ramesh', 'Bhatt', 'ramesh@ramesh.com', '$2a$10$iFcq7QU0MTFhsMESWUkkzeb2HR66wDjyt0gkMzbJFYNbmSylBX/Ce', 'Business', '7542456799', '2020-05-02', 1, 'https://lh3.googleusercontent.com/-rcvTUg2sz5U/VzKUZCb44WI/AAAAAAAACDA/8wKhhMLhDHI/s1600/Lara-Dutta-Dp-profile-pics-90.jpg');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `phone`, `created_at`, `active`, `user_image`) VALUES (9, 'Jackie', 'Chan', 'jackie@jackie.com', '$2a$10$iFcq7QU0MTFhsMESWUkkzeb2HR66wDjyt0gkMzbJFYNbmSylBX/Ce', 'Business', '4562819132', '2020-05-02', 1, 'https://www.juvefc.com/wp-content/uploads/2018/08/RONALDO_501x752.png');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `phone`, `created_at`, `active`, `user_image`) VALUES (10, 'Robbie', 'Williams', 'robie@robie.com', '$2a$10$iFcq7QU0MTFhsMESWUkkzeb2HR66wDjyt0gkMzbJFYNbmSylBX/Ce', 'Business', '1231231333', '2020-05-03', 1, 'https://lh3.googleusercontent.com/-H1UhNnAB_os/WXNicO6hZNI/AAAAAAAAvuE/zuNu4R8vsKgk0v1QpKZhMEiFCbMzozuKgCHMYCw/s1600/Mark-Wahlberg-awesome-profile-pics-MyWhatsappImages.com-dp-pics-whatsapp-Facebook-Instagram-813.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `preference`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (1, 'Burritos', 'Food');
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (2, 'Winery', 'Food');
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (3, 'Micobrews', 'Food');
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (4, 'Arts & crafts', 'Shopping');
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (5, 'Mini Golf', 'Entertainment');
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (6, 'Kids Activities', 'Sports');
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (7, 'Gastropubs', 'Entertainment');
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (8, 'Trainers, Gym, Sports Club', 'Sports');
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (9, 'Chinese', 'Food');
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (10, 'Shopping Centers', 'Shopping');
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (11, 'Sporting Goods', 'Sports');
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (12, 'Stadiums & Arenas', 'Sports');
INSERT INTO `preference` (`id`, `preference_type`, `preference_category`) VALUES (13, 'Shopping Mall', 'Shopping');

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
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`, `web_url`) VALUES (1, 'Monkey Business Burritos', 'Denver Restaurants - Burritos', '3033829710', 1, 'http://www.lynnkehr.com/wp-content/uploads/2016/05/MBB-600x300.jpg', 3, '2020-05-01', 'http://monkeybusinessburritos.com/');
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`, `web_url`) VALUES (2, 'Water 2 Wine ', 'Winery', '3037999463', 1, 'https://www.visitbrookfield.com/wp-content/sabai/File/files/fb5c8da09c77d509a2d64282c58b33ba.jpg', 4, '2020-05-01', 'https://www.water2wine.com/');
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`, `web_url`) VALUES (3, 'Pug Ryan\'s Steakhouse & Brewery', 'Microbrews, Catering', '9704682145', 1, 'http://coloradobeer.org/wp-content/uploads/gravity_forms/14-9c9fca815a9748a9d553b5c66056473a/2015/09/264x264WebLogo.png', 5, '2020-05-01', 'https://www.pugryans.com/tiki-bar');
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`, `web_url`) VALUES (4, 'The Artisan Center', 'Arts & Crafts, Gift Shop Accessories', '3033331201', 1, 'https://cherrycreeknorth.com/_files/images/artisan-center-2.jpg', 6, '2020-05-02', 'http://www.artisancenterdenver.com/');
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`, `web_url`) VALUES (5, 'Urban Putt', 'Mini Golf', '7203603020', 1, 'https://cdn.vox-cdn.com/thumbor/CQ-BvsWSFP7oFfh8alnpvFCeT30=/0x0:6720x4480/1200x800/filters:focal(2823x1703:3897x2777)/cdn.vox-cdn.com/uploads/chorus_image/image/65218017/UP.0.jpg', 7, '2020-05-02', 'https://www.urbanputt.com/');
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`, `web_url`) VALUES (6, 'Stapleton All Sports', 'Kids Activities, Summer Camps', '7209856642', 1, 'http://stapletonallsports.com/images/slide/43.jpg', 8, '2020-05-02', 'http://stapletonallsports.com/');
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`, `web_url`) VALUES (7, 'Punch Bowl Social Denver', 'American , Gastropubs', '3037652695', 1, 'https://www.restaurant-hospitality.com/sites/restaurant-hospitality.com/files/punch-bowl-social-exterior.jpg', 8, '2020-05-02', 'https://punchbowlsocial.com/');
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`, `web_url`) VALUES (8, 'Icelandic Fitness', 'Trainers,Gyms,Sports Club', '3036418149', 1, 'http://www.icelandicfitness.com/wp-content/uploads/2014/04/icelanidcgroup.jpeg', 7, '2020-05-02', 'http://www.icelandicfitness.com/');
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`, `web_url`) VALUES (9, 'Hong Kong Cafe', 'Chinese, Cafes', '3036966688', 1, 'https://s3-media3.fl.yelpcdn.com/bphoto/kX4Remmst9c3CXJDBCPDTw/o.jpg', 9, '2020-05-02', 'https://www.denverhongkongcafe.com/');
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`, `web_url`) VALUES (10, 'Larimer Square', 'Shopping Centers', '3036858120', 1, 'https://cdn.vox-cdn.com/thumbor/hGePka1ecEthWmRHxSJ5dA0Q2ZE=/0x0:800x534/1200x900/filters:focal(336x203:464x331)/cdn.vox-cdn.com/uploads/chorus_image/image/63718306/about_larimer_walkway.0.jpg', 10, '2020-05-03', 'https://www.larimersquare.com/');
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`, `web_url`) VALUES (11, 'Big 5 Sporting Goods', 'Sporting Goods', '3037562212', 1, 'https://cdn.nevadaappeal.com/wp-content/uploads/sites/2/2016/10/Big5-LVN-062714.jpg', 10, '2020-05-03', 'https://www.big5sportinggoods.com/store/');
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`, `web_url`) VALUES (12, 'Colorado Sports Hall of Fame', 'Stadiums & Arenas', '7202583888', 1, 'https://media-cdn.tripadvisor.com/media/photo-s/0e/d8/c1/af/hall-of-fame-logo.jpg', 10, '2020-05-03', 'https://www.coloradosports.org/');
INSERT INTO `business` (`id`, `name`, `description`, `phone`, `active`, `image_url`, `manager_id`, `create_date`, `web_url`) VALUES (13, '16th Street Mall', 'Shopping Centers', '3035346161', 1, 'https://1063cowboycountry.com/files/2016/11/Facebook-16th-Street-Mall.jpg', 3, '2020-05-03', 'https://www.the16thstreetmall.com/');

COMMIT;


-- -----------------------------------------------------
-- Data for table `business_preference`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (1, 1);
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (2, 2);
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (3, 3);
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (4, 4);
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (5, 5);
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (6, 6);
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (7, 7);
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (8, 8);
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (9, 9);
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (10, 10);
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (11, 11);
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (12, 12);
INSERT INTO `business_preference` (`business_id`, `preference_id`) VALUES (13, 13);

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (1, '585 S Pecos St', NULL, 'Denver', 'CO', '80223', 'USA', 1);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (2, '8130 S University Blvd', 'Suite 110', 'Centennial', 'CO', '80112', 'USA', 2);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (3, ' 104 Village Pl', NULL, 'Dillon', 'CO', '80435', 'USA', 3);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (4, '2757 E 3rd Ave', NULL, 'Denver', 'CO', '80206', 'USA', 4);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (5, '1201 18th St', NULL, 'Denver', 'CO', '80202', 'USA', 5);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (6, '2823 Havana St', NULL, 'Denver', 'CO', '80238', 'USA', 6);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (7, '65 Broadway', NULL, 'Denver', 'CO', '80203', 'USA', 7);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (8, '600 S Holly St', 'Unit 104', 'Denver', 'CO', '80246', 'USA', 8);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (9, '10890 E Dartmouth Ave', NULL, 'Denver', 'CO', '80014', 'USA', 9);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (10, '1430 Larimer St', NULL, 'Denver', 'CO', '80202', 'USA', 10);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (11, '1430 S Colorado Blvd', NULL, 'Denver', 'CO', '80222', 'USA', 11);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (12, '1701 Mile High Stadium Cir', 'Se 500', 'Denver', 'CO', '80204', 'USA', 12);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `postal_code`, `country`, `business_id`) VALUES (13, '1515 Arapahoe St', NULL, 'Denver', 'CO', '80202', 'USA', 13);

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
INSERT INTO `article` (`id`, `user_id`, `business_id`, `title`, `content`, `create_at`, `image_url`, `active`) VALUES (1, 1, 1, 'Hello World', 'Trial and Error', '2020-04-27', 'https://www.digitalmeetsculture.net/wp-content/uploads/2015/04/article.jpg', 1);
INSERT INTO `article` (`id`, `user_id`, `business_id`, `title`, `content`, `create_at`, `image_url`, `active`) VALUES (2, 2, 2, 'Some Small Businesses That Got Aid Fear the Rules Too Much to Spend It', 'When a $192,000 loan from the federal government’s small-business aid program arrived in his bank account last month, George Evageliou, the founder of a custom woodworking company, felt like one of the lucky ones.\n\nUnder the program’s rules, Mr. Evageliou has eight weeks from the day he received the cash to spend it. But nearly three weeks after the clock started on April 14, he hasn’t used a penny.', '2020-05-01', 'https://cdn.the-scientist.com/assets/articleNo/30636/iImg/2186/b531a5bf-c7af-43de-9fc1-3560813f9d49-online.jpg', 1);
INSERT INTO `article` (`id`, `user_id`, `business_id`, `title`, `content`, `create_at`, `image_url`, `active`) VALUES (3, 1, 1, 'There’s a more accurate way to compare coronavirus deaths to the flu', 'Months into the coronavirus pandemic, some politicians and pundits continue to promote ham-handed comparisons between covid-19 and the seasonal flu to score political points.\n\nThough there are many ways to debunk this fundamentally flawed comparison, one of the clearest was put forth this week by Jeremy Samuel Faust, an emergency room physician at Brigham and Women’s Hospital at Harvard Medical School', '2020-05-01', 'https://images.wisegeek.com/pile-of-newspapers.jpg', 1);
INSERT INTO `article` (`id`, `user_id`, `business_id`, `title`, `content`, `create_at`, `image_url`, `active`) VALUES (4, 2, 1, '\'Tesla Stock Price Is Too High,\' Elon Musk Tweeted. Then Came the Stock Plunge\n\'Tesla Stock Price Is Too High,\' Elon Musk Tweeted. Then Came the Stock Plunge\n', 'Tesla shares plunged as much as 13% as of noon Friday in New York trading. The stock is still up about 65% for the year, an advance that’s put him in position to meet the final performance threshold needed to be awarded stock options that would yield him a windfall of about $730 million.\n\nMusk, 48, didn’t immediately respond to an email seeking further comment. He joked about Tesla’s stock being “so high” in December, and has said in the past the company has a higher market capitalization than it deserves.\n\nMusk settled a securities-fraud lawsuit the U.S. Securities and Exchange Commission brought in 2018 over his tweets claiming that he had the funding to take Tesla private. The two sides carried on their dispute into last year, when they amended an earlier agreement to add specific topics that Musk isn’t supposed to tweet about without advance approval from a Tesla lawyer.\n\nThe topics subject to the agreement include the company’s financial condition, potential mergers or acquisitions, production and sales numbers, new or proposed business lines, projections and forecasts that haven’t been previously published, and Musk’s purchase or sale of Tesla securities.', '2020-05-01', 'https://specials-images.forbesimg.com/imageserve/5db8f9cbb2a63d000793abc2/960x0.jpg?fit=scale', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `article_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `supportlocaldb`;
INSERT INTO `article_comment` (`id`, `article_id`, `content`, `create_date`, `inreply_to_id`, `user_id`) VALUES (1, 1, 'Great Article', '2020-04-27', NULL, 1);
INSERT INTO `article_comment` (`id`, `article_id`, `content`, `create_date`, `inreply_to_id`, `user_id`) VALUES (2, 1, 'Loved it', '2020-04-29', 1, 2);
INSERT INTO `article_comment` (`id`, `article_id`, `content`, `create_date`, `inreply_to_id`, `user_id`) VALUES (3, 2, 'Love your work', '2020-05-01', NULL, 3);
INSERT INTO `article_comment` (`id`, `article_id`, `content`, `create_date`, `inreply_to_id`, `user_id`) VALUES (4, 2, 'Thank you for the comment', '2020-05-01', 2, 2);

COMMIT;
