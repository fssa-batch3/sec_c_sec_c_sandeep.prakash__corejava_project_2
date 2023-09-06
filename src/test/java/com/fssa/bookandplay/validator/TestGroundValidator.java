package com.fssa.bookandplay.validator;

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

class TestGroundValidator {
	GroundValidator groundValidator=new GroundValidator();

	/**
	 * @throws DAOException 
	 * @throws SQLException 
	 * @throws InvalidGroundDetailException 
	 * @Test
	 * 
	 *       void testValidgroundobject() { List<String> validImages =
	 *       Arrays.asList("https://example.com/image1.jpg",
	 *       "https://example.com/image2.jpg"); List<String> validsports =
	 *       Arrays.asList("cricket", "football", "tennis"); LocalTime startTime =
	 *       LocalTime.of(10, 30); // 10:00 AM LocalTime endTime = LocalTime.of(11,
	 *       30); // 5:00 PM Ground ground=new Ground("samplename",
	 *       "samplemainarea", "sampleaddress", "http://google.com",
	 *       "sampledistrict", validImages, validsports, startTime, endTime,
	 *       "samplerules", 200, 200, 3);
	 *       Assertions.assertTrue(GroundValidator.validate(ground)); }
	 */

	@Test

	void testValidgroundobject2() throws InvalidGroundDetailException, SQLException, DAOException {
		List<String> validImages = Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg");
		List<String> validsports = Arrays.asList("cricket", "football", "tennis");
		LocalTime startTime = LocalTime.of(10, 30); // 10:00 AM
		LocalTime endTime = LocalTime.of(11, 30); // 5:00 PM
		// Ground ground=new Ground(1,"samplename", "samplemainarea", "sampleaddress",
		// "http://google.com", "sampledistrict", validImages, validsports, startTime,
		// endTime, "samplerules", 200, 200, 3);
		Ground ground1 = new GroundBuilder()

				.groundIdBuild(1).groundNameBuild("ExampleGround").groundMainAreaBuild("Main Area")
				.groundAddressBuild("123ExampleStreet").groundLocationLinkBuild("https://maps.example.com")
				.districtBuild("SampleDistrict").groundImagesBuild(validImages).sportsAvailableBuild(validsports)
				.startTimeBuild(startTime).endTimeBuild(endTime).groundRulesBuild("Nosmokingallowed").priceBuild(170)
				.increasingPriceForExtraHoursBuild(200).courtsAvailableBuild(2).build();

		Assertions.assertTrue(groundValidator.validate(ground1));
	}

	@Test

	void testInValidgroundobject() throws SQLException, DAOException {
		try {
			groundValidator.validate(null);
			Assertions.fail("Validateobject failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_OBJECT_NULL, ex.getMessage());
		}

	}

//  for valid  ground  name
	@Test

	void testValidName() {
		Assertions.assertTrue(groundValidator.groundNameValidator("sandeep    ok"));
	}

//  for invalid ground name
	@Test
	void testInvalidName() {

		try {
			groundValidator.groundNameValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDNAME_NULL, ex.getMessage());
		}

		try {
			groundValidator.groundNameValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDNAME_NULL, ex.getMessage());
		}

		try {
			groundValidator.groundNameValidator("a");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUND_NAME, ex.getMessage());
		}

	}

//  for valid groundarea
	@Test

	void testValidareaName() {
		Assertions.assertTrue(groundValidator.groundAreaValidator("sandeep  ok"));
	}

//  for invalid groundarea
	@Test
	void testInvalidareaName() {

		try {
			groundValidator.groundAreaValidator(null);
			Assertions.fail("Validatemainarea name failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_MAINGROUNDAREA_NULL, ex.getMessage());
		}
		try {
			groundValidator.groundAreaValidator("a");
			Assertions.fail("Validatemainarea name failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_MAINGROUNDAREA_NAME, ex.getMessage());
		}

		try {
			groundValidator.groundAreaValidator("");
			Assertions.fail("Validatemainarea name failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_MAINGROUNDAREA_NULL, ex.getMessage());
		}

	}

//  for valid groundaddress
	@Test

	void testValidaddress() {
		Assertions.assertTrue(groundValidator.groundAddressValidator("123 Main St."));
	}

//  for invalid groundaddress
	@Test
	void testInvalidaddress() {

		try {
			groundValidator.groundAddressValidator(null);
			Assertions.fail("Validateaddress failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_ADDRESS_NULL, ex.getMessage());
		}
		try {
			groundValidator.groundAddressValidator("");
			Assertions.fail("Validateaddress failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_ADDRESS_NULL, ex.getMessage());
		}

		try {
			groundValidator.groundAddressValidator("a");
			Assertions.fail("Validateaddress failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_ADDRESS_TYPE, ex.getMessage());
		}

	}

//  for valid groundLocationLink
	@Test

	void testValidlocation() {
		Assertions.assertTrue(groundValidator.groundLocationLink("http://google.com"));
	}

//  for invalid groundLocationLink
	@Test
	void testInvalidlocation() {

		try {
			groundValidator.groundLocationLink(null);
			Assertions.fail("Validatelocation failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_LOCATION_NULL, ex.getMessage());
		}
		try {
			groundValidator.groundLocationLink("a");
			Assertions.fail("Validatelocation failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_LOCATION_TYPE, ex.getMessage());
		}

	}

//  for valid districtname
	@Test

	void testValiddistrict() {
		Assertions.assertTrue(groundValidator.districtNameValidator("sandeepok"));
	}

//  for invalid districtname
	@Test
	void testInvaliddistrict() {

		try {

			groundValidator.districtNameValidator(null);
			Assertions.fail("Validatedistrictname failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDDISNAME_NULL, ex.getMessage());
		}

		try {
			groundValidator.districtNameValidator("1");
			Assertions.fail("Validatedistrictname failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDDIS_NAME, ex.getMessage());
		}

	}

//  for valid groundImages
	@Test

	void testValidimages() {
		List<String> validImages = Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg");

		Assertions.assertDoesNotThrow(() -> {
			groundValidator.groundImagesValidator(validImages);
		});

	}

//  for invalid groundImages
	@Test
	void testInvalidimages() {

		List<String> invalidImages = Arrays.asList("image1.jpg", "https://example.com/image2.jpg",
				"https://example.com/image3");
		List<String> invalidImages2 = null;
		List<String> invalidImages3 = Arrays.asList();

		try {
			groundValidator.groundImagesValidator(invalidImages);
			Assertions.fail("Validateimages failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDIMAGES, ex.getMessage());
		}

		try {
			groundValidator.groundImagesValidator(invalidImages2);
			Assertions.fail("Validateimages failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDIMAGES_NULL, ex.getMessage());
		}

		try {
			groundValidator.groundImagesValidator(invalidImages3);
			Assertions.fail("Validateimages failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDIMAGES_NULL, ex.getMessage());
		}

	}

//  for valid sportsAvailable
	@Test

	void testValidsports() {
		List<String> validsports = Arrays.asList("cricket", "football", "tennis");

		Assertions.assertDoesNotThrow(() -> {
			groundValidator.sportsAvailableValidator(validsports);
		});
	}

//  for invalid sportsAvailable
	@Test
	void testInvalidsports() {

		List<String> invalidsports = Arrays.asList();

		try {
			groundValidator.sportsAvailableValidator(invalidsports);
			Assertions.fail("Validatesports failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_SPORTSTYPE, ex.getMessage());
		}

		List<String> invalidsports2 = Arrays.asList("i", "https://example.com/image2.jpg",
				"https://example.com/image3");

		try {
			groundValidator.sportsAvailableValidator(invalidsports2);
			Assertions.fail("Validatesports failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_SPORTSTYPE_PATTERN, ex.getMessage());
		}

	}

//  for valid startTime
	@Test

	void testValidstarttime() {
		LocalTime startTime = LocalTime.of(20, 0); // 10:00 AM

		Assertions.assertTrue(groundValidator.startTimeValidator(startTime));
	}

//  for invalid startTime
	@Test
	void testInvalidstarttime() {
		try {
			groundValidator.startTimeValidator(null);
			Assertions.fail("Validatetime failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_STARTTIME_NULL, ex.getMessage());
		}

		/**

		LocalTime startTime = LocalTime.of(17, 77); // 10:00 AM
		try {

			groundValidator.startTimeValidator(startTime);
			Assertions.fail("Validatetime failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_STARTTIME_TYPE, ex.getMessage());
		}
		*/

	}

//  for valid endTime
	@Test

	void testValidendtime() {

		LocalTime endTime = LocalTime.of(12, 0); // 5:00 PM
		Assertions.assertTrue(groundValidator.endTimeValidator(endTime));
	}

//  for invalid endTime
	@Test
	void testInvalidendtime() {
		try {
			groundValidator.endTimeValidator(null);
			Assertions.fail("Validatetime failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_ENDTIME_NULL, ex.getMessage());
		}
		

		/**
		LocalTime endTime = LocalTime.of(17, 77); // 5:00 PM
		try {

			groundValidator.endTimeValidator(endTime);
			Assertions.fail("Validatetime failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_ENDTIME_TYPE, ex.getMessage());
		}
*/
	}

//  for valid groundRules
	@Test

	void testValidrules() {
		Assertions.assertTrue(groundValidator.groundRulesValidator("hellohellook"));
	}

//  for invalid groundRules
	@Test
	void testInvalidrules() {

		try {
			groundValidator.groundRulesValidator("sssdd");
			Assertions.fail("Validaterules failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDRULES_NAME, ex.getMessage());
		}

		try {
			groundValidator.groundRulesValidator("");
			Assertions.fail("Validaterules failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDRULES_NULL, ex.getMessage());
		}
	}

	@Test
	void testInvalidrulesnull() {

		try {
			groundValidator.groundRulesValidator(null);
			Assertions.fail("Validaterules failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDRULES_NULL, ex.getMessage());
		}

	}

//  for valid price
	@Test

	void testValidprice() {
		Assertions.assertTrue(groundValidator.priceValidator((double) 200));
	}

//  for invalid price
	@Test
	void testInvalidprice() {
		try {
			groundValidator.priceValidator(90);
			Assertions.fail("Validateprice failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_PRICE, ex.getMessage());
		}

	}

//  for valid price
	@Test

	void testValidincreaseprice() {
		Assertions.assertTrue(groundValidator.increasingPriceForExtraHoursValidator(200));
	}

//  for invalid price
	@Test
	void testInvalidincreaseprice() {
		try {
			groundValidator.increasingPriceForExtraHoursValidator(89);
			Assertions.fail("Validateprice failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_INCREASE_PRICE, ex.getMessage());
		}

	}

//  for valid courts
	@Test

	void testValidcourt() {
		Assertions.assertTrue(groundValidator.courtsAvailableValidator(2));
	}

//  for invalid courts
	@Test
	void testInvalidcourt() {
		try {
			groundValidator.courtsAvailableValidator(7);
			Assertions.fail("Validatecourts failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_COURTSAVAIL_TYPE, ex.getMessage());
		}

	}

	@Test
	void testValidgroundId() {
		Assertions.assertTrue(groundValidator.groundIdValidator(2));
	}

//  for invalid courts
	@Test
	void testInvalidgroundId() {
		try {
			groundValidator.groundIdValidator(-2);
			Assertions.fail("Validatecourts failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_PRODUCT_ID, ex.getMessage());
		}

	}

}
