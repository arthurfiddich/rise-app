package com.data.generator.util;

public class PreconditionException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PreconditionException() {
		super();
	}

	public PreconditionException(String message, Throwable cause) {
		super(message, cause);
	}

	public PreconditionException(String s) {
		super(s);
	}

	public PreconditionException(Throwable cause) {
		super(cause);
	}
}
