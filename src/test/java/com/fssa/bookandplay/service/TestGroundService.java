package com.fssa.bookandplay.service;

import java.sql.SQLException;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.bookandplay.builder.GroundBuilder;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.Ground;

class TestGroundService {

	@Test
	void testAddGround() throws DAOException, SQLException {
		Ground ground = getGround2();

		Assertions.assertTrue(GroundService.addGround(ground));

	}

	@Test
	void testUpdateGround() throws DAOException, SQLException {
		Ground ground = getGround2();

		Assertions.assertTrue(GroundService.updateGround(ground));

	}

	@Test
	void testDeleteGround() throws DAOException, SQLException {

		Assertions.assertTrue(GroundService.deleteGround(37));

	}

	 Ground getGround2() {
		 List<String> validImages = Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg");
			List<String> validsports = Arrays.asList("cricket", "football", "tennis");
			LocalTime startTime = LocalTime.of(10, 30); // 10:00 AM
			LocalTime endTime = LocalTime.of(11, 30);   // 5:00 PM
			//Ground ground=new Ground(1,"samplename", "samplemainarea", "sampleaddress", "http://google.com", "sampledistrict", validImages, validsports, startTime, endTime, "samplerules", 200, 200, 3);
			Ground ground1=new GroundBuilder()
					.groundId(1)
				  	  .groundName("ExampleGround")
				    .groundMainArea("Main Area")
				    .groundAddress("123ExampleStreet")
				    .groundLocationLink("https://maps.example.com")
				    .district("SampleDistrict")
				    .groundImages(validImages)
				    .sportsAvailable(validsports)
				    .startTime(startTime)
				    .endTime(endTime)
				    .groundRules("Nosmokingallowed")
				    .price(170)
				    .increasingPriceForExtraHours(200)
				    .courtsAvailable(2)
				    .build();
		
		return ground1;

	}

	 /**
	Ground getGround() {
		List<String> validImages = Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg");
		List<String> validsports = Arrays.asList("cricket", "football", "tennis");
		LocalTime startTime = LocalTime.of(10, 30); // 10:00 AM
		LocalTime endTime = LocalTime.of(11, 30); // 5:00 PM
		Ground ground = new Ground("sample name", "sample main area", "sampleaddress", "http://google.com",
				"sampledistrict", validImages, validsports, startTime, endTime, "samplerules", 200, 200, 3);
		return ground;

	}
	*/


	@Test
	void testGetGroundDetail() throws DAOException, SQLException {

		Assertions.assertTrue(GroundService.getGroundDetails());

	}

}
