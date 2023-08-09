package com.fssa.bookandplay.service;

import java.sql.SQLException;
import com.fssa.bookandplay.dao.GroundDao;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.Ground;
import com.fssa.bookandplay.validator.GroundValidator;
/**
 * The GroundService class which calls validator and dao class. */
public class GroundService {
	private GroundValidator groundvalidator;
	private  GroundDao groundDao;

	public GroundService(GroundValidator groundvalidator, GroundDao groundDao) {
		this.groundvalidator = groundvalidator;
		this.groundDao = groundDao;
	}

	public GroundService() {
		
	}
	/**
	 * The  add ground */
	public boolean addGround(Ground ground) throws DAOException, SQLException {
		if (groundvalidator.validate(ground)) {
			groundDao.addGround(ground);
		}
		return true; 

	}
	/**
	 * The  update ground */
	public boolean updateGround(Ground ground) throws DAOException, SQLException {
		if (groundvalidator.validate(ground)) {
			 groundDao.updateGround(ground);
		}
		return true;

	}

	/**
	 * The  delete ground */
	public boolean deleteGround(int groundId) throws DAOException, SQLException {

		GroundValidator ground = new GroundValidator();
		if (ground.groundIdValidator(groundId)) {
			groundDao.deleteGround(groundId);
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

	

}
