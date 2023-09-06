package com.fssa.bookandplay.model;

public class GroundOwner {
	
	private int groundOwnerId;
	private String name;
	private String organisationName;
	private String email;
	private long phoneNumber;
	private String password;
    private String image;
    
    
	@Override
	public String toString() {
		return "GroundOwner [groundOwnerId=" + groundOwnerId + ", name=" + name + ", organisationName="
				+ organisationName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", password=" + password
				+ ", image=" + image + "]";
	}

	public GroundOwner() {

	}
	
	/**
	 * Ground owner constructor with id
	 *
	 */

	public GroundOwner(int groundOwnerId, String name, String organisationName, String email, long phoneNumber,
			String password, String image) {
		super();
		this.groundOwnerId = groundOwnerId;
		this.name = name;
		this.organisationName = organisationName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.image = image;
	}
	/**
	 * Ground owner constructor without id
	 *
	 */

	public GroundOwner( String name, String organisationName, String email, long phoneNumber,
			String password, String image) {
		super();
		this.name = name;
		this.organisationName = organisationName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.image = image;
	}
	
	
	public int getGroundOwnerId() {
		return groundOwnerId;
	}
	public void setGroundOwnerId(int groundOwnerId) {
		this.groundOwnerId = groundOwnerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
