package njubs.nju.edu.cn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class CollectUrls {
	private final HtmlExtractor htmlExtractor;

	public CollectUrls(HtmlExtractor argHtmlExtractor) {
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
		Precondition.ensureNotNull(source, "Source");
		List<Element> elementsList = source
				.getAllElementsByClass("tag_content");
		Precondition.checkNotEmpty(elementsList);
		return getNameVsEmailUrlMap(elementsList);
	}

	private Map<String, String> getNameVsEmailUrlMap(
			List<Element> argElementsList) {
		Map<String, String> nameVSEmailUrlMap = new HashMap<String, String>();
		for (Element element : argElementsList) {
			String attributeValue = element.getAttributeValue("id");
			if (Precondition.checkNotEmpty(attributeValue)
					&& attributeValue.equals("tag_content_6")) {
				List<Element> anchorTagElementsList = element
						.getAllElements(HTMLElementName.A);
				if (Precondition.checkNotEmpty(anchorTagElementsList)) {
					for (Element anchorTagElement : anchorTagElementsList) {
						String hrefValue = anchorTagElement
								.getAttributeValue("href");
						String name = anchorTagElement.getContent()
								.getTextExtractor().toString();
						if (Precondition.checkNotEmpty(hrefValue)
								&& Precondition.checkNotEmpty(name)) {
							nameVSEmailUrlMap.put(name, hrefValue);
						}
					}
				}
				return nameVSEmailUrlMap;
			}
		}
		return null;
	}
}
