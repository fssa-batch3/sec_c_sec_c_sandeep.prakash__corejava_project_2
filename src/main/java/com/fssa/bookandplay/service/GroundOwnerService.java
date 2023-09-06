package com.fssa.bookandplay.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.bookandplay.dao.GroundOwnerDao;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.GroundOwner;
import com.fssa.bookandplay.validator.GroundOwnerDetailValidator;

public class GroundOwnerService {
	

	public GroundOwnerService() {
		// private constructor
	}
	
	
	GroundOwnerDetailValidator groundOwnerVal=new GroundOwnerDetailValidator();
	GroundOwnerDao groundOwnerDao =new GroundOwnerDao();
	
	
	
	
	/**
	 * The add user
	 */
	public boolean addgroundOwner(GroundOwner groundOwner) throws DAOException, SQLException {
		if (groundOwnerVal.validateGroundOwner(groundOwner) && !groundOwnerDao.isEmailExist(groundOwner.getEmail()) ) {
			groundOwnerDao.addGroundOwner(groundOwner);
			return true;
		}
		return false;

	}

	/**
	 * The update user
	 */
	public boolean updategroundOwner(GroundOwner groundOwner) throws DAOException, SQLException {
		if (groundOwnerVal.validateGroundOwner(groundOwner)) {
			groundOwnerDao.updateGroundOwner(groundOwner);
		}
		return true;

	}
	
	
	/**
	 * The get the user details
	 */
	public List<GroundOwner> getgroundOwnerDetails() throws DAOException, SQLException {

		return 	groundOwnerDao.getAllGroundOwner();

	}
	
	
	public GroundOwner getgroundOwneremailpass(String email, String password) throws DAOException, SQLException {

		return 	groundOwnerDao.getGroundOwnerByEmailAndPassword(email, password);

	}
	
}
