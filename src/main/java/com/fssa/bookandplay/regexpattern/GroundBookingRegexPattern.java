package com.fssa.bookandplay.regexpattern;

public class GroundBookingRegexPattern {

	public GroundBookingRegexPattern() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String GROUND_SPORTS_CHARACTER_REGEX = "^[a-zA-Z ]{2,35}$";
	public static final String GROUND_COURTS_CHARACTER_REGEX = "^[a-zA-Z ]{2,35}$";
	
	 public static final String GROUND_IMAGES_REGEX = "(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b";
	 public static final String GROUND_RULES_REGEX = "^[A-Za-z0-9\\s\\p{P}]{7,300}$";
	 public static final String GROUND_LOCATION_REGEX ="\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	 public static final String GROUND_ADDRESS_REGEX ="^[A-Za-z0-9\\s\\p{P}]{7,170}$";
	 public static final String GROUND_TIMINGSLOT_REGEX ="^(1[0-2]|0?[1-9]):[0-5][0-9] [APM]- (1[0-2]|0?[1-9]):[0-5][0-9] [APM]$";
	 public static final String GROUND_PAYMENT ="(?=.*\\bCash\\b)(?=.*\\bUPI\\b).*$";
	 
}
