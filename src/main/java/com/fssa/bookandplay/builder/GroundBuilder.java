package com.fssa.bookandplay.builder;

import java.time.LocalTime;
import java.util.List;

import com.fssa.bookandplay.model.Ground;

public class GroundBuilder {
	private int groundId;
	private String groundName;
	private String groundMainArea;
	private String groundAddress;
	private String groundLocationLink;
	private String district;
	private List<String> groundImages;
	private List<String> sportsAvailable;
	private LocalTime startTime;
	private LocalTime endTime;
	private String groundRules;
	private double price;
	private double increasingPriceForExtraHours;
	private int courtsAvailable;

	public GroundBuilder groundId(int groundId) {
		this.groundId=groundId;
		return this;
	}

	public GroundBuilder groundName(String groundName) {
		this.groundName = groundName;
		return this;
	}

	public GroundBuilder groundMainArea(String groundMainArea) {
		this.groundMainArea = groundMainArea;
		return this;
	}

	public GroundBuilder groundAddress(String groundAddress) {
		this.groundAddress = groundAddress;
		return this;
	}

	public GroundBuilder groundLocationLink(String groundLocationLink) {
		this.groundLocationLink = groundLocationLink;
		return this;
	}

	public GroundBuilder district(String district) {
		this.district = district;
		return this;
	}

	public GroundBuilder groundImages(List<String> groundImages) {
		this.groundImages = groundImages;
		return this;
	}

	public GroundBuilder sportsAvailable(List<String> sportsAvailable) {
		this.sportsAvailable = sportsAvailable;
		return this;
	}

	public GroundBuilder startTime(LocalTime startTime) {
		this.startTime = startTime;
		return this;
	}

	public GroundBuilder endTime(LocalTime endTime) {
		this.endTime = endTime;
		return this;
	}

	public GroundBuilder groundRules(String groundRules) {
		this.groundRules = groundRules;
		return this;
	}

	public GroundBuilder price(double price) {
		this.price = price;
		return this;
	}

	public GroundBuilder increasingPriceForExtraHours(double increasingPriceForExtraHours) {
		this.increasingPriceForExtraHours = increasingPriceForExtraHours;
		return this;
	}

	public GroundBuilder courtsAvailable(int courtsAvailable) {
		this.courtsAvailable = courtsAvailable;
		return this;
	}

	public Ground build() {
        Ground ground = new Ground();
       ground.setgroundId(groundId);
        ground.setGroundName(groundName);
        ground.setGroundMainArea(groundMainArea);
        ground.setGroundAddress(groundAddress);
        ground.setGroundLocationLink(groundLocationLink);
        ground.setDistrict(district);
        ground.setGroundImages(groundImages);
        ground.setSportsAvailable(sportsAvailable);
        ground.setStartTime(startTime);
        ground.setEndTime(endTime);
        ground.setGroundRules(groundRules);
        ground.setPrice(price);
        ground.setIncreasingPriceForExtraHours(increasingPriceForExtraHours);
        ground.setCourtsAvailable(courtsAvailable);
        
        return ground;
    }
	


}