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
  `enabled` TINYINT NOT NULL DEFAULT 1,
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
  `user_id` INT NOT NULL,
  `avatarUrl` TEXT NOT NULL,
  `bodyType` VARCHAR(100) NOT NULL,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_avatar_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_id_avatar`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  `replyContent` TEXT NOT NULL,
  `dateCreated` DATE NOT NULL,
  `dateUpdated` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reply_user_id_post_reply_idx` (`reply_user_id` ASC),
  INDEX `fk_originalPost_id_post_reply_idx` (`originalPost_id` ASC),
  CONSTRAINT `fk_reply_user_id_post_reply`
    FOREIGN KEY (`reply_user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_originalPost_id_post_reply`
    FOREIGN KEY (`originalPost_id`)
    REFERENCES `post` (`id`)
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
INSERT INTO `user` (`id`, `username`, `password`, `firstName`, `lastName`, `birthday`, `sex`, `email`, `enabled`, `role`, `dateCreated`, `dateUpdated`) VALUES (1, 'admin', 'YWRtaW4=', 'Web', 'Administrator', '1983-01-01', 'M', 'admin@redou.com', 1, 'admin', '2020-01-16', '2020-01-16');
INSERT INTO `user` (`id`, `username`, `password`, `firstName`, `lastName`, `birthday`, `sex`, `email`, `enabled`, `role`, `dateCreated`, `dateUpdated`) VALUES (2, 'travisd', 'dGVzdA==', 'Travis', 'Duplantis', '1983-07-01', 'M', 'tduplantis83@gmail.com', 1, 'user', '2020-01-16', '2020-01-16');
INSERT INTO `user` (`id`, `username`, `password`, `firstName`, `lastName`, `birthday`, `sex`, `email`, `enabled`, `role`, `dateCreated`, `dateUpdated`) VALUES (3, 'emilyd', 'dGVzdA==', 'Emily', 'Duplantis', '1985-03-27', 'F', 'eduplantis85@gmail.com', 1, 'user', '2020-01-16', '2020-01-16');

COMMIT;


-- -----------------------------------------------------
-- Data for table `body_measurement_metric`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `body_measurement_metric` (`id`, `user_id`, `dateMeasured`, `dateUpdated`, `heightMM`, `weightKg`, `waistMM`, `neckMM`, `shouldersMM`, `chestMM`, `bicepMM`, `hipsMM`, `thighMM`) VALUES (1, 2, '2020-01-15', '2020-01-15', 1854, 94.26, 1090, 400, 1220, 1075, 304, 1100, 630);
INSERT INTO `body_measurement_metric` (`id`, `user_id`, `dateMeasured`, `dateUpdated`, `heightMM`, `weightKg`, `waistMM`, `neckMM`, `shouldersMM`, `chestMM`, `bicepMM`, `hipsMM`, `thighMM`) VALUES (2, 3, '2020-01-18', '2020-01-18', 1549, 74.66, 1140, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `body_measurement_metric` (`id`, `user_id`, `dateMeasured`, `dateUpdated`, `heightMM`, `weightKg`, `waistMM`, `neckMM`, `shouldersMM`, `chestMM`, `bicepMM`, `hipsMM`, `thighMM`) VALUES (3, 2, '2020-01-20', '2020-01-20', 1854, 87.63, 1030, NULL, NULL, NULL, NULL, NULL, NULL);

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
INSERT INTO `user_current_goal` (`id`, `user_id`, `goal_id`, `enabled`, `dateCreated`, `dateUpdated`) VALUES (3, 2, 2, 0, '2020-01-18', '2020-01-18');

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

COMMIT;


-- -----------------------------------------------------
-- Data for table `image`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `image` (`id`, `user_id`, `imageUrl`, `dateCreated`, `dateUpdated`) VALUES (1, 2, 'https://i.imgur.com/puVjtA9.jpg', '2020-01-15', '2020-01-15');
INSERT INTO `image` (`id`, `user_id`, `imageUrl`, `dateCreated`, `dateUpdated`) VALUES (2, 2, 'https://i.imgur.com/zSACF2B.jpg', '2020-01-15', '2020-01-15');

COMMIT;


-- -----------------------------------------------------
-- Data for table `avatar`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `avatar` (`id`, `user_id`, `avatarUrl`, `bodyType`, `dateCreated`, `dateUpdated`) VALUES (1, 2, 'https://i.imgur.com/uJaRmvQ.jpg', 'Thin', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `user_id`, `avatarUrl`, `bodyType`, `dateCreated`, `dateUpdated`) VALUES (2, 2, 'https://i.imgur.com/MeZUyU7.jpg', 'Average', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `user_id`, `avatarUrl`, `bodyType`, `dateCreated`, `dateUpdated`) VALUES (3, 2, 'https://i.imgur.com/HM7aIAS.jpg', 'Fat', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `user_id`, `avatarUrl`, `bodyType`, `dateCreated`, `dateUpdated`) VALUES (4, 2, 'https://i.imgur.com/mLpfYbC.jpg', 'Athletic', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `user_id`, `avatarUrl`, `bodyType`, `dateCreated`, `dateUpdated`) VALUES (5, 2, 'https://i.imgur.com/U74DJg6.jpg', 'Muscular', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `user_id`, `avatarUrl`, `bodyType`, `dateCreated`, `dateUpdated`) VALUES (6, 3, 'https://i.imgur.com/6XHNaUq.jpg', 'Thin', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `user_id`, `avatarUrl`, `bodyType`, `dateCreated`, `dateUpdated`) VALUES (7, 3, 'https://i.imgur.com/uXyVqic.jpg', 'Average', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `user_id`, `avatarUrl`, `bodyType`, `dateCreated`, `dateUpdated`) VALUES (8, 3, 'https://i.imgur.com/P0LEumI.jpg', 'Fat', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `user_id`, `avatarUrl`, `bodyType`, `dateCreated`, `dateUpdated`) VALUES (9, 3, 'https://i.imgur.com/hwcAc1R.jpg', 'Athletic', '2020-01-17', '2020-01-17');
INSERT INTO `avatar` (`id`, `user_id`, `avatarUrl`, `bodyType`, `dateCreated`, `dateUpdated`) VALUES (10, 3, 'https://i.imgur.com/e9q4Cgh.jpg', 'Muscular', '2020-01-17', '2020-01-17');

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

COMMIT;


-- -----------------------------------------------------
-- Data for table `post_reply`
-- -----------------------------------------------------
START TRANSACTION;
USE `redou`;
INSERT INTO `post_reply` (`id`, `reply_user_id`, `originalPost_id`, `replyContent`, `dateCreated`, `dateUpdated`) VALUES (1, 2, 1, 'There are several different types of intermittent fasting including: 16 hours fasting with 8 hours eating, One Meal A Day (OMAD). The general idea is to get your blood sugar down and prevent insulin spikes. In reality you can modify it so that it works best for you, but you should go for 16 hour fasts at a minimum. A lot of new research shows that this is the best way for humans to lose weight. It\'s actually thought that this is how our evolutionary ancestors ate (they didn\'t eat 3 meals a day, but instead ate when they were able to get food.', '2020-01-17', '2020-01-17');

COMMIT;

