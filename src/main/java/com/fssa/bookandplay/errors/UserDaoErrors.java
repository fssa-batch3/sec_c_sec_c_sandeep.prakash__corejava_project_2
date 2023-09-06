package com.fssa.bookandplay.errors;

public class UserDaoErrors {

	private UserDaoErrors() {
	
	}
	/**
	 * While Inserting the GroundDetails into the database
	 */
	public static final String INSERT_USER_DETAILS_ERROR = "The ADD User details to database is failed";
	/**
	 * While Updating the GroundDetails into the database
	 */

	public static final String UPDATE_USER_DETAILS_ERROR = "The Update User details to database is failed";

	/**
	 * While Getting the all GroundDetails From the database
	 */
	public static final String READ_USER_DETAILS_ERROR = "Error getting User data";
	
	
	public static final String READ_USER_EMAIL_ERROR = "Invalid password. Please check your password and try again.";
	public static final String READ_USER_PASS_ERROR = "User not found with the provided email.";
}
