package com.fssa.bookandplay.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.bookandplay.errors.GroundOwnerDetailValidationErrors;
import com.fssa.bookandplay.exceptions.InvalidGroundOwnerDetailException;
import com.fssa.bookandplay.model.GroundOwner;

class TestGroundOwnerValidator {
	
	GroundOwnerDetailValidator groundOwnerVal =new GroundOwnerDetailValidator();

	
	
	@Test

	void testValidGroundOwnerUserObject() {
		GroundOwner user1 = new GroundOwner("sandeep","sand","sasndeep@gmail.com",9878457899l,"sand@U2208892*7","https://example.com/image1.jpg");

		Assertions.assertTrue(groundOwnerVal.validateGroundOwner(user1));
	}

	@Test

	void testInValidOwnerUserobject() {
		try {
			groundOwnerVal.validateGroundOwner(null);
			Assertions.fail("Validateobject failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_OBJECT_NULL, ex.getMessage());
		}

	}
	
	
//  for valid  ground  name
	@Test

	void testValidName() {
		Assertions.assertTrue(groundOwnerVal.nameValidator("sandeep    ok"));
	}

//  for invalid ground name
	@Test
	void testInvalidName() {

		try {
			groundOwnerVal.nameValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_GROUNDOWNERNAME_NULL, ex.getMessage());
		}

		try {
			groundOwnerVal.nameValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_GROUNDOWNERNAME_NULL, ex.getMessage());
		}

		try {
			groundOwnerVal.nameValidator("a");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_GROUNDOWNERNAME_NAME, ex.getMessage());
		}

	}

//  for valid  ground  name
	@Test

	void testValidOrganisationName() {
		Assertions.assertTrue(groundOwnerVal.organisationNameValidator("sandeep    ok"));
	}

//  for invalid ground name
	@Test
	void testInvalidOrganisationName() {

		try {
			groundOwnerVal.organisationNameValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_ORGANISATION_NAME_NULL, ex.getMessage());
		}

		try {
			groundOwnerVal.organisationNameValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_ORGANISATION_NAME_NULL, ex.getMessage());
		}

		try {
			groundOwnerVal.organisationNameValidator("a");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_ORGANISATION_NAME_NAME, ex.getMessage());
		}

	}

//  for valid  ground  name
	@Test

	void testValidEmail() {
		Assertions.assertTrue(groundOwnerVal.emailValidator("sandeep@gmail.com"));
	}

//  for invalid ground name
	@Test
	void testInvalidEmail() {

		try {
			groundOwnerVal.emailValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_EMAIL_NULL, ex.getMessage());
		}

		try {
			groundOwnerVal.emailValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_EMAIL_NULL, ex.getMessage());
		}

		try {
			groundOwnerVal.emailValidator("sandeepgmail.com");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_EMAIL_PATTERN, ex.getMessage());
		}

	}

	// for valid ground name
	@Test

	void testValidPhoneNo() {
		Assertions.assertTrue(groundOwnerVal.phoneNumberValidator(9922920022l));
	}

//  for invalid ground name
	@Test
	void testInvalidPhoneNo() {

		try {
			groundOwnerVal.phoneNumberValidator(1234567l);
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_PHONENO_PATTERN, ex.getMessage());
		}

	}

	// for valid ground name
	@Test

	void testValidPassword() {
		Assertions.assertTrue(groundOwnerVal.passwordValidator("sand@U2208892*7"));
	}

	// for invalid ground name
	@Test
	void testInvalidPassword() {

		try {
			groundOwnerVal.passwordValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_PASSWORD_NULL, ex.getMessage());
		}

		try {
			groundOwnerVal.passwordValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_PASSWORD_NULL, ex.getMessage());
		}

		try {
			groundOwnerVal.passwordValidator("123");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_PASSWORD_PATTERN, ex.getMessage());
		}

	}


	// for valid ground name
	@Test

	void testValidImage() {
		Assertions.assertTrue(groundOwnerVal.imageValidator("https://example.com/image1.jpg"));
	}

	// for invalid ground name
	@Test
	void testInvalidImage() {

		try {
			groundOwnerVal.imageValidator(null);
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_GROUNDOWNERIMAGE_NULL, ex.getMessage());
		}

		try {
			groundOwnerVal.imageValidator("");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_GROUNDOWNERIMAGE_NULL, ex.getMessage());
		}

		try {
			groundOwnerVal.imageValidator("ma");
			Assertions.fail("Validatename failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_GROUNDOWNERIMAGE, ex.getMessage());
		}

	}

	@Test
	void testValidGroundOwnerId() {
		Assertions.assertTrue(groundOwnerVal.groundOwnerIdValidator(28));
	}

	// for invalid courts
	@Test
	void testInvalidGroundOwnerId() {
		try {
			groundOwnerVal.groundOwnerIdValidator(-2);
			Assertions.fail("Validatecourts failed");
		} catch (InvalidGroundOwnerDetailException ex) {
			Assertions.assertEquals(GroundOwnerDetailValidationErrors.INVALID_GROUNDOWNER_ID, ex.getMessage());
		}

	}


}


