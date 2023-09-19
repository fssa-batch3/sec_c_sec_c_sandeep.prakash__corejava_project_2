package com.fssa.bookandplay.validator;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.bookandplay.constants.GroundConstants;
import com.fssa.bookandplay.errors.GroundBookingDetailValidatorErrors;
import com.fssa.bookandplay.errors.GroundValidatorsErrors;
import com.fssa.bookandplay.exceptions.InvalidGroundBookingDetailException;
import com.fssa.bookandplay.exceptions.InvalidGroundDetailException;
import com.fssa.bookandplay.model.GroundBooking;
import com.fssa.bookandplay.regexpattern.GroundBookingRegexPattern;

public class GroundBookingValidator {

	public boolean validateBooking(GroundBooking groundBooking) throws InvalidGroundBookingDetailException {
		if (groundBooking == null) {
			throw new InvalidGroundBookingDetailException(GroundBookingDetailValidatorErrors.INVALID_OBJECT_NULL);
		}
		bookingDateValidator(groundBooking.getBookingDate());
		bookingSportsValidator(groundBooking.getBookingSports());
		//validateBookingTime(groundBooking.getBookingTime());
		//selectedCourtsValidator(groundBooking.getSelectedCourts());
		//selectedPlayersValidator(groundBooking.getSelectedPlayers());
		groundPrice(groundBooking.getGroundPrice());
		groundIdValidator(groundBooking.getGroundId());
		validatePaymentDate(groundBooking.getPaymentDate());
		//paymentMethodValidator(groundBooking.getPaymentMethod());

		return true;

	}

	public boolean bookingDateValidator(LocalDate bookingDate) throws InvalidGroundBookingDetailException {

		if (bookingDate == null) {
			throw new InvalidGroundBookingDetailException(GroundBookingDetailValidatorErrors.INVALID_DATE_NULL);

		}

		LocalDate currentDate = LocalDate.now();
		if (bookingDate.isBefore(currentDate)) {
			throw new InvalidGroundBookingDetailException(GroundBookingDetailValidatorErrors.INVALID_DATE);

		}
		return true;
	}

	public boolean bookingSportsValidator(String bookingSports) throws InvalidGroundBookingDetailException {
		/**
		 * sportsAvailable empty check
		 */
		if (bookingSports == null) {
			throw new InvalidGroundBookingDetailException(GroundBookingDetailValidatorErrors.INVALID_SPORTSTYPE);
		}

		/**
		 * sportsAvailable regex pattern check
		 */

		String urlRegex = GroundBookingRegexPattern.GROUND_SPORTS_CHARACTER_REGEX;
		Pattern pattern = Pattern.compile(urlRegex);
		Matcher matcher = pattern.matcher(bookingSports);
		if (!matcher.matches()) {
			throw new InvalidGroundBookingDetailException(
					GroundBookingDetailValidatorErrors.INVALID_SPORTSTYPE_PATTERN);
		}

		return true;
	}

	public static boolean validateBookingTime(List<String> bookingTimeSlots)
			throws InvalidGroundBookingDetailException {

		Pattern pattern = Pattern.compile(GroundBookingRegexPattern.GROUND_TIMINGSLOT_REGEX);

		for (String timeSlot : bookingTimeSlots) {
			Matcher matcher = pattern.matcher(timeSlot);

			if (!matcher.matches()) {
				throw new InvalidGroundBookingDetailException(GroundBookingDetailValidatorErrors.INVALID_TIMESLOT);
			}
		}
		return true;
	}

	public boolean selectedCourtsValidator(String selectedCourts) throws InvalidGroundBookingDetailException {
		/**
		 * groundcourtsAvailable should be within 6.
		 */
		// contain max 6

		if (selectedCourts == null) {
			throw new InvalidGroundBookingDetailException(GroundBookingDetailValidatorErrors.INVALID_COURTSAVAIL_NULL);
		}

		/**
		 * groundistrict regex pattern minimum 2 charcter and max 35 charcter
		 */
		String nameregex = GroundBookingRegexPattern.GROUND_COURTS_CHARACTER_REGEX;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(selectedCourts);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundBookingDetailException(GroundBookingDetailValidatorErrors.INVALID_COURTSAVAIL_TYPE);

		}

		return true;

	}

	public boolean selectedPlayersValidator(int selectedPlayers) throws InvalidGroundBookingDetailException {
		/**
		 * groundcourtsAvailable should be within 6.
		 */
		// contain max 6

		if (selectedPlayers < GroundConstants.MIN_PLAYERS_AVAILABLE
				|| selectedPlayers > GroundConstants.MAX_PLAYERS_AVAILABLE) {
			throw new InvalidGroundBookingDetailException(
					GroundBookingDetailValidatorErrors.INVALID_PLAYERS_AVAIL_TYPE);
		}
		return true;
	}

	public boolean groundPrice(double price) throws InvalidGroundBookingDetailException {
		/**
		 * groundprice should be greater than 150 and lesser than 2000.
		 */
		if (price >= GroundConstants.GROUNDBOOKING_MIN_PRICE && price <= GroundConstants.GROUNDBOOKING_MAX_PRICE) {
			return true;
		}
		throw new InvalidGroundBookingDetailException(GroundBookingDetailValidatorErrors.INVALID_PRICE);

	}

	public boolean groundIdValidator(int groundId) throws InvalidGroundBookingDetailException {
		/**
		 * groundId should be greaterthan 1.
		 */
		if (groundId <= GroundConstants.INVALID_GROUNDID) {
			throw new InvalidGroundBookingDetailException(GroundBookingDetailValidatorErrors.INVALID_PRODUCT_ID);

		}
		return true;

	}

	public static boolean validatePaymentDate(LocalDate paymentDate) throws InvalidGroundBookingDetailException {
		LocalDate currentDate = LocalDate.now();

		// Check if the payment date is equal to the current date (present)
		if (paymentDate.equals(currentDate)) {

			return true;
		}
		throw new InvalidGroundBookingDetailException(GroundBookingDetailValidatorErrors.INVALID_PAYMENT_DATE);

	}

	public boolean paymentMethodValidator(String paymentMethod) throws InvalidGroundBookingDetailException {
		/**
		 * groundistrict null and empty string check
		 */
		if (paymentMethod == null) {
			throw new InvalidGroundBookingDetailException(GroundBookingDetailValidatorErrors.INVALID_PAYMENT_NULL);
		}

		/**
		 * groundistrict regex pattern minimum 2 charcter and max 35 charcter
		 */
		String nameregex = GroundBookingRegexPattern.GROUND_PAYMENT;
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(paymentMethod);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidGroundBookingDetailException(GroundBookingDetailValidatorErrors.INVALID_PAYMENT_METHOD);

		}

		return true;

	}
}
