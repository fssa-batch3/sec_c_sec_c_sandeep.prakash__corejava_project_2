-- desc groundimages;
DROP DATABASE IF  EXISTS bookandplaybackend2;
CREATE DATABASE IF NOT EXISTS bookandplaybackend2;
use bookandplaybackend2;
CREATE TABLE Ground (
  `id` int NOT NULL AUTO_INCREMENT,
  `groundName` varchar(255)UNIQUE NOT NULL,
  `groundMainArea` varchar(255) NOT NULL,
  `groundAddress` varchar(255)UNIQUE NOT NULL,
`groundLocationLink` TEXT NOT NULL,
UNIQUE KEY `unique_groundLocationLink` (`groundLocationLink`(355)),
  `district` varchar(100) NOT NULL,
  `startTime` TIME NOT NULL,
  `endTime` TIME  NOT NULL,
  `groundRules` text,
  `price` decimal(10,2) NOT NULL,
  `increasingPriceForExtraHours` decimal(10,2) NOT NULL,
  `courtsAvailable` int NOT NULL,
  `groundStatus` TINYINT DEFAULT 1,
   -- `seller_id` INT UNIQUE,
  PRIMARY KEY (`id`),
   `groundOwnerId` INT NOT NULL,
 FOREIGN KEY (`groundOwnerId`) REFERENCES GroundOwner(`id`),
 UNIQUE KEY `unique_groundOwner` (`groundOwnerId`)
);
Select * From Ground;
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
Select * From SportsAvailable;

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
Select * From SportsAvailable;
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
    IN  groundOwnerId INT ,
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
       groundOwnerId,
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
        groundOwnerId,
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
    IN groundName VARCHAR(55),
    IN groundMainArea VARCHAR(55),
    IN groundAddress VARCHAR(255),
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
   UPDATE Ground SET
		
        groundName = groundName,
        groundMainArea = groundMainArea,
        groundAddress = groundAddress,
        startTime = startTime,
        endTime = endTime,
        groundRules = groundRules,
        price = price,
        increasingPriceForExtraHours = increasingPriceForExtraHours,
        courtsAvailable = courtsAvailable
        WHERE
        id = groundIdp;
    DELETE FROM GroundImages WHERE groundId = groundIdp;
    DELETE FROM SportsAvailable WHERE groundId = groundIdp;
     -- Get the auto-generated id of the newly inserted row
  --   SET @groundId = LAST_INSERT_ID();

    -- Insert groundImages into the GroundImages table
    SET @groundImagesList = groundImagesList;
    WHILE CHAR_LENGTH(@groundImagesList) > 0 DO
        SET @imageUrl = SUBSTRING_INDEX(@groundImagesList, ',', 1);
        INSERT INTO GroundImages (groundId, imageUrl) VALUES (groundIdp, @imageUrl);
        SET @groundImagesList = SUBSTRING(@groundImagesList, CHAR_LENGTH(@imageUrl) + 2);
    END WHILE;

    -- Insert sportsAvailable into the SportsAvailable table
    SET @sportsAvailableList = sportsAvailableList;
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
    -- Set the groundStatus to FALSE (0) for the given groundId
    UPDATE Ground
    SET groundStatus = 0
    WHERE id = groundIdp;
END &&
DELIMITER ;
-- DROP PROCEDURE IF EXISTS DeleteGround;
select * from  Ground;

desc GroundImages;


SELECT
    g.*,
    (SELECT GROUP_CONCAT(imageUrl) FROM GroundImages gi WHERE gi.groundId = g.id) AS imageUrls,
    (SELECT GROUP_CONCAT(sportName) FROM SportsAvailable sa WHERE sa.groundId = g.id) AS sportNames
FROM Ground g;

-- call InsertGround();

Select * From GroundImages;

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

-- user model

-- user model
CREATE TABLE `User` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(35) NOT NULL,
  `last_name` varchar(35) NOT NULL,
  `email` varchar(20) NOT NULL UNIQUE,
  `phone_number` BIGINT  NOT NULL,
  `password` varchar(255) NOT NULL,
    `imageUrl`TEXT  NOT NULL,
  `playerstatus` BOOLEAN DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(17)  DEFAULT NULL,
  `location` varchar(25) DEFAULT NULL,
  `timing_from` time DEFAULT NULL,
  `timing_to` time DEFAULT NULL,
  `about`TEXT DEFAULT NULL,
  `userStatus` TINYINT DEFAULT 1,
  PRIMARY KEY (`id`)
);
select * from User;
CREATE TABLE UserSportSKnown(
    id INT PRIMARY KEY AUTO_INCREMENT,
    userId INT DEFAULT NULL,
    sportName VARCHAR(100) DEFAULT NULL,
    FOREIGN KEY (userId) REFERENCES User(id)
);
select * from UserSportSKnown;

DELIMITER &&
CREATE  PROCEDURE `InsertUser`(
    IN p_first_name VARCHAR(35),
    IN p_last_name VARCHAR(35),
    IN p_email VARCHAR(100),
    IN p_phone_number BIGINT ,
    IN p_password VARCHAR(255),
        IN p_imageUrl TEXT,
    IN p_playerstatus BOOLEAN,
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
    INSERT INTO User (
       `first_name`,
        `last_name`,
        `email`,
        `phone_number`,
        `password`,
         `imageUrl`,
        `playerstatus`,
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
        INSERT INTO UserSportSKnown (userId, sportName) VALUES (@userId, @sportName);
        SET @sportsKnownList = SUBSTRING(@sportsKnownList, CHAR_LENGTH(@sportName) + 2);
    END WHILE;
    ELSE
        INSERT INTO UserSportSKnown (userId, sportName) VALUES (NULL, NULL);
    END IF;
END &&
DELIMITER ;


DELIMITER &&
CREATE  PROCEDURE `UpdateUser`(
 IN userIdp INT ,
  IN p_first_name VARCHAR(35),
    IN p_last_name VARCHAR(35),
    IN p_phone_number VARCHAR(15),
    IN p_imageUrl TEXT,
    IN p_playerstatus BOOLEAN,
    IN p_age INT,
    IN p_gender VARCHAR(17),
    IN p_location VARCHAR(25),
    IN p_timing_from TIME,
    IN p_timing_to TIME,
    IN p_about TEXT,  
     IN sportsKnownList TEXT 
)
BEGIN
    -- Insert the data into the Ground table
   UPDATE User SET
 first_name=p_first_name,
        last_name=p_last_name,
        phone_number=p_phone_number,
         imageUrl=p_imageUrl,
        playerstatus=p_playerstatus,
        age = IF(p_playerstatus, p_age, NULL),
        gender = IF(p_playerstatus, p_gender, NULL),
        location = IF(p_playerstatus, p_location, NULL),
        timing_from = IF(p_playerstatus, p_timing_from, NULL),
        timing_to = IF(p_playerstatus, p_timing_to, NULL),
        about = IF(p_playerstatus, p_about, NULL)
        WHERE
        id = userIdp;
    
    DELETE FROM UserSportSKnown WHERE userId = userIdp;
 
    IF p_playerstatus THEN
    -- Insert sportsAvailable into the SportsAvailable table
    SET @sportsKnownList = sportsKnownList;
    WHILE CHAR_LENGTH(@sportsKnownList) > 0 DO
        SET @sportName = SUBSTRING_INDEX(@sportsKnownList, ',', 1);
        INSERT INTO UserSportSKnown (userId, sportName) VALUES (userIdp, @sportName);
        SET @sportsKnownList = SUBSTRING(@sportsKnownList, CHAR_LENGTH(@sportName) + 2);
    END WHILE;
    END IF;
END &&
DELIMITER ;

 -- DROP PROCEDURE IF EXISTS UpdateGround;

DELIMITER &&
CREATE PROCEDURE DeleteUser(
    IN userIdp INT
)
BEGIN
    -- Set the groundStatus to FALSE (0) for the given groundId
    UPDATE User
    SET userStatus = 0
    WHERE id = userIdp;
END &&
DELIMITER ;


SELECT
    g.*,
    
    (SELECT GROUP_CONCAT(sportName) FROM UserSportSKnwon sa WHERE sa.userId = g.id) AS sportNames
FROM user g;

-- groundowner table

CREATE TABLE GroundOwner (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    organisationName VARCHAR(30),
    email VARCHAR(25) NOT NULL UNIQUE,
    phoneNumber BIGINT  NOT NULL,
    password VARCHAR(255) NOT NULL,
    image TEXT,
    status  TINYINT DEFAULT 1
);
SELECT COUNT(*) FROM  GroundOwner WHERE email = 'sandeo@gmqail,com';
select * from GroundOwner;
select * from Ground;
select * from User;
select * from Ground;

SELECT g.*, 
    (SELECT GROUP_CONCAT(imageUrl) FROM GroundImages gi WHERE gi.groundId = g.id) AS imageUrls, 
    (SELECT GROUP_CONCAT(sportName) FROM SportsAvailable sa WHERE sa.groundId = g.id) AS sportNames 
FROM Ground g 
WHERE g.groundOwnerId =11 AND g.groundStatus = 1;

-- Booking Table
CREATE TABLE GroundBooking (
    bookingId INT PRIMARY KEY AUTO_INCREMENT,
    bookingDate DATE NOT NULL,
    bookingDuration VARCHAR(55) NOT NULL,
    bookingSports VARCHAR(55) NOT NULL,
    bookingStatus TINYINT DEFAULT 1,
    userBookingStatus TINYINT DEFAULT 1,
    selectedCourts VARCHAR(55) NOT NULL,
    selectedPlayers INT NOT NULL,
    bookedAt BIGINT,
    groundPrice DOUBLE NOT NULL,
    groundId INT NOT NULL,
    requestUserId INT NOT NULL,
    sellerId INT NOT NULL,
    FOREIGN KEY (groundId) REFERENCES Ground(id), 
    FOREIGN KEY (requestUserId) REFERENCES User(id), 
    FOREIGN KEY (sellerId) REFERENCES GroundOwner(id)
);
CREATE TABLE Payment (
    paymentId INT PRIMARY KEY AUTO_INCREMENT,
    bookingId INT NOT NULL,
    paymentDate DATE NOT NULL,
    paymentAmount DOUBLE NOT NULL,
    paymentMethod VARCHAR(25) NOT NULL,
    FOREIGN KEY (bookingId) REFERENCES GroundBooking(bookingId)
);
CREATE TABLE BookingTime (
  bookingTimeId INT PRIMARY KEY AUTO_INCREMENT,
    bookingId INT,
    bookingTime TEXT  NOT NULL,
    FOREIGN KEY (bookingId) REFERENCES GroundBooking(bookingId)
);
select * from GroundBooking;
select * from ground;
select * from Payment;
select * from BookingTime;
select * from User;
select * from GroundOwner;
SELECT COUNT(*) AS count
FROM GroundBooking gb
JOIN BookingTime bt ON gb.bookingId = bt.bookingId
WHERE gb.bookingDate = '2023-09-17'
  AND bt.bookingTime IN ('12:00 PM-1:00 PM')
  AND gb.selectedCourts = 'Court 1'
  AND gb.groundId = 1;

DELIMITER &&
CREATE PROCEDURE `InsertGroundBooking`(
    IN bookingDate DATE,
    IN bookingDuration VARCHAR(55),
    IN bookingSports VARCHAR(55),
    IN selectedCourts VARCHAR(55),
    IN selectedPlayers INT,
    IN bookedAt BIGINT,
    IN groundPrice DOUBLE,
    IN groundId INT,
    IN requestUserId INT,
    IN sellerId INT,
    IN paymentDate DATE,
    IN paymentAmount DOUBLE,
    IN paymentMethod VARCHAR(25),
    IN bookingTimeList TEXT -- Comma-separated list of booking times
)
BEGIN
    -- Insert the data into the GroundBooking table
    INSERT INTO GroundBooking (
        bookingDate,
        bookingDuration,
        bookingSports,
        selectedCourts,
        selectedPlayers,
        bookedAt,
        groundPrice,
        groundId,
        requestUserId,
        sellerId
    ) VALUES (
        bookingDate,
        bookingDuration,
        bookingSports,
        selectedCourts,
        selectedPlayers,
        bookedAt,
        groundPrice,
        groundId,
        requestUserId,
        sellerId
    );

    -- Get the auto-generated bookingId
    SET @bookingId = LAST_INSERT_ID();

    -- Insert the payment information into the Payment table
    INSERT INTO Payment (
        bookingId,
        paymentDate,
        paymentAmount,
        paymentMethod
    ) VALUES (
        @bookingId,
        paymentDate,
        paymentAmount,
        paymentMethod
    );

    -- Insert booking times into the BookingTime table
    SET @bookingTimeList = bookingTimeList;
    WHILE CHAR_LENGTH(@bookingTimeList) > 0 DO
        SET @bookingTime = SUBSTRING_INDEX(@bookingTimeList, ',', 1);
        INSERT INTO BookingTime (bookingId, bookingTime) VALUES (@bookingId, @bookingTime);
        SET @bookingTimeList = SUBSTRING(@bookingTimeList, CHAR_LENGTH(@bookingTime) + 2);
    END WHILE;
END &&
DELIMITER ;





DELIMITER &&
CREATE PROCEDURE `GetGroundBookingById`(
    IN bookingId INT
)
BEGIN
    -- Retrieve the booking details from the GroundBooking table
    SELECT * FROM GroundBooking WHERE bookingId = bookingId;

    -- Retrieve payment information for the booking
    SELECT * FROM Payment WHERE bookingId = bookingId;

    -- Retrieve booking times for the booking
    SELECT * FROM BookingTime WHERE bookingId = bookingId;
END &&
DELIMITER ;




-- request
CREATE TABLE Friend_requests (
    request_id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    status ENUM('Pending', 'Accepted', 'Rejected') NOT NULL DEFAULT 'Pending',
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_sender FOREIGN KEY (sender_id) REFERENCES User(id),
    CONSTRAINT fk_receiver FOREIGN KEY (receiver_id) REFERENCES User(id)
);
select * from Friend_requests;
select * from User;

SELECT fr.*, u.*
FROM Friend_requests fr
JOIN User u ON fr.sender_id = u.id
WHERE fr.receiver_id = 3 AND fr.status = 'Accepted';


SELECT COUNT(*)
FROM Friend_requests
WHERE ((sender_id = 5 AND receiver_id = 3) OR (sender_id = 3 AND receiver_id = 5))
AND status = 'Accepted';









CREATE TABLE messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    text TEXT NOT NULL,
    timestamp BIGINT NOT NULL,
    FOREIGN KEY (sender_id) REFERENCES User(id),
    FOREIGN KEY (receiver_id) REFERENCES User(id)
);



