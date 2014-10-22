package www8.gsb.columbia.edu;

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

public class CollectUrls {
	private final HtmlExtractor htmlExtractor;
	private final PageSource pageSource;
	private static final String HOME_PAGE_URL = "http://www8.gsb.columbia.edu/";

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
					.getAllElementsByClass("fac-grid-item");
			Map<String, String> nameVsEmailUrlMap = new HashMap<String, String>();
			if (Precondition.checkNotEmpty(elementsList)) {
				for (Element element : elementsList) {
					Element nameElement = element
							.getFirstElementByClass("name");
					if (Precondition.checkNotNull(nameElement)) {
						StartTag startTag = nameElement
								.getFirstStartTag(HTMLElementName.A);
						if (Precondition.checkNotNull(startTag)) {
							String name = nameElement.getContent()
									.getTextExtractor().toString();
							String attibuteValue = startTag
									.getAttributeValue("href");
							if (Precondition.checkNotEmpty(attibuteValue)
									&& Precondition.checkNotEmpty(name)) {
								String emailUrl = HOME_PAGE_URL + attibuteValue;
								nameVsEmailUrlMap.put(name, emailUrl);
							}
						}
					}
				}
			}
			return nameVsEmailUrlMap;
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
