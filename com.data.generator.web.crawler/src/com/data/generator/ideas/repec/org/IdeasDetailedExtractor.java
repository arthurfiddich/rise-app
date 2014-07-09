package com.data.generator.ideas.repec.org;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generato.econpapers.HtmlExtractorConstants;
import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;

public class IdeasDetailedExtractor {

	private final HtmlExtractor htmlExtractor;

	public IdeasDetailedExtractor(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public Map<String, Map<String, String>> getPageNameVsLinksMap(
			String argHtmlToken) {
		if (Precondition.checkNotEmpty(argHtmlToken)) {
			Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
					argHtmlToken);
			if (Precondition.checkNotNull(source)) {
				List<Element> elementList = source
						.getAllElements(HTMLElementName.A);
				if (Precondition.checkNotEmpty(elementList)) {
					return build(elementList);
				}
			}
		}
		return null;
	}

	protected Map<String, Map<String, String>> build(List<Element> elementList) {
		Map<String, Map<String, String>> pageNameVsLinksMap = new HashMap<String, Map<String, String>>();
		boolean foundKey = false;
		String key = null;
		Map<String, String> linkVsNameMap = null;
		for (Element element : elementList) {
			if (foundKey) {
				linkVsNameMap = new HashMap<String, String>();
				foundKey = false;
			}
			Attribute attribute = this.getNameAttribute(element);
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
				buildLinkVsNameMap(linkVsNameMap, element);
			}
		}
		if (Precondition.checkNotEmpty(key)
				&& Precondition.checkNotEmpty(linkVsNameMap)) {
			pageNameVsLinksMap.put(key, linkVsNameMap);
		}
		return pageNameVsLinksMap;
	}

	protected void buildLinkVsNameMap(Map<String, String> linkVsNameMap,
			Element element) {
		Attribute hrefAttribute = element.getAttributes().get(
				HtmlExtractorConstants.HREF);
		if (Precondition.checkNotNull(hrefAttribute)
				&& Precondition.checkNotEmpty(hrefAttribute.getValue())) {
			String value = hrefAttribute.getValue();
			if (value.startsWith(HtmlExtractorConstants.IDEAS_LINK_CONSTANT[0])
					|| value.startsWith(HtmlExtractorConstants.IDEAS_LINK_CONSTANT[1])) {
				String content = element.getContent().getTextExtractor()
						.toString();
				value = prepareIdeasLink(value);
				if (Precondition.checkNotEmpty(value)) {
					linkVsNameMap.put(value, content);
				}
			}
		}
	}

	private String prepareIdeasLink(String argValue) {
		if (Precondition.checkNotEmpty(argValue)) {
			StringBuilder linkBuilder = new StringBuilder();
			linkBuilder.append(HtmlExtractorConstants.HOME_PAGE_LINK);
			linkBuilder.append(argValue);
			return linkBuilder.toString();
		}
		return null;
	}

	protected Attribute getNameAttribute(Element element) {
		return element.getAttributes().get("Name");
	}
}
