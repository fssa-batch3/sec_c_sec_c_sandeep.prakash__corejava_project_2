package com.fssa.bookandplay.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.bookandplay.dao.GroundDao;
import com.fssa.bookandplay.dao.GroundOwnerDao;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.exceptions.InvalidGroundDetailException;
import com.fssa.bookandplay.model.Ground;
import com.fssa.bookandplay.validator.GroundValidator;

/**
 * The GroundService class which calls validator and dao class.
 */
public class GroundService {

	public GroundService() {
		// private constructor
	}

	GroundValidator groundValidator = new GroundValidator();
	GroundDao groundDao = new GroundDao();
	GroundOwnerDao gwdao=new GroundOwnerDao();


	/**
	 * The add ground
	 */
	public boolean addGround(Ground ground) throws DAOException, SQLException,InvalidGroundDetailException {
		if (groundValidator.validate(ground) && (!groundDao.isGroundOwnerExist(ground.getGroundOwnerId()))) {
				groundDao.addGround(ground);
				return true;

		}
	
		return false;
	

	}

	/**
	 * The update ground
	 */
	public boolean updateGround(Ground ground) throws DAOException, SQLException,InvalidGroundDetailException {
		if (groundValidator.validate(ground)) {
			groundDao.updateGround(ground);
		}
		return true;

	}

	/**
	 * The delete ground
	 */
	public boolean deleteGround(int groundId) throws DAOException, SQLException,InvalidGroundDetailException {

		if (groundValidator.groundIdValidator(groundId)) {
			groundDao.deleteGround(groundId);
		}
		return true;
	}

	/**
	 * The get the ground details
	 */
	public List<Ground> getGroundDetails() throws DAOException {

		return groundDao.getAllGround();

	}
	
	/**
	 * The get the ground details
	 */
	public Ground getGroundById(int id) throws DAOException {

		return groundDao.getGroundById(id);

	}
	/**
	 * The get the ground details
	 */
	
	public Ground getGroundByOwnerId(int id) throws DAOException {

		return groundDao.getGroundByOwnerId(id);

	}
	


}
