package com.fssa.bookandplay.service;

import java.sql.SQLException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fssa.bookandplay.dao.GroundDao;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.Ground;
import com.fssa.bookandplay.validator.GroundValidator;
/**
 * The GroundService class which calls validator and dao class. */
public class GroundService {
	private GroundValidator groundvalidator;
	private GroundDao groundDao;

	public GroundService(GroundValidator groundvalidator, GroundDao groundDao) {
		this.groundvalidator = groundvalidator;
		this.groundDao = groundDao;
	}

	public GroundService() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * The  add ground */
	public boolean addGround(Ground ground) throws DAOException, SQLException {
		if (this.groundvalidator.validate(ground)) {
			this.groundDao.addGround(ground);
		}
		return true; 

	}
	/**
	 * The  update ground */
	public boolean updateGround(Ground ground) throws DAOException, SQLException {
		if (this.groundvalidator.validate(ground)) {
			 this.groundDao.updateGround(ground);
		}
		return true;

	}

	/**
	 * The  delete ground */
	public boolean deleteGround(int groundId) throws DAOException, SQLException {

		GroundValidator ground = new GroundValidator();
		if (ground.groundIdValidator(groundId)) {
			this.groundDao.deleteGround(groundId);
		}
		return true;
	}
	/**
	 * The  get the  ground details */
	public boolean getGroundDetails() throws DAOException, SQLException {
		GroundDao grounddao = new GroundDao();
		grounddao.getAllGround();
		return true;

	}

	public static void main(String[] args) throws DAOException, SQLException {
//
//		List<String> groundImages = new ArrayList<>();
//		groundImages.add("https://example.com/image1.jpg");
//		groundImages.add("https://example.com/image1.jpg");
//
//		List<String> sportsAvailable = new ArrayList<>();
//		sportsAvailable.add("Football");
//		sportsAvailable.add("Cricket");
//
//		// Create LocalDateTime objects for startTime and endTime
//		LocalDateTime startTime = LocalDateTime.of(2023, 7, 26, 12, 0);
//		LocalDateTime endTime = LocalDateTime.of(2023, 7, 26, 12, 0);
//
//		// Create a Ground object using the constructor
//		Ground ground1 = new Ground(5,"dondeeo", "danny", "dannysannssmkmdssd", "http://google.com", "dannyDistr",
//				groundImages, sportsAvailable, startTime, endTime, "Somerulesfortheground", 200, 300, 3);
//		//int  ground1=5;
//		// Call the addGround method to insert data
//		GroundDao groundDao = new GroundDao();
//		GroundValidator groundValidate = new GroundValidator();
//		
//
//		// Create a GroundService object and pass the GroundValidator and GroundDao
//		// instances to the constructor
//		GroundService data = new GroundService(groundValidate, groundDao);
////		GroundService data=new GroundService();
//		boolean dataInserted = data.addGround(ground1);
//
//		if (dataInserted) {
//			System.out.println("Data inserted successfully.");
//		} else {
//			System.out.println("Failed to insert data.");
//		}

	}

}
