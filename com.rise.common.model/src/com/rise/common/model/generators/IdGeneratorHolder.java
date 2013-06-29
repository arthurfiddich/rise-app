package com.rise.common.model.generators;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.id.IdentifierGeneratorHelper.BasicHolder;
import org.hibernate.id.IntegralDataTypeHolder;

public class IdGeneratorHolder implements IntegralDataTypeHolder {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Class<?> exactType;
	private long value = Long.MIN_VALUE;

	public IdGeneratorHolder(Class<?> exactType) {
		this.exactType = exactType;
		if (exactType != Long.class && exactType != Integer.class
				&& exactType != Short.class && exactType != String.class) {
			throw new IdentifierGenerationException(
					"Invalid type for basic integral holder : " + exactType);
		}
	}

	public long getActualLongValue() {
		return value;
	}

	public IntegralDataTypeHolder initialize(long value) {
		this.value = value;
		return this;
	}

	public IntegralDataTypeHolder initialize(ResultSet resultSet,
			long defaultValue) throws SQLException {
		long value = resultSet.getLong(1);
		if (resultSet.wasNull()) {
			value = defaultValue;
		}
		return initialize(value);
	}

	public void bind(PreparedStatement preparedStatement, int position)
			throws SQLException {
		// TODO : bind it as 'exact type'? Not sure if that gains us
		// anything...
		preparedStatement.setLong(position, value);
	}

	public IntegralDataTypeHolder increment() {
		checkInitialized();
		value++;
		return this;
	}

	private void checkInitialized() {
		if (value == Long.MIN_VALUE) {
			throw new IdentifierGenerationException(
					"integral holder was not initialized");
		}
	}

	public IntegralDataTypeHolder add(long addend) {
		checkInitialized();
		value += addend;
		return this;
	}

	public IntegralDataTypeHolder decrement() {
		checkInitialized();
		value--;
		return this;
	}

	public IntegralDataTypeHolder subtract(long subtrahend) {
		checkInitialized();
		value -= subtrahend;
		return this;
	}

	public IntegralDataTypeHolder multiplyBy(IntegralDataTypeHolder factor) {
		return multiplyBy(extractLong(factor));
	}

	public IntegralDataTypeHolder multiplyBy(long factor) {
		checkInitialized();
		value *= factor;
		return this;
	}

	public boolean eq(IntegralDataTypeHolder other) {
		return eq(extractLong(other));
	}

	public boolean eq(long value) {
		checkInitialized();
		return this.value == value;
	}

	public boolean lt(IntegralDataTypeHolder other) {
		return lt(extractLong(other));
	}

	public boolean lt(long value) {
		checkInitialized();
		return this.value < value;
	}

	public boolean gt(IntegralDataTypeHolder other) {
		return gt(extractLong(other));
	}

	public boolean gt(long value) {
		checkInitialized();
		return this.value > value;
	}

	public IntegralDataTypeHolder copy() {
		IdGeneratorHolder copy = new IdGeneratorHolder(exactType);
		copy.value = value;
		return copy;
	}

	public Number makeValue() {
		// TODO : should we check for truncation?
//		checkInitialized();
		if (exactType == Long.class) {
			return value;
		} else if (exactType == Integer.class) {
			return (int) value;
		} else {
			return (short) value;
		}
	}

	public Number makeValueThenIncrement() {
		final Number result = makeValue();
		value++;
		return result;
	}

	public Number makeValueThenAdd(long addend) {
		final Number result = makeValue();
		value += addend;
		return result;
	}

	@Override
	public String toString() {
		return "BasicHolder[" + exactType.getName() + "[" + value + "]]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		IdGeneratorHolder that = (IdGeneratorHolder) o;

		return value == that.value;
	}

	@Override
	public int hashCode() {
		return (int) (value ^ (value >>> 32));
	}

	public static long extractLong(IntegralDataTypeHolder holder) {
		if (holder.getClass() == BasicHolder.class) {
			((IdGeneratorHolder) holder).checkInitialized();
			return ((IdGeneratorHolder) holder).value;
		}
		throw new IdentifierGenerationException(
				"Unknown IntegralDataTypeHolder impl [" + holder + "]");
	}
}
