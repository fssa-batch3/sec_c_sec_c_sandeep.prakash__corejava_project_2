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
