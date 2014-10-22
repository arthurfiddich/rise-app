package www.albany.edu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;
import www.iese.edu.PageSource;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;

public class UrlCollector {
	private final HtmlExtractor htmlExtractor;
	private final PageSource pageSource;
	private static final String HOME_PAGE_URL = "http://www.albany.edu/rockefeller/";

	public UrlCollector(HtmlExtractor argHtmlExtractor, PageSource argPageSource) {
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
					.getAllElementsByClass("text-box");
			if (Precondition.checkNotEmpty(elementsList)) {
				Map<String, String> nameVsEmailUrlMap = new HashMap<String, String>();
				for (Element boxElement : elementsList) {
					List<Element> ulElementsList = boxElement
							.getAllElements(HTMLElementName.LI);
					if (Precondition.checkNotEmpty(ulElementsList)) {
						for (Element liElement : ulElementsList) {
							StartTag startTag = liElement
									.getFirstStartTag(HTMLElementName.A);
							if (Precondition.checkNotNull(startTag)) {
								String attributeValue = startTag
										.getAttributeValue("href");
								String name = liElement.getContent()
										.getTextExtractor().toString();
								if (Precondition.checkNotEmpty(name)) {
									if (Precondition
											.checkNotEmpty(attributeValue)
											&& !attributeValue
													.startsWith("http")) {
										attributeValue = HOME_PAGE_URL
												+ attributeValue;
									}
									nameVsEmailUrlMap.put(name, attributeValue);
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
