package ssrn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;

import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;

public class Parse {

	private HtmlExtractor htmlExtractor = new HtmlExtractor();

	public Map<String, String> getNameVsEmailUrlMap(String argHtmlToken) {
		String htmlToken = Precondition.ensureNotEmpty(argHtmlToken,
				"Html Token");
		Source source = this.htmlExtractor
				.getSourceBasedOnHtmlString(htmlToken);
		Precondition.ensureNotNull(source, "Html Source Object");
		List<Element> tableRowDarkElementsList = source
				.getAllElementsByClass("TableRowDark");
		Map<String, String> nameVsEmailUrlMap = new HashMap<String, String>();
		Map<String, String> tableRowDarkElementsMap = process(tableRowDarkElementsList);
		if (Precondition.checkNotEmpty(tableRowDarkElementsMap)) {
			nameVsEmailUrlMap.putAll(tableRowDarkElementsMap);
		}
		List<Element> tableRowLightElementsList = source
				.getAllElementsByClass("TableRowLight");
		Map<String, String> tableRowLightElementsMap = process(tableRowLightElementsList);
		if (Precondition.checkNotEmpty(tableRowDarkElementsMap)) {
			nameVsEmailUrlMap.putAll(tableRowLightElementsMap);
		}
		return nameVsEmailUrlMap;
	}

	protected Map<String, String> process(List<Element> tableRowDarkElementsList) {
		if (Precondition.checkNotEmpty(tableRowDarkElementsList)) {
			Map<String, String> nameVsEmailUrlMap = new HashMap<String, String>();
			for (Element element : tableRowDarkElementsList) {
				List<Element> tdElementsList = element
						.getAllElements(HTMLElementName.TD);
				if (Precondition.checkNotEmpty(tdElementsList)) {
					for (Element tdElement : tdElementsList) {
						Element nameElement = tdElement.getFirstElement(
								"align", "left", true);
						if (Precondition.checkNotNull(nameElement)) {
							StartTag startTag = nameElement
									.getFirstStartTag(HTMLElementName.A);
							if (Precondition.checkNotNull(startTag)) {
								String email = startTag
										.getAttributeValue("href");
								String name = nameElement.getContent()
										.getTextExtractor().toString();
								if (Precondition.checkNotEmpty(email)
										&& Precondition.checkNotEmpty(name)) {
									nameVsEmailUrlMap.put(name, email);
								}
							}
						}
					}
				}
			}
			return nameVsEmailUrlMap;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserInfo(Map<String, String> argNameVsEmailUrlMap,
			HttpClient argHttpClient) {
		Map<String, String> nameVsEmailUrlMap = (Map<String, String>) Precondition
				.ensureNotEmpty(argNameVsEmailUrlMap, "NameVsEmailUrlMap");
		Iterator<Entry<String, String>> iterator = nameVsEmailUrlMap.entrySet()
				.iterator();
		List<User> usersList = new ArrayList<User>();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			String name = entry.getKey();
			String emailUrl = entry.getValue();
			List<String> emailsList = getEmails(emailUrl, argHttpClient);
			if (Precondition.checkNotEmpty(emailsList)
					&& Precondition.checkNotEmpty(name)) {
				User user = new User(name, emailsList);
				usersList.add(user);
			}
		}
		return usersList;
	}

	public List<String> getEmails(String argEmailUrl, HttpClient argHttpClient) {
		if (Precondition.checkNotEmpty(argEmailUrl)) {
			String emailUrl = argEmailUrl;
			HttpRequest httpRequest = prepareRequest(emailUrl);
			String emailContent = getResponse(argHttpClient, httpRequest);
			if (Precondition.checkNotEmpty(emailContent)) {
				Source source = this.getHtmlExtractor()
						.getSourceBasedOnHtmlString(emailContent);
				if (Precondition.checkNotNull(source)) {
					Element affiliationElement = source
							.getFirstElementByClass("affiliation");
					if (Precondition.checkNotNull(affiliationElement)) {
						Element emailElement = affiliationElement
								.getFirstElement("href", "#", true);
						if (Precondition.checkNotNull(emailElement)) {
							String emailLink = emailElement
									.getAttributeValue("onclick");
							if (Precondition.checkNotEmpty(emailLink)
									&& emailLink.startsWith("window.open(")) {
								emailLink = emailLink.replaceAll(
										"window.open\\(", "");
								emailLink = emailLink.substring(0,
										emailLink.length() - 1);
							}
							if (Precondition.checkNotEmpty(emailLink)) {
								return getEmailList(emailLink, argHttpClient);
							}
						}
					}
				}
			}
		}
		return null;
	}

	private List<String> getEmailList(String argEmailLink,
			HttpClient argHttpClient) {
		String[] tokens = argEmailLink.split(",");
		if (Precondition.checkNotEmpty(tokens) && tokens.length > 1) {
			String emailLink = tokens[0];
			if (Precondition.checkNotEmpty(emailLink)) {
				emailLink = emailLink.substring(1, emailLink.length() - 1);
				emailLink = emailLink.replaceAll("../", "");
				emailLink = "http://papers.ssrn.com/sol3/" + emailLink;
				HttpRequest httpRequest = prepareRequest(emailLink);
				String emailContent = getResponse(argHttpClient, httpRequest);
				List<String> emailsList = new ArrayList<String>();
				if (Precondition.checkNotEmpty(emailContent)) {
					Source emailSource = this.getHtmlExtractor()
							.getSourceBasedOnHtmlString(emailContent);
					if (Precondition.checkNotNull(emailSource)) {
						List<Element> tdElementsList = emailSource
								.getAllElements("align", "left", true);
						if (Precondition.checkNotEmpty(tdElementsList)) {
							for (Element tdElement : tdElementsList) {
								Element anchorTagElement = tdElement
										.getFirstElement(HTMLElementName.A);
								if (Precondition.checkNotNull(anchorTagElement)) {
									String attributeValue = anchorTagElement
											.getAttributeValue("href");
									if (Precondition
											.checkNotEmpty(attributeValue)
											&& attributeValue
													.startsWith("mailto:")) {
										String email = anchorTagElement
												.getContent()
												.getTextExtractor().toString();
										if (Precondition.checkNotEmpty(email)) {
											emailsList.add(email);
										}
									}
								}
							}
						}
					}
					return emailsList;
				}
			}
		}
		return null;
	}

	protected String getResponse(HttpClient argHttpClient,
			HttpRequest httpRequest) {
		ExecutorTest executorTest = new ExecutorTest();
		return executorTest.execute(argHttpClient, httpRequest);
	}

	public HttpRequest prepareRequest(String argUrl) {
		HttpRequest loginRequest = new HttpGet(argUrl);
		loginRequest
				.setHeader(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36");
		return loginRequest;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public void write(List<User> argUsersList, int argPageCount) {
		List<User> usersList = (List<User>) Precondition.ensureNotEmpty(
				argUsersList, "Users List");
		int emailCount = getEmailCount(usersList);
		Map<String[], List<List<String>>> headerArrayVsTokensListMap = new HashMap<String[], List<List<String>>>();
		List<List<String>> tokensList = new ArrayList<List<String>>();
		for (User user : usersList) {
			List<String> tokens = new ArrayList<String>();
			tokens.add(user.getName());
			List<String> emailsList = user.getEmailsList();
			if (Precondition.checkNonNegative(emailCount)) {
				int size = emailsList.size();
				for (int i = 0; i < emailCount - size; i++) {
					emailsList.add("");
				}
			}
			tokens.addAll(emailsList);
			tokensList.add(tokens);
		}
		List<String> headersList = new ArrayList<String>();
		headersList.add("Name");
		for (int i = 0; i < emailCount; i++) {
			headersList.add("Email-" + (i + 1));
		}
		String[] headerArray = headersList.toArray(new String[headersList
				.size()]);
		headerArrayVsTokensListMap.put(headerArray, tokensList);
		try {
			CsvWriter csvWriter = new CsvWriter("./output/business/" + argPageCount
					+ ".csv");
			csvWriter.write(headerArrayVsTokensListMap);
		} catch (Exception e) {
			throw new RuntimeException(
					"Exception while writing the file content into a file: ", e);
		}
	}

	private int getEmailCount(List<User> argUsersList) {
		int emailCount = 0;
		for (User user : argUsersList) {
			int emailsSize = user.getEmailsList().size();
			if (emailCount < emailsSize) {
				emailCount = emailsSize;
			}
		}
		return emailCount;
	}
}
