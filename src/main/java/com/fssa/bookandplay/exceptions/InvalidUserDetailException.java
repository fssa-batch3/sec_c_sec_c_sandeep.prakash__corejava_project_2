package com.fssa.bookandplay.exceptions;

public class InvalidUserDetailException extends RuntimeException {
	/**
	 * Creating Custom Exception for Invalid Ground Details
	 */
	private static final long serialVersionUID = -8105491977357554060L;

	/**
	 *  Calling each super constructors for each of the types
	 */
	public InvalidUserDetailException(String msg) {
		super(msg);
	}                                                 
 
	public InvalidUserDetailException(Throwable te) {
		super(te);
	}
	
	public InvalidUserDetailException(String msg, Throwable te) {
		super(msg, te);
	}
}
