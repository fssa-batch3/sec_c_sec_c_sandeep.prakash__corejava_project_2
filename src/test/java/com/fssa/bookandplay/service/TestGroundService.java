package com.fssa.bookandplay.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.fssa.bookandplay.builder.GroundBuilder;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.Ground;
import com.fssa.bookandplay.util.Logger;

class TestGroundService {
	GroundService groundService = new GroundService();

	static Logger logger = new Logger();

	@Test
	void testAddGround() throws DAOException, SQLException {
		Ground ground = getGround2();

		Assertions.assertTrue(groundService.addGround(ground));

	}

	@Test
	void testUpdateGround() throws DAOException, SQLException {
		Ground ground = getGround2();

		Assertions.assertTrue(groundService.updateGround(ground));

	}

	@Test
	void testDeleteGround() throws DAOException, SQLException {

		Assertions.assertTrue(groundService.deleteGround(1));

	}

	Ground getGround2() {
		List<String> validImages = Arrays.asList("https://iili.iosa/Hj2NLMJ.jpg", "https://iili.io/Hj2NLMJ.jpg");
		List<String> validsports = Arrays.asList("cricket", "tennis");
		LocalTime startTime = LocalTime.of(10, 30); // 10:00 AM
		LocalTime endTime = LocalTime.of(11, 30); // 5:00 PM
		// Ground ground=new Ground(1,"samplename", "samplemainarea", "sampleaddress",
		// "http://google.com", "sampledistrict", validImages, validsports, startTime,
		// endTime, "samplerules", 200, 200, 3);
		Ground ground1 = new GroundBuilder().groundIdBuild(2).groundNameBuild("Groundsss  TURF")
				.groundMainAreaBuild("Vyasarpadi").groundAddressBuild("Sri  srirangam rajpurohitssss complex, 1/340, West Ave, MKB Nagar, Vyasarpadi, Chennai, Tamil Nadu 600039")
				.groundLocationLinkBuild("https://goo.gl/maps/YB4zbcOExPUTbGFT6").districtBuild("Chennai")
			.groundOwnerIdBuild(2)
				.groundImagesBuild(validImages).sportsAvailableBuild(validsports).startTimeBuild(startTime)
				.endTimeBuild(endTime).groundRulesBuild("Nosmokingallowedss")// TODO increase the length size
				.priceBuild(270).increasingPriceForExtraHoursBuild(200).courtsAvailableBuild(5).build();

		return ground1;

	}

	/**
	 * Ground getGround() { List<String> validImages =
	 * Arrays.asList("https://example.com/image1.jpg",
	 * "https://example.com/image2.jpg"); List<String> validsports =
	 * Arrays.asList("cricket", "football", "tennis"); LocalTime startTime =
	 * LocalTime.of(10, 30); // 10:00 AM LocalTime endTime = LocalTime.of(11, 30);
	 * // 5:00 PM Ground ground = new Ground("sample name", "sample main area",
	 * "sampleaddress", "http://google.com", "sampledistrict", validImages,
	 * validsports, startTime, endTime, "samplerules", 200, 200, 3); return ground;
	 * 
	 * }
	 */

//

	@Test
	void testGetGroundDetail() throws DAOException, SQLException {

		List<Ground> groundList = groundService.getGroundDetails();
		for (Ground gr : groundList) {
			logger.info(gr);

		}

	}

	@Test
	void testGetGroundDetailById() throws DAOException, SQLException {

		logger.info(assertDoesNotThrow(() -> groundService.getGroundById(2)));

	}
	
	

	@Test
	void testGetGroundDetailByOwnerId() throws DAOException, SQLException {

		logger.info(assertDoesNotThrow(() -> groundService.getGroundByOwnerId(10)));

	}

	@Test
	void testInvalidGetGroundDetailById() throws DAOException, SQLException {

		try {
			assertNull(groundService.getGroundById(0));
		}

		catch (DAOException e) {
			fail("Read ground by id test case failed");
		}

	}

}
