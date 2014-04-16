package com.data.generator.google.place.api.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseUncheckedException.
 * 
 * @author chakra
 */
public class BaseUncheckedException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new base unchecked exception.
	 */
	public BaseUncheckedException() {
		super();
	}

	/**
	 * Instantiates a new base unchecked exception.
	 * 
	 * @param argMessage the message
	 * @param argCause the cause
	 */
	public BaseUncheckedException(String argMessage, Throwable argCause) {
		super(argMessage, argCause);
	}

	/**
	 * Instantiates a new base unchecked exception.
	 * 
	 * @param argMessage the message
	 */
	public BaseUncheckedException(String argMessage) {
		super(argMessage);
	}

	/**
	 * Instantiates a new base unchecked exception.
	 * 
	 * @param argCause the cause
	 */
	public BaseUncheckedException(Throwable argCause) {
		super(argCause);
	}

}
