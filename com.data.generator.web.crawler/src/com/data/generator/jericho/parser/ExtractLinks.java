package com.data.generator.jericho.parser;

import java.net.URL;
import java.util.List;

import org.apache.log4j.jmx.Agent;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.HTMLElements;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.MicrosoftConditionalCommentTagTypes;
import net.htmlparser.jericho.PHPTagTypes;
import net.htmlparser.jericho.Source;

public class ExtractLinks {
	public static void main(String argHtmlToken) throws Exception {
		String sourceUrlString = "./output/input.html";
		// String sourceUrlString = argHtmlToken;
		if (sourceUrlString.indexOf(':') == -1)
			sourceUrlString = "file:" + sourceUrlString;
		MicrosoftConditionalCommentTagTypes.register();
		PHPTagTypes.register();
		PHPTagTypes.PHP_SHORT.deregister(); // remove PHP short tags for this
											// example otherwise they override
											// processing instructions
		MasonTagTypes.register();
		Source source = new Source(new URL(sourceUrlString));
		// Source source = new Source(sourceUrlString);

		// Call fullSequentialParse manually as most of the source will be
		// parsed.
		source.fullSequentialParse();

		System.out.println("\nLinks to other documents:");
		getAllElementsByClassName("issuelinks", source);
		// hrefList(source);
	}

	protected static void hrefList(Source argSource) {
		List<Element> linkElements = argSource
				.getAllElements(HTMLElementName.A);
		for (Element linkElement : linkElements) {
			String href = linkElement.getAttributeValue("href");
			if (href == null) {
				continue;
			}
			if (href.startsWith("/RAS/p")) {
				// A element can contain other tags so need to extract the text
				// from it:
				String label = linkElement.getContent().getTextExtractor()
						.toString();
				// System.out.println(label + " <" + href + '>');
				System.out.println(label);
			}
		}
	}

	protected static void hrefList(Element argElement) {
		list(argElement);
	}

	protected static void list(Element argElement) {
		List<Element> linkElements = argElement
				.getAllElements(HTMLElementName.A);
		for (Element linkElement : linkElements) {
			String href = linkElement.getAttributeValue("href");
			if (href == null) {
				continue;
			}
			// A element can contain other tags so need to extract the text from
			// it:
			String label = linkElement.getContent().getTextExtractor()
					.toString();
			System.out.println(label + " <" + href + '>');
		}
	}

	public static void getAllElementsByClassName(String argClassName,
			Source argSource) {
		Source source = argSource;
		List<Element> elementsList = source.getAllElementsByClass(argClassName);
		for (Element element : elementsList) {
			hrefList(element);
		}
	}

	public static void main(String[] args) throws Exception {
		ExtractLinks.main("");
	}
}