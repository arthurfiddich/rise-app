package com.data.generato.econpapers.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.HTMLElements;
import net.htmlparser.jericho.Source;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class WebContentDownloaderTest {

	public static void main(String[] args) {
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String html = webContentDownloader
				.getHtmlContent("http://ideas.repec.org/i/eall.html");
		// System.out.println(html);
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		Source source = htmlExtractor.getSourceBasedOnHtmlString(html);
		List<Element> elementsList = source.getAllElements(HTMLElementName.A);
		Map<String, Map<String, String>> pageNameVsLinksMap = new HashMap<String, Map<String, String>>();
		boolean foundKey = false;
		String key = null;
		Map<String, String> linkVsNameMap = null;
		for (Element element : elementsList) {
			if (foundKey) {
				linkVsNameMap = new HashMap<String, String>();
				foundKey = false;
			}
			Attribute attribute = element.getAttributes().get("Name");
			if (Precondition.checkNotNull(attribute)) {
				if (Precondition.checkNotEmpty(key)
						&& Precondition.checkNotNull(linkVsNameMap)) {
					pageNameVsLinksMap.put(key, linkVsNameMap);
				}
				key = attribute.getValue().trim();
				if (Precondition.checkNotEmpty(key) && key.length() == 1) {
					foundKey = true;
				}
			} else {
				Attribute hrefAttribute = element.getAttributes().get("HREF");
				if (Precondition.checkNotNull(hrefAttribute)
						&& Precondition.checkNotEmpty(hrefAttribute.getValue())) {
					String value = hrefAttribute.getValue();
					if (value.startsWith("/e/p") || value.startsWith("/f/p")) {
						String content = element.getContent()
								.getTextExtractor().toString();
						linkVsNameMap.put(value, content);
					}
				}
			}

		}
		System.out.println(pageNameVsLinksMap.size());
		System.exit(0);
	}
}
