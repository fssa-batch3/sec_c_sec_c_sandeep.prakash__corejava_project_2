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

import com.fssa.bookandplay.model.Ground;
import com.fssa.bookandplay.validator.GroundValidatorsErrors;

public class GroundDao { 
	public  static boolean addGround(Ground ground) throws DAOException, SQLException {
		

		String query = "INSERT INTO Ground (groundName, groundMainArea, groundAddress, groundLocationLink, district, startTime, endTime, groundRules, price, increasingPriceForExtraHours, courtsAvailable) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		LocalTime startTime = ground.getStartTime();
	    Time startTimeTs = Time.valueOf(startTime);

	    LocalTime endTime = ground.getEndTime();
	    Time endTimeTs= Time.valueOf(endTime);

		// String insertImageQuery = "INSERT INTO GroundImages (groundId, imageUrl)
		// VALUES (?, ?)";
		String storedProcedureCall = "{call InsertGround(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}";
		boolean rows;
		CallableStatement callableStatement=null;

		try {
			con = ConnectionUtil.getConnection();
			// PreparedStatement preparedStatement = con.prepareStatement(query);
		callableStatement = con.prepareCall(storedProcedureCall);

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

			rows = callableStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("The ADD Ground details to database is failed");
			
		} finally {
			ConnectionUtil.close(con, null, null,null,callableStatement);
		}
		return true;

	}

	public static boolean updateGround(Ground ground) throws DAOException, SQLException {
		
		if(ground.getgroundId()<=0) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_PRODUCT_ID);
			
			
		}

		Connection con = null;
		LocalTime startTime = ground.getStartTime();
	    Time startTimeTs = Time.valueOf(startTime);

	    LocalTime endTime = ground.getEndTime();
	    Time endTimeTs= Time.valueOf(endTime);

		// String insertImageQuery = "INSERT INTO GroundImages (groundId, imageUrl)
		// VALUES (?, ?)";
		String storedProcedureCall = "{call UpdateGround(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)}";
		boolean rows;
		CallableStatement callableStatement=null;
		
		try {
			con = ConnectionUtil.getConnection();
			// PreparedStatement preparedStatement = con.prepareStatement(query);
			 callableStatement = con.prepareCall(storedProcedureCall);
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

			rows = callableStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("error");
			throw new DAOException("The Update Ground details to database is failed");
		} finally {
			ConnectionUtil.close(con, null, null,null,callableStatement);
		}
		return true;

	} 

	public static boolean deleteGround(int groundId) throws DAOException, SQLException {
		if(groundId<=0) {
			throw new InvalidGroundDetailException(GroundValidatorsErrors.INVALID_PRODUCT_ID);
			
			 
		}

		Connection con = null;

		String storedProcedureCall = "{call DeleteGround(?)}";
		boolean rows;
		CallableStatement callableStatement=null;

		try {
			con = ConnectionUtil.getConnection();

			callableStatement = con.prepareCall(storedProcedureCall);

			callableStatement.setInt(1, groundId);

			rows = callableStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("The Details Ground details to database is failed");
		} finally {
			ConnectionUtil.close(con, null, null,null,callableStatement);
		}
		return true;

	}

	public static boolean getAllGround() throws DAOException, SQLException {
		Connection con = null;
		List<Ground> groundList = new ArrayList<>();
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		try {

			con = ConnectionUtil.getConnection();

			String selectQuery = "SELECT g.*, "
					+ "(SELECT GROUP_CONCAT(imageUrl) FROM GroundImages gi WHERE gi.groundId = g.id) AS imageUrls, "
					+ "(SELECT GROUP_CONCAT(sportName) FROM SportsAvailable sa WHERE sa.groundId = g.id) AS sportNames "
					+ "FROM Ground g";

		preparedStatement = con.prepareStatement(selectQuery);
			 rs = preparedStatement.executeQuery();
	
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

		} catch (SQLException e) {
			throw new DAOException("Error getting Ground data", e);
		}
		 finally {
				ConnectionUtil.close(con, null, rs,preparedStatement,null);
			}

		return true;
	}

	public static void main(String[] args) throws DAOException, SQLException {
//		int groundId = 1;
	List<String> groundImages = new ArrayList<>();
		groundImages.add("image199.jpg");
		groundImages.add("image299.jpg");

		List<String> sportsAvailable = new ArrayList<>();
		sportsAvailable.add("Football");
		sportsAvailable.add("Cricket");

		// Create LocalDateTime objects for startTime and endTime
		LocalTime startTime = LocalTime.of(10, 30); // 10:00 AM
		LocalTime endTime = LocalTime.of(11,30);   // 5:00 PM

		// Create a Ground object using the constructor
		Ground ground1 = new Ground("danny data Ground", "danny  Area 1", "danny don Street",
			"https://danny .example.com/ground", "danny  District", groundImages, sportsAvailable, startTime,
			endTime, "Some rules for the ground", 200.00, 30.00, 3);
		// Call the addGround method to insert data
		 boolean dataInserted = addGround(ground1);

		if (dataInserted) {
		System.out.println("Data inserted successfully.");
	} else {
			System.out.println("Failed to insert data.");
		}
//		try {
//			
//		
//
//			List<Ground> groundlist = getAllGround();
//			for (Ground ground : groundlist) {
//
//				System.out.println("Ground ID: " + ground.getgroundId());
//				System.out.println("Ground Name: " + ground.getGroundName());
//				System.out.println("Ground Mainarea: " + ground.getGroundMainArea());
//				System.out.println("Ground Address: " + ground.getGroundAddress());
//				System.out.println("Ground Locationlink: " + ground.getGroundLocationLink());
//				System.out.println("Ground district: " + ground.getDistrict());
//				System.out.println("Ground starttime: " + ground.getStartTime());
//				System.out.println("Ground endtime: " + ground.getEndTime());
//				System.out.println("Ground rules: " + ground.getGroundRules());
//				System.out.println("Ground price: " + ground.getPrice());
//				System.out.println("Ground increasingPriceForExtraHours: " + ground.getIncreasingPriceForExtraHours());
//				System.out.println("Ground courtsAvailable : " + ground.getCourtsAvailable());
//
//				List<String> groundImagesList = ground.getGroundImages();
//				for (String imageurl : groundImagesList) {
//
//					System.out.println(imageurl);
//				}
//
//				List<String> groundsportsAvailable = ground.getSportsAvailable();
//				for (String sportsAvail : groundsportsAvailable) {
//					System.out.println(sportsAvail);
//				}
//
//			}
//		} catch (DAOException ex) {
//			ex.printStackTrace();
//		}
//		boolean data=deleteGround(1);
//		if (data) {
//			System.out.println("Data inserted successfully.");
//		}
//	 else {
//		System.out.println("Failed to insert data.");
//	}
//	
		
	}

}
