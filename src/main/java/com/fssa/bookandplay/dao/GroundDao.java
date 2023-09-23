package com.fssa.bookandplay.dao;

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

import com.fssa.bookandplay.errors.GroundDaoErrors;
import com.fssa.bookandplay.errors.GroundOwnerDaoErrors;
import com.fssa.bookandplay.errors.GroundValidatorsErrors;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.exceptions.InvalidGroundDetailException;
import com.fssa.bookandplay.model.Ground;
import com.fssa.bookandplay.util.ConnectionUtil;
import com.fssa.bookandplay.util.Logger;

/**
 * The GroundDao class contains the crud operations for sql database
 */
public class GroundDao {

	public GroundDao() {
		// private constructor
	}
	static final String SELECT_G = "SELECT g.*, ";
	static final String SELECT_IMAGE_URLS_AND_SPORT_NAMES =
	        "(SELECT GROUP_CONCAT(imageUrl) FROM GroundImages gi WHERE gi.groundId = g.id) AS imageUrls, " +
	        "(SELECT GROUP_CONCAT(sportName) FROM SportsAvailable sa WHERE sa.groundId = g.id) AS sportNames ";
	static final String FROM_GROUND = "FROM Ground g ";
	  static final String COLUMN_GROUND_NAME = "groundName";
	  
	     static final String COLUMN_GROUND_MAIN_AREA = "groundMainArea";
	     static final String COLUMN_GROUND_ADDRESS = "groundAddress";
	     static final String COLUMN_GROUND_LOCATION_LINK = "groundLocationLink";
	     static final String COLUMN_DISTRICT = "district";
	     static final String COLUMN_START_TIME = "startTime";
	     static final String COLUMN_END_TIME = "endTime";
	     static final String COLUMN_GROUND_RULES = "groundRules";
	     static final String COLUMN_PRICE = "price";
	     static final String COLUMN_INCREASING_PRICE = "increasingPriceForExtraHours";
	     static final String COLUMN_COURTS_AVAILABLE = "courtsAvailable";
	      static final String COLUMN_IMAGE_URLS = "imageUrls";
	      static final String COLUMN_SPORTS = "sportNames";
	/**
	 * The Logger Class is created
	 */
	static Logger logger = new Logger();

	/**
	 * The AddGround Method insert ground details in the database.
	 */
	public boolean addGround(Ground ground) throws DAOException, SQLException {

		LocalTime startTime1 = ground.getStartTime();
		Time startTimeTs1 = Time.valueOf(startTime1);

		LocalTime endTime1 = ground.getEndTime();
		Time endTimeTs1 = Time.valueOf(endTime1);

		/**
		 * The Query for calling insertground from sql
		 */
		String storedProcedureCall = "{call InsertGround(?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?)}";

		/**
		 * Getting the ground details and inserting in sql
		 */
		try (Connection con = ConnectionUtil.getConnection()) {

			try (CallableStatement callableStatement = con.prepareCall(storedProcedureCall)) {

				callableStatement.setString(1, ground.getGroundName());
				callableStatement.setString(2, ground.getGroundMainArea());
				callableStatement.setString(3, ground.getGroundAddress());
				callableStatement.setString(4, ground.getGroundLocationLink());
				callableStatement.setString(5, ground.getDistrict());
				callableStatement.setTime(6, startTimeTs1);
				callableStatement.setTime(7, endTimeTs1);
				callableStatement.setString(8, ground.getGroundRules());
				callableStatement.setDouble(9, ground.getPrice());
				callableStatement.setInt(10, ground.getGroundOwnerId());
				callableStatement.setDouble(11, ground.getIncreasingPriceForExtraHours());
				callableStatement.setInt(12, ground.getCourtsAvailable());

				String groundImagesStr = String.join(",", ground.getGroundImages());
				String sportsAvailableStr = String.join(",", ground.getSportsAvailable());

				callableStatement.setString(13, groundImagesStr);
				callableStatement.setString(14, sportsAvailableStr);
				callableStatement.execute();

			}
		} catch (SQLException e) {

			throw new DAOException(GroundDaoErrors.INSERT_GROUND_DETAILS_ERROR);
		}

		return true;

	}

	/**
	 * The UpdateGround Method Update ground details in the database
	 */
	public boolean updateGround(Ground ground) throws DAOException, SQLException {

		/**
		 * If the ground id is invalid
		 */
		if (ground.getGroundId() <= 0) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_PRODUCT_ID);

		}

		LocalTime startTime2 = ground.getStartTime();
		Time startTimeTs2 = Time.valueOf(startTime2);

		LocalTime endTime2 = ground.getEndTime();
		Time endTimeTs2 = Time.valueOf(endTime2);

		/**
		 * The Query for calling UpdateGround from sql
		 */

		String storedProcedureCall = "{call UpdateGround(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (CallableStatement callableStatement2 = con.prepareCall(storedProcedureCall)) {

				callableStatement2.setInt(1, ground.getGroundId());
				callableStatement2.setString(2, ground.getGroundName());
				callableStatement2.setString(3, ground.getGroundMainArea());
				callableStatement2.setString(4, ground.getGroundAddress());
				callableStatement2.setTime(5, startTimeTs2);
				callableStatement2.setTime(6, endTimeTs2);
				callableStatement2.setString(7, ground.getGroundRules());
				callableStatement2.setDouble(8, ground.getPrice());
				callableStatement2.setDouble(9, ground.getIncreasingPriceForExtraHours());
				callableStatement2.setInt(10, ground.getCourtsAvailable());

				String groundImagesStr = String.join(",", ground.getGroundImages());
				String sportsAvailableStr = String.join(",", ground.getSportsAvailable());

				callableStatement2.setString(11, groundImagesStr);
				callableStatement2.setString(12, sportsAvailableStr);

				callableStatement2.execute();

			}
		} catch (SQLException e) {

			throw new DAOException(GroundDaoErrors.UPDATE_GROUND_DETAILS_ERROR);
		}

		return true;

	}

	/**
	 * The DeleteGround Method Delete ground details in the database
	 */

	public boolean deleteGround(int groundId) throws DAOException, SQLException {
		/**
		 * If the ground id is invalid
		 */
		if (groundId <= 0) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_PRODUCT_ID);

		}

		/**
		 * The Query for calling DeleteGround from sql
		 */

		String storedProcedureCall = "{call DeleteGround(?)}";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (CallableStatement callableStatement3 = con.prepareCall(storedProcedureCall)) {

				callableStatement3.setInt(1, groundId);
				callableStatement3.execute();

			}
		} catch (SQLException e) {

			throw new DAOException(GroundDaoErrors.DELETE_GROUND_DETAILS_ERROR);
		}

		return true;

	}

	/**
	 * The getAllGround Method get all ground details from the database
	 */
	public List<Ground> getAllGround() throws DAOException {

		List<Ground> groundList = new ArrayList<>();

		/**
		 * The Query for selecting all grounddetails from all the table
		 */
		 String selectQuery = SELECT_G +
			        SELECT_IMAGE_URLS_AND_SPORT_NAMES +
			        FROM_GROUND +
			        "WHERE g.groundStatus = 1";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				try (ResultSet rs = preparedStatement.executeQuery()) {

					while (rs.next()) {
						//int groundId = rs.getInt("id");

						Ground ground1 = new Ground();
						Time startTimeSql3 = rs.getTime(COLUMN_START_TIME);
						LocalTime startTime3 = startTimeSql3.toLocalTime();
						Time endTimeSql3 = rs.getTime(COLUMN_END_TIME);
						LocalTime endTime3= endTimeSql3.toLocalTime();
						ground1.setGroundId(rs.getInt("id"));
						ground1.setGroundName(rs.getString(COLUMN_GROUND_NAME));
						ground1.setGroundMainArea(rs.getString(COLUMN_GROUND_MAIN_AREA));
						ground1.setGroundAddress(rs.getString(COLUMN_GROUND_ADDRESS));
						ground1.setGroundLocationLink(rs.getString(COLUMN_GROUND_LOCATION_LINK));
						ground1.setDistrict(rs.getString(COLUMN_DISTRICT));
						ground1.setStartTime(startTime3);
						ground1.setEndTime(endTime3);
						ground1.setGroundRules(rs.getString(COLUMN_GROUND_RULES));
						ground1.setPrice(rs.getDouble(COLUMN_PRICE));
						ground1.setIncreasingPriceForExtraHours(rs.getDouble(COLUMN_INCREASING_PRICE));
						ground1.setCourtsAvailable(rs.getInt(COLUMN_COURTS_AVAILABLE));
						
						String imageUrlsdata = rs.getString(COLUMN_IMAGE_URLS);
						if (imageUrlsdata != null) {
							String[] imageUrl = imageUrlsdata.split(",");
							ground1.setGroundImages(Arrays.asList(imageUrl));
						} else {
							ground1.setGroundImages(new ArrayList<>());
						}

						String sportNamesdata = rs.getString(COLUMN_SPORTS);
						if (sportNamesdata != null) {
							String[] sportNames = sportNamesdata.split(",");
							ground1.setSportsAvailable(Arrays.asList(sportNames));
						} else {
							ground1.setSportsAvailable(new ArrayList<>());
						}

						// add ground object
						groundList.add(ground1);
					}
				}
			}

		}

		catch (SQLException e) {
			throw new DAOException(GroundDaoErrors.READ_GROUND_DETAILS_ERROR);
		}

		return groundList;
	}

	
	
	/**
	 * The getAllGround Method get all ground details from the database
	 */
	public Ground getGroundById(int id) throws DAOException {


		/**
		 * The Query for selecting all grounddetails from all the table
		 */

		String selectQuery = SELECT_G +
		        SELECT_IMAGE_URLS_AND_SPORT_NAMES +
		        FROM_GROUND +
		        "WHERE g.id = ? AND g.groundStatus = 1";
			Ground ground =null;

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				 preparedStatement.setInt(1, id);
				try (ResultSet rs = preparedStatement.executeQuery()) {

					while (rs.next()) {
						int groundId = rs.getInt("id");

		 ground = new Ground();
						Time startTimeSql4 = rs.getTime(COLUMN_START_TIME);
						LocalTime startTime4 = startTimeSql4.toLocalTime();

						Time endTimeSql4 = rs.getTime(COLUMN_END_TIME);
						LocalTime endTime4 = endTimeSql4.toLocalTime();
						
	
						
						ground.setGroundOwnerId(rs.getInt("groundOwnerId"));
						ground.setGroundId(groundId);
						ground.setGroundName(rs.getString(COLUMN_GROUND_NAME));
						ground.setGroundMainArea(rs.getString(COLUMN_GROUND_MAIN_AREA));
						ground.setGroundAddress(rs.getString(COLUMN_GROUND_ADDRESS));
						ground.setGroundLocationLink(rs.getString(COLUMN_GROUND_LOCATION_LINK));
						ground.setDistrict(rs.getString(COLUMN_DISTRICT));
						ground.setStartTime(startTime4);
						ground.setEndTime(endTime4);
						ground.setGroundRules(rs.getString(COLUMN_GROUND_RULES));
						ground.setPrice(rs.getDouble(COLUMN_PRICE));
						ground.setIncreasingPriceForExtraHours(rs.getDouble(COLUMN_INCREASING_PRICE));
						ground.setCourtsAvailable(rs.getInt(COLUMN_COURTS_AVAILABLE));

						String imageUrlsdata2 = rs.getString(COLUMN_IMAGE_URLS);
						if (imageUrlsdata2 != null) {
							String[] imageUrl2 = imageUrlsdata2.split(",");
							ground.setGroundImages(Arrays.asList(imageUrl2));
						} else {
							ground.setGroundImages(new ArrayList<>());
						}

						String sportNamesdata2 = rs.getString(COLUMN_SPORTS);
						if (sportNamesdata2 != null) {
							String[] sportNames2 = sportNamesdata2.split(",");
							ground.setSportsAvailable(Arrays.asList(sportNames2));
						} else {
							ground.setSportsAvailable(new ArrayList<>());
						}

						// add ground object
					
					}
				}
			}

		}

		catch (SQLException e) {
			throw new DAOException(GroundDaoErrors.READ_GROUND_DETAILS_ERROR);
		}

		return ground;
	}
	
	
	public Ground getGroundByOwnerId(int id) throws DAOException {


		/**
		 * The Query for selecting all grounddetails from all the table
		 */

		String selectQuery =  SELECT_G +
		        SELECT_IMAGE_URLS_AND_SPORT_NAMES +
		        FROM_GROUND +
		        "WHERE g.groundOwnerId = ? AND g.groundStatus = 1";
			Ground ground2 =null;

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				 preparedStatement.setInt(1, id);
				try (ResultSet rs = preparedStatement.executeQuery()) {

					while (rs.next()) {
						int groundOwnerId = rs.getInt("groundOwnerId");
						int groundId = rs.getInt("id");


		 ground2 = new Ground();
						Time startTimeSql5 = rs.getTime(COLUMN_START_TIME);
						LocalTime startTime5 = startTimeSql5.toLocalTime();

						Time endTimeSql5 = rs.getTime(COLUMN_END_TIME);
						LocalTime endTime5 = endTimeSql5.toLocalTime();
                 ground2.setGroundId(groundId);
             	ground2.setGroundName(rs.getString(COLUMN_GROUND_NAME));
				ground2.setGroundMainArea(rs.getString(COLUMN_GROUND_MAIN_AREA));
				ground2.setGroundAddress(rs.getString(COLUMN_GROUND_ADDRESS));
				ground2.setGroundLocationLink(rs.getString(COLUMN_GROUND_LOCATION_LINK));
				ground2.setDistrict(rs.getString(COLUMN_DISTRICT));
				ground2.setGroundOwnerId(groundOwnerId);
				ground2.setStartTime(startTime5);
				ground2.setEndTime(endTime5);
				ground2.setGroundRules(rs.getString(COLUMN_GROUND_RULES));
				ground2.setPrice(rs.getDouble(COLUMN_PRICE));
				ground2.setIncreasingPriceForExtraHours(rs.getDouble(COLUMN_INCREASING_PRICE));
				ground2.setCourtsAvailable(rs.getInt(COLUMN_COURTS_AVAILABLE));

				String imageUrlsdata2 = rs.getString(COLUMN_IMAGE_URLS);
						if (imageUrlsdata2 != null) {
							String[] imageUrl2 = imageUrlsdata2.split(",");
							ground2.setGroundImages(Arrays.asList(imageUrl2));
						} else {
							ground2.setGroundImages(new ArrayList<>());
						}

						String sportNamesdata2 = rs.getString(COLUMN_SPORTS);
						if (sportNamesdata2 != null) {
							String[] sportNames2 = sportNamesdata2.split(",");
							ground2.setSportsAvailable(Arrays.asList(sportNames2));
						} else {
							ground2.setSportsAvailable(new ArrayList<>());
						}

						// add ground object
					
					}
				}
			}

		}

		catch (SQLException e) {
			throw new DAOException(GroundDaoErrors.READ_GROUND_DETAILS_ERROR);
		}

		return ground2;
	}
	
	
	

	public boolean isGroundOwnerExist(int id) throws SQLException, DAOException {

		/**
		 * The Query for calling insertground from sql
		 */
		 boolean exists = false;
		 String query = "SELECT COUNT(*) FROM Ground WHERE groundOwnerId = ?";
		/**
		 * Getting the ground details and inserting in sql
		 */
		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setInt(1, id);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
	                    int count = rs.getInt(1);
	                    exists = count > 0;
	                }
			}
		}
		}catch (SQLException e) {

			throw new DAOException(GroundOwnerDaoErrors.INSERT_GROUNDOWNER_DETAILS_ERROR);
		}

			 return exists;


}
	

}
