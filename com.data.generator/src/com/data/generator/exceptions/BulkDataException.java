package com.data.generator.exceptions;

public class BulkDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BulkDataException() {
		super();
	}

	public BulkDataException(String argMessage, Throwable argCause,
			boolean argEnableSuppression, boolean argWritableStackTrace) {
		super(argMessage, argCause, argEnableSuppression, argWritableStackTrace);
	}

	public BulkDataException(String argMessage, Throwable argCause) {
		super(argMessage, argCause);
	}

	public BulkDataException(String argMessage) {
		super(argMessage);
	}

	public BulkDataException(Throwable argCause) {
		super(argCause);
	}

}
