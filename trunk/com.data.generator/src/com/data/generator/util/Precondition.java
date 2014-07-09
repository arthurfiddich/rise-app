package com.data.generator.util;

import java.util.Collection;
import java.util.Map;

public class Precondition {

	public static boolean checkNotNull(Object argValue) {
		return argValue != null;
	}

	public static boolean checkNull(Object argValue) {
		return argValue == null;
	}

	public static <T> T ensureNotNull(T argValue, String argParamName)
			throws PreconditionException {
		if (!checkNotNull(argValue)) {
			throw new PreconditionException("Expected not to be null: "
					+ argParamName);
		}
		return argValue;
	}

	public static boolean checkNotEmpty(Collection<?> argValue) {
		return checkNotNull(argValue) && !argValue.isEmpty();
	}

	public static boolean checkNotEmpty(Map<?, ?> argValue) {
		return checkNotNull(argValue) && !argValue.isEmpty();
	}

	public static <T> Collection<T> ensureNotEmpty(Collection<T> argValue,
			String argParamName) throws PreconditionException {
		ensureNotNull(argValue, argParamName);
		if (argValue.isEmpty()) {
			throw new PreconditionException("Expected not to be empty: "
					+ argParamName);
		}
		return argValue;
	}

	public static boolean checkNotEmpty(byte[] argValue) {
		return checkNotNull(argValue) && argValue.length > 0;
	}

	public static byte[] ensureNotEmpty(byte[] argValue, String argParamName)
			throws PreconditionException {
		ensureNotNull(argValue, argParamName);
		if (argValue.length == 0) {
			throw new PreconditionException("Expected not to be empty: "
					+ argParamName);
		}
		return argValue;
	}

	public static Object[] ensureNotEmpty(Object[] argValue, String argParamName)
			throws PreconditionException {
		ensureNotNull(argValue, argParamName);
		if (argValue.length == 0) {
			throw new PreconditionException("Expected not to be empty: "
					+ argParamName);
		}
		return argValue;
	}

	public static boolean checkNotEmpty(String argValue) {
		return checkNotNull(argValue) && !argValue.isEmpty();
	}

	public static String ensureNotEmpty(String argValue, String argParamName)
			throws PreconditionException {
		ensureNotNull(argValue, argParamName);
		if (argValue.isEmpty()) {
			throw new PreconditionException("Expected not to be empty: "
					+ argParamName);
		}
		return argValue;
	}

	public static boolean checkNonNegative(int argValue) {
		// if (argValue == -1) {
		// throw new PreconditionException(
		// "Expected value not to be negitive: " + argValue);
		// }
		return argValue > -1;
	}

	public static int ensureNonNegative(int argValue, String argParamName)
			throws PreconditionException {
		if (argValue == -1) {
			throw new PreconditionException("Expected to be non-negative: "
					+ argParamName);
		}
		return argValue;
	}

	public static boolean checkZero(int argValue) {
		return (argValue == 0);
	}

	public static boolean checkNotEmpty(Object[] argValue) {
		return checkNotNull(argValue) && argValue.length > 0;
	}

	public static boolean checkEmpty(Collection<?> argValue) {
		return checkNotNull(argValue) && argValue.isEmpty();
	}

	public static boolean checkEmpty(String argValue) {
		return checkNotNull(argValue) && argValue.isEmpty();
	}

	public static boolean checkZero(double argValue) {
		return (argValue == 0.0);
	}

	public static Map<?, ?> ensureNotEmpty(Map<?, ?> argMap, String argParamName) {
		ensureNotNull(argMap, argParamName);
		if (argMap.isEmpty()) {
			throw new PreconditionException("Expected not to be empty: "
					+ argParamName);
		}
		return argMap;
	}
}
