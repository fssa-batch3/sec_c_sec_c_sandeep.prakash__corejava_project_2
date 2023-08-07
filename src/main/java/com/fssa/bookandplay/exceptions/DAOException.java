package com.fssa.bookandplay.exceptions;

public class DAOException extends Exception {
	/**
	 * Creating Custom Exception for DAOException 
	 */
	private static final long serialVersionUID = -8105491977357554060L;
	/**
	 *  Calling each super constructors for each of the types
	 */
	
	public DAOException(String msg) {
		super(msg);
	}

	public DAOException(Throwable te) {
		super(te);
	}

	public DAOException(String msg, Throwable te) {
		super(msg, te);
	}
}
