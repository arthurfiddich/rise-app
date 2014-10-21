package www.iese.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;

public class CollectUrls {

	private final HtmlExtractor htmlExtractor;
	private final PageSource pageSource;
	private static final String FIRST_PART = "http://www.iese.edu/en/faculty-research/professors/?paginaActual=";
	private static final String LAST_PART = "&initialLetter=&language=en&name=&lastname=&department=";

	public CollectUrls(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.pageSource = new PageSource();
	}

	public List<String> collectUrls(int argStart, int argEnd) {
		int endCount = argEnd;
		int startCount = argStart;
		if (Precondition.checkNonNegative(endCount)
				&& Precondition.checkNonNegative(startCount)) {
			endCount = 10;
			startCount = 1;
		}
		List<String> urlsList = new ArrayList<String>();
		for (int i = startCount; i <= endCount; i++) {
			StringBuilder urlBuilder = new StringBuilder();
			urlBuilder.append(FIRST_PART);
			urlBuilder.append(i);
			urlBuilder.append(LAST_PART);
			urlsList.add(urlBuilder.toString());
		}
		return urlsList;
	}

	public Map<String, String> getNameVsEmailUrlMap(List<String> argUrlsList) {
		List<String> urlsList = argUrlsList;
		Precondition.ensureNotEmpty(urlsList, "URLS List");
		Map<String, String> nameVsEmailUrlOuterMap = new HashMap<String, String>();
		for (String url : urlsList) {
			Map<String, String> nameVsEmailUrlInnerMap = extractNameVsEmail(url);
			if (Precondition.checkNotEmpty(nameVsEmailUrlInnerMap)) {
				nameVsEmailUrlOuterMap.putAll(nameVsEmailUrlInnerMap);
			}
		}
		return nameVsEmailUrlOuterMap;
	}

	private Map<String, String> extractNameVsEmail(String argUrl) {
		Map<String, String> nameVsEmailUrlMap = new HashMap<String, String>();
		if (Precondition.checkNotEmpty(argUrl)) {
			String htmlContent = this.getPageSource().getHtmlContent(argUrl);
			if (Precondition.checkNotEmpty(htmlContent)) {
				Source source = this.getHtmlExtractor()
						.getSourceBasedOnHtmlString(htmlContent);
				if (Precondition.checkNotNull(source)) {
					List<Element> elementsList = source
							.getAllElementsByClass("outside");
					if (Precondition.checkNotEmpty(elementsList)) {
						for (Element element : elementsList) {
							List<Element> liElementsList = element
									.getAllElements(HTMLElementName.LI);
							if (Precondition.checkNotEmpty(liElementsList)) {
								for (Element liElemnt : liElementsList) {
									String name = getName(liElemnt);
									if (Precondition.checkNotEmpty(name)) {
										List<Element> anchorTagElementsList = liElemnt
												.getAllElements(HTMLElementName.A);
										if (Precondition
												.checkNotEmpty(anchorTagElementsList)) {
											for (Element anchorTagElement : anchorTagElementsList) {
												String attributeValue = anchorTagElement
														.getAttributeValue("href");
												if (Precondition
														.checkNotEmpty(attributeValue)) {
													nameVsEmailUrlMap.put(name,
															attributeValue);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return nameVsEmailUrlMap;
	}

	private String getName(Element argLiElemnt) {
		List<Element> strongElementsList = argLiElemnt
				.getAllElements(HTMLElementName.STRONG);
		if (Precondition.checkNotEmpty(strongElementsList)) {
			Element strongElement = strongElementsList.get(0);
			if (Precondition.checkNotNull(strongElement)) {
				return strongElement.getContent().getTextExtractor().toString();
			}
		}
		return null;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public PageSource getPageSource() {
		return this.pageSource;
	}

}
