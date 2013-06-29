package com.rise.common.model.generators;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.id.IdentifierGeneratorHelper.BasicHolder;
import org.hibernate.id.IdentifierGeneratorHelper.BigDecimalHolder;
import org.hibernate.id.IdentifierGeneratorHelper.BigIntegerHolder;
import org.hibernate.id.IncrementGenerator;
import org.hibernate.id.IntegralDataTypeHolder;
import org.hibernate.type.Type;

import com.rise.common.util.checker.Precondition;

public class CopyOfIdGenerator extends IncrementGenerator {

	private static final int SCHEME_LENGTH = 12;
	private static final char _PAD_CHAR = '0';
	private final char[] BASE62_CODES = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', };
	private int codesLength;
	private IntegralDataTypeHolder previousValueHolder;
	// private String sql;
	private Class returnClass;
	String sql = "select max(PERSON_ID) from Person";

	public CopyOfIdGenerator() {
		super();
		this.codesLength = this.BASE62_CODES.length;
	}

	@Override
	public void configure(Type argType, Properties argParams, Dialect argDialect)
			throws MappingException {
		returnClass = argType.getReturnedClass();
		super.configure(argType, argParams, argDialect);
	}

	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
//		return new String(getText().concat(getNext(session)));
		return getNext(session);
	}

	private int getNext(SessionImplementor session) {
		int next = 0;

		java.sql.Connection conn = session.connection();

		try {
			// PersistentIdentifierGenerator.SQL.debug(sql);
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = null;
			try {
				rs = st.executeQuery();
				if (rs.next()) {
					if (rs.wasNull())
						next = 1;
					next = rs.getInt(1) + 1;
				} else {
					next = 1;
				}
				sql = null;
			} finally {
				if (rs != null)
					rs.close();
				st.close();
			}

		} catch (SQLException sqle) {
			// throw JDBCExceptionHelper.convert(session.getFactory()
			// .getSQLExceptionConverter(), sqle,
			// "could not fetch initial value", sql);
		}

//		return new Long(next).toString();
		return next;
	}

	public String getText() {
		return "EMP";
	}

	// @Override
	// public synchronized Serializable generate(SessionImplementor argSession,
	// Object argObject) throws HibernateException {
	// IntegralDataTypeHolder value = null;
	// while (value == null) {
	// value = getIntegralDataTypeHolder(returnClass);
	// }
	// return value.makeValue();
	// }

	// @Override
	// public synchronized Serializable generate(SessionImplementor argSession,
	// Object argObject) throws HibernateException {
	// Field privateStringField = null;
	// try {
	// privateStringField = IncrementGenerator.class
	// .getDeclaredField("sql");
	// } catch (NoSuchFieldException e) {
	// e.printStackTrace();
	// } catch (SecurityException e) {
	// e.printStackTrace();
	// }
	// privateStringField.setAccessible(true);
	// System.out.println(privateStringField.toString());
	// initializePreviousValueHolder(argSession);
	// String fieldValue = null;
	// try {
	// fieldValue = (String) privateStringField.get(new IncrementGenerator());
	// } catch (IllegalArgumentException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// System.out.println("fieldValue = " + fieldValue);
	// Serializable se = super.generate(argSession, argObject);
	// System.out.println(se.toString());
	// System.out.println(se.getClass());
	// // return generateId(id);
	// return "";
	// }

	private void initializePreviousValueHolder(SessionImplementor session) {
		previousValueHolder = getIntegralDataTypeHolder(returnClass);

		// LOG.debugf( "Fetching initial value: %s", sql );
		try {
			PreparedStatement st = session.getTransactionCoordinator()
					.getJdbcCoordinator().getStatementPreparer()
					.prepareStatement(sql);
			try {
				ResultSet rs = st.executeQuery();
				try {
					if (rs.next())
						previousValueHolder.initialize(rs, 0L).increment();
					else
						previousValueHolder.initialize(1L);
					// sql = null;
					// if ( LOG.isDebugEnabled() ) {
					// LOG.debugf( "First free id: %s",
					// previousValueHolder.makeValue() );
					// }
				} finally {
					rs.close();
				}
			} finally {
				st.close();
			}
		} catch (SQLException sqle) {
			throw session
					.getFactory()
					.getSQLExceptionHelper()
					.convert(
							sqle,
							"could not fetch initial value for increment generator",
							sql);
		}
	}

	public static IntegralDataTypeHolder getIntegralDataTypeHolder(
			Class<?> integralType) {
		if (integralType == Long.class || integralType == Integer.class
				|| integralType == Short.class) {
			return new BasicHolder(integralType);
		} else if (integralType == BigInteger.class) {
			return new BigIntegerHolder();
		} else if (integralType == BigDecimal.class) {
			return new BigDecimalHolder();
		} else if (integralType == String.class) {
			return new IdGeneratorHolder(integralType);
		} else {
			throw new IdentifierGenerationException(
					"Unknown integral data type for ids : "
							+ integralType.getName());
		}
	}

	private String generateId(Number argId) {
		Number number = Precondition.ensureNotNull(argId,
				"Generated number instance/object from IncrementGenerator");
		int intValue = Precondition.ensureNonNegative(number.intValue(), "ID");
		StringBuilder idBuilder = new StringBuilder();
		while (intValue >= this.getCodesLength()) {
			intValue = buildId(intValue, idBuilder);
		}
		if (intValue % this.getCodesLength() != 0) {
			buildId(intValue, idBuilder);
		}

		return padWithPlaceHolder(idBuilder.reverse().toString());
	}

	private String padWithPlaceHolder(String argIdToken) {
		String id = Precondition.ensureNotEmpty(argIdToken, "Final ID");
		StringBuffer idBuffer = new StringBuffer(id);
		while (idBuffer.length() < SCHEME_LENGTH) {
			idBuffer.insert(0, _PAD_CHAR);
		}
		return idBuffer.toString();
	}

	private int buildId(int argValue, StringBuilder argIdBuilder) {
		int rem = argValue % this.getCodesLength();
		char ch = this.getCodecTable()[rem];
		argIdBuilder.append(ch);
		return argValue / this.getCodesLength();
	}

	public int getCodesLength() {
		return this.codesLength;
	}

	public char[] getCodecTable() {
		return BASE62_CODES;
	}

}
