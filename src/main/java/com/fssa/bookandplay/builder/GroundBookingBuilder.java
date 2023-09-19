package com.fssa.bookandplay.builder;

import java.time.LocalDate;
import java.util.List;

import com.fssa.bookandplay.model.GroundBooking;

public class GroundBookingBuilder {

	private int bookingIdBuilder;
	private LocalDate bookingDateBuilder;
	private String bookingDurationBuilder;
	private String bookingSportsBuilder;
	private boolean bookingStatusBuilder;
	private boolean userBookingStatusBuilder;
	private List<String> bookingTimeBuilder;
	private String selectedCourtsBuilder;
	private int selectedPlayersBuilder;
	private long bookedAtBuilder;
	private double groundPriceBuilder;
	private int groundIdBuilder;
	private int requestUserIdBuilder;
	private int sellerIdBuilder;
	private int paymentIdBuilder;
	private LocalDate paymentDateBuilder;
	private double paymentAmountBuilder;
	private String paymentMethodBuilder;

	public GroundBookingBuilder bookingIdBuild(int bookingIdBuilder) {
		this.bookingIdBuilder = bookingIdBuilder;
		return this;
	}

	public GroundBookingBuilder bookingDateBuild(LocalDate bookingDateBuilder) {
		this.bookingDateBuilder = bookingDateBuilder;
		return this;
	}

	public GroundBookingBuilder bookingDurationBuild(String bookingDurationBuilder) {
		this.bookingDurationBuilder = bookingDurationBuilder;
		return this;
	}

	public GroundBookingBuilder bookingSportsBuild(String bookingSportsBuilder) {
		this.bookingSportsBuilder = bookingSportsBuilder;
		return this;
	}

	public GroundBookingBuilder bookingStatusBuild(boolean bookingStatusBuilder) {
		this.bookingStatusBuilder = bookingStatusBuilder;
		return this;
	}

	public GroundBookingBuilder userBookingStatusBuild(boolean userBookingStatusBuilder) {
		this.userBookingStatusBuilder = userBookingStatusBuilder;
		return this;
	}

	public GroundBookingBuilder bookingTimeBuild(List<String> bookingTimeBuilder) {
		this.bookingTimeBuilder = bookingTimeBuilder;
		return this;
	}

	public GroundBookingBuilder selectedCourtsBuild(String selectedCourtsBuilder) {
		this.selectedCourtsBuilder = selectedCourtsBuilder;
		return this;
	}

	public GroundBookingBuilder selectedPlayersBuild(int selectedPlayersBuilder) {
		this.selectedPlayersBuilder = selectedPlayersBuilder;
		return this;
	}

	public GroundBookingBuilder bookedAtBuild(long bookedAtBuilder) {
		this.bookedAtBuilder = bookedAtBuilder;
		return this;
	}

	public GroundBookingBuilder groundPriceBuild(double groundPriceBuilder) {
		this.groundPriceBuilder = groundPriceBuilder;
		return this;
	}

	
	public GroundBookingBuilder groundIdBuild(int groundIdBuilder) {
		this.groundIdBuilder = groundIdBuilder;
		return this;
	}
	public GroundBookingBuilder requestUserIdBuild(int requestUserIdBuilder) {
		this.requestUserIdBuilder = requestUserIdBuilder;
		return this;
	}

	public GroundBookingBuilder sellerIdBuild(int sellerIdBuilder) {
		this.sellerIdBuilder = sellerIdBuilder;
		return this;
	}

	public GroundBookingBuilder paymentIdBuild(int paymentIdBuilder) {
		this.paymentIdBuilder = paymentIdBuilder;
		return this;
	}

	public GroundBookingBuilder paymentDateBuild(LocalDate paymentDateBuilder) {
		this.paymentDateBuilder = paymentDateBuilder;
		return this;
	}

	public GroundBookingBuilder paymentAmountBuild(double paymentAmountBuilder) {
		this.paymentAmountBuilder = paymentAmountBuilder;
		return this;
	}

	public GroundBookingBuilder paymentMethodBuild(String paymentMethodBuilder) {
		this.paymentMethodBuilder = paymentMethodBuilder;
		return this;
	}

	public GroundBooking build() {
		GroundBooking groundBooking = new GroundBooking();

		groundBooking.setBookingId(bookingIdBuilder);
		groundBooking.setBookingDate(bookingDateBuilder);
		groundBooking.setBookingDuration(bookingDurationBuilder);
		groundBooking.setBookingSports(bookingSportsBuilder);
		groundBooking.setBookingStatus(bookingStatusBuilder);
		groundBooking.setUserBookingStatus(userBookingStatusBuilder);
		;
		groundBooking.setBookingTime(bookingTimeBuilder);
		groundBooking.setSelectedCourts(selectedCourtsBuilder);
		groundBooking.setSelectedPlayers(selectedPlayersBuilder);
		groundBooking.setBookedAt(bookedAtBuilder);
		groundBooking.setGroundPrice(groundPriceBuilder);
		groundBooking.setGroundId(groundIdBuilder);
		groundBooking.setRequestUserId(requestUserIdBuilder);
		groundBooking.setSellerId(sellerIdBuilder);
		groundBooking.setPaymentId(paymentIdBuilder);
		groundBooking.setPaymentDate(paymentDateBuilder);
		groundBooking.setPaymentAmount(paymentAmountBuilder);
		groundBooking.setPaymentMethod(paymentMethodBuilder);
		return groundBooking;

	}
}
