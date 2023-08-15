package com.fssa.bookandplay.builder;

import java.time.LocalTime;
import java.util.List;

import com.fssa.bookandplay.model.User;

public class UserBuilder {
	private int userIdForBuilder;
	private String firstNameForBuilder;
	private String lastNameForBuilder;
	private String emailForBuilder;
	private String phoneNumberForBuilder;
	private String passwordForBuilder;
	private boolean playerStatusForBuilder;
    private String displayNameForBuilder;
    private int ageForBuilder;
    private String genderForBuilder;
    private List<String> knownSportsForBuilder;
    private String locationForBuilder;
    private LocalTime timingAvailFromForBuilder;
    private LocalTime timingAvailToForBuilder;
    private String aboutForBuilder;
    private String imageForBuilder;
    
    public UserBuilder userIdBuild(int userIdForBuilder) {
		this.userIdForBuilder=userIdForBuilder;
		return this;
	}

	public UserBuilder firstNameBuild(String firstNameForBuilder) {
		this.firstNameForBuilder = firstNameForBuilder;
		return this;
	}

	public UserBuilder lastNameBuild(String lastNameForBuilder) {
		this.lastNameForBuilder = lastNameForBuilder;
		return this;
	}

	public UserBuilder emailBuild(String emailForBuilder) {
		this.emailForBuilder = emailForBuilder;
		return this;
	}

	public UserBuilder phoneNumberBuild(String phoneNumberForBuilder) {
		this.phoneNumberForBuilder = phoneNumberForBuilder;
		return this;
	}

	public UserBuilder passwordBuild(String passwordForBuilder) {
		this.passwordForBuilder = passwordForBuilder;
		return this;
	}

	public UserBuilder playerStatusBuild(boolean playerStatusForBuilder) {
		this.playerStatusForBuilder = playerStatusForBuilder;
		return this;
	}
	
	public UserBuilder displayNameBuid(String displayNameForBuilder) {
		this.displayNameForBuilder =displayNameForBuilder;
		return this;
	}
	
    public UserBuilder ageBuild(int ageForBuilder) {
		this.ageForBuilder=ageForBuilder;
		return this;
	}
	public UserBuilder genderBuild(String genderForBuilder) {
		this.genderForBuilder =genderForBuilder;
		return this;
	}


	public UserBuilder knownSportsBuild(List<String> knownSportsForBuilder) {
		this.knownSportsForBuilder = knownSportsForBuilder;
		return this;
	}
	public UserBuilder locationBuild(String locationForBuilder) {
		this.locationForBuilder = locationForBuilder;
		return this;
	}
	public UserBuilder timingAvailFromBuild(LocalTime timingAvailFromForBuilder) {
		this.timingAvailFromForBuilder = timingAvailFromForBuilder;
		return this;
	}

	public UserBuilder timingAvailToBuild(LocalTime timingAvailToForBuilder) {
		this.timingAvailToForBuilder = timingAvailToForBuilder;
		return this;
	}

	public UserBuilder aboutBuilder(String aboutForBuilder) {
		this.aboutForBuilder = aboutForBuilder;
		return this;
	}
	public UserBuilder imageBuilder(String imageForBuilder) {
		this.imageForBuilder = imageForBuilder;
		return this;
	}

	
	public User build() {
		User user=new User();
		user.setUserId(userIdForBuilder);
		user.setFirstName(firstNameForBuilder);
		user.setLastName(lastNameForBuilder);
		user.setEmail(emailForBuilder);
		user.setPhoneNumber(phoneNumberForBuilder);
		user.setPassword(passwordForBuilder);
		user.setPlayerStatus(playerStatusForBuilder);
		user.setDisplayName(displayNameForBuilder);
		user.setAge(ageForBuilder);
		user.setGender(genderForBuilder);
		user.setKnownSports(knownSportsForBuilder);
		user.setLocation(locationForBuilder);
		user.setTimingAvailFrom(timingAvailFromForBuilder);
		user.setTimingAvailTo(timingAvailToForBuilder);
		user.setAbout(aboutForBuilder);
		user.setImage(imageForBuilder);
		
		
		return user;
	}

    
    
}
