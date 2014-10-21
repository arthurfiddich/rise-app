package facultybio.haas.berkeley.edu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import www.iese.edu.PageSource;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;

public class CollectUrls {

	private final HtmlExtractor htmlExtractor;
	private final PageSource pageSource;
	private static final String HOME_PAGE_URL = "http://facultybio.haas.berkeley.edu";

	public CollectUrls(HtmlExtractor argHtmlExtractor, PageSource argPageSource) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.pageSource = argPageSource;
	}

	public Map<String, String> getNameVsEmailUrlMap(String argUrl) {
		String url = Precondition.ensureNotEmpty(argUrl, "URL");
		String htmlContent = this.getPageSource().getHtmlContent(url);
		Precondition.ensureNotEmpty(htmlContent, "Html Content");
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		Precondition.ensureNotNull(source, "Html Source");
		List<Element> elementsList = source.getAllElementsByClass("content");
		Precondition.ensureNotEmpty(elementsList, "Elements List");
		Map<String, String> nameVsNameVsEmailUrlMap = new HashMap<String, String>();
		for (Element element : elementsList) {
			List<Element> anchorTagElementsList = element
					.getAllElementsByClass("blue");
			Precondition.ensureNotEmpty(anchorTagElementsList,
					"Anchor Tag Elements List");
			for (Element anchorTagElement : anchorTagElementsList) {
				String name = anchorTagElement.getContent().getTextExtractor()
						.toString();
				String attributeValue = anchorTagElement
						.getAttributeValue("href");
				if (Precondition.checkNotEmpty(attributeValue)) {
					String email = HOME_PAGE_URL + attributeValue;
					if (Precondition.checkNotEmpty(name)
							&& Precondition.checkNotEmpty(email)) {
						nameVsNameVsEmailUrlMap.put(name, email);
					}
				}
			}
		}
		return nameVsNameVsEmailUrlMap;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public PageSource getPageSource() {
		return this.pageSource;
	}

}
