package georgetown;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	public Map<String, Map<String, String>> collectNameVsUrl(
			String argHomePageUrl) {
		String homePageUrl = Precondition.ensureNotEmpty(argHomePageUrl,
				"Home Page URL");
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String htmlContent = webContentDownloader.getHtmlContent(homePageUrl);
		Precondition.ensureNotEmpty(htmlContent, "Html Content");
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		Precondition.ensureNotNull(source, "Html Source");
		List<Element> alternativeElementsList = source
				.getAllElementsByClass("ListAlternate");
		Map<String, Map<String, String>> nameVsEmailUrlMap = new LinkedHashMap<String, Map<String, String>>();
		// Map<String, String> nameVsEmailUrlMap = new LinkedHashMap<String,
		// String>();
		Map<String, String> alternateNameVsEmailUrlMap = getNameVsEmailUrlMap(alternativeElementsList);
		if (Precondition.checkNotEmpty(alternateNameVsEmailUrlMap)) {
			// nameVsEmailUrlMap.putAll(alternateNameVsEmailUrlMap);
			nameVsEmailUrlMap.put("ListAlternate", alternateNameVsEmailUrlMap);
		}
		List<Element> primaryElementsList = source
				.getAllElementsByClass("ListPrimary");
		Map<String, String> primaryNameVsEmailUrlMap = getNameVsEmailUrlMap(primaryElementsList);
		if (Precondition.checkNotEmpty(primaryNameVsEmailUrlMap)) {
			// nameVsEmailUrlMap.putAll(primaryNameVsEmailUrlMap);
			nameVsEmailUrlMap.put("ListPrimary", primaryNameVsEmailUrlMap);
		}
		return nameVsEmailUrlMap;
	}

	protected Map<String, String> getNameVsEmailUrlMap(
			List<Element> alternativeElementsList) {
		Map<String, String> nameVsEmailUrlMap = new LinkedHashMap<String, String>();
		if (Precondition.checkNotEmpty(alternativeElementsList)) {
			for (Element alternativeElement : alternativeElementsList) {
				List<Element> anchorTagElementsList = alternativeElement
						.getAllElements(HTMLElementName.A);
				if (Precondition.checkNotEmpty(anchorTagElementsList)) {
					for (Element anchorTagElement : anchorTagElementsList) {
						String attributeValue = anchorTagElement
								.getAttributeValue("href");
						if (Precondition.checkNotEmpty(attributeValue)
								&& attributeValue
										.startsWith("http://explore.georgetown.edu/people/")) {
							String name = anchorTagElement.getContent()
									.getTextExtractor().toString();
							if (Precondition.checkNotEmpty(name)) {
								nameVsEmailUrlMap.put(name, attributeValue);
							}
						}
					}
				}
			}
		}
		return nameVsEmailUrlMap;
	}

	public static void main(String[] args) {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		CollectUrls collectUrls = new CollectUrls(htmlExtractor);
		Map<String, Map<String, String>> map = collectUrls
				.collectNameVsUrl("http://explore.georgetown.edu/faculty/index.cfm?Action=List&Letter=A#_ga=1.262893764.919768958.1409213630");
		System.out.println(map.size());
		Iterator<Entry<String, Map<String, String>>> iterator = map.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, Map<String, String>> entry = iterator.next();
			Map<String, String> valueMap = entry.getValue();
			Iterator<Entry<String, String>> innerIterator = valueMap.entrySet()
					.iterator();
			while (innerIterator.hasNext()) {
				Entry<String, String> innerEntry = innerIterator.next();
				String value = innerEntry.getValue();
				if (value.isEmpty()) {
					System.out.println(entry.getKey());
				}
				System.out.println(value);
			}
		}
		System.exit(0);
	}
}
