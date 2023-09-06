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

	UserValidator ul = new UserValidator();
	UserDao ud = new UserDao();

	/**
	 * The add user
	 */
	public boolean addUserPlayer(User user) throws DAOException, SQLException {
		if (ul.validateUserPlayer(user)&& !ud.isUserEmailExist(user.getEmail())) {
			ud.addUser(user);
			return true;
		}
	return false;

	}

	/**
	 * The add user
	 */
	public boolean addUserOnly(User user) throws DAOException, SQLException {
		if (ul.validateUser(user)&& !ud.isUserEmailExist(user.getEmail())) {
			ud.addUser(user);
			return true;
		}
		return false;

	}

	/**
	 * The update user
	 */
	public boolean updateUserPlayer(User user) throws DAOException, SQLException {
		if (ul.validateUserPlayer(user)) {
			ud.updateUser(user);
		}
		return true;

	}

	public boolean updateUserOnly(User user) throws DAOException, SQLException {
		if (ul.validateUser(user)) {
			ud.updateUser(user);
		}
		return true;

	}

	/**
	 * The get the user details
	 */
	public boolean getUserDetails() throws DAOException, SQLException {
		ud.getAllUser();
		return true;

	}
	
	
	
	public User getUseremailpass(String email, String password) throws DAOException, SQLException {

		return 	ud.getUserByEmailAndPassword(email, password);

	}
	
}
