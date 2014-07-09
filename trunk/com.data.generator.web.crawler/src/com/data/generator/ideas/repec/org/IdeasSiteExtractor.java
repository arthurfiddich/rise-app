package com.data.generator.ideas.repec.org;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generato.econpapers.DynamicAssignmentException;
import com.data.generato.econpapers.HtmlExtractorConstants;
import com.data.generato.econpapers.HtmlExtractorUtil;
import com.data.generator.constants.KeyBoardConstants;
import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.file.Reader;
import com.data.generator.file.impl.TextFileReader;
import com.data.generator.ideas.repec.org.Ideas.IdeasBuilder;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

/**
 * 
 * @author Amar
 * 
 */
public class IdeasSiteExtractor {

	private final HtmlExtractor htmlExtractor;
	private final WebContentDownloader webContentDownloader;
	private final List<String> ideasSiteTokensList;
	private final Reader<String, List<String>> reader = new TextFileReader();

	public IdeasSiteExtractor(HtmlExtractor argHtmlExtractor,
			String argInputFileReader) {
		this.htmlExtractor = argHtmlExtractor;
		this.webContentDownloader = new WebContentDownloader();
		this.ideasSiteTokensList = reader.read(argInputFileReader);
	}

	/**
	 * <ul>
	 * All the fields will extracted except Email field. The Extracted fields
	 * are
	 * 
	 * <ul>
	 * <li>Affiliation</li>
	 * <li>Location</li>
	 * <li>HomePage</li>
	 * <li>Phone</li>
	 * <li>Fax</li>
	 * <li>Postal</li>
	 * </ul>
	 * 
	 * Example data is looks like
	 * 
	 * <ul>
	 * <li>Affiliation=Federal Reserve Bank of Chicago</li>
	 * <li>Location=Chicago, Illinois (United States)</li>
	 * <li>HomePage=http://www.chicagofed.org/</li>
	 * <li>Phone=312/322-5111</li>
	 * <li>Fax=312/322-5515</li>
	 * <li>Postal=P.O. Box 834, 230 South LaSalle Street, Chicago, Illinois
	 * 60690-0834</li>
	 * </ul>
	 * </ul>
	 * 
	 * @param argHtmlElementName
	 *            , Based on this Html Element Name, it will extract all the
	 *            elements from the source.
	 * @param argSource
	 *            , This is a Jericho Html source object.
	 * @return
	 */

	public Ideas extract(String argUrl, String argHtmlElementName) {
		if (Precondition.checkNotEmpty(argUrl)
				&& Precondition.checkNotEmpty(argHtmlElementName)) {
			String html = this.getWebContentDownloader().getHtmlContent(argUrl);
			if (Precondition.checkNotEmpty(html)) {
				Source source = this.getHtmlExtractor()
						.getSourceBasedOnHtmlString(html);
				if (Precondition.checkNotNull(source)) {
					List<Affiliation> affiliationList = extractAffiliations(
							argHtmlElementName, source);
					IdeasBuilder ideasBuilder = extractName(source);
					List<String> emailList = extractEmails(source,
							HTMLElementName.SCRIPT);
					List<String> paragraphEmailsList = extractEmails(source,
							HTMLElementName.P);
					if (Precondition.checkNotNull(ideasBuilder)) {
						if (Precondition.checkNotEmpty(affiliationList)) {
							ideasBuilder.setAffiliationList(affiliationList);
						}
						if (Precondition.checkNotEmpty(emailList)) {
							if (Precondition.checkNotEmpty(paragraphEmailsList)) {
								emailList.addAll(paragraphEmailsList);
							}
							ideasBuilder.setEmailList(emailList);
						}
						return ideasBuilder.build();
					}
				}
			}
		}
		return null;
	}

	private List<String> extractEmails(Source argSource, String argElementName) {
		List<Element> scriptElementsList = argSource
				.getAllElements(argElementName);
		if (Precondition.checkNotEmpty(scriptElementsList)) {
			List<String> emailsList = new ArrayList<String>();
			for (Element element : scriptElementsList) {
				String emailToken = element.getContent().getTextExtractor()
						.toString();
				if (Precondition.checkNotEmpty(emailToken)
						&& emailToken
								.startsWith(HtmlExtractorConstants.EMAIL_KEYWORD)) {
					emailToken = prepareEmail(emailToken);
					if (Precondition.checkNotEmpty(emailToken)) {
						emailsList.add(emailToken);
					}
				}
			}
			return emailsList;
		}
		return null;
	}

	private String prepareEmail(String argEmailToken) {
		String email = argEmailToken;
		email = email.substring(0 + HtmlExtractorConstants.EMAIL_KEYWORD
				.length());
		email = HtmlExtractorUtil.removeSpecialSymbol(email,
				KeyBoardConstants.OPEN_PARANTHESIS,
				KeyBoardConstants.CLOSE_PARANTHESIS);
		if (Precondition.checkNotEmpty(email)) {
			String[] emailParts = StringUtils.splitByWholeSeparator(email,
					HtmlExtractorConstants.IDEAS_AT);
			return prepareEmail(emailParts);
		}
		return argEmailToken;
	}

	private String prepareEmail(String[] argEmailParts) {
		if (Precondition.checkNotEmpty(argEmailParts)
				&& argEmailParts.length == 2) {
			StringBuilder emailBuilder = new StringBuilder();
			String localPart = argEmailParts[1];
			String domainPart = argEmailParts[0];
			localPart = prepareEmailPart(localPart);
			domainPart = prepareEmailPart(domainPart);
			if (Precondition.checkNotEmpty(localPart)
					&& Precondition.checkNotEmpty(domainPart)) {
				emailBuilder.append(HtmlExtractorUtil
						.removeSpecialSymbol(localPart, KeyBoardConstants.DOT,
								KeyBoardConstants.DOT));
				emailBuilder.append(KeyBoardConstants.AT);
				emailBuilder.append(HtmlExtractorUtil.removeSpecialSymbol(
						domainPart, KeyBoardConstants.DOT,
						KeyBoardConstants.DOT));
				return emailBuilder.toString();
			}
		}
		return null;
	}

	private String prepareEmailPart(String argEmailPart) {
		String emailPart = argEmailPart;
		if (Precondition.checkNotEmpty(emailPart)) {
			String[] tokenArray = StringUtils.split(emailPart,
					KeyBoardConstants.COMMA);
			if (Precondition.checkNotEmpty(tokenArray)) {
				StringBuilder emailPartBuilder = new StringBuilder();
				for (int i = tokenArray.length - 1; i >= 0; i--) {
					String token = tokenArray[i];
					token = HtmlExtractorUtil.removeSpecialSymbol(token,
							KeyBoardConstants.SINGLE_QUOTE,
							KeyBoardConstants.SINGLE_QUOTE);
					if (Precondition.checkNotEmpty(token)) {
						emailPartBuilder.append(token);
						if (i > 0) {
							emailPartBuilder.append(KeyBoardConstants.DOT);
						}
					}
				}
				return emailPartBuilder.toString();
			}
		}
		return emailPart;
	}

	private IdeasBuilder extractName(Source argSource) {
		List<Element> paragraphElementsList = argSource
				.getAllElements(HTMLElementName.P);
		if (Precondition.checkNotEmpty(paragraphElementsList)) {
			HashMap<String, String> keyVsValueMap = new HashMap<String, String>();
			for (int i = 0; i < paragraphElementsList.size(); i++) {
				Element paragraphElement = paragraphElementsList.get(i);
				String token = paragraphElement.getContent().getTextExtractor()
						.toString();
				if (Precondition.checkNotEmpty(token)
						&& token.startsWith(HtmlExtractorConstants.FIRST_NAME_COLON)) {
					prepareMap(token, keyVsValueMap,
							HtmlExtractorConstants.NAME_PARTS_LIST, 0);
					i = paragraphElementsList.size();
				}
			}
			return (IdeasBuilder) assignValues(keyVsValueMap,
					Ideas.IdeasBuilder.class);
		}
		return null;
	}

	/**
	 * 
	 * All the fields will extracted except Email field. The Extracted fields
	 * are
	 * 
	 * <ul>
	 * <li>Affiliation</li>
	 * <li>Location</li>
	 * <li>HomePage</li>
	 * <li>Phone</li>
	 * <li>Fax</li>
	 * <li>Postal</li>
	 * </ul>
	 * 
	 * Example data is looks like
	 * 
	 * <ul>
	 * <li>Affiliation=Federal Reserve Bank of Chicago</li>
	 * <li>Location=Chicago, Illinois (United States)</li>
	 * <li>HomePage=http://www.chicagofed.org/</li>
	 * <li>Phone=312/322-5111</li>
	 * <li>Fax=312/322-5515</li>
	 * <li>Postal=P.O. Box 834, 230 South LaSalle Street, Chicago, Illinois
	 * 60690-0834</li>
	 * </ul>
	 * 
	 * @param argHtmlElementName
	 *            , Based on this Html Element Name, it will extract all the
	 *            elements from the source.
	 * @param argSource
	 *            , This is a Jericho Html source object.
	 * @return
	 */

	private List<Affiliation> extractAffiliations(String argHtmlElementName,
			Source argSource) {
		List<Element> elementList = argSource
				.getAllElements(argHtmlElementName);
		if (Precondition.checkNotEmpty(elementList)) {
			List<Affiliation> affiliationsList = new ArrayList<Affiliation>();
			for (Element element : elementList) {
				Element ddElement = element.getFirstElement(HTMLElementName.DD);
				if (Precondition.checkNotNull(ddElement)) {
					String ddElementToken = ddElement.getContent()
							.getTextExtractor().toString();
					if (Precondition.checkNotEmpty(ddElementToken)
							&& Precondition.checkNotEmpty(this
									.getIdeasSiteTokensList())) {
						String affiliationToken = element
								.getFirstElement(HTMLElementName.B)
								.getContent().getTextExtractor().toString();
						Map<String, String> keyVsValueMap = new HashMap<String, String>();
						String affiliationKey = this.getIdeasSiteTokensList()
								.get(0);
						keyVsValueMap.put(
								affiliationKey.substring(0,
										affiliationKey.length() - 1)
										.toLowerCase(), affiliationToken);
						prepareMap(ddElementToken, keyVsValueMap,
								this.getIdeasSiteTokensList(), 1);
						Affiliation affiliation = (Affiliation) assignValues(
								keyVsValueMap, Affiliation.class);
						if (Precondition.checkNotNull(affiliation)) {
							affiliationsList.add(affiliation);
						}
					}
				}
			}
			return affiliationsList;
		}
		return null;
	}

	protected void prepareMap(String argElementToken,
			Map<String, String> argKeyVsValueMap,
			List<String> argIdeaTokenList, int argStartIndex) {
		for (int i = argStartIndex; i < argIdeaTokenList.size(); i++) {
			String firstToken = argIdeaTokenList.get(i);
			String secondToken = null;
			if (i == argIdeaTokenList.size() - 1) {
				secondToken = KeyBoardConstants.EMPTY;
			} else {
				secondToken = argIdeaTokenList.get(i + 1);
			}
			String token = get(secondToken, argElementToken).trim();
			argElementToken = prepareDdElementToken(firstToken, token,
					argElementToken);
			String lowerCase = firstToken.substring(0, firstToken.length() - 1)
					.toLowerCase();
			lowerCase = removeSpaces(lowerCase); // Removing spaces because it
													// needs to looks like as
													// variable name.
			argKeyVsValueMap.put(lowerCase, token);
		}
	}

	private String removeSpaces(String argLowerCase) {
		char[] chars = argLowerCase.toCharArray();
		StringBuilder tokenBuilder = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			char ch = chars[i];
			if (ch != HtmlExtractorConstants.SPACE_CHAR) {
				tokenBuilder.append(ch);
			}
		}
		return tokenBuilder.toString();
	}

	private Object assignValues(Map<String, String> argKeyVsValueMap,
			Class<?> argClass) {
		if (Precondition.checkNotEmpty(argKeyVsValueMap)) {
			try {
				PropertyDescriptor[] propertyDescriptorArray = Introspector
						.getBeanInfo(argClass).getPropertyDescriptors();
				if (Precondition.checkNotEmpty(propertyDescriptorArray)) {
					Class<?> clazz = Class.forName(argClass.getName());
					Object obj = clazz.newInstance();
					for (PropertyDescriptor propertyDescriptor : propertyDescriptorArray) {
						String variableName = propertyDescriptor.getName();
						String value = argKeyVsValueMap.get(variableName
								.toLowerCase());
						if (Precondition.checkNotEmpty(value)) {
							Method method = propertyDescriptor.getWriteMethod();
							method.invoke(obj, value);
						}
					}
					return obj;
				}
			} catch (IntrospectionException e) {
				throw new DynamicAssignmentException(
						"Exception while getting the bean info using Introspector: ",
						e);
			} catch (IllegalAccessException e) {
				throw new DynamicAssignmentException(
						"Exception while accessing a method reflectivily: ", e);
			} catch (IllegalArgumentException e) {
				throw new DynamicAssignmentException(
						"Exception while accessing a method reflectivily with illegal arguments: ",
						e);
			} catch (InvocationTargetException e) {
				throw new DynamicAssignmentException(
						"Exception while invoking the method reflectivily: ", e);
			} catch (ClassNotFoundException e) {
				throw new DynamicAssignmentException("Class not founnd: "
						+ argClass.getName(), e);
			} catch (InstantiationException e) {
				throw new DynamicAssignmentException(
						"Exception while creating an istance to the specified class dynamically: "
								+ argClass.getName(), e);
			}
		}
		return null;
	}

	private String prepareDdElementToken(String argKey, String argValue,
			String argDdElementToken) {
		if (Precondition.checkNotEmpty(argKey)
				&& Precondition.checkNotNull(argValue)
				&& Precondition.checkNotEmpty(argDdElementToken)) {
			StringBuilder tokenBuilder = new StringBuilder();
			tokenBuilder.append(argKey);
			tokenBuilder.append(KeyBoardConstants.SPACE);
			tokenBuilder.append(argValue);
			int index = argDdElementToken.indexOf(tokenBuilder.toString());
			if (Precondition.checkNonNegative(index)) {
				return argDdElementToken.substring(index
						+ tokenBuilder.toString().length());
			}
		}
		return KeyBoardConstants.EMPTY;
	}

	private String get(String argSecondToken, String argDdElementToken) {
		String token = argDdElementToken;
		String secondToken = argSecondToken;
		if (Precondition.checkNotEmpty(token)
				&& Precondition.checkNotEmpty(secondToken)) {
			int index = token.indexOf(secondToken);
			if (Precondition.checkNonNegative(index)) {
				token = token.substring(0, index);
			}
		}
		return seperateValue(token);
	}

	protected String seperateValue(String argToken) {
		int colonIndex = argToken.indexOf(KeyBoardConstants.COLON);
		if (Precondition.checkNonNegative(colonIndex)) {
			String[] tokensArray = argToken.split(KeyBoardConstants.COLON
					+ KeyBoardConstants.SPACE);
			if (Precondition.checkNotEmpty(tokensArray)
					&& tokensArray.length == 2) {
				return tokensArray[1];
			}
		}
		return KeyBoardConstants.EMPTY;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public WebContentDownloader getWebContentDownloader() {
		return this.webContentDownloader;
	}

	public List<String> getIdeasSiteTokensList() {
		return this.ideasSiteTokensList;
	}

}