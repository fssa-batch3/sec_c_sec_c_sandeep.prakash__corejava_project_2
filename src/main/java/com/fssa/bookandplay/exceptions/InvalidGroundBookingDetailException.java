package com.fssa.bookandplay.exceptions;

public class InvalidGroundBookingDetailException extends RuntimeException {
	/**
	 * Creating Custom Exception for Invalid Ground Details
	 */
	private static final long serialVersionUID = -8105491977357554060L;

	/**
	 *  Calling each super constructors for each of the types
	 */
	public InvalidGroundBookingDetailException(String msg) {
		super(msg);
	}                                                 
 
	public InvalidGroundBookingDetailException(Throwable te) {
		super(te);
	}
	
	public InvalidGroundBookingDetailException(String msg, Throwable te) {
		super(msg, te);
	}
}
