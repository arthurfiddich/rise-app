package com.data.generato.econpapers;

public class HtmlParserException extends RuntimeException {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public HtmlParserException() {
		super();
	}

	public HtmlParserException(String argMessage, Throwable argCause,
			boolean argEnableSuppression, boolean argWritableStackTrace) {
		super(argMessage, argCause, argEnableSuppression, argWritableStackTrace);
	}

	public HtmlParserException(String argMessage, Throwable argCause) {
		super(argMessage, argCause);
	}

	public HtmlParserException(String argMessage) {
		super(argMessage);
	}

	public HtmlParserException(Throwable argCause) {
		super(argCause);
	}

}
