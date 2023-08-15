package com.fssa.bookandplay.errors;

public class UserValidationErrors {

	private UserValidationErrors() {
		// private construtor
	}

	/**
	 * Error message when a User object is null.
	 */
	public static final String INVALID_OBJECT_NULL = "User object can not null";
	/**
	 * Error message when the userfirst name is empty or null.
	 */
	public static final String INVALID_USERFIRSTNAME_NULL = "User firstname cannot be empty or null";
	/**
	 * Error message when the userfirst name does not meet the length requirements.
	 */
	public static final String INVALID_USERFIRSTNAME_NAME = "The firstname should be  minimum 2 letters and maximum 35 letters";
	/**
	 * Error message when the userlast name is empty or null.
	 */
	public static final String INVALID_USERLASTNAME_NULL = "User lastname cannot be empty or null";
	/**
	 * Error message when the userlast name does not meet the length requirements.
	 */
	public static final String INVALID_USERLASTNAME_NAME = "The lastname should be  minimum 2 letters and maximum 35 letters";

	public static final String INVALID_USEREMAIL_NULL = "email cannot be empty or null";
	public static final String INVALID_USEREMAIL_PATTERN = "Invalid email format";

	public static final String INVALID_USERPHONENO_NULL = "phone number cannot be empty or null";
	public static final String INVALID_USERPHONENO_PATTERN = "Invalid phone number format";
	public static final String INVALID_USERPASSWORD_NULL = "password cannot be empty or null";
	public static final String INVALID_USERPASSWORD_PATTERN = "A valid password should meet the following criteria Have at least 8 characters Contain at least one lowercase letter Contain at least one uppercase letter Contain at least one digit Contain at least one special character";
	public static final String INVALID_USERDISPLAYNAME_NULL = "User displayname cannot be empty or null";
	/**
	 * Error message when the userfirst name does not meet the length requirements.
	 */
	public static final String INVALID_USERDISPLAYNAME_NAME = "The displayname should be  minimum 2 letters and maximum 35 letters";

	public static final String INVALID_USERAGE = "Age should be between 10 and 90";
	public static final String INVALID_USERGENDER_NULL = "Usergender cannot be empty or null";
	/**
	 * Error message when the userfirst name does not meet the length requirements.
	 */
	public static final String INVALID_USERGENDER_DATA = "Invalid usergender data";
	/**
	 * Error message when no sport is selected.
	 */
	public static final String INVALID_SPORTSTYPE = "No sport selected";
	/**
	 * Error message when the sports name does not meet the length requirements.
	 */
	public static final String INVALID_SPORTSTYPE_PATTERN = "The sports name should be  minimum 2 letters and maximum 35 letters";

	/**
	 * Error message when the userfirst name is empty or null.
	 */
	public static final String INVALID_USERLOCATION_NULL = "User location cannot be empty or null";
	/**
	 * Error message when the userfirst name does not meet the length requirements.
	 */
	public static final String INVALID_USERLOCATION = "The User location should be  minimum 2 letters and maximum 35 letters";

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
	 * Error message when the userfirst name is empty or null.
	 */
	public static final String INVALID_USERABOUT_NULL = "Userabout cannot be empty or null";
	/**
	 * Error message when the userfirst name does not meet the length requirements.
	 */
	public static final String INVALID_USERABOUT_FORMAT = "The about information should be a string of maximum 300 characters.";

	/**
	 * Error message when an invalid product ID is passed.
	 */
	public static final String INVALID_USER_ID = "Invalid User id is passed";
	
	
	/**
	 * Error message when an invalid image URL format is provided.
	 */
	public static final String INVALID_USERIMAGE = "Invalid image It should be in URL format";
	/**
	 * Error message when the images field is empty or null.
	 */
	public static final String INVALID_USERIMAGE_NULL = "Images cannot be empty or null";
	

}
