package com.fssa.bookandplay.exceptions;

public class InvalidMessageException  extends Exception {

	/**
	 * Creating Custom Exception for DAOException 
	 */
	private static final long serialVersionUID = -8105491977357554060L;
	/**
	 *  Calling each super constructors for each of the types
	 */
	
	public InvalidMessageException(String msg) {
		super(msg);
	}

	public InvalidMessageException(Throwable te) {
		super(te);
	}

	public InvalidMessageException(String msg, Throwable te) {
		super(msg, te);
	}
}
