package www.albany.edu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import www.iese.edu.PageSource;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;

public class CollectUrls {
	private final HtmlExtractor htmlExtractor;
	private final PageSource pageSource;
	private static final String HOME_PAGE_URL = "http://www.albany.edu";

	public CollectUrls(HtmlExtractor argHtmlExtractor, PageSource argPageSource) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.pageSource = argPageSource;
	}

	public Map<String, String> getDepartmentVsNameVsEmailrlMap(String argUrl) {
		String url = Precondition.ensureNotEmpty(argUrl, "URL");
		String htmlContent = this.getPageSource().getHtmlContent(url);
		Precondition.ensureNotEmpty(htmlContent, "Html Content");
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		if (Precondition.checkNotNull(source)) {
			List<Element> elementsList = source
					.getAllElementsByClass("content-box");
			if (Precondition.checkNotEmpty(elementsList)) {
				Map<String, String> nameVsEmailUrlMap = new HashMap<String, String>();
				for (Element boxElement : elementsList) {
					List<Element> ulElementsList = boxElement
							.getAllElements(HTMLElementName.UL);
					if (Precondition.checkNotEmpty(ulElementsList)) {
						for (Element ulElement : ulElementsList) {
							String ulAttributeValue = ulElement
									.getAttributeValue("style");
							if (Precondition.checkNotEmpty(ulAttributeValue)) {
								List<Element> anchorTagElementsList = ulElement
										.getAllElements(HTMLElementName.A);
								if (Precondition
										.checkNotEmpty(anchorTagElementsList)) {
									for (Element anchorTagElement : anchorTagElementsList) {
										String attributeValue = anchorTagElement
												.getAttributeValue("href");
										if (Precondition
												.checkNotEmpty(attributeValue)) {
											String email = HOME_PAGE_URL
													+ attributeValue;
											String name = anchorTagElement
													.getContent()
													.getTextExtractor()
													.toString();
											if (Precondition
													.checkNotEmpty(email)
													&& Precondition
															.checkNotEmpty(name)) {
												nameVsEmailUrlMap.put(name,
														email);
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
