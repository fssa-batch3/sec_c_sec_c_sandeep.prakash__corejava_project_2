-- desc groundimages;
-- DROP DATABASE IF  EXISTS bookandplaybackend;
-- CREATE DATABASE IF NOT EXISTS bookandplaybackend;
use sandeep_prakash_corejava_project;
CREATE TABLE Ground (
  `id` int NOT NULL AUTO_INCREMENT,
  `groundName` varchar(255) NOT NULL,
  `groundMainArea` varchar(255) NOT NULL,
  `groundAddress` varchar(255) NOT NULL,
  `groundLocationLink` TEXT NOT NULL,
  `district` varchar(100) NOT NULL,
  `startTime` TIME NOT NULL,
  `endTime` TIME  NOT NULL,
  `groundRules` text,
  `price` decimal(10,2) NOT NULL,
  `increasingPriceForExtraHours` decimal(10,2) NOT NULL,
  `courtsAvailable` int NOT NULL,
   -- `seller_id` INT UNIQUE,
  PRIMARY KEY (`id`)
  -- FOREIGN KEY (`seller_id`) REFERENCES sellers(`seller_id`)
);

CREATE TABLE GroundImages (
    id INT PRIMARY KEY AUTO_INCREMENT,
    groundId INT NOT NULL,
    imageUrl TEXT  NOT NULL,
    FOREIGN KEY (groundId) REFERENCES Ground(id)
);
CREATE TABLE SportsAvailable (
    id INT PRIMARY KEY AUTO_INCREMENT,
    groundId INT NOT NULL,
    sportName VARCHAR(100) NOT NULL,
    FOREIGN KEY (groundId) REFERENCES Ground(id)
);


/*
CREATE TABLE `groundimages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `groundId` int NOT NULL,
  `imageUrl` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `groundId` (`groundId`),
  CONSTRAINT `groundimages_ibfk_1` FOREIGN KEY (`groundId`) REFERENCES `ground` (`id`)
);
Select * From groundimages;-- 

CREATE TABLE `sportsavailable` (
  `id` int NOT NULL AUTO_INCREMENT,
  `groundId` int NOT NULL,
  `sportName` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `groundId` (`groundId`),
  CONSTRAINT `sportsavailable_ibfk_1` FOREIGN KEY (`groundId`) REFERENCES `ground` (`id`)
);
*/

DELIMITER &&
CREATE  PROCEDURE `InsertGround`(
    IN groundName VARCHAR(55),
    IN groundMainArea VARCHAR(55),
    IN groundAddress VARCHAR(255),
    IN groundLocationLink TEXT,
    IN district VARCHAR(100),
    IN startTime TIME,
    IN endTime TIME,
    IN groundRules TEXT,
    IN price DECIMAL(10, 2),
    IN increasingPriceForExtraHours DECIMAL(10, 2),
    IN courtsAvailable INT,
    IN groundImagesList TEXT, -- Comma-separated list of image URLs
    IN sportsAvailableList TEXT -- Comma-separated list of sport names
)
BEGIN
    -- Insert the data into the Ground table
    INSERT INTO Ground (
        groundName,
        groundMainArea,
        groundAddress,
        groundLocationLink,
        district,
        startTime,
        endTime,
        groundRules,
        price,
        increasingPriceForExtraHours,
        courtsAvailable
    ) VALUES (
        groundName,
        groundMainArea,
        groundAddress,
        groundLocationLink,
        district,
        startTime,
        endTime,
        groundRules,
        price,
        increasingPriceForExtraHours,
        courtsAvailable
    );
     -- Get the auto-generated id of the newly inserted row
    SET @groundId = LAST_INSERT_ID();

    -- Insert groundImages into the GroundImages table
    SET @groundImagesList = groundImagesList;
    WHILE CHAR_LENGTH(@groundImagesList) > 0 DO
        SET @imageUrl = SUBSTRING_INDEX(@groundImagesList, ',', 1);
        INSERT INTO GroundImages (groundId, imageUrl) VALUES (@groundId, @imageUrl);
        SET @groundImagesList = SUBSTRING(@groundImagesList, CHAR_LENGTH(@imageUrl) + 2);
    END WHILE;

    -- Insert sportsAvailable into the SportsAvailable table
    SET @sportsAvailableList = sportsAvailableList;
    WHILE CHAR_LENGTH(@sportsAvailableList) > 0 DO
        SET @sportName = SUBSTRING_INDEX(@sportsAvailableList, ',', 1);
        INSERT INTO SportsAvailable (groundId, sportName) VALUES (@groundId, @sportName);
        SET @sportsAvailableList = SUBSTRING(@sportsAvailableList, CHAR_LENGTH(@sportName) + 2);
    END WHILE;
END &&
DELIMITER ;


DELIMITER &&
CREATE  PROCEDURE `UpdateGround`(
 IN groundIdp INT ,
    IN groundNamein VARCHAR(55),
    IN groundMainAreain VARCHAR(55),
    IN groundAddressin VARCHAR(255),
    IN groundLocationLinkin TEXT,
    IN districtin VARCHAR(100),
    IN startTimein TIME,
    IN endTimein TIME,
    IN groundRulesin TEXT,
    IN pricein DECIMAL(10, 2),
    IN increasingPriceForExtraHoursin DECIMAL(10, 2),
    IN courtsAvailablein INT,
    IN groundImagesListin TEXT, -- Comma-separated list of image URLs
    IN sportsAvailableListin TEXT -- Comma-separated list of sport names
)
BEGIN
    -- Insert the data into the Ground table
   UPDATE Ground SET
		
        groundName = groundNamein,
        groundMainArea = groundMainAreain,
        groundAddress = groundAddressin,
        groundLocationLink = groundLocationLinkin,
        district = districtin,
        startTime = startTimein,
        endTime = endTimein,
        groundRules = groundRulesin,
        price = pricein,
        increasingPriceForExtraHours = increasingPriceForExtraHoursin,
        courtsAvailable = courtsAvailablein
        WHERE
        id = groundIdp;
    DELETE FROM GroundImages WHERE groundId = groundIdp;
    DELETE FROM SportsAvailable WHERE groundId = groundIdp;
     -- Get the auto-generated id of the newly inserted row
  --   SET @groundId = LAST_INSERT_ID();

    -- Insert groundImages into the GroundImages table
    SET @groundImagesList = groundImagesListin;
    WHILE CHAR_LENGTH(@groundImagesList) > 0 DO
        SET @imageUrl = SUBSTRING_INDEX(@groundImagesList, ',', 1);
        INSERT INTO GroundImages (groundId, imageUrl) VALUES (groundIdp, @imageUrl);
        SET @groundImagesList = SUBSTRING(@groundImagesList, CHAR_LENGTH(@imageUrl) + 2);
    END WHILE;

    -- Insert sportsAvailable into the SportsAvailable table
    SET @sportsAvailableList = sportsAvailableListin;
    WHILE CHAR_LENGTH(@sportsAvailableList) > 0 DO
        SET @sportName = SUBSTRING_INDEX(@sportsAvailableList, ',', 1);
        INSERT INTO SportsAvailable (groundId, sportName) VALUES (groundIdp, @sportName);
        SET @sportsAvailableList = SUBSTRING(@sportsAvailableList, CHAR_LENGTH(@sportName) + 2);
    END WHILE;
END &&
DELIMITER ;

 -- DROP PROCEDURE IF EXISTS UpdateGround;

DELIMITER &&
CREATE PROCEDURE DeleteGround(
    IN groundIdp INT
)
BEGIN
    -- Delete the record from the Ground table
--    DELETE FROM Ground WHERE id = groundId;

    -- Delete existing GroundImages and SportsAvailable records for the given groundId
    DELETE FROM GroundImages WHERE groundId = groundIdp;
    DELETE FROM SportsAvailable WHERE groundId = groundIdp;
    
        DELETE FROM Ground WHERE id = groundIdp;
END &&
DELIMITER ;
-- DROP PROCEDURE IF EXISTS DeleteGround;


desc GroundImages;


SELECT
    g.*,
    (SELECT GROUP_CONCAT(imageUrl) FROM GroundImages gi WHERE gi.groundId = g.id) AS imageUrls,
    (SELECT GROUP_CONCAT(sportName) FROM SportsAvailable sa WHERE sa.groundId = g.id) AS sportNames
FROM Ground g;

-- call InsertGround();



/*
 CREATE TABLE sellers (
    seller_id INT NOT NULL AUTO_INCREMENT,
    seller_name VARCHAR (150) NOT NULL,
    seller_email VARCHAR (200) NOT NULL UNIQUE KEY,
    seller_password VARCHAR(255) NOT NULL,
    seller_phoneno VARCHAR(20) NOT NULL,
    seller_ground_name VARCHAR(255)NOT NULL,
    imagename VARCHAR(255),
    image TEXT,
      PRIMARY KEY (`seller_id`)
);

*/

-- user model
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(35) NOT NULL,
  `last_name` varchar(35) NOT NULL,
  `email` varchar(20) NOT NULL UNIQUE,
  `phone_number` VARCHAR(15) NOT NULL,
  `password` varchar(255) NOT NULL,
    `imageUrl`TEXT  NOT NULL,
  `playerstatus` BOOLEAN DEFAULT NULL,
  `display_name` varchar(20) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(17)  DEFAULT NULL,
  `location` varchar(25) DEFAULT NULL,
  `timing_from` time DEFAULT NULL,
  `timing_to` time DEFAULT NULL,
  `about`TEXT DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE UserSportSKnwon (
    id INT PRIMARY KEY AUTO_INCREMENT,
    userId INT DEFAULT NULL,
    sportName VARCHAR(100) DEFAULT NULL,
    FOREIGN KEY (userId) REFERENCES user(id)
);


DELIMITER &&
CREATE  PROCEDURE `InsertUser`(
    IN p_first_name VARCHAR(35),
    IN p_last_name VARCHAR(35),
    IN p_email VARCHAR(100),
    IN p_phone_number VARCHAR(15),
    IN p_password VARCHAR(255),
        IN p_imageUrl TEXT,
    IN p_playerstatus BOOLEAN,
    IN p_display_name VARCHAR(20),
    IN p_age int,
    IN p_gender VARCHAR(17),
    IN p_location VARCHAR(25),
    IN p_timing_from TIME,
    IN p_timing_to TIME,
    IN p_about TEXT,  
     IN sportsKnownList TEXT 
)
BEGIN

 IF p_age IS NULL THEN
        SET p_age = NULL;
    END IF;
    -- Insert the data into the Ground table
    INSERT INTO user (
       `first_name`,
        `last_name`,
        `email`,
        `phone_number`,
        `password`,
         `imageUrl`,
        `playerstatus`,
        `display_name`,
        `age`,
        `gender`,
        `location`,
        `timing_from`,
        `timing_to`,
        `about`
       
    ) VALUES (
      p_first_name,
        p_last_name,
        p_email,
        p_phone_number,
        p_password,
          p_imageUrl,
        p_playerstatus,
       IF(p_playerstatus, p_display_name, NULL),
         IF(p_playerstatus, p_age, NULL),
        IF(p_playerstatus, p_gender, NULL),
        IF(p_playerstatus, p_location, NULL),
        IF(p_playerstatus, p_timing_from, NULL),
        IF(p_playerstatus, p_timing_to, NULL),
        IF(p_playerstatus, p_about, NULL)

    );
     -- Get the auto-generated id of the newly inserted row
    SET @userId = LAST_INSERT_ID();
    -- Insert sportsAvailable into the SportsAvailable table
      IF p_playerstatus THEN
    SET @sportsKnownList = sportsKnownList;
    WHILE CHAR_LENGTH(@sportsKnownList) > 0 DO
        SET @sportName = SUBSTRING_INDEX(@sportsKnownList, ',', 1);
        INSERT INTO UserSportSKnwon (userId, sportName) VALUES (@userId, @sportName);
        SET @sportsKnownList = SUBSTRING(@sportsKnownList, CHAR_LENGTH(@sportName) + 2);
    END WHILE;
    ELSE
        INSERT INTO UserSportSKnwon (userId, sportName) VALUES (NULL, NULL);
    END IF;
END &&
DELIMITER ;


DELIMITER &&
CREATE  PROCEDURE `UpdateUser`(
 IN userIdp INT ,
  IN p_first_namein VARCHAR(35),
    IN p_last_namein VARCHAR(35),
    IN p_emailin VARCHAR(100),
    IN p_phone_numberin VARCHAR(15),
    IN p_passwordin VARCHAR(255),
        IN p_imageUrlin TEXT,
    IN p_playerstatusin BOOLEAN,
    IN p_display_namein VARCHAR(20),
    IN p_agein INT,
    IN p_genderin VARCHAR(17),
    IN p_locationin VARCHAR(25),
    IN p_timing_fromin TIME,
    IN p_timing_toin TIME,
    IN p_aboutin TEXT,  
     IN sportsKnownListin TEXT 
)
BEGIN
    -- Insert the data into the Ground table
   UPDATE user SET
 first_name=p_first_namein,
        last_name=p_last_namein,
        email=p_emailin,
        phone_number=p_phone_numberin,
        password=p_passwordin,
         imageUrl=p_imageUrlin,
        playerstatus=p_playerstatusin,
        display_name = IF(p_playerstatus, p_display_namein, NULL),
        age = IF(p_playerstatus, p_agein, NULL),
        gender = IF(p_playerstatus, p_genderin, NULL),
        location = IF(p_playerstatus, p_locationin, NULL),
        timing_from = IF(p_playerstatus, p_timing_fromin, NULL),
        timing_to = IF(p_playerstatus, p_timing_toin, NULL),
        about = IF(p_playerstatus, p_aboutin, NULL)
        WHERE
        id = userIdp;
    
    DELETE FROM UserSportSKnwon WHERE userId = userIdp;
 
    IF p_playerstatus THEN
    -- Insert sportsAvailable into the SportsAvailable table
    SET @sportsKnownList = sportsKnownListin;
    WHILE CHAR_LENGTH(@sportsKnownList) > 0 DO
        SET @sportName = SUBSTRING_INDEX(@sportsKnownList, ',', 1);
        INSERT INTO UserSportSKnwon (userId, sportName) VALUES (userIdp, @sportName);
        SET @sportsKnownList = SUBSTRING(@sportsKnownList, CHAR_LENGTH(@sportName) + 2);
    END WHILE;
    END IF;
END &&
DELIMITER ;

 -- DROP PROCEDURE IF EXISTS UpdateGround;

DELIMITER &&
CREATE PROCEDURE DeleteGround(
    IN userIdp INT
)
BEGIN


    DELETE FROM UserSportSKnwon WHERE userId = userIdp;
    
        DELETE FROM Ground WHERE id = userIdp;
END &&
DELIMITER ;


SELECT
    g.*,
    
    (SELECT GROUP_CONCAT(sportName) FROM UserSportSKnwon sa WHERE sa.userId = g.id) AS sportNames
FROM user g;