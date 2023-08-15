package com.fssa.bookandplay.regexpattern;

public class UserRegexPattern {

	private UserRegexPattern() {
//  private constructor
	}

	public static final String USER_NAME_REGEX = "^[a-zA-Z ]{2,35}$";
	public static final String USER_EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	public static final String USER_PHONENUMBER_REGEX = "(0/91)?[7-9][0-9]{9}";
	public static final String USER_PASSWORD_REGEX = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
	public static final String USER_GENDER_REGEX = "^(?i)(male|female|cisgender|transgender|non-binary|genderqueer|agender|bigender|"
			+ "two-spirit|androgyne|genderfluid|demiboy|demigirl|neutrois|pangender|"
			+ "gender questioning|gender nonconforming|other)$";
	public static final String USER_KNOWNSPORTS_REGEX = "^[a-zA-Z ]{2,35}$";
	public static final String USER_LOCATION_REGEX = "^[a-zA-Z ]{2,35}$";
	public static final String USER_ABOUT_REGEX =  "^[a-zA-Z0-9\\\\\\\\p{Punct}\\\\\\\\s]{10,250}$"; 
	 public static final String USER_IMAGE_REGEX = "(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b";
}
