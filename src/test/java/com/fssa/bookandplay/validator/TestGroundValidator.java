package com.fssa.bookandplay.validator;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.bookandplay.builder.GroundBuilder;
import com.fssa.bookandplay.errors.GroundValidatorsErrors;
import com.fssa.bookandplay.exceptions.InvalidGroundDetailException;
import com.fssa.bookandplay.model.Ground;

class TestGroundValidator {

	/**
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

	void testValidgroundobject2() {
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

		Assertions.assertTrue(GroundValidator.validate(ground1));
	}

	@Test

	void testInValidgroundobject() {
		try {
			GroundValidator.validate(null);
			Assertions.fail("Validateobject failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_OBJECT_NULL, ex.getMessage());
		}

	}

//  for valid  ground  name
	@Test

	void testValidName() {
		Assertions.assertTrue(GroundValidator.groundNameValidator("sandeep    ok"));
	}

//  for invalid ground name
	@Test
	void testInvalidName() {

		try {
			GroundValidator.groundNameValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDNAME_NULL, ex.getMessage());
		}

		try {
			GroundValidator.groundNameValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDNAME_NULL, ex.getMessage());
		}

		try {
			GroundValidator.groundNameValidator("a");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUND_NAME, ex.getMessage());
		}

	}

//  for valid groundarea
	@Test

	void testValidareaName() {
		Assertions.assertTrue(GroundValidator.groundAreaValidator("sandeep  ok"));
	}

//  for invalid groundarea
	@Test
	void testInvalidareaName() {

		try {
			GroundValidator.groundAreaValidator(null);
			Assertions.fail("Validatemainarea name failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_MAINGROUNDAREA_NULL, ex.getMessage());
		}
		try {
			GroundValidator.groundAreaValidator("a");
			Assertions.fail("Validatemainarea name failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_MAINGROUNDAREA_NAME, ex.getMessage());
		}

		try {
			GroundValidator.groundAreaValidator("");
			Assertions.fail("Validatemainarea name failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_MAINGROUNDAREA_NULL, ex.getMessage());
		}

	}

//  for valid groundaddress
	@Test

	void testValidaddress() {
		Assertions.assertTrue(GroundValidator.groundAddressValidator("123 Main St."));
	}

//  for invalid groundaddress
	@Test
	void testInvalidaddress() {

		try {
			GroundValidator.groundAddressValidator(null);
			Assertions.fail("Validateaddress failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_ADDRESS_NULL, ex.getMessage());
		}
		try {
			GroundValidator.groundAddressValidator("");
			Assertions.fail("Validateaddress failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_ADDRESS_NULL, ex.getMessage());
		}

		try {
			GroundValidator.groundAddressValidator("a");
			Assertions.fail("Validateaddress failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_ADDRESS_TYPE, ex.getMessage());
		}

	}

//  for valid groundLocationLink
	@Test

	void testValidlocation() {
		Assertions.assertTrue(GroundValidator.groundLocationLink("http://google.com"));
	}

//  for invalid groundLocationLink
	@Test
	void testInvalidlocation() {

		try {
			GroundValidator.groundLocationLink(null);
			Assertions.fail("Validatelocation failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_LOCATION_NULL, ex.getMessage());
		}
		try {
			GroundValidator.groundLocationLink("a");
			Assertions.fail("Validatelocation failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_LOCATION_TYPE, ex.getMessage());
		}

	}

//  for valid districtname
	@Test

	void testValiddistrict() {
		Assertions.assertTrue(GroundValidator.districtNameValidator("sandeepok"));
	}

//  for invalid districtname
	@Test
	void testInvaliddistrict() {

		try {

			GroundValidator.districtNameValidator(null);
			Assertions.fail("Validatedistrictname failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDDISNAME_NULL, ex.getMessage());
		}

		try {
			GroundValidator.districtNameValidator("1");
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
			GroundValidator.groundImagesValidator(validImages);
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
			GroundValidator.groundImagesValidator(invalidImages);
			Assertions.fail("Validateimages failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDIMAGES, ex.getMessage());
		}

		try {
			GroundValidator.groundImagesValidator(invalidImages2);
			Assertions.fail("Validateimages failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDIMAGES_NULL, ex.getMessage());
		}

		try {
			GroundValidator.groundImagesValidator(invalidImages3);
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
			GroundValidator.sportsAvailableValidator(validsports);
		});
	}

//  for invalid sportsAvailable
	@Test
	void testInvalidsports() {

		List<String> invalidsports = Arrays.asList();

		try {
			GroundValidator.sportsAvailableValidator(invalidsports);
			Assertions.fail("Validatesports failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_SPORTSTYPE, ex.getMessage());
		}

		List<String> invalidsports2 = Arrays.asList("i", "https://example.com/image2.jpg",
				"https://example.com/image3");

		try {
			GroundValidator.sportsAvailableValidator(invalidsports2);
			Assertions.fail("Validatesports failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_SPORTSTYPE_PATTERN, ex.getMessage());
		}

	}

//  for valid startTime
	@Test

	void testValidstarttime() {
		LocalTime startTime = LocalTime.of(10, 0); // 10:00 AM

		Assertions.assertTrue(GroundValidator.startTimeValidator(startTime));
	}

//  for invalid startTime
	@Test
	void testInvalidstarttime() {
		try {
			GroundValidator.startTimeValidator(null);
			Assertions.fail("Validatetime failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_STARTTIME_NULL, ex.getMessage());
		}

		LocalTime startTime = LocalTime.of(17, 0); // 10:00 AM
		try {

			GroundValidator.startTimeValidator(startTime);
			Assertions.fail("Validatetime failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_STARTTIME_TYPE, ex.getMessage());
		}

	}

//  for valid endTime
	@Test

	void testValidendtime() {

		LocalTime endTime = LocalTime.of(12, 0); // 5:00 PM
		Assertions.assertTrue(GroundValidator.endTimeValidator(endTime));
	}

//  for invalid endTime
	@Test
	void testInvalidendtime() {
		try {
			GroundValidator.endTimeValidator(null);
			Assertions.fail("Validatetime failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_ENDTIME_NULL, ex.getMessage());
		}
		LocalTime endTime = LocalTime.of(17, 0); // 5:00 PM
		try {

			GroundValidator.endTimeValidator(endTime);
			Assertions.fail("Validatetime failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_ENDTIME_TYPE, ex.getMessage());
		}

	}

//  for valid groundRules
	@Test

	void testValidrules() {
		Assertions.assertTrue(GroundValidator.groundRulesValidator("hellohellook"));
	}

//  for invalid groundRules
	@Test
	void testInvalidrules() {

		try {
			GroundValidator.groundRulesValidator("sssdd");
			Assertions.fail("Validaterules failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDRULES_NAME, ex.getMessage());
		}

		try {
			GroundValidator.groundRulesValidator("");
			Assertions.fail("Validaterules failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDRULES_NULL, ex.getMessage());
		}
	}

	@Test
	void testInvalidrulesnull() {

		try {
			GroundValidator.groundRulesValidator(null);
			Assertions.fail("Validaterules failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_GROUNDRULES_NULL, ex.getMessage());
		}

	}

//  for valid price
	@Test

	void testValidprice() {
		Assertions.assertTrue(GroundValidator.priceValidator((double) 200));
	}

//  for invalid price
	@Test
	void testInvalidprice() {
		try {
			GroundValidator.priceValidator(90);
			Assertions.fail("Validateprice failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_PRICE, ex.getMessage());
		}

	}

//  for valid price
	@Test

	void testValidincreaseprice() {
		Assertions.assertTrue(GroundValidator.increasingPriceForExtraHoursValidator(200));
	}

//  for invalid price
	@Test
	void testInvalidincreaseprice() {
		try {
			GroundValidator.increasingPriceForExtraHoursValidator(89);
			Assertions.fail("Validateprice failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_INCREASE_PRICE, ex.getMessage());
		}

	}

//  for valid courts
	@Test

	void testValidcourt() {
		Assertions.assertTrue(GroundValidator.courtsAvailableValidator(2));
	}

//  for invalid courts
	@Test
	void testInvalidcourt() {
		try {
			GroundValidator.courtsAvailableValidator(7);
			Assertions.fail("Validatecourts failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_COURTSAVAIL_TYPE, ex.getMessage());
		}

	}

	@Test
	void testValidgroundId() {
		Assertions.assertTrue(GroundValidator.groundIdValidator(2));
	}

//  for invalid courts
	@Test
	void testInvalidgroundId() {
		try {
			GroundValidator.groundIdValidator(-2);
			Assertions.fail("Validatecourts failed");
		} catch (InvalidGroundDetailException ex) {
			Assertions.assertEquals(GroundValidatorsErrors.INVALID_PRODUCT_ID, ex.getMessage());
		}

	}

}
