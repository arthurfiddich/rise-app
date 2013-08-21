package com.rise.common.util.converters;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomDate extends Date {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomDate.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1930276382732401373L;

	public CustomDate() {
		super();
	}

	public CustomDate(long argDate) {
		super(argDate);
	}

	public CustomDate(String argS) {
		this(parse(argS));
	}

	@SuppressWarnings("deprecation")
	public static long parse(String argToken) {
		if (argToken.isEmpty()) {
			long currentTimeMillis = System.currentTimeMillis();
			if (logger.isTraceEnabled()) {
				logger.trace("Current time in millis: " + currentTimeMillis);
			}
			return currentTimeMillis;
		}
		return Date.parse(argToken);
	}
}
