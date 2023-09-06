package com.fssa.bookandplay.builder;

import java.time.LocalTime;
import java.util.List;

import com.fssa.bookandplay.model.Ground;

public class GroundBuilder {
	
	private int groundIdForBuilder;
	private String groundNameForBuilder;
	private String groundMainAreaForBuilder;
	private String groundAddressForBuilder;
	private String groundLocationLinkForBuilder;
	private String districtForBuilder;
	private List<String> groundImagesForBuilder;
	private List<String> sportsAvailableForBuilder;
	private LocalTime startTimeForBuilder;
	private LocalTime endTimeForBuilder;
	private String groundRulesForBuilder;
	private double priceForBuilder;
	private double increasingPriceForExtraHoursForBuilder;
	private int courtsAvailableForBuilder;
	private int groundOwnerIdForBuilder;

	public GroundBuilder groundIdBuild(int groundIdForBuilder) {
		this.groundIdForBuilder=groundIdForBuilder;
		return this;
	}

	public GroundBuilder groundNameBuild(String groundNameForBuilder) {
		this.groundNameForBuilder = groundNameForBuilder;
		return this;
	}

	public GroundBuilder groundMainAreaBuild(String groundMainAreaForBuilder) {
		this.groundMainAreaForBuilder = groundMainAreaForBuilder;
		return this;
	}

	public GroundBuilder groundAddressBuild(String groundAddressForBuilder) {
		this.groundAddressForBuilder = groundAddressForBuilder;
		return this;
	}

	public GroundBuilder groundLocationLinkBuild(String groundLocationLinkForBuilder) {
		this.groundLocationLinkForBuilder = groundLocationLinkForBuilder;
		return this;
	}

	public GroundBuilder districtBuild(String districtForBuilder) {
		this.districtForBuilder = districtForBuilder;
		return this;
	}

	public GroundBuilder groundImagesBuild(List<String> groundImagesForBuilder) {
		this.groundImagesForBuilder = groundImagesForBuilder;
		return this;
	}

	public GroundBuilder sportsAvailableBuild(List<String> sportsAvailableForBuilder) {
		this.sportsAvailableForBuilder = sportsAvailableForBuilder;
		return this;
	}

	public GroundBuilder startTimeBuild(LocalTime startTimeForBuilder) {
		this.startTimeForBuilder = startTimeForBuilder;
		return this;
	}

	public GroundBuilder endTimeBuild(LocalTime endTimeForBuilder) {
		this.endTimeForBuilder = endTimeForBuilder;
		return this;
	}

	public GroundBuilder groundRulesBuild(String groundRulesForBuilder) {
		this.groundRulesForBuilder = groundRulesForBuilder;
		return this;
	}

	public GroundBuilder priceBuild(double priceForBuilder) {
		this.priceForBuilder = priceForBuilder;
		return this;
	}

	public GroundBuilder increasingPriceForExtraHoursBuild(double increasingPriceForExtraHoursForBuilder) {
		this.increasingPriceForExtraHoursForBuilder = increasingPriceForExtraHoursForBuilder;
		return this;
	}

	public GroundBuilder courtsAvailableBuild(int courtsAvailableForBuilder) {
		this.courtsAvailableForBuilder = courtsAvailableForBuilder;
		return this;
	}
	
	public GroundBuilder groundOwnerIdBuild(int groundOwnerIdForBuilder) {
		this.groundOwnerIdForBuilder = groundOwnerIdForBuilder;
		return this;
	}

	public Ground build() {
        Ground ground = new Ground();
       ground.setgroundId(groundIdForBuilder);
        ground.setGroundName(groundNameForBuilder);
        ground.setGroundMainArea(groundMainAreaForBuilder);
        ground.setGroundAddress(groundAddressForBuilder);
        ground.setGroundLocationLink(groundLocationLinkForBuilder);
        ground.setDistrict(districtForBuilder);
        ground.setGroundImages(groundImagesForBuilder);
        ground.setSportsAvailable(sportsAvailableForBuilder);
        ground.setStartTime(startTimeForBuilder);
        ground.setEndTime(endTimeForBuilder);
        ground.setGroundRules(groundRulesForBuilder);
        ground.setPrice(priceForBuilder);
        ground.setIncreasingPriceForExtraHours(increasingPriceForExtraHoursForBuilder);
        ground.setCourtsAvailable(courtsAvailableForBuilder);
        ground.setGroundOwnerId(groundOwnerIdForBuilder);
        
        return ground;
    }
	



}