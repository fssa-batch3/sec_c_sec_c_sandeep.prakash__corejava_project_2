package com.fssa.bookandplay.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.bookandplay.errors.GroundOwnerDetailValidationErrors;
import com.fssa.bookandplay.exceptions.InvalidGroundOwnerDetailException;
import com.fssa.bookandplay.model.GroundOwner;
import com.fssa.bookandplay.regexpattern.UserRegexPattern;

public class GroundOwnerDetailValidator {
	

	public GroundOwnerDetailValidator() {
		// private construtor
	}
	
	
	
	
	public boolean validateGroundOwner(GroundOwner groundOwner) throws InvalidGroundOwnerDetailException {
		if (groundOwner == null) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_OBJECT_NULL);
		}

		nameValidator(groundOwner.getName());
		organisationNameValidator(groundOwner.getOrganisationName());
		emailValidator(groundOwner.getEmail());
		phoneNumberValidator(groundOwner.getPhoneNumber());
		passwordValidator(groundOwner.getPassword());
		imageValidator(groundOwner.getImage());
		return true;
	}

	
	
	/**
	 * UserFirstname Validator
	 */
	public boolean nameValidator(String name) throws InvalidGroundOwnerDetailException {
		/**
		 * firstName null and empty string check
		 */
		if (name == null || "".equals(name.trim())) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_GROUNDOWNERNAME_NULL);
		}
		/**
		 * firstName regex pattern minimum 2 charcter and max 35 charcter
		 */
		//
		String nameregex = UserRegexPattern.USER_NAME_REGEX;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(name);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_GROUNDOWNERNAME_NAME);

		}

		return true;

	}

	/**
	 * UserlastName Validator
	 */
	public boolean organisationNameValidator(String organisationName) throws InvalidGroundOwnerDetailException {
		/**
		 * firstName null and empty string check
		 */
		if (organisationName == null || "".equals(organisationName.trim())) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_ORGANISATION_NAME_NULL);
		}
		/**
		 * firstName regex pattern minimum 2 charcter and max 35 charcter
		 */
		//
		String nameregex = UserRegexPattern.USER_NAME_REGEX;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(organisationName);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_ORGANISATION_NAME_NAME);

		}

		return true;

	}

	/**
	 * Useremail Validator
	 */
	public boolean emailValidator(String email) throws InvalidGroundOwnerDetailException {
		/**
		 * firstName null and empty string check
		 */
		if (email == null || "".equals(email.trim())) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_EMAIL_NULL);
		}
		/**
		 * email regex pattern
		 */
		//
		String emailregex = UserRegexPattern.USER_EMAIL_REGEX;
		Pattern pattern = Pattern.compile(emailregex);
		Matcher matcher = pattern.matcher(email);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_EMAIL_PATTERN);

		}

		return true;

	}

	public boolean phoneNumberValidator(long phoneNumber) throws InvalidGroundOwnerDetailException {
		/**
		 * firstName null and empty string check
		 */

		/**
		 * email regex pattern
		 */
		//
		String regex = UserRegexPattern.USER_PHONENUMBER_REGEX;
		Pattern pattern = Pattern.compile(regex);
		String numberStr = String.valueOf(phoneNumber);
		Matcher matcher = pattern.matcher(numberStr);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_PHONENO_PATTERN);

		}

		return true;

	}

	public boolean passwordValidator(String password) throws InvalidGroundOwnerDetailException {
		/**
		 * firstName null and empty string check
		 */
		if (password == null || "".equals(password.trim())) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_PASSWORD_NULL);
		}
		/**
		 * email regex pattern
		 */
		//
		String regex = UserRegexPattern.USER_PASS_REGEX;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_PASSWORD_PATTERN);

		}

		return true;

	}

	
	/**
	 * groundImagesValidator
	 */
	public boolean imageValidator(String groundOwnerImage) throws InvalidGroundOwnerDetailException {
		/**
		 * groundImages null and empty check
		 */
		if (groundOwnerImage == null || "".equals(groundOwnerImage.trim())) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_GROUNDOWNERIMAGE_NULL);
		}

		/**
		 * groundImages regex pattern check
		 */

		String urlRegex = UserRegexPattern.USER_IMAGE_REGEX;
		Pattern pattern = Pattern.compile(urlRegex);
		Matcher matcher = pattern.matcher(groundOwnerImage);
		Boolean isMatch = matcher.matches();
		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_GROUNDOWNERIMAGE);

		}

		return true;

	}

	/**
	 * groundIdValidator
	 */
	public boolean groundOwnerIdValidator(int groundOwnerId) throws InvalidGroundOwnerDetailException {
		/**
		 * groundId should be greaterthan 1.
		 */
		if (groundOwnerId <= 0) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_GROUNDOWNER_ID);

		}
		return true;

	}


}
