package com.fssa.bookandplay.exceptions;

public class ServiceException extends Exception{

	/**
	 * Creating Custom Exception for DAOException 
	 */
	private static final long serialVersionUID = -8105491977357554060L;
	/**
	 *  Calling each super constructors for each of the types
	 */
	
	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(Throwable te) {
		super(te);
	}

	public ServiceException(String msg, Throwable te) {
		super(msg, te);
	}
}
