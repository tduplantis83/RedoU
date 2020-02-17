-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema redou
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `redou` ;

-- -----------------------------------------------------
-- Schema redou
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `redou` DEFAULT CHARACTER SET utf8 ;
USE `redou` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` TEXT NOT NULL,
  `firstName` VARCHAR(250) NOT NULL,
  `lastName` VARCHAR(250) NOT NULL,
  `birthday` DATE NOT NULL,
  `sex` VARCHAR(1) NOT NULL,
  `email` VARCHAR(250) NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `role` VARCHAR(20) NOT NULL,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `body_measurement_metric`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `body_measurement_metric` ;

CREATE TABLE IF NOT EXISTS `body_measurement_metric` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `dateMeasured` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  `heightMM` INT NOT NULL,
  `weightKg` DECIMAL(5,2) NOT NULL,
  `waistMM` INT NOT NULL,
  `neckMM` INT NULL,
  `shouldersMM` INT NULL,
  `chestMM` INT NULL,
  `bicepMM` INT NULL,
  `hipsMM` INT NULL,
  `thighMM` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_id_body_measurement_metric`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `goal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `goal` ;

CREATE TABLE IF NOT EXISTS `goal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `goalName` VARCHAR(100) NOT NULL,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_current_goal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_current_goal` ;

CREATE TABLE IF NOT EXISTS `user_current_goal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `goal_id` INT NOT NULL,
  `enabled` TINYINT NULL DEFAULT 0,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_current_goal_idx` (`user_id` ASC),
  INDEX `fk_goal_id_current_goal_idx` (`goal_id` ASC),
  CONSTRAINT `fk_user_id_current_goal`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_goal_id_current_goal`
    FOREIGN KEY (`goal_id`)
    REFERENCES `goal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meal_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meal_type` ;

CREATE TABLE IF NOT EXISTS `meal_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mealTypeName` VARCHAR(100) NOT NULL,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daily_caloric_intake`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `daily_caloric_intake` ;

CREATE TABLE IF NOT EXISTS `daily_caloric_intake` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `mealType_id` INT NOT NULL,
  `caloriesThisMeal` INT NOT NULL,
  `mealDescription` VARCHAR(250) NULL,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_daily_caloric_intake_idx` (`user_id` ASC),
  INDEX `fk_mealType_id_daily_caloric_intake_idx` (`mealType_id` ASC),
  CONSTRAINT `fk_user_id_daily_caloric_intake`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mealType_id_daily_caloric_intake`
    FOREIGN KEY (`mealType_id`)
    REFERENCES `meal_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daily_exercise_caloric_deficit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `daily_exercise_caloric_deficit` ;

CREATE TABLE IF NOT EXISTS `daily_exercise_caloric_deficit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `totalCaloriesBurned` INT NOT NULL,
  `activityDescription` VARCHAR(250) NULL,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_daily_exercise_caloric_deficit_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_id_daily_exercise_caloric_deficit`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `image` ;

CREATE TABLE IF NOT EXISTS `image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `imageUrl` TEXT NOT NULL,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_image_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_id_image`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `avatar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `avatar` ;

CREATE TABLE IF NOT EXISTS `avatar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `avatarGroup` INT NOT NULL,
  `sex` VARCHAR(1) NOT NULL,
  `bodyType` VARCHAR(100) NOT NULL,
  `avatarUrl` TEXT NOT NULL,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post_topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post_topic` ;

CREATE TABLE IF NOT EXISTS `post_topic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `topicName` VARCHAR(100) NOT NULL,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `postTopic_id` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `content` TEXT NOT NULL,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_message_board_idx` (`user_id` ASC),
  INDEX `fk_postTopic_id_post_idx` (`postTopic_id` ASC),
  CONSTRAINT `fk_user_id_message_board`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_postTopic_id_post`
    FOREIGN KEY (`postTopic_id`)
    REFERENCES `post_topic` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post_reply`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post_reply` ;

CREATE TABLE IF NOT EXISTS `post_reply` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reply_user_id` INT NOT NULL,
  `originalPost_id` INT NOT NULL,
  `reply_to_reply_id` INT NULL,
  `replyContent` TEXT NOT NULL,
  `unread` TINYINT NOT NULL DEFAULT 1,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reply_user_id_post_reply_idx` (`reply_user_id` ASC),
  INDEX `fk_originalPost_id_post_reply_idx` (`originalPost_id` ASC),
  INDEX `fk_reply_to_reply_post_reply_idx` (`reply_to_reply_id` ASC),
  CONSTRAINT `fk_reply_user_id_post_reply`
    FOREIGN KEY (`reply_user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_originalPost_id_post_reply`
    FOREIGN KEY (`originalPost_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reply_to_reply_post_reply`
    FOREIGN KEY (`reply_to_reply_id`)
    REFERENCES `post_reply` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_avatar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_avatar` ;

CREATE TABLE IF NOT EXISTS `user_avatar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `avatar_id` INT NOT NULL,
  `current` TINYINT NULL DEFAULT 0,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_user_current_avatar_idx` (`user_id` ASC),
  INDEX `fk_avatar_id_user_current_avatar_idx` (`avatar_id` ASC),
  CONSTRAINT `fk_user_id_user_current_avatar`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_avatar_id_user_current_avatar`
    FOREIGN KEY (`avatar_id`)
    REFERENCES `avatar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS redouadmin@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'redouadmin'@'localhost' IDENTIFIED BY 'redou';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'redouadmin'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `user` (`id`, `username`, `password`, `firstName`, `lastName`, `birthday`, `sex`, `email`, `enabled`, `role`, `dateCreated`, `dateUpdated`) VALUES (1, 'admin', '$2a$10$twiPO/2B4aV26SaHefJUbu8jlUEF9JvxDL1qqN9Bh4EK1yVgnHVYS', 'RedoU', 'Administrator', '1983-01-01', 'M', 'admin@redou.com', 1, 'admin', '2020-01-16', '2020-01-22');
INSERT INTO `user` (`id`, `username`, `password`, `firstName`, `lastName`, `birthday`, `sex`, `email`, `enabled`, `role`, `dateCreated`, `dateUpdated`) VALUES (2, 'travisd', '$2a$10$3PsHWAFTOzhDDqGED0ypPepRZbW7ikKgeYwsY3wqIbWcnjgSarZ0K', 'Travis', 'Duplantis', '1983-07-01', 'M', 'tduplantis83@gmail.com', 1, 'user', '2020-01-16', '2020-01-22');
INSERT INTO `user` (`id`, `username`, `password`, `firstName`, `lastName`, `birthday`, `sex`, `email`, `enabled`, `role`, `dateCreated`, `dateUpdated`) VALUES (3, 'emilyd', '$2a$10$3PsHWAFTOzhDDqGED0ypPepRZbW7ikKgeYwsY3wqIbWcnjgSarZ0K', 'Emily', 'Duplantis', '1985-03-27', 'F', 'eduplantis85@gmail.com', 1, 'user', '2020-01-16', '2020-01-22');

COMMIT;


-- -----------------------------------------------------
-- Data for table `body_measurement_metric`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `body_measurement_metric` (`id`, `user_id`, `dateMeasured`, `dateUpdated`, `heightMM`, `weightKg`, `waistMM`, `neckMM`, `shouldersMM`, `chestMM`, `bicepMM`, `hipsMM`, `thighMM`) VALUES (1, 2, '2020-01-15', '2020-01-15', 1854, 94.26, 1090, 400, 1220, 1075, 304, 1100, 630);
INSERT INTO `body_measurement_metric` (`id`, `user_id`, `dateMeasured`, `dateUpdated`, `heightMM`, `weightKg`, `waistMM`, `neckMM`, `shouldersMM`, `chestMM`, `bicepMM`, `hipsMM`, `thighMM`) VALUES (2, 3, '2020-01-18', '2020-01-18', 1549, 74.66, 1140, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `body_measurement_metric` (`id`, `user_id`, `dateMeasured`, `dateUpdated`, `heightMM`, `weightKg`, `waistMM`, `neckMM`, `shouldersMM`, `chestMM`, `bicepMM`, `hipsMM`, `thighMM`) VALUES (3, 2, '2020-01-20', '2020-01-20', 1854, 87.63, 1035, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `body_measurement_metric` (`id`, `user_id`, `dateMeasured`, `dateUpdated`, `heightMM`, `weightKg`, `waistMM`, `neckMM`, `shouldersMM`, `chestMM`, `bicepMM`, `hipsMM`, `thighMM`) VALUES (4, 2, '2020-01-29', '2020-01-29', 1854, 85.91, 1030, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `body_measurement_metric` (`id`, `user_id`, `dateMeasured`, `dateUpdated`, `heightMM`, `weightKg`, `waistMM`, `neckMM`, `shouldersMM`, `chestMM`, `bicepMM`, `hipsMM`, `thighMM`) VALUES (5, 2, '2020-02-05', '2020-02-05', 1854, 85.09, 1025, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `body_measurement_metric` (`id`, `user_id`, `dateMeasured`, `dateUpdated`, `heightMM`, `weightKg`, `waistMM`, `neckMM`, `shouldersMM`, `chestMM`, `bicepMM`, `hipsMM`, `thighMM`) VALUES (6, 2, '2020-02-12', '2020-02-12', 1854, 84.19, 1010, NULL, NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `goal`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `goal` (`id`, `goalName`, `dateCreated`, `dateUpdated`) VALUES (1, 'Weight Loss', '2020-01-16', '2020-01-16');
INSERT INTO `goal` (`id`, `goalName`, `dateCreated`, `dateUpdated`) VALUES (2, 'Muscle Building', '2020-01-16', '2020-01-16');
INSERT INTO `goal` (`id`, `goalName`, `dateCreated`, `dateUpdated`) VALUES (3, 'General Fitness Maintanence', '2020-01-16', '2020-01-16');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_current_goal`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `user_current_goal` (`id`, `user_id`, `goal_id`, `enabled`, `dateCreated`, `dateUpdated`) VALUES (1, 2, 1, 1, '2020-01-16', '2020-01-16');
INSERT INTO `user_current_goal` (`id`, `user_id`, `goal_id`, `enabled`, `dateCreated`, `dateUpdated`) VALUES (2, 3, 1, 1, '2020-01-16', '2020-01-16');

COMMIT;


-- -----------------------------------------------------
-- Data for table `meal_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `meal_type` (`id`, `mealTypeName`, `dateCreated`, `dateUpdated`) VALUES (1, 'Breakfast', '2020-01-18', '2020-01-18');
INSERT INTO `meal_type` (`id`, `mealTypeName`, `dateCreated`, `dateUpdated`) VALUES (2, 'Lunch', '2020-01-18', '2020-01-18');
INSERT INTO `meal_type` (`id`, `mealTypeName`, `dateCreated`, `dateUpdated`) VALUES (3, 'Dinner', '2020-01-18', '2020-01-18');
INSERT INTO `meal_type` (`id`, `mealTypeName`, `dateCreated`, `dateUpdated`) VALUES (4, 'Snack', '2020-01-18', '2020-01-18');
INSERT INTO `meal_type` (`id`, `mealTypeName`, `dateCreated`, `dateUpdated`) VALUES (5, 'Dessert', '2020-02-16', '2020-02-16');

COMMIT;


-- -----------------------------------------------------
-- Data for table `daily_caloric_intake`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (1, 2, 3, 500, 'Small bowl of tatertot breakfast casserole', '2020-01-16', '2020-01-18');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (2, 2, 4, 100, 'Blueberries and baked apple chips', '2020-01-17', '2020-01-17');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (3, 2, 3, 500, 'Small bowl of tatertot breakfast casserole', '2020-01-17', '2020-01-17');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (4, 2, 3, 600, 'Corn Dogs', '2020-01-19', '2020-01-20');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (5, 2, 4, 200, 'Carrots with hummus', '2020-01-20', '2020-01-20');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (6, 2, 3, 500, 'Taco Salad', '2020-01-20', '2020-01-20');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (7, 2, 4, 200, 'Hippeas', '2020-01-20', '2020-01-20');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (8, 2, 3, 1000, 'Quesadilla', '2020-01-21', '2020-01-21');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (9, 2, 3, 700, 'Baked Potato and Chili', '2020-01-22', '2020-01-22');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (10, 2, 3, 700, 'Baked Potato and Chili', '2020-01-23', '2020-01-23');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (11, 2, 4, 200, 'PB&J', '2020-01-23', '2020-01-23');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (12, 2, 3, 300, 'Vegan Cauliflower Mac n Cheese', '2020-01-24', '2020-01-26');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (13, 2, 3, 200, 'Baked Potato', '2020-01-24', '2020-01-26');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (14, 2, 3, 900, 'Chipotle Vegan Burrito Bowl - Sofritas', '2020-01-25', '2020-01-26');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (15, 2, 4, 130, 'Nutrigrain Bar - Strawberry', '2020-01-25', '2020-01-26');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (16, 2, 3, 700, 'Spaghetti with Boca Meat Crumbles - 1 bowl (about 2 cups)', '2020-01-26', '2020-01-28');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (17, 2, 3, 700, 'Veggie Fried Rice - 1 bowl (about 2 cups)', '2020-01-27', '2020-01-28');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (18, 2, 4, 200, 'PB&J', '2020-01-27', '2020-01-28');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (19, 2, 3, 700, 'Spaghetti with Boca Meat Crumbles - 1 bowl (about 2 cups)', '2020-01-28', '2020-01-29');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (20, 2, 3, 200, 'Salad', '2020-01-28', '2020-01-29');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (21, 2, 2, 325, 'Buffalo Vegan Cauliflower Mac n Cheese', '2020-01-29', '2020-01-29');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (22, 2, 4, 125, 'Banana', '2020-01-29', '2020-01-29');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (23, 2, 3, 700, 'Breakfast Dinner - Hashbrowns & Eggs - 2 bowls', '2020-01-29', '2020-01-29');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (24, 2, 4, 150, 'Apple', '2020-01-30', '2020-02-01');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (25, 2, 3, 500, 'Breakfast Dinner - Hashbrowns and Eggs (small bowl)', '2020-01-30', '2020-02-01');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (26, 2, 2, 300, 'Veggie Fried Rice (small bowl)', '2020-01-31', '2020-02-01');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (27, 2, 3, 500, 'Burger', '2020-01-31', '2020-02-01');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (28, 2, 3, 200, 'Salad', '2020-01-31', '2020-02-01');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (29, 2, 4, 300, 'PBJ', '2020-01-31', '2020-02-01');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (30, 2, 4, 300, 'Fig Bar', '2020-01-31', '2020-02-01');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (31, 2, 4, 400, 'Small snacks throughout the day', '2020-02-02', '2020-02-03');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (32, 2, 3, 1200, 'Noodles & Co Large Mac n Cheese', '2020-02-01', '2020-02-03');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (33, 2, 4, 500, 'Noodles & Co Snickerdoodle Cookie', '2020-02-01', '2020-02-03');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (34, 2, 3, 500, 'Burger', '2020-02-03', '2020-02-04');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (35, 2, 3, 300, 'Salad (2 bowls)', '2020-02-03', '2020-02-04');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (36, 2, 3, 200, 'Salad', '2020-02-04', '2020-02-05');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (37, 2, 3, 700, 'Tofu Buffalo Bites (a lot)', '2020-02-04', '2020-02-05');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (38, 2, 3, 800, 'Fajita Bowl', '2020-02-05', '2020-02-07');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (39, 2, 2, 300, 'Tofu Buffalo Bites (small amt)', '2020-02-06', '2020-02-07');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (40, 2, 3, 800, 'Fajita Bowl', '2020-02-06', '2020-02-07');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (41, 2, 3, 0, 'Fasting', '2020-02-07', '2020-02-09');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (42, 2, 2, 2000, 'Hacienda Colorado - Enchiladas, Chips', '2020-02-08', '2020-02-09');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (43, 2, 4, 800, 'Cinnamon Toast Crunch - HUGE Bowl', '2020-02-08', '2020-02-09');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (44, 2, 3, 800, 'Vegetable Soup (2 big bowls)', '2020-02-09', '2020-02-10');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (45, 2, 2, 340, 'Siracha & Sesame Veggie Bowl', '2020-02-10', '2020-02-10');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (46, 2, 3, 1000, 'Hacienda Colorado - Fajitas', '2020-02-10', '2020-02-10');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (47, 2, 3, 0, 'Fasting', '2020-02-11', '2020-02-11');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (48, 2, 3, 750, '4 Tacos with Rice & Beans', '2020-02-12', '2020-02-12');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (49, 2, 2, 200, 'Thai Veggie Bowl', '2020-02-13', '2020-02-14');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (50, 2, 3, 3500, 'Maggianos', '2020-02-13', '2020-02-14');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (51, 2, 3, 600, 'Fajita Bowl - small', '2020-02-14', '2020-02-16');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (52, 2, 3, 2000, 'Burger King - Impossible Whopper, Fries, Onion Rings', '2020-02-15', '2020-02-16');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (53, 2, 1, 220, 'Raisin Bread with Butter - 2 slices', '2020-02-16', '2020-02-16');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (54, 2, 2, 300, 'Little Ceasar\'s Cheese Pizza (2 slices)', '2020-02-16', '2020-02-16');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (55, 2, 3, 500, 'Little Ceasar\'s Cheese Pizza (3 slices)', '2020-02-16', '2020-02-16');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (56, 2, 5, 1000, 'Stuffed Cookies - 4', '2020-02-16', '2020-02-16');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (57, 2, 3, 0, 'Fasting', '2020-02-17', '2020-02-17');
INSERT INTO `daily_caloric_intake` (`id`, `user_id`, `mealType_id`, `caloriesThisMeal`, `mealDescription`, `dateCreated`, `dateUpdated`) VALUES (58, 2, 3, 0, 'Fasting', '2020-02-18', '2020-02-17');

COMMIT;


-- -----------------------------------------------------
-- Data for table `daily_exercise_caloric_deficit`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (1, 2, 1928, 'Basal Metabolic Rate', '2020-01-16', '2020-01-18');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (2, 2, 1928, 'Basal Metabolic Rate', '2020-01-17', '2020-01-17');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (3, 2, 1928, 'Basal Metabolic Rate', '2020-01-18', '2020-01-18');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (4, 2, 1888, 'Basal Metabolic Rate', '2020-01-19', '2020-01-20');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (6, 2, 1888, 'Basal Metabolic Rate', '2020-01-20', '2020-01-20');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (7, 2, 1888, 'Basal Metabolic Rate', '2020-01-21', '2020-01-21');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (8, 2, 1888, 'Basal Metabolic Rate', '2020-01-22', '2020-01-22');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (9, 2, 1888, 'Basal Metabolic Rate', '2020-01-23', '2020-01-23');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (10, 2, 1888, 'Basal Metabolic Rate', '2020-01-24', '2020-01-24');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (11, 2, 1888, 'Basal Metabolic Rate', '2020-01-25', '2020-01-26');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (12, 2, 1888, 'Basal Metabolic Rate', '2020-01-26', '2020-01-26');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (13, 2, 1888, 'Basal Metabolic Rate', '2020-01-27', '2020-01-28');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (14, 2, 1888, 'Basal Metabolic Rate', '2020-01-28', '2020-01-28');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (15, 2, 1888, 'Basal Metabolic Rate', '2020-01-29', '2020-01-29');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (16, 2, 1888, 'Basal Metabolic Rate', '2020-01-30', '2020-01-30');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (17, 2, 1888, 'Basal Metabolic Rate', '2020-01-31', '2020-01-31');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (18, 2, 1888, 'Basal Metabolic Rate', '2020-02-01', '2020-02-01');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (19, 2, 1888, 'Basal Metabolic Rate', '2020-02-02', '2020-02-03');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (21, 2, 1888, 'Basal Metabolic Rate', '2020-02-03', '2020-02-04');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (22, 2, 1888, 'Basal Metabolic Rate', '2020-02-04', '2020-02-04');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (23, 2, 1850, 'Basal Metabolic Rate', '2020-02-05', '2020-02-05');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (24, 2, 1850, 'Basal Metabolic Rate', '2020-02-06', '2020-02-07');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (25, 2, 1850, 'Basal Metabolic Rate', '2020-02-07', '2020-02-07');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (26, 2, 1850, 'Basal Metabolic Rate', '2020-02-08', '2020-02-09');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (27, 2, 1850, 'Basal Metabolic Rate', '2020-02-09', '2020-02-09');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (28, 2, 1850, 'Basal Metabolic Rate', '2020-02-10', '2020-02-10');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (29, 2, 1850, 'Basal Metabolic Rate', '2020-02-11', '2020-02-11');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (30, 2, 1842, 'Basal Metabolic Rate', '2020-02-12', '2020-02-12');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (31, 2, 1842, 'Basal Metabolic Rate', '2020-02-13', '2020-02-12');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (32, 2, 1842, 'Basal Metabolic Rate', '2020-02-14', '2020-02-14');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (33, 2, 1842, 'Basal Metabolic Rate', '2020-02-15', '2020-02-16');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (34, 2, 1842, 'Basal Metabolic Rate', '2020-02-16', '2020-02-16');
INSERT INTO `daily_exercise_caloric_deficit` (`id`, `user_id`, `totalCaloriesBurned`, `activityDescription`, `dateCreated`, `dateUpdated`) VALUES (35, 2, 1842, 'Basal Metabolic Rate', '2020-02-17', '2020-02-17');

COMMIT;


-- -----------------------------------------------------
-- Data for table `image`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `image` (`id`, `user_id`, `imageUrl`, `dateCreated`, `dateUpdated`) VALUES (1, 2, 'https://i.imgur.com/puVjtA9.jpg', '2020-01-15', '2020-01-15');
INSERT INTO `image` (`id`, `user_id`, `imageUrl`, `dateCreated`, `dateUpdated`) VALUES (2, 2, 'https://i.imgur.com/zSACF2B.jpg', '2020-01-15', '2020-01-15');
INSERT INTO `image` (`id`, `user_id`, `imageUrl`, `dateCreated`, `dateUpdated`) VALUES (3, 2, 'https://i.imgur.com/5tSrGNG.jpg', '2020-01-22', '2020-01-22');
INSERT INTO `image` (`id`, `user_id`, `imageUrl`, `dateCreated`, `dateUpdated`) VALUES (4, 2, 'https://i.imgur.com/V9rUJEe.jpg', '2020-01-22', '2020-01-22');
INSERT INTO `image` (`id`, `user_id`, `imageUrl`, `dateCreated`, `dateUpdated`) VALUES (5, 2, 'https://i.imgur.com/k4mJmLQ.jpg', '2020-01-29', '2020-01-29');
INSERT INTO `image` (`id`, `user_id`, `imageUrl`, `dateCreated`, `dateUpdated`) VALUES (6, 2, 'https://i.imgur.com/mDquJgP.jpg', '2020-01-29', '2020-01-29');
INSERT INTO `image` (`id`, `user_id`, `imageUrl`, `dateCreated`, `dateUpdated`) VALUES (7, 2, 'https://i.imgur.com/uK3VMWA.jpg', '2020-02-05', '2020-02-07');
INSERT INTO `image` (`id`, `user_id`, `imageUrl`, `dateCreated`, `dateUpdated`) VALUES (8, 2, 'https://i.imgur.com/BHxZJ6P.jpg', '2020-02-05', '2020-02-07');
INSERT INTO `image` (`id`, `user_id`, `imageUrl`, `dateCreated`, `dateUpdated`) VALUES (9, 2, 'https://i.imgur.com/n1qmq6J.jpg', '2020-02-12', '2020-02-12');
INSERT INTO `image` (`id`, `user_id`, `imageUrl`, `dateCreated`, `dateUpdated`) VALUES (10, 2, 'https://i.imgur.com/XUgd4tZ.jpg', '2020-02-12', '2020-02-12');

COMMIT;


-- -----------------------------------------------------
-- Data for table `avatar`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (1, 1, 'M', 'Thin', 'https://i.imgur.com/uJaRmvQ.jpg', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (2, 1, 'M', 'Average', 'https://i.imgur.com/MeZUyU7.jpg', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (3, 1, 'M', 'Fat', 'https://i.imgur.com/HM7aIAS.jpg', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (4, 1, 'M', 'Athletic', 'https://i.imgur.com/mLpfYbC.jpg', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (5, 1, 'M', 'Muscular', 'https://i.imgur.com/U74DJg6.jpg', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (6, 2, 'F', 'Thin', 'https://i.imgur.com/6XHNaUq.jpg', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (7, 2, 'F', 'Average', 'https://i.imgur.com/uXyVqic.jpg', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (8, 2, 'F', 'Fat', 'https://i.imgur.com/P0LEumI.jpg', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (9, 2, 'F', 'Athletic', 'https://i.imgur.com/hwcAc1R.jpg', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (10, 2, 'F', 'Muscular', 'https://i.imgur.com/e9q4Cgh.jpg', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (11, 3, 'M', 'Thin', 'https://i.imgur.com/924NgVA.jpg', '2020-01-22', '2020-01-22');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (12, 3, 'M', 'Average', 'https://i.imgur.com/RVbXOPm.jpg', '2020-01-22', '2020-01-22');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (13, 3, 'M', 'Fat', 'https://i.imgur.com/SB7Is6U.jpg', '2020-01-22', '2020-01-22');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (14, 3, 'M', 'Athletic', 'https://i.imgur.com/eWJ31IE.jpg', '2020-01-22', '2020-01-22');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (15, 3, 'M', 'Muscular', 'https://i.imgur.com/3dvjEp5.jpg', '2020-01-22', '2020-01-22');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (16, 4, 'M', 'Thin', 'https://i.imgur.com/88Xh2ys.jpg', '2020-01-22', '2020-01-22');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (17, 4, 'M', 'Average', 'https://i.imgur.com/KC3R5bH.jpg', '2020-01-22', '2020-01-22');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (18, 4, 'M', 'Fat', 'https://i.imgur.com/FzEJwcu.jpg', '2020-01-22', '2020-01-22');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (19, 4, 'M', 'Athletic', 'https://i.imgur.com/R8mmba1.jpg', '2020-01-22', '2020-01-22');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (20, 4, 'M', 'Muscular', 'https://i.imgur.com/9FnnFPu.jpg', '2020-01-22', '2020-01-22');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (21, 5, 'F', 'Thin', 'https://i.imgur.com/S0PFg0i.jpg', '2020-01-23', '2020-01-23');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (22, 5, 'F', 'Average', 'https://i.imgur.com/T8TYnYW.jpg', '2020-01-23', '2020-01-23');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (23, 5, 'F', 'Fat', 'https://i.imgur.com/rnTFgft.jpg', '2020-01-23', '2020-01-23');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (24, 5, 'F', 'Athletic', 'https://i.imgur.com/JACaIOG.jpg', '2020-01-23', '2020-01-23');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (25, 5, 'F', 'Muscular', 'https://i.imgur.com/d5TqMy2.jpg', '2020-01-23', '2020-01-23');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (26, 6, 'F', 'Thin', 'https://i.imgur.com/mEGkmLT.jpg', '2020-01-23', '2020-01-23');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (27, 6, 'F', 'Average', 'https://i.imgur.com/xYGCFyF.jpg', '2020-01-23', '2020-01-23');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (28, 6, 'F', 'Fat', 'https://i.imgur.com/H1L5R87.jpg', '2020-01-23', '2020-01-23');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (29, 6, 'F', 'Athletic', 'https://i.imgur.com/hju1Kc9.jpg', '2020-01-23', '2020-01-23');
INSERT INTO `avatar` (`id`, `avatarGroup`, `sex`, `bodyType`, `avatarUrl`, `dateCreated`, `dateUpdated`) VALUES (30, 6, 'F', 'Muscular', 'https://i.imgur.com/gPqVEqo.jpg', '2020-01-23', '2020-01-23');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post_topic`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `post_topic` (`id`, `topicName`, `dateCreated`, `dateUpdated`) VALUES (1, 'Diet', '2020-01-18', '2020-01-18');
INSERT INTO `post_topic` (`id`, `topicName`, `dateCreated`, `dateUpdated`) VALUES (2, 'Exercise', '2020-01-18', '2020-01-18');
INSERT INTO `post_topic` (`id`, `topicName`, `dateCreated`, `dateUpdated`) VALUES (3, 'Surgery', '2020-01-18', '2020-01-18');
INSERT INTO `post_topic` (`id`, `topicName`, `dateCreated`, `dateUpdated`) VALUES (4, 'Recipes', '2020-01-18', '2020-01-18');
INSERT INTO `post_topic` (`id`, `topicName`, `dateCreated`, `dateUpdated`) VALUES (5, 'Medical Conditions', '2020-01-18', '2020-01-18');
INSERT INTO `post_topic` (`id`, `topicName`, `dateCreated`, `dateUpdated`) VALUES (6, 'Tips & Tricks', '2020-01-18', '2020-01-18');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `post` (`id`, `user_id`, `postTopic_id`, `title`, `content`, `dateCreated`, `dateUpdated`) VALUES (1, 3, 1, 'Intermittent Fasting', 'I\'m curious how intermittent fasting works. Can anyone give me some details about it and the different methods?', '2020-01-17', '2020-01-17');
INSERT INTO `post` (`id`, `user_id`, `postTopic_id`, `title`, `content`, `dateCreated`, `dateUpdated`) VALUES (2, 2, 1, '40 Hour Fast', 'I\'m trying a 40 hour fast for the first time. I figured its good to switch things up occassionally to help prevent plateaus. Anyone tried this with good results?', '2020-02-07', '2020-02-07');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post_reply`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `post_reply` (`id`, `reply_user_id`, `originalPost_id`, `reply_to_reply_id`, `replyContent`, `unread`, `dateCreated`, `dateUpdated`) VALUES (1, 2, 1, NULL, 'There are several different types of intermittent fasting including: 16 hours fasting with 8 hours eating, One Meal A Day (OMAD). The general idea is to get your blood sugar down and prevent insulin spikes. In reality you can modify it so that it works best for you, but you should go for 16 hour fasts at a minimum. A lot of new research shows that this is the best way for humans to lose weight. It\'s actually thought that this is how our evolutionary ancestors ate (they didn\'t eat 3 meals a day, but instead ate when they were able to get food.', 1, '2020-01-17', '2020-01-17');
INSERT INTO `post_reply` (`id`, `reply_user_id`, `originalPost_id`, `reply_to_reply_id`, `replyContent`, `unread`, `dateCreated`, `dateUpdated`) VALUES (2, 3, 1, 1, 'Which intermittent fasting method is the best for losing weight quickly, and why?', 1, '2020-01-23', '2020-01-23');
INSERT INTO `post_reply` (`id`, `reply_user_id`, `originalPost_id`, `reply_to_reply_id`, `replyContent`, `unread`, `dateCreated`, `dateUpdated`) VALUES (3, 2, 1, 2, 'It\'s really a personal choice - I\'d go with whicever is easiest for you to stick to. The best diet is the one you can do long-term; it\'s a marathon, not a race.', 1, '2020-01-24', '2020-01-24');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_avatar`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (1, 2, 1, 0, '2020-01-20', '2020-01-20');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (2, 2, 2, 0, '2020-01-20', '2020-01-20');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (3, 2, 3, 1, '2020-01-20', '2020-01-20');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (4, 2, 4, 0, '2020-01-20', '2020-01-20');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (5, 2, 5, 0, '2020-01-20', '2020-01-20');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (6, 3, 6, 0, '2020-01-20', '2020-01-20');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (7, 3, 7, 0, '2020-01-20', '2020-01-20');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (8, 3, 8, 1, '2020-01-20', '2020-01-20');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (9, 3, 9, 0, '2020-01-20', '2020-01-20');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (10, 3, 10, 0, '2020-01-20', '2020-01-20');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (11, 1, 11, 0, '2020-01-22', '2020-01-22');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (12, 1, 12, 0, '2020-01-22', '2020-01-22');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (13, 1, 13, 0, '2020-01-22', '2020-01-22');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (14, 1, 14, 1, '2020-01-22', '2020-01-22');
INSERT INTO `user_avatar` (`id`, `user_id`, `avatar_id`, `current`, `dateCreated`, `dateUpdated`) VALUES (15, 1, 15, 0, '2020-01-22', '2020-01-22');

COMMIT;

