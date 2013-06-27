package com.rise.common.model.generators;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IncrementGenerator;

import com.rise.common.util.checker.Precondition;

public class IdGenerator extends IncrementGenerator {

	private static final int SCHEME_LENGTH = 12;
	private static final char _PAD_CHAR = '0';
	private final char[] BASE62_CODES = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', };
	private int codesLength;

	public IdGenerator() {
		super();
		this.codesLength = this.BASE62_CODES.length;
	}

	@Override
	public synchronized Serializable generate(SessionImplementor argSession,
			Object argObject) throws HibernateException {
		Number id = (Number) super.generate(argSession, argObject);
		return generateId(id);
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
