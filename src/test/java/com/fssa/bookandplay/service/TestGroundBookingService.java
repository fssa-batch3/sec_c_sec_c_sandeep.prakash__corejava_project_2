package com.fssa.bookandplay.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.bookandplay.builder.GroundBookingBuilder;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.GroundBooking;
import com.fssa.bookandplay.util.Logger;

public class TestGroundBookingService {

	GroundBookingService gds = new GroundBookingService();

	static Logger logger = new Logger();

	GroundBooking getGroundBookingInfo() {
		String bookDate = "2023-10-09";

		// Define the date formatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// Parse the date string to a LocalDate object
		LocalDate bookingLocalDate = LocalDate.parse(bookDate, formatter);
		GroundBooking groundbooking = new GroundBookingBuilder().bookingDateBuild(bookingLocalDate)
				.bookingSportsBuild("Cricket").bookingDurationBuild("1").selectedPlayersBuild(11)
				.selectedCourtsBuild("Court 1").groundPriceBuild(300).paymentMethodBuild("cash")
				.bookedAtBuild(1695056944151l).groundIdBuild(1).sellerIdBuild(1).paymentAmountBuild(200)
				.requestUserIdBuild(1).paymentDateBuild(bookingLocalDate)
				.bookingTimeBuild(Arrays.asList("11:00 AM-12:00 PM", "12:00 PM-01:00 PM")).build();
		return groundbooking;
	}

	@Test
	// @Disabled("This test is currently disabled ")
	void testAddGroundBooking() throws DAOException, SQLException {
		GroundBooking ground = getGroundBookingInfo();
		Assertions.assertTrue(gds.addGroundBooking(ground));

	}

	@Test
	// @Disabled("This test is currently disabled ")
	void testCancelGroundBooking() throws DAOException, SQLException {

		Assertions.assertTrue(gds.cancelBooking(1));

	}

	@Test
	// @Disabled("This test is currently disabled ")
	void testCancelGroundBookingByAdmin() throws DAOException, SQLException {

		Assertions.assertTrue(gds.cancelBookingByAdmin(1));

	}

	@Test
	void testGetGroundBookingDetailUser() throws DAOException, SQLException {

		List<GroundBooking> groundList = gds.getBookingDetailsByUserId(1);
		assertNotNull(groundList);
		for (GroundBooking gr : groundList) {

			logger.info(gr);

		}

	}

	@Test
	void testGetGroundBookingDetailAdmin() throws DAOException, SQLException {

		List<GroundBooking> groundList = gds.getBookingDetailsBySellerId(1);
		assertNotNull(groundList);
		for (GroundBooking gr : groundList) {

			logger.info(gr);

		}

	}

}
