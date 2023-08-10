package com.fssa.bookandplay.service;

import java.sql.SQLException;
import com.fssa.bookandplay.dao.GroundDao;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.Ground;
import com.fssa.bookandplay.validator.GroundValidator;

/**
 * The GroundService class which calls validator and dao class.
 */
public class GroundService {


	private GroundService() {
		// private constructor
	}

	/**
	 * The add ground
	 */
	public static boolean addGround(Ground ground) throws DAOException, SQLException {
		if (GroundValidator.validate(ground)) {
			GroundDao.addGround(ground);
		}
		return true;

	}

	/**
	 * The update ground
	 */
	public static boolean updateGround(Ground ground) throws DAOException, SQLException {
		if (GroundValidator.validate(ground)) {
			GroundDao.updateGround(ground);
		}
		return true;

	}

	/**
	 * The delete ground
	 */
	public static boolean deleteGround(int groundId) throws DAOException, SQLException {

		if (GroundValidator.groundIdValidator(groundId)) {
			GroundDao.deleteGround(groundId);
		}
		return true;
	}

	/**
	 * The get the ground details
	 */
	public static boolean getGroundDetails() throws DAOException, SQLException {
//		GroundDao grounddao = new GroundDao();
		GroundDao.getAllGround();
		return true;

	}

}
