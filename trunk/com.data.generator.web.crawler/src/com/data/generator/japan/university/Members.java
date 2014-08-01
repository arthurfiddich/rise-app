package com.data.generator.japan.university;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generato.econpapers.HtmlExtractorConstants;
import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class Members {

	private final HtmlExtractor htmlExtractor;

	public Members(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public Map<String, String> getMemeberNameVsLinkMap(String argUrl) {
		String url = Precondition.ensureNotEmpty(argUrl, "URL");
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String htmlToken = webContentDownloader.getHtmlContent(url);
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlToken);
		List<Element> elementsList = source.getAllElementsByClass("members");
		if (Precondition.checkNotEmpty(elementsList)) {
			Map<String, String> linkVsNameMap = new LinkedHashMap<String, String>();
			for (Element element : elementsList) {
				List<Element> anchorElementsList = element
						.getAllElements(HTMLElementName.A);
				if (Precondition.checkNotEmpty(anchorElementsList)) {
					for (Element anchorElemenet : anchorElementsList) {
						Attribute hrefAttribute = anchorElemenet
								.getAttributes().get(
										HtmlExtractorConstants.HREF);
						if (Precondition.checkNotNull(hrefAttribute)
								&& Precondition.checkNotEmpty(hrefAttribute
										.getValue())) {
							String value = hrefAttribute.getValue();
							String content = anchorElemenet.getContent()
									.getTextExtractor().toString();
							value = prepareIdeasLink(value);
							if (Precondition.checkNotEmpty(value)) {
								linkVsNameMap.put(value, content);
							}
						}
					}
				}
			}
			return linkVsNameMap;
		}
		return null;
	}

	private String prepareIdeasLink(String argValue) {
		if (Precondition.checkNotEmpty(argValue)) {
			StringBuilder linkBuilder = new StringBuilder();
			linkBuilder.append(HtmlExtractorConstants.JAPANESE_HOME_PAGE_LINK);
			linkBuilder.append(argValue);
			return linkBuilder.toString();
		}
		return null;
	}
}
