package com.data.generator.jericho.parser;

import java.net.URL;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.MicrosoftConditionalCommentTagTypes;
import net.htmlparser.jericho.PHPTagTypes;
import net.htmlparser.jericho.Source;

public class ExtractTd {
	public static void main(String argHtmlToken) throws Exception {
//		String sourceUrlString = "./output/input.html";
		String sourceUrlString = argHtmlToken;
		if (sourceUrlString.indexOf(':') == -1)
			sourceUrlString = "file:" + sourceUrlString;
		MicrosoftConditionalCommentTagTypes.register();
		PHPTagTypes.register();
		PHPTagTypes.PHP_SHORT.deregister(); // remove PHP short tags for this
											// example otherwise they override
											// processing instructions
		MasonTagTypes.register();
//		Source source = new Source(new URL(sourceUrlString));
		Source source = new Source(sourceUrlString);

		// Call fullSequentialParse manually as most of the source will be
		// parsed.
		source.fullSequentialParse();

		System.out.println("\nTD: ");
		List<Element> linkElements = source.getAllElements(HTMLElementName.SCRIPT);
		for (Element linkElement : linkElements) {
			String label = linkElement.getContent().getTextExtractor()
					.toString();
			System.out.println(label);
		}
	}
	public static void main(String[] args) {
		try {
			ExtractTd.main("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
