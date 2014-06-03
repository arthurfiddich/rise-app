package com.data.generator.extractor;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generato.econpapers.HtmlExtractorConstants;
import com.data.generator.custom.annotation.EconPaper;
import com.data.generator.util.Precondition;

@EconPaper(priority = 1)
public class EconPaperListViewExtractor {

	private HtmlExtractor htmlExtractor;
	private String econPageLink = "http://econpapers.repec.org";

	public EconPaperListViewExtractor(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	public Map<String, String> getListViewLinks(String argInputFileName) {
		String inputFileName = Precondition.ensureNotEmpty(argInputFileName,
				"Input File Name");
		return getListViewLinks(new File(inputFileName));
	}

	public Map<String, String> getListViewLinksBasedOnHtml(String argHtmlToken) {
		String htmlToken = Precondition.ensureNotEmpty(argHtmlToken,
				"Html Token");
		Source source = this.htmlExtractor
				.getSourceBasedOnHtmlString(htmlToken);
		return getListViewLinks(source);
	}

	public Map<String, String> getListViewLinks(File argInputFile) {
		File inputFile = Precondition.ensureNotNull(argInputFile, "Input File");
		Source source = this.htmlExtractor.getSource(inputFile);
		return getListViewLinks(source);
	}

	private Map<String, String> getListViewLinks(Source argSource) {
		if (Precondition.checkNotNull(argSource)) {
			List<Element> linkElementsList = argSource
					.getAllElementsByClass(HtmlExtractorConstants.ISSUE_LINKS);
			if (Precondition.checkNotEmpty(linkElementsList)) {
				Map<String, String> labelVsLinkMap = new LinkedHashMap<String, String>();
				for (Element element : linkElementsList) {
					populateListViewLinks(element, labelVsLinkMap);
				}
				return labelVsLinkMap;
			}
		}
		return null;
	}

	private void populateListViewLinks(Element argElement,
			Map<String, String> argLabelVsLinkMap) {
		if (Precondition.checkNotNull(argElement)
				&& Precondition.checkNotNull(argLabelVsLinkMap)) {
			List<Element> linkElements = argElement
					.getAllElements(HTMLElementName.A);
			put(argLabelVsLinkMap, linkElements);
		}
	}

	protected void put(Map<String, String> argLabelVsLinkMap,
			List<Element> linkElements) {
		for (Element linkElement : linkElements) {
			String href = linkElement.getAttributeValue("href");
			if (Precondition.checkNotEmpty(href)) {
				StringBuilder linkBuilder = new StringBuilder();
				String label = linkElement.getContent().getTextExtractor()
						.toString();
				linkBuilder.append(econPageLink);
				linkBuilder.append(href);
				argLabelVsLinkMap.put(label, linkBuilder.toString());
			}
		}
	}
}
