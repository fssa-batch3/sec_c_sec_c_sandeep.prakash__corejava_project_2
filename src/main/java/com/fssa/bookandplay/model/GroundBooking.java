package com.fssa.bookandplay.model;

import java.time.LocalDate;
import java.util.List;

public class GroundBooking {
	private int bookingId;
	private LocalDate bookingDate;
    private String bookingDuration;
    private String bookingSports;
    private boolean bookingStatus;
    private List<String> bookingTime;
    private String selectedCourts;
    private String selectedPlayers;
    private long createdAt;
    private double groundPrice;
    private int groundId;
    private int requestUserId;
    private int sellerId;
    private int paymentId;
    private LocalDate paymentDate;
    private double paymentAmount;
    private String paymentMethod;

}
