package com.fssa.bookandplay.validator;

import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.bookandplay.constants.UserConstants;
import com.fssa.bookandplay.errors.UserValidationErrors;

import com.fssa.bookandplay.exceptions.InvalidUserDetailException;
import com.fssa.bookandplay.model.User;

import com.fssa.bookandplay.regexpattern.UserRegexPattern;

public class UserValidator {

	public UserValidator() {
		// private construtor
	}

	public boolean validateUserPlayer(User user) throws InvalidUserDetailException {
		if (user == null) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_OBJECT_NULL);
		}

		userFirstNameValidator(user.getFirstName());
		userLastNameValidator(user.getLastName());
		emailValidator(user.getEmail());
		phoneNumberValidator(user.getPhoneNumber());
		passwordValidator(user.getPassword());
		ageValidator(user.getAge());
		genderValidator(user.getGender());
		sportsKnownValidator(user.getKnownSports());
		locationValidator(user.getLocation());
		timeAvailFromValidator(user.getTimingAvailFrom());
		timeAvailToValidator(user.getTimingAvailTo());
		//aboutUserValidator(user.getAbout());
		userImageValidator(user.getImage());
		return true;
	}

	
	public boolean validateUserPlayerUpdate(User user) throws InvalidUserDetailException {
		if (user == null) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_OBJECT_NULL);
		}

		userFirstNameValidator(user.getFirstName());
		userLastNameValidator(user.getLastName());
		phoneNumberValidator(user.getPhoneNumber());
		ageValidator(user.getAge());
		genderValidator(user.getGender());
		sportsKnownValidator(user.getKnownSports());
		locationValidator(user.getLocation());
		timeAvailFromValidator(user.getTimingAvailFrom());
		timeAvailToValidator(user.getTimingAvailTo());
		aboutUserValidator(user.getAbout());
		userImageValidator(user.getImage());
		return true;
	}
	
	
	public boolean validateUser(User user) throws InvalidUserDetailException {
		if (user == null) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_OBJECT_NULL);
		}

		userFirstNameValidator(user.getFirstName());
		userLastNameValidator(user.getLastName());
		emailValidator(user.getEmail());
		phoneNumberValidator(user.getPhoneNumber());
		passwordValidator(user.getPassword());
		userImageValidator(user.getImage());
		return true;
	}
	
	public boolean validateUserUpdate(User user) throws InvalidUserDetailException {
		if (user == null) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_OBJECT_NULL);
		}

		userFirstNameValidator(user.getFirstName());
		userLastNameValidator(user.getLastName());
		phoneNumberValidator(user.getPhoneNumber());	
		userImageValidator(user.getImage());
		return true;
	}

	/**
	 * UserFirstname Validator
	 */
	public boolean userFirstNameValidator(String firstName) throws InvalidUserDetailException {
		/**
		 * firstName null and empty string check
		 */
		if (firstName == null || "".equals(firstName.trim())) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERFIRSTNAME_NULL);
		}
		/**
		 * firstName regex pattern minimum 2 charcter and max 35 charcter
		 */
		//
		String nameregex = UserRegexPattern.USER_NAME_REGEX;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(firstName);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERFIRSTNAME_NAME);

		}

		return true;

	}

	/**
	 * UserlastName Validator
	 */
	public boolean userLastNameValidator(String lastName) throws InvalidUserDetailException {
		/**
		 * firstName null and empty string check
		 */
		if (lastName == null || "".equals(lastName.trim())) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERLASTNAME_NULL);
		}
		/**
		 * firstName regex pattern minimum 2 charcter and max 35 charcter
		 */
		//
		String nameregex = UserRegexPattern.USER_NAME_REGEX;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(lastName);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERLASTNAME_NAME);

		}

		return true;

	}

	/**
	 * Useremail Validator
	 */
	public boolean emailValidator(String email) throws InvalidUserDetailException {
		/**
		 * firstName null and empty string check
		 */
		if (email == null || "".equals(email.trim())) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USEREMAIL_NULL);
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
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USEREMAIL_PATTERN);

		}

		return true;

	}

	public boolean phoneNumberValidator(long phoneNumber) throws InvalidUserDetailException {
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
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERPHONENO_PATTERN);

		}

		return true;

	}

	public boolean passwordValidator(String password) throws InvalidUserDetailException {
		/**
		 * firstName null and empty string check
		 */
		if (password == null || "".equals(password.trim())) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERPASSWORD_NULL);
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
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERPASSWORD_PATTERN);

		}

		return true;

	}

	/**
	 * UserFirstname Validator
	 */
	
	public boolean ageValidator(int age) throws InvalidUserDetailException {

		// 17 to 20
		if (age >= 10 && age <= 90) {
			return true;
		}
		throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERAGE);

	}

	public  boolean genderValidator(String gender) throws InvalidUserDetailException {
		/**
		 * firstName null and empty string check
		 */
		if (gender == null || "".equals(gender.trim())) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERGENDER_NULL);
		}
		/**
		 * firstName regex pattern minimum 2 charcter and max 35 charcter
		 */
		//
		String regex = UserRegexPattern.USER_GENDER_REGEX;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(gender);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERGENDER_DATA);

		}

		return true;

	}

	/**
	 * sportsAvailableValidator
	 */
	public boolean sportsKnownValidator(List<String> sportsKnown) throws InvalidUserDetailException {
		/**
		 * sportsAvailable empty check
		 */
		if (sportsKnown==null ) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_SPORTSTYPE);
		}

		/**
		 * sportsAvailable regex pattern check
		 */

		for (String sports : sportsKnown) {

			String urlRegex = UserRegexPattern.USER_KNOWNSPORTS_REGEX;
			Pattern pattern = Pattern.compile(urlRegex);
			Matcher matcher = pattern.matcher(sports);
			if (!matcher.matches()) {
				throw new InvalidUserDetailException(UserValidationErrors.INVALID_SPORTSTYPE_PATTERN);
			}
		}

		return true;
	}

	/**
	 * districtNameValidator
	 */
	public boolean locationValidator(String location) throws InvalidUserDetailException {
		/**
		 * groundistrict null and empty string check
		 */
		if (location == null || "".equals(location.trim())) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERLOCATION_NULL);
		}
		/**
		 * groundistrict regex pattern minimum 2 charcter and max 35 charcter
		 */
		String nameregex = UserRegexPattern.USER_LOCATION_REGEX;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(location);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERLOCATION);

		}

		return true;

	}

	/**
	 * startTimeValidator
	 */

	public boolean timeAvailFromValidator(LocalTime startTime) throws InvalidUserDetailException {
		/**
		 * starttime null check
		 */
		if (startTime == null) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_STARTTIME_NULL);
		}
		/**
		 * starttime format check hours or minutes in AM/PM format
		 */
		int hour = startTime.getHour();
		int minute = startTime.getMinute();

		if ((hour < UserConstants.MIN_HOUR || hour > UserConstants.MAX_HOUR) || (minute < UserConstants.MIN_MINUTE || minute > UserConstants.MAX_MINUTE)) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_STARTTIME_TYPE);
		}

		return true;

	}

	/**
	 * endTimeValidator
	 */
	public boolean timeAvailToValidator(LocalTime endTime) throws InvalidUserDetailException {
		/**
		 * endTime null check
		 */
		if (endTime == null) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_ENDTIME_NULL);
		}

		/**
		 * endTime format check hours or minutes in AM/PM format
		 */

		int hour = endTime.getHour();
		int minute = endTime.getMinute();


		if ((hour < UserConstants.MIN_HOUR || hour > UserConstants.MAX_HOUR) || (minute < UserConstants.MIN_MINUTE || minute > UserConstants.MAX_MINUTE)) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_ENDTIME_TYPE);
		}

		return true;

	}

	/**
	 * groundRulesValidator
	 */
	public boolean aboutUserValidator(String about) throws InvalidUserDetailException {
		/**
		 * groundRules null and empty string check
		 */

		if (about == null || "".equals(about.trim())) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERABOUT_NULL);
		}

		String nameregex = UserRegexPattern.USER_ABOUT_REGEX;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(about);
		Boolean isMatch = matcher.matches();

		/**
		 * groundRules regex pattern check
		 */

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERABOUT_FORMAT);

		}

		return true;

	}

	/**
	 * groundImagesValidator
	 */
	public boolean userImageValidator(String userImage) throws InvalidUserDetailException {
		/**
		 * groundImages null and empty check
		 */
		if (userImage == null || "".equals(userImage.trim())) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERIMAGE_NULL);
		}

		/**
		 * groundImages regex pattern check
		 */

		String urlRegex = UserRegexPattern.USER_IMAGE_REGEX;
		Pattern pattern = Pattern.compile(urlRegex);
		Matcher matcher = pattern.matcher(userImage);
		Boolean isMatch = matcher.matches();
		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USERIMAGE);

		}

		return true;

	}

	/**
	 * groundIdValidator
	 */
	public boolean userIdValidator(int userId) throws InvalidUserDetailException {
		/**
		 * groundId should be greaterthan 1.
		 */
		if (userId <= UserConstants.INVALID_USERID) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USER_ID);

		}
		return true;

	}

	
}
