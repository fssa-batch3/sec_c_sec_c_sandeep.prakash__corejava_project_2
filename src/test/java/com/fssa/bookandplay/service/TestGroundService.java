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
	GroundService groundService=new GroundService();
	

	@Test
	void testAddGround() throws DAOException, SQLException {
		Ground ground = getGround2();

		Assertions.assertTrue(groundService.addGround(ground));

	}

	@Test
	void testUpdateGround() throws DAOException, SQLException {
		Ground ground = getGround2();

		Assertions.assertTrue(groundService.updateGround(ground));

	}

	@Test
	void testDeleteGround() throws DAOException, SQLException {

		Assertions.assertTrue(groundService.deleteGround(114));

	}

	 Ground getGround2() {
		 List<String> validImages = Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg");
			List<String> validsports = Arrays.asList("cricket", "football", "tennis");
			LocalTime startTime = LocalTime.of(10, 30); // 10:00 AM
			LocalTime endTime = LocalTime.of(11, 30);   // 5:00 PM
			//Ground ground=new Ground(1,"samplename", "samplemainarea", "sampleaddress", "http://google.com", "sampledistrict", validImages, validsports, startTime, endTime, "samplerules", 200, 200, 3);
			Ground ground1=new GroundBuilder()
					.groundIdBuild(29)
				  	  .groundNameBuild("Dns ground")
				    .groundMainAreaBuild("Main Area")
				    .groundAddressBuild("123ExampleStreet")
				    .groundLocationLinkBuild("https://maps.example.com")
				    .districtBuild("SampleDistrict")
				    .groundImagesBuild(validImages)
				    .sportsAvailableBuild(validsports)
				    .startTimeBuild(startTime)
				    .endTimeBuild(endTime)
				    .groundRulesBuild("Nosmokingallowed")// TODO increase the length size
				    .priceBuild(170)
				    .increasingPriceForExtraHoursBuild(200)
				    .courtsAvailableBuild(2)
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

		Assertions.assertTrue(groundService.getGroundDetails());

	}

}
