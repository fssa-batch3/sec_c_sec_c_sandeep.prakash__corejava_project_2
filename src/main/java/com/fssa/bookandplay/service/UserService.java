package com.fssa.bookandplay.service;

import java.sql.SQLException;

import com.fssa.bookandplay.dao.UserDao;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.User;
import com.fssa.bookandplay.validator.UserValidator;

public class UserService {

	public UserService() {
		// private constructor
	}
	UserValidator ul=new UserValidator();

	/**
	 * The add user
	 */
	public  boolean addUserPlayer(User user) throws DAOException, SQLException {
		if (ul.validateUserPlayer(user)) {
			UserDao.addUser(user);
		}
		return true;

	}
	/**
	 * The add user
	 */
	public  boolean addUserOnly(User user) throws DAOException, SQLException {
		if (ul.validateUser(user)) {
			UserDao.addUser(user);
		}
		return true;

	}
	/**
	 * The update user
	 */
	public  boolean updateUserPlayer(User user) throws DAOException, SQLException {
		if (ul.validateUserPlayer(user)) {
			UserDao.updateUser(user);
		}
		return true;

	}
	
	public  boolean updateUserOnly(User user) throws DAOException, SQLException {
		if (ul.validateUser(user)) {
			UserDao.updateUser(user);
		}
		return true;

	}

	/**
	 * The get the user details
	 */
	public  boolean getUserDetails() throws DAOException, SQLException {
		UserDao.getAllUser();
		return true;

	}
}
