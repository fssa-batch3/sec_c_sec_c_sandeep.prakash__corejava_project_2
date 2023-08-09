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

import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.exceptions.InvalidGroundDetailException;
import com.fssa.bookandplay.model.Ground;
import com.fssa.bookandplay.util.ConnectionUtil;
import com.fssa.bookandplay.util.Logger;
import com.fssa.bookandplay.validator.GroundValidatorsErrors;

/**
 * The GroundDao class contains the crud operations for sql database
 */
public class GroundDao {
	
	public GroundDao() {
	
	}

	/**
	 * The Logger Class is created
	 */
	static Logger logger = new Logger();

	/**
	 * The AddGround Method insert ground details in the database
	 */
	public static boolean addGround(Ground ground) throws DAOException, SQLException {

		LocalTime startTime = ground.getStartTime();
		Time startTimeTs = Time.valueOf(startTime);

		LocalTime endTime = ground.getEndTime();
		Time endTimeTs = Time.valueOf(endTime);

		/**
		 * The Query for calling insertground from sql
		 */
		String storedProcedureCall = "{call InsertGround(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}";
	

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
				callableStatement.setTime(6, startTimeTs);
				callableStatement.setTime(7, endTimeTs);
				callableStatement.setString(8, ground.getGroundRules());
				callableStatement.setDouble(9, ground.getPrice());
				callableStatement.setDouble(10, ground.getIncreasingPriceForExtraHours());
				callableStatement.setInt(11, ground.getCourtsAvailable());

				String groundImagesStr = String.join(",", ground.getGroundImages());
				String sportsAvailableStr = String.join(",", ground.getSportsAvailable());

				callableStatement.setString(12, groundImagesStr);
				callableStatement.setString(13, sportsAvailableStr);
				  callableStatement.execute();

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(GroundDaoErrors.INSERT_GROUND_DETAILS_ERROR);
		}

		return true;

	}

	/**
	 * The UpdateGround Method Update ground details in the database
	 */
	public static boolean updateGround(Ground ground) throws DAOException, SQLException {

		/**
		 * If the ground id is invalid
		 */
		if (ground.getgroundId() <= 0) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_PRODUCT_ID);

		}

		LocalTime startTime = ground.getStartTime();
		Time startTimeTs = Time.valueOf(startTime);

		LocalTime endTime = ground.getEndTime();
		Time endTimeTs = Time.valueOf(endTime);

		/**
		 * The Query for calling UpdateGround from sql
		 */

		String storedProcedureCall = "{call UpdateGround(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)}";
		

		try (Connection con = ConnectionUtil.getConnection()) {
			try (CallableStatement callableStatement = con.prepareCall(storedProcedureCall)) {

				callableStatement.setInt(1, ground.getgroundId());
				callableStatement.setString(2, ground.getGroundName());
				callableStatement.setString(3, ground.getGroundMainArea());
				callableStatement.setString(4, ground.getGroundAddress());
				callableStatement.setString(5, ground.getGroundLocationLink());
				callableStatement.setString(6, ground.getDistrict());
				callableStatement.setTime(7, startTimeTs);
				callableStatement.setTime(8, endTimeTs);
				callableStatement.setString(9, ground.getGroundRules());
				callableStatement.setDouble(10, ground.getPrice());
				callableStatement.setDouble(11, ground.getIncreasingPriceForExtraHours());
				callableStatement.setInt(12, ground.getCourtsAvailable());

				String groundImagesStr = String.join(",", ground.getGroundImages());
				String sportsAvailableStr = String.join(",", ground.getSportsAvailable());

				callableStatement.setString(13, groundImagesStr);
				callableStatement.setString(14, sportsAvailableStr);

			 callableStatement.execute();

			}
		} catch (SQLException e) {
			e.printStackTrace();

			throw new DAOException(GroundDaoErrors.UPDATE_GROUND_DETAILS_ERROR);
		}

		return true;

	}

	/**
	 * The DeleteGround Method Delete ground details in the database
	 */

	public static boolean deleteGround(int groundId) throws DAOException, SQLException {
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

			try (CallableStatement callableStatement = con.prepareCall(storedProcedureCall)) {

				callableStatement.setInt(1, groundId);
           callableStatement.execute();

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(GroundDaoErrors.DELETE_GROUND_DETAILS_ERROR);
		}

		return true;

	}

	/**
	 * The getAllGround Method get all ground details from the database
	 */
	public static boolean getAllGround() throws DAOException, SQLException {

		List<Ground> groundList = new ArrayList<>();

		/**
		 * The Query for selecting all grounddetails from all the table
		 */

		String selectQuery = "SELECT g.*, "
				+ "(SELECT GROUP_CONCAT(imageUrl) FROM GroundImages gi WHERE gi.groundId = g.id) AS imageUrls, "
				+ "(SELECT GROUP_CONCAT(sportName) FROM SportsAvailable sa WHERE sa.groundId = g.id) AS sportNames "
				+ "FROM Ground g";

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
						logger.info(rs.getString("groundName"));
						logger.info(rs.getString("groundMainArea"));
						logger.info(rs.getString("groundAddress"));
						logger.info(rs.getString("groundLocationLink"));
						logger.info(rs.getString("district"));
						logger.info(rs.getTime("startTime"));
						logger.info(rs.getTime("endTime"));
						logger.info(rs.getString("groundRules"));
						logger.info(rs.getDouble("price"));
						logger.info(rs.getDouble("increasingPriceForExtraHours"));
						logger.info(rs.getInt("courtsAvailable"));
						// add ground object
						groundList.add(ground);
					}
				}
			}

		}

		catch (SQLException e) {
			throw new DAOException(GroundDaoErrors.READ_GROUND_DETAILS_ERROR);
		}

		return true;
	}


}
