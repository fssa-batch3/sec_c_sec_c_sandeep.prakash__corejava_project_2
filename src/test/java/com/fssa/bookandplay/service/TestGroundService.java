package com.fssa.bookandplay.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.bookandplay.dao.GroundDao;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.Ground;
import com.fssa.bookandplay.validator.GroundValidator;

public class TestGroundService {

	

	@Test
	public void testAddGround() throws DAOException, SQLException {
		Ground ground=getGround();
		GroundService groundService=getGroundService();
		Assertions.assertTrue(groundService.addGround(ground));
		
	}

	
	@Test
	public void testUpdateGround() throws DAOException, SQLException {
		Ground ground=getGround2();
		GroundService groundService=getGroundService();
		Assertions.assertTrue(groundService.updateGround(ground));
		
	}
	@Test
	public void testDeleteGround() throws DAOException, SQLException {
		Ground ground=getGround();
		GroundService groundService=getGroundService();
		Assertions.assertTrue(groundService.deleteGround(2));
		
	}
	public Ground getGround2() {
		List<String> validImages = Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg");
		List<String> validsports = Arrays.asList("cricket", "football", "tennis");
		LocalTime startTime = LocalTime.of(10, 30); // 10:00 AM
		LocalTime endTime = LocalTime.of(11, 30);   // 5:00 PM
		Ground ground=new Ground(4,"sample name", "sample main area", "sampleaddress", "http://google.com", "sampledistrict", validImages, validsports, startTime, endTime, "samplerules", 200, 200, 3);
		return ground;
		
	}
	
	
	public Ground getGround() {
		List<String> validImages = Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg");
		List<String> validsports = Arrays.asList("cricket", "football", "tennis");
		LocalTime startTime = LocalTime.of(10, 30); // 10:00 AM
		LocalTime endTime = LocalTime.of(11, 30);   // 5:00 PM
		Ground ground=new Ground("sample name", "sample main area", "sampleaddress", "http://google.com", "sampledistrict", validImages, validsports, startTime, endTime, "samplerules", 200, 200, 3);
		return ground;
		
	}
	public GroundService getGroundService() {
		
		GroundValidator groundValidator=new GroundValidator();
		GroundDao groundDao=new GroundDao();
		GroundService groundService =new GroundService(groundValidator, groundDao);
		return groundService;
		
	}
	
	@Test
	public void testGetGroundDetail() throws DAOException, SQLException {
		Ground ground=getGround();
		GroundService groundService=getGroundService();
		Assertions.assertTrue(groundService.getGroundDetails());
		
	}
	
	
	

}
