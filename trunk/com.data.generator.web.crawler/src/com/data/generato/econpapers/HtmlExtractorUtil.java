package com.data.generato.econpapers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

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

	public static String getTitle(Source argSource) {
		if (Precondition.checkNotNull(argSource)) {
			Element titleElement = argSource
					.getFirstElement(HTMLElementName.TITLE);
			if (Precondition.checkNotNull(titleElement)) {
				// TITLE element never contains other tags so just decode it
				// collapsing whitespace:
				return CharacterReference.decodeCollapseWhiteSpace(titleElement
						.getContent());
			}
		}
		return null;
	}

	/*
	 * This title we are extracting from the site called
	 * "http://ideas.repec.org/e/paa13.html".
	 */
	public static String getIdeasTitle(Source argSource) {
		if (Precondition.checkNotNull(argSource)) {
			Element titleElement = argSource.getElementById("title");
			if (Precondition.checkNotNull(titleElement)) {
				return CharacterReference.decodeCollapseWhiteSpace(titleElement
						.getContent());
			}
		}
		return null;
	}

	public static Pattern getSplitDetectorPattern() {
		return Pattern.compile("([\\r\\n])+");
	}

	// public static String removeStartEndSingleQuotes(String argPart) {
	// String token = argPart;
	// if (Precondition.checkNotEmpty(token)) {
	// if (token.startsWith(KeyBoardConstants.SINGLE_QUOTE)) {
	// token = token.substring(1);
	// }
	// if (token.endsWith(KeyBoardConstants.SINGLE_QUOTE)) {
	// token = token.substring(0, token.length() - 1);
	// }
	// }
	// return token;
	// }

	public static String removeSpecialSymbol(String argToken,
			String argOpenSpecialSymbol, String argCloseSpecialSymbol) {
		String token = argToken;
		if (Precondition.checkNotEmpty(token)) {
			if (token.startsWith(argOpenSpecialSymbol)) {
				token = token.substring(1);
			}
			if (token.endsWith(argCloseSpecialSymbol)) {
				token = token.substring(0, token.length() - 1);
			}
		}
		return token;
	}
	
	/*
	 * It will give a list of annotated fields for a given class instance
	 */
	public static List<Field> getAnnotatedFields(Class<?> argClassInstance,
			Class<? extends Annotation> argAnnotationType) {
		if (Precondition.checkNotNull(argClassInstance)
				&& Precondition.checkNotNull(argAnnotationType)) {
			Field[] fieldArray = argClassInstance.getDeclaredFields();
			if (Precondition.checkNotEmpty(fieldArray)) {
				List<Field> annotatedFieldsList = new ArrayList<Field>();
				for (Field field : fieldArray) {
					if (Precondition.checkNotNull(field
							.getAnnotation(argAnnotationType))) {
						annotatedFieldsList.add(field);
					}
				}
				return annotatedFieldsList;
			}
		}
		return null;
	}
}