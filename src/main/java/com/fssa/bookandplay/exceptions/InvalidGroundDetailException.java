package com.fssa.bookandplay.exceptions;

public class InvalidGroundDetailException extends RuntimeException {
	/**
	 * Creating Custom Exception for Invalid Ground Details
	 */
	private static final long serialVersionUID = -8105491977357554060L;

	/**
	 *  Calling each super constructors for each of the types
	 */
	public InvalidGroundDetailException(String msg) {
		super(msg);
	}                                                 
 
	public InvalidGroundDetailException(Throwable te) {
		super(te);
	}
	
	public InvalidGroundDetailException(String msg, Throwable te) {
		super(msg, te);
	}
}