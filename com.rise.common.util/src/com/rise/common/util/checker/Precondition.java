package com.rise.common.util.checker;

import java.util.Collection;

public class Precondition {

	public static boolean checkNotNull(Object value) {
		return value != null;
	}

	public static <T> T ensureNotNull(T value, String paramName)
			throws PreconditionException {
		if (!checkNotNull(value)) {
			throw new PreconditionException("Expected not to be null: "
					+ paramName);
		}
		return value;
	}

	public static boolean checkNotEmpty(Collection<?> value) {
		return checkNotNull(value) && !value.isEmpty();
	}

	public static <T> Collection<T> ensureNotEmpty(Collection<T> value,
			String paramName) throws PreconditionException {
		ensureNotNull(value, paramName);
		if (value.isEmpty()) {
			throw new PreconditionException("Expected not to be empty: "
					+ paramName);
		}
		return value;
	}

	public static boolean checkNotEmpty(byte[] value) {
		return checkNotNull(value) && value.length > 0;
	}

	public static byte[] ensureNotEmpty(byte[] value, String paramName)
			throws PreconditionException {
		ensureNotNull(value, paramName);
		if (value.length == 0) {
			throw new PreconditionException("Expected not to be empty: "
					+ paramName);
		}
		return value;
	}

	public static boolean checkNotEmpty(String value) {
		return checkNotNull(value) && !value.isEmpty();
	}

	public static String ensureNotEmpty(String value, String paramName)
			throws PreconditionException {
		ensureNotNull(value, paramName);
		if (value.isEmpty()) {
			throw new PreconditionException("Expected not to be empty: "
					+ paramName);
		}
		return value;
	}

	public static boolean checkNonNegative(int value) {
		if (value == -1) {
			throw new PreconditionException(
					"Expected value not to be negitive: " + value);
		}
		return value > -1;
	}

	public static int ensureNonNegative(int value, String paramName)
			throws PreconditionException {
		if (value == -1) {
			throw new PreconditionException("Expected to be non-negative: "
					+ paramName);
		}
		return value;
	}
	
	public static boolean checkNotEmpty(Object[] value) {
		return checkNotNull(value) && value.length > 0;
	}
}
