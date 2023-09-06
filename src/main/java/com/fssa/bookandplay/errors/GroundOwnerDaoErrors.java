package com.fssa.bookandplay.errors;

public class GroundOwnerDaoErrors {
	private GroundOwnerDaoErrors() {
		
	}
	/**
	 * While Inserting the GroundDetails into the database
	 */
	public static final String INSERT_GROUNDOWNER_DETAILS_ERROR = "The ADD GroundOwner details to database is failed";
	/**
	 * While Updating the GroundDetails into the database
	 */

	public static final String UPDATE_GROUNDOWNER_DETAILS_ERROR = "The Update GroundOwner details to database is failed";

	/**
	 * While Getting the all GroundDetails From the database
	 */
	public static final String READ_GROUNDOWNER_DETAILS_ERROR = "Error getting GroundOwner data";
	
	public static final String READ_GROUNDOWNER_EMAIL_ERROR = "Invalid password. Please check your password and try again.";
	public static final String READ_GROUND_OWNER_PASS_ERROR = "User not found with the provided email.";
	
	
}
