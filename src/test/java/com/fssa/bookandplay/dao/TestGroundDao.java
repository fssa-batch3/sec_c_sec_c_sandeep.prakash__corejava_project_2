package com.fssa.bookandplay.dao;

import java.sql.SQLException;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.bookandplay.builder.GroundBuilder;
import com.fssa.bookandplay.errors.GroundValidatorsErrors;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.exceptions.InvalidGroundDetailException;
import com.fssa.bookandplay.model.Ground;

public class TestGroundDao {
	GroundDao groundDao=new GroundDao();
	
	public static Ground groundValidate() {
		List<String> validImages = Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg");
		List<String> validsports = Arrays.asList("cricket", "football", "tennis");
		LocalTime startTime = LocalTime.of(10, 30); // 10:00 AM
		LocalTime endTime = LocalTime.of(11, 30);   // 5:00 PM
		//Ground ground=new Ground(1,"samplename", "samplemainarea", "sampleaddress", "http://google.com", "sampledistrict", validImages, validsports, startTime, endTime, "samplerules", 200, 200, 3);
		Ground ground1=new GroundBuilder()
				.groundIdBuild(1)
			  	  .groundNameBuild("ExampleGround")
			    .groundMainAreaBuild("Main Area")
			    .groundAddressBuild("123ExampleStreet")
			    .groundLocationLinkBuild("https://maps.example.com")
			    .districtBuild("SampleDistrict")
			    .groundImagesBuild(validImages)
			    .sportsAvailableBuild(validsports)
			    .startTimeBuild(startTime)
			    .endTimeBuild(endTime)
			    .groundRulesBuild("Nosmokingallowed")
			    .priceBuild(170)
			    .increasingPriceForExtraHoursBuild(200)
			    .courtsAvailableBuild(2)
			    .build();
	

		return ground1;

	}

	
	/**
	public static Ground groundValidate2() {
		List<String> validImages = Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg");
		List<String> validsports = Arrays.asList("cricket", "football", "tennis");
		LocalTime startTime = LocalTime.of(10, 30); // 10:00 AM
		LocalTime endTime = LocalTime.of(11, 30); // 5:00 PM

		Ground ground = new Ground();

		ground.setGroundName("s");
		ground.setGroundMainArea("sample main area");
		ground.setGroundAddress("sample address");
		ground.setGroundLocationLink("http://google.com");
		ground.setGroundImages(validImages);
		ground.setSportsAvailable(validsports);
		ground.setDistrict("summa district");
		ground.setStartTime(startTime);
		ground.setEndTime(endTime);
		ground.setCourtsAvailable(8);
		ground.setPrice(200);
		ground.setIncreasingPriceForExtraHours(200);
		ground.setGroundRules("ground rules");

		return ground;

	}
	*/

	public static Ground InvalidGround() {
		Ground ground = new Ground();
		ground.setgroundId(-1);
		return ground;
	}

	@Test

	void testValidAddGround() throws DAOException, SQLException {
		Assertions.assertTrue(groundDao.addGround(groundValidate()));
	}

	@Test

	void testValidUpdateGround() throws DAOException, SQLException {
		Assertions.assertTrue(groundDao.updateGround(groundValidate()));
	}

	@Test

	void testValidDeleteGround() throws DAOException, SQLException {
	
		Assertions.assertTrue(groundDao.deleteGround(36));
	}

	@Test

	void testValidGetGroundDetails() throws DAOException, SQLException {

		Assertions.assertTrue(groundDao.getAllGround());
	}

	// Invalid TestCase

	@Test

	void testInValidAddGround() throws SQLException {
		try {
			groundDao.addGround(groundValidate());
		} catch (DAOException ex) {
			Assertions.assertEquals("The ADD Ground details to database is failed", ex.getMessage());

		}
	}

	@Test
	void testInValidUpdateGround1() throws SQLException, DAOException {
		try {
			Ground ground = new Ground();
			ground.setgroundId(-1);
			groundDao.updateGround(ground);
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_PRODUCT_ID, ex.getMessage());

		}
	}

	@Test

	void testInValidUpdateGround2() throws SQLException, DAOException {
		try {
			groundDao.updateGround(InvalidGround());
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_PRODUCT_ID, ex.getMessage());

		}
	}

	@Test

	void testInValidDeleteGround() throws SQLException, InvalidGroundDetailException, DAOException {
		try {
			groundDao.deleteGround(InvalidGround().getgroundId());
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_PRODUCT_ID, ex.getMessage());

		}
	}
}
