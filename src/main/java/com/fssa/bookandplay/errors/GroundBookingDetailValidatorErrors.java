package com.fssa.bookandplay.errors;

public class GroundBookingDetailValidatorErrors {
	/**
	 * Error message when the start time format is invalid (hours or minutes in
	 * AM/PM format).
	 */
	
	public static final String INVALID_OBJECT_NULL= "Booking details should not be null";
	
	public static final String INVALID_DATE_NULL= "Booking should not be null";
	public static final String INVALID_DATE= "Booking  date should not be in the past";
	
	public static final String INVALID_SPORTSTYPE = "No sport selected";
	/**
	 * Error message when the sports name does not meet the length requirements.
	 */
	public static final String INVALID_SPORTSTYPE_PATTERN = "The sports name should be  minimum 2 letters and maximum 35 letters";
	public static final String INVALID_TIMESLOT = "Invalid time slot";
	public static final String INVALID_COURTSAVAIL_TYPE = "Invalid number of courts available";
	public static final String INVALID_COURTSAVAIL_NULL = "Invalid  courts null";
	public static final String INVALID_PLAYERS_AVAIL_TYPE = "Invalid number of players available";
	
	public static final String INVALID_PRICE = "price should be between 150 and 2000";
	
	public static final String INVALID_PRODUCT_ID = "Invalid Product id is passed";
	public static final String INVALID_PAYMENT_DATE = "Payment  date should not be in the past";
	public static final String INVALID_PAYMENT_NULL = "Payment should not be null ";
	public static final String INVALID_PAYMENT_METHOD = "Invalid Payment method";
	
}
