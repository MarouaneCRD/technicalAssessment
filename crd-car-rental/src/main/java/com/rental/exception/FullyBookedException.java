package com.rental.exception;

public class FullyBookedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7411963817691017998L;

	public FullyBookedException() {
		// TODO Auto-generated constructor stub
	}

	public FullyBookedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FullyBookedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public FullyBookedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FullyBookedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
