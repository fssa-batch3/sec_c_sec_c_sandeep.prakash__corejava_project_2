package com.fssa.bookandplay.validator;

import com.fssa.bookandplay.dao.InvalidGroundDetailException;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.bookandplay.model.Ground;

public class GroundValidator {

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

	// name validate
	public static boolean groundNameValidator(String groundName) throws InvalidGroundDetailException {

		if (groundName == null || "".equals(groundName.trim())) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUNDNAME_NULL);
		}
		// minimum 2 charcter and max 35 charcter
		String nameregex = "^[a-zA-Z ]{2,35}$";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(groundName);
		Boolean isMatch = matcher.matches();

		if (!isMatch) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUND_NAME);

		}

		return true;

	}

	// main area
	public static boolean groundAreaValidator(String groundMainArea) throws InvalidGroundDetailException {
		// minimum 2 charcter and max 27 charcter
		if (groundMainArea == null || "".equals(groundMainArea.trim())) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_MAINGROUNDAREA_NULL);
		}

		String nameregex = "^[a-zA-Z ]{2,35}$";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(groundMainArea);
		Boolean isMatch = matcher.matches();

		if (!isMatch) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_MAINGROUNDAREA_NAME);

		}

		return true;

	}

	// address
	public static boolean groundAddressValidator(String groundAddress) throws InvalidGroundDetailException {
		// minimum 2 charcter and max 150 charcter
		if (groundAddress == null || "".equals(groundAddress.trim())) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_ADDRESS_NULL);
		}

		String nameregex =  "^[A-Za-z0-9'.,\\-\\s]{5,170}$";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(groundAddress);
		Boolean isMatch = matcher.matches();

		if (!isMatch) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_ADDRESS_TYPE);

		}

		return true;

	}

	public static boolean groundLocationLink(String groundLocationLink) throws InvalidGroundDetailException {

		if (groundLocationLink == null) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_LOCATION_NULL);
		}

		String nameregex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(groundLocationLink);
		Boolean isMatch = matcher.matches();

		if (!isMatch) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_LOCATION_TYPE);

		}

		return true;

	}

	// district validate
	public static boolean districtNameValidator(String district) throws InvalidGroundDetailException {

		if (district == null || "".equals(district.trim())) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUNDDISNAME_NULL);
		}

		String nameregex = "^[a-zA-Z ]{2,35}$";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(district);
		Boolean isMatch = matcher.matches();

		if (!isMatch) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUNDDIS_NAME);

		}

		return true;

	}

	// images validate
	public static boolean groundImagesValidator(List<String> groundImages) throws InvalidGroundDetailException {
//
//			if (groundImages == null) {
//				throw new IllegalArgumentException("Images cannot " + "be empty or null");
//			}

		for (String image : groundImages) {
			// "^https?://[\\w.-]+(?:/\\S*)?$"
			// "(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b"
			String urlRegex = "(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b";
			Pattern pattern = Pattern.compile(urlRegex);
			Matcher matcher = pattern.matcher(image);
			if (!matcher.matches()) {
				throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUNDIMAGES);
			}
		}

		return true;

	}

	// sports validate
	public static boolean sportsAvailableValidator(List<String> sportsAvailable) throws InvalidGroundDetailException {

		if (sportsAvailable.isEmpty()) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_SPORTSTYPE);
		}

//		for (String sport : sportsAvailable) {
//			if (sport == null || sport.isEmpty()) {
//				throw new InvalidGroundDetailException("Invalid sport" + sport);
//			}
//		}

		return true;
	}

	// starttime validate
	public static boolean startTimeValidator(LocalTime startTime) throws InvalidGroundDetailException {

		if (startTime == null) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_STARTTIME_NULL);
		}

		int hour = startTime.getHour();
		int minute = startTime.getMinute();
		String amPm = startTime.format(DateTimeFormatter.ofPattern("a"));

		if ((hour < 1 || hour > 12) || (minute < 0 || minute > 59)
				|| (!amPm.equalsIgnoreCase("am") && !amPm.equalsIgnoreCase("pm"))) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_STARTTIME_TYPE);
		}

		return true;

	}

	// endTime validate
	public static boolean endTimeValidator(LocalTime endTime) throws InvalidGroundDetailException {

		if (endTime == null) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_ENDTIME_NULL);
		}

		int hour = endTime.getHour();
		int minute = endTime.getMinute();
		String amPm = endTime.format(DateTimeFormatter.ofPattern("a"));

		if ((hour < 1 || hour > 12) || (minute < 0 || minute > 59)
				|| (!amPm.equalsIgnoreCase("am") && !amPm.equalsIgnoreCase("pm"))) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_ENDTIME_TYPE);
		}

		return true;

	}

	// groundRules
	public static boolean groundRulesValidator(String groundRules) throws InvalidGroundDetailException {

		String nameregex = "^[a-zA-Z0-9\\\\p{Punct}\\\\s]{10,250}$";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(groundRules);
		Boolean isMatch = matcher.matches();

		if (groundRules == null || "".equals(groundRules.trim())) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUNDRULES_NULL);
		}

		else if (!isMatch) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_GROUNDRULES_NAME);

		}

		return true;

	}

	// price
	public static boolean priceValidator(double price) throws InvalidGroundDetailException {

		if (price >= 150 && price <= 2000) {
			return true;
		}
		throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_PRICE);

	}

	// increasingPriceForExtraHours
	public static boolean increasingPriceForExtraHoursValidator(double increasingPriceForExtraHours)
			throws InvalidGroundDetailException {

		if (increasingPriceForExtraHours >= 150 && increasingPriceForExtraHours <= 2000) {
			return true;
		}
		throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_INCREASE_PRICE);

	}

	// courtsAvailable
	public static boolean courtsAvailableValidator(int courtsAvailable) throws InvalidGroundDetailException {
		// contain max 6
		List<Integer> validOptions = List.of(1, 2, 3, 4, 5, 6);
		if (!validOptions.contains(courtsAvailable)) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_COURTSAVAIL_TYPE);
		}
		return true;
	}

	// price
	public static boolean groundIdValidator(int groundId) throws InvalidGroundDetailException {

		if (groundId <= 0) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_PRODUCT_ID);
			

		}
		return true;

	}

}
