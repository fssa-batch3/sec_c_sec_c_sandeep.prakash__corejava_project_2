package com.fssa.bookandplay.dao;

import java.sql.SQLException;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.exceptions.InvalidGroundDetailException;
import com.fssa.bookandplay.model.Ground;
import com.fssa.bookandplay.validator.GroundValidatorsErrors;

public class TestGroundDao {

	public static Ground groundValidate() {
		List<String> validImages = Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg");
		List<String> validsports = Arrays.asList("cricket", "football", "tennis");
		LocalTime startTime = LocalTime.of(10, 30); // 10:00 AM
		LocalTime endTime = LocalTime.of(11, 30); // 5:00 PM

		Ground ground = new Ground();
		ground.setgroundId(7);
		ground.setGroundName("sample name");
		ground.setGroundMainArea("sample main area");
		ground.setGroundAddress("sample address");
		ground.setGroundLocationLink("http://google.com");
		ground.setGroundImages(validImages);
		ground.setSportsAvailable(validsports);
		ground.setDistrict("summa district");
		ground.setStartTime(startTime);
		ground.setEndTime(endTime);
		ground.setCourtsAvailable(2);
		ground.setPrice(200);
		ground.setIncreasingPriceForExtraHours(200);
		ground.setGroundRules("ground rules");

		return ground;

	}

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

	public static Ground InvalidGround() {
		Ground ground = new Ground();
		ground.setgroundId(-1);
		return ground;
	}

	@Test

	void testValidAddGround() throws DAOException, SQLException {
		Assertions.assertTrue(GroundDao.addGround(groundValidate2()));
	}

	@Test

	void testValidUpdateGround() throws DAOException, SQLException {
		Assertions.assertTrue(GroundDao.updateGround(groundValidate()));
	}

	@Test

	void testValidDeleteGround() throws DAOException, SQLException {
	
		Assertions.assertTrue(GroundDao.deleteGround(26));
	}

	@Test

	void testValidGetGroundDetails() throws DAOException, SQLException {

		Assertions.assertTrue(GroundDao.getAllGround());
	}

	// Invalid TestCase

	@Test

	void testInValidAddGround() throws SQLException {
		try {
			GroundDao.addGround(groundValidate2());
		} catch (DAOException ex) {
			Assertions.assertEquals("The ADD Ground details to database is failed", ex.getMessage());

		}
	}

	@Test
	void testInValidUpdateGround1() throws SQLException, DAOException {
		try {
			Ground ground = new Ground();
			ground.setgroundId(-1);
			GroundDao.updateGround(ground);
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_PRODUCT_ID, ex.getMessage());

		}
	}

	@Test

	void testInValidUpdateGround2() throws SQLException, DAOException {
		try {
			GroundDao.updateGround(InvalidGround());
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_PRODUCT_ID, ex.getMessage());

		}
	}

	@Test

	void testInValidDeleteGround() throws SQLException, InvalidGroundDetailException, DAOException {
		try {
			GroundDao.deleteGround(InvalidGround().getgroundId());
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_PRODUCT_ID, ex.getMessage());

		}
	}
}
