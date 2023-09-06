package com.fssa.bookandplay.validator;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.bookandplay.builder.UserBuilder;
import com.fssa.bookandplay.errors.UserValidationErrors;
import com.fssa.bookandplay.exceptions.InvalidUserDetailException;
import com.fssa.bookandplay.model.User;

class TestUserValidator {
	UserValidator ul=new UserValidator();

	@Test

	void testValidUserObject() {
		User user1 = new UserBuilder().userIdBuild(1).firstNameBuild("sandeep").lastNameBuild("sand")
				.emailBuild("sandeep@gmail.com").phoneNumberBuild(9922920022l).passwordBuild("sand@U2208892*7")
				.imageBuilder("https://example.com/image1.jpg").build();

		Assertions.assertTrue(ul.validateUser(user1));
	}

	@Test

	void testInValidUserobject() {
		try {
			ul.validateUser(null);
			Assertions.fail("Validateobject failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_OBJECT_NULL, ex.getMessage());
		}

	}

	@Test

	void testValidUserPlayerObject() {

		List<String> validsports = Arrays.asList("cricket", "football", "tennis");
		LocalTime startTime = LocalTime.of(10, 30); // 10:00 AM
		LocalTime endTime = LocalTime.of(11, 30); // 5:00 PM
		User user1 = new UserBuilder().userIdBuild(1).firstNameBuild("sandeep").lastNameBuild("sand")
				.emailBuild("sandeep@gmail.com").phoneNumberBuild(9922920022l).passwordBuild("sand@U2208892*7")
		.ageBuild(27).genderBuild("male").knownSportsBuild(validsports)
				.locationBuild("chennai").timingAvailFromBuild(startTime).timingAvailToBuild(endTime)
				.aboutBuilder("HelloWorld").imageBuilder("https://example.com/image1.jpg").build();

		Assertions.assertTrue(ul.validateUserPlayer(user1));
	}

	@Test

	void testInValidUserPlayerobject() {
		try {
			ul.validateUserPlayer(null);
			Assertions.fail("Validateobject failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_OBJECT_NULL, ex.getMessage());
		}

	}

//  for valid  ground  name
	@Test

	void testValidFirstName() {
		Assertions.assertTrue(ul.userFirstNameValidator("sandeep    ok"));
	}

//  for invalid ground name
	@Test
	void testInvalidFirstName() {

		try {
			ul.userFirstNameValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERFIRSTNAME_NULL, ex.getMessage());
		}

		try {
			ul.userFirstNameValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERFIRSTNAME_NULL, ex.getMessage());
		}

		try {
			ul.userFirstNameValidator("a");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERFIRSTNAME_NAME, ex.getMessage());
		}

	}

//  for valid  ground  name
	@Test

	void testValidLastName() {
		Assertions.assertTrue(ul.userLastNameValidator("sandeep    ok"));
	}

//  for invalid ground name
	@Test
	void testInvalidLastName() {

		try {
			ul.userLastNameValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERLASTNAME_NULL, ex.getMessage());
		}

		try {
			ul.userLastNameValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERLASTNAME_NULL, ex.getMessage());
		}

		try {
			ul.userLastNameValidator("a");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERLASTNAME_NAME, ex.getMessage());
		}

	}

//  for valid  ground  name
	@Test

	void testValidEmail() {
		Assertions.assertTrue(ul.emailValidator("sandeep@gmail.com"));
	}

//  for invalid ground name
	@Test
	void testInvalidEmail() {

		try {
			ul.emailValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USEREMAIL_NULL, ex.getMessage());
		}

		try {
			ul.emailValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USEREMAIL_NULL, ex.getMessage());
		}

		try {
			ul.emailValidator("sandeepgmail.com");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USEREMAIL_PATTERN, ex.getMessage());
		}

	}

	// for valid ground name
	@Test

	void testValidPhoneNo() {
		Assertions.assertTrue(ul.phoneNumberValidator(9922920022l));
	}

//  for invalid ground name
	@Test
	void testInvalidPhoneNo() {

		
		try {
			ul.phoneNumberValidator(1234567l);
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERPHONENO_PATTERN, ex.getMessage());
		}

	}

	// for valid ground name
	@Test

	void testValidPassword() {
		Assertions.assertTrue(ul.passwordValidator("sand@U2208892*7"));
	}

	// for invalid ground name
	@Test
	void testInvalidPassword() {

		try {
			ul.passwordValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERPASSWORD_NULL, ex.getMessage());
		}

		try {
			ul.passwordValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERPASSWORD_NULL, ex.getMessage());
		}

		try {
			ul.passwordValidator("123");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERPASSWORD_PATTERN, ex.getMessage());
		}

	}


	@Test
	void testValidAge() {
		Assertions.assertTrue(ul.ageValidator(28));
	}

	// for invalid courts
	@Test
	void testInvalidAge() {
		try {
			ul.ageValidator(-2);
			Assertions.fail("Validatecourts failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERAGE, ex.getMessage());
		}

	}

	// for valid ground name
	@Test

	void testValidGender() {
		Assertions.assertTrue(ul.genderValidator("male"));
	}

	// for invalid ground name
	@Test
	void testInvalidGender() {

		try {
			ul.genderValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERGENDER_NULL, ex.getMessage());
		}

		try {
			ul.genderValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERGENDER_NULL, ex.getMessage());
		}

		try {
			ul.genderValidator("mal");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERGENDER_DATA, ex.getMessage());
		}

	}

	// for valid sportsAvailable
	@Test

	void testValidsports() {
		List<String> validsports = Arrays.asList("cricket", "football", "tennis");

		Assertions.assertDoesNotThrow(() -> {
			ul.sportsKnownValidator(validsports);
		});
	}

	// for invalid sportsAvailable
	@Test
	void testInvalidsports() {

		List<String> invalidsports = Arrays.asList();

		try {
			ul.sportsKnownValidator(invalidsports);
			Assertions.fail("Validatesports failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_SPORTSTYPE, ex.getMessage());
		}

		List<String> invalidsports2 = Arrays.asList("i", "https://example.com/image2.jpg",
				"https://example.com/image3");

		try {
			ul.sportsKnownValidator(invalidsports2);
			Assertions.fail("Validatesports failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_SPORTSTYPE_PATTERN, ex.getMessage());
		}

	}

	// for valid ground name
	@Test

	void testValidLocation() {
		Assertions.assertTrue(ul.locationValidator("chennnai"));
	}

	// for invalid ground name
	@Test
	void testInvalidLocation() {

		try {
			ul.locationValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERLOCATION_NULL, ex.getMessage());
		}

		try {
			ul.locationValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERLOCATION_NULL, ex.getMessage());
		}

		try {
			ul.locationValidator("u");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERLOCATION, ex.getMessage());
		}

	}

	// for valid startTime
	@Test

	void testValidstarttime() {
		LocalTime startTime = LocalTime.of(10, 0); // 10:00 AM

		Assertions.assertTrue(ul.timeAvailFromValidator(startTime));
	}

	// for invalid startTime
	@Test
	void testInvalidstarttime() {
		try {
			ul.timeAvailFromValidator(null);
			Assertions.fail("Validatetime failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_STARTTIME_NULL, ex.getMessage());
		}

		/**
		LocalTime startTime = LocalTime.of(17, 0); // 10:00 AM
		try {

			ul.timeAvailFromValidator(startTime);
			Assertions.fail("Validatetime failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_STARTTIME_TYPE, ex.getMessage());
		}
		*/

	}

	// for valid endTime
	@Test

	void testValidendtime() {

		LocalTime endTime = LocalTime.of(12, 0); // 5:00 PM
		Assertions.assertTrue(ul.timeAvailToValidator(endTime));
	}

	// for invalid endTime
	@Test
	void testInvalidendtime() {
		try {
			ul.timeAvailToValidator(null);
			Assertions.fail("Validatetime failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_ENDTIME_NULL, ex.getMessage());
		}

		/**
		LocalTime endTime = LocalTime.of(17, 0); // 5:00 PM
		try {

			ul.timeAvailToValidator(endTime);
			Assertions.fail("Validatetime failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_ENDTIME_TYPE, ex.getMessage());
		}

	}*/
	}

	// for valid ground name
	@Test

	void testValidAbout() {
		Assertions.assertTrue(ul.aboutUserValidator("HelloWorld"));
	}

	// for invalid ground name
	@Test
	void testInvalidAbout() {

		try {
			ul.aboutUserValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERABOUT_NULL, ex.getMessage());
		}

		try {
			ul.aboutUserValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERABOUT_NULL, ex.getMessage());
		}

		try {
			ul.aboutUserValidator("ma");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERABOUT_FORMAT, ex.getMessage());
		}

	}

	// for valid ground name
	@Test

	void testValidImage() {
		Assertions.assertTrue(ul.userImageValidator("https://example.com/image1.jpg"));
	}

	// for invalid ground name
	@Test
	void testInvalidImage() {

		try {
			ul.userImageValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERIMAGE_NULL, ex.getMessage());
		}

		try {
			ul.userImageValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERIMAGE_NULL, ex.getMessage());
		}

		try {
			ul.userImageValidator("ma");
			Assertions.fail("Validatename failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USERIMAGE, ex.getMessage());
		}

	}

	@Test
	void testValidUserId() {
		Assertions.assertTrue(ul.userIdValidator(28));
	}

	// for invalid courts
	@Test
	void testInvalidUserId() {
		try {
			ul.userIdValidator(-2);
			Assertions.fail("Validatecourts failed");
		} catch (InvalidUserDetailException ex) {
			Assertions.assertEquals(UserValidationErrors.INVALID_USER_ID, ex.getMessage());
		}

	}

}
