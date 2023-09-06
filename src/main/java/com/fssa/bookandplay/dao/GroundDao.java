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
		if (ground.getgroundId() <= 0) {
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

				callableStatement2.setInt(1, ground.getgroundId());
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
		String selectQuery = "SELECT g.*, " +
			    "(SELECT GROUP_CONCAT(imageUrl) FROM GroundImages gi WHERE gi.groundId = g.id) AS imageUrls, " +
			    "(SELECT GROUP_CONCAT(sportName) FROM SportsAvailable sa WHERE sa.groundId = g.id) AS sportNames " +
			    "FROM Ground g " +
			    "WHERE g.groundStatus = 1"; 

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				try (ResultSet rs = preparedStatement.executeQuery()) {

					while (rs.next()) {
						int groundId = rs.getInt("id");

						Ground ground = new Ground();
						Time startTimeSql = rs.getTime("startTime");
						LocalTime startTime = startTimeSql.toLocalTime();
						Time endTimeSql = rs.getTime("endTime");
						LocalTime endTime = endTimeSql.toLocalTime();
						ground.setgroundId(groundId);
						ground.setGroundName(rs.getString("groundName"));
						ground.setGroundMainArea(rs.getString("groundMainArea"));
						ground.setGroundAddress(rs.getString("groundAddress"));
						ground.setGroundLocationLink(rs.getString("groundLocationLink"));
						ground.setDistrict(rs.getString("district"));
						ground.setStartTime(startTime);
						ground.setEndTime(endTime);
						ground.setGroundRules(rs.getString("groundRules"));
						ground.setPrice(rs.getDouble("price"));
						ground.setIncreasingPriceForExtraHours(rs.getDouble("increasingPriceForExtraHours"));
						ground.setCourtsAvailable(rs.getInt("courtsAvailable"));
						
						String imageUrlsdata = rs.getString("imageUrls");
						if (imageUrlsdata != null) {
							String[] imageUrl = imageUrlsdata.split(",");
							ground.setGroundImages(Arrays.asList(imageUrl));
						} else {
							ground.setGroundImages(new ArrayList<>());
						}

						String sportNamesdata = rs.getString("sportNames");
						if (sportNamesdata != null) {
							String[] sportNames = sportNamesdata.split(",");
							ground.setSportsAvailable(Arrays.asList(sportNames));
						} else {
							ground.setSportsAvailable(new ArrayList<>());
						}

						// add ground object
						groundList.add(ground);
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

		String selectQuery = "SELECT g.*, " +
			    "(SELECT GROUP_CONCAT(imageUrl) FROM GroundImages gi WHERE gi.groundId = g.id) AS imageUrls, " +
			    "(SELECT GROUP_CONCAT(sportName) FROM SportsAvailable sa WHERE sa.groundId = g.id) AS sportNames " +
			    "FROM Ground g " +
			    "WHERE g.id = ? AND g.groundStatus = 1"; 
			Ground ground =null;

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				 preparedStatement.setInt(1, id);
				try (ResultSet rs = preparedStatement.executeQuery()) {

					while (rs.next()) {
						int groundId = rs.getInt("id");

		 ground = new Ground();
						Time startTimeSql2 = rs.getTime("startTime");
						LocalTime startTime2 = startTimeSql2.toLocalTime();

						Time endTimeSql2 = rs.getTime("endTime");
						LocalTime endTime2 = endTimeSql2.toLocalTime();
						
	
						

						ground.setgroundId(groundId);
						ground.setGroundName(rs.getString("groundName"));
						ground.setGroundMainArea(rs.getString("groundMainArea"));
						ground.setGroundAddress(rs.getString("groundAddress"));
						ground.setGroundLocationLink(rs.getString("groundLocationLink"));
						ground.setDistrict(rs.getString("district"));
						ground.setStartTime(startTime2);
						ground.setEndTime(endTime2);
						ground.setGroundRules(rs.getString("groundRules"));
						ground.setPrice(rs.getDouble("price"));
						ground.setIncreasingPriceForExtraHours(rs.getDouble("increasingPriceForExtraHours"));
						ground.setCourtsAvailable(rs.getInt("courtsAvailable"));

						String imageUrlsdata2 = rs.getString("imageUrls");
						if (imageUrlsdata2 != null) {
							String[] imageUrl2 = imageUrlsdata2.split(",");
							ground.setGroundImages(Arrays.asList(imageUrl2));
						} else {
							ground.setGroundImages(new ArrayList<>());
						}

						String sportNamesdata2 = rs.getString("sportNames");
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

		String selectQuery = "SELECT g.*, "
		        + "(SELECT GROUP_CONCAT(imageUrl) FROM GroundImages gi WHERE gi.groundId = g.id) AS imageUrls, "
		        + "(SELECT GROUP_CONCAT(sportName) FROM SportsAvailable sa WHERE sa.groundId = g.id) AS sportNames "
		        + "FROM Ground g "
		        + "WHERE g.groundOwnerId = ? AND g.groundStatus = 1";
			Ground ground =null;

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				 preparedStatement.setInt(1, id);
				try (ResultSet rs = preparedStatement.executeQuery()) {

					while (rs.next()) {
						int groundOwnerId = rs.getInt("groundOwnerId");
						int groundId = rs.getInt("id");


		 ground = new Ground();
						Time startTimeSql2 = rs.getTime("startTime");
						LocalTime startTime2 = startTimeSql2.toLocalTime();

						Time endTimeSql2 = rs.getTime("endTime");
						LocalTime endTime2 = endTimeSql2.toLocalTime();
                 ground.setgroundId(groundId);
						ground.setGroundOwnerId(groundOwnerId);
						ground.setGroundName(rs.getString("groundName"));
						ground.setGroundMainArea(rs.getString("groundMainArea"));
						ground.setGroundAddress(rs.getString("groundAddress"));
						ground.setGroundLocationLink(rs.getString("groundLocationLink"));
						ground.setDistrict(rs.getString("district"));
						ground.setStartTime(startTime2);
						ground.setEndTime(endTime2);
						ground.setGroundRules(rs.getString("groundRules"));
						ground.setPrice(rs.getDouble("price"));
						ground.setIncreasingPriceForExtraHours(rs.getDouble("increasingPriceForExtraHours"));
						ground.setCourtsAvailable(rs.getInt("courtsAvailable"));

						String imageUrlsdata2 = rs.getString("imageUrls");
						if (imageUrlsdata2 != null) {
							String[] imageUrl2 = imageUrlsdata2.split(",");
							ground.setGroundImages(Arrays.asList(imageUrl2));
						} else {
							ground.setGroundImages(new ArrayList<>());
						}

						String sportNamesdata2 = rs.getString("sportNames");
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
