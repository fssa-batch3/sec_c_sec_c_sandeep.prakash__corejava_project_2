package com.fssa.bookandplay.errors;

public class GroundValidatorsErrors {
	
	
	private GroundValidatorsErrors() {
		// private constructor
		
	}
	/**
	 * Error message when a ground object is null.
	 */
	public static final String INVALID_OBJECT_NULL = "Ground object can not null";
	/**
	 * Error message when the ground name is empty or null.
	 */
	public static final String INVALID_GROUNDNAME_NULL = "ground name cannot be empty or null";
	/**
	 * Error message when the ground name does not meet the length requirements.
	 */
	public static final String INVALID_GROUND_NAME = "The name should be  minimum 2 letters and maximum 35 letters";
	/**
	 * Error message when the ground main area field is empty or null.
	 */
	public static final String INVALID_MAINGROUNDAREA_NULL = "Ground main area field cannot be empty or null";
	/**
	 * Error message when the main area name does not meet the length requirements.
	 */
	public static final String INVALID_MAINGROUNDAREA_NAME = "The main area name should be  minimum 2 letters and maximum 27 letters";
	/**
	 * Error message when the address field is empty or null.
	 */
	public static final String INVALID_ADDRESS_NULL = "address field cannot be empty or null";
	/**
	 * Error message when the address does not meet the length requirements.
	 */
	public static final String INVALID_ADDRESS_TYPE = "The address should be  minimum 10 letters and maximum 150 letters";
	/**
	 * Error message when the location field is empty or null.
	 */
	public static final String INVALID_LOCATION_NULL = "location field cannot be empty or null";
	/**
	 * Error message when the location field is not in URL format.
	 */
	public static final String INVALID_LOCATION_TYPE = "Location field should be in url format";
	/**
	 * Error message when the district is empty or null.
	 */
	public static final String INVALID_GROUNDDISNAME_NULL = "district cannot be empty or null";
	/**
	 * Error message when the district name does not meet the length requirements.
	 */
	public static final String INVALID_GROUNDDIS_NAME = "The district name should be  minimum 2 letters and maximum 35 letters";
	/**
	 * Error message when an invalid image URL format is provided.
	 */
	public static final String INVALID_GROUNDIMAGES = "Invalid image It should be in URL format";
	/**
	 * Error message when the images field is empty or null.
	 */
	public static final String INVALID_GROUNDIMAGES_NULL = "Images cannot be empty or null";
	/**
	 * Error message when no sport is selected.
	 */
	public static final String INVALID_SPORTSTYPE = "No sport selected";
	/**
	 * Error message when the sports name does not meet the length requirements.
	 */
	public static final String INVALID_SPORTSTYPE_PATTERN = "The sports name should be  minimum 2 letters and maximum 35 letters";
	/**
	 * Error message when the start time is empty or null.
	 */
	public static final String INVALID_STARTTIME_NULL = "Start time cannot be empty or null";
	/**
	 * Error message when the start time format is invalid (hours or minutes in
	 * AM/PM format).
	 */
	public static final String INVALID_STARTTIME_TYPE = "Invalid hours or minutes in AM/PM format";
	/**
	 * Error message when the end time is empty or null.
	 */
	public static final String INVALID_ENDTIME_NULL = "End time cannot be empty or null";
	/**
	 * Error message when the end time format is invalid (hours or minutes in AM/PM
	 * format).
	 */
	public static final String INVALID_ENDTIME_TYPE = "Invalid hours or minutes in AM/PM format";
	/**
	 * Error message when the ground rules field is empty or null.
	 */
	public static final String INVALID_GROUNDRULES_NULL = "rules field cannot be empty or null";
	/**
	 * Error message when the ground rules do not meet the length requirements.
	 */
	public static final String INVALID_GROUNDRULES_NAME = "The groundRules should be  minimum 10 letters and maximum 150 letters";
	/**
	 * Error message when the price is not within the allowed range (150 to 2000).
	 */
	public static final String INVALID_PRICE = "price should be between 150 and 2000";

	/**
	 * Error message when the increasing price for extra hours is not within the
	 * allowed range (150 to 2000).
	 */

	public static final String INVALID_INCREASE_PRICE = "increasingPriceForExtraHours should be between 150 and 2000";
	/**
	 * Error message when an invalid number of courts available is provided.
	 */
	public static final String INVALID_COURTSAVAIL_TYPE = "Invalid number of courts available";
	/**
	 * Error message when an invalid product ID is passed.
	 */
	public static final String INVALID_PRODUCT_ID = "Invalid Product id is passed";

}
