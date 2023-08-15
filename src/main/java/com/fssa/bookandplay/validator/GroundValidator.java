package com.fssa.bookandplay.validator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.bookandplay.errors.GroundValidatorsErrors;
import com.fssa.bookandplay.exceptions.InvalidGroundDetailException;
import com.fssa.bookandplay.model.Ground;
import com.fssa.bookandplay.regexpattern.GroundDetailRegexPattern;

/**
 * Ground Validator class has many methods which validate the attribute
 */
public class GroundValidator {
	


	public GroundValidator() {
	//constructor
	}

	public static boolean validate(Ground ground) throws InvalidGroundDetailException {
		if (ground == null) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_OBJECT_NULL);
		}
		groundNameValidator(ground.getGroundName());
		groundAreaValidator(ground.getGroundMainArea());
		groundAddressValidator(ground.getGroundAddress());
		groundLocationLink(ground.getGroundLocationLink());
		districtNameValidator(ground.getDistrict());
		groundImagesValidator(ground.getGroundImages());
		sportsAvailableValidator(ground.getSportsAvailable());
		startTimeValidator(ground.getStartTime());
		endTimeValidator(ground.getEndTime());
		groundRulesValidator(ground.getGroundRules());
		priceValidator(ground.getPrice());
		increasingPriceForExtraHoursValidator(ground.getIncreasingPriceForExtraHours());
		courtsAvailableValidator(ground.getCourtsAvailable());

		return true;

	}

	/**
	 * Ground name Validator
	 */
	public static boolean groundNameValidator(String groundName) throws InvalidGroundDetailException {
		/**
		 * Ground name null and empty string check
		 */
		if (groundName == null || "".equals(groundName.trim())) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUNDNAME_NULL);
		}
		/**
		 * Ground name regex pattern minimum 2 charcter and max 35 charcter
		 */
		//
		String nameregex =GroundDetailRegexPattern.GROUND_CHARACTER_REGEX;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(groundName);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUND_NAME);

		}

		return true;

	}

	// main area
	public static boolean groundAreaValidator(String groundMainArea) throws InvalidGroundDetailException {
		/**
		 * Groundmain area null and empty string check
		 */
		if (groundMainArea == null || "".equals(groundMainArea.trim())) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_MAINGROUNDAREA_NULL);
		}
		/**
		 * Groundmain area regex pattern minimum 2 charcter and max 27 charcter
		 */
		String nameregex = GroundDetailRegexPattern.GROUND_CHARACTER_REGEX;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(groundMainArea);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_MAINGROUNDAREA_NAME);

		}

		return true;

	}

	/**
	 * groundAddressValidator
	 */
	public static boolean groundAddressValidator(String groundAddress) throws InvalidGroundDetailException {
		// minimum 2 charcter and max 150 charcter
		/**
		 * groundAddress null and empty string check
		 */
		if (groundAddress == null || "".equals(groundAddress.trim())) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_ADDRESS_NULL);
		}
		/**
		 * groundAddress regex pattern minimum 5 charcter and max 170 charcter
		 */
		String nameregex = GroundDetailRegexPattern.GROUND_ADDRESS_REGEX;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(groundAddress);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_ADDRESS_TYPE);

		}

		return true;

	}

	/**
	 * groundLocationLinkValidator
	 */
	public static boolean groundLocationLink(String groundLocationLink) throws InvalidGroundDetailException {
		/**
		 * groundLocationLink null and empty string check
		 */
		if (groundLocationLink == null) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_LOCATION_NULL);
		}
		/**
		 * groundLocationLink regex pattern check
		 */
		String nameregex =GroundDetailRegexPattern.GROUND_LOCATION_REGEX;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(groundLocationLink);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_LOCATION_TYPE);

		}

		return true;

	}

	/**
	 * districtNameValidator
	 */
	public static boolean districtNameValidator(String district) throws InvalidGroundDetailException {
		/**
		 * groundistrict null and empty string check
		 */
		if (district == null || "".equals(district.trim())) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUNDDISNAME_NULL);
		}
		/**
		 * groundistrict regex pattern minimum 2 charcter and max 35 charcter
		 */
		String nameregex =GroundDetailRegexPattern.GROUND_CHARACTER_REGEX;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(district);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUNDDIS_NAME);

		}

		return true;

	}

	/**
	 * groundImagesValidator
	 */
	public static boolean groundImagesValidator(List<String> groundImages) throws InvalidGroundDetailException {
		/**
		 * groundImages null and empty check
		 */
		if (groundImages == null || groundImages.isEmpty()) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUNDIMAGES_NULL);
		}

		/**
		 * groundImages regex pattern check
		 */

		for (String image : groundImages) {

			
			String urlRegex =GroundDetailRegexPattern.GROUND_IMAGES_REGEX;
			Pattern pattern = Pattern.compile(urlRegex);
			Matcher matcher = pattern.matcher(image);
			if (!matcher.matches()) {
				throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUNDIMAGES);
			}
		}

		return true;

	}

	/**
	 * sportsAvailableValidator
	 */
	public static boolean sportsAvailableValidator(List<String> sportsAvailable) throws InvalidGroundDetailException {
		/**
		 * sportsAvailable empty check
		 */
		if (sportsAvailable.isEmpty()) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_SPORTSTYPE);
		}

		/**
		 * sportsAvailable regex pattern check
		 */

		for (String sports : sportsAvailable) {

			String urlRegex = GroundDetailRegexPattern.GROUND_CHARACTER_REGEX;
			Pattern pattern = Pattern.compile(urlRegex);
			Matcher matcher = pattern.matcher(sports);
			if (!matcher.matches()) {
				throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_SPORTSTYPE_PATTERN);
			}
		}

		return true;
	}

	/**
	 * startTimeValidator
	 */

	public static boolean startTimeValidator(LocalTime startTime) throws InvalidGroundDetailException {
		/**
		 * starttime null check
		 */
		if (startTime == null) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_STARTTIME_NULL);
		}
		/**
		 * starttime format check hours or minutes in AM/PM format
		 */
		int hour = startTime.getHour();
		int minute = startTime.getMinute();
		String amPm = startTime.format(DateTimeFormatter.ofPattern("a"));

		if ((hour < 1 || hour > 12) || (minute < 0 || minute > 59)
				|| (!amPm.equalsIgnoreCase("am") && !amPm.equalsIgnoreCase("pm"))) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_STARTTIME_TYPE);
		}

		return true;

	}

	/**
	 * endTimeValidator
	 */
	public static boolean endTimeValidator(LocalTime endTime) throws InvalidGroundDetailException {
		/**
		 * endTime null check
		 */
		if (endTime == null) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_ENDTIME_NULL);
		}

		/**
		 * endTime format check hours or minutes in AM/PM format
		 */

		int hour = endTime.getHour();
		int minute = endTime.getMinute();
		String amPm = endTime.format(DateTimeFormatter.ofPattern("a"));

		if ((hour < 1 || hour > 12) || (minute < 0 || minute > 59)
				|| (!amPm.equalsIgnoreCase("am") && !amPm.equalsIgnoreCase("pm"))) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_ENDTIME_TYPE);
		}

		return true;

	}

	/**
	 * groundRulesValidator
	 */
	public static boolean groundRulesValidator(String groundRules) throws InvalidGroundDetailException {
		/**
		 * groundRules null and empty string check
		 */
		
		if (groundRules == null || "".equals(groundRules.trim())) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUNDRULES_NULL);
		}

		String nameregex = GroundDetailRegexPattern.GROUND_RULES_REGEX;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(groundRules);
		Boolean isMatch = matcher.matches();

	

		/**
		 * groundRules regex pattern check
		 */

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUNDRULES_NAME);

		}

		return true;

	}

	/**
	 * priceValidator
	 */

	public static boolean priceValidator(double price) throws InvalidGroundDetailException {
		/**
		 * groundprice should be greater than 150 and lesser than 2000.
		 */
		if (price >= 150 && price <= 2000) {
			return true;
		}
		throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_PRICE);

	}

	/**
	 * increasingPriceForExtraHoursValidator
	 */

	public static boolean increasingPriceForExtraHoursValidator(double increasingPriceForExtraHours)
			throws InvalidGroundDetailException {
		/**
		 * groundincreasingPriceForExtraHours should be greater than 150 and lesser than
		 * 2000.
		 */
		if (increasingPriceForExtraHours >= 150 && increasingPriceForExtraHours <= 2000) {
			return true;
		}
		throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_INCREASE_PRICE);

	}

	/**
	 * courtsAvailableValidator
	 */

	public static boolean courtsAvailableValidator(int courtsAvailable) throws InvalidGroundDetailException {
		/**
		 * groundcourtsAvailable should be within 6.
		 */
		// contain max 6
		List<Integer> validOptions = List.of(1, 2, 3, 4, 5, 6);
		if (!validOptions.contains(courtsAvailable)) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_COURTSAVAIL_TYPE);
		}
		return true;
	}

	/**
	 * groundIdValidator
	 */
	public static boolean groundIdValidator(int groundId) throws InvalidGroundDetailException {
		/**
		 * groundId should be greaterthan 1.
		 */
		if (groundId <= 0) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_PRODUCT_ID);

		}
		return true;

	}

}
