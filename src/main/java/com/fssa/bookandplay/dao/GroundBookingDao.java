package com.fssa.bookandplay.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fssa.bookandplay.errors.GroundBookingDetailDaoErrors;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.Ground;
import com.fssa.bookandplay.model.GroundBooking;
import com.fssa.bookandplay.model.GroundOwner;
import com.fssa.bookandplay.model.User;
import com.fssa.bookandplay.util.ConnectionUtil;
import com.fssa.bookandplay.util.Logger;

public class GroundBookingDao {

	static Logger logger = new Logger();

	public GroundBookingDao() {
		// TODO Auto-generated constructor stub
	}

	public boolean addGroundBooking(GroundBooking groundBooking) throws DAOException, SQLException {

		/**
		 * The Query for calling insertground from sql
		 */
		String storedProcedureCall = "{CALL InsertGroundBooking(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

		/**
		 * Getting the ground details and inserting in sql
		 */
		try (Connection con = ConnectionUtil.getConnection()) {

			try (CallableStatement callableStatement = con.prepareCall(storedProcedureCall)) {

				Date bookingUtilDate = Date.valueOf(groundBooking.getBookingDate());
				Date paymentUtilDate = Date.valueOf(groundBooking.getPaymentDate());

				callableStatement.setDate(1, bookingUtilDate);
				callableStatement.setString(2, groundBooking.getBookingDuration());
				callableStatement.setString(3, groundBooking.getBookingSports());
				callableStatement.setString(4, groundBooking.getSelectedCourts());
				callableStatement.setInt(5, groundBooking.getSelectedPlayers());
				callableStatement.setLong(6, groundBooking.getBookedAt());
				callableStatement.setDouble(7, groundBooking.getGroundPrice());
				callableStatement.setInt(8, groundBooking.getGroundId());
				callableStatement.setInt(9, groundBooking.getRequestUserId());
				callableStatement.setInt(10, groundBooking.getSellerId());
				callableStatement.setDate(11, paymentUtilDate);
				callableStatement.setDouble(12, groundBooking.getPaymentAmount());
				callableStatement.setString(13, groundBooking.getPaymentMethod());

				String groundBookingTimingStr = String.join(",", groundBooking.getBookingTime());
				callableStatement.setString(14, groundBookingTimingStr);
				callableStatement.execute();

			}
		} catch (SQLException e) {

			throw new DAOException(GroundBookingDetailDaoErrors.INSERT_GROUND_BOOKING_DETAILS_ERROR);
		}

		return true;

	}

	public boolean checkBookingExists(LocalDate bookDate, List<String> selectedTimings, String selectedCourts,
			int groundId) throws SQLException, DAOException {

		String selectedTimingsStr = String.join(",", selectedTimings);
		Date bookingUtilDate = Date.valueOf(bookDate);
		String sql = "SELECT COUNT(*) " + "FROM GroundBooking gb "
				+ "JOIN BookingTime bt ON gb.bookingId = bt.bookingId "
				+ "WHERE gb.bookingDate = ? AND FIND_IN_SET(bt.bookingTime, ?) > 0 AND gb.selectedCourts = ? AND gb.groundId = ?";
		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(sql)) {

				pst.setDate(1, bookingUtilDate);
				pst.setString(2, selectedTimingsStr);
				pst.setString(3, selectedCourts);
				pst.setInt(4, groundId);

				try (ResultSet rs = pst.executeQuery()) {

					rs.next();
					int count = rs.getInt(1);
					return count > 0;
				}

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Failed to check booking existence.");
		}

	}

	public List<GroundBooking> findUserBookings(int userId) throws SQLException, DAOException {

		List<GroundBooking> bookings = new ArrayList<>();
		String sql = "SELECT gb.bookingId, gb.bookingDate, gb.bookingDuration, gb.bookingSports, gb.bookingStatus, "
				+ "gb.userBookingStatus, gb.selectedCourts, gb.selectedPlayers, gb.bookedAt, gb.groundPrice, "
				+ "gb.groundId, gb.requestUserId, gb.sellerId, p.paymentDate, p.paymentAmount, p.paymentMethod, "
				+ "GROUP_CONCAT(DISTINCT bt.bookingTime ORDER BY bt.bookingTime ASC SEPARATOR ', ') AS bookingTimes, "
				+ "GROUP_CONCAT(DISTINCT gi.imageUrl) AS groundImages, "
				+ "GROUP_CONCAT(DISTINCT sa.sportName) AS sportsAvailable, " + "g.groundName, g.groundMainArea, "
				+ "go.name AS ownerName, go.organisationName AS ownerOrganisation, go.email AS ownerEmail, "
				+ "go.phoneNumber AS ownerPhoneNumber, go.image AS ownerImage, go.status AS ownerStatus "
				+ "FROM GroundBooking gb " + "LEFT JOIN Payment p ON gb.bookingId = p.bookingId "
				+ "LEFT JOIN BookingTime bt ON gb.bookingId = bt.bookingId "
				+ "LEFT JOIN Ground g ON gb.sellerId = g.groundOwnerId "
				+ "LEFT JOIN GroundImages gi ON gb.sellerId = gi.groundId "
				+ "LEFT JOIN SportsAvailable sa ON gb.sellerId = sa.groundId "
				+ "LEFT JOIN GroundOwner go ON gb.sellerId = go.id " + // Join GroundOwner table
				"WHERE gb.requestUserId = ? AND gb.bookingStatus = 1 AND gb.userBookingStatus = 1 "
				+ "GROUP BY gb.bookingId, gb.bookingDate, gb.bookingDuration, gb.bookingSports, gb.bookingStatus, "
				+ "         gb.userBookingStatus, gb.selectedCourts, gb.selectedPlayers, gb.bookedAt, gb.groundPrice, "
				+ "         gb.groundId, gb.requestUserId, gb.sellerId, p.paymentDate, p.paymentAmount, p.paymentMethod, "
				+ "         g.groundName, g.groundMainArea, g.groundAddress, g.groundLocationLink, g.district, "
				+ "         g.startTime, g.endTime, g.groundRules, g.price, g.increasingPriceForExtraHours, "
				+ "         g.courtsAvailable, g.groundStatus, go.name, go.organisationName, go.email, "
				+ "         go.phoneNumber, go.image, go.status";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
				preparedStatement.setInt(1, userId);

				try (ResultSet rs = preparedStatement.executeQuery()) {
					while (rs.next()) {
						GroundBooking booking = new GroundBooking();

						booking.setBookingId(rs.getInt("bookingId"));
						booking.setBookingDate(rs.getDate("bookingDate").toLocalDate());
						booking.setBookingDuration(rs.getString("bookingDuration"));
						booking.setBookingSports(rs.getString("bookingSports"));
						booking.setBookingStatus(rs.getBoolean("bookingStatus"));
						booking.setUserBookingStatus(rs.getBoolean("userBookingStatus"));
						booking.setSelectedCourts(rs.getString("selectedCourts"));
						booking.setSelectedPlayers(rs.getInt("selectedPlayers"));
						booking.setBookedAt(rs.getLong("bookedAt"));
						booking.setGroundPrice(rs.getDouble("groundPrice"));
						booking.setGroundId(rs.getInt("groundId"));
						booking.setRequestUserId(rs.getInt("requestUserId"));
						booking.setSellerId(rs.getInt("sellerId"));
						booking.setPaymentDate(rs.getDate("paymentDate").toLocalDate());
						booking.setPaymentAmount(rs.getDouble("paymentAmount"));
						booking.setPaymentMethod(rs.getString("paymentMethod"));
						String bookingTiming = rs.getString("bookingTimes");
						if (bookingTiming != null) {
							String[] bookingTimingData = bookingTiming.split(",");
							booking.setBookingTime(Arrays.asList(bookingTimingData));
							;
						} else {
							booking.setBookingTime(new ArrayList<>());
						}

						Ground ground = new Ground();
						ground.setGroundName(rs.getString("groundName"));
						ground.setGroundMainArea(rs.getString("groundMainArea"));

						String imageUrlsdata = rs.getString("groundImages");
						if (imageUrlsdata != null) {
							String[] imageUrl = imageUrlsdata.split(",");
							ground.setGroundImages(Arrays.asList(imageUrl));
						} else {
							ground.setGroundImages(new ArrayList<>());
						}

						String sportNamesdata = rs.getString("sportsAvailable");
						if (sportNamesdata != null) {
							String[] sportNames = sportNamesdata.split(",");
							ground.setSportsAvailable(Arrays.asList(sportNames));
						} else {
							ground.setSportsAvailable(new ArrayList<>());
						}
						booking.setGround(ground);

						GroundOwner groundOwner = new GroundOwner();
						groundOwner.setEmail(rs.getString("ownerEmail"));
						booking.setGroundOwner(groundOwner);
						bookings.add(booking);
					}

				}

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Failed to check booking existence.");
		}
		return bookings;

	}

	public boolean updateUserBookingStatusToZero(int bookingId) throws DAOException {
	   	try (Connection con = ConnectionUtil.getConnection()) {

			String updateQuery = "UPDATE GroundBooking SET userBookingStatus = 0 WHERE bookingId = ?";

			try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
				preparedStatement.setInt(1, bookingId);
				preparedStatement.executeUpdate();
				return true;

			}

		}

		catch (SQLException e) {
			throw new DAOException("Failed to check booking existence.");
		}

	}
	
	
	
	public List<GroundBooking> findGroundAdminBookings(int userId) throws SQLException, DAOException {

		List<GroundBooking> bookings = new ArrayList<>();
		String sql = "SELECT gb.bookingId, gb.bookingDate, gb.bookingDuration, gb.bookingSports, gb.bookingStatus, " +
	             "gb.userBookingStatus, gb.selectedCourts, gb.selectedPlayers, gb.bookedAt, gb.groundPrice, " +
	             "gb.groundId, gb.requestUserId, gb.sellerId, p.paymentDate, p.paymentAmount, p.paymentMethod, " +
	             "GROUP_CONCAT(DISTINCT bt.bookingTime ORDER BY bt.bookingTime ASC SEPARATOR ', ') AS bookingTimes, " +
	             "u.first_name AS userName, u.email AS userEmail, u.phone_number AS userPhoneNumber, " +
	             "u.imageUrl AS userImage, u.userStatus AS userStatus, " +
	             "go.name AS ownerName, go.organisationName AS ownerOrganisation, go.email AS ownerEmail, " +
	             "go.phoneNumber AS ownerPhoneNumber, go.image AS ownerImage, go.status AS ownerStatus " +
	             "FROM GroundBooking gb " +
	             "LEFT JOIN Payment p ON gb.bookingId = p.bookingId " +
	             "LEFT JOIN BookingTime bt ON gb.bookingId = bt.bookingId " +
	             "LEFT JOIN GroundOwner go ON gb.sellerId = go.id " + // Join GroundOwner table
	             "LEFT JOIN User u ON gb.requestUserId = u.id " + // Join User table
	             "WHERE gb.sellerId = ? AND gb.bookingStatus = 1 AND gb.userBookingStatus = 1 " +
	             "GROUP BY gb.bookingId, gb.bookingDate, gb.bookingDuration, gb.bookingSports, gb.bookingStatus, " +
	             "         gb.userBookingStatus, gb.selectedCourts, gb.selectedPlayers, gb.bookedAt, gb.groundPrice, " +
	             "         gb.groundId, gb.requestUserId, gb.sellerId, p.paymentDate, p.paymentAmount, p.paymentMethod, " +
	             "         u.first_name, u.email, u.phone_number, u.imageUrl, u.userStatus, " +
	             "         go.name, go.organisationName, go.email, " +
	             "         go.phoneNumber, go.image, go.status";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
				preparedStatement.setInt(1, userId);

				try (ResultSet rs = preparedStatement.executeQuery()) {
					while (rs.next()) {
						GroundBooking booking = new GroundBooking();

						booking.setBookingId(rs.getInt("bookingId"));
						booking.setBookingDate(rs.getDate("bookingDate").toLocalDate());
						booking.setBookingDuration(rs.getString("bookingDuration"));
						booking.setBookingSports(rs.getString("bookingSports"));
						booking.setBookingStatus(rs.getBoolean("bookingStatus"));
						booking.setUserBookingStatus(rs.getBoolean("userBookingStatus"));
						booking.setSelectedCourts(rs.getString("selectedCourts"));
						booking.setSelectedPlayers(rs.getInt("selectedPlayers"));
						booking.setBookedAt(rs.getLong("bookedAt"));
						booking.setGroundPrice(rs.getDouble("groundPrice"));
						booking.setGroundId(rs.getInt("groundId"));
						booking.setRequestUserId(rs.getInt("requestUserId"));
						booking.setSellerId(rs.getInt("sellerId"));
						booking.setPaymentDate(rs.getDate("paymentDate").toLocalDate());
						booking.setPaymentAmount(rs.getDouble("paymentAmount"));
						booking.setPaymentMethod(rs.getString("paymentMethod"));
						String bookingTiming = rs.getString("bookingTimes");
						if (bookingTiming != null) {
							String[] bookingTimingData = bookingTiming.split(",");
							booking.setBookingTime(Arrays.asList(bookingTimingData));
							;
						} else {
							booking.setBookingTime(new ArrayList<>());
						}
						
						User user=new User();
						user.setEmail(rs.getString("userEmail"));
						booking.setUser(user);
						bookings.add(booking);
					}

				}

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Failed to check booking existence.");
		}
		return bookings;

	}
	
	
	
	public boolean updateBookingStatusToZero(int bookingId) throws DAOException {
	   	try (Connection con = ConnectionUtil.getConnection()) {

			String updateQuery = "UPDATE GroundBooking SET bookingStatus = 0 WHERE bookingId = ?";

			try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
				preparedStatement.setInt(1, bookingId);
				preparedStatement.executeUpdate();
				return true;

			}

		}

		catch (SQLException e) {
			throw new DAOException("Failed to check booking existence.");
		}

	}
	

	public static void main(String[] args) {

		GroundBookingDao data = new GroundBookingDao();

		List<GroundBooking> bookings = null;
		try {
			bookings = data.findGroundAdminBookings(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info(bookings);
	}

}
