package com.data.generato.econpapers;

import com.data.generator.constants.KeyBoardConstants;
import com.data.generator.util.Precondition;

public class HtmlExtractorUtil {

	public static String removeBrackets(String argLabel) {
		String label = argLabel;
		if (Precondition.checkNotEmpty(argLabel)) {
			if (argLabel.startsWith(KeyBoardConstants.OPEN_PARANTHESIS)) {
				label = label.substring(KeyBoardConstants.OPEN_PARANTHESIS
						.length());
			}
			if (argLabel.endsWith(KeyBoardConstants.CLOSE_PARANTHESIS)) {
				int length = KeyBoardConstants.CLOSE_PARANTHESIS.length();
				label = label.substring(0, label.length() - length);
			}
		}
		return label;
	}

	/*
	 * Example input: 'ssb.no', 'rolf.aaberge'
	 */
	public static Email splitAndConstructEmail(String argToken) {
		String token = argToken;
		if (Precondition.checkNotEmpty(token)) {
			token = token.trim();
			String[] tokenArray = token.split(KeyBoardConstants.COMMA);
			if (Precondition.checkNotEmpty(tokenArray)
					&& tokenArray.length == 2) {
				return constructEmail(tokenArray[1], tokenArray[0]);
			}
		}
		return null;
	}

	private static Email constructEmail(String argLocalPart,
			String argDomainPart) {
		String localPart = removeSingleQuotes(argLocalPart.trim());
		String domainPart = removeSingleQuotes(argDomainPart.trim());
		return new Email(localPart, KeyBoardConstants.AT, domainPart);
	}

	private static String removeSingleQuotes(String argToken) {
		String token = argToken;
		if (token.startsWith(KeyBoardConstants.SINGLE_QUOTE)) {
			token = token.substring(KeyBoardConstants.SINGLE_QUOTE.length());
		}
		if (token.endsWith(KeyBoardConstants.SINGLE_QUOTE)) {
			token = token.substring(0, token.length()
					- KeyBoardConstants.SINGLE_QUOTE.length());
		}
		return token;
	}

}
