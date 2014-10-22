package wasedamba.jp;

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
					.getAllElementsByClass("name-link");
			if (Precondition.checkNotEmpty(elementsList)) {
				Map<String, String> nameVsEmailUrlMap = new HashMap<String, String>();
				for (Element boxElement : elementsList) {
					List<Element> anchorTagElementsList = boxElement
							.getAllElements(HTMLElementName.A);
					if (Precondition.checkNotEmpty(anchorTagElementsList)) {
						for (Element anchorTagElement : anchorTagElementsList) {
							String attributeValue = anchorTagElement
									.getAttributeValue("href");
							if (Precondition.checkNotEmpty(attributeValue)) {
								String name = anchorTagElement.getContent()
										.getTextExtractor().toString();
								if (Precondition.checkNotEmpty(name)) {
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
