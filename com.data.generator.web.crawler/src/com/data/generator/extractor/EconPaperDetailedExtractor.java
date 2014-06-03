package com.data.generator.extractor;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generator.util.Precondition;

public class EconPaperDetailedExtractor {

	private HtmlExtractor htmlExtractor;
	private String econPageLink = "http://econpapers.repec.org";

	public EconPaperDetailedExtractor(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	public Map<String, String> extractNamesInPage(String argInputFileName) {
		String inputFileName = Precondition.ensureNotEmpty(argInputFileName,
				"Input File Name");
		return extractNamesInPage(new File(inputFileName));
	}

	public Map<String, String> extractNamesInPageBasedOnHtml(String argHtmlToken) {
		String htmlToken = Precondition.ensureNotEmpty(argHtmlToken,
				"Html Token");
		Source source = this.htmlExtractor
				.getSourceBasedOnHtmlString(htmlToken);
		return getNamesInPage(source);
	}

	public Map<String, String> extractNamesInPage(File argInputFile) {
		File inputFile = Precondition.ensureNotNull(argInputFile, "Input File");
		Source source = this.htmlExtractor.getSource(inputFile);
		return getNamesInPage(source);
	}

	public Map<String, String> getNamesInPage(Source argSource) {
		if (Precondition.checkNotNull(argSource)) {
			List<Element> linkElements = argSource
					.getAllElements(HTMLElementName.A);
			Map<String, String> labelVsPageLinksMap = new LinkedHashMap<String, String>();
			for (Element linkElement : linkElements) {
				String href = linkElement.getAttributeValue("href");
				if (Precondition.checkNotEmpty(href)
						&& href.startsWith("/RAS/p")) {
					StringBuilder linkBuilder = new StringBuilder();
					linkBuilder.append(econPageLink);
					String label = linkElement.getContent().getTextExtractor()
							.toString();
					linkBuilder.append(href);
					labelVsPageLinksMap.put(linkBuilder.toString(), label);
				}
			}
			return labelVsPageLinksMap;
		}
		return null;
	}

}
