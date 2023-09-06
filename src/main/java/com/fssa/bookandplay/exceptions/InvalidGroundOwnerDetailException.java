package com.fssa.bookandplay.exceptions;

public class InvalidGroundOwnerDetailException extends RuntimeException {
	/**
	 * Creating Custom Exception for Invalid Ground Details
	 */
	private static final long serialVersionUID = -8105491977357554060L;

	/**
	 *  Calling each super constructors for each of the types
	 */
	public InvalidGroundOwnerDetailException(String msg) {
		super(msg);
	}                                                 
 
	public InvalidGroundOwnerDetailException(Throwable te) {
		super(te);
	}
	
	public InvalidGroundOwnerDetailException(String msg, Throwable te) {
		super(msg, te);
	}
}
