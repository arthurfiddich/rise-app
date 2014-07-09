package com.data.generato.econpapers;

public class DynamicAssignmentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DynamicAssignmentException() {
		super();
	}

	public DynamicAssignmentException(String argMessage, Throwable argCause,
			boolean argEnableSuppression, boolean argWritableStackTrace) {
		super(argMessage, argCause, argEnableSuppression, argWritableStackTrace);
	}

	public DynamicAssignmentException(String argMessage, Throwable argCause) {
		super(argMessage, argCause);
	}

	public DynamicAssignmentException(String argMessage) {
		super(argMessage);
	}

	public DynamicAssignmentException(Throwable argCause) {
		super(argCause);
	}

}
