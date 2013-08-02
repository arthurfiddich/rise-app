package com.rise.common.util.converters;

import java.lang.reflect.Field;

import com.rise.common.util.checker.Precondition;

public abstract class AbstractConverter implements Converter<String, Object> {

	private enum Type {
		BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE, CHARACTER, BOOLEAN, STRING, BIGINT;
	}

	@Override
	public String convert(Object argToken, Object argFieldToken) {
		Object obj = Precondition.ensureNotNull(argToken, "Input Object");
		Field field = (Field) Precondition
				.ensureNotNull(argFieldToken, "Field");

		Type type = Type.valueOf(field.getType().getName());
		switch (type) {
		case BYTE:
			break;
		case SHORT:
			break;
		case INTEGER:
//			return Integer.parseInt(argToken.toString());
			break;
		case LONG:
			break;
		case FLOAT:
			break;
		case DOUBLE:
			break;
		case CHARACTER:
			break;
		case BOOLEAN:
			break;
		case STRING:
			break;
		case BIGINT:
			break;

		default:
			break;
		}
		return null;
	}

}
