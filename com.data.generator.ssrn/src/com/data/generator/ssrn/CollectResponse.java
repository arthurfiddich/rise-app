package com.data.generator.ssrn;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class CollectResponse {

	private final HtmlExtractor htmlExtractor;

	private CollectResponse(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	public Map<String, String> getNameVsUrlMap(String argPageUrl) {
		String pageUrl = Precondition.ensureNotEmpty(argPageUrl, "Page URL");
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String htmlContent = webContentDownloader.getHtmlContent(pageUrl);
		Precondition.ensureNotEmpty(htmlContent, "Html Content");
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		Precondition.ensureNotNull(source, "Source");
		List<Element> darkElementsList = source
				.getAllElementsByClass("TableRowDark");
		Map<String, String> nameVsUrlMap = new LinkedHashMap<String, String>();
		get(darkElementsList, nameVsUrlMap);
		List<Element> lightElementsList = source
				.getAllElementsByClass("TableRowLight");
		get(lightElementsList, nameVsUrlMap);
		return nameVsUrlMap;
	}

	protected void get(List<Element> darkElementsList,
			Map<String, String> nameVsUrlMap) {
		if (Precondition.checkNotEmpty(darkElementsList)) {
			for (Element darkElement : darkElementsList) {
				List<Element> tdList = darkElement.getAllElements("align",
						"left", true);
				if (Precondition.checkNotEmpty(tdList)) {
					for (Element tdElement : tdList) {
						List<Element> anchorElementsList = tdElement
								.getAllElements(HTMLElementName.A);
						if (Precondition.checkNotEmpty(anchorElementsList)) {
							for (Element anchorTagElement : anchorElementsList) {
								String hrefValue = anchorTagElement
										.getAttributeValue("href");
								String name = tdElement.getContent()
										.getTextExtractor().toString();
								if (Precondition.checkNotEmpty(hrefValue)
										&& Precondition.checkNotEmpty(name)) {
									nameVsUrlMap.put(name, hrefValue);
								}
							}
						}
					}
				}
			}
		}
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

}
