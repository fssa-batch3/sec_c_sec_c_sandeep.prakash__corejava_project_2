package com.fssa.bookandplay.model;

import java.time.LocalTime;

import java.util.List;


public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private long phoneNumber;
	private String password;
	private boolean playerStatus;
  
    private int age;
    private String gender;
    private List<String> knownSports;
    private String location;
    private LocalTime timingAvailFrom;
    private LocalTime timingAvailTo;
    private String about;
    private String image;
    
    
    
    


	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", password=" + password + ", playerStatus=" + playerStatus
				+ ", age=" + age + ", gender=" + gender + ", knownSports=" + knownSports + ", location=" + location
				+ ", timingAvailFrom=" + timingAvailFrom + ", timingAvailTo=" + timingAvailTo + ", about=" + about
				+ ", image=" + image + "]";
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getPlayerStatus() {
		return playerStatus;
	}
	public void setPlayerStatus(boolean playerStatus) {
		this.playerStatus = playerStatus;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<String> getKnownSports() {
		return knownSports;
	}
	public void setKnownSports(List<String> knownSports) {
		this.knownSports = knownSports;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalTime getTimingAvailFrom() {
		return timingAvailFrom;
	}
	public void setTimingAvailFrom(LocalTime timingAvailFrom) {
		this.timingAvailFrom = timingAvailFrom;
	}
	public LocalTime getTimingAvailTo() {
		return timingAvailTo;
	}
	public void setTimingAvailTo(LocalTime timingAvailTo) {
		this.timingAvailTo = timingAvailTo;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
    
}
