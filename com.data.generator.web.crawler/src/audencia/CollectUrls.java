package audencia;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class CollectUrls {

	private static final String HOME_PAGE_URL = "http://www.audencia.com/";
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
				.getAllElementsByClass("grey-bloc-two-cols");
		Precondition.checkNotEmpty(elementsList);
		Map<String, String> nameVsEmailUrlMap = new LinkedHashMap<String, String>();
		for (Element divElement : elementsList) {
			List<Element> anchorTagElementsList = divElement
					.getAllElements(HTMLElementName.A);
			if (Precondition.checkNotEmpty(anchorTagElementsList)) {
				for (Element anchorTagElement : anchorTagElementsList) {
					String hrefValue = anchorTagElement
							.getAttributeValue("href");
					if (Precondition.checkNotEmpty(hrefValue)
							&& !hrefValue.equals("#")) {
						String emailUrl = HOME_PAGE_URL + hrefValue;
						String name = anchorTagElement.getContent()
								.getTextExtractor().toString();
						if (Precondition.checkNotEmpty(emailUrl)
								&& Precondition.checkNotEmpty(name)) {
							nameVsEmailUrlMap.put(name, emailUrl);
						}
					}
				}
			}
		}
		return nameVsEmailUrlMap;
	}
}
