package com.fssa.bookandplay.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fssa.bookandplay.errors.GroundOwnerDaoErrors;
import com.fssa.bookandplay.errors.UserDaoErrors;
import com.fssa.bookandplay.errors.UserValidationErrors;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.exceptions.InvalidUserDetailException;
import com.fssa.bookandplay.model.GroundOwner;
import com.fssa.bookandplay.model.User;
import com.fssa.bookandplay.util.ConnectionUtil;
import com.fssa.bookandplay.util.Logger;

public class UserDao {

	public UserDao() {
		// private constructor
	}

	public  String hashPassword(String password) throws InvalidUserDetailException {
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
	 * The Logger Class is created
	 */
	static Logger logger = new Logger();

	/**
	 * The AddGround Method insert ground details in the database.
	 */
	public  boolean addUser(User user) throws DAOException, SQLException {

		LocalTime startTime1 = user.getTimingAvailFrom();
		Time startTimeTs1;
		if(startTime1!=null) {
		 startTimeTs1 = Time.valueOf(startTime1);
		}
		else {
			 startTimeTs1 = null;
		}
		
		LocalTime endTime1 = user.getTimingAvailTo();
		Time endTimeTs1;
		if(endTime1!=null) {
		 endTimeTs1 = Time.valueOf(endTime1);
		}
		else {
			 endTimeTs1 =null;
		}

		/**
		 * The Query for calling insertground from sql
		 */
		String storedProcedureCall = "{call InsertUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)}";

		/**
		 * Getting the ground details and inserting in sql
		 */
		try (Connection con = ConnectionUtil.getConnection()) {

			try (CallableStatement callableStatement = con.prepareCall(storedProcedureCall)) {

				callableStatement.setString(1, user.getFirstName());
				callableStatement.setString(2, user.getLastName());
				callableStatement.setString(3, user.getEmail());
				callableStatement.setLong(4, user.getPhoneNumber());
				callableStatement.setString(5, hashPassword(user.getPassword()));
				callableStatement.setString(6, user.getImage());
				callableStatement.setBoolean(7, user.getPlayerStatus());
				//callableStatement.setString(8, user.getDisplayName());
				callableStatement.setInt(8, user.getAge());
				callableStatement.setString(9, user.getGender());
				callableStatement.setString(10, user.getLocation());
				callableStatement.setTime(11, startTimeTs1);
				callableStatement.setTime(12, endTimeTs1);
				callableStatement.setString(13, user.getAbout());
				if(user.getKnownSports()!=null) {
				String sportsKnownStr = String.join(",", user.getKnownSports());
				callableStatement.setString(14, sportsKnownStr);
				}
				else 
				{
					callableStatement.setString(14, null);
				}

				callableStatement.execute();

			}
		} catch (SQLException e) {

			throw new DAOException(UserDaoErrors.INSERT_USER_DETAILS_ERROR);
		}

		return true;

	}

	/**
	 * The AddGround Method insert ground details in the database.
	 */
	public  boolean updateUser(User user) throws DAOException, SQLException {

		/**
		 * If the ground id is invalid
		 */
		if (user.getUserId() <= 0) {
			throw new InvalidUserDetailException(UserValidationErrors.INVALID_USER_ID);

		}
		LocalTime startTime2 = user.getTimingAvailFrom();
		Time startTimeTs2;
		if(startTime2!=null) {
		 startTimeTs2 = Time.valueOf(startTime2);
		}
		else {
			 startTimeTs2 = null;
		}
		
		LocalTime endTime2 = user.getTimingAvailTo();
		Time endTimeTs2;
		if(endTime2!=null) {
		 endTimeTs2 = Time.valueOf(endTime2);
		}
		else {
			 endTimeTs2 =null;
		}
		/**
		 * The Query for calling insertground from sql
		 */
		String storedProcedureCall = "{call UpdateUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)}";

		/**
		 * Getting the ground details and inserting in sql
		 */
		try (Connection con = ConnectionUtil.getConnection()) {

			try (CallableStatement callableStatement2 = con.prepareCall(storedProcedureCall)) {
				callableStatement2.setInt(1, user.getUserId());
				callableStatement2.setString(2, user.getFirstName());
				callableStatement2.setString(3, user.getLastName());
				//callableStatement2.setString(4, user.getEmail());
				callableStatement2.setLong(4, user.getPhoneNumber());
				//callableStatement2.setString(6, hashPassword(user.getPassword()));
				callableStatement2.setString(5, user.getImage());
				callableStatement2.setBoolean(6, user.getPlayerStatus());
				//callableStatement2.setString(9, user.getDisplayName());
				callableStatement2.setLong(7, user.getAge());
				callableStatement2.setString(8, user.getGender());
				callableStatement2.setString(9, user.getLocation());
				callableStatement2.setTime(10, startTimeTs2);
				callableStatement2.setTime(11, endTimeTs2);
				callableStatement2.setString(12, user.getAbout());
				if(user.getKnownSports()!=null) {
					String sportsKnownStr = String.join(",", user.getKnownSports());
					callableStatement2.setString(13, sportsKnownStr);
					}
					else 
					{
						callableStatement2.setString(13, null);
					}
				callableStatement2.execute();

			}
		} catch (SQLException e) {

			throw new DAOException(UserDaoErrors.UPDATE_USER_DETAILS_ERROR);
		}

		return true;

	}

	/**
	 * The getAllGround Method get all ground details from the database
	 */
	public  boolean getAllUser() throws DAOException, SQLException {

		List<User> userList = new ArrayList<>();

		/**
		 * The Query for selecting all grounddetails from all the table
		 */

		String selectQuery = "SELECT u.*, "
				+ "(SELECT GROUP_CONCAT(sportName) FROM UserSportSKnown sa WHERE sa.userId = u.id) AS sportNames "
				+ "FROM User u";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				try (ResultSet rs = preparedStatement.executeQuery()) {

					while (rs.next()) {
						int userId = rs.getInt("id");

						User user = new User();
						
						Time startTimeSql = rs.getTime("timing_from");
						LocalTime startTime = null;
						if (startTimeSql != null) {
						    startTime = startTimeSql.toLocalTime();
						}

						Time endTimeSql = rs.getTime("timing_to");
						LocalTime endTime = null;
						if (endTimeSql != null) {
						    endTime = endTimeSql.toLocalTime();
						}


						user.setUserId(userId);
						user.setFirstName(rs.getString("first_name"));
						user.setLastName(rs.getString("last_name"));
						user.setEmail(rs.getString("email"));
						user.setPhoneNumber(rs.getLong("phone_number"));
						user.setPassword(rs.getString("password"));
						user.setPlayerStatus(rs.getBoolean("playerstatus"));
						//user.setDisplayName(rs.getString("display_name"));
						user.setAge(rs.getInt("age"));
						user.setGender(rs.getString("gender"));
						user.setLocation(rs.getString("location"));
						user.setTimingAvailFrom(startTime);
						user.setTimingAvailTo(endTime);
						user.setAbout(rs.getString("about"));

						String sportNamesdata = rs.getString("sportNames");
						if (sportNamesdata != null) {
							String[] sportNames = sportNamesdata.split(",");
							user.setKnownSports(Arrays.asList(sportNames));
						} else {
							user.setKnownSports(new ArrayList<>());
						}
						logger.info(rs.getString("first_name"));
						logger.info(rs.getString("last_name"));
						logger.info(rs.getString("email"));
						logger.info(rs.getString("phone_number"));
						logger.info(rs.getString("password"));
						logger.info(rs.getBoolean("playerstatus"));
						//logger.info(rs.getString("display_name"));
						logger.info(rs.getInt("age"));
						logger.info(rs.getString("gender"));
						logger.info(rs.getString("location"));

						logger.info(rs.getTime("timing_from"));
						logger.info(rs.getTime("timing_to"));
						// add ground object
						userList.add(user);
					}
				}
			}

		}

		catch (SQLException e) {
			throw new DAOException(UserDaoErrors.READ_USER_DETAILS_ERROR);
		}

		return true;
	}

	

	
	public boolean isUserEmailExist(String email) throws SQLException, DAOException {

		/**
		 * The Query for calling insertground from sql
		 */
		 boolean exists = false;
		 String query = "SELECT COUNT(*) FROM User WHERE email = ?";
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

			throw new DAOException(UserDaoErrors.READ_USER_DETAILS_ERROR);
		}

			 return exists;


}
	public User getUserByEmailAndPassword(String email, String enteredPassword) throws SQLException, DAOException {
	    User user = null;
	    String selectQuery = "SELECT u.*, " +
                "(SELECT GROUP_CONCAT(sportName) FROM UserSportSKnown sa WHERE sa.userId = u.id) AS sportNames " +
                "FROM User u " +
                "WHERE u.email = ?";
	    
	    
	    
	    try (Connection con = ConnectionUtil.getConnection()) {
	        try (PreparedStatement pst = con.prepareStatement(selectQuery)) {
	            pst.setString(1, email);
	            
	            try (ResultSet rs = pst.executeQuery()) {
	                if (rs.next()) {
	                	
	                	Time startTimeSql = rs.getTime("timing_from");
						LocalTime startTime = null;
						if (startTimeSql != null) {
						    startTime = startTimeSql.toLocalTime();
						}

						Time endTimeSql = rs.getTime("timing_to");
						LocalTime endTime = null;
						if (endTimeSql != null) {
						    endTime = endTimeSql.toLocalTime();
						}

	                    String storedHashedPassword = rs.getString("password");
	                  
	                    String enteredHashedPassword = hashPassword(enteredPassword);
	                  
	                    if (storedHashedPassword.equals(enteredHashedPassword)) {
	                        user = new User();
	                        user.setUserId(rs.getInt("id"));
	                        user.setEmail(rs.getString("email"));
	                        user.setPassword(rs.getString("password"));
	                        user.setPlayerStatus(rs.getBoolean("playerstatus"));
	                        user.setFirstName(rs.getString("first_name"));
							user.setLastName(rs.getString("last_name"));
						user.setPhoneNumber(rs.getLong("phone_number"));
							user.setPassword(rs.getString("password"));
							user.setAge(rs.getInt("age"));
							user.setGender(rs.getString("gender"));
							user.setLocation(rs.getString("location"));
							user.setTimingAvailFrom(startTime);
							user.setTimingAvailTo(endTime);
							user.setAbout(rs.getString("about"));
							
							String sportNamesdata2 = rs.getString("sportNames");
							if (sportNamesdata2 != null) {
								String[] sportNames2 = sportNamesdata2.split(",");
								user.setKnownSports(Arrays.asList(sportNames2));
							} else {
								user.setKnownSports(new ArrayList<>());
							}
	                       
	                    } else {
	                        throw new DAOException(UserDaoErrors.READ_USER_EMAIL_ERROR);
	                    }
	                } else {
	                    throw new DAOException(UserDaoErrors.READ_USER_PASSWORD_ERROR);
	                }
	            }
	        }
	    } catch (SQLException e) {
	      
	        e.printStackTrace();
	        
	        throw new DAOException(UserDaoErrors.READ_USER_DETAILS_ERROR);
	    }
	    
	    return user;
	}

}
