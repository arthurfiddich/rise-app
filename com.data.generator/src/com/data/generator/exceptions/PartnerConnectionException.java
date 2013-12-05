package com.data.generator.exceptions;

public class PartnerConnectionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PartnerConnectionException() {
		super();
	}

	public PartnerConnectionException(String argMessage, Throwable argCause,
			boolean argEnableSuppression, boolean argWritableStackTrace) {
		super(argMessage, argCause, argEnableSuppression, argWritableStackTrace);
	}

	public PartnerConnectionException(String argMessage, Throwable argCause) {
		super(argMessage, argCause);
	}

	public PartnerConnectionException(String argMessage) {
		super(argMessage);
	}

	public PartnerConnectionException(Throwable argCause) {
		super(argCause);
	}

}
