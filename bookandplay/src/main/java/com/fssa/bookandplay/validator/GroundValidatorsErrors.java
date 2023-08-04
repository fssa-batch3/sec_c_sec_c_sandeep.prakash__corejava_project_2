package com.fssa.bookandplay.validator;

public interface GroundValidatorsErrors {
	
	public static final String INVALID_OBJECT_NULL = "Ground object can not null";
	public static final String INVALID_GROUNDNAME_NULL = "ground name cannot be empty or null";
	public static final String INVALID_GROUND_NAME = "The name should be  minimum 2 letters and maximum 35 letters";
	public static final String INVALID_MAINGROUNDAREA_NULL = "Ground main area field cannot be empty or null";
	public static final String INVALID_MAINGROUNDAREA_NAME = "The main area name should be  minimum 2 letters and maximum 27 letters";
	public static final String INVALID_ADDRESS_NULL = "address field cannot be empty or null";
	public static final String INVALID_ADDRESS_TYPE = "The address should be  minimum 10 letters and maximum 150 letters";
	public static final String INVALID_LOCATION_NULL = "location field cannot be empty or null";
	public static final String INVALID_LOCATION_TYPE = "Location field should be in url format";
	public static final String INVALID_GROUNDDISNAME_NULL = "district cannot be empty or null";
	public static final String INVALID_GROUNDDIS_NAME = "The district name should be  minimum 2 letters and maximum 35 letters";
	public static final String INVALID_GROUNDIMAGES = "Invalid image It should be in URL format";
	public static final String INVALID_SPORTSTYPE = "No sport selected";
	public static final String INVALID_STARTTIME_NULL = "Start time cannot be empty or null";
	public static final String INVALID_STARTTIME_TYPE = "Invalid hours or minutes in AM/PM format";
	public static final String INVALID_ENDTIME_NULL = "End time cannot be empty or null";
	public static final String INVALID_ENDTIME_TYPE = "Invalid hours or minutes in AM/PM format";
	public static final String INVALID_GROUNDRULES_NULL = "rules field cannot be empty or null";
	public static final String INVALID_GROUNDRULES_NAME = "The groundRules should be  minimum 10 letters and maximum 150 letters";
	public static final String INVALID_PRICE = "price should be between 150 and 2000";
	public static final String INVALID_INCREASE_PRICE = "increasingPriceForExtraHours should be between 150 and 2000";
	public static final String INVALID_COURTSAVAIL_TYPE = "Invalid number of courts available";
	public static final String INVALID_PRODUCT_ID = "Invalid Product id is passed";


}
