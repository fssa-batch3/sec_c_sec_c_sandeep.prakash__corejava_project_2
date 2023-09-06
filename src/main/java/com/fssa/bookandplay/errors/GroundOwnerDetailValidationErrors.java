package com.fssa.bookandplay.errors;

public class GroundOwnerDetailValidationErrors {

	private GroundOwnerDetailValidationErrors() {
		// private construtor
	}
	

	/**
	 * Error message when a User object is null.
	 */
	public static final String INVALID_OBJECT_NULL = "GroundOwner object can not null";
	/**
	 * Error message when the userfirst name is empty or null.
	 */
	public static final String INVALID_GROUNDOWNERNAME_NULL = "User firstname cannot be empty or null";
	/**
	 * Error message when the userfirst name does not meet the length requirements.
	 */
	public static final String INVALID_GROUNDOWNERNAME_NAME = "The firstname should be  minimum 2 letters and maximum 35 letters";
	/**
	 * Error message when the userlast name is empty or null.
	 */
	public static final String INVALID_ORGANISATION_NAME_NULL = "organisation name cannot be empty or null";
	/**
	 * Error message when the userlast name does not meet the length requirements.
	 */
	public static final String INVALID_ORGANISATION_NAME_NAME = "organisation name  should be  minimum 2 letters and maximum 35 letters";

	public static final String INVALID_EMAIL_NULL = "email cannot be empty or null";
	public static final String INVALID_EMAIL_PATTERN = "Invalid email format";

	public static final String INVALID_PHONENO_NULL = "phone number cannot be empty or null";
	public static final String INVALID_PHONENO_PATTERN = "Invalid phone number format";
	public static final String INVALID_PASSWORD_NULL = "password cannot be empty or null";
	public static final String INVALID_PASSWORD_PATTERN = "A valid password should meet the following criteria Have at least 8 characters Contain at least one lowercase letter Contain at least one uppercase letter Contain at least one digit Contain at least one special character";
	/**
	 * Error message when an invalid product ID is passed.
	 */
	public static final String INVALID_GROUNDOWNER_ID= "Invalid User id is passed";
	
	
	/**
	 * Error message when an invalid image URL format is provided.
	 */
	public static final String INVALID_GROUNDOWNERIMAGE = "Invalid image It should be in URL format";
	/**
	 * Error message when the images field is empty or null.
	 */
	public static final String INVALID_GROUNDOWNERIMAGE_NULL = "Images cannot be empty or null";
	

}
