package com.fssa.bookandplay.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.bookandplay.errors.GroundOwnerDaoErrors;
import com.fssa.bookandplay.errors.GroundOwnerDetailValidationErrors;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.exceptions.InvalidGroundOwnerDetailException;
import com.fssa.bookandplay.exceptions.InvalidUserDetailException;
import com.fssa.bookandplay.model.GroundOwner;
import com.fssa.bookandplay.util.ConnectionUtil;

public class GroundOwnerDao {

	public GroundOwnerDao() {
		// private constructor
	}

	
	static final String EMAIL="email";
	static final String PHONE_NO="phone_number";
	static final String PASSWORD= "password";
	public String hashPassword(String password) throws InvalidGroundOwnerDetailException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

			StringBuilder hashsb = new StringBuilder();
			for (byte b : hashBytes) {
				hashsb.append(String.format("%02x", b));
			}

			return hashsb.toString();
		} catch (NoSuchAlgorithmException e) {

			throw new InvalidUserDetailException(e.getMessage());

		}
	}

	/**
	 * The AddGround Method insert ground details in the database.
	 */
	public boolean addGroundOwner(GroundOwner groundOwner) throws DAOException, SQLException {

		/**
		 * The Query for calling insertground from sql
		 */
		String insertQuery = "INSERT INTO GroundOwner (name, organisationName, email, phoneNumber, password, image) values (?, ?, ?, ?, ?, ?)";
		/**
		 * Getting the ground details and inserting in sql
		 */
		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(insertQuery)) {

				pst.setString(1, groundOwner.getName());
				pst.setString(2, groundOwner.getOrganisationName());
				pst.setString(3, groundOwner.getEmail());
				pst.setLong(4, groundOwner.getPhoneNumber());
				pst.setString(5, hashPassword(groundOwner.getPassword()));
				pst.setString(6, groundOwner.getImage());
				pst.executeUpdate();
			}
		} catch (SQLException e) {

			throw new DAOException(GroundOwnerDaoErrors.INSERT_GROUNDOWNER_DETAILS_ERROR);
		}

		return true;

	}

	public boolean updateGroundOwner(GroundOwner groundOwner) throws DAOException, SQLException {
		
		
		if (groundOwner.getGroundOwnerId() <= 0) {
			throw new InvalidGroundOwnerDetailException(GroundOwnerDetailValidationErrors.INVALID_GROUNDOWNER_ID);

		}


		/**
		 * The Query for calling insertground from sql
		 */
		String updateQuery = "UPDATE GroundOwner SET name = ?, organisationName = ?, phoneNumber = ?, image = ? WHERE id = ?";

		/**
		 * Getting the ground details and inserting in sql
		 */
		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(updateQuery)) {

				pst.setString(1, groundOwner.getName());
				pst.setString(2, groundOwner.getOrganisationName());

				pst.setLong(3, groundOwner.getPhoneNumber());
				
				pst.setString(4, groundOwner.getImage());
				pst.setInt(5, groundOwner.getGroundOwnerId());
				pst.executeUpdate();
			}
		} catch (SQLException e) {

			throw new DAOException(GroundOwnerDaoErrors.UPDATE_GROUNDOWNER_DETAILS_ERROR);
		}

		return true;

	}

	public List<GroundOwner> getAllGroundOwner() throws DAOException, SQLException {

		List<GroundOwner> groundOwners = new ArrayList<>();
		/**
		 * The Query for calling insertground from sql
		 */
		String selectQuery = "SELECT * FROM GroundOwner";
		/**
		 * Getting the ground details and inserting in sql
		 */
		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				try (ResultSet rs = preparedStatement.executeQuery()) {

					while (rs.next()) {
						GroundOwner go = new GroundOwner();
						int groundOwnerId = rs.getInt("id");
						String name = rs.getString("name");
						String organisationName = rs.getString("organisationName");
						String email = rs.getString(EMAIL);
						long phoneNumber = rs.getLong("phoneNumber");
						String password = rs.getString(PASSWORD);
						String image = rs.getString("image");
						go.setGroundOwnerId(groundOwnerId);
						go.setName(name);
						go.setOrganisationName(organisationName);
						go.setEmail(email);
						go.setPhoneNumber(phoneNumber);
						go.setPassword(password);
						go.setImage(image);

						// add ground object
						groundOwners.add(go);
					}
				}
			}

		} catch (SQLException e) {

			throw new DAOException(GroundOwnerDaoErrors.READ_GROUNDOWNER_DETAILS_ERROR);
		}

		return groundOwners;

	}
	
	
		
	
	
	public boolean isEmailExist(String email) throws SQLException, DAOException {

		/**
		 * The Query for calling insertground from sql
		 */
		 boolean exists = false;
		 String query = "SELECT COUNT(*) FROM GroundOwner WHERE email = ?";
		/**
		 * Getting the ground details and inserting in sql
		 */
		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, email);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
	                    int count = rs.getInt(1);
	                    exists = count > 0;
	                }
			}
		}
		}catch (SQLException e) {

			throw new DAOException(GroundOwnerDaoErrors.READ_GROUNDOWNER_EMAIL_ERROR);
		}

			 return exists;


}
	public GroundOwner getGroundOwnerByEmailAndPassword(String email, String enteredPassword) throws SQLException, DAOException {
	    GroundOwner go = null;
	    String query = "SELECT * FROM GroundOwner WHERE email = ?";
	    
	    try (Connection con = ConnectionUtil.getConnection()) {
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            pst.setString(1, email);
	            
	            try (ResultSet rs = pst.executeQuery()) {
	                if (rs.next()) {
	                    String storedHashedPassword = rs.getString(PASSWORD);
	                  
	                    String enteredHashedPassword = hashPassword(enteredPassword);
	                  
	                    if (storedHashedPassword.equals(enteredHashedPassword)) {
	                        go = new GroundOwner();
	                        go.setGroundOwnerId(rs.getInt("id"));
	                        go.setEmail(rs.getString(EMAIL));
	                        go.setPassword(rs.getString(PASSWORD));
	                        go.setName(rs.getString("name"));
	                        go.setOrganisationName(rs.getString("organisationName"));
	                        go.setPhoneNumber(rs.getLong("phoneNumber"));
	                       
	                    } else {
	                        throw new DAOException(GroundOwnerDaoErrors.READ_GROUNDOWNER_EMAIL_ERROR);
	                    }
	                } else {
	                    throw new DAOException(GroundOwnerDaoErrors.READ_GROUND_OWNER_PASS_ERROR);
	                }
	            }
	        }
	    } catch (SQLException e) {
	      
	    	/**e.printStackTrace();
	    	 * 
	    	 */
	        
	        throw new DAOException(GroundOwnerDaoErrors.READ_GROUNDOWNER_DETAILS_ERROR);
	    }
	    
	    return go;
	}

	
	

}