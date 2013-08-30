package com.rise.common.util.exception;

@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException {

	public DatabaseException() {
		super();
	}

	public DatabaseException(String argMessage, Throwable argCause,
			boolean argEnableSuppression, boolean argWritableStackTrace) {
		super(argMessage, argCause, argEnableSuppression, argWritableStackTrace);
	}

	public DatabaseException(String argMessage, Throwable argCause) {
		super(argMessage, argCause);
	}

	public DatabaseException(String argMessage) {
		super(argMessage);
	}

	public DatabaseException(Throwable argCause) {
		super(argCause);
	}

}
