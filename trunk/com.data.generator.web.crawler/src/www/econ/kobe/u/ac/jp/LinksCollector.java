package www.econ.kobe.u.ac.jp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class LinksCollector {
	private static final String HOME_PAGE_URL = "http://www.econ.kobe-u.ac.jp/en/people/index.html";
	private static final String VIEW_PAGE_URL_PREFIX = "http://www.econ.kobe-u.ac.jp/en/people/";
	private final HtmlExtractor htmlExtractor;

	public LinksCollector(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public Map<String, String> getNameVSEmailUrlMap(String argHomePageUrl) {
		String homePageUrl = Precondition.ensureNotEmpty(argHomePageUrl,
				"Home Page URL");
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String htmlContent = webContentDownloader.getHtmlContent(homePageUrl);
		Precondition.ensureNotEmpty(htmlContent, "Html Content");
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		Map<String, String> nameVsEmailUrlMap = new HashMap<String, String>();
		Precondition.ensureNotNull(source, "Source");
		List<Element> leftElementsList = source
				.getAllElementsByClass("list_left");
		Map<String, String> nameVsLeftEmailUrlMap = getMap(leftElementsList);
		if (Precondition.checkNotEmpty(nameVsLeftEmailUrlMap)) {
			nameVsEmailUrlMap.putAll(nameVsLeftEmailUrlMap);
		}

		List<Element> rightElementsList = source
				.getAllElementsByClass("list_right");
		Map<String, String> nameVsRightEmailUrlMap = getMap(rightElementsList);
		if (Precondition.checkNotEmpty(nameVsRightEmailUrlMap)) {
			nameVsEmailUrlMap.putAll(nameVsRightEmailUrlMap);
		}
		return nameVsRightEmailUrlMap;
	}

	protected Map<String, String> getMap(List<Element> argElementsList) {
		Map<String, String> nameVsEmailUrlMap = new HashMap<String, String>();
		if (Precondition.checkNotEmpty(argElementsList)) {
			for (Element leftElement : argElementsList) {
				List<Element> anchorTagElementsList = leftElement
						.getAllElements(HTMLElementName.A);
				if (Precondition.checkNotEmpty(anchorTagElementsList)) {
					for (Element anchorTagElement : anchorTagElementsList) {
						String name = anchorTagElement.getContent()
								.getTextExtractor().toString();
						String attributeValue = anchorTagElement
								.getAttributeValue("href");
						if (Precondition.checkNotEmpty(name)
								&& Precondition.checkNotEmpty(attributeValue)) {
							String emailUrl = VIEW_PAGE_URL_PREFIX
									+ attributeValue;
							nameVsEmailUrlMap.put(name, emailUrl);
						}
					}
				}
			}
		}
		return nameVsEmailUrlMap;
	}
}
