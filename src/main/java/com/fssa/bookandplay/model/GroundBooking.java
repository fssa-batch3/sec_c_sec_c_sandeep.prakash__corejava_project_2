package com.fssa.bookandplay.model;

import java.time.LocalDate;
import java.util.List;

public class GroundBooking {
	private int bookingId;
	private LocalDate bookingDate;
	private String bookingDuration;
	private String bookingSports;
	private boolean bookingStatus;
	private boolean userBookingStatus;
	private List<String> bookingTime;
	private String selectedCourts;
	private int selectedPlayers;
	private long bookedAt;
	private double groundPrice;
	private int groundId;
	private int requestUserId;
	private int sellerId;
	private int paymentId;
	private LocalDate paymentDate;
	private double paymentAmount;
	private String paymentMethod;
	private Ground ground;
	private GroundOwner groundOwner;
	private User user;

	@Override
	public String toString() {
		return "GroundBooking [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", bookingDuration="
				+ bookingDuration + ", bookingSports=" + bookingSports + ", bookingStatus=" + bookingStatus
				+ ", userBookingStatus=" + userBookingStatus + ", bookingTime=" + bookingTime + ", selectedCourts="
				+ selectedCourts + ", selectedPlayers=" + selectedPlayers + ", bookedAt=" + bookedAt + ", groundPrice="
				+ groundPrice + ", groundId=" + groundId + ", requestUserId=" + requestUserId + ", sellerId=" + sellerId
				+ ", paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", paymentAmount=" + paymentAmount
				+ ", paymentMethod=" + paymentMethod + ", ground=" + ground + ", groundOwner=" + groundOwner + ", user="
				+ user + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GroundOwner getGroundOwner() {
		return groundOwner;
	}

	public void setGroundOwner(GroundOwner groundOwner) {
		this.groundOwner = groundOwner;
	}

	public Ground getGround() {
		return ground;
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBookingDuration() {
		return bookingDuration;
	}

	public void setBookingDuration(String bookingDuration) {
		this.bookingDuration = bookingDuration;
	}

	public String getBookingSports() {
		return bookingSports;
	}

	public void setBookingSports(String bookingSports) {
		this.bookingSports = bookingSports;
	}

	public boolean isBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(boolean bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public boolean isUserBookingStatus() {
		return userBookingStatus;
	}

	public void setUserBookingStatus(boolean userBookingStatus) {
		this.userBookingStatus = userBookingStatus;
	}

	public List<String> getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(List<String> bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getSelectedCourts() {
		return selectedCourts;
	}

	public void setSelectedCourts(String selectedCourts) {
		this.selectedCourts = selectedCourts;
	}

	public int getSelectedPlayers() {
		return selectedPlayers;
	}

	public void setSelectedPlayers(int selectedPlayers) {
		this.selectedPlayers = selectedPlayers;
	}

	public long getBookedAt() {
		return bookedAt;
	}

	public void setBookedAt(long createdAt) {
		this.bookedAt = createdAt;
	}

	public double getGroundPrice() {
		return groundPrice;
	}

	public void setGroundPrice(double groundPrice) {
		this.groundPrice = groundPrice;
	}

	public int getGroundId() {
		return groundId;
	}

	public void setGroundId(int groundId) {
		this.groundId = groundId;
	}

	public int getRequestUserId() {
		return requestUserId;
	}

	public void setRequestUserId(int requestUserId) {
		this.requestUserId = requestUserId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
